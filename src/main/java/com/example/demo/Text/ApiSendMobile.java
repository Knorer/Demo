package com.example.demo.Text;

import com.jasson.im.api.APIClient;

import java.io.BufferedReader;

public class ApiSendMobile {
    private String host = "10.197.33.252";
    private String name = "union";
    private String pwd = "union";
    private String apiId = "union";
    private String dbName = "mas";
    private APIClient handler = new APIClient();
    BufferedReader in = null;


    private long smId = 1;


    public void init(){
        int connectRe = handler.init(host,name,pwd,apiId,dbName);
        if(connectRe == APIClient.IMAPI_SUCC)
            System.out.println("初始化成功");
        else if(connectRe == APIClient.IMAPI_CONN_ERR)
            System.out.println("链接数据库出错");
        else if(connectRe == APIClient.IMAPI_API_ERR)
            System.out.println("API编码非法");
        if(connectRe != APIClient.IMAPI_SUCC){
            System.exit(-1);
        }
    }

    public void release(){
        handler.release();
        Thread.currentThread().interrupt();
    }

    public void sendSM(String mobile,String content){
        init();
        int sendRe = 0;
        sendRe = handler.sendSM(mobile,content,smId);
        if(sendRe == APIClient.IMAPI_SUCC)
            System.out.println("发送成功");
        else if(sendRe == APIClient.IMAPI_INIT_ERR)
            System.out.println("初始化失败");
        else if(sendRe == APIClient.IMAPI_CONN_ERR)
            System.out.println("数据库连接失败");
        else if(sendRe == APIClient.IMAPI_DATA_ERR)
            System.out.println("参数错误");
        else if(sendRe == APIClient.IMAPI_DATA_TOOLONG)
            System.out.println("参数超长");
        else if(sendRe == APIClient.IMAPI_IFSTATUS_INVALID)
            System.out.println("API接口处于暂停(失败)状态");
        else if(sendRe == APIClient.IMAPI_GATEWAY_CONN_ERR)
            System.out.println("短信网关未连接");
        else
            System.out.println("其他错误");

        release();
    }

    public void quit(){
        release();
        System.exit(0);
    }
}
