package com.example.demo.service;

import com.example.demo.entity.LawsMessage;
import com.example.demo.util.Result;

public interface LawsMessageService {
    Result addLawsMessage(LawsMessage message);
    Result findAllLawsMessage(String sql);
}
