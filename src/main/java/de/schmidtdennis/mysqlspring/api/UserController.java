package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import de.schmidtdennis.mysqlspring.model.request.GetUserRequest;
import de.schmidtdennis.mysqlspring.model.response.AddUserResponse;
import de.schmidtdennis.mysqlspring.repository.RedisUserRepository;
import de.schmidtdennis.mysqlspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final RedisUserRepository redisUserRepository;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService, RedisUserRepository redisUserRepository){
        this.userMapper = userMapper;
        this.userService = userService;
        this.redisUserRepository = redisUserRepository;
    }

    @PostMapping(value = "user/add")
    public AddUserResponse addUser(@RequestBody User user) {
       return userService.addUser(user);
    }

    @PostMapping("user/get")
    public User getUser(@RequestBody GetUserRequest request){
        return userService.getUser(request);
    }

    @GetMapping("user/getAll")
    public List<User> getAll() {
        return userService.getAllUser();
    }

    @DeleteMapping("user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        userMapper.deleteUser(userId);
        redisUserRepository.deleteUser(userId);
        return "User " + userId + " deleted.";
    }

    @PostMapping("user/update")
    public String updateUser(@RequestBody User user) {
        this.checkUpdateUserRequest(user);
        int rows = userService.updateUser(user);
        return rows + " updated.";
    }

    private void checkUpdateUserRequest(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User-Id darf nicht null sein.");
        }

        try {
            if (!user.atLeastOneFieldToBeUpdated()) {
                throw new IllegalArgumentException("Es muss mindestens ein Feld geupdated werden.");
            }
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Unbekannter Fehler");
        }

    }
}
