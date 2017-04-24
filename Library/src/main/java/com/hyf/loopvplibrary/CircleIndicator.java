package com.hyf.loopvplibrary;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/4/23.
 */

public class CircleIndicator extends LinearLayout {

    private Animator mImmediateAnimatorOut;
    private Animator mImmediateAnimatorIn;

    private ViewPager mViewPager;
    private Drawable indicatorDrawable;


    public CircleIndicator(Context context) {
        super(context);
        init(context);
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
//        setPadding(0,dip2px(10),0,dip2px(10));
        mImmediateAnimatorOut = createAnimatorOut(context);
        mImmediateAnimatorIn = createAnimatorIn(context);
    }

    private int mLastPosition = -1;
    private int realCount = 0;

    public void setViewPager(ViewPager viewPager,int realCount){
        if (mViewPager==null) {
            mViewPager = viewPager;
            this.realCount = realCount;

            indicatorDrawable = getResources().getDrawable(R.drawable.loop_indicator_dot);
            indicatorDrawable.setColorFilter(indicatorColor, PorterDuff.Mode.SRC_IN);

            createIndicators();
            mViewPager.removeOnPageChangeListener(onPageChangeListener);
            mViewPager.addOnPageChangeListener(onPageChangeListener);
            onPageChangeListener.onPageSelected(mViewPager.getCurrentItem());
        }
    }


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position % realCount;
            if (mViewPager.getAdapter() == null || mViewPager.getAdapter().getCount() <= 0) {
                return;
            }

            if (mImmediateAnimatorIn.isRunning()) {
                mImmediateAnimatorIn.end();
                mImmediateAnimatorIn.cancel();
            }

            if (mImmediateAnimatorOut.isRunning()) {
                mImmediateAnimatorOut.end();
                mImmediateAnimatorOut.cancel();
            }

            View currentIndicator;
            if (mLastPosition >= 0 && (currentIndicator = getChildAt(mLastPosition)) != null) {
                currentIndicator.setBackgroundDrawable(indicatorDrawable);
                mImmediateAnimatorIn.setTarget(currentIndicator);
                mImmediateAnimatorIn.start();
            }

            View selectedIndicator = getChildAt(position);
            if (selectedIndicator != null) {
                selectedIndicator.setBackgroundDrawable(indicatorDrawable);
                mImmediateAnimatorOut.setTarget(selectedIndicator);
                mImmediateAnimatorOut.start();
            }
            mLastPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void createIndicators() {
        removeAllViews();
        int count = realCount;
        if (count <= 0) {
            return;
        }
        int currentItem = mViewPager.getCurrentItem();
        for (int i = 0; i < count; i++) {
            if (currentItem == i) {
                addIndicator(indicatorDrawable, mImmediateAnimatorOut);
            } else {
                addIndicator(indicatorDrawable, mImmediateAnimatorIn);
            }
        }
    }


//    设置indicator的宽高，默认设置成20
    private int indicatorWidth = dip2px(5);
    public void setIndicatorWidth(int width){
        indicatorWidth = width;
    }

//    设置indicator的margin
    private int margin = dip2px(5);
    public void setIndicatorMargin(int margin){
        this.margin = margin;
    }

//    设置indicator的小圆点的颜色
    private int indicatorColor = Color.WHITE;
    public void setIndicatorColor(int color){
        this.indicatorColor = color;
    }


    private void addIndicator(Drawable backgroundDrawableId, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }

        View Indicator = new View(getContext());
        Indicator.setBackgroundDrawable(backgroundDrawableId);
//        Indicator.setBackgroundResource(R.drawable.loop_indicator_dot);
        addView(Indicator, indicatorWidth, indicatorWidth);
        LayoutParams lp = (LayoutParams) Indicator.getLayoutParams();
        lp.leftMargin = margin;
        lp.rightMargin = margin;
        Indicator.setLayoutParams(lp);

        animator.setTarget(Indicator);
        animator.start();
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private Animator createAnimatorOut(Context context) {
        return AnimatorInflater.loadAnimator(context, R.anim.scale_with_alpha);
    }

    private Animator createAnimatorIn(Context context) {
        Animator animatorIn;
//        if (mAnimatorReverseResId == 0) {
        animatorIn = AnimatorInflater.loadAnimator(context, R.anim.scale_with_alpha);
        animatorIn.setInterpolator(new ReverseInterpolator());
//        } else {
//            animatorIn = AnimatorInflater.loadAnimator(context, mAnimatorReverseResId);
//        }
        return animatorIn;
    }

    private class ReverseInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float value) {
            return Math.abs(1.0f - value);
        }
    }
}
