package com.micai.fox.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.fragment.ZhouChouDetailIntroduceFragment;
import com.micai.fox.fragment.ZhouChouDetailPilutFragment;
import com.micai.fox.fragment.ZhouChouDetailReportFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*众筹详情页面*/
public class ZhongChouDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.zhongchou_detail_tv_talk)
    TextView zhongchouDetailTvTalk;
    @Bind(R.id.zhongchou_detail_tv1)
    TextView zhongchouDetailTv1;
    @Bind(R.id.zhongchou_detail_tv2)
    TextView zhongchouDetailTv2;
    @Bind(R.id.zhongchou_detail_tv_xiangmu)
    TextView zhongchouDetailTvXiangmu;
    @Bind(R.id.zhongchou_detail_tv_report)
    TextView zhongchouDetailTvReport;
    @Bind(R.id.zhongchou_detail_tv_pilu)
    TextView zhongchouDetailTvPilu;
    @Bind(R.id.zhongchou_detail_view_xiangmu)
    View zhongchouDetailViewXiangmu;
    @Bind(R.id.zhongchou_detail_view_report)
    View zhongchouDetailViewReport;
    @Bind(R.id.zhongchou_detail_view_pilu)
    View zhongchouDetailViewPilu;
    @Bind(R.id.zhongchou_detail_ll)
    LinearLayout zhongchouDetailLl;
    @Bind(R.id.home_zhong_tv_state)
    TextView homeZhongTvState;
    @Bind(R.id.btn_zhongchou_detail_pay)
    Button btnZhongchouDetailPay;
    private Fragment[] mFragments;
    private FragmentManager mManager;
    private FragmentTransaction transcation;
    private int kind = 0;
    private Handler mHandler = new Handler();

    //    Runnable scrollViewRunable = new Runnable() {
//        @Override
//        public void run() {
//            scrollView.scrollTo(0, 0);
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_chou_detail);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText("众筹详情");
        initTalk(0);
        initFragments(0);
        switchFragment(mFragments[0]);
    }

    private void initTalk(int type) {
        if (type == 0) {
            SpannableString spannableString = new SpannableString(" 预热中 原有的文字大小的基础上，相对设置文字大小，实现方法如下：");
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ffffff"));
            spannableString.setSpan(colorSpan, 0, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            BackgroundColorSpan colorSpans = new BackgroundColorSpan(Color.parseColor("#FFEC7C64"));
            spannableString.setSpan(colorSpans, 0, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1.2f);
            spannableString.setSpan(sizeSpan, 4, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            zhongchouDetailTvTalk.setText(spannableString);
        }
    }

    private void initFragments(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("KIND", type);
        mFragments = new Fragment[3];
        mFragments[0] = new ZhouChouDetailIntroduceFragment();
        mFragments[1] = new ZhouChouDetailReportFragment();
        mFragments[1].setArguments(bundle);
        mFragments[2] = new ZhouChouDetailPilutFragment();
        mFragments[2].setArguments(bundle);
    }

    private Fragment currentFragment = new Fragment();

    //正确的做法
    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.zhongchou_detail_ll, targetFragment)
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

    @OnClick({R.id.btn_zhongchou_detail_pay, R.id.tv_back, R.id.zhongchou_detail_tv_xiangmu, R.id.zhongchou_detail_tv_report, R.id.zhongchou_detail_tv_pilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.zhongchou_detail_tv_xiangmu:
                zhongchouDetailViewXiangmu.setBackgroundResource(R.color.white);
                zhongchouDetailViewReport.setBackgroundColor(new Color().alpha(0));
                zhongchouDetailViewPilu.setBackgroundColor(new Color().alpha(0));
                switchFragment(mFragments[0]);
                break;
            case R.id.zhongchou_detail_tv_report:
                zhongchouDetailViewReport.setBackgroundResource(R.color.white);
                zhongchouDetailViewPilu.setBackgroundColor(new Color().alpha(0));
                zhongchouDetailViewXiangmu.setBackgroundColor(new Color().alpha(0));
                switchFragment(mFragments[1]);
                break;
            case R.id.zhongchou_detail_tv_pilu:
                zhongchouDetailViewPilu.setBackgroundResource(R.color.white);
                zhongchouDetailViewXiangmu.setBackgroundColor(new Color().alpha(0));
                zhongchouDetailViewReport.setBackgroundColor(new Color().alpha(0));
                switchFragment(mFragments[2]);
                break;
            case R.id.btn_zhongchou_detail_pay:
                Intent intent = new Intent(ZhongChouDetailActivity.this, BuyZhongChouActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
