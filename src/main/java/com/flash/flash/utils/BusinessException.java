package com.flash.flash.utils;

import lombok.Getter;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/07/11 20:00
 */
@Getter
public class BusinessException extends Exception{

    int code;
    String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
