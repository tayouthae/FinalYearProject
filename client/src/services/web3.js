import Web3 from 'web3';

const detectProvider = () => {
    let provider;
    if (window.ethereum) {
        provider = window.ethereum;
    } else if (window.web3) {
        provider = window.web3.currentProvider;
    } else {
        window.alert("No Ethereum browser detected! Check out MetaMask");
    }
    return provider;
};

const provider = detectProvider();

if (provider) {
    if (provider !== window.ethereum) {
        console.error(
            "Not window.ethereum provider. Do you have multiple wallet installed ?"
        );
    }

    provider.request({
        method: "eth_requestAccounts",
    });

}
export const web3 = new Web3(provider);