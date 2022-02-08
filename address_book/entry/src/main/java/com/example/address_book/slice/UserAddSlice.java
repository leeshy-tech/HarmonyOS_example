package com.example.address_book.slice;

import com.example.address_book.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.TextField;
import ohos.data.distributed.common.Value;
import ohos.data.rdb.ValuesBucket;
import ohos.utils.net.Uri;
import ohos.utils.system.SystemCapability;

public class UserAddSlice extends AbilitySlice {
    private DataAbilityHelper dataAbilityHelper;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_user_add);
        dataAbilityHelper = DataAbilityHelper.creator(this);
        //获取组件对象
        Button btn_add = (Button) findComponentById(ResourceTable.Id_btn_add);
        TextField tf1 = (TextField) findComponentById(ResourceTable.Id_textField1);
        TextField tf2 = (TextField) findComponentById(ResourceTable.Id_textField2);
        TextField tf3 = (TextField) findComponentById(ResourceTable.Id_textField3);
        //绑定事件监听器
        btn_add.setClickedListener(component -> {
            String userName = tf1.getText();
            String userTel = tf2.getText();
            String userAddr = tf3.getText();
            //构造VB
            ValuesBucket valuesBucket = new ValuesBucket();
            valuesBucket.putString("userName",userName);
            valuesBucket.putString("userTel",userTel);
            valuesBucket.putString("userAddr",userAddr);
            //插入数据
            try{
                Uri uri = Uri.parse("dataability:///com.example.address_book.DataBaseAbility/users");
                int i = dataAbilityHelper.insert(uri,valuesBucket);
                System.out.println("------------>>>>>>"+i);
            }catch (DataAbilityRemoteException e){
                e.printStackTrace();
            }
        });
    }
}
