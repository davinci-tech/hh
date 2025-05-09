package com.huawei.health.tradeservice.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.TradeViewApi;
import com.huawei.ui.commonui.base.BaseActivity;
import health.compact.a.Services;

/* loaded from: classes.dex */
public class TradeOrderListActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f3454a;
    private Context d;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_order_list);
        clearBackgroundDrawable();
        this.d = this;
        e();
        c();
    }

    private void e() {
        this.f3454a = (LinearLayout) findViewById(R.id.lin_content);
    }

    private void c() {
        LogUtil.a("TradeOrderListActivity", "addTradeView");
        this.f3454a.removeAllViews();
        Intent intent = getIntent();
        int intExtra = intent != null ? intent.getIntExtra("PAGE_TYPE", 1) : 1;
        LogUtil.a("TradeOrderListActivity", "pageType = ", Integer.valueOf(intExtra));
        TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
        if (tradeViewApi == null) {
            LogUtil.b("TradeOrderListActivity", "tradeViewApi is null.");
            return;
        }
        View orderVeiw = tradeViewApi.getOrderVeiw(this.d, intExtra);
        orderVeiw.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f3454a.addView(orderVeiw);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
