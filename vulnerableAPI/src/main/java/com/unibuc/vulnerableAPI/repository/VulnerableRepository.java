package com.unibuc.vulnerableAPI.repository;

import com.unibuc.vulnerableAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class VulnerableRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public VulnerableRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User createUser(User user) {
        String sql = "insert into users values (?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setFloat(3, user.getMoney());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        user.setId(generatedKeyHolder.getKey().longValue());
        return user;
    }

    public User getUser(String username, String password) {
        String sql = "select * from users  u WHERE username = '" + username + "' AND password ='" + password + "' LIMIT 1";

        return jdbcTemplate.query(sql, getUserRowMapper()).get(0);
    }

    private RowMapper<User> getUserRowMapper() {
        return (resultSet, rowNum) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setMoney(resultSet.getInt("money"));
            return user;
        };
    }
}
