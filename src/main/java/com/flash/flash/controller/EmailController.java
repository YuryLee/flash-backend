package com.flash.flash.controller;

import com.flash.flash.response.CustomResponse;
import com.flash.flash.service.EmailService;
import com.flash.flash.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/07/11 20:14
 */
@CrossOrigin
@RestController
@Tag(name = "邮箱")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email/send/{email}")
    @Operation(summary = "发送验证码邮件")
    @Parameters(value = {
            @Parameter(name = "email", description = "邮箱", in = ParameterIn.PATH, example = "xxx@buaa.edu.cn")
    })
    public CustomResponse sendMail(@PathVariable String email) {
        CustomResponse customResponse = new CustomResponse();
        try {
            emailService.sendActiveCodeEMail(email);
            customResponse.setMessage("发送成功");
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}
