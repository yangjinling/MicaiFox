package com.micai.fox.activity;

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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.ViewPageAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.fragment.ExpertsReportFragment;
import com.micai.fox.fragment.ExpertsZhongChouFragment;
import com.micai.fox.parambean.BotomBean;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.ExpertsDetailResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.micai.fox.view.CustomViewPager;
import com.micai.fox.view.PageListScrollView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/*专家详情页面*/
public class ExpertsDetailActivity extends AppCompatActivity implements PageListScrollView.OnScrollToBottomListener {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.experts_detail_iv_head)
    ImageView head;
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
    PageListScrollView scrollView;
    @Bind(R.id.experts_detail_moveview)
    LinearLayout expertsDetailMoveview;
    @Bind(R.id.experts_detail_parentcontent)
    LinearLayout expertsDetailParentcontent;
    @Bind(R.id.xuantingquyu)
    LinearLayout xuantingquyu;
    @Bind(R.id.experts_detail_tablayout)
    TabLayout expertsDetailTablayout;
    @Bind(R.id.experts_detail_viewpager)
    CustomViewPager expertsDetailViewpager;
    @Bind(R.id.experts_detail_tv_count)
    TextView expertsDetailTvCount;
    private Fragment[] mFragments;
    private FragmentManager mManager;
    private FragmentTransaction transcation;
    private int kind = 0;
    private Handler mHandler = new Handler();
    Runnable scrollViewRunable = new Runnable() {
        @Override
        public void run() {
            scrollView.smoothScrollTo(0, 0);
        }
    };
    private FragmentPagerAdapter fAdapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_title; //tab名称列表
    String proId;
    private ExpertsDetailResultBean expertsDetailResultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_experts);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setText("专家详情");
/*        initFragments();
//        initLinearLayout();
        switchFragment(mFragments[0]);
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                LogUtil.e("YJL", "悬停");
                scrollView.setXuantingquyu(xuantingquyu, expertsDetailParentcontent, expertsDetailMoveview);
                scrollView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            }
        });*/
        proId = getIntent().getStringExtra("proId");
        getExpertsDetail(proId);
        initControls();
//        mHandler.post(scrollViewRunable);
        expertsDetailViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                expertsDetailViewpager.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        expertsDetailViewpager.resetHeight(0);
        expertsDetailViewpager.setOffscreenPageLimit(0);
        scrollView.setOnScrollToBottomListener(this);
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
//                FragmentTransaction transaction1 = mManager.beginTransaction();
//                transaction1.replace(R.id.experts_detail_ll, mFragments[0]).commit();
                switchFragment(mFragments[0]);
                break;
            case R.id.experts_detail_tv_report:
                expertsDetailLineReport.setBackgroundResource(R.color.white);
                expertsDetailLineZhongchou.setBackgroundColor(new Color().alpha(0));
//                FragmentTransaction transaction2 = mManager.beginTransaction();
//                transaction2.replace(R.id.experts_detail_ll, mFragments[1]).commit();
                switchFragment(mFragments[1]);
                break;
        }
    }

    private Fragment currentFragment = new Fragment();

    //正确的做法
    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.experts_detail_ll, targetFragment)
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

    /**
     * 初始化各控件
     */
    private void initControls() {
        list_fragment = new ArrayList<>();
        //初始化各fragment
  /*      for (int i = 0; i < 2; i++) {
            Fragment fragment = new ExpertsZhongChouFragment(expertsDetailViewpager);
            //将fragment装进列表中
            Bundle bundle = new Bundle();
            bundle.putInt("KIND", i);
            fragment.setArguments(bundle);
            list_fragment.add(fragment);
        }*/
        Bundle bundle;
        bundle = new Bundle();
        bundle.putString("proId", proId);
        ExpertsZhongChouFragment expertsZhongChouFragment = new ExpertsZhongChouFragment(expertsDetailViewpager);
        expertsZhongChouFragment.setArguments(bundle);
        list_fragment.add(expertsZhongChouFragment);
        ExpertsReportFragment expertsReportFragment = new ExpertsReportFragment(expertsDetailViewpager);
        expertsReportFragment.setArguments(bundle);
        list_fragment.add(expertsReportFragment);
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("众筹");
        list_title.add("报告");
//        list_title.add("已兑换");
        //设置TabLayout的模式
//        expertsTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        expertsDetailTablayout.setTabMode(TabLayout.MODE_FIXED);
        fAdapter = new ViewPageAdapter(getSupportFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        expertsDetailViewpager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        expertsDetailTablayout.setupWithViewPager(expertsDetailViewpager);
        //为TabLayout添加tab名称
        /* for (int i = 0; i < 4; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(list_title.get(i)));
        }*/
        initTable();
    }

    private void initTable() {
        for (int i = 0; i < fAdapter.getCount(); i++) {
            TabLayout.Tab tab = expertsDetailTablayout.getTabAt(i);//获得每一个tab
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
        expertsDetailTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_tableitem).setSelected(true);
                tab.getCustomView().findViewById(R.id.line_tableitem1).setVisibility(View.VISIBLE);
                tab.getCustomView().findViewById(R.id.line_tableitem2).setVisibility(View.VISIBLE);
                expertsDetailViewpager.setCurrentItem(tab.getPosition());
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

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void getExpertsDetail(String proId) {
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setProId(proId);
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_EXPERTS_DETAIL)
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "专家详情-data" + response);
                if (Tools.isGoodJson(response)) {
                    expertsDetailResultBean = new Gson().fromJson(response, ExpertsDetailResultBean.class);
                    if (expertsDetailResultBean.isExecResult()) {
                        expertsDetailTvName.setText(expertsDetailResultBean.getExecDatas().getProfessor().getProName());
                        expertsDetailTvIntroduce.setText(expertsDetailResultBean.getExecDatas().getProfessor().getProAuth());
                        expertsDetailTvContent.setText(expertsDetailResultBean.getExecDatas().getProfessor().getSelfIntro());
                        expertsDetailTvRate.setText("" + expertsDetailResultBean.getExecDatas().getProfessor().getHitRate());
                        expertsDetailTvYili.setText("" + expertsDetailResultBean.getExecDatas().getProfessor().getProfitRate());
                        expertsDetailTvMaxyili.setText("" + expertsDetailResultBean.getExecDatas().getProfessor().getMaxProfitRate());
                        expertsDetailTvCount.setText("共" + expertsDetailResultBean.getExecDatas().getProfessor().getTotalNum() + "次众筹");
                        Glide.with(ExpertsDetailActivity.this).load(Url.WEB_BASE_IP + expertsDetailResultBean.getExecDatas().getProfessor().getProPhoto()).asBitmap().placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round).into(head);
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


    @Override
    public void onScrollBottomListener(boolean isBottom) {
        LogUtil.e("YJL","专家isBottom=="+isBottom);
        BotomBean botomBean = new BotomBean();
        botomBean.setBootom(isBottom);
        EventBus.getDefault().post(botomBean);
    }
}
