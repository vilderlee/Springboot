package com.vilderlee.clickhousedemo;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * UserInfoMapper
 *
 * @ClassName UserInfoMapper
 * @Description
 * @Author VilderLee
 * @Date 2021/3/29 7:49 下午
 */
@Mapper
public interface UserInfoMapper {

    @Update("create table users(id Int64, username String, createTime DateTime, createDate Date) ENGINE = MergeTree ORDER BY createTime")
    void createTable();

    @Select("select * from users")
    List<Users> query();

    @Delete("alter table users delete where username = 'lichao123'")
    void delete();

    void insert();

    @Update("alter table users update username = #{username} where username = #{oldUsername}")
    void update(@Param("oldUsername") String oldUsername, @Param("username") String username);

    @Update("${sql}")
    void updateSql( Map map);



    @Select("describe ${tableName}")
    List<Map<String, Object>> desc(@Param("tableName") String tableName);

    @Select("show tables from ${databases} like '%${tableName}%' ")
    List<DatabasesDesc> descTable(@Param("databases")String databases, @Param("tableName") String tableName);
}