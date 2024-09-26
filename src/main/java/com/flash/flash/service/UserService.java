package com.flash.flash.service;


import com.flash.flash.dto.UserDTO;
import com.flash.flash.utils.BusinessException;

public interface UserService {
    UserDTO getUserByUid(Integer uid);

    UserDTO register(String email, String name, String password, String activeCode) throws BusinessException;

    void userLogin(String email, String password) throws BusinessException;

    UserDTO getUserByEmail(String email) throws BusinessException;

//    /**
//     *  用户注册
//     * @param user
//     */
//    void add(User user);
//
//    /**
//     *  根据激活码查找用户
//     * @param activeCode
//     * @return
//     */
//    User getUserByActiveCode(String activeCode);
//
//    /**
//     * 修改
//     * @param user
//     */
//    void modify(User user);
//
//    /**
//     * 登录
//     * @param user
//     * @return
//     */
//    User get(User user);

}
