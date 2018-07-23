package com.example.demo.controller;


import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    private JavaMailSender mailSender;



    @RequestMapping("/addMessage")
    public Result addMessage(HttpServletRequest request) throws IOException
    {
        System.out.println("/addmessage controller");
        Message message = new Message();
        String id = UUID.randomUUID().toString().replace("-", "");
        message.setId(id);
        message.setClient_name(request.getParameter("client_name"));
        message.setCompany_name(request.getParameter("company_name"));
        message.setPhone(request.getParameter("phone" ));
        message.setQuestion(request.getParameter("question"));
        String mail = request.getParameter("email");
        message.setEmail(mail);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String q_time = sdf.format(date);
        message.setQ_time(q_time);
        message.setAnswer(" ");
        message.setAn_time(" ");
        message.setStatus(" ");
        message.setAddr(" ");
        System.out.println(message.toString());
        Result result = messageService.addMessage(message);
        System.out.println(result.getMessage());

        if(result.getMessage()=="提交成功"){
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("xlzx@gzport.com");
            mailMessage.setTo("cc-fzh@gzport.com");
            mailMessage.setSubject("心理咨询留言板");
            String Text = "真实姓名："+request.getParameter("client_name")+"\n联系电话："+request.getParameter("phone")+"\n所属单位名称："+request.getParameter("company_name")+"\n联系邮箱:"+request.getParameter("email")+"\n咨询内容:"+request.getParameter("question")+"\n提交时间："+q_time;
            mailMessage.setText(Text);
            mailSender.send(mailMessage);
        }

        return result;
    }

    @RequestMapping("/findAllMessage")
    public Map<String,Object> findAllMessage(){
        String sql = "select * from message ORDER BY q_time DESC ";
        Map<String,Object> returnval = new HashMap();
        returnval = (Map<String, Object>)messageService.findAllMessage(sql).getData();
        return returnval;
    }
}
