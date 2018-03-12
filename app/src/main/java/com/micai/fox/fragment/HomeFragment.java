package com.micai.fox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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

public class HomeFragment extends Fragment implements PageListScrollView.OnScrollToBottomListener {
    /* @Bind(R.id.iv_home)
     ImageView homeIv;*/
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
    @Bind(R.id.home_xuanting)
    LinearLayout homeXuanting;
    @Bind(R.id.home_scroll)
    PageListScrollView homeScroll;
    @Bind(R.id.home_xuanting2)
    LinearLayout homeXuanting2;
    //设置图片标题:自动对应
    String[] titles = new String[]{"十大星级品牌联盟，全场2折起", "全场2折起", "十大星级品牌联盟", "嗨购5折不要停", "12趁现在", "嗨购5折不要停，12.12趁现在", "实打实大顶顶顶顶"};
    private View footer_view;
    private HomeResultBean homeResultBean;
    private List<HomeResultBean.ExecDatasBean.BannerBean> bannerBeanList;
    private Handler mHandler = new Handler();
    Runnable scrollViewRunable = new Runnable() {
        @Override
        public void run() {
            homeScroll.smoothScrollTo(0, 0);
        }
    };
    private HomeResultBean.ExecDatasBean.CrowdfundingBean crowdfundingBeanList;
    private List<HomeZhongChouResultBean.ExecDatasBean.RecordListBean> data = new ArrayList<>();
    private HomeZhongChouResultBean homeZhongChouResultBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        rl.setVisibility(View.GONE);
        tvTitle.setText("迷彩狐");
        listviewHome.setFocusable(false);
        getHomeData();
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
        listviewHome.addFooterView(footer_view);
        adapter = new MyHomeZhongChouAdapter(data, getContext(), R.layout.item_v_listview);
        listviewHome.setAdapter(adapter);
        getZhongChouList(0);
//        homeScroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Log.e("YJL", "悬停");
//                homeScroll.setXuantingquyu(homeXuanting, homeExpertsParent, homeExpertsMoveview);
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
        return view;
    }

    private MyHomeZhongChouAdapter adapter;

    private void initView() {
        initBanner();
        //横向recycle
        LinearLayoutManager mLayoutManagerH = new LinearLayoutManager(getContext());
        mLayoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        MyRecycleHAdapter mAdapterH = new MyRecycleHAdapter(homeResultBean.getExecDatas().getProfessor(), getContext());
        // 设置布局管理器
        recycleviewH.setLayoutManager(mLayoutManagerH);
        // 设置adapter
        recycleviewH.setAdapter(mAdapterH);
        int itemSpacing = 13;
        recycleviewH.addItemDecoration(new MyDividerItemDecoration(itemSpacing));

//        ToolUtils.setListViewHeightBasedOnChildren(listviewHome);
        mAdapterH.setOnItemClickListener(new MyRecycleHAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "click " + position + " item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ExpertsDetailActivity.class);
                intent.putExtra("proId", homeResultBean.getExecDatas().getProfessor().get(position).getProId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), "long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initBanner() {
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
                System.out.println("加载中");
                Glide.with(getActivity()).load(url).into(view);
                System.out.println("加载完");
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
    private void getHomeData() {
        paramBean = new ParamBean();
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_HOME, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "home--data" + response);
                if (Tools.isGoodJson(response)) {
                    homeResultBean = new Gson().fromJson(response, HomeResultBean.class);
                    if (homeResultBean.isExecResult()) {
                        initView();
                    } else {
                    }
                }
            }
        });
    }

    private int pagesize = 20;
    private int currentpage = 1;
    private boolean judgeCanLoadMore = true;
    private int totalCount = 20;//设置本次加载的数据的总数
    TextView tv_foot;

    @Override
    public void onScrollBottomListener(boolean isBottom) {
        if (!isBottom) {
            tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
            tv_foot.setVisibility(View.GONE);
        } else {
            //模拟进行数据的分页加载
            if (judgeCanLoadMore && isBottom) {
//            commentLv.startLoading();
//            if (currentpage == 0) {
//                Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getContext(), "正在加载中", Toast.LENGTH_SHORT).show();
//                getZhongChouList(currentpage);
//            }
                if (++currentpage > homeZhongChouResultBean.getExecDatas().getTotalPage()) {
                    tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
                    tv_foot.setVisibility(View.VISIBLE);
                    tv_foot.setText("没有更多了");
//                Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                } else {
                    tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
                    tv_foot.setVisibility(View.VISIBLE);
                    tv_foot.setText("加载中...");
//                Toast.makeText(getContext(), "正在加载中", Toast.LENGTH_SHORT).show();
                    getZhongChouList(currentpage);
                }
            }
            if (!judgeCanLoadMore && isBottom) {
                tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
                tv_foot.setVisibility(View.VISIBLE);
                tv_foot.setText("没有更多了");
//            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initLoadMoreTagOp() {
        if (data.size() == 0 || data.size() <= 20 + ((currentpage - 1) * 20)) {//当前获取的数目大于等于总共的数目时表示数据加载完毕，禁止滑动
            judgeCanLoadMore = false;
//            commentLv.loadComplete();
//            Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void getZhongChouList(int pageNum) {
        paramBean = new ParamBean();
        paramBean.setPageNum("" + pageNum);
        paramBean.setLength("" + 20);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_HOME_ZHONGCHOU, Config.getInstance().getSessionId()))
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
                        data.clear();
//                    crowdfundingBeanList = homeResultBean.getExecDatas().getCrowdfunding();
                        data.addAll(homeZhongChouResultBean.getExecDatas().getRecordList());
//                    if (crowdfundingBeanList.getTotalPage()>0) {
//                        currentpage++;
//                    }
                        adapter.notifyDataSetChanged();
//                        mHandler.post(scrollViewRunable);
                        tv_foot = ((TextView) footer_view.findViewById(R.id.foot_tv));
                        if (tv_foot.getVisibility() == View.VISIBLE)
                            tv_foot.setVisibility(View.GONE);
                        initLoadMoreTagOp();
//                        currentpage++;
                    }

                }
            }
        });
    }

}
