package com.huawei.ui.main.stories.settings.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.kox;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.rvd;
import defpackage.rvg;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes7.dex */
public class BaseHeartRateZoneFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10458a;
    private HealthTextView ae;
    private HealthTextView af;
    private IBaseResponseCallback ah;
    private HealthRadioButton aj;
    private HealthTextView ak;
    private HealthTextView am;
    private HealthTextView ao;
    private HealthTextView ap;
    private HealthTextView ar;
    private String at;
    private View av;
    private HealthSwitchButton aw;
    private int ax;
    private HealthTextView ay;
    private HealthTextView az;
    private HealthTextView ba;
    private HealthTextView c;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView j;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private Context p;
    private HealthTextView q;
    private HealthDivider s;
    private HealthTextView v;
    private HeartRateThresholdConfig w;
    private HealthTextView x;
    private HealthTextView y;
    private HealthRadioButton z;
    private int as = 60;
    private boolean r = true;
    private int au = 195;
    private int al = 195;
    private int ag = 176;
    private float ai = 90.0f;
    private int i = 156;
    private float h = 80.0f;
    private int d = 137;
    private float b = 70.0f;
    private int an = 117;
    private float aq = 60.0f;
    private int bb = 98;
    private float bc = 50.0f;
    private float ad = 95.0f;
    private float ab = 88.0f;
    private float ac = 84.0f;
    private float aa = 74.0f;
    private float u = 59.0f;
    private int k = 0;
    private Handler t = new d(this);

    public BaseHeartRateZoneFragment() {
    }

    public BaseHeartRateZoneFragment(int i, IBaseResponseCallback iBaseResponseCallback) {
        this.ax = i;
        this.at = "BaseHeartRateZoneFragment" + i;
        this.ah = iBaseResponseCallback;
    }

    static class d extends BaseHandler<BaseHeartRateZoneFragment> {
        d(BaseHeartRateZoneFragment baseHeartRateZoneFragment) {
            super(baseHeartRateZoneFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dRT_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BaseHeartRateZoneFragment baseHeartRateZoneFragment, Message message) {
            int i = message.what;
            if (i == 7) {
                baseHeartRateZoneFragment.e();
            } else if (i == 8) {
                baseHeartRateZoneFragment.b();
                return;
            } else if (i == 4368) {
                BaseHeartRateZoneFragment.dRS_(baseHeartRateZoneFragment, message);
            }
            baseHeartRateZoneFragment.b(false);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(600)) {
            LogUtil.e(this.at, "CLICK IS FAST");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (R.id.upper_limit_layout == view.getId()) {
            n();
        }
        if (R.id.heart_rate_reserve_layout == view.getId()) {
            a(this.ax, 1);
        } else if (R.id.max_heart_rate_layout == view.getId()) {
            a(this.ax, 0);
        } else if (R.id.advance_settings_tv == view.getId()) {
            c();
        } else {
            LogUtil.c(this.at, "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.av == null) {
            LogUtil.c("BaseHeartRateZoneFragment", "onCreateView");
            if (nsn.r()) {
                this.av = layoutInflater.inflate(R.layout.activity_heart_rate_zone_setting_fitolder, viewGroup, false);
            } else {
                this.av = layoutInflater.inflate(R.layout.activity_heart_rate_zone_setting_new, viewGroup, false);
            }
            this.p = getActivity();
            l();
            a();
            h();
        }
        return this.av;
    }

    public void a() {
        this.w = kox.e().d().getPostureType(this.ax);
        d(this.ax);
        this.aw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.settings.fragment.BaseHeartRateZoneFragment.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BaseHeartRateZoneFragment.this.r = z;
                if (BaseHeartRateZoneFragment.this.d()) {
                    kox.e().b(false, BaseHeartRateZoneFragment.this.ax, BaseHeartRateZoneFragment.this.r, BaseHeartRateZoneFragment.this.au, BaseHeartRateZoneFragment.this.al, BaseHeartRateZoneFragment.this.ag, BaseHeartRateZoneFragment.this.i, BaseHeartRateZoneFragment.this.d, BaseHeartRateZoneFragment.this.an, BaseHeartRateZoneFragment.this.bb);
                } else {
                    kox.e().d(false, BaseHeartRateZoneFragment.this.ax, BaseHeartRateZoneFragment.this.r, BaseHeartRateZoneFragment.this.au, BaseHeartRateZoneFragment.this.al, BaseHeartRateZoneFragment.this.ag, BaseHeartRateZoneFragment.this.i, BaseHeartRateZoneFragment.this.d, BaseHeartRateZoneFragment.this.an, BaseHeartRateZoneFragment.this.bb);
                }
                if (z) {
                    BaseHeartRateZoneFragment.this.b(AnalyticsValue.HEALTH_HEART_RATE_INTERVAL_WARNING_2090001.value(), "1");
                } else {
                    BaseHeartRateZoneFragment.this.b(AnalyticsValue.HEALTH_HEART_RATE_INTERVAL_WARNING_2090001.value(), "2");
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    public void h() {
        jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.settings.fragment.BaseHeartRateZoneFragment.4
            @Override // java.lang.Runnable
            public void run() {
                HeartRateZoneMgr d2 = kox.e().d();
                BaseHeartRateZoneFragment baseHeartRateZoneFragment = BaseHeartRateZoneFragment.this;
                baseHeartRateZoneFragment.k = d2.getPostureType(baseHeartRateZoneFragment.ax).getClassifyMethod();
                BaseHeartRateZoneFragment baseHeartRateZoneFragment2 = BaseHeartRateZoneFragment.this;
                baseHeartRateZoneFragment2.as = d2.getPostureType(baseHeartRateZoneFragment2.ax).getRestHeartRate();
                LogUtil.c(BaseHeartRateZoneFragment.this.at, "mClassifyMethod = ", Integer.valueOf(BaseHeartRateZoneFragment.this.k), " mRestHeartRateCount = ", Integer.valueOf(BaseHeartRateZoneFragment.this.as));
                BaseHeartRateZoneFragment.this.t.sendEmptyMessage(8);
            }
        });
    }

    public void b() {
        e();
        int a2 = kox.e().a(this.ax);
        LogUtil.c(this.at, "old maxHeartRateValue ", Integer.valueOf(a2));
        a(a2);
        b(a2);
        if (d()) {
            f();
        } else {
            j();
        }
        i();
        b(false);
    }

    public void e() {
        if (d()) {
            this.r = this.w.getWarningEnable();
            this.au = this.w.getWarningLimit();
            this.al = this.w.getMaxThreshold();
            this.ag = this.w.getAnaerobicThreshold();
            this.i = this.w.getAerobicThreshold();
            this.d = this.w.getFatBurnThreshold();
            this.an = this.w.getWarmUpThreshold();
            this.bb = this.w.getFitnessThreshold();
        } else {
            this.r = this.w.getWarningEnable();
            this.au = this.w.getWarningLimit();
            this.al = this.w.getMaxThreshold();
            this.ag = this.w.getAnaerobicAdvanceThreshold();
            this.i = this.w.getAnaerobicBaseThreshold();
            this.d = this.w.getLacticAcidThreshold();
            this.an = this.w.getAerobicAdvanceThreshold();
            this.bb = this.w.getAerobicBaseThreshold();
        }
        LogUtil.c(this.at, "initData mHasMaxAlarm:", Boolean.valueOf(this.r), ", mUpLimit = ", Integer.valueOf(this.au), ",mMaxCount = ", Integer.valueOf(this.al), ",mLimitCount = ", Integer.valueOf(this.ag), ", mAnaerobic= ", Integer.valueOf(this.i), ", mAerobic= ", Integer.valueOf(this.d), ",mReduceFat= ", Integer.valueOf(this.an), ",mWarmUp = ", Integer.valueOf(this.bb));
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.ah.d(0, true);
        if (intent == null) {
            LogUtil.e(this.at, "onActivityResult data is null");
            return;
        }
        LogUtil.c(this.at, "onActivityResult requestCode = ", Integer.valueOf(i));
        if (i2 == 3 && i == 110) {
            dRQ_(intent, 5, "maxHeartValue");
            dRQ_(intent, 4, "heartRateZone1");
            dRQ_(intent, 3, "heartRateZone2");
            dRQ_(intent, 2, "heartRateZone3");
            dRQ_(intent, 1, "heartRateZone4");
            dRQ_(intent, 0, "heartRateZone5");
            boolean booleanExtra = intent.getBooleanExtra("intent_extra_other_heart_rate_change", false);
            boolean booleanExtra2 = intent.getBooleanExtra("intent_extra_max_heart_rate_change", false);
            if (d()) {
                if (booleanExtra2) {
                    i();
                }
                if (booleanExtra || !booleanExtra2) {
                    kox.e().b(false, this.ax, this.r, this.au, this.al, this.ag, this.i, this.d, this.an, this.bb);
                }
            } else {
                dRQ_(intent, 6, "intent_extra_hrr_heart_rate");
                kox.e().a(false, this.ax, this.as);
                if (booleanExtra2) {
                    i();
                }
                if (booleanExtra || !booleanExtra2) {
                    kox.e().d(false, this.ax, this.r, this.au, this.al, this.ag, this.i, this.d, this.an, this.bb);
                }
            }
            dRR_(intent);
            b(true);
        }
    }

    private void dRR_(Intent intent) {
        if (d()) {
            this.ai = intent.getFloatExtra("heartRatePercent1", this.ai);
            this.h = intent.getFloatExtra("heartRatePercent2", this.h);
            this.b = intent.getFloatExtra("heartRatePercent3", this.b);
            this.aq = intent.getFloatExtra("heartRatePercent4", this.aq);
            this.bc = intent.getFloatExtra("heartRatePercent5", this.bc);
            return;
        }
        this.ad = intent.getFloatExtra("heartRatePercent1", this.ad);
        this.ab = intent.getFloatExtra("heartRatePercent2", this.ab);
        this.ac = intent.getFloatExtra("heartRatePercent3", this.ac);
        this.aa = intent.getFloatExtra("heartRatePercent4", this.aa);
        this.u = intent.getFloatExtra("heartRatePercent5", this.u);
    }

    private void dRQ_(Intent intent, int i, String str) {
        try {
            e(i, intent.getIntExtra(str, -1));
        } catch (NumberFormatException unused) {
            LogUtil.e(this.at, "dealWithOnResultData NumberFormatException");
        }
    }

    private void e(int i, int i2) {
        LogUtil.c(this.at, "setRangValue index= ", Integer.valueOf(i), "  value= ", Integer.valueOf(i2));
        switch (i) {
            case 0:
                this.bb = i2;
                break;
            case 1:
                this.an = i2;
                break;
            case 2:
                this.d = i2;
                break;
            case 3:
                this.i = i2;
                break;
            case 4:
                this.ag = i2;
                break;
            case 5:
                this.al = i2;
                break;
            case 6:
                this.as = i2;
                break;
        }
    }

    public void a(int i) {
        if (!d()) {
            int i2 = i - this.as;
            if (i2 == 0) {
                LogUtil.a(this.at, "updateUiPercent reserve is zero");
                return;
            }
            float f = i2;
            this.ad = ((this.ag - r0) * 100) / f;
            this.ab = ((this.i - r0) * 100) / f;
            this.ac = ((this.d - r0) * 100) / f;
            this.aa = ((this.an - r0) * 100) / f;
            float f2 = ((this.bb - r0) * 100) / f;
            this.u = f2;
            if (f2 < 30.0f) {
                this.u = 30.0f;
            }
        } else {
            if (i == 0) {
                LogUtil.a(this.at, "updateUiPercent maxHeartRateValue is zero");
                return;
            }
            float f3 = i;
            this.ai = (this.ag * 100) / f3;
            this.h = (this.i * 100) / f3;
            this.b = (this.d * 100) / f3;
            this.aq = (this.an * 100) / f3;
            float f4 = (this.bb * 100) / f3;
            this.bc = f4;
            if (f4 < 30.0f) {
                this.bc = 30.0f;
            }
        }
        LogUtil.c(this.at, "updateUiPercent LimitPercent = ", Float.valueOf(this.ai), ", AnaerobicPercent = ", Float.valueOf(this.h), ", AerobicPercent = ", Float.valueOf(this.b), ", ReduceFatPercent = ", Float.valueOf(this.aq), ", WarmUpPercent = ", Float.valueOf(this.bc));
    }

    public void i() {
        int round = Math.round((this.al * this.ai) / 100.0f);
        int round2 = Math.round((this.al * this.h) / 100.0f);
        int round3 = Math.round((this.al * this.b) / 100.0f);
        int round4 = Math.round((this.al * this.aq) / 100.0f);
        int round5 = Math.round((this.al * this.bc) / 100.0f);
        kox.e().b(false, this.ax, this.r, this.au, this.al, round, round2, round3, round4, round5);
        LogUtil.c(this.at, "updateHeartZoneDataConfig mHasMaxAlarm = ", Boolean.valueOf(this.r), ", mUpLimit", Integer.valueOf(this.au), ",mMaxCount = ", Integer.valueOf(this.al), ", limit = ", Integer.valueOf(round), ",mAnaerobic = ", Integer.valueOf(round2), ",mAerobic = ", Integer.valueOf(round3), ",mReduceFat = ", Integer.valueOf(round4), ", warmUp = ", Integer.valueOf(round5));
        float f = this.al - this.as;
        int round6 = Math.round((this.ad * f) / 100.0f) + this.as;
        int round7 = Math.round((this.ab * f) / 100.0f) + this.as;
        int round8 = Math.round((this.ac * f) / 100.0f) + this.as;
        int round9 = Math.round((this.aa * f) / 100.0f) + this.as;
        int round10 = Math.round((f * this.u) / 100.0f) + this.as;
        kox.e().d(false, this.ax, this.r, this.au, this.al, round6, round7, round8, round9, round10);
        LogUtil.c(this.at, "updateHeartZoneDataConfig mHasMaxAlarm = ", Boolean.valueOf(this.r), ", mUpLimit", Integer.valueOf(this.au), ", mMaxCount = ", Integer.valueOf(this.al), ", limit = ", Integer.valueOf(round6), ", mAnaerobic = ", Integer.valueOf(round7), ", mAerobic = ", Integer.valueOf(round8), ",mReduceFat = ", Integer.valueOf(round9), ", warmUp = ", Integer.valueOf(round10));
    }

    private void l() {
        this.q = (HealthTextView) nsy.cMd_(this.av, R.id.heart_gesture_introduction);
        this.s = (HealthDivider) nsy.cMd_(this.av, R.id.heart_rate_introduction_divider);
        this.q.setVisibility(0);
        this.s.setVisibility(0);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) nsy.cMd_(this.av, R.id.max_heart_alarm_switch_button);
        this.aw = healthSwitchButton;
        healthSwitchButton.setVisibility(8);
        nsy.cMd_(this.av, R.id.upper_limit_layout).setOnClickListener(this);
        this.v = (HealthTextView) nsy.cMd_(this.av, R.id.upper_limit_textview);
        this.y = (HealthTextView) nsy.cMd_(this.av, R.id.upper_limit_heart_rate_desc);
        this.aj = (HealthRadioButton) nsy.cMd_(this.av, R.id.max_heart_rate_radio_button);
        this.z = (HealthRadioButton) nsy.cMd_(this.av, R.id.heart_rate_reserve_radio_button);
        nsy.cMd_(this.av, R.id.heart_rate_reserve_layout).setOnClickListener(this);
        ((RadioGroup) nsy.cMd_(this.av, R.id.layout_radio_group)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.settings.fragment.BaseHeartRateZoneFragment.5
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.max_heart_rate_radio_button) {
                    BaseHeartRateZoneFragment baseHeartRateZoneFragment = BaseHeartRateZoneFragment.this;
                    baseHeartRateZoneFragment.a(baseHeartRateZoneFragment.ax, 0);
                } else {
                    BaseHeartRateZoneFragment baseHeartRateZoneFragment2 = BaseHeartRateZoneFragment.this;
                    baseHeartRateZoneFragment2.a(baseHeartRateZoneFragment2.ax, 1);
                }
                ViewClickInstrumentation.clickOnRadioGroup(radioGroup, i);
            }
        });
        m();
    }

    private void m() {
        this.x = (HealthTextView) nsy.cMd_(this.av, R.id.heart_rate_describe);
        nsy.cMd_(this.av, R.id.advance_settings_tv).setOnClickListener(this);
        this.m = (HealthTextView) nsy.cMd_(this.av, R.id.zone_name1);
        this.g = (HealthTextView) nsy.cMd_(this.av, R.id.zone_name2);
        this.ae = (HealthTextView) nsy.cMd_(this.av, R.id.zone_name3);
        this.f10458a = (HealthTextView) nsy.cMd_(this.av, R.id.zone_name4);
        this.e = (HealthTextView) nsy.cMd_(this.av, R.id.zone_name5);
        this.af = (HealthTextView) nsy.cMd_(this.av, R.id.text_view_limit);
        this.am = (HealthTextView) nsy.cMd_(this.av, R.id.text_limit_unit);
        this.ak = (HealthTextView) nsy.cMd_(this.av, R.id.heart_zone_percent_01);
        this.n = (HealthTextView) nsy.cMd_(this.av, R.id.text_view_anaerobic);
        this.o = (HealthTextView) nsy.cMd_(this.av, R.id.text_anaerobic_unit);
        this.l = (HealthTextView) nsy.cMd_(this.av, R.id.heart_zone_percent_02);
        this.c = (HealthTextView) nsy.cMd_(this.av, R.id.text_view_aerobic);
        this.j = (HealthTextView) nsy.cMd_(this.av, R.id.text_aerobic_unit);
        this.f = (HealthTextView) nsy.cMd_(this.av, R.id.heart_zone_percent_03);
        this.ao = (HealthTextView) nsy.cMd_(this.av, R.id.text_view_reduce_fat);
        this.ar = (HealthTextView) nsy.cMd_(this.av, R.id.text_reduce_fat_unit);
        this.ap = (HealthTextView) nsy.cMd_(this.av, R.id.heart_zone_percent_04);
        this.ay = (HealthTextView) nsy.cMd_(this.av, R.id.text_view_warm_up);
        this.ba = (HealthTextView) nsy.cMd_(this.av, R.id.text_warm_up_unit);
        this.az = (HealthTextView) nsy.cMd_(this.av, R.id.heart_zone_percent_05);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            ((ImageView) nsy.cMd_(this.av, R.id.upper_limit_enter_ic)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        o();
        nsy.cMd_(this.av, R.id.max_heart_rate_layout).setOnClickListener(this);
    }

    private void o() {
        if (LanguageUtil.ai(BaseApplication.getContext())) {
            this.am.setText(d(this.ag, this.al, 1));
            this.o.setText(d(this.i, this.ag - 1, 1));
            this.j.setText(d(this.d, this.i - 1, 1));
            this.ar.setText(d(this.an, this.d - 1, 1));
            this.ba.setText(d(this.bb, this.an - 1, 1));
            this.af.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            this.n.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            this.c.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            this.ao.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            this.ay.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
            return;
        }
        this.af.setText(d(this.ag, this.al, 1));
        this.n.setText(d(this.i, this.ag - 1, 1));
        this.c.setText(d(this.d, this.i - 1, 1));
        this.ao.setText(d(this.an, this.d - 1, 1));
        this.ay.setText(d(this.bb, this.an - 1, 1));
        this.am.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.o.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.j.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.ar.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
        this.ba.setText(this.p.getString(R$string.IDS_main_watch_heart_rate_unit_string));
    }

    public void a(int i, int i2) {
        this.k = i2;
        kox.e().a(i, i2);
        this.t.removeMessages(7);
        this.t.sendEmptyMessage(7);
    }

    private void n() {
        b(100, HeartRateThresholdConfig.HEART_RATE_LIMIT, 4368, this.au, this.p.getString(R$string.IDS_rate_max_limit));
        b(AnalyticsValue.HEALTH_HEART_RATE_LIMIT_2090002.value(), (String) null);
    }

    public boolean d() {
        return this.k == 0;
    }

    public static void dRS_(BaseHeartRateZoneFragment baseHeartRateZoneFragment, Message message) {
        baseHeartRateZoneFragment.au = message.arg1;
        if (baseHeartRateZoneFragment.d()) {
            kox.e().b(false, baseHeartRateZoneFragment.ax, baseHeartRateZoneFragment.r, baseHeartRateZoneFragment.au, baseHeartRateZoneFragment.al, baseHeartRateZoneFragment.ag, baseHeartRateZoneFragment.i, baseHeartRateZoneFragment.d, baseHeartRateZoneFragment.an, baseHeartRateZoneFragment.bb);
        } else {
            kox.e().d(false, baseHeartRateZoneFragment.ax, baseHeartRateZoneFragment.r, baseHeartRateZoneFragment.au, baseHeartRateZoneFragment.al, baseHeartRateZoneFragment.ag, baseHeartRateZoneFragment.i, baseHeartRateZoneFragment.d, baseHeartRateZoneFragment.an, baseHeartRateZoneFragment.bb);
        }
        kox.e().c(baseHeartRateZoneFragment.ax);
    }

    private void b(int i, int i2, int i3, int i4, String str) {
        rvg.a aVar;
        rvg c;
        if (i <= i2 && (c = (aVar = new rvg.a(this.p, this.t, i3)).c(c(i, i2), Integer.valueOf(i4))) != null) {
            aVar.a(str);
            c.show();
        }
    }

    private int[] c(int i, int i2) {
        if (i > i2) {
            return null;
        }
        int[] iArr = new int[(i2 - i) + 1];
        for (int i3 = i; i3 <= i2; i3++) {
            iArr[i3 - i] = i3;
        }
        return iArr;
    }

    public String d(int i, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer(UnitUtil.e(i, i3, 0));
        stringBuffer.append("～");
        stringBuffer.append(UnitUtil.e(i2, i3, 0));
        return stringBuffer.toString();
    }

    protected void b(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.p, str, hashMap, 0);
    }

    public void c() {
        LogUtil.c(this.at, "goToHeartRateSettingActivity");
        this.ah.d(0, false);
        Intent intent = new Intent(this.p, (Class<?>) HeartRateSectionActivity.class);
        intent.putExtra("maxHeartValue", this.al);
        intent.putExtra("heartRateZone1", this.ag);
        intent.putExtra("heartRateZone2", this.i);
        intent.putExtra("heartRateZone3", this.d);
        intent.putExtra("heartRateZone4", this.an);
        intent.putExtra("heartRateZone5", this.bb);
        intent.putExtra("heartRateZone_posture_type", this.ax);
        intent.putExtra("isStudentMode", false);
        if (d()) {
            intent.putExtra("intent_extra_heart_rate_type", 0);
            intent.putExtra("heartRatePercent1", this.ai);
            intent.putExtra("heartRatePercent2", this.h);
            intent.putExtra("heartRatePercent3", this.b);
            intent.putExtra("heartRatePercent4", this.aq);
            intent.putExtra("heartRatePercent5", this.bc);
        } else {
            intent.putExtra("intent_extra_heart_rate_type", 1);
            intent.putExtra("heartRatePercent1", this.ad);
            intent.putExtra("heartRatePercent2", this.ab);
            intent.putExtra("heartRatePercent3", this.ac);
            intent.putExtra("heartRatePercent4", this.aa);
            intent.putExtra("heartRatePercent5", this.u);
        }
        intent.putExtra("intent_extra_hrr_heart_rate", this.as);
        startActivityForResult(intent, 110);
    }

    public void b(boolean z) {
        String format;
        this.aw.setChecked(this.r);
        this.aw.setVisibility(0);
        this.v.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903303_res_0x7f030107, 2, Integer.valueOf(this.au)));
        this.y.setText(getString(R$string.IDS_hwh_motiontrack_sport_heart_rate_limit_warning, Integer.valueOf(this.au)));
        String string = getString(R$string.IDS_unusual_stopped_message_more_new);
        if (d()) {
            this.aj.setChecked(true);
            this.z.setChecked(false);
            format = this.p.getResources().getString(R$string.IDS_max_heart_rate_introduction, string);
            this.m.setText(R$string.IDS_rate_zone_maximum_threshold);
            this.g.setText(R$string.IDS_rate_zone_anaerobic_threshold);
            this.ae.setText(R$string.IDS_rate_zone_aerobic_threshold);
            this.f10458a.setText(R$string.IDS_rate_zone_fatburn_threshold_string);
            this.e.setText(R$string.IDS_rate_zone_warmup_threshold);
        } else {
            this.aj.setChecked(false);
            this.z.setChecked(true);
            format = String.format(Locale.ENGLISH, getString(R$string.IDS_hrr_heart_rate_description), string);
            this.m.setText(R$string.IDS_rate_zone_hrr_anaerobicAdvance_threshold);
            this.g.setText(R$string.IDS_rate_zone_hrr_anaerobicBase_threshold);
            this.ae.setText(R$string.IDS_rate_zone_hrr_lacticAcid_threshold);
            this.f10458a.setText(R$string.IDS_rate_zone_hrr_aerobicAdvance_threshold);
            this.e.setText(R$string.IDS_rate_zone_hrr_aerobicBase_threshold);
        }
        SpannableString spannableString = new SpannableString(format);
        int length = format.length();
        spannableString.setSpan(new rvd(d()), length - string.length(), length, 17);
        this.x.setMovementMethod(LinkMovementMethod.getInstance());
        this.x.setText(spannableString);
        a(z);
    }

    public void a(boolean z) {
        int i;
        int i2;
        int i3;
        o();
        if (!z) {
            a(this.al);
        }
        if (d()) {
            this.ak.setText(d(Math.round(this.ai), 100, 2));
            this.l.setText(d(Math.round(this.h), Math.round(this.ai), 2));
            this.f.setText(d(Math.round(this.b), Math.round(this.h), 2));
            this.ap.setText(d(Math.round(this.aq), Math.round(this.b), 2));
            this.az.setText(d(Math.round(this.bc), Math.round(this.aq), 2));
        } else {
            this.ak.setText(d(Math.round(this.ad), 100, 2));
            this.l.setText(d(Math.round(this.ab), Math.round(this.ad), 2));
            this.f.setText(d(Math.round(this.ac), Math.round(this.ab), 2));
            this.ap.setText(d(Math.round(this.aa), Math.round(this.ac), 2));
            this.az.setText(d(Math.round(this.u), Math.round(this.aa), 2));
        }
        int i4 = this.ag;
        if (i4 > this.al || (i = this.i) > i4 || (i2 = this.d) > i || (i3 = this.an) > i2 || this.bb > i3) {
            LogUtil.c(this.at, "updateUiZone threshold wrong");
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
            linkedHashMap.put("actiontype", Integer.toString(2));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_HEART_RATE_ZONE_80040005.value(), linkedHashMap);
        }
        LogUtil.c(this.at, "setHeartRateZonePercent LimitPercent = ", Float.valueOf(this.ai), " AnaerobicPercent = ", Float.valueOf(this.h), " AerobicPercent = ", Float.valueOf(this.b), " ReduceFatPercent = ", Float.valueOf(this.aq), " WarmUpPercent = ", Float.valueOf(this.bc), "setHeartRateZone Limit = ", Integer.valueOf(this.ag), " Anaerobic = ", Integer.valueOf(this.i), " Aerobic = ", Integer.valueOf(this.d), " ReduceFat = ", Integer.valueOf(this.an), " WarmUp = ", Integer.valueOf(this.bb));
    }

    public void b(int i, boolean z) {
        if (z && i == this.al) {
            return;
        }
        if (d()) {
            if (Math.round(this.ai) == 90.0f && Math.round(this.h) == 80.0f && Math.round(this.b) == 70.0f && Math.round(this.aq) == 60.0f && Math.round(this.bc) == 50.0f) {
                this.ai = 90.0f;
                this.h = 80.0f;
                this.b = 70.0f;
                this.aq = 60.0f;
                this.bc = 50.0f;
                ReleaseLogUtil.e(this.at, "reset maxPercent");
                return;
            }
            return;
        }
        if (Math.round(this.ad) == 95.0f && Math.round(this.ab) == 88.0f && Math.round(this.ac) == 84.0f && Math.round(this.aa) == 74.0f && Math.round(this.u) == 59.0f) {
            this.ad = 95.0f;
            this.ab = 88.0f;
            this.ac = 84.0f;
            this.aa = 74.0f;
            this.u = 59.0f;
            ReleaseLogUtil.e(this.at, "reset HrrPercent");
        }
    }

    public void g() {
        if (this.ai > 100.0f) {
            this.ai = 90.0f;
            this.h = 80.0f;
            this.b = 70.0f;
            this.aq = 60.0f;
            this.bc = 50.0f;
            LogUtil.c(this.at, "max percent exception");
        }
        int round = Math.round(((this.al - this.as) * this.ad) / 100.0f) + this.as;
        this.ag = round;
        if (round >= this.al) {
            this.ad = 95.0f;
            this.ab = 88.0f;
            this.ac = 84.0f;
            this.aa = 74.0f;
            this.u = 59.0f;
            LogUtil.c(this.at, "hrr percent exception");
        }
    }

    public void f() {
        this.ag = Math.round((this.al * this.ai) / 100.0f);
        this.i = Math.round((this.al * this.h) / 100.0f);
        this.d = Math.round((this.al * this.b) / 100.0f);
        this.an = Math.round((this.al * this.aq) / 100.0f);
        this.bb = Math.round((this.al * this.bc) / 100.0f);
        LogUtil.c(this.at, "processAllValue LimitPercent = ", Float.valueOf(this.ai), ", mLimitCount = ", Integer.valueOf(this.ag), ", AnaerobicPercent = ", Float.valueOf(this.h), "， mAnaerobic = ", Integer.valueOf(this.i), ", AerobicPercent = ", Float.valueOf(this.b), ", ReduceFatPercent = ", Float.valueOf(this.aq), ", mMaxCount = ", Integer.valueOf(this.al), ", mReduceFat = ", Integer.valueOf(this.an), ", WarmUpPercent = ", Float.valueOf(this.bc), ", mWarmUp = ", Integer.valueOf(this.bb));
    }

    public void j() {
        int i = this.al - this.as;
        float f = i;
        this.ag = Math.round((this.ad * f) / 100.0f) + this.as;
        this.i = Math.round((this.ab * f) / 100.0f) + this.as;
        this.d = Math.round((this.ac * f) / 100.0f) + this.as;
        this.an = Math.round((this.aa * f) / 100.0f) + this.as;
        this.bb = Math.round((f * this.u) / 100.0f) + this.as;
        LogUtil.c(this.at, "updateRestHeartRate mHrrAnaerobicPercent = ", Float.valueOf(this.ad), ", mLimitCount = ", Integer.valueOf(this.ag), ", mHrrAnaerobicBasePercent = ", Float.valueOf(this.ab), ", hrr = ", Integer.valueOf(i), ", mAnaerobic = ", Integer.valueOf(this.i), ", mHrrLacticAcidPercent = ", Float.valueOf(this.ac), ", mAerobic = ", Integer.valueOf(this.d), ", mHrrAerobicPercent = ", Float.valueOf(this.aa), ", mReduceFat = ", Integer.valueOf(this.an), ", mHrrAerobicBasePercent = ", Float.valueOf(this.u), ", mWarmUp = ", Integer.valueOf(this.bb));
    }

    private void d(int i) {
        if (i == 1) {
            this.q.setText(R$string.IDS_heart_raet_standing_introduction);
            return;
        }
        if (i == 2) {
            this.q.setText(R$string.IDS_heart_raet_riding_introduction);
            return;
        }
        if (i == 3) {
            this.q.setText(R$string.IDS_heart_raet_strokes_introduction);
        } else if (i == 4) {
            this.q.setText(R$string.IDS_heart_raet_other_introduction);
        } else {
            LogUtil.a(this.at, "exception position:", Integer.valueOf(i));
        }
    }

    private void b(int i) {
        int anaerobicThreshold = this.w.getAnaerobicThreshold();
        int aerobicThreshold = this.w.getAerobicThreshold();
        if (i <= 0) {
            LogUtil.e(this.at, "Heart Rate is wrong");
            return;
        }
        float f = i;
        this.ai = (anaerobicThreshold * 100) / f;
        this.h = (aerobicThreshold * 100) / f;
        this.b = (this.w.getFatBurnThreshold() * 100) / f;
        this.aq = (this.w.getWarmUpThreshold() * 100) / f;
        float fitnessThreshold = (this.w.getFitnessThreshold() * 100) / f;
        this.bc = fitnessThreshold;
        if (fitnessThreshold < 30.0f) {
            this.bc = 30.0f;
        }
        LogUtil.c(this.at, "setHeartRateZonePercent LimitPercent = ", Float.valueOf(this.ai), "AnaerobicPercent = ", Float.valueOf(this.h), "AerobicPercent = ", Float.valueOf(this.b), "ReduceFatPercent = ", Float.valueOf(this.aq), "WarmUpPercent = ", Float.valueOf(this.bc));
        int anaerobicAdvanceThreshold = this.w.getAnaerobicAdvanceThreshold();
        int anaerobicBaseThreshold = this.w.getAnaerobicBaseThreshold();
        int lacticAcidThreshold = this.w.getLacticAcidThreshold();
        int aerobicAdvanceThreshold = this.w.getAerobicAdvanceThreshold();
        int aerobicBaseThreshold = this.w.getAerobicBaseThreshold();
        float f2 = i - this.as;
        float f3 = ((anaerobicAdvanceThreshold - r6) * 100) / f2;
        this.ad = f3;
        this.ab = ((anaerobicBaseThreshold - r6) * 100) / f2;
        this.ac = ((lacticAcidThreshold - r6) * 100) / f2;
        this.aa = ((aerobicAdvanceThreshold - r6) * 100) / f2;
        float f4 = ((aerobicBaseThreshold - r6) * 100) / f2;
        this.u = f4;
        if (f4 < 30.0f) {
            this.u = 30.0f;
        }
        LogUtil.c(this.at, "setHeartRateZonePercent hrrHeartRate LimitPercent =", Float.valueOf(f3), " AnaerobicPercent = ", Float.valueOf(this.ab), " AerobicPercent = ", Float.valueOf(this.ac), " ReduceFatPercent = ", Float.valueOf(this.aa), " WarmUpPercent = ", Float.valueOf(this.u));
        b(i, true);
        g();
    }
}
