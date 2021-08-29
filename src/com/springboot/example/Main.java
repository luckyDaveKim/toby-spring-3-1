package com.springboot.example;

import com.springboot.example.dao.NUserDao;
import com.springboot.example.dao.UserDao;
import com.springboot.example.domain.User;
import java.sql.SQLException;

public class Main {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    UserDao dao = new NUserDao();

    User user = new User();
    user.setId("dave");
    user.setName("김민규");
    user.setPassword("lucky");

    dao.add(user);

    System.out.println(user.getId() + " 등록 성공");

    User user2 = dao.get(user.getId());
    System.out.println(user2.getName());
    System.out.println(user2.getPassword());

    System.out.println(user2.getId() + " 조회 성공");
  }
}
