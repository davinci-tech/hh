package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.WifiDevicePressureCalibrateGuideActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.PressureCalibrateQuestionActivity;
import defpackage.csf;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.dcr;
import defpackage.dks;
import defpackage.jbs;
import defpackage.psm;
import defpackage.qyr;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class WifiDevicePressureCalibrateGuideActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f9868a;
    private CustomTitleBar b;
    private Context e;
    private c h;
    private String d = "";
    private String c = "";
    private View.OnClickListener j = new View.OnClickListener() { // from class: psv
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            WifiDevicePressureCalibrateGuideActivity.this.dtc_(view);
        }
    };
    private View.OnClickListener g = new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.WifiDevicePressureCalibrateGuideActivity.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (WifiDevicePressureCalibrateGuideActivity.this.d(ResourceManager.e().a().e(), HealthDevice.HealthDeviceKind.HDK_WEIGHT.name()) != null) {
                qyr.e();
                dks.e(WifiDevicePressureCalibrateGuideActivity.this.e, "HDK_WEIGHT");
                WifiDevicePressureCalibrateGuideActivity.this.finish();
            } else {
                LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "WEIGHT ProductGroup is error ");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    /* loaded from: classes6.dex */
    static class c extends StaticHandler<WifiDevicePressureCalibrateGuideActivity> {
        c(WifiDevicePressureCalibrateGuideActivity wifiDevicePressureCalibrateGuideActivity) {
            super(wifiDevicePressureCalibrateGuideActivity);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: dtd_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(WifiDevicePressureCalibrateGuideActivity wifiDevicePressureCalibrateGuideActivity, Message message) {
            if (wifiDevicePressureCalibrateGuideActivity.isFinishing() || wifiDevicePressureCalibrateGuideActivity.isDestroyed()) {
                LogUtil.b("WifiDevicePressureCalibrateGuideActivity", "WifiDevicePressureCalibrateGuideActivity is Destroyed");
                return;
            }
            LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "MyHandler what:", Integer.valueOf(message.what));
            if (message.what == 104) {
                if (message.obj == null) {
                    wifiDevicePressureCalibrateGuideActivity.e(null);
                    return;
                } else {
                    wifiDevicePressureCalibrateGuideActivity.e(String.valueOf(message.obj));
                    return;
                }
            }
            LogUtil.h("WifiDevicePressureCalibrateGuideActivity", "MyHandler what is other");
        }
    }

    public /* synthetic */ void dtc_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wifi_device_pressure_calibrate_guide);
        this.e = this;
        this.h = new c(this);
        psm.e().c(true);
        Intent intent = getIntent();
        if (intent != null) {
            this.d = intent.getStringExtra("health_wifi_device_userId");
            this.c = intent.getStringExtra("health_wifi_device_productId");
            dsZ_(intent);
        }
        this.b = (CustomTitleBar) findViewById(R.id.hw_wifi_device_pressure_calibrate_title_bar);
        this.f9868a = (HealthButton) findViewById(R.id.hw_wifi_device_pressure_calibrate_start_btn);
        d();
        a();
    }

    private void dsZ_(Intent intent) {
        Uri zs_;
        if (!TextUtils.isEmpty(this.c) || (zs_ = AppRouterUtils.zs_(intent)) == null) {
            return;
        }
        this.c = zs_.getQueryParameter("productId");
        this.d = zs_.getQueryParameter("useId");
        LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "init from uri");
    }

    private void d() {
        this.b.setLeftButtonClickable(true);
        this.b.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: pss
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDevicePressureCalibrateGuideActivity.this.dta_(view);
            }
        });
        this.f9868a.setOnClickListener(new View.OnClickListener() { // from class: psu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDevicePressureCalibrateGuideActivity.this.dtb_(view);
            }
        });
    }

    public /* synthetic */ void dta_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dtb_(View view) {
        LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "TO PressureCalibrateQuestionActivity time = ", Long.valueOf(System.currentTimeMillis()));
        b(PressureCalibrateQuestionActivity.class);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "keyCode = ", Integer.valueOf(i));
        if (i == 4) {
            finish();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void b(Class cls) {
        Intent intent = new Intent(this.e, (Class<?>) cls);
        intent.putExtra("health_device_type", "wifi_device");
        intent.putExtra("health_wifi_device_userId", this.d);
        intent.putExtra("health_wifi_device_productId", this.c);
        startActivity(intent);
        finish();
    }

    private void a() {
        LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "checkDevice() checkDevice mProductId:", this.c);
        final ctk e = ctq.e(this.c);
        if (e == null) {
            LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "checkDevice() mWiFiDevice is null");
            Message obtain = Message.obtain();
            obtain.what = 104;
            this.h.sendMessage(obtain);
            return;
        }
        WifiDeviceGetWifiDeviceInfoReq wifiDeviceGetWifiDeviceInfoReq = new WifiDeviceGetWifiDeviceInfoReq();
        wifiDeviceGetWifiDeviceInfoReq.setDevId(e.b().a());
        jbs.a(this.e).a(wifiDeviceGetWifiDeviceInfoReq, new ICloudOperationResult() { // from class: psr
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WifiDevicePressureCalibrateGuideActivity.this.a(e, (WifiDeviceGetWifiDeviceInfoRsp) obj, str, z);
            }
        });
    }

    public /* synthetic */ void a(ctk ctkVar, WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, String str, boolean z) {
        int i;
        String str2;
        if (!z) {
            if (wifiDeviceGetWifiDeviceInfoRsp != null) {
                i = wifiDeviceGetWifiDeviceInfoRsp.getResultCode().intValue();
                str2 = wifiDeviceGetWifiDeviceInfoRsp.getResultDesc();
                if (i == 112000000) {
                    LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "device already not exists");
                    Message obtain = Message.obtain();
                    obtain.obj = ctkVar.d();
                    obtain.what = 104;
                    this.h.sendMessage(obtain);
                }
            } else {
                i = Constants.CODE_UNKNOWN_ERROR;
                str2 = "unknown error";
            }
            LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "checkDevice() errCode = ", Integer.valueOf(i), ",resultDesc:", str2);
            return;
        }
        if (wifiDeviceGetWifiDeviceInfoRsp != null && wifiDeviceGetWifiDeviceInfoRsp.getDeviceDetailInfo() == null) {
            LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "checkDevice() device already not exists");
            Message obtain2 = Message.obtain();
            obtain2.obj = ctkVar.d();
            obtain2.what = 104;
            this.h.sendMessage(obtain2);
            return;
        }
        LogUtil.a("WifiDevicePressureCalibrateGuideActivity", "checkDevice() device already exists");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public dcr d(ArrayList<dcr> arrayList, String str) {
        Iterator<dcr> it = arrayList.iterator();
        dcr dcrVar = null;
        while (it.hasNext()) {
            dcr next = it.next();
            LogUtil.a("WifiDevicePressureCalibrateGuideActivity", " item.kind.name() = ", next.c().name(), " deviceType = ", str);
            if (next.c().name().equals(str)) {
                dcrVar = next;
            }
        }
        return dcrVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        this.h.removeCallbacksAndMessages(null);
        csf.LF_(this.e, str, this.j, this.g);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
