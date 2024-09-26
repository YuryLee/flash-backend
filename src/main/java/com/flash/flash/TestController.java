package com.flash.flash;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author Yury
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
