package com.springboot.example.dao;

public class UserDaoFactory {

  public UserDao userDao() {
    return new UserDao(connectionMaker());
  }

  private ConnectionMaker connectionMaker() {
    return new NConnectionMaker();
  }
}
