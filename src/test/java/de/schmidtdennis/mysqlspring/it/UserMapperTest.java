package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:META-INF/application-test.properties")
@SpringJUnitConfig(locations = "classpath:META-INF/test-context.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        userMapper.addUser("Dennis", "Schmidt", "dennis.schmidt@mail.de");
        System.out.println(userMapper.getAllUser());
    }

}
