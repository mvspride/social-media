import React,{Component} from 'react';
import {InputGroup,FormControl,Card,Button} from 'react-bootstrap';
import Avatar from '@mui/material/Avatar';
import profile_pic from '../../images/profile_pic.JPG';
import "./StatusBar.css" 

class StatusBar extends Component{
    constructor(props){
        super(props);
        this.state={
            statusList:[]
        }
    }
    
    getData=()=>{
        let data=[
            {
                "username":"sir_Pride",
                "imageUrl":"../../images/pp1.png"
            },
            {
                "username":"sir_James",
                "imageUrl":"../../images/pp2.png"
            }
        ]
        this.setState({statusList:data});
    }
    render(){
        return(
        <div className='StatusBar_container'>
            {/* {
                this.state.statusList.map((item,index)=>(

                ))
            } */}
                    <div className ="status">
                        <Avatar className='avatar-profile' alt="Remy Sharp" src= {profile_pic} sx={{ width: 56, height: 56 }}/>
                        <div className='avatar-names'>defaulmnopqrs</div>
                    </div>
                    <div className ="status">
                        <Avatar className='avatar-profile' alt="Remy Sharp" src= {profile_pic} sx={{ width: 56, height: 56 }}/>
                        <div className='avatar-names'>
                            defaulmnopqrs
                        </div>
                    </div>
                    <div className ="status">
                        <Avatar className='avatar-profile' alt="Remy Sharp" src= {profile_pic} sx={{ width: 56, height: 56 }}/>
                        <div className='avatar-names'>
                            defaulmnopqrs
                        </div>
                    </div>
                    <div className ="status">
                        <Avatar className='avatar-profile' alt="Remy Sharp" src= {profile_pic} sx={{ width: 56, height: 56 }}/>
                        <div className='avatar-names'>
                            defaulmnopqrs
                        </div>
                    </div>
                    <div className ="status">
                        <Avatar className='avatar-profile' alt="Remy Sharp" src= {profile_pic} sx={{ width: 56, height: 56 }}/>
                        <div className='avatar-names'>
                            defaulmnopqrs
                        </div>
                    </div>
                    <div className ="status">
                        <Avatar className='avatar-profile' alt="Remy Sharp" src= {profile_pic} sx={{ width: 56, height: 56 }}/>
                        <div className='avatar-names'>
                            defaulmnopqrs
                        </div>
                    </div>
                    <div className ="status">
                        <Avatar className='avatar-profile' alt="Remy Sharp" src= {profile_pic} sx={{ width: 56, height: 56 }}/>
                        <div className='avatar-names'>
                            defaulmnopqrs
                        </div>
                    </div>               
        </div>
        )
    }
}

export default StatusBar;