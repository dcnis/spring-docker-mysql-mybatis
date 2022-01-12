package de.schmidtdennis.mysqlspring.repository;

import de.schmidtdennis.mysqlspring.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RedisUserRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private HashOperations<String, Integer, User> hashOperationsUser;

    public static String USER_PREFIX = "User:";


    public void saveUser(User user){
        log.info("Save User to REDIS");
        hashOperationsUser.put(USER_PREFIX, user.getId(), user);
    }

    public void updateUser(User user){
        hashOperationsUser.put(USER_PREFIX, user.getId(), user);
    }

    public User findUser(int id){
        return hashOperationsUser.get(USER_PREFIX, id);
    }

    public void deleteUser(int id){
        hashOperationsUser.delete(USER_PREFIX, id);
    }

    public Integer getUserIdByEmail(String email){
        Object object = redisTemplate.opsForValue().get(email);

        if(object == null){
            return null;
        }

        log.info("Get Userid of " + email);
        return (Integer) object;
    }

    public void saveUserEmail(Integer id, String email){
        log.info(String.format("Save %s:%s", email, id));
        redisTemplate.opsForValue().set(email, id);
    }

}
