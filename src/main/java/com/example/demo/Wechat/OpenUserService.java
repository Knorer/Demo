package com.example.demo.Wechat;


public  interface OpenUserService {
     String  addOpenUser (WeixinOauth2Token weixinOauth2Token);
     String  addUserInfo (OpenUserInfo openUserInfo);
}
