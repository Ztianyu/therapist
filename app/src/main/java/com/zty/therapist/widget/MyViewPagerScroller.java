package com.zty.therapist.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

public class MyViewPagerScroller extends Scroller {

	private int mDuration = 0;// 滑动速度

	public MyViewPagerScroller(Context context) {
		super(context);
	}
	
	/** 
     * 设置速度速度 
     * @param duration 
     */  
    public void setScrollDuration(int duration){  
        this.mDuration = duration;  
    }

	public MyViewPagerScroller(Context context, Interpolator interpolator,
							   boolean flywheel) {
		super(context, interpolator, flywheel);
	}

	public MyViewPagerScroller(Context context, Interpolator interpolator) {
		super(context, interpolator);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy) {
		super.startScroll(startX, startY, dx, dy, mDuration);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
		super.startScroll(startX, startY, dx, dy, mDuration);
	}
	
	public void initViewPagerScroll(ViewPager viewPager) {
        try {  
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);  
            mScroller.set(viewPager, this);  
        } catch(Exception e) {
            e.printStackTrace();  
        }  
    }

}
