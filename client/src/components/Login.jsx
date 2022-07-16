import React from "react";
import { Link } from "react-router-dom";
import Loader from "react-spinners/SyncLoader";

function Login(props) {

  const detectProvider = () => {
    if (props.username) {
      let provider;
      if (window.ethereum) {
        provider = window.ethereum;
      } else if (window.web3) {
        provider = window.web3.currentProvider;
      } else {
        window.alert("No Ethereum browser detected! Check out MetaMask");
      }
      return provider;
    }
    return null;
  };

  const submitHandler = (event) => {
    event.preventDefault();

    if (event.target[0].value) {
      if (event.target[0].value !== "teacher@deerwalk.edu.np") {
        return alert("Invalid Username or Password");
      }
      props.setUsername("Teacher");
  
      onLoginHandler();
    }
  };

  const onLoginHandler = async () => {
    const provider = detectProvider();
    if (provider) {
      if (provider !== window.ethereum) {
        console.error(
          "Not window.ethereum provider. Do you have multiple wallet installed ?"
        );
      }
      props.setIsConnecting(true);
      await provider.request({
        method: "eth_requestAccounts",
      });
      props.setIsConnecting(false);
    }
    props.onLogin(provider);
  };

  return (
    <>
      {!props.isConnecting ? (
        <form name='loginForm' onSubmit={submitHandler} className='loginForm'>
          <h3>Login Here</h3>

          <label for='username' className='loginLabel'>
            Username
          </label>
          <input
            type='email'
            placeholder='Email'
            id='username'
            required
            className='loginInput'
          />

          <label for='password' className='loginLabel'>
            Password
          </label>
          <input
            type='password'
            placeholder='Password'
            id='password'
            required
            className='loginInput'
          />

          <button type='submit' className='loginButton'>
            Log In
          </button>
          <Link to='#' className='link'>
            Register
          </Link>
          
        </form>
      ) : (
        <>
          <Loader />
          
        </>
      )}
    </>
  );
}

export default Login;
