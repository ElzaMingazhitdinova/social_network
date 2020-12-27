package com.example.testingrestdocs.service;



import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.repository.PostRepository;
import com.example.testingrestdocs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostFinderService postFinderService;

    public List<Post> GetUserWithPost(Long id) {
       return postFinderService.findByUser(id);
    }

}
