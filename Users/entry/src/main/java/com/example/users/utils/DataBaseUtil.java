package com.example.users.utils;

import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.app.Context;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.utils.net.Uri;

//数据库查询、插入 帮助类
public class DataBaseUtil {
    private static Uri uri = Uri.parse("dataability:///com.example.users.LocalDBAbility/user_info");
    public static String getToken(Context context){
        String value = null;

        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        String[] colums = {"token"};
        DataAbilityPredicates predicates = new DataAbilityPredicates();
        try {
            ResultSet rs = dataAbilityHelper.query(uri, colums, predicates);
            if(rs.getRowCount() >0){
                rs.goToFirstRow();
                value = rs.getString(0);
            }
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return value;
    }
    public static int setToken(String token,Context context) {
        int i = 0;
        ValuesBucket valuesBucket = new ValuesBucket();
        valuesBucket.putString("token",token);
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        try {
            i = dataAbilityHelper.insert(uri, valuesBucket);
        } catch (DataAbilityRemoteException e) {
            e.printStackTrace();
        }
        return i;
    }
}