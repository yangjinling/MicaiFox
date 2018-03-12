package com.micai.fox.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by lq on 2018/3/12.
 */

public class LvUtil {
    public static void setListViewHeightBasedOnChildren(ListView listView) { //获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            //为空没没做任何事情
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            //listAdapter.getCount()过的适配器的item行数
            View listItem = listAdapter.getView(i, null, listView);
            //计算子项View 的宽高
            listItem.measure(0, 0);
            //统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        //获取listView布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //listView.getDividerHeight()获取子项间分隔符占用的高度
        //params.height最后得到整个ListView完整显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
