package com.example.page_to_page.slice;

import com.example.page_to_page.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        //通过xml文件设置页面
        super.setUIContent(ResourceTable.Layout_ability_main);
        //单击按钮一 导航到SecondAbilitySlice
        Button btn1 = (Button) findComponentById(ResourceTable.Id_btn1);
        btn1.setClickedListener(listener->present(new SecondAbilitySlice(),new Intent()));
        //单击按钮二 导航到SecondAbilitySlice，并传递参数字符串
        Button btn2 = (Button) findComponentById(ResourceTable.Id_btn2);
        btn2.setClickedListener(listener -> {
            Intent intent1 = new Intent();
            intent1.setParam("my_string","从MainAbilitySlice传参");
            this.present(new SecondAbilitySlice(),intent1);
        });
        //单击按钮三
        Button btn3 = (Button) findComponentById(ResourceTable.Id_btn3);
        btn3.setClickedListener(listener -> navigateToAnotherPage(listener));
    }
    //导航到另一个页面的方法实现
    private void navigateToAnotherPage(Component component){
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")						//空字符串为本机
                .withBundleName("com.example.page_to_page")//本应用的标识
                .withAbilityName("com.example.page_to_page.AnotherAbility")//想启动的Ability
                .build();

        intent.setOperation(operation);
        this.startAbility(intent);
    }

}
