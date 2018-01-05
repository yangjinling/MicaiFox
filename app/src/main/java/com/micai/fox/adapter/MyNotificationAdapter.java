package com.micai.fox.adapter;

import android.content.Context;

import java.util.List;

/**
 * 作者：杨金玲 on 2018/1/4 14:54
 * 邮箱：18363820101@163.com
 */

public class MyNotificationAdapter extends MyBaseAdapter<String> {
    private List<String> mLists;

    public MyNotificationAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        this.mLists = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {

    }
}
