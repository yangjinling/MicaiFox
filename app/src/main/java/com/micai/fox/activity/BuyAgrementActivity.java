package com.micai.fox.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.micai.fox.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyAgrementActivity extends AppCompatActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.buy_agree_web)
    TextView buyAgreeWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_agrement);
        ButterKnife.bind(this);
        tvTitle.setText("众筹购买协议");
        String content = "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t甲方(投资人)：\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t身份证号码：&nbsp;\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t乙方：杭州迷彩狐网络科技有限公司\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t注册号&nbsp; ：91330105MA2AXB069W\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>重要须知：</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t一、本认购协议书在法律许可的范围内由筹委会负责解释。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t二、迷彩狐（乙方）及筹委会销售的众筹产品与存款存在明显区别，具有投资风险，购买须谨慎。本产品涉及的主要风险包括市场风险、信用风险、流动性风险、政策风险、信息传递风险等。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t&nbsp;三、本产品开放、到期或项目方杭州迷彩狐网络科技有限公司提前终止后对于在投资期限内投资本金和所享收益的支付由筹委会监督杭州迷彩狐网络有限公司完成。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t四、根据中国金融监管机构关于无投资经验及有投资经验投资人的要求，本产品适合有投资经验和无投资经验的投资人。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t五、在购买本产品后，投资人（甲方）应随时关注该产品的信息披露情况，及时获取相关信息。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t六、本产品的测算收益不等于实际收益，投资须谨慎。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t七、投资人（甲方）与筹委会及授权机构（乙方）签署产品协议书等销售文件后，甲方同意乙方对投资资金的划拨事宜，筹委会及授权机构（乙方）在划款时，不再另行征得甲方的最后确认，可以直接划款。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:24px;font-family:&quot;\">\n" +
                "\t<b>风险提示：本产品有投资风险，投资人（甲方）应充分认识投资风险，谨慎投资。投资人（甲方）投资本产品可能存在但不限于以下风险：</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>1</b><b>、信用风险：</b>本产品开放、到期或项目方提前终止后对于本金和收益的支付由筹委会监督杭州迷彩狐网络科技有限公司完成。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>2</b><b>、利率风险：</b>本产品收益根据单次项目方案而定，支付周期基本固定，在单一的收益支付周期内收益率不随市场利率调整而变化，遇市场利率上调，本产品内在价值可能相对降低，建议投资人(甲方)在对自身收益目标、财务结构和市场利率走势进行慎重评估后谨慎购买。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>&nbsp;3</b><b>、流动性风险：</b>因本产品投资组合中的投资品种延期兑付本金和利息可能将导致本产品的投资人（甲方）本金和收益延期支付的情形。本产品投资管理团队将密切关注资产的变化，及时调整方案，以保障投资人（甲方）的利益\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>4</b><b>、政策风险：</b>本产品是针对当前的相关法规和政策设计的。如国家宏观政策以及市场相关法规政策发生变化，可能影响产品的受理、投资、偿还等工作的正常进行。本产品投资管理团队将密切关注国家金融政策的变化，以保障投资人（甲方）的利益。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>5</b><b>．管理风险：</b>由于受客观经验、技能等因素的限制，可能会影响本产品的管理，导致本产品项下的受托资金遭受损失。本产品管理团队在对项目方案进行投资时将严格按照本产品规定执行，严格按照中国银行业监督管理委员会的相关政策执行。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>6</b><b>、其它风险：</b>指由于自然灾害、战争、系统性故障等不可抗力因素的出现，将严重影响金融市场的正常运行，从而导致本产品资产收益降低或损失，甚至影响本产品的受理、投资、偿还等的正常进行，进而影响本产品的资产本金和收益安全。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>一、产品基本条款</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>1 </b><b>、产品名称：</b>迷彩狐众筹产品<b> &nbsp; &nbsp;</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>2 </b><b>、投资及收益币种：</b>人民币\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>3 </b><b>、产品类型：</b>收益型\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>4 </b><b>、产品运作起始日：</b>20&nbsp; 年&nbsp; 月&nbsp; 日\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>5 </b><b>、产品规模起购金：</b>2000元\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>6</b><b>、产品周期及收益返还方式：</b>一周（以实际期限为准）/一次性还本付息\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>7</b><b>、单一帐户投资金额限制：</b>20万\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>8 </b><b>、购买确认：</b>T日购买，T日日终扣款\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>9 </b><b>、赎回确认：</b>用户不可主动赎回，到期直接兑付至用户投资卡上\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>10 </b><b>、预期年收益率：</b>浮动\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>11</b><b>、收益计算基础：</b>实际投资天数/365，按照四舍五入的方式计息。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>12</b><b>、收益计算方式：</b>投资计划存续期内，每次投资计算\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>13</b><b>、众筹管理平台：</b>迷彩狐\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>二、产品的购买、退出</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:36px;font-family:&quot;\">\n" +
                "\t迷彩狐产品是指筹委会及授权机构（乙方）接受投资人（甲方）委托，将投资人（甲方）的闲置资金，根据投资人（甲方）要求转作投资，以获得投资收益回报的金融投资服务。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>1</b><b>、产品的签约购买</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:24px;font-family:&quot;\">\n" +
                "\t投资人（甲方）须在迷彩狐众筹平台上传本人有效身份证件进行实名认证，，选择并制定投资方案，在线签订《 “迷彩狐”产品认购协议书》进行投资，指定相联接的同名资金帐户。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t筹委会及授权机构（乙方）将在投资人（甲方）签订协议的当日，依照协议为投资人（甲方）自动生成认购业务。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>2</b><b>、产品的退出</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:24px;font-family:&quot;\">\n" +
                "\t“迷彩狐”产品到期或是根据筹委会及授权的迷彩狐网络科技有限公司项目提前终止等情况，该产品可退出。到期产品本金和收益的支付由筹委会监督杭州迷彩狐网络科技有限公司完成；提前终止的产品则由筹委会监督杭州迷彩狐网络科技有限公司将本金按照原资金流退还至用户投资卡。产品到期无需用户主动赎回，T日到期，T+3日到账。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:24px;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>三、本金和投资收益的计算及分配</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>1</b><b>、预期年化收益率：</b>本产品的预期年收益率容易受到实际投资运作情况以及投资管理方投资能力的影响，筹委会及授权机构（乙方）司将根据资金运作情况每单次投资公告单次收益率。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>2</b><b>、</b><b> </b><b>收益率测算依据：</b>本产品收益＝产品投资收益\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>3</b><b>、相关费用：</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t（1）购买、赎回费用：本产品无购买、到期赎回费用。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t（2）中途赎回费用：按照协议条款资金的赎回（退出）规定，不予以用户主动赎回的权利。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>四、产品的投资理念、投资范围</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t1、投资理念：安全性和流动性优先、追求适度收益。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t2、投资范围：迷彩狐另类投资方案\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>五、特别提示</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:24px;font-family:&quot;\">\n" +
                "\t本产品不同于银行存款、理财产品，不承诺保本保息。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:24px;font-family:&quot;\">\n" +
                "\t投资人（甲方）与筹委会及授权机构（乙方）已就上述产品达成一致，已阅读并领取“迷彩狐用户产品认购协议”，清楚知晓第2页的风险提示，明白投资风险，自愿购买。\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:24px;font-family:&quot;\">\n" +
                "\t【本协议以甲方通过线上合作平台在线点击确认的方式签署，认购时间以甲方在线勾选同意认购协议且购买成功的时间为准】\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;text-indent:24px;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>甲方</b><b>(</b><b>投资人</b><b>)</b><b>：</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<b>&nbsp;&nbsp;</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:Helvetica;\">\n" +
                "\t<b>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</b>\n" +
                "</p>\n" +
                "<p style=\"text-align:justify;font-family:&quot;\">\n" +
                "\t<b>乙方（签章）：</b>\n" +
                "</p>";
        buyAgreeWeb.setText(Html.fromHtml(content));
    }
    @OnClick(R.id.tv_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
