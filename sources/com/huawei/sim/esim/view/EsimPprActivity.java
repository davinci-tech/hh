package com.huawei.sim.esim.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ktx;
import defpackage.ncf;

/* loaded from: classes6.dex */
public class EsimPprActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f8688a;
    private CustomTitleBar b;
    private HealthButton d;
    private LinearLayout f;
    private boolean h;
    private LinearLayout j;
    private byte[] o;
    private CommonDialog21 i = null;
    private boolean g = false;
    private IBaseResponseCallback c = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimPprActivity.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("EsimPprActivity", "errorCode ", Integer.valueOf(i));
            Message obtain = Message.obtain();
            obtain.what = 0;
            obtain.arg1 = i;
            if (i != 0 || !EsimPprActivity.this.g) {
                EsimPprActivity.this.e.sendMessage(obtain);
            } else {
                EsimPprActivity.this.e.sendMessageDelayed(obtain, 5000L);
            }
        }
    };
    private Handler e = new Handler() { // from class: com.huawei.sim.esim.view.EsimPprActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("EsimPprActivity", "mHandler message is null");
                return;
            }
            super.handleMessage(message);
            LogUtil.c("EsimPprActivity", "message ", Integer.valueOf(message.what));
            if (message.what == 0) {
                EsimPprActivity.this.e(message.arg1);
            } else {
                LogUtil.h("EsimPprActivity", "handleMessage default");
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ppr_rules);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        try {
            this.o = intent.getByteArrayExtra("profile_policy_rules");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("EsimPprActivity", "onCreate ArrayIndexOutOfBoundsException");
        }
        boolean booleanExtra = intent.getBooleanExtra("confirm_status", false);
        this.h = booleanExtra;
        LogUtil.a("EsimPprActivity", "the mIsNeedConfirm: ", Boolean.valueOf(booleanExtra));
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ppr_title_bar);
        this.b = customTitleBar;
        customTitleBar.setTitleText(getResources().getString(R.string._2130847900_res_0x7f02289c));
        this.b.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimPprActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EsimPprActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        c();
        HealthButton healthButton = (HealthButton) findViewById(R.id.cancel_button);
        this.f8688a = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimPprActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EsimPprActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        e();
        this.g = ncf.b();
    }

    private void c() {
        HealthButton healthButton = (HealthButton) findViewById(R.id.confirm_button);
        this.d = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimPprActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EsimPprActivity.this.g) {
                    if (EsimPprActivity.this.h) {
                        EsimPprActivity.this.startActivity(new Intent(EsimPprActivity.this, (Class<?>) ConfirmActivity.class));
                        EsimPprActivity.this.finish();
                    } else {
                        ktx.e().e(null, 0, null, EsimPprActivity.this.c);
                        EsimPprActivity.this.a(R.string._2130847931_res_0x7f0228bb);
                    }
                } else {
                    if (EsimPprActivity.this.h) {
                        EsimPprActivity.this.startActivity(new Intent(EsimPprActivity.this, (Class<?>) ConfirmActivity.class));
                    } else {
                        ktx.e().e(null, 0, EsimPprActivity.this.c, null);
                        EsimPprActivity.this.a(R.string._2130847931_res_0x7f0228bb);
                    }
                    EsimPprActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e() {
        this.f = (LinearLayout) findViewById(R.id.ll_ppr1_info);
        this.j = (LinearLayout) findViewById(R.id.ll_ppr2_info);
        byte[] bArr = this.o;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        byte b = bArr[0];
        if (b == 64 || b == -64) {
            LogUtil.a("EsimPprActivity", "mPprTypes[0] == 0x40 || mPprTypes[0] == 0xC0");
            this.f.setVisibility(0);
            return;
        }
        if (b == 32 || b == -96) {
            LogUtil.a("EsimPprActivity", "mPprTypes[0] == 0x20 || mPprTypes[0] == 0xA0");
            this.j.setVisibility(0);
        } else {
            if (b == 96 || b == -32) {
                LogUtil.a("EsimPprActivity", "mPprTypes[0] == 0x60 || mPprTypes[0] == 0xE0");
                this.f.setVisibility(0);
                this.j.setVisibility(0);
                return;
            }
            LogUtil.a("EsimPprActivity", "initPprTypes default");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (this.h) {
            LogUtil.c("EsimPprActivity", "commandResult errorcode ", Integer.valueOf(i));
            d();
            return;
        }
        ncf.c(i, true);
        if (i == 0) {
            Intent intent = new Intent(this, (Class<?>) EsimProfileSuccessActivity.class);
            intent.putExtra("confirm_status", false);
            startActivity(intent);
        } else if (ktx.e().c() == 2) {
            LogUtil.h("EsimPprActivity", "DeviceConnectState.DEVICE_CONNECTED");
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        CommonDialog21 commonDialog21 = this.i;
        if (commonDialog21 == null) {
            new CommonDialog21(this, R.style.common_dialog21);
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.i = a2;
            a2.e(getResources().getString(i));
            this.i.setCancelable(false);
        } else {
            commonDialog21.e(getResources().getString(i));
        }
        this.i.a();
        LogUtil.a("EsimPprActivity", "mLoadingUserInformationDialog.show()");
    }

    private void d() {
        CommonDialog21 commonDialog21;
        LogUtil.a("EsimPprActivity", "enter dismissLoadingDialog()");
        if (isFinishing() || (commonDialog21 = this.i) == null || !commonDialog21.isShowing()) {
            return;
        }
        LogUtil.a("EsimPprActivity", "dismissLoadingDialog()!");
        this.i.cancel();
        this.i = null;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
