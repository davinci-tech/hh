package com.huawei.health.tradeservice.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.tradeservice.cloud.ProductManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.TimeLimitedPromotion;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gla;
import defpackage.gle;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class TradeSureOrderActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3456a;
    private LinearLayout b;
    private Context c;
    private HealthTextView d;
    private Handler e;
    private HealthTextView f;
    private ImageView g;
    private String h;
    private String i;
    private HealthTextView j;
    private gle k;
    private Product l;
    private View m;
    private RelativeLayout n;
    private HealthButton o;
    private HealthTextView q;
    private HealthTextView s;

    static class d extends BaseHandler<TradeSureOrderActivity> {
        d(TradeSureOrderActivity tradeSureOrderActivity) {
            super(tradeSureOrderActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aNw_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TradeSureOrderActivity tradeSureOrderActivity, Message message) {
            int i = message.what;
            if (i == 1101) {
                tradeSureOrderActivity.e();
            } else if (i == 1102) {
                tradeSureOrderActivity.a();
            } else {
                LogUtil.h("TradeInformationLayout", "handleMessageWhenReferenceNotNull not match ");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sure_trade_order);
        if (getIntent() == null) {
            LogUtil.h("TradeInformationLayout", "getIntent() = null");
            finish();
            return;
        }
        this.h = getIntent().getStringExtra("productId");
        this.i = getIntent().getStringExtra("productInfo");
        this.e = new d(this);
        this.c = this;
        c();
        d();
    }

    private void c() {
        this.m = findViewById(R.id.rl_hint);
        this.g = (ImageView) findViewById(R.id.img_goods);
        this.q = (HealthTextView) findViewById(R.id.tv_show_price);
        this.j = (HealthTextView) findViewById(R.id.tv_procuct_name);
        this.o = (HealthButton) findViewById(R.id.btn_submit);
        this.s = (HealthTextView) findViewById(R.id.tv_pay_price);
        this.d = (HealthTextView) findViewById(R.id.tv_end_time);
        this.f3456a = (HealthTextView) findViewById(R.id.tv_lin_price);
        this.d.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.n = (RelativeLayout) findViewById(R.id.rl_content);
        this.f = (HealthTextView) findViewById(R.id.tv_show_quantity);
        this.b = (LinearLayout) findViewById(R.id.lin_countdown);
        this.o.setOnClickListener(this);
    }

    private void d() {
        if (!CommonUtil.aa(this.c.getApplicationContext())) {
            a();
        } else {
            ProductManager.getProductDetails(this.h, new IBaseResponseCallback() { // from class: com.huawei.health.tradeservice.activity.TradeSureOrderActivity.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0 && (obj instanceof Product)) {
                        LogUtil.h("TradeInformationLayout", "getProductInfo success");
                        TradeSureOrderActivity.this.l = (Product) obj;
                        TradeSureOrderActivity.this.e.sendEmptyMessage(1101);
                        return;
                    }
                    LogUtil.h("TradeInformationLayout", "getProductSummaryInfo fail errorCode = ", Integer.valueOf(i));
                    TradeSureOrderActivity.this.e.sendEmptyMessage(1102);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.n.setVisibility(0);
        this.m.setVisibility(8);
        this.f.setText(String.format(getString(R.string._2130844798_res_0x7f021c7e), 1));
        nrf.cIU_(this.l.getProductUrl(), this.g, (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238));
        this.j.setText(this.l.getProductName());
        String currency = this.l.getCurrency();
        String a2 = gla.a(currency, this.l.getMicroPrice() / 1000000.0f);
        this.q.setText(a2);
        gla.aNL_(a2, gla.b(currency), this.s);
        b(this.l.getGiveaways());
        d(currency);
    }

    private void d(String str) {
        TimeLimitedPromotion promotion = this.l.getPromotion();
        this.b.setVisibility(8);
        if (promotion != null && promotion.getMicroOriginPrice() > 0) {
            this.f3456a.setText(gla.a(str, promotion.getMicroOriginPrice() / 1000000.0f));
            this.f3456a.getPaint().setFlags(16);
        }
        if (this.l.getSaleTime() - this.l.getNowTime() > 0) {
            this.b.setVisibility(0);
            String a2 = UnitUtil.a("yyyy/MM/dd HH:mm:ss", this.l.getSaleTime());
            gla.aNK_(String.format(Locale.ENGLISH, getResources().getString(R.string._2130844780_res_0x7f021c6c), a2), a2, this.d, R.color.emui_accent);
            return;
        }
        TimeLimitedPromotion promotion2 = this.l.getPromotion();
        if (promotion2 == null) {
            LogUtil.a("TradeInformationLayout", "do not need show promotin");
            return;
        }
        long promotionEndTime = promotion2.getPromotionEndTime() - this.l.getNowTime();
        if (promotionEndTime > 0) {
            this.b.setVisibility(0);
            gle gleVar = new gle(this.e, this.d, promotionEndTime);
            this.k = gleVar;
            gleVar.c();
        }
    }

    private void b(List<Product> list) {
        if (koq.b(list)) {
            LogUtil.a("TradeInformationLayout", "giveaways is empty");
            return;
        }
        ViewStub viewStub = (ViewStub) findViewById(R.id.viewstub_giveaway);
        if (nsn.p()) {
            viewStub = (ViewStub) findViewById(R.id.viewstub_giveaway_large);
        }
        viewStub.inflate();
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.tv_giveaway_title);
        healthTextView.setText(LanguageUtil.m(this.c) ? "【" + this.c.getString(R.string._2130844782_res_0x7f021c6e) + "】" : this.c.getString(R.string._2130844782_res_0x7f021c6e));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin_giveaway);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.lin_combine_product);
        linearLayout.removeAllViews();
        for (Product product : list) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, nsn.c(this, 32.0f));
            View inflate = View.inflate(this, R.layout.trade_giveaway_item_sure, null);
            if (nsn.p()) {
                inflate = View.inflate(this, R.layout.trade_giveaway_item_sure_large, null);
            }
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.tv_giveaway_name);
            ((HealthTextView) inflate.findViewById(R.id.tv_giveaway_quantity)).setText(String.format(getString(R.string._2130844798_res_0x7f021c7e), 1));
            healthTextView2.setText(product.getProductName());
            inflate.setLayoutParams(layoutParams);
            if (TextUtils.equals(product.getAttachType(), "giveaway")) {
                healthTextView.setVisibility(0);
                linearLayout.addView(inflate);
            } else {
                linearLayout2.addView(inflate);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.h("TradeInformationLayout", "button click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.rl_hint) {
            d();
        } else if (view.getId() == R.id.btn_submit) {
            b();
        } else {
            LogUtil.h("TradeInformationLayout", "onClick not match");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        ((PayApi) Services.a("TradeService", PayApi.class)).buyByProductId(this, 0, this.i);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.n.setVisibility(8);
        this.m.setVisibility(0);
        this.m.setOnClickListener(this);
        ((HealthTextView) findViewById(R.id.tv_hint)).setText(getString(R.string._2130842061_res_0x7f0211cd));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        gle gleVar = this.k;
        if (gleVar != null) {
            gleVar.e();
        } else {
            LogUtil.h("TradeInformationLayout", "mTimeUtils = null");
        }
        LogUtil.a("TradeInformationLayout", "onDestroy");
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
