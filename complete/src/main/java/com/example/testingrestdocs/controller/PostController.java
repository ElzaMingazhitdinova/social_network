package com.example.testingrestdocs.controller;

import com.example.testingrestdocs.HomeController;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.repository.UserRepository;
import com.example.testingrestdocs.service.CategoryService;
import com.example.testingrestdocs.service.CommentAppenderService;
import com.example.testingrestdocs.service.PostFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;

@RestController
@CrossOrigin
public class PostController {
    @Autowired
    private PostFinderService postFinderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentAppenderService commentAppenderService;
    @Autowired
    private UserRepository userRepository;

    //html
    @RequestMapping(value = "/post/page")
    public String post(@RequestParam(name = "id") String id) {
        String html = this.fileToString("/Users/ruamgeg/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/html/category.html");
        return new Formatter().format(html, id).toString();
    }

    @GetMapping(value = "/posts")
    public String postListPage() {
        return this.fileToString("/Users/ruamgeg/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/html/posts.html");
    }

    //логика
    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(PostFinderService.createNewPost(post), HttpStatus.OK);
    }

    public String fileToString(String filename) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return sb.toString();
    }

}
