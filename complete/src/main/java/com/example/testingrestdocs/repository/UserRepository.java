package com.example.testingrestdocs.repository;


import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Post;
import com.example.testingrestdocs.objects.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private Map<Long, User> userMap;
    private Map<String, Long> nameIndexMap;

    public final NamedParameterJdbcTemplate jdbcTemplate;
    RowMapper<User> rowMapper = (resultSet, i) -> {
        String username = resultSet.getString("username");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        String clientToken = resultSet.getString("clientToken");
        Long id = resultSet.getLong("id");
        return new User(id, username, phone, email, clientToken);
    };

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMap = new HashMap<>();
        this.nameIndexMap = new HashMap<>();
    }

    public void createUser(User user) {
        userMap.put(user.getId(), user);
        nameIndexMap.put(user.getUsername(), user.getId());
        SqlParameterSource params = params(user);
        new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("user")
                .usingColumns(params.getParameterNames())
                .execute(params);
    }

    public User findUser(Long id) {
        String query = "SELECT * FROM USER "   +
                "WHERE ID=:ID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return DataAccessUtils.singleResult(jdbcTemplate.query(query, params, rowMapper));
    }

    private MapSqlParameterSource params(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        Optional.ofNullable(user.getId()).ifPresent(id -> params.addValue("id", id));
        Optional.ofNullable(user.getUsername()).ifPresent(username -> params.addValue("username", username));
        Optional.ofNullable(user.getPhone()).ifPresent(phone -> params.addValue("phone", phone));
        Optional.ofNullable(user.getEmail()).ifPresent(email -> params.addValue("username", email));
        Optional.ofNullable(user.getClientToken()).ifPresent(clientToken -> params.addValue("clientToken", clientToken));
        return params;
    }
}
