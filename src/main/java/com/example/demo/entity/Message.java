package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {

    @Id
    @Column(name="id")
    private String id;
    @Column(name = "client_name")
    private String client_name;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "question")
    private String question;
    @Column(name = "q_time")
    private String q_time;
    @Column(name = "status")
    private String status;
    @Column(name = "an_time")
    private String an_time;
    @Column(name = "answer")
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQ_time() {
        return q_time;
    }

    public void setQ_time(String q_time) {
        this.q_time = q_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAn_time() {
        return an_time;
    }

    public void setAn_time(String an_time) {
        this.an_time = an_time;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Message(String id, String client_name, String company_name, String phone, String question, String q_time, String status, String an_time, String answer) {
        this.id = id;
        this.client_name = client_name;
        this.company_name = company_name;
        this.phone = phone;
        this.question = question;
        this.q_time = q_time;
        this.status = status;
        this.an_time = an_time;
        this.answer = answer;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", client_name='" + client_name + '\'' +
                ", company_name='" + company_name + '\'' +
                ", phone='" + phone + '\'' +
                ", question='" + question + '\'' +
                ", q_time='" + q_time + '\'' +
                ", status='" + status + '\'' +
                ", an_time='" + an_time + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

}
