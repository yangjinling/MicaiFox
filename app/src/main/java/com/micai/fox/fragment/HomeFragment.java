package com.micai.fox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.activity.ExpertsDetailActivity;
import com.micai.fox.activity.ReportDetailActivity;
import com.micai.fox.activity.WebActivity;
import com.micai.fox.activity.ZhongChouDetailActivity;
import com.micai.fox.adapter.MyExpertsListAdapter;
import com.micai.fox.adapter.MyHomeZhongChouAdapter;
import com.micai.fox.adapter.MyRecycleHAdapter;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.BotomBean;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.resultbean.BaseResultBean;
import com.micai.fox.resultbean.ExpertsResultBean;
import com.micai.fox.resultbean.HomeResultBean;
import com.micai.fox.resultbean.HomeZhongChouResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.micai.fox.view.CustomCompatScrollView;
import com.micai.fox.view.MyDividerItemDecoration;
import com.micai.fox.view.MyScrollView;
import com.micai.fox.view.PageListScrollView;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by lq on 2017/12/19.
 */

public class HomeFragment extends Fragment implements PageListScrollView.OnScrollToBottomListener, SwipeRefreshLayout.OnRefreshListener, View.OnTouchListener {
    @Bind(R.id.recycleview_h)
    RecyclerView recycleviewH;
    @Bind(R.id.listview_home)
    ListView listviewHome;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.home_experts_moveview)
    LinearLayout homeExpertsMoveview;
    @Bind(R.id.home_experts_parent)
    LinearLayout homeExpertsParent;
    @Bind(R.id.home_zhongchou_moveview)
    LinearLayout homeZhongchouMoveview;
    @Bind(R.id.home_zhongchou_parent)
    LinearLayout homeZhongchouParent;
    @Bind(R.id.home_scroll)
    PageListScrollView homeScroll;
    @Bind(R.id.home_swp)
    SwipeRefreshLayout swipeRefreshLayout;
    private View footer_view;
    private HomeResultBean homeResultBean;
    private List<HomeResultBean.ExecDatasBean.BannerBean> bannerBeanList;
    private HomeResultBean.ExecDatasBean.CrowdfundingBean crowdfundingBeanList;
    private List<HomeZhongChouResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private List<HomeResultBean.ExecDatasBean.ProfessorBean> datas = new ArrayList<>();
    private HomeZhongChouResultBean homeZhongChouResultBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        rl.setVisibility(View.GONE);
        tvTitle.setText("迷彩狐");
        listviewHome.setFocusable(false);
        getHomeData(1);
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
        listviewHome.addFooterView(footer_view);
        adapter = new MyHomeZhongChouAdapter(data, getContext(), R.layout.item_v_listview);
        listviewHome.setAdapter(adapter);
        getZhongChouList(0, 1);
//        homeScroll.getViewTreeObs
// erver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Log.e("YJL", "悬停");
//                ho.smeScrolletXuantingquyu(homeXuanting, homeExpertsParent, homeExpertsMoveview);
//                homeScroll.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//
//            }
//        });
       /* homeScroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("YJL", "悬停");
                homeScroll.setXuantingquyu(homeXuanting, homeZhongchouParent, homeZhongchouMoveview);
                homeScroll.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            }
        });*/
       /* listviewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/
        homeScroll.setOnScrollToBottomListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        banner.setOnTouchListener(this);
        return view;
    }

    private MyHomeZhongChouAdapter adapter;

    private void initView(int type) {
        initBanner(type);
        //横向recycle
        LinearLayoutManager mLayoutManagerH = new LinearLayoutManager(getContext()) {
            //禁止水平滑动
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        mLayoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        MyRecycleHAdapter mAdapterH = new MyRecycleHAdapter(datas, getContext());
        // 设置布局管理器
        recycleviewH.setLayoutManager(mLayoutManagerH);
        // 设置adapter
        recycleviewH.setAdapter(mAdapterH);

//        ToolUtils.setListViewHeightBasedOnChildren(listviewHome);
        mAdapterH.setOnItemClickListener(new MyRecycleHAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "click " + position + " item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ExpertsDetailActivity.class);
                intent.putExtra("proId", datas.get(position).getProId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(getContext(), "long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
        if (null != homeResultBean.getExecDatas().getProfessor()) {
            LogUtil.e("yjl", "recycle" + type);
            if (type == 0) {
                datas.clear();
                datas.addAll(homeResultBean.getExecDatas().getProfessor());
                mAdapterH.notifyDataSetChanged();
            } else {
                datas.addAll(homeResultBean.getExecDatas().getProfessor());
                mAdapterH.notifyDataSetChanged();
                int itemSpacing = 13;
                recycleviewH.addItemDecoration(new MyDividerItemDecoration(itemSpacing));
            }
            LogUtil.e("yjl", type + "recycle" + datas.size());

        }
    }

    private void initBanner(int type) {
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        banner.setIndicatorGravity(Banner.CENTER);

        /*//设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        banner.setBannerTitle(titles);*/

        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
