package com.example.demo.serviceimpl;

import com.example.demo.entity.LawsMessage;
import com.example.demo.repository.LawsMessageRepository;
import com.example.demo.service.LawsMessageService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LawsMessageServiceImpl implements LawsMessageService {
    @Autowired
    LawsMessageRepository lawsMessageRepository;
    @Override
    public Result addLawsMessage(LawsMessage message){
        return lawsMessageRepository.addLawsMessage(message);
    }
    @Override
    public Result findAllLawsMessage(String sql){
        return lawsMessageRepository.findAllLawsMessage(sql);
    }
}
