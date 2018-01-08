package com.micai.fox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micai.fox.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*修改昵称界面*/
public class ModifyNiChengActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_notify)
    TextView tvNotify;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.modify_tv_nicheng)
    EditText modifyTvNicheng;
    @Bind(R.id.iv_tv_nicheng)
    ImageView ivTvNicheng;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_ni_cheng);
        ButterKnife.bind(this);
        rl.setVisibility(View.VISIBLE);
        tvBack.setVisibility(View.VISIBLE);
        tvNotify.setVisibility(View.VISIBLE);
        tvNotify.setText("完成");
        tvTitle.setText("修改昵称");
        name = getIntent().getStringExtra("NAME");
        modifyTvNicheng.setHint("" + name);
        ivTvNicheng.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_back, R.id.tv_notify, R.id.iv_tv_nicheng})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_notify:
                String name = modifyTvNicheng.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {

                } else {
                    Intent intent = new Intent();
                    intent.putExtra("NAME", modifyTvNicheng.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.iv_tv_nicheng:
                modifyTvNicheng.setHint("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
