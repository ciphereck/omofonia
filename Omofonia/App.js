import React, {Component} from 'react';
import 'react-native-gesture-handler';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import MainActivity from './MainActivity';
import MobileOnboard from './component/userProfile/userProfile'

const Stack = createStackNavigator();

export default class App extends Component {
  render() {
    return (
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen
            name="Login"
            component={MainActivity}
            options={{title: 'Login'}}
          />
          <Stack.Screen
            name="UserOnBoard"
            component={MobileOnboard}
            options={{title: 'On Board'}}
          />
        </Stack.Navigator>
      </NavigationContainer>
    )
  }
}
