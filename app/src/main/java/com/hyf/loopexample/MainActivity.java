package com.hyf.loopexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hyf.loopvplibrary.CircleIndicator;
import com.hyf.loopvplibrary.LoopBannerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Button update;
    private LoopBannerView bannerView;
    private CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        update = (Button) findViewById(R.id.btn_update);
        bannerView = (LoopBannerView) findViewById(R.id.banner_view);
        indicator = (CircleIndicator) findViewById(R.id.circle_indicator);
    }

    @Override
    protected void initData() {
        List<String> images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492942831262&di=08f274a55e2b99bce8c15dc2ea20e496&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F308%2F43%2FRPNLKL2F5F5O_1000x500.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492942954128&di=ef05ca7f34fdeb8b9d69f50603fc8b1d&imgtype=0&src=http%3A%2F%2Fyouimg1.c-ctrip.com%2Ftarget%2Ftg%2F618%2F555%2F456%2Ff4b989835eb5401881fd8cda48447124.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492942954123&di=f27d468cf3df990b5c3b398ee9df7b85&imgtype=0&src=http%3A%2F%2Fpic.eastlady.cn%2Fuploads%2Ftp%2F201703%2F9999%2F50839889fc.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492942954123&di=cc54b4395a93b60e86189c729af0c26d&imgtype=0&src=http%3A%2F%2Fimg5.gomein.net.cn%2Fimage%2Fprodimg%2FproductDesc%2FdescImg%2F201401%2Fdesc6082%2F9128321450%2F3_01.jpg");
        bannerView.setIsLoop(false);
        bannerView.setIndicatorColor(Color.parseColor("#ff705e"));
        bannerView.setIsShowIndicator(true);
        bannerView.setData(images);

        indicator.setVisibility(View.GONE);
//        indicator.setIndicatorColor(Color.parseColor("#369369"));
//        indicator.setViewPager(bannerView.getViewPager(),images.size());
    }

    @Override
    protected void initListener() {

    }
}
