package com.micai.fox.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.micai.fox.R;
import com.micai.fox.activity.NotificationActivity;
import com.micai.fox.app.Config;
import com.micai.fox.parambean.NotiBean;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by lq on 2018/3/2.
 */

public class IntentService extends GTIntentService {
    NotificationManager mange;
    public IntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();
        mange = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));

        Log.d(TAG, "onReceiveMessageData -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nmessageid = " + messageid + "\npkg = " + pkg
                + "\ncid = " + cid);

        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
        } else {
            String data = new String(payload);
            Log.d(TAG, "receiver payload = " + data);
//            sendMessage(data, 0);
            NotiBean bean=new NotiBean();
            bean.setHaveN(true);
            EventBus.getDefault().post(bean);
            nomaleNotif(context,data);
        }

        Log.d(TAG, "----------------------------------------------------------------------------------------------");

    }
    /*一般的通知*/
    public void nomaleNotif(Context  context,String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        builder.setTicker("提示");
//        builder.setContentTitle(""+content);
//        builder.setSubText(""+content);
        builder.setContentText(""+content);
//        builder.setContentInfo("显示于通知时间的下面");
        builder.setNumber(1);//显示同种通知的数量，塔河setContentinfo只能设置其中一种
        builder.setAutoCancel(true);//可以点击通知栏的删除按钮删除
        builder.setSmallIcon(R.drawable.push);//显示的小图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.push));//下拉现实的额大图表
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(this, NotificationActivity.class), 0);//点击实现跳转
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        mange.notify(1, notification);
    }
    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e("YJL", "onReceiveClientId -> " + "clientid = " + clientid);
        Config.getInstance().setClientId(clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
    }

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
    }
}