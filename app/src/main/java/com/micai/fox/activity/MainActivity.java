package com.micai.fox.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

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


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
