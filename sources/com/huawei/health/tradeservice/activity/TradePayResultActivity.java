package com.huawei.health.tradeservice.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.trade.datatype.PayLoadBean;
import com.huawei.trade.datatype.PayRequest;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gla;
import defpackage.ixx;
import defpackage.nla;
import defpackage.nsn;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes.dex */
public class TradePayResultActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private CustomTitleBar f3455a;
    private ImageView b;
    private Context c;
    private LinearLayout d;
    private LinearLayout e;
    private View f;
    private String g;
    private PayLoadBean h;
    private HealthButton i;
    private HealthTextView j;
    private String k;
    private HealthTextView l;
    private HealthButton m;
    private PayRequest o;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pay_success);
        this.c = this;
        e();
        d();
    }

    private void e() {
        this.f3455a = (CustomTitleBar) findViewById(R.id.trade_titlebar);
        this.f = findViewById(R.id.rl_hint);
        this.b = (ImageView) findViewById(R.id.img_hint);
        this.j = (HealthTextView) findViewById(R.id.tv_hint);
        this.d = (LinearLayout) findViewById(R.id.lin_content);
        this.l = (HealthTextView) findViewById(R.id.tv_price);
        HealthButton healthButton = (HealthButton) findViewById(R.id.btn_start_train);
        this.m = healthButton;
        healthButton.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.btn_view_order);
        this.i = healthButton2;
        healthButton2.setOnClickListener(this);
        this.e = (LinearLayout) findViewById(R.id.column_button);
        if (nsn.ag(this.c)) {
            ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
            layoutParams.width = nla.e(false, 3);
            this.e.setLayoutParams(layoutParams);
        }
    }

    private void d() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            LogUtil.a("TradePayResultActivity", "intent = null");
            a();
            return;
        }
        int i = extras.getInt("shoppingResult", -1);
        LogUtil.c("TradePayResultActivity", "requestCode:", Integer.valueOf(i));
        if (i != 0) {
            LogUtil.a("TradePayResultActivity", "requestCode is not ok");
            a();
            return;
        }
        this.g = extras.getString("orderCode");
        String string = extras.getString("payRequest");
        Gson gson = new Gson();
        try {
            PayRequest payRequest = (PayRequest) gson.fromJson(string, PayRequest.class);
            this.o = payRequest;
            if (payRequest == null) {
                LogUtil.a("TradePayResultActivity", "mPayRequest = null");
                a();
            } else {
                this.k = payRequest.getProductId();
                this.h = (PayLoadBean) gson.fromJson(this.o.getPayLoad(), PayLoadBean.class);
                b();
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.e("TradePayResultActivity", "initData JsonSyntaxException");
            a();
        }
    }

    private void b() {
        if (this.o == null) {
            LogUtil.a("TradePayResultActivity", "mPayRequest = null");
            a();
        } else {
            this.l.setText(String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R.string._2130844775_res_0x7f021c67), gla.a(this.h.getCurrency(), this.h.getMicroPrice() / 1000000.0f)));
            c();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || nsn.o()) {
            LogUtil.a("TradePayResultActivity", "onClick view is null or isFaskClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.btn_start_train) {
            finish();
        } else if (id == R.id.btn_view_order) {
            Intent intent = new Intent(this, (Class<?>) TradeOrderDetailActivity.class);
            intent.putExtra("orderCode", this.g);
            intent.putExtra("productId", this.k);
            intent.putExtra("pageFrom", true);
            this.c.startActivity(intent);
            finish();
        } else {
            LogUtil.c("TradePayResultActivity", "onClick not match");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        this.f3455a.setTitleText(getString(R.string._2130844776_res_0x7f021c68));
        this.d.setVisibility(8);
        this.f.setVisibility(0);
        this.b.setOnClickListener(this);
        this.j.setText(getString(R.string._2130844789_res_0x7f021c75));
    }

    private void c() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", this.h.getType());
        hashMap.put("name", this.h.getProductName());
        hashMap.put(ParsedFieldTag.PRICE, String.valueOf(this.h.getMicroPrice() / 1000000.0f));
        hashMap.put("id", this.h.getProductId());
        hashMap.put("discountPeriod", gla.a());
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.TRADE_PAY_SUCCESS.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
