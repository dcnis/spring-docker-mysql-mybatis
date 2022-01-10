package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import de.schmidtdennis.mysqlspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping(value = "user/add")
    public String addUser(@RequestBody User user) {
        userMapper.addUser(user.getFirstName(), user.getLastName(), user.getEmail());
        return "saved";
    }

    @GetMapping("user")
    public User getUser(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "email", required = false) String email){
        return userService.getUser(id, email);
    }

    @GetMapping("user/getAll")
    public List<User> getAll() {
        return userMapper.getAllUser();
    }

    @DeleteMapping("user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        userMapper.deleteUser(userId);
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
