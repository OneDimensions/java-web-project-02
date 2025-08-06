package com.onedimension.controller;

import com.onedimension.pojo.Emp;
import com.onedimension.pojo.LoginInfo;
import com.onedimension.pojo.Result;
import com.onedimension.service.LoginService;
import com.onedimension.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/login")
@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("用户登录: {}", emp);
        LoginInfo loginInfo = loginService.login(emp);
        if(loginInfo != null) {
            return ResultUtil.success(loginInfo);
        }
        return ResultUtil.fail("用户名或密码错误");
    }

}
