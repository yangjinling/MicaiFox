package com.micai.fox.adapter;

import android.content.Context;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.ExpertsReportResultBean;
import com.micai.fox.resultbean.ZhonchouReportResultBean;

import java.util.List;

/**
 * Created by lq on 2018/1/8.
 */

/*专家详情---报告列表*/
public class MyZhongchouReportAdapter extends MyBaseAdapter<ZhonchouReportResultBean.ExecDatasBean> {
    private List<ZhonchouReportResultBean.ExecDatasBean> mList;

    public MyZhongchouReportAdapter(List<ZhonchouReportResultBean.ExecDatasBean> list, Context context, int resId) {
        super(list, context, resId);
        this.mList = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.findViewById(R.id.zhouchou_detail_report_title)).setText("" + mList.get(position).getTitle());
        ((TextView) viewHolder.findViewById(R.id.zhouchou_detail_report_tv_title)).setText("【" + mList.get(position).getGameName() + "】");
        ((TextView) viewHolder.findViewById(R.id.zhouchou_detail_report_tv_country1)).setText("" + mList.get(position).getHomeTeamName());
        ((TextView) viewHolder.findViewById(R.id.zhouchou_detail_report_tv_country2)).setText("" + mList.get(position).getGuestTeamName());
        ((TextView) viewHolder.findViewById(R.id.zhouchou_detail_report_tv_score)).setText("" + mList.get(position).getHomeScore() + ":" + mList.get(position).getGuestScore());

    }
}
