package com.example.demo.controller;

import com.example.demo.service.MessageService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportContorller {
    @Autowired
    MessageService messageService;

    //创建表头
    private void createTitle(HSSFWorkbook hssfWorkbook, HSSFSheet hssfSheet){
        HSSFRow hssfRow = hssfSheet.createRow(0);
        hssfSheet.setColumnWidth(0,20);//序号
        hssfSheet.setColumnWidth(1,40);//姓名
        hssfSheet.setColumnWidth(2,50);//电话
        hssfSheet.setColumnWidth(3,200);//咨询内容
        hssfSheet.setColumnWidth(4,20);//咨询时间

        //设置居中加粗
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        HSSFFont font = hssfWorkbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = hssfRow.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);

        cell = hssfRow.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = hssfRow.createCell(2);
        cell.setCellValue("电话");
        cell.setCellStyle(style);

        cell = hssfRow.createCell(3);
        cell.setCellValue("咨询内容");
        cell.setCellStyle(style);

        cell = hssfRow.createCell(4);
        cell.setCellValue("咨询时间");
        cell.setCellStyle(style);




    }
    @RequestMapping("/getExcel")
    public String getExcel(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("心理咨询汇总表");
        createTitle(workbook,sheet);

        String sql = "select * from message ORDER BY q_time DESC ";
        List list = messageService.ExportMessage(sql);

        System.out.println(list);

//        //设置日期格式
    //        HSSFCellStyle style = workbook.createCellStyle();
//        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
//
//        int rowNum = 1;
       return "0";
       }
    }


