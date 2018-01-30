package com.micai.fox.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.micai.fox.R;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.fragment.ExpertsFragment;
import com.micai.fox.fragment.HomeFragment;
import com.micai.fox.fragment.MineFragmnet;
import com.micai.fox.util.ExitAppUtils;
import com.micai.fox.view.CyDownProgressView;
import com.zhy.http.okhttp.callback.DialogInShow;

import org.greenrobot.eventbus.EventBus;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ExitAppUtils.getInstance().addActivity(this);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rb_home, R.id.rb_experts, R.id.rb_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                homeMinePoint.setVisibility(View.GONE);
                rbHome.setChecked(true);
                rbExperts.setChecked(false);
                rbMine.setChecked(false);
                FragmentTransaction transaction1 = mManager.beginTransaction();
                transaction1.replace(R.id.main_fragment, mFragments[0]).commit();
                break;
            case R.id.rb_experts:
                homeMinePoint.setVisibility(View.GONE);
                rbHome.setChecked(false);
                rbExperts.setChecked(true);
                rbMine.setChecked(false);
                FragmentTransaction transaction2 = mManager.beginTransaction();
                transaction2.replace(R.id.main_fragment, mFragments[1]).commit();
                break;
            case R.id.rb_mine:
                homeMinePoint.setVisibility(View.VISIBLE);
                rbHome.setChecked(false);
                rbExperts.setChecked(false);
                rbMine.setChecked(true);
                FragmentTransaction transaction3 = mManager.beginTransaction();
                transaction3.replace(R.id.main_fragment, mFragments[2]).commit();
                break;
        }
    }
}
