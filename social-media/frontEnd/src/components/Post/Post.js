import React, { Component } from "react";
import { InputGroup, FormControl, Card, Button } from "react-bootstrap";
import home from "./src/components/home/Home.svg";
import message from "../../images/message.svg";
import like from "../../images/love.svg";
import Avatar from "@mui/material/Avatar";
import profile_pic from "../../images/profile_pic.JPG";
import "./Post.css";
import StatusBar from "../StatusBar/StatusBar";
import PostNComments from "./PostNComments";
import { BsEmojiHeartEyesFill } from "react-icons/bs";
class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      // username:null,
      // profileImage:null,
      // postImage:null,
      // likes:null,
      commentList:[],
      comments: true,
      seeComments: false,
      modal: false,
      addFiller: false,
      moreComments: true,
      liked : false
    };
    this.likePost = this.likePost.bind(this);
    this.unlikePost =this.unlikePost.bind(this);

  }
  async likePost(){
    try {
        const local = JSON.parse(localStorage.getItem("users"))
        const axios = require('axios')
        const response = await axios.post("http://localhost:8080/posts/like",null,{
            params: {
              firebaseUid : local.uid,
              postId :this.props.postId
            }
          }
          );
          
    } catch (error) {
      console.error(error);
      console.error(this.props.postId)
    }
    console.log(this.state.liked)
    this.setState({ liked:true }, () => {                              
      //callback
      console.log(this.state.liked) // myname
    });
          console.log("true")
  }

  async unlikePost(){
    try{
      const local = JSON.parse(localStorage.getItem("users"))
    const axios = require("axios")
    const response = await axios.put("http://localhost:8080/posts/unlike",null,{
      params: {
        firebaseUid: local.uid,
        postId: this.props.postId
      }}
    );
    
    }catch(error) {
      console.error(error);
  }
  console.log(this.state.liked)
  this.setState({ liked:false }, () => {                              
    //callback
    console.log(this.state.liked) // myname
  });
    console.log("false")
  }
  componentDidMount(){
      this.getComments();
  }
  getComments=()=>{
      let data=[
          {
          "username":"yurrr",
          "profileImageURL":"https://media-exp1.licdn.com/dms/image/C4E03AQFDZG-dLzDyRw/profile-displayphoto-shrink_200_200/0/1648202734851?e=1658361600&v=beta&t=v53tGBoZJx8H9KB8BTSE1PS5MZAPAGj3iKeMVOeijIs",
          "commentId":"12",
          "timeStamp":"5d",
          "description":"this aint the easy way out gang... it is onle th begining of the end"
          },
          {
            "username":"yur",
            "profileImageURL":"https://dw3jhbqsbya58.cloudfront.net/rosters/8b46cffe-f646-476c-af58-014a057934cb/c/b/7/cb7751df-cfb4-e711-93f8-ecf4bbe7be14/thumbnail.jpg?version=636845464623148690",
            "commentId":"12",
            "timeStamp":"4d",
            "description":"comment 2"
            }
      ]
      this.setState({commentList:data})
  }
  
  submitComments =(event)=>{
    if(event.key == "Enter"){
      let comment = event.currentTarget.value;
      if(comment !=null || comment != undefined){
        
      }
    }
  }
  toggleComments = () => {
    this.setState({
      seeComments: !this.state.seeComments,
    });
  };


  render() {
    return (
      <div>
        <div className="Posts">
          <div className="post">
            <div className="post_title_Container">
              <div className="post_title_items avatar">
                <Avatar
                  className="avater_icon"
                  alt="Remy Sharp"
                  src={this.props.profileImage}
                  sx={{ width: 32, height: 32 }}
                />
              </div>
              {this.state.addFiller ? (
                <div className="post_title_items title_username_filler">
                  <button
                    className="username_ button"
                    style={{
                      border: 0,
                      background: 0,
                      fontWeight: 600,
                      height: 18,
                      fontSize: 14,
                    }}
                  >
                    {this.props.username}
                  </button>
                  <button
                    className="username_ filler_button"
                    style={{ border: 0, background: 0, fontSize: 14 }}
                  >
                    {" "}
                    filler{" "}
                  </button>
                </div>
              ) : (
                <div className="post_title_items title_username">
                  <button
                    className="username_ button"
                    style={{
                      border: 0,
                      background: 0,
                      fontWeight: 600,
                      height: 18,
                      fontSize: 14,
                    }}
                  >
                    {this.props.username}
                  </button>
                </div>
              )}

              <button
                className="post_title_items dots_button"
                style={{ border: 0, background: 0 }}
              >
                <div className="dots_text">...</div>
              </button>
            </div>
            <div className="post_content_container">
              <img className="post_image" src={this.props.postImage} />
            </div>
            <div className="post_analytics_container">
              <div className="post_analytics_item interactives_container">
                <button style={{background:0,border:0}} 
                className="post_icons likes"
                onClick={
                  this.state.liked ?
                  this.unlikePost:
                  this.likePost
                  }>
                  <img  src={like} />
                </button>
                <img className="post_icons message" src={message} />
                <img className="post_icons share" src={home} />
                
                <img className="post_icons save" src={like} />
              </div>

              <div className="post_analytics_item post_likes">
                {this.props.likes} likes
              </div>
              <div className="post_analytics_item captionContainer">
                <button
                  className="post_caption caption_username"
                  style={{
                    border: 0,
                    background: 0,
                    fontWeight: 600,
                    fontSize: 14,
                  }}
                >
                  {this.props.username}
                </button>
                <div className="post_caption caption"> {this.props.caption}</div>
              </div>
              <div className="post_analytics_item comments">
                {this.state.comments ? (
                  <button
                    className="view_all_comments"
                    style={{ border: 0, background: 0 }}
                    onClick={this.toggleComments}
                  >
                    View all 80 comments{" "}
                  </button>
                ) : null}
                {this.state.seeComments && (
                  <PostNComments
                    toggle={this.toggleComments}
                    postImage={this.props.postImage}
                    profileImage ={this.props.profileImage}
                    username={this.props.username}
                    commentList ={this.state.commentList}

                  />
                )}
              </div>

              <div className="post_analytics_item timeStamp">5 hours ago</div>
              <div className="post_analytics_item add_commentContainer">
                <img className="comment comment_imoji" src={like} />
                <input
                  className="comment comment_text "
                  style={{ border: 0, background: 0 }}
                  text="text"
                  placeholder="add comment"
                  onKeyPressCapture={this.submitComments}
                />
                <button
                  className="comment comment_button"
                  style={{ border: 0, background: 0 }}
                >
                  Post
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Posts;
