package com.example.testingrestdocs.objects;

public class User {

    private Long id;
    private String username;
    private String phone;
    private String email;
    private String clienttoken;


    public User(Long id, String name, String phone, String email, String clienttoken) {
        this.id = id;
        this.username = name;
        this.phone = phone;
        this.email = email;
        this.clienttoken = clienttoken;
    }

    public Long getId() {
        return id;
    }

    public String getClientToken() {
        return clienttoken;
    }

    public void setClientToken(String clienttoken) {
        this.clienttoken = clienttoken;
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


