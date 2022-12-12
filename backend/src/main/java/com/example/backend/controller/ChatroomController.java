package com.example.backend.controller;

import com.example.backend.model.Chatroom;
import com.example.backend.model.Message;
import com.example.backend.service.ChatroomService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/chatroom"})
@RequiredArgsConstructor
public class ChatroomController {
    private final ChatroomService chatroomService;

    @PostMapping({"/createChatroom"})
    public ResponseEntity<Chatroom> createChatroom(@RequestBody Chatroom chatroom) {
        return new ResponseEntity<>(this.chatroomService.create(chatroom), HttpStatus.OK);
    }

    @GetMapping({"/openChatroom/{id}"})
    public ResponseEntity<Chatroom> getChatroom(@PathVariable Long id) {
        return new ResponseEntity<>((Chatroom)this.chatroomService.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping({"/getChatrooms"})
    public ResponseEntity<List<Chatroom>> getChatrooms() {
        return new ResponseEntity<>(this.chatroomService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/getMessageHistory/{chatroomId}"})
    public ResponseEntity<List<Message>> getMessageHistory(@PathVariable Long chatroomId) {
        return new ResponseEntity<>(this.chatroomService.findMessageHistory(chatroomId), HttpStatus.OK);
    }

}
