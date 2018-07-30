package com.hyf.loopvplibrary;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class LoopViewPagerAdapter<T> extends PagerAdapter {

    private Context context;
    private List<T> imageList;

    private ImageView.ScaleType scaleType = ImageView.ScaleType.CENTER_CROP;
    private boolean isMaxValue = true;

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public void setMaxValue(boolean maxValue) {
        isMaxValue = maxValue;
    }

    public LoopViewPagerAdapter(Context context, List<T> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    @Override
    public int getCount() {
        return imageList.size() > 0 && isMaxValue ? Integer.MAX_VALUE : imageList.size();
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
        RequestOptions options = new RequestOptions()
                .error(R.drawable.loop_view_placeholder);
        Glide.with(context).load(imageList.get(position)).apply(options).into(imageView);
        container.addView(imageView);
        return imageView;
    }
}
