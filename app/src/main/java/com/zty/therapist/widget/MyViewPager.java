package com.zty.therapist.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {

	private boolean isPagingEnabled = false;// 是否可滑动

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyViewPager(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		MyViewPagerScroller scroller = new MyViewPagerScroller(context);
		scroller.setScrollDuration(0);
		scroller.initViewPagerScroll(this); // 这个是设置切换过渡时间为0毫秒
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.isPagingEnabled && super.onTouchEvent(event);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		return this.isPagingEnabled && super.onInterceptTouchEvent(event);
	}

	public void setPagingEnabled(boolean b) {
		this.isPagingEnabled = b;
	}
}
