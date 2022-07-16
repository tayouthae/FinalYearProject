package org.notalgodemons.tokenservice.api;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import org.notalgodemons.tokenservice.TokenserviceApplication;
import org.notalgodemons.tokenservice.wrappers.Course;
import org.notalgodemons.tokenservice.wrappers.Course_sol_Course;
import org.notalgodemons.tokenservice.wrappers.ERC20;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import org.web3j.protocol.http.HttpService;

// ssh -R 80:localhost:8080 ssh.localhost.run
@RestController
public class Eduthon {

    @Qualifier("getCreatorWallet")
    @Autowired
    private String creatorWallet;
    @Qualifier("getStudentPrivateKey")
    @Autowired
    private String studentPrivateKey;
    @Qualifier("getContractAddress")
    @Autowired
    private String contractAddress;
    @Autowired
    private Web3j web3j;
    @Autowired
    public ERC20 eip20Creator;

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    public String newWallet()
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        System.out.println("ecKeyPair\n" + ecKeyPair);
        BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
        System.out.println("\nprivateKeyInDec\n" + privateKeyInDec);
        String privateKeyInHex = privateKeyInDec.toString(16);
        System.out.println("\nprivateKeyInHex\n" + privateKeyInHex);
        return privateKeyInHex;
    }

    @RequestMapping("/newStudentWallet")
    public String newStudentWallet() {
        try {
            // web3j = Web3j.build(new HttpService("http://localhost:3000/"));

            // String privateKey = newWallet();
            String privateKey = studentPrivateKey;
            System.out.println("privateKey\n" + privateKey);
            Credentials credentials = Credentials.create(privateKey);
            System.out.println("\ngetAddress\n" + credentials.getAddress());
            // TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j,
            // Credentials.create(creatorWallet),
            // credentials.getAddress(), new BigDecimal("0.01"), Convert.Unit.ETHER).send();
            System.out.println("Hello\n" + privateKey + " " + credentials.getAddress());
            return privateKey + " " + credentials.getAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/submit/{privateKey}/{courseAddress}/{fileHash}")
    public String submit(@PathVariable String privateKey, @PathVariable String courseAddress,
            @PathVariable String fileHash) {
        System.out.println("Submitting......");
        try {
            Credentials credentials = Credentials.create(privateKey);
            TransactionManager transactionManager = new RawTransactionManager(
                    web3j,
                    credentials);
            Course_sol_Course course2 = Course_sol_Course.load(courseAddress, web3j, credentials, GAS_PRICE,
                    GAS_LIMIT);
            // Course course = Course.load(courseAddress, web3j, credentials,
            // ManagedTransaction.GAS_PRICE,
            // Contract.GAS_LIMIT);
            TransactionReceipt transactionReceipt = course2.submit(fileHash).sendAsync().get();
            System.out.print("Transaction = " + transactionReceipt.getTransactionHash());
            return transactionReceipt.getTransactionHash();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/fileHashMatch", method = RequestMethod.POST)
    public String hashFile(@RequestParam("file") MultipartFile file,
            @RequestParam("comparisonHash") String comparisonHash) {
        File temp = null;
        try {
            // System.out.println(Arrays.toString(file.getBytes()));
            temp = File.createTempFile("hashed", ".sha");
            file.transferTo(temp);
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(temp);

            byte[] data = new byte[1024];
            int read = 0;
            while ((read = fis.read(data)) != -1) {
                sha256.update(data, 0, read);
            }
            ;
            byte[] hashBytes = sha256.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hashBytes.length; i++) {
                sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            String fileHash = sb.toString();
            if (fileHash.equals(comparisonHash)) {
                return "verified";
            } else {
                return "notVerified";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/encrypt", method = RequestMethod.POST)
    public String encryptFile(@RequestParam("file") MultipartFile file) {
        try {
            File tempIn = File.createTempFile("xyz", ".pdf");
            String secretKey = "";
            for (int i = 0; i < 16; i++) {
                secretKey += (int) (Math.random() * 10);
            }

            file.transferTo(tempIn);

            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            FileInputStream inputStream = new FileInputStream(tempIn);
            byte[] inputBytes = new byte[(int) tempIn.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);
            // System.out.println(Arrays.toString(outputBytes));
            File tempOut = File.createTempFile("encrypted", ".enc");
            FileOutputStream outputStream = new FileOutputStream(tempOut);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

            IPFS ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
            NamedStreamable.InputStreamWrapper is = new NamedStreamable.InputStreamWrapper(
                    new FileInputStream(tempOut));
            MerkleNode response = ipfs.add(is).get(0);
            String responseHash = response.hash.toBase58();
            System.out.println("Hash (base 58): " + response.name.get() + " - " + response.hash.toBase58());

            System.out.println("File successfully encrypted!" + secretKey + " " + " " + responseHash);

            return (secretKey + " " + responseHash);

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/twillioTest", method = RequestMethod.POST)
    public String twillioTest() {
        try {
            ResourceSet<Message> messages = Message.reader()
                    .setTo(new com.twilio.type.PhoneNumber("+19033296557"))
                    .limit(20)
                    .read();

            List<String> AL = new ArrayList<>();
            for (Message record : messages) {
                AL.add(record.getBody());
            }
            System.out.println(AL.get(0));
            String respMessage = AL.get(0);
            respMessage = respMessage.trim();
            // String
            // respMessage="97d8cb40d55f97fa4a9dcbb9d89b159128b95043edfd835467553f3b5c69d7af
            // 0x5BA3Ad623fFcAF030760405a10eBF44fB04eD774 hi";
            String privateKey = respMessage.substring(0, respMessage.indexOf(':'));
            String courseAddress = respMessage.substring(respMessage.indexOf(':') + 1, respMessage.lastIndexOf(':'));
            String fileHash = respMessage.substring(respMessage.lastIndexOf(':') + 1);
            String transactionHash = submit(privateKey, courseAddress, fileHash);
            System.out.println(transactionHash);
            return transactionHash;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/twillioResponse", method = RequestMethod.POST)
    public String twillioResponse(@RequestParam("date") String date,
            @RequestParam("LocalDateTime") String LocalDateTime) {
        try {

            ResourceSet<Message> messages = Message.reader()
                    .setTo(new com.twilio.type.PhoneNumber("+19033296557"))
                    .limit(20)
                    .read();

            if (date == LocalDateTime) {
                Message.creator(
                        new com.twilio.type.PhoneNumber("9818629809"),
                        new com.twilio.type.PhoneNumber("+19033296557"),
                        "Sorry you have passed the submission deadline.")
                        .create();
            }

            Message.creator(
                    new com.twilio.type.PhoneNumber("9818629809"),
                    new com.twilio.type.PhoneNumber("+19033296557"),
                    "You have successfully submitted your answer sheet")
                    .create();

            System.out.print(messages);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestParam("privateKey") String privateKey,
            @RequestParam("courseAddress") String courseAddress, @RequestParam("fileHash") String fileHash) {
        String transactionHash = submit(privateKey, courseAddress, fileHash);
        System.out.println(transactionHash);
        return transactionHash;

    }

    @RequestMapping(value = "/ipfsStore", method = RequestMethod.POST)
    public String ipfsStore(@RequestParam("file") MultipartFile file) {
        File temp = null;
        try {
            temp = File.createTempFile("hashed", ".sha");
            file.transferTo(temp);
            IPFS ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
            NamedStreamable.InputStreamWrapper is = new NamedStreamable.InputStreamWrapper(new FileInputStream(temp));
            MerkleNode response = ipfs.add(is).get(0);
            String responseHash = response.hash.toBase58();
            System.out.println("Hash (base 58): " + response.name.get() + " - " + response.hash.toBase58());
            return responseHash;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}