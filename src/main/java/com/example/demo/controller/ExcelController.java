package com.example.demo.controller;

import com.example.demo.entity.ExcelData;
import com.example.demo.entity.LawsMessage;
import com.example.demo.entity.Message;
import com.example.demo.service.MessageService;
import com.example.demo.util.ExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/messageExcel", method = RequestMethod.GET)
    public void messagExcel(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("心理咨询");
        String name = data.getName();
        List<String> titles = new ArrayList();
        titles.add("序号");
        titles.add("姓名");
        titles.add("电话");
        titles.add("咨询内容");
        titles.add("咨询时间");
        data.setTitles(titles);
        String sql = "select * from message ORDER BY q_time DESC ";
        List<Message> list = messageService.ExportMessage(sql);

        List<List<Object>> rows = new ArrayList();
        for (Message m: list ) {
            List<Object> row = new ArrayList();
            row.add(m.getId());
            row.add(m.getClient_name());
            row.add(m.getPhone());
            row.add(m.getQuestion());
            row.add(m.getQ_time());
            rows.add(row);
        }
        data.setRows(rows);
        ExportExcelUtils.exportExcel(response,name,data);
    }


    @RequestMapping(value = "/messageExcel", method = RequestMethod.GET)
    public void lawExcel(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("法律咨询");
        String name = data.getName();
        List<String> titles = new ArrayList();
        titles.add("序号");
        titles.add("姓名");
        titles.add("电话");
        titles.add("咨询内容");
        titles.add("咨询时间");
        data.setTitles(titles);
        String sql = "select * from lawsMessage ORDER BY q_time DESC ";
        List<LawsMessage> list = messageService.ExportMessage(sql);

        List<List<Object>> rows = new ArrayList();
        for (LawsMessage m: list ) {
            List<Object> row = new ArrayList();
            row.add(m.getId());
            row.add(m.getClient_name());
            row.add(m.getPhone());
            row.add(m.getQuestion());
            row.add(m.getQ_time());
            rows.add(row);
        }
        data.setRows(rows);
        ExportExcelUtils.exportExcel(response,name,data);
    }

}