import React from 'react';


function Dashboard  (props) {
  return (
    <>
      <h1 className='colorWhite'>Welcome {props.username}</h1>
      <p className='colorWhite'>Your ID: {props.currentAccount}</p>
      <p className='colorWhite'>Balance: {props.balance} ETH</p>
      <button className="onLogOut" onClick={props.onLogout}>Log Out</button>
    </>
  );
};
export default Dashboard;
