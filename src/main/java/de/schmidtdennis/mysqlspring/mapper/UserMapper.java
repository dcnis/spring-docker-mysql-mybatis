package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAllUser();

    @Insert("INSERT into users (name, email) VALUES (#{userName}, #{userEmail})")
    void addUser(@Param("userName") String userName, @Param("userEmail") String userEmail);

}
