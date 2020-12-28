package com.example.testingrestdocs.objects;

import java.util.Date;

public class Comment {
    private Long id;
    public String text;
    private Date date;
    private Long postid;

    public Long getPostid() {
        return postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }

    public Comment(Long id, String text, Date date, Long postid) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.postid = postid;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDateTime() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(Date date) {
        this.date = date;
    }

}
