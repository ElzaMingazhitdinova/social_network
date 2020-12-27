package com.example.testingrestdocs;

import java.util.Collections;
import java.util.*;
import java.nio.file.Files;

import com.example.testingrestdocs.repository.UserRepository;
import com.example.testingrestdocs.service.CategoryService;
import com.example.testingrestdocs.service.CommentAppenderService;
import com.example.testingrestdocs.service.PostFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;


@RestController
//@CrossOrigin
public class HomeController {
    @Autowired
    private PostFinderService postFinderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentAppenderService commentAppenderService;
    @Autowired
    private UserRepository userRepository;

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

    @RequestMapping(value = "/post/list", method = RequestMethod.GET)
    public Map<String, Object> postList(@RequestParam(name = "id") String id, @RequestParam(name = "sorting", required = false) String sorting, @RequestParam(name = "category", required = false) String category, @RequestParam(name = "username", required = false) String username) {
        ArrayList<Map<String, String>> response = new ArrayList<>();
        if ("fun".equals(category)) {
            response.add(new HashMap<String, String>() {{
                put("id", "2");
                put("post", "Покупает мужик шляпу - а она ему как раз");
                put("author", "Jack");
                put("category", "fun");
                put("date", "21.12.2020");
            }});
        } else if ("Tommy".equals(username)) {
            response.add(new HashMap<String, String>() {{
                put("id", "3");
                put("post", "Она никак не могла понять, почему она отбрасывает две тени. Ведь в комнате была всего одна лампа.");
                put("author", "Tommy");
                put("category", "creepy");
                put("date", "20.12.2020");
            }});
        } else if ("By date".equals(sorting)) {
            response.add(new HashMap<String, String>() {{
                put("id", "1");
                put("post", "Российским водителям напомнили правила прогрева автомобиля");
                put("author", "Micle");
                put("category", "cars");
                put("date", "22.12.2020");
            }});
            response.add(new HashMap<String, String>() {{
                put("id", "2");
                put("post", "Покупает мужик шляпу - а она ему как раз");
                put("author", "Jack");
                put("category", "fun");
                put("date", "21.12.2020");
            }});
            response.add(new HashMap<String, String>() {{
                put("id", "3");
                put("post", "Она никак не могла понять, почему она отбрасывает две тени. Ведь в комнате была всего одна лампа.");
                put("author", "Tommy");
                put("category", "creepy");
                put("date", "20.12.2020");
            }});
            response.add(new HashMap<String, String>() {{
                put("id", "4");
                put("post", "Лемуры — примитивные приматы, латинское название которых в переводе означает дух, привидение");
                put("author", "Mark");
                put("category", "animals");
                put("date", "19.12.2020");
            }});
        } else {
            response.add(new HashMap<String, String>() {{
                put("id", "4");
                put("post", "Лемуры — примитивные приматы, латинское название которых в переводе означает дух, привидение");
                put("author", "Mark");
                put("category", "animals");
                put("date", "19.12.2020");
            }});
            response.add(new HashMap<String, String>() {{
                put("id", "3");
                put("post", "Она никак не могла понять, почему она отбрасывает две тени. Ведь в комнате была всего одна лампа.");
                put("author", "Tommy");
                put("category", "creepy");
                put("date", "20.12.2020");
            }});
            response.add(new HashMap<String, String>() {{
                put("id", "2");
                put("post", "Покупает мужик шляпу - а она ему как раз");
                put("author", "Jack");
                put("category", "fun");
                put("date", "21.12.2020");
            }});
            response.add(new HashMap<String, String>() {{
                put("id", "1");
                put("post", "Российским водителям напомнили правила прогрева автомобиля");
                put("author", "Micle");
                put("category", "cars");
                put("date", "22.12.2020");
            }});
        }

        return Collections.singletonMap("response", response);
    }

    @GetMapping(value = "/sorting/list")
    public Map<String, Object> sortings() {
        ArrayList<String> response = new ArrayList<>(Arrays.asList("By alphabet", "By date"));

        return Collections.singletonMap("response", response);
    }



    @GetMapping(value = "/category")
    public String categoryPage(@RequestParam(name = "name") String name) {
        String html = this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/category.html");
        return new Formatter().format(html, name).toString();
    }




    @RequestMapping(value = "/login")
    public String loginPage() {
        return this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/reg_auth.html");
    }

//	@RequestMapping(value="/post")
//	public Map<String, Object> post (@RequestParam(name = "id") String id) {
//		Map<String, Object> response = new HashMap<String, Object>(){{
//			put("id", "1");
//			put("post", "Российским водителям напомнили правила прогрева автомобиля");
//			put("author", "Micle");
//			put("category", "cars");
//			put("date", "22.12.2020");
//			put("comment_list", new ArrayList<Object>(){{add()}});
//		}};
//
//	}



    public class PostCreateRequest {
        private String text;
        private String author;
        private String Category;
    }

}
