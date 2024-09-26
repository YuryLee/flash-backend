package com.flash.flash.controller;

import com.flash.flash.dto.RegisterDTO;
import com.flash.flash.dto.UserDTO;
import com.flash.flash.dto.LoginDTO;
import com.flash.flash.response.CustomResponse;
import com.flash.flash.service.UserService;
import com.flash.flash.utils.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author Yury
 */
@CrossOrigin
@RestController
@Tag(name = "用户", description = "用户信息")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/email/{email}")
    @Operation(summary = "由email获取用户信息", description = "由email获取用户信息")
    @Parameters(value = {
            @Parameter(name = "email", description = "用户邮箱", in = ParameterIn.PATH, example = "xxx@buaa.edu.cn")
    })
    public CustomResponse getUserByEmail(@PathVariable String email) {
        CustomResponse customResponse = new CustomResponse();
        UserDTO userDTO;
        try {
            userDTO = userService.getUserByEmail(email);
            customResponse.setData(userDTO);
        } catch (BusinessException e) {
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }

    /**
     * 注册接口
     * @param registerDTO 包含 sid username password 的 map
     * @return CustomResponse对象
     */
    // 前端使用axios传递的data是Content-Type: application/json，需要用@RequestBody获取参数
    @PostMapping("/user/register")
    @Operation(summary = "用户注册，传email,nickname,password,activeCode")
    public CustomResponse userRegister(@RequestBody RegisterDTO registerDTO) throws BusinessException {
        CustomResponse customResponse = new CustomResponse();
        String email = registerDTO.getEmail();
        String nickname = registerDTO.getNickname();
        String password = registerDTO.getPassword();
        String activeCode = registerDTO.getActiveCode();
        // try {
            UserDTO userDTO = userService.register(email, nickname, password, activeCode);
            customResponse.setData(userDTO);
            customResponse.setMessage("注册成功");
//        } catch (BusinessException e) {
//            e.printStackTrace();
//            customResponse.setCode(e.getCode());
//            customResponse.setMessage(e.getMessage());
//            return customResponse;
//        }
        return customResponse;
    }

    @PostMapping("/user/login")
    @Operation(summary = "用户登录")
    public CustomResponse userLogin(@RequestBody LoginDTO loginDTO) {
        CustomResponse customResponse = new CustomResponse();
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        try {
            userService.userLogin(email, password);
            customResponse.setData("true");
            customResponse.setMessage("登录成功");
        } catch (BusinessException e) {
            e.printStackTrace();
            customResponse.setCode(e.getCode());
            customResponse.setMessage(e.getMessage());
        }
        return customResponse;
    }
}

