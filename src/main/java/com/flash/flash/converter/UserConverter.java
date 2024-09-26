package com.flash.flash.converter;


import com.flash.flash.dao.User;
import com.flash.flash.dto.UserDTO;

/**
 * Description:
 *
 * @author Yury
 */
public class UserConverter {

    public static UserDTO convertUser(User user) {
        UserDTO userDTO = new UserDTO();
        // userDTO.setUid(user.getUid());
        userDTO.setEmail(user.getEmail());
        userDTO.setNickname(user.getNickname());
        userDTO.setName(user.getName());
        //userDTO.setPassword(user.getPassword());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setGender(user.getGender());
        userDTO.setRole(user.getRole());
        userDTO.setCreateDate(user.getCreateDate());

        // userDTO.setDeleteDate(user.getDeleteDate());
        return userDTO;
    }

    public static User convertUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        // user.setPassword(userDTO.getPassword());
        user.setAvatar(userDTO.getAvatar());
        user.setGender(userDTO.getGender());
        return user;
    }
}
