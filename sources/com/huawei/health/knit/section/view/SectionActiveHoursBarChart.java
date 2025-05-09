package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.HwHealthDetailBarChart;
import com.huawei.health.knit.section.listener.ChartStatisticChangeCallback;
import com.huawei.health.knit.section.listener.OnMarkViewTextNotify;
import com.huawei.health.knit.section.listener.OnXRangeTextCallback;
import com.huawei.health.knit.section.view.SectionActiveHoursBarChart;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.icommon.LogicalUnit;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.eet;
import defpackage.efz;
import defpackage.ega;
import defpackage.nnc;
import defpackage.nne;
import defpackage.noy;
import defpackage.nru;
import defpackage.nsy;
import defpackage.ntq;
import health.compact.a.LanguageUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class SectionActiveHoursBarChart extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HwHealthBarScrollChartHolder f2659a;
    private HwHealthDetailBarChart b;
    private HealthViewPager c;
    private int d;
    protected View e;
    private ImageView f;
    private ntq<View> g;
    private ImageView h;
    private ArrayList<View> i;
    private ChartStatisticChangeCallback j;
    private final AtomicBoolean k;
    private HealthTextView l;
    private Context m;
    private HealthTextView n;
    private HealthTextView o;
    private final AtomicBoolean p;
    private HwHealthBaseScrollBarLineChart.OnXRangeSet r;

    public SectionActiveHoursBarChart(Context context) {
        this(context, null);
        this.m = context;
    }

    public SectionActiveHoursBarChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionActiveHoursBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.p = new AtomicBoolean(false);
        this.k = new AtomicBoolean(false);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SCUI_SectionActiveHoursBarChart", "onCreateView");
        this.m = context;
        if (this.e == null) {
            this.e = LayoutInflater.from(context).inflate(R.layout.section_active_hours_barchart, (ViewGroup) this, false);
        }
        this.f = (ImageView) this.e.findViewById(R.id.active_hours_arrow_left);
        this.h = (ImageView) this.e.findViewById(R.id.active_hours_arrow_right);
        this.l = (HealthTextView) this.e.findViewById(R.id.tv_active_hours_date);
        this.n = (HealthTextView) this.e.findViewById(R.id.tv_active_hours_time);
        this.o = (HealthTextView) this.e.findViewById(R.id.tv_active_hours_completed);
        this.c = (HealthViewPager) this.e.findViewById(R.id.active_hours_viewpager);
        d();
        e();
        h();
        f();
        return this.e;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SCUI_SectionActiveHoursBarChart", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SCUI_SectionActiveHoursBarChart", "no need to bind");
            return;
        }
        if ((hashMap.get("BAR_COMMON_CHART_HOLDER") instanceof HwHealthBarScrollChartHolder) && this.f2659a == null && (hashMap.get("BAR_DATA_INFOS") instanceof DataInfos)) {
            this.f2659a = (HwHealthBarScrollChartHolder) hashMap.get("BAR_COMMON_CHART_HOLDER");
            this.f2659a.addDataLayer((HwHealthBarScrollChartHolder) this.b, (DataInfos) hashMap.get("BAR_DATA_INFOS"));
            this.g.notifyDataSetChanged();
            if (hashMap.get("BAR_COMMON_MARK_CHANGE_CALL_BACK") instanceof OnXRangeTextCallback) {
                final OnXRangeTextCallback onXRangeTextCallback = (OnXRangeTextCallback) hashMap.get("BAR_COMMON_MARK_CHANGE_CALL_BACK");
                HwHealthBaseScrollBarLineChart.OnXRangeSet onXRangeSet = new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: efy
                    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
                    public final void onRangeShow(int i, int i2) {
                        SectionActiveHoursBarChart.this.b(onXRangeTextCallback, i, i2);
                    }
                };
                this.r = onXRangeSet;
                this.b.addOnXRangeSet(onXRangeSet);
            }
            if (hashMap.get("BAR_COMMON_MARK_TEXT_CALL_BACK") instanceof OnMarkViewTextNotify) {
                final OnMarkViewTextNotify onMarkViewTextNotify = (OnMarkViewTextNotify) hashMap.get("BAR_COMMON_MARK_TEXT_CALL_BACK");
                this.b.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: efx
                    @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
                    public final void onTextChanged(String str, List list) {
                        SectionActiveHoursBarChart.this.d(onMarkViewTextNotify, str, list);
                    }
                });
                this.b.acquireScrollAdapter().acquireXAxisValueFormatter().setHealthType(HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_ACTIVE_HOUR);
            }
            if (hashMap.get("BAR_OBSERVER_LABEL_CUMULATIVE_AVERAGE") instanceof ChartStatisticChangeCallback) {
                this.j = (ChartStatisticChangeCallback) nru.c(hashMap, "BAR_OBSERVER_LABEL_CUMULATIVE_AVERAGE", ChartStatisticChangeCallback.class, null);
                g();
            }
        }
        Object obj = hashMap.get("BAR_COMMON_START_TIME");
        if ((obj instanceof Integer) && this.d == 0) {
            this.b.setShowRange(((Integer) obj).intValue(), this.b.acquireScrollAdapter().acquireRange());
            this.b.refresh();
        }
        Object obj2 = hashMap.get("BAR_COMMON_REFLESH_TIME");
        if (obj2 instanceof Long) {
            HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.b.acquireScrollAdapter();
            acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
            this.b.setWillNotDraw(false);
            this.b.setMarkerViewPosition(null);
            this.b.reflesh(((Long) obj2).longValue());
        }
        setText(hashMap);
        this.d--;
    }

    public /* synthetic */ void b(OnXRangeTextCallback onXRangeTextCallback, int i, int i2) {
        onXRangeTextCallback.onRangeShow(i, i2, this.b.formatRangeText(i, i2));
    }

    public /* synthetic */ void d(OnMarkViewTextNotify onMarkViewTextNotify, String str, List list) {
        onMarkViewTextNotify.onTextChanged(str, list, this.b.fetchMarkViewMinuteValue());
    }

    private void g() {
        if (this.r != null) {
            HwHealthBaseScrollBarLineChart.OnXRangeSet onXRangeSet = new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: efw
                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
                public final void onRangeShow(int i, int i2) {
                    SectionActiveHoursBarChart.this.b(i, i2);
                }
            };
            this.r = onXRangeSet;
            this.b.addOnXRangeSet(onXRangeSet);
        }
    }

    public /* synthetic */ void b(int i, int i2) {
        ChartStatisticChangeCallback chartStatisticChangeCallback = this.j;
        if (chartStatisticChangeCallback != null) {
            chartStatisticChangeCallback.onStatisticChange((int) c(), Math.round(b()));
        }
    }

    private void setText(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.l, nru.b(hashMap, "BAR_CHART_DATE_TEXT", ""));
        nsy.cMr_(this.n, nru.b(hashMap, "BAR_CHART_PERIOD_STRING", ""));
        nsy.cMr_(this.o, (CharSequence) nru.c(hashMap, "BAR_CHART_LEGEND_ONE_TEXT", SpannableString.class, new SpannableString("")));
        if (hashMap.get("BAR_CHART_CLICK_EVENT") instanceof OnClickSectionListener) {
            setClickListenerEvent(hashMap.get("BAR_CHART_CLICK_EVENT"));
        }
    }

    private void d() {
        this.i = new ArrayList<>(16);
    }

    private void e() {
        if (this.b == null) {
            HwHealthDetailBarChart hwHealthDetailBarChart = new HwHealthDetailBarChart(this.m.getApplicationContext());
            this.b = hwHealthDetailBarChart;
            hwHealthDetailBarChart.setLayerType(1, null);
            a();
            this.i.add(0, this.b);
            this.g = new ntq<>(this.i);
            this.c.setIsCompatibleScrollView(true);
            this.c.setIsScroll(false);
            this.c.setAdapter(this.g);
        }
    }

    private void a() {
        this.b.setPagerNoMoreListener(new c(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.b.canScrollOlderPager()) {
            this.f.setVisibility(0);
            this.f.setClickable(true);
            this.p.set(true);
        } else {
            this.f.setVisibility(4);
            this.f.setClickable(false);
            this.p.set(false);
        }
        if (this.b.canScrollNewerPager()) {
            this.h.setVisibility(0);
            this.h.setClickable(true);
            this.k.set(true);
        } else {
            this.h.setVisibility(4);
            this.h.setClickable(false);
            this.k.set(false);
        }
    }

    private void h() {
        if (LanguageUtil.bc(this.m)) {
            this.f.setBackground(ContextCompat.getDrawable(this.m, R.drawable._2131427831_res_0x7f0b01f7));
            this.h.setBackground(ContextCompat.getDrawable(this.m, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.f.setBackground(ContextCompat.getDrawable(this.m, R.drawable._2131427825_res_0x7f0b01f1));
            this.h.setBackground(ContextCompat.getDrawable(this.m, R.drawable._2131427831_res_0x7f0b01f7));
        }
    }

    private void f() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionActiveHoursBarChart.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!SectionActiveHoursBarChart.this.p.get()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                eet.b(2, "activity");
                if (!SectionActiveHoursBarChart.this.b.isAnimating()) {
                    SectionActiveHoursBarChart.this.d = 1;
                    HwHealthDetailBarChart hwHealthDetailBarChart = SectionActiveHoursBarChart.this.b;
                    HwHealthDetailBarChart hwHealthDetailBarChart2 = SectionActiveHoursBarChart.this.b;
                    Objects.requireNonNull(hwHealthDetailBarChart2);
                    hwHealthDetailBarChart.scrollOnePageOlder(new efz(this, hwHealthDetailBarChart2));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionActiveHoursBarChart.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!SectionActiveHoursBarChart.this.k.get()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                eet.b(1, "activity");
                if (!SectionActiveHoursBarChart.this.b.isAnimating()) {
                    SectionActiveHoursBarChart.this.d = 1;
                    HwHealthDetailBarChart hwHealthDetailBarChart = SectionActiveHoursBarChart.this.b;
                    HwHealthDetailBarChart hwHealthDetailBarChart2 = SectionActiveHoursBarChart.this.b;
                    Objects.requireNonNull(hwHealthDetailBarChart2);
                    hwHealthDetailBarChart.scrollOnePageNewer(new ega(this, hwHealthDetailBarChart2));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void setClickListenerEvent(Object obj) {
        if (obj instanceof OnClickSectionListener) {
            final OnClickSectionListener onClickSectionListener = (OnClickSectionListener) obj;
            HealthTextView healthTextView = this.l;
            if (healthTextView != null) {
                healthTextView.setOnClickListener(new View.OnClickListener() { // from class: eft
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SectionActiveHoursBarChart.ais_(OnClickSectionListener.this, view);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void ais_(OnClickSectionListener onClickSectionListener, View view) {
        onClickSectionListener.onClick("BAR_CHART_CALENDAR_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    private float c() {
        HwHealthBarDataSet hwHealthBarDataSet = this.b.getBarData().getDataSets().get(0) instanceof HwHealthBarDataSet ? (HwHealthBarDataSet) this.b.getBarData().getDataSets().get(0) : null;
        if (hwHealthBarDataSet == null) {
            throw new RuntimeException("calculateSum not find dataSet! logic error!!!");
        }
        return hwHealthBarDataSet.calculateLogicByShowRange(this.b, new LogicalUnit() { // from class: com.huawei.health.knit.section.view.SectionActiveHoursBarChart.3
            @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
            public float calculate(List<? extends HwHealthBaseEntry> list) {
                float f = 0.0f;
                if (list == null || list.size() == 0) {
                    return 0.0f;
                }
                HwHealthBaseEntry hwHealthBaseEntry = list.get(0);
                if (hwHealthBaseEntry instanceof HwHealthBarEntry) {
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry;
                    if (hwHealthBarEntry.acquireModel() instanceof nnc) {
                        if (!(((nnc) hwHealthBarEntry.acquireModel()) instanceof nne)) {
                            for (HwHealthBaseEntry hwHealthBaseEntry2 : list) {
                                if (hwHealthBaseEntry2 != null) {
                                    f += SectionActiveHoursBarChart.this.d(((HwHealthBarEntry) hwHealthBaseEntry2).acquireModel());
                                }
                            }
                            return f;
                        }
                        for (HwHealthBaseEntry hwHealthBaseEntry3 : list) {
                            if (hwHealthBaseEntry3 != null) {
                                f += ((nne) ((HwHealthBarEntry) hwHealthBaseEntry3).acquireModel()).d();
                            }
                        }
                        return f;
                    }
                }
                throw new RuntimeException("calculateSum not instanceof HwHealthBarEntry! logic error!!!");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float d(IStorageModel iStorageModel) {
        float c2 = noy.c(iStorageModel);
        if (Math.abs(c2 - 3.999f) > 1.0E-6f) {
            return c2;
        }
        return 0.0f;
    }

    private float b() {
        HwHealthBarDataSet hwHealthBarDataSet = this.b.getBarData().getDataSets().get(0) instanceof HwHealthBarDataSet ? (HwHealthBarDataSet) this.b.getBarData().getDataSets().get(0) : null;
        if (hwHealthBarDataSet == null) {
            throw new RuntimeException("mAvgCalculator can't find dataSet");
        }
        return hwHealthBarDataSet.calculateLogicByShowRange(this.b, new LogicalUnit() { // from class: com.huawei.health.knit.section.view.SectionActiveHoursBarChart.4
            @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
            public float calculate(List<? extends HwHealthBaseEntry> list) {
                if (list == null || list.size() == 0) {
                    return 0.0f;
                }
                HwHealthBaseEntry hwHealthBaseEntry = list.get(0);
                if (hwHealthBaseEntry instanceof HwHealthBarEntry) {
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry;
                    if (hwHealthBarEntry.acquireModel() instanceof nnc) {
                        return SectionActiveHoursBarChart.this.b(list, (nnc) hwHealthBarEntry.acquireModel());
                    }
                }
                throw new RuntimeException("mAvgCalculator not instanceof HwHealthBarEntry! logic error!!!");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float b(List<? extends HwHealthBaseEntry> list, nnc nncVar) {
        float f;
        int i = 0;
        if (!(nncVar instanceof nne)) {
            f = 0.0f;
            for (HwHealthBaseEntry hwHealthBaseEntry : list) {
                if (hwHealthBaseEntry != null) {
                    f += noy.c(((HwHealthBarEntry) hwHealthBaseEntry).acquireModel());
                    i++;
                }
            }
            if (i == 0) {
                LogUtil.h("SCUI_SectionActiveHoursBarChart", "Division by zero attempted!");
                return 0.0f;
            }
        } else {
            f = 0.0f;
            for (HwHealthBaseEntry hwHealthBaseEntry2 : list) {
                if (hwHealthBaseEntry2 instanceof HwHealthBarEntry) {
                    HwHealthBarEntry hwHealthBarEntry = (HwHealthBarEntry) hwHealthBaseEntry2;
                    if (hwHealthBarEntry.acquireModel() instanceof nne) {
                        nne nneVar = (nne) hwHealthBarEntry.acquireModel();
                        f += nneVar.d();
                        i += nneVar.c();
                    }
                }
            }
            if (i == 0) {
                LogUtil.h("SCUI_SectionActiveHoursBarChart", "Division by zero attempted!");
                return 0.0f;
            }
        }
        return f / i;
    }

    static class c implements HwHealthBaseScrollBarLineChart.PagerNoMoreListener {
        private final WeakReference<SectionActiveHoursBarChart> c;

        c(SectionActiveHoursBarChart sectionActiveHoursBarChart) {
            this.c = new WeakReference<>(sectionActiveHoursBarChart);
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
        public void notifyNewerPager(boolean z) {
            SectionActiveHoursBarChart sectionActiveHoursBarChart = this.c.get();
            if (sectionActiveHoursBarChart != null) {
                sectionActiveHoursBarChart.i();
            }
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
        public void notifyOlderPager(boolean z) {
            SectionActiveHoursBarChart sectionActiveHoursBarChart = this.c.get();
            if (sectionActiveHoursBarChart != null) {
                sectionActiveHoursBarChart.i();
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SCUI_SectionActiveHoursBarChart";
    }
}
