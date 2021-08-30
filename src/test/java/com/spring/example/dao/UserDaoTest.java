package com.spring.example.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.spring.example.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {

  @Autowired
  private UserDao dao;

  private User user1;
  private User user2;
  private User user3;

  @BeforeEach
  void setUp() {
    user1 = new User("dave", "김민규", "lucky");
    user2 = new User("dave2", "김민규2", "lucky2");
    user3 = new User("dave3", "김민규3", "lucky3");
  }

  @Test
  public void addAndGet() {
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
  public void getUserFailure() {
    dao.deleteAll();
    assertThat(dao.getCount()).isEqualTo(0);

    assertThatThrownBy(() -> dao.get("unknown_id"))
        .isInstanceOf(EmptyResultDataAccessException.class);
  }

  @Test
  public void count() {
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
