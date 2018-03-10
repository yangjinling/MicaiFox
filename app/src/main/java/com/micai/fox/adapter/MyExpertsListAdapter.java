package com.micai.fox.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.micai.fox.R;
import com.micai.fox.app.Url;
import com.micai.fox.resultbean.ExpertsResultBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：杨金玲 on 2018/1/5 13:42
 * 邮箱：18363820101@163.com
 */

/*专家---专家列表*/
public class MyExpertsListAdapter extends MyBaseAdapter<ExpertsResultBean.ExecDatasBean.RecordListBean> {
    private List<ExpertsResultBean.ExecDatasBean.RecordListBean> mLists;
    private Context mContext;

    public MyExpertsListAdapter(List<ExpertsResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        this.mLists = list;
        this.mContext = context;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.findViewById(R.id.experts_tv_name)).setText(mLists.get(position).getProName());
        ((TextView) viewHolder.findViewById(R.id.experts_tv_talk)).setText(mLists.get(position).getProAuth());
        ((TextView) viewHolder.findViewById(R.id.experts_tv_rate)).setText("" + mLists.get(position).getHitRate());
        CircleImageView iv = ((CircleImageView) viewHolder.findViewById(R.id.experts_iv_head));
        Glide.with(mContext).load(Url.WEB_BASE_IP + mLists.get(position).getProPhoto()).asBitmap().placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round).into(iv);


    }
}
