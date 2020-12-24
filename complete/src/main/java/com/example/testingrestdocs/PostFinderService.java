package com.example.testingrestdocs;


import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostFinderService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Post> findByCategory(String categoryName) {
        Category category = categoryRepository.findCategory(categoryName);
        return postRepository.findPostByCategory(category);
    }

    public List<Post> findByUser(String userName) {
        User user = userRepository.findUser(userName);
        return postRepository.findPostByUser(user);
    }
}
