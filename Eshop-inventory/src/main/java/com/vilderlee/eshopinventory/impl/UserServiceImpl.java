package com.vilderlee.eshopinventory.impl;

import com.vilderlee.eshopinventory.mapper.UserMapper;
import com.vilderlee.eshopinventory.model.User;
import com.vilderlee.eshopinventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.impl
 * @auther vilderlee
 * @date 2019/8/26 11:36 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public User getUser(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
