package learningtest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/emptyContext.xml")
public class JUnitTest {

  static Set<JUnitTest> testObjects = new HashSet<>();
  static ApplicationContext contextObject = null;

  @Autowired
  ApplicationContext context;

  @Test
  public void contextObjectSeparationTest1() {
    assertThat(testObjects).isNotIn(this);
    testObjects.add(this);

    assertThat(contextObject).satisfiesAnyOf(
        param -> assertThat(param).isNull(),
        param -> assertThat(param).isEqualTo(context)
    );
    contextObject = this.context;
  }

  @Test
  public void contextObjectSeparationTest2() {
    assertThat(testObjects).isNotIn(this);
    testObjects.add(this);

    assertThat(contextObject).satisfiesAnyOf(
        param -> assertThat(param).isNull(),
        param -> assertThat(param).isEqualTo(context)
    );
    contextObject = this.context;
  }

  @Test
  public void contextObjectSeparationTest3() {
    assertThat(testObjects).isNotIn(this);
    testObjects.add(this);

    assertThat(contextObject).satisfiesAnyOf(
        param -> assertThat(param).isNull(),
        param -> assertThat(param).isEqualTo(context)
    );
    contextObject = this.context;
  }
}
