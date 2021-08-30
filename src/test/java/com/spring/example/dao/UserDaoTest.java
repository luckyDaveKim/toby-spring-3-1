package com.spring.example.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring.example.domain.User;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

  @Test
  public void addAndGet() throws SQLException {
    ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    UserDao dao = context.getBean("userDao", UserDao.class);

    User user = new User();
    user.setId("dave");
    user.setName("김민규");
    user.setPassword("lucky");

    dao.add(user);

    User user2 = dao.get(user.getId());
    assertThat(user2.getName()).isEqualTo(user.getName());
    assertThat(user2.getPassword()).isEqualTo(user.getPassword());
  }
}
