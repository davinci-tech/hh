package com.huawei.ui.homehealth.runcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.homehealth.runcard.adapter.PaceRangeDistanceAdapter;
import com.huawei.ui.homehealth.runcard.trackfragments.PaceRangeResultFragment;
import com.huawei.ui.homehealth.runcard.trackfragments.PaceRangeRunFragment;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelCurrentData;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import defpackage.cad;
import defpackage.cam;
import defpackage.ffg;
import defpackage.gvv;
import defpackage.hcl;
import defpackage.ixx;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mkc;
import defpackage.mlg;
import defpackage.nqx;
import defpackage.qrp;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes6.dex */
public class PaceRangeActivity extends BaseActivity implements View.OnClickListener, PaceRangeResultFragment.OnResultItemClickListener, PaceRangeRunFragment.OnRuntItemClickListener, HwViewPager.OnPageChangeListener {
    private NoTitleCustomAlertDialog aa;
    private HealthMultiNumberPicker ab;
    private HealthButton ad;
    private HealthEditText ae;
    private HealthImageView af;
    private HealthImageView ag;
    private HealthImageView ah;
    private HealthImageView ai;
    private HealthImageView ak;
    private boolean al;
    private HealthSubTabWidget am;
    private HealthViewPager an;
    private boolean ao;
    private boolean aq;
    private boolean ar;
    private HealthTextView at;
    private HealthTextView av;
    private HealthTextView aw;
    private boolean ax;
    private HealthTextView ay;
    private HealthColumnRelativeLayout az;
    private RelativeLayout b;
    private HealthTextView ba;
    private HealthTextView bb;
    private HealthTextView bc;
    private HealthTextView bd;
    private HealthImageView be;
    private LinearLayout bf;
    private HealthTextView bg;
    private LinearLayout bh;
    private HealthImageView bi;
    private double bj;
    private int bk;
    private HealthTextView bl;
    private HealthColumnRelativeLayout bm;
    private PaceRangeResultFragment bn;
    private int bo;
    private HealthTextView bp;
    private int[] bq;
    private PaceRangeRunFragment br;
    private NoTitleCustomAlertDialog bs;
    private CustomViewDialog.Builder bt;
    private ffg bu;
    private CustomViewDialog bv;
    private CustomViewDialog bw;
    private HealthColumnRelativeLayout bx;
    private HealthColumnRelativeLayout by;
    private int bz;
    private ImageView c;
    private RunningStateIndexData ca;
    private HealthTextView cb;
    private HealthColumnRelativeLayout cc;
    private HealthTextView cd;
    private HealthImageView ce;
    private HealthImageView cf;
    private HealthTextView cg;
    private NoTitleCustomAlertDialog ch;
    private HealthTextView ci;
    private HealthTextView cj;
    private HealthTextView ck;
    private HealthColumnRelativeLayout cl;
    private NoTitleCustomAlertDialog cm;
    private CustomViewDialog d;
    private HealthImageView f;
    private HealthColumnRelativeLayout g;
    private HealthTextView h;
    private ffg j;
    private NoTitleCustomAlertDialog k;
    private HealthTextView l;
    private int m;
    private ffg n;
    private float p;
    private RelativeLayout q;
    private CustomViewDialog r;
    private ImageView s;
    private int t;
    private HealthImageView u;
    private HealthColumnRelativeLayout v;
    private HealthTextView w;
    private HealthTextView x;
    private SparseArray<cad> y;
    private HealthButton z;

