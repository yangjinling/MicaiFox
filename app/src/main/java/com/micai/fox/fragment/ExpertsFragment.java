package com.micai.fox.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.micai.fox.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lq on 2017/12/19.
 */

public class ExpertsFragment extends Fragment {
    @Bind(R.id.experts_ll)
    LinearLayout expertsLl;
    @Bind(R.id.experts_tv_all)
    TextView expertsTvAll;
    @Bind(R.id.experts_tv_income)
    TextView expertsTvIncome;
    @Bind(R.id.experts_tv_mizhong)
    TextView expertsTvMizhong;
    @Bind(R.id.experts_view_all)
    View expertsViewAll;
    @Bind(R.id.experts_view_income)
    View expertsViewIncome;
    @Bind(R.id.experts_view_mizhong)
    View expertsViewMizhong;
    private FragmentManager manager;
    private FragmentTransaction transcation;
    private int kind = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experts, container, false);
        ButterKnife.bind(this, view);
//        EventBus.getDefault().register(this);
        manager = getActivity().getSupportFragmentManager();
        choiceFragment(kind);
        return view;
    }

    @OnClick({R.id.experts_tv_all, R.id.experts_tv_income, R.id.experts_tv_mizhong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.experts_tv_all:
                //全部
                expertsTvAll.setTextColor(getResources().getColor(R.color.blue));
                expertsTvIncome.setTextColor(getResources().getColor(R.color.gray));
                expertsTvMizhong.setTextColor(getResources().getColor(R.color.gray));
                expertsViewAll.setBackgroundResource(R.color.blue);
                expertsViewIncome.setBackgroundResource(R.color.white);
                expertsViewMizhong.setBackgroundResource(R.color.white);
                kind = 0;
                choiceFragment(kind);
                break;
            case R.id.experts_tv_income:
                //盈利帮
                expertsTvAll.setTextColor(getResources().getColor(R.color.gray));
                expertsTvIncome.setTextColor(getResources().getColor(R.color.blue));
                expertsTvMizhong.setTextColor(getResources().getColor(R.color.gray));
                expertsViewAll.setBackgroundResource(R.color.white);
                expertsViewIncome.setBackgroundResource(R.color.blue);
                expertsViewMizhong.setBackgroundResource(R.color.white);
                kind = 1;
                choiceFragment(kind);
                break;
            case R.id.experts_tv_mizhong:
                //命中帮
                expertsTvAll.setTextColor(getResources().getColor(R.color.gray));
                expertsTvIncome.setTextColor(getResources().getColor(R.color.gray));
                expertsTvMizhong.setTextColor(getResources().getColor(R.color.blue));
                expertsViewAll.setBackgroundResource(R.color.white);
                expertsViewIncome.setBackgroundResource(R.color.white);
                expertsViewMizhong.setBackgroundResource(R.color.blue);
                kind = 2;
                choiceFragment(kind);
                break;

        }
    }

    public void choiceFragment(int flags) {
        transcation = manager.beginTransaction();
        ExpertsDetailFragment allFragment = new ExpertsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("KIND", flags);
        allFragment.setArguments(bundle);
        transcation.replace(R.id.experts_ll, allFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
