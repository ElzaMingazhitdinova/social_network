package com.example.testingrestdocs.controller;

import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Comment;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.service.CommentAppenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentAppenderService commentAppenderService;

    @PostMapping(value = "/comment/create")
    public void createNewComment(@RequestBody Comment comment) {
        commentAppenderService.createComment(comment);
    }

}
