package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAllUser();

    @Insert("INSERT into Users (first_name, last_name, email) VALUES (#{firstName}, #{lastName}, #{email})")
    void addUser(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email);

    void deleteUser(int userId);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Results(id = "userResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "email", column = "email")
    })
    @Select("SELECT * FROM Users WHERE Users.id=#{id}")
    User getUserById(@Param("id") String id);

    @ResultMap("userResult")
    @Select("SELECT * FROM Users WHERE Users.email=#{email}")
    User getUserByEmail(@Param("email") String email);

}
