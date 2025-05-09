package com.huawei.ui.homewear21.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.nue;
import defpackage.pem;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class DirectConnectDeviceActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private pem f9638a = null;
    private c c = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_direct_connect_loading);
        LogUtil.a("DirectConnectDeviceActivity", "onCreate handle direct connect device.");
        d();
        pem d = pem.d();
        this.f9638a = d;
        d.dmN_(this);
        this.f9638a.g();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("DirectConnectDeviceActivity", "Enter onDestroy.");
        e();
        pem.d().b();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("DirectConnectDeviceActivity", "onActivityResult requestCode: ", Integer.valueOf(i), ", resultCode: ", Integer.valueOf(i2));
        if (i == 1 && i2 == 2 && intent != null) {
            nue.cNU_(intent, this, nue.e(i2, true, intent.getIntExtra("product_type", -1), true));
        } else {
            finish();
        }
    }

    static class c extends BroadcastReceiver {
        private WeakReference<DirectConnectDeviceActivity> c;

        c(DirectConnectDeviceActivity directConnectDeviceActivity) {
            this.c = new WeakReference<>(directConnectDeviceActivity);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("DirectConnectDeviceActivity", "onReceive intent is null.");
                return;
            }
            DirectConnectDeviceActivity directConnectDeviceActivity = this.c.get();
            if (directConnectDeviceActivity != null) {
                if (directConnectDeviceActivity.f9638a == null) {
                    LogUtil.h("DirectConnectDeviceActivity", "onReceive mDeviceManager is null.");
                    return;
                }
                if ("com.huawei.health.action.DOWNLOAD_SINGLE_DEVICE_PROCESS".equals(intent.getAction())) {
                    directConnectDeviceActivity.f9638a.b(intent.getIntExtra("progress", -1));
                    return;
                } else {
                    if ("com.huawei.health.action.ACTION_BACKGROUND_DOWNLOAD_DEVICE".equals(intent.getAction())) {
                        directConnectDeviceActivity.f9638a.c();
                        if (!directConnectDeviceActivity.f9638a.j()) {
                            directConnectDeviceActivity.a();
                            return;
                        } else {
                            directConnectDeviceActivity.f9638a.i();
                            return;
                        }
                    }
                    LogUtil.h("DirectConnectDeviceActivity", "onReceive intent: ", intent.getAction());
                    return;
                }
            }
            LogUtil.h("DirectConnectDeviceActivity", "onReceive activity is null.");
        }
    }

    private void d() {
        LogUtil.a("DirectConnectDeviceActivity", "Enter registerProcessBroadcast.");
        this.c = new c(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.action.DOWNLOAD_SINGLE_DEVICE_PROCESS");
        intentFilter.addAction("com.huawei.health.action.ACTION_BACKGROUND_DOWNLOAD_DEVICE");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.c, intentFilter);
    }

    public void e() {
        LogUtil.a("DirectConnectDeviceActivity", "Enter unregisterProcessBroadcast");
        if (this.c == null) {
            LogUtil.h("DirectConnectDeviceActivity", "unregisterProcessBroadcast mProcessBroadcastReceiver is null.");
            return;
        }
        try {
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.c);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DirectConnectDeviceActivity", "unregisterProcessBroadcast IllegalArgumentException.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("DirectConnectDeviceActivity", "Enter showNoSupportDevicePairDialog.");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(getResources().getString(R.string.IDS_qrcode_no_support_device_title));
        builder.e(getResources().getString(R.string.IDS_device_mgr_no_support_device_tips));
        builder.cyV_(getResources().getString(R.string._2130841794_res_0x7f0210c2), new View.OnClickListener() { // from class: com.huawei.ui.homewear21.home.DirectConnectDeviceActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DirectConnectDeviceActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (isFinishing()) {
            return;
        }
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
