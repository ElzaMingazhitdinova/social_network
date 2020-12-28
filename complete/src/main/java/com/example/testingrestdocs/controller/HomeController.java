package com.example.testingrestdocs;

import java.util.Collections;
import java.util.*;
import java.nio.file.Files;

import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Characteristic;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.objects.User;
import com.example.testingrestdocs.repository.UserRepository;
import com.example.testingrestdocs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;


//@RestController
//@CrossOrigin
public class HomeController {
    @Autowired
    private PostFinderService postFinderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentAppenderService commentAppenderService;
    @Autowired
    private CharacteristicService characteristicService;
    @Autowired
    private UserService userService;

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

    @GetMapping(value = "/posts")
    public String postListPage(@RequestParam(name = "id") String id) {
        String html = this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/posts.html");
        return new Formatter().format(html, id).toString();
    }

    @GetMapping(value = "/category")
    public String categoryPage(@RequestParam(name = "name") String name) {
        String html = this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/category.html");
        return new Formatter().format(html, name).toString();
    }

    @GetMapping(value = "/user")
    public String userPage(@RequestParam(name = "name") String name) {
        String html = this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/user.html");
        return new Formatter().format(html, name).toString();
    }

    @GetMapping(value = "/category/list/page")
    public String categoryListPage(@RequestParam(name = "contains") String contains) {
        String html = this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/category_list.html");
        return new Formatter().format(html, contains).toString();
    }

    @GetMapping(value = "/user/list/page")
    public String userListPage(@RequestParam(name = "contains") String contains) {
        String html = this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/user_list.html");
        return new Formatter().format(html, contains).toString();
    }

    //last
    @RequestMapping(value = "/user/categories")
    public String userCategories(@RequestParam(name = "id") String id) {
        String html = this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/user_categories.html");
        return new Formatter().format(html, id).toString();
    }

    @RequestMapping(value = "/post/page")
    public String postPage(@RequestParam(name = "id") String id) {
        String html = this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/post.html");
        return new Formatter().format(html, id).toString();
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        return this.fileToString("/Users/dtsapaev/Downloads/gs-testing-restdocs/complete/src/main/java/com/example/testingrestdocs/reg_auth.html");
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

    @GetMapping(value = "/category/list")
    public Map<String, Object> categoryList(@RequestParam(name = "contains") String contains) {
        ArrayList<String> response = new ArrayList<>(Arrays.asList("fun"));

        return Collections.singletonMap("response", response);
    }
    //получаем список постов в определенной категории
    @GetMapping(path = "/category/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object>  findCategoryWithPosts(@PathVariable Long id) {
        return Collections.singletonMap("response",categoryService.GetCategoryWithPosts(id) );
    }

    @GetMapping(value = "/user/list")
    public Map<String, Object> userList(@RequestParam(name = "contains") String contains) {
        ArrayList<String> response = new ArrayList<>(Arrays.asList("Tommy"));

        return Collections.singletonMap("response", response);
    }

    @GetMapping(value = "/user/category/list")
    public Map<String, Object> userCategoryList(@RequestParam(name = "id") String id) {
        ArrayList<String> response = new ArrayList<>(Arrays.asList("creepy"));

        return Collections.singletonMap("response", response);
    }

    @GetMapping(value = "/user/add_characteristic")
    public Map<String, Object> userCategory(
            @RequestParam(name = "user_id") String user_id,
            @RequestParam(name = "category_id") String category_id) {
        ArrayList<String> response = new ArrayList<>(Arrays.asList("creepy"));

        return Collections.singletonMap("response", response);
    }


    /*   @RequestMapping(value="/post")
    public Map<String, Object> post (@RequestParam(name = "id") String id) {
        Map<String, Object> response = new HashMap<String, Object>(){{
            put("id", "1");
            put("post", "Российским водителям напомнили правила прогрева автомобиля");
            put("author", "Micle");
            put("category", "cars");
            put("date", "22.12.2020");
            put("comment_list", new ArrayList<Object>(){{add()}});
        }};

    }*/

    @RequestMapping(path = "/post/{id}", method = RequestMethod.GET)
    public Post getPostById(@PathVariable Long id) {
        return postFinderService.getPost(id);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> gettingUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/post/create")
    public void createPost(@RequestBody Post post) {
        postFinderService.createNewPost(post);
    }

    @PostMapping(value = "/category/create")
    public void makeCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @PostMapping(value = "/characteristic/create")
    public void createCharac(@RequestBody Characteristic characteristic) {
        characteristicService.createCharasterictic(characteristic);
    }


 /*   @RequestMapping(value="/user/subscribe", method=RequestMethod.POST)
    public String subscribeUser(@RequestBody subscribeUserRequest post)  {
//		private String user_id;
//		private String author_id;
//		private boolean subscribe; - true - подписался, false - отписался

        return "ok";
    }

    @RequestMapping(value="/category/subscribe", method=RequestMethod.POST)
    public String subscribeCategory(@RequestBody subscribeCategoryRequest post)  {
//		private String user_id;
//		private String category_id;
//		private boolean subscribe; - true - подписался, false - отписался

        return "ok";
    }*/

    @PostMapping(value = "/user/create")
    public void createUser(@RequestBody User user) {
        userService.addUser(user);
    }
/*
    @RequestMapping(value="/user/logout", method=RequestMethod.POST)
    public String userLogout(@RequestBody userLogoutRequest post)  {
//		String token
        return "ok";
    }

    @RequestMapping(value="/post/set-mark", method=RequestMethod.POST)
    public String postSetMark(@RequestBody postSetMarkRequest post)  {
//		String postID
//		String userID
//		String characteristicID
//		String mark
//		boolean set - true поставить оценку, иначе снять

        return "ok";
    }

    @RequestMapping(value="/post/block", method=RequestMethod.POST)
    public String postSetMark(@RequestBody postBlockRequest post)  {
//		String postID
//		boolean block - true - заблокировать, иначе разблокировать

        return "ok";
    }*/
}
