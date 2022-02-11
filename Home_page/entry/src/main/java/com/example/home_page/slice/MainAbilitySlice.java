package com.example.home_page.slice;

import com.example.home_page.ResourceTable;
import com.example.home_page.provider.TabPageSliderProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        //1.初始化TabList
        TabList tabList = (TabList)
                findComponentById(ResourceTable.Id_tab_list);
        String[] tabListTags = {"⾸⻚","分类","购物⻋","我的"};
        for (int i = 0; i < tabListTags.length ; i++) {
            TabList.Tab tab = tabList.new Tab(this);
            tab.setText(tabListTags[i]);
            tabList.addTab(tab);
        }
        //2.初始化PageSlider
        List<Integer> layoutFileIds = new ArrayList<>();
        layoutFileIds.add(ResourceTable.Layout_ability_main_index);
        layoutFileIds.add(ResourceTable.Layout_ability_main_category);
        layoutFileIds.add(ResourceTable.Layout_ability_main_shopcart);
        layoutFileIds.add(ResourceTable.Layout_ability_main_user_center);
        PageSlider pageSlider = (PageSlider) findComponentById(ResourceTable.Id_page_slider);
        pageSlider.setProvider( new TabPageSliderProvider(layoutFileIds,this));
        //3.TabList与PageSlider联动
        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            public void onSelected(TabList.Tab tab) {
                //获取点击的菜单的索引
                int index = tab.getPosition();
                //设置pageSlider的索引与菜单索引⼀只
                pageSlider.setCurrentPage(index);
            }
            public void onUnselected(TabList.Tab tab) { }
            public void onReselected(TabList.Tab tab) { }
        });
        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            public void onPageSliding(int i, float v, int i1) {}
            public void onPageSlideStateChanged(int i) {}
            public void onPageChosen(int i) {
                //参数i就表单当前pageSlider的索引
                if (tabList.getSelectedTabIndex() != i){
                    tabList.selectTabAt(i);
                }
            }
        });
        //4.tabList默认选中第⼀个菜单，加载PageSlider的第⼀个⻚⾯（默认）
        tabList.selectTabAt(0);
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
