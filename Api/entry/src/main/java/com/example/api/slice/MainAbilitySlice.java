package com.example.api.slice;

import com.alibaba.fastjson.JSON;
import com.example.api.ResourceTable;
import com.example.api.beans.requestBody;
import com.example.api.beans.returnBody;
import com.example.api.utils.LoadImageUtil;
import com.example.api.utils.baiduApi;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Image;
import ohos.agp.components.TextField;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;


public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        //获取组件
        Button btn1 = (Button) findComponentById(ResourceTable.Id_btn1);
        Image image1 = (Image) findComponentById(ResourceTable.Id_imageComponent);
        TextField tf1 = (TextField) findComponentById(ResourceTable.Id_text_field);
        //设置按键监听
        btn1.setClickedListener(component -> {
            //开一个新线程
            TaskDispatcher globalTaskDispatcher = this.getGlobalTaskDispatcher(TaskPriority.DEFAULT);
            //异步
            globalTaskDispatcher.asyncDispatch(()->{
                //调用API生成二维码图片（网络地址）
                //返回字符串格式：{"imageUrl":"https://bj.bcebos.com/qr-code/22021215e07535dcaa53.jpg"}
                String string = tf1.getText();
                requestBody request_body = new requestBody(string,20,"L","jpg",
                        "https://apisown-test.bj.bcebos.com/qr-code-api-store.png");
                String request_result = baiduApi.sendRequest(request_body);
                //将JSON字符串转换为类，取出imageUrl
                returnBody returndata = JSON.parseObject(request_result, returnBody.class);
                String image_url = returndata.getImageUrl();
                //将网络图片显示到image组件
                LoadImageUtil.loadImg(this,image_url,image1);
            });
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
