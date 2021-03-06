package com.zty.therapist.imlib.task;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @Description 异步加载URL图片
 * 
 * @author xiaogao
 * 
 * @date 2015-03-24
 * */
public class AsyncImageLoader {
	
	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	public Drawable loadDrawable(final String imageUrl,
								 final ImageCallback imageCallback) {
		
		if (imageCache.containsKey(imageUrl)) {
			
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			
			if (drawable != null) {
				return drawable;
			}
		}
		
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
			}
		};
		
		new Thread() {
			@Override
			public void run() {
				Drawable drawable = loadImageFromUrl(imageUrl);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}.start();
		
		return null;
	}

	public static Drawable loadImageFromUrl(String url) {
		URL mUrl;
		InputStream inputStream = null;
		try {
			mUrl = new URL(url);
			inputStream = (InputStream) mUrl.getContent();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Drawable drawable = Drawable.createFromStream(inputStream, "src");
		
		return drawable;
	}

	public interface ImageCallback {
		void imageLoaded(Drawable imageDrawable, String imageUrl);
	}
}
