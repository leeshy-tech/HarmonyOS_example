package com.example.api;

import com.example.api.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
// 若你想运行此项目，则需要在该API官网获取自己的accessKey和secretKey，并填入baiduApi.java的对应位置，否则无法正常运行
// https://apis.baidu.com/store/detail/581576df-bc52-4e4a-8a3a-2abd6035e7ae
// 若只是参考代码，请忽略。
public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
    }
}
