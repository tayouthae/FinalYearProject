import React, { useState } from "react";
import { CourseCreatorAbi, CourseCreatorAddress, CourseAbi } from "../config";
import axios from "axios";
import Loader from "./Loader";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { web3 } from "../services/web3";
import Login from "./Login";
import Dashboard from "./Dashboard";
import { useHistory } from "react-router-dom";

toast.configure();

const Teacher = () => {
  const { IPFS_LINK, HERO_KU_LINK } = process.env;

  const [isConnected, setIsConnected] = useState(false);
  const [currentAccount, setCurrentAccount] = useState(null);
  const [balance, setBalance] = useState(0);
  const history = useHistory();

  const onLogin = async () => {
    const accounts = await web3.eth.getAccounts();
    if (accounts.length === 0) {
      console.log("Please connect to MetaMask!");
    } else if (accounts[0] !== currentAccount) {
      setCurrentAccount(accounts[0]);
      const accBalanceEth = web3.utils.fromWei(
        await web3.eth.getBalance(accounts[0]),
        "ether"
      );
      setBalance(Number(accBalanceEth).toFixed(6));
      setIsConnected(true);
    }
  };

  const onLogout = () => {
    setIsConnected(false);
    history.push("/");
  };

  const [loading, setLoading] = useState(false);
  const [file, setFile] = useState("");
  const [link, setLink] = useState("");
  const [studentId, setStudentId] = useState("");
  const [show, setShow] = useState(null);
  const [deadline, setDeadline] = useState(null);
  const [courseAddress, setCourseAddress] = useState("");
  const [username, setUsername] = useState();

  const handleCreateCource = async (e) => {
    e.preventDefault();

    if (deadline === null) {
      return alert("File or Deadline Missing!");
    }
    console.log("Submiting data");
    setLoading(true);

    // 1.  Give file to backend for encryption
    const formData = new FormData();
    formData.append("file", file);
    console.log(file);
    let res;
    try {
      res = await axios.post(`${HERO_KU_LINK}/encrypt`, formData);
    } catch (e) {
      console.log("error", e);
    }
    toast("File Encrypted", { type: "success" });
    let str = res.data;
    str = str.split(" ");
    const [privateKey, fh] = str;
    console.log(privateKey, fh);

    // 2.  Create course functionality for a new registered course

    await web3.eth.net.getNetworkType();

    const courseCreator = new web3.eth.Contract(
      CourseCreatorAbi,
      CourseCreatorAddress
    );
    await courseCreator.methods
      .createCourse(deadline)
      .send({ from: currentAccount });
    toast("Course Created", { type: "success" });

    // fetch course deployed address for login part
    let t;
    await courseCreator.methods
      .fetchCourseAddress()
      .call({ from: currentAccount }, (error, result) => {
        console.log(`Course ${result}`);
        t = result;
      });

    setCourseAddress(t);
    setShow(
      `Private Key: ${privateKey} \n File link: ${IPFS_LINK}/${fh} \n Course Address: ${courseAddress}`
    );
    setShow({
      pkey: privateKey,
      file_link: `${IPFS_LINK}/${fh}`,
      cs: t,
    });
    console.log(show);
    setCurrentAccount(currentAccount);
    setLoading(false);
  };

  const handleGetAnswer = async (e) => {
    e.preventDefault();
    setLoading(true);
    let hash;

    // const networkId = await web3.eth.net.getId();
    const courseCreator = new web3.eth.Contract(
      CourseCreatorAbi,
      CourseCreatorAddress
    );
    let t;
    await courseCreator.methods
      .fetchCourseAddress()
      .call({ from: currentAccount }, (error, result) => {
        console.log(`Course ${result}`);
        t = result;
      });

    const course = new web3.eth.Contract(CourseAbi, t);
    console.log(t);
    await course.methods
      .fileHash(studentId)
      .call({ from: currentAccount }, (error, result) => {
        console.log(`fileHash ${result}`);
        hash = result;
      });
    console.log(hash);

    const x = await axios({
      url: link,
      method: "GET",
      responseType: "blob",
    });
    const file2 = new Blob([x.data]);
    const formData = new FormData();
    formData.append("file", file2);
    formData.append("comparisonHash", hash);

    const res = await axios.post(`${HERO_KU_LINK}/fileHashMatch`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    console.log(res.data);
    if (res.data === "verified") {
      toast("Verified, Download starts", { type: "success" });
      const url = window.URL.createObjectURL(new Blob([x.data]));
      const ll = document.createElement("a");
      ll.href = url;
      ll.setAttribute("download", "file.pdf");
      document.body.appendChild(ll);
      ll.click();
    } else {
      toast("File Hash Not matched", { type: "error" });
    }
    setLoading(false);
  };

  const handleFileUpload = async (e) => {
    e.preventDefault();
    setFile(e.target.files[0]);
  };
  const handleDeadline = (e) => {
    e.preventDefault();
    let now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());

    if (
      Date.parse(now.toISOString().slice(0, 16)) >= Date.parse(e.target.value)
    ) {
      setDeadline(null);
      return alert("Invalid date and time input");
    }
    setDeadline(Date.parse(e.target.value));
    console.log(deadline);
  };

  const handlelink = (e) => {
    e.preventDefault();
    setLink(e.target.value);
  };
  const handlestudentId = (e) => {
    e.preventDefault();
    setStudentId(e.target.value);
  };

  function IfShow() {
    return (
      <div className="form-wrapper">
        <h3 className="info-title-ifShow">
          Course Created Please message these
        </h3>

        <span className="paragraph">
          <b>File Password:</b> {show.pkey}
        </span>
        <br />

        <span className="paragraph">
          <b>File link:</b> {show.file_link}
        </span>
        <br />

        <span className="paragraph">
          <b>Subject Id:</b> {show.cs}
        </span>
      </div>
    );
  }

  return (
    <>
      {!isConnected && (
        <>
          <Login
            onLogin={onLogin}
            onLogout={onLogout}
            isConnecting={isConnected}
            setIsConnecting={setIsConnected}
            username={username}
            setUsername={setUsername}
          />
        </>
      )}

      {isConnected && (
        <div className="teacherWrapper">
          <Dashboard
            currentAccount={currentAccount}
            balance={balance}
            username={username}
            onLogout={onLogout}
          />
          <div className="container">
            <h1 className="text-box">
              <span style={{ fontWeight: "400" }} className="colorWhite">
                Teacher's Application
              </span>
            </h1>
          </div>
          {loading ? <Loader /> : <></>}

          <div className="sec-container">
            <div className="sec">
              {show ? (
                <IfShow />
              ) : (
                <>
                  <br />
                  <form className="form-wrapper" onSubmit={handleCreateCource}>
                    <div className="info-title">Create New Course</div>
                    <h3 className="text-light">Upload Question paper</h3>
                    <input
                      className="custom-file-input"
                      type="file"
                      id="file"
                      onChange={handleFileUpload}
                      required
                    />
                    <h3 className="text-light">Choose Exam End time</h3>
                    <input
                      className="exam-date"
                      type="datetime-local"
                      id="birthdaytime"
                      name="birthdaytime"
                      onChange={handleDeadline}
                      required
                    />
                    <button className="create-course" type="submit">
                      Create
                    </button>
                  </form>
                </>
              )}
            </div>
            <>
              <div className="sec">
                <div className="sec">
                  <form className="form-wrapper" onSubmit={handleGetAnswer}>
                    <div className="info-title">
                      Get students solution sheet
                    </div>
                    <h3 className="text-light">Enter File link</h3>
                    <input
                      className="custom-file-input"
                      onChange={handlelink}
                      placeholder="IPFS link"
                      required
                    />
                    <h3 className="text-light">Enter Student Id</h3>
                    <input
                      className="custom-file-input"
                      onChange={handlestudentId}
                      placeholder="Enter student id"
                      required
                    />
                    <button className="create-course" type="submit">
                      Get Answer
                    </button>
                  </form>
                </div>
              </div>
            </>
          </div>
        </div>
      )}
    </>
  );
};

export default Teacher;
