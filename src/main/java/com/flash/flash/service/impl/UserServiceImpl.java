package com.flash.flash.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.flash.converter.UserConverter;
import com.flash.flash.dao.Email;
import com.flash.flash.dao.User;
import com.flash.flash.dto.UserDTO;
import com.flash.flash.mapper.EmailMapper;
import com.flash.flash.mapper.UserMapper;
import com.flash.flash.service.UserService;
import com.flash.flash.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;


/**
 * Description:
 *
 * @author Yury
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public UserDTO getUserByUid(Integer uid) {
        return null;
    }

    /**
     * 用户注册
     */
    @Override
    public UserDTO register(String email, String nickname, String password, String activeCode) throws BusinessException {
        if (email == null) {
            throw new BusinessException(400, "账号不能为空"); // 账号不能为空
        }
        if (nickname == null) {
            throw new BusinessException(400, "用户名不能为空"); // 用户名不能为空
        }
        nickname = nickname.trim();   //删掉用户名的空白符
        if (nickname.isEmpty()) {
            throw new BusinessException(400, "用户名不能为空"); // 用户名不能为空
        }
        if (password == null) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        if (password.isEmpty()) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        if (nickname.length() > 20) {
            throw new BusinessException(400, "账号长度不能大于50"); // 账号长度不能大于50
        }
        if (password.length() > 50) {
            throw new BusinessException(400, "密码长度不能大于50"); // 密码长度不能大于50

        }
//        if (!password.equals(confirmedPassword)) {
//            customResponse.setCode(403);
//            customResponse.setMessage("两次输入的密码不一致");
//            return customResponse;
//        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userMapper.selectOne(queryWrapper);   //查询数据库里值等于email并且没有注销的数据
        if (user != null) {
            throw new BusinessException(400, "邮箱已注册"); // 邮箱已注册
        }

        QueryWrapper<Email> emailQueryWrapper = new QueryWrapper<>();
        emailQueryWrapper.eq("email", email);
        Email emailUsed = emailMapper.selectOne(emailQueryWrapper);
        if (!Objects.equals(emailUsed.getActiveCode(), activeCode)) {
            throw new BusinessException(400, "验证码错误"); // 验证码错误
        }

        String avatarUrl = "cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png";
        Date now = new Date();
        User new_user = new User(
                null,
                email,
                nickname,
                null,
                password,
                avatarUrl,
                2,
                0,
                0,
                now,
                null
        );
        userMapper.insert(new_user);

        return UserConverter.convertUser(new_user);
    }

    @Override
    public void userLogin(String email, String password) throws BusinessException {
        if (email == null) {
            throw new BusinessException(400, "账号不能为空"); // 账号不能为空
        }
        if (password == null) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }
        if (password.isEmpty()) {
            throw new BusinessException(400, "密码不能为空"); // 密码不能为空
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userMapper.selectOne(queryWrapper);   //查询数据库里值等于email并且没有注销的数据
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new BusinessException(400, "密码错误");
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) throws BusinessException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(400, "用户不存在");
        }
        return UserConverter.convertUser(user);
    }

}
