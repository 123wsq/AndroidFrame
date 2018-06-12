package com.example.administrator.androidframe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wsq.library.utils.LogUtils;

public class MainActivity extends AppCompatActivity {

    private TextView start_break;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_break = this.findViewById(R.id.start_break);

        start_break.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* ARouter.getInstance().build("/test/testactivity").navigation(MainActivity.this, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        LogUtils.d("找到跳转activity");
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        LogUtils.d("跳转activity找不到了");
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        LogUtils.d("跳转完了");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        LogUtils.d("跳转activity被拦截了");
                    }
                });*/
            }
        });
    }
}
