package com.example.demo.repository;

import com.example.demo.entity.Message;
import com.example.demo.util.Result;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessageRepository {
    @PersistenceContext(type= PersistenceContextType.EXTENDED)
    EntityManager em;

    @Transactional
    public Result addMessage(Message message){
        Result result = new Result();
        em.persist(message);
        result.setMessage("提交成功");
        return result;
    }

    @Transactional
    public Result findAllMessage(String sql){
        Result result = new Result();
        Map data = new HashMap<>();
        Query query = em.createNativeQuery(sql, Message.class);
        List list = query.setHint(QueryHints.RESULT_TYPE, ResultType.Map).getResultList();
        data.put("rows",list);
//        System.out.println(data);
        result.setData(data);
        return result;
    }

//    @Transactional
//    public Result findAllMessage(){
//        StringBuffer stringBuffer = new StringBuffer();
//        Result result = new Result();
//        Query query = em.createNativeQuery("select * from message" , Message.class);
//        List list = query.getResultList();
//        Message message = new Message();
//        for (int i = 0; i < list.size(); i++) {
//            message = (Message)list.get(i);
//
//        }
//        return result;
//    }





}
