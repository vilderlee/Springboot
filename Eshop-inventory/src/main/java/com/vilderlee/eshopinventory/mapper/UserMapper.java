package com.vilderlee.eshopinventory.mapper;

import com.vilderlee.eshopinventory.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.dao
 * @auther vilderlee
 * @date 2019/8/26 11:32 下午
 */
@Repository
public interface UserMapper {

    User getUserById(long id);

    List<User> getAll();
}
