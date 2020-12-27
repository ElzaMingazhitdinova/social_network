package com.example.testingrestdocs.service;


import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.objects.User;
import com.example.testingrestdocs.repository.CategoryRepository;
import com.example.testingrestdocs.repository.PostRepository;
import com.example.testingrestdocs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostFinderService {
    @Autowired
    private static PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    public static Post createNewPost(Post post) {
        postRepository.savePost(post);
        return post;
    }

    public List<Post> findByCategory(Long id) {
        Category category = categoryRepository.findCategory(id);
        return postRepository.findPostByCategory(category);
    }

    public List<Post> findByUser(Long id) {
        User user = userRepository.findUser(id);
        return postRepository.findPostByUser(user);
    }
}
