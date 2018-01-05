package com.micai.fox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micai.fox.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：杨金玲 on 2017/12/27 08:45
 * 邮箱：18363820101@163.com
 */

public class MyListViewVAdapter extends MyBaseAdapter<String> {
    private List<String> mList;

    public MyListViewVAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
//        mList = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
//        ((TextView) viewHolder.findViewById(R.id.item_tv)).setText(mList.get(position));
    }
}
