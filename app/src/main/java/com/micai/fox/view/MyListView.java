package com.micai.fox.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作者：杨金玲 on 2018/1/5 08:47
 * 邮箱：18363820101@163.com
 */

public class MyListView extends ListView{

        public MyListView(Context context) {
            // TODO Auto-generated method stub
            super(context);
        }

        public MyListView(Context context, AttributeSet attrs) {
            // TODO Auto-generated method stub
            super(context, attrs);
        }

        public MyListView(Context context, AttributeSet attrs, int defStyle) {
            // TODO Auto-generated method stub
            super(context, attrs, defStyle);
        }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
