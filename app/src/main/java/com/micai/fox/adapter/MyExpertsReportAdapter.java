package com.micai.fox.adapter;

import android.content.Context;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.ExpertsReportResultBean;

import java.util.List;

/**
 * Created by lq on 2018/1/8.
 */

/*专家详情---报告列表*/
public class MyExpertsReportAdapter extends MyBaseAdapter<ExpertsReportResultBean.ExecDatasBean.RecordListBean> {
    private List<ExpertsReportResultBean.ExecDatasBean.RecordListBean> mList;

    public MyExpertsReportAdapter(List<ExpertsReportResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        this.mList = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.findViewById(R.id.experts_report_title)).setText(""+mList.get(position).getTitle());
        ((TextView) viewHolder.findViewById(R.id.experts_report_tv_title)).setText("【"+mList.get(position).getGameName()+"】");
        ((TextView) viewHolder.findViewById(R.id.experts_report_tv_country1)).setText(""+mList.get(position).getHomeTeamName());
        ((TextView) viewHolder.findViewById(R.id.experts_report_tv_country2)).setText(""+mList.get(position).getGuestTeamName());

    }
}
