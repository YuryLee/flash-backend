package com.flash.flash.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/07/11 21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户登录入参")
public class LoginDTO {
    @NotNull
    @Schema(description = "邮箱", example = "123@buaa.edu.cn")
    private String email;
    @NotNull
    @Schema(description = "密码", example = "123456")
    private String password;
}
