package com.hyf.loopvplibrary;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class LoopViewPager extends ViewPager{
    private static final int MSG_LOOP_PICTURE = 1001;

    private PagerAdapter adapter;


    public LoopViewPager(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (onPageChangeListener != null) {
            super.removeOnPageChangeListener(onPageChangeListener);
        }
        super.addOnPageChangeListener(onPageChangeListener);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (autoPlay && adapter.getCount()>0){
                int count = adapter.getCount();
                int current = getCurrentItem();
                if (current == count-1){
                    setCurrentItem(0,true);
                }else {
                    setCurrentItem(++current, true);
                }
            }
        }
    };

    private boolean autoPlay = false;
    private long delayTime = 5000;

    public void setAutoPlay(boolean autoPlay,long delayTime) {
        if (!this.autoPlay) {
            this.autoPlay = autoPlay;
            this.delayTime = delayTime >= 0 ? delayTime : this.delayTime;
            //设置轮播时间
            if (this.autoPlay) {
                loopPictureIfNeed();
            }
        }else {
            throw new RuntimeException("setAutoPlay() method must be performed once!");
        }
    }

    private void loopPictureIfNeed() {
        handler.removeMessages(MSG_LOOP_PICTURE);
        handler.sendEmptyMessageDelayed(MSG_LOOP_PICTURE,delayTime);
    }

    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            loopPictureIfNeed();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void setAdapter(PagerAdapter adapter) {
        this.adapter = adapter;
        super.setAdapter(adapter);
    }
}
