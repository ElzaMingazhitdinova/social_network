package com.example.testingrestdocs.controller;

import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.repository.UserRepository;
import com.example.testingrestdocs.service.CategoryService;
import com.example.testingrestdocs.service.CommentAppenderService;
import com.example.testingrestdocs.service.PostFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Formatter;
import java.util.Map;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private PostFinderService postFinderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentAppenderService commentAppenderService;
    @Autowired
    private UserRepository userRepository;

    //html
    @GetMapping(value = "/category")
    public String categoryPage(@RequestParam(name = "name") String name) {
        String html = this.fileToString("/Users/ruamgeg/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/html/category.html");
        return new Formatter().format(html, name).toString();
    }

    @GetMapping(value = "/category/list/page")
    public String categoryListPage(@RequestParam(name = "contains") String contains) {
        String html = this.fileToString("/Users/ruamgeg/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/html/category_list.html");
        return new Formatter().format(html, contains).toString();
    }

    //логика

    @PostMapping(value = "/category")
    public void makeCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @GetMapping(value = "/category/")
    public Map<String, Object> categoryList() {
        return Collections.singletonMap("response", categoryService.findAllCategories());
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
