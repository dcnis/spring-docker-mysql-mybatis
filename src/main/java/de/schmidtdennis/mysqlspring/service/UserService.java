package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.User;
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
public class UserService {

    @Autowired
    private UserMapper userMapper;

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

        return userMapper.update(updateStatement);
    }


}
