package com.example.testingrestdocs.objects;

public class User {

    private Long id;
    private String username;
    private String phone;
    private String email;
    private String clientToken;


    public User(Long id, String name, String phone, String email, String clientToken) {
        this.id = id;
        this.username = name;
        this.phone = phone;
        this.email = email;
        this.clientToken = clientToken;
    }

    public Long getId() {
        return id;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


