import React, {Component} from 'react';
import { storage} from "../../utils/firebase.js";
import { getAuth, createUserWithEmailAndPassword } from "firebase/auth";
import axios from "axios"
import "/node_modules/bootstrap/dist/css/bootstrap.min.css";

import "../LoginPage/LoginPage.css";
import "../SignUP/SignUP.css";
class SignUP extends Component{
    constructor(props){
        super(props);
        this.auth = getAuth();

        this.state={
            email : null,
            emailError : "",
            name: null,
            nameError: "",
            username: null,
            usernameError: "",
            password: null,
            passwordError : "",
            confirmPassword:null,
            passwordMatch: true,

        }
    
    }
    newSignUp=()=>{
        if(this.state.password !== this.state.confirmPassword){
            this.setState({passwordError: "passwords do not match!"})
            return this.setState({passwordMatch: false})

        }
      
        createUserWithEmailAndPassword(this.auth, this.state.email, this.state.password)
          .then((userCredential) => {
            // Signed in 
            const user = userCredential.user;
            const axios = require('axios')
            localStorage.setItem("users",JSON.stringify(user))
            const local = JSON.parse(localStorage.getItem("users"))


            axios.post("http://localhost:8080/users", {
                bio: "hello sir",
                firebaseUid :local.uid,
                email : this.state.email,
                password: this.state.password,
                username :this.state.username
            })
            .then(function (response) {
                console.log(response);
            })
            local["displayName"] = this.state.username;
            localStorage.setItem("users", JSON.stringify(local));

            window.location.reload()
            // ...
          })
          .catch((error) => {
            const errorCode = error.code;
            const errorMessage = error.message;
            // ..
          });
        

    }
    render(){
        return(
            <div>
                <input className ="loginpage__text"onChange={(event)=>{this.state.username = event.currentTarget.value;
}}  type="text" placeholder = "Username"/>
                <input className ="loginpage__text"onChange={(event)=>{this.state.password = event.currentTarget.value;
}} type="password" placeholder = "Password"/>
                <input className ="loginpage__text"onChange={(event)=>{this.state.confirmPassword = event.currentTarget.value;
}} type="confirmPassword" placeholder ="Confirm Password"/>
                <div>{
                  this.state.passwordMatch ?
                  <div>
                  </div>:
                <span className="text-danger">{this.state.passwordError}</span>
              }
                </div>
               
  

                <input className ="loginpage__text" onChange={(event)=>{this.state.email = event.currentTarget.value;
}} type="email/number" placeholder ="Mobile Number or Email"/>
                <button className ="login__button" onClick={() => this.newSignUp()}> Sign Up </button>

            </div>)
    }
}

export default SignUP;