package com.example.serviceability;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.LocalRemoteObject;
import ohos.aafwk.content.Intent;
import ohos.rpc.IRemoteObject;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MyService extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    //在每个生命周期函数内加一句sout调试
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        HiLog.error(LABEL_LOG, "MyService::onStart");
        System.out.println("--------------------onStart");
    }

    @Override
    public void onBackground() {
        super.onBackground();
        HiLog.info(LABEL_LOG, "MyService::onBackground");
        System.out.println("--------------------onBackground");
    }

    @Override
    public void onStop() {
        super.onStop();
        HiLog.info(LABEL_LOG, "MyService::onStop");
        System.out.println("--------------------onStop");
    }

    @Override
    public void onCommand(Intent intent, boolean restart, int startId) {
        System.out.println("--------------------onCommand");
    }

    @Override
    public IRemoteObject onConnect(Intent intent) {
        System.out.println("--------------------onConnect");
        //注意这个返回对象
        return new LocalRemoteObject() {};
    }

    @Override
    public void onDisconnect(Intent intent) {
        System.out.println("--------------------onDisconnect");
    }
}