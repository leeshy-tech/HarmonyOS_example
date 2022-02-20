package com.example.users.slice;

import com.alibaba.fastjson.JSON;
import com.example.users.ResourceTable;
import com.example.users.beans.Account;
import com.example.users.beans.LoginMsg;
import com.example.users.utils.DataBaseUtil;
import com.example.users.utils.HttpRequestUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.PopupDialog;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

import java.util.concurrent.atomic.AtomicReference;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        String token_s =  DataBaseUtil.getToken(this);
        //如果本地数据库没有token，说明用户还没有登陆
        if (token_s == null) {
            Button btn_login = findComponentById(ResourceTable.Id_login_btn);
            TextField tf_userid = findComponentById(ResourceTable.Id_login_id_textfield);
            TextField tf_userPwd = findComponentById(ResourceTable.Id_login_pwd_textfield);

            String url = "http://8.136.83.196:8899/users/login";
            btn_login.setClickedListener(component -> {
                //开新线程
                TaskDispatcher globalTaskDispatcher = this.getGlobalTaskDispatcher(TaskPriority.DEFAULT);
                //异步
                globalTaskDispatcher.asyncDispatch(() -> {
                    String user_id = tf_userid.getText();
                    String user_pwd = tf_userPwd.getText();
                    //发送http请求，并获得数据
                    Account account = new Account(user_id, user_pwd);
                    String account_json = JSON.toJSONString(account);
                    String login_msg = HttpRequestUtil.sendPostRequest(this, url, account_json);
                    LoginMsg login_msg_obj = JSON.parseObject(login_msg, LoginMsg.class);
                    String token = login_msg_obj.getToken();
                    String msg = login_msg_obj.getMsg();
                    if (token != null) {
                        //将token存入本地数据库，并跳到个人页
                        DataBaseUtil.setToken(token, this);
                        present(new UserInfoAbilitySlice(), new Intent());
                    } else {
                        //返回主线程进行UI重绘，原因是show方法不能在子线程中运行
                        getUITaskDispatcher().asyncDispatch(new Runnable() {
                            @Override
                            public void run() {
                                new ToastDialog(getContext()).setText(msg).show();
                            }
                        });
                    }
                });
            });
        }
        //如果本地数据库有token，说明已经登陆，就跳到个人页
        else{
            present(new UserInfoAbilitySlice(), new Intent());
        }
    }

    @Override
    public void onActive() {
        super.onActive();
        //程序重新返回前台调用
        //若已经登陆，则导航到个人页
        String token_s =  DataBaseUtil.getToken(this);
        if (token_s != null){
            present(new UserInfoAbilitySlice(), new Intent());
        }
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);

    }
}
