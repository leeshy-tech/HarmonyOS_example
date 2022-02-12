package com.example.api;

import com.example.api.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
//若你想运行此项目，则需要在baiduApi.java里填入自己的accessKey和secretKey。
//若只是参考代码，请忽略。
public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
    }
}
