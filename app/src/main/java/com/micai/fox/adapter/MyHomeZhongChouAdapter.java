package com.micai.fox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.HomeResultBean;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * 作者：杨金玲 on 2017/12/27 08:45
 * 邮箱：18363820101@163.com
 */

/*首页众筹列表*/
public class MyHomeZhongChouAdapter extends MyBaseAdapter<HomeResultBean.ExecDatasBean.CrowdfundingBean.RecordListBean> {
    private List<HomeResultBean.ExecDatasBean.CrowdfundingBean.RecordListBean> mList;
    private Context mContext;

    public MyHomeZhongChouAdapter(List<HomeResultBean.ExecDatasBean.CrowdfundingBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        mList = list;
        mContext = context;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        //头像
        ((ImageView) viewHolder.findViewById(R.id.home_zhong_iv_head)).setImageResource(R.mipmap.ic_launcher_round);
        //姓名
        ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_name)).setText(mList.get(position).getProName());
        //专家简介
        ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_introduce)).setText(mList.get(position).getProAuth());
        //命中率
        ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_rates)).setText(""+mList.get(position).getHitRate());
        //标题
        ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_talk)).setText(mList.get(position).getTitle());
        //进度条
        ProgressBar pb_ing = ((ProgressBar) viewHolder.findViewById(R.id.home_zhong_prograss_ing));
        ProgressBar pb_liu = ((ProgressBar) viewHolder.findViewById(R.id.home_zhong_prograss_liu));
        //目标金额
        ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_mubiao)).setText("" + mList.get(position).getAmountDown());
        //已筹
        LinearLayout ll_have = ((LinearLayout) viewHolder.findViewById(R.id.home_zhong_ll_have));
        TextView tv_have = ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_have));

        //支持人次
        LinearLayout ll_people = ((LinearLayout) viewHolder.findViewById(R.id.home_zhong_ll_people));
        TextView tv_people = ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_people));
        //未开始
        LinearLayout ll_start = ((LinearLayout) viewHolder.findViewById(R.id.home_zhong_ll_nostart));
        TextView tv_time = ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_daojishi));
        if (null != mList.get(position).getStatus()) {
            if ("0".equals(mList.get(position).getStatus())) {
                //未开始
                pb_ing.setProgress(0);
                ll_start.setVisibility(View.VISIBLE);
                ll_people.setVisibility(View.GONE);
                ll_have.setVisibility(View.GONE);
            } else if ("9".equals(mList.get(position).getStatus())) {
                //流标
                pb_ing.setVisibility(View.GONE);
                pb_liu.setVisibility(View.VISIBLE);
                pb_liu.setProgress(50);
                ll_start.setVisibility(View.GONE);
                ll_people.setVisibility(View.VISIBLE);
                tv_people.setText(""+mList.get(position).getSupNum());
                ll_have.setVisibility(View.VISIBLE);
            } else {
                pb_ing.setProgress(80);
                ll_start.setVisibility(View.GONE);
                ll_people.setVisibility(View.VISIBLE);
                tv_people.setText(""+mList.get(position).getSupNum());
                ll_have.setVisibility(View.VISIBLE);
            }
        }

    }
}
