package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import de.schmidtdennis.mysqlspring.model.request.GetUserRequest;
import de.schmidtdennis.mysqlspring.model.response.AddUserResponse;
import de.schmidtdennis.mysqlspring.repository.RedisUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.JDBCType;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;

@Service
@Slf4j
public class UserService {

    private final UserMapper userMapper;
    private final RedisUserRepository redisUserRepository;

    @Autowired
    public UserService(UserMapper userMapper, RedisUserRepository redisUserRepository){
        this.userMapper = userMapper;
        this.redisUserRepository = redisUserRepository;
    }

    private static final SqlTable users = SqlTable.of("Users");
    private static final SqlColumn<Integer> id = users.column("id", JDBCType.INTEGER);
    private static final SqlColumn<String> firstName = users.column("first_name", JDBCType.VARCHAR);
    private static final SqlColumn<String> lastName = users.column("last_name", JDBCType.VARCHAR);
    private static final SqlColumn<String> email = users.column("email", JDBCType.VARCHAR);

    public AddUserResponse addUser(User user){
        Integer insertedRows = userMapper.addUser(user);
        redisUserRepository.saveUser(user);
        return new AddUserResponse(insertedRows, user.getId());
    }

    public int updateUser(User user){

        UpdateStatementProvider updateStatement = update(users)
                .set(firstName).equalToWhenPresent(user.getFirstName())
                .set(lastName).equalToWhenPresent(user.getLastName())
                .set(email).equalToWhenPresent(user.getEmail())
                .where(id, isEqualTo(user.getId()))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        this.updateUserInRedis(user);
        return userMapper.update(updateStatement);
    }

    private void updateUserInRedis(User user) {
        User redisUser = redisUserRepository.findUser(user.getId());
        if(redisUser != null){

            if(user.getFirstName() != null){
                redisUser.setFirstName(user.getFirstName());
            }
            if(user.getLastName() != null){
                redisUser.setLastName(user.getLastName());
            }
            if(user.getEmail() != null){
                redisUser.setEmail(user.getEmail());
            }

            log.info("User in Redis geupdated");
            redisUserRepository.updateUser(redisUser);
        }
    }

    public User getUser(GetUserRequest request){

        if(request.getUserId() != null){
            User userFromRedis = redisUserRepository.findUser(request.getUserId());
            if(userFromRedis != null){
                log.info("Return User {} from Redis", userFromRedis.getId());
                return userFromRedis;
            } else {
                log.info("Get User from MySQL Database by id");
                User user = userMapper.getUserById(request.getUserId());
                redisUserRepository.saveUser(user);
                return user;
            }
        } else if(request.getUserEmail() != null){

            Integer userId = redisUserRepository.getUserIdByEmail(request.getUserEmail());

            if(userId != null){
                return redisUserRepository.findUser(userId);
            } else {
                log.info("Get User from MySQL Database by email");
                User user = userMapper.getUserByEmail(request.getUserEmail());
                redisUserRepository.saveUserEmail(user.getId(), request.getUserEmail());
                return user;
            }
        }

        return null;
    }


    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }
}
