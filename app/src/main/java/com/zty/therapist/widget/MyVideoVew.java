package com.zty.therapist.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class MyVideoVew extends VideoView {

	public MyVideoVew(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MyVideoVew(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyVideoVew(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	          
	    // 下面的代码是让视频的播放的长宽是根据你设置的参数来决定  
	  
	    int width = getDefaultSize(0, widthMeasureSpec);  
	    int height = getDefaultSize(0, heightMeasureSpec);  
	    setMeasuredDimension(width, height);  
	} 
}
