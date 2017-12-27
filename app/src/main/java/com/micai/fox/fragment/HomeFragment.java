package com.micai.fox.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.micai.fox.R;
import com.micai.fox.adapter.MyListViewVAdapter;
import com.micai.fox.adapter.MyRecycleHAdapter;
import com.micai.fox.util.ToolUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lq on 2017/12/19.
 */

public class HomeFragment extends Fragment {
    @Bind(R.id.iv_home)
    ImageView homeIv;
    @Bind(R.id.recycleview_h)
    RecyclerView recycleviewH;
    @Bind(R.id.listview_home)
    ListView listviewHome;
    private ArrayList<String> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        data = getData();
        initView();
        return view;
    }

    private void initView() {
        //横向recycle
        LinearLayoutManager mLayoutManagerH = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        MyRecycleHAdapter mAdapterH = new MyRecycleHAdapter(data);
        // 设置布局管理器
        recycleviewH.setLayoutManager(mLayoutManagerH);
        // 设置adapter
        recycleviewH.setAdapter(mAdapterH);
        MyListViewVAdapter adapter = new MyListViewVAdapter(data, getContext(), R.layout.item_v_listview);
        listviewHome.setAdapter(adapter);
        ToolUtils.setListViewHeightBasedOnChildren(listviewHome);
        mAdapterH.setOnItemClickListener(new MyRecycleHAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), "long click " + position + " item", Toast.LENGTH_SHORT).show();
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
        for (int i = 0; i < 50; i++) {
            data.add(i + temp);
        }

        return data;
    }

}
