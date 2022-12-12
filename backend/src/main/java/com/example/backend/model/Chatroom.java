package com.example.backend.model;


import lombok.Data;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Data
public class Chatroom {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;
    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    List<User> users;
    String chatroomName;

}
