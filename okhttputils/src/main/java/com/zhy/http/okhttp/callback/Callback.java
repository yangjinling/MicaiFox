package com.zhy.http.okhttp.callback;

import android.content.Context;
import android.system.ErrnoException;
import android.util.Log;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Callback<T>
{

    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request, int id)
    {

        Log.e("callBack","onBefore");

    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter(int id)
    {
        Log.e("callBack","onAfter");
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress, long total , int id)
    {

    }

    /**
     * if you parse reponse code in parseNetworkResponse, you should make this method return true.
     *
     * @param response
     * @return
     */
    public boolean validateReponse(Response response, int id)
    {
        return response.isSuccessful();
    }

    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseNetworkResponse(Response response, int id) throws Exception;

    public abstract void onError(Call call, Exception e, int id);

    public abstract void onResponse(T response, int id) throws Exception;
//    public  void onError(Call call, Exception e, int id){
//
//        Log.e("callBack","有没有继承到这个onError");
//    };
//
//    public  void onResponse(T response, int id)throws Exception{
//        Log.e("callBack","有没有继承到这个onResponse");
//
//    };


    public static Callback CALLBACK_DEFAULT = new Callback()
    {

        @Override
        public Object parseNetworkResponse(Response response, int id) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            Log.e("callBack","onError");

        }

        @Override
        public void onResponse(Object response, int id)
        {
            Log.e("callBack","onResponse");

        }
    };

}