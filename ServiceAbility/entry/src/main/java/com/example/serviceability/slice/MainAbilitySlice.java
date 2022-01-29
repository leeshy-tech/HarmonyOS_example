package com.example.serviceability.slice;

import com.example.serviceability.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.IAbilityConnection;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.bundle.ElementName;
import ohos.media.audio.SoundPlayer;
import ohos.rpc.IRemoteObject;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        //按钮1开启MyService
        Button btn1 = (Button) findComponentById(ResourceTable.Id_btn1);
        btn1.setClickedListener(component -> {
            Intent intent1 = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.serviceability")
                    .withAbilityName("com.example.serviceability.MyService")
                    .build();
            intent1.setOperation(operation);
            this.startAbility(intent1);
        });

        //按钮2连接到MyService
        Button btn2 = (Button) findComponentById(ResourceTable.Id_btn2);

        IAbilityConnection connection = new IAbilityConnection() {
            @Override
            public void onAbilityConnectDone(ElementName elementName, IRemoteObject iRemoteObject, int i) {
                System.out.println("----------------------连接MyService成功");
            }

            @Override
            public void onAbilityDisconnectDone(ElementName elementName, int i) {
                System.out.println("----------------------连接MyService失败");
            }
        };

        btn2.setClickedListener(component -> {
            Intent intent2 = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.serviceability")
                    .withAbilityName("com.example.serviceability.MyService")
                    .build();
            intent2.setOperation(operation);
            this.connectAbility(intent2,connection);
        });

        //按钮3断开与MyService的连接
        Button btn3 = (Button) findComponentById(ResourceTable.Id_btn3);
        btn3.setClickedListener(component -> {
            if(connection != null){
                this.disconnectAbility(connection);
            }
        });

        //按钮4关闭MyService
        Button btn4 = (Button) findComponentById(ResourceTable.Id_btn4);
        btn4.setClickedListener(component -> {
            Intent intent3 = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("")
                    .withBundleName("com.example.serviceability")
                    .withAbilityName("com.example.serviceability.MyService")
                    .build();
            intent3.setOperation(operation);
            this.stopAbility(intent3);
        });

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
