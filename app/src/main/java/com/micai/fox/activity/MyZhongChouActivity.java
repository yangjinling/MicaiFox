package com.micai.fox.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.adapter.ViewPageAdapter;
import com.micai.fox.fragment.MyZhongChouFragment;
import com.micai.fox.parambean.RreshBean;
import com.micai.fox.parambean.ZhongChouRefreshBean;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*我的众筹*/
public class MyZhongChouActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.tablayout)
    TabLayout tabLayout;
    @Bind(R.id.mine_zhongchou_viewpager)
    ViewPager mineZhongchouViewpager;


    private FragmentPagerAdapter fAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zhong_chou);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText("我的众筹");
        initControls();
    }

    @OnClick(R.id.tv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    /**
     * 初始化各控件
     */
    private void initControls() {
        list_fragment = new ArrayList<>();
        //初始化各fragment
        for (int i = 0; i < 4; i++) {
            Fragment fragment = new MyZhongChouFragment();
            //将fragment装进列表中
            Bundle bundle = new Bundle();
            bundle.putInt("KIND", i);
            fragment.setArguments(bundle);
            list_fragment.add(fragment);
        }
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("全部");
        list_title.add("待支付");
        list_title.add("已支付");
        list_title.add("已兑付");
        //设置TabLayout的模式
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        fAdapter = new ViewPageAdapter(getSupportFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        mineZhongchouViewpager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(mineZhongchouViewpager);
        //为TabLayout添加tab名称
        /* for (int i = 0; i < 4; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(list_title.get(i)));
        }*/
        initTable();
    }

    private void initTable() {
        for (int i = 0; i < fAdapter.getCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.item_tablayout);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.tv_tableitem).setSelected(true);//第一个tab被选中
                tab.getCustomView().findViewById(R.id.line_tableitem).setVisibility(View.VISIBLE);
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tv_tableitem);
            textView.setText(list_title.get(i));//设置tab上的文字
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_tableitem).setSelected(true);
                tab.getCustomView().findViewById(R.id.line_tableitem).setVisibility(View.VISIBLE);
                mineZhongchouViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_tableitem).setSelected(false);
                tab.getCustomView().findViewById(R.id.line_tableitem).setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
