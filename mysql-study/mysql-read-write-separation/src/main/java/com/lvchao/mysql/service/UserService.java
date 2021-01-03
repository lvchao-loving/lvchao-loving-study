package com.lvchao.mysql.service;

import com.lvchao.mysql.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lvchao
 * @since 2020-12-16
 */
public interface UserService extends IService<User> {

    User getUserById(Integer id);

    Integer updateUserById(User user);
}
