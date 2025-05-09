package com.huawei.ui.device.activity.finddevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cvc;
import defpackage.cvn;
import defpackage.cvx;
import defpackage.cwf;
import defpackage.cwi;
import defpackage.jfu;
import defpackage.jgv;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;

/* loaded from: classes6.dex */
public class FindDeviceActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9092a;
    private DeviceInfo b;
    private boolean e;
    private ImageView f;
    private FindDeviceDynamicImageView g;
    private ImageView h;
    private HealthTextView i;
    private NoTitleCustomAlertDialog k;
    private String l;
    private HealthTextView m;
    private RelativeLayout n;
    private String o;
    private ImageView p;
    private String r;
    private String s;
    private HealthTextView t;
    private Context c = null;
    private final BroadcastReceiver j = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceActivity.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("FindDeviceActivity", "mDeviceStatusReceiver onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                LogUtil.h("FindDeviceActivity", "mDeviceStatusReceiver onReceive action is null");
                return;
            }
            LogUtil.a("FindDeviceActivity", "mDeviceStatusReceiver onReceive action :", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
                if (deviceInfo == null || FindDeviceActivity.this.b == null || !deviceInfo.getDeviceIdentify().equals(FindDeviceActivity.this.b.getDeviceIdentify())) {
                    LogUtil.h("FindDeviceActivity", "mDeviceStatusReceiver not current device");
                    return;
                }
                jgv.c().e();
                LogUtil.a("FindDeviceActivity", "device connectionState is ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                if (deviceInfo.getDeviceConnectState() != 2) {
                    FindDeviceActivity.this.j();
                } else if (deviceInfo.getDeviceConnectState() == 2) {
                    FindDeviceActivity.this.g();
                }
            }
        }
    };
    private Handler d = new Handler() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("FindDeviceActivity", "handleMessage message is null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 2) {
                FindDeviceActivity.this.n();
                if (!FindDeviceActivity.this.e) {
                    FindDeviceActivity.this.t.setText(R.string._2130846101_res_0x7f022195);
                    return;
                } else {
                    FindDeviceActivity.this.t.setText(R.string._2130845225_res_0x7f021e29);
                    return;
                }
            }
            if (i == 3) {
                FindDeviceActivity.this.l();
                return;
            }
            if (i == 4) {
                FindDeviceActivity.this.b(4);
            } else if (i == 5) {
                FindDeviceActivity.this.t.setText("");
            } else {
                LogUtil.h("FindDeviceActivity", "handler message default");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.f9092a.setVisibility(8);
        this.p.setVisibility(0);
        this.n.setAlpha(1.0f);
        this.n.setClickable(true);
        if (!this.e) {
            this.f.setBackground(this.c.getResources().getDrawable(R.drawable._2131430658_res_0x7f0b0d02));
        } else {
            this.f.setBackground(this.c.getResources().getDrawable(R.mipmap._2131821462_res_0x7f110396));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.k;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.k.dismiss();
        }
        this.h.setVisibility(8);
        this.g.setVisibility(8);
        this.p.setVisibility(0);
        this.f9092a.setVisibility(0);
        this.t.setText("");
        this.m.setText(this.b.getDeviceName());
        if (!this.e) {
            this.i.setText(this.l);
            this.f.setBackground(this.c.getResources().getDrawable(R.drawable._2131430658_res_0x7f0b0d02));
        } else {
            this.i.setText(this.o);
            this.f.setBackground(this.c.getResources().getDrawable(R.mipmap._2131821464_res_0x7f110398));
        }
        this.n.setBackground(this.c.getResources().getDrawable(R.drawable._2131432057_res_0x7f0b1279));
        this.i.setTextColor(this.c.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.n.setAlpha(0.4f);
        this.n.setClickable(false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this;
        LogUtil.a("FindDeviceActivity", "onCreate()");
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("FindDeviceActivity", "onCreate intent is null");
            return;
        }
        Parcelable parcelableExtra = intent.getParcelableExtra("device");
        if (parcelableExtra instanceof DeviceInfo) {
            this.b = (DeviceInfo) parcelableExtra;
        }
        if (this.b == null) {
            LogUtil.h("FindDeviceActivity", "onCreate mDeviceInfo is null");
            return;
        }
        setContentView(R.layout.activity_find_device);
        a();
        jgv.c().e();
        e();
        i();
    }

    private void e() {
        LogUtil.a("FindDeviceActivity", "Enter initView!");
        this.i = (HealthTextView) nsy.cMc_(this, R.id.device_find);
        this.p = (ImageView) nsy.cMc_(this, R.id.picture);
        this.h = (ImageView) nsy.cMc_(this, R.id.dynamic_picture);
        this.m = (HealthTextView) nsy.cMc_(this, R.id.device_name);
        this.t = (HealthTextView) nsy.cMc_(this, R.id.device_tips);
        this.g = (FindDeviceDynamicImageView) nsy.cMc_(this, R.id.scan_radar);
        this.n = (RelativeLayout) nsy.cMc_(this, R.id.device_find_all);
        this.f = (ImageView) nsy.cMc_(this, R.id.icon);
        this.f9092a = (RelativeLayout) nsy.cMc_(this, R.id.find_error_layout);
        d();
        this.p.setVisibility(0);
        this.m.setText(this.b.getDeviceName());
        boolean z = !cwi.c(this.b, 135);
        this.e = z;
        LogUtil.a("FindDeviceActivity", "isDeviceSupportSpeaker: ", Boolean.valueOf(z));
        this.o = this.c.getResources().getString(R.string._2130845278_res_0x7f021e5e);
        this.s = this.c.getResources().getString(R.string._2130845279_res_0x7f021e5f);
        this.l = this.c.getResources().getString(R.string._2130845819_res_0x7f02207b);
        this.r = this.c.getResources().getString(R.string._2130846098_res_0x7f022192);
        if (!this.e) {
            this.i.setText(this.l);
            this.f.setBackground(this.c.getResources().getDrawable(R.drawable._2131430658_res_0x7f0b0d02));
        } else {
            this.i.setText(this.o);
        }
        b();
    }

    private void b() {
        this.n.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.o()) {
                    String obj = FindDeviceActivity.this.i.getText().toString();
                    if (FindDeviceActivity.this.o.equalsIgnoreCase(obj) || FindDeviceActivity.this.l.equalsIgnoreCase(obj)) {
                        if (FindDeviceActivity.this.e) {
                            FindDeviceActivity.this.f();
                        } else {
                            FindDeviceActivity.this.m();
                        }
                    } else if (FindDeviceActivity.this.s.equalsIgnoreCase(obj) || FindDeviceActivity.this.r.equalsIgnoreCase(obj)) {
                        FindDeviceActivity.this.b(1);
                    } else {
                        LogUtil.h("FindDeviceActivity", "onClick error content");
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("FindDeviceActivity", "onClick isFastClick");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.h.setVisibility(8);
        this.g.setVisibility(8);
        this.p.setVisibility(0);
        this.m.setText(this.b.getDeviceName());
        this.t.setText("");
        if (!this.e) {
            this.i.setText(this.l);
            this.f.setBackground(this.c.getResources().getDrawable(R.drawable._2131430658_res_0x7f0b0d02));
        } else {
            this.i.setText(this.c.getResources().getString(R.string._2130845278_res_0x7f021e5e));
            this.f.setBackground(this.c.getResources().getDrawable(R.mipmap._2131821462_res_0x7f110396));
        }
        this.n.setBackground(this.c.getResources().getDrawable(R.drawable._2131432057_res_0x7f0b1279));
        this.i.setTextColor(this.c.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        if (1 == i) {
            i();
        }
    }

    private void d() {
        DeviceInfo deviceInfo = this.b;
        if (deviceInfo == null) {
            LogUtil.h("FindDeviceActivity", "deviceInfo is null");
            return;
        }
        if (jfu.m(deviceInfo.getProductType())) {
            String j = jfu.j(this.b.getProductType());
            LogUtil.a("FindDeviceActivity", "is plugin download uuid:", j);
            boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j);
            LogUtil.a("FindDeviceActivity", "is plugin download pluginAvaiable:", Boolean.valueOf(isResourcesAvailable));
            if (isResourcesAvailable) {
                b(j);
                return;
            } else {
                h();
                return;
            }
        }
        h();
    }

    private void b(String str) {
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
        if (pluginInfoByUuid != null) {
            String a2 = cwf.a(pluginInfoByUuid, 2, this.b);
            LogUtil.a("FindDeviceActivity", "is plugin download image:", a2);
            Bitmap loadImageByImageName = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, a2);
            this.p.setImageBitmap(loadImageByImageName);
            this.h.setImageBitmap(loadImageByImageName);
            return;
        }
        h();
    }

    private void h() {
        if (jfu.h(this.b.getProductType())) {
            this.p.setImageResource(R.mipmap._2131820664_res_0x7f110078);
        } else {
            this.p.setImageResource(R.mipmap._2131820674_res_0x7f110082);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.c).e(R.string.IDS_bolt_device_maximum_volume_playback).czz_(R.string._2130845222_res_0x7f021e26, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FindDeviceActivity.this.k != null && FindDeviceActivity.this.k.isShowing()) {
                    FindDeviceActivity.this.k.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R.string._2130845280_res_0x7f021e60, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FindDeviceActivity.this.m();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.k = e;
        if (e.isShowing()) {
            return;
        }
        this.k.setCancelable(false);
        this.k.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        n();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.p.setVisibility(8);
        this.h.setVisibility(0);
        this.g.setVisibility(0);
        this.g.b();
        this.m.setText(this.b.getDeviceName());
        if (!this.e) {
            this.i.setText(this.r);
            this.f.setBackground(this.c.getResources().getDrawable(R.drawable._2131430657_res_0x7f0b0d01));
        } else {
            this.i.setText(this.c.getResources().getString(R.string._2130845279_res_0x7f021e5f));
            this.f.setBackground(this.c.getResources().getDrawable(R.mipmap._2131821463_res_0x7f110397));
        }
        this.n.setBackground(this.c.getResources().getDrawable(R.drawable._2131432059_res_0x7f0b127b));
        this.i.setTextColor(this.c.getResources().getColor(R.color._2131299238_res_0x7f090ba6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        this.p.setVisibility(0);
        this.m.setText(R.string._2130845228_res_0x7f021e2c);
        this.t.setText(R.string._2130845229_res_0x7f021e2d);
        if (!this.e) {
            this.i.setText(this.l);
            this.f.setBackground(this.c.getResources().getDrawable(R.drawable._2131430657_res_0x7f0b0d01));
        } else {
            this.i.setText(this.c.getResources().getString(R.string._2130845278_res_0x7f021e5e));
            this.f.setBackground(this.c.getResources().getDrawable(R.mipmap._2131821464_res_0x7f110398));
        }
        this.n.setBackground(this.c.getResources().getDrawable(R.drawable._2131432057_res_0x7f0b1279));
        this.i.setTextColor(this.c.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.n.setAlpha(0.4f);
        this.n.setClickable(false);
    }

    private void c() {
        LogUtil.a("FindDeviceActivity", "sendFindDeviceCommand enter");
        jgv.c().b(this.b, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceActivity.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("FindDeviceActivity", "onCreate sendFindDeviceCommand onResponse errorCode,", Integer.valueOf(i));
                if (i == 100000 && (obj instanceof cvn)) {
                    cvn cvnVar = (cvn) obj;
                    int w = CommonUtil.w(cvx.d(cvnVar.b()));
                    LogUtil.a("FindDeviceActivity", "onCreate onResponse,", Integer.valueOf(cvnVar.e()), Integer.valueOf(w));
                    if (cvnVar.e() == 2) {
                        if (w == 0) {
                            FindDeviceActivity.this.d.sendEmptyMessage(2);
                            return;
                        } else {
                            FindDeviceActivity.this.d.sendEmptyMessage(3);
                            return;
                        }
                    }
                    if (cvnVar.e() == 4) {
                        FindDeviceActivity.this.d.sendEmptyMessage(4);
                        return;
                    } else {
                        LogUtil.h("FindDeviceActivity", "sendFindDeviceCommand else");
                        return;
                    }
                }
                LogUtil.a("FindDeviceActivity", "sendFindDeviceCommand showResponseFailedDialog");
            }
        });
    }

    private void i() {
        LogUtil.a("FindDeviceActivity", "sendStopDeviceCommand enter");
        jgv.c().e(this.b, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.finddevice.FindDeviceActivity.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("FindDeviceActivity", "showDialog sendStopFindDeviceCommand onResponse errorCode,", Integer.valueOf(i));
                if (i == 100000 && (obj instanceof cvn)) {
                    cvn cvnVar = (cvn) obj;
                    int w = CommonUtil.w(cvx.d(cvnVar.b()));
                    LogUtil.a("FindDeviceActivity", "showDialog onResponse,", Integer.valueOf(cvnVar.e()), Integer.valueOf(w));
                    if (cvnVar.e() == 1) {
                        if (w != 0) {
                            FindDeviceActivity.this.d.sendEmptyMessage(3);
                            return;
                        } else {
                            LogUtil.a("FindDeviceActivity", "sendStopDeviceCommand success");
                            FindDeviceActivity.this.d.sendEmptyMessage(5);
                            return;
                        }
                    }
                    return;
                }
                LogUtil.h("FindDeviceActivity", "sendStopDeviceCommand showResponseFailedDialog");
            }
        });
    }

    private void a() {
        BroadcastManagerUtil.bFC_(this.c, this.j, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    private void k() {
        try {
            LogUtil.a("FindDeviceActivity", "Enter unregisterBindDeviceBroadcast()!");
            unregisterReceiver(this.j);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("FindDeviceActivity", "unregisterBindDeviceBroadcast failed");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("FindDeviceActivity", "onResume()");
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        FindDeviceDynamicImageView findDeviceDynamicImageView = this.g;
        if (findDeviceDynamicImageView != null && findDeviceDynamicImageView.getVisibility() == 0) {
            i();
            this.d.sendEmptyMessage(4);
        }
        super.onDestroy();
        LogUtil.a("FindDeviceActivity", "onDestroy()");
        k();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
