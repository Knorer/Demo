package com.example.demo.serviceimpl;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Override
    public Result addMessage(Message message){
        return messageRepository.addMessage(message);
    }
    @Override
    public Result findAllMessage(String sql){
        return messageRepository.findAllMessage(sql);
    }
    @Override
    public List ExportMessage(String sql){
        return messageRepository.ExportMessage(sql);
    }
}
