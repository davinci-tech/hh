package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.BloodOxygenBarChartView;
import com.huawei.health.knit.section.listener.OnDataChangedListener;
import com.huawei.health.knit.section.listener.OnMarkViewTextNotify;
import com.huawei.health.knit.section.listener.OnXRangeTextCallback;
import com.huawei.health.knit.section.view.SectionBloodOxygenBaseBarChart;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.egw;
import defpackage.egz;
import defpackage.jcf;
import defpackage.nru;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.ntq;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class SectionBloodOxygenBaseBarChart extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f2677a;
    private LinearLayout b;
    private int c;
    private BloodOxygenBarChartView d;
    private HwHealthBarScrollChartHolder e;
    private ntq<View> f;
    private HealthTextView g;
    private List<String> h;
    private HealthTextView i;
    private Context j;
    private LinearLayout k;
    private LinearLayout l;
    private HealthTextView m;
    private HealthViewPager n;
    private HealthTextView o;
    private ImageView p;
    private ImageView q;
    private View r;
    private HealthTextView s;
    private HealthTextView t;
    private ArrayList<View> u;
    private HealthTextView w;
    private final AtomicBoolean x;
    private final AtomicBoolean y;

    public SectionBloodOxygenBaseBarChart(Context context) {
        this(context, null);
    }

    public SectionBloodOxygenBaseBarChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionBloodOxygenBaseBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.x = new AtomicBoolean(false);
        this.y = new AtomicBoolean(false);
        this.c = 0;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.j = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_bar_chart, (ViewGroup) this, false);
        setIntervalLegendLayout(inflate);
        this.q = (ImageView) inflate.findViewById(R.id.image_up_arrow_left);
        this.p = (ImageView) inflate.findViewById(R.id.image_up_arrow_right);
        this.o = (HealthTextView) inflate.findViewById(R.id.detail_time_date);
        this.g = (HealthTextView) inflate.findViewById(R.id.cursor_time);
        this.m = (HealthTextView) inflate.findViewById(R.id.cursor_value);
        this.i = (HealthTextView) inflate.findViewById(R.id.cursor_step);
        this.l = (LinearLayout) inflate.findViewById(R.id.horizontal_jump);
        this.n = (HealthViewPager) inflate.findViewById(R.id.detail_view_pager);
        this.l.setVisibility(8);
        e();
        c();
        d();
        g();
        h();
        return inflate;
    }

    /* renamed from: com.huawei.health.knit.section.view.SectionBloodOxygenBaseBarChart$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2678a;

        static {
            int[] iArr = new int[DataInfos.values().length];
            f2678a = iArr;
            try {
                iArr[DataInfos.BloodOxygenWeekDetail.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2678a[DataInfos.BloodOxygenMonthDetail.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2678a[DataInfos.BloodOxygenYearDetail.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void setAccessibilityArrowDescription(DataInfos dataInfos) {
        int i = AnonymousClass2.f2678a[dataInfos.ordinal()];
        if (i == 1) {
            jcf.bEz_(this.q, nsf.h(R$string.accessibility_last_week));
            jcf.bEz_(this.p, nsf.h(R$string.accessibility_next_week));
        } else if (i == 2) {
            jcf.bEz_(this.q, nsf.h(R$string.accessibility_last_month));
            jcf.bEz_(this.p, nsf.h(R$string.accessibility_next_month));
        } else if (i == 3) {
            jcf.bEz_(this.q, nsf.h(R$string.accessibility_last_year));
            jcf.bEz_(this.p, nsf.h(R$string.accessibility_next_year));
        } else {
            ReleaseLogUtil.e("SectionBloodOxygenBaseBarChart", "unknown type chart");
        }
    }

    private void setIntervalLegendLayout(View view) {
        if (nsn.p()) {
            aiZ_(view, R.id.interval_legend_viewstub_treble, R.id.interval_legend_viewstub_treble_inflated);
        } else {
            aiZ_(view, R.id.interval_legend_viewstub, R.id.interval_legend_viewstub_inflated);
        }
        View view2 = this.r;
        if (view2 != null) {
            this.t = (HealthTextView) view2.findViewById(R.id.blood_oxygen_measure_range_1);
            this.s = (HealthTextView) this.r.findViewById(R.id.blood_oxygen_measure_range_2);
            this.w = (HealthTextView) this.r.findViewById(R.id.blood_oxygen_measure_range_3);
            this.b = (LinearLayout) this.r.findViewById(R.id.legend_layout_5);
            this.f2677a = (LinearLayout) this.r.findViewById(R.id.legend_layout_abnormal);
            LinearLayout linearLayout = (LinearLayout) this.r.findViewById(R.id.extra_line_legend_layout);
            this.k = linearLayout;
            linearLayout.setVisibility(8);
            this.b.setVisibility(8);
            this.f2677a.setVisibility(8);
        }
    }

    private void d() {
        this.h = new ArrayList(16);
        String e = UnitUtil.e(70.0d, 2, 0);
        String e2 = UnitUtil.e(70.0d, 1, 0);
        String e3 = UnitUtil.e(89.0d, 2, 0);
        String e4 = UnitUtil.e(90.0d, 2, 0);
        this.h.add(e);
        this.h.add(e2);
        this.h.add(e3);
        this.h.add(e4);
        setBloodOxygenInterval(this.h);
    }

    private void setBloodOxygenInterval(List<String> list) {
        this.t.setText(String.format(Locale.ENGLISH, this.j.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_lower_than), list.get(0)));
        this.s.setText(String.format(Locale.ENGLISH, this.j.getString(R$string.IDS_press_auto_monitor_relax_range), list.get(1), list.get(2)));
        this.w.setText(String.format(Locale.ENGLISH, this.j.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_greater_than), list.get(3)));
    }

    private void aiZ_(View view, int i, int i2) {
        ViewStub viewStub = (ViewStub) view.findViewById(i);
        if (viewStub.getParent() != null) {
            this.r = viewStub.inflate();
        } else {
            this.r = view.findViewById(i2);
        }
    }

    private void e() {
        this.u = new ArrayList<>(16);
    }

    private void c() {
        if (this.d == null) {
            BloodOxygenBarChartView bloodOxygenBarChartView = new BloodOxygenBarChartView(this.j.getApplicationContext());
            this.d = bloodOxygenBarChartView;
            bloodOxygenBarChartView.setLayerType(1, null);
            b();
            this.u.add(0, this.d);
            this.f = new ntq<>(this.u);
            this.n.setIsCompatibleScrollView(true);
            this.n.setIsScroll(false);
            this.n.setAdapter(this.f);
        }
    }

    private void b() {
        this.d.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBaseBarChart.4
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                SectionBloodOxygenBaseBarChart.this.a();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                SectionBloodOxygenBaseBarChart.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.d.canScrollOlderPager()) {
            this.q.setVisibility(0);
            this.q.setClickable(true);
            this.x.set(true);
        } else {
            this.q.setVisibility(4);
            this.q.setClickable(false);
            this.x.set(false);
        }
        if (this.d.canScrollNewerPager()) {
            this.p.setVisibility(0);
            this.p.setClickable(true);
            this.y.set(true);
        } else {
            this.p.setVisibility(4);
            this.p.setClickable(false);
            this.y.set(false);
        }
    }

    private void g() {
        if (LanguageUtil.bc(this.j)) {
            this.q.setBackground(ContextCompat.getDrawable(this.j, R.drawable._2131427831_res_0x7f0b01f7));
            this.p.setBackground(ContextCompat.getDrawable(this.j, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.q.setBackground(ContextCompat.getDrawable(this.j, R.drawable._2131427825_res_0x7f0b01f1));
            this.p.setBackground(ContextCompat.getDrawable(this.j, R.drawable._2131427831_res_0x7f0b01f7));
        }
        this.p.setVisibility(0);
        this.x.set(true);
        this.q.setClickable(true);
        this.p.setVisibility(4);
        this.y.set(false);
        this.p.setClickable(false);
    }

    private void h() {
        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBaseBarChart.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SectionBloodOxygenBaseBarChart.this.x.get()) {
                    if (!SectionBloodOxygenBaseBarChart.this.d.isAnimating()) {
                        SectionBloodOxygenBaseBarChart.this.c = 2;
                        BloodOxygenBarChartView bloodOxygenBarChartView = SectionBloodOxygenBaseBarChart.this.d;
                        BloodOxygenBarChartView bloodOxygenBarChartView2 = SectionBloodOxygenBaseBarChart.this.d;
                        Objects.requireNonNull(bloodOxygenBarChartView2);
                        bloodOxygenBarChartView.scrollOnePageOlder(new egw(this, bloodOxygenBarChartView2));
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBaseBarChart.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SectionBloodOxygenBaseBarChart.this.y.get()) {
                    if (!SectionBloodOxygenBaseBarChart.this.d.isAnimating()) {
                        SectionBloodOxygenBaseBarChart.this.c = 2;
                        BloodOxygenBarChartView bloodOxygenBarChartView = SectionBloodOxygenBaseBarChart.this.d;
                        BloodOxygenBarChartView bloodOxygenBarChartView2 = SectionBloodOxygenBaseBarChart.this.d;
                        Objects.requireNonNull(bloodOxygenBarChartView2);
                        bloodOxygenBarChartView.scrollOnePageNewer(new egz(this, bloodOxygenBarChartView2));
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if ((hashMap.get("BAR_COMMON_CHART_HOLDER") instanceof HwHealthBarScrollChartHolder) && this.e == null && (hashMap.get("BAR_DATA_INFOS") instanceof DataInfos)) {
            HwHealthBarScrollChartHolder hwHealthBarScrollChartHolder = (HwHealthBarScrollChartHolder) hashMap.get("BAR_COMMON_CHART_HOLDER");
            this.e = hwHealthBarScrollChartHolder;
            hwHealthBarScrollChartHolder.addDataLayer((HwHealthBarScrollChartHolder) this.d, (DataInfos) hashMap.get("BAR_DATA_INFOS"));
            this.f.notifyDataSetChanged();
            if (hashMap.get("BAR_COMMON_MARK_CHANGE_CALL_BACK") instanceof OnXRangeTextCallback) {
                final OnXRangeTextCallback onXRangeTextCallback = (OnXRangeTextCallback) hashMap.get("BAR_COMMON_MARK_CHANGE_CALL_BACK");
                this.d.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: egu
                    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
                    public final void onRangeShow(int i, int i2) {
                        SectionBloodOxygenBaseBarChart.this.a(onXRangeTextCallback, i, i2);
                    }
                });
            }
            if (hashMap.get("BAR_COMMON_RANGE_SHOW_CALL_BACK") instanceof OnDataChangedListener) {
                final OnDataChangedListener onDataChangedListener = (OnDataChangedListener) hashMap.get("BAR_COMMON_RANGE_SHOW_CALL_BACK");
                this.d.setOnDataChangedListener(new BloodOxygenBarChartView.OnDataChangedListener() { // from class: egt
                    @Override // com.huawei.health.knit.section.chart.BloodOxygenBarChartView.OnDataChangedListener
                    public final void onDataChangedListener(float f, float f2) {
                        OnDataChangedListener.this.onDataChangedListener(f, f2);
                    }
                });
            }
            if (hashMap.get("BAR_COMMON_MARK_TEXT_CALL_BACK") instanceof OnMarkViewTextNotify) {
                final OnMarkViewTextNotify onMarkViewTextNotify = (OnMarkViewTextNotify) hashMap.get("BAR_COMMON_MARK_TEXT_CALL_BACK");
                this.d.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: egr
                    @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
                    public final void onTextChanged(String str, List list) {
                        SectionBloodOxygenBaseBarChart.this.e(onMarkViewTextNotify, str, list);
                    }
                });
            }
            setAccessibilityArrowDescription((DataInfos) hashMap.get("BAR_DATA_INFOS"));
        }
        if ((hashMap.get("BAR_COMMON_START_TIME") instanceof Integer) && this.c == 0) {
            this.d.setShowRange(((Integer) hashMap.get("BAR_COMMON_START_TIME")).intValue(), this.d.acquireScrollAdapter().acquireRange());
            this.d.refresh();
        }
        if (hashMap.get("BAR_COMMON_REFLESH_TIME") instanceof Long) {
            this.d.reflesh(((Long) hashMap.get("BAR_COMMON_REFLESH_TIME")).longValue());
        }
        nsy.cMr_(this.o, nru.b(hashMap, "BAR_CHART_DATE_TEXT", ""));
        nsy.cMr_(this.g, nru.b(hashMap, "BAR_CHART_PERIOD_STRING", ""));
        if (hashMap.get("BAR_CHART_VALUE") instanceof String) {
            this.m.setText((String) hashMap.get("BAR_CHART_VALUE"));
        }
        this.i.setVisibility(8);
        if ((hashMap.get("BAR_CHART_CLICK_EVENT") instanceof OnClickSectionListener) && this.c == 0) {
            setClickListenerEvent(hashMap.get("BAR_CHART_CLICK_EVENT"));
        }
        this.c--;
    }

    public /* synthetic */ void a(OnXRangeTextCallback onXRangeTextCallback, int i, int i2) {
        onXRangeTextCallback.onRangeShow(i, i2, this.d.formatRangeText(i, i2));
    }

    public /* synthetic */ void e(OnMarkViewTextNotify onMarkViewTextNotify, String str, List list) {
        onMarkViewTextNotify.onTextChanged(str, list, this.d.fetchMarkViewMinuteValue());
    }

    private void setClickListenerEvent(Object obj) {
        if (obj instanceof OnClickSectionListener) {
            final OnClickSectionListener onClickSectionListener = (OnClickSectionListener) obj;
            HealthTextView healthTextView = this.o;
            if (healthTextView != null) {
                healthTextView.setOnClickListener(new View.OnClickListener() { // from class: egs
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SectionBloodOxygenBaseBarChart.aiY_(OnClickSectionListener.this, view);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void aiY_(OnClickSectionListener onClickSectionListener, View view) {
        onClickSectionListener.onClick("BAR_CHART_CALENDAR_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionBloodOxygenBaseBarChart";
    }
}
