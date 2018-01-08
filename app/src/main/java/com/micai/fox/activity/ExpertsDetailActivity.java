package com.micai.fox.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.fragment.ExpertsReportFragment;
import com.micai.fox.fragment.ExpertsZhongChouFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*专家详情页面*/
public class ExpertsDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.experts_detail_iv_head)
    ImageView expertsDetailIvHead;
    @Bind(R.id.experts_detail_tv_name)
    TextView expertsDetailTvName;
    @Bind(R.id.experts_detail_tv_introduce)
    TextView expertsDetailTvIntroduce;
    @Bind(R.id.experts_detail_tv_rate)
    TextView expertsDetailTvRate;
    @Bind(R.id.experts_detail_tv_content)
    TextView expertsDetailTvContent;
    @Bind(R.id.experts_detail_tv_yili)
    TextView expertsDetailTvYili;
    @Bind(R.id.experts_detail_tv_maxyili)
    TextView expertsDetailTvMaxyili;
    @Bind(R.id.experts_detail_tv_zhongchou)
    TextView expertsDetailTvZhongchou;
    @Bind(R.id.experts_detail_tv_report)
    TextView expertsDetailTvReport;
    @Bind(R.id.experts_detail_line_zhongchou)
    View expertsDetailLineZhongchou;
    @Bind(R.id.experts_detail_line_report)
    View expertsDetailLineReport;
    @Bind(R.id.experts_detail_ll)
    LinearLayout expertsDetailLl;
    @Bind(R.id.experts_detail_scroll)
    ScrollView scrollView;
    private Fragment[] mFragments;
    private FragmentManager mManager;
    private FragmentTransaction transcation;
    private int kind = 0;
    private Handler mHandler = new Handler();
    Runnable scrollViewRunable = new Runnable() {
        @Override
        public void run() {
            scrollView.scrollTo(0, 20);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_experts);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText("专家详情");
        initFragments();
        initLinearLayout();
        mHandler.postDelayed(scrollViewRunable, 1);
    }

    @OnClick({R.id.tv_back, R.id.experts_detail_tv_zhongchou, R.id.experts_detail_tv_report})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.experts_detail_tv_zhongchou:
                expertsDetailLineZhongchou.setBackgroundResource(R.color.white);
                expertsDetailLineReport.setBackgroundColor(new Color().alpha(0));
                kind = 0;
                FragmentTransaction transaction1 = mManager.beginTransaction();
                transaction1.replace(R.id.experts_detail_ll, mFragments[0]).commit();
                break;
            case R.id.experts_detail_tv_report:
                expertsDetailLineReport.setBackgroundResource(R.color.white);
                expertsDetailLineZhongchou.setBackgroundColor(new Color().alpha(0));
                FragmentTransaction transaction2 = mManager.beginTransaction();
                transaction2.replace(R.id.experts_detail_ll, mFragments[1]).commit();
                break;
        }
    }

    private void initFragments() {
        mFragments = new Fragment[2];
        mFragments[0] = new ExpertsZhongChouFragment();
        mFragments[1] = new ExpertsReportFragment();
    }

    private void initLinearLayout() {
        //导航
        // mMainTabBar.setOnClickListener(this);
        mManager = getSupportFragmentManager();
        mManager.beginTransaction().replace(R.id.experts_detail_ll, mFragments[0]).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
