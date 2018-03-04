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
import com.micai.fox.util.DateUtil;

import java.util.List;

/**
 * 作者：杨金玲 on 2018/1/5 13:42
 * 邮箱：18363820101@163.com
 */

/*我的--我的众筹列表*/
public class MyZhonChouAdapter extends MyBaseAdapter<MyZhongChouResultBean.ExecDatasBean.RecordListBean> {
    private List<MyZhongChouResultBean.ExecDatasBean.RecordListBean> mLists;
    private Context context;

    public MyZhonChouAdapter(List<MyZhongChouResultBean.ExecDatasBean.RecordListBean> list, Context context, int resId) {
        super(list, context, resId);
        this.mLists = list;
        this.context = context;
    }

    @Override
    public void setData(ViewHolder viewHolder, final int position) {
        ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_orderid)).setText(mLists.get(position).getOrderId());
        TextView orderStatus = ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_orderstate));
        Button pay = ((Button) viewHolder.findViewById(R.id.item_zhongchou_btn_pay));
        TextView zhongchouStatus = ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_state));
        switch (mLists.get(position).getOrderStatus()) {
            case 0:
                orderStatus.setText("待支付");
                pay.setVisibility(View.VISIBLE);
                pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, PayActivity.class);
                        intent.putExtra("orderId", mLists.get(position).getOrderId());
                        context.startActivity(intent);
//                Toast.makeText(context, "去支付", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 1:
                orderStatus.setText("已支付");
                break;
            case 3:
                orderStatus.setText("已退款");
                break;
            case 4:
                orderStatus.setText("已过期");
                break;
            case 7:
                orderStatus.setText("已兑付");
                break;
        }
        switch (mLists.get(position).getCrowdfundingStatus()) {
            case 1://众筹中
                zhongchouStatus.setText("众筹中");
                break;
            case 2://已满标
                zhongchouStatus.setText("已满标");
                break;
            case 5://已披露
                zhongchouStatus.setText("已披露");
                break;
            case 7:
                //已兑付
                zhongchouStatus.setText("已兑付");
                break;
            case 9:
                //流标
                zhongchouStatus.setText("流标");
                break;
        }
        ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_talk)).setText(mLists.get(position).getTitle());
      String date=  DateUtil.getDateToString(mLists.get(position).getCreateDate());
        ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_date)).setText(""+date);
        ((TextView) viewHolder.findViewById(R.id.item_zhongchou_tv_money)).setText("￥"+mLists.get(position).getPurchaseAmount());
    }
}
