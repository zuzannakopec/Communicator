package com.example.backend.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;
    String text;
    @OneToOne(
            cascade = {CascadeType.MERGE}
    )
    Chatroom chatroom;
    @OneToOne(
            cascade = {CascadeType.MERGE}
    )
    User user;

}
