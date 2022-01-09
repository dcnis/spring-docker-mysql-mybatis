package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService testee;

    @Test
    public void should_call_userMapper(){
        // GIVEN
        User user = new User(1, "first", "last", "email");

        // WHEN
        testee.updateUser(user);

        // THEN
        Mockito.verify(userMapper, Mockito.times(1)).update(ArgumentMatchers.any(UpdateStatementProvider.class));
    }

    @Test
    public void should_update_all_elements(){
        // GIVEN
        User user = new User(99, "first", "last", "email");
        String expected = "update Users set first_name = #{parameters.p1,jdbcType=VARCHAR}, last_name = #{parameters.p2,jdbcType=VARCHAR}, " +
                "email = #{parameters.p3,jdbcType=VARCHAR} where id = #{parameters.p4,jdbcType=INTEGER}";

        // WHEN
        testee.updateUser(user);

        // THEN
        Mockito.verify(userMapper, Mockito.times(1)).update(ArgumentMatchers.argThat(updateStatementProvider -> {
            boolean statementOk = updateStatementProvider.getUpdateStatement().equals(expected);
            boolean parametersOk = updateStatementProvider.getParameters().size() == 4;
            boolean firstNameOk = updateStatementProvider.getParameters().get("p1").equals("first");
            boolean lastNameOk = updateStatementProvider.getParameters().get("p2").equals("last");
            boolean emailOk = updateStatementProvider.getParameters().get("p3").equals("email");
            boolean idOk = updateStatementProvider.getParameters().get("p4").equals(99);
            return statementOk && parametersOk && firstNameOk && lastNameOk && emailOk && idOk;
        }));
    }

    @Test
    public void should_only_update_firstName(){
        // GIVEN
        User user = new User();
        user.setId(88);
        user.setFirstName("firstName");

        String expected = "update Users set first_name = #{parameters.p1,jdbcType=VARCHAR} where id = #{parameters.p2,jdbcType=INTEGER}";

        // WHEN
        testee.updateUser(user);

        // THEN
        Mockito.verify(userMapper, Mockito.times(1)).update(ArgumentMatchers.argThat(updateStatementProvider -> {
            boolean statementOk = updateStatementProvider.getUpdateStatement().equals(expected);
            boolean parametersOk = updateStatementProvider.getParameters().size() == 2;
            boolean firstNameOk = updateStatementProvider.getParameters().get("p1").equals("firstName");
            boolean idOk = updateStatementProvider.getParameters().get("p2").equals(88);
            return statementOk && parametersOk && firstNameOk && idOk;
        }));
    }

}