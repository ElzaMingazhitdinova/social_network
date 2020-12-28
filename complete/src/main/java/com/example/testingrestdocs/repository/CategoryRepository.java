package com.example.testingrestdocs.repository;

import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;

@Repository
public class CategoryRepository {
    private Map<Long, Category> categoryMap;
    private Map<String, Long> nameIndexMap;
  @Autowired
  private PostRepository postRepository;

    public final NamedParameterJdbcTemplate jdbcTemplate;
    RowMapper<Category> rowMapper = (resultSet, i) -> {
        String name = resultSet.getString("NAME");
        String description = resultSet.getString("DESCRIPTION");
        Long id = resultSet.getLong("ID");
        return new Category(id, name, description);
    };

    public final NamedParameterJdbcTemplate jdbcTemplate1;
    RowMapper<Post> rowMapper1 = (resultSet, i) -> {
        String text = resultSet.getString("text");
        Long userid = resultSet.getLong("userid");
        Boolean blocked = resultSet.getBoolean("blocked");
        Date date = resultSet.getDate("date");
        Long id = resultSet.getLong("id");
        return new Post(id, userid, text, date,  blocked, null);
    };

    private MapSqlParameterSource params1(Post post) {
        MapSqlParameterSource params1 = new MapSqlParameterSource();
        Optional.ofNullable(post.getId()).ifPresent(id -> params1.addValue("id", id));
        Optional.ofNullable(post.getIdUser()).ifPresent(userid -> params1.addValue("userid", userid));
        Optional.ofNullable(post.getText()).ifPresent(text -> params1.addValue("text", text));
        Optional.ofNullable(post.getDateTime()).ifPresent(date -> params1.addValue("date", date));
        Optional.ofNullable(post.isBlocked()).ifPresent(blocked -> params1.addValue("blocked", blocked));
        return params1;
    }

    private MapSqlParameterSource params(Category category) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        Optional.ofNullable(category.getId()).ifPresent(id -> params.addValue("id", id));
        Optional.ofNullable(category.getName()).ifPresent(name -> params.addValue("name", name));
        Optional.ofNullable(category.getDescription()).ifPresent(description -> params.addValue("description", description));
        return params;
    }

    public CategoryRepository(NamedParameterJdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate jdbcTemplate1) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate1 = jdbcTemplate1;
        this.categoryMap = new HashMap<>();
        this.nameIndexMap = new HashMap<>();
    }

    public void createCategory(Category category) {
        categoryMap.put(category.getId(), category);
        nameIndexMap.put(category.getName(), category.getId());

        SqlParameterSource params = params(category);
        new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("CATEGORY")
                .usingColumns(params.getParameterNames())
                .execute(params);
    }

    public Category findCategory(Long id) {
        //categoryMap.get(nameIndexMap.get(categoryName));
        String query = "SELECT * FROM CATEGORY " +
                "WHERE ID=:ID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id);
        return DataAccessUtils.singleResult(jdbcTemplate.query(query, params, rowMapper));
    }

    public List<Post> findCategoryWithPosts(Long id) {
        //categoryMap.get(nameIndexMap.get(categoryName));
      //  List<Post> posts = new ArrayList<>();
        String query = "select post.*" +
                "from post_x_category as p" +
                "inner join post on post.id = p.postId" +
                "where p.categoryId =:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        System.out.println(rowMapper1);
        System.out.println(jdbcTemplate1);
        return jdbcTemplate1.query(query,rowMapper1);
    }

    public List<Category> findAllCategories() {
        String query = "SELECT * FROM CATEGORY";

        return jdbcTemplate.query(query, rowMapper);
    }



}
/*
create table CATEGORY
(
    ID           bigserial  not null    primary key,
    NAME         varchar(20),
    DESCRIPTION  varchar(20) not null
);

INSERT INTO CATEGORY (NAME, DESCRIPTION) VALUES ('bubu1', 'dudu1');
INSERT INTO CATEGORY (NAME, DESCRIPTION) VALUES ('bubu2', 'dudu2');
*/