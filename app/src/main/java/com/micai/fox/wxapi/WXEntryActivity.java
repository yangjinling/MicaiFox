package com.micai.fox.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.micai.fox.R;
import com.micai.fox.app.Config;
import com.micai.fox.app.Constant;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        api = WXAPIFactory.createWXAPI(this, Config.getInstance().getAppId());
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }


    //处理支付的回调
    @Override
    public void onResp(BaseResp baseResp) {
        Log.e(Constant.TAG, "进来了没有");
        Log.e(Constant.TAG, "errStr===" + baseResp.errStr + "errStr===errCode" + baseResp.errCode + "type===" + baseResp.getType());
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
          /*  AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("" + baseResp.errCode);
            builder.show();*/
            /*名称	描述	解决方案
            0	成功	展示成功页面
            -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
            -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。*/
            Intent intent;
            if (baseResp.errCode == 0) {
//                如果支付成功则去后台查询支付结果再展示用户实际支付结果
               /* intent = new Intent();
                intent.putExtra("RESULT", "OK");
                setResult(RESULT_OK, intent);*/
                Intent finishPayIntent = new Intent();
                finishPayIntent.setAction("finishPay");
                sendBroadcast(finishPayIntent);
                finish();
            } else if (baseResp.errCode == -1) {
//                Toast.makeText(this, "签名问题", Toast.LENGTH_SHORT).show();
//                ToolsC.CenterToast(this, "签名问题");
               /* Intent failedOrderIntent = new Intent();
                failedOrderIntent.setAction("failedPay");
                sendBroadcast(failedOrderIntent);*/
                finish();
            } else if (baseResp.errCode == -2) {
                //取消支付
//                Toast.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
//                ToolsC.CenterToast(this, "取消支付");
              /*  Intent unCancleOrderIntent = new Intent();
                unCancleOrderIntent.setAction("unCancleOrder");
                sendBroadcast(unCancleOrderIntent);*/
                finish();
            /*    intent = new Intent();
                intent.putExtra("RESULT", "CANCLE");
                setResult(RESULT_OK, intent);*/

            }
        }
    }
}
