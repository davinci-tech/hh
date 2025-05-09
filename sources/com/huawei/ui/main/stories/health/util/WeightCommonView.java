package com.huawei.ui.main.stories.health.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.util.WeightCommonView;
import com.huawei.ui.main.stories.health.views.HealthBodyBarData;
import com.huawei.ui.main.stories.health.views.HealthBodyDetailData;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.cpa;
import defpackage.cps;
import defpackage.doj;
import defpackage.dph;
import defpackage.ixx;
import defpackage.nld;
import defpackage.nrs;
import defpackage.nsy;
import defpackage.qgn;
import defpackage.qrj;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class WeightCommonView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthColumnSystem f10259a;
    private LinearLayout b;
    private Context c;
    private LinearLayout d;
    private boolean e;
    private HealthSubHeader f;
    private LinearLayout g;
    private HealthSubHeader h;
    private LinearLayout i;
    private LinearLayout j;
    private cfe l;
    private LinearLayout o;

    public WeightCommonView(Context context) {
        this(context, null);
    }

    public WeightCommonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        b();
    }

    private void b() {
        if (this.c == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initView Context is null");
            return;
        }
        this.f10259a = new HealthColumnSystem(this.c);
        LayoutInflater layoutInflater = (LayoutInflater) this.c.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initView LayoutInflater is null");
            return;
        }
        View inflate = layoutInflater.inflate(R.layout.health_weight_body_view, this);
        this.f = (HealthSubHeader) nsy.cMd_(inflate, R.id.base_weight_segmental_fat_sub_header);
        this.g = (LinearLayout) nsy.cMd_(inflate, R.id.base_weight_segmental_fat);
        this.h = (HealthSubHeader) nsy.cMd_(inflate, R.id.base_weight_segmental_skeletal_muscle_sub_header);
        this.i = (LinearLayout) nsy.cMd_(inflate, R.id.base_weight_segmental_skeletal_muscle);
        this.d = (LinearLayout) nsy.cMd_(inflate, R.id.base_weight_body_analysis_layout);
        this.b = (LinearLayout) nsy.cMd_(inflate, R.id.base_weight_peer_comparison_layout);
        this.o = (LinearLayout) nsy.cMd_(inflate, R.id.base_weight_suggest_layout);
        this.j = (LinearLayout) nsy.cMd_(inflate, R.id.base_weight_age_suggest_layout);
        d();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        d();
        super.onConfigurationChanged(configuration);
    }

    private void d() {
        Context context;
        HealthColumnSystem healthColumnSystem = this.f10259a;
        if (healthColumnSystem == null || (context = this.c) == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "resetColumnLayoutParams Context or mHealthColumnSystem is null");
            return;
        }
        healthColumnSystem.e(context);
        int dimension = (int) this.c.getResources().getDimension(R.dimen._2131363209_res_0x7f0a0589);
        if (this.f10259a.f() > 4) {
            this.f10259a.e(0);
            dimension = (nrs.c(this.c) - this.f10259a.h()) / 2;
        }
        dHO_(this.g.getLayoutParams(), dimension, this.g);
        dHO_(this.i.getLayoutParams(), dimension, this.i);
    }

    private void dHO_(ViewGroup.LayoutParams layoutParams, int i, LinearLayout linearLayout) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.setMarginStart(i);
            marginLayoutParams.setMarginEnd(i);
            linearLayout.setLayoutParams(marginLayoutParams);
        }
    }

    public void setWeightCommonView(cfe cfeVar, boolean z, boolean z2) {
        this.e = z;
        this.l = cfeVar;
        if (cfeVar == null || this.c == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "WeightBean or mContext is null.");
        } else {
            a(z2);
        }
    }

    private void a(boolean z) {
        if (!z) {
            LogUtil.a("HealthWeight_WeightCommonView", "not expend, not initWeightCommonView");
            return;
        }
        e(this.l);
        if (this.l.ax() <= 0.0d || !this.l.isVisible(1, this.e) || !qsj.e(this.l, this.e)) {
            this.f.setVisibility(8);
            this.g.setVisibility(8);
            this.h.setVisibility(8);
            this.i.setVisibility(8);
            this.d.setVisibility(8);
            this.b.setVisibility(8);
            LogUtil.a("HealthWeight_WeightCommonView", "setWeightCommonView mSegmentalFat mSegmentalSkeletalMuscle isGone");
            return;
        }
        this.d.setVisibility(0);
        this.b.setVisibility(0);
        this.o.setVisibility(0);
        b(this.l);
        c(this.l);
        d(this.l);
        h(this.l);
    }

    private void d(cfe cfeVar) {
        if (j(cfeVar) && !Utils.o()) {
            d("peers");
            this.b.setVisibility(0);
            HealthSubHeader healthSubHeader = (HealthSubHeader) this.b.findViewById(R.id.peer_comparison_health_subheader);
            if (healthSubHeader != null) {
                healthSubHeader.setSubHeaderBackgroundColor(0);
            }
            qrj.a(this.c, cfeVar, (HealthBodyBarData) this.b.findViewById(R.id.healthBodyBarData));
            nsy.cMr_((HealthTextView) this.b.findViewById(R.id.peer_comparison_health_summary), qrj.c(cfeVar));
            return;
        }
        this.b.setVisibility(8);
        LogUtil.a("HealthWeight_WeightCommonView", "showPeerComparisonChart mPeerComparisonLayout isGone");
    }

    private void b(cfe cfeVar) {
        if (qsj.a(cfeVar)) {
            this.g.setVisibility(0);
            g(cfeVar);
        } else {
            this.f.setVisibility(8);
            this.g.setVisibility(8);
            LogUtil.a("HealthWeight_WeightCommonView", "setWeightCommonView mSegmentalFat isGone");
        }
        if (qsj.b(cfeVar)) {
            this.i.setVisibility(0);
            a(cfeVar);
        } else {
            this.h.setVisibility(8);
            this.i.setVisibility(8);
            LogUtil.a("HealthWeight_WeightCommonView", "setWeightCommonView mSegmentalSkeletalMuscle isGone");
        }
    }

    private void e(cfe cfeVar) {
        boolean e;
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initWeightAgeSuggest weightBean is null.");
            return;
        }
        int e2 = cfeVar.e();
        String weightScaleProductId = cfeVar.getWeightScaleProductId();
        int i = 81;
        int i2 = 18;
        if (cpa.v(weightScaleProductId) && !this.e) {
            if (Utils.o()) {
                e = qrp.e(e2);
            } else {
                e = qrp.d(e2);
                i2 = 6;
            }
        } else if (cpa.w(weightScaleProductId) || cpa.r(weightScaleProductId) || (cpa.v(weightScaleProductId) && this.e)) {
            e = qrp.e(e2);
        } else {
            e = qrp.b(e2);
            i = 66;
        }
        if (e) {
            this.j.setVisibility(8);
            LogUtil.a("HealthWeight_WeightCommonView", "initWeightAgeSuggest age is over limit");
            return;
        }
        int l = cfeVar.l();
        if (l <= 0 || (cfeVar instanceof qgn)) {
            this.j.setVisibility(8);
            LogUtil.a("HealthWeight_WeightCommonView", "initWeightAgeSuggest dataType is illegal");
        } else {
            LogUtil.c("HealthWeight_WeightCommonView", "initWeightAgeSuggest age = ", Integer.valueOf(e2), ", dataType = ", Integer.valueOf(l));
            ((HealthTextView) this.j.findViewById(R.id.weight_result_suggest_age_tips)).setText(this.c.getResources().getString(R$string.IDS_device_result_data_analysis_age_tips, Integer.valueOf(i2), Integer.valueOf(i - 1)));
            this.j.setVisibility(0);
        }
    }

    private String c(int i, double d) {
        return this.c.getResources().getString(i, qsj.e(UnitUtil.a(d), 1));
    }

    private void g(final cfe cfeVar) {
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser.p()) {
            b(cfeVar, currentUser.g());
        } else {
            MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: qrs
                @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    WeightCommonView.this.d(cfeVar, i, (cfi) obj);
                }
            });
        }
    }

    public /* synthetic */ void d(final cfe cfeVar, final int i, final cfi cfiVar) {
        Context context = this.c;
        if (!(context instanceof Activity)) {
            LogUtil.h("HealthWeight_WeightCommonView", "mContext is null or mContext is not Activity");
        } else {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: qru
                @Override // java.lang.Runnable
                public final void run() {
                    WeightCommonView.this.e(cfiVar, i, cfeVar);
                }
            });
        }
    }

    public /* synthetic */ void e(cfi cfiVar, int i, cfe cfeVar) {
        if (cfiVar == null || i != 0) {
            LogUtil.h("HealthWeight_WeightCommonView", "loadDataSuccess getCurrentUser: currentUser is null return");
            cfiVar = MultiUsersManager.INSTANCE.getCurrentUser();
        }
        b(cfeVar, cfiVar.g());
    }

    private void b(cfe cfeVar, int i) {
        if (this.g == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initSegmentalFat mSegmentalFat is null");
            return;
        }
        double d = doj.d(cfeVar.c());
        if (d <= 0.0d) {
            d = doj.a(cfeVar.a(), cfeVar.ax(), 1);
        }
        HealthSubHeader healthSubHeader = this.f;
        if (healthSubHeader != null) {
            healthSubHeader.setVisibility(0);
            this.f.setSubHeaderBackgroundColor(0);
            this.f.setHeadTitleText(c(R$string.IDS_hwh_home_fat, d));
        }
        byte an = cfeVar.an();
        int e = cfeVar.e();
        cps c = c(cfeVar, an, i);
        double c2 = UnitUtil.c(cfeVar.v(), 1);
        double c3 = UnitUtil.c(cfeVar.u(), 1);
        double c4 = UnitUtil.c(cfeVar.am(), 1);
        double c5 = UnitUtil.c(cfeVar.ai(), 1);
        int[] b = qsj.b(doj.i(an, c.l(), e), doj.i(an, c.ad(), e), c2, c4);
        int[] b2 = qsj.b(doj.c(an, c.o(), e), doj.c(an, c.z(), e), c3, c5);
        double e2 = doj.e(UnitUtil.a(d), c2, c3, c4, c5);
        HealthBodyDetailData healthBodyDetailData = (HealthBodyDetailData) this.g.findViewById(R.id.base_weight_segmental_body_detail_data);
        if (healthBodyDetailData == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initSegmentalFat: bodyDetailData is null");
            return;
        }
        healthBodyDetailData.setBodyDetailType(0);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(Double.valueOf(e2));
        arrayList.add(Double.valueOf(c2));
        arrayList.add(Double.valueOf(c4));
        arrayList.add(Double.valueOf(c3));
        arrayList.add(Double.valueOf(c5));
        qsj.a(healthBodyDetailData, (ArrayList<Double>) arrayList, qsj.d(c, b, b2, cfeVar));
        c();
    }

    private void c() {
        if (!Utils.o() || Utils.a(BaseApplication.getContext())) {
            return;
        }
        qsj.dIh_(this.g, R.id.base_weight_segmental_include_body_standard_horizontal);
    }

    private cps c(cfe cfeVar, byte b, int i) {
        if (cpa.c(cfeVar)) {
            return new cps(cfeVar.t(), (float) cfeVar.ax(), b, cfeVar.e(), i, 8, cfeVar.ag(), 2, cfeVar.q());
        }
        return new cps(cfeVar.t(), (float) cfeVar.ax(), b, cfeVar.e(), i, cfeVar.ag());
    }

    private void a(cfe cfeVar) {
        if (this.i == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initSegmentalSkeletalMuscle mSegmentalSkeletalMuscle is null");
            return;
        }
        double aj = cfeVar.aj();
        HealthSubHeader healthSubHeader = this.h;
        if (healthSubHeader != null) {
            healthSubHeader.setVisibility(0);
            this.h.setSubHeaderBackgroundColor(0);
            this.h.setHeadTitleText(c(R$string.IDS_hwh_home_skeletal_muscle, aj));
        }
        double c = UnitUtil.c(cfeVar.y(), 1);
        double c2 = UnitUtil.c(cfeVar.x(), 1);
        double c3 = UnitUtil.c(cfeVar.ak(), 1);
        double c4 = UnitUtil.c(cfeVar.ah(), 1);
        double c5 = doj.c(UnitUtil.a(aj), c, c2, c3, c4);
        HealthBodyDetailData healthBodyDetailData = (HealthBodyDetailData) this.i.findViewById(R.id.base_weight_segmental_body_detail_data);
        if (healthBodyDetailData == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initSegmentalSkeletalMuscle: bodyDetailData is null");
            return;
        }
        healthBodyDetailData.setBodyDetailType(1);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(Double.valueOf(c5));
        arrayList.add(Double.valueOf(c));
        arrayList.add(Double.valueOf(c3));
        arrayList.add(Double.valueOf(c2));
        arrayList.add(Double.valueOf(c4));
        qsj.a(healthBodyDetailData, (ArrayList<Double>) arrayList, qsj.c(cfeVar));
        if (!Utils.o() || Utils.a(BaseApplication.getContext())) {
            return;
        }
        qsj.dIh_(this.i, R.id.base_weight_segmental_include_body_standard_horizontal);
    }

    private void c(cfe cfeVar) {
        int i;
        if (this.d == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initBodyAnalysisChart mBodyAnalysisLayout is null");
            return;
        }
        if (i(cfeVar)) {
            d("bodyComposition");
            this.d.setVisibility(0);
            HealthRingChart healthRingChart = (HealthRingChart) this.d.findViewById(R.id.body_analysis_chart_view);
            if (healthRingChart == null) {
                LogUtil.b("HealthWeight_WeightCommonView", "initBodyAnalysisChart healthRingChart is null!");
                return;
            }
            double ap = cfeVar.ap();
            if (!dph.t(ap)) {
                ap = cfeVar.al();
            }
            double a2 = cfeVar.a();
            double c = UnitUtil.c(cfeVar.ax(), cfeVar.getFractionDigitByType(0));
            double c2 = UnitUtil.c(doj.a(a2, cfeVar.ax(), 2), 2);
            double c3 = UnitUtil.c(cfeVar.i(), 2);
            double c4 = UnitUtil.c(doj.e(ap, cfeVar.ax(), 2), 2);
            double a3 = UnitUtil.a(((c - c4) - c2) - c3, 2);
            int a4 = UnitUtil.a();
            if (a4 == 1) {
                i = R$string.IDS_weight_body_component_catty;
            } else if (a4 == 3) {
                i = R$string.IDS_weight_body_component_lbs;
            } else {
                i = R$string.IDS_weight_body_component_kg;
            }
            ((HealthTextView) this.d.findViewById(R.id.base_weight_body_analysis_weight_introduction)).setText(i);
            HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.c, new nld().c(false).b(true).d(false), qsj.b(this.c, new float[]{(float) c4, (float) a3, (float) c2, (float) c3}));
            healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: qrr
                @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
                public final String format(nkz nkzVar) {
                    String e;
                    e = UnitUtil.e(nkzVar.i(), 1, 2);
                    return e;
                }
            });
            healthRingChart.setAdapter(healthRingChartAdapter);
            e(c, healthRingChart);
            return;
        }
        this.d.setVisibility(8);
    }

    private void d(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("dataType", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.WEIGHT_PAGE_SLIDE_DATA_DETAIL_2160114.value(), hashMap, 0);
    }

    private void e(double d, HealthRingChart healthRingChart) {
        HealthSubHeader healthSubHeader;
        LinearLayout linearLayout = this.d;
        if (linearLayout != null && (healthSubHeader = (HealthSubHeader) linearLayout.findViewById(R.id.base_weight_body_composition_analysis_sub_header)) != null) {
            healthSubHeader.setSubHeaderBackgroundColor(0);
        }
        if (LanguageUtil.j(this.c)) {
            healthRingChart.setDesc(this.c.getResources().getString(R$string.IDS_hw_health_show_healthdata_weight), String.valueOf(d));
        }
    }

    private void h(cfe cfeVar) {
        LinearLayout linearLayout = this.o;
        if (linearLayout == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initWeightSuggest mWeightSuggestLayout is null");
            return;
        }
        RelativeLayout relativeLayout = (RelativeLayout) linearLayout.findViewById(R.id.base_weight_bottom_logo);
        if (relativeLayout == null) {
            LogUtil.h("HealthWeight_WeightCommonView", "initWeightSuggest logo Layout is null");
            return;
        }
        if (!cfeVar.isNewScaleType()) {
            if (cfeVar.l() == 133) {
                ImageView imageView = (ImageView) this.o.findViewById(R.id.img_origin_icon);
                HealthTextView healthTextView = (HealthTextView) this.o.findViewById(R.id.information_source_company);
                imageView.setVisibility(8);
                healthTextView.setText(this.c.getResources().getString(R$string.IDS_weight_chip_sea_company));
                ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
                layoutParams.height = (int) this.c.getResources().getDimension(R.dimen._2131363775_res_0x7f0a07bf);
                relativeLayout.setLayoutParams(layoutParams);
                relativeLayout.setVisibility(0);
                this.o.setVisibility(0);
                return;
            }
            return;
        }
        relativeLayout.setVisibility(0);
    }

    private boolean i(cfe cfeVar) {
        if (dph.t(cfeVar.ap()) && dph.l(cfeVar.ab()) && dph.b(cfeVar.i()) && dph.j(cfeVar.a())) {
            return true;
        }
        LogUtil.h("HealthWeight_WeightCommonView", "showBodyAnalysisChart invalid data in BodyAnalysisChart");
        return false;
    }

    private boolean j(cfe cfeVar) {
        if (this.e || !cfeVar.isVisible(30, false)) {
            LogUtil.h("HealthWeight_WeightCommonView", "is oversea or peerComparisonVisible is false ");
            return false;
        }
        if (dph.p(cfeVar.aj()) || dph.y(cfeVar.s()) || dph.j(cfeVar.a()) || dph.e(cfeVar.j(), false)) {
            return true;
        }
        LogUtil.h("HealthWeight_WeightCommonView", "showPeerComparisonChart peerComparisonChart not show");
        return false;
    }
}
