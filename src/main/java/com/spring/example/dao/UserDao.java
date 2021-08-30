package com.spring.example.dao;

import com.spring.example.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {

  private DataSource dataSource;
  private JdbcContext jdbcContext;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void setJdbcContext(JdbcContext jdbcContext) {
    this.jdbcContext = jdbcContext;
  }

  public void add(final User user) throws SQLException {
    StatementStrategy st = c -> {
      PreparedStatement ps = c.prepareStatement(
          "insert into users(id, name, password) values(?,?,?)");
      ps.setString(1, user.getId());
      ps.setString(2, user.getName());
      ps.setString(3, user.getPassword());

      return ps;
    };
    jdbcContext.workWithStatementStrategy(st);
  }

  public User get(String id) throws SQLException {
    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      c = dataSource.getConnection();

      ps = c.prepareStatement("select * from users where id = ?");
      ps.setString(1, id);

      rs = ps.executeQuery();

      User user = null;
      if (rs.next()) {
        user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
      }

      if (user == null) {
        throw new EmptyResultDataAccessException(1);
      }

      return user;
    } catch (SQLException e) {
      throw e;
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
        }
      }
      if (ps != null) {
        try {
          ps.close();
        } catch (SQLException e) {
        }
      }
      if (c != null) {
        try {
          c.close();
        } catch (SQLException e) {
        }
      }
    }
  }

  public void deleteAll() throws SQLException {
    StatementStrategy st = c -> c.prepareStatement("delete from users");
    jdbcContext.workWithStatementStrategy(st);
  }

  public int getCount() throws SQLException {
    try (
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement(
            "select count(*) from users");
        ResultSet rs = ps.executeQuery()
    ) {
      rs.next();
      return rs.getInt(1);
    } catch (SQLException e) {
      throw e;
    }
  }
}
