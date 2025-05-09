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
import com.huawei.sim.esim.qrcode.QrCodeActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ktx;
import defpackage.ncf;

/* loaded from: classes6.dex */
public class EsimProfileBtFailActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private View f8693a;
    private View b;
    private ImageView c;
    private HealthButton f;
    private ImageView g;
    private HealthTextView h;
    private HealthButton j;
    private int k;
    private HealthTextView l;
    private ImageView m;
    private View o;
    private int d = 3;
    private Handler i = new Handler() { // from class: com.huawei.sim.esim.view.EsimProfileBtFailActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("EsimProfileBtFailActivity", "message is null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 2) {
                LogUtil.c("EsimProfileBtFailActivity", "message MESSAGE_BT_CONNECTED");
                EsimProfileBtFailActivity.this.d();
                return;
            }
            if (i == 3) {
                LogUtil.c("EsimProfileBtFailActivity", "message MESSAGE_BT_DISCONNECTED");
                EsimProfileBtFailActivity.this.b();
            } else if (i == 4) {
                LogUtil.c("EsimProfileBtFailActivity", "message MESSAGE_BT_CONNECTING");
                EsimProfileBtFailActivity.this.a();
            } else if (i == 6) {
                LogUtil.c("EsimProfileBtFailActivity", "message MESSAGE_BT_RECONNECT_TIMEOUT");
                EsimProfileBtFailActivity.this.b();
            } else {
                LogUtil.h("EsimProfileBtFailActivity", "message default:", Integer.valueOf(message.what));
            }
        }
    };
    private IBaseResponseCallback e = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimProfileBtFailActivity.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            EsimProfileBtFailActivity.this.i.removeMessages(6);
            LogUtil.a("EsimProfileBtFailActivity", "errorCode:", Integer.valueOf(i));
            if (i == 1) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                EsimProfileBtFailActivity.this.i.sendMessage(obtain);
            } else if (i == 2) {
                Message obtain2 = Message.obtain();
                obtain2.what = 2;
                EsimProfileBtFailActivity.this.i.sendMessage(obtain2);
            } else {
                LogUtil.a("EsimProfileBtFailActivity", "errorCode other");
                Message obtain3 = Message.obtain();
                obtain3.what = 3;
                EsimProfileBtFailActivity.this.i.sendMessage(obtain3);
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_profile_bt_fail);
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getIntExtra("esim_new_original_key", 0);
        }
        HealthButton healthButton = (HealthButton) findViewById(R.id.esim_profile_cancel);
        this.j = healthButton;
        healthButton.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.esim_profile_retry);
        this.f = healthButton2;
        healthButton2.setOnClickListener(this);
        this.h = (HealthTextView) findViewById(R.id.esim_failed_info);
        this.g = (ImageView) findViewById(R.id.esim_image_failed);
        this.l = (HealthTextView) findViewById(R.id.esim_success_info);
        this.m = (ImageView) findViewById(R.id.esim_image_success);
        this.b = findViewById(R.id.bt_disconnect);
        this.o = findViewById(R.id.set_bt_reconnect);
        if (ncf.b()) {
            this.o.setVisibility(8);
        } else {
            this.o.setVisibility(0);
            this.o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimProfileBtFailActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new Handler().post(new Runnable() { // from class: com.huawei.sim.esim.view.EsimProfileBtFailActivity.3.4
                        @Override // java.lang.Runnable
                        public void run() {
                            ncf.i(EsimProfileBtFailActivity.this);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        this.c = (ImageView) findViewById(R.id.bt_connecting_imgage);
        this.f8693a = findViewById(R.id.bt_connecting);
        ktx.e().c(this.e);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        int c = ktx.e().c();
        this.d = c;
        if (c == 2) {
            d();
        } else if (c == 1) {
            LogUtil.a("EsimProfileBtFailActivity", "mBTStatus equals DEVICE_CONNECTING");
            a();
        } else {
            b();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ktx.e().e(this.e);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.esim_profile_cancel) {
            e();
        } else if (view.getId() == R.id.esim_profile_retry) {
            c();
        } else {
            LogUtil.h("EsimProfileBtFailActivity", "onClick other");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f8693a.setVisibility(8);
        this.b.setVisibility(8);
        this.f.setEnabled(true);
        this.h.setVisibility(8);
        this.g.setVisibility(8);
        this.l.setVisibility(0);
        this.m.setVisibility(0);
        this.f.setBackgroundResource(R.drawable._2131431480_res_0x7f0b1038);
        this.f.setTextColor(getResources().getColor(R.color._2131296318_res_0x7f09003e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.f8693a.setVisibility(8);
        this.b.setVisibility(0);
        this.f.setEnabled(false);
        this.h.setVisibility(0);
        this.g.setVisibility(0);
        this.l.setVisibility(8);
        this.m.setVisibility(8);
        this.f.setBackgroundResource(R.drawable._2131431481_res_0x7f0b1039);
        this.f.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.f8693a.setVisibility(0);
        this.b.setVisibility(8);
        this.c.startAnimation(AnimationUtils.loadAnimation(this, R.anim._2130771992_res_0x7f010018));
        this.h.setVisibility(0);
        this.g.setVisibility(0);
        this.l.setVisibility(8);
        this.m.setVisibility(8);
        this.f.setEnabled(false);
        this.f.setBackgroundResource(R.drawable._2131431481_res_0x7f0b1039);
        this.f.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
    }

    private void e() {
        Intent intent = new Intent();
        if (this.k == 1) {
            intent.setClass(this, EsimManagerActivity.class);
        } else {
            intent.setClass(this, EsimActivationActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void c() {
        Intent intent = new Intent(this, (Class<?>) QrCodeActivity.class);
        intent.putExtra("esim_new_original_key", this.k);
        startActivity(intent);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
