package com.micai.fox.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.NotificationResultBean;

import java.util.List;

/**
 * 作者：杨金玲 on 2018/1/4 14:54
 * 邮箱：18363820101@163.com
 */
/*我的--通知列表*/
public class MyNotificationAdapter extends MyBaseAdapter<NotificationResultBean.ExecDatasBean.RecordListBean> {
    private List<NotificationResultBean.ExecDatasBean.RecordListBean> mLists;
    private Context context;
    //首先设置一个变量，因为最初没有选择任何Item，所以我设为-1了，如果默认是第一个，也可以设为0
    private int selectedId = -1;

    public MyNotificationAdapter(List<NotificationResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        this.mLists = list;
        this.context = context;
    }

    //这里新写一个方法，用来设置选中的id
    public void setSelectedId(int position) {
        selectedId = position;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        TextView content = ((TextView) viewHolder.findViewById(R.id.notification_tv_content));
        content.setText("" + mLists.get(position).getContent());
        if (1 == mLists.get(position).getReviewFlag()) {
            content.setTextColor(context.getResources().getColor(R.color.text_gray));
        }
        if (selectedId == position) {
            content.setTextColor(context.getResources().getColor(R.color.text_gray));
        }
        TextView title = ((TextView) viewHolder.findViewById(R.id.notification_tv_title));
        ImageView iv = ((ImageView) viewHolder.findViewById(R.id.notification_iv));
        switch (mLists.get(position).getType()) {
            case 0://众筹满标
                title.setText("满标通知");
                iv.setBackgroundResource(R.mipmap.welldone);
                break;
            case 1://众筹流标
                title.setText("流标通知");
                iv.setBackgroundResource(R.mipmap.fail);

                break;
            case 2://众筹退款
                title.setText("退款通知");
                iv.setBackgroundResource(R.mipmap.refund);
                break;
            case 3://众筹催付
                title.setText("催付通知");
                iv.setBackgroundResource(R.mipmap.pay);
                break;
            case 4://众筹披露
                title.setText("披露通知");
                iv.setBackgroundResource(R.mipmap.forms);
                break;
            case 5://众筹兑付
                title.setText("兑付通知");
                iv.setBackgroundResource(R.mipmap.charge);
                break;
        }

    }
}
