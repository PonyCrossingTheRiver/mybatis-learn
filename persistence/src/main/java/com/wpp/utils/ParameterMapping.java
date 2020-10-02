package com.wpp.utils;

public class ParameterMapping {

    // 属性的名字 #{content} eg: id or username
    private String content;

    public ParameterMapping(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