    /* renamed from: a, reason: collision with root package name */
    private int f9501a = 0;
    private int e = 0;
    private int i = 0;
    private boolean ap = false;
    private boolean as = false;
    private boolean au = false;
    private boolean aj = false;
    private Handler ac = new a(this);
    private Bundle o = new Bundle();

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    static class a extends BaseHandler<PaceRangeActivity> {
        a(PaceRangeActivity paceRangeActivity) {
            super(paceRangeActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: deZ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PaceRangeActivity paceRangeActivity, Message message) {
            switch (message.what) {
                case 100:
                    long longValue = ((Long) message.obj).longValue();
                    int i = message.arg1;
                    if ((i != 5 && i != 6 && i != 7) || !paceRangeActivity.au) {
                        paceRangeActivity.a(cam.e(i), longValue);
                        break;
                    } else {
                        LogUtil.h("Track_PaceRangeActivity", "handleMessage acquireBestRun no metric distance in the imperial system");
                        break;
                    }
                    break;
                case 101:
                    Object obj = message.obj;
                    if (obj instanceof int[]) {
                        paceRangeActivity.af();
                        paceRangeActivity.b((int[]) obj);
                        break;
                    }
                    break;
                case 102:
                    paceRangeActivity.e(paceRangeActivity.ae);
                    break;
                default:
                    LogUtil.h("Track_PaceRangeActivity", "handleMessageWhenReferenceNotNull switch default");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.1
            @Override // java.lang.Runnable
            public void run() {
                cam.a(PaceRangeActivity.this.al);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int[] iArr) {
        SharedPreferenceManager.b("pace_zone_module_id", "last_click_apply_fragment_position", this.m);
        ai();
        Intent intent = new Intent();
        if (iArr != null) {
            intent.putExtra("current_pace_zone_value", iArr);
        }
        setResult(2, intent);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pace_range);
        k();
        boolean h = UnitUtil.h();
        this.au = h;
        if (h) {
            this.t = 3;
            this.y = cam.Cu_(this);
            this.bj = 1.609344d;
        } else {
            this.t = 0;
            this.y = cam.Ct_(this);
            this.bj = 1.0d;
        }
        if (j(this.t)) {
            g(this.y.get(this.t).e());
        }
        ad();
        z();
        x();
        ab();
        aq();
        v();
        as();
        ay();
        g();
        t();
        s();
    }

    private void t() {
        a(this.ad, false, 0);
        a(this.z, false, 0);
        a(this.ad, false, 1);
        a(this.z, false, 1);
    }

    private void ad() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.ctb_pace_range_title);
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaceRangeActivity.this.ae();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void z() {
        aa();
        u();
        w();
        y();
    }

    private void aa() {
        HealthCardView healthCardView = (HealthCardView) findViewById(R.id.hcv_pace_range_list_container);
        HealthTextView healthTextView = (HealthTextView) healthCardView.findViewById(R.id.tv_calc_result);
        this.bp = healthTextView;
        healthTextView.setVisibility(0);
        ((HealthTextView) healthCardView.findViewById(R.id.tv_current_pace)).setText(R.string._2130845430_res_0x7f021ef6);
        this.ba = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_easy_run_label);
        this.ay = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_marathon_label);
        this.bb = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_lactic_acid_label);
        this.at = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_anaerobic_label);
        this.aw = (HealthTextView) healthCardView.findViewById(R.id.tv_pace_range_take_oxygen_label);
        this.ai = (HealthImageView) healthCardView.findViewById(R.id.hiv_pace_range_easy_run);
        this.af = (HealthImageView) healthCardView.findViewById(R.id.hiv_pace_range_marathon);
        this.ah = (HealthImageView) healthCardView.findViewById(R.id.hiv_pace_range_lactic_acid);
        this.ag = (HealthImageView) healthCardView.findViewById(R.id.hiv_pace_range_anaerobic);
        this.ak = (HealthImageView) healthCardView.findViewById(R.id.hiv_range_take_oxygen);
        this.w = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_one);
        this.bl = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_two);
        this.bg = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_three);
        this.l = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_four);
        this.ck = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_five);
        this.cd = (HealthTextView) healthCardView.findViewById(R.id.htv_current_range_six);
        ((ConstraintLayout) healthCardView.findViewById(R.id.cl_calc_result)).setVisibility(0);
        this.v = (HealthColumnRelativeLayout) healthCardView.findViewById(R.id.hcrl_calc_result_one);
        this.x = (HealthTextView) healthCardView.findViewById(R.id.htv_calc_result_one);
        this.u = (HealthImageView) healthCardView.findViewById(R.id.hiv_calc_result_one);
        this.bm = (HealthColumnRelativeLayout) healthCardView.findViewById(R.id.hcrl_calc_result_two);
        this.bd = (HealthTextView) healthCardView.findViewById(R.id.htv_calc_result_two);
        this.bi = (HealthImageView) healthCardView.findViewById(R.id.hiv_calc_result_two);
        this.az = (HealthColumnRelativeLayout) healthCardView.findViewById(R.id.hcrl_calc_result_three);
        this.bc = (HealthTextView) healthCardView.findViewById(R.id.htv_calc_result_three);
        this.be = (HealthImageView) healthCardView.findViewById(R.id.hiv_calc_result_three);
        this.g = (HealthColumnRelativeLayout) healthCardView.findViewById(R.id.hcrl_calc_result_four);
        this.h = (HealthTextView) healthCardView.findViewById(R.id.htv_calc_result_four);
        this.f = (HealthImageView) healthCardView.findViewById(R.id.hiv_calc_result_four);
        this.cc = (HealthColumnRelativeLayout) healthCardView.findViewById(R.id.hcrl_calc_result_five);
        this.cg = (HealthTextView) healthCardView.findViewById(R.id.htv_calc_result_five);
        this.ce = (HealthImageView) healthCardView.findViewById(R.id.hiv_calc_result_five);
        this.by = (HealthColumnRelativeLayout) healthCardView.findViewById(R.id.hcrl_calc_result_six);
        this.cb = (HealthTextView) healthCardView.findViewById(R.id.htv_calc_result_six);
        this.cf = (HealthImageView) healthCardView.findViewById(R.id.hiv_calc_result_six);
    }

    private void u() {
        this.bf = (LinearLayout) findViewById(R.id.ll_no_cloud_base_result_container);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hcv_pace_range_result_container);
        this.q = (RelativeLayout) linearLayout.findViewById(R.id.layout_pace_range_distance);
        this.b = (RelativeLayout) linearLayout.findViewById(R.id.layout_pace_range_achievement);
        this.s = (ImageView) linearLayout.findViewById(R.id.img_pace_range_right_distance);
        this.c = (ImageView) linearLayout.findViewById(R.id.img_pace_range_right_achievement);
        this.cj = (HealthTextView) linearLayout.findViewById(R.id.tv_pace_range_right_distance);
        this.av = (HealthTextView) linearLayout.findViewById(R.id.tv_pace_range_label_achievement);
        this.ci = (HealthTextView) linearLayout.findViewById(R.id.tv_pace_range_right_achievement);
    }

    private void w() {
        this.bh = (LinearLayout) findViewById(R.id.ll_cloud_base_all_container);
        HealthSubTabWidget healthSubTabWidget = (HealthSubTabWidget) findViewById(R.id.hstw_sub_tab);
        this.am = healthSubTabWidget;
        healthSubTabWidget.setBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.hvp_view_pager);
        this.an = healthViewPager;
        healthViewPager.setIsAutoHeight(true);
    }

    private void y() {
        this.z = (HealthButton) findViewById(R.id.hb_pace_range_calculation);
        this.ad = (HealthButton) findViewById(R.id.hb_pace_range_apply);
    }

    private void x() {
        this.v.setOnClickListener(this);
        this.bm.setOnClickListener(this);
        this.az.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.cc.setOnClickListener(this);
        this.by.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.ad.setOnClickListener(this);
    }

    private void aq() {
        if (Utils.i()) {
            this.bh.setVisibility(0);
            this.bf.setVisibility(8);
        } else {
            this.bh.setVisibility(8);
            this.bf.setVisibility(0);
        }
    }

    private void v() {
        int a2 = SharedPreferenceManager.a("pace_zone_module_id", "last_click_apply_fragment_position", 0);
        this.m = a2;
        nqx nqxVar = new nqx(this, this.an, this.am);
        this.bn = new PaceRangeResultFragment();
        this.br = new PaceRangeRunFragment();
        nqxVar.c(this.am.c(getString(R.string._2130845427_res_0x7f021ef3)), this.bn, a2 == 0);
        nqxVar.c(this.am.c(getString(R.string._2130845428_res_0x7f021ef4)), this.br, a2 == 1);
        this.bn.c(this);
        this.br.e(this);
        this.an.addOnPageChangeListener(this);
        RunningStateIndexData runningStateIndexData = this.ca;
        if (runningStateIndexData != null) {
            this.o.putParcelable("running_level_data", runningStateIndexData);
        }
        this.o.putBoolean("setting_auto_calc_key_status", this.al);
        this.br.setArguments(this.o);
        e(a2);
        if (a2 == 1) {
            c();
        }
    }

    private void as() {
        this.ba.setText(a(1));
        this.ay.setText(a(2));
        this.bb.setText(a(3));
        this.at.setText(a(4));
        this.aw.setText(a(5));
        int r = r();
        d(r, this.ba);
        d(r, this.ay);
        d(r, this.bb);
        d(r, this.at);
        d(r, this.aw);
    }

    private void ay() {
        if (LanguageUtil.bc(this)) {
            this.s.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.c.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ai.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            this.af.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            this.ah.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            this.ag.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            this.ak.setImageResource(R.drawable._2131430956_res_0x7f0b0e2c);
            return;
        }
        this.s.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.c.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        this.ai.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
        this.af.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
        this.ah.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
        this.ag.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
        this.ak.setImageResource(R.drawable._2131430955_res_0x7f0b0e2b);
    }

    private void g() {
        if (this.au) {
            this.bp.setText(getString(R.string._2130845437_res_0x7f021efd));
        } else {
            this.bp.setText(getString(R.string._2130843968_res_0x7f021940));
        }
    }

    private void av() {
        if (this.r == null) {
            View deY_ = deY_();
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
            builder.a(getString(R.string._2130841530_res_0x7f020fba)).czh_(deY_, 0, 0).czc_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.24
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.r = builder.e();
        }
        this.r.show();
    }

    private View deY_() {
        View inflate = View.inflate(this, R.layout.item_pace_range_distances_view, null);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.hrv_pace_range_distance);
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this));
        final PaceRangeDistanceAdapter paceRangeDistanceAdapter = new PaceRangeDistanceAdapter(this, this.y);
        healthRecycleView.setAdapter(paceRangeDistanceAdapter);
        paceRangeDistanceAdapter.d(new PaceRangeDistanceAdapter.OnItemClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.22
            @Override // com.huawei.ui.homehealth.runcard.adapter.PaceRangeDistanceAdapter.OnItemClickListener
            public void onItemClick(int i) {
                if (PaceRangeActivity.this.j(i)) {
                    if (i != PaceRangeActivity.this.t) {
                        int l = PaceRangeActivity.this.l();
                        cad cadVar = (cad) PaceRangeActivity.this.y.get(i);
                        if (cadVar.d() < l || l < cadVar.e()) {
                            PaceRangeActivity.this.g(cadVar.e());
                            PaceRangeActivity.this.ci.setText(PaceRangeActivity.this.getString(R.string._2130843976_res_0x7f021948));
                            PaceRangeActivity.this.bn.b(PaceRangeActivity.this.getString(R.string._2130843976_res_0x7f021948));
                            PaceRangeActivity paceRangeActivity = PaceRangeActivity.this;
                            paceRangeActivity.a("achievement_value", paceRangeActivity.getString(R.string._2130843976_res_0x7f021948));
                            PaceRangeActivity.this.as = false;
                            PaceRangeActivity paceRangeActivity2 = PaceRangeActivity.this;
                            paceRangeActivity2.a(paceRangeActivity2.z, false, 0);
                        }
                    }
                    PaceRangeActivity.this.d(i);
                    paceRangeDistanceAdapter.notifyDataSetChanged();
                    PaceRangeActivity.this.e();
                    PaceRangeActivity paceRangeActivity3 = PaceRangeActivity.this;
                    paceRangeActivity3.c(paceRangeActivity3.r);
                    return;
                }
                LogUtil.h("Track_PaceRangeActivity", "onItemClick distanceType error");
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (!j(i) || !j(this.t)) {
            LogUtil.h("Track_PaceRangeActivity", "changePositionUpdateDistance distanceType or mDistanceSelectType error");
            return;
        }
        this.y.get(this.t).c(false);
        cad cadVar = this.y.get(i);
        cadVar.c(true);
        this.t = i;
        this.ap = true;
        this.cj.setText(cadVar.a());
        this.bn.e(cadVar.a());
        a("distance_value", cadVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.o.putString(str, str2);
        if (this.bn.isAdded()) {
            Bundle arguments = this.bn.getArguments();
            if (arguments != null) {
                arguments.putString(str, str2);
                return;
            }
            return;
        }
        this.bn.setArguments(this.o);
    }

    private void ap() {
        c(this.d);
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        this.ab = healthMultiNumberPicker;
        healthMultiNumberPicker.setPickerCount(3, new boolean[]{false, true, true});
        this.ab.setColonAndUnit(3);
        int l = l();
        if (j(this.t)) {
            cad cadVar = this.y.get(this.t);
            final int e = cadVar.e();
            final int d = cadVar.d();
            if (e > d || e < 0) {
                LogUtil.h("Track_PaceRangeActivity", "get minTime maxTime error");
                return;
            }
            int i = e / 3600;
            this.bk = i;
            int i2 = l / 3600;
            final String[] c2 = c(e, d);
            String[] a2 = a(e, d, i2 - i, c2.length);
            String[] q = q();
            this.ab.setDisplayedValues(0, c2, i2 - this.bk);
            this.ab.setDisplayedValues(1, a2, ((l % 3600) / 60) - this.bo);
            this.ab.setDisplayedValues(2, q, l % 60);
            this.ab.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.23
                @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
                public void onValueChange(int i3, HealthMultiNumberPicker healthMultiNumberPicker2, int i4, int i5) {
                    int length = PaceRangeActivity.this.ab.e(0).length;
                    if (i3 != 0 || length <= 1) {
                        return;
                    }
                    healthMultiNumberPicker2.setDisplayedValues(1, PaceRangeActivity.this.a(e, d, i5, c2.length), 0);
                }
            });
            b();
        }
    }

    private void b() {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.a(getString(R.string._2130843977_res_0x7f021949)).czh_(this.ab, 24, 24).czc_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R.string._2130837648_res_0x7f020090, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int[] selectedLocations = PaceRangeActivity.this.ab.getSelectedLocations();
                if (selectedLocations.length < 3) {
                    LogUtil.h("Track_PaceRangeActivity", "selected length error ", Integer.valueOf(selectedLocations.length));
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                PaceRangeActivity paceRangeActivity = PaceRangeActivity.this;
                paceRangeActivity.f9501a = selectedLocations[0] + paceRangeActivity.bk;
                PaceRangeActivity paceRangeActivity2 = PaceRangeActivity.this;
                paceRangeActivity2.e = selectedLocations[1] + paceRangeActivity2.bo;
                PaceRangeActivity.this.i = selectedLocations[2];
                PaceRangeActivity.this.an();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.d = e;
        e.show();
    }

    private String[] c(int i, int i2) {
        return this.ab.d(i / 3600, i2 / 3600, getResources().getString(R$string.IDS_band_data_sleep_unit_h));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] a(int i, int i2, int i3, int i4) {
        int i5 = (i % 3600) / 60;
        int i6 = (i2 % 3600) / 60;
        int i7 = 59;
        if (i4 >= 2 && i3 == 0) {
            i6 = 59;
        }
        int i8 = 0;
        if (i4 >= 2 && i3 == i4 - 1) {
            i5 = 0;
        }
        if (i4 <= 2 || i3 == 0 || i3 == i4 - 1) {
            i8 = i5;
            i7 = i6;
        }
        this.bo = i8;
        return this.ab.d(i8, i7, getResources().getString(R$string.IDS_band_data_sleep_unit_m));
    }

    private String[] q() {
        return this.ab.d(0, 59, getResources().getString(R$string.IDS_second));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        SimpleDateFormat simpleDateFormat;
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, this.f9501a);
        calendar.set(12, this.e);
        calendar.set(13, this.i);
        Date time = calendar.getTime();
        if (this.f9501a == 0) {
            simpleDateFormat = new SimpleDateFormat("mm:ss");
        } else {
            simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT_WITHOUT_MILLS);
        }
        String format = simpleDateFormat.format(time);
        this.ci.setText(format);
        this.bn.b(format);
        a("achievement_value", format);
        this.as = true;
        this.aq = true;
        if (this.m == 0) {
            e();
        }
    }

    private void d(HealthTextView healthTextView, int i, boolean z) {
        boolean b2;
        if (healthTextView == null || (this.m == 1 && this.al)) {
            LogUtil.h("Track_PaceRangeActivity", "showRangeTimeDialog textView is null or auto calc");
            return;
        }
        c(this.bv);
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        this.ab = healthMultiNumberPicker;
        healthMultiNumberPicker.setPickerCount(2, new boolean[]{true, true});
        this.ab.setColonAndUnit(2);
        int round = (int) Math.round(d(i, z) * this.bj);
        if (this.au) {
            b2 = b(120, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, round);
        } else {
            b2 = b(60, 900, round);
        }
        if (!b2) {
            LogUtil.c("Track_PaceRangeActivity", "showRangeTimeDialog init error");
            return;
        }
        this.ab.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.30
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i2, HealthMultiNumberPicker healthMultiNumberPicker2, int i3, int i4) {
                int length = PaceRangeActivity.this.ab.e(0).length;
                if (i2 == 0) {
                    healthMultiNumberPicker2.setDisplayedValues(1, PaceRangeActivity.this.b(i4, length), PaceRangeActivity.this.bz);
                }
            }
        });
        e(i, z);
    }

    private void e(final int i, final boolean z) {
        String string;
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        if (z) {
            string = getString(R.string._2130843979_res_0x7f02194b, new Object[]{Integer.valueOf(i)});
        } else {
            string = getString(R.string._2130843980_res_0x7f02194c, new Object[]{Integer.valueOf(i)});
        }
        builder.a(string).czh_(this.ab, 0, 0).czc_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R.string._2130837648_res_0x7f020090, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaceRangeActivity.this.c(i, z);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.bv = e;
        e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (PaceRangeActivity.this.bx == null || PaceRangeActivity.this.bx == PaceRangeActivity.this.cl) {
                    return;
                }
                PaceRangeActivity.this.bx.setBackgroundResource(R.drawable.pace_range_time_background);
                PaceRangeActivity.this.bx = null;
            }
        });
        this.bv.show();
    }

    private int d(int i, boolean z) {
        int a2;
        HealthColumnRelativeLayout healthColumnRelativeLayout = this.cl;
        if (healthColumnRelativeLayout != null) {
            healthColumnRelativeLayout.setBackgroundResource(R.drawable.pace_range_time_background);
            this.cl = null;
        }
        int i2 = this.m;
        if (i2 == 0) {
            a2 = a(i, z, this.j);
        } else {
            a2 = i2 == 1 ? a(i, z, this.bu) : 0;
        }
        this.bx.setBackgroundResource(R.drawable.pace_range_time_select_background);
        return a2;
    }

    private int a(int i, boolean z, ffg ffgVar) {
        if (i == 1) {
            r1 = ffgVar != null ? ffgVar.j() : 0;
            this.bx = this.v;
        } else if (i == 2) {
            r1 = ffgVar != null ? ffgVar.b() : 0;
            this.bx = this.bm;
        } else if (i == 3) {
            r1 = ffgVar != null ? ffgVar.d() : 0;
            this.bx = this.az;
        } else if (i == 4) {
            r1 = ffgVar != null ? ffgVar.c() : 0;
            this.bx = this.g;
        } else if (i != 5) {
            LogUtil.h("Track_PaceRangeActivity", "getPaceRangeAccordingFragment getPaceRange switch default");
        } else if (z) {
            r1 = ffgVar != null ? ffgVar.e() : 0;
            this.bx = this.cc;
        } else {
            r1 = ffgVar != null ? ffgVar.a() : 0;
            this.bx = this.by;
        }
        return r1;
    }

    private boolean b(int i, int i2, int i3) {
        int i4;
        if (i > i2 || i < 0) {
            LogUtil.h("Track_PaceRangeActivity", "setMaxMinRangeData minTime maxTime error");
            return false;
        }
        int i5 = i / 60;
        this.bo = i5;
        int i6 = i3 / 60;
        int i7 = i3 % 60;
        this.bz = i7;
        if (i3 > i2 || i3 < i) {
            i4 = 0;
            i7 = 0;
        } else {
            i4 = i6 - i5;
        }
        String[] d = this.ab.d(i5, i2 / 60, getResources().getString(R$string.IDS_band_data_sleep_unit_m));
        String[] b2 = b(i4, d.length);
        this.ab.setDisplayedValues(0, d, i4);
        this.ab.setDisplayedValues(1, b2, i7);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] b(int i, int i2) {
        int i3;
        if (i == i2 - 1) {
            this.bz = 0;
            i3 = 0;
        } else {
            i3 = 59;
        }
        return this.ab.d(0, i3, getResources().getString(R$string.IDS_second));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, boolean z) {
        int[] selectedLocations = this.ab.getSelectedLocations();
        if (selectedLocations.length < 2) {
            LogUtil.h("Track_PaceRangeActivity", "the locations of MultiNumberPicker have something wrong");
            return;
        }
        float f = ((selectedLocations[0] + this.bo) * 60) + selectedLocations[1];
        String a2 = gvv.a(f);
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        int i2 = (int) (f / this.bj);
        c cVar = new c();
        cVar.c(i);
        cVar.c(z);
        b(cVar, a2, hashMap, i2);
        this.aj = true;
        a(this.ad, true, 0);
        d(hashMap);
    }

    private void b(c cVar, String str, Map<String, Object> map, int i) {
        int i2 = this.m;
        if (i2 == 0) {
            if (this.j == null) {
                LogUtil.a("Track_PaceRangeActivity", "setRangeTimeDataForView mAchievementResultConfig is null");
                this.j = new ffg();
            }
            a(cVar, str, map, i, this.j);
            return;
        }
        if (i2 == 1) {
            if (this.bu == null) {
                LogUtil.a("Track_PaceRangeActivity", "setRangeTimeDataForView mRunResultConfig is null");
                this.bu = new ffg();
            }
            a(cVar, str, map, i, this.bu);
        }
    }

    private void a(c cVar, String str, Map<String, Object> map, int i, ffg ffgVar) {
        int d = cVar.d();
        if (d == 1) {
            this.x.setText(str);
            ffgVar.h(i);
            map.put("type", 1);
            a(ffgVar.j(), this.n.j(), this.u);
            return;
        }
        if (d == 2) {
            this.bd.setText(str);
            ffgVar.e(i);
            map.put("type", 2);
            a(ffgVar.b(), this.n.b(), this.bi);
            return;
        }
        if (d == 3) {
            this.bc.setText(str);
            ffgVar.c(i);
            map.put("type", 3);
            a(ffgVar.d(), this.n.d(), this.be);
            return;
        }
        if (d == 4) {
            this.h.setText(str);
            ffgVar.a(i);
            map.put("type", 4);
            a(ffgVar.c(), this.n.c(), this.f);
            return;
        }
        if (d != 5) {
            LogUtil.h("Track_PaceRangeActivity", "setRangeTimeDataForView switch default");
            return;
        }
        if (cVar.e()) {
            this.cg.setText(str);
            ffgVar.d(i);
            map.put("type", 5);
            a(ffgVar.e(), this.n.e(), this.ce);
            return;
        }
        this.cb.setText(str);
        ffgVar.b(i);
        map.put("type", 6);
        a(ffgVar.a(), this.n.a(), this.cf);
    }

    private void j() {
        if (!j(this.t)) {
            LogUtil.h("Track_PaceRangeActivity", "calculatePace mDistanceSelectType error");
            return;
        }
        double c2 = cam.c(this.y.get(this.t).c(), l());
        if (c2 == 0.0d) {
            LogUtil.h("Track_PaceRangeActivity", "calculatePace error");
            return;
        }
        if (this.j == null) {
            this.j = new ffg();
        }
        this.j.h(cam.d(c2));
        this.j.e(cam.a(c2));
        this.j.c(cam.e(c2));
        this.j.a(cam.c(c2));
        this.j.d(cam.h(c2));
        this.j.b(cam.b(c2));
        d(this.j);
        a(this.ad, true, 0);
        this.aj = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.ap && this.as) {
            a(this.z, true, 0);
        }
        f();
    }

    private void a() {
        this.ap = false;
        this.as = false;
        a(this.z, false, 0);
        f();
    }

    private void f() {
        if (this.ap) {
            this.av.setTextColor(ContextCompat.getColor(this, R.color._2131299236_res_0x7f090ba4));
            this.ci.setTextColor(ContextCompat.getColor(this, R.color._2131299241_res_0x7f090ba9));
            this.b.setClickable(true);
        } else {
            this.av.setTextColor(ContextCompat.getColor(this, R.color._2131299244_res_0x7f090bac));
            this.ci.setTextColor(ContextCompat.getColor(this, R.color._2131299244_res_0x7f090bac));
            this.b.setClickable(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HealthButton healthButton, boolean z, int i) {
        if (healthButton == null) {
            LogUtil.h("Track_PaceRangeActivity", "setBtnClickableAndAlpha healthButton null");
            return;
        }
        if (z) {
            healthButton.setAlpha(1.0f);
        } else {
            healthButton.setAlpha(0.38f);
        }
        healthButton.setClickable(z);
        if (i == 0) {
            if (healthButton == this.z) {
                this.aq = z;
            }
            if (healthButton == this.ad) {
                this.ar = z;
            }
        }
        if (i == 1) {
            if (healthButton == this.z) {
                this.ax = z;
            }
            if (healthButton == this.ad) {
                this.ao = z;
            }
        }
    }

    private void ax() {
        if (this.k == null) {
            this.k = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130845435_res_0x7f021efb)).czA_(getString(R.string._2130845436_res_0x7f021efc), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czE_(getString(R.string._2130837648_res_0x7f020090), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PaceRangeActivity.this.al = false;
                    PaceRangeActivity.this.ah();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        }
        this.k.show();
    }

    private void au() {
        if (this.bs == null) {
            this.bs = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130843984_res_0x7f021950)).czA_(getString(R.string._2130845098_res_0x7f021daa), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czE_(getString(R.string._2130837648_res_0x7f020090), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PaceRangeActivity.this.ah();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        }
        this.bs.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HealthColumnRelativeLayout healthColumnRelativeLayout = this.cl;
        if (healthColumnRelativeLayout != null) {
            healthColumnRelativeLayout.setBackgroundResource(R.drawable.pace_range_time_background);
            this.cl = null;
        }
        ai();
        ah();
    }

    private void ai() {
        if (!j(this.t) || !c(getString(R.string._2130849885_res_0x7f02305d))) {
            LogUtil.h("Track_PaceRangeActivity", "saveUserAchievement mDistanceSelectType error");
        } else {
            cam.d(this, this.y.get(this.t).b(), l(), new Date().getTime());
        }
    }

    private void am() {
        if (this.n == null) {
            LogUtil.h("Track_PaceRangeActivity", "setPaceRangeTimeView mApplyConfig null");
            return;
        }
        this.w.setText(gvv.a((float) (r0.j() * this.bj)));
        this.bl.setText(gvv.a((float) (this.n.b() * this.bj)));
        this.bg.setText(gvv.a((float) (this.n.d() * this.bj)));
        this.l.setText(gvv.a((float) (this.n.c() * this.bj)));
        this.ck.setText(gvv.a((float) (this.n.e() * this.bj)));
        this.cd.setText(gvv.a((float) (this.n.a() * this.bj)));
        ak();
    }

    private boolean c(ffg ffgVar) {
        if (ffgVar == null) {
            LogUtil.h("Track_PaceRangeActivity", "judgeTimePaceRangeValid config is null");
            return false;
        }
        if (ffgVar.j() < 110 || ffgVar.j() < ffgVar.b()) {
            b(this.v);
        } else if (ffgVar.b() < ffgVar.d()) {
            if (ffgVar.d() > ffgVar.j()) {
                b(this.az);
            } else {
                b(this.bm);
            }
        } else if (ffgVar.d() < ffgVar.c()) {
            if (ffgVar.c() > ffgVar.b()) {
                b(this.g);
            } else {
                b(this.az);
            }
        } else if (ffgVar.c() < ffgVar.e()) {
            if (ffgVar.e() > ffgVar.d()) {
                b(this.cc);
            } else {
                b(this.g);
            }
        } else {
            if (ffgVar.e() >= ffgVar.a()) {
                return true;
            }
            if (ffgVar.a() > ffgVar.c()) {
                b(this.by);
            } else {
                b(this.cc);
            }
        }
        return false;
    }

    private boolean e(ffg ffgVar) {
        if (ffgVar == null) {
            LogUtil.h("Track_PaceRangeActivity", "judgeTimeGreaterThanTen config is null");
            return false;
        }
        if (Math.round((ffgVar.j() - ffgVar.b()) * this.bj) < 10) {
            b(this.v);
        } else if (Math.round((ffgVar.b() - ffgVar.d()) * this.bj) < 10) {
            if (ffgVar.d() > ffgVar.j()) {
                b(this.az);
            } else {
                b(this.bm);
            }
        } else if (Math.round((ffgVar.d() - ffgVar.c()) * this.bj) < 10) {
            if (ffgVar.c() > ffgVar.b()) {
                b(this.g);
            } else {
                b(this.az);
            }
        } else if (Math.round((ffgVar.c() - ffgVar.e()) * this.bj) < 10) {
            if (ffgVar.e() > ffgVar.d()) {
                b(this.cc);
            } else {
                b(this.g);
            }
        } else {
            if (Math.round((ffgVar.e() - ffgVar.a()) * this.bj) >= 10) {
                return true;
            }
            if (ffgVar.a() > ffgVar.c()) {
                b(this.by);
            } else {
                b(this.cc);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ac() {
        ffg ffgVar;
        if (this.m == 0) {
            e(this.n, this.j);
            ffgVar = this.j;
        } else {
            ffgVar = null;
        }
        if (this.m == 1) {
            e(this.n, this.bu);
            ffgVar = this.bu;
        }
        if (!c(ffgVar)) {
            e(false);
            return true;
        }
        if (e(ffgVar)) {
            return false;
        }
        e(true);
        return true;
    }

    private void b(HealthColumnRelativeLayout healthColumnRelativeLayout) {
        healthColumnRelativeLayout.setBackgroundResource(R.drawable.pace_range_time_warning_background);
        this.cl = healthColumnRelativeLayout;
    }

    private void e(boolean z) {
        String string;
        c(this.cm);
        if (z) {
            string = getString(R.string._2130844030_res_0x7f02197e, new Object[]{10});
        } else {
            string = getString(R.string._2130843981_res_0x7f02194d);
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(string).czE_(getString(R.string._2130841794_res_0x7f0210c2), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.cm = e;
        e.show();
    }

    private void aw() {
        if (this.aa == null) {
            this.aa = new NoTitleCustomAlertDialog.Builder(this).e(getString(R.string._2130843983_res_0x7f02194f)).czA_(getString(R.string._2130845098_res_0x7f021daa), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czE_(getString(R.string._2130837648_res_0x7f020090), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PaceRangeActivity.this.b((int[]) null);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
        }
        this.aa.show();
    }

    private void a(final boolean z) {
        String string;
        String string2;
        String string3;
        c(this.ch);
        if (z) {
            string = getString(R.string._2130844031_res_0x7f02197f);
            string2 = getString(R.string._2130845098_res_0x7f021daa);
            string3 = getString(R.string._2130837648_res_0x7f020090);
        } else {
            string = getString(R.string._2130843982_res_0x7f02194e);
            string2 = getString(R.string._2130841389_res_0x7f020f2d);
            string3 = getString(R.string._2130841751_res_0x7f021097);
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(string).czA_(string2, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaceRangeActivity.this.b((int[]) null);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czE_(string3, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PaceRangeActivity.this.ac()) {
                    LogUtil.h("Track_PaceRangeActivity", "showUnSavePromptDialog isShowJudgeTimePaceDialog true");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    if (z) {
                        PaceRangeActivity.this.d();
                    } else {
                        PaceRangeActivity.this.ah();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).e();
        this.ch = e;
        e.show();
    }

    private void k() {
        Intent intent = getIntent();
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("running_level_data");
            if (parcelableExtra instanceof RunningStateIndexData) {
                this.ca = (RunningStateIndexData) parcelableExtra;
            }
            this.al = intent.getBooleanExtra("setting_auto_calc_key_status", false);
            try {
                this.bq = intent.getIntArrayExtra("intent_pace_zone");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("Track_PaceRangeActivity", "getIntentContent mPaceZone ArrayIndexOutOfBoundsException.");
            }
        }
    }

    private void ab() {
        int[] iArr = this.bq;
        if (iArr == null || iArr.length != 6) {
            al();
        } else {
            ffg ffgVar = new ffg();
            this.n = ffgVar;
            ffgVar.h(this.bq[0]);
            this.n.e(this.bq[1]);
            this.n.c(this.bq[2]);
            this.n.a(this.bq[3]);
            this.n.d(this.bq[4]);
            this.n.b(this.bq[5]);
        }
        am();
    }

    private void al() {
        ffg ffgVar = new ffg();
        this.n = ffgVar;
        ffgVar.h(450);
        this.n.e(420);
        this.n.c(390);
        this.n.a(360);
        this.n.d(330);
        this.n.b(300);
    }

    private void s() {
        String b2;
        a();
        if (this.au) {
            b2 = SharedPreferenceManager.b(this, String.valueOf(10000), "USER_SAVE_ACHIEVEMENT_IMPERIAL_KEY");
        } else {
            b2 = SharedPreferenceManager.b(this, String.valueOf(10000), "USER_SAVE_ACHIEVEMENT_METRIC_KEY");
        }
        if (TextUtils.isEmpty(b2)) {
            n();
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(b2);
            if (jSONArray.length() != 3) {
                n();
                LogUtil.h("Track_PaceRangeActivity", "userAchievement userPreference invalid");
            } else {
                a(Integer.parseInt(jSONArray.getString(0)), Integer.parseInt(jSONArray.getString(1)));
            }
        } catch (NumberFormatException | JSONException unused) {
            n();
            LogUtil.b("Track_PaceRangeActivity", "getUserAchievement JSONException error");
        }
    }

    private void n() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.12
            @Override // java.lang.Runnable
            public void run() {
                mcz d = meh.c(BaseApplication.getContext()).d(2, new HashMap(16));
                SingleDayRecord singleDayRecord = d instanceof SingleDayRecord ? (SingleDayRecord) d : null;
                if (singleDayRecord != null) {
                    if (PaceRangeActivity.this.e(6, singleDayRecord) || PaceRangeActivity.this.e(7, singleDayRecord) || PaceRangeActivity.this.e(8, singleDayRecord) || PaceRangeActivity.this.e(5, singleDayRecord)) {
                        return;
                    }
                    PaceRangeActivity.this.e(9, singleDayRecord);
                    return;
                }
                LogUtil.h("Track_PaceRangeActivity", "getAcquireBestRun singleDayRecord null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(int i, SingleDayRecord singleDayRecord) {
        String b2 = mlg.b(i, singleDayRecord);
        if (TextUtils.isEmpty(b2)) {
            LogUtil.h("Track_PaceRangeActivity", "getAchieveToAchievement bestRunJson is error");
            return false;
        }
        mkc d = mlg.d(b2);
        if (d == null || this.ac == null) {
            LogUtil.h("Track_PaceRangeActivity", "getAchieveToAchievement bestMotionPace or mHandler is null");
            return false;
        }
        Message obtain = Message.obtain();
        obtain.what = 100;
        obtain.arg1 = i;
        obtain.obj = Long.valueOf((long) Math.ceil(d.b()));
        LogUtil.a("Track_PaceRangeActivity", "getAchieveToAchievement acquireValue=", Double.valueOf(d.b()));
        this.ac.sendMessage(obtain);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, long j) {
        if (!j(i)) {
            LogUtil.h("Track_PaceRangeActivity", "makeToAchievementDataUi distanceType invalid");
            return;
        }
        cad cadVar = this.y.get(i);
        if (j < cadVar.e() || j > cadVar.d()) {
            LogUtil.h("Track_PaceRangeActivity", "makeToAchievementDataUi elapsedTime invalid");
            return;
        }
        d(i);
        g((int) j);
        an();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i) {
        this.f9501a = i / 3600;
        this.e = (i % 3600) / 60;
        this.i = i % 60;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int l() {
        return (this.f9501a * 3600) + (this.e * 60) + this.i;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.hcrl_calc_result_one == id) {
            d(this.x, 1, true);
        }
        if (R.id.hcrl_calc_result_two == id) {
            d(this.bd, 2, true);
        }
        if (R.id.hcrl_calc_result_three == id) {
            d(this.bc, 3, true);
        }
        if (R.id.hcrl_calc_result_four == id) {
            d(this.h, 4, true);
        }
        if (R.id.hcrl_calc_result_five == id) {
            d(this.cg, 5, true);
        }
        if (R.id.hcrl_calc_result_six == id) {
            d(this.cb, 5, false);
        }
        if (R.id.layout_pace_range_distance == id) {
            av();
        }
        if (R.id.layout_pace_range_achievement == id) {
            ap();
        }
        if (R.id.hb_pace_range_calculation == id) {
            m();
        }
        if (R.id.hb_pace_range_apply == id) {
            h();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void m() {
        if (this.m == 0) {
            j();
            c(1);
        }
        if (this.m == 1) {
            i();
        }
    }

    private void h() {
        if (this.m == 0) {
            c(2);
        }
        if (ac()) {
            LogUtil.h("Track_PaceRangeActivity", "onClick apply button calc result has error data");
        } else if (this.al) {
            ax();
        } else {
            au();
        }
    }

    private void ag() {
        if (this.n == null) {
            LogUtil.h("Track_PaceRangeActivity", "saveCurrentPaceToDb mApplyConfig is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.15
                @Override // java.lang.Runnable
                public void run() {
                    cam.c(new int[]{PaceRangeActivity.this.n.j(), PaceRangeActivity.this.n.b(), PaceRangeActivity.this.n.d(), PaceRangeActivity.this.n.c(), PaceRangeActivity.this.n.e(), PaceRangeActivity.this.n.a()});
                }
            });
        }
    }

    private void d(Map map) {
        if (map != null) {
            ixx.d().d(this, AnalyticsValue.PACE_RANGE_2040084.value(), map, 0);
        } else {
            LogUtil.h("Track_PaceRangeActivity", "map is null");
        }
    }

    private void c(int i) {
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this, AnalyticsValue.PACE_RANGE_2040085.value(), hashMap, 0);
    }

    private void c() {
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        ixx.d().d(this, AnalyticsValue.PACE_RANGE_2040194.value(), hashMap, 0);
    }

    private void b(boolean z) {
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        if (z) {
            hashMap.put("type", 1);
        } else {
            hashMap.put("type", 2);
        }
        ixx.d().d(this, AnalyticsValue.PACE_RANGE_2040195.value(), hashMap, 0);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("Track_PaceRangeActivity", "onBackPressed");
        ae();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Track_PaceRangeActivity", "onDestroy");
        c(this.r);
        c(this.d);
        c(this.bv);
        c(this.bs);
        c(this.cm);
        c(this.aa);
        c(this.ch);
        c(this.bw);
        c(this.k);
        Handler handler = this.ac;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.ac = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BaseDialog baseDialog) {
        if (baseDialog == null || !baseDialog.isShowing()) {
            return;
        }
        baseDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        String string = getString(R.string._2130849885_res_0x7f02305d);
        if (string == null || !c(string) || ac()) {
            LogUtil.h("Track_PaceRangeActivity", "onBackJudgeNeedPrompt data is error");
            b((int[]) null);
            return;
        }
        if (this.cl != null) {
            LogUtil.h("Track_PaceRangeActivity", "onBackJudgeNeedPrompt mWarnTimeView is null");
            aw();
            return;
        }
        if (this.m == 1) {
            LogUtil.a("Track_PaceRangeActivity", "onBackJudgeNeedPrompt mCurrentFragmentPosition is run fragment");
            if (this.al) {
                b((int[]) null);
                return;
            } else if (e(this.bu)) {
                a(!this.aj);
                return;
            } else {
                e(true);
                return;
            }
        }
        if (this.al) {
            ax();
        } else if (e(this.j)) {
            a(!this.aj);
        } else {
            e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j(int i) {
        SparseArray<cad> sparseArray = this.y;
        if (sparseArray != null && sparseArray.get(i) != null) {
            return true;
        }
        LogUtil.h("Track_PaceRangeActivity", "isDistanceArrayKeyValid distanceType invalid");
        return false;
    }

    private String a(int i) {
        return getResources().getString(R.string._2130843969_res_0x7f021941, Integer.valueOf(i));
    }

    private void at() {
        if (this.bw == null) {
            View inflate = View.inflate(this, R.layout.item_pace_range_run_edit_view, null);
            this.ae = (HealthEditText) inflate.findViewById(R.id.het_edit_value);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.htv_edit_remark);
            healthTextView.setText(getString(R.string._2130844221_res_0x7f021a3d, new Object[]{String.valueOf(20.0f), String.valueOf(90.0f)}));
            a(this.ae, healthTextView);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
            this.bt = builder;
            builder.a(getString(R.string._2130844910_res_0x7f021cee)).czh_(inflate, 0, 0).czc_(R.string._2130845098_res_0x7f021daa, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.16
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czf_(getString(R.string._2130837648_res_0x7f020090), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.20
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    String trim = PaceRangeActivity.this.ae.getText().toString().trim();
                    float j = CommonUtils.j(trim);
                    if (j <= 90.0f && j >= 20.0f) {
                        LogUtil.a("Track_PaceRangeActivity", "showRunDataEditDialog edit run data is right");
                        PaceRangeActivity.this.br.d(trim);
                        PaceRangeActivity.this.p = j;
                        PaceRangeActivity paceRangeActivity = PaceRangeActivity.this;
                        paceRangeActivity.a(paceRangeActivity.z, true, 1);
                        PaceRangeActivity.this.bw.dismiss();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).b(false);
            this.bw = this.bt.e();
        }
        this.bw.show();
        this.ac.sendEmptyMessageDelayed(102, 300L);
    }

    private void a(HealthEditText healthEditText, final HealthTextView healthTextView) {
        healthEditText.setFilters(new InputFilter[]{new b(), new InputFilter.LengthFilter(4)});
        healthEditText.addTextChangedListener(new TextWatcher() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.19
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                float j = CommonUtils.j(editable.toString().trim());
                if (PaceRangeActivity.this.bt == null) {
                    LogUtil.h("Track_PaceRangeActivity", "setRunInputEditProperty addTextChangedListener mRunDataEditDialogBuilder is null");
                    return;
                }
                if (j > 90.0f || j < 20.0f) {
                    PaceRangeActivity.this.bt.a(false);
                    healthTextView.setTextColor(ContextCompat.getColor(PaceRangeActivity.this, R.color._2131296671_res_0x7f09019f));
                } else {
                    PaceRangeActivity.this.bt.a(true);
                    healthTextView.setTextColor(ContextCompat.getColor(PaceRangeActivity.this, R.color._2131299241_res_0x7f090ba9));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HealthEditText healthEditText) {
        if (healthEditText == null) {
            LogUtil.h("Track_PaceRangeActivity", "showSoftKeyboard inputEditText is null");
            return;
        }
        healthEditText.setFocusable(true);
        healthEditText.setFocusableInTouchMode(true);
        healthEditText.requestFocus();
        Object systemService = healthEditText.getContext().getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            ((InputMethodManager) systemService).showSoftInput(healthEditText, 0);
        }
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.PaceRangeResultFragment.OnResultItemClickListener
    public void onResultItemClick(int i) {
        if (i == 1) {
            av();
        }
        if (i == 2) {
            ap();
        }
    }

    @Override // com.huawei.ui.homehealth.runcard.trackfragments.PaceRangeRunFragment.OnRuntItemClickListener
    public void onRunItemClick(int i, final boolean z) {
        LogUtil.a("Track_PaceRangeActivity", "onRunItemClick isChecked is ", Boolean.valueOf(z));
        this.al = z;
        if (i == 1) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.17
                @Override // java.lang.Runnable
                public void run() {
                    cam.a(z);
                }
            });
            if (this.al) {
                ar();
            } else {
                aj();
            }
            a(this.z, false, 1);
            a(this.ad, false, 1);
            b(this.al);
        }
        if (i == 2) {
            if (!this.al) {
                at();
            } else {
                LogUtil.h("Track_PaceRangeActivity", "onRunItemClick auto calc toggle is close");
            }
        }
    }

    private boolean b(RunningStateIndexData runningStateIndexData) {
        if (runningStateIndexData == null) {
            LogUtil.h("Track_PaceRangeActivity", "hasRunningData data is null");
            return false;
        }
        RunningLevelCurrentData runningLevelCurrentData = runningStateIndexData.getRunningLevelCurrentData();
        if (runningLevelCurrentData == null) {
            LogUtil.h("Track_PaceRangeActivity", "hasRunningData runningLevelCurrentData is null");
            return false;
        }
        if (runningLevelCurrentData.getLastCurrentRunLevel() != 0.0f) {
            return true;
        }
        LogUtil.h("Track_PaceRangeActivity", "hasRunningData currentRunLevel is zero");
        return false;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.m = i;
        e(i);
        LogUtil.a("Track_PaceRangeActivity", "onPageSelected mCurrentFragmentPosition ", Integer.valueOf(this.m));
    }

    private void e(int i) {
        if (i == 0) {
            a(this.z, this.aq, i);
            a(this.ad, this.ar, i);
            d(this.j);
        }
        if (i == 1) {
            a(this.z, this.ax, i);
            a(this.ad, this.ao, i);
            d(this.bu);
            if (this.al) {
                ar();
            }
            c();
        }
    }

    private void ar() {
        if (this.n == null) {
            this.n = new ffg();
        }
        if (b(this.ca)) {
            float a2 = (float) UnitUtil.a(this.ca.getRunningLevelCurrentData().getLastCurrentRunLevel(), 1);
            this.p = a2;
            LogUtil.a("Track_PaceRangeActivity", "showAutoCalcPaceView user current running value is ", Float.valueOf(a2));
            b(this.n);
            am();
            ag();
        } else {
            LogUtil.a("Track_PaceRangeActivity", "showAutoCalcPaceView user has not running data");
            cam.c(this.bq);
        }
        aj();
    }

    private void i() {
        if (this.p == 0.0f) {
            LogUtil.h("Track_PaceRangeActivity", "calculatePaceByRun mCurrentRunValue is error");
            return;
        }
        ao();
        a(this.ad, true, 1);
        this.aj = false;
    }

    private void ao() {
        if (this.bu == null) {
            this.bu = new ffg();
        }
        b(this.bu);
        d(this.bu);
    }

    private void b(ffg ffgVar) {
        ffgVar.h(cam.e(0, this.p));
        ffgVar.e(cam.e(1, this.p));
        ffgVar.c(cam.e(2, this.p));
        ffgVar.a(cam.e(3, this.p));
        ffgVar.d(cam.e(4, this.p));
        ffgVar.b(cam.e(5, this.p));
    }

    private void d(ffg ffgVar) {
        if (ffgVar == null || !a(ffgVar)) {
            aj();
        } else {
            h(ffgVar);
            j(ffgVar);
        }
    }

    private void aj() {
        this.x.setText(getString(R.string._2130849885_res_0x7f02305d));
        this.bd.setText(getString(R.string._2130849885_res_0x7f02305d));
        this.bc.setText(getString(R.string._2130849885_res_0x7f02305d));
        this.h.setText(getString(R.string._2130849885_res_0x7f02305d));
        this.cg.setText(getString(R.string._2130849885_res_0x7f02305d));
        this.cb.setText(getString(R.string._2130849885_res_0x7f02305d));
        p();
    }

    private void h(ffg ffgVar) {
        this.x.setText(gvv.a((float) (ffgVar.j() * this.bj)));
        this.bd.setText(gvv.a((float) (ffgVar.b() * this.bj)));
        this.bc.setText(gvv.a((float) (ffgVar.d() * this.bj)));
        this.h.setText(gvv.a((float) (ffgVar.c() * this.bj)));
        this.cg.setText(gvv.a((float) (ffgVar.e() * this.bj)));
        this.cb.setText(gvv.a((float) (ffgVar.a() * this.bj)));
    }

    private void j(ffg ffgVar) {
        a(ffgVar.j(), this.n.j(), this.u);
        a(ffgVar.b(), this.n.b(), this.bi);
        a(ffgVar.d(), this.n.d(), this.be);
        a(ffgVar.c(), this.n.c(), this.f);
        a(ffgVar.e(), this.n.e(), this.ce);
        a(ffgVar.a(), this.n.a(), this.cf);
    }

    private void a(int i, int i2, HealthImageView healthImageView) {
        if (i == 0) {
            healthImageView.setVisibility(8);
            return;
        }
        if (i < i2) {
            healthImageView.setImageResource(R.drawable._2131430961_res_0x7f0b0e31);
            healthImageView.setVisibility(0);
        } else if (i > i2) {
            healthImageView.setImageResource(R.drawable._2131430957_res_0x7f0b0e2d);
            healthImageView.setVisibility(0);
        } else {
            healthImageView.setVisibility(8);
        }
    }

    private boolean a(ffg ffgVar) {
        if (ffgVar == null) {
            LogUtil.h("Track_PaceRangeActivity", "isValidPaceConfig config is null");
            return false;
        }
        if (ffgVar.j() == 0) {
            LogUtil.h("Track_PaceRangeActivity", "isValidPaceConfig config getRompedPaceZoneMinvalue is zero");
            return false;
        }
        if (ffgVar.b() == 0) {
            LogUtil.h("Track_PaceRangeActivity", "isValidPaceConfig config getMarathonPaceZoneMinValue is zero");
            return false;
        }
        if (ffgVar.d() == 0) {
            LogUtil.h("Track_PaceRangeActivity", "isValidPaceConfig config getLactatePaceZoneMinValue is zero");
            return false;
        }
        if (ffgVar.c() == 0) {
            LogUtil.h("Track_PaceRangeActivity", "isValidPaceConfig config getAnaerobicPaceZoneMinValue is zero");
            return false;
        }
        if (ffgVar.e() == 0) {
            LogUtil.h("Track_PaceRangeActivity", "isValidPaceConfig config getMaxOxygenPaceZoneMinValue is zero");
            return false;
        }
        if (ffgVar.a() != 0) {
            return true;
        }
        LogUtil.h("Track_PaceRangeActivity", "isValidPaceConfig config getMaxOxygenPaceZoneMaxValue is zero");
        return false;
    }

    private void p() {
        this.u.setVisibility(8);
        this.bi.setVisibility(8);
        this.be.setVisibility(8);
        this.f.setVisibility(8);
        this.ce.setVisibility(8);
        this.cf.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        int i = this.m;
        final ffg ffgVar = i == 0 ? this.j : null;
        if (i == 1) {
            ffgVar = this.bu;
        }
        if (!a(ffgVar)) {
            LogUtil.h("Track_PaceRangeActivity", "saveResultPaceRange config has invalid value");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.PaceRangeActivity.18
                @Override // java.lang.Runnable
                public void run() {
                    int[] iArr = {ffgVar.j(), ffgVar.b(), ffgVar.d(), ffgVar.c(), ffgVar.e(), ffgVar.a()};
                    cam.c(iArr);
                    if (PaceRangeActivity.this.ac == null) {
                        LogUtil.h("Track_PaceRangeActivity", "saveResultPaceRange mHandler is null");
                        return;
                    }
                    Message obtain = Message.obtain();
                    obtain.what = 101;
                    obtain.obj = iArr;
                    PaceRangeActivity.this.ac.sendMessage(obtain);
                }
            });
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private boolean f9511a;
        private int c;

        c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(int i) {
            this.c = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(boolean z) {
            this.f9511a = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int d() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean e() {
            return this.f9511a;
        }
    }

    static class b implements InputFilter {
        private b() {
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            int length = spanned.length();
            int i5 = 0;
            while (true) {
                if (i5 >= length) {
                    i5 = -1;
                    break;
                }
                char charAt = spanned.charAt(i5);
                if (".".equals(String.valueOf(charAt)) || ",".equals(String.valueOf(charAt))) {
                    break;
                }
                i5++;
            }
            if (i5 < 0) {
                return null;
            }
            if (".".equals(charSequence.toString().trim()) || ",".equals(charSequence.toString().trim()) || length - i5 > 1) {
                return "";
            }
            return null;
        }
    }

    private void ak() {
        int b2 = b(22);
        d(b2, this.w);
        d(b2, this.bl);
        d(b2, this.bg);
        d(b2, this.l);
        d(b2, this.ck);
        d(b2, this.cd);
    }

    private void d(int i, HealthTextView healthTextView) {
        healthTextView.setMaxWidth(i);
        e(healthTextView, i);
    }

    private int b(int i) {
        return ((hcl.a(this) - (qrp.a(this, 16.0f) * 2)) * i) / 100;
    }

    private int r() {
        return (b(30) - qrp.a(this, 10.0f)) - qrp.a(this, 22.0f);
    }

    private void e(HealthTextView healthTextView, float f) {
        float textSize = healthTextView.getTextSize();
        String obj = healthTextView.getText().toString();
        float measureText = healthTextView.getPaint().measureText(obj);
        if (measureText == 0.0f) {
            LogUtil.h("Track_PaceRangeActivity", "changeTextSize textViewWidth is zero");
            return;
        }
        if (measureText <= f) {
            LogUtil.h("Track_PaceRangeActivity", "changeTextSize textViewWidth less than textViewMaxWidth");
            return;
        }
        while (measureText > f) {
            textSize -= 1.0f;
            healthTextView.setTextSize(0, textSize);
            measureText = healthTextView.getPaint().measureText(obj);
            if (measureText == f) {
                break;
            }
        }
        healthTextView.setTextSize(0, textSize);
    }

    private void e(ffg ffgVar, ffg ffgVar2) {
        if (ffgVar == null || ffgVar2 == null) {
            LogUtil.h("Track_PaceRangeActivity", "checkResultValue applyConfig or resultConfig is null");
            return;
        }
        String string = getString(R.string._2130849885_res_0x7f02305d);
        if (c(string)) {
            LogUtil.h("Track_PaceRangeActivity", "checkResultValue all result value is not valid value");
            return;
        }
        if (this.x.getText().toString().trim().equals(string)) {
            ffgVar2.h(ffgVar.j());
        }
        if (this.bd.getText().toString().trim().equals(string)) {
            ffgVar2.e(ffgVar.b());
        }
        if (this.bc.getText().toString().trim().equals(string)) {
            ffgVar2.c(ffgVar.d());
        }
        if (this.h.getText().toString().trim().equals(string)) {
            ffgVar2.a(ffgVar.c());
        }
        if (this.cg.getText().toString().trim().equals(string)) {
            ffgVar2.d(ffgVar.e());
        }
        if (this.cb.getText().toString().trim().equals(string)) {
            ffgVar2.b(ffgVar.a());
        }
    }

    private boolean c(String str) {
        if (this.x.getText().toString().trim().equals(str)) {
            LogUtil.h("Track_PaceRangeActivity", "isAllResultHasValue easyRunUpResult is not valid value");
            return false;
        }
        if (this.bd.getText().toString().trim().equals(str)) {
            LogUtil.h("Track_PaceRangeActivity", "isAllResultHasValue marathonUpResult is not valid value");
            return false;
        }
        if (this.bc.getText().toString().trim().equals(str)) {
            LogUtil.h("Track_PaceRangeActivity", "isAllResultHasValue lacticAcidUpResult is not valid value");
            return false;
        }
        if (this.h.getText().toString().trim().equals(str)) {
            LogUtil.h("Track_PaceRangeActivity", "isAllResultHasValue anaerobicUpResult is not valid value");
            return false;
        }
        if (this.cg.getText().toString().trim().equals(str)) {
            LogUtil.h("Track_PaceRangeActivity", "isAllResultHasValue takeOxygenUpResult is not valid value");
            return false;
        }
        if (!this.cb.getText().toString().trim().equals(str)) {
            return true;
        }
        LogUtil.h("Track_PaceRangeActivity", "isAllResultHasValue takeOxygenLowResult is not valid value");
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
