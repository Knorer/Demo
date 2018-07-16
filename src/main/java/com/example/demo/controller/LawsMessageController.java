package com.example.demo.controller;

import com.example.demo.entity.LawsMessage;
import com.example.demo.service.LawsMessageService;
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
@RequestMapping("/lawsMessage")
public class LawsMessageController {

        @Autowired
        LawsMessageService lawsMessageService;
        @Autowired
        private JavaMailSender mailSender;

        @RequestMapping("/addLawsMessage")
        public Result addMessage(HttpServletRequest request) throws IOException
        {
            System.out.println("/addlawsmessage controller");
            LawsMessage lawsMessage = new LawsMessage();
            String id = UUID.randomUUID().toString().replace("-", "");
            lawsMessage.setId(id);
            lawsMessage.setClient_name(request.getParameter("client_name"));
            lawsMessage.setCompany_name(request.getParameter("company_name"));
            lawsMessage.setPhone(request.getParameter("phone" ));
            lawsMessage.setQuestion(request.getParameter("question"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date = new Date();
            String q_time = sdf.format(date);
            lawsMessage.setQ_time(q_time);
            lawsMessage.setAnswer(" ");
            lawsMessage.setAn_time(" ");
            lawsMessage.setStatus(" ");
            System.out.println(lawsMessage.toString());
            Result result = lawsMessageService.addLawsMessage(lawsMessage);
            System.out.println(result.getMessage());

            if(result.getMessage()=="提交成功"){
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom("flzx@gzport.com");
                mailMessage.setTo("flzx@gzport.com");
                mailMessage.setSubject("法律咨询留言板");
                String Text = "姓名："+request.getParameter("client_name")+"\n联系电话："+request.getParameter("phone")+"\n公司："+request.getParameter("company_name")+"\n咨询内容:"+request.getParameter("question")+"\n提交时间："+q_time;
                mailMessage.setText(Text);
                mailSender.send(mailMessage);
            }

            return result;
        }

        @RequestMapping("/findAllLawsMessage")
        public Map<String,Object> findAllMessage(){
            String sql = "select * from laws_message ORDER BY q_time DESC ";
            Map<String,Object> returnval = new HashMap();
            returnval = (Map<String, Object>)lawsMessageService.findAllLawsMessage(sql).getData();
            return returnval;
        }
    }

