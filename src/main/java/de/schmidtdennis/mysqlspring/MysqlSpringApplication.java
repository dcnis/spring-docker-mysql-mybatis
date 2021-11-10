package de.schmidtdennis.mysqlspring;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MysqlSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlSpringApplication.class, args);
	}


}
