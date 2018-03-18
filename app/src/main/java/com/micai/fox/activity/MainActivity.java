package com.micai.fox.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.fragment.ExpertsFragment;
import com.micai.fox.fragment.HomeFragment;
import com.micai.fox.fragment.MineFragmnet;
import com.micai.fox.parambean.NotiBean;
import com.micai.fox.util.ExitAppUtils;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.PrefUtils;
import com.micai.fox.view.CyDownProgressView;
import com.zhy.http.okhttp.callback.DialogInShow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*主界面*/
public class MainActivity extends BaseActivity {
    @Bind(R.id.main_fragment)
    FrameLayout fragmentlayout;
    @Bind(R.id.main_rg)
    LinearLayout mainRg;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_experts)
    RadioButton rbExperts;
    @Bind(R.id.rb_mine)
    RadioButton rbMine;
    @Bind(R.id.home_mine_point)
    ImageView homeMinePoint;


    private Fragment[] mFragments;
    private FragmentManager mManager;
    private Handler handler = new Handler();
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ExitAppUtils.getInstance().addActivity(this);
        //初始化fragment
        initFragments();
        type = getIntent().getIntExtra("TYPE", 0);
        //初始化布局
        initLinearLayout(type);
    }


    private void initFragments() {
        mFragments = new Fragment[3];
        mFragments[0] = new HomeFragment();
        mFragments[1] = new ExpertsFragment();
        mFragments[2] = new MineFragmnet();
    }

    private Fragment currentFragment = new Fragment();

    //正确的做法
    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.main_fragment, targetFragment)
                    .commit();
            System.out.println("还没添加呢");
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
            System.out.println("添加了( ⊙o⊙ )哇");
        }
        currentFragment = targetFragment;
    }

    private void initLinearLayout(int type) {
        //导航
        // mMainTabBar.setOnClickListener(this);
        Bundle bundle = new Bundle();
        mManager = getSupportFragmentManager();
        if (type == 1) {
            //众筹过来的
            if (!Config.getInstance().isSet()) {
                bundle.putBoolean("LOGIN", true);
                mFragments[2].setArguments(bundle);
                Config.getInstance().setSet(true);
            }
            rbHome.setChecked(false);
            rbExperts.setChecked(false);
            rbMine.setChecked(true);
            mManager.beginTransaction().replace(R.id.main_fragment, mFragments[2]).commit();
            currentFragment = mFragments[2];
        } else if (type == 2) {
            //设置退出
            if (!Config.getInstance().isSet()) {
                bundle.putBoolean("LOGIN", false);
                mFragments[2].setArguments(bundle);
                Config.getInstance().setSet(true);
            }
            homeMinePoint.setVisibility(View.GONE);
            rbHome.setChecked(false);
            rbExperts.setChecked(false);
            rbMine.setChecked(true);
            mManager.beginTransaction().replace(R.id.main_fragment, mFragments[2]).commit();
            currentFragment = mFragments[2];
        } else {
            mManager.beginTransaction().replace(R.id.main_fragment, mFragments[0]).commit();
            currentFragment = mFragments[0];
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rb_home, R.id.rb_experts, R.id.rb_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
//                homeMinePoint.setVisibility(View.GONE);
                rbHome.setChecked(true);
                rbExperts.setChecked(false);
                rbMine.setChecked(false);
//                FragmentTransaction transaction1 = mManager.beginTransaction();
//                transaction1.replace(R.id.main_fragment, mFragments[0]).commit();
                switchFragment(mFragments[0]);
                break;
            case R.id.rb_experts:
//                homeMinePoint.setVisibility(View.GONE);
                rbHome.setChecked(false);
                rbExperts.setChecked(true);
                rbMine.setChecked(false);
//                FragmentTransaction transaction2 = mManager.beginTransaction();
//                transaction2.replace(R.id.main_fragment, mFragments[1]).commit();
                switchFragment(mFragments[1]);
                break;
            case R.id.rb_mine:
//                homeMinePoint.setVisibility(View.GONE);
                rbHome.setChecked(false);
                rbExperts.setChecked(false);
                rbMine.setChecked(true);
                Bundle bundle = new Bundle();
                if (null == PrefUtils.getString(Config.getInstance().getmContext(), "SESSIONID", null) || TextUtils.isEmpty(PrefUtils.getString(Config.getInstance().getmContext(), "SESSIONID", ""))) {
                    bundle.putBoolean("LOGIN", false);
                } else {
                    bundle.putBoolean("LOGIN", true);
                }
                if (!Config.getInstance().isSet()) {
                    mFragments[2].setArguments(bundle);
                    Config.getInstance().setSet(true);
                }
//                FragmentTransaction transaction3 = mManager.beginTransaction();
//                transaction3.replace(R.id.main_fragment, mFragments[2]).commit();
                switchFragment(mFragments[2]);
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(NotiBean bean) {
        LogUtil.e("eventbus", "" + bean.isHaveN());

        if (bean.isHaveN()) {
            if (null == PrefUtils.getString(Config.getInstance().getmContext(), "SESSIONID", null) || TextUtils.isEmpty(PrefUtils.getString(Config.getInstance().getmContext(), "SESSIONID", ""))) {
                Config.getInstance().setLoginAndNo(true);
                handler.post(runnableUis);
            } else {
                Config.getInstance().setLoginAndNo(true);
                Config.getInstance().setNoti(true);
                handler.post(runnableUi);
            }

        } else {
            Config.getInstance().setNoti(false);
            handler.post(runnableUis);
        }
    }

    // 构建Runnable对象，在runnable中更新界面
    Runnable runnableUi = new Runnable() {
        @Override
        public void run() {
//更新界面
            homeMinePoint.setVisibility(View.VISIBLE);
        }

    };
    // 构建Runnable对象，在runnable中更新界面
    Runnable runnableUis = new Runnable() {
        @Override
        public void run() {
//更新界面
            homeMinePoint.setVisibility(View.GONE);
        }

    };
}
