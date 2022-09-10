import React, {Component} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import Grid from '@material-ui/core/Grid';
import profile_pic from '../../images/profile_pic.JPG';
import insta_logo from '../../images/logoinsta.png';
import './LoginPage.css'
import SignIN from '../Signin/SignIN.js';
import SignUP from '../SignUP/SignUP.js';
class LoginPage extends Component{
    constructor(props){
        super(props)
        this.state ={
        isLogin : true           
        }
    }
    
    changeLogin=()=>{
        if(this.state.isLogin)
        this.setState({isLogin: false});
        else{
            this.setState({isLogin: true});
        }
    }
    render(){
        return(
            <div>
                <Grid container>
                    <Grid item xs ={3}>
                        
                    </Grid>
                    <Grid item xs ={6}>
                    
                        <div className ="loginpage__main">
                            <div>
                                {/*<img src={inst_image} width="454px"/>*/}
                            </div>
                            <div>
                                <div className ="loginpage_rightcomponent">
                                    {/*<div className ="loginpage__logo"> Pride </div>*/}
                                    <div className ="loginpage__pride"> Pride's Social Media</div>

                                    <div className ="loginpage__signin">
                                        {
                                            this.state.isLogin ? <SignIN/> : <SignUP/>
                                        }
  
                                    </div>
                                    <div className = "login__ordiv">
                                    <div className ="login__dividor"></div>
                                    <div className ="login__or">OR</div>
                                    <div className ="login__dividor"></div>
                                    </div>
                                    <div className="login_forgot"> Forgot password?</div>

                                </div> 
                                <div className ="loginpage__signUpOption">
                                    {
                                        this.state.isLogin ? 
                                        <div className ="loginPage__signup">
                                            Don't have an account? <span onClick={this.changeLogin} style ={{"fontWeight":"bold","color":"blue"}}>Sign up</span> 
                                        </div>:
                                    <div className ="loginPage__signup">
                                    Have an account? <span onClick={this.changeLogin} style ={{"fontWeight":"bold","color":"blue"}}>Sign In</span> 
                                    </div>
                                    }
                                    
                                </div>
                                

                            </div>
                        </div>
                    </Grid>
                    <Grid item xs ={3}>
                        
                    </Grid>
                </Grid>
            </div>
        );
    }
}

export default LoginPage;