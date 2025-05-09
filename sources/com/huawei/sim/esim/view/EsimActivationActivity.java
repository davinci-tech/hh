package com.huawei.sim.esim.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.sim.esim.qrcode.QrCodeActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.ktx;
import defpackage.ncf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
public class EsimActivationActivity extends BaseActivity {
    private ImageView c;
    private View d;
    private View e;
    private HealthButton f;
    private CustomTitleBar h;
    private Context i;
    private HealthTextView j;
    private View m;

    /* renamed from: a, reason: collision with root package name */
    private int f8674a = 3;
    private PermissionsResultAction n = null;
    private IBaseResponseCallback b = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimActivationActivity.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            EsimActivationActivity.this.g.removeMessages(6);
            if (i == 1) {
                LogUtil.c("EsimActivationActivity", "err_code DEVICE_CONNECTING");
                Message obtain = Message.obtain();
                obtain.what = 4;
                EsimActivationActivity.this.g.sendMessage(obtain);
                return;
            }
            if (i == 2) {
                LogUtil.c("EsimActivationActivity", "err_code DEVICE_CONNECTED");
                Message obtain2 = Message.obtain();
                obtain2.what = 2;
                EsimActivationActivity.this.g.sendMessage(obtain2);
                return;
            }
            Message obtain3 = Message.obtain();
            obtain3.what = 3;
            EsimActivationActivity.this.g.sendMessage(obtain3);
        }
    };
    private Handler g = new Handler() { // from class: com.huawei.sim.esim.view.EsimActivationActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.c("EsimActivationActivity", "message ", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        EsimActivationActivity.this.a();
                        return;
                    } else if (i != 6) {
                        LogUtil.c("EsimActivationActivity", "handleMessage default");
                        return;
                    }
                }
                EsimActivationActivity.this.c();
                return;
            }
            EsimActivationActivity.this.d();
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("EsimActivationActivity", "EsimActivationActivity enter");
        super.onCreate(bundle);
        setContentView(R.layout.layout_activate_esim);
        this.i = this;
        e();
        ktx.e().c(this.b);
        ktx.e().c();
        this.n = new CustomPermissionAction(this.i) { // from class: com.huawei.sim.esim.view.EsimActivationActivity.1
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("EsimActivationActivity", "mQrCodeAction onGranted");
                EsimActivationActivity.this.b();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("EsimActivationActivity", "mQrCodeAction onDenied");
                EsimActivationActivity.this.b();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("EsimActivationActivity", "mQrCodeAction onForeverDenied");
                EsimActivationActivity.this.b();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            Intent intent = new Intent();
            intent.setClass(this.i, QrCodeActivity.class);
            intent.putExtra("esim_new_original_key", 0);
            this.i.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("EsimActivationActivity", "onGranted ActivityNotFoundException");
        }
    }

    private void e() {
        this.h = (CustomTitleBar) findViewById(R.id.wirless_manager_title_bar);
        if (LanguageUtil.f(this.i)) {
            this.h.setTitleSize(15.0f);
        }
        this.f = (HealthButton) findViewById(R.id.open_esim_button);
        h();
        this.e = findViewById(R.id.bt_disconnect);
        this.m = findViewById(R.id.set_bt_reconnect);
        if (ncf.b()) {
            this.m.setVisibility(8);
        } else {
            this.m.setVisibility(0);
            this.m.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimActivationActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new Handler().post(new Runnable() { // from class: com.huawei.sim.esim.view.EsimActivationActivity.4.5
                        @Override // java.lang.Runnable
                        public void run() {
                            ncf.i(EsimActivationActivity.this);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        this.d = findViewById(R.id.bt_connecting);
        this.c = (ImageView) findViewById(R.id.bt_connecting_imgage);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.launch_tips);
        this.j = healthTextView;
        healthTextView.setText(String.format(Locale.ENGLISH, getString(R.string._2130848064_res_0x7f022940), UnitUtil.e(30.0d, 2, 0), UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0)));
    }

    private void h() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimActivationActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionUtil.b(EsimActivationActivity.this.i, PermissionUtil.PermissionType.CAMERA, EsimActivationActivity.this.n);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.c("EsimActivationActivity", "onResume");
        int c = ktx.e().c();
        this.f8674a = c;
        if (c == 2) {
            LogUtil.c("EsimActivationActivity", "btConnectView()");
            d();
        } else if (c == 1) {
            LogUtil.c("EsimActivationActivity", "btConnectingView()");
            a();
        } else {
            LogUtil.c("EsimActivationActivity", "btDisconnectView()");
            c();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ktx.e().e(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.d.setVisibility(8);
        this.e.setVisibility(0);
        this.f.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.f.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        this.f.setEnabled(false);
        this.c.startAnimation(AnimationUtils.loadAnimation(this, R.anim._2130771992_res_0x7f010018));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
