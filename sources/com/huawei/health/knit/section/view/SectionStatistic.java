package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.view.SectionStatistic;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nkz;
import defpackage.nld;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionStatistic extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private Context f2741a;
    private HealthTextView b;
    private HealthRingChart c;
    private int d;
    private View e;
    private View f;
    private LinearLayout g;
    private LinearLayout h;
    private HealthTextView i;
    private HealthTextView j;

    public SectionStatistic(Context context) {
        this(context, null);
        this.f2741a = context;
    }

    public SectionStatistic(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionStatistic(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(final Context context) {
        LogUtil.a("SectionStatistic", "onCreateView");
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_statistic_layout, (ViewGroup) this, false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.section_root_view);
        this.g = linearLayout;
        linearLayout.post(new Runnable() { // from class: eho
            @Override // java.lang.Runnable
            public final void run() {
                SectionStatistic.this.d(context);
            }
        });
        this.h = (LinearLayout) inflate.findViewById(R.id.stress_trend_average);
        this.b = (HealthTextView) inflate.findViewById(R.id.currentAverageStressValue);
        this.f = inflate.findViewById(R.id.verticalLine);
        this.i = (HealthTextView) inflate.findViewById(R.id.pressureTrendRange);
        b(context);
        this.j = (HealthTextView) inflate.findViewById(R.id.pressureAdvice);
        this.e = inflate.findViewById(R.id.pieChartLayout);
        this.c = (HealthRingChart) inflate.findViewById(R.id.pieChartView);
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(context, new nld(false, true));
        healthRingChartAdapter.b(new HealthRingChartAdapter.DataFormatter() { // from class: ehl
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                String e;
                e = UnitUtil.e(nkzVar.a(), 2, 0);
                return e;
            }
        });
        this.c.setAdapter(healthRingChartAdapter);
        return inflate;
    }

    public /* synthetic */ void d(Context context) {
        this.g.setMinimumWidth(nsn.h(context));
    }

    private void b(Context context) {
        if (nsn.r()) {
            LogUtil.a("SectionStatistic", "modifyLargeFontScale is large scale");
            this.h.setOrientation(1);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = c(context, 0.25f);
            this.f.setLayoutParams(layoutParams);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = -2;
            layoutParams2.weight = 0.0f;
            this.b.setLayoutParams(layoutParams2);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams3.width = -1;
            layoutParams3.height = -2;
            layoutParams3.weight = 0.0f;
            this.i.setLayoutParams(layoutParams3);
        }
    }

    private int c(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.b, (CharSequence) nru.c(hashMap, "STATISTIC_AVERAGE", SpannableStringBuilder.class, null));
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) nru.c(hashMap, "STATISTIC_MAX_MIN", SpannableStringBuilder.class, null);
        if (spannableStringBuilder != null) {
            this.f.setVisibility(0);
            this.i.setVisibility(0);
            this.i.setText(spannableStringBuilder);
        }
        List d = nru.d(hashMap, "PIE_CHART_DESC", String.class, null);
        if (LanguageUtil.j(this.f2741a) && d.size() == 2) {
            this.c.setDesc((String) d.get(0), (String) d.get(1));
        }
        this.c.c(nru.d(hashMap, "PIE_CHART_ITEM", nkz.class, null));
        nsy.cMr_(this.j, (CharSequence) nru.c(hashMap, "ADVICE", String.class, null));
        this.d = nru.d((Map) hashMap, "ADVICE_HAVE_DATA", 0);
    }

    public void setPieChartLayoutVisibility(int i) {
        View view = this.e;
        if (view != null) {
            view.setVisibility(i);
        }
    }

    public void setPressureAdviceTvVisibility(int i) {
        HealthTextView healthTextView = this.j;
        if (healthTextView != null && this.d == 0) {
            healthTextView.setVisibility(8);
        } else if (healthTextView != null) {
            healthTextView.setVisibility(i);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionStatistic";
    }
}
