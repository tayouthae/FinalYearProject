package org.notalgodemons.tokenservice;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageReader;
import org.notalgodemons.tokenservice.wrappers.ERC20;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.web3j.utils.Convert;

@SpringBootApplication
public class TokenserviceApplication {

    @Qualifier("getCreatorWallet")
    @Autowired
    private String creatorWallet;
    @Qualifier("getContractAddress")
    @Autowired
    private String contractAddress;
    @Qualifier("getACCOUNT_SID")
    @Autowired
    private String ACCOUNT_SID;
    @Qualifier("getAUTH_TOKEN")
    @Autowired
    private String AUTH_TOKEN;
    @Qualifier("getPRIVATE_KEY")
    @Autowired
    private String PRIVATE_KEY;
    @Qualifier("getRECIPIENT")
    @Autowired
    private String RECIPIENT;
    @Qualifier("getTwilioNo")
    @Autowired
    private String TwilioNo;

    private ERC20 eip20;
    private Web3j web3j;

    public static final String ACCOUNT_SID = ACCOUNT_SID;
    public static final String AUTH_TOKEN = AUTH_TOKEN;
    public static MessageReader messageReader;

    private final static String PRIVATE_KEY = PRIVATE_KEY;

    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);

    private final static String RECIPIENT = RECIPIENT;

    public static void main(String[] args) {
        SpringApplication.run(TokenserviceApplication.class, args);
    }

    private Credentials getCredentialsFromPrivateKey() {
        return Credentials.create(PRIVATE_KEY);
    }

    @PostConstruct
    public void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        messageReader = Message.reader()
                .setTo(new com.twilio.type.PhoneNumber(TwilioNo));

        web3j = Web3j.build(new HttpService());
        TransactionManager transactionManager = new RawTransactionManager(
                web3j,
                getCredentialsFromPrivateKey());

//        Transfer transfer = new Transfer(web3j, transactionManager);

//        try {
//
//            TransactionReceipt transactionReceipt = transfer.sendFunds(
//                    RECIPIENT,
//                    BigDecimal.ONE,
//                    Convert.Unit.ETHER,
//                    GAS_PRICE,
//                    GAS_LIMIT).send();
//            System.out.print("Transaction = " + transactionReceipt.getTransactionHash());
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        Credentials creatorCredentials = Credentials.create(creatorWallet);
        eip20 = eip20.load(contractAddress, web3j, creatorCredentials, ManagedTransaction.GAS_PRICE,
                Contract.GAS_LIMIT);
    }

    @Bean
    public Web3j getWeb3j() {
        return web3j;
    }

    @Bean
    public ERC20 getEip20Creator() {
        return eip20;
    }

}
