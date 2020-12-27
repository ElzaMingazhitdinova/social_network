package com.example.testingrestdocs.service;


import com.example.testingrestdocs.objects.Comment;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.objects.User;
import com.example.testingrestdocs.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentAppenderService { /*
    @Autowired
    private PostRepository postRepository;

    public Post addComment(Post post, Comment comment, User user) throws Exception {
       if (user.getClientToken() == "REGISTERED") { //(проверить регистрацию клиента)
            ArrayList<Comment> comments = post.getComments();
            if (comments == null) {
                post.setComments(new ArrayList<>());
            }
            post.getComments().add(comment);
        } else throw new Exception("User has not got access to add comment");

        postRepository.updatePost(post);
        return post;
    }*/
}

