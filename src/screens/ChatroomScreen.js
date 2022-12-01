import { StatusBar } from 'expo-status-bar'
import { StyleSheet, Text, View } from 'react-native'
import React, { useEffect, useState } from 'react'
import { Button, Input, Icon } from 'react-native-elements'
import axios from 'axios'
import config from '../config'

const ChatroomScreen = ({navigation, route}) => {
  
  var ws = new WebSocket('ws://192.168.1.21:8080/chat');

 
     ws.onmessage = (e) => {
       // a message was received
       console.log(e.data);
     }; 
 
     ws.onerror = (e) => {
       // an error occurred
       console.log("ERROR");
       console.log(e.message);
     };
 
     ws.onclose = (e) => {
       // connection closed
       console.log("CLOSING");
       console.log(e);
       console.log(e.code, e.reason);
     };

  const chatroom = route.params.chatroom;
  const email = route.params.email;
  const [messages, setMessages] = useState([])
  const [message, setMessage] = useState("")
  const [userId, setUserId] = useState("")

  useEffect(()=>{
    axios.get(config.url + "/chatroom/getMessageHistory/" + chatroom.id).then((response)=>{
      console.log("HALO")
      setMessages(response.data)
      console.log(messages)
    })
  }, [])

  useEffect(()=>{
    axios.get(config.url + "/users/getId/" + email).then((response)=>{
      console.log(response)
      setUserId(response)
      console.log(response)
    })
  }, "")

  const sendMessage = () =>{
    console.log("message:" + message)
    if(message == ""){
      console.log("pusta wiadomosc nie wysylam")
    }else{
      console.log("wysylam")
      ws.send(JSON.stringify({"message":message, "chatroomId":parseInt(chatroom.id), "sender": email}));
    }

  }
   
       return (
        <View style={styles.container}>
          <Text>Awesome Chatroom with {chatroom.users[1].email}</Text>
          {
          messages.map((message, key)=>
            message.userId == userId?
             <Text style = {styles.userMessage}>{message.text}</Text> :
             <Text style = {styles.incomingMessage}>{message.text}</Text> 
          )
        }
          <View style={styles.input}>
          <Input placeholder='Write something...' onChangeText={(text)=>setMessage(text)}/>
          <Button onPress={()=>sendMessage()}/>
          </View>
          <StatusBar style="auto" />
        </View>
      )
      
    }
     
    export default ChatroomScreen;  

    const styles = StyleSheet.create({
      container: {
        flex: 1,
        alignItems:'flex-end',
        justifyContent:'flex-end'
      },
      input:{
        flexDirection:'row',
        alignItems:'flex-end',
        justifyContent:'flex-end',
        width:'80%',
      },
      userMessage:{
        alignItems:'flex-end',
        justifyContent:'flex-end',
      },
      incomingMessage:{
        alignItems:'flex-start',
        justifyContent:'flex-start',
      }
    });
    
    