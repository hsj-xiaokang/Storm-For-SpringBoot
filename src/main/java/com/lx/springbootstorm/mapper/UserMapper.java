package com.lx.springbootstorm.mapper;

import com.lx.springbootstorm.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LX
 * @since 2019-03-12
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM t_user WHERE user_id = #{userId}")
    User selectByPrimaryKey(Integer userId);

}
