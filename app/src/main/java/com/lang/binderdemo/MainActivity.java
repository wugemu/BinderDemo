package com.lang.binderdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lang.binderdemo.model.GoodBean;
import com.lang.binderdemo.model.IGoodManager;

public class MainActivity extends AppCompatActivity {
    private GoodBean goodBean;
    private IGoodManager goodManager;
    private TextView tv_hellow;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                goodManager = IGoodManager.Stub.asInterface(service);
                goodBean = goodManager.getGoodBean();
                tv_hellow.setText(goodBean.getGoodName());
            }catch (Exception e){
                Log.e("0.0",e.toString());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_hellow=findViewById(R.id.tv_hellow);
        Log.d("0.0","Thread name: "+BDUtil.getProcessName(MainActivity.this));

        Button btn_jump=findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goodManager!=null){
                    goodBean=new GoodBean();
                    goodBean.setGoodName("我是"+BDUtil.getProcessName(MainActivity.this)+"发来的信息");
                    try {
                        goodManager.setGoodBean(goodBean);
                    }catch (Exception e){
                        Log.e("0.0",e.toString());
                    }
                }
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("hellow","123456");
                startActivity(intent);
            }
        });

        Intent intent = new Intent(this, BinderService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if(goodManager!=null) {
                goodBean = goodManager.getGoodBean();
                tv_hellow.setText(goodBean.getGoodName());
            }
        }catch (Exception e){
            Log.e("0.0",e.toString());
        }
    }
}
