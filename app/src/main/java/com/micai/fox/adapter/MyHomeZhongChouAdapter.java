package com.micai.fox.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.micai.fox.R;
import com.micai.fox.activity.ExpertsDetailActivity;
import com.micai.fox.activity.ZhongChouDetailActivity;
import com.micai.fox.app.Url;
import com.micai.fox.resultbean.HomeResultBean;
import com.micai.fox.resultbean.HomeZhongChouResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：杨金玲 on 2017/12/27 08:45
 * 邮箱：18363820101@163.com
 */

/*首页众筹列表*/
public class MyHomeZhongChouAdapter extends MyBaseAdapter<HomeZhongChouResultBean.ExecDatasBean.RecordListBean> {
    private List<HomeZhongChouResultBean.ExecDatasBean.RecordListBean> mList;
    private Context mContext;

    public MyHomeZhongChouAdapter(List<HomeZhongChouResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        mList = list;
        mContext = context;
    }

    @Override
    public void setData(ViewHolder viewHolder, final int position) {
        //头像
        CircleImageView head = ((CircleImageView) viewHolder.findViewById(R.id.home_zhong_iv_head));
        Glide.with(mContext).load(Url.WEB_BASE_IP + mList.get(position).getProPhoto()).asBitmap().placeholder(R.drawable.circle).error(R.drawable.circle).into(head);
        //姓名
        ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_name)).setText(mList.get(position).getProName());
        //专家简介
        ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_introduce)).setText(mList.get(position).getProAuth());
        //命中率
        ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_rates)).setText("" + mList.get(position).getHitRate());
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

        TextView tv_status = ((TextView) viewHolder.findViewById(R.id.home_zhong_tv_state));
        BigDecimal score = null;
        try {
            score = Tools.div(mList.get(position).getRealAmount(), mList.get(position).getAmountDown(), 2);
            LogUtil.e("YJL", mList.get(position).getRealAmount() + "====" + mList.get(position).getAmountDown() + "score==" + score);
            score = score.multiply(new BigDecimal(100));
            LogUtil.e("YJL", "score==" + score);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (null != mList.get(position).getStatus()) {
            if ("0".equals(mList.get(position).getStatus())) {
                //未开始
                pb_ing.setProgress(0);
                ll_start.setVisibility(View.VISIBLE);
                ll_people.setVisibility(View.GONE);
                ll_have.setVisibility(View.GONE);
                tv_status.setText("未开始");
                String time = DateUtil.getDistanceTime(mList.get(position).getStartDate(), System.currentTimeMillis());
                tv_time.setText("" + time);
            } else if ("9".equals(mList.get(position).getStatus())) {
                //流标
                pb_ing.setVisibility(View.GONE);
                pb_liu.setVisibility(View.VISIBLE);
                pb_liu.setProgress(score.intValue());
                ll_start.setVisibility(View.GONE);
                ll_people.setVisibility(View.VISIBLE);
                tv_people.setText("" + mList.get(position).getSupNum());
                tv_status.setText("流标");
                ll_have.setVisibility(View.VISIBLE);
                tv_have.setText("" + mList.get(position).getRealAmount());
            } else {
                pb_ing.setProgress(score.intValue());
                ll_start.setVisibility(View.GONE);
                ll_people.setVisibility(View.VISIBLE);
                tv_people.setText("" + mList.get(position).getSupNum());
                ll_have.setVisibility(View.VISIBLE);
                tv_have.setText("" + mList.get(position).getRealAmount());
                if ("4".equals(mList.get(position).getStatus()) || "3".equals(mList.get(position).getStatus()) || "2".equals(mList.get(position).getStatus())) {
                    tv_status.setText("已结束");
                } else {
                    tv_status.setText(score.intValue() + "%");
                }
            }
        }
        LinearLayout home_zhong_ll_zhonchou = ((LinearLayout) viewHolder.findViewById(R.id.home_zhong_ll_zhonchou));
        LinearLayout home_zhong_ll_expert = ((LinearLayout) viewHolder.findViewById(R.id.home_zhong_ll_experts));
        home_zhong_ll_zhonchou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ZhongChouDetailActivity.class);
                intent.putExtra("crowdingId", mList.get(position).getCrowdfundingId());
                intent.putExtra("status", mList.get(position).getStatus());
                mContext.startActivity(intent);
            }
        });
        home_zhong_ll_expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ExpertsDetailActivity.class);
                intent.putExtra("proId", mList.get(position).getProId());
                mContext.startActivity(intent);
            }
        });
    }
}
