package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.util.Result;

public interface MessageService {
    Result addMessage(Message message);
    Result findAllMessage(String sql);
}
