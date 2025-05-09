package com.huawei.ui.main.stories.settings.activity.heartrate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.fitnessdatatype.StudentHeartRateZoneMgr;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.jho;
import defpackage.kox;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.rvd;
import defpackage.rvg;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes7.dex */
public class StudentHeartRateActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10456a;
    private HealthRadioButton ac;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ah;
    private HealthRadioButton aj;
    private int al;
    private View am;
    private HealthTextView an;
    private HealthTextView ao;
    private HealthTextView aq;
    private HealthTextView as;
    private StudentHeartRateZoneMgr av;
    private HealthSwitchButton ax;
    private HealthTextView az;
    private HealthTextView bb;
    private HealthTextView bc;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView i;
    private int j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private CustomTitleBar p;
    private Context t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthTextView y;
    private boolean s = true;
    private int q = 0;
    private int at = 60;
    private int au = 195;
    private int ak = 195;
    private int ai = 176;
    private float ag = 90.0f;
    private int h = 156;
    private float o = 80.0f;
    private int b = 137;
    private float e = 70.0f;
    private int ap = 117;
    private float ar = 60.0f;
    private int aw = 98;
    private float ba = 50.0f;
    private float z = 95.0f;
    private float ab = 88.0f;
    private float aa = 84.0f;
    private float w = 74.0f;
    private float x = 59.0f;
    private boolean ad = false;
    private Handler r = new d(this);

    static class d extends BaseHandler<StudentHeartRateActivity> {
        d(StudentHeartRateActivity studentHeartRateActivity) {
            super(studentHeartRateActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dRO_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(StudentHeartRateActivity studentHeartRateActivity, Message message) {
            int i = message.what;
            if (i == 7) {
                studentHeartRateActivity.g();
            } else if (i == 8) {
                studentHeartRateActivity.f();
                return;
            } else if (i == 4368) {
                StudentHeartRateActivity.dRN_(studentHeartRateActivity, message);
            }
            studentHeartRateActivity.d(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dRN_(StudentHeartRateActivity studentHeartRateActivity, Message message) {
        int i = message.arg1;
        studentHeartRateActivity.au = i;
        studentHeartRateActivity.av.setHeartRateConfig(studentHeartRateActivity.s, i, studentHeartRateActivity.q, studentHeartRateActivity.ak, studentHeartRateActivity.at);
        studentHeartRateActivity.av.setStudentWarningLimitStatus(true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        dRK_(bundle);
        this.t = getApplicationContext();
        i();
        b();
        LogUtil.c("StudentHeartRateActivity", "onCreate");
        l();
    }

    private void dRK_(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("currentActivityHashCode", "");
            if (TextUtils.isEmpty(string) || string.equals(toString())) {
                return;
            }
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.MainActivity");
            intent.setFlags(268468224);
            startActivity(intent);
            finish();
        }
    }

    private void b() {
        this.j = c();
        b(j() ? 8 : 0);
    }

    private void o() {
        c();
        LogUtil.c("StudentHeartRateActivity", "refreshUserInfo isBirthdayValid() ", Boolean.valueOf(j()));
        b(j() ? 8 : 0);
        int i = this.j;
        int i2 = this.al;
        if (i != i2) {
            this.j = i2;
            l();
        }
    }

    private void b(int i) {
        View view = this.am;
        if (view != null) {
            view.setVisibility(i);
        }
    }

    private boolean j() {
        return this.al != 0;
    }

    private int c() {
        int e = CommonUtil.e(KeyValDbManager.b(BaseApplication.getContext()).d("family_mode_student_age_key", new StorageParams(1)), 0);
        this.al = e;
        return e;
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (this.ad) {
            o();
            this.ad = false;
        }
    }

    private void l() {
        jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.StudentHeartRateActivity.5
            @Override // java.lang.Runnable
            public void run() {
                StudentHeartRateActivity.this.av = kox.e().a().c();
                StudentHeartRateActivity studentHeartRateActivity = StudentHeartRateActivity.this;
                studentHeartRateActivity.q = studentHeartRateActivity.av.getStudentHeartRateThresholdData().getClassifyMethod();
                StudentHeartRateActivity studentHeartRateActivity2 = StudentHeartRateActivity.this;
                studentHeartRateActivity2.at = studentHeartRateActivity2.av.getStudentHeartRateThresholdData().getRestHeartRate();
                StudentHeartRateActivity.this.r.sendEmptyMessage(8);
            }
        });
    }

    private void i() {
        e();
        this.af = (HealthTextView) nsy.cMc_(this, R.id.text_view_limit);
        this.an = (HealthTextView) nsy.cMc_(this, R.id.text_limit_unit);
        this.ah = (HealthTextView) nsy.cMc_(this, R.id.heart_zone_percent_01);
        this.l = (HealthTextView) nsy.cMc_(this, R.id.text_view_anaerobic);
        this.k = (HealthTextView) nsy.cMc_(this, R.id.text_anaerobic_unit);
        this.m = (HealthTextView) nsy.cMc_(this, R.id.heart_zone_percent_02);
        this.d = (HealthTextView) nsy.cMc_(this, R.id.text_view_aerobic);
        this.i = (HealthTextView) nsy.cMc_(this, R.id.text_aerobic_unit);
        this.f = (HealthTextView) nsy.cMc_(this, R.id.heart_zone_percent_03);
        this.as = (HealthTextView) nsy.cMc_(this, R.id.text_view_reduce_fat);
        this.ao = (HealthTextView) nsy.cMc_(this, R.id.text_reduce_fat_unit);
        this.aq = (HealthTextView) nsy.cMc_(this, R.id.heart_zone_percent_04);
        this.bb = (HealthTextView) nsy.cMc_(this, R.id.text_view_warm_up);
        this.az = (HealthTextView) nsy.cMc_(this, R.id.text_warm_up_unit);
        this.bc = (HealthTextView) nsy.cMc_(this, R.id.heart_zone_percent_05);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            ((ImageView) nsy.cMc_(this, R.id.upper_limit_enter_ic)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        q();
    }

    private void q() {
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            this.an.setText(b(this.ai, this.ak, 1));
            this.k.setText(b(this.h, this.ai - 1, 1));
            this.i.setText(b(this.b, this.h - 1, 1));
            this.ao.setText(b(this.ap, this.b - 1, 1));
            this.az.setText(b(this.aw, this.ap - 1, 1));
            this.af.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            this.l.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            this.d.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            this.as.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            this.bb.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            return;
        }
        this.af.setText(b(this.ai, this.ak, 1));
        this.l.setText(b(this.h, this.ai - 1, 1));
        this.d.setText(b(this.b, this.h - 1, 1));
        this.as.setText(b(this.ap, this.b - 1, 1));
        this.bb.setText(b(this.aw, this.ap - 1, 1));
        this.an.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.k.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.i.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.ao.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.az.setText(this.t.getString(R$string.IDS_main_watch_heart_rate_unit_string));
    }

    private void e() {
        setContentView(R.layout.activity_heart_rate_zone_setting_new);
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.heart_rate_title_bar);
        this.p = customTitleBar;
        customTitleBar.setVisibility(0);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) nsy.cMc_(this, R.id.max_heart_alarm_switch_button);
        this.ax = healthSwitchButton;
        healthSwitchButton.setVisibility(8);
        this.ax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.StudentHeartRateActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                StudentHeartRateActivity.this.s = z;
                StudentHeartRateActivity.this.av.setHeartRateConfig(StudentHeartRateActivity.this.s, StudentHeartRateActivity.this.au, StudentHeartRateActivity.this.q, StudentHeartRateActivity.this.ak, StudentHeartRateActivity.this.at);
                if (z) {
                    StudentHeartRateActivity.this.c(AnalyticsValue.HEALTH_HEART_RATE_INTERVAL_WARNING_2090001.value(), "1");
                } else {
                    StudentHeartRateActivity.this.c(AnalyticsValue.HEALTH_HEART_RATE_INTERVAL_WARNING_2090001.value(), "2");
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        findViewById(R.id.upper_limit_layout).setOnClickListener(this);
        this.u = (HealthTextView) nsy.cMc_(this, R.id.upper_limit_textview);
        this.y = (HealthTextView) nsy.cMc_(this, R.id.upper_limit_heart_rate_desc);
        this.aj = (HealthRadioButton) nsy.cMc_(this, R.id.max_heart_rate_radio_button);
        findViewById(R.id.max_heart_rate_layout).setOnClickListener(this);
        this.ac = (HealthRadioButton) nsy.cMc_(this, R.id.heart_rate_reserve_radio_button);
        findViewById(R.id.heart_rate_reserve_layout).setOnClickListener(this);
        ((RadioGroup) findViewById(R.id.layout_radio_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.StudentHeartRateActivity.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.max_heart_rate_radio_button) {
                    StudentHeartRateActivity.this.d(0);
                } else {
                    StudentHeartRateActivity.this.d(1);
                }
                ViewClickInstrumentation.clickOnRadioGroup(radioGroup, i);
            }
        });
        d();
    }

    private void d() {
        this.v = (HealthTextView) nsy.cMc_(this, R.id.heart_rate_describe);
        nsy.cMc_(this, R.id.advance_settings_tv).setOnClickListener(this);
        this.n = (HealthTextView) nsy.cMc_(this, R.id.zone_name1);
        this.g = (HealthTextView) nsy.cMc_(this, R.id.zone_name2);
        this.ae = (HealthTextView) nsy.cMc_(this, R.id.zone_name3);
        this.f10456a = (HealthTextView) nsy.cMc_(this, R.id.zone_name4);
        this.c = (HealthTextView) nsy.cMc_(this, R.id.zone_name5);
        this.am = findViewById(R.id.hw_health_rate_zone_tips);
        ((HealthTextView) findViewById(R.id.notice_message)).setText(this.t.getResources().getString(R$string.IDS_rate_zone_notice_message, Integer.valueOf(HeartRateThresholdConfig.HEART_RATE_LIMIT)));
        findViewById(R.id.rate_zone_set_age_text).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.StudentHeartRateActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StudentHeartRateActivity.this.ad = true;
                Intent intent = new Intent();
                intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.me.activity.StudentInfoActivity");
                StudentHeartRateActivity.this.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("currentActivityHashCode", toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        g();
        int oldMaxThreshold = this.av.getOldMaxThreshold();
        LogUtil.c("StudentHeartRateActivity", "old maxHeartRateValue ", Integer.valueOf(oldMaxThreshold));
        e(oldMaxThreshold);
        if (oldMaxThreshold == this.ak) {
            d(false);
            return;
        }
        if (h()) {
            m();
        } else {
            s();
        }
        t();
        d(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.s = this.av.getStudentHeartRateThresholdData().getWarningEnable();
        this.au = this.av.getStudentHeartRateThresholdData().getWarningLimit();
        this.ak = this.av.getStudentHeartRateThresholdData().getMaxThreshold();
        if (h()) {
            this.ai = this.av.getStudentHeartRateThresholdData().getAnaerobicThreshold();
            this.h = this.av.getStudentHeartRateThresholdData().getAerobicThreshold();
            this.b = this.av.getStudentHeartRateThresholdData().getFatBurnThreshold();
            this.ap = this.av.getStudentHeartRateThresholdData().getWarmUpThreshold();
            this.aw = this.av.getStudentHeartRateThresholdData().getFitnessThreshold();
        } else {
            this.ai = this.av.getStudentHeartRateThresholdData().getAnaerobicAdvanceThreshold();
            this.h = this.av.getStudentHeartRateThresholdData().getAnaerobicBaseThreshold();
            this.b = this.av.getStudentHeartRateThresholdData().getLacticAcidThreshold();
            this.ap = this.av.getStudentHeartRateThresholdData().getAerobicAdvanceThreshold();
            this.aw = this.av.getStudentHeartRateThresholdData().getAerobicBaseThreshold();
        }
        LogUtil.c("StudentHeartRateActivity", "initData from sp mHasMaxAlarm:", Boolean.valueOf(this.s), ", mUpLimit = ", Integer.valueOf(this.au), ",mMaxCount = ", Integer.valueOf(this.ak), ",mLimitCount = ", Integer.valueOf(this.ai), ", mAnaerobic = ", Integer.valueOf(this.h), ", mAerobic = ", Integer.valueOf(this.b), ",mReduceFat = ", Integer.valueOf(this.ap), ",mWarmUp = ", Integer.valueOf(this.aw));
    }

    private void c(int i) {
        if (!h()) {
            int i2 = i - this.at;
            if (i2 == 0) {
                LogUtil.a("StudentHeartRateActivity", "updateUiPercent reserve is zero");
                return;
            }
            float f = i2;
            this.z = ((this.ai - r0) * 100) / f;
            this.ab = ((this.h - r0) * 100) / f;
            this.aa = ((this.b - r0) * 100) / f;
            this.w = ((this.ap - r0) * 100) / f;
            float f2 = ((this.aw - r0) * 100) / f;
            this.x = f2;
            if (f2 < 30.0f) {
                this.x = 30.0f;
            }
        } else {
            if (i == 0) {
                LogUtil.a("StudentHeartRateActivity", "updateUiPercent maxHeartRateValue is zero");
                return;
            }
            float f3 = i;
            this.ag = (this.ai * 100) / f3;
            this.o = (this.h * 100) / f3;
            this.e = (this.b * 100) / f3;
            this.ar = (this.ap * 100) / f3;
            float f4 = (this.aw * 100) / f3;
            this.ba = f4;
            if (f4 < 30.0f) {
                this.ba = 30.0f;
            }
        }
        LogUtil.c("StudentHeartRateActivity", "updateUiPercent LimitPercent = ", Float.valueOf(this.ag), ", AnaerobicPercent = ", Float.valueOf(this.o), ", AerobicPercent = ", Float.valueOf(this.e), ", ReduceFatPercent = ", Float.valueOf(this.ar), ", WarmUpPercent = ", Float.valueOf(this.ba));
    }

    private void e(int i) {
        int anaerobicThreshold = this.av.getStudentHeartRateThresholdData().getAnaerobicThreshold();
        int aerobicThreshold = this.av.getStudentHeartRateThresholdData().getAerobicThreshold();
        if (i <= 0) {
            LogUtil.e("StudentHeartRateActivity", "Heart Rate is wrong");
            return;
        }
        float f = i;
        this.ag = (anaerobicThreshold * 100) / f;
        this.o = (aerobicThreshold * 100) / f;
        this.e = (this.av.getStudentHeartRateThresholdData().getFatBurnThreshold() * 100) / f;
        this.ar = (this.av.getStudentHeartRateThresholdData().getWarmUpThreshold() * 100) / f;
        float fitnessThreshold = (this.av.getStudentHeartRateThresholdData().getFitnessThreshold() * 100) / f;
        this.ba = fitnessThreshold;
        if (fitnessThreshold < 30.0f) {
            this.ba = 30.0f;
        }
        LogUtil.c("StudentHeartRateActivity", "setHeartRateZonePercent LimitPercent = ", Float.valueOf(this.ag), " AnaerobicPercent = ", Float.valueOf(this.o), "AerobicPercent = ", Float.valueOf(this.e), "ReduceFatPercent = ", Float.valueOf(this.ar), "WarmUpPercent = ", Float.valueOf(this.ba));
        int anaerobicAdvanceThreshold = this.av.getStudentHeartRateThresholdData().getAnaerobicAdvanceThreshold();
        int anaerobicBaseThreshold = this.av.getStudentHeartRateThresholdData().getAnaerobicBaseThreshold();
        int lacticAcidThreshold = this.av.getStudentHeartRateThresholdData().getLacticAcidThreshold();
        int aerobicAdvanceThreshold = this.av.getStudentHeartRateThresholdData().getAerobicAdvanceThreshold();
        int aerobicBaseThreshold = this.av.getStudentHeartRateThresholdData().getAerobicBaseThreshold();
        float f2 = i - this.at;
        float f3 = ((anaerobicAdvanceThreshold - r7) * 100) / f2;
        this.z = f3;
        this.ab = ((anaerobicBaseThreshold - r7) * 100) / f2;
        this.aa = ((lacticAcidThreshold - r7) * 100) / f2;
        this.w = ((aerobicAdvanceThreshold - r7) * 100) / f2;
        float f4 = ((aerobicBaseThreshold - r7) * 100) / f2;
        this.x = f4;
        if (f4 < 30.0f) {
            this.x = 30.0f;
        }
        LogUtil.c("StudentHeartRateActivity", "setHeartRateZonePercent hrrHeartRate LimitPercent =", Float.valueOf(f3), " AnaerobicPercent = ", Float.valueOf(this.ab), " AerobicPercent = ", Float.valueOf(this.aa), " ReduceFatPercent = ", Float.valueOf(this.w), " WarmUpPercent = ", Float.valueOf(this.x));
        d(i, true);
        k();
    }

    private void d(int i, boolean z) {
        if (z && i == this.ak) {
            return;
        }
        if (h()) {
            if (Math.round(this.ag) == 90.0f && Math.round(this.o) == 80.0f && Math.round(this.e) == 70.0f && Math.round(this.ar) == 60.0f && Math.round(this.ba) == 50.0f) {
                this.ag = 90.0f;
                this.o = 80.0f;
                this.e = 70.0f;
                this.ar = 60.0f;
                this.ba = 50.0f;
                ReleaseLogUtil.e("StudentHeartRateActivity", "reset maxPercent");
                return;
            }
            return;
        }
        if (Math.round(this.z) == 95.0f && Math.round(this.ab) == 88.0f && Math.round(this.aa) == 84.0f && Math.round(this.w) == 74.0f && Math.round(this.x) == 59.0f) {
            this.z = 95.0f;
            this.ab = 88.0f;
            this.aa = 84.0f;
            this.w = 74.0f;
            this.x = 59.0f;
            ReleaseLogUtil.e("StudentHeartRateActivity", "reset HrrPercent");
        }
    }

    private void k() {
        if (this.ag > 100.0f) {
            this.ag = 90.0f;
            this.o = 80.0f;
            this.e = 70.0f;
            this.ar = 60.0f;
            this.ba = 50.0f;
            LogUtil.c("StudentHeartRateActivity", "max percent exception");
        }
        int round = Math.round(((this.ak - this.at) * this.z) / 100.0f) + this.at;
        this.ai = round;
        if (round >= this.ak) {
            this.z = 95.0f;
            this.ab = 88.0f;
            this.aa = 84.0f;
            this.w = 74.0f;
            this.x = 59.0f;
            LogUtil.c("StudentHeartRateActivity", "hrr percent exception");
        }
    }

    private void t() {
        int round = Math.round((this.ak * this.ag) / 100.0f);
        int round2 = Math.round((this.ak * this.o) / 100.0f);
        int round3 = Math.round((this.ak * this.e) / 100.0f);
        int round4 = Math.round((this.ak * this.ar) / 100.0f);
        int round5 = Math.round((this.ak * this.ba) / 100.0f);
        this.av.setHeartRateConfig(this.s, this.au, this.q, this.ak, this.at);
        this.av.setMaxHeartRateThreshold(round, round2, round3, round4, round5);
        LogUtil.c("StudentHeartRateActivity", "updateHeartZoneDataConfig mHasMaxAlarm = ", Boolean.valueOf(this.s), ", mUpLimit", Integer.valueOf(this.au), ", mClassifyMethod = ", Integer.valueOf(this.q), ", mMaxCount = ", Integer.valueOf(this.ak), ", mRestHeartRateCount = ", Integer.valueOf(this.at));
        LogUtil.c("StudentHeartRateActivity", "updateHeartZoneDataConfig MaxHeartRateThreshold = ", Integer.valueOf(round), " ", Integer.valueOf(round2), " ", Integer.valueOf(round3), " ", Integer.valueOf(round4), " ", Integer.valueOf(round5));
        float f = this.ak - this.at;
        int round6 = Math.round((this.z * f) / 100.0f) + this.at;
        int round7 = Math.round((this.ab * f) / 100.0f) + this.at;
        int round8 = Math.round((this.aa * f) / 100.0f) + this.at;
        int round9 = Math.round((this.w * f) / 100.0f) + this.at;
        int round10 = Math.round((f * this.x) / 100.0f) + this.at;
        this.av.setHrrHeartRateZoneThreshold(round6, round7, round8, round9, round10);
        LogUtil.c("StudentHeartRateActivity", "updateHeartZoneDataConfig HrrHeartRateZoneThreshold = ", Integer.valueOf(round6), " ", Integer.valueOf(round7), " ", Integer.valueOf(round8), " ", Integer.valueOf(round9), " ", Integer.valueOf(round10));
    }

    private void m() {
        this.ai = Math.round((this.ak * this.ag) / 100.0f);
        this.h = Math.round((this.ak * this.o) / 100.0f);
        this.b = Math.round((this.ak * this.e) / 100.0f);
        this.ap = Math.round((this.ak * this.ar) / 100.0f);
        this.aw = Math.round((this.ak * this.ba) / 100.0f);
        LogUtil.c("StudentHeartRateActivity", "processAllValue LimitPercent = ", Float.valueOf(this.ag), ", mLimitCount = ", Integer.valueOf(this.ai), ", AnaerobicPercent = ", Float.valueOf(this.o), "， mAnaerobic = ", Integer.valueOf(this.h), ", AerobicPercent = ", Float.valueOf(this.e), ", ReduceFatPercent = ", Float.valueOf(this.ar), ", mMaxCount = ", Integer.valueOf(this.ak), ", mReduceFat = ", Integer.valueOf(this.ap), ", WarmUpPercent = ", Float.valueOf(this.ba), ", mWarmUp = ", Integer.valueOf(this.aw));
    }

    private void s() {
        int i = this.ak - this.at;
        float f = i;
        this.ai = Math.round((this.z * f) / 100.0f) + this.at;
        this.h = Math.round((this.ab * f) / 100.0f) + this.at;
        this.b = Math.round((this.aa * f) / 100.0f) + this.at;
        this.ap = Math.round((this.w * f) / 100.0f) + this.at;
        this.aw = Math.round((f * this.x) / 100.0f) + this.at;
        LogUtil.c("StudentHeartRateActivity", "updateRestHeartRate mHrrAnaerobicPercent = ", Float.valueOf(this.z), ", mLimitCount = ", Integer.valueOf(this.ai), ", mHrrAnaerobicBasePercent = ", Float.valueOf(this.ab), ", hrr = ", Integer.valueOf(i), ", mAnaerobic = ", Integer.valueOf(this.h), ", mHrrLacticAcidPercent = ", Float.valueOf(this.aa), ", mAerobic = ", Integer.valueOf(this.b), ", mHrrAerobicPercent = ", Float.valueOf(this.w), ", mReduceFat = ", Integer.valueOf(this.ap), ", mHrrAerobicBasePercent = ", Float.valueOf(this.x), ", mWarmUp = ", Integer.valueOf(this.aw));
    }

    private boolean h() {
        return this.q == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        String format;
        this.ax.setChecked(this.s);
        this.ax.setVisibility(0);
        this.u.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903303_res_0x7f030107, 2, Integer.valueOf(this.au)));
        this.y.setText(String.format(Locale.ENGLISH, getString(R$string.IDS_hwh_motiontrack_sport_heart_rate_limit_warning), Integer.valueOf(this.au)));
        String string = getString(R$string.IDS_unusual_stopped_message_more_new);
        if (h()) {
            this.aj.setChecked(true);
            this.ac.setChecked(false);
            format = this.t.getResources().getString(R$string.IDS_max_heart_rate_description, Integer.valueOf(HeartRateThresholdConfig.HEART_RATE_LIMIT), string);
            this.n.setText(R$string.IDS_rate_zone_maximum_threshold);
            this.g.setText(R$string.IDS_rate_zone_anaerobic_threshold);
            this.ae.setText(R$string.IDS_rate_zone_aerobic_threshold);
            this.f10456a.setText(R$string.IDS_rate_zone_fatburn_threshold_string);
            this.c.setText(R$string.IDS_rate_zone_warmup_threshold);
        } else {
            this.aj.setChecked(false);
            this.ac.setChecked(true);
            format = String.format(Locale.ENGLISH, getString(R$string.IDS_hrr_heart_rate_description), string);
            this.n.setText(R$string.IDS_rate_zone_hrr_anaerobicAdvance_threshold);
            this.g.setText(R$string.IDS_rate_zone_hrr_anaerobicBase_threshold);
            this.ae.setText(R$string.IDS_rate_zone_hrr_lacticAcid_threshold);
            this.f10456a.setText(R$string.IDS_rate_zone_hrr_aerobicAdvance_threshold);
            this.c.setText(R$string.IDS_rate_zone_hrr_aerobicBase_threshold);
        }
        SpannableString spannableString = new SpannableString(format);
        int length = format.length();
        spannableString.setSpan(new rvd(h()), length - string.length(), length, 17);
        this.v.setMovementMethod(LinkMovementMethod.getInstance());
        this.v.setText(spannableString);
        a(z);
    }

    private void a(boolean z) {
        int i;
        int i2;
        int i3;
        q();
        if (!z) {
            c(this.ak);
        }
        if (h()) {
            this.ah.setText(b(Math.round(this.ag), 100, 2));
            this.m.setText(b(Math.round(this.o), Math.round(this.ag), 2));
            this.f.setText(b(Math.round(this.e), Math.round(this.o), 2));
            this.aq.setText(b(Math.round(this.ar), Math.round(this.e), 2));
            this.bc.setText(b(Math.round(this.ba), Math.round(this.ar), 2));
        } else {
            this.ah.setText(b(Math.round(this.z), 100, 2));
            this.m.setText(b(Math.round(this.ab), Math.round(this.z), 2));
            this.f.setText(b(Math.round(this.aa), Math.round(this.ab), 2));
            this.aq.setText(b(Math.round(this.w), Math.round(this.aa), 2));
            this.bc.setText(b(Math.round(this.x), Math.round(this.w), 2));
        }
        int i4 = this.ai;
        if (i4 > this.ak || (i = this.h) > i4 || (i2 = this.b) > i || (i3 = this.ap) > i2 || this.aw > i3) {
            LogUtil.c("StudentHeartRateActivity", "updateUiZone threshold wrong");
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
            linkedHashMap.put("actiontype", Integer.toString(2));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEART_RATE_ZONE_80040005.value(), linkedHashMap);
        }
        LogUtil.c("StudentHeartRateActivity", "setHeartRateZonePercent LimitPercent = ", Float.valueOf(this.ag), " AnaerobicPercent = ", Float.valueOf(this.o), " AerobicPercent = ", Float.valueOf(this.e), " ReduceFatPercent = ", Float.valueOf(this.ar), " WarmUpPercent = ", Float.valueOf(this.ba), "setHeartRateZone Limit = ", Integer.valueOf(this.ai), " Anaerobic = ", Integer.valueOf(this.h), " Aerobic = ", Integer.valueOf(this.b), " ReduceFat = ", Integer.valueOf(this.ap), " WarmUp = ", Integer.valueOf(this.aw));
    }

    private String b(int i, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer(UnitUtil.e(i, i3, 0));
        stringBuffer.append("～");
        stringBuffer.append(UnitUtil.e(i2, i3, 0));
        return stringBuffer.toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(600)) {
            LogUtil.e("StudentHeartRateActivity", "CLICK IS FAST");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (R.id.upper_limit_layout == view.getId()) {
            n();
        }
        if (R.id.heart_rate_reserve_layout == view.getId()) {
            d(1);
        } else if (R.id.max_heart_rate_layout == view.getId()) {
            d(0);
        } else if (R.id.advance_settings_tv == view.getId()) {
            a();
        } else {
            LogUtil.c("StudentHeartRateActivity", "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void n() {
        a(100, HeartRateThresholdConfig.HEART_RATE_LIMIT, 4368, this.au, this.t.getString(R$string.IDS_rate_max_limit));
        c(AnalyticsValue.HEALTH_HEART_RATE_LIMIT_2090002.value(), (String) null);
    }

    private void a(int i, int i2, int i3, int i4, String str) {
        rvg.a aVar;
        rvg c;
        if (i <= i2 && (c = (aVar = new rvg.a(this, this.r, i3)).c(a(i, i2), Integer.valueOf(i4))) != null) {
            aVar.a(str);
            c.show();
        }
    }

    private int[] a(int i, int i2) {
        if (i > i2) {
            return null;
        }
        int[] iArr = new int[(i2 - i) + 1];
        for (int i3 = i; i3 <= i2; i3++) {
            iArr[i3 - i] = i3;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        this.q = i;
        this.av.setHeartRateConfig(this.s, this.au, i, this.ak, this.at);
        this.r.removeMessages(7);
        this.r.sendEmptyMessage(7);
    }

    private void a() {
        LogUtil.c("StudentHeartRateActivity", "goToHeartRateSettingActivity");
        Intent intent = new Intent(this, (Class<?>) HeartRateSectionActivity.class);
        intent.putExtra("maxHeartValue", this.ak);
        intent.putExtra("heartRateZone1", this.ai);
        intent.putExtra("heartRateZone2", this.h);
        intent.putExtra("heartRateZone3", this.b);
        intent.putExtra("heartRateZone4", this.ap);
        intent.putExtra("heartRateZone5", this.aw);
        if (h()) {
            intent.putExtra("intent_extra_heart_rate_type", 0);
            intent.putExtra("heartRatePercent1", this.ag);
            intent.putExtra("heartRatePercent2", this.o);
            intent.putExtra("heartRatePercent3", this.e);
            intent.putExtra("heartRatePercent4", this.ar);
            intent.putExtra("heartRatePercent5", this.ba);
        } else {
            intent.putExtra("intent_extra_heart_rate_type", 1);
            intent.putExtra("heartRatePercent1", this.z);
            intent.putExtra("heartRatePercent2", this.ab);
            intent.putExtra("heartRatePercent3", this.aa);
            intent.putExtra("heartRatePercent4", this.w);
            intent.putExtra("heartRatePercent5", this.x);
        }
        intent.putExtra("intent_extra_hrr_heart_rate", this.at);
        intent.putExtra("isStudentMode", true);
        startActivityForResult(intent, 110);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            LogUtil.e("StudentHeartRateActivity", "onActivityResult data is null");
            return;
        }
        LogUtil.c("StudentHeartRateActivity", "onActivityResult requestCode = ", Integer.valueOf(i));
        if (i2 == 3 && i == 110) {
            dRL_(intent, 5, "maxHeartValue");
            dRL_(intent, 4, "heartRateZone1");
            dRL_(intent, 3, "heartRateZone2");
            dRL_(intent, 2, "heartRateZone3");
            dRL_(intent, 1, "heartRateZone4");
            dRL_(intent, 0, "heartRateZone5");
            boolean booleanExtra = intent.getBooleanExtra("intent_extra_max_heart_rate_change", false);
            if (h()) {
                if (booleanExtra) {
                    t();
                }
                this.av.setHeartRateConfig(this.s, this.au, this.q, this.ak, this.at);
                this.av.setMaxHeartRateThreshold(this.ai, this.h, this.b, this.ap, this.aw);
            } else {
                dRL_(intent, 6, "intent_extra_hrr_heart_rate");
                if (booleanExtra) {
                    t();
                }
                this.av.setHeartRateConfig(this.s, this.au, this.q, this.ak, this.at);
                this.av.setHrrHeartRateZoneThreshold(this.ai, this.h, this.b, this.ap, this.aw);
            }
            dRM_(intent);
            d(true);
        }
    }

    private void dRM_(Intent intent) {
        if (h()) {
            this.ag = intent.getFloatExtra("heartRatePercent1", this.ag);
            this.o = intent.getFloatExtra("heartRatePercent2", this.o);
            this.e = intent.getFloatExtra("heartRatePercent3", this.e);
            this.ar = intent.getFloatExtra("heartRatePercent4", this.ar);
            this.ba = intent.getFloatExtra("heartRatePercent5", this.ba);
            return;
        }
        this.z = intent.getFloatExtra("heartRatePercent1", this.z);
        this.ab = intent.getFloatExtra("heartRatePercent2", this.ab);
        this.aa = intent.getFloatExtra("heartRatePercent3", this.aa);
        this.w = intent.getFloatExtra("heartRatePercent4", this.w);
        this.x = intent.getFloatExtra("heartRatePercent5", this.x);
    }

    private void dRL_(Intent intent, int i, String str) {
        try {
            b(i, intent.getIntExtra(str, -1));
        } catch (NumberFormatException unused) {
            LogUtil.e("StudentHeartRateActivity", "dealWithOnResultData NumberFormatException");
        }
    }

    private void b(int i, int i2) {
        LogUtil.c("StudentHeartRateActivity", "setRangValue index= ", Integer.valueOf(i), "  value= ", Integer.valueOf(i2));
        switch (i) {
            case 0:
                this.aw = i2;
                break;
            case 1:
                this.ap = i2;
                break;
            case 2:
                this.b = i2;
                break;
            case 3:
                this.h = i2;
                break;
            case 4:
                this.ai = i2;
                break;
            case 5:
                this.ak = i2;
                break;
            case 6:
                this.at = i2;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.t, str, hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("StudentHeartRateActivity", "onStop");
        this.av.getStudentHeartRateThresholdData().setOldMaxThreshold(this.ak);
        this.av.setStudentHeartDataToSp();
        jho.c((HeartZoneConf) null, kox.e().a().c(), true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
