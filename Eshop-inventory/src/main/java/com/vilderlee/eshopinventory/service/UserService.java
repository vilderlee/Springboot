package com.vilderlee.eshopinventory.service;

import com.vilderlee.eshopinventory.dto.User;

import java.util.List;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.service
 * @auther vilderlee
 * @date 2019/8/26 11:35 下午
 */
public interface UserService {
    User getUser(long id);

    List<User> getAll();
}
