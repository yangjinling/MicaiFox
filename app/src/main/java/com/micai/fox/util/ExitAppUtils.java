package com.micai.fox.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * android退出程序的工具类，使用单例模式
 * 1.在Activity的onCreate()的方法中调用addActivity()方法添加到mActivityList
 * 2.你可以在Activity的onDestroy()的方法中调用delActivity()来删除已经销毁的Activity实例
 * 这样避免了mActivityList容器中有多余的实例而影响程序退出速度
 *
 * @author xiaanming
 */
public class ExitAppUtils {
    /**
     * 转载Activity的容器
     */
    private static ExitAppUtils instance;
    private Stack<Activity> activityStack;
    /**
     * 将构造函数私有化
     */
    private ExitAppUtils() {
        activityStack = new Stack<Activity>();
    }
    /**
     * 获取ExitAppUtils的实例，保证只有一个ExitAppUtils实例存在
     *
     * @return
     */
    public static ExitAppUtils getInstance() {
        if (instance == null) {
            instance = new ExitAppUtils();
        }
        return instance;
    }


    /**
     * 添加activity到栈中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {

        activityStack.add(activity);
    }

    /**
     * 获取当前activity
     *
     * @return
     */
    public Activity getCurrentActivity() {

        return activityStack.lastElement();
    }

    /**
     * 结束当前activity
     */
    public void finishCurrentActivity() {

        finishActivity(getCurrentActivity());
    }

    /**
     * 结束指定的activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {

        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 结束指定类名activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity o = iterator.next();
            if (o.getClass().equals(cls)) {
                o.finish();
                iterator.remove();
                activityStack.remove(o);
            } //注意这个地方
        }
    }

    /**
     * 结束所有activity
     */

    public void finishAllActivities() {
        for (Activity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }

    /**
     * 退出程序的方法
     */
    public void exit(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.killBackgroundProcesses(context.getPackageName());
        System.exit(0);
    }


}
