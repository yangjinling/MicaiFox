package com.micai.fox.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by lq on 2018/1/10.
 */

public class ReportDetailLvAdapter extends MyBaseAdapter<String> {
    private List<String> mList;

    public ReportDetailLvAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        mList = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {

    }
}
