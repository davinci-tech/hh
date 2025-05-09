package com.huawei.sim.multisim;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ktx;
import java.util.Locale;

/* loaded from: classes9.dex */
public class MultiSimAuth extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f8704a = null;
    private HealthTextView b;
    private HealthButton c;
    private HealthButton d;
    private CustomTitleBar e;
    private HealthTextView j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("MultiSimAuth", "onCreate");
        this.f8704a = this;
        setContentView(R.layout.activity_multi_sim_auth);
        b();
        c();
    }

    private void b() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.multi_sim_auth_title_bar);
        this.e = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.b = (HealthTextView) findViewById(R.id.multi_sim_auth_notice);
        this.j = (HealthTextView) findViewById(R.id.multi_sim_auth_tip);
        HealthButton healthButton = (HealthButton) findViewById(R.id.multi_sim_auth_agree);
        this.c = healthButton;
        healthButton.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.multi_sim_auth_cancel);
        this.d = healthButton2;
        healthButton2.setOnClickListener(this);
    }

    public static String c(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("MultiSimAuth", "getAppName NameNotFoundException");
            return "";
        }
    }

    private void c() {
        LogUtil.a("MultiSimAuth", "initAuthRequestView()");
        String format = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_plugin_multi_sim_get_device_info), c(this.f8704a, ktx.e().i()), ktx.e().f());
        String format2 = String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_plugin_multi_sim_get_device_tip), getResources().getString(R.string._2130847939_res_0x7f0228c3), c(this.f8704a, ktx.e().i()));
        this.b.setText(format);
        this.j.setText(format2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.a("MultiSimAuth", "onClick enter");
        Intent intent = getIntent();
        if (intent != null) {
            LogUtil.a("MultiSimAuth", intent.getAction());
            if (view.getId() == R.id.multi_sim_auth_agree) {
                LogUtil.a("MultiSimAuth", "onClick agree");
                ktx.e().e(true);
                if ("com.huawei.sim.multisim.MultiSimAuth".equals(intent.getAction())) {
                    d();
                } else {
                    finish();
                }
            } else if (view.getId() == R.id.multi_sim_auth_cancel) {
                LogUtil.a("MultiSimAuth", "onClick cancel");
                if ("com.huawei.sim.multisim.MultiSimAuth".equals(intent.getAction())) {
                    ktx.e().e(false);
                    d();
                } else {
                    a();
                }
            } else {
                LogUtil.h("MultiSimAuth", "onClick");
            }
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.h("MultiSimAuth", "onClick intent is null");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("MultiSimAuth", "onClick back button");
        Intent intent = getIntent();
        if (intent != null) {
            LogUtil.a("MultiSimAuth", "inEsim :", intent.getAction());
            if ("com.huawei.sim.multisim.MultiSimAuth".equals(intent.getAction())) {
                ktx.e().e(false);
                d();
                return;
            } else {
                a();
                return;
            }
        }
        LogUtil.h("MultiSimAuth", "onBackPressed intent is null");
    }

    private void d() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
        if (LocalBroadcastManager.getInstance(this.f8704a) != null) {
            LocalBroadcastManager.getInstance(this.f8704a).sendBroadcast(intent);
        }
    }

    private void a() {
        ktx.e().e(false);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
