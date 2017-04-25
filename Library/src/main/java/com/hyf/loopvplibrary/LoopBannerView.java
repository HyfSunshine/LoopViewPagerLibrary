package com.hyf.loopvplibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by HeYongFeng on 2017/4/20.
 */

class LoopBannerView extends RelativeLayout{
    private static final String TAG = "LoopBannerView";

    private Context context;
    private ViewPager viewPager;

    public LoopBannerView(Context context) {
        this(context,null);
        Log.e(TAG,"一个参数的构造方法");

    }

    public LoopBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Log.e(TAG,"两个参数的构造方法");
        initView();
    }

    private void initView() {
        viewPager = new ViewPager(context);
        addView(viewPager);
    }

    public LoopBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG,"三个参数的构造方法");
    }

    private List<String> imageList;
    public void setData(List<String> imageList){
        if (this.imageList!=null){
            Log.e(TAG,"setData()方法只能调用一次!");
            return;
        }else {
            this.imageList = imageList;
            LoopPageAdapter pageAdapter = new LoopPageAdapter();
            viewPager.setAdapter(pageAdapter);

            if (isShowIndicator) {
                initIndicatorView();
            }
        }
    }


    private void initIndicatorView() {
        CircleIndicator indicator = new CircleIndicator(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,dip2px(48));
        params.addRule(ALIGN_PARENT_BOTTOM);
        indicator.setLayoutParams(params);
        indicator.setIndicatorColor(indicatorColor);
        indicator.setViewPager(viewPager,imageList.size());
        addView(indicator);
    }

    private int indicatorColor = Color.WHITE;
    public void setIndicatorColor(int color){
        this.indicatorColor = color;
    }

    private boolean isLoop = false;
    public void setIsLoop(boolean isLoop){
        this.isLoop = isLoop;
    }

    private boolean autoPlay = false;
    public void setAutoPlay(boolean autoPlay){
        this.autoPlay = autoPlay;
    }

    private long playTime = 5000;
    public void setPlayTime(long time){
        playTime = time;
    }

    private ImageView.ScaleType ScaleType = ImageView.ScaleType.FIT_XY;
    public void setImageScaleType(ImageView.ScaleType scaleType){
        this.ScaleType = scaleType;
    }

    private boolean isShowIndicator = false;
    public void setIsShowIndicator(boolean isShowIndicator){
        this.isShowIndicator = isShowIndicator;
    }



    private class LoopPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return isLoop?Integer.MAX_VALUE:imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position%imageList.size();
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ScaleType);
            Glide.with(context).load(imageList.get(position)).into(imageView);
            container.addView(imageView);
            return imageView;
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public ViewPager getViewPager(){
        return  viewPager;
    }
}
