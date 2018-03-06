package com.micai.fox.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.ViewPageAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.fragment.ZhouChouDetailIntroduceFragment;
import com.micai.fox.fragment.ZhouChouDetailPilutFragment;
import com.micai.fox.fragment.ZhouChouDetailReportFragment;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.ZhongChouDetailResultBean;
import com.micai.fox.util.DateUtil;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.micai.fox.view.CustomViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.MediaType;

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
    @Bind(R.id.zhongchou_detail_tablayout)
    TabLayout zhongchouDetailTablayout;
    @Bind(R.id.zhongchou_detail_viewpager)
    CustomViewPager zhongchouDetailViewpager;
    @Bind(R.id.zhongchou_detail_tv_name)
    TextView zhongchouDetailTvName;
    @Bind(R.id.zhongchou_detail_tv_introduce)
    TextView zhongchouDetailTvIntroduce;
    @Bind(R.id.zhongchou_detail_tv_rate)
    TextView zhongchouDetailTvRate;
    @Bind(R.id.zhongchou_detail_tv_mubiao)
    TextView zhongchouDetailTvMubiao;
    @Bind(R.id.zhongchou_detail_tv_have)
    TextView zhongchouDetailTvHave;
    @Bind(R.id.zhongchou_detail_tv_people)
    TextView zhongchouDetailTvPeople;
    @Bind(R.id.zhongchou_detail_tv_state)
    TextView zhongchouDetailTvState;
    @Bind(R.id.zhongchou_detail_prograss1)
    ProgressBar zhongchouDetailPrograss1;
    @Bind(R.id.zhongchou_detail_prograss2)
    ProgressBar zhongchouDetailPrograss2;
    @Bind(R.id.zhongchou_detail_ll_rate)
    LinearLayout zhongchouDetailLlRate;
    @Bind(R.id.zhongchou_detail_ll_experts)
    LinearLayout zhongchouDetailLlExperts;
    @Bind(R.id.zhongchou_detail_iv_head)
    CircleImageView head;
    private Fragment[] mFragments;
    private FragmentManager mManager;
    private FragmentTransaction transcation;
    private int kind = 0;
    private Handler mHandler = new Handler();
    private FragmentPagerAdapter fAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表
    private String crowdingId;
    private ZhongChouDetailResultBean zhongChouDetailResultBean;
    private String status;

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
        crowdingId = getIntent().getStringExtra("crowdingId");
        status = getIntent().getStringExtra("status");
