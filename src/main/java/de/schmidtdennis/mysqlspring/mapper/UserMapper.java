package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;
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

}
