package com.example.demo.controller;

import com.example.demo.entity.Message;
import jxl.CellView;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping(value="/export")
public class ExportController {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    public void export (HttpServletResponse response){

        //sql
        Query query = entityManager.createNativeQuery("select * from message ORDER BY q_time DESC");
        List list = query.getResultList();
        Message message = new Message();


        //文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String filename = sdf.format(new Date()) + ".xls";

        //输出
        File file = null;
        PrintWriter out = null;
        response.setContentType("application/x-msdonwload");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=" + filename);//excel文件名


        try{
//            1.创建excel文件
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(response.getOutputStream());
//            居中
            WritableCellFormat writableCellFormat = new WritableCellFormat();
            writableCellFormat.setAlignment(Alignment.CENTRE);

            WritableSheet sheet = null;
            SheetSettings sheetSettings = null;
            out = response.getWriter();
            file = new File(filename);
            for (int i = 0; i < list.size(); i++) {
//                2.创建sheet并设置冻结前一行
                sheet = writableWorkbook.createSheet(sdf.format(new Date()),0);
                sheetSettings = sheet.getSettings();
                sheetSettings.setVerticalFreeze(1);
//                3.第一行标题数据
                sheet.addCell(new Label(0,0,"姓名",writableCellFormat));
                sheet.addCell(new Label(1,0,"联系方式",writableCellFormat));
                sheet.addCell(new Label(2,0,"公司",writableCellFormat));
                sheet.addCell(new Label(3,0,"咨询内容",writableCellFormat));
                sheet.addCell(new Label(4,0,"咨询时间",writableCellFormat));

            }
            CellView cellView = new CellView();
            cellView.setAutosize(true);
//            4.将数据添加到单元格中
            if(list != null && list.size()>0){
                for (int i = 0; i < list.size() ; i++) {
                    message = (Message)list.get(i);
                    sheet.addCell(new Label(0,i+1,message.getClient_name()+"",writableCellFormat));
                    sheet.addCell(new Label(1,i+1,message.getPhone()));
                    sheet.addCell(new Label(2,i+1,message.getCompany_name()));
                    sheet.addCell(new Label(3,i+1,message.getQuestion()));
                    sheet.addCell(new Label(4,i+1,message.getQ_time()));
                }
            }
            writableWorkbook.write();
            writableWorkbook.close();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;
            OutputStream outputStream = response.getOutputStream();
            while((len = bufferedInputStream.read(buf))>0)
                outputStream.write(buf,0,len);
            bufferedInputStream.close();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            {
                if(file.exists()){
                    file.delete();
                }
                if(out!=null){
                    out.flush();
                    out.close();
                }
            }
        }
    }
}
