package com.lvchao.mysql.service.impl;

import com.lvchao.mysql.datasource.DataSource;
import com.lvchao.mysql.datasource.SourceName;
import com.lvchao.mysql.entity.User;
import com.lvchao.mysql.mapper.UserMapper;
import com.lvchao.mysql.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lvchao
 * @since 2020-12-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @DataSource(SourceName.read)
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @DataSource(SourceName.write)
    @Transactional
    @Override
    public Integer updateUserById(User user) {
        User user1 = new User();
        user1.setId(8);
        user1.setName(UUID.randomUUID().toString().replace("-","").substring(0,10));
        userMapper.updateById(user1);
        // TODO 测试数据源事务
        if (user.getId() == 0){
            throw new RuntimeException("");
        }
        return userMapper.updateById(user);
    }
}
