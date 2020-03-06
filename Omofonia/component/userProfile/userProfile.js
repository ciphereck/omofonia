import React, {Component} from 'react';
import { Text, Image, View, Button, TextInput } from 'react-native';
import Config from '../../config';

export default class MobileOnboard extends Component {
    state = {
        token: "", 
        userInfo : {
        },
        otp: '5656'
    }

    constructor(props) {
        super(props);
        this._initialize();
    }

    async _getPersonalDetails() {
        await fetch(Config.BASE + '/login', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                idToken: this.props.userInfo.idToken
            })
        }).then((response) => response.json())
        .then((responseJson) => {
            if(responseJson.success == true) {
                this.setState({
                    token: responseJson.data.token,
                    userInfo: responseJson.data.userInfo
                })
            }else {
                console.log("error error")
            }
        }).catch((err) => {
            console.log(err)
        })
    }

    async getDetails() {
        await fetch(Config.BASE + '/user/profile', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                "Authorization": this.state.token
            }
        }).then((response) => response.json())
        .then((responseJson) => {
            if(responseJson.success == true) {
                this.setState({
                    userInfo: responseJson.data.userInfo
                })
            }else {
                console.log("error error")
            }
        }).catch((err) => {
            console.log(err)
        })
    }

    async _initialize() {
        await fetch(Config.BASE + '/login', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                idToken: this.props.userInfo.idToken
            })
        }).then((response) => response.json())
        .then((responseJson) => {
            if(responseJson.success == true) {
                this.setState({
                    token: responseJson.data.token,
                    userInfo: responseJson.data.userInfo
                })
            }else {
                console.log("error error")
            }
        }).catch((err) => {
            console.log(err)
        })
    }

    async sendOtp() {
        await fetch(Config.BASE + '/user/otp', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                'Authorization': this.state.token
            },
            body: JSON.stringify({
                mobileNo: this.state.userInfo.mobileNo,
                otp: Math.floor(Math.random() * 1000) + 1
            })
        }).then((response) => response.json())
        .then((responseJson) => {
            console.log(responseJson)
            if(responseJson.success == true) {
                console.log(responseJson)
            }else {
                console.log("error error")
            }
        }).catch((err) => {
            console.log(err)
        })
    }

    render() {
        return (
            <View style={{justifyContent:'center', alignItems:'center'}}>
                <Image
                    style={{width: 150, height: 150}}
                    source={{uri: this.state.userInfo.photo}}
                />
                <Text>{this.state.userInfo.email}</Text>
                <Text>{this.state.userInfo.name}</Text>
                <TextInput 
                    style={{borderColor: '#000000', borderWidth: 1}}
                    editable = {this.state.userInfo.mobileNo != ""}
                    keyboardType = "number-pad"
                    onChangeText={mobileNo => this.state.userInfo.mobileNo = mobileNo} ></TextInput>
                <Button title="Send OTP" onPress={this.sendOtp.bind(this)}/>
            </View>
        )
    }
}