package com.example.testingrestdocs.repository;

import com.example.testingrestdocs.objects.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Date;
import java.util.HashMap;

public class CharacteristicRepository {

    /* public final NamedParameterJdbcTemplate jdbcTemplate;
    RowMapper<Post> rowMapper = (resultSet, i) -> {
        String text = resultSet.getString("text");
        // Long userId = resultSet.getLong("userId");
        //Long categoryId = resultSet.getLong("categoryId");
        //Long commentId = resultSet.getLong("commentId");
        Date date = resultSet.getDate("date");
        Long id = resultSet.getLong("id");
        return new Post(id, null, text, date, null, null);
    };

   public CharacteristicRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.postMap = new HashMap<>();
        this.userIndexMap = new HashMap<>();
    }*/

}
