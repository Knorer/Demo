package com.example.demo.repository;

import com.example.demo.entity.LawsMessage;
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
public class LawsMessageRepository {
    @PersistenceContext(type= PersistenceContextType.EXTENDED)
    EntityManager em;

    @Transactional
    public Result addLawsMessage(LawsMessage lawsMessage){
        Result result = new Result();
        em.persist(lawsMessage);
        result.setMessage("提交成功");
        return result;
    }

    @Transactional
    public Result findAllLawsMessage(String sql){
        Result result = new Result();
        Map data = new HashMap<>();
        Query query = em.createNativeQuery(sql, LawsMessage.class);
        List list = query.setHint(QueryHints.RESULT_TYPE, ResultType.Map).getResultList();
        data.put("rows",list);
//        System.out.println(data);
        result.setData(data);
        return result;
    }



}
