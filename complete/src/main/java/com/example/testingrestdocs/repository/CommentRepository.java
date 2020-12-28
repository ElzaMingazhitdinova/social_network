package com.example.testingrestdocs.repository;

import com.example.testingrestdocs.objects.Comment;
import com.example.testingrestdocs.objects.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CommentRepository {
    private Map<Long, Comment> postMap;
    private Map<String, Long> nameIndexMap;


    public final NamedParameterJdbcTemplate jdbcTemplate;
    RowMapper<Comment> rowMapper = (resultSet, i) -> {
        String text = resultSet.getString("text");
        Date date = resultSet.getDate("date");
        Long id = resultSet.getLong("id");
        Long postid = resultSet.getLong("postid");
        return new Comment(id, text, date, postid);
    };

    public CommentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.postMap = new HashMap<>();
        this.nameIndexMap = new HashMap<>();
    }

    public void createComment(Comment comment) {
        postMap.put(comment.getId(), comment);
        nameIndexMap.put(comment.getText(), comment.getId());

        SqlParameterSource params = params(comment);
        new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("comment")
                .usingColumns(params.getParameterNames())
                .execute(params);
    }

    private MapSqlParameterSource params(Comment comment) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        Optional.ofNullable(comment.getId()).ifPresent(id -> params.addValue("id", id));
        Optional.ofNullable(comment.getPostid()).ifPresent(postid -> params.addValue("postid", postid));
        Optional.ofNullable(comment.getText()).ifPresent(text -> params.addValue("text", text));
        Optional.ofNullable(comment.getDateTime()).ifPresent(date -> params.addValue("date", date));
        return params;
    }
}
