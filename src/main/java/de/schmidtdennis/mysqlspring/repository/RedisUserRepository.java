package de.schmidtdennis.mysqlspring.repository;

import de.schmidtdennis.mysqlspring.constants.RedisKeys;
import de.schmidtdennis.mysqlspring.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RedisUserRepository {

    private final HashOperations<String, Integer, User> hashOperationsUser;
    private final HashOperations<String, String, Integer> hashOperationsEmailToUserId;

    @Autowired
    public RedisUserRepository(HashOperations<String, Integer, User> hashOperationsUser,
                               HashOperations<String, String, Integer> hashOperationsEmailToUserId){
        this.hashOperationsUser = hashOperationsUser;
        this.hashOperationsEmailToUserId = hashOperationsEmailToUserId;
    }


    public void saveUser(User user){
        log.info("Save User to REDIS");
        hashOperationsUser.put(RedisKeys.REDIS_KEY_USER, user.getId(), user);
    }

    public void updateUser(User user){
        hashOperationsUser.put(RedisKeys.REDIS_KEY_USER, user.getId(), user);
    }

    public User findUser(int id){
        return hashOperationsUser.get(RedisKeys.REDIS_KEY_USER, id);
    }

    public void deleteUser(int id){
        hashOperationsUser.delete(RedisKeys.REDIS_KEY_USER, id);
    }

    public Integer getUserIdByEmail(String userEmail){
        Integer userId = hashOperationsEmailToUserId.get(RedisKeys.REDIS_KEY_EMAIL, userEmail);

        if(userId == null){
            return null;
        }

        log.info("Get Userid of " + userEmail);
        return userId;
    }

    public void saveUserEmail(Integer userId, String userEmail){
        log.info(String.format("Save %s:%s", userEmail, userId));
        hashOperationsEmailToUserId.put(RedisKeys.REDIS_KEY_EMAIL, userEmail, userId);
    }

}
