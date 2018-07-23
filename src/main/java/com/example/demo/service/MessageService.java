package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.util.Result;

import java.util.List;

public interface MessageService {
    Result addMessage(Message message);
    Result findAllMessage(String sql);
    List ExportMessage(String sql);
}
