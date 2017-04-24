package com.hyf.loopvplibrary;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class LoopViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> imageList;

    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;

    public LoopViewPagerAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    public LoopViewPagerAdapter(Context context, List<String> imageList, ImageView.ScaleType scaleType) {
        this.context = context;
        this.imageList = imageList;
        this.scaleType = scaleType;
    }

    @Override
    public int getCount() {
        return imageList.size()>0?Integer.MAX_VALUE:imageList.size();
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
        position = position % imageList.size();
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(scaleType);
        Glide.with(context).load(imageList.get(position)).into(imageView);
        container.addView(imageView);
        return imageView;
    }
}
