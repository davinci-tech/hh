package com.huawei.ui.main.stories.settings.activity.heartrate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.ixx;
import defpackage.kox;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.rve;
import defpackage.rvg;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.LinkedList;

/* loaded from: classes7.dex */
public class HeartRateSectionActivity extends BaseActivity implements View.OnClickListener {
    private boolean ab;
    private HealthTextView ae;
    private int aj;
    private HealthTextView ak;
    private int al;
    private int an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private HealthTextView av;
    private int ax;
    private RelativeLayout ay;
    private int az;
    private NoTitleCustomAlertDialog ba;
    private NoTitleCustomAlertDialog bb;
    private NoTitleCustomAlertDialog be;
    private CustomTitleBar bg;
    private HealthTextView bi;
    private HealthTextView bj;
    private HealthTextView bm;
    private HealthTextView e;
    private Context f;
    private HealthTextView h;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private HeartRateZoneSeekBar w;
    private HealthTextView z;
    private int bc = 60;
    private boolean ah = false;
    private boolean g = true;
    private int bd = 195;
    private int am = 195;
    private int ai = 176;
    private float af = 90.0f;

    /* renamed from: a, reason: collision with root package name */
    private int f10449a = 156;
    private float d = 80.0f;
    private int b = 137;
    private float c = 70.0f;
    private int aw = 117;
    private float at = 60.0f;
    private int bf = 98;
    private float bh = 50.0f;
    private float aa = 95.0f;
    private float ac = 88.0f;
    private float ad = 84.0f;
    private float x = 74.0f;
    private float y = 59.0f;
    private Handler i = new e(this);
    private boolean ag = false;
    private View.OnClickListener au = new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(HeartRateSectionActivity.this);
            builder.e(HeartRateSectionActivity.this.f.getResources().getString(R$string.IDS_sure_to_reset_button_rate_zone_text)).czE_(HeartRateSectionActivity.this.f.getResources().getString(R$string.IDS_plugin_menu_reset), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.1.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    HeartRateSectionActivity.this.c(AnalyticsValue.HEALTH_MINE_RECOVER_HEARTRATE_AND_WARNING_2040041.value(), "1");
                    kox.e().c(HeartRateSectionActivity.this.ah, HeartRateSectionActivity.this.az);
                    HeartRateSectionActivity.this.bc = kox.e().d(HeartRateSectionActivity.this.ah, HeartRateSectionActivity.this.az);
                    HeartRateSectionActivity.this.d();
                    if (HeartRateSectionActivity.this.i()) {
                        kox.e().b(HeartRateSectionActivity.this.ah, HeartRateSectionActivity.this.az, HeartRateSectionActivity.this.g, HeartRateSectionActivity.this.bd, HeartRateSectionActivity.this.am, HeartRateSectionActivity.this.ai, HeartRateSectionActivity.this.f10449a, HeartRateSectionActivity.this.b, HeartRateSectionActivity.this.aw, HeartRateSectionActivity.this.bf);
                    } else {
                        kox.e().d(HeartRateSectionActivity.this.ah, HeartRateSectionActivity.this.az, HeartRateSectionActivity.this.g, HeartRateSectionActivity.this.bd, HeartRateSectionActivity.this.am, HeartRateSectionActivity.this.ai, HeartRateSectionActivity.this.f10449a, HeartRateSectionActivity.this.b, HeartRateSectionActivity.this.aw, HeartRateSectionActivity.this.bf);
                        kox.e().a(HeartRateSectionActivity.this.ah, HeartRateSectionActivity.this.az, HeartRateSectionActivity.this.bc);
                    }
                    HeartRateSectionActivity.this.a();
                    HeartRateSectionActivity.this.b(HeartRateSectionActivity.this.am);
                    HeartRateSectionActivity.this.ag = false;
                    HeartRateSectionActivity.this.ab = true;
                    HeartRateSectionActivity.this.n();
                    HeartRateSectionActivity.this.aa();
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }).czA_(HeartRateSectionActivity.this.f.getResources().getString(R$string.IDS_settings_button_cancal), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.1.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    HeartRateSectionActivity.this.c(AnalyticsValue.HEALTH_MINE_RECOVER_HEARTRATE_AND_WARNING_2040041.value(), "2");
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
            builder.e().show();
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private int j = 0;

    private float a(float f) {
        if (f < 30.0f) {
            return 30.0f;
        }
        return f;
    }

    static class e extends BaseHandler<HeartRateSectionActivity> {
        e(HeartRateSectionActivity heartRateSectionActivity) {
            super(heartRateSectionActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dRz_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HeartRateSectionActivity heartRateSectionActivity, Message message) {
            if (message == null || heartRateSectionActivity == null) {
                LogUtil.b("HeartRateSectionActivity", "handleMessageWhenReferenceNotNull  message is null or aactivity is null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                heartRateSectionActivity.ai = message.arg1;
                b(heartRateSectionActivity);
            } else if (i == 2) {
                heartRateSectionActivity.f10449a = message.arg1;
                b(heartRateSectionActivity);
            } else if (i == 3) {
                heartRateSectionActivity.b = message.arg1;
                b(heartRateSectionActivity);
            } else if (i == 4) {
                heartRateSectionActivity.aw = message.arg1;
                b(heartRateSectionActivity);
            } else if (i == 5) {
                heartRateSectionActivity.bf = message.arg1;
                b(heartRateSectionActivity);
            } else if (i == 1114) {
                heartRateSectionActivity.am = message.arg1;
                heartRateSectionActivity.a(heartRateSectionActivity.am, false);
                c(heartRateSectionActivity);
            } else if (i == 1115) {
                heartRateSectionActivity.bc = message.arg1;
                if (!heartRateSectionActivity.i()) {
                    heartRateSectionActivity.ac();
                }
            }
            heartRateSectionActivity.ag = false;
            heartRateSectionActivity.aa();
        }

        private void b(HeartRateSectionActivity heartRateSectionActivity) {
            heartRateSectionActivity.b(heartRateSectionActivity.am);
            c(heartRateSectionActivity);
        }

        private void c(HeartRateSectionActivity heartRateSectionActivity) {
            if (heartRateSectionActivity.i()) {
                heartRateSectionActivity.l();
            } else {
                heartRateSectionActivity.ac();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_heart_rate_section_new);
        j();
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        return this.j == 0;
    }

    private void r() {
        String string;
        if (i()) {
            string = getResources().getString(R$string.IDS_rate_zone_max_text);
        } else {
            string = getResources().getString(R$string.IDS_rate_zone_hrr_text);
        }
        this.bg.setTitleText(string);
    }

    private void e() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("HeartRateSectionActivity", "initData Intent  is null");
            return;
        }
        this.j = intent.getIntExtra("intent_extra_heart_rate_type", 0);
        this.am = intent.getIntExtra("maxHeartValue", this.am);
        this.ai = intent.getIntExtra("heartRateZone1", this.ai);
        this.f10449a = intent.getIntExtra("heartRateZone2", this.f10449a);
        this.b = intent.getIntExtra("heartRateZone3", this.b);
        this.aw = intent.getIntExtra("heartRateZone4", this.aw);
        this.bf = intent.getIntExtra("heartRateZone5", this.bf);
        this.az = intent.getIntExtra("heartRateZone_posture_type", 0);
        this.ah = intent.getBooleanExtra("isStudentMode", false);
        this.bc = intent.getIntExtra("intent_extra_hrr_heart_rate", this.bc);
        if (i()) {
            this.ae.setText(R$string.IDS_rate_zone_maximum_threshold);
            this.h.setText(R$string.IDS_rate_zone_anaerobic_threshold);
            this.e.setText(R$string.IDS_rate_zone_aerobic_threshold);
            this.av.setText(R$string.IDS_rate_zone_fatburn_threshold_string);
            this.bm.setText(R$string.IDS_rate_zone_warmup_threshold);
        } else {
            this.ay.setVisibility(0);
            this.z.setVisibility(0);
            this.ae.setText(R$string.IDS_rate_zone_hrr_anaerobicAdvance_threshold);
            this.h.setText(R$string.IDS_rate_zone_hrr_anaerobicBase_threshold);
            this.e.setText(R$string.IDS_rate_zone_hrr_lacticAcid_threshold);
            this.av.setText(R$string.IDS_rate_zone_hrr_aerobicAdvance_threshold);
            this.bm.setText(R$string.IDS_rate_zone_hrr_aerobicBase_threshold);
        }
        dRy_(intent);
        a();
        r();
        aa();
    }

    private void dRy_(Intent intent) {
        if (i()) {
            this.af = intent.getFloatExtra("heartRatePercent1", this.af);
            this.d = intent.getFloatExtra("heartRatePercent2", this.d);
            this.c = intent.getFloatExtra("heartRatePercent3", this.c);
            this.at = intent.getFloatExtra("heartRatePercent4", this.at);
            this.bh = intent.getFloatExtra("heartRatePercent5", this.bh);
            return;
        }
        this.aa = intent.getFloatExtra("heartRatePercent1", this.aa);
        this.ac = intent.getFloatExtra("heartRatePercent2", this.ac);
        this.ad = intent.getFloatExtra("heartRatePercent3", this.ad);
        this.x = intent.getFloatExtra("heartRatePercent4", this.x);
        this.y = intent.getFloatExtra("heartRatePercent5", this.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.ao = this.am;
        this.ar = this.bc;
        this.ap = this.ai;
        this.aq = this.f10449a;
        this.aj = this.b;
        this.as = this.aw;
        this.ax = this.bf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return (this.ao == this.am && this.ar == this.bc && this.ap == this.ai && this.aq == this.f10449a && this.aj == this.b && this.as == this.aw && this.ax == this.bf) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.am = this.ao;
        this.bc = this.ar;
        this.ai = this.ap;
        this.f10449a = this.aq;
        this.b = this.aj;
        this.aw = this.as;
        this.bf = this.ax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aa() {
        this.p.setText(UnitUtil.e(100.0d, 2, 0));
        this.ak.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903303_res_0x7f030107, 2, Integer.valueOf(this.am)));
        this.t.setText(UnitUtil.e(this.am, 1, 0));
        this.q.setText(UnitUtil.e(this.ai, 1, 0));
        this.m.setText(UnitUtil.e(this.f10449a, 1, 0));
        this.n.setText(UnitUtil.e(this.b, 1, 0));
        this.s.setText(UnitUtil.e(this.aw, 1, 0));
        this.v.setText(UnitUtil.e(this.bf, 1, 0));
        if (i()) {
            this.al = Math.round((this.am * 30) / 100.0f);
            this.r.setText(UnitUtil.e(Math.round(this.af), 2, 0));
            this.o.setText(UnitUtil.e(Math.round(this.d), 2, 0));
            this.l.setText(UnitUtil.e(Math.round(this.c), 2, 0));
            this.k.setText(UnitUtil.e(Math.round(this.at), 2, 0));
            this.u.setText(UnitUtil.e(Math.round(this.bh), 2, 0));
        } else {
            int i = this.am;
            this.an = Math.round((((i - r2) * 30) / 100.0f) + this.bc);
            this.z.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903303_res_0x7f030107, 2, Integer.valueOf(this.bc)));
            this.r.setText(UnitUtil.e(Math.round(this.aa), 2, 0));
            this.o.setText(UnitUtil.e(Math.round(this.ac), 2, 0));
            this.l.setText(UnitUtil.e(Math.round(this.ad), 2, 0));
            this.k.setText(UnitUtil.e(Math.round(this.x), 2, 0));
            this.u.setText(UnitUtil.e(Math.round(this.y), 2, 0));
        }
        w();
    }

    private void w() {
        if (c()) {
            LogUtil.h("HeartRateSectionActivity", "updateHeartZoneDataConfig isHeartRateZoneConflict");
        } else if (i()) {
            this.w.a(c(Math.round(this.bh), this.at, this.c, this.d, this.af));
        } else {
            this.w.a(c(Math.round(this.y), this.x, this.ad, this.ac, this.aa));
        }
    }

    private rve c(float f, float f2, float f3, float f4, float f5) {
        rve.c cVar = new rve.c(f, f2, -12739329);
        rve.c cVar2 = new rve.c(f2, f3, -16722343);
        rve.c cVar3 = new rve.c(f3, f4, -17893);
        rve.c cVar4 = new rve.c(f4, f5, -301790);
        rve.c cVar5 = new rve.c(f5, 100.0f, -52448);
        LinkedList linkedList = new LinkedList();
        linkedList.add(cVar);
        linkedList.add(cVar2);
        linkedList.add(cVar3);
        linkedList.add(cVar4);
        linkedList.add(cVar5);
        return new rve(30.0f, 100.0f, this.f.getResources().getColor(R.color._2131296670_res_0x7f09019e), linkedList, new float[]{30.0f, 40.0f, 50.0f, 60.0f, 70.0f, 80.0f, 90.0f, 100.0f});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        if (z && i == this.am) {
            return;
        }
        if (i()) {
            if (Math.round(this.af) == 90.0f && Math.round(this.d) == 80.0f && Math.round(this.c) == 70.0f && Math.round(this.at) == 60.0f && Math.round(this.bh) == 50.0f) {
                this.af = 90.0f;
                this.d = 80.0f;
                this.c = 70.0f;
                this.at = 60.0f;
                this.bh = 50.0f;
                ReleaseLogUtil.e("HeartRateSectionActivity", "reset maxPercent");
                return;
            }
            return;
        }
        if (Math.round(this.aa) == 95.0f && Math.round(this.ac) == 88.0f && Math.round(this.ad) == 84.0f && Math.round(this.x) == 74.0f && Math.round(this.y) == 59.0f) {
            this.aa = 95.0f;
            this.ac = 88.0f;
            this.ad = 84.0f;
            this.x = 74.0f;
            this.y = 59.0f;
            ReleaseLogUtil.e("HeartRateSectionActivity", "reset HrrPercent");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.ai = Math.round((this.am * this.af) / 100.0f);
        this.f10449a = Math.round((this.am * this.d) / 100.0f);
        this.b = Math.round((this.am * this.c) / 100.0f);
        this.aw = Math.round((this.am * this.at) / 100.0f);
        this.bf = Math.round((this.am * this.bh) / 100.0f);
        LogUtil.a("HeartRateSectionActivity", "processAllValue LimitPercent = ", Float.valueOf(this.af), ", mLimitCount = ", Integer.valueOf(this.ai), ", AnaerobicPercent = ", Float.valueOf(this.d), "， mAnaerobic = ", Integer.valueOf(this.f10449a), ", AerobicPercent = ", Float.valueOf(this.c), ", ReduceFatPercent = ", Float.valueOf(this.at), ", mMaxCount = ", Integer.valueOf(this.am), ", mReduceFat = ", Integer.valueOf(this.aw), ", WarmUpPercent = ", Float.valueOf(this.bh), ", mWarmUp = ", Integer.valueOf(this.bf));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        int i = this.am - this.bc;
        float f = i;
        this.ai = Math.round((this.aa * f) / 100.0f) + this.bc;
        this.f10449a = Math.round((this.ac * f) / 100.0f) + this.bc;
        this.b = Math.round((this.ad * f) / 100.0f) + this.bc;
        this.aw = Math.round((this.x * f) / 100.0f) + this.bc;
        this.bf = Math.round((f * this.y) / 100.0f) + this.bc;
        LogUtil.a("HeartRateSectionActivity", "updateRestHeartRate mHrrAnaerobicPercent = ", Float.valueOf(this.aa), ", mLimitCount = ", Integer.valueOf(this.ai), ", mHrrAnaerobicBasePercent = ", Float.valueOf(this.ac), ", hrr = ", Integer.valueOf(i), ", mAnaerobic = ", Integer.valueOf(this.f10449a), ", mHrrLacticAcidPercent = ", Float.valueOf(this.ad), ", mAerobic = ", Integer.valueOf(this.b), ", mHrrAerobicPercent = ", Float.valueOf(this.x), ", mReduceFat = ", Integer.valueOf(this.aw), ", mHrrAerobicBasePercent = ", Float.valueOf(this.y), ", mWarmUp = ", Integer.valueOf(this.bf));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (!i()) {
            int i2 = i - this.bc;
            if (i2 == 0) {
                LogUtil.h("HeartRateSectionActivity", "updateUiPercent reserve is zero");
                return;
            }
            float f = i2;
            this.aa = a(((this.ai - r0) * 100) / f);
            this.ac = a(((this.f10449a - this.bc) * 100) / f);
            this.ad = a(((this.b - this.bc) * 100) / f);
            this.x = a(((this.aw - this.bc) * 100) / f);
            this.y = a(((this.bf - this.bc) * 100) / f);
        } else {
            if (i == 0) {
                LogUtil.h("HeartRateSectionActivity", "updateUiPercent maxHeartRateValue is zero");
                return;
            }
            float f2 = i;
            this.af = a((this.ai * 100) / f2);
            this.d = a((this.f10449a * 100) / f2);
            this.c = a((this.b * 100) / f2);
            this.at = a((this.aw * 100) / f2);
            this.bh = a((this.bf * 100) / f2);
        }
        LogUtil.a("HeartRateSectionActivity", "updateUiPercent LimitPercent = ", Float.valueOf(this.af), ", AnaerobicPercent = ", Float.valueOf(this.d), ", AerobicPercent = ", Float.valueOf(this.c), ", ReduceFatPercent = ", Float.valueOf(this.at), ", WarmUpPercent = ", Float.valueOf(this.bh));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        HealthTextView healthTextView = this.bi;
        if (healthTextView == null || this.bj == null) {
            return;
        }
        healthTextView.setBackgroundResource(R.drawable.pace_range_time_background);
        this.bj.setBackgroundResource(R.drawable.pace_range_time_background);
        this.bj = null;
        this.bi = null;
    }

    private void c(boolean z) {
        String string;
        if (z) {
            if (i()) {
                string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_maximum_threshold)});
            } else {
                string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_hrr_anaerobicAdvance_threshold)});
            }
        } else {
            string = getString(R$string.IDS_main_watch_detail_max_heart_rate_string);
        }
        n();
        int i = this.am;
        b(Math.max(100, this.bc + 15), HeartRateThresholdConfig.HEART_RATE_LIMIT, 1114, i, string);
        c(AnalyticsValue.HEALTH_HEART_RATE_MAX_2090003.value(), (String) null);
    }

    private void j() {
        this.f = this;
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.heart_rate_section_title_bar);
        this.bg = customTitleBar;
        customTitleBar.setRightButtonVisibility(0);
        this.bg.setRightButtonDrawable(getDrawable(R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R$string.IDS_contact_confirm));
        this.bg.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HeartRateSectionActivity.this.c()) {
                    HeartRateSectionActivity.this.s();
                    HeartRateSectionActivity.this.x();
                } else {
                    HeartRateSectionActivity.this.o();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.bg.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!HeartRateSectionActivity.this.g() || !HeartRateSectionActivity.this.ag || !HeartRateSectionActivity.this.c()) {
                    if (HeartRateSectionActivity.this.g() || HeartRateSectionActivity.this.c()) {
                        HeartRateSectionActivity.this.v();
                    } else if (HeartRateSectionActivity.this.ab) {
                        HeartRateSectionActivity.this.o();
                    } else {
                        HeartRateSectionActivity.this.finish();
                    }
                } else {
                    HeartRateSectionActivity.this.y();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        nsy.cMc_(this, R.id.max_layout).setOnClickListener(this);
        this.ak = (HealthTextView) nsy.cMc_(this, R.id.max_textview);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.rest_heart_rate_layout);
        this.ay = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.z = (HealthTextView) nsy.cMc_(this, R.id.rest_heart_rate_textview);
        nsy.cMc_(this, R.id.reset_heart_rate_zone).setOnClickListener(this.au);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            ((ImageView) nsy.cMc_(this, R.id.max_enter_ic)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            ((ImageView) nsy.cMc_(this, R.id.rest_heart_rate_enter_ic)).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        h();
    }

    private void h() {
        this.ae = (HealthTextView) findViewById(R.id.tv_heart_rate_zone_01);
        this.h = (HealthTextView) findViewById(R.id.tv_heart_rate_zone_name_02);
        this.e = (HealthTextView) findViewById(R.id.tv_heart_rate_zone_name_03);
        this.av = (HealthTextView) findViewById(R.id.tv_heart_rate_zone_name_04);
        this.bm = (HealthTextView) findViewById(R.id.tv_heart_rate_zone_name_5);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.heart_rate_max_value);
        this.t = healthTextView;
        healthTextView.setOnClickListener(this);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.heart_rate_limit_setting_value);
        this.q = healthTextView2;
        healthTextView2.setOnClickListener(this);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.heart_rate_anaerobic_value);
        this.m = healthTextView3;
        healthTextView3.setOnClickListener(this);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.heart_rate_aerobic_value);
        this.n = healthTextView4;
        healthTextView4.setOnClickListener(this);
        HealthTextView healthTextView5 = (HealthTextView) findViewById(R.id.heart_fat_reduce_value);
        this.s = healthTextView5;
        healthTextView5.setOnClickListener(this);
        HealthTextView healthTextView6 = (HealthTextView) findViewById(R.id.heart_rate_warm_up_value);
        this.v = healthTextView6;
        healthTextView6.setOnClickListener(this);
        HealthTextView healthTextView7 = (HealthTextView) findViewById(R.id.heart_rate_max_percent);
        this.p = healthTextView7;
        healthTextView7.setOnClickListener(this);
        HealthTextView healthTextView8 = (HealthTextView) findViewById(R.id.heart_rate_limit_setting_percent);
        this.r = healthTextView8;
        healthTextView8.setOnClickListener(this);
        HealthTextView healthTextView9 = (HealthTextView) findViewById(R.id.heart_rate_anaerobic_percent);
        this.o = healthTextView9;
        healthTextView9.setOnClickListener(this);
        HealthTextView healthTextView10 = (HealthTextView) findViewById(R.id.heart_rate_aerobic_percent);
        this.l = healthTextView10;
        healthTextView10.setOnClickListener(this);
        HealthTextView healthTextView11 = (HealthTextView) findViewById(R.id.heart_fat_reduce_percent);
        this.k = healthTextView11;
        healthTextView11.setOnClickListener(this);
        HealthTextView healthTextView12 = (HealthTextView) findViewById(R.id.heart_rate_warm_up_percent);
        this.u = healthTextView12;
        healthTextView12.setOnClickListener(this);
        ((HealthTextView) nsy.cMc_(this, R.id.tv_heart_percent_range_result_title)).setText(getString(R$string.IDS_hwh_motiontrack_heart_rate_percentage, new Object[]{Character.valueOf(new DecimalFormatSymbols().getPercent())}));
        this.w = (HeartRateZoneSeekBar) nsy.cMc_(this, R.id.kRangeSeekBarView);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("HeartRateSectionActivity", "onBackPressed");
        if (g() && this.ag && c()) {
            y();
            return;
        }
        if (g() || c()) {
            v();
        } else if (this.ab) {
            o();
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.ao != this.am) {
            kox.e().e(this.ah, this.az);
        }
        Intent intent = new Intent(this, (Class<?>) HeartRateZoneSettingActivity.class);
        intent.putExtra("maxHeartValue", this.am);
        intent.putExtra("heartRateZone1", this.ai);
        intent.putExtra("heartRateZone2", this.f10449a);
        intent.putExtra("heartRateZone3", this.b);
        intent.putExtra("heartRateZone4", this.aw);
        intent.putExtra("heartRateZone5", this.bf);
        intent.putExtra("intent_extra_max_heart_rate_change", this.ao != this.am);
        intent.putExtra("intent_extra_other_heart_rate_change", g());
        if (!i()) {
            intent.putExtra("intent_extra_hrr_heart_rate", this.bc);
            intent.putExtra("heartRatePercent1", this.aa);
            intent.putExtra("heartRatePercent2", this.ac);
            intent.putExtra("heartRatePercent3", this.ad);
            intent.putExtra("heartRatePercent4", this.x);
            intent.putExtra("heartRatePercent5", this.y);
        } else {
            intent.putExtra("heartRatePercent1", this.af);
            intent.putExtra("heartRatePercent2", this.d);
            intent.putExtra("heartRatePercent3", this.c);
            intent.putExtra("heartRatePercent4", this.at);
            intent.putExtra("heartRatePercent5", this.bh);
        }
        setResult(3, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        int i;
        int i2;
        int i3;
        int i4 = this.ai;
        return i4 >= this.am || i4 <= (i = this.f10449a) || i <= (i2 = this.b) || i2 <= (i3 = this.aw) || i3 <= this.bf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        int i = this.ai;
        int i2 = this.am;
        if (i >= i2) {
            a(this.q, this.r);
            return;
        }
        int i3 = this.f10449a;
        if (i <= i3) {
            if (i2 <= i3) {
                a(this.m, this.o);
                return;
            } else {
                a(this.q, this.r);
                return;
            }
        }
        int i4 = this.b;
        if (i3 <= i4) {
            if (i <= i4) {
                a(this.n, this.l);
                return;
            } else {
                a(this.m, this.o);
                return;
            }
        }
        int i5 = this.aw;
        if (i4 <= i5) {
            if (i3 <= i5) {
                a(this.s, this.k);
                return;
            } else {
                a(this.n, this.l);
                return;
            }
        }
        int i6 = this.bf;
        if (i5 <= i6) {
            if (i4 <= i6) {
                a(this.v, this.u);
            } else {
                a(this.s, this.k);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        c(this.be);
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(getString(R$string.IDS_pace_range_exit_save_content)).czA_(getString(R$string.IDS_btn_discard), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HeartRateSectionActivity.this.ab) {
                    HeartRateSectionActivity.this.f();
                    HeartRateSectionActivity.this.o();
                } else {
                    HeartRateSectionActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czE_(getString(R$string.IDS_save), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HeartRateSectionActivity.this.c()) {
                    HeartRateSectionActivity.this.s();
                    HeartRateSectionActivity.this.x();
                } else {
                    HeartRateSectionActivity.this.o();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.be = e2;
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.ab) {
            d();
            b(this.am);
            o();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        c(this.ba);
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(getString(R$string.IDS_heart_zone_waring_exit)).czA_(getString(R$string.IDS_hw_common_ui_dialog_cancel), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HeartRateSectionActivity.this.ba != null) {
                    HeartRateSectionActivity.this.ba.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czE_(getString(R$string.IDS_hw_common_ui_dialog_confirm), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeartRateSectionActivity.this.p();
                HeartRateSectionActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.ba = e2;
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        c(this.bb);
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(getString(R$string.IDS_heart_zone_waring_content)).czE_(getString(R$string.IDS_common_notification_know_tips), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HeartRateSectionActivity.this.ag = true;
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.bb = e2;
        e2.show();
        this.bb.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.ui.main.stories.settings.activity.heartrate.HeartRateSectionActivity.7
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                HeartRateSectionActivity.this.ag = true;
            }
        });
    }

    private void c(BaseDialog baseDialog) {
        if (baseDialog == null || !baseDialog.isShowing()) {
            return;
        }
        baseDialog.dismiss();
    }

    private void a(HealthTextView healthTextView, HealthTextView healthTextView2) {
        if (healthTextView2 == null || healthTextView == null) {
            return;
        }
        healthTextView.setBackgroundResource(R.drawable.pace_range_time_warning_background);
        healthTextView2.setBackgroundResource(R.drawable.pace_range_time_warning_background);
        this.bj = healthTextView;
        this.bi = healthTextView2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(600)) {
            LogUtil.b("HeartRateSectionActivity", "CLICK IS FAST");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.max_layout) {
            c(false);
        } else if (view.getId() == R.id.rest_heart_rate_layout) {
            b();
        } else if (view == this.t || view == this.p) {
            c(true);
        } else if (view == this.q || view == this.r) {
            q();
        } else if (view == this.m || view == this.o) {
            m();
        } else if (view == this.n || view == this.l) {
            k();
        } else if (view == this.s || view == this.k) {
            t();
        } else if (view == this.v || view == this.u) {
            u();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        b(30, Math.min(110, this.am - 15), 1115, this.bc, getString(R$string.IDS_resting_heart_rate_string));
        c(AnalyticsValue.HEALTH_HEART_RATE_REST_2090010.value(), (String) null);
    }

    private void b(int i, int i2, int i3, int i4, String str) {
        rvg.a aVar;
        rvg c;
        if (i <= i2 && (c = (aVar = new rvg.a(this, this.i, i3)).c(e(i, i2), Integer.valueOf(i4))) != null) {
            aVar.a(str);
            c.show();
        }
    }

    private int[] e(int i, int i2) {
        if (i > i2) {
            return new int[0];
        }
        int[] iArr = new int[(i2 - i) + 1];
        for (int i3 = i; i3 <= i2; i3++) {
            iArr[i3 - i] = i3;
        }
        return iArr;
    }

    private void q() {
        String string;
        int i;
        if (i()) {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_anaerobic_threshold)});
            i = this.al;
        } else {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_hrr_anaerobicBase_threshold)});
            i = this.an;
        }
        n();
        b(i, HeartRateThresholdConfig.HEART_RATE_LIMIT, 1, this.ai, string);
        c(AnalyticsValue.HEALTH_HEART_RATE_MAXIMUM_2090004.value(), (String) null);
    }

    private void m() {
        String string;
        int i;
        if (i()) {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_aerobic_threshold)});
            i = this.al;
        } else {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_hrr_lacticAcid_threshold)});
            i = this.an;
        }
        n();
        b(i, HeartRateThresholdConfig.HEART_RATE_LIMIT, 2, this.f10449a, string);
        c(AnalyticsValue.HEALTH_HEART_RATE_ANAEROBIC_ENDURANCE_2090005.value(), (String) null);
    }

    private void k() {
        String string;
        int i;
        if (i()) {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_fatburn_threshold_string)});
            i = this.al;
        } else {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_hrr_aerobicAdvance_threshold)});
            i = this.an;
        }
        n();
        b(i, HeartRateThresholdConfig.HEART_RATE_LIMIT, 3, this.b, string);
        c(AnalyticsValue.HEALTH_HEART_RATE_AEROBIC_ENDURANCE_2090006.value(), (String) null);
    }

    private void t() {
        String string;
        int i;
        if (i()) {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_warmup_threshold)});
            i = this.al;
        } else {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_upper_limit, new Object[]{getString(R$string.IDS_rate_zone_hrr_aerobicBase_threshold)});
            i = this.an;
        }
        n();
        b(i, HeartRateThresholdConfig.HEART_RATE_LIMIT, 4, this.aw, string);
        c(AnalyticsValue.HEALTH_HEART_RATE_FAT_BRUNING_2090007.value(), (String) null);
    }

    private void u() {
        String string;
        int i;
        if (i()) {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_lower_limit, new Object[]{getString(R$string.IDS_rate_zone_warmup_threshold)});
            i = this.al;
        } else {
            string = getString(R$string.IDS_hwh_motiontrack_heart_rate_lower_limit, new Object[]{getString(R$string.IDS_rate_zone_hrr_aerobicBase_threshold)});
            i = this.an;
        }
        n();
        b(i, HeartRateThresholdConfig.HEART_RATE_LIMIT, 5, this.bf, string);
        c(AnalyticsValue.HEALTH_HEART_RATE_WARM_UP_2090008.value(), (String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.f, str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HeartRateThresholdConfig a2 = a(this.ah, this.az);
        this.g = a2.getWarningEnable();
        this.bd = a2.getWarningLimit();
        this.am = a2.getMaxThreshold();
        if (i()) {
            this.ai = a2.getAnaerobicThreshold();
            this.f10449a = a2.getAerobicThreshold();
            this.b = a2.getFatBurnThreshold();
            this.aw = a2.getWarmUpThreshold();
            this.bf = a2.getFitnessThreshold();
        } else {
            this.ai = a2.getAnaerobicAdvanceThreshold();
            this.f10449a = a2.getAnaerobicBaseThreshold();
            this.b = a2.getLacticAcidThreshold();
            this.aw = a2.getAerobicAdvanceThreshold();
            this.bf = a2.getAerobicBaseThreshold();
        }
        LogUtil.a("HeartRateSectionActivity", "initData mHasMaxAlarm:", Boolean.valueOf(this.g), ", mUpLimit = ", Integer.valueOf(this.bd), ", mMaxCount = ", Integer.valueOf(this.am), ",mLimitCount = ", Integer.valueOf(this.ai), ", mAnaerobic = ", Integer.valueOf(this.f10449a), ", mAerobic = ", Integer.valueOf(this.b), ", mReduceFat = ", Integer.valueOf(this.aw), ", mWarmUp = ", Integer.valueOf(this.bf));
    }

    private HeartRateThresholdConfig a(boolean z, int i) {
        if (z) {
            return kox.e().a().c().getStudentHeartRateThresholdData();
        }
        return kox.e().d().getPostureType(i);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
