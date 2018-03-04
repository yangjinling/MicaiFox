package com.micai.fox.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextClock;
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
        ((TextView) viewHolder.findViewById(R.id.tv_type_lv_report)).setText("【" + list.get(position).getGameName() + "】");
        ((TextView) viewHolder.findViewById(R.id.tv_country1_lv_report)).setText(list.get(position).getHomeTeamName());
        ((TextView) viewHolder.findViewById(R.id.tv_country2_lv_report)).setText(list.get(position).getGuestTeamName());
        String result = list.get(position).getTotalMatchNum() + "中" + list.get(position).getWinMatchNum();
        TextView tv_rate = ((TextView) viewHolder.findViewById(R.id.tv_rate_lv_report));
        if (0 == list.get(position).getTotalMatchNum()) {
            tv_rate.setVisibility(View.GONE);
        } else {
            tv_rate.setText(result);
        }
        TextView tv_score = ((TextView) viewHolder.findViewById(R.id.tv_score_lv_report));
        if (null==list.get(position).getGuestScore()){
            tv_score.setText("VS");
        }else {
            tv_score.setText("" + list.get(position).getHomeScore() + ":" + list.get(position).getGuestScore());
        }

        MyReportResultBean.ExecDatasBean.RecordListBean  recordListBean = list.get(position);
        TextView tv_date = ((TextView) viewHolder.findViewById(R.id.tv_date_lv_report));
        if (null!=recordListBean.getIssue()){
            String date=recordListBean.getIssue();
            if (date.substring(4).startsWith("0")){
                tv_date.setText(""+date.substring(5,6)+"月"+date.substring(6)+"日");
            }else {
                tv_date.setText(""+date.substring(4,6)+"月"+date.substring(6)+"日");
            }
        }

    }
}
