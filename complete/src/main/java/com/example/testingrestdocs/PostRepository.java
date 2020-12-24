package com.example.testingrestdocs;


import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.objects.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {
    private Map<Long, Post> postMap;
    private Map<Category, List<Long>> categoryIndexMap;
    private Map<User, List<Long>> userIndexMap;

    public PostRepository() {
        this.postMap = new HashMap<>();
        this.categoryIndexMap = new HashMap<>();
        this.userIndexMap = new HashMap<>();
    }

    public void createPost(Post post) {
        List<Long> categoryPostIds = categoryIndexMap.get(post.getCategory());
        List<Long> userPostIds = userIndexMap.get(post.getUser());

        if (categoryPostIds == null) {
            categoryPostIds = new ArrayList<>();
        }

        if (userPostIds == null) {
            userPostIds = new ArrayList<>();
        }
        categoryPostIds.add(post.getId());
        userPostIds.add(post.getId());
        postMap.put(post.getId(), post);
        categoryIndexMap.put(post.getCategory(), categoryPostIds);
        userIndexMap.put(post.getUser(), userPostIds);
    }

    public void updatePost(Post post) {
        postMap.put(post.getId(), post);
        List<Long> ids = categoryIndexMap.get(post.getCategory());
        ids.add(post.getId());
        categoryIndexMap.put(post.getCategory(), ids);
        userIndexMap.put(post.getUser(), ids);
    }

    public Post findPost(Long postId) {
        return postMap.get(postId);
    }

    public List<Post> findPostByCategory(Category category) {
        List<Long> postIds = categoryIndexMap.get(category);
        List<Post> posts = new ArrayList<>();

        if (postIds == null) {
            postIds = new ArrayList<>();
        }
        for (Long id : postIds) {
            posts.add(postMap.get(id));
        }
        return posts;
    }

    public List<Post> findPostByUser(User user) {
        List<Long> postIds = userIndexMap.get(user);
        List<Post> posts = new ArrayList<>();

        if (postIds == null) {
            postIds = new ArrayList<>();
        }
        for (Long id : postIds) {
            posts.add(postMap.get(id));
        }
        return posts;
    }
}