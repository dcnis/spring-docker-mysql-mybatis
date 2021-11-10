package de.schmidtdennis.mysqlspring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DatabaseMapper {

    void createTableIfNotExists(@Param("dbName") String dbName);

}
