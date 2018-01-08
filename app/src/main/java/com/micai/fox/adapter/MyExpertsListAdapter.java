package com.micai.fox.adapter;

import android.content.Context;
import android.widget.LinearLayout;

import com.micai.fox.R;

import java.util.List;

/**
 * 作者：杨金玲 on 2018/1/5 13:42
 * 邮箱：18363820101@163.com
 */

public class MyExpertsListAdapter extends MyBaseAdapter<String> {
    private List<String> mLists;

    public MyExpertsListAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        this.mLists = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
    }
}
