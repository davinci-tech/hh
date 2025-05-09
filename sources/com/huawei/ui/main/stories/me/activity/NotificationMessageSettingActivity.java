package com.huawei.ui.main.stories.me.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.push.HmsMessaging;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.dss;
import defpackage.enh;
import defpackage.gzl;
import defpackage.ixx;
import defpackage.jds;
import defpackage.jec;
import defpackage.jfq;
import defpackage.jpp;
import defpackage.jqi;
import defpackage.lcu;
import defpackage.njh;
import defpackage.pxp;
import defpackage.qif;
import defpackage.riy;
import defpackage.sdr;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StepNotificationByHardWareUtils;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class NotificationMessageSettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private HealthTextView aa;
    private HealthSwitchButton ab;
    private HealthSwitchButton ac;
    private LinearLayout ad;
    private LinearLayout ae;
    private HealthSwitchButton af;
    private HealthTextView ag;
    private HealthTextView ah;
    private enh ai;
    private HealthSwitchButton aj;
    private HealthSwitchButton ak;
    private HealthTextView al;
    private HealthTextView am;
    private HealthTextView an;
    private HealthSwitchButton ao;
    private LinearLayout ap;
    private HealthDivider aq;
    private HealthTextView ar;
    private HealthSwitchButton as;
    private LinearLayout at;
    private HealthTextView au;
    private LinearLayout av;
    private HealthTextView aw;
    private HealthTextView ax;
    private LinearLayout az;
    private HealthSwitchButton ba;
    private Context e;
    private HealthSwitchButton f;
    private HealthTextView h;
    private LinearLayout i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthSwitchButton m;
    private HealthSwitchButton n;
    private LinearLayout o;
    private HealthDivider t;
    private LinearLayout v;
    private LinearLayout w;
    private LinearLayout x;
    private HealthTextView z;
    private boolean c = false;
    private boolean g = false;
    private boolean d = false;
    private boolean b = true;

    /* renamed from: a, reason: collision with root package name */
    private boolean f10336a = true;
    private boolean q = true;
    private boolean y = true;
    private boolean u = false;
    private boolean p = false;
    private HealthOpenSDK r = null;
    private Handler s = new Handler() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("NotificationMessageSettingActivity", "handleMessage message is null");
            }
            super.handleMessage(message);
            switch (message.what) {
                case 100:
                    LogUtil.a("NotificationMessageSettingActivity", "handleMessage coreSleep switchStatus:", Boolean.valueOf(NotificationMessageSettingActivity.this.q));
                    NotificationMessageSettingActivity.this.m.setChecked(NotificationMessageSettingActivity.this.q);
                    break;
                case 101:
                    LogUtil.a("NotificationMessageSettingActivity", "handleMessage motionPath switchStatus:", Boolean.valueOf(NotificationMessageSettingActivity.this.y));
                    NotificationMessageSettingActivity.this.ab.setChecked(NotificationMessageSettingActivity.this.y);
                    break;
                case 102:
                    if (message.getData() == null) {
                        LogUtil.h("NotificationMessageSettingActivity", "data = null");
                        break;
                    } else {
                        NotificationMessageSettingActivity.this.af.setChecked(Boolean.parseBoolean(message.getData().getString("status")));
                        break;
                    }
                case 103:
                    Object obj = message.obj;
                    if (obj instanceof HashMap) {
                        NotificationMessageSettingActivity.this.e((HashMap<String, String>) obj);
                        break;
                    }
                    break;
                case 104:
                    if (NotificationMessageSettingActivity.this.ai != null) {
                        NotificationMessageSettingActivity.this.ap.setVisibility(0);
                        NotificationMessageSettingActivity.this.aq.setVisibility(0);
                        NotificationMessageSettingActivity.this.ao.setChecked("1".equals(NotificationMessageSettingActivity.this.ai.a()));
                        if (NotificationMessageSettingActivity.this.e instanceof CompoundButton.OnCheckedChangeListener) {
                            NotificationMessageSettingActivity.this.ao.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) NotificationMessageSettingActivity.this.e);
                            break;
                        }
                    } else {
                        LogUtil.h("NotificationMessageSettingActivity", "mPathAutoSwitchInfo == null");
                        break;
                    }
                    break;
                default:
                    LogUtil.h("NotificationMessageSettingActivity", "handleMessage");
                    break;
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Bundle extras;
        LogUtil.a("NotificationMessageSettingActivity", "onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.hw_show_settings_notification_message);
        this.e = this;
        this.r = dss.c(this).d();
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            this.g = extras.getBoolean("isOpenRealTimeStep");
            this.c = extras.getBoolean("isOpenCompleteGoal");
            this.d = extras.getBoolean("isOpenNotification");
        }
        f();
        b();
        a();
        d();
        k();
        c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.p = true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        pxp.c(3, "900200011", this.f.isChecked());
        pxp.c(3, "900200010", this.ak.isChecked());
        pxp.c(3, "900200004", this.ac.isChecked());
        Handler handler = this.s;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.s = null;
        }
    }

    private void b() {
        jqi.a().getSwitchSetting("core_sleep_switch_status", new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("NotificationMessageSettingActivity", "getCoreSleepSwitchStatus errorCode:", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof String)) {
                    NotificationMessageSettingActivity.this.q = true;
                } else {
                    String valueOf = String.valueOf(obj);
                    LogUtil.a("NotificationMessageSettingActivity", "getCoreSleepSwitchStatus switchStatus:", valueOf);
                    if (jds.c(valueOf, 10) == 1) {
                        NotificationMessageSettingActivity.this.q = true;
                    } else {
                        NotificationMessageSettingActivity.this.q = false;
                    }
                }
                if (NotificationMessageSettingActivity.this.s != null) {
                    NotificationMessageSettingActivity.this.s.sendEmptyMessage(100);
                } else {
                    LogUtil.h("NotificationMessageSettingActivity", "getCoreSleepSwitchStatus, mHandler is null");
                }
            }
        });
    }

    private void a() {
        jqi.a().getSwitchSetting("motion_path_switch_status", new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("NotificationMessageSettingActivity", "getMotionPathSwitchStatus errorCode:", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof String)) {
                    NotificationMessageSettingActivity.this.y = true;
                } else {
                    String valueOf = String.valueOf(obj);
                    LogUtil.a("NotificationMessageSettingActivity", "getMotionPathSwitchStatus switchStatus:", valueOf);
                    if (jds.c(valueOf, 10) == 1) {
                        NotificationMessageSettingActivity.this.y = true;
                    } else {
                        NotificationMessageSettingActivity.this.y = false;
                    }
                }
                if (NotificationMessageSettingActivity.this.s != null) {
                    NotificationMessageSettingActivity.this.s.sendEmptyMessage(101);
                } else {
                    LogUtil.h("NotificationMessageSettingActivity", "getMotionPathSwitchStatus, mHandler is null");
                }
            }
        });
    }

    private void d() {
        if (!sdr.a() && Utils.o()) {
            LogUtil.a("NotificationMessageSettingActivity", "is not support notify reminder");
            this.v.setVisibility(8);
            findViewById(R.id.divider4).setVisibility(8);
            this.x.setVisibility(8);
            findViewById(R.id.divider5).setVisibility(8);
        }
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("900200004");
        arrayList.add("900200010");
        arrayList.add("900200011");
        njh.c(arrayList, new a());
    }

    private void c() {
        LogUtil.a("NotificationMessageSettingActivity", "enter getRouteAutoSwitch");
        if (Utils.o()) {
            return;
        }
        gzl.b(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            LogUtil.a("NotificationMessageSettingActivity", "setSwitchButton, switchStatusMap is null");
            return;
        }
        LogUtil.a("NotificationMessageSettingActivity", "setSwitchButton, switchStatusMap is ", hashMap);
        if (hashMap.containsKey("900200004")) {
            this.ac.setChecked("1".equals(hashMap.get("900200004")));
        }
        if (hashMap.containsKey("900200010")) {
            this.ak.setChecked("1".equals(hashMap.get("900200010")));
        }
        if (hashMap.containsKey("900200011")) {
            this.f.setChecked("1".equals(hashMap.get("900200011")));
        }
    }

    private void f() {
        ((CustomTitleBar) findViewById(R.id.message_notification_title)).setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        ((HealthSubHeader) findViewById(R.id.sport_sub_header)).setSubHeaderBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        ((HealthSubHeader) findViewById(R.id.health_sub_header)).setSubHeaderBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.ad = (LinearLayout) findViewById(R.id.layout_system_notification);
        this.ae = (LinearLayout) findViewById(R.id.layout_popup_message);
        this.at = (LinearLayout) findViewById(R.id.layout_step_notification);
        this.az = (LinearLayout) findViewById(R.id.layout_step_gaol_success);
        this.i = (LinearLayout) findViewById(R.id.layout_core_sleep_report);
        this.av = (LinearLayout) findViewById(R.id.layout_sport_track);
        this.ag = (HealthTextView) this.ad.findViewById(R.id.reminder_switch_title);
        this.ah = (HealthTextView) this.ad.findViewById(R.id.reminder_switch_description);
        this.af = (HealthSwitchButton) this.ad.findViewById(R.id.reminder_switch_btn);
        this.al = (HealthTextView) this.ae.findViewById(R.id.notification_switch_title);
        this.aj = (HealthSwitchButton) this.ae.findViewById(R.id.notification_switch_btn);
        this.ax = (HealthTextView) this.at.findViewById(R.id.notification_switch_title);
        this.as = (HealthSwitchButton) this.at.findViewById(R.id.notification_switch_btn);
        this.au = (HealthTextView) this.az.findViewById(R.id.notification_switch_title);
        this.ba = (HealthSwitchButton) this.az.findViewById(R.id.notification_switch_btn);
        this.k = (HealthTextView) this.i.findViewById(R.id.notification_switch_title);
        this.m = (HealthSwitchButton) this.i.findViewById(R.id.notification_switch_btn);
        this.aw = (HealthTextView) this.av.findViewById(R.id.notification_switch_title);
        this.ab = (HealthSwitchButton) this.av.findViewById(R.id.notification_switch_btn);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_moving_remind);
        this.w = linearLayout;
        this.z = (HealthTextView) linearLayout.findViewById(R.id.reminder_switch_title);
        this.aa = (HealthTextView) this.w.findViewById(R.id.reminder_switch_description);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) this.w.findViewById(R.id.reminder_switch_btn);
        this.ac = healthSwitchButton;
        healthSwitchButton.setChecked(true);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.layout_progress_remind);
        this.v = linearLayout2;
        this.am = (HealthTextView) linearLayout2.findViewById(R.id.reminder_switch_title);
        this.an = (HealthTextView) this.v.findViewById(R.id.reminder_switch_description);
        HealthSwitchButton healthSwitchButton2 = (HealthSwitchButton) this.v.findViewById(R.id.reminder_switch_btn);
        this.ak = healthSwitchButton2;
        healthSwitchButton2.setChecked(true);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.layout_achieve_remind);
        this.x = linearLayout3;
        this.j = (HealthTextView) linearLayout3.findViewById(R.id.reminder_switch_title);
        this.h = (HealthTextView) this.x.findViewById(R.id.reminder_switch_description);
        HealthSwitchButton healthSwitchButton3 = (HealthSwitchButton) this.x.findViewById(R.id.reminder_switch_btn);
        this.f = healthSwitchButton3;
        healthSwitchButton3.setChecked(true);
        this.as.setOnCheckedChangeListener(this);
        this.ba.setOnCheckedChangeListener(this);
        this.m.setOnCheckedChangeListener(this);
        this.ab.setOnCheckedChangeListener(this);
        this.ac.setOnCheckedChangeListener(this);
        this.ak.setOnCheckedChangeListener(this);
        this.f.setOnCheckedChangeListener(this);
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.layout_route_auto_punch_remind);
        this.ap = linearLayout4;
        this.ar = (HealthTextView) linearLayout4.findViewById(R.id.notification_switch_title);
        this.ao = (HealthSwitchButton) this.ap.findViewById(R.id.notification_switch_btn);
        this.aq = (HealthDivider) findViewById(R.id.divider6);
        h();
        j();
    }

    private void h() {
        this.ag.setText(getString(R$string.IDS_hw_me_settings_message_noticebar));
        this.ah.setText(getString(R$string.IDS_hw_me_settings_message_noticebar_description));
        this.al.setText(getString(R$string.IDS_hw_me_settings_message_promotion));
        this.ax.setText(getString(R$string.IDS_hw_show_setting_real_time_push_steps));
        this.au.setText(getString(R$string.IDS_hw_show_setting_steps_target_complete_remind));
        this.k.setText(getString(R$string.IDS_hw_me_settings_message_sleep_notify));
        this.aw.setText(getString(R$string.IDS_hw_me_settings_message_track_notify));
        this.ar.setText(getString(R$string.IDS_hw_routes_auto_punch_route_remind));
        this.z.setText(getString(R$string.IDS_settings_moving_remind));
        this.aa.setText(getString(R$string.IDS_stood_up_remind_description, new Object[]{UnitUtil.e(1.0d, 1, 0)}));
        this.am.setText(getString(R$string.IDS_progress_remind));
        this.an.setText(getString(R$string.IDS_progress_remind_description));
        this.j.setText(getString(R$string.IDS_achieve_remind));
        this.h.setText(getString(R$string.IDS_achieve_remind_description));
    }

    private void j() {
        if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
            this.ad.setVisibility(8);
            cancelLayoutById(findViewById(R.id.divider));
        }
        this.as.setChecked(this.g);
        this.ba.setChecked(this.c);
        if (!this.g) {
            this.b = false;
        }
        if (!this.c) {
            this.f10336a = false;
        }
        HealthDivider healthDivider = (HealthDivider) findViewById(R.id.divider2);
        HealthDivider healthDivider2 = (HealthDivider) findViewById(R.id.divider3);
        if (!this.d) {
            this.at.setVisibility(8);
            this.az.setVisibility(8);
            healthDivider.setVisibility(8);
            healthDivider2.setVisibility(8);
        }
        if (!StepNotificationByHardWareUtils.a()) {
            this.at.setVisibility(8);
            healthDivider.setVisibility(8);
        }
        this.aj.setChecked(!"0".equals(SharedPreferenceManager.b(this.e, Integer.toString(10000), "health_msg_switch_promotion")));
        this.af.setChecked(!"0".equals(SharedPreferenceManager.b(this.e, Integer.toString(10000), "health_msg_switch_noticebar")));
        this.aj.setOnCheckedChangeListener(this);
        this.af.setOnCheckedChangeListener(this);
        l();
        e();
        this.ap.setVisibility(8);
        this.aq.setVisibility(8);
    }

    private void b(boolean z) {
        if (z) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, 20);
            calendar.set(12, 0);
            calendar.set(13, 0);
            long timeInMillis = calendar.getTimeInMillis();
            if (System.currentTimeMillis() > timeInMillis) {
                calendar.add(5, 1);
                timeInMillis = calendar.getTimeInMillis();
            }
            LogUtil.a("NotificationMessageSettingActivity", "setting time ", jec.e(timeInMillis));
            riy.b(1000, timeInMillis, 86400000L);
            return;
        }
        riy.c(1000);
    }

    private void l() {
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                if (view != null) {
                    if (NotificationMessageSettingActivity.this.q) {
                        NotificationMessageSettingActivity.this.m.setChecked(false);
                        NotificationMessageSettingActivity.this.q = false;
                        str = "0";
                    } else {
                        NotificationMessageSettingActivity.this.m.setChecked(true);
                        NotificationMessageSettingActivity.this.q = true;
                        str = "1";
                    }
                    LogUtil.a("NotificationMessageSettingActivity", "coreSleep click switchStatus:", Boolean.valueOf(NotificationMessageSettingActivity.this.q));
                    jqi.a().setSwitchSetting("core_sleep_switch_status", str, null);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("NotificationMessageSettingActivity", "mCoreSleepSyncCompleteRemindSwitch view is null");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ab.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                if (view != null) {
                    if (NotificationMessageSettingActivity.this.y) {
                        NotificationMessageSettingActivity.this.ab.setChecked(false);
                        NotificationMessageSettingActivity.this.y = false;
                        str = "0";
                    } else {
                        NotificationMessageSettingActivity.this.ab.setChecked(true);
                        NotificationMessageSettingActivity.this.y = true;
                        str = "1";
                    }
                    LogUtil.a("NotificationMessageSettingActivity", "motionPath click switchStatus:", Boolean.valueOf(NotificationMessageSettingActivity.this.y));
                    jqi.a().setSwitchSetting("motion_path_switch_status", str, null);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("NotificationMessageSettingActivity", "mMotionPathSyncCompleteRemindSwitch view is null");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        HealthSwitchButton healthSwitchButton;
        HealthSwitchButton healthSwitchButton2;
        HealthSwitchButton healthSwitchButton3;
        if (compoundButton == this.as) {
            c(z);
        } else if (compoundButton == this.ba) {
            e(z);
        } else {
            if (compoundButton == this.aj) {
                c(AnalyticsValue.HEALTH_MINE_SETTINGS_PROMOTION_2040046.value(), z ? "1" : "0");
                this.aj.setChecked(z);
                SharedPreferenceManager.e(this.e, Integer.toString(10000), "health_msg_switch_promotion", z ? "1" : "0", new StorageParams());
            } else if (compoundButton == this.af) {
                g(z);
            } else {
                boolean z2 = this.p;
                if (z2 && compoundButton == (healthSwitchButton3 = this.ac)) {
                    a(healthSwitchButton3, "900200004", z);
                } else if (z2 && compoundButton == (healthSwitchButton2 = this.ak)) {
                    a(healthSwitchButton2, "900200010", z);
                } else if (z2 && compoundButton == (healthSwitchButton = this.f)) {
                    a(healthSwitchButton, "900200011", z);
                } else if (compoundButton == this.ao) {
                    LogUtil.a("NotificationMessageSettingActivity", "mRouteAutoPunchRemindSwitch isChecked is ", Boolean.valueOf(z));
                    enh enhVar = this.ai;
                    if (enhVar == null) {
                        LogUtil.h("NotificationMessageSettingActivity", "mPathAutoSwitchInfo == null");
                        ViewClickInstrumentation.clickOnView(compoundButton);
                        return;
                    } else {
                        if (TextUtils.isEmpty(enhVar.c())) {
                            LogUtil.a("NotificationMessageSettingActivity", "mPathAutoSwitchInfo.getParticipatePushSwitch() isEmpty");
                            this.ai.a("1");
                        }
                        this.ai.e(z);
                        gzl.b(this.ai);
                    }
                } else {
                    LogUtil.h("NotificationMessageSettingActivity", "no this button");
                }
            }
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void a(HealthSwitchButton healthSwitchButton, String str, boolean z) {
        LogUtil.a("NotificationMessageSettingActivity", "changeSwitchAndSend, configId is ", str, ", isChecked is ", Boolean.valueOf(z));
        healthSwitchButton.setChecked(z);
        njh.b(str, z);
    }

    private void g(boolean z) {
        LogUtil.a("NotificationMessageSettingActivity", "updateNoticeBarSwitch", Boolean.valueOf(z));
        if (this.u) {
            this.u = false;
            LogUtil.a("NotificationMessageSettingActivity", "trigger by fallback");
            return;
        }
        if (CommonUtil.cj() && !CommonUtil.cc() && !Utils.l()) {
            LogUtil.a("NotificationMessageSettingActivity", "switchPushWithHms");
            c(z, new e(this, z, true));
        } else {
            d(z);
        }
        qif.e(z);
    }

    static class e implements OnCompleteListener {
        private boolean b;
        private WeakReference<NotificationMessageSettingActivity> c;
        private boolean e;

        e(NotificationMessageSettingActivity notificationMessageSettingActivity, boolean z, boolean z2) {
            this.c = new WeakReference<>(notificationMessageSettingActivity);
            this.e = z;
            this.b = z2;
        }

        @Override // com.huawei.hmf.tasks.OnCompleteListener
        public void onComplete(Task task) {
            NotificationMessageSettingActivity notificationMessageSettingActivity = this.c.get();
            if (notificationMessageSettingActivity == null || task == null) {
                LogUtil.h("NotificationMessageSettingActivity", "notificationMessageSettingActivity = null or task = null");
                return;
            }
            if (task.isSuccessful()) {
                LogUtil.a("NotificationMessageSettingActivity", "switchPushWithHms success");
                if (this.b) {
                    notificationMessageSettingActivity.d(this.e);
                    return;
                }
                return;
            }
            LogUtil.b("NotificationMessageSettingActivity", "switchPushWithHms fail:", task.getException().getMessage());
            if (this.b) {
                Toast.makeText(notificationMessageSettingActivity, com.huawei.ui.commonui.R$string.IDS_notification_set_fail_text, 0).show();
                notificationMessageSettingActivity.u = true;
                notificationMessageSettingActivity.d(!this.e);
                if (notificationMessageSettingActivity.s != null) {
                    Message obtainMessage = notificationMessageSettingActivity.s.obtainMessage();
                    obtainMessage.what = 102;
                    Bundle bundle = new Bundle();
                    bundle.putString("status", String.valueOf(true ^ this.e));
                    obtainMessage.setData(bundle);
                    notificationMessageSettingActivity.s.sendMessage(obtainMessage);
                    return;
                }
                LogUtil.h("NotificationMessageSettingActivity", "MyCompleteListener, mHandler is null");
            }
        }
    }

    private void k() {
        if (!CommonUtil.cj() || CommonUtil.cc()) {
            return;
        }
        LogUtil.a("NotificationMessageSettingActivity", "updatePushWithHmsIfDeeded");
        boolean isChecked = this.af.isChecked();
        c(isChecked, new e(this, isChecked, false));
    }

    private void c(boolean z, OnCompleteListener<Void> onCompleteListener) {
        if (z) {
            HmsMessaging.getInstance(BaseApplication.getContext()).turnOnPush().addOnCompleteListener(onCompleteListener);
        } else {
            HmsMessaging.getInstance(BaseApplication.getContext()).turnOffPush().addOnCompleteListener(onCompleteListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.a("NotificationMessageSettingActivity", "saveNoticeBarSwitch");
        String str = z ? "1" : "0";
        c(AnalyticsValue.HEALTH_MINE_SETTINGS_PHONE_NOTIFICATION_2040050.value(), str);
        SharedPreferenceManager.e(this.e, Integer.toString(10000), "health_msg_switch_noticebar", str, new StorageParams());
        KeyValDbManager.b(BaseApplication.getContext()).e("health_msg_switch_noticebar", str);
        LogUtil.a("NotificationMessageSettingActivity", "saveNoticeBarSwitch recommendStatus set finish");
    }

    private void c(boolean z) {
        if (this.b) {
            this.b = false;
            return;
        }
        d(AnalyticsValue.HEALTH_MINE_SETTINGS_REAL_PUSH_STEP_040018.value(), z);
        if (this.r != null) {
            LogUtil.a("NotificationMessageSettingActivity", "isChecked : ", Boolean.valueOf(z));
            if (!z) {
                if (CommonUtil.bh()) {
                    this.r.d(z, (IExecuteResult) null);
                    return;
                }
                if (LanguageUtil.j(this.e) && g()) {
                    n();
                }
                if (jpp.a()) {
                    i();
                    return;
                } else {
                    sendBroadcast(new Intent("com.huawei.health.action.unbindNotification"), LocalBroadcast.c);
                    this.r.d(z, (IExecuteResult) null);
                    return;
                }
            }
            this.r.d(z, (IExecuteResult) null);
        }
    }

    private boolean g() {
        return Build.VERSION.SDK_INT >= 28 && this.r.c() != 3;
    }

    private void n() {
        Context context = this.e;
        if (context != null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R$string.IDS_step_note_settings_dialog_title).e(context.getString(R$string.IDS_step_note_keep_alive)).cyU_(R$string.IDS_settings_close, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NotificationMessageSettingActivity.this.r.d(false, (IExecuteResult) null);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.15
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NotificationMessageSettingActivity.this.as.setChecked(true);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            a2.setCancelable(false);
            a2.show();
        }
    }

    private void i() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R$string.IDS_settings_restore_factory_settings_dialog_title).e(this.e.getString(R$string.IDS_wear_note_keep_alive)).cyU_(R$string.IDS_settings_close, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationMessageSettingActivity.this.sendBroadcast(new Intent("com.huawei.health.action.unbindNotification"), LocalBroadcast.c);
                if (NotificationMessageSettingActivity.this.r != null) {
                    NotificationMessageSettingActivity.this.r.d(false, (IExecuteResult) null);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationMessageSettingActivity.this.as.setChecked(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    private void e(boolean z) {
        if (!this.f10336a) {
            d(AnalyticsValue.HEALTH_MINE_STEP_COMPLETE_REMIND_2040019.value(), z);
            if (this.r != null) {
                LogUtil.a("NotificationMessageSettingActivity", "isChecked : " + z);
                this.r.e(z, (IExecuteResult) null);
            }
        } else {
            this.f10336a = false;
        }
        LogUtil.a("NotificationMessageSettingActivity", "doStepsTargetCompleteRemindSwitch isChecked ", Boolean.valueOf(z));
        b(z);
    }

    private void c(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    private void d(String str, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (z) {
            hashMap.put("type", "1");
        } else {
            hashMap.put("type", "2");
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    private void e() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_device_band_status);
        this.o = linearLayout;
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.notification_switch_title);
        this.l = healthTextView;
        healthTextView.setText(getString(R$string.IDS_notification_switch));
        this.n = (HealthSwitchButton) this.o.findViewById(R.id.notification_switch_btn);
        this.t = (HealthDivider) findViewById(R.id.divider1);
        if (!lcu.e() || !jpp.a()) {
            LogUtil.h("NotificationMessageSettingActivity", "app has not device");
            this.o.setVisibility(8);
            this.t.setVisibility(8);
        } else {
            this.o.setVisibility(0);
            this.t.setVisibility(0);
            jqi.a().getSwitchSetting("device_notification_key", new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    final boolean z = true;
                    if (i == 0 && (obj instanceof String)) {
                        z = true ^ "0".equals((String) obj);
                    }
                    NotificationMessageSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.2.5
                        @Override // java.lang.Runnable
                        public void run() {
                            NotificationMessageSettingActivity.this.n.setChecked(z);
                        }
                    });
                }
            });
            this.n.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NotificationMessageSettingActivity.this.n.setClickable(false);
                    boolean isChecked = NotificationMessageSettingActivity.this.n.isChecked();
                    if (!isChecked) {
                        NotificationMessageSettingActivity.this.o();
                    } else {
                        NotificationMessageSettingActivity.this.a(isChecked);
                        NotificationMessageSettingActivity.this.i(true);
                    }
                    NotificationMessageSettingActivity.this.n.setClickable(true);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R$string.IDS_settings_restore_factory_settings_dialog_title).e(this.e.getString(R$string.IDS_wear_keep_alive_tips)).cyU_(R$string.IDS_settings_close, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationMessageSettingActivity.this.i(false);
                NotificationMessageSettingActivity.this.a(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NotificationMessageSettingActivity.this.n.setChecked(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        jqi.a().setSwitchSetting("device_notification_key", z ? "1" : "0", new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.me.activity.NotificationMessageSettingActivity.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("NotificationMessageSettingActivity", "saveDeviceSwitchStatus: ", Integer.valueOf(i), "  ", (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(boolean z) {
        jfq.c().d("deleteDevice", new DeviceInfo(), 0, z ? "1" : "0");
    }

    static class a implements IBaseResponseCallback {
        private final WeakReference<NotificationMessageSettingActivity> b;

        private a(NotificationMessageSettingActivity notificationMessageSettingActivity) {
            this.b = new WeakReference<>(notificationMessageSettingActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[2];
            objArr[0] = "onResponse: ";
            objArr[1] = obj == null ? null : obj.toString();
            LogUtil.a("NotificationMessageSettingActivity", objArr);
            NotificationMessageSettingActivity notificationMessageSettingActivity = this.b.get();
            if (notificationMessageSettingActivity == null) {
                LogUtil.h("NotificationMessageSettingActivity", "onResponse: activity is null");
                return;
            }
            if (obj instanceof HashMap) {
                if (notificationMessageSettingActivity.s == null) {
                    LogUtil.h("NotificationMessageSettingActivity", "ActiveRingsSwitchStatusDataCallback, mHandler is null");
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 103;
                obtain.obj = (HashMap) obj;
                notificationMessageSettingActivity.s.sendMessage(obtain);
                return;
            }
            LogUtil.h("NotificationMessageSettingActivity", "onResponse: objData is not instanceof HashMap");
        }
    }

    static class c implements IBaseResponseCallback {
        private final WeakReference<NotificationMessageSettingActivity> e;

        private c(NotificationMessageSettingActivity notificationMessageSettingActivity) {
            LogUtil.a("NotificationMessageSettingActivity", "create RouteAutoSwitchStatusDataCallback");
            this.e = new WeakReference<>(notificationMessageSettingActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[2];
            objArr[0] = "RouteAutoSwitchStatusDataCallback onResponse: ";
            objArr[1] = obj == null ? null : obj.toString();
            LogUtil.a("NotificationMessageSettingActivity", objArr);
            NotificationMessageSettingActivity notificationMessageSettingActivity = this.e.get();
            if (notificationMessageSettingActivity == null) {
                LogUtil.h("NotificationMessageSettingActivity", "RouteAutoSwitchStatusDataCallback onResponse: activity is null");
                return;
            }
            if (obj instanceof enh) {
                if (notificationMessageSettingActivity.s != null) {
                    notificationMessageSettingActivity.ai = (enh) obj;
                    Message obtain = Message.obtain();
                    obtain.what = 104;
                    notificationMessageSettingActivity.s.sendMessage(obtain);
                    return;
                }
                LogUtil.h("NotificationMessageSettingActivity", "RouteAutoSwitchStatusDataCallback, mHandler is null");
                return;
            }
            LogUtil.h("NotificationMessageSettingActivity", "RouteAutoSwitchStatusDataCallback onResponse: objData is not instanceof PathAutoSwitchInfo");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
