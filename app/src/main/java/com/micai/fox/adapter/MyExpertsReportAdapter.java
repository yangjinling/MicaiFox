package com.micai.fox.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.ExpertsReportResultBean;
import com.micai.fox.util.DateUtil;

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
        viewHolder.needReInflate=true;
        ((TextView) viewHolder.findViewById(R.id.experts_report_title)).setText("" + mList.get(position).getTitle());
        ((TextView) viewHolder.findViewById(R.id.experts_report_tv_title)).setText("【" + mList.get(position).getGameName() + "】");
        ((TextView) viewHolder.findViewById(R.id.experts_report_tv_country1)).setText("" + mList.get(position).getHomeTeamName());
        ((TextView) viewHolder.findViewById(R.id.experts_report_tv_country2)).setText("" + mList.get(position).getGuestTeamName());
        TextView tv_date = ((TextView) viewHolder.findViewById(R.id.experts_report_tv_date));
        if (null != mList.get(position).getIssue()) {
            String date = mList.get(position).getIssue();
            if (date.substring(4).startsWith("0")) {
                tv_date.setText("" + date.substring(5, 6) + "月" + date.substring(6) + "日");
            } else {
                tv_date.setText("" + date.substring(4, 6) + "月" + date.substring(6) + "日");
            }
        }
        TextView tv_score = ((TextView) viewHolder.findViewById(R.id.experts_report_tv_score));
        if (null == mList.get(position).getGuestScore()) {
            tv_score.setText("VS");
        } else {
            tv_score.setText("" + mList.get(position).getHomeScore() + ":" + mList.get(position).getGuestScore());
        }
        TextView tv_rate = ((TextView) viewHolder.findViewById(R.id.experts_report_tv_changci));
        if (0 == mList.get(position).getTotalMatchNum()) {
            tv_rate.setVisibility(View.GONE);
        } else {
            String result = mList.get(position).getTotalMatchNum() + "中" + mList.get(position).getWinMatchNum();
            tv_rate.setText(result);
        }
        TextView tv_state = ((TextView) viewHolder.findViewById(R.id.experts_report_tv_state));
        tv_state.setText("" + DateUtil.getDistanceTimes(mList.get(position).getCreateDate(), System.currentTimeMillis()) + "发布");

    }
}
