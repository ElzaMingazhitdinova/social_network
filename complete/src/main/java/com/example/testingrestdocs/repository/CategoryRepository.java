package com.example.testingrestdocs.repository;

import com.example.testingrestdocs.objects.Category;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CategoryRepository {
    private Map<Long, Category> categoryMap;
    private Map<String, Long> nameIndexMap;

    public final NamedParameterJdbcTemplate jdbcTemplate;
    RowMapper<Category> rowMapper = (resultSet, i) -> {
        String name = resultSet.getString("NAME");
        String description = resultSet.getString("DESCRIPTION");
        Long id = resultSet.getLong("ID");

        return new Category(id, name, description, null,null);
    };

    public CategoryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        String query = "SELECT * FROM CATEGORY "   +
                "WHERE ID=:ID";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id);
        return DataAccessUtils.singleResult(jdbcTemplate.query(query, params, rowMapper));
    }

    public List<Category> findAllCategories() {
        String query = "SELECT * FROM CATEGORY";

        return jdbcTemplate.query(query, rowMapper);
    }

    private MapSqlParameterSource params(Category category) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        Optional.ofNullable(category.getId()).ifPresent(id -> params.addValue("ID", id));
        Optional.ofNullable(category.getName()).ifPresent(name -> params.addValue("NAME", name));
        Optional.ofNullable(category.getDescription()).ifPresent(description -> params.addValue("DESCRIPTION", description));
        return params;
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