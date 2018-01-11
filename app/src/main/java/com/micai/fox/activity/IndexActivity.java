package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.base.BaseActivity;
import com.micai.fox.util.ExitAppUtils;
import com.micai.fox.util.PrefUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*引导页*/
public class IndexActivity extends BaseActivity {

    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_regist)
    Button btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        if (PrefUtils.getBoolean(Config.getInstance().getmContext(), "ISFIRST", false)) {
            Intent intent = new Intent(IndexActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick({R.id.btn_login, R.id.btn_regist})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_login:
                intent = new Intent(IndexActivity.this, LoginActivity.class);
                startActivity(intent);
                ExitAppUtils.getInstance().addActivity(this);
                break;
            case R.id.btn_regist:
                intent = new Intent(IndexActivity.this, RegistActivity.class);
                startActivity(intent);
                ExitAppUtils.getInstance().addActivity(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
