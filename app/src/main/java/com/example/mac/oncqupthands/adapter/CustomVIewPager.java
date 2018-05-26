package com.example.mac.oncqupthands.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by mac on 2018/5/25.
 */

public class CustomVIewPager extends ViewPager {

    private boolean isCanScroll = true;
    public CustomVIewPager(@NonNull Context context) {
        super(context);
    }

    public CustomVIewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll&&super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll&&super.onTouchEvent(ev);
    }
}
