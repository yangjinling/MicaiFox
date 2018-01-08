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

import com.micai.fox.R;
import com.micai.fox.activity.ExpertsDetailActivity;
import com.micai.fox.activity.ReportDetailActivity;
import com.micai.fox.activity.ZhongChouDetailActivity;
import com.micai.fox.adapter.MyExpertsListAdapter;
import com.micai.fox.adapter.MyExpertsReportAdapter;
import com.micai.fox.adapter.MyExpertsZhonChouAdapter;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * 作者：杨金玲 on 2017/12/27 16:45
 * 邮箱：18363820101@163.com
 */

public class ExpertsZhongChouFragment extends Fragment {
    private int kind;
    //    private TextView tv;
    private ArrayList<String> data;
    private ListView lv;
    private View footer_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhong_report_detail_experts, container, false);
//        kind = getArguments().getInt("KIND", 0);
        lv = ((ListView) view.findViewById(R.id.experts_detail_zhong_report_lv));
        lv.setFocusable(false);
        data = getData();
      /*  switch (kind) {
            case 0://众筹
//                tv.setText("全部");
                MyExpertsZhonChouAdapter adapter = new MyExpertsZhonChouAdapter(data, getContext(), R.layout.item_lv_experts_zhongchou);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), ZhongChouDetailActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 1://报告
                MyExpertsReportAdapter reportAdapter = new MyExpertsReportAdapter(data, getContext(), R.layout.item_lv_experts_report);
                lv.setAdapter(reportAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), ReportDetailActivity.class);
                        startActivity(intent);
                    }
                });
//                tv.setText("盈利榜");
                break;

        }*/
//        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
//        lv.addFooterView(footer_view);
        MyExpertsZhonChouAdapter adapter = new MyExpertsZhonChouAdapter(data, getContext(), R.layout.item_lv_experts_zhongchou);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ZhongChouDetailActivity.class);
                startActivity(intent);}});
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
