package com.example.demo.util;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class FileUpload {


    /**
     *文件上传
     * @param fileData
     * @param fileName
     * @return
     */
    public String uploadFile(String fileData,String fileName){
        //存放文件的文件夹
        String imgPath = "../upload/";
        File f = new File(imgPath);
        if(!f.exists()){
            f.mkdir();
        }
        //拼接文件名称，不存在就创建
        imgPath = imgPath +"/"+fileName+".jpg";
        f = new File(imgPath);
        if(!f.exists()){
            f.mkdir();
        }

        System.out.println("文件保存到："+imgPath);
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //通过Base64解密，将图片数据解密成字节数组
            byte[] bytes = decoder.decodeBuffer(fileData);
            //构造字节数组输入流
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            //读取输入流的数据
            BufferedImage bi = ImageIO.read(bais);
            //将数据信息写进图片文件中
            ImageIO.write(bi,"jpg",f);
            bais.close();
        }catch (IOException e){
            System.out.println(e);
        }
        return imgPath;
    }

}
