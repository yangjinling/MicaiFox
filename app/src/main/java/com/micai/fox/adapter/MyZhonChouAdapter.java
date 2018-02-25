package com.micai.fox.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.micai.fox.R;
import com.micai.fox.activity.PayActivity;
import com.micai.fox.resultbean.MyZhongChouResultBean;

import java.util.List;

/**
 * 作者：杨金玲 on 2018/1/5 13:42
 * 邮箱：18363820101@163.com
 */

/*我的--我的众筹列表*/
public class MyZhonChouAdapter extends MyBaseAdapter<MyZhongChouResultBean.ExecDatasBean.RecordListBean> {
    private List<MyZhongChouResultBean.ExecDatasBean.RecordListBean> mLists;
    private Context context;
    private int type;

    public MyZhonChouAdapter(int type,List<MyZhongChouResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        this.type=type;
        this.mLists = list;
        this.context = context;
    }

    @Override
    public void setData(ViewHolder viewHolder, final int position) {
        ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_orderid)).setText(mLists.get(position).getOrderId());
       if (type==1){
        Button pay = ((Button) viewHolder.findViewById(R.id.item_zhongchou_btn_pay));
        pay.setVisibility(View.VISIBLE);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PayActivity.class);
                intent.putExtra("orderId",mLists.get(position).getOrderId());
                context.startActivity(intent);
//                Toast.makeText(context, "去支付", Toast.LENGTH_SHORT).show();
            }
        });}else if (type==2){
           ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_orderstate)).setText("已支付");
       }
        ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_talk)).setText(mLists.get(position).getTitle());
    }
}
