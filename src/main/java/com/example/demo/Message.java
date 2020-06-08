package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="msg")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "message")
    private String post;

    @Column(name="username")
    private String username;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="user_username")
//    private User user;



    public Message(){
    }

    public Message(String username, String post) {
        this.username= username;
        this.post= post;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
