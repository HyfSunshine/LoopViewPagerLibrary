package com.hyf.loopexample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HeYongFeng on 2017/4/20.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
        initListener();
    }
}
