import axios from 'axios';
import { StatusBar } from 'expo-status-bar';
import { useEffect, useState } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Button, Input } from 'react-native-elements';
import config from '../config';

const HomeScreen = ({route, navigation}) => {

  const [users, setUsers] = useState([])
  const [chatrooms, setChatrooms] = useState([])
  const email = route.params.email;
  useEffect(()=>{
    axios.get(config.url + "/user/getAll").then((response)=>{
      setUsers(response.data)
      console.log(users)
    })
  }, [])
  
  useEffect(()=>{
    axios.get(config.url + "/chatroom/getChatrooms").then((response)=>{
      setChatrooms(response.data)
      console.log(chatrooms)
    })
  }, [])

  const createChatroom = (user) => {
    const currentUser ={
      "email":email
    }
    const body = {
        "users":[currentUser, user],
        "chatroomName":user.email + " chat"
    }
    axios.post(config.url + "/chatroom/createChatroom", body).then((response) =>{
      console.log(response.data)
      if(response.status == 200){
        navigation.navigate("Chatroom", {"chatroom":response.data, "email":email})
      }
    }) 
  }


  const openChatroom = (chatroom) => {
    axios.get(config.url + "/chatroom/openChatroom/" + chatroom.id).then((response) =>{
        console.log(response.data)
        if(response.status == 200){
          navigation.navigate("Chatroom", {"chatroom":response.data})
        }

    })
  }

  return (
    <View style={styles.container}>
      <Text>Your chatrooms:</Text>
      {
      chatrooms.map((chatroom, key)=>
        <Button title={"Awesome"}  
        buttonStyle={{
          backgroundColor: 'black', 
          borderWidth: 2,
          borderColor: 'white',
          borderRadius: 30,
        }}
        containerStyle={{
          width: 200,
          marginHorizontal: 50,
          marginVertical: 10,
        }}
        titleStyle={{ fontWeight: 'bold' }}
        onPress = {() => openChatroom(chatroom)}
        />
      )
     }
      <Text>Avaiable users:</Text>
     {
      users.map((user, key)=>
        <Button title={user.email}  
        buttonStyle={{
          backgroundColor: 'black',
          borderWidth: 2,
          borderColor: 'white',
          borderRadius: 30,
        }}
        containerStyle={{
          width: 200,
          marginHorizontal: 50,
          marginVertical: 10,
        }}
        titleStyle={{ fontWeight: 'bold' }}
        onPress = {() => createChatroom(user)}
        />
        
      )
     }



      <StatusBar style="auto" />
    </View>
  );
  
}
 
export default HomeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});



