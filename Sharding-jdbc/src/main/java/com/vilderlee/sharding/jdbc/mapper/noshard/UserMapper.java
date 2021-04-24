package com.vilderlee.sharding.jdbc.mapper.noshard;

import com.vilderlee.sharding.jdbc.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * User
 *
 * @ClassName User
 * @Description
 * @Author VilderLee
 * @Date 2021/1/21 5:14 下午
 */
@Mapper
public interface UserMapper {

    @Insert("Insert into user values(#{id}, #{name}, #{age}, #{email})")
    void save(User user);

    @Select("Select * From user")
    List<User> findAll();
}
