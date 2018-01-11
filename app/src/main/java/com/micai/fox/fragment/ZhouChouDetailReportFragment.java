package com.micai.fox.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.micai.fox.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：杨金玲 on 2017/12/27 16:45
 * 邮箱：18363820101@163.com
 */

public class ZhouChouDetailReportFragment extends Fragment {
    @Bind(R.id.zhouchou_detail_report_ll_ing)
    LinearLayout zhouchouDetailReportLlIng;
    @Bind(R.id.test)
    TextView test;
    private int kind;
    //    private TextView tv;
    private ArrayList<String> data;
    private ListView lv;
    private View footer_view;
    private View headView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhouchou_detail_report, container, false);
        ButterKnife.bind(this, view);
        kind = getArguments().getInt("KIND", 0);
        switch (kind) {
            case 0:
//                tv.setText("全部");
                zhouchouDetailReportLlIng.setVisibility(View.VISIBLE);
                break;
            case 1:
//                tv.setText("盈利榜");
                test.setVisibility(View.VISIBLE);
                break;
            case 2:
//                tv.setText("命中榜");
                break;
        }
        data = getData();
        return view;
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 50; i++) {
            data.add(i + temp);
        }

        return data;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}