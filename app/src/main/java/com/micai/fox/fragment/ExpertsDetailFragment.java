package com.micai.fox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.activity.ExpertsDetailActivity;
import com.micai.fox.adapter.MyExpertsListAdapter;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * 作者：杨金玲 on 2017/12/27 16:45
 * 邮箱：18363820101@163.com
 */

public class ExpertsDetailFragment extends Fragment {
    private int kind;
//    private TextView tv;
    private ArrayList<String> data;
    private ListView lv;
    private View footer_view;
    private View headView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_experts, container, false);
        kind = getArguments().getInt("KIND", 0);
        lv = ((ListView) view.findViewById(R.id.experts_fragment_lv));
        switch (kind) {
            case 0:
//                tv.setText("全部");
                break;
            case 1:
//                tv.setText("盈利榜");
                break;
            case 2:
//                tv.setText("命中榜");
                break;
        }
        data = getData();
        headView = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.headview_lv, null);
        lv.addHeaderView(headView);
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
        lv.addFooterView(footer_view);
        MyExpertsListAdapter adapter = new MyExpertsListAdapter(data, getContext(),R.layout.item_lv_experts);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ExpertsDetailActivity.class);
                startActivity(intent);
            }
        });
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
    }
}
