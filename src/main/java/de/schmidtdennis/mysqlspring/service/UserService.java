package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
import de.schmidtdennis.mysqlspring.repository.RedisUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.mybatis.dynamic.sql.SqlBuilder.update;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.sql.JDBCType;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUserRepository redisUserRepository;

    private static final SqlTable foo = SqlTable.of("Users");
    private static final SqlColumn<Integer> id = foo.column("id", JDBCType.INTEGER);
    private static final SqlColumn<String> firstName = foo.column("first_name", JDBCType.VARCHAR);
    private static final SqlColumn<String> lastName = foo.column("last_name", JDBCType.VARCHAR);
    private static final SqlColumn<String> email = foo.column("email", JDBCType.VARCHAR);

    public int updateUser(User user){

        UpdateStatementProvider updateStatement = update(foo)
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

    public User getUser(Integer id, String email){

        if(id != null){
            if(redisUserRepository.findUser(id) != null){
                return redisUserRepository.findUser(id);
            } else {
                log.info("Get User from MySQL Database by id");
                User user = userMapper.getUserById(id);
                redisUserRepository.saveUser(user);
                return user;
            }
        } else if(email != null){

            Integer userId = redisUserRepository.getUserIdByEmail(email);

            if(userId != null){
                return redisUserRepository.findUser(userId);
            } else {
                log.info("Get User from MySQL Database by email");
                User user = userMapper.getUserByEmail(email);
                redisUserRepository.saveUserEmail(user.getId(), email);
                return user;
            }
        }

        return null;
    }


}
