package com.huawei.ui.device.activity.donotdisturb;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.datatype.DataDeviceAvoidDisturbInfo;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import defpackage.cvs;
import defpackage.ixx;
import defpackage.jec;
import defpackage.jqi;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.nsz;
import defpackage.oae;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class NoDisturbSettingActivity extends BaseActivity implements View.OnClickListener {
    private HealthSwitchButton aa;
    private HealthTimePickerDialog ab;
    private HealthSwitchButton ac;
    private RelativeLayout ad;
    private ImageView ae;
    private HealthTextView af;
    private LinearLayout ah;
    private RelativeLayout aj;
    private HealthSwitchButton al;
    private HealthTextView am;
    private Context b;
    private HealthSubHeader d;
    private CustomTitleBar e;
    private HealthTextView f;
    private oae g;
    private DataDeviceAvoidDisturbInfo h;
    private DeviceSettingsInteractors j;
    private LinearLayout l;
    private ImageView m;
    private HealthTextView n;
    private jqi q;
    private int r;
    private HealthTextView t;
    private HealthSwitchButton u;
    private HealthTextView v;
    private NotificationPushInteractor w;
    private RelativeLayout x;
    private RelativeLayout y;
    private HealthTextView z;
    private DeviceCapability i = null;
    private boolean s = false;
    private int ag = 0;
    private int o = 0;
    private String ai = "";
    private String k = "";
    private Handler p = new e(this);
    private String c = "";

    /* renamed from: a, reason: collision with root package name */
    private CompoundButton.OnCheckedChangeListener f9077a = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity.3
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.a("NoDisturbSettingActivity", "mNotificationSwitchOnlyWear clicked isChecked is:", Boolean.valueOf(z));
            if (cvs.e(NoDisturbSettingActivity.this.c) == null || !cvs.e(NoDisturbSettingActivity.this.c).isSupportWearMessagePush()) {
                nrh.e(NoDisturbSettingActivity.this.b, R.string._2130841440_res_0x7f020f60);
            } else {
                NoDisturbSettingActivity.this.w.a(z, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity.3.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("NoDisturbSettingActivity", 1, "NoDisturbSettingActivity", "setWearMessagePushSwitchStatus errorCode is:", Integer.valueOf(i), " objectData is:", obj);
                        NoDisturbSettingActivity.this.k();
                    }
                });
            }
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    class e extends Handler {
        WeakReference<NoDisturbSettingActivity> d;

        e(NoDisturbSettingActivity noDisturbSettingActivity) {
            this.d = new WeakReference<>(noDisturbSettingActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("NoDisturbSettingActivity", "handleMessage message is null");
                return;
            }
            super.handleMessage(message);
            if (this.d.get() == null) {
                LogUtil.h("NoDisturbSettingActivity", "handleMessage activity is null");
                return;
            }
            int i = message.what;
            if (i == 2) {
                NoDisturbSettingActivity.this.f();
                return;
            }
            if (i == 3) {
                NoDisturbSettingActivity.this.c();
                return;
            }
            if (i == 4) {
                NoDisturbSettingActivity.this.n();
                NoDisturbSettingActivity.this.s();
                if (NoDisturbSettingActivity.this.s) {
                    return;
                }
                NoDisturbSettingActivity.this.k();
                return;
            }
            if (i == 5) {
                if (message.obj instanceof Boolean) {
                    NoDisturbSettingActivity.this.a(((Boolean) message.obj).booleanValue());
                    return;
                } else {
                    LogUtil.h("NoDisturbSettingActivity", "handleMessage message.obj not instanceof Boolean");
                    return;
                }
            }
            LogUtil.h("NoDisturbSettingActivity", "handleMessage default:", Integer.valueOf(message.what));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_settings_nodisturb);
        this.w = new NotificationPushInteractor(this.b);
        this.y = (RelativeLayout) nsy.cMc_(this, R.id.setting_device_notification_only_wearable);
        this.v = (HealthTextView) nsy.cMc_(this, R.id.content_only_wearable);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) nsy.cMc_(this, R.id.switch_button_notification_only_wearable);
        this.u = healthSwitchButton;
        healthSwitchButton.setChecked(this.w.c());
        this.u.setOnCheckedChangeListener(this.f9077a);
        g();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.w.c()) {
            LogUtil.a("NoDisturbSettingActivity", "onResume isWearPushEnabled is true");
            this.u.setChecked(true);
        } else {
            LogUtil.a("NoDisturbSettingActivity", "onResume isWearPushEnabled is false");
            this.u.setChecked(false);
        }
        if (cvs.e(this.c) != null && cvs.e(this.c).isSupportWearMessagePush()) {
            this.y.setVisibility(0);
        } else {
            this.y.setVisibility(8);
        }
    }

    private void g() {
        LogUtil.a("NoDisturbSettingActivity", "initData");
        Context applicationContext = getApplicationContext();
        this.b = applicationContext;
        this.j = DeviceSettingsInteractors.d(applicationContext);
        this.g = oae.c(this.b);
        this.q = jqi.a();
        if (this.j == null || this.g == null) {
            LogUtil.h("NoDisturbSettingActivity", "initData mDeviceSettingsInteractors is null or mDeviceInteractors is null");
            return;
        }
        Intent intent = getIntent();
        if (intent != null) {
            LogUtil.h("NoDisturbSettingActivity", "initData intent is null");
            this.c = intent.getStringExtra("device_id");
        }
        this.i = cvs.e(this.c);
        e();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        this.r = calendar.get(11);
    }

    private void e() {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        this.q.getSwitchSetting("custom.avoid_disturb", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity.5
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r6v25, types: [java.util.List] */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_NoDisturbSettingActivity", "getAvoidDisturb time: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
                if (NoDisturbSettingActivity.this.isFinishing() || NoDisturbSettingActivity.this.isDestroyed()) {
                    ReleaseLogUtil.e("DEVMGR_NoDisturbSettingActivity", "onResponse isFinishing() or isDestroyed()");
                    return;
                }
                ArrayList arrayList = new ArrayList(16);
                if (i == 0 && (obj instanceof String)) {
                    String str = (String) obj;
                    ReleaseLogUtil.e("DEVMGR_NoDisturbSettingActivity", "getAvoidDisturb value = ", str);
                    try {
                        arrayList = (List) new Gson().fromJson(str, new TypeToken<List<DataDeviceAvoidDisturbInfo>>() { // from class: com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity.5.3
                        }.getType());
                    } catch (JsonSyntaxException unused) {
                        LogUtil.b("NoDisturbSettingActivity", "getAvoidDisturb JsonSyntaxException");
                    }
                }
                if (arrayList.isEmpty()) {
                    LogUtil.a("NoDisturbSettingActivity", "getAvoidDisturb avoidDisturbInfoList is invalid");
                    arrayList.add(new DataDeviceAvoidDisturbInfo());
                }
                NoDisturbSettingActivity.this.l();
                NoDisturbSettingActivity.this.h = (DataDeviceAvoidDisturbInfo) arrayList.get(0);
                LogUtil.a("NoDisturbSettingActivity", "getAvoidDisturb mDataDeviceAvoidDisturbInfo is:", NoDisturbSettingActivity.this.h);
                NoDisturbSettingActivity noDisturbSettingActivity = NoDisturbSettingActivity.this;
                noDisturbSettingActivity.ag = noDisturbSettingActivity.h.getDeviceAvoidDisturbStartTime();
                NoDisturbSettingActivity noDisturbSettingActivity2 = NoDisturbSettingActivity.this;
                noDisturbSettingActivity2.o = noDisturbSettingActivity2.h.getDeviceAvoidDisturbEndTime();
                NoDisturbSettingActivity.this.o();
                Message obtainMessage = NoDisturbSettingActivity.this.p.obtainMessage();
                obtainMessage.what = 4;
                NoDisturbSettingActivity.this.p.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        DeviceCapability deviceCapability = this.i;
        if (deviceCapability == null || !deviceCapability.isSupportQueryAllowDisturbContent()) {
            return;
        }
        int d = this.g.d(this.c);
        if ((d & 20) == 20) {
            this.s = true;
        }
        LogUtil.a("NoDisturbSettingActivity", "requestDeviceAllowDisturbItem allowDisturbValue is:", Integer.valueOf(d), " mIsHaveAllowDisturbInfo is:", Boolean.valueOf(this.s));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.f = (HealthTextView) nsy.cMc_(this, R.id.device_settings_disturb_des);
        this.x = (RelativeLayout) nsy.cMc_(this, R.id.device_settings_nodisturb_start_layout);
        this.ac = (HealthSwitchButton) nsy.cMc_(this, R.id.device_settings_nodisturb_start_switch_button);
        this.ad = (RelativeLayout) nsy.cMc_(this, R.id.device_settings_nodisturb_scheduled_layout);
        this.aa = (HealthSwitchButton) nsy.cMc_(this, R.id.device_settings_nodisturb_scheduled_switch_button);
        i();
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.ae.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.m.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.ae.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.m.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.aj = (RelativeLayout) nsy.cMc_(this, R.id.device_settings_disturb_up_layout);
        this.d = (HealthSubHeader) nsy.cMc_(this, R.id.device_settings_can_disturb_tv);
        this.al = (HealthSwitchButton) nsy.cMc_(this, R.id.device_settings_disturb_up_switch_button);
        j();
        this.x.setOnClickListener(this);
        this.ad.setOnClickListener(this);
        this.ah.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.aj.setOnClickListener(this);
        if (!this.s) {
            this.aj.setVisibility(8);
            this.d.setVisibility(8);
        }
        h();
    }

    private void i() {
        this.ah = (LinearLayout) nsy.cMc_(this, R.id.device_settings_nodisturb_start_time_layout);
        this.l = (LinearLayout) nsy.cMc_(this, R.id.device_settings_nodisturb_end_time_layout);
        this.e = (CustomTitleBar) nsy.cMc_(this, R.id.device_settings_nodisturb_title_bar);
        this.z = (HealthTextView) nsy.cMc_(this, R.id.device_settings_nodisturb_scheduled_text);
        this.am = (HealthTextView) nsy.cMc_(this, R.id.device_settings_nodisturb_start_time_title_tv);
        this.t = (HealthTextView) nsy.cMc_(this, R.id.device_settings_nodisturb_end_time_title_tv);
        this.af = (HealthTextView) nsy.cMc_(this, R.id.device_settings_nodisturb_start_time_tv);
        this.n = (HealthTextView) nsy.cMc_(this, R.id.device_settings_nodisturb_end_time_tv);
        this.ae = (ImageView) nsy.cMc_(this, R.id.device_settings_nodisturb_start_time_iv);
        this.m = (ImageView) nsy.cMc_(this, R.id.device_settings_nodisturb_end_time_iv);
        HealthTextView healthTextView = this.af;
        if (healthTextView == null) {
            ReleaseLogUtil.d("DEVMGR_NoDisturbSettingActivity", "initTimeView mStartTimeText == null");
            nsz.cLX_("NoDisturbSettingActivity", getWindow().getDecorView(), R.id.device_settings_nodisturb_start_time_tv);
        } else {
            healthTextView.setText(this.ai);
            this.n.setText(this.k);
        }
    }

    private void h() {
        this.e.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NoDisturbSettingActivity.this.g.e(NoDisturbSettingActivity.this.c) == 2) {
                    NoDisturbSettingActivity.this.m();
                } else {
                    NoDisturbSettingActivity.this.t();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void j() {
        NoDisturbSettingSubHeaderAdapter noDisturbSettingSubHeaderAdapter = new NoDisturbSettingSubHeaderAdapter(this.b, 0);
        this.d.setLayoutManager(new LinearLayoutManager(this.b));
        this.d.setAdapter(noDisturbSettingSubHeaderAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        String b = jec.b(this.ag);
        String b2 = jec.b(this.o);
        this.ai = this.j.d(b);
        if (this.ag > this.o) {
            this.k = this.b.getResources().getString(R.string._2130842215_res_0x7f021267, this.j.d(b2));
        } else {
            this.k = this.j.d(b2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("NoDisturbSettingActivity", "updateViewByDisturbData mDataDeviceAvoidDisturbInfo is:", this.h);
        if (this.h.getDeviceAvoidDisturbSwitch() == 1) {
            d();
        } else {
            this.ac.setChecked(false);
            this.y.setEnabled(true);
            this.ad.setEnabled(true);
            this.u.setEnabled(true);
            this.aa.setEnabled(true);
            this.v.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.z.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            if (this.h.getDeviceAvoidDisturbTimeSwitch() == 1) {
                this.aa.setChecked(true);
                this.ah.setEnabled(true);
                this.l.setEnabled(true);
                this.am.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                this.t.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                this.af.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
                this.n.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            } else {
                this.aa.setChecked(false);
                this.ah.setEnabled(false);
                this.l.setEnabled(false);
                this.am.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
                this.t.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
                this.af.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
                this.n.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
            }
        }
        if (this.s) {
            q();
        }
    }

    private void d() {
        this.ac.setChecked(true);
        this.y.setEnabled(false);
        this.ad.setEnabled(false);
        this.u.setEnabled(false);
        this.aa.setEnabled(false);
        this.v.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        this.z.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        this.ah.setEnabled(false);
        this.l.setEnabled(false);
        this.am.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        this.t.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        this.af.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        this.n.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
        if (this.h.getDeviceAvoidDisturbTimeSwitch() == 1) {
            this.aa.setChecked(true);
        } else {
            this.aa.setChecked(false);
        }
    }

    private void q() {
        this.q.getSwitchSetting("auto_light_screen", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_NoDisturbSettingActivity", "updateAllowDisturbInfoView errorCode = ", Integer.valueOf(i), " ; objectData = ", obj);
                boolean z = true;
                if (i == 0 && (obj instanceof String)) {
                    z = true ^ "0".equals((String) obj);
                }
                Message obtainMessage = NoDisturbSettingActivity.this.p.obtainMessage();
                obtainMessage.what = 5;
                obtainMessage.obj = Boolean.valueOf(z);
                NoDisturbSettingActivity.this.p.sendMessage(obtainMessage);
            }
        });
        if (a()) {
            this.d.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            if ((this.h.getDeviceAvoidDisturbType() & 20) == 20) {
                this.f.setText(getResources().getString(R.string._2130842349_res_0x7f0212ed));
                this.al.setChecked(true);
                return;
            } else {
                this.f.setText(getResources().getString(R.string._2130841474_res_0x7f020f82));
                this.al.setChecked(false);
                return;
            }
        }
        this.d.setVisibility(8);
        this.aj.setVisibility(8);
        this.f.setText(getResources().getString(R.string._2130841474_res_0x7f020f82));
        this.al.setChecked(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.x.setClickable(false);
        this.ad.setClickable(false);
        this.aj.setClickable(false);
        int id = view.getId();
        if (id == R.id.device_settings_nodisturb_scheduled_layout) {
            LogUtil.a("NoDisturbSettingActivity", "onClick device_settings_nodisturb_scheduled_layout");
            if (this.aa.isChecked()) {
                this.h.setDeviceAvoidDisturbTimeSwitch(0);
            } else {
                this.h.setDeviceAvoidDisturbTimeSwitch(1);
            }
            s();
        } else if (id == R.id.device_settings_nodisturb_start_layout) {
            LogUtil.a("NoDisturbSettingActivity", "onClick device_settings_nodisturb_start_layout");
            if (this.ac.isChecked()) {
                this.h.setDeviceAvoidDisturbSwitch(0);
            } else {
                this.h.setDeviceAvoidDisturbSwitch(1);
            }
            s();
        } else if (id == R.id.device_settings_nodisturb_start_time_layout) {
            b(0);
        } else if (id == R.id.device_settings_nodisturb_end_time_layout) {
            b(1);
        } else if (id == R.id.device_settings_disturb_up_layout) {
            b();
        } else {
            LogUtil.h("NoDisturbSettingActivity", "onClick viewId is:", Integer.valueOf(id));
        }
        this.x.setClickable(true);
        this.ad.setClickable(true);
        this.aj.setClickable(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        if (this.al.isChecked()) {
            this.h.setDeviceAvoidDisturbType(0);
        } else {
            this.h.setDeviceAvoidDisturbType(20);
        }
        s();
    }

    private void b(final int i) {
        String string;
        Calendar calendar = Calendar.getInstance();
        if (i == 0) {
            string = getResources().getString(R.string._2130841730_res_0x7f021082);
            calendar.set(11, this.ag / 100);
            calendar.set(12, this.ag % 100);
        } else {
            string = getResources().getString(R.string._2130841731_res_0x7f021083);
            calendar.set(11, this.o / 100);
            calendar.set(12, this.o % 100);
        }
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog(this, new HealthTimePickerDialog.TimeSelectedListener() { // from class: com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity.1
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i2, int i3) {
                LogUtil.a("NoDisturbSettingActivity", "showNewTimeDialog hour is:", Integer.valueOf(i2), " minute is:", Integer.valueOf(i3));
                NoDisturbSettingActivity.this.r = i2;
                if (i == 0) {
                    NoDisturbSettingActivity noDisturbSettingActivity = NoDisturbSettingActivity.this;
                    noDisturbSettingActivity.ag = (noDisturbSettingActivity.r * 100) + i3;
                } else {
                    NoDisturbSettingActivity noDisturbSettingActivity2 = NoDisturbSettingActivity.this;
                    noDisturbSettingActivity2.o = (noDisturbSettingActivity2.r * 100) + i3;
                }
                NoDisturbSettingActivity.this.o();
                NoDisturbSettingActivity.this.af.setText(NoDisturbSettingActivity.this.ai);
                NoDisturbSettingActivity.this.n.setText(NoDisturbSettingActivity.this.k);
                if (NoDisturbSettingActivity.this.ab != null) {
                    NoDisturbSettingActivity.this.ab.dismiss();
                    NoDisturbSettingActivity.this.ab = null;
                }
            }
        });
        this.ab = healthTimePickerDialog;
        healthTimePickerDialog.e(0, 0, 0, calendar.get(11), calendar.get(12));
        this.ab.b(string);
        this.ab.show();
    }

    private boolean a() {
        return this.aj.getVisibility() == 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("all_day", Integer.valueOf(this.ac.isChecked() ? 1 : 0));
        if (cvs.e(this.c) != null && cvs.e(this.c).isSupportWearMessagePush()) {
            hashMap.put("not_worn", Integer.valueOf(this.u.isChecked() ? 1 : 0));
        }
        hashMap.put("timing", Integer.valueOf(this.aa.isChecked() ? 1 : 0));
        if (this.s) {
            hashMap.put("up_hand", Integer.valueOf(this.al.isChecked() ? 1 : 0));
        }
        ixx.d().d(this.b, AnalyticsValue.SETTING_NOT_DISTURB_1090031.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        LogUtil.a("NoDisturbSettingActivity", "showNoDeviceConnectedToast");
        nrh.b(this.b, R.string.IDS_connect_device_fail);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.j == null) {
            LogUtil.h("NoDisturbSettingActivity", "sendCommandToDevice cause null pointer");
            return;
        }
        if (this.h.getDeviceAvoidDisturbTimeSwitch() == 1 && this.h.getDeviceAvoidDisturbSwitch() == 0) {
            int i = this.ag;
            if (i == this.o) {
                nrh.b(this, R.string._2130841445_res_0x7f020f65);
                return;
            } else {
                this.h.setDeviceAvoidDisturbStartTime(i);
                this.h.setDeviceAvoidDisturbEndTime(this.o);
            }
        }
        LogUtil.a("NoDisturbSettingActivity", "sendCommandToDevice mDataDeviceAvoidDisturbInfo is:", this.h);
        this.j.d(this.c, this.h, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 != 0 || !(obj instanceof Integer) || ((Integer) obj).intValue() != 100000) {
                    NoDisturbSettingActivity.this.p.sendEmptyMessage(3);
                } else {
                    NoDisturbSettingActivity.this.p.sendEmptyMessage(2);
                    NoDisturbSettingActivity.this.k();
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("NoDisturbSettingActivity", "onDestroy start");
        CommonUtil.a(this.b);
        super.onDestroy();
        LogUtil.a("NoDisturbSettingActivity", "onDestroy end");
        setResult(0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("NoDisturbSettingActivity", "handleWhenSetNoDisturbSuccess");
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("NoDisturbSettingActivity", "handleWhenSetNoDisturbFail");
        nrh.b(this.b, R.string._2130841508_res_0x7f020fa4);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
