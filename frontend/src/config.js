import {Dimensions} from "react-native"

const window = Dimensions.get('window');

export default {
    screenHeight: window.height,
    screenWidth: window.width,

    url: "http://192.168.1.21:8080",
    appName:"Temprorary name"
}