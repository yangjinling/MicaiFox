package com.micai.fox.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistActivity extends BaseActivity {

    @Bind(R.id.regist_tv)
    TextView registTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
