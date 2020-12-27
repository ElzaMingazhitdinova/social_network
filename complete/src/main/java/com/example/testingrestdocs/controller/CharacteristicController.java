package com.example.testingrestdocs.controller;

import com.example.testingrestdocs.repository.UserRepository;
import com.example.testingrestdocs.service.CategoryService;
import com.example.testingrestdocs.service.CommentAppenderService;
import com.example.testingrestdocs.service.PostFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CharacteristicController {
    @Autowired
    private PostFinderService postFinderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentAppenderService commentAppenderService;
    @Autowired
    private UserRepository userRepository;

}
