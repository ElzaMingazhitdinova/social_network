package com.example.testingrestdocs.objects;

import java.util.Date;

public class Comment {
    private Long id;
    public String text;
    private Date dateTime;

    public Comment(Long id, String text, Date dateTime) {
        this.id = id;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

}
