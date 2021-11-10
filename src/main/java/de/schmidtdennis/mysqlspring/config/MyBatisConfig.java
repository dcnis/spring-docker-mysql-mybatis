package de.schmidtdennis.mysqlspring.config;

import de.schmidtdennis.mysqlspring.model.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("de.schmidtdennis.mysqlspring.mapper")
@MappedTypes(User.class)
public class MyBatisConfig {

}
