package com.example.demo.Wechat;

import com.example.demo.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class OpenUserController {

    @Autowired
    OpenUserService openUserService;

    @RequestMapping(value = "wx",method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;

        try{
            out = response.getWriter();
            if(CheckUtil.checkSignature(signature,timestamp,nonce)){
                out.write(echostr);
            }
        }catch (IOException e){
            e.printStackTrace();

        }finally {
            out.close();
        }

    }

    /**
     *
     * @param code ,这里需要输入appsecret和appid
     * @return
     */

    @RequestMapping(value = "/laws",method = RequestMethod.GET)
    public String getCode(@RequestParam("code") String code){
        String gCode = code;
        WeixinOauth2Token token = AcceptMessage.getWeixinOauth2Token("wx9396f4a434d93e31","4c41656f1a5c6b6f7692f1689a92e2c2",gCode);
        WeixinOauth2Token Alltoken = AcceptMessage.getOverAllToken("wx9396f4a434d93e31","4c41656f1a5c6b6f7692f1689a92e2c2");
        //获取OpenId 和 网页授权Access_Token
        //
        String string = openUserService.addOpenUser(token);
        //获取全局Access_Token,好像不用了.
        //获取用户信息
        OpenUserInfo info = AcceptMessage.getUserInfo(token.getAccessToken(),token.getOpenId());
        String addUserInfo = openUserService.addUserInfo(info);

        return("/laws.html");
    }



}
