package com.example.api.beans;

import ohos.javax.xml.stream.StreamFilter;

//API的请求体，共五个参数
public class requestBody {
    private String data;
    private int size;
    private String level;
    private String format;
    private String logo;

    public requestBody(String data, int size, String level, String format, String logo) {
        this.data = data;
        this.size = size;
        this.level = level;
        this.format = format;
        this.logo = logo;
    }

    public requestBody() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
