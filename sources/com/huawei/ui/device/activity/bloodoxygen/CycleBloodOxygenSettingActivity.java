package com.huawei.ui.device.activity.bloodoxygen;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.main.R$string;
import defpackage.cwi;
import defpackage.jcf;
import defpackage.jfu;
import defpackage.jpo;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.obd;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class CycleBloodOxygenSettingActivity extends BaseActivity {
    private HealthTextView b;
    private HealthTextView d;
    private RelativeLayout e;
    private String f;
    private HealthTextView h;
    private HealthSwitchButton i;
    private DeviceInfo j;
    private obd k;
    private DeviceSettingsInteractors m;
    private ImageView n;
    private jqi o;
    private boolean p;
    private PopupWindow s;
    private ImageView u;
    private HealthTextView v;
    private HealthTextView x;
    private ImageView y;
    private int c = 0;
    private int w = 0;
    private Map<Integer, RelativeLayout> q = new HashMap(16);
    private Map<Integer, HealthRadioButton> t = new HashMap(16);
    private boolean r = true;
    private CompoundButton.OnCheckedChangeListener g = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.5
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ReleaseLogUtil.e("DEVMGR_CycleBloodOxygenSettingActivity", "mBloodOxygenSwitchCheckedListener isChecked:", Boolean.valueOf(z), ", mIsUpdateStatus:", Boolean.valueOf(CycleBloodOxygenSettingActivity.this.r), ", mIsSupportHighLand:", Boolean.valueOf(CycleBloodOxygenSettingActivity.this.p));
            if (CycleBloodOxygenSettingActivity.this.r) {
                if (!CycleBloodOxygenSettingActivity.this.p) {
                    CycleBloodOxygenSettingActivity.this.b(z);
                } else if (z) {
                    CycleBloodOxygenSettingActivity.this.b(true);
                } else {
                    CycleBloodOxygenSettingActivity.this.c();
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            LogUtil.h("CycleBloodOxygenSettingActivity", "mIsUpdateStatus is false,return");
            CycleBloodOxygenSettingActivity.this.r = true;
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private View.OnClickListener f9069a = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!nsn.o()) {
                CycleBloodOxygenSettingActivity.this.h();
                ViewClickInstrumentation.clickOnView(view);
            } else {
                LogUtil.h("CycleBloodOxygenSettingActivity", "downRemindClick is fast click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    };
    private View.OnClickListener z = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.9
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!nsn.o()) {
                if (CycleBloodOxygenSettingActivity.this.s.isShowing()) {
                    CycleBloodOxygenSettingActivity.this.u.setVisibility(4);
                    CycleBloodOxygenSettingActivity.this.s.dismiss();
                } else {
                    int[] iArr = new int[2];
                    CycleBloodOxygenSettingActivity.this.u.getLocationOnScreen(iArr);
                    CycleBloodOxygenSettingActivity.this.s.showAtLocation(CycleBloodOxygenSettingActivity.this.u, 0, 0, iArr[1] + CycleBloodOxygenSettingActivity.this.u.getHeight());
                    CycleBloodOxygenSettingActivity.this.u.setVisibility(0);
                    if (jcf.c()) {
                        jcf.bEk_(CycleBloodOxygenSettingActivity.this.y, nsf.h(R.string._2130845563_res_0x7f021f7b));
                    }
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private View.OnClickListener l = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (Map.Entry entry : CycleBloodOxygenSettingActivity.this.q.entrySet()) {
                if (entry.getValue() == view) {
                    CycleBloodOxygenSettingActivity.this.w = ((Integer) entry.getKey()).intValue();
                    LogUtil.c("CycleBloodOxygenSettingActivity", "select blood oxygen is ", Integer.valueOf(CycleBloodOxygenSettingActivity.this.w));
                    CycleBloodOxygenSettingActivity cycleBloodOxygenSettingActivity = CycleBloodOxygenSettingActivity.this;
                    cycleBloodOxygenSettingActivity.e(cycleBloodOxygenSettingActivity.w);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_continue_measure_blood_oxygen);
        this.o = jqi.a();
        this.m = DeviceSettingsInteractors.d(BaseApplication.getContext());
        this.k = new obd();
        a();
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.o.getSwitchSetting("highland.blood.oxygen.switch", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.12
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("CycleBloodOxygenSettingActivity", "getHighLandBloodOxygen objectData:", obj, ", errorCode:", Integer.valueOf(i));
                final String str = (i == 0 && (obj instanceof String)) ? (String) obj : "0";
                CycleBloodOxygenSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.12.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if ("0".equals(str)) {
                            CycleBloodOxygenSettingActivity.this.b(false);
                        } else {
                            CycleBloodOxygenSettingActivity.this.i();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R$string.IDS_blood_oxygen_alert_off);
        builder.czC_(R$string.IDS_highland_sure_off, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CycleBloodOxygenSettingActivity.this.b(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(R$string.IDS_highland_cancel, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("CycleBloodOxygenSettingActivity", "dialog set negative");
                CycleBloodOxygenSettingActivity.this.r = false;
                CycleBloodOxygenSettingActivity.this.i.setChecked(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.m != null) {
            ReleaseLogUtil.e("DEVMGR_CycleBloodOxygenSettingActivity", "send BloodOxygen switch status command");
            this.m.b(z ? 1 : 0);
        }
        ReleaseLogUtil.e("DEVMGR_CycleBloodOxygenSettingActivity", "blood switch button is ", Boolean.valueOf(z));
        e(z);
        a(z);
        this.k.a("biBloodOxygenSwitch", z ? 1 : 0);
        ObserverManagerUtil.c(ObserveLabels.SPO2_MONITOR_SWITCH_STATUS, Integer.valueOf(z ? 1 : 0));
    }

    private void a() {
        DeviceInfo a2 = jpt.a("CycleBloodOxygenSettingActivity");
        this.j = a2;
        this.p = cwi.c(a2, 72);
        this.n = (ImageView) nsy.cMc_(this, R.id.settings_blood_oxygen_imageView);
        this.i = (HealthSwitchButton) nsy.cMc_(this, R.id.blood_oxygen_switch_button);
        this.e = (RelativeLayout) nsy.cMc_(this, R.id.settings_blood_oxygen_down_remind_explain_layout);
        this.h = (HealthTextView) nsy.cMc_(this, R.id.raise_remind_number);
        LogUtil.a("CycleBloodOxygenSettingActivity", "isRTLLanguage is ", Boolean.valueOf(LanguageUtil.bc(BaseApplication.getContext())));
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            Drawable drawable = getResources().getDrawable(R.mipmap._2131820654_res_0x7f11006e);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.h.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable2 = getResources().getDrawable(R.drawable._2131430054_res_0x7f0b0aa6);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            this.h.setCompoundDrawables(null, null, drawable2, null);
        }
        this.b = (HealthTextView) nsy.cMc_(this, R.id.blood_oxygen_down_remind_explain);
        this.d = (HealthTextView) nsy.cMc_(this, R.id.blood_oxygen_down_remind_content_explain);
        this.x = (HealthTextView) nsy.cMc_(this, R.id.blood_oxygen_tip1_tv);
        this.v = (HealthTextView) nsy.cMc_(this, R.id.blood_oxygen_tip2_tv);
        this.x.setText(getResources().getString(R.string._2130843911_res_0x7f021907, 1));
        this.v.setText(getResources().getString(R.string._2130843912_res_0x7f021908, 2));
        this.e.setOnClickListener(this.f9069a);
        DeviceInfo deviceInfo = this.j;
        if (deviceInfo != null) {
            if (!jfu.h(deviceInfo.getProductType())) {
                this.n.setImageResource(R.drawable._2131428622_res_0x7f0b050e);
            } else {
                this.n.setImageResource(R.drawable._2131428621_res_0x7f0b050d);
            }
        }
        ImageView imageView = (ImageView) nsy.cMc_(this, R.id.oxygen_remind_tips);
        this.u = imageView;
        imageView.setAlpha(0.8f);
        this.y = (ImageView) nsy.cMc_(this, R.id.text_oxygen_remind_tips);
        e();
        jcf.bEz_(this.y, nsf.h(R.string._2130847331_res_0x7f022663));
        this.y.setOnClickListener(this.z);
    }

    private void e() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_oxygen_i_hint, (ViewGroup) null, false);
        this.s = new PopupWindow(inflate, -1, -2);
        inflate.measure(0, 0);
        this.s.setBackgroundDrawable(getDrawable(R.color._2131296971_res_0x7f0902cb));
        this.s.setOutsideTouchable(true);
        this.s.setFocusable(false);
        this.s.setTouchInterceptor(new View.OnTouchListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                CycleBloodOxygenSettingActivity.this.u.setVisibility(4);
                CycleBloodOxygenSettingActivity.this.s.dismiss();
                return false;
            }
        });
    }

    private String d(int i) {
        String e = UnitUtil.e(i, 2, 0);
        LogUtil.a("CycleBloodOxygenSettingActivity", "languageLocalNumber ", e);
        return e;
    }

    private void d() {
        DeviceInfo deviceInfo = this.j;
        if (deviceInfo != null && jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            b();
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.14
                @Override // java.lang.Runnable
                public void run() {
                    Semaphore semaphore = new Semaphore(1);
                    CycleBloodOxygenSettingActivity.this.d(semaphore);
                    CycleBloodOxygenSettingActivity.this.e(semaphore);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final Semaphore semaphore) {
        LogUtil.a("CycleBloodOxygenSettingActivity", "getBloodOxygenSwitch enter");
        try {
            if (semaphore.tryAcquire(1000L, TimeUnit.MILLISECONDS)) {
                LogUtil.a("CycleBloodOxygenSettingActivity", "getBloodOxygenSwitch isTryAcquire true");
            } else {
                ReleaseLogUtil.d("CycleBloodOxygenSettingActivity", "getBloodOxygenSwitch isTryAcquire false");
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("getBloodOxygenSwitchError", "getBloodOxygenSwitch time out");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("CycleBloodOxygenSettingActivity", "getBloodOxygenSwitch InterruptedException");
        }
        this.o.getSwitchSetting("custom.blood.oxygen.switch", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("CycleBloodOxygenSettingActivity", "bloodOxygen errorCode = ", Integer.valueOf(i));
                CycleBloodOxygenSettingActivity.this.f = (i == 0 && (obj instanceof String)) ? (String) obj : "0";
                CycleBloodOxygenSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.1.5
                    @Override // java.lang.Runnable
                    public void run() {
                        CycleBloodOxygenSettingActivity.this.g();
                    }
                });
                semaphore.release();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("CycleBloodOxygenSettingActivity", "updateBloodOxygenUi enter");
        boolean equals = "1".equals(this.f);
        this.i.setChecked(equals);
        this.i.setOnCheckedChangeListener(this.g);
        e(equals);
        c(equals, false, false);
    }

    private void b() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "100", "custom.blood.oxygen.switch");
        boolean z = CommonUtil.h(b) == 1;
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "103", "custom.blood.oxygen.remind");
        this.c = CommonUtil.b(BaseApplication.getContext(), b2, 0);
        this.w = CommonUtil.b(BaseApplication.getContext(), b2, 0);
        LogUtil.a("CycleBloodOxygenSettingActivity", "family MODULE_BLOOD_OXYGEN_REMIND status : ", b, "mBloodOxygenNumber : ", Integer.valueOf(this.c));
        this.i.setChecked(z);
        this.i.setOnCheckedChangeListener(this.g);
        e(z);
        c(z, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final Semaphore semaphore) {
        LogUtil.a("CycleBloodOxygenSettingActivity", "getBloodOxygenNumber enter");
        try {
            if (semaphore.tryAcquire(1000L, TimeUnit.MILLISECONDS)) {
                LogUtil.a("CycleBloodOxygenSettingActivity", "getBloodOxygenNumber isTryAcquire true");
            } else {
                ReleaseLogUtil.d("CycleBloodOxygenSettingActivity", "getBloodOxygenNumber isTryAcquire false");
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("getBloodOxygenNumberError", "getBloodOxygenNumber time out");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("CycleBloodOxygenSettingActivity", "getBloodOxygenNumber InterruptedException");
        }
        this.o.getSwitchSetting("custom.blood.oxygen.remind", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("CycleBloodOxygenSettingActivity", "getBloodOxygenNumber errorCode = ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    String str = (String) obj;
                    CycleBloodOxygenSettingActivity.this.c = CommonUtil.b(BaseApplication.getContext(), str, 0);
                    CycleBloodOxygenSettingActivity.this.w = CommonUtil.b(BaseApplication.getContext(), str, 0);
                    CycleBloodOxygenSettingActivity cycleBloodOxygenSettingActivity = CycleBloodOxygenSettingActivity.this;
                    cycleBloodOxygenSettingActivity.c = Math.min(90, cycleBloodOxygenSettingActivity.c);
                    CycleBloodOxygenSettingActivity cycleBloodOxygenSettingActivity2 = CycleBloodOxygenSettingActivity.this;
                    cycleBloodOxygenSettingActivity2.w = Math.min(90, cycleBloodOxygenSettingActivity2.w);
                }
                semaphore.release();
            }
        });
    }

    private void e(boolean z) {
        if (z) {
            this.b.setTextColor(getResources().getColor(R.color._2131297823_res_0x7f09061f));
            this.h.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.d.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.e.setEnabled(true);
            return;
        }
        this.b.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.h.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
        this.d.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
        this.e.setEnabled(false);
    }

    private void a(final boolean z) {
        if (z) {
            this.o.getSwitchSetting("custom.blood.oxygen.remind", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("CycleBloodOxygenSettingActivity", "setDefaultDownRemindCommand errorCode: ", Integer.valueOf(i), ", objectData:", obj);
                    if (obj == null) {
                        CycleBloodOxygenSettingActivity.this.c = 90;
                    }
                    CycleBloodOxygenSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            CycleBloodOxygenSettingActivity.this.c(z, false, true);
                        }
                    });
                }
            });
        } else {
            c(z, false, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z, boolean z2, boolean z3) {
        int i;
        ReleaseLogUtil.e("DEVMGR_CycleBloodOxygenSettingActivity", "enter handleDownRemindCommand isMigrate: ", Boolean.valueOf(z2), " isSendCommand: ", Boolean.valueOf(z3), " isBloodOxygenChecked: ", Boolean.valueOf(z));
        if (z && (i = this.c) != 0) {
            this.h.setText(d(i));
            if (z3) {
                DeviceSettingsInteractors deviceSettingsInteractors = this.m;
                if (deviceSettingsInteractors == null) {
                    LogUtil.h("CycleBloodOxygenSettingActivity", "handleDownRemindCommand turn on mDeviceInteractors is null");
                    return;
                } else {
                    deviceSettingsInteractors.e(1, this.c, z2);
                    return;
                }
            }
            return;
        }
        this.h.setText(R.string._2130841259_res_0x7f020eab);
        if (z3) {
            DeviceSettingsInteractors deviceSettingsInteractors2 = this.m;
            if (deviceSettingsInteractors2 == null) {
                LogUtil.h("CycleBloodOxygenSettingActivity", "handleDownRemindCommand turn off mDeviceInteractors is null");
                return;
            } else {
                deviceSettingsInteractors2.e(0, 0, z2);
                return;
            }
        }
        if (z2) {
            this.c = Math.min(this.c, 90);
            this.o.setSwitchSetting("custom.blood.oxygen.remind", this.c + "", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("CycleBloodOxygenSettingActivity", "migrateBloodOxygenNumber bloodRemidNumber is", obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ReleaseLogUtil.e("DEVMGR_CycleBloodOxygenSettingActivity", "downRemind showDialog");
        this.q.clear();
        this.t.clear();
        View inflate = LayoutInflater.from(this).inflate(R.layout.hw_show_setting_blood_oxygen_down_remind, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.a(getString(R$string.IDS_blood_oxygen_down_remind)).czh_(inflate, 0, 0).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CycleBloodOxygenSettingActivity cycleBloodOxygenSettingActivity = CycleBloodOxygenSettingActivity.this;
                cycleBloodOxygenSettingActivity.c = cycleBloodOxygenSettingActivity.w;
                CycleBloodOxygenSettingActivity.this.c(true, true, true);
                CycleBloodOxygenSettingActivity.this.k.a("biBloodOxygenRemindValue", CycleBloodOxygenSettingActivity.this.c);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("CycleBloodOxygenSettingActivity", "dialog set negative");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        cPu_(inflate);
        CustomViewDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    private void cPu_(View view) {
        ((HealthTextView) nsy.cMd_(view, R.id.tv_75)).setText(d(75));
        ((HealthTextView) nsy.cMd_(view, R.id.tv_80)).setText(d(80));
        ((HealthTextView) nsy.cMd_(view, R.id.tv_85)).setText(d(85));
        ((HealthTextView) nsy.cMd_(view, R.id.tv_90)).setText(d(90));
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMd_(view, R.id.rl_close);
        HealthRadioButton healthRadioButton = (HealthRadioButton) nsy.cMd_(view, R.id.iv_close);
        this.q.put(0, relativeLayout);
        this.t.put(0, healthRadioButton);
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMd_(view, R.id.rl_75);
        HealthRadioButton healthRadioButton2 = (HealthRadioButton) nsy.cMd_(view, R.id.iv_75);
        this.q.put(75, relativeLayout2);
        this.t.put(75, healthRadioButton2);
        RelativeLayout relativeLayout3 = (RelativeLayout) nsy.cMd_(view, R.id.rl_80);
        HealthRadioButton healthRadioButton3 = (HealthRadioButton) nsy.cMd_(view, R.id.iv_80);
        this.q.put(80, relativeLayout3);
        this.t.put(80, healthRadioButton3);
        RelativeLayout relativeLayout4 = (RelativeLayout) nsy.cMd_(view, R.id.rl_85);
        HealthRadioButton healthRadioButton4 = (HealthRadioButton) nsy.cMd_(view, R.id.iv_85);
        this.q.put(85, relativeLayout4);
        this.t.put(85, healthRadioButton4);
        RelativeLayout relativeLayout5 = (RelativeLayout) nsy.cMd_(view, R.id.rl_90);
        HealthRadioButton healthRadioButton5 = (HealthRadioButton) nsy.cMd_(view, R.id.iv_90);
        this.q.put(90, relativeLayout5);
        this.t.put(90, healthRadioButton5);
        relativeLayout.setOnClickListener(this.l);
        relativeLayout2.setOnClickListener(this.l);
        relativeLayout3.setOnClickListener(this.l);
        relativeLayout4.setOnClickListener(this.l);
        relativeLayout5.setOnClickListener(this.l);
        e(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ReleaseLogUtil.e("DEVMGR_CycleBloodOxygenSettingActivity", "updateItemSelectButtonDrawable");
        for (Map.Entry<Integer, HealthRadioButton> entry : this.t.entrySet()) {
            int intValue = entry.getKey().intValue();
            HealthRadioButton value = entry.getValue();
            if (i == intValue) {
                value.setChecked(true);
            } else {
                value.setChecked(false);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
