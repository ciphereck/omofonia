import React, {Component} from 'react';
import { Text } from 'react-native';
import { GoogleSignin, GoogleSigninButton, statusCodes } from 'react-native-google-signin';
import { thisExpression } from '@babel/types';
import MobileOnboard from './component/userProfile/userProfile';

export default class MainActivity extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userInfo: null
        }
        GoogleSignin.configure({
            webClientId: '632226048159-3jerskhg1ip2auoi1ihkeabh65lkg9eo.apps.googleusercontent.com',
            offlineAccess: false,
        });
        this._isSignedIn();
    }

    _isSignedIn = async () => {
        const isSignedIn = await GoogleSignin.isSignedIn();
        if (isSignedIn) {
          this.getCurrentUserInfo();
        } else {
          console.log('Please Login');
        }
      };

    getCurrentUserInfo = async () => {
        try {
          const userInfo = await GoogleSignin.signInSilently();
          this.setState({ userInfo });
        } catch (error) {
          if (error.code === statusCodes.SIGN_IN_REQUIRED) {
            console.log("Sign In Required")
          } else {
            console.log(error)
          }
        }
    };

    signOut = async () => {
        if(this.state.userInfo == null) {
            return
        }
        try {
          await GoogleSignin.revokeAccess();
          await GoogleSignin.signOut();
          this.setState({ user: null });
        } catch (error) {
          console.error(error);
        }
      };

    _signIn = async () => {
        try {
          await GoogleSignin.hasPlayServices();
          const userInfo = await GoogleSignin.signIn();
          this.setState({ userInfo });
        } catch (error) {
          if (error.code === statusCodes.SIGN_IN_CANCELLED) {
            console.log("SIGN IN Cancelled")
          } else if (error.code === statusCodes.IN_PROGRESS) {
            console.log("SIGN IN in progress")
          } else if (error.code === statusCodes.PLAY_SERVICES_NOT_AVAILABLE) {
            console.log("SIGN IN Play Service is not available")
          } else {
            console.log("SIGN IN Error")
          }
        }
      };

    render() {
        if(this.state.userInfo != null) {
            return <MobileOnboard userInfo={this.state.userInfo} signOut={this.signOut} />
        } else {
            return (
                <GoogleSigninButton
                    style={{ width: 192, height: 48 }}
                    size={GoogleSigninButton.Size.Wide}
                    color={GoogleSigninButton.Color.Dark}
                    onPress={this._signIn}
                />
            )
        }
    }
}