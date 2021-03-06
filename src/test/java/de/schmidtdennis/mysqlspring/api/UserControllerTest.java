package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import de.schmidtdennis.mysqlspring.model.request.GetUserRequest;
import de.schmidtdennis.mysqlspring.model.response.AddUserResponse;
import de.schmidtdennis.mysqlspring.repository.RedisUserRepository;
import de.schmidtdennis.mysqlspring.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RedisUserRepository redisUserRepository;

    @InjectMocks
    private UserController testee;

    @Test
    public void should_call_userservice(){
        // GIVEN
        User user = new User(1, "first", "last", "email");

        // WHEN
        testee.updateUser(user);

        // THEN
        Mockito.verify(userService, Mockito.times(1)).updateUser(user);
    }

    @Test
    public void should_throw_exception_if_id_is_missing(){
        // GIVEN
        User user = new User();
        user.setFirstName("first");

        // WHEN
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> testee.updateUser(user));

        // THEN
        assertThat(exception.getMessage()).isEqualTo("User-Id darf nicht null sein.");
    }


    @Test
    public void should_Not_throw_error_if_only_update_email(){
        // GIVEN
        User user = new User();
        user.setId(1);
        user.setEmail("email");

        // WHEN
        testee.updateUser(user);

        // THEN
        Mockito.verify(userService, Mockito.times(1)).updateUser(user);
    }

    @Test
    public void should_test_deleteUser(){
        // GIVEN

        // WHEN
        String response = testee.deleteUser(1);

        // THEN
        Mockito.verify(userMapper, Mockito.times(1)).deleteUser(1);
        Mockito.verify(redisUserRepository, Mockito.times(1)).deleteUser(1);
        assertThat(response).isEqualTo("User 1 deleted.");
    }

    @Test
    public void should_test_getAll(){
        // GIVEN

        // WHEN
        testee.getAll();

        // THEN
        Mockito.verify(userService, Mockito.times(1)).getAllUser();
    }

    @Test
    public void should_test_addUser(){
        // GIVEN
        User user = new User(20, "first", "last", "email");

        Mockito.when(userService.addUser(user)).thenReturn(new AddUserResponse(1,20 ));

        // WHEN
        AddUserResponse response = testee.addUser(user);

        // THEN
        Mockito.verify(userService, Mockito.times(1)).addUser(user);
        assertThat(response).isNotNull();
        assertThat(response.getInsertedRows()).isEqualTo(1);
        assertThat(response.getUserId()).isEqualTo(20);
    }

    @Test
    public void should_throw_exception_if_updateFields_are_missing(){
        // GIVEN
        User myUser = new User();
        myUser.setId(1);

        // WHEN
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> testee.updateUser(myUser));

        // THEN
        assertThat(exception.getMessage()).isEqualTo("Es muss mindestens ein Feld geupdated werden.");
    }

    @Test
    public void should_get_User(){
        // GIVEN
        User mockUser = new User(1, "first", "last", "email");
        Integer userId = 1;
        GetUserRequest request = new GetUserRequest(userId, "email");
        Mockito.when(userService.getUser(request)).thenReturn(mockUser);

        // WHEN
        User user = testee.getUser(request);

        // THEN
        Mockito.verify(userService, Mockito.times(1)).getUser(request);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getFirstName()).isEqualTo("first");
        assertThat(user.getLastName()).isEqualTo("last");
        assertThat(user.getEmail()).isEqualTo("email");
    }

}