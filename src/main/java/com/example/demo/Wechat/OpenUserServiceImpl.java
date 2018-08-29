package com.example.demo.Wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenUserServiceImpl implements OpenUserService {
    @Autowired
    OpenUserRepository openUserRepository;

    @Override
    public String addOpenUser(WeixinOauth2Token weixinOauth2Token) {
        return openUserRepository.addOpenUser(weixinOauth2Token);
    }

    @Override
    public String addUserInfo(OpenUserInfo openUserInfo) {
        return openUserRepository.addUserInfo(openUserInfo);
    }


}
