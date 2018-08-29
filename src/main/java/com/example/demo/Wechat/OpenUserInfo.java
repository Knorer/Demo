package com.example.demo.Wechat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  用户微信资料类
 */
@Entity
@Table(name = "userInfo")
public class OpenUserInfo {
    //用户的唯一标识
    @Id
    @Column(name = "openid")
    private String openId;

    //用户昵称
    @Column(name = "nickname")
    private String nickName;

    //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    @Column(name = "sex")
    private int sex;

    //用户个人资料填写的省份
    @Column(name = "province")
    private String province;

    //普通用户个人资料填写的城市
    @Column(name = "city")
    private String city;

    //国家，如中国为CN
    @Column(name = "country")
    private String country;

    //	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
    @Column(name = "headimgurl")
    private String headingImgUrl;

    //	用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）

    //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。因为没有绑定所以删掉unionId,privilege 没有啥用 也去掉


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadingImgUrl() {
        return headingImgUrl;
    }

    public void setHeadingImgUrl(String headingImgUrl) {
        this.headingImgUrl = headingImgUrl;
    }



    @Override
    public String toString() {
        return "OpenUserInfo{" +
                "openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headingImgUrl='" + headingImgUrl + '\'' +
                '}';
    }



}
