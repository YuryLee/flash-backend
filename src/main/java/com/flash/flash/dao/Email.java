package com.flash.flash.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/07/11 19:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @TableId
    private String email;
    private String activeCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date createDate;
}
