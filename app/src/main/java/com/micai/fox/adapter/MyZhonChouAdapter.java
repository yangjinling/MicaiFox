package com.micai.fox.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.micai.fox.R;

import java.util.List;

/**
 * 作者：杨金玲 on 2018/1/5 13:42
 * 邮箱：18363820101@163.com
 */

/*我的--我的众筹列表*/
public class MyZhonChouAdapter extends MyBaseAdapter<String> {
    private List<String> mLists;
    private Context context;

    public MyZhonChouAdapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        this.mLists = list;
        this.context = context;
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        Button pay = ((Button) viewHolder.findViewById(R.id.item_zhongchou_btn_pay));
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "去支付", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
