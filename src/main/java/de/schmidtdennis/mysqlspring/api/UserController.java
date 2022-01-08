package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.DatabaseMapper;
import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DatabaseMapper databaseMapper;


    @PostMapping(value = "user/add")
    public String addUser(@RequestBody User user){
        userMapper.addUser(user.getFirstName(), user.getLastName(), user.getEmail());
        return "saved";
    }

    @GetMapping("user/getAll")
    public List<User> getAll(){
        return userMapper.getAllUser();
    }

    @PostMapping("/createTable")
    public void createTable(@RequestBody String dbName){
        databaseMapper.createTableIfNotExists(dbName);
    }

}
