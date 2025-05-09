package com.huawei.ui.device.activity.doublephone;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jfq;
import defpackage.jpt;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public class DoublePhoneActivity extends BaseActivity {
    private DeviceSettingsInteractors b;
    private CustomTitleBar d;
    private ImageView e;
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private RelativeLayout j;
    private LinearLayout k;
    private RelativeLayout m;
    private HealthTextView n;
    private HealthTextView q;
    private HealthTextView r;
    private HealthButton s;
    private HealthTextView t;
    private final Object o = new Object();
    private List<String> c = new CopyOnWriteArrayList();
    private int l = 15;
    private int i = 9;

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f9081a = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.doublephone.DoublePhoneActivity.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("DoublePhoneActivity", "mNonLocalBroadcastReceiver()  intent : " + intent.getAction());
            String action = intent.getAction();
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10) == 10) {
                    DoublePhoneActivity.this.k();
                }
            } else {
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    DeviceInfo deviceInfo = intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo ? (DeviceInfo) intent.getParcelableExtra("deviceinfo") : null;
                    if (deviceInfo != null) {
                        if (DoublePhoneActivity.this.b(deviceInfo)) {
                            DoublePhoneActivity.this.a(deviceInfo);
                            return;
                        } else {
                            LogUtil.a("DoublePhoneActivity", "This device does not have the correspond capability.");
                            return;
                        }
                    }
                    return;
                }
                LogUtil.c("DoublePhoneActivity", "other action");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "DoublePhoneActivity");
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("DoublePhoneActivity", "enter mConnectStateChangedReceiver, deviceCapabilityHashMaps is null or empty");
            return false;
        }
        DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability != null && (deviceCapability.isSupportPhonesInfo() || deviceCapability.isSupportNotifyDeviceBroadCast())) {
            return true;
        }
        LogUtil.h("DoublePhoneActivity", "device not support phones info or device broadcast.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        LogUtil.a("DoublePhoneActivity", "dealDeviceConnectState Entering");
        if (deviceInfo.getDeviceConnectState() == 2) {
            if (this.s.isEnabled()) {
                return;
            }
            b();
            e();
            return;
        }
        LogUtil.c("DoublePhoneActivity", "other status");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        e();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.f9081a);
        } catch (IllegalStateException unused) {
            LogUtil.b("DoublePhoneActivity", "unregisterBroadcast IllegalStateException");
        }
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        a();
        e();
    }

    private void e() {
        g();
        c();
        m();
        this.b = DeviceSettingsInteractors.d(BaseApplication.getContext());
        d();
    }

    private void c() {
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.a("DoublePhoneActivity", "checkBluetooth ");
            k();
        } else {
            LogUtil.a("DoublePhoneActivity", "normal, continue");
            m();
            this.b = DeviceSettingsInteractors.d(BaseApplication.getContext());
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.j.setVisibility(0);
        this.n.setVisibility(0);
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.s.setVisibility(0);
        this.s.setEnabled(false);
        this.k.setVisibility(8);
        this.d.setTitleText(getResources().getString(R.string._2130842817_res_0x7f0214c1));
    }

    private void m() {
        this.m.setVisibility(0);
        this.j.setVisibility(8);
        this.f.setVisibility(8);
        this.n.setVisibility(0);
        this.g.setVisibility(0);
        this.s.setVisibility(0);
        this.k.setVisibility(8);
        this.s.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.n.setVisibility(8);
        this.g.setVisibility(8);
        this.s.setVisibility(8);
        this.f.setVisibility(8);
        this.k.setVisibility(0);
        this.d.setTitleText(getResources().getString(R.string._2130842818_res_0x7f0214c2));
        DeviceInfo d = jpt.d("DoublePhoneActivity");
        if (d != null) {
            this.r.setText(getResources().getString(R.string._2130842821_res_0x7f0214c5, d.getDeviceName()));
        } else {
            LogUtil.a("DoublePhoneActivity", "pairOtherPhone deviceInfo is null");
        }
        setResult(-1);
    }

    private void a() {
        setContentView(R.layout.activity_device_double_phone);
        this.d = (CustomTitleBar) nsy.cMc_(this, R.id.setting_device_title_bar);
        this.m = (RelativeLayout) nsy.cMc_(this, R.id.layout_normal);
        this.j = (RelativeLayout) nsy.cMc_(this, R.id.addDevice_error_layout);
        this.f = (LinearLayout) nsy.cMc_(this, R.id.layout_device_connected);
        this.k = (LinearLayout) nsy.cMc_(this, R.id.layout_normal_pair_other);
        this.g = (LinearLayout) nsy.cMc_(this, R.id.rl_bottom);
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.double_phone_parent);
        this.h = linearLayout;
        nsn.cLA_(linearLayout, 3);
        this.e = (ImageView) nsy.cMc_(this, R.id.pic_double_phone);
        this.n = (HealthTextView) nsy.cMc_(this, R.id.textview_pair_content1);
        this.r = (HealthTextView) nsy.cMc_(this, R.id.text_view_content3);
        this.q = (HealthTextView) nsy.cMc_(this, R.id.text_view_phone1);
        this.t = (HealthTextView) nsy.cMc_(this, R.id.text_view_phone2);
        this.s = (HealthButton) nsy.cMc_(this, R.id.button_start_notify_device);
        j();
        if (nsn.ag(BaseApplication.getContext())) {
            n();
        } else {
            l();
        }
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.doublephone.DoublePhoneActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DoublePhoneActivity.this.h();
                DoublePhoneActivity.this.i();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        f();
    }

    private void f() {
        final int[] iArr = {this.l};
        for (int i = 0; i < this.l - this.i; i++) {
            this.s.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.device.activity.doublephone.DoublePhoneActivity.4
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (DoublePhoneActivity.this.s.getLineCount() <= 1) {
                        DoublePhoneActivity.this.s.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        int width = ((WindowManager) DoublePhoneActivity.this.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getWidth();
                        ViewGroup.LayoutParams layoutParams = DoublePhoneActivity.this.s.getLayoutParams();
                        if (width == layoutParams.width) {
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] - 1;
                            DoublePhoneActivity.this.s.setTextSize(1, iArr[0]);
                        } else {
                            layoutParams.width = width;
                            DoublePhoneActivity.this.s.setLayoutParams(layoutParams);
                        }
                    }
                    LogUtil.a("DoublePhoneActivity", "globalLayout...");
                }
            });
        }
    }

    private void j() {
        DeviceInfo d = jpt.d("DoublePhoneActivity");
        if (d == null || this.n == null) {
            return;
        }
        this.n.setText(getResources().getString(R.string._2130842819_res_0x7f0214c3, d.getDeviceName()));
    }

    private void l() {
        int width = ((WindowManager) getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getWidth();
        ImageView imageView = this.e;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = (width * 960) / ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
            layoutParams.width = width;
            this.e.setLayoutParams(layoutParams);
        }
    }

    private void n() {
        int width = ((WindowManager) getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getWidth();
        ImageView imageView = this.e;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = width;
            this.e.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.b.c(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.doublephone.DoublePhoneActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DoublePhoneActivity", "notifyDevice errCode ", Integer.valueOf(i), " objData is  ", obj);
                if (i != 0 || obj == null) {
                    return;
                }
                byte[] bArr = new byte[0];
                if (obj instanceof byte[]) {
                    bArr = (byte[]) obj;
                }
                if (bArr.length <= 1) {
                    return;
                }
                byte b = bArr[1];
                if (b == 35) {
                    String d = cvx.d(bArr);
                    if (d.length() > 4) {
                        try {
                            DoublePhoneActivity.this.d(new cwl().a(d.substring(4, d.length())).e());
                            LogUtil.a("DoublePhoneActivity", "mConnectedPhoneNames : " + DoublePhoneActivity.this.c.toString());
                            return;
                        } catch (cwg unused) {
                            LogUtil.b("DoublePhoneActivity", "notifyDevice TlvException");
                            return;
                        }
                    }
                    return;
                }
                LogUtil.a("DoublePhoneActivity", "notifyDevice error command id:", Byte.valueOf(b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<cwd> list) {
        if (list != null && list.size() > 0) {
            for (cwd cwdVar : list) {
                if (CommonUtil.w(cwdVar.e()) == 127) {
                    LogUtil.a("DoublePhoneActivity", "parseNotifyDevice get error code. : " + CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.h("DoublePhoneActivity", "parseNotifyDevice get wrong code.");
                }
            }
            return;
        }
        LogUtil.h("DoublePhoneActivity", "tlvList is empty.");
    }

    private void d() {
        this.b.a(new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.doublephone.DoublePhoneActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DoublePhoneActivity", "getDevicePhoneInfo errCode is ", Integer.valueOf(i), " objData is ", obj);
                DoublePhoneActivity.this.b();
                if (i != 0 || obj == null) {
                    return;
                }
                byte[] bArr = (byte[]) obj;
                if (bArr.length <= 1) {
                    return;
                }
                byte b = bArr[1];
                if (b == 36) {
                    String d = cvx.d(bArr);
                    if (d.length() > 4) {
                        try {
                            DoublePhoneActivity.this.a(new cwl().a(d.substring(4, d.length())).a());
                            LogUtil.a("DoublePhoneActivity", "mConnectedPhoneNames : " + DoublePhoneActivity.this.c.toString());
                            DoublePhoneActivity.this.s.post(new Runnable() { // from class: com.huawei.ui.device.activity.doublephone.DoublePhoneActivity.1.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    DoublePhoneActivity.this.o();
                                }
                            });
                            return;
                        } catch (cwg unused) {
                            LogUtil.b("DoublePhoneActivity", "getDevicePhoneInfo TlvException");
                            return;
                        }
                    }
                    return;
                }
                LogUtil.a("DoublePhoneActivity", "getDevicePhoneInfo error command id:", Byte.valueOf(b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<cwe> list) {
        if (list != null && list.size() > 0) {
            LogUtil.a("DoublePhoneActivity", "parsePhoneName tlvFatherList.size() : ", Integer.valueOf(list.size()));
            Iterator<cwe> it = list.iterator();
            while (it.hasNext()) {
                List<cwe> a2 = it.next().a();
                if (a2 != null && a2.size() > 0) {
                    Iterator<cwe> it2 = a2.iterator();
                    while (it2.hasNext()) {
                        c(it2.next());
                    }
                }
            }
        }
    }

    private void c(cwe cweVar) {
        List<cwd> e = cweVar.e();
        if (e == null || e.size() <= 0) {
            return;
        }
        String str = null;
        for (cwd cwdVar : e) {
            if (CommonUtil.w(cwdVar.e()) == 3) {
                str = cvx.e(cwdVar.c());
            } else {
                LogUtil.h("DoublePhoneActivity", "parsePhoneNameSub default.");
            }
            e(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.k.getVisibility() == 0) {
            return;
        }
        synchronized (this.o) {
            List<String> list = this.c;
            if (list != null && list.size() == 2) {
                this.f.setVisibility(0);
                this.n.setVisibility(8);
                if (this.c.size() > 1) {
                    this.q.setText(this.c.get(0));
                    this.t.setText(this.c.get(1));
                }
            } else {
                this.f.setVisibility(8);
                this.n.setVisibility(0);
                LogUtil.h("DoublePhoneActivity", "mConnectedPhoneNames is NULL or mConnectedPhoneNames.size is not 2");
            }
        }
    }

    private void g() {
        try {
            IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            BroadcastManagerUtil.bFC_(this, this.f9081a, intentFilter, LocalBroadcast.c, null);
        } catch (IllegalStateException unused) {
            LogUtil.b("DoublePhoneActivity", "registerBroadcast IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        synchronized (this.o) {
            this.c.clear();
        }
    }

    private void e(String str) {
        synchronized (this.o) {
            this.c.add(str);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
