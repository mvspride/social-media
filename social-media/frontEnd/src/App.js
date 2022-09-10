import React from 'react';
import logo from './logo.svg';
import './App.css';
import LoginPage from "../src/components/LoginPage/LoginPage.js";
import Home from "../src/components/HomePage/Home.js";
function App() {
  return(

    <div className="App">
      {
        (localStorage.getItem("users")==null)?
        <LoginPage/>:<Home/> 

      }
    </div>
  )
  
}

export default App;
