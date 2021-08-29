package com.springboot.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao extends UserDao {

  protected Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("org.mariadb.jdbc.Driver");
    return DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/spring?useUnicode=true&characterEncoding=utf8mb4",
        "root",
        "root");
  }
}