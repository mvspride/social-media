import React, {Component} from 'react';
import "../LoginPage/LoginPage.css";
import { getAuth, signInWithEmailAndPassword } from "firebase/auth";
import {signInWithPopup, GoogleAuthProvider } from "firebase/auth";
import { Button,FloatingLabel,Form} from 'react-bootstrap';


class SignIN extends Component{
    constructor(props){
        super(props);
        this.provider = new GoogleAuthProvider()
        this.auth = getAuth();
        this.state={
            email:null,
            password: null,
            signInError: "Password or Email is Incorrect!",
            wrongCredentials : false
        }
    }
    googleSignIn=()=>{
        signInWithPopup(this.auth, this.provider)
  .then((result) => {
    // This gives you a Google Access Token. You can use it to access the Google API.
    const credential = GoogleAuthProvider.credentialFromResult(result);
    const token = credential.accessToken;
    // The signed-in user info.
    const user = result.user;
    const axios = require('axios')

    localStorage.setItem("users",JSON.stringify(user))
    const local = JSON.parse(localStorage.getItem("users"))

    axios.post("http://localhost:8080/users", {
        firebaseUid : local.uid,
        email : local.email,
        password: user.password,
        username :local.displayName,
    })
    .then(function (response) {
        console.log(response);
    })
    window.location.reload()

    // ...
  }).catch((error) => {
    // Handle Errors here.
    const errorCode = error.code;
    const errorMessage = error.message;
    // The email of the user's account used.
    const email = error.customData.email;
    // The AuthCredential type that was used.
    const credential = GoogleAuthProvider.credentialFromError(error);
    // ...
  });

    }
    signInToHome=()=>{
        signInWithEmailAndPassword(this.auth, this.state.email, this.state.password)
          .then((userCredential) => {
            // Signed in 
            const user = userCredential.user;
            localStorage.setItem("users", user)
            window.location.reload()
            // ...
          })
          .catch((error) => {
            this.setState({wrongCredentials:true})
            const errorCode = error.code;
            const errorMessage = error.message;
          });
    }

    
    render(){
        return(
            
            <div>
                <>
  <FloatingLabel
    controlId="floatingInput"
    label="Email address"
    className="mb-3"
    style={{height : "50%",margin:"30px"}}
  >
    <Form.Control type="email" onChange={(event)=>{this.state.email = event.currentTarget.value;
        }}placeholder="name@example.com"/>
  </FloatingLabel>
  <FloatingLabel controlId="floatingPassword" label="Password" style={{height : "50%",margin:"30px"}}>
    <Form.Control type="password"onChange={(event)=>{this.state.password = event.currentTarget.value;
        }} placeholder="Password" />
  </FloatingLabel>
</>
                
    <div>{
        this.state.wrongCredentials?
        <span className="text-danger">{this.state.signInError}</span>
        
        :<span></span>
        }
    </div>
    <button className ="login__button" onClick={this.signInToHome}> Log In</button>
    
    <Button variant="primary" onClick={this.googleSignIn} style={{ margin: '10px' }}> Continue with Google</Button>
    

</div>)
    }
}

export default SignIN;