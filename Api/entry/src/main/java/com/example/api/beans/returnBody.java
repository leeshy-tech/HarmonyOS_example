package com.example.api.beans;

//API的返回类，只有一个参数
public class returnBody {
    private String imageUrl;

    public returnBody(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public returnBody() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
