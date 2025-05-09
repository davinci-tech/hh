package com.huawei.ui.commonui.webview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwnetworkmodel.NetworkStatusListener;
import com.huawei.ui.commonui.R$anim;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class NetworkExceptionShowActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f8989a;
    private Context b;
    private NetworkStatusListener c;
    private boolean d = false;
    private CustomTitleBar e;
    private String g;
    private HealthTextView h;
    private HealthButton i;
    private ImageView j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(R$anim.activity_no_animation, R$anim.activity_no_animation);
        setContentView(R.layout.network_exception_error);
        this.b = this;
        a();
        b();
        d();
    }

    private void a() {
        this.e = (CustomTitleBar) findViewById(R.id.titlebar_panel);
        this.j = (ImageView) findViewById(R.id.set_network_info_image);
        this.h = (HealthTextView) findViewById(R.id.tv_network_info);
        this.i = (HealthButton) findViewById(R.id.btn_set_network);
    }

    private void b() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("NetworkExceptionShowActivity", "intent is null.");
            return;
        }
        this.g = intent.getStringExtra("extraTitleBarText");
        this.f8989a = intent.getStringExtra("className");
        this.d = intent.getBooleanExtra("server_exception_flag", false);
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            this.d = false;
        }
        if (TextUtils.isEmpty(this.g)) {
            this.g = getString(R$string.IDS_hwh_home_group_network_error);
        }
        this.e.setTitleText(this.g);
        if (this.d) {
            this.h.setText(R$string.IDS_hwh_home_group_server_exception);
            this.i.setVisibility(8);
        }
    }

    private void d() {
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.webview.NetworkExceptionShowActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetworkExceptionShowActivity networkExceptionShowActivity = NetworkExceptionShowActivity.this;
                networkExceptionShowActivity.d(networkExceptionShowActivity.c());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.webview.NetworkExceptionShowActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetworkExceptionShowActivity networkExceptionShowActivity = NetworkExceptionShowActivity.this;
                networkExceptionShowActivity.d(networkExceptionShowActivity.c());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.webview.NetworkExceptionShowActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                intent.setClassName(BaseApplication.getAppPackage(), NetworkExceptionShowActivity.this.f8989a);
                NetworkExceptionShowActivity.this.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        if (this.d) {
            return;
        }
        if (z) {
            if (TextUtils.isEmpty(this.f8989a)) {
                LogUtil.h("NetworkExceptionShowActivity", "refreshActivityData mLastClassName is empty.");
                return;
            }
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getAppPackage(), this.f8989a);
            intent.setFlags(603979776);
            startActivity(intent);
            finish();
            overridePendingTransition(R$anim.activity_no_animation, R$anim.activity_no_animation);
            return;
        }
        Toast.makeText(BaseApplication.getContext(), this.b.getResources().getString(R$string.IDS_connect_error), 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        return CommonUtil.aa(BaseApplication.getContext());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        NetworkStatusListener networkStatusListener = this.c;
        if (networkStatusListener != null) {
            networkStatusListener.unregister(BaseApplication.getContext());
            this.c = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
