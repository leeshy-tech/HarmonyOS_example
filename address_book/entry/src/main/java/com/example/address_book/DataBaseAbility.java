package com.example.address_book;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.data.DatabaseHelper;
import ohos.data.dataability.DataAbilityUtils;
import ohos.data.rdb.*;
import ohos.data.resultset.ResultSet;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.net.Uri;
import ohos.utils.PacMap;

import java.io.FileDescriptor;

public class DataBaseAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    //RdbStore 对象就表示与数据库的连接，通过此对象可以完成对数据表中数据的CRUD操作
    private RdbStore rdbStore;

    //StoreConfig对象关联数据⽂件配置(数据库)
    private StoreConfig config = StoreConfig.newDefaultConfig("UserStore.db");

    //RdbOpenCallback 使⽤rdbStore对象回调此RdbOpenCallback对象的onCreate创建数据表
    private RdbOpenCallback callback = new RdbOpenCallback() {
        @Override
        public void onCreate(RdbStore rdbStore) {
            //使⽤rdbStore对象执⾏SQL创建数据表
            rdbStore.executeSql("create table if not exists users(" +
                    "userId integer primary key autoincrement," +
                    "userName text not null," +
                    "userTel text not null unique," +
                    "userAddr text)");
        }
        @Override
        public void onUpgrade(RdbStore rdbStore, int i, int i1) {
        }
    };
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        HiLog.info(LABEL_LOG, "DataBaseAbility onStart");

        //初始化与数据库的连接
        DatabaseHelper helper = new DatabaseHelper(this);
        rdbStore = helper.getRdbStore(config,1,callback);
    }

    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {
        RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "users");
        ResultSet resultSet = rdbStore.query(rdbPredicates, columns);
        return resultSet;
    }

    public int insert(Uri uri, ValuesBucket value) {
        int i = -1;
        String path = uri.getLastPath();
        if("users".equalsIgnoreCase(path)){
            i = (int)rdbStore.insert("users",value);
        }
        return i;
    }

    public int delete(Uri uri, DataAbilityPredicates predicates) {
        RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "users");
        int i = rdbStore.delete(rdbPredicates);
        return i;
    }

    public int update(Uri uri, ValuesBucket value, DataAbilityPredicates predicates) {
        RdbPredicates rdbPredicates = DataAbilityUtils.createRdbPredicates(predicates, "users");
        int i = rdbStore.update(value, rdbPredicates);
        return i;
    }

}