import React from "react";

function Home() {
  return (
    <div className='homeWrapper'>
      <div className='container'>
        <h1 className='text-box'>
          <span style={{ fontWeight: "400" }}>First online </span>
          <span className='underline'> examination tool </span>
          <p>which works offline.</p>
        </h1>
      </div>

      <div class='page1'>
        <div class='container'>
          <div class='page1-box'>
            <img
              class='page1-img'
              src='/graduating-student.png'
              alt='android-app'
              width='200'
              height='200'
            />
            <div class='information'>
              <div class='info-title'>For Students</div>
              <br />
              <div class='info-all'>
                Student's side Android and ios application where students will
                have to download the AES encrypted question papers of all exams
                collectively before 10-15days of exams. And can attemp paper on
                the date with SMS OTP key without internet.
              </div>
            </div>
          </div>
        </div>
        <div className='page1'>
          <div className='container'>
            <div className='page1-box'>
              <img
                className='page1-img'
                src='/teacher.png'
                alt='android-app'
                width='200'
                height='200'
              />
              <div className='information'>
                <div className='info-title'>For Teachers</div>
                <br />
                <div className='info-all'>
                  Teacher's side web application where teacher can create test,
                  get studnets answer sheet. Now the professor's can download
                  the answer sheets and do the evaluation.
                </div>
                <a href='/teachers'>
                  <button className='create-course'>Open Portal</button>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
