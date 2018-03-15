package com.micai.fox.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.micai.fox.app.Url;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by lq on 2018/3/15.
 */

public class URLImageParser implements Html.ImageGetter {
    private String shopDeString;
    private TextView textView;
    Context context;
    private DisplayImageOptions options;

    public URLImageParser(String shopDeString, Context context, TextView textView, DisplayImageOptions options) {
        this.shopDeString = shopDeString;
        this.context = context;
        this.textView = textView;
        this.options = options;
    }

    @Override
    public Drawable getDrawable(String source) {

        final URLDrawable urlDrawable = new URLDrawable();
        ImageSize imageSize = new ImageSize(480, 320);
        NonViewAware nonViewAware = new NonViewAware(imageSize, ViewScaleType.CROP);
        ImageLoader.getInstance().displayImage(Url.WEB_BASE_IP + source, nonViewAware, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                urlDrawable.bitmap = loadedImage;
                urlDrawable.setBounds(0, 0, loadedImage.getWidth(), loadedImage.getHeight());
                textView.invalidate();
                textView.setText(textView.getText()); // 解决图文重叠
            }
        });
        /*ImageLoader.getInstance().loadImage(source,options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            }
        });*/
        return urlDrawable;
    }
}