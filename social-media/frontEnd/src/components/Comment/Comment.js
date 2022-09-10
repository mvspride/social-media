import React, { Component } from 'react'
import Avatar from "@mui/material/Avatar";
import './Comment.css'
class Comment extends Component {
  constructor(props) {
    super(props);
    this.state={
      replies : true
    }
  }

  render() {
    return (
      <div className='single_commentContainer'>
        <div className="comment_body_username-image-comment-likes">
          <Avatar
            className="comment_body_image"
            src={this.props.profileImage}
            sx={{ width: 32, height: 32 }}
          ></Avatar>
          <div className='comment_body_username-comment-likes'>
            <div className='comment_body_username-comment'>
              <button

              className="comment_body_username"
              style={{
                border: 0,
                background:0,
                fontWeight: 600,
                fontSize: 14,
                marginLeft: -4
              }}
            >
              {this.props.username}
            </button>
            <div className='comment_body_description'>
              <text className='comment_body_description_text'>{this.props.description}</text>

            </div>

            </div>
            <div className='comment_body_timeStamp-likes-reply'
                  style={{
                    fontWeight: 400,
                    fontSize: 12,
                  }}>
            20h  131 likes Reply
        </div>
        {
            this.state.replies ?
            <div className='comment_body_viewReplies'
            >
            view replies
          </div>:
          null
          }
          </div>
        </div>
          
       
      </div>
    );
  }
}
export default Comment;