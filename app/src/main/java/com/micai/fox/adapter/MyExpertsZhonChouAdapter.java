package com.micai.fox.adapter;

import android.content.Context;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.ExpertsZhongchouResultBean;

import java.util.List;

/**
 * Created by lq on 2018/1/8.
 */
/*专家详情---众筹列表*/
public class MyExpertsZhonChouAdapter extends MyBaseAdapter<ExpertsZhongchouResultBean.ExecDatasBean.RecordListBean> {
    private List<ExpertsZhongchouResultBean.ExecDatasBean.RecordListBean> list;

    public MyExpertsZhonChouAdapter(List<ExpertsZhongchouResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list=list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_tv_title)).setText(list.get(position).getTitle());
        ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_mubiao)).setText(""+list.get(position).getAmountDown());
        ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_have)).setText(""+list.get(position).getRealAmount());
        ((TextView) viewHolder.findViewById(R.id.experts_zhongchou_people)).setText(""+list.get(position).getSupNum());
    }
}
