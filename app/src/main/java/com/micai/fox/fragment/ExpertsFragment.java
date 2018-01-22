package com.micai.fox.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.adapter.ViewPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lq on 2017/12/19.
 */

public class ExpertsFragment extends Fragment {
    @Bind(R.id.experts_ll)
    LinearLayout expertsLl;
    @Bind(R.id.experts_tv_all)
    TextView expertsTvAll;
    @Bind(R.id.experts_tv_income)
    TextView expertsTvIncome;
    @Bind(R.id.experts_tv_mizhong)
    TextView expertsTvMizhong;
    @Bind(R.id.experts_view_all)
    View expertsViewAll;
    @Bind(R.id.experts_view_income)
    View expertsViewIncome;
    @Bind(R.id.experts_view_mizhong)
    View expertsViewMizhong;
    @Bind(R.id.experts_tablayout)
    TabLayout expertsTablayout;
    @Bind(R.id.experts_viewpager)
    ViewPager expertsViewpager;
    private FragmentManager manager;
    private FragmentTransaction transcation;
    private int kind = 0;
    private FragmentPagerAdapter fAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experts, container, false);
        ButterKnife.bind(this, view);
//        EventBus.getDefault().register(this);
//        manager = getActivity().getSupportFragmentManager();
//        choiceFragment(kind);
        initControls();
        return view;
    }

    @OnClick({R.id.experts_tv_all, R.id.experts_tv_income, R.id.experts_tv_mizhong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.experts_tv_all:
                //全部
//                expertsTvAll.setTextColor(getResources().getColor(R.color.white));
//                expertsTvIncome.setTextColor(getResources().getColor(R.color.gray));
//                expertsTvMizhong.setTextColor(getResources().getColor(R.color.gray));
                expertsViewAll.setBackgroundResource(R.color.white);
                expertsViewIncome.setBackgroundColor(new Color().alpha(0));
                expertsViewMizhong.setBackgroundColor(new Color().alpha(0));
                kind = 0;
                choiceFragment(kind);
                break;
            case R.id.experts_tv_income:
                //盈利帮
//                expertsTvAll.setTextColor(getResources().getColor(R.color.gray));
//                expertsTvIncome.setTextColor(getResources().getColor(R.color.blue));
//                expertsTvMizhong.setTextColor(getResources().getColor(R.color.gray));
                expertsViewAll.setBackgroundColor(new Color().alpha(0));
                expertsViewIncome.setBackgroundResource(R.color.white);
                expertsViewMizhong.setBackgroundColor(new Color().alpha(0));
                kind = 1;
                choiceFragment(kind);
                break;
            case R.id.experts_tv_mizhong:
                //命中帮
//                expertsTvAll.setTextColor(getResources().getColor(R.color.gray));
//                expertsTvIncome.setTextColor(getResources().getColor(R.color.gray));
//                expertsTvMizhong.setTextColor(getResources().getColor(R.color.blue));
                expertsViewAll.setBackgroundColor(new Color().alpha(0));
                expertsViewIncome.setBackgroundColor(new Color().alpha(0));
                expertsViewMizhong.setBackgroundResource(R.color.white);
                kind = 2;
                choiceFragment(kind);
                break;

        }
    }

    public void choiceFragment(int flags) {
        transcation = manager.beginTransaction();
        ExpertsDetailFragment allFragment = new ExpertsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("KIND", flags);
        allFragment.setArguments(bundle);
        transcation.replace(R.id.experts_ll, allFragment).commit();
    }

    /**
     * 初始化各控件
     */
    private void initControls() {
        list_fragment = new ArrayList<>();
        //初始化各fragment
        for (int i = 0; i < 3; i++) {
            Fragment fragment = new ExpertsDetailFragment();
            //将fragment装进列表中
            Bundle bundle = new Bundle();
            bundle.putInt("KIND", i);
            fragment.setArguments(bundle);
            list_fragment.add(fragment);
        }
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("全部");
        list_title.add("周盈利榜");
        list_title.add("周命中榜");
//        list_title.add("已兑换");
        //设置TabLayout的模式
//        expertsTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        expertsTablayout.setTabMode(TabLayout.MODE_FIXED);
        fAdapter = new ViewPageAdapter(getChildFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        expertsViewpager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        expertsTablayout.setupWithViewPager(expertsViewpager);
        //为TabLayout添加tab名称
        /* for (int i = 0; i < 4; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(list_title.get(i)));
        }*/
        initTable();
    }

    private void initTable() {
        for (int i = 0; i < fAdapter.getCount(); i++) {
            TabLayout.Tab tab = expertsTablayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.item_tablayout);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.tv_tableitem).setSelected(true);//第一个tab被选中
                tab.getCustomView().findViewById(R.id.line_tableitem1).setVisibility(View.VISIBLE);
                tab.getCustomView().findViewById(R.id.line_tableitem2).setVisibility(View.VISIBLE);
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tv_tableitem);
            textView.setText(list_title.get(i));//设置tab上的文字
        }
        expertsTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_tableitem).setSelected(true);
                tab.getCustomView().findViewById(R.id.line_tableitem1).setVisibility(View.VISIBLE);
                tab.getCustomView().findViewById(R.id.line_tableitem2).setVisibility(View.VISIBLE);
                expertsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_tableitem).setSelected(false);
                tab.getCustomView().findViewById(R.id.line_tableitem1).setVisibility(View.GONE);
                tab.getCustomView().findViewById(R.id.line_tableitem2).setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
