package com.example.address_book.slice;

import com.example.address_book.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        Button btn1 = (Button) findComponentById(ResourceTable.Id_btn1);
        btn1.setClickedListener(listener->present(new UserAddSlice(),new Intent()));

        Button btn2 = (Button) findComponentById(ResourceTable.Id_btn2);
        btn2.setClickedListener(listener->present(new UserListSlice(),new Intent()));

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

}
