package com.vilderlee.eshopinventory.dao;

import com.vilderlee.eshopinventory.dto.User;
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
public interface UserDao {

    User getUserById(long id);

    List<User> getAll();
}
