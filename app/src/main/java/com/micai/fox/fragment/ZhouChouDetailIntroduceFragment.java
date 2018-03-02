package com.micai.fox.fragment;

import android.annotation.SuppressLint;
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
import com.micai.fox.adapter.MyExpertsListAdapter;
import com.micai.fox.view.CustomViewPager;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * 作者：杨金玲 on 2017/12/27 16:45
 * 邮箱：18363820101@163.com
 */

/*众筹详情--项目介绍模块*/
public class ZhouChouDetailIntroduceFragment extends Fragment {
    private int kind;
//    private TextView tv;
    private ArrayList<String> data;
    private CustomViewPager vp;
    public ZhouChouDetailIntroduceFragment() {
    }

    @SuppressLint("ValidFragment")
    public ZhouChouDetailIntroduceFragment(CustomViewPager vp){
        this.vp=vp;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhouchou_detail_introduce, container, false);
//        kind = getArguments().getInt("KIND", 0);
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
        vp.setObjectForPosition(view,0);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
