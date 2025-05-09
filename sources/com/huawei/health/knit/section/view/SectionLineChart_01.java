package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.listener.LineChartRangeShowCallback;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.eaq;
import defpackage.ecm;
import defpackage.eeu;
import defpackage.ehc;
import defpackage.ehe;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nom;
import defpackage.nru;
import defpackage.nsk;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
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
public class SectionLineChart_01 extends BaseSection implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private List<Object> f2697a;
    private final AtomicBoolean aa;
    private HealthTextView ab;
    private String ac;
    private Resources ad;
    private HealthViewPager af;
    private long ag;
    private ViewStub ah;
    private ImageView b;
    protected View c;
    private ImageView d;
    private HealthTextView e;
    private Context f;
    private HealthTextView g;
    private HealthCalendar h;
    private HealthTextView i;
    private LineChartRangeShowCallback j;
    private DataInfos k;
    private HealthTextView l;
    private boolean m;
    private HealthTextView n;
    private HealthTextView o;
    private boolean p;
    private HwHealthCommonLineChart q;
    private boolean r;
    private boolean s;
    private View t;
    private eaq u;
    private HwHealthLineScrollChartHolder v;
    private HealthTextView w;
    private ArrayList<View> x;
    private final AtomicBoolean y;
    private String z;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    public SectionLineChart_01(Context context) {
        this(context, null);
    }

    public SectionLineChart_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionLineChart_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ag = System.currentTimeMillis();
        this.aa = new AtomicBoolean(false);
        this.y = new AtomicBoolean(false);
        this.ac = "TEMPERATURE_MIN_MAX";
        this.z = "TEMPERATURE_MIN_MAX";
        this.r = true;
        this.m = false;
        e(context);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionLineChart_01", "onCreateView");
        h();
        return this.c;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionLineChart_01", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionLineChart_01", "no need to bind");
            return;
        }
        Object obj = hashMap.get("FIRST_TIME_BIND");
        boolean booleanValue = obj instanceof Boolean ? ((Boolean) obj).booleanValue() : false;
        Object obj2 = hashMap.get("START_TIME");
        if (obj2 instanceof Long) {
            this.ag = ((Long) obj2).longValue();
        }
        this.k = (DataInfos) nru.c(hashMap, "DATA_INFO", DataInfos.class, null);
        this.ag = nru.d((Map) hashMap, "START_TIME", 0L);
        nsy.cMr_(this.e, (CharSequence) nru.c(hashMap, "CURSOR_UP_AVERAGE_TEXT", String.class, ""));
        this.f2697a = (List) nru.c(hashMap, "BOTTOM_LEGEND_TEXT", List.class, null);
        this.v = (HwHealthLineScrollChartHolder) nru.c(hashMap, "HEALTH_CHART_HOLDER", HwHealthLineScrollChartHolder.class, null);
        this.j = (LineChartRangeShowCallback) nru.c(hashMap, "RANGE_SHOW_CALL_BACK", LineChartRangeShowCallback.class, null);
        this.ac = (String) nru.c(hashMap, "SHOW_DATA_TYPE", String.class, "");
        nsy.cMl_(this.w, (Drawable) nru.c(hashMap, "BOTTOM_LEFT_COLOR", Drawable.class, null));
        nsy.cMl_(this.ab, (Drawable) nru.c(hashMap, "BOTTOM_MID_COLOR", Drawable.class, null));
        nsy.cMl_(this.n, (Drawable) nru.c(hashMap, "BOTTOM_RIGHT_COLOR", Drawable.class, null));
        DataInfos dataInfos = this.k;
        if (dataInfos == null || dataInfos == DataInfos.NoDataPlaceHolder) {
            LogUtil.a("SectionLineChart_01", "mDataInfos is invalid");
        } else {
            d(booleanValue);
        }
    }

    private void d(boolean z) {
        if (!z) {
            LogUtil.a("SectionLineChart_01", "is not FirstTimeBind");
            if (this.z != this.ac) {
                g();
                this.z = this.ac;
                return;
            } else {
                j();
                return;
            }
        }
        if (this.v == null) {
            LogUtil.a("SectionLineChart_01", "mChartHolder is null");
            return;
        }
        e();
        n();
        j();
    }

    private void g() {
        HwHealthCommonLineChart hwHealthCommonLineChart;
        LogUtil.c("SectionLineChart_01", "onSelect is ", this.ac);
        n();
        if (this.v == null || (hwHealthCommonLineChart = this.q) == null || hwHealthCommonLineChart.acquireScrollAdapter() == null) {
            return;
        }
        if (this.p) {
            this.q.clearValues();
            this.v.addDataLayer((HwHealthLineScrollChartHolder) this.q, DataInfos.TemperatureDayDetail);
        }
        this.p = true;
        this.q.setWillNotDraw(false);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.q.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(1 | acquireScrollAdapter.getFlag());
        this.q.setMarkerViewPosition(null);
        this.q.resetYaxisAnimateValue();
        this.q.refresh();
    }

    private void e(Context context) {
        if (context == null) {
            LogUtil.h("SectionLineChart_01", "initConstructor context or dataType is null");
            return;
        }
        Context e = BaseApplication.e();
        this.f = e;
        this.ad = e.getResources();
        this.r = UnitUtil.d();
        boolean o = Utils.o();
        this.s = o;
        if (o) {
            this.ac = "SKIN_TEMPERATURE_MIN_MAX";
        }
    }

    private void h() {
        if (this.c == null) {
            LogUtil.h("SectionLineChart_01", "initView mainView is null, start to inflate");
            this.c = LayoutInflater.from(this.f).inflate(R.layout.section_temperature_barchart, (ViewGroup) this, false);
        }
        this.d = (ImageView) this.c.findViewById(R.id.temperature_arrow_left);
        this.b = (ImageView) this.c.findViewById(R.id.temperature_arrow_right);
        this.o = (HealthTextView) this.c.findViewById(R.id.temperature_detail_time_date_tv);
        this.g = (HealthTextView) this.c.findViewById(R.id.temperature_cursor_time);
        this.l = (HealthTextView) this.c.findViewById(R.id.temperature_cursor_value);
        this.i = (HealthTextView) this.c.findViewById(R.id.temperature_cursor_unit);
        this.e = (HealthTextView) this.c.findViewById(R.id.temperature_cursor_avg);
        this.af = (HealthViewPager) this.c.findViewById(R.id.temperature_viewpager);
        m();
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.o.setOnClickListener(this);
        Typeface cKN_ = nsk.cKN_();
        this.l.setTypeface(cKN_);
        this.i.setTypeface(cKN_);
        setLiftAndRightImage();
    }

    private void m() {
        if (nsn.t()) {
            c(R.id.temperature_chart_view_legend_trable, R.id.temperature_chart_view_legend_trable_inflated);
        } else {
            c(R.id.temperature_chart_view_legend, R.id.temperature_chart_view_legend_inflated);
        }
        View view = this.t;
        if (view != null) {
            this.w = (HealthTextView) view.findViewById(R.id.temperature_low_legend);
            this.ab = (HealthTextView) this.t.findViewById(R.id.temperature_normal_legend);
            this.n = (HealthTextView) this.t.findViewById(R.id.temperature_high_legend);
        }
    }

    private void c(int i, int i2) {
        if (this.ah == null) {
            this.ah = (ViewStub) this.c.findViewById(i);
        }
        if (this.ah.getParent() != null) {
            this.t = this.ah.inflate();
        } else {
            this.t = this.c.findViewById(i2);
        }
    }

    public void setLiftAndRightImage() {
        if (LanguageUtil.bc(this.f)) {
            this.d.setBackground(ContextCompat.getDrawable(this.f, R.drawable._2131427831_res_0x7f0b01f7));
            this.b.setBackground(ContextCompat.getDrawable(this.f, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.d.setBackground(ContextCompat.getDrawable(this.f, R.drawable._2131427825_res_0x7f0b01f1));
            this.b.setBackground(ContextCompat.getDrawable(this.f, R.drawable._2131427831_res_0x7f0b01f7));
        }
    }

    private void j() {
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.q.acquireScrollAdapter();
        if (acquireScrollAdapter == null) {
            LogUtil.b("SectionLineChart_01", "refreshShowRange scrollAdapter is null.");
            return;
        }
        acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
        this.q.setWillNotDraw(false);
        this.q.setMarkerViewPosition(null);
        this.q.resetYaxisAnimateValue();
        this.q.reflesh(this.ag);
        LogUtil.a("SectionLineChart_01", "lineChart reflesh on show range change " + this.ag);
    }

    private void e() {
        this.x = new ArrayList<>(16);
        this.u = new eaq(this.x);
        this.af.setIsCompatibleScrollView(true);
        this.af.setIsScroll(false);
        this.af.setAdapter(this.u);
        if (this.q == null) {
            LogUtil.a("SectionLineChart_01", "mLineChart == null");
            HwHealthCommonLineChart hwHealthCommonLineChart = new HwHealthCommonLineChart(this.f.getApplicationContext());
            this.q = hwHealthCommonLineChart;
            hwHealthCommonLineChart.setMoveToLastDataStamp(true);
            this.q.setLayerType(1, null);
            b();
            this.x.clear();
            this.x.add(0, this.q);
            this.v.addDataLayer((HwHealthLineScrollChartHolder) this.q, this.k);
            this.u.notifyDataSetChanged();
            this.q.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: com.huawei.health.knit.section.view.SectionLineChart_01.5
                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
                public void onRangeShow(int i, int i2) {
                    long millis = TimeUnit.MINUTES.toMillis(i);
                    if (jdl.d(millis, TimeUnit.MINUTES.toMillis(i2))) {
                        SectionLineChart_01.this.a(i);
                    }
                    SectionLineChart_01.this.d(i, i2);
                    if (SectionLineChart_01.this.j != null) {
                        SectionLineChart_01.this.j.onRangeChange(SectionLineChart_01.this.getContext(), millis);
                    }
                }
            });
            this.q.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.health.knit.section.view.SectionLineChart_01.4
                @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
                public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                    int h = nom.h((int) SectionLineChart_01.this.q.fetchMarkViewMinuteValue());
                    ObserverManagerUtil.c("KnitHealthData_MarkView_" + SectionLineChart_01.this.k, Long.valueOf(TimeUnit.MINUTES.toMillis(h)));
                    SectionLineChart_01.this.a(h);
                    SectionLineChart_01.this.d(str, list);
                }
            });
        }
    }

    private void b() {
        this.q.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.health.knit.section.view.SectionLineChart_01.1
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                SectionLineChart_01.this.f();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                SectionLineChart_01.this.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.q.canScrollOlderPager()) {
            this.d.setVisibility(0);
            this.d.setClickable(true);
            this.aa.set(true);
        } else {
            this.d.setVisibility(4);
            this.d.setClickable(false);
            this.aa.set(false);
        }
        if (this.q.canScrollNewerPager()) {
            this.b.setVisibility(0);
            this.b.setClickable(true);
            this.y.set(true);
        } else {
            this.b.setVisibility(4);
            this.b.setClickable(false);
            this.y.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (this.h == null) {
            this.h = new HealthCalendar();
        }
        this.h = this.h.transformFromCalendar(calendar);
    }

    public void d(int i, int i2) {
        HwHealthCommonLineChart hwHealthCommonLineChart = this.q;
        if (hwHealthCommonLineChart == null) {
            return;
        }
        String formatRangeText = hwHealthCommonLineChart.formatRangeText(i, i2);
        long j = i * 60000;
        setDayAndWeek(formatRangeText, DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_WEEK), jdl.ac(j));
    }

    public void setDayAndWeek(String str, String str2, boolean z) {
        this.o.setText(str);
        if (this.k != DataInfos.TemperatureDayDetail) {
            return;
        }
        if (z) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
        }
    }

    public void a(String str, List<HwHealthMarkerView.a> list) {
        if (koq.c(list)) {
            c(str, list);
        } else {
            c("--", (List<HwHealthMarkerView.a>) null);
        }
    }

    protected void d(String str, List<HwHealthMarkerView.a> list) {
        String d = eeu.d(eeu.d(str));
        if (this.k == DataInfos.TemperatureDayDetail) {
            a(d, list);
        } else {
            a(str, list);
        }
    }

    public void c(String str, List<HwHealthMarkerView.a> list) {
        HealthTextView healthTextView = this.g;
        if (healthTextView != null) {
            healthTextView.setText(str);
        } else {
            LogUtil.h("SectionLineChart_01", "notifyNumerical, mCursorTime is null");
        }
        b(list, str);
    }

    private void b(List<HwHealthMarkerView.a> list, String str) {
        String d;
        String string;
        View view;
        if (koq.b(list)) {
            LogUtil.h("SectionLineChart_01", "notifyView datas is empty");
            d = "--";
        } else {
            d = d(list.get(list.size() - 1).b);
        }
        LogUtil.a("SectionLineChart_01", "notifyView data is ", d);
        if ("--".equals(d)) {
            this.l.setText(d);
            this.e.setVisibility(8);
            this.i.setVisibility(8);
            return;
        }
        if (this.r) {
            string = this.ad.getString(R$string.IDS_settings_health_temperature_unit, "");
        } else {
            string = this.ad.getString(R$string.IDS_temp_unit_fahrenheit, "");
        }
        DataInfos dataInfos = this.k;
        if (dataInfos != null && dataInfos.isDayData()) {
            this.l.setTextSize(0, getResources().getDimension(R.dimen._2131365073_res_0x7f0a0cd1));
        }
        this.l.setText(d);
        this.i.setText(string);
        if (d(str)) {
            this.e.setVisibility(8);
        } else {
            this.e.setVisibility(0);
        }
        this.i.setVisibility(0);
        if (!nsn.l() || (view = this.c) == null) {
            return;
        }
        view.requestLayout();
        invalidate();
    }

    private boolean d(String str) {
        HwHealthBaseBarLineData hwHealthBaseBarLineData = (HwHealthBaseBarLineData) this.q.getData();
        if (hwHealthBaseBarLineData == null) {
            LogUtil.b("SectionLineChart_01", "getHwHealthUnixBarDataSet data null");
            return true;
        }
        List<T> dataSets = hwHealthBaseBarLineData.getDataSets();
        if (dataSets == 0) {
            LogUtil.b("SectionLineChart_01", "dataSets is null");
            return true;
        }
        for (T t : dataSets) {
            if (t != null) {
                for (Map.Entry<Long, IStorageModel> entry : t.acquireOriginalVals().entrySet()) {
                    if (entry != null && c(entry.getKey().longValue(), str)) {
                        IStorageModel value = entry.getValue();
                        if ((value instanceof ecm) && ((ecm) value).c() > 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean c(long j, String str) {
        String d = eeu.d(j);
        return d != null && d.equals(str);
    }

    private void n() {
        String valueOf;
        String valueOf2;
        String valueOf3;
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.ac)) {
            this.w.setVisibility(8);
            this.ab.setVisibility(8);
            this.n.setVisibility(8);
            return;
        }
        if (this.f2697a.isEmpty()) {
            return;
        }
        if (this.r) {
            valueOf = String.valueOf(this.f2697a.get(0));
            valueOf2 = String.valueOf(this.f2697a.get(1));
            valueOf3 = String.valueOf(this.f2697a.get(2));
        } else {
            valueOf = String.valueOf(this.f2697a.get(3));
            valueOf2 = String.valueOf(this.f2697a.get(4));
            valueOf3 = String.valueOf(this.f2697a.get(5));
        }
        this.w.setText(valueOf);
        this.ab.setText(valueOf2);
        this.n.setText(valueOf3);
        this.w.setVisibility(0);
        this.ab.setVisibility(0);
        this.n.setVisibility(0);
        if (LanguageUtil.bf(this.f)) {
            HealthTextView healthTextView = this.w;
            healthTextView.setTextSize(0, healthTextView.getTextSize() * 0.7f);
            HealthTextView healthTextView2 = this.ab;
            healthTextView2.setTextSize(0, healthTextView2.getTextSize() * 0.7f);
            HealthTextView healthTextView3 = this.n;
            healthTextView3.setTextSize(0, healthTextView3.getTextSize() * 0.7f);
        }
    }

    public String d(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : UnitUtil.e(hwHealthBaseEntry.getY(), 1, 1);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void initView(KnitFragment knitFragment, int i) {
        super.initView(knitFragment, i);
        knitFragment.setOnActivityResultListener(new KnitFragment.OnActivityResultListener() { // from class: com.huawei.health.knit.section.view.SectionLineChart_01.3
            @Override // com.huawei.health.knit.ui.KnitFragment.OnActivityResultListener
            public void onActivityResult(int i2, int i3, Intent intent) {
                if (i3 != -1 || intent == null) {
                    return;
                }
                Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
                if (serializableExtra instanceof HealthCalendar) {
                    SectionLineChart_01.this.h = (HealthCalendar) serializableExtra;
                    SectionLineChart_01 sectionLineChart_01 = SectionLineChart_01.this;
                    sectionLineChart_01.ag = sectionLineChart_01.h.transformCalendar().getTimeInMillis();
                    if (SectionLineChart_01.this.q != null) {
                        SectionLineChart_01.this.q.reflesh(SectionLineChart_01.this.ag);
                        LogUtil.a("SectionLineChart_01", "lineChart reflesh on activity result " + SectionLineChart_01.this.ag);
                    }
                }
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.d) {
            if (!this.aa.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            d();
        } else if (view == this.b) {
            if (!this.y.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            i();
        } else if (view.getId() == R.id.temperature_detail_time_date_tv) {
            KnitFragment knitFragment = getKnitFragment();
            if (knitFragment != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("calendar", this.h);
                bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(new int[]{2104, HiHealthDataType.b, DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()}, new int[]{DicDataTypeUtil.DataType.LOW_BODY_TEMPERATURE_ALARM.value(), DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM.value()}));
                HealthCalendarActivity.cxl_(knitFragment, bundle);
            }
        } else {
            LogUtil.h("SectionLineChart_01", "click view unknow");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        LogUtil.c("SectionLineChart_01", "Day initLeftArrowClick");
        HwHealthCommonLineChart hwHealthCommonLineChart = this.q;
        if (hwHealthCommonLineChart == null || hwHealthCommonLineChart.isAnimating()) {
            return;
        }
        c();
    }

    private void i() {
        HwHealthCommonLineChart hwHealthCommonLineChart = this.q;
        if (hwHealthCommonLineChart == null || hwHealthCommonLineChart.isAnimating()) {
            return;
        }
        a();
    }

    private void c() {
        HwHealthCommonLineChart hwHealthCommonLineChart = this.q;
        Objects.requireNonNull(hwHealthCommonLineChart);
        hwHealthCommonLineChart.scrollOnePageOlder(new ehc(this, hwHealthCommonLineChart));
    }

    private void a() {
        HwHealthCommonLineChart hwHealthCommonLineChart = this.q;
        Objects.requireNonNull(hwHealthCommonLineChart);
        hwHealthCommonLineChart.scrollOnePageNewer(new ehe(this, hwHealthCommonLineChart));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionLineChart_01";
    }
}
