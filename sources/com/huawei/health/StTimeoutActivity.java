package com.huawei.health;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.StTimeoutActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import health.compact.a.CommonLibUtil;
import java.util.Locale;

/* loaded from: classes8.dex */
public class StTimeoutActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    private Context f2183a = null;
    private LocalBroadcastManager b = null;
    private final BroadcastReceiver e = new BroadcastReceiver() { // from class: com.huawei.health.StTimeoutActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("Login_StTimeoutActivity", "onReceive");
            StTimeoutActivity.this.finish();
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        LogUtil.a("Login_StTimeoutActivity", "onCreate()");
        super.onCreate(bundle);
        Window window = getWindow();
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        this.f2183a = this;
        this.b = LocalBroadcastManager.getInstance(getApplicationContext());
        e();
        d();
        c();
    }

    private void d() {
        LogUtil.a("Login_StTimeoutActivity", "initView()");
        a();
    }

    private void e() {
        LogUtil.a("Login_StTimeoutActivity", "registerWaitDialogBroadcast()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("hwid_finish");
        LocalBroadcastManager localBroadcastManager = this.b;
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(this.e, intentFilter);
        }
    }

    private void b() {
        LogUtil.a("Login_StTimeoutActivity", "unregisterWaitDialogBroadcast()");
        try {
            LocalBroadcastManager localBroadcastManager = this.b;
            if (localBroadcastManager != null) {
                localBroadcastManager.unregisterReceiver(this.e);
            }
        } catch (IllegalArgumentException e) {
            LogUtil.b("Login_StTimeoutActivity", e.getMessage());
        } catch (RuntimeException e2) {
            LogUtil.b("Login_StTimeoutActivity", e2.getMessage());
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        LogUtil.a("Login_StTimeoutActivity", "onDestroy()");
        super.onDestroy();
        b();
    }

    private void a() {
        LogUtil.a("Login_StTimeoutActivity", "initConfirmDialog()");
        String upperCase = this.f2183a.getString(R.string._2130837648_res_0x7f020090).toUpperCase(Locale.getDefault());
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.f2183a);
        builder.b(this.f2183a.getString(R.string._2130837692_res_0x7f0200bc));
        builder.e(this.f2183a.getString(R.string._2130841725_res_0x7f02107d));
        builder.cyV_(upperCase, new View.OnClickListener() { // from class: bys
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StTimeoutActivity.this.BW_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void BW_(View view) {
        LogUtil.a("Login_StTimeoutActivity", "Enter sure()");
        LoginInit.getInstance(this.f2183a).cleanLoginData();
        CommonLibUtil.d(this.f2183a);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c() {
        LogUtil.a("Login_StTimeoutActivity", "reportErroeCode().");
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_AUTH_FAILED_85070021.value(), intent.getIntExtra("rspErrorCode", 0));
    }

    @Override // android.app.Activity
    protected void onResume() {
        LogUtil.a("Login_StTimeoutActivity", "onResume()");
        super.onResume();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
