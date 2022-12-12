package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.model.dto.UserDto;
import com.example.backend.service.UserService;
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
@RequestMapping({"/user"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping({"/login"})
    ResponseEntity<String> login(@RequestBody UserDto userDto) {
        return this.userService.login(userDto);
    }

    @GetMapping({"/hashValue"})
    ResponseEntity<String> hashValue(@PathVariable String email) {
        String result = this.userService.hashValue(email);
        return result.isEmpty() ? new ResponseEntity("Not found", HttpStatus.NOT_FOUND) : new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping({"/register"})
    ResponseEntity<String> register(@RequestBody UserDto userDto) {
        return this.userService.register(userDto);
    }

    @GetMapping({"/getAll"})
    ResponseEntity<List<User>> getAll() {
        return new ResponseEntity(this.userService.getAll(), HttpStatus.OK);
    }

}