//        banner.setDelayTime(5000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);
        bannerBeanList = homeResultBean.getExecDatas().getBanner();
        //自定义图片加载框架
        List<String> images = new ArrayList<String>();
        for (HomeResultBean.ExecDatasBean.BannerBean bannerBean : bannerBeanList) {
            images.add(Url.WEB_BASE_IP + bannerBean.getImgPath());
        }
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(getActivity()).load(url).into(view);
            }
        });
        //设置点击事件，下标是从1开始
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            private String forwardModule;

            @Override
            public void OnBannerClick(View view, int position) {
//                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_LONG).show();
                forwardModule = bannerBeanList.get(position - 1).getForwardModule();
                LogUtil.e("YJL", "banner-position" + forwardModule);
                Intent intent;
                switch (forwardModule) {
                    case "0":
                        //众筹
                        intent = new Intent(getActivity(), ZhongChouDetailActivity.class);
                        intent.putExtra("crowdingId", bannerBeanList.get(position - 1).getForwardPath());
                        startActivity(intent);
                        break;
                    case "1":
                        //报告
                        intent = new Intent(getActivity(), ReportDetailActivity.class);
                        intent.putExtra("reportId", bannerBeanList.get(position - 1).getForwardPath());
                        startActivity(intent);
                        break;
                    case "2":
                        //专家
                        intent = new Intent(getActivity(), ExpertsDetailActivity.class);
                        intent.putExtra("proId", bannerBeanList.get(position - 1).getForwardPath());
                        startActivity(intent);
                        break;
                    case "9":
                        //网页
                        intent = new Intent(getActivity(), WebActivity.class);
                        intent.putExtra("URL", bannerBeanList.get(position - 1).getForwardPath());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    ParamBean paramBean;
    ParamBean.ParamData paramData;

    //获取首页数据
    private void getHomeData(final int type) {
        paramBean = new ParamBean();
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(Url.WEB_HOME)
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (type == 0) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "home--data" + response);
                if (Tools.isGoodJson(response)) {
                    homeResultBean = new Gson().fromJson(response, HomeResultBean.class);
                    if (homeResultBean.isExecResult()) {
                        if (type == 0) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        initView(type);
                    } else {
                        if (type == 0) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                } else {
                    if (type == 0) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }
        });
    }

    private int currentpage = 0;
    TextView tv_foot;

    @Override
    public void onScrollBottomListener(boolean isBottom) {
        if (isBottom) {
            LogUtil.e("YJL", "总页数==" + homeZhongChouResultBean.getExecDatas().getTotalPage() + "--" + data.size());
            if (data.size() < homeZhongChouResultBean.getExecDatas().getTotalRow()) {
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("加载中...");
                currentpage++;
                getZhongChouList(currentpage, 1);
            } else {
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");
            }
        } /*else {
            tv_foot.setVisibility(View.GONE);
        }*/
    }


    private void getZhongChouList(int pageNum, final int type) {
        paramBean = new ParamBean();
        paramBean.setPageNum("" + pageNum);
        paramBean.setLength("" + 20);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url((Url.WEB_HOME_ZHONGCHOU))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "home-众筹-data" + response);
                if (Tools.isGoodJson(response)) {
                    homeZhongChouResultBean = new Gson().fromJson(response, HomeZhongChouResultBean.class);
                    if (homeZhongChouResultBean.isExecResult()) {
                        if (type == 0) {
                            currentpage = 0;
                            data.clear();
                            data.addAll(homeZhongChouResultBean.getExecDatas().getRecordList());
                            adapter.notifyDataSetChanged();
                        } else {
                            data.addAll(homeZhongChouResultBean.getExecDatas().getRecordList());
                            adapter.notifyDataSetChanged();
                            if (tv_foot.getVisibility() == View.VISIBLE)
                                tv_foot.setVisibility(View.GONE);
//
                        }
                    }

                }
            }
        });
    }

    @Override
    public void onRefresh() {
        getHomeData(0);
        getZhongChouList(0, 0);
    }

    /**
     * 解决swip嵌套scroll中viewpager滑动冲突
     */
    int downX;
    int downY;
    int dragthreshold = 30;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE:
                swipeRefreshLayout.setEnabled(false);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                swipeRefreshLayout.setEnabled(true);
                break;
        }
        return false;
    }

}
