package com.company;

/**
 * Created by eladlavi on 26/04/2017.
 */
public class Message {
    private String content;
    private String sender;

    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return sender + "|" + content;
    }
}
