package com.huy.automationexercise.utils;

public class EmailData {
    private String subject;
    private String content;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EmailData(String subject, String content){
        this.subject = subject;
        this.content = content;
    }
}
