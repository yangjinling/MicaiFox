package com.micai.fox.adapter;

import android.content.Context;

import java.util.List;

/**
 * 作者：杨金玲 on 2018/1/4 14:49
 * 邮箱：18363820101@163.com
 */

/*我的---我的报告列表*/
public class MyReportAdapter extends MyBaseAdapter<String> {
    private List<String> list;

    public MyReportAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {

    }
}
