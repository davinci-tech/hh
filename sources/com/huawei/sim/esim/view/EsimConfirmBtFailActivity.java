package com.huawei.sim.esim.view;

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
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ktx;
import defpackage.ncf;
import defpackage.sqo;

/* loaded from: classes6.dex */
public class EsimConfirmBtFailActivity extends BaseActivity implements View.OnClickListener {
    private View b;
    private ImageView d;
    private View e;
    private String g;
    private CustomTitleBar h;
    private HealthButton i;
    private HealthTextView k;
    private String l;
    private ImageView n;
    private ImageView p;
    private HealthButton q;
    private HealthTextView r;
    private View t;

    /* renamed from: a, reason: collision with root package name */
    private int f8677a = 3;
    private CommonDialog21 m = null;
    private IBaseResponseCallback j = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimConfirmBtFailActivity.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("EsimConfirmBtFailActivity", "the errorCode ", Integer.valueOf(i));
        }
    };
    private Handler o = new Handler() { // from class: com.huawei.sim.esim.view.EsimConfirmBtFailActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("EsimConfirmBtFailActivity", "mHandler msg is null");
                return;
            }
            super.handleMessage(message);
            LogUtil.c("EsimConfirmBtFailActivity", "message ", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        EsimConfirmBtFailActivity.this.d();
                        return;
                    } else if (i != 6) {
                        LogUtil.h("EsimConfirmBtFailActivity", "mHandler default");
                        return;
                    }
                }
                EsimConfirmBtFailActivity.this.a();
                return;
            }
            EsimConfirmBtFailActivity.this.l = null;
            EsimConfirmBtFailActivity.this.b();
        }
    };
    private IBaseResponseCallback c = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimConfirmBtFailActivity.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            LogUtil.c("EsimConfirmBtFailActivity", "errorCode ", Integer.valueOf(i));
            EsimConfirmBtFailActivity.this.o.post(new Runnable() { // from class: com.huawei.sim.esim.view.EsimConfirmBtFailActivity.3.2
                @Override // java.lang.Runnable
                public void run() {
                    ncf.c(i, true);
                    int i2 = i;
                    if (i2 == 0) {
                        Intent intent = new Intent(EsimConfirmBtFailActivity.this, (Class<?>) EsimProfileSuccessActivity.class);
                        intent.putExtra("confirm_status", true);
                        EsimConfirmBtFailActivity.this.startActivity(intent);
                        EsimConfirmBtFailActivity.this.finish();
                    } else if (i2 == 1) {
                        EsimConfirmBtFailActivity.this.l = "network_failed";
                    } else if (i2 == 1 || i2 == 2) {
                        Intent intent2 = new Intent();
                        intent2.putExtra("confirm_report", 1);
                        EsimConfirmBtFailActivity.this.setResult(1, intent2);
                        EsimConfirmBtFailActivity.this.finish();
                    } else {
                        Object obj2 = obj;
                        if (obj2 != null && (obj2 instanceof Integer) && ((Integer) obj2).intValue() == -2) {
                            EsimConfirmBtFailActivity.this.e();
                            return;
                        }
                        sqo.o("mAuthBaseResponseCallback errorCode: " + i);
                        EsimConfirmBtFailActivity.this.b(i);
                    }
                    EsimConfirmBtFailActivity.this.e();
                }
            });
        }
    };
    private IBaseResponseCallback f = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimConfirmBtFailActivity.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("EsimConfirmBtFailActivity", "errorCode ", Integer.valueOf(i));
            EsimConfirmBtFailActivity.this.o.removeMessages(6);
            if (i == 1) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                EsimConfirmBtFailActivity.this.o.sendMessage(obtain);
            } else if (i == 2) {
                Message obtain2 = Message.obtain();
                obtain2.what = 2;
                EsimConfirmBtFailActivity.this.o.sendMessage(obtain2);
            } else {
                Message obtain3 = Message.obtain();
                obtain3.what = 3;
                EsimConfirmBtFailActivity.this.o.sendMessage(obtain3);
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_confirm_bt_fail);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("confirm_error");
        this.l = stringExtra;
        LogUtil.a("EsimConfirmBtFailActivity", "mErrorCode: ", stringExtra);
        this.g = intent.getStringExtra("confirm_code");
        HealthButton healthButton = (HealthButton) findViewById(R.id.esim_profile_cancel);
        this.i = healthButton;
        healthButton.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.esim_profile_retry);
        this.q = healthButton2;
        healthButton2.setText(R.string._2130847924_res_0x7f0228b4);
        this.q.setOnClickListener(this);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.profile_BT_fail_title_bar);
        this.h = customTitleBar;
        customTitleBar.setLeftButtonClickable(true);
        this.k = (HealthTextView) findViewById(R.id.esim_failed_info);
        this.r = (HealthTextView) findViewById(R.id.esim_success_info);
        this.p = (ImageView) findViewById(R.id.esim_status_success_image);
        this.n = (ImageView) findViewById(R.id.esim_status_image);
        this.e = findViewById(R.id.bt_disconnect);
        this.t = findViewById(R.id.set_bt_reconnect);
        if (ncf.b()) {
            this.t.setVisibility(8);
        } else {
            this.t.setVisibility(0);
            this.t.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimConfirmBtFailActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new Handler().post(new Runnable() { // from class: com.huawei.sim.esim.view.EsimConfirmBtFailActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ncf.i(EsimConfirmBtFailActivity.this);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        this.d = (ImageView) findViewById(R.id.bt_connecting_imgage);
        this.b = findViewById(R.id.bt_connecting);
        ktx.e().c(this.f);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        int c = ktx.e().c();
        this.f8677a = c;
        if (c == 2) {
            b();
        } else if (c == 1) {
            d();
        } else {
            a();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        e();
        super.onDestroy();
        ktx.e().e(this.f);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.esim_profile_cancel) {
            startActivity(new Intent(this, (Class<?>) ConfirmActivity.class));
            finish();
        } else if (view.getId() == R.id.esim_profile_retry) {
            d(R.string._2130847908_res_0x7f0228a4);
            ktx.e().e(this.g, 0, this.j, this.c);
        } else {
            LogUtil.h("EsimConfirmBtFailActivity", "onClick default");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        LogUtil.a("EsimConfirmBtFailActivity", "the error code is: ", Integer.valueOf(i));
        Intent intent = new Intent(this, (Class<?>) EsimConfirmInvailActivity.class);
        intent.putExtra("confirm_error", i);
        startActivity(intent);
        finish();
    }

    private void d(int i) {
        CommonDialog21 commonDialog21 = this.m;
        if (commonDialog21 == null) {
            new CommonDialog21(this, R.style.common_dialog21);
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.m = a2;
            a2.e(getResources().getString(i));
            this.m.setCancelable(false);
        } else {
            commonDialog21.e(getResources().getString(i));
        }
        this.m.a();
        LogUtil.a("EsimConfirmBtFailActivity", "mLoadingUserInformationDialog.show()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.b.setVisibility(8);
        this.e.setVisibility(0);
        this.q.setEnabled(false);
        this.k.setVisibility(0);
        this.k.setText(R.string._2130847920_res_0x7f0228b0);
        this.n.setVisibility(0);
        this.r.setVisibility(8);
        this.p.setVisibility(8);
        this.q.setBackgroundResource(R.drawable._2131431481_res_0x7f0b1039);
        this.q.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("EsimConfirmBtFailActivity", "enter dismissLoadingDialog()");
        if (isFinishing()) {
            LogUtil.h("EsimConfirmBtFailActivity", "dismissLoadingDialog isFinishing");
            return;
        }
        CommonDialog21 commonDialog21 = this.m;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.m.cancel();
        this.m = null;
        LogUtil.a("EsimConfirmBtFailActivity", "dismissLoadingDialog()!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.q.setEnabled(true);
        String str = this.l;
        if (str != null && "network_failed".equals(str)) {
            this.k.setVisibility(0);
            this.k.setText(R.string._2130847884_res_0x7f02288c);
            this.n.setVisibility(0);
            this.r.setVisibility(8);
            this.p.setVisibility(8);
        } else {
            this.k.setVisibility(8);
            this.n.setVisibility(8);
            this.r.setVisibility(0);
            this.p.setVisibility(0);
        }
        this.q.setBackgroundResource(R.drawable._2131431480_res_0x7f0b1038);
        this.q.setTextColor(getResources().getColor(R.color._2131296318_res_0x7f09003e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.b.setVisibility(0);
        this.e.setVisibility(8);
        this.d.startAnimation(AnimationUtils.loadAnimation(this, R.anim._2130771992_res_0x7f010018));
        this.k.setVisibility(0);
        this.k.setText(R.string._2130847920_res_0x7f0228b0);
        this.n.setVisibility(0);
        this.r.setVisibility(8);
        this.p.setVisibility(8);
        this.q.setEnabled(false);
        this.q.setBackgroundResource(R.drawable._2131431481_res_0x7f0b1039);
        this.q.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
