package com.lang.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lang.binderdemo.model.GoodBean;
import com.lang.binderdemo.model.IGoodManager;

public class BinderService extends Service {
    private Binder binder;
    private GoodBean goodBean;
    @Override
    public void onCreate() {
        super.onCreate();

        binder=new IGoodManager.Stub() {
            @Override
            public GoodBean getGoodBean() throws RemoteException {
                if(goodBean==null) {
                    goodBean = new GoodBean();
                    goodBean.setGoodName("我是" + BDUtil.getProcessName(BinderService.this) + "发来的信息");
                    goodBean.setId("1234567");
                }
                return goodBean;
            }

            @Override
            public void setGoodBean(GoodBean goodB) throws RemoteException {
                goodBean=goodB;
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
