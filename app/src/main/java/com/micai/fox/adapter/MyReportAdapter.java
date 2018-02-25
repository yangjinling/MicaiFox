package com.micai.fox.adapter;

import android.content.Context;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.MyReportResultBean;

import java.util.List;

/**
 * 作者：杨金玲 on 2018/1/4 14:49
 * 邮箱：18363820101@163.com
 */

/*我的---我的报告列表*/
public class MyReportAdapter extends MyBaseAdapter<MyReportResultBean.ExecDatasBean.RecordListBean> {
    private List<MyReportResultBean.ExecDatasBean.RecordListBean> list;

    public MyReportAdapter(List<MyReportResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.findViewById(R.id.tv_title_lv_report)).setText(list.get(position).getTitle());
        ((TextView) viewHolder.findViewById(R.id.tv_type_lv_report)).setText("【"+list.get(position).getGameName()+"】");
        ((TextView) viewHolder.findViewById(R.id.tv_country1_lv_report)).setText(list.get(position).getHomeTeamName());
        ((TextView) viewHolder.findViewById(R.id.tv_country2_lv_report)).setText(list.get(position).getGuestTeamName());

    }
}
