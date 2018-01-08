package com.micai.fox.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by lq on 2018/1/8.
 */

public class MyExpertsReportAdapter extends MyBaseAdapter<String> {
    private List<String> mList;

    public MyExpertsReportAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        this.mList = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {

    }
}
