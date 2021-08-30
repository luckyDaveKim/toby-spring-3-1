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

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
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
    jdbcContextWithStatementStrategy(st);
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
    jdbcContextWithStatementStrategy(st);
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

  private void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
    try (
        Connection c = dataSource.getConnection();
        PreparedStatement ps = stmt.makePreparedStatement(c)
    ) {
      ps.executeUpdate();
    } catch (SQLException e) {
      throw e;
    }
  }
}
