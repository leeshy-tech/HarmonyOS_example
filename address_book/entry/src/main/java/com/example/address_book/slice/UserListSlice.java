package com.example.address_book.slice;

import com.example.address_book.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.aafwk.content.Intent;


import ohos.agp.components.Text;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.resultset.ResultSet;
import ohos.utils.net.Uri;

public class UserListSlice extends AbilitySlice {
    private DataAbilityHelper dataAbilityHelper;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        this.setUIContent(ResourceTable.Layout_user_list);

        Text text = (Text) findComponentById(ResourceTable.Id_infoText);
        text.setText("");
        dataAbilityHelper = DataAbilityHelper.creator(this);
        //查询所有联系⼈信息
        Uri uri = Uri.parse("dataability:///com.example.address_book.DataBaseAbility/users");
        String[] colums = {"userId","userName","userTel","userAddr"};
        DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates();
        try {
            ResultSet rs = dataAbilityHelper.query(uri,colums,dataAbilityPredicates);
            //从rs中获取查询结果
            int rowCount = rs.getRowCount();
            if(rowCount>0){
                rs.goToFirstRow();
                do{
                    int userId = rs.getInt( 0);
                    String userName = rs.getString(1);
                    String userTel = rs.getString(2);
                    String userAddr = rs.getString(3);
                    String info = " ["+userId+","+userName+","+userTel+","+userAddr+"]";
                    text.setText( text.getText()+info );
                }while(rs.goToNextRow());
            }
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
    }
}