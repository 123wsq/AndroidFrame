package com.example.administrator.androidframe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;


@Route(path = "/app/testrouter")
public class TestRaouterActivity extends Activity{

    TextView start_break;
    @Autowired
    String name;
    @Autowired
    int age;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arouter);
        ARouter.getInstance().inject(this);


        start_break = findViewById(R.id.start_break);


        start_break.setText(name +"========"+age);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode ==KeyEvent.KEYCODE_BACK){
            setResult(202);
        }
        return super.onKeyDown(keyCode, event);

    }
}
