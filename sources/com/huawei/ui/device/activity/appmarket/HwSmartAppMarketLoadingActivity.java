package com.huawei.ui.device.activity.appmarket;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.jiw;
import defpackage.sqo;

/* loaded from: classes6.dex */
public class HwSmartAppMarketLoadingActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9059a;
    private Context c;
    private CustomTextAlertDialog d;
    private d e = new d(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_smart_market_loading);
        this.c = this;
        a();
        e();
    }

    private void a() {
        ((HealthProgressBar) findViewById(R.id.app_market_progressbar)).setLayerType(1, null);
    }

    private void e() {
        this.f9059a = false;
        jiw.a().d(new PingCallback() { // from class: com.huawei.ui.device.activity.appmarket.HwSmartAppMarketLoadingActivity.1
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public void onPingResult(int i) {
                LogUtil.a("HwSmartAppMarketLoadingActivity", "pingDevice resultCode:", Integer.valueOf(i));
                if (!HwSmartAppMarketLoadingActivity.this.f9059a) {
                    if (HwSmartAppMarketLoadingActivity.this.e != null) {
                        HwSmartAppMarketLoadingActivity.this.e.removeMessages(1);
                    }
                    if (HwSmartAppMarketLoadingActivity.this.isFinishing() || HwSmartAppMarketLoadingActivity.this.isDestroyed()) {
                        LogUtil.a("HwSmartAppMarketLoadingActivity", "pingDevice canceled");
                        return;
                    }
                    if (i != 207 && i != 202) {
                        HwSmartAppMarketLoadingActivity.this.d();
                        return;
                    }
                    jiw.a().d(0);
                    HwSmartAppMarketLoadingActivity.this.finish();
                    HwSmartAppMarketLoadingActivity.this.overridePendingTransition(0, 0);
                    return;
                }
                LogUtil.a("HwSmartAppMarketLoadingActivity", "pingDevice time out");
                sqo.e("pingDevice time out");
            }
        });
        d dVar = this.e;
        if (dVar != null) {
            dVar.sendEmptyMessageDelayed(1, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("HwSmartAppMarketLoadingActivity", "showMarketOverTimeDialog");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.c);
        builder.b(R.string._2130843410_res_0x7f021712);
        builder.d(R.string.IDS_sms_get_device_failed);
        builder.cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.appmarket.HwSmartAppMarketLoadingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwSmartAppMarketLoadingActivity", "showMarketOverTimeDialog click button known");
                HwSmartAppMarketLoadingActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.d = a2;
        a2.setCancelable(false);
        if (this.d.isShowing()) {
            return;
        }
        this.d.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("HwSmartAppMarketLoadingActivity", "onDestroy");
        super.onDestroy();
        this.d = null;
    }

    static class d extends BaseHandler<HwSmartAppMarketLoadingActivity> {
        public d(HwSmartAppMarketLoadingActivity hwSmartAppMarketLoadingActivity) {
            super(hwSmartAppMarketLoadingActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cPk_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HwSmartAppMarketLoadingActivity hwSmartAppMarketLoadingActivity, Message message) {
            if (message.what == 1) {
                LogUtil.a("HwSmartAppMarketLoadingActivity", "visit webView timeout");
                hwSmartAppMarketLoadingActivity.f9059a = true;
                hwSmartAppMarketLoadingActivity.d();
                return;
            }
            LogUtil.h("HwSmartAppMarketLoadingActivity", "handleMessageWhenReferenceNotNull default what");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
