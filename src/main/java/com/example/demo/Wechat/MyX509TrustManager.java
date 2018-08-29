package com.example.demo.Wechat;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 忽略证书验证过程，忽略之后任何Https协议网站皆能正常访问
 */
public class MyX509TrustManager implements X509TrustManager {

    @Override
    //检查客户端证书
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    //检查服务端证书
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    //返回受信人的X509证书数组
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
