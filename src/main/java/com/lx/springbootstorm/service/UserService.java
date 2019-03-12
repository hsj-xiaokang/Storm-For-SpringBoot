package com.lx.springbootstorm.service;

import com.lx.springbootstorm.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LX
 * @since 2019-03-12
 */
public interface UserService extends IService<User> {
    User selectByPrimaryKey(Integer userId);
}
