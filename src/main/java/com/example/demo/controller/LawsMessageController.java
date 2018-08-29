package com.example.demo.controller;

import com.example.demo.Text.ApiSendMobile;
import com.example.demo.entity.LawsMessage;
import com.example.demo.service.LawsMessageService;
import com.example.demo.util.Result;
import com.jasson.im.api.APIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
        private APIClient apiClient;

        private static int Random = 0;

    /**
     * 新增一条信息，并发送邮件通知
     * @param request
     * @return
     * @throws IOException
     */
        @RequestMapping("/addLawsMessage")
        public Result addMessage(@RequestParam(value = "photo",required = false)MultipartFile file, HttpServletRequest request) throws Exception
        {
            System.out.println("/addlawsmessage controller");
            Boolean ans = uploadFile(file);
            LawsMessage lawsMessage = new LawsMessage();
            String id = UUID.randomUUID().toString().replace("-", "");
            lawsMessage.setId(id);
            lawsMessage.setClient_name(request.getParameter("client_name"));
            lawsMessage.setCompany_name(request.getParameter("company_name"));
            lawsMessage.setPhone(request.getParameter("phone" ));
            lawsMessage.setQuestion(request.getParameter("question"));
            lawsMessage.setStatus(request.getParameter("status"));
            lawsMessage.setEmail(request.getParameter("email"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            String q_time = sdf.format(date);
            lawsMessage.setQ_time(q_time);
            lawsMessage.setAnswer(" ");
            lawsMessage.setAn_time(" ");
            System.out.println(lawsMessage.toString());
            Result result = lawsMessageService.addLawsMessage(lawsMessage);
            System.out.println(result.getMessage());

            if(result.getMessage()=="提交成功"){
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom("flzx@gzport.com");
                mailMessage.setTo("cc-fzh@gzport.com");
                mailMessage.setSubject("法律咨询留言板");
                String Text = "真实姓名："+request.getParameter("client_name")+"\n联系电话："+request.getParameter("phone")+"\n所属单位名称："+request.getParameter("company_name")+"\n联系邮箱："+request.getParameter("email")+"\n主题内容："+request.getParameter("status")+"\n详细咨询内容:"+request.getParameter("question")+"\n提交时间："+q_time;
                mailMessage.setText(Text);
                mailSender.send(mailMessage);
            }


            return result;
        }

    /**
     * 查找全部留言消息，按时间顺序排序
     * @return
     */
        @RequestMapping("/findAllLawsMessage")
        public Map<String,Object> findAllMessage(){
            String sql = "select * from laws_message ORDER BY q_time DESC ";
            Map<String,Object> returnval = new HashMap();
            returnval = (Map<String, Object>)lawsMessageService.findAllLawsMessage(sql).getData();
            return returnval;
        }


        @RequestMapping("/sendText")
        public int sendcode(HttpServletRequest request){
            String mobile = request.getParameter("phone");
            if(mobile!=null&&mobile!=""){
                Random = (int)((Math.random()*9+1)*1000);
                String content = "法律平台验证码："+Random;
                //发送短信
                ApiSendMobile apiSendMobile = new ApiSendMobile();
               // apiSendMobile.sendSM(mobile,content);

            }else {
                Random = 0;
            }

            return Random;
        }

        @RequestMapping(value = "/photo",method = RequestMethod.POST)
        public Map<String,Object> getPhoto(HttpServletRequest request){
            Map<String,Object> resultMap = new HashMap<>();
            String param= request.getParameter("data");
            System.out.println(param);
            return resultMap;
        }

        public boolean uploadFile(MultipartFile file) throws Exception {
            String fileName = file.getOriginalFilename();
            // 获取文件后缀
            String prefix=fileName.substring(fileName.lastIndexOf("."));
            // 用uuid作为文件名，防止生成的临时文件重复
            final File excelFile = File.createTempFile("temp", prefix);
            // MultipartFile to File
            file.transferTo(excelFile);
            if (!file.isEmpty()){
                String path = "D:\\upload\\laws\\";
                File target = new File(path+fileName);
                if(!target.exists()){
                    //先得到文件的上级目录，并创建上级目录，再创建文件
                    target.getParentFile().mkdir();
                }
                try{
                    //创建文件
                    target.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                OutputStream out = null;
                InputStream in = null;
                int length = 0;
                try {
                    in = new FileInputStream(excelFile);
                    out = new FileOutputStream(target);
                    byte[] buf = file.getBytes();
                    while((length = in.read(buf))!=-1){
                        out.write(buf,0,length);
                    }
                    out.flush();
                    in.close();
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                return true;
            }
            return false;
        }
    }

