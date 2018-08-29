package com.example.demo.Wechat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 网页授权 accessToken类
 */
@Entity
@Table(name = "wechat_token")
public class WeixinOauth2Token {
    //网页授权接口调用凭证
    @Id
    @Column(name = "access_token")
    private String accessToken;
    //凭证有效时长
    @Column(name = "expires_in")
    private int expiresIn;
    //刷新凭证
    @Column(name = "refresh_token")
    private String refreshToken;
    //用户标识
    @Column(name = "open_id")
    private String openId;
    //用户授权作用域
    @Column(name = "scope")
    private String scope;
    //新增接口访问时间
    @Column(name = "timestamp")
    private String timestamp;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WeixinOauth2Token{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public WeixinOauth2Token(String accessToken, int expiresIn, String refreshToken, String openId, String scope,String timestamp) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.openId = openId;
        this.scope = scope;
        this.timestamp = timestamp;
    }

    public WeixinOauth2Token() {
    }
}
