import React, {Component}from 'react';
import Posts from '../Post/Post';
import "./MainPaige.css"
import Grid from '@material-ui/core/Grid';
import { StyleSheet, Text, View } from "react-native";
class MainPaige extends Component{
    constructor(props){
        super(props);
        this.state={
            postArray:[],
            feedArray:[]
        }
        this.getPosts = this.getPosts.bind(this);
    }
    componentDidMount(){
        this.getPosts();
        this.getFeed();
    }
    async getPosts(){
        try {
            const local = JSON.parse(localStorage.getItem("users"))
            const axios = require('axios')
          const response = await axios.get("http://localhost:8080/posts",{
            params: {
                firebaseUid : local.uid
            }
          }
          );
          console.log(response);
          console.log(response.data)
          this.setState({postArray:response.data})
        } catch (error) {
          console.error(error);
        }
      }

      async getFeed(){
        try {
            const local = JSON.parse(localStorage.getItem("users"))
            const axios = require('axios')
            const response = await axios.get("http://localhost:8080/feed"
          );
          console.log(response);
          console.log(response.data)
          this.setState({feedArray:response.data})
        } catch (error) {
          console.error(error);
        }
      }

    render(){
        return(
            <div>
                {
                    this.state.feedArray.map((item)=>(
                        <Posts postId={item.id} username ={item.username} postImage ={item.postImageURL} profileImage ={item.profileImageURL} caption={item.caption} likes={item.likes} comments ={item.comments}/>
                    ))
                }
                
            </div>
           
        );
    }
}

export default MainPaige;