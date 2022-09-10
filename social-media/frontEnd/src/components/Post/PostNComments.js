import React, { Component } from "react";
import "./PostNComments.css";
import Posts from "./Post";
import Grid from "@material-ui/core/Grid";
import home from "../../images/home.svg";
import message from "../../images/message.svg";
import like from "../../images/love.svg";
import Avatar from "@mui/material/Avatar";
import Comment from "../Comment/Comment";

class PostNComments extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  closeComments = () => {
    this.props.toggle();
  };

  render() {
    return (
      <div className="popup-box">
        <div className="box">
          <Grid container>
            <Grid className="popUp_stGrid" item xs={7}>
              <div className="popUp_postContainer">
                <img className="popUp_postImage" src={this.props.postImage} />
              </div>
            </Grid>
            <Grid className="popUp_ndGrid" item xs={5}>
              <div className="popUp_commentsContainer">
                <div className="popUp_comments comment_header">
                  <div className="comment_header_username-image">
                    <Avatar
                      className="comment_header_image"
                      src={this.props.profileImage}
                      sx={{ width: 32, height: 32 }}
                    ></Avatar>
                    <button
                      className="comment_header_username"
                      style={{
                        border: 0,
                        background: 0,
                        fontWeight: 600,
                        height: 18,
                        fontSize: 14,
                        marginTop: -5,
                      }}
                    >
                      {this.props.username}
                    </button>
                  </div>
                  <div className="comment_header_3dots">
                    <button
                      className="comment_header_3dots_button"
                      style={{
                        border: 0,
                        background: 0,
                        fontWeight: 600,
                        height: 18,
                        fontSize: 20,
                        marginTop: -23,
                      }}
                    >
                      ...
                    </button>
                  </div>
                </div>

                <div className="popUp_comments comment_body">
                    {
                        this.props.commentList.map((item,index)=>(
                            <Comment
                                profileImage ={item.profileImageURL}
                                username ={item.username}
                                description ={item.description}
                                />
                        ))
                    }
                    
                </div>
                <div className="popUp_comments comment_icons_etc">
                  <div className="popUp_comments comment_icons">3</div>
                  <div className="popUp_comments comment_likes">4</div>
                  <div className="popUp_comments comment_timeStamp">5</div>
                </div>

                <div className="popUp_comments addComment">6</div>
              </div>
            </Grid>
          </Grid>
        </div>
        <span className="close-icon" onClick={this.closeComments}>
          x
        </span>
      </div>
    );
  }
}

export default PostNComments;
