package org.notalgodemons.tokenservice.wrappers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class Course_sol_Course extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506040516103f73803806103f783398101604081905261002f91610037565b600055610050565b60006020828403121561004957600080fd5b5051919050565b6103988061005f6000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80631a6c5b631461003b578063d99a8dc314610064575b600080fd5b61004e6100493660046101db565b610079565b60405161005b919061020b565b60405180910390f35b610077610072366004610276565b610113565b005b6001602052600090815260409020805461009290610327565b80601f01602080910402602001604051908101604052809291908181526020018280546100be90610327565b801561010b5780601f106100e05761010080835404028352916020019161010b565b820191906000526020600020905b8154815290600101906020018083116100ee57829003601f168201915b505050505081565b60005442101561013f57336000908152600160209081526040909120825161013d92840190610142565b505b50565b82805461014e90610327565b90600052602060002090601f01602090048101928261017057600085556101b6565b82601f1061018957805160ff19168380011785556101b6565b828001600101855582156101b6579182015b828111156101b657825182559160200191906001019061019b565b506101c29291506101c6565b5090565b5b808211156101c257600081556001016101c7565b6000602082840312156101ed57600080fd5b81356001600160a01b038116811461020457600080fd5b9392505050565b600060208083528351808285015260005b818110156102385785810183015185820160400152820161021c565b8181111561024a576000604083870101525b50601f01601f1916929092016040019392505050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561028857600080fd5b813567ffffffffffffffff808211156102a057600080fd5b818401915084601f8301126102b457600080fd5b8135818111156102c6576102c6610260565b604051601f8201601f19908116603f011681019083821181831017156102ee576102ee610260565b8160405282815287602084870101111561030757600080fd5b826020860160208301376000928101602001929092525095945050505050565b600181811c9082168061033b57607f821691505b6020821081141561035c57634e487b7160e01b600052602260045260246000fd5b5091905056fea2646970667358221220811662cf65a83fa2cb6a7359037ecccc56a6d0e61acda94ee865e954a436e37064736f6c634300080b0033";

    public static final String FUNC_FILEHASH = "fileHash";

    public static final String FUNC_SUBMIT = "submit";

    @Deprecated
    protected Course_sol_Course(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Course_sol_Course(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Course_sol_Course(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Course_sol_Course(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Course_sol_Course> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger dl) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(dl)));
        return deployRemoteCall(Course_sol_Course.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Course_sol_Course> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger dl) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(dl)));
        return deployRemoteCall(Course_sol_Course.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Course_sol_Course> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger dl) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(dl)));
        return deployRemoteCall(Course_sol_Course.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Course_sol_Course> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger dl) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(dl)));
        return deployRemoteCall(Course_sol_Course.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public RemoteCall<TransactionReceipt> fileHash(String param0) {
        final Function function = new Function(
                FUNC_FILEHASH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> submit(String file) {
        final Function function = new Function(
                FUNC_SUBMIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(file)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Course_sol_Course load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Course_sol_Course(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Course_sol_Course load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Course_sol_Course(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Course_sol_Course load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Course_sol_Course(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Course_sol_Course load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Course_sol_Course(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
