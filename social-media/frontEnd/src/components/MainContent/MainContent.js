import React, {Component}from 'react';
import Posts from '../Post/Post';
import "./MainContent.css"
import Grid from '@material-ui/core/Grid';
import { StyleSheet, Text, View } from "react-native";
import StatusBar from '../StatusBar/StatusBar';
import MainPaige from '../MainPaige/MainPaige';

class MainContent extends Component{
    constructor(props){
        super(props);
        this.state={
        }
    }

    render(){
        return(
            <div>
                <Grid container>
                    <Grid className='stGrid' item xs ={2}>
                    1st
                    </Grid>
                    <Grid item xs ={6}>
                        <div className='middle_page'>
                        <StatusBar/>
                        <MainPaige/>
                        </div>
                    </Grid>
                    <Grid item xs ={2}>
                    </Grid>
                    <Grid iten xs={2}>
                        
                    4th

                    </Grid>
                        
                </Grid> 
            </div>
        )
    }

}
export default MainContent;