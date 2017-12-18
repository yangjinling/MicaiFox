package com.zhy.http.okhttp.callback;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        return response.body().string();
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        DialogInShow.getInstance().setShow(true);
        EventBus.getDefault().post(DialogInShow.getInstance());
        Log.e("StringCallBack","onBefore");
    }

//    @Override
//    public void onResponse(String response, int id) throws Exception {
//        DialogInShow.getInstance().setShow(false);
//        EventBus.getDefault().post(DialogInShow.getInstance());
//        Log.e("StringCallBack","onResponse");
//    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        DialogInShow.getInstance().setShow(false);
        EventBus.getDefault().post(DialogInShow.getInstance());
        Log.e("StringCallBack","onAfter");

    }
}
