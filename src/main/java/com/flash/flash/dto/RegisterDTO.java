package com.flash.flash.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/07/11 17:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户注册入参")
public class RegisterDTO {
    @NotNull
    @Schema(description = "邮箱", example = "123@buaa.edu.cn")
    private String email;
    @NotNull
    @Schema(description = "昵称", example = "张三")
    private String nickname;
    @NotNull
    @Schema(description = "密码", example = "123456")
    private String password;
    @NotNull
    @Schema(description = "给注册填写的邮箱发送的激活码", example = "1234")
    private String activeCode;
}
