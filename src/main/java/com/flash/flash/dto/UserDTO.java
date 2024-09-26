package com.flash.flash.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description:
 *
 * @author Yury
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    // private Long uid;
    private String email;
    private String nickname;
    private String name;
    // private String password;
    private String avatar;
    private Integer gender; // 性别，0女性 1男性 2无性别，默认2
    private Integer role;   // 0 普通用户，1 科研人员，2 管理员
    // private Integer state;   // 状态 0正常 1封禁 2注销
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date createDate;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
//    private Date deleteDate;
}
