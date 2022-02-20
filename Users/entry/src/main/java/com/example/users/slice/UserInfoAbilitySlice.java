package com.example.users.slice;

import com.example.users.ResourceTable;
import com.example.users.utils.DataBaseUtil;
import com.example.users.utils.HttpRequestUtil;
import com.example.users.utils.LoadImageUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Image;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

public class UserInfoAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_userinfo);
        //能导航到此页说明用户已经登陆，向服务器请求用户的头像
        String token = DataBaseUtil.getToken(this);
        Image image = findComponentById(ResourceTable.Id_image);
        if (token != null){
            //建新线程
            TaskDispatcher globalTaskDispatcher = this.getGlobalTaskDispatcher(TaskPriority.DEFAULT);
            //异步
            globalTaskDispatcher.asyncDispatch(()->{
                //发送请求，更新image组件
                String url = "http://8.136.83.196:8899/users/info";
                String img_url = HttpRequestUtil.sendPostRequestWithToken(this,url,token);
                LoadImageUtil.loadImg(this,img_url,image);
            });
        }
    }
}
