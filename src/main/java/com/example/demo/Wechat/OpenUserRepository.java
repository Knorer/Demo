package com.example.demo.Wechat;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class OpenUserRepository {
    @PersistenceContext(type= PersistenceContextType.EXTENDED)
    EntityManager em;


    @Transactional
    public String addOpenUser(WeixinOauth2Token weixinOauth2Token){
        WeixinOauth2Token weixinOauth2Token1 = new WeixinOauth2Token();
        System.out.println(weixinOauth2Token);
        weixinOauth2Token1.setAccessToken(weixinOauth2Token.getAccessToken());
        weixinOauth2Token1.setExpiresIn(weixinOauth2Token.getExpiresIn());
        weixinOauth2Token1.setRefreshToken(weixinOauth2Token.getRefreshToken());
        weixinOauth2Token1.setOpenId(weixinOauth2Token.getOpenId());
        weixinOauth2Token1.setScope(weixinOauth2Token.getScope());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String timestamp = sdf.format(date);
        weixinOauth2Token1.setTimestamp(timestamp);
        em.persist(weixinOauth2Token1);
        return "get token and openid";
    }

    @Transactional
    public String addUserInfo(OpenUserInfo openUserInfo){
        em.persist(openUserInfo);
        return "Userinfo is added";
    }

}
