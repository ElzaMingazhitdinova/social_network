package com.example.testingrestdocs.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
@CrossOrigin
public class UserController {
    //html
    @GetMapping(value = "/user")
    public String userPage(@RequestParam(name = "name") String name) {
        String html = this.fileToString("/Users/ruamgeg/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/html/user.html");
        return new Formatter().format(html, name).toString();
        //return Collections.(userRepository.findUser(name));
    }

    @GetMapping(value = "/user/list/page")
    public String userListPage(@RequestParam(name = "contains") String contains) {
        String html = this.fileToString("/Users/ruamgeg/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/html/user_list.html");
        return new Formatter().format(html, contains).toString();
    }

    //логика
    @GetMapping(value = "/user/list")
    public Map<String, Object> userList(@RequestParam(name = "contains") String contains) {
        ArrayList<String> response = new ArrayList<>(Arrays.asList("Tommy"));

        return Collections.singletonMap("response", response);
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
