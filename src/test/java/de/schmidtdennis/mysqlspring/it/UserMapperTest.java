package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.JDBCType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:META-INF/application-test.properties")
@SpringJUnitConfig(locations = "classpath:META-INF/test-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // The spring application context will be considered "dirty" before each test method, and will be rebuilt. It means that
// your autowired beans will not carry any data between test cases.
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private static final SqlTable users = SqlTable.of("Users");
    private static final SqlColumn<Integer> id = users.column("id", JDBCType.INTEGER);
    private static final SqlColumn<String> firstName = users.column("first_name", JDBCType.VARCHAR);
    private static final SqlColumn<String> lastName = users.column("last_name", JDBCType.VARCHAR);
    private static final SqlColumn<String> email = users.column("email", JDBCType.VARCHAR);

    @Test
    public void should_add_user(){
        // GIVEN
        userMapper.addUser("Dennis", "Schmidt", "dennis.schmidt@mail.de");

        // WHEN
        List<User> users = userMapper.getAllUser();

        // THEN
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getId()).isEqualTo(1);
        assertThat(users.get(0).getEmail()).isEqualTo("dennis.schmidt@mail.de");
        assertThat(users.get(0).getFirstName()).isEqualTo("Dennis");
        assertThat(users.get(0).getLastName()).isEqualTo("Schmidt");
    }

    @Test
    public void should_delete_user(){
        // GIVEN
        userMapper.addUser("Dennis", "Schmidt", "dennis.schmidt@mail.de");

        // WHEN
        userMapper.deleteUser(1);

        // THEN
        assertThat(userMapper.getAllUser().size()).isEqualTo(0);
    }

    @Test
    public void should_getUserById(){
        // GIVEN
        userMapper.addUser("Antiqa", "Massito", "antiqa.mastio@mail.de");
        userMapper.addUser("Dennis", "Schmidt", "dennis.schmidt@mail.de");

        // WHEN
        User user = userMapper.getUserById(2);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getFirstName()).isEqualTo("Dennis");
        assertThat(user.getLastName()).isEqualTo("Schmidt");
        assertThat(user.getEmail()).isEqualTo("dennis.schmidt@mail.de");
    }

    @Test
    public void should_getUserByEmail(){
        // GIVEN
        userMapper.addUser("Antiqa", "Massito", "antiqa.mastio@mail.de");
        userMapper.addUser("Dennis", "Schmidt", "dennis.schmidt@mail.de");

        // WHEN
        User user = userMapper.getUserByEmail("dennis.schmidt@mail.de");

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getFirstName()).isEqualTo("Dennis");
        assertThat(user.getLastName()).isEqualTo("Schmidt");
        assertThat(user.getEmail()).isEqualTo("dennis.schmidt@mail.de");
    }


    @Test
    public void should_return_null_if_email_does_not_exist(){
        // GIVEN
        userMapper.addUser("Antiqa", "Massito", "antiqa.mastio@mail.de");
        userMapper.addUser("Dennis", "Schmidt", "dennis.schmidt@mail.de");

        // WHEN
        User user = userMapper.getUserByEmail("doesnotexist@mail.de");

        // THEN
        assertThat(user).isNull();
    }

    @Test
    public void should_return_null_if_id_does_not_exist(){
        // GIVEN
        userMapper.addUser("Antiqa", "Massito", "antiqa.mastio@mail.de");
        userMapper.addUser("Dennis", "Schmidt", "dennis.schmidt@mail.de");

        // WHEN
        User user = userMapper.getUserById(100);

        // THEN
        assertThat(user).isNull();
    }

    @Test
    public void should_update_user(){
        // GIVEN
        userMapper.addUser("Antiqa", "Massito", "antiqa.mastio@mail.de");
        userMapper.addUser("Dennis", "Schmidt", "dennis.schmidt@mail.de");

        UpdateStatementProvider updateStatement = update(users)
                .set(firstName).equalToWhenPresent("Dennis_Updated")
                .set(lastName).equalToWhenPresent("Schmidt_Updated")
                .set(email).equalToWhenPresent("Email_Updated")
                .where(id, isEqualTo(2))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        // WHEN
        int rows = userMapper.update(updateStatement);
        User updatedUser = userMapper.getUserById(2);

        // THEN
        assertThat(rows).isEqualTo(1);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(2);
        assertThat(updatedUser.getFirstName()).isEqualTo("Dennis_Updated");
        assertThat(updatedUser.getLastName()).isEqualTo("Schmidt_Updated");
        assertThat(updatedUser.getEmail()).isEqualTo("Email_Updated");
    }

}
