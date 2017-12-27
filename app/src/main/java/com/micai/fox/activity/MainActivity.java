package com.micai.fox.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.micai.fox.R;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.fragment.ExpertsFragment;
import com.micai.fox.fragment.HomeFragment;
import com.micai.fox.fragment.MineFragmnet;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Bind(R.id.main_fragment)
    FrameLayout fragmentlayout;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_experts)
    RadioButton rbExperts;
    @Bind(R.id.rb_mine)
    RadioButton rbMine;


    private Fragment[] mFragments;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化fragment
        initFragments();
        //初始化布局
        initLinearLayout();
    }


    private void initFragments() {
        mFragments = new Fragment[3];
        mFragments[0] = new HomeFragment();
        mFragments[1] = new ExpertsFragment();
        mFragments[2] = new MineFragmnet();
    }


    private void initLinearLayout() {
        //导航
        // mMainTabBar.setOnClickListener(this);
        mManager = getSupportFragmentManager();
        mManager.beginTransaction().replace(R.id.main_fragment, mFragments[0]).commit();
//        FragmentTransaction lTransaction = mManager.beginTransaction();
//        lTransaction.replace(R.id.main_fragment_container, mFragments[2]).commit();
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_home:
                        //首页
                        FragmentTransaction transaction1 = mManager.beginTransaction();
                        transaction1.replace(R.id.main_fragment, mFragments[0]).commit();
                        break;
                    case R.id.rb_experts:
                        //专家
                        FragmentTransaction transaction2 = mManager.beginTransaction();
                        transaction2.replace(R.id.main_fragment, mFragments[1]).commit();
                        break;
                    case R.id.rb_mine:
                        //我的
                        FragmentTransaction transaction3 = mManager.beginTransaction();
                        transaction3.replace(R.id.main_fragment, mFragments[2]).commit();
                        break;
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
