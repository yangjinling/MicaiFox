package com.micai.fox.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.util.PrefUtils;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PrefUtils.getBoolean(Config.getInstance().getmContext(), "ISFIRST", false)) {
                    Config.getInstance().setSessionId(PrefUtils.getString(Config.getInstance().getmContext(), "SESSIONID", ""));
                    Config.getInstance().setPhone(PrefUtils.getString(Config.getInstance().getmContext(), "PHONE", ""));
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, IndexActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },1500);

    }
}
