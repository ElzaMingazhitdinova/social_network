package com.example.testingrestdocs.repository;

import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.objects.User;
import jdk.nashorn.api.scripting.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepository {
    private Map<Long, Post> postMap;
    private Map<String, Long> nameIndexMap;
    @Autowired
    private UserRepository userRepository;

    public final NamedParameterJdbcTemplate jdbcTemplate;
    RowMapper<Post> rowMapper = (resultSet, i) -> {
        String text = resultSet.getString("text");
        Long userid = resultSet.getLong("userid");
        Boolean blocked = resultSet.getBoolean("blocked");
        Date date = resultSet.getDate("date");
        Long id = resultSet.getLong("id");
        return new Post(id, userid, text, date,  blocked, null);
    };

    public PostRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.postMap = new HashMap<>();
        this.nameIndexMap = new HashMap<>();
    }

    public void CategoryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.nameIndexMap = new HashMap<>();
    }

    /*public void createPost(Post post) {
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
    }*/

    public void createPost(Post post) {
        postMap.put(post.getId(), post);
        nameIndexMap.put(post.getText(), post.getId());

        SqlParameterSource params = params(post);
        new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("post")
                .usingColumns(params.getParameterNames())
                .execute(params);
    }

    public Post getPost(Long id) {
        String query = "select * from post as p " +
                "where p.id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return DataAccessUtils.singleResult(jdbcTemplate.query(query, params, rowMapper));
    }

    public JSObject getPostWithCommentsAndUser(Long id) {
        String query = "SELECT p.*, c.*, u.username" +
                "FROM public.post as p" +
                "inner join public.user as u on u.id= p.userid" +
                "inner join comment as c on c.postid=p.id" +
                "where p.id=:id";
       MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", id);
        return JSObject;
    }

  /*  public void updatePost(Post post) {
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
*/
    private MapSqlParameterSource params(Post post) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        Optional.ofNullable(post.getId()).ifPresent(id -> params.addValue("id", id));
        Optional.ofNullable(post.getIdUser()).ifPresent(userid -> params.addValue("userid", userid));
        Optional.ofNullable(post.getText()).ifPresent(text -> params.addValue("text", text));
        Optional.ofNullable(post.getDateTime()).ifPresent(date -> params.addValue("date", date));
        Optional.ofNullable(post.isBlocked()).ifPresent(blocked -> params.addValue("blocked", blocked));
        return params;
    }
}