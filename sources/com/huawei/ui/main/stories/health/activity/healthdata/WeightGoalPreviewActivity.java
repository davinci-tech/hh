package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalPreviewActivity;
import defpackage.ase;
import defpackage.gnm;
import defpackage.grz;
import defpackage.gsi;
import defpackage.jdl;
import defpackage.koq;
import defpackage.kot;
import defpackage.nrh;
import defpackage.qpz;
import defpackage.qsj;
import defpackage.rag;
import defpackage.rar;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class WeightGoalPreviewActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10102a;
    private LinearLayout aa;
    private LinearLayout ab;
    private LinearLayout ac;
    private HealthTextView ad;
    private HealthTextView ae;
    private LinearLayout af;
    private ConstraintLayout ai;
    private rar aj;
    private WeightTargetDifferences ak;
    private HealthTextView am;
    private gsi an;
    private HealthTextView b;
    private HealthTextView c;
    private IntPlan d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private double h;
    private HealthTextView i;
    private float j;
    private LinearLayout k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthButton n;
    private ImageView o;
    private HealthTextView p;
    private SpannableString r;
    private HealthTextView s;
    private RelativeLayout t;
    private ImageView u;
    private HealthTextView v;
    private View w;
    private HealthTextView x;
    private float y;
    private float z;
    private int ag = 0;
    private boolean q = false;
    private Handler ah = new b(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_weight_goal_preview);
        g();
        d();
    }

    private void g() {
        this.af = (LinearLayout) findViewById(R.id.hw_weight_management_goal_ll);
        this.k = (LinearLayout) findViewById(R.id.hw_weight_edit_go_back_ll);
        this.n = (HealthButton) findViewById(R.id.hw_weight_edit_go_back);
        this.x = (HealthTextView) findViewById(R.id.hw_weight_setup_new_goal);
        this.g = (HealthTextView) findViewById(R.id.hw_weight_edit_current_goal);
        this.ai = (ConstraintLayout) findViewById(R.id.weight_data_two_line);
        this.s = (HealthTextView) findViewById(R.id.hw_weight_goal_time_length_value);
        this.ae = (HealthTextView) findViewById(R.id.hw_weight_goal_cumulative_weight_value);
        this.v = (HealthTextView) findViewById(R.id.hw_weight_goal_time_length_text);
        this.ad = (HealthTextView) findViewById(R.id.hw_weight_goal_cumulative_weight_text);
        this.m = (HealthTextView) findViewById(R.id.hw_weight_every_week_weight_text);
        this.i = (HealthTextView) findViewById(R.id.hw_weight_every_week_weight_value);
        this.f = (HealthTextView) findViewById(R.id.hw_weight_every_week_heat_gap_text);
        this.l = (HealthTextView) findViewById(R.id.hw_weight_every_week_heat_gap_value);
        c();
        this.w = findViewById(R.id.static_chart_layout);
        this.u = (ImageView) findViewById(R.id.static_chart_image);
        this.t = (RelativeLayout) findViewById(R.id.data_loading);
        this.am = (HealthTextView) findViewById(R.id.weight_type);
        this.ab = (LinearLayout) findViewById(R.id.tips_one);
        this.aa = (LinearLayout) findViewById(R.id.tips_two);
        this.ac = (LinearLayout) findViewById(R.id.tips_three);
        this.c = (HealthTextView) findViewById(R.id.chart_low_value);
        this.f10102a = (HealthTextView) findViewById(R.id.chart_high_value);
        this.e = (HealthTextView) findViewById(R.id.chart_start_date);
        this.p = (HealthTextView) findViewById(R.id.currentPoint);
        this.o = (ImageView) findViewById(R.id.hw_show_finish_date_suggestion_icon);
        this.b = (HealthTextView) findViewById(R.id.chart_end_date);
        this.x.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
    }

    private void c() {
        rar rarVar = new rar(findViewById(R.id.weight_goal_line_chart).getRootView(), null);
        this.aj = rarVar;
        rarVar.e("bodyWeight");
    }

    private void d() {
        if (getIntent() == null) {
            LogUtil.h("WeightGoalPreviewActivity", "intent is null, return");
            return;
        }
        if (getIntent().getExtras() == null) {
            LogUtil.h("WeightGoalPreviewActivity", "extra is null, return");
            return;
        }
        Bundle extras = getIntent().getExtras();
        if (AppRouterUtils.zs_(getIntent()) != null && AuthorizationUtils.a(this)) {
            this.ag = 1;
        } else {
            this.ag = extras.getInt("WEIGHT_GOAL_ACTION", 0);
        }
        this.y = extras.getFloat("startWeight");
        this.z = extras.getFloat("targetWeight");
        this.q = extras.getBoolean("IS_WEIGHT_CURRENT_GOAL_EDIT_TYPE", false);
        this.ak = (WeightTargetDifferences) extras.getParcelable("WEIGHT_TARGET_DIFFERENCES");
        if (this.ag == 0) {
            this.af.setVisibility(8);
            this.k.setVisibility(0);
            Serializable serializable = extras.getSerializable("weightManager");
            if (serializable instanceof gsi) {
                this.an = (gsi) serializable;
            }
            LogUtil.a("WeightGoalPreviewActivity", "initData mWeightManager ", this.an);
            nrh.b(BaseApplication.getContext(), this.q ? R$string.IDS_hwh_home_target_adjustment_succeeded : R$string.IDS_hwh_home_target_set_success);
        } else {
            this.af.setVisibility(0);
            this.k.setVisibility(8);
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: qfv
            @Override // java.lang.Runnable
            public final void run() {
                WeightGoalPreviewActivity.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        i();
        e();
    }

    private void b(WeightTargetDifferences weightTargetDifferences) {
        long currentTimeMillis = System.currentTimeMillis();
        HiAggregateOption a2 = grz.a("");
        a2.setTimeRange(jdl.t(weightTargetDifferences.e()), currentTimeMillis);
        a2.setGroupUnitType(3);
        a2.setAggregateType(3);
        a2.setSortOrder(0);
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).aggregateHiHealthData(a2, new a(this, weightTargetDifferences));
    }

    /* loaded from: classes6.dex */
    static class a implements HiAggregateListener {
        private final WeightTargetDifferences b;
        private final WeakReference<WeightGoalPreviewActivity> d;

        a(WeightGoalPreviewActivity weightGoalPreviewActivity, WeightTargetDifferences weightTargetDifferences) {
            this.d = new WeakReference<>(weightGoalPreviewActivity);
            this.b = weightTargetDifferences;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            WeightGoalPreviewActivity weightGoalPreviewActivity = this.d.get();
            if (weightGoalPreviewActivity != null) {
                weightGoalPreviewActivity.b((List<Pair<Long, Float>>) weightGoalPreviewActivity.e(list, weightGoalPreviewActivity.y, this.b.e()));
            } else {
                LogUtil.b("WeightGoalPreviewActivity", "InnerViewHiAggregateListener activity == null");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            WeightGoalPreviewActivity weightGoalPreviewActivity = this.d.get();
            if (weightGoalPreviewActivity == null) {
                LogUtil.b("WeightGoalPreviewActivity", "InnerViewHiAggregateListener activity == null");
            } else {
                LogUtil.h("WeightGoalPreviewActivity", "getChartData intentType");
                weightGoalPreviewActivity.b(new ArrayList());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<Pair<Long, Float>> list) {
        Message obtainMessage = this.ah.obtainMessage();
        obtainMessage.what = 10;
        obtainMessage.obj = list;
        this.ah.sendMessage(obtainMessage);
    }

    private void e() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi != null) {
            planApi.b(new c(this));
        }
    }

    private void i() {
        if (this.an == null) {
            kot.a().b(new e(this));
        } else {
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.ak == null) {
            this.ak = rag.b();
        }
        rag.d(new d(this));
    }

    /* loaded from: classes6.dex */
    public static class d implements ResponseCallback<HashMap<String, Double>> {
        private WeakReference<WeightGoalPreviewActivity> e;

        d(WeightGoalPreviewActivity weightGoalPreviewActivity) {
            this.e = new WeakReference<>(weightGoalPreviewActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, final HashMap<String, Double> hashMap) {
            final WeightGoalPreviewActivity weightGoalPreviewActivity = this.e.get();
            if (weightGoalPreviewActivity == null || weightGoalPreviewActivity.isDestroyed() || weightGoalPreviewActivity.isFinishing()) {
                LogUtil.h("WeightGoalPreviewActivity", "InnerGetWeightCallback activity is null");
            } else if (hashMap == null) {
                LogUtil.h("WeightGoalPreviewActivity", "InnerGetWeightCallback map is null");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: qfu
                    @Override // java.lang.Runnable
                    public final void run() {
                        WeightGoalPreviewActivity.d.a(hashMap, weightGoalPreviewActivity);
                    }
                });
            }
        }

        public static /* synthetic */ void a(HashMap hashMap, WeightGoalPreviewActivity weightGoalPreviewActivity) {
            float f;
            float f2;
            Double d = (Double) hashMap.get("targetWeight");
            if (d != null) {
                if (weightGoalPreviewActivity.z > 0.0f) {
                    f2 = weightGoalPreviewActivity.z;
                } else {
                    f2 = d.floatValue();
                }
                weightGoalPreviewActivity.z = f2;
            }
            Double d2 = (Double) hashMap.get("startWeight");
            if (d2 != null) {
                if (weightGoalPreviewActivity.y > 0.0f) {
                    f = weightGoalPreviewActivity.y;
                } else {
                    f = d2.floatValue();
                }
                weightGoalPreviewActivity.y = f;
            }
            weightGoalPreviewActivity.j = grz.a();
            LogUtil.a("WeightGoalPreviewActivity", "initWeightGoal mTargetWeight: ", Float.valueOf(weightGoalPreviewActivity.z), " mStartWeight :", Float.valueOf(weightGoalPreviewActivity.y), " mCurrentWeight: ", Float.valueOf(weightGoalPreviewActivity.j));
            weightGoalPreviewActivity.a(weightGoalPreviewActivity.ak);
        }
    }

    /* loaded from: classes6.dex */
    static class e implements ResponseCallback<gsi> {
        private WeakReference<WeightGoalPreviewActivity> b;

        e(WeightGoalPreviewActivity weightGoalPreviewActivity) {
            this.b = new WeakReference<>(weightGoalPreviewActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, gsi gsiVar) {
            WeightGoalPreviewActivity weightGoalPreviewActivity = this.b.get();
            if (weightGoalPreviewActivity == null) {
                LogUtil.h("WeightGoalPreviewActivity", "InnerReadWeightManagerCallBack activity is null");
                return;
            }
            LogUtil.a("WeightGoalPreviewActivity", "InnerGetWeightManager weightManager ", gsiVar);
            if (gsiVar == null) {
                weightGoalPreviewActivity.j();
            } else {
                weightGoalPreviewActivity.an = gsiVar;
                weightGoalPreviewActivity.j();
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.n) {
            finish();
        }
        if (view == this.m && this.q) {
            nrh.b(BaseApplication.getContext(), R$string.IDS_hwh_home_overall_target_calculation);
        }
        IntPlan intPlan = this.d;
        if (intPlan != null && intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            qsj.b(this.d.getPlanId(), (Context) this, true);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.g) {
            Intent intent = new Intent(this, (Class<?>) WeightGoalActivity.class);
            intent.putExtra("WEIGHT_GOAL_ACTION", 1);
            intent.putExtra("from", 0);
            gnm.aPB_(BaseApplication.getActivity(), intent);
            finish();
        }
        if (view == this.x) {
            Intent intent2 = new Intent(this, (Class<?>) WeightGoalActivity.class);
            intent2.putExtra("WEIGHT_GOAL_ACTION", 0);
            intent2.putExtra("from", 0);
            gnm.aPB_(BaseApplication.getActivity(), intent2);
            finish();
        }
        if (view == this.o) {
            qpz.e(this);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void a(final WeightTargetDifferences weightTargetDifferences) {
        if (weightTargetDifferences == null) {
            LogUtil.h("WeightGoalPreviewActivity", "weightTargetDifferences == null");
            return;
        }
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: qfw
                @Override // java.lang.Runnable
                public final void run() {
                    WeightGoalPreviewActivity.this.a(weightTargetDifferences);
                }
            });
            return;
        }
        boolean isFinishing = isFinishing();
        boolean isDestroyed = isDestroyed();
        if (isFinishing || isDestroyed) {
            ReleaseLogUtil.d("WeightGoalPreviewActivity", "initDataView isFinishing ", Boolean.valueOf(isFinishing), " isDestroyed ", Boolean.valueOf(isDestroyed));
            return;
        }
        LogUtil.a("WeightGoalPreviewActivity", "weightTargetDifferences:", weightTargetDifferences.toString());
        this.t.setVisibility(8);
        this.h = Math.abs(UnitUtil.a(weightTargetDifferences.a() * 7.7d, 1));
        if (weightTargetDifferences.a() == 0.0d) {
            LogUtil.a("WeightGoalPreviewActivity", "initDataView keepWeight");
            b(getString(R$string.IDS_hwh_home_weight_loss_mild_tips), "", "");
            c(weightTargetDifferences);
            a(R.drawable._2131429855_res_0x7f0b09df, weightTargetDifferences);
            return;
        }
        if (weightTargetDifferences.a() < 0.0d) {
            LogUtil.a("WeightGoalPreviewActivity", "initDataView addWeight");
            b(getString(R$string.IDS_hwh_home_weight_loss_mild_tips), "", "");
            d(weightTargetDifferences);
            a(LanguageUtil.bc(this) ? R.drawable._2131429854_res_0x7f0b09de : R.drawable._2131429853_res_0x7f0b09dd, weightTargetDifferences);
            return;
        }
        LogUtil.a("WeightGoalPreviewActivity", "initDataView lossWeight");
        g(weightTargetDifferences);
        h();
        a(LanguageUtil.bc(this) ? R.drawable._2131429857_res_0x7f0b09e1 : R.drawable._2131429856_res_0x7f0b09e0, weightTargetDifferences);
    }

    private void g(WeightTargetDifferences weightTargetDifferences) {
        this.am.setText(R$string.IDS_health_reduce_weight);
        this.v.setText(R$string.IDS_hwh_home_target_duration);
        this.ad.setText(R$string.IDS_hwh_home_cumulative_weight_loss);
        this.m.setText(R$string.IDS_hwh_home_weekly_weight_loss);
        this.f.setText(R$string.IDS_hwh_home_daily_caloric_gap);
        if (this.q) {
            this.m.setText(R$string.IDS_hwh_home_avg_weekly_weight_loss);
            b();
        }
        gsi gsiVar = this.an;
        if (gsiVar != null && gsiVar.g() == 1) {
            int c2 = this.an.c();
            if (c2 != 0) {
                this.h = c2;
            }
            this.r = dBO_(qsj.c(this.an.b().a(), this.j));
        }
        f(weightTargetDifferences);
    }

    private SpannableString dBO_(double[] dArr) {
        String string = getResources().getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(dArr[0], 1, 1), UnitUtil.e(dArr[1], 1, 1));
        SpannableString spannableString = new SpannableString(getResources().getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(dArr[0], 1, 1), qsj.e(dArr[1], 1)));
        if (string.length() < spannableString.length()) {
            spannableString.setSpan(new TextAppearanceSpan(this, R.style.health_text_chart_cursor_sub_value), 0, string.length(), 33);
            spannableString.setSpan(new TextAppearanceSpan(this, R.style.week_day_text_style), string.length(), spannableString.length(), 33);
        }
        return spannableString;
    }

    private void b() {
        this.v.setText(R$string.IDS_hwh_home_total_target_duration);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable._2131430493_res_0x7f0b0c5d);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        this.o.setVisibility(0);
    }

    private void h() {
        b(getString(R$string.IDS_hwh_home_weight_loss_mild_tips), "", "");
    }

    private void b(String str, String str2, String str3) {
        TextView textView = (TextView) this.ab.findViewById(R.id.tips_weight);
        if (TextUtils.isEmpty(str)) {
            this.ab.setVisibility(8);
        } else {
            this.ab.setVisibility(0);
            if (textView != null) {
                textView.setText(str);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            this.aa.setVisibility(8);
            this.ac.setVisibility(8);
            return;
        }
        ((TextView) this.aa.findViewById(R.id.tips_weight)).setText(str2);
        if (TextUtils.isEmpty(str3)) {
            this.ac.setVisibility(8);
        } else {
            ((TextView) this.ac.findViewById(R.id.tips_weight)).setText(str3);
        }
    }

    private String b(double d2) {
        return qsj.e(a(d2), 1);
    }

    private void c(WeightTargetDifferences weightTargetDifferences) {
        this.am.setText(R$string.IDS_health_hold_weight);
        this.ai.setVisibility(8);
        this.v.setText(R$string.IDS_hwh_home_current_weight);
        this.ad.setText(R$string.IDS_hwh_home_retention_range);
        this.s.setText(dBP_(b(this.j)));
        this.ae.setText(dBQ_(getResources().getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(UnitUtil.c(d(weightTargetDifferences, true), 1), 1, 1), b(d(weightTargetDifferences, false))), "\\d+-\\d+|\\d+"));
        this.p.setText(UnitUtil.e(UnitUtil.a(this.j), 1, 1));
    }

    private void a(int i, WeightTargetDifferences weightTargetDifferences) {
        if (weightTargetDifferences == null) {
            LogUtil.b("WeightGoalPreviewActivity", "weightTargetDifferences null");
            return;
        }
        if (this.ag == 1) {
            LogUtil.a("WeightGoalPreviewActivity", "setStaticChartView WEIGHT_GOAL_PREVIEW_ACTION_PREVIEW");
            this.w.setVisibility(8);
            b(weightTargetDifferences);
            return;
        }
        LogUtil.a("WeightGoalPreviewActivity", "setStaticChartView weightTargetDifferences:", weightTargetDifferences.toString());
        float d2 = d(weightTargetDifferences, true);
        float d3 = d(weightTargetDifferences, false);
        if (weightTargetDifferences.d() == WeightTargetDifferences.WeightTargetType.WEIGHT_KEE) {
            a(d2, d3);
            this.b.setVisibility(8);
        }
        this.w.setVisibility(0);
        rar rarVar = this.aj;
        if (rarVar != null) {
            rarVar.a(new ArrayList());
        }
        this.u.setImageResource(i);
        String e2 = UnitUtil.e(a(d2), 1, 1);
        String e3 = UnitUtil.e(a(d3), 1, 1);
        this.c.setText(e2);
        this.f10102a.setText(e3);
        this.e.setText(HiDateUtil.i(weightTargetDifferences.e()));
        this.b.setText(HiDateUtil.i(weightTargetDifferences.b()));
    }

    private void a(float f, float f2) {
        double d2 = f2 - f;
        double d3 = f2 - this.j;
        int i = 0;
        this.p.setVisibility(0);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        float f3 = this.j;
        if (f3 > f2) {
            d3 = 0.0d;
        } else if (f3 < f) {
            i = dimensionPixelOffset + dimensionPixelOffset;
            d3 = d2;
        } else {
            i = dimensionPixelOffset;
        }
        double dimension = getResources().getDimension(R.dimen._2131362560_res_0x7f0a0300) / d2;
        if (this.p.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.p.getLayoutParams();
            layoutParams.topMargin = ((int) (d3 * dimension)) + i;
            this.p.setLayoutParams(layoutParams);
        }
    }

    private void d(WeightTargetDifferences weightTargetDifferences) {
        this.am.setText(R$string.IDS_health_gain_weight);
        this.v.setText(R$string.IDS_hwh_home_target_duration);
        this.ad.setText(R$string.IDS_hwh_home_cumulative_weight_increase);
        this.m.setText(R$string.IDS_hwh_home_weekly_weight_increase);
        this.f.setText(R$string.IDS_hwh_home_daily_caloric_surplus);
        f(weightTargetDifferences);
        if (this.q) {
            this.m.setText(R$string.IDS_hwh_home_avg_weekly_weight_increase);
            b();
        }
    }

    private int c(IntPlan intPlan) {
        if (intPlan == null || intPlan.getMetaInfo() == null || intPlan.getPlanType() != IntPlan.PlanType.AI_FITNESS_PLAN) {
            return 0;
        }
        return ase.e(intPlan);
    }

    private void f(WeightTargetDifferences weightTargetDifferences) {
        int c2 = c(this.d);
        if (!CommonUtil.c(weightTargetDifferences.b()) || c2 <= 0) {
            c2 = rag.d(weightTargetDifferences, this.y, this.z);
        }
        if (this.ag == 1 || this.q) {
            c(weightTargetDifferences, c2, jdl.d(DateFormatUtil.b(weightTargetDifferences.e()), DateFormatUtil.b(System.currentTimeMillis())));
        } else {
            this.s.setText(dBP_(getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, c2, d(c2))));
            this.ae.setText(dBP_(b(Math.abs(this.z - this.y))));
        }
        this.i.setText(dBN_(UnitUtil.a((weightTargetDifferences.a() / 1000.0d) * 7.0d, 1)));
        if (!TextUtils.isEmpty(this.r)) {
            this.i.setText(this.r);
        }
        int a2 = (int) UnitUtil.a(this.h, 0);
        this.l.setText(dBP_(getResources().getQuantityString(R.plurals._2130903083_res_0x7f03002b, a2, d(a2))));
    }

    private SpannableString dBN_(double d2) {
        String e2 = UnitUtil.e(a(Math.abs(d2)), 1, 1);
        SpannableString spannableString = new SpannableString(b(Math.abs(d2)));
        if (e2.length() <= spannableString.length()) {
            spannableString.setSpan(new TextAppearanceSpan(this, R.style.health_text_chart_cursor_sub_value), 0, e2.length(), 33);
            spannableString.setSpan(new TextAppearanceSpan(this, R.style.week_day_text_style), e2.length(), spannableString.length(), 33);
        }
        return spannableString;
    }

    private void c(WeightTargetDifferences weightTargetDifferences, int i, int i2) {
        double a2;
        if (weightTargetDifferences.d() == WeightTargetDifferences.WeightTargetType.WEIGHT_GAIN) {
            a2 = a(this.j - this.y);
        } else {
            a2 = a(this.y - this.j);
        }
        if (CommonUtil.c(weightTargetDifferences.b())) {
            this.s.setText(dBP_(getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, d(i))));
        } else {
            c(d(i2), getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, i, d(i)), this.s);
        }
        c(UnitUtil.e(a2, 1, 1), b(Math.abs(this.z - this.y)), this.ae);
    }

    private void c(String str, String str2, HealthTextView healthTextView) {
        healthTextView.setText(UnitUtil.bCR_(this, "/[^/]+$", getResources().getString(R$string.IDS_hwh_home_range, str, str2), R.style.week_day_text_style, R.style.health_text_chart_cursor_sub_value));
    }

    private double a(double d2) {
        return UnitUtil.c(d2, 1);
    }

    private String d(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    private SpannableString dBP_(String str) {
        return dBQ_(str, "\\d");
    }

    private SpannableString dBQ_(String str, String str2) {
        return UnitUtil.bCR_(this, str2, str, R.style.health_text_chart_cursor_sub_value, R.style.week_day_text_style);
    }

    private float d(WeightTargetDifferences weightTargetDifferences, boolean z) {
        if (weightTargetDifferences == null) {
            LogUtil.b("WeightGoalPreviewActivity", "weightTargetDifferences == null");
            return 0.0f;
        }
        if (weightTargetDifferences.d() == WeightTargetDifferences.WeightTargetType.WEIGHT_GAIN) {
            return z ? this.y : this.z;
        }
        if (weightTargetDifferences.d() == WeightTargetDifferences.WeightTargetType.WEIGHT_LOSS) {
            return z ? this.z : this.y;
        }
        return d(weightTargetDifferences, z, this.z);
    }

    private float d(WeightTargetDifferences weightTargetDifferences, boolean z, float f) {
        if (weightTargetDifferences.c() == 0) {
            return z ? f - 1.0f : f + 1.0f;
        }
        if (z) {
            return f - (weightTargetDifferences.c() / 1000.0f);
        }
        return f + (weightTargetDifferences.c() / 1000.0f);
    }

    /* loaded from: classes6.dex */
    static class c extends UiCallback<IntPlan> {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<WeightGoalPreviewActivity> f10104a;

        c(WeightGoalPreviewActivity weightGoalPreviewActivity) {
            this.f10104a = new WeakReference<>(weightGoalPreviewActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            WeightGoalPreviewActivity weightGoalPreviewActivity = this.f10104a.get();
            if (weightGoalPreviewActivity == null || weightGoalPreviewActivity.isFinishing() || weightGoalPreviewActivity.isDestroyed()) {
                LogUtil.h("WeightGoalPreviewActivity", "GetIntPlanCallback onFailure activity is not exist");
                return;
            }
            LogUtil.b("WeightGoalPreviewActivity", "getCurrentPlan onFailure");
            weightGoalPreviewActivity.d = null;
            weightGoalPreviewActivity.a(weightGoalPreviewActivity.ak);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(IntPlan intPlan) {
            WeightGoalPreviewActivity weightGoalPreviewActivity = this.f10104a.get();
            if (weightGoalPreviewActivity == null || weightGoalPreviewActivity.isFinishing() || weightGoalPreviewActivity.isDestroyed()) {
                LogUtil.h("WeightGoalPreviewActivity", "GetIntPlanCallback onSuccess activity is not exist");
                return;
            }
            if (intPlan != null && intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
                weightGoalPreviewActivity.d = intPlan;
            } else {
                LogUtil.h("WeightGoalPreviewActivity", "getCurrentIntPlan onSuccess intPlan is not exist");
                weightGoalPreviewActivity.d = null;
            }
            weightGoalPreviewActivity.a(weightGoalPreviewActivity.ak);
        }
    }

    /* loaded from: classes6.dex */
    public class b extends BaseHandler<WeightGoalPreviewActivity> {
        b(WeightGoalPreviewActivity weightGoalPreviewActivity) {
            super(weightGoalPreviewActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dBR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeightGoalPreviewActivity weightGoalPreviewActivity, Message message) {
            ArrayList arrayList;
            if (weightGoalPreviewActivity == null) {
                LogUtil.h("WeightGoalPreviewActivity", "handleMessageWhenReferenceNotNull obj is null");
                return;
            }
            int i = message.what;
            if (i != 10) {
                if (i == 18) {
                    if (WeightGoalPreviewActivity.this.aj != null) {
                        WeightGoalPreviewActivity.this.aj.a(new ArrayList());
                        return;
                    }
                    return;
                }
                LogUtil.h("WeightGoalPreviewActivity", "other msg");
                return;
            }
            if (koq.e(message.obj, Pair.class)) {
                arrayList = (ArrayList) message.obj;
            } else {
                LogUtil.h("WeightGoalPreviewActivity", "msg.obj is not instanceof List Pair");
                arrayList = new ArrayList();
            }
            if (WeightGoalPreviewActivity.this.aj != null) {
                WeightGoalPreviewActivity.this.aj.a(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Pair<Long, Float>> e(List<HiHealthData> list, double d2, long j) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            float a2 = (float) UnitUtil.a(hiHealthData.getDouble("bodyWeight"));
            if (a2 > 0.0f) {
                arrayList.add(new Pair<>(Long.valueOf(hiHealthData.getStartTime()), Float.valueOf(a2)));
            }
        }
        a(arrayList, d2, j);
        return arrayList;
    }

    private void a(List<Pair<Long, Float>> list, double d2, long j) {
        if (j == 0) {
            LogUtil.a("WeightGoalPreviewActivity", "startTime is 0, return");
            return;
        }
        if (koq.b(list)) {
            LogUtil.a("WeightGoalPreviewActivity", "trendData List is empty");
        } else if (jdl.d(((Long) list.get(0).first).longValue(), j)) {
            LogUtil.a("WeightGoalPreviewActivity", "first weight and start time is same day, return");
        } else {
            list.add(0, new Pair<>(Long.valueOf(j), Float.valueOf((float) UnitUtil.a(d2))));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