//        LogUtil.e("YJL", "众筹状态" + status);
        getZhongChouDetail(crowdingId);

        zhongchouDetailViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                zhongchouDetailViewpager.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        zhongchouDetailViewpager.resetHeight(0);
    }

    private void initViewData() {
        switch (status) {
            case "0"://未开始
                zhongchouDetailTvState.setText("预热中");
                zhongchouDetailPrograss1.setProgress(0);
                btnZhongchouDetailPay.setText("尚未开始");
                btnZhongchouDetailPay.setClickable(false);
//                yyyy.mm.dd或已筹金额达￥XXX时结束众筹
                zhongchouDetailTv1.setText("" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getEndDate()) + "或已筹金额达￥" + zhongChouDetailResultBean.getExecDatas().getAmountDown() + "时结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getCashDate()) + "起开始兑付");
                break;
            case "1":
                //进行中
                zhongchouDetailTvState.setText("众筹中");
                btnZhongchouDetailPay.setText("去支持(￥" + zhongChouDetailResultBean.getExecDatas().getOneAmountDown() + ")起");
                btnZhongchouDetailPay.setBackgroundResource(R.drawable.buttonstyle);
                btnZhongchouDetailPay.setClickable(true);
                zhongchouDetailTv1.setText("" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getEndDate()) + "或已筹金额达￥" + zhongChouDetailResultBean.getExecDatas().getAmountDown() + "时结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getCashDate()) + "起开始兑付");
                break;
            case "2":
            case "3":
            case "4":
                //已结束
                zhongchouDetailTvState.setText("已结束");
                btnZhongchouDetailPay.setText("项目已结束");
                btnZhongchouDetailPay.setClickable(false);
                zhongchouDetailTv1.setText("已于" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getEndDate()) + "结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getCashDate()) + "起已兑付");
                break;
            case "5":
            case "6":
                //已披露
                zhongchouDetailTvState.setText("已披露");
                btnZhongchouDetailPay.setText("项目已结束");
                btnZhongchouDetailPay.setClickable(false);
                zhongchouDetailTv1.setText("已于" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getEndDate()) + "结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getCashDate()) + "起已兑付");
                break;
            case "7":
                //已兑付
                zhongchouDetailTvState.setText("已兑付");
                zhongchouDetailLlRate.setVisibility(View.VISIBLE);
                zhongchouDetailTvRate.setText("" + zhongChouDetailResultBean.getExecDatas().getHitRate());
                btnZhongchouDetailPay.setText("项目已结束");
                btnZhongchouDetailPay.setClickable(false);
                zhongchouDetailTv1.setText("已于" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getEndDate()) + "结束众筹");
                zhongchouDetailTv2.setText("" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getCashDate()) + "起已兑付");
                break;
            case "9"://流标
                zhongchouDetailTvState.setText("已结束");
                zhongchouDetailPrograss1.setVisibility(View.GONE);
                zhongchouDetailPrograss2.setVisibility(View.VISIBLE);
                btnZhongchouDetailPay.setText("项目已结束");
                btnZhongchouDetailPay.setClickable(false);
                zhongchouDetailTv1.setText("已于" + DateUtil.getDateToStrings(zhongChouDetailResultBean.getExecDatas().getEndDate()) + "结束众筹");
                zhongchouDetailTv2.setText("项目未达成，支付金额原路退回。");
                break;
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

    @OnClick({R.id.zhongchou_detail_ll_experts, R.id.btn_zhongchou_detail_pay, R.id.tv_back, R.id.zhongchou_detail_tv_xiangmu, R.id.zhongchou_detail_tv_report, R.id.zhongchou_detail_tv_pilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhongchou_detail_ll_experts:
                Intent intent = new Intent(this, ExpertsDetailActivity.class);
                intent.putExtra("proId", zhongChouDetailResultBean.getExecDatas().getProId());
                startActivity(intent);
                break;
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
                Intent intents = new Intent(ZhongChouDetailActivity.this, BuyZhongChouActivity.class);
                intents.putExtra("BEAN", zhongChouDetailResultBean);
                startActivity(intents);
                break;
        }
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getZhongChouDetail(String crowdfundingId) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setCrowdfundingId(crowdfundingId);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_HOME_ZHONGCHOU_DETAIL, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                LogUtil.e("yjl", "众筹详情-data" + response);
                if (Tools.isGoodJson(response)) {
                    zhongChouDetailResultBean = new Gson().fromJson(response, ZhongChouDetailResultBean.class);
                    if (zhongChouDetailResultBean.isExecResult()) {
                        status = "" + zhongChouDetailResultBean.getExecDatas().getStatus();
                        if (null != status) {
                            if (status.equals("5") || status.equals("6") || status.equals("7")) {
                                //5/6：已披露  7：已兑付
                                initControls(1);
                            } else {
                                initControls(0);
                            }
                        }
                        if (null != status)
                            initViewData();
                        Glide.with(ZhongChouDetailActivity.this).load(Url.WEB_BASE_IP + zhongChouDetailResultBean.getExecDatas().getProPhoto()).asBitmap().placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round).into(head);
                        zhongchouDetailTvTalk.setText("" + zhongChouDetailResultBean.getExecDatas().getTitle());
                        zhongchouDetailTvName.setText("" + zhongChouDetailResultBean.getExecDatas().getProName());
                        zhongchouDetailTvIntroduce.setText("" + zhongChouDetailResultBean.getExecDatas().getProAuth());
                        zhongchouDetailTvMubiao.setText("" + zhongChouDetailResultBean.getExecDatas().getAmountDown());
                        zhongchouDetailTvHave.setText("" + zhongChouDetailResultBean.getExecDatas().getRealAmount());
                        zhongchouDetailTvPeople.setText("" + zhongChouDetailResultBean.getExecDatas().getSupNum());
//                        zhongchouDetailTv1.setText("" + zhongChouDetailResultBean.getExecDatas().getRemarks());
//                        zhongchouDetailTv2.setText("" + zhongChouDetailResultBean.getExecDatas().getRemarks());
                        zhongchouDetailTvRate.setText("" + zhongChouDetailResultBean.getExecDatas().getHitRate());
                        BigDecimal score = null;
                        try {
                            score = Tools.div(zhongChouDetailResultBean.getExecDatas().getRealAmount(), zhongChouDetailResultBean.getExecDatas().getAmountDown(), 0);
                            score = score.multiply(new BigDecimal(100));
                            LogUtil.e("YJL", "score==" + score);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        if ("9".equals(status)) {
                            //流标
                            zhongchouDetailPrograss2.setProgress(score.intValue());
                        } else {
                            zhongchouDetailPrograss1.setProgress(score.intValue());
                        }
                        if (!"0".equals(status))
                            homeZhongTvState.setText("" + score.intValue() + "%");
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 初始化各控件
     */
    private void initControls(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("KIND", type);
        bundle.putString("crowdingId", crowdingId);
        list_fragment = new ArrayList<>();
        //初始化各fragment
        list_fragment.add(new ZhouChouDetailIntroduceFragment(zhongchouDetailViewpager));
        list_fragment.add(new ZhouChouDetailReportFragment(zhongchouDetailViewpager));
        list_fragment.add(new ZhouChouDetailPilutFragment(zhongchouDetailViewpager));
        list_fragment.get(1).setArguments(bundle);
        list_fragment.get(2).setArguments(bundle);
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("项目说明");
        list_title.add("相关报告");
        list_title.add("披露信息");
//        list_title.add("已兑换");
        //设置TabLayout的模式
//        expertsTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        zhongchouDetailTablayout.setTabMode(TabLayout.MODE_FIXED);
        fAdapter = new ViewPageAdapter(getSupportFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        zhongchouDetailViewpager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        zhongchouDetailTablayout.setupWithViewPager(zhongchouDetailViewpager);
        //为TabLayout添加tab名称
        /* for (int i = 0; i < 4; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(list_title.get(i)));
        }*/
//        zhongchouDetailViewpager.setObjectForPosition(list_fragment.get(1).getView(), 1);
        initTable();
    }

    private void initTable() {
        for (int i = 0; i < fAdapter.getCount(); i++) {
            TabLayout.Tab tab = zhongchouDetailTablayout.getTabAt(i);//获得每一个tab
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
        zhongchouDetailTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_tableitem).setSelected(true);
                tab.getCustomView().findViewById(R.id.line_tableitem1).setVisibility(View.VISIBLE);
                tab.getCustomView().findViewById(R.id.line_tableitem2).setVisibility(View.VISIBLE);
                zhongchouDetailViewpager.setCurrentItem(tab.getPosition());
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

}
