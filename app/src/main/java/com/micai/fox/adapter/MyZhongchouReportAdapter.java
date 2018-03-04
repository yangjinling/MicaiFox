package com.micai.fox.adapter;

import android.content.Context;
import android.view.View;
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
        TextView tv_score = ((TextView) viewHolder.findViewById(R.id.zhouchou_detail_report_tv_score));
        if (null == mList.get(position).getHomeScore()) {
            tv_score.setText("VS");
        } else {
            tv_score.setText("" + mList.get(position).getHomeScore() + ":" + mList.get(position).getGuestScore());
        }
        TextView tv_rate = ((TextView) viewHolder.findViewById(R.id.zhouchou_detail_report_tv_changci));
        if (0 != mList.get(position).getTotalMatchNum()) {
            tv_rate.setVisibility(View.VISIBLE);
            tv_rate.setText("" + mList.get(position).getTotalMatchNum() + "中" + mList.get(position).getWinMatchNum());

        }
        TextView tv_date=((TextView) viewHolder.findViewById(R.id.zhouchou_detail_report_tv_date));
        if (null!=mList.get(position).getIssue()){
            String date=mList.get(position).getIssue();
            if (date.substring(4).startsWith("0")){
                tv_date.setText(""+date.substring(5,6)+"月"+date.substring(6)+"日");
            }else {
                tv_date.setText(""+date.substring(4,6)+"月"+date.substring(6)+"日");
            }
        }



    }
}
