package com.example.api.utils;


import com.alibaba.fastjson.JSON;
import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.example.api.beans.requestBody;

public class baiduApi {
    //百度API的请求代码，由示例代码更改而来：https://apis.baidu.com/store/detail/581576df-bc52-4e4a-8a3a-2abd6035e7ae
    public static String sendRequest(requestBody request_body) {
        //填入自己的accessKey，secretKey，否则项目无法正常运行。
        String accessKey = "0e541dadcd4d47468f4fe0c391f39401";
        String secretKey = "b8836a69a3874d04ab84bfbc6231ac22";

        String result = null;
        String path = "http://qrcode.api.bdymkt.com/qrcode/generate";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
        request.setCredentials(accessKey, secretKey);

        // 设置header参数
        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");

        // 设置jsonBody参数
        String objStr = JSON.toJSONString(request_body);
        request.setJsonBody(objStr);

        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());

        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            result = response.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}