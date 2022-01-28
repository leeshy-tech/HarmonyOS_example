package com.example.page_to_page;

import com.example.page_to_page.slice.AnotherAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class AnotherAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(AnotherAbilitySlice.class.getName());
    }
}
