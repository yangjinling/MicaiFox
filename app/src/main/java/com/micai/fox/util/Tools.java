package com.micai.fox.util;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.micai.fox.R;
import com.micai.fox.activity.AcountEditActivity;
import com.micai.fox.parambean.PayRefreshBean;
import com.micai.fox.view.UploadHeader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import okhttp3.Call;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Tools {

	public static String getCurrentDateTime() {
		Date localDate = new Date();
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(localDate);
	}

	public static String getCurrentTime() {
		Date localDate = new Date();
		return new SimpleDateFormat("HH:mm").format(localDate);
	}

	public static String getIMEI(Context paramContext) {
		TelephonyManager telephonyManager = (TelephonyManager) paramContext
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		return imei;
	}

	public final static String getMD5(String s) {
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static int getRandom() {
		return Math.abs(new Random().nextInt()) % 1000000;
	}

	public static int getScreenHeight(Context paramContext) {
		new DisplayMetrics();
		return paramContext.getResources().getDisplayMetrics().heightPixels;
	}

	public static int getScreenWidth(Context paramContext) {
		new DisplayMetrics();
		return paramContext.getResources().getDisplayMetrics().widthPixels;
	}

	public static long getSystemTime() {
		return System.currentTimeMillis();
	}

	public static String getVersionName(Context paramContext) {
		String str = "";
		try {
			ComponentName localComponentName = new ComponentName(paramContext,
					"cn.com.mbook2.android");
			str = paramContext.getPackageManager().getPackageInfo(
					localComponentName.getPackageName(), 0).versionName;
		} catch (NameNotFoundException localNameNotFoundException) {
			localNameNotFoundException.printStackTrace();
		}
		return str;
	}

	public static String getVersionCode(Context context)// 获取版本号(内部识别号)
	{
		try {
			PackageInfo pi = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			String code = pi.versionCode + "";
			return code;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "0";
		}
	}

	public static void showDialog(String info, Activity activity) {
		if (activity != null && !activity.isFinishing()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setMessage(info);
			builder.setTitle("提示");
			// builder.setIcon(android.R.drawable.ic_dialog_info);
			builder.setCancelable(false);
			builder.setPositiveButton("确认", null).create().show();
		}
	}

	public static void showFinishDialog(String info, final Activity activity) {
		if (activity != null && !activity.isFinishing()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setMessage(info);
			builder.setTitle("提示");
			builder.setCancelable(false);
			builder.setPositiveButton("确认", new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					activity.finish();
				}
			}).create().show();
		}
	}

	public static void showFinishDialogCancelable(String info,
												  final Activity activity) {
		if (activity != null && !activity.isFinishing()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setMessage(info);
			builder.setTitle("提示");
			builder.setCancelable(false);
			builder.setPositiveButton("确认", new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					activity.finish();
				}
			}).setNegativeButton("取消", null).create().show();
		}
	}

	public static void ShowToast(String info, Context context) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

	public static void ShowToastLong(String info, Context context) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}


	public static byte[] getBytes(InputStream input) throws Exception {
		byte temp[] = new byte[1024];
		int len = 0;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while ((len = input.read(temp)) != -1) {
			out.write(temp, 0, len);
		}

		return out.toByteArray();
	}

	public static boolean goodNet(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			return false;
		}
		return true;
	}

	/***
	 * 图片的缩放方法
	 *
	 * @param bgimage   ：源图片资源
	 * @param newWidth  ：缩放后宽度
	 * @param newHeight ：缩放后高度
	 * @return
	 */
	public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
								   double newHeight) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 计算宽高缩放率
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);
		try {
			Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
					(int) height, matrix, true);
			return bitmap;
		} catch (OutOfMemoryError er) {
			return null;
		}
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 金额格式化 ###,##0.00
	 *
	 * @param s 金额
	 * @return 格式后的金额
	 */
	public static String formatMoney(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		NumberFormat formater = null;
		double num = Double.parseDouble(s);
		formater = new DecimalFormat("###,##0.00");
		return formater.format(num);
	}

	/**
	 * 前面补零
	 *
	 * @param youNumber
	 * @return
	 */
	public static String formatNumCompleteBeforeByZero(int youNumber) {
		// 0 代表前面补充0
		// 6代表长度为6
		// d 代表参数为正数型
		return String.format("%06d", youNumber);
	}

	/**
	 * 格式化银行卡号
	 *
	 * @param cardNo
	 * @return
	 */
	public static String formatCardNo(String cardNo) {
		return cardNo.substring(0, 6) + "******"
				+ cardNo.substring(cardNo.length() - 4, cardNo.length());
	}

	/**
	 * 格式化身份证号码
	 *
	 * @param idCardNum
	 * @return
	 */
	public static String formatIdCardNum(String idCardNum) {
		return idCardNum.substring(0, 10) + "****" + idCardNum.substring(14);
	}

	/**
	 * 格式化手机号码
	 *
	 * @param phoneNum
	 * @return
	 */
	public static String formatPhoneNum(String phoneNum) {
		return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7);
	}

	public static String getCurrentBaiDuTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
				Locale.CHINA);
		URL url;
		try {
			url = new URL("http://www.baidu.com");
			URLConnection uc = url.openConnection();// 生成连接对象
			uc.connect(); // 发出连接
			long ld = uc.getDate(); // 取得网站日期时间
			return sdf.format(new Date(ld));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Integer.parseInt(sdf.format(new Date()).substring(0, 4)) < 2015) {
			return "2015" + sdf.format(new Date()).substring(4,
					sdf.format(new Date()).length());
		} else {
			return sdf.format(new Date());
		}
	}


	/**
	 * 是否是成人
	 *
	 * @param idCard
	 * @param currentTime yyyy/MM/dd 或者 2014/10/11 09:31:07
	 * @return
	 */
	public static boolean isAdult(String idCard, String currentTime) {
		if (TextUtils.isEmpty(idCard) || TextUtils.isEmpty(currentTime)) {
			return false;
		}
		String year = "";
		String month = "";
		String day = "";
		int userYear = 0;
		int userMonth = 0;
		int userDay = 0;
		int currentYear = 0;
		int currentMonth = 0;
		int currentDay = 0;
		String[] date = currentTime.split(" ")[0].split("/");
		if (idCard.length() == 15) {
			year = "19" + idCard.substring(6, 8);
			month = idCard.substring(8, 10);
			day = idCard.substring(10, 12);
		} else if (idCard.length() == 18) {
			year = idCard.substring(6, 10);
			month = idCard.substring(10, 12);
			day = idCard.substring(12, 14);
		} else {
			return false;
		}
		try {
			userYear = Integer.parseInt(year);
			userMonth = Integer.parseInt(month);
			userDay = Integer.parseInt(day);
			currentYear = Integer.parseInt(date[0]);
			currentMonth = Integer.parseInt(date[1]);
			currentDay = Integer.parseInt(date[2]);
		} catch (Exception e) {
			return false;
		}
		if (currentYear - userYear > 18) {
			return true;
		} else if (currentYear - userYear < 18) {
			return false;
		} else {
			if (currentMonth > userMonth) {
				return true;
			} else if (currentMonth < userMonth) {
				return false;
			} else {
				if (currentDay > userDay) {
					return true;
				} else if (currentDay < userDay) {
					return false;
				} else {
					return true;
				}
			}
		}
	}


	private static String getHexLenthByString(String data) {
		if (TextUtils.isEmpty(data)) {
			return "00";
		}
		String result = Integer.toHexString(data.split(" ").length);
		if (result.length() == 1) {
			return "0" + result;
		}
		return result;
	}

	private static String getHexByStringNotEmpty(String data) {
		if (TextUtils.isEmpty(data)) {
			return "00 ";
		}
		return data + " ";
	}

	private static String getHexByString(String data) {
		if (TextUtils.isEmpty(data)) {
			return "";
		}
		return data + " ";
	}

	/**
	 * 取小于等于data的保留两位小数
	 *
	 * @return
	 */
	public static String floor(String data) {
		String result = formatMoney2(data);
		if ("".equals(result) || result == null) {
			return "0.00";
		}
		if (Double.parseDouble(result) > Double.parseDouble(data)) {
			BigDecimal b1 = new BigDecimal(result);// 必须用字符串
			BigDecimal b2 = new BigDecimal("0.01");
			BigDecimal b = b1.subtract(b2);
			result = b + "";
		}
		return result;
	}

	/**
	 * 保留两位小数
	 *
	 * @param s
	 * @return
	 */
	public static String formatMoney2(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		NumberFormat formater = null;
		double num = Double.parseDouble(s);
		formater = new DecimalFormat("######0.00");
		return formater.format(num);
	}

	/**
	 * 是否包含小于等于1个小数点
	 *
	 * @param s
	 * @return true 包含小于等于1个小数点 false包含两个及以上小数点
	 */
	public static boolean checkPoint(String s) {
		if ("".equals(s) || s == null) {
			return false;
		}
		int beforeLength = s.trim().length();
		int afterLenght = s.trim().replace(".", "").length();
		return beforeLength - afterLenght <= 1;
	}


	public static void killAll(Context context) {

		// 获取一个ActivityManager 对象
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		// 获取系统中所有正在运行的进程
		List<RunningAppProcessInfo> appProcessInfos = activityManager
				.getRunningAppProcesses();
		// 获取当前activity所在的进程
		String currentProcess = context.getApplicationInfo().processName;
		// 对系统中所有正在运行的进程进行迭代，如果进程名不是当前进程，则Kill掉
		for (RunningAppProcessInfo appProcessInfo : appProcessInfos) {

			String name = appProcessInfo.processName;

			LogUtil.e("YJL", "processName--->>" + name);

			// 杀掉除了自身应用以及百度之外的，因为百度的服务是 包名 + android:process=":bdservice_v1"
			// processName--->>cn.cbapay.fivezero
			// processName--->>cn.cbapay.fivezero:bdservice_v1
			if (name.indexOf(currentProcess) == -1) {
				LogUtil.e("YJL", "kill-->>processName--->>" + name);
				activityManager.killBackgroundProcesses(name);
			}

		}

	}

	/**
	 * 获取Activity名字
	 *
	 * @param context
	 * @return
	 */
	public static String getActivityName(Context context) {
		String contextString = context.toString();
		return contextString.substring(contextString.lastIndexOf(".") + 1,
				contextString.indexOf("@"));
	}

	/**
	 * 获取Fragment名字
	 *
	 * @param context
	 * @return
	 */
	public static String getFragmentName(Fragment context) {
		String contextString = context.toString();
		return contextString.substring(0, contextString.indexOf("{"));
	}


	public static String getTopActivity(Activity activity) {

		ActivityManager manager = (ActivityManager) activity
				.getSystemService(Activity.ACTIVITY_SERVICE);

		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
		if (runningTaskInfos != null) {
//			return (runningTaskInfos.get(0).topActivity).toString();
			return runningTaskInfos.get(0).topActivity.getShortClassName();
		}
		return null;
	}

	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.getDefault());
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/*判断是否为有效的json串*/
	public static boolean isBadJson(String json) {
		return !isGoodJson(json);
	}

	public static boolean isGoodJson(String json) {
		if (TextUtils.isEmpty(json)) {
			return false;
		}
		try {
			new JsonParser().parse(json);
			return true;
		} catch (JsonParseException e) {
			LogUtil.e("YJL", "bad json: " + json);
			return false;
		}
	}


	private static long lastClickTime = 0;

	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 5000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	public static String formatLevel(int index) {
		String[] num = {"", "一级", "二级", "三级", "四级", "五级"};
		return num[index];
	}

	//点击电话时弹出的diglog
	public static void showDialogCall(final Context context, String title, final String phoneNum) {
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
		if (null != title) { //如果不想要标题，可以传null过来
			builder.setTitle(title);
		}
		builder.setMessage("" + phoneNum);
		builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 使用系统的电话拨号服务，必须在AndroidManifest.xml中声明权限
				//<uses-permission android:name="android.permission.CALL_PHONE" />
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ phoneNum));  //ACTION_DIAL是跳转到拨号界面，ACTION_CALL是直接拨号
				if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					// TODO: Consider calling
					//    ActivityCompat#requestPermissions
					// here to request the missing permissions, and then overriding
					//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
					//                                          int[] grantResults)
					// to handle the case where the user grants the permission. See the documentation
					// for ActivityCompat#requestPermissions for more details.
					return;
				}
				context.startActivity(intent);
			}
		});
		builder.create().show();
	}


	public static boolean isValidName(String name) {
		String strPattern = "^([\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,15}([•][\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,15})*([.][\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,15})*([·][\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,15})*)$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(name);
		return m.matches();
	}


	//修改类提示dialog
	public static Dialog showDialog(final Activity activity, int type, String content) {
		final Dialog dialog = new Dialog(activity, R.style.Translucent_NoTitle);
		View view = ((LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_dialog, null);
		//设置它的ContentView
		dialog.setContentView(view);
		TextView tv = view.findViewById(R.id.dialog_tv);
		ImageView iv = view.findViewById(R.id.dialog_iv);
		if (type == 0) {
			//注册成功
			tv.setText("注册成功");
		} else if (type == 1) {
			tv.setText("重置成功");
		} else if (type == 2) {
			tv.setText("修改成功");
		} else if (type == 3) {
			tv.setText("提交成功");
		} else if (type == 4) {
			iv.setVisibility(View.GONE);
			tv.setText("支付成功");
		} else if (type == 5) {
			iv.setVisibility(View.GONE);
			tv.setText("" + content);
		} else if (type == 6) {
			iv.setVisibility(View.GONE);
			tv.setText("支付结果获取中...");
		}
		WindowManager m = activity.getWindowManager();
		Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
		WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
		/* (int) (d.getHeight() * 0.3);  */ //高度设置为屏幕的0.3
		p.height = (int) (d.getHeight() * 0.3); //高度设置为屏幕-标题栏
		/*(int) (d.getWidth() * 0.5);*/
		p.width = (int) (d.getWidth() * 0.9);   //宽度设置为屏幕的0.5
		if (type == 4) {
			p.alpha = 1;
		}
		dialog.getWindow().setAttributes(p);     //设置生效
		dialog.show();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_SEARCH) {
					return true;
				} else {
					return true; //默认返回 false，这里false不能屏蔽返回键，改成true就可以了
				}
			}
		});
		return dialog;
	}

	/**
	 * 提供精确的除法运算方法div
	 *
	 * @param value1 被除数
	 * @param value2 除数
	 * @param scale  精确范围
	 * @return 两个参数的商
	 * @throws IllegalAccessException
	 */
	public static BigDecimal div(double value1, double value2, int scale) throws IllegalAccessException {
//如果精确范围小于0，抛出异常信息
		if (scale < 0) {
			throw new IllegalAccessException("精确度不能小于0");
		}
		BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
		BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}

	/*放弃编辑的弹框*/
	public static void showPayPopWindow(final Activity activity, final View view, final String content, final int type) {
		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
		View contentview = inflater.inflate(R.layout.popwindow_waitpay, null);
		final PopupWindow popupWindowConfirm = new PopupWindow(contentview, LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
//        要让点击PopupWindow之外的地方PopupWindow消失你需要调用
		popupWindowConfirm.setBackgroundDrawable(new ColorDrawable(0x55000000));
		popupWindowConfirm.setFocusable(true);
		popupWindowConfirm.setOutsideTouchable(false);
		popupWindowConfirm.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
		Button pop_cancle = ((Button) contentview.findViewById(R.id.waitpay_pop_btn_cancle));
		Button pop_sure = ((Button) contentview.findViewById(R.id.waitpay_pop_btn_sure));
		TextView content1 = ((TextView) contentview.findViewById(R.id.pop_tv_content_1));
		TextView content2 = ((TextView) contentview.findViewById(R.id.pop_tv_content_2));
		View views = ((View) contentview.findViewById(R.id.waitpay_view));
		View line = ((View) contentview.findViewById(R.id.line_view));

		if (type == 0) {
			content1.setText("" + content);
		} else if (type == 1) {
			//支付失败
			content1.setText("支付失败，请重新支付");
			content1.setTextSize(18);
			content2.setVisibility(View.VISIBLE);
			content2.setText("如订单显示未支付但仍有扣款，可联系客服处理");
			content2.setTextColor(activity.getResources().getColor(R.color.text_gray));
		} else if (type == 2) {
			content1.setText("未获取到支付结果，刷新看看");
			content1.setTextSize(18);
			content2.setVisibility(View.VISIBLE);
			content2.setText("如订单显示未支付但仍有扣款，可联系客服处理");
			content2.setTextColor(activity.getResources().getColor(R.color.text_gray));
			pop_cancle.setText("取消");
			line.setVisibility(View.VISIBLE);
			views.setVisibility(View.VISIBLE);
			pop_sure.setVisibility(View.VISIBLE);
			pop_sure.setText("刷新看看");
		} else if (type == 3) {
			content1.setText("您还未设置收款账户，请前往设置以便兑换众筹营收金额。");
			pop_cancle.setText("稍后设置");
			pop_sure.setVisibility(View.VISIBLE);
			line.setVisibility(View.VISIBLE);
			views.setVisibility(View.VISIBLE);
			pop_sure.setText("去设置");
		} else if (type == 4) {
			content1.setText("未完成支付，刷新可重新查看支付状态");
			content2.setVisibility(View.VISIBLE);
			content2.setText("如订单显示未支付但仍有扣款，可联系客服处理");
			content2.setTextColor(activity.getResources().getColor(R.color.text_gray));
			content1.setTextSize(18);
			pop_cancle.setText("取消");
			line.setVisibility(View.VISIBLE);
			views.setVisibility(View.VISIBLE);
			pop_sure.setVisibility(View.VISIBLE);
			pop_sure.setText("刷新看看");
		}
		pop_cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				popupWindowConfirm.dismiss();
			}
		});
		pop_sure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				popupWindowConfirm.dismiss();
				if (type == 2 || type == 4) {
					EventBus.getDefault().post(new PayRefreshBean(true));
				} else if (type == 3) {
					Intent intent = new Intent(activity, AcountEditActivity.class);
					intent.putExtra("KIND", 1);
					activity.startActivity(intent);
				}
			}
		});
	}

	public static String fomatMoney(String money) {
		if (money.contains(".")) {
			if ("00".equals(money.substring(money.indexOf(".") + 1))) {
				money = money.substring(0, money.indexOf("."));
			} else if ("0".equals(money.substring(money.indexOf(".") + 1))) {
				money = money.substring(0, money.indexOf("."));
			} else if (!money.substring(money.indexOf(".") + 1).equals(0) && money.endsWith("0")) {
				money = money.substring(0, money.length() - 1);
			}
		}
		return money;
	}

	//带向下挤压的下拉刷新
	public static void upLoadGagrProgress(String color, PtrFrameLayout mPtrFrame, Context context, PtrHandler ptrHandler) {
		if (null != mPtrFrame) {
			mPtrFrame.setLoadingMinTime(1000);
//            mPtrFrame.setLoadingMinTime(500);
			final UploadHeader header = new UploadHeader(context);

			header.setBackgroundColor(Color.parseColor(color));
			header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

			mPtrFrame.setHeaderView(header);
			mPtrFrame.addPtrUIHandler(header);
			mPtrFrame.setPtrHandler(ptrHandler);
		}

	}

}
