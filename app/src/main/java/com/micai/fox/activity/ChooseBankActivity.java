package com.micai.fox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.micai.fox.R;
import com.micai.fox.adapter.BankAdapter;
import com.micai.fox.adapter.MyBaseAdapter;
import com.micai.fox.adapter.ViewHolder;
import com.micai.fox.app.Config;
import com.micai.fox.app.Url;
import com.micai.fox.parambean.ParamBean;
import com.micai.fox.parambean.ZhongChouBean;
import com.micai.fox.resultbean.BankBean;
import com.micai.fox.resultbean.PayResultBean;
import com.micai.fox.util.LogUtil;
import com.micai.fox.util.Tools;
import com.micai.fox.view.MyListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class ChooseBankActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_parent_bank)
    LinearLayout llParentBank;
    @Bind(R.id.bank_go)
    Button bankGo;
    @Bind(R.id.bank_lv)
    MyListView bankLv;
    private ZhongChouBean bean;
    private List<BankBean> list = new ArrayList<>();
    private BankAdapter bankAdapter;
    //    private String[] idList = new String[]{"01020000", "01040000", "03010000", "01030000", "01050000", "03020000",
//            "03070000", "03050000", "03060000", "03090000", "03100000", "03080000", "03030000", "03040000", "04030000"};
//    private String[] nameList = new String[]{"工商银行", "中国银行", "交通银行", "农业银行", "建设银行"
//            , "中信银行", "平安银行", "民生银行", "广发银行", "兴业银行", "浦发银行", "招商银行", "光大银行", "华夏银行", "邮政储蓄银行"};
    private String[] idList = new String[]{"01020000", "01050000", "03020000", "01040000", "03050000", "01030000",
            "04030000", "03100000", "03030000"/*,"00000000(北京银行银行号没有)"*/};
    private String[] nameList = new String[]{"工商银行", "建设银行", "中信银行", "中国银行", "民生银行", "农业银行", "邮储银行"
            , "上海银行", "光大银行"/*,"北京银行"*/};
    private int[] imgList = new int[]{R.mipmap.gongshang, R.mipmap.jianshe, R.mipmap.zhongxin, R.mipmap.zhongguo, R.mipmap.minsheng, R.mipmap.nongye,
            R.mipmap.youchu, R.mipmap.shanghai, R.mipmap.guangda/*, R.mipmap.beijing*/};
    //交通、平安、广发、兴业、招商、华夏没有
//    private int[] imgList = new int[]{R.mipmap.gongshang, R.mipmap.zhongguo, R.mipmap.ic_launcher, R.mipmap.nongye, R.mipmap.jianshe, R.mipmap.zhongxin
//            , R.mipmap.ic_launcher, R.mipmap.minsheng, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.shanghai, R.mipmap.ic_launcher, R.mipmap.guangda,
//            R.mipmap.ic_launcher, R.mipmap.youchu};
    private int positions = 0;
    public ArrayList<Integer> mArrayListChoosed;
    private int selectPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_bank);
        ButterKnife.bind(this);
        tvTitle.setText("选择银行");
        bean = ((ZhongChouBean) getIntent().getSerializableExtra("BEAN"));
        bankAdapter = new BankAdapter(list, this, R.layout.item_bank);
        bankLv.setAdapter(bankAdapter);
        initData();
        bankLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtil.e("YJL", "position==" + i);
                positions = i;
                selectPosition = i;
                bankAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 9; i++) {
            BankBean bankBean = new BankBean(idList[i], nameList[i], imgList[i]);
            list.add(bankBean);
        }
        bankAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @OnClick(R.id.bank_go)
    public void onViewClicked() {
        pay();
    }

    private ParamBean paramBean;
    private ParamBean.ParamData paramData;

    private void pay() {
        LogUtil.e("YJL", "position==" + positions + "bank==" + list.get(positions).getId() + "orderid==" + bean.getOrderId());
        paramBean = new ParamBean();
        paramData = new ParamBean.ParamData();
        paramData.setOrderId("" + bean.getOrderId());
        paramData.setBankId(list.get(positions).getId());
        paramBean.setParamData(paramData);
        OkHttpUtils.postString()
                .mediaType(MediaType.parse(Url.CONTENT_TYPE))
                .url(String.format(Url.WEB_PAY, Config.getInstance().getSessionId()))
                .content(new Gson().toJson(paramBean))
                .build().execute(new StringCallback() {

            private PayResultBean payResultBean;

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) throws Exception {
                Log.e("yjl", "pay--data" + response);
                if (Tools.isGoodJson(response)) {
                    Config.getInstance().setJin(false);
                    payResultBean = new Gson().fromJson(response, PayResultBean.class);
                    Config.getInstance().setPayId(payResultBean.getExecDatas().getPayId());
                    Intent intent = new Intent(ChooseBankActivity.this, PayResultActivity.class);
                    intent.putExtra("URL", payResultBean.getExecDatas().getPayHtml());
                    startActivity(intent);
                    finish();
                } else {
                    Config.getInstance().setJin(true);
                }

            }
        });
    }

    public class BankAdapter extends MyBaseAdapter<BankBean> {
        private List<BankBean> list;

        public BankAdapter(List<BankBean> list, Context context, int resId) {
            super(list, context, resId);
            this.list = list;
        }

        @Override
        public void setData(ViewHolder viewHolder, final int position) {
            TextView tv_name = ((TextView) viewHolder.findViewById(R.id.bank_name_tv));
            final ImageView iv = ((ImageView) viewHolder.findViewById(R.id.bank_iv));
            tv_name.setText(list.get(position).getName());
            ImageView iv_logo = ((ImageView) viewHolder.findViewById(R.id.iv_logo));
            iv_logo.setBackgroundResource(list.get(position).getImg_id());
            LogUtil.e("YJL", "position==" + position + "--selectPosition==" + selectPosition);
            if (selectPosition == position) {
                iv.setBackgroundResource(R.drawable.pointedselect);
            } else {
                iv.setBackgroundResource(R.drawable.point);
            }

        }
    }

}
