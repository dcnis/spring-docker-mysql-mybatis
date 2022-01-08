package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "user/add")
    public String addUser(@RequestBody User user){
        userMapper.addUser(user.getFirstName(), user.getLastName(), user.getEmail());
        return "saved";
    }

    @GetMapping("user/getAll")
    public List<User> getAll(){
        return userMapper.getAllUser();
    }

    @DeleteMapping("user/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId){
        userMapper.deleteUser(userId);
        return "User " + userId + " deleted.";
    }

}
