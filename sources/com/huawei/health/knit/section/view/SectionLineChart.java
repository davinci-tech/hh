package com.huawei.health.knit.section.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.HwHealthPressureBarChart;
import com.huawei.health.knit.section.listener.BarChartRangeShowCallback;
import com.huawei.health.knit.section.view.SectionLineChart;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.eap;
import defpackage.eeu;
import defpackage.ehb;
import defpackage.ehd;
import defpackage.jcf;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nlg;
import defpackage.nom;
import defpackage.nru;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class SectionLineChart extends BaseSection implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    protected LinearLayout f2694a;
    private RelativeLayout ab;
    private HealthViewPager ac;
    protected DataInfos b;
    protected Context c;
    protected HealthTextView d;
    protected ImageView e;
    protected HealthTextView f;
    protected List<Object> g;
    protected ImageView h;
    protected HealthTextView i;
    protected HealthTextView j;
    protected View k;
    protected HealthTextView l;
    protected List<Object> m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView p;
    private BarChartRangeShowCallback q;
    private HwHealthLineScrollChartHolder r;
    private HealthCalendar s;
    private HealthTextView t;
    private HwHealthPressureBarChart u;
    private eap v;
    private final AtomicBoolean w;
    private final AtomicBoolean x;
    private ArrayList<View> y;
    private long z;

    public SectionLineChart(Context context) {
        this(context, null);
    }

    public SectionLineChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.z = 0L;
        this.w = new AtomicBoolean(false);
        this.x = new AtomicBoolean(false);
        c(context);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("Section_SectionLineChart", "onCreateView");
        h();
        return this.k;
    }

    private void h() {
        if (this.k == null) {
            LogUtil.h("Section_SectionLineChart", "initView mainView is null, start to inflate");
            this.k = LayoutInflater.from(this.c).inflate(R.layout.section1_linechart_02_layout, (ViewGroup) this, false);
        }
        LinearLayout linearLayout = (LinearLayout) this.k.findViewById(R.id.section_line_ll);
        this.f2694a = linearLayout;
        linearLayout.setMinimumWidth(nsn.h(this.c));
        this.e = (ImageView) this.k.findViewById(R.id.left_arrow);
        this.h = (ImageView) this.k.findViewById(R.id.right_arrow);
        this.d = (HealthTextView) this.k.findViewById(R.id.first_layer_date);
        this.j = (HealthTextView) this.k.findViewById(R.id.second_layer_date_time);
        this.i = (HealthTextView) this.k.findViewById(R.id.third_layer_cursor_text);
        this.l = (HealthTextView) this.k.findViewById(R.id.third_layer_cursor_value);
        this.f = (HealthTextView) this.k.findViewById(R.id.third_layer_cursor_state);
        this.ac = (HealthViewPager) this.k.findViewById(R.id.section_view_pager);
        this.n = (HealthTextView) this.k.findViewById(R.id.legend_one_text);
        this.t = (HealthTextView) this.k.findViewById(R.id.legend_two_text);
        this.p = (HealthTextView) this.k.findViewById(R.id.legend_three_text);
        this.o = (HealthTextView) this.k.findViewById(R.id.legend_four_text);
        this.e.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.d.setOnClickListener(this);
        if (LanguageUtil.bc(this.c)) {
            this.e.setImageResource(R.drawable._2131427831_res_0x7f0b01f7);
            this.h.setImageResource(R.drawable._2131427825_res_0x7f0b01f1);
        } else {
            this.e.setImageResource(R.drawable._2131427825_res_0x7f0b01f1);
            this.h.setImageResource(R.drawable._2131427831_res_0x7f0b01f7);
        }
        getKnitFragment().setRecyclerViewDescendantFocusability(393216);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        HwHealthPressureBarChart hwHealthPressureBarChart;
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("Section_SectionLineChart", "no need to bind");
            return;
        }
        boolean d = nru.d((Map) hashMap, "FIRST_TIME_BIND", false);
        this.z = nru.d((Map) hashMap, "START_TIME", 0L);
        d(nru.b(hashMap, "TOAST_TEXT", ""));
        if (!d) {
            long j = this.z;
            if (j != 0 && (hwHealthPressureBarChart = this.u) != null) {
                hwHealthPressureBarChart.reflesh(j);
            }
            f();
            return;
        }
        this.b = (DataInfos) nru.c(hashMap, "DATA_INFO", DataInfos.class, null);
        this.m = (List) nru.c(hashMap, "CURSOR_UP_AVERAGE_TEXT", List.class, null);
        this.g = (List) nru.c(hashMap, "CURSOR_UP_AVERAGE_STATUS", List.class, null);
        this.n.setText((CharSequence) nru.c(hashMap, "LEGEND_ONE_TEXT", String.class, ""));
        this.t.setText((CharSequence) nru.c(hashMap, "LEGEND_TWO_TEXT", String.class, ""));
        this.p.setText((CharSequence) nru.c(hashMap, "LEGEND_THREE_TEXT", String.class, ""));
        this.o.setText((CharSequence) nru.c(hashMap, "LEGEND_FOUR_TEXT", String.class, ""));
        HwHealthLineScrollChartHolder hwHealthLineScrollChartHolder = (HwHealthLineScrollChartHolder) nru.c(hashMap, "HEALTH_CHART_HOLDER", HwHealthLineScrollChartHolder.class, null);
        if (hwHealthLineScrollChartHolder != null) {
            this.r = hwHealthLineScrollChartHolder;
        }
        this.q = (BarChartRangeShowCallback) nru.c(hashMap, "RANGE_SHOW_CALL_BACK", BarChartRangeShowCallback.class, null);
        if (this.r == null) {
            LogUtil.a("Section_SectionLineChart", "mChartHolder is null");
            return;
        }
        DataInfos dataInfos = this.b;
        if (dataInfos == null || dataInfos == DataInfos.NoDataPlaceHolder) {
            LogUtil.a("Section_SectionLineChart", "mDataInfos is invalid");
            return;
        }
        c();
        n();
        f();
        k();
    }

    /* renamed from: com.huawei.health.knit.section.view.SectionLineChart$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2696a;

        static {
            int[] iArr = new int[DataInfos.values().length];
            f2696a = iArr;
            try {
                iArr[DataInfos.PressureDayDetail.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2696a[DataInfos.PressureWeekDetail.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2696a[DataInfos.PressureMonthDetail.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2696a[DataInfos.PressureYearDetail.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void k() {
        int i = AnonymousClass4.f2696a[this.b.ordinal()];
        if (i == 1) {
            jcf.bEz_(this.e, nsf.h(R$string.accessibility_last_day));
            jcf.bEz_(this.h, nsf.h(R$string.accessibility_next_day));
            return;
        }
        if (i == 2) {
            jcf.bEz_(this.e, nsf.h(R$string.accessibility_last_week));
            jcf.bEz_(this.h, nsf.h(R$string.accessibility_next_week));
        } else if (i == 3) {
            jcf.bEz_(this.e, nsf.h(R$string.accessibility_last_month));
            jcf.bEz_(this.h, nsf.h(R$string.accessibility_next_month));
        } else if (i == 4) {
            jcf.bEz_(this.e, nsf.h(R$string.accessibility_last_year));
            jcf.bEz_(this.h, nsf.h(R$string.accessibility_next_year));
        } else {
            ReleaseLogUtil.d("Section_SectionLineChart", "DataInfos is unknown");
        }
    }

    private void n() {
        if (this.z <= 0 || this.u.acquireScrollAdapter() == null) {
            return;
        }
        int e = e(this.z);
        int f = nom.f(e);
        e(e);
        HwHealthPressureBarChart hwHealthPressureBarChart = this.u;
        hwHealthPressureBarChart.setShowRange(f, hwHealthPressureBarChart.acquireScrollAdapter().acquireRange());
    }

    private int e(long j) {
        int i = AnonymousClass4.f2696a[this.b.ordinal()];
        if (i == 1) {
            return nom.a(j);
        }
        if (i == 2) {
            return nom.m(j);
        }
        if (i == 3) {
            return nom.f(j);
        }
        if (i != 4) {
            return 0;
        }
        return nom.t(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        HwHealthPressureBarChart hwHealthPressureBarChart = this.u;
        if (hwHealthPressureBarChart == null) {
            LogUtil.b("Section_SectionLineChart", "mLineChart null");
            return;
        }
        hwHealthPressureBarChart.setWillNotDraw(false);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.u.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
        this.u.setMarkerViewPosition(null);
        this.u.refresh();
    }

    private void c() {
        this.y = new ArrayList<>(16);
        eap eapVar = new eap(this.y, this.c);
        this.v = eapVar;
        this.ac.setAdapter(eapVar);
        this.ac.setIsCompatibleScrollView(true);
        this.ac.setIsScroll(false);
        if (this.u == null) {
            HwHealthPressureBarChart hwHealthPressureBarChart = new HwHealthPressureBarChart(this.c.getApplicationContext(), this.b);
            this.u = hwHealthPressureBarChart;
            hwHealthPressureBarChart.setLayerType(1, null);
            a();
            this.r.addDataLayer((HwHealthLineScrollChartHolder) this.u, this.b);
            this.y.add(0, this.u);
            this.v.notifyDataSetChanged();
            this.u.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: com.huawei.health.knit.section.view.SectionLineChart.5
                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
                public void onRangeShow(int i, int i2) {
                    if (jdl.d(TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(i2))) {
                        SectionLineChart.this.e(i);
                    }
                    SectionLineChart.this.e(i, i2);
                    if (SectionLineChart.this.q != null) {
                        SectionLineChart.this.q.onRangeChange(i, i2);
                    }
                }
            });
            this.u.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.health.knit.section.view.SectionLineChart.3
                @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
                public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                    int h = nom.h((int) SectionLineChart.this.u.fetchMarkViewMinuteValue());
                    ObserverManagerUtil.c("KnitHealthData_MarkView_" + SectionLineChart.this.b, Long.valueOf(TimeUnit.MINUTES.toMillis(h)));
                    SectionLineChart.this.e(h);
                    SectionLineChart.this.b(str, list);
                }
            });
            this.u.setSection(this);
        }
        g();
    }

    private void g() {
        List<Object> list = this.m;
        if (list != null && list.size() > 1 && (this.m.get(1) instanceof String)) {
            this.i.setText(String.valueOf(this.m.get(1)));
        }
        this.i.setVisibility(0);
        this.l.setText("--");
    }

    public void e(int i, int i2) {
        HwHealthPressureBarChart hwHealthPressureBarChart = this.u;
        if (hwHealthPressureBarChart == null) {
            return;
        }
        String formatRangeText = hwHealthPressureBarChart.formatRangeText(i, i2);
        long j = i * 60000;
        d(formatRangeText, DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_WEEK), jdl.ac(j));
    }

    public void b(String str, List<HwHealthMarkerView.a> list) {
        if (koq.c(list)) {
            a(str, list);
        } else {
            a("--", list);
        }
    }

    private void c(Context context) {
        if (context == null) {
            LogUtil.h("Section_SectionLineChart", "initConstructor context or dataType is null");
        } else {
            this.c = context;
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void initView(KnitFragment knitFragment, int i) {
        super.initView(knitFragment, i);
        knitFragment.setOnActivityResultListener(new KnitFragment.OnActivityResultListener() { // from class: com.huawei.health.knit.section.view.SectionLineChart.1
            @Override // com.huawei.health.knit.ui.KnitFragment.OnActivityResultListener
            public void onActivityResult(int i2, int i3, Intent intent) {
                if (i3 != -1 || intent == null) {
                    return;
                }
                Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
                if (serializableExtra instanceof HealthCalendar) {
                    SectionLineChart.this.s = (HealthCalendar) serializableExtra;
                    SectionLineChart sectionLineChart = SectionLineChart.this;
                    sectionLineChart.z = sectionLineChart.s.transformCalendar().getTimeInMillis();
                    SectionLineChart.this.u.reflesh(SectionLineChart.this.z);
                    SectionLineChart.this.f();
                }
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.e) {
            if (!this.w.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            b();
        } else if (view == this.h) {
            if (!this.x.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            j();
        } else if (view.getId() == R.id.first_layer_date) {
            KnitFragment knitFragment = getKnitFragment();
            if (knitFragment != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("calendar", this.s);
                bundle.putBoolean("isSetGrayUnmarkedDate", true);
                bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(new int[]{44301, 44307}));
                HealthCalendarActivity.cxl_(knitFragment, bundle);
            }
        } else {
            LogUtil.h("Section_SectionLineChart", "click view unknow");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        HwHealthPressureBarChart hwHealthPressureBarChart = this.u;
        if (hwHealthPressureBarChart == null || hwHealthPressureBarChart.isAnimating()) {
            return;
        }
        e();
    }

    private void j() {
        HwHealthPressureBarChart hwHealthPressureBarChart = this.u;
        if (hwHealthPressureBarChart == null || hwHealthPressureBarChart.isAnimating()) {
            return;
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (this.s == null) {
            this.s = new HealthCalendar();
        }
        this.s = this.s.transformFromCalendar(calendar);
    }

    private void a() {
        this.u.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.health.knit.section.view.SectionLineChart.2
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                SectionLineChart.this.i();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                SectionLineChart.this.i();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.u.canScrollOlderPager()) {
            this.e.setVisibility(0);
            this.e.setClickable(true);
            this.w.set(true);
        } else {
            this.e.setVisibility(4);
            this.e.setClickable(false);
            this.w.set(false);
        }
        if (this.u.canScrollNewerPager()) {
            this.h.setVisibility(0);
            this.h.setClickable(true);
            this.x.set(true);
        } else {
            this.h.setVisibility(4);
            this.h.setClickable(false);
            this.x.set(false);
        }
    }

    private void e() {
        HwHealthPressureBarChart hwHealthPressureBarChart = this.u;
        Objects.requireNonNull(hwHealthPressureBarChart);
        hwHealthPressureBarChart.scrollOnePageOlder(new ehb(this, hwHealthPressureBarChart));
    }

    private void d() {
        HwHealthPressureBarChart hwHealthPressureBarChart = this.u;
        Objects.requireNonNull(hwHealthPressureBarChart);
        hwHealthPressureBarChart.scrollOnePageNewer(new ehd(this, hwHealthPressureBarChart));
    }

    private void d(String str, String str2, boolean z) {
        this.d.setText(str);
    }

    private void a(String str, List<HwHealthMarkerView.a> list) {
        HealthTextView healthTextView = this.j;
        if (healthTextView != null) {
            healthTextView.setText(str);
        } else {
            LogUtil.h("Section_SectionLineChart", "notifyNumerical, mDateOrTime is null");
        }
        b(list);
    }

    private void b(List<HwHealthMarkerView.a> list) {
        String d;
        if (koq.b(list)) {
            LogUtil.h("Section_SectionLineChart", "notifyView datas is empty");
            d = "--";
        } else {
            d = d(list.get(list.size() - 1).b);
        }
        this.l.setText(d);
        if ("--".equals(d)) {
            this.i.setVisibility(4);
            this.f.setVisibility(4);
            return;
        }
        List<Object> list2 = this.m;
        if (list2 != null && list2.size() > 1) {
            String thirdLayerText = getThirdLayerText();
            String c = c(list.get(list.size() - 1).b);
            this.i.setText(thirdLayerText);
            this.f.setText(c);
        }
        this.i.setVisibility(0);
        this.f.setVisibility(0);
    }

    public String d(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : UnitUtil.e(hwHealthBaseEntry.getY(), 1, 0);
    }

    public String c(HwHealthBaseEntry hwHealthBaseEntry) {
        return eeu.b((hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? 0 : (int) hwHealthBaseEntry.getY(), this.g);
    }

    public String getThirdLayerText() {
        if (this.b == DataInfos.PressureWeekDetail || this.b == DataInfos.PressureMonthDetail) {
            return String.valueOf(this.m.get(0));
        }
        return this.b == DataInfos.PressureYearDetail ? String.valueOf(this.m.get(1)) : "";
    }

    private void d(String str) {
        if (str.isEmpty()) {
            return;
        }
        ViewStub viewStub = (ViewStub) this.k.findViewById(R.id.auto_test_view_stub);
        if (viewStub != null) {
            View inflate = viewStub.inflate();
            if (inflate instanceof RelativeLayout) {
                this.ab = (RelativeLayout) inflate;
            }
        }
        nlg.cxS_(this.c, new IBaseResponseCallback() { // from class: egx
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                SectionLineChart.this.e(i, obj);
            }
        }, this.ab, str, new String[]{"pressure_auto_detector_agree_no_again_tip", "pressure_auto_detector_dialog_time", "pressure_auto_detector_count", "pressure_auto_detetor_is_show"});
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (i == 0) {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity");
            intent.putExtra("pressure_is_have_datas", true);
            intent.putExtra("from_card", true);
            try {
                this.c.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                LogUtil.h("Section_SectionLineChart", "ActivityNotFoundException", e.getMessage());
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section_SectionLineChart";
    }
}
