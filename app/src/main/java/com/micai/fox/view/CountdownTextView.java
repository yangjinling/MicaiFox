package com.micai.fox.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import com.micai.fox.adapter.MyBaseAdapter;
import com.micai.fox.util.DateUtil;

import java.util.Date;

/**
 * Created by louqiang on 2018/3/18.
 */

public class CountdownTextView extends AppCompatTextView {

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 在控件被销毁时移除消息
        handler.removeMessages(0);
    }

    long Time;
    private boolean run = true; // 是否启动了
    @SuppressLint("NewApi")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (run) {
                        long mTime = Time;
                        if (mTime > 0) {
                            String time = DateUtil.getDistanceTime(mT, System.currentTimeMillis());
                            CountdownTextView.this.setText(time);
                            Time = Time - 1000;
                            handler.sendEmptyMessageDelayed(0, 1000);
                        }
                    } else {
                        CountdownTextView.this.setVisibility(View.GONE);
                    }
                    break;

            }
        }
    };


    public CountdownTextView(Context context) {
        super(context);
    }

    ;

    public CountdownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private long mT;

    @SuppressLint("NewApi")
    public void setTimes(long mT) {
        // 标示已经启动
        Date date = new Date();
        long t2 = date.getTime();
        this.mT = mT;
        Time = mT - t2;
        if (Time > 0) {
            handler.removeMessages(0);
            handler.sendEmptyMessage(0);
        } else {
            CountdownTextView.this.setVisibility(View.GONE);
        }
    }

    public void stop() {
        run = false;
    }
}