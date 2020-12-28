package com.example.testingrestdocs.repository;

import com.example.testingrestdocs.objects.Category;
import com.example.testingrestdocs.objects.Characteristic;
import com.example.testingrestdocs.objects.Post;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
public class CharacteristicRepository {
    private Map<Long, Characteristic> characteristicMapMap;
    private Map<String, Long> nameIndexMap;

    public final NamedParameterJdbcTemplate jdbcTemplate;
    RowMapper<Characteristic> rowMapper = (resultSet, i) -> {
        String name = resultSet.getString("name");
        String type = resultSet.getString("type");
        Long id = resultSet.getLong("id");
        Boolean refillable = resultSet.getBoolean("refillable");
        return new Characteristic(id, name, type, refillable);
    };

    public CharacteristicRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.characteristicMapMap = new HashMap<>();
        this.nameIndexMap = new HashMap<>();
    }

    private MapSqlParameterSource params(Characteristic characteristic) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        Optional.ofNullable(characteristic.getId()).ifPresent(id -> params.addValue("id", id));
        Optional.ofNullable(characteristic.getName()).ifPresent(name -> params.addValue("name", name));
        Optional.ofNullable(characteristic.getType()).ifPresent(description -> params.addValue("type", description));
        Optional.ofNullable(characteristic.isRefillable()).ifPresent(refillable -> params.addValue("refillable", refillable));
        return params;
    }

    public void createCharasterictic(Characteristic characteristic) {
        characteristicMapMap.put(characteristic.getId(), characteristic);
        nameIndexMap.put(characteristic.getName(), characteristic.getId());

        SqlParameterSource params = params(characteristic);
        new SimpleJdbcInsert(jdbcTemplate.getJdbcTemplate())
                .withTableName("characteristic")
                .usingColumns(params.getParameterNames())
                .execute(params);
    }
}

