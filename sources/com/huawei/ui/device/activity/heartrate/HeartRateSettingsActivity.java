package com.huawei.ui.device.activity.heartrate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.main.R$string;
import defpackage.cvs;
import defpackage.jfu;
import defpackage.jlk;
import defpackage.jpo;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.nsy;
import defpackage.nvj;
import defpackage.obd;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class HeartRateSettingsActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private int f9102a;
    private RelativeLayout aa;
    private RelativeLayout ab;
    private HealthTextView ad;
    private HealthTextView ae;
    private HealthTextView ag;
    private HealthTextView ah;
    private HealthTextView aj;
    private HealthTextView ak;
    private HealthTextView al;
    private HealthTextView am;
    private HealthTextView an;
    private HealthTextView ao;
    private HealthTextView as;
    private HealthTextView b;
    private Context c;
    private DeviceCapability d;
    private int e;
    private ImageView f;
    private DeviceInfo g;
    private DeviceSettingsInteractors h;
    private LinearLayout k;
    private HealthSwitchButton l;
    private HealthTextView m;
    private obd o;
    private jqi q;
    private ImageView r;
    private HealthTextView s;
    private List<String> u;
    private LinearLayout v;
    private ImageView w;
    private int p = 0;
    private int n = 0;
    private Map<Integer, HealthRadioButton> x = new HashMap(16);
    private Map<Integer, RelativeLayout> z = new HashMap(16);
    private Map<Integer, RelativeLayout> ac = new HashMap(16);
    private Map<Integer, HealthRadioButton> y = new HashMap(16);
    private View.OnTouchListener t = new View.OnTouchListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.4
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view.getId() != HeartRateSettingsActivity.this.l.getId() || motionEvent.getAction() != 0) {
                return false;
            }
            if (!HeartRateSettingsActivity.this.l.isChecked()) {
                HeartRateSettingsActivity.this.b(true);
                return true;
            }
            jlk.a().a(HeartRateSettingsActivity.this, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.4.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        HeartRateSettingsActivity.this.b(false);
                    }
                }
            });
            return true;
        }
    };
    private View.OnClickListener j = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (Map.Entry entry : HeartRateSettingsActivity.this.z.entrySet()) {
                if (entry.getValue() == view) {
                    HeartRateSettingsActivity.this.f9102a = ((Integer) entry.getKey()).intValue();
                    HeartRateSettingsActivity heartRateSettingsActivity = HeartRateSettingsActivity.this;
                    heartRateSettingsActivity.a(heartRateSettingsActivity.f9102a);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private View.OnClickListener af = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HeartRateSettingsActivity.this.f();
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private View.OnClickListener i = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.10
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (Map.Entry entry : HeartRateSettingsActivity.this.ac.entrySet()) {
                if (entry.getValue() == view) {
                    HeartRateSettingsActivity.this.e = ((Integer) entry.getKey()).intValue();
                    HeartRateSettingsActivity heartRateSettingsActivity = HeartRateSettingsActivity.this;
                    heartRateSettingsActivity.e(heartRateSettingsActivity.e);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private View.OnClickListener ai = new View.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HeartRateSettingsActivity.this.a();
            ViewClickInstrumentation.clickOnView(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        this.l.setChecked(z);
        ReleaseLogUtil.e("DEVMGR_HeartRateSettingsActivity", "Heart Rate Switch is onCheckedChanged isChecked is ", Boolean.valueOf(z));
        DeviceSettingsInteractors deviceSettingsInteractors = this.h;
        if (deviceSettingsInteractors != null) {
            deviceSettingsInteractors.e(z);
        }
        Intent intent = new Intent();
        intent.putExtra("status", z ? "1" : "0");
        d(z);
        a(z);
        setResult(-1, intent);
        this.o.a("biAutoHeartRateSwitch", CommonUtil.h(z ? "1" : "0"));
        ObserverManagerUtil.c(ObserveLabels.HEART_RATE_SWITCH_STATUS, Integer.valueOf(z ? 1 : 0));
    }

    private void a(final boolean z) {
        if (z) {
            DeviceInfo deviceInfo = this.g;
            if (deviceInfo != null && jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
                c(z);
                return;
            }
            ArrayList arrayList = new ArrayList(16);
            arrayList.add("custom.heart_rate_raise_remind");
            arrayList.add("custom.heart_rate_down_remind");
            this.q.getSwitchSetting(arrayList, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HeartRateSettingsActivity", "refreshHeartRateNumber errorCode is:", Integer.valueOf(i), ", objectData:", obj);
                    if (obj == null) {
                        HeartRateSettingsActivity.this.p = 120;
                        HeartRateSettingsActivity.this.n = 40;
                    } else {
                        List list = (List) obj;
                        String a2 = nvj.a(list, "custom.heart_rate_raise_remind");
                        LogUtil.a("HeartRateSettingsActivity", "refreshHeartRateNumber raiseNumber is:", a2);
                        if (a2 == null) {
                            HeartRateSettingsActivity.this.p = 120;
                        }
                        String a3 = nvj.a(list, "custom.heart_rate_down_remind");
                        LogUtil.a("HeartRateSettingsActivity", "refreshHeartRateNumber downNumber is:", a3);
                        if (a3 == null) {
                            HeartRateSettingsActivity.this.n = 40;
                        }
                    }
                    HeartRateSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.8.3
                        @Override // java.lang.Runnable
                        public void run() {
                            HeartRateSettingsActivity.this.e(z);
                        }
                    });
                }
            });
            return;
        }
        e(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        LogUtil.a("HeartRateSettingsActivity", "updateHeartRateValue mHeartRateRaiseNumber: ", Integer.valueOf(this.p), " mHeartRateDownNumber: ", Integer.valueOf(this.n));
        DeviceCapability deviceCapability = this.d;
        if (deviceCapability == null) {
            LogUtil.h("HeartRateSettingsActivity", "updateHeartRateValue mDeviceCapability is null not send command");
            b(false, z, false);
            e(false, z, false);
        } else {
            b(deviceCapability.isSupportHeartRateRaiseAlarm(), z, false);
            e(this.d.isSupportHeartRateDownAlarm(), z, false);
        }
    }

    private void c(boolean z) {
        if (SharedPreferenceManager.b(BaseApplication.getContext(), "104", "custom.heart_rate_raise_remind") == null) {
            this.p = 120;
        }
        if (SharedPreferenceManager.b(BaseApplication.getContext(), "105", "custom.heart_rate_down_remind") == null) {
            this.n = 40;
        }
        e(z);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_heart_rate_settings);
        this.c = BaseApplication.getContext();
        this.q = jqi.a();
        this.d = cvs.d();
        this.o = new obd();
        e();
        b();
        DeviceInfo deviceInfo = this.g;
        if (deviceInfo != null && jpo.c(deviceInfo.getDeviceIdentify()) == 2) {
            d();
        } else {
            c();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.x.clear();
        this.z.clear();
        CommonUtil.a(this);
    }

    private void e() {
        this.l = (HealthSwitchButton) nsy.cMc_(this, R.id.heart_rate_switch_button);
        this.b = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_content_tv);
        this.am = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tip1_tv);
        this.as = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tip2_tv);
        this.r = (ImageView) nsy.cMc_(this, R.id.settings_heart_rate_imageView);
        this.s = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tv_sub);
        this.an = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tip3_tv);
        this.ao = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_tip4_tv);
        this.aa = (RelativeLayout) nsy.cMc_(this, R.id.settings_heart_rate_raise_remind_explain_layout);
        this.ab = (RelativeLayout) nsy.cMc_(this, R.id.settings_heart_rate_down_remind_explain_layout);
        this.aj = (HealthTextView) nsy.cMc_(this, R.id.raise_remind_number);
        this.al = (HealthTextView) nsy.cMc_(this, R.id.down_remind_number);
        this.ak = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_up_remind_explain);
        this.ah = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_down_remind_explain);
        this.ag = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_up_remind_content_explain);
        this.ae = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_down_remind_content_explain);
        this.v = (LinearLayout) nsy.cMc_(this, R.id.layout_raise_remind_number);
        this.k = (LinearLayout) nsy.cMc_(this, R.id.layout_down_remind_number);
        this.ad = (HealthTextView) nsy.cMc_(this, R.id.raise_remind_value);
        this.m = (HealthTextView) nsy.cMc_(this, R.id.down_remind_value);
        this.f = (ImageView) nsy.cMc_(this, R.id.image_right_down);
        this.w = (ImageView) nsy.cMc_(this, R.id.image_right_raise);
        setViewSafeRegion(false, this.ag);
        DeviceCapability d = cvs.d();
        if (d != null) {
            LogUtil.a("HeartRateSettingsActivity", "get deviceCapability 5.1.33 ", Boolean.valueOf(d.isSupportDefaultSwitch()));
            if (!d.isSupportHeartRateRaiseAlarm()) {
                this.aa.setVisibility(8);
            }
            if (!d.isSupportHeartRateDownAlarm()) {
                this.ab.setVisibility(8);
                this.ae.setVisibility(8);
            }
            if (!d.isSupportContinueHeartRate() && !d.isSupportHeartRateRaiseAlarm()) {
                this.ag.setVisibility(8);
                ((HealthSubHeader) nsy.cMc_(this, R.id.trend_change_sub_header)).setVisibility(8);
            }
        }
        this.aa.setOnClickListener(this.af);
        this.ab.setOnClickListener(this.ai);
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            this.v.setLayoutDirection(1);
            this.k.setLayoutDirection(1);
        }
    }

    private void b() {
        String string;
        String string2;
        String string3;
        this.g = jpt.d("HeartRateSettingsActivity");
        DeviceSettingsInteractors d = DeviceSettingsInteractors.d(this.c);
        this.h = d;
        DeviceInfo a2 = d.a();
        if (a2 == null) {
            LogUtil.a("HeartRateSettingsActivity", "refresh dialog Support deviceInfo is null , return");
            return;
        }
        if (!jfu.h(a2.getProductType())) {
            string = getResources().getString(R.string._2130842230_res_0x7f021276);
            string2 = getResources().getString(R.string._2130842232_res_0x7f021278);
            string3 = getResources().getString(R.string.IDS_hwh_motiontrack_device_using_tips);
            this.r.setImageResource(R.drawable._2131428622_res_0x7f0b050e);
        } else {
            string = getResources().getString(R.string._2130842228_res_0x7f021274);
            string2 = getResources().getString(R.string._2130842229_res_0x7f021275);
            string3 = getResources().getString(R.string._2130843194_res_0x7f02163a);
            this.r.setImageResource(R.drawable._2131428621_res_0x7f0b050d);
        }
        String string4 = getResources().getString(R.string._2130842231_res_0x7f021277);
        String string5 = getResources().getString(R.string._2130843136_res_0x7f021600);
        this.b.setText(String.format(string, 24));
        this.am.setText(String.format(string2, 1));
        this.as.setText(String.format(string4, 2));
        this.an.setText(String.format(string3, 3));
        this.ao.setText(String.format(string5, 4));
        this.s.setText(String.format(getResources().getString(R.string._2130843138_res_0x7f021602), Integer.valueOf(CommonUtil.m(BaseApplication.getContext(), nvj.c(24)))));
    }

    private void d() {
        this.n = CommonUtil.m(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), "105", "custom.heart_rate_down_remind"));
        this.p = CommonUtil.m(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), "104", "custom.heart_rate_raise_remind"));
        String b = SharedPreferenceManager.b(this.c, "102", "heart_rate_button");
        LogUtil.a("HeartRateSettingsActivity", "family MODULE_CYCLE_HEART_RATE_SWITCH status: ", b, "mHeartRateRaiseNumber: ", Integer.valueOf(this.p), ",mHeartRateDownNumber: ", Integer.valueOf(this.n));
        boolean equals = !TextUtils.isEmpty(b) ? "1".equals(b) : false;
        this.l.setChecked(equals);
        this.l.setOnTouchListener(this.t);
        d(equals);
        b(false, equals, false);
        e(false, equals, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.z.clear();
        this.x.clear();
        ReleaseLogUtil.e("DEVMGR_HeartRateSettingsActivity", "showDialogHighRate()");
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.hw_show_setting_heart_reate_rasise_remind, (ViewGroup) null);
        cPW_(inflate);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.a(getString(R$string.IDS_heartrate_raise_remind)).cyp_(inflate).cyo_(R$string.IDS_hw_common_ui_dialog_confirm, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("HeartRateSettingsActivity", "dialog set positive");
                HeartRateSettingsActivity heartRateSettingsActivity = HeartRateSettingsActivity.this;
                heartRateSettingsActivity.p = heartRateSettingsActivity.f9102a;
                HeartRateSettingsActivity.this.b(true, true, true);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyn_(R$string.IDS_hw_show_cancel, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("HeartRateSettingsActivity", "dialog set negative");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setCancelable(false);
        c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.ac.clear();
        this.y.clear();
        ReleaseLogUtil.e("DEVMGR_HeartRateSettingsActivity", "Enter showDialogDownRate()");
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.hw_show_setting_heart_reate_down_remind, (ViewGroup) null);
        cPX_(inflate);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.a(getString(R$string.IDS_heartrate_down_remind)).cyp_(inflate).cyo_(R$string.IDS_hw_common_ui_dialog_confirm, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("HeartRateSettingsActivity", "hypo heart rate dialog set positive");
                HeartRateSettingsActivity heartRateSettingsActivity = HeartRateSettingsActivity.this;
                heartRateSettingsActivity.n = heartRateSettingsActivity.e;
                HeartRateSettingsActivity.this.e(true, true, true);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyn_(R$string.IDS_hw_show_cancel, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("HeartRateSettingsActivity", "hypoheart rate dialog set negative");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setCancelable(false);
        c.show();
    }

    private void c() {
        ArrayList arrayList = new ArrayList(16);
        this.u = arrayList;
        arrayList.add("heart_rate_button");
        this.u.add("custom.heart_rate_raise_remind");
        this.u.add("custom.heart_rate_down_remind");
        this.q.getSwitchSetting(this.u, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HeartRateSettingsActivity", "initHeartRemind list errCode is ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof List)) {
                    List list = (List) obj;
                    String a2 = nvj.a(list, "custom.heart_rate_raise_remind");
                    HeartRateSettingsActivity.this.p = CommonUtil.h(a2);
                    String a3 = nvj.a(list, "custom.heart_rate_down_remind");
                    HeartRateSettingsActivity.this.n = CommonUtil.h(a3);
                    final boolean equals = "1".equals(nvj.a(list, "heart_rate_button"));
                    LogUtil.a("HeartRateSettingsActivity", "updateSwitchStatus isEnable: ", Boolean.valueOf(equals));
                    HeartRateSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            HeartRateSettingsActivity.this.l.setChecked(equals);
                            HeartRateSettingsActivity.this.l.setOnTouchListener(HeartRateSettingsActivity.this.t);
                            HeartRateSettingsActivity.this.d(equals);
                            HeartRateSettingsActivity.this.b(false, equals, false);
                            HeartRateSettingsActivity.this.e(false, equals, false);
                        }
                    });
                    if (HeartRateSettingsActivity.this.h != null) {
                        HeartRateSettingsActivity.this.h.e(equals);
                    } else {
                        LogUtil.h("HeartRateSettingsActivity", "updateSwitchStatus mDeviceInteractors is null");
                    }
                }
            }
        });
    }

    private void cPW_(View view) {
        b((HealthTextView) nsy.cMd_(view, R.id.tv_150), 150);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_140), 140);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(view, R.id.tv_130);
        Integer valueOf = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_TENNIS);
        b(healthTextView, OldToNewMotionPath.SPORT_TYPE_TENNIS);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_120), 120);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_110), 110);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_100), 100);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMd_(view, R.id.rl_close);
        this.z.put(0, relativeLayout);
        this.x.put(0, (HealthRadioButton) nsy.cMd_(view, R.id.iv_close));
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMd_(view, R.id.rl_100);
        this.z.put(100, relativeLayout2);
        RelativeLayout relativeLayout3 = (RelativeLayout) nsy.cMd_(view, R.id.rl_110);
        this.z.put(110, relativeLayout3);
        RelativeLayout relativeLayout4 = (RelativeLayout) nsy.cMd_(view, R.id.rl_120);
        this.z.put(120, relativeLayout4);
        RelativeLayout relativeLayout5 = (RelativeLayout) nsy.cMd_(view, R.id.rl_130);
        this.z.put(valueOf, relativeLayout5);
        RelativeLayout relativeLayout6 = (RelativeLayout) nsy.cMd_(view, R.id.rl_140);
        this.z.put(140, relativeLayout6);
        RelativeLayout relativeLayout7 = (RelativeLayout) nsy.cMd_(view, R.id.rl_150);
        this.z.put(150, relativeLayout7);
        this.x.put(100, (HealthRadioButton) nsy.cMd_(view, R.id.iv_100));
        this.x.put(110, (HealthRadioButton) nsy.cMd_(view, R.id.iv_110));
        this.x.put(120, (HealthRadioButton) nsy.cMd_(view, R.id.iv_120));
        this.x.put(valueOf, (HealthRadioButton) nsy.cMd_(view, R.id.iv_130));
        this.x.put(140, (HealthRadioButton) nsy.cMd_(view, R.id.iv_140));
        this.x.put(150, (HealthRadioButton) nsy.cMd_(view, R.id.iv_150));
        relativeLayout.setOnClickListener(this.j);
        relativeLayout2.setOnClickListener(this.j);
        relativeLayout3.setOnClickListener(this.j);
        relativeLayout4.setOnClickListener(this.j);
        relativeLayout5.setOnClickListener(this.j);
        relativeLayout6.setOnClickListener(this.j);
        relativeLayout7.setOnClickListener(this.j);
        a(this.p);
    }

    private void cPX_(View view) {
        b((HealthTextView) nsy.cMd_(view, R.id.tv_40), 40);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_45), 45);
        b((HealthTextView) nsy.cMd_(view, R.id.tv_50), 50);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMd_(view, R.id.rl_close);
        this.ac.put(0, relativeLayout);
        this.y.put(0, (HealthRadioButton) nsy.cMd_(view, R.id.iv_close));
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMd_(view, R.id.rl_40);
        this.ac.put(40, relativeLayout2);
        RelativeLayout relativeLayout3 = (RelativeLayout) nsy.cMd_(view, R.id.rl_45);
        this.ac.put(45, relativeLayout3);
        RelativeLayout relativeLayout4 = (RelativeLayout) nsy.cMd_(view, R.id.rl_50);
        this.ac.put(50, relativeLayout4);
        this.y.put(40, (HealthRadioButton) nsy.cMd_(view, R.id.iv_40));
        this.y.put(45, (HealthRadioButton) nsy.cMd_(view, R.id.iv_45));
        this.y.put(50, (HealthRadioButton) nsy.cMd_(view, R.id.iv_50));
        relativeLayout.setOnClickListener(this.i);
        relativeLayout2.setOnClickListener(this.i);
        relativeLayout3.setOnClickListener(this.i);
        relativeLayout4.setOnClickListener(this.i);
        e(this.n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z, boolean z2, boolean z3) {
        int i;
        ReleaseLogUtil.e("DEVMGR_HeartRateSettingsActivity", "updateViewByHeartRateNumber isSendCommand:", Boolean.valueOf(z), ", isContinueHeartRateIsChecked:", Boolean.valueOf(z2), ", isMigrateHeartRateRaiseRemind:", Boolean.valueOf(z3));
        int i2 = this.p;
        this.f9102a = i2;
        if (z2 && i2 != 0) {
            this.ag.setText(getResources().getString(R.string._2130842696_res_0x7f021448, getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(10.0d, 1, 0)), UnitUtil.e(this.p, 1, 0)));
            String c = nvj.c(this.p);
            this.ad.setText(c);
            b(this.aj, CommonUtil.m(BaseApplication.getContext(), c));
            if (z) {
                b(1, this.p, true);
            }
        } else {
            try {
                i = Integer.parseInt(UnitUtil.e(10.0d, 1, 0));
            } catch (NumberFormatException unused) {
                LogUtil.b("HeartRateSettingsActivity", "updateViewByHeartRateNumber NumberFormatException");
                i = 10;
            }
            this.ag.setText(getResources().getString(R.string._2130843450_res_0x7f02173a, Integer.valueOf(i)));
            this.aj.setText(R.string._2130841259_res_0x7f020eab);
            this.ad.setText("");
            if (z) {
                b(0, 0, z3);
            } else if (z3) {
                this.q.setSwitchSetting("custom.heart_rate_raise_remind", this.p + "", null);
            }
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.w.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.w.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    private void b(HealthTextView healthTextView, int i) {
        healthTextView.setText(getResources().getQuantityString(R.plurals._2130903203_res_0x7f0300a3, i, Integer.valueOf(i)));
    }

    private void b(int i, int i2, boolean z) {
        LogUtil.a("HeartRateSettingsActivity", "openOrCloseHeartRateRaiseRemindEnable openOrClose : ", Integer.valueOf(i), " number: ", Integer.valueOf(i2));
        DeviceSettingsInteractors deviceSettingsInteractors = this.h;
        if (deviceSettingsInteractors == null) {
            LogUtil.h("HeartRateSettingsActivity", "openOrCloseHeartRateRaiseRemindEnable mDeviceInteractors is null");
        } else {
            deviceSettingsInteractors.d(i, i2, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        ReleaseLogUtil.e("DEVMGR_HeartRateSettingsActivity", "Enter updateHighItemSelectButtonDrawable");
        for (Map.Entry<Integer, HealthRadioButton> entry : this.x.entrySet()) {
            entry.getValue().setChecked(i == entry.getKey().intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ReleaseLogUtil.e("DEVMGR_HeartRateSettingsActivity", "Enter updateHighItemSelectButtonDrawable");
        for (Map.Entry<Integer, HealthRadioButton> entry : this.y.entrySet()) {
            entry.getValue().setChecked(i == entry.getKey().intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z, boolean z2, boolean z3) {
        DeviceCapability d;
        ReleaseLogUtil.e("DEVMGR_HeartRateSettingsActivity", "updateViewByHeartRateNumberDownRate isSendCommand:", Boolean.valueOf(z), ", isContinueHeartRateChecked:", Boolean.valueOf(z2), ", isMigrateHeartRateRaiseRemind:", Boolean.valueOf(z3));
        int i = this.n;
        this.e = i;
        if (z2 && i != 0) {
            HealthTextView healthTextView = this.ae;
            Resources resources = getResources();
            String quantityString = getResources().getQuantityString(R.plurals._2130903333_res_0x7f030125, 10, 10);
            Resources resources2 = getResources();
            int i2 = this.n;
            healthTextView.setText(resources.getString(R.string._2130846566_res_0x7f022366, quantityString, resources2.getQuantityString(R.plurals._2130903203_res_0x7f0300a3, i2, Integer.valueOf(i2))));
            String c = nvj.c(this.n);
            this.m.setText(c);
            b(this.al, CommonUtil.m(BaseApplication.getContext(), c));
            if (z) {
                c(1, this.n, true);
            }
        } else {
            this.ae.setText(getResources().getString(R.string._2130846567_res_0x7f022367, getResources().getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(10.0d, 1, 0))));
            this.al.setText(R.string._2130841259_res_0x7f020eab);
            this.m.setText("");
            if (z) {
                c(0, 0, z3);
            } else if (z3 && (d = cvs.d()) != null && d.isSupportHeartRateDownAlarm()) {
                this.q.setSwitchSetting("custom.heart_rate_down_remind", this.n + "", null);
                LogUtil.a("HeartRateSettingsActivity", "updateViewByHeartRateNumberDownRate update view by heart rate down");
            }
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.f.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.f.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }

    private void c(int i, int i2, boolean z) {
        LogUtil.a("HeartRateSettingsActivity", "openOrCloseHeartRateDownRemindEnable openOrClose: ", Integer.valueOf(i), " number: ", Integer.valueOf(i2));
        DeviceSettingsInteractors deviceSettingsInteractors = this.h;
        if (deviceSettingsInteractors == null) {
            LogUtil.h("HeartRateSettingsActivity", "openOrCloseHeartRateDownRemindEnable mDeviceInteractors is null");
        } else {
            deviceSettingsInteractors.a(i, i2, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.a("HeartRateSettingsActivity", "updateViewBySwitchStatus: ", Boolean.valueOf(z));
        if (!z) {
            this.ak.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.aj.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.ag.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.aa.setEnabled(false);
            this.ah.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            this.al.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.ae.setTextColor(getResources().getColor(R.color._2131296698_res_0x7f0901ba));
            this.ab.setEnabled(false);
            return;
        }
        this.ak.setTextColor(getResources().getColor(R.color._2131297823_res_0x7f09061f));
        this.aj.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.ag.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.aa.setEnabled(true);
        this.ah.setTextColor(getResources().getColor(R.color._2131297823_res_0x7f09061f));
        this.al.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.ae.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.ab.setEnabled(true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
