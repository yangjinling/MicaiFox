package com.micai.fox.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.resultbean.BankBean;

import java.util.List;

/**
 * Created by lq on 2018/4/20.
 */

public class BankAdapter extends MyBaseAdapter<BankBean> {
    private List<BankBean> list;

    public BankAdapter(List<BankBean> list, Context context, int resId) {
        super(list, context, resId);
        this.list = list;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        TextView tv_name = ((TextView) viewHolder.findViewById(R.id.bank_name_tv));
        final ImageView iv = ((ImageView) viewHolder.findViewById(R.id.bank_iv));
        tv_name.setText(list.get(position).getName());
        ImageView iv_logo = ((ImageView) viewHolder.findViewById(R.id.iv_logo));
        iv_logo.setBackgroundResource(list.get(position).getImg_id());
        LinearLayout bank_pay_ll = ((LinearLayout) viewHolder.findViewById(R.id.bank_pay_ll));
        bank_pay_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setBackgroundResource(R.drawable.pointedselect);
            }
        });
    }
}
