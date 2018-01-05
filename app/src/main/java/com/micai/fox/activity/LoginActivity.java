package com.micai.fox.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.micai.fox.R;
import com.micai.fox.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
/*登陆页面*/
public class LoginActivity extends BaseActivity {

    @Bind(R.id.login_tv)
    TextView loginTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
