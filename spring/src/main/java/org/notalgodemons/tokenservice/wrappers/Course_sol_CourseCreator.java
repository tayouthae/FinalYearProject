package org.notalgodemons.tokenservice.wrappers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
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
public class Course_sol_CourseCreator extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610550806100326000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80630ee2cb10146100465780631bd4edf31461006f5780632e3396461461008d575b600080fd5b6000546001600160a01b03165b6040516001600160a01b03909116815260200160405180910390f35b336000908152600160205260409020546001600160a01b0316610053565b61005361009b36600461010a565b600080826040516100ab906100fd565b908152602001604051809103906000f0801580156100cd573d6000803e3d6000fd5b5033600090815260016020526040902080546001600160a01b0319166001600160a01b0383161790559392505050565b6103f78061012483390190565b60006020828403121561011c57600080fd5b503591905056fe608060405234801561001057600080fd5b506040516103f73803806103f783398101604081905261002f91610037565b600055610050565b60006020828403121561004957600080fd5b5051919050565b6103988061005f6000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80631a6c5b631461003b578063d99a8dc314610064575b600080fd5b61004e6100493660046101db565b610079565b60405161005b919061020b565b60405180910390f35b610077610072366004610276565b610113565b005b6001602052600090815260409020805461009290610327565b80601f01602080910402602001604051908101604052809291908181526020018280546100be90610327565b801561010b5780601f106100e05761010080835404028352916020019161010b565b820191906000526020600020905b8154815290600101906020018083116100ee57829003601f168201915b505050505081565b60005442101561013f57336000908152600160209081526040909120825161013d92840190610142565b505b50565b82805461014e90610327565b90600052602060002090601f01602090048101928261017057600085556101b6565b82601f1061018957805160ff19168380011785556101b6565b828001600101855582156101b6579182015b828111156101b657825182559160200191906001019061019b565b506101c29291506101c6565b5090565b5b808211156101c257600081556001016101c7565b6000602082840312156101ed57600080fd5b81356001600160a01b038116811461020457600080fd5b9392505050565b600060208083528351808285015260005b818110156102385785810183015185820160400152820161021c565b8181111561024a576000604083870101525b50601f01601f1916929092016040019392505050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561028857600080fd5b813567ffffffffffffffff808211156102a057600080fd5b818401915084601f8301126102b457600080fd5b8135818111156102c6576102c6610260565b604051601f8201601f19908116603f011681019083821181831017156102ee576102ee610260565b8160405282815287602084870101111561030757600080fd5b826020860160208301376000928101602001929092525095945050505050565b600181811c9082168061033b57607f821691505b6020821081141561035c57634e487b7160e01b600052602260045260246000fd5b5091905056fea2646970667358221220811662cf65a83fa2cb6a7359037ecccc56a6d0e61acda94ee865e954a436e37064736f6c634300080b0033a2646970667358221220598df25098532998807f31ab10fce889f621cdb1c5fd2d40c61a76d1f44f050a64736f6c634300080b0033";

    public static final String FUNC_CREATECOURSE = "createCourse";

    public static final String FUNC_FETCHCOURSEADDRESS = "fetchCourseAddress";

    public static final String FUNC_GETCREATOR = "getCreator";

    @Deprecated
    protected Course_sol_CourseCreator(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Course_sol_CourseCreator(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Course_sol_CourseCreator(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Course_sol_CourseCreator(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Course_sol_CourseCreator> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Course_sol_CourseCreator.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Course_sol_CourseCreator> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Course_sol_CourseCreator.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Course_sol_CourseCreator> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Course_sol_CourseCreator.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Course_sol_CourseCreator> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Course_sol_CourseCreator.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public RemoteCall<TransactionReceipt> createCourse(BigInteger deadline) {
        final Function function = new Function(
                FUNC_CREATECOURSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(deadline)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> fetchCourseAddress() {
        final Function function = new Function(
                FUNC_FETCHCOURSEADDRESS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> getCreator() {
        final Function function = new Function(
                FUNC_GETCREATOR, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Course_sol_CourseCreator load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Course_sol_CourseCreator(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Course_sol_CourseCreator load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Course_sol_CourseCreator(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Course_sol_CourseCreator load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Course_sol_CourseCreator(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Course_sol_CourseCreator load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Course_sol_CourseCreator(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
