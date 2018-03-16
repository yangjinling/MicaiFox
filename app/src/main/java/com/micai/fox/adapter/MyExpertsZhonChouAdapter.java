package com.micai.fox.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.ExpertsZhongchouResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lq on 2018/1/8.
 */
/*专家详情---众筹列表*/
public class MyExpertsZhonChouAdapter extends MyBaseAdapter<ExpertsZhongchouResultBean.ExecDatasBean.RecordListBean> {
    private List<ExpertsZhongchouResultBean.ExecDatasBean.RecordListBean> list;

    public MyExpertsZhonChouAdapter(List<ExpertsZhongchouResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_tv_title)).setText(list.get(position).getTitle());
        ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_mubiao)).setText("" + list.get(position).getAmountDown());
        //已筹
        LinearLayout ll_have = ((LinearLayout) viewHolder.findViewById(R.id.experts_zhong_ll_have));
        TextView tv_have = ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_have));

        //支持人次
        LinearLayout ll_people = ((LinearLayout) viewHolder.findViewById(R.id.experts_zhong_ll_people));
        TextView tv_people = ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_people));
        //未开始
        LinearLayout ll_start = ((LinearLayout) viewHolder.findViewById(R.id.experts_zhong_ll_nostart));
        TextView tv_time = ((TextView) viewHolder.findViewById(R.id.experts_zhong_tv_daojishi));

        TextView tv_status = ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_state));
        //进度条
        ProgressBar pb_ing = ((ProgressBar) viewHolder.findViewById(R.id.experts_zhongchou_prograss));
        ProgressBar pb_liu = ((ProgressBar) viewHolder.findViewById(R.id.experts_zhongchou_prograss_liubiao));
        BigDecimal score = null;
        try {
            score = Tools.div(list.get(position).getRealAmount(), list.get(position).getAmountDown(), 2);
            LogUtil.e("YJL", list.get(position).getRealAmount() + list.get(position).getAmountDown() + "score==" + score);
            score = score.multiply(new BigDecimal(100));
            LogUtil.e("YJL", "score==" + score);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (null != list.get(position).getStatus()) {
            if ("0".equals(list.get(position).getStatus())) {
                //未开始
                pb_ing.setProgress(0);
                ll_start.setVisibility(View.VISIBLE);
                ll_people.setVisibility(View.GONE);
                ll_have.setVisibility(View.GONE);
                tv_status.setText("未开始");
                String time = DateUtil.getDistanceTime(list.get(position).getStartDate(), System.currentTimeMillis());
                tv_time.setText("" + time);
            } else if ("9".equals(list.get(position).getStatus())) {
                //流标
                pb_ing.setVisibility(View.GONE);
                pb_liu.setVisibility(View.VISIBLE);
                pb_liu.setProgress(score.intValue());
                ll_start.setVisibility(View.GONE);
                ll_people.setVisibility(View.VISIBLE);
                tv_people.setText("" + list.get(position).getSupNum());
                tv_status.setText("流标");
                ll_have.setVisibility(View.VISIBLE);
                tv_have.setText("" + list.get(position).getRealAmount());
            } else {
                pb_ing.setProgress(score.intValue());
                ll_start.setVisibility(View.GONE);
                ll_people.setVisibility(View.VISIBLE);
                tv_people.setText("" + list.get(position).getSupNum());
                ll_have.setVisibility(View.VISIBLE);
                tv_have.setText("" + list.get(position).getRealAmount());
                if ("4".equals(list.get(position).getStatus()) || "3".equals(list.get(position).getStatus()) || "2".equals(list.get(position).getStatus())) {
                    tv_status.setText("已结束");
                } else {
                    tv_status.setText(score.intValue() + "%");
                }
            }
        }
    }
}
