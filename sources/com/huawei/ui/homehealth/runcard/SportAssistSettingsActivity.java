package com.huawei.ui.homehealth.runcard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.ui.view.SportSettingSwitchItem;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity;
import com.huawei.ui.homehealth.runcard.trackfragments.RopeSkippingTargetDialog;
import com.huawei.ui.homehealth.runcard.trackfragments.SportCustomTargetDialog;
import com.huawei.ui.homehealth.runcard.utils.TargetChoicePickerUtils;
import com.huawei.ui.main.stories.ihealthlabs.freeindoorrunning.FreeIndoorRunningActivity;
import com.huawei.ui.main.stories.me.activity.AutoIdentifyRecordActivity;
import com.huawei.ui.main.stories.settings.js.CustomDataService;
import defpackage.ash;
import defpackage.bzs;
import defpackage.caj;
import defpackage.car;
import defpackage.enh;
import defpackage.ggs;
import defpackage.gso;
import defpackage.guw;
import defpackage.guz;
import defpackage.gww;
import defpackage.gzl;
import defpackage.ixx;
import defpackage.jah;
import defpackage.jdx;
import defpackage.koq;
import defpackage.njh;
import defpackage.nsn;
import defpackage.ory;
import defpackage.osl;
import defpackage.owp;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class SportAssistSettingsActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthImageView f9517a;
    private boolean an;
    private RelativeLayout ao;
    private HealthTextView at;
    private HealthDivider au;
    private HealthScrollView av;
    private ImageView aw;
    private RelativeLayout ax;
    private LinearLayout ay;
    private ImageView az;
    private ImageView ba;
    private HealthDivider bb;
    private RelativeLayout bc;
    private HealthTextView be;
    private HealthDivider bf;
    private HealthTextView bg;
    private HealthTextView bh;
    private int bi;
    private HealthTextView bj;
    private ImageView bk;
    private RelativeLayout bl;
    private HealthTextView bm;
    private String bo;
    private HealthSwitchButton br;
    private HealthTextView bs;
    private TrackVoiceSettingsView bt;
    private ImageView bv;
    private HealthImageView c;
    private HealthTextView d;
    private LinearLayout e;
    private guz f;
    private HealthDivider i;
    private Context j;
    private CustomViewDialog n;
    private CustomTitleBar o;
    private HealthRadioButton s;
    private String t;
    private RadioGroup v;
    private String w;
    private HealthRadioButton x;
    private LinearLayout y;
    private int as = 0;
    private RelativeLayout aj = null;
    private RelativeLayout ak = null;
    private HealthTextView ap = null;
    private ImageView aa = null;
    private ImageView ai = null;
    private HealthDivider ah = null;
    private HealthDivider z = null;
    private HealthDivider ad = null;
    private HealthDivider af = null;
    private HealthDivider ab = null;
    private HealthDivider ac = null;
    private HealthDivider ae = null;
    private RelativeLayout am = null;
    private HealthSwitchButton bq = null;
    private Dialog l = null;
    private int h = 1;
    private RelativeLayout aq = null;
    private RelativeLayout ar = null;
    private ImageView r = null;
    private HealthTextView p = null;
    private HealthDivider k = null;
    private ImageView q = null;
    private LinearLayout al = null;
    private int bw = -1;
    private float bu = -1.0f;
    private boolean ag = false;
    private Activity b = null;
    private Handler m = new Handler() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.a("Track_SportSettingsFragment", "msg is null");
                return;
            }
            super.handleMessage(message);
            SportAssistSettingsActivity.this.bw = message.arg1;
            if (message.obj instanceof Float) {
                SportAssistSettingsActivity.this.bu = ((Float) message.obj).floatValue();
            }
            SportAssistSettingsActivity sportAssistSettingsActivity = SportAssistSettingsActivity.this;
            sportAssistSettingsActivity.a(sportAssistSettingsActivity.bw, SportAssistSettingsActivity.this.bu, true, true);
            LogUtil.a("Track_SportSettingsFragment", " targetType = ", Integer.valueOf(SportAssistSettingsActivity.this.bw));
        }
    };
    private String bn = "";
    private int bd = 0;
    private UiCallback u = new UiCallback<String>() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.15
        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            if (!TextUtils.isEmpty(str)) {
                SportAssistSettingsActivity.this.h = CommonUtil.e(str, 1);
                LogUtil.a("Track_SportSettingsFragment", "get coach gender success");
            }
            SportAssistSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.15.1
                @Override // java.lang.Runnable
                public void run() {
                    SportAssistSettingsActivity.this.b(SportAssistSettingsActivity.this.h);
                    if (SportAssistSettingsActivity.this.l != null) {
                        SportAssistSettingsActivity.this.a(SportAssistSettingsActivity.this.h);
                    }
                }
            });
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            int a2 = ggs.a();
            LogUtil.h("Track_SportSettingsFragment", "failed get coach gender errorInfo: ", str);
            SportAssistSettingsActivity.this.h = a2;
            SportAssistSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.15.4
                @Override // java.lang.Runnable
                public void run() {
                    SportAssistSettingsActivity.this.b(SportAssistSettingsActivity.this.h);
                    if (SportAssistSettingsActivity.this.l != null) {
                        SportAssistSettingsActivity.this.a(SportAssistSettingsActivity.this.h);
                    }
                }
            });
        }
    };
    private UiCallback g = new UiCallback<Boolean>() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.13
        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Track_SportSettingsFragment", "confirmClearCache delUseCache onFailure errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Boolean bool) {
            LogUtil.a("Track_SportSettingsFragment", "delCourseUseCache");
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                LogUtil.h("Track_SportSettingsFragment", "confirmClearCache courseApi == null");
            } else {
                courseApi.updateCourseDataState();
                ggs.e();
            }
        }
    };
    private UiCallback bp = new UiCallback<Map<String, String>>() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.11
        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Track_SportSettingsFragment", "mSwitchUiCallback onFailure");
            SportAssistSettingsActivity.this.al();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<String, String> map) {
            LogUtil.a("Track_SportSettingsFragment", "mSwitchUiCallback onSuccess");
            if (map == null) {
                LogUtil.h("Track_SportSettingsFragment", "onSuccess data == null ");
                return;
            }
            SportAssistSettingsActivity.this.bo = map.get("sport_remind_switch_status");
            LogUtil.a("Track_SportSettingsFragment", "mSwitchStatus ", SportAssistSettingsActivity.this.bo);
            SportAssistSettingsActivity.this.al();
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_SportSettingsFragment", "onCreate");
        setContentView(R.layout.track_sport_settings_frag);
        this.j = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.bi = intent.getIntExtra("currentSportType", 0);
            this.an = intent.getBooleanExtra("enterSportSetting", false);
            s();
        }
        this.b = this;
        t();
        osl.e((UiCallback<Map<String, String>>) this.bp);
        ao();
        v();
        this.bt = new TrackVoiceSettingsView(this.j, this.bi, this.an, this.b);
        z();
    }

    private void s() {
        if (this.j == null) {
            return;
        }
        ReleaseLogUtil.e("Track_SportSettingsFragment", "initCurrentSportType mSportType ", Integer.valueOf(this.bi));
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        int i = this.bi;
        if (i == 264) {
            this.bn = getString(R.string.IDS_start_track_sport_type_indoor_run);
            hashMap.put("type", 2);
        } else if (i == 10001) {
            this.bn = getString(R.string._2130842980_res_0x7f021564);
            hashMap.put("type", 3);
        } else if (i == 282) {
            this.bn = getString(R.string.IDS_hwh_sport_type_hiking);
            hashMap.put("type", 7);
        } else if (i != 283) {
            switch (i) {
                case 257:
                    this.bn = getString(R.string._2130842252_res_0x7f02128c);
                    hashMap.put("type", 4);
                    break;
                case 258:
                    this.bn = getString(R.string.IDS_start_track_sport_type_outdoor_run);
                    hashMap.put("type", 1);
                    break;
                case 259:
                    this.bn = getString(R.string._2130842145_res_0x7f021221);
                    hashMap.put("type", 5);
                    break;
                case 260:
                    this.bn = getString(R.string.IDS_motiontrack_climb_hill_tip);
                    hashMap.put("type", 8);
                    break;
                default:
                    this.bn = getString(R.string.IDS_start_track_sport_type_outdoor_run);
                    hashMap.put("type", 1);
                    break;
            }
        } else {
            this.bn = getString(R.string.IDS_indoor_skipper_rope_sport_type);
        }
        if (this.an) {
            hashMap.put("type", 6);
        }
        e(hashMap);
    }

    private void ab() {
        this.bh = (HealthTextView) findViewById(R.id.target_explain);
        if (LanguageUtil.j(BaseApplication.getContext())) {
            this.bh.setText(this.j.getString(R.string._2130844057_res_0x7f021999, UnitUtil.e(5.2d, 1, 2), UnitUtil.e(11.11d, 1, 2), UnitUtil.e(13.14d, 1, 2)));
            this.bh.setVisibility(0);
            return;
        }
        this.bh.setVisibility(8);
    }

    private void t() {
        this.y = (LinearLayout) findViewById(R.id.hw_show_userinfo_gender_layout);
        this.ac = (HealthDivider) findViewById(R.id.img_noun_explain_bottom_line);
        this.ae = (HealthDivider) findViewById(R.id.img_pause_trackline_type_bottom_line);
        this.al = (LinearLayout) findViewById(R.id.layout_pause_trackline_type_setting);
        this.aw = (ImageView) findViewById(R.id.img_pause_trackline_type_setting);
        if (this.bi != 10001) {
            this.y.setVisibility(8);
            this.ae.setVisibility(8);
        } else {
            HealthScrollView healthScrollView = (HealthScrollView) findViewById(R.id.sport_setting_scroll);
            this.av = healthScrollView;
            healthScrollView.setOverScrollable(false);
        }
        k();
        this.be = (HealthTextView) findViewById(R.id.txt_track_sport_type_target);
        this.bj = (HealthTextView) findViewById(R.id.txt_track_sport_target_interval_value);
        this.bm = (HealthTextView) findViewById(R.id.txt_track_sport_target_interval_unit);
        this.bl = (RelativeLayout) findViewById(R.id.layout_track_sport_target_setting);
        ab();
        this.bk = (ImageView) findViewById(R.id.img_track_target_interval_image);
        this.ap = (HealthTextView) findViewById(R.id.txt_pause_trackline_type_value);
        this.o = (CustomTitleBar) findViewById(R.id.titlebar_track_sport_setting);
        this.aj = (RelativeLayout) findViewById(R.id.layout_track_voice_heart_setting);
        this.aa = (ImageView) findViewById(R.id.img_track_heart_interval_value);
        this.ad = (HealthDivider) findViewById(R.id.img_track_interval_pace_bottom_line);
        this.af = (HealthDivider) findViewById(R.id.img_track_personal_pace_bottom_line);
        this.ab = (HealthDivider) findViewById(R.id.img_auto_pause_bottom_line);
        this.bq = (HealthSwitchButton) findViewById(R.id.switch_track_autopause);
        this.am = (RelativeLayout) findViewById(R.id.layout_auto_pause);
        if ("1".equals(KeyValDbManager.b(this.j).e("SUPPORT_AR_ABILITY"))) {
            this.am.setVisibility(0);
            this.ad.setVisibility(0);
        } else {
            this.am.setVisibility(8);
            this.ad.setVisibility(8);
        }
        q();
        this.aq = (RelativeLayout) findViewById(R.id.layout_sport_noun_explain);
        this.az = (ImageView) findViewById(R.id.img_sport_noun_explain);
        x();
        o();
        l();
        u();
        y();
        w();
    }

    private void q() {
        this.ar = (RelativeLayout) findViewById(R.id.layout_track_free_indoor);
        this.p = (HealthTextView) this.b.findViewById(R.id.txt_track_free_indoor_minor_value);
        this.r = (ImageView) this.b.findViewById(R.id.free_indoor);
        this.k = (HealthDivider) this.b.findViewById(R.id.layout_track_voice_heart_setting_labs_line);
        this.q = (ImageView) this.b.findViewById(R.id.free_indoor_red_point);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.r.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.r.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.ar.setOnClickListener(new View.OnClickListener() { // from class: opr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportAssistSettingsActivity.this.dfi_(view);
            }
        });
    }

    public /* synthetic */ void dfi_(View view) {
        ae();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void k() {
        int i = this.bi;
        if (i == 264 || i == 137 || i == 10001 || ac() || this.bi == 283) {
            this.ae.setVisibility(8);
            this.ac.setVisibility(8);
            this.al.setVisibility(8);
            this.aw.setVisibility(8);
            LogUtil.a("Track_SportSettingsFragment", "initLayout mSportType no LayoutPauseTracklineTypeSetting");
        }
        if (this.bi == 10001) {
            this.ac.setVisibility(0);
        }
    }

    private void w() {
        this.y.setOnClickListener(this);
        this.al.setOnClickListener(this);
        this.bl.setOnClickListener(this);
        this.aj.setOnClickListener(this);
        this.o.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportAssistSettingsActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.bq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.16
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SportAssistSettingsActivity.this.ag = z;
                HashMap hashMap = new HashMap(10);
                if (compoundButton != null) {
                    compoundButton.setChecked(SportAssistSettingsActivity.this.ag);
                }
                hashMap.put("click", 1);
                if (SportAssistSettingsActivity.this.ag) {
                    owp.d(BaseApplication.getContext(), true);
                    hashMap.put("type", 4);
                } else {
                    owp.d(BaseApplication.getContext(), false);
                    hashMap.put("type", 3);
                }
                SportAssistSettingsActivity.this.a(hashMap);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        this.aq.setOnClickListener(this);
    }

    private void y() {
        this.au = (HealthDivider) findViewById(R.id.img_rope_skip_process_top_line);
        this.ao = (RelativeLayout) findViewById(R.id.layout_display_rope_skip_process);
        this.at = (HealthTextView) findViewById(R.id.rope_skip_process_tips);
        this.br = (HealthSwitchButton) findViewById(R.id.switch_track_display_rope_skip_process);
        if (this.bi == 283) {
            this.au.setVisibility(0);
            this.ao.setVisibility(0);
            if (nsn.ae(this.j)) {
                this.at.setText(R.string._2130845983_res_0x7f02211f);
            } else {
                this.at.setText(R.string._2130844949_res_0x7f021d15);
            }
            String b = ash.b("ROPE_DISPLAY_PROCESS");
            if (TextUtils.isEmpty(b)) {
                this.br.setChecked(false);
            } else {
                this.br.setChecked("true".equals(b));
            }
        } else {
            this.au.setVisibility(8);
            this.ao.setVisibility(8);
        }
        this.br.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.17
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SportAssistSettingsActivity.this.br.setChecked(z);
                ash.a("ROPE_DISPLAY_PROCESS", String.valueOf(z));
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    private void x() {
        this.bf = (HealthDivider) findViewById(R.id.layout_track_sport_remind_top_divider);
        this.bb = (HealthDivider) findViewById(R.id.layout_track_sport_remind_bottom_divider);
        if (aa() || ac()) {
            this.bf.setVisibility(8);
            this.bb.setVisibility(0);
        } else if (this.an) {
            this.bf.setVisibility(8);
            this.bb.setVisibility(8);
        } else {
            this.bf.setVisibility(0);
            this.bb.setVisibility(8);
        }
        this.ay = (LinearLayout) findViewById(R.id.layout_track_sport_remind);
        this.bg = (HealthTextView) findViewById(R.id.txt_track_sport_remind_value);
        this.ba = (ImageView) findViewById(R.id.img_track_sport_remind_value);
        this.ay.setOnClickListener(this);
    }

    private void o() {
        this.e = (LinearLayout) findViewById(R.id.cl_auto_identify_record);
        this.i = (HealthDivider) findViewById(R.id.layout_track_auto_identify_top_divider);
        this.e.setOnClickListener(this);
        int i = (!ory.d(this.j, gso.e().d(this.j)) || Utils.o()) ? 8 : 0;
        this.e.setVisibility(i);
        this.i.setVisibility(i);
        this.f9517a = (HealthImageView) findViewById(R.id.hiv_red_point);
        this.d = (HealthTextView) findViewById(R.id.tv_auto_identify_record_right_text);
        this.c = (HealthImageView) findViewById(R.id.iv_auto_identify_record_arrow);
    }

    private void u() {
        if (SportSupportUtil.j(this.bi)) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add("900200033");
            njh.e(arrayList, new IBaseResponseCallback() { // from class: opm
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SportAssistSettingsActivity.this.a(i, obj);
                }
            }, "0");
        }
    }

    public /* synthetic */ void a(int i, Object obj) {
        String str;
        if (isFinishing()) {
            ReleaseLogUtil.e("Track_SportSettingsFragment", "initReserveLink failed with activity is finishing");
            return;
        }
        if (obj instanceof HashMap) {
            str = (String) ((HashMap) obj).get("900200033");
            ReleaseLogUtil.e("Track_SportSettingsFragment", "initReserveLink with config:", str);
        } else {
            ReleaseLogUtil.e("Track_SportSettingsFragment", "initReserveLink with empty config");
            str = null;
        }
        final boolean equals = "1".equals(str);
        HandlerExecutor.a(new Runnable() { // from class: opl
            @Override // java.lang.Runnable
            public final void run() {
                SportAssistSettingsActivity.this.c(equals);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(boolean z) {
        SportSettingSwitchItem sportSettingSwitchItem = (SportSettingSwitchItem) findViewById(R.id.layout_mirror_link);
        HealthDivider healthDivider = (HealthDivider) findViewById(R.id.layout_mirror_link_top_divider);
        sportSettingSwitchItem.setTitleText(R.string._2130847212_res_0x7f0225ec);
        sportSettingSwitchItem.setTipsTxt(R.string._2130847213_res_0x7f0225ed);
        sportSettingSwitchItem.setChecked(z);
        sportSettingSwitchItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity$$ExternalSyntheticLambda5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                SportAssistSettingsActivity.dfg_(compoundButton, z2);
            }
        });
        healthDivider.setVisibility(0);
        sportSettingSwitchItem.setVisibility(0);
    }

    static /* synthetic */ void dfg_(CompoundButton compoundButton, boolean z) {
        ReleaseLogUtil.e("Track_SportSettingsFragment", "showRideReserveLinkSwitchView is change:", Boolean.valueOf(z));
        njh.b("900200033", z);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void l() {
        if (ag()) {
            gzl.b(new AnonymousClass18());
        }
    }

    /* renamed from: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity$18, reason: invalid class name */
    public class AnonymousClass18 implements IBaseResponseCallback {
        AnonymousClass18() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Track_SportSettingsFragment", "onResult errorCode: ", Integer.valueOf(i), ", data: ", obj);
            if (SportAssistSettingsActivity.this.isFinishing()) {
                ReleaseLogUtil.e("Track_SportSettingsFragment", "SportAssistSettingsActivity is finishing");
                return;
            }
            if (obj instanceof enh) {
                final enh enhVar = (enh) obj;
                final boolean h = RunningRouteUtils.h();
                if (h) {
                    RunningRouteUtils.f();
                }
                HandlerExecutor.a(new Runnable() { // from class: opq
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportAssistSettingsActivity.AnonymousClass18.this.c(enhVar, h);
                    }
                });
            }
        }

        public /* synthetic */ void c(enh enhVar, boolean z) {
            SportAssistSettingsActivity.this.a(enhVar, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final enh enhVar, boolean z) {
        final SportSettingSwitchItem sportSettingSwitchItem = (SportSettingSwitchItem) findViewById(R.id.layout_auto_punch);
        HealthDivider healthDivider = (HealthDivider) findViewById(R.id.layout_track_auto_punch_top_divider);
        sportSettingSwitchItem.setTitleText(R.string._2130847824_res_0x7f022850);
        sportSettingSwitchItem.setTipsTxt(R.string._2130847818_res_0x7f02284a);
        sportSettingSwitchItem.setRedPointShow(z);
        sportSettingSwitchItem.setChecked(!"0".equals(enhVar.c()));
        sportSettingSwitchItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity$$ExternalSyntheticLambda8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                SportAssistSettingsActivity.dff_(SportSettingSwitchItem.this, enhVar, compoundButton, z2);
            }
        });
        healthDivider.setVisibility(0);
        sportSettingSwitchItem.setVisibility(0);
    }

    static /* synthetic */ void dff_(SportSettingSwitchItem sportSettingSwitchItem, enh enhVar, CompoundButton compoundButton, boolean z) {
        sportSettingSwitchItem.setRedPointShow(false);
        if (TextUtils.isEmpty(enhVar.a())) {
            enhVar.e(z);
        }
        enhVar.c(z);
        gzl.b(enhVar);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private boolean ag() {
        if (Utils.o()) {
            return false;
        }
        return RunningRouteUtils.a(this.bi);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        LogUtil.a("Track_SportSettingsFragment", "readSportRemindValueAndSetValue ", this.bo);
        if (TextUtils.isEmpty(this.bo)) {
            String b = SharedPreferenceManager.b(this.j, Integer.toString(20002), "sport_remind_is_opened");
            LogUtil.a("Track_SportSettingsFragment", "value ", b);
            if (!TextUtils.isEmpty(b)) {
                this.bd = CommonUtils.h(b);
            }
        } else {
            this.bd = CommonUtils.h(this.bo);
        }
        LogUtil.a("Track_SportSettingsFragment", "mSportRemindValue ", Integer.valueOf(this.bd));
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.20
            @Override // java.lang.Runnable
            public void run() {
                if (SportAssistSettingsActivity.this.bd == 0) {
                    SportAssistSettingsActivity.this.bg.setText(SportAssistSettingsActivity.this.getResources().getString(R.string._2130844048_res_0x7f021990));
                } else {
                    SportAssistSettingsActivity.this.bg.setText(SportAssistSettingsActivity.this.getResources().getString(R.string._2130844049_res_0x7f021991));
                }
            }
        });
    }

    private void v() {
        this.ah = (HealthDivider) findViewById(R.id.img_track_voice_bottom_line);
        this.z = (HealthDivider) findViewById(R.id.layout_track_voice_heart_setting_line);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout_track_pace_interval);
        this.ak = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.ai = (ImageView) findViewById(R.id.img_track_pace_interval_value);
    }

    private void z() {
        aj();
        int i = this.bi;
        if (i == 258 || i == 264) {
            this.ak.setVisibility(0);
            this.ah.setVisibility(0);
            if (!this.an) {
                SharedPreferenceManager.e(this.j, Integer.toString(20002), "MOTION_PACE_RANGE_SETTING_RED_POINT_CLICK", "true", new StorageParams());
            }
        } else {
            this.ak.setVisibility(8);
            this.ah.setVisibility(8);
        }
        ad();
        am();
        if (!LanguageUtil.j(BaseApplication.getContext()) && !this.an) {
            this.aj.setVisibility(8);
            this.ah.setVisibility(8);
        }
        if ((this.aj.getVisibility() == 0 || this.ak.getVisibility() == 0) && !aa() && !ac()) {
            this.z.setVisibility(0);
        } else {
            this.z.setVisibility(8);
        }
        this.bs = (HealthTextView) findViewById(R.id.hw_show_userinfo_gender);
        this.bv = (ImageView) findViewById(R.id.user_info_fragment_set_gender_image);
        if (LanguageUtil.bc(this.j)) {
            this.bv.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.bv.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        if (aa() && !this.an) {
            ash.a("COACH_GENDER_SETTING_ENTRANCE_RED_POINT_CLICK", "true");
        }
        this.w = getResources().getString(R.string._2130837640_res_0x7f020088);
        this.t = getResources().getString(R.string._2130837639_res_0x7f020087);
        n();
    }

    private void aj() {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.aa.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ai.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.bk.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.az.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.aw.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ba.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.c.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            return;
        }
        this.aa.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.ai.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.bk.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.az.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.aw.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.ba.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.c.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
    }

    private void n() {
        int i = this.bi;
        boolean z = i == 264 || i == 258;
        LogUtil.a("Track_SportSettingsFragment", "initCourseEndConfigLayout isRunningSportType: ", Boolean.valueOf(z));
        if (z) {
            SportSettingSwitchItem sportSettingSwitchItem = (SportSettingSwitchItem) findViewById(R.id.layout_course_end_config);
            sportSettingSwitchItem.setVisibility(0);
            sportSettingSwitchItem.setTitleText(R.string._2130845411_res_0x7f021ee3);
            sportSettingSwitchItem.setChecked(f());
            sportSettingSwitchItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.2
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    SportAssistSettingsActivity.this.b(z2);
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            });
            findViewById(R.id.divide_line_course_end_config).setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        car.d(this.j, z);
    }

    private boolean f() {
        return car.a(this.j);
    }

    private void ad() {
        this.bw = owp.e(this.j, this.bi);
        float a2 = owp.a(this.j, this.bi);
        this.bu = a2;
        a(this.bw, a2, false, false);
        r();
        this.be.setText(String.format(getString(R.string._2130843254_res_0x7f021676), this.bn));
        if (LanguageUtil.bj(this)) {
            this.be.setTextSize(1, 9.0f);
            this.bj.setTextSize(1, 9.0f);
            this.bm.setTextSize(1, 9.0f);
        }
        if (aa() || ac() || this.an) {
            this.bl.setVisibility(8);
            this.bh.setVisibility(8);
            this.z.setVisibility(8);
            this.am.setVisibility(8);
            this.ad.setVisibility(8);
        } else if (ai()) {
            this.aj.setVisibility(8);
            this.bh.setVisibility(8);
            this.am.setVisibility(8);
            this.ad.setVisibility(8);
            this.aq.setVisibility(8);
        }
        if (this.an) {
            this.ak.setVisibility(0);
            this.ah.setVisibility(0);
            this.aq.setVisibility(8);
            this.al.setVisibility(8);
            this.ae.setVisibility(8);
            this.ac.setVisibility(8);
            this.af.setVisibility(8);
            this.ab.setVisibility(8);
            LogUtil.a("Track_SportSettingsFragment", "mIsPersonalEnterActivity enter enter");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.c("Track_SportSettingsFragment", "onResume");
        m();
        j();
        ao();
        this.bt.a();
        String b = SharedPreferenceManager.b(this.j, Integer.toString(20002), "sport_remind_is_opened");
        this.bo = b;
        LogUtil.a("Track_SportSettingsFragment", "Activity Result switch= ", b);
        al();
        if (!SportSupportUtil.a() || this.an) {
            return;
        }
        a("custom.UserPreference_coach_gender_Flag", (UiCallback<String>) this.u);
        guw.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ao() {
        int e = e();
        this.as = e;
        if (e == 0) {
            this.ap.setText(R.string._2130844506_res_0x7f021b5a);
        } else {
            this.ap.setText(R.string._2130844507_res_0x7f021b5b);
        }
    }

    public int e() {
        String b = SharedPreferenceManager.b(this.j, Integer.toString(20002), "pause_trackline_type");
        if (TextUtils.isEmpty(b)) {
            return 0;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.b("Track_SportSettingsFragment", "acquirePauseTracklineType NumberFormatException", LogAnonymous.b((Throwable) e));
            return 0;
        }
    }

    public void e(int i) {
        SharedPreferenceManager.e(this.j, Integer.toString(20002), "pause_trackline_type", String.valueOf(i), new StorageParams());
    }

    private void am() {
        if (CommonUtil.aw() || CommonUtil.bf()) {
            if (owp.c(BaseApplication.getContext())) {
                this.ag = true;
            } else {
                this.ag = false;
            }
            this.bq.setChecked(this.ag);
            return;
        }
        this.am.setVisibility(8);
        this.ad.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map map) {
        if (map != null) {
            ixx.d().d(this, AnalyticsValue.MOTION_TRACK_1040023.value(), map, 0);
        } else {
            LogUtil.h("Track_SportSettingsFragment", "map is null");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(500)) {
            LogUtil.b("Track_SportSettingsFragment", "is click fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        if (view.getId() == R.id.layout_track_voice_heart_setting) {
            i();
            hashMap.put("type", 9);
        }
        if (view.getId() == R.id.layout_track_pace_interval) {
            c(hashMap);
        }
        if (view.getId() == R.id.layout_sport_noun_explain) {
            caj.a().e();
            hashMap.put("type", 12);
        }
        if (view.getId() == R.id.layout_pause_trackline_type_setting) {
            LogUtil.a("Track_SportSettingsFragment", "onClick showPauseTracklineTypeSetting()");
            a();
            hashMap.put("type", 18);
        }
        if (view.getId() == R.id.layout_track_sport_target_setting) {
            if (ai()) {
                new RopeSkippingTargetDialog(this).dfR_(283, this.m);
            } else {
                c();
                hashMap.put("type", 14);
            }
        }
        if (view.getId() == R.id.hw_show_userinfo_gender_layout) {
            af();
        }
        if (view.getId() == R.id.layout_track_sport_remind) {
            ah();
        }
        if (view.getId() == R.id.cl_auto_identify_record) {
            h();
        } else {
            LogUtil.h("Track_SportSettingsFragment", "wrong view id");
        }
        a(hashMap);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        Intent intent = new Intent(this, (Class<?>) AutoIdentifyRecordActivity.class);
        int i = this.bi;
        if (i == 257) {
            intent.putExtra("auto_track_launch_source", 4);
        } else if (i == 258) {
            intent.putExtra("auto_track_launch_source", 2);
        } else if (i == 264) {
            intent.putExtra("auto_track_launch_source", 3);
        } else {
            LogUtil.h("Track_SportSettingsFragment", "enterAutoIdentifyRecordActivity switch default");
        }
        startActivity(intent);
    }

    private void af() {
        ash.a("COACH_GENDER_SETTING_RED_POINT_CLICK", "true");
        an();
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.j, AnalyticsValue.HEALTH_FITNESS_GENDER_CLICK_1130039.value(), hashMap, 0);
    }

    private void ah() {
        Intent intent = new Intent(this, (Class<?>) SportRemindSettingsActivity.class);
        intent.putExtra("sport_remind_value", this.bd);
        startActivity(intent);
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.j, AnalyticsValue.REMIND_SPORT_CLICK_SPORT_REMIND_ITEM_COUNT.value(), hashMap, 0);
    }

    private void ae() {
        if (nsn.a(500)) {
            LogUtil.b("Track_SportSettingsFragment", "is click fast");
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_MINE_FREE_INDOOR_RUNNING_2040073.value(), hashMap, 0);
        startActivity(new Intent(this, (Class<?>) FreeIndoorRunningActivity.class));
        new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002)).c(false);
    }

    private void i() {
        LogUtil.a("Track_SportSettingsFragment", "gotoHeartRateSettingH5");
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        H5ProServiceManager.getInstance().registerService(CustomDataService.class);
        bzs.e().loadH5ProApp(this.j, "com.huawei.health.h5.setting", builder);
    }

    private void c(Map<String, Object> map) {
        if (map == null) {
            LogUtil.h("Track_SportSettingsFragment", "startPaceRangeShowActivity map null");
            return;
        }
        startActivity(new Intent(this, (Class<?>) PaceRangeShowActivity.class));
        SharedPreferenceManager.e(this.j, Integer.toString(20002), "PACE_RANGE_SETTING_RED_POINT_CLICK", "true", new StorageParams());
        map.put("type", 15);
    }

    private void e(Map map) {
        if (map != null) {
            ixx.d().d(this, AnalyticsValue.MOTION_TRACK_1040066.value(), map, 0);
        } else {
            LogUtil.h("Track_SportSettingsFragment", "map is null");
        }
    }

    private void an() {
        LogUtil.a("Track_SportSettingsFragment", "showGenderPickerDialog");
        View inflate = ((LayoutInflater) this.j.getSystemService("layout_inflater")).inflate(R.layout.dialog_settings_gender_view, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.j);
        builder.a(getString(R.string._2130837629_res_0x7f02007d)).czg_(inflate).czc_(R.string._2130837555_res_0x7f020033, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_SportSettingsFragment", "CANCELED");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.l = builder.e();
        if (!dfe_(inflate)) {
            this.l = null;
        } else {
            this.l.show();
        }
    }

    private void d() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_SportSettingsFragment", "confirmClearCache courseApi == null");
        } else {
            courseApi.delCourseUseCache(this.g);
        }
    }

    private boolean dfe_(View view) {
        LogUtil.a("Track_SportSettingsFragment", "initializeGenderDialogLayout()");
        if (view == null) {
            return false;
        }
        this.x = (HealthRadioButton) view.findViewById(R.id.settings_radio_gender_male);
        this.s = (HealthRadioButton) view.findViewById(R.id.settings_radio_gender_female);
        this.bc = (RelativeLayout) view.findViewById(R.id.settings_gender_view_male);
        this.ax = (RelativeLayout) view.findViewById(R.id.settings_gender_view_female);
        this.bc.setOnClickListener(new View.OnClickListener() { // from class: opk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportAssistSettingsActivity.this.dfj_(view2);
            }
        });
        this.ax.setOnClickListener(new View.OnClickListener() { // from class: opo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportAssistSettingsActivity.this.dfk_(view2);
            }
        });
        a(this.h);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.layout_radio_group);
        this.v = radioGroup;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.3
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup2, int i) {
                SportAssistSettingsActivity.this.g();
                if (i == R.id.settings_radio_gender_male) {
                    if (SportAssistSettingsActivity.this.h != 0) {
                        SportAssistSettingsActivity.this.c(0);
                    }
                } else if (SportAssistSettingsActivity.this.h != 1) {
                    SportAssistSettingsActivity.this.c(1);
                }
                ViewClickInstrumentation.clickOnRadioGroup(radioGroup2, i);
            }
        });
        a("custom.UserPreference_coach_gender_Flag", (UiCallback<String>) this.u);
        return true;
    }

    public /* synthetic */ void dfj_(View view) {
        g();
        if (this.h != 0) {
            c(0);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dfk_(View view) {
        g();
        if (this.h != 1) {
            c(1);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("coachGenderType", Integer.valueOf(i));
        ixx.d().d(this.j, AnalyticsValue.HEALTH_FITNESS_GENDER_SELECT_1130040.value(), hashMap, 0);
    }

    private static void b(final String str, final int i) {
        jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.1
            @Override // java.lang.Runnable
            public void run() {
                HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference(str, String.valueOf(i)), true);
                HiSyncOption hiSyncOption = new HiSyncOption();
                hiSyncOption.setSyncModel(2);
                hiSyncOption.setSyncAction(0);
                hiSyncOption.setSyncDataType(20000);
                hiSyncOption.setSyncScope(1);
                hiSyncOption.setSyncMethod(2);
                HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
            }
        });
    }

    private static void a(final String str, final UiCallback<String> uiCallback) {
        if (str == null) {
            LogUtil.b("Track_SportSettingsFragment", "getCoachGenderFromDb key == null");
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.8
                @Override // java.lang.Runnable
                public void run() {
                    HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
                    if (userPreference == null) {
                        uiCallback.onFailure(0, "getCoachGenderFromDb userPreference is null");
                    } else if (TextUtils.isEmpty(userPreference.getValue())) {
                        uiCallback.onFailure(0, "getCoachGenderFromDb userinfo is null");
                    } else {
                        uiCallback.onSuccess(userPreference.getValue());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Dialog dialog = this.l;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        a(i);
        ash.a("coachGenderConfig", String.valueOf(i));
        this.h = i;
        b("custom.UserPreference_coach_gender_Flag", i);
        d();
        b(i);
        d(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.a("Track_SportSettingsFragment", "refreshGenderImageView");
        if (i == 0) {
            this.x.setChecked(true);
            this.s.setChecked(false);
        } else if (i == 1) {
            this.x.setChecked(false);
            this.s.setChecked(true);
        } else {
            this.x.setChecked(false);
            this.s.setChecked(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        LogUtil.a("Track_SportSettingsFragment", "refreshGenderText");
        if (i == 0) {
            this.bs.setText(this.w);
        } else if (i == 1) {
            this.bs.setText(this.t);
        } else {
            this.bs.setText(this.t);
        }
        this.bs.setTextColor(this.j.getResources().getColor(R.color._2131297373_res_0x7f09045d));
    }

    private int c(String str, String[] strArr) {
        if (strArr == null) {
            return 0;
        }
        int length = strArr.length;
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return length;
    }

    public void a() {
        View inflate = getLayoutInflater().inflate(R.layout.pause_trackline_type_setting_dialog, (ViewGroup) null);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.pause_trackline_type_picker);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(this.j.getResources().getString(R.string._2130844506_res_0x7f021b5a));
        arrayList.add(this.j.getResources().getString(R.string._2130844507_res_0x7f021b5b));
        healthNumberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[arrayList.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(arrayList.size() - 1);
        if (e() == 0) {
            int c = c((String) arrayList.get(0), (String[]) arrayList.toArray(new String[arrayList.size()]));
            LogUtil.a("Track_SportSettingsFragment", "selectIndex healthNumberPicker :", Integer.valueOf(c));
            healthNumberPicker.setValue(c);
        } else {
            int c2 = c((String) arrayList.get(1), (String[]) arrayList.toArray(new String[arrayList.size()]));
            LogUtil.a("Track_SportSettingsFragment", "selectIndex healthNumberPicker else :", Integer.valueOf(c2));
            healthNumberPicker.setValue(c2);
        }
        dfh_(inflate, healthNumberPicker);
    }

    private void dfh_(View view, final HealthNumberPicker healthNumberPicker) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.a(getString(R.string._2130841779_res_0x7f0210b3)).czg_(view).cze_(R.string._2130837648_res_0x7f020090, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LogUtil.a("Track_SportSettingsFragment", "showPauseTracklineTypeSetting confirm：", Integer.valueOf(healthNumberPicker.getValue()));
                HashMap hashMap = new HashMap(10);
                hashMap.put("click", 1);
                if (healthNumberPicker.getValue() == 0) {
                    SportAssistSettingsActivity.this.as = 0;
                    hashMap.put("type", 0);
                } else if (healthNumberPicker.getValue() == 1) {
                    SportAssistSettingsActivity.this.as = 1;
                    hashMap.put("type", 1);
                } else {
                    LogUtil.a("Track_SportSettingsFragment", "showPauseTracklineTypeSetting：", Integer.valueOf(healthNumberPicker.getValue()));
                }
                SportAssistSettingsActivity sportAssistSettingsActivity = SportAssistSettingsActivity.this;
                sportAssistSettingsActivity.e(sportAssistSettingsActivity.as);
                ixx.d().d(SportAssistSettingsActivity.this.j, AnalyticsValue.PAUSE_LINE_TYPE_CLICK_1130050.value(), hashMap, 0);
                SportAssistSettingsActivity.this.ao();
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czc_(R.string._2130837555_res_0x7f020033, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LogUtil.a("Track_SportSettingsFragment", "showPauseTracklineTypeSetting cancel：", Integer.valueOf(healthNumberPicker.getValue()));
                SportAssistSettingsActivity sportAssistSettingsActivity = SportAssistSettingsActivity.this;
                sportAssistSettingsActivity.e(sportAssistSettingsActivity.e());
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        builder.e().show();
    }

    public void c() {
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        TargetChoicePickerUtils targetChoicePickerUtils = new TargetChoicePickerUtils(BaseApplication.getContext());
        final List<String> a2 = targetChoicePickerUtils.a();
        final Map<String, ArrayList<String>> b = targetChoicePickerUtils.b(healthMultiNumberPicker, this.bi);
        healthMultiNumberPicker.setPickerCount(2, new boolean[]{false, false});
        healthMultiNumberPicker.setDisplayedValues(0, (String[]) a2.toArray(new String[a2.size()]), 0);
        int d = targetChoicePickerUtils.d(this.bw);
        int a3 = targetChoicePickerUtils.a(this.bw, this.bu);
        if (koq.b(a2, d)) {
            LogUtil.h("Track_SportSettingsFragment", "showTargetChoiceWheelPickerDialog firstLocation is out of keyList.");
            return;
        }
        ArrayList<String> arrayList = b.get(a2.get(d));
        healthMultiNumberPicker.setDisplayedValues(1, (String[]) arrayList.toArray(new String[arrayList.size()]), 2);
        healthMultiNumberPicker.a(new int[]{d, a3}, arrayList.size());
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.9
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker2, int i2, int i3) {
                if (i == 0) {
                    if (i3 >= a2.size() || !b.containsKey(a2.get(i3))) {
                        LogUtil.h("Track_SportSettingsFragment", "the sport target is not valid");
                    } else {
                        ArrayList arrayList2 = (ArrayList) b.get(a2.get(i3));
                        healthMultiNumberPicker2.setDisplayedValues(1, (String[]) arrayList2.toArray(new String[arrayList2.size()]), 2);
                    }
                }
            }
        });
        d(healthMultiNumberPicker, targetChoicePickerUtils);
    }

    private void d(final HealthMultiNumberPicker healthMultiNumberPicker, final TargetChoicePickerUtils targetChoicePickerUtils) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.j);
        builder.d(R.string._2130841787_res_0x7f0210bb).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                SportAssistSettingsActivity.this.bw = targetChoicePickerUtils.c(selectedLocations[0]);
                SportAssistSettingsActivity sportAssistSettingsActivity = SportAssistSettingsActivity.this;
                sportAssistSettingsActivity.bu = targetChoicePickerUtils.e(selectedLocations[1], sportAssistSettingsActivity.bw);
                if (selectedLocations[0] != 0 && selectedLocations[1] == 0) {
                    SportAssistSettingsActivity sportAssistSettingsActivity2 = SportAssistSettingsActivity.this;
                    sportAssistSettingsActivity2.a(sportAssistSettingsActivity2.j, SportAssistSettingsActivity.this.bw);
                } else {
                    SportAssistSettingsActivity sportAssistSettingsActivity3 = SportAssistSettingsActivity.this;
                    sportAssistSettingsActivity3.a(sportAssistSettingsActivity3.bw, SportAssistSettingsActivity.this.bu, true, false);
                    HashMap hashMap = new HashMap(2);
                    hashMap.put("click", 1);
                    hashMap.put("goalType", Integer.valueOf(SportAssistSettingsActivity.this.bw));
                    if (!Utils.o()) {
                        hashMap.put("goalValue", Integer.valueOf((int) SportAssistSettingsActivity.this.bu));
                        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(SportAssistSettingsActivity.this.bi));
                    }
                    ixx.d().d(SportAssistSettingsActivity.this.j, AnalyticsValue.BI_TRACK_SPORT_GOAL_ACTION_KEY.value(), hashMap, 0);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.n = e;
        e.show();
    }

    public SportCustomTargetDialog a(Context context, int i) {
        if (context == null) {
            LogUtil.h("Track_SportSettingsFragment", "context is null");
            return null;
        }
        SportCustomTargetDialog.b bVar = new SportCustomTargetDialog.b(context, this.m);
        bVar.d(owp.a(this.j, this.bi, i));
        SportCustomTargetDialog d = bVar.d(i, this.bi);
        if (d != null) {
            d.show();
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0010, code lost:
    
        if (r7 != 6) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r7, float r8, boolean r9, boolean r10) {
        /*
            r6 = this;
            r0 = -1
            if (r7 == r0) goto Laf
            r0 = 1
            r1 = 0
            if (r7 == 0) goto L6b
            if (r7 == r0) goto L64
            r2 = 2
            if (r7 == r2) goto L3b
            r2 = 5
            if (r7 == r2) goto L14
            r0 = 6
            if (r7 == r0) goto Laf
            goto Lb2
        L14:
            com.huawei.ui.commonui.healthtextview.HealthTextView r2 = r6.bj
            double r3 = (double) r8
            java.lang.String r0 = health.compact.a.UnitUtil.e(r3, r0, r1)
            r2.setText(r0)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r6.bm
            android.content.Context r2 = r6.j
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2130903273(0x7f0300e9, float:1.741336E38)
            int r4 = (int) r8
            java.lang.String r2 = r2.getQuantityString(r3, r4)
            r0.setText(r2)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r6.bm
            r0.setVisibility(r1)
            r6.ar()
            goto Lb2
        L3b:
            com.huawei.ui.commonui.healthtextview.HealthTextView r2 = r6.bj
            r3 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r8 / r3
            double r3 = (double) r3
            java.lang.String r0 = health.compact.a.UnitUtil.e(r3, r0, r1)
            r2.setText(r0)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r6.bm
            android.content.Context r2 = r6.j
            android.content.res.Resources r2 = r2.getResources()
            r3 = 2130837659(0x7f02009b, float:1.7280278E38)
            java.lang.String r2 = r2.getString(r3)
            r0.setText(r2)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r6.bm
            r0.setVisibility(r1)
            r6.ar()
            goto Lb2
        L64:
            r6.e(r8)
            r6.ar()
            goto Lb2
        L6b:
            int r2 = r6.bi
            r3 = 283(0x11b, float:3.97E-43)
            if (r2 != r3) goto L85
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r6.bj
            float r1 = r6.bu
            int r1 = (int) r1
            java.lang.String r1 = health.compact.a.UnitUtil.a(r1)
            r0.setText(r1)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r6.bm
            r1 = 8
            r0.setVisibility(r1)
            goto Lb2
        L85:
            r2 = 1114636288(0x42700000, float:60.0)
            float r2 = r8 / r2
            com.huawei.ui.commonui.healthtextview.HealthTextView r3 = r6.bj
            double r4 = (double) r2
            java.lang.String r0 = health.compact.a.UnitUtil.e(r4, r0, r1)
            r3.setText(r0)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r6.bm
            android.content.Context r3 = r6.j
            android.content.res.Resources r3 = r3.getResources()
            int r2 = (int) r2
            r4 = 2130903233(0x7f0300c1, float:1.7413278E38)
            java.lang.String r2 = r3.getQuantityString(r4, r2)
            r0.setText(r2)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r6.bm
            r0.setVisibility(r1)
            r6.ar()
            goto Lb2
        Laf:
            r6.ak()
        Lb2:
            r6.b(r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity.a(int, float, boolean, boolean):void");
    }

    private void b(int i, float f, boolean z, boolean z2) {
        if (z) {
            owp.c(this.j, this.bi, i);
            owp.c(this.j, f, this.bi);
            owp.e(this.j, this.bi, z2);
            if (z2) {
                owp.b(this.j, this.bi, i, f);
            } else {
                owp.b(this.j, this.bi, i, -1.0f);
            }
        }
    }

    private void r() {
        if (getApplicationContext() == null) {
            LogUtil.h("Track_SportSettingsFragment", "initLanguageTextSize getApplicationContext is null");
        } else if (LanguageUtil.au(getApplicationContext()) || LanguageUtil.q(getApplicationContext())) {
            this.be.setTextSize(1, 12.0f);
        }
    }

    private void ar() {
        this.bm.setVisibility(0);
    }

    private void ak() {
        this.bm.setVisibility(8);
        this.bj.setVisibility(0);
        this.bj.setText(this.j.getResources().getText(R.string._2130842526_res_0x7f02139e));
    }

    private void e(float f) {
        if (UnitUtil.h()) {
            this.bj.setText(UnitUtil.e(UnitUtil.e(f, 3), 1, 2));
            this.bm.setVisibility(0);
            this.bm.setText(this.j.getResources().getString(R.string._2130841383_res_0x7f020f27));
            return;
        }
        this.bm.setText(this.j.getResources().getString(R.string._2130837660_res_0x7f02009c));
        double d = f;
        if (Math.abs(d - 42.195d) < 1.0E-5d) {
            this.bj.setText(UnitUtil.e(d, 1, 3));
        } else if (Math.abs(d - 21.0975d) < 1.0E-5d) {
            this.bj.setText(UnitUtil.e(d, 1, 4));
        } else {
            this.bm.setVisibility(0);
            this.bj.setText(UnitUtil.e(d, 1, 2));
        }
    }

    private boolean aa() {
        int i = this.bi;
        return i == 10001 || i == 137;
    }

    private boolean ai() {
        return this.bi == 283;
    }

    private boolean ac() {
        return this.bi == 286;
    }

    private void m() {
        if (Utils.o()) {
            LogUtil.h("Track_SportSettingsFragment", "initAutoTrackOption the version is oversea");
            this.e.setVisibility(8);
            this.i.setVisibility(8);
            return;
        }
        this.f = gso.e().d(this.j);
        if (a(KeyValDbManager.b(this.j).e("SUPPORT_AR_ABILITY"))) {
            this.e.setVisibility(0);
            this.i.setVisibility(0);
            this.d.setText(this.f.e() ? R.string._2130841536_res_0x7f020fc0 : R.string._2130841535_res_0x7f020fbf);
            this.f9517a.setVisibility(this.f.g() ? 0 : 8);
            return;
        }
        this.e.setVisibility(8);
        this.i.setVisibility(8);
    }

    private boolean a(String str) {
        int i;
        guz guzVar = this.f;
        return guzVar != null && guzVar.b() && "1".equals(str) && !nsn.ae(this.j) && ((i = this.bi) == 258 || i == 257);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (!LoginInit.getInstance(this.j).isBrowseMode() && this.bi == 264) {
            this.ar.setVisibility(0);
            this.k.setVisibility(0);
            gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
            this.p.setText("true".equals(SharedPreferenceManager.b(this.j, Integer.toString(20002), "ihealthlabs")) ? R.string._2130844049_res_0x7f021991 : R.string._2130844048_res_0x7f021990);
            this.q.setVisibility(gwwVar.z() ? 0 : 8);
            return;
        }
        this.ar.setVisibility(8);
        this.k.setVisibility(8);
    }

    private void j() {
        ThreadPoolManager.d().d("Track_SportSettingsFragment", new Runnable() { // from class: opt
            @Override // java.lang.Runnable
            public final void run() {
                SportAssistSettingsActivity.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        String e = jah.c().e("support_sport_lab");
        LogUtil.a("Track_SportSettingsFragment", "SUPPORT_SPORT_LAB: ", e);
        if (e == null) {
            e = "HMA,LYA,EVR,BSA,VOG,DZH,ELE,NLE,AMZ,LIO,TAS,FRO,NEY,ANA,ELS,KUL,RAI,OCE,NOH,NOP,LAN";
        }
        if (!CommonUtil.c(e.split(",")) || nsn.ae(BaseApplication.getContext())) {
            return;
        }
        runOnUiThread(new Runnable() { // from class: ops
            @Override // java.lang.Runnable
            public final void run() {
                SportAssistSettingsActivity.this.p();
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
