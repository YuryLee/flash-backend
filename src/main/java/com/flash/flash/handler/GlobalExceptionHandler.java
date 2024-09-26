package com.flash.flash.handler;

import com.flash.flash.response.CustomResponse;
import com.flash.flash.response.StatusCode;
import com.flash.flash.utils.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yury
 * @description: TODO
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse handleBusinessException(BusinessException e) {
        return new CustomResponse(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomResponse handleException(HttpServletRequest httpServletRequest, Exception e) {
        return new CustomResponse(StatusCode.RC500.getCode(), StatusCode.RC500.getMessage(), null);
    }
}
