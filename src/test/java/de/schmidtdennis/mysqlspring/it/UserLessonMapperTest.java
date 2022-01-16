package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.mapper.UserLessonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:META-INF/application-test.properties")
@SpringJUnitConfig(locations = "classpath:META-INF/test-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // The spring application context will be considered "dirty" before each test method, and will be rebuilt. It means that
// your autowired beans will not carry any data between test cases.
public class UserLessonMapperTest {

    @Autowired
    private UserLessonMapper userLessonMapper;

    @Test
    public void test(){
        // GIVEN
        Integer id = 1;

        // WHEN
        userLessonMapper.getUserLessonsByUserId(id);

        // THEN
    }
}
