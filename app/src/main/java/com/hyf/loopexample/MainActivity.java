package com.hyf.loopexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hyf.loopvplibrary.LoopBanner;

public class MainActivity extends BaseActivity {

    private Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        update = (Button) findViewById(R.id.btn_update);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoopBanner.showToast(MainActivity.this,"这是一个吐司");
            }
        });
    }
}
