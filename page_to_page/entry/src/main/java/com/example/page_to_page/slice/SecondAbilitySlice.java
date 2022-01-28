package com.example.page_to_page.slice;

import com.example.page_to_page.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.agp.components.Text;


public class SecondAbilitySlice extends AbilitySlice {
    @Override
    protected void onStart(Intent intent){
        super.onStart(intent);
        //通过xml文件设置页面
        this.setUIContent(ResourceTable.Layout_ability_second);
        if(intent.getParams() != null){
            //从intent中取出参数
            IntentParams params = intent.getParams();
            String my_string = (String) params.getParam("my_string");
            //获得Text对象并赋值text属性
            Text text = (Text) findComponentById(ResourceTable.Id_text1);
            text.setText(my_string);
        }
    }
}
