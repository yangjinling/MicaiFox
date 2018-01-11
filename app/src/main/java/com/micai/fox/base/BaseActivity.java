package com.micai.fox.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.app.Constant;
import com.micai.fox.util.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DialogInShow;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * Created by lq on 2017/12/19.
 */

public class BaseActivity extends AppCompatActivity {

    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        EventBus.getDefault().register(this);
        mDialog = new Dialog(this, R.style.Translucent_NoTitle);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View contentview = inflater.inflate(R.layout.progress, null);
        mDialog.setContentView(contentview);
        mDialog.setCancelable(true);

    }


    //给网络请求加缓冲小黄圈
    @Subscribe
    public void onEventMainThread(DialogInShow dialogInShow) {

        if (dialogInShow.isShow()) {
            mDialog.show();
        } else {
            mDialog.dismiss();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            LogUtil.e(Constant.TAG, "按返回键");
        }
        return super.onKeyDown(keyCode, event);

    }
}
