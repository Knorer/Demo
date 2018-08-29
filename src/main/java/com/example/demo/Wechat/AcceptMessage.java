package com.example.demo.Wechat;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


public class AcceptMessage {

    private static Logger logger = LoggerFactory.getLogger(AcceptMessage.class);


    public final static String requestUrl = " https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";


    /**
     * 发送https请求
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET/POST）
     * @param outputStr     提交的数据
     * @return JSONObject   通过JSONObject.get(key)的方式获取json对象的属性值
     */

    public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr ){
        JSONObject jsonObject =null;
        try {
            //创建SSLContext对象，并使用我们指定的信人管理器进行初始化
            TrustManager[] trustManagers = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,trustManagers,new java.security.SecureRandom());
            //从上述SSLContext对象种得到SSLSocketFactory对象
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            //创建HttpsURLConnection对象，并设置SSLSocketFactory对象
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setSSLSocketFactory(sslSocketFactory);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            //设置请求方式（GET/POST）
            connection.setRequestMethod(requestMethod);

            //当outpusStr不为null时像输出流写数据
            if(null != outputStr){
                OutputStream outputStream = connection.getOutputStream();
                //注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            //从输入流读取返回内容
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((str = bufferedReader.readLine())!=null){
                stringBuffer.append(str);
            }

            //释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            connection.disconnect();
            jsonObject = JSONObject.fromObject(stringBuffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     *获取网页授权的token
     *  @param appid 微信公众号唯一标识
     *  @param appsecret 公众号密钥
     *  @param code 访问页面的code
     */
    public static WeixinOauth2Token getWeixinOauth2Token(String appid,String appsecret,String code){
        WeixinOauth2Token token = null;
        String requesturl = requestUrl.replace("APPID",appid).replace("SECRET",appsecret).replace("CODE",code);
        //发现GET请求获取凭证
        JSONObject jsonObject = httpRequest(requesturl,"GET",null);
        if(null!=jsonObject){
            try {
                token = new WeixinOauth2Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
                token.setRefreshToken(jsonObject.getString("refresh_token"));
                token.setOpenId(jsonObject.getString("openid"));
                token.setScope(jsonObject.getString("scope"));

            }catch (JSONException e){
                token = null;
                logger.error("获取token失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
            }
        }
        return token;
    }

    /**
     * 获取全局Token
     * @param appid
     * @param appsecret
     * @return
     */
    public static WeixinOauth2Token getOverAllToken(String appid,String appsecret){
        WeixinOauth2Token allToken = null;
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        url = url.replace("APPID",appid).replace("APPSECRET",appsecret);
        //发现GET请求获取凭证
        JSONObject jsonObject = httpRequest(url,"GET",null);
        if(null!=jsonObject){
            try {
                allToken = new WeixinOauth2Token();
                allToken.setAccessToken(jsonObject.getString("access_token"));
                allToken.setExpiresIn(jsonObject.getInt("expires_in"));

            }catch (JSONException e){
                allToken = null;
                logger.error("获取token失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));
            }
        }
        return allToken;
    }

    /**
     *  刷新access_token
     * @param appid
     * @param refresh_token
     * @return
     */
    public static WeixinOauth2Token getRefreshToken(String appid,String refresh_token){
        WeixinOauth2Token token = null;
        String url="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        String refresh_url = url.replace("APPID",appid).replace("REFRESH_TOKEN",refresh_token);
        JSONObject jsonObject = httpRequest(refresh_url,"GET",null);
        if(null != jsonObject){
            try {
                token = new WeixinOauth2Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
                token.setRefreshToken(jsonObject.getString("refresh_token"));
                token.setOpenId(jsonObject.getString("openid"));
                token.setScope(jsonObject.getString("scope"));

            }catch (JSONException e){
                token = null;
                logger.error("获取refresh_token失败 errcode:{} errmsg:{}",jsonObject.getInt("errcode"),jsonObject.getString("errmsg"));

            }
        }
        return token;
    }

    public static OpenUserInfo getUserInfo(String access_token,String open_id){
        OpenUserInfo info = null;
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESS_TOKEN",access_token).replace("OPENID",open_id);
        System.out.println(url);
        JSONObject jsonObject = httpRequest(url,"GET",null);
        if(null != jsonObject){
            try {
                info = new OpenUserInfo();
                info.setOpenId(jsonObject.getString("openid"));
                info.setNickName(jsonObject.getString("nickname"));
                info.setSex(jsonObject.getInt("sex"));
                info.setProvince(jsonObject.getString("province"));
                info.setCity(jsonObject.getString("city"));
                info.setCountry(jsonObject.getString("country"));
                info.setHeadingImgUrl(jsonObject.getString("headimgurl"));
                System.out.println("info_nickName= "+info.getNickName() );
            }catch (Exception e){
                info = null;
                System.out.println("error AcceptMessage - getUserInfo");
                logger.error("获取用户数据错误 errcode:{} errmsg:{}",jsonObject.getString("errcode"),jsonObject.getString("errmsg"));

            }
        }
        return info;
    }




}
