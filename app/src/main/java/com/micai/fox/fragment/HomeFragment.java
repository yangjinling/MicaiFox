package com.micai.fox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.micai.fox.R;
import com.micai.fox.activity.ExpertsDetailActivity;
import com.micai.fox.activity.ZhongChouDetailActivity;
import com.micai.fox.adapter.MyHomeZhongChouAdapter;
import com.micai.fox.adapter.MyRecycleHAdapter;
import com.micai.fox.view.MyDividerItemDecoration;
import com.micai.fox.view.MyScrollView;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by lq on 2017/12/19.
 */

public class HomeFragment extends Fragment {
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
    MyScrollView homeScroll;
    @Bind(R.id.home_xuanting2)
    LinearLayout homeXuanting2;
    private ArrayList<String> data;
    //设置图片资源:url或本地资源
    String[] images = new String[]{
            /*"http://218.192.170.132/BS80.jpg",*/
           /* "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",*/
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};

    //设置图片标题:自动对应
    String[] titles = new String[]{"十大星级品牌联盟，全场2折起", "全场2折起", "十大星级品牌联盟", "嗨购5折不要停", "12趁现在", "嗨购5折不要停，12.12趁现在", "实打实大顶顶顶顶"};
    private View footer_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        rl.setVisibility(View.GONE);
        tvTitle.setText("迷彩狐");
        data = getData();
        initView();
//        homeScroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Log.e("YJL", "悬停");
//                homeScroll.setXuantingquyu(homeXuanting, homeExpertsParent, homeExpertsMoveview);
//                homeScroll.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//
//            }
//        });
        homeScroll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("YJL", "悬停");
                homeScroll.setXuantingquyu(homeXuanting, homeZhongchouParent, homeZhongchouMoveview);
                homeScroll.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            }
        });
        return view;
    }

    private void initView() {
        //横向recycle
        LinearLayoutManager mLayoutManagerH = new LinearLayoutManager(getContext());
        mLayoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        MyRecycleHAdapter mAdapterH = new MyRecycleHAdapter(data);
        // 设置布局管理器
        recycleviewH.setLayoutManager(mLayoutManagerH);
        // 设置adapter
        recycleviewH.setAdapter(mAdapterH);
        int itemSpacing = 13;
        recycleviewH.addItemDecoration(new MyDividerItemDecoration(itemSpacing));
        footer_view = ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footerview_lv_home_zhongchou, null);
        listviewHome.addFooterView(footer_view);
        MyHomeZhongChouAdapter adapter = new MyHomeZhongChouAdapter(data, getContext(), R.layout.item_v_listview);
        listviewHome.setAdapter(adapter);
//        ToolUtils.setListViewHeightBasedOnChildren(listviewHome);
        mAdapterH.setOnItemClickListener(new MyRecycleHAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "click " + position + " item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ExpertsDetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), "long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
        listviewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ZhongChouDetailActivity.class);
                getContext().startActivity(intent);
            }
        });
        initBanner();
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

        //自定义图片加载框架
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                System.out.println("加载中");
                Glide.with(getActivity()).load(url).into(view);
                System.out.println("加载完");
            }
        });
        //设置点击事件，下标是从1开始
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 4; i++) {
            data.add(i + temp);
        }

        return data;
    }

}
