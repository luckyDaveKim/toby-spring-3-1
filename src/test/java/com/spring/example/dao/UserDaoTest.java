package com.spring.example.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.spring.example.domain.User;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDaoTest {

  @Test
  public void addAndGet() throws SQLException {
    ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    UserDao dao = context.getBean("userDao", UserDao.class);

    User user1 = new User("dave", "김민규", "lucky");
    User user2 = new User("dave2", "김민규2", "lucky2");

    dao.deleteAll();
    assertThat(dao.getCount()).isEqualTo(0);

    dao.add(user1);
    dao.add(user2);
    assertThat(dao.getCount()).isEqualTo(2);

    User foundUser1 = dao.get(user1.getId());
    assertThat(foundUser1.getName()).isEqualTo(user1.getName());
    assertThat(foundUser1.getPassword()).isEqualTo(user1.getPassword());

    User foundUser2 = dao.get(user2.getId());
    assertThat(foundUser2.getName()).isEqualTo(user2.getName());
    assertThat(foundUser2.getPassword()).isEqualTo(user2.getPassword());
  }

  @Test
  public void getUserFailure() throws SQLException {
    ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    UserDao dao = context.getBean("userDao", UserDao.class);

    dao.deleteAll();
    assertThat(dao.getCount()).isEqualTo(0);

    assertThatThrownBy(() -> dao.get("unknown_id"))
        .isInstanceOf(EmptyResultDataAccessException.class);
  }

  @Test
  public void count() throws SQLException {
    ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    UserDao dao = context.getBean("userDao", UserDao.class);

    User user1 = new User("dave", "김민규", "lucky");
    User user2 = new User("dave2", "김민규2", "lucky2");
    User user3 = new User("dave3", "김민규3", "lucky3");

    dao.deleteAll();
    assertThat(dao.getCount()).isEqualTo(0);

    dao.add(user1);
    assertThat(dao.getCount()).isEqualTo(1);

    dao.add(user2);
    assertThat(dao.getCount()).isEqualTo(2);

    dao.add(user3);
    assertThat(dao.getCount()).isEqualTo(3);
  }
}
