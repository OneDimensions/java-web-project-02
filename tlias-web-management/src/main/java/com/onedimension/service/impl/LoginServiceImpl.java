package com.onedimension.service.impl;

import com.onedimension.mapper.LoginMapper;
import com.onedimension.pojo.Emp;
import com.onedimension.pojo.LoginInfo;
import com.onedimension.service.LoginService;
import com.onedimension.utils.JwtsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    private final LoginMapper loginMapper;

    public LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = loginMapper.login(emp);
        if (e != null) {
            log.info("登录成功: {}", e);
            HashMap<String, Object> infoMap = new HashMap<>();
            infoMap.put("id", e.getId());
            infoMap.put("username", e.getUsername());
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), JwtsUtils.generateJwt(infoMap));
        }
        return null;
    }
}
