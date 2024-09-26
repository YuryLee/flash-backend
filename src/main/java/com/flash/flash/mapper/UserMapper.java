package com.flash.flash.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash.flash.dao.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
