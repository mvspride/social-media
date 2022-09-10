import React, { Component } from "react";
import Grid from "@material-ui/core/Grid";
import inst_logo from "../../images/logoinsta.png";
import home from "../../images/home.svg";
import message from "../../images/message.svg";
import like from "../../images/love.svg";
import Avatar from "@mui/material/Avatar";
import upload from "../../images/upload1.png";
import profile_pic from "../../images/profile_pic.JPG";
import { InputGroup, FormControl, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios"

import {
  getStorage,
  ref,
  uploadBytesResumable,
  getDownloadURL,
} from "firebase/storage";
import { getAuth, signInWithEmailAndPassword } from "firebase/auth";


import "./NavBar.css";

class NavBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      signedOut: false,
      
    };
    this.local = JSON.parse(localStorage.getItem("users"))

  }

  upload = (event) => {
    const storage = getStorage();

    let image = event.target.files[0];
    if(image == null || image == undefined){
        return;
    }
    // Upload file and metadata to the object 'images/mountains.jpg'
    const storageRef = ref( storage, "images/" + image.name);
    const uploadTask = uploadBytesResumable(storageRef, image,getAuth);

    // Listen for state changes, errors, and completion of the upload.
    uploadTask.on(
      "state_changed",
      (snapshot) => {
        // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
        const progress =
          (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        console.log("Upload is " + progress + "% done");
        switch (snapshot.state) {
          case "paused":
            console.log("Upload is paused");
            break;
          case "running":
            console.log("Upload is running");
            break;
        }
      },
      (error) => {
        // A full list of error codes is available at
        // https://firebase.google.com/docs/storage/web/handle-errors
        switch (error.code) {
          case "storage/unauthorized":
            // User doesn't have permission to access the object
            break;
          case "storage/canceled":
            // User canceled the upload
            break;

          // ...

          case "storage/unknown":
            // Unknown error occurred, inspect error.serverResponse
            break;
        }
      },
      () => {
        // Upload completed successfully, now we can get the download URL
        getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
            
            console.log("File available at", downloadURL);
            const axios = require('axios')

            axios.post("http://localhost:8080/posts", {
                userFirebaseUid: this.local.uid,
                username:this.local.displayName,
                postImageURL: downloadURL,
                profileImageURL: this.local.photoURL,
                timeStamp: new Date().getTime(),
                caption: "nah this drip on my sleeve",
                likes: 0            
            })
            .then(function (response) {
                console.log(response);
            })
            window.location.reload();
        });
      }
    );
  };
  signOut = () => {
    localStorage.removeItem("users");
    window.location.reload();
  };

  render() {
    return (
      <div>
        <div className="navbarContainer">
          <div className="navbar logo">
            <div className="logo_text"></div>
            Social Media
          </div>
          <input className="navbar search" text="text" placeholder="Search" />

          <div className="navbar icons">
            <div class="iconsContainer">
              <img
                className="icon home"
                src={home}
                style={{ width: 24, height: 24 }}
              />
              <img
                className="icon message"
                src={message}
                style={{ width: 24, height: 24 }}
              />

              <img
                className="icon likes"
                src={like}
                style={{ width: 24, height: 24 }}
              />
              <div className="fileUpload">
                <label for="file_upload">
                  
                    <img
                      className="icon upload"
                      onClick={this.upload}
                      src={upload}
                      style={{ width: 44, height: 44 }}
                    />
                </label>
                <input onChange={this.upload} id="file_upload" type="file" />
              </div>

              <img
                className="icon message"
                src={message}
                style={{ width: 24, height: 24 }}
              />
              <Avatar
                className="icon message"
                alt="Remy Sharp"
                src={this.local.photoURL}
                style={{ width: 24, height: 24 }}
              />
            </div>
          </div>
          <Button className='navbar sign_out_button' onClick={this.signOut}>
                                <div className='sign_out_text'>sign out</div>
                            </Button>
        </div>
      </div>
    );
  }
}
export default NavBar;
