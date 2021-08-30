package com.spring.example.dao;

import com.spring.example.domain.User;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

  private JdbcTemplate jdbcTemplate;

  public void setDataSource(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public void add(final User user) {
    jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
        user.getId(),
        user.getName(),
        user.getPassword());
  }

  public User get(String id) {
    return jdbcTemplate.queryForObject("select * from users where id = ?",
        (rs, rowNum) -> {
          User user = new User();
          user.setId(rs.getString("id"));
          user.setName(rs.getString("name"));
          user.setPassword(rs.getString("password"));
          return user;
        },
        id);
  }

  public List<User> getAll() {
    return jdbcTemplate.query("select * from users order by id",
        (rs, rowNum) -> {
          User user = new User();
          user.setId(rs.getString("id"));
          user.setName(rs.getString("name"));
          user.setPassword(rs.getString("password"));
          return user;
        });
  }

  public void deleteAll() {
    jdbcTemplate.update("delete from users");
  }

  public Integer getCount() {
    return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
  }
}
