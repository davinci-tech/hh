package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.PopupWindow;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.TemperatureBarChart;
import com.huawei.health.knit.section.listener.LineChartRangeShowCallback;
import com.huawei.health.knit.section.view.SectionBarChart_01;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.eaq;
import defpackage.edm;
import defpackage.egj;
import defpackage.egk;
import defpackage.jdl;
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
public class SectionBarChart_01 extends BaseSection implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    protected View f2672a;
    private String aa;
    private final AtomicBoolean ab;
    private final AtomicBoolean ac;
    private HealthTextView ad;
    private Resources ae;
    private long af;
    private PopupWindow ag;
    private String ah;
    private HealthViewPager ai;
    private ImageView aj;
    private ViewStub ak;
    private View al;
    private eaq b;
    private TemperatureBarChart c;
    private HealthTextView d;
    private HwHealthBarScrollChartHolder e;
    private ArrayList<View> f;
    private List<Object> g;
    private ImageView h;
    private ImageView i;
    private HealthCalendar j;
    private LineChartRangeShowCallback k;
    private HealthTextView l;
    private HealthTextView m;
    private Context n;
    private HealthTextView o;
    private HealthTextView p;
    private View q;
    private boolean r;
    private DataInfos s;
    private HealthTextView t;
    private HealthTextView u;
    private boolean v;
    private boolean w;
    private boolean x;
    private long y;
    private HealthTextView z;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    public SectionBarChart_01(Context context) {
        this(context, null);
    }

    public SectionBarChart_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionBarChart_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.af = System.currentTimeMillis();
        this.ab = new AtomicBoolean(false);
        this.ac = new AtomicBoolean(false);
        this.ah = "TEMPERATURE_MIN_MAX";
        this.aa = "TEMPERATURE_MIN_MAX";
        this.v = true;
        this.r = false;
        b(context);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionBarChart_01", "onCreateView");
        g();
        return this.f2672a;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionBarChart_01", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionBarChart_01", "no need to bind");
            return;
        }
        Object obj = hashMap.get("FIRST_TIME_BIND");
        boolean booleanValue = obj instanceof Boolean ? ((Boolean) obj).booleanValue() : false;
        Object obj2 = hashMap.get("START_TIME");
        if (obj2 instanceof Long) {
            Long l = (Long) obj2;
            r3 = this.af != l.longValue();
            this.af = l.longValue();
        }
        setView(hashMap);
        if (!booleanValue) {
            LogUtil.a("SectionBarChart_01", "is not FirstTimeBind");
            if (this.aa != this.ah) {
                j();
                this.aa = this.ah;
                return;
            } else {
                e(booleanValue, r3);
                return;
            }
        }
        m();
        if (this.e == null) {
            LogUtil.a("SectionBarChart_01", "mChartHolder is null");
            return;
        }
        DataInfos dataInfos = this.s;
        if (dataInfos == null || dataInfos == DataInfos.NoDataPlaceHolder) {
            LogUtil.a("SectionBarChart_01", "mDataInfos is invalid");
            return;
        }
        c();
        l();
        nsy.cMl_(this.u, (Drawable) nru.c(hashMap, "BOTTOM_LEFT_COLOR", Drawable.class, null));
        nsy.cMl_(this.ad, (Drawable) nru.c(hashMap, "BOTTOM_MID_COLOR", Drawable.class, null));
        nsy.cMl_(this.p, (Drawable) nru.c(hashMap, "BOTTOM_RIGHT_COLOR", Drawable.class, null));
        e(booleanValue, r3);
    }

    private void setView(HashMap<String, Object> hashMap) {
        this.s = (DataInfos) nru.c(hashMap, "DATA_INFO", DataInfos.class, null);
        this.af = nru.d((Map) hashMap, "START_TIME", 0L);
        this.g = (List) nru.c(hashMap, "BOTTOM_LEGEND_TEXT", List.class, null);
        this.e = (HwHealthBarScrollChartHolder) nru.c(hashMap, "HEALTH_CHART_HOLDER", HwHealthBarScrollChartHolder.class, null);
        this.k = (LineChartRangeShowCallback) nru.c(hashMap, "RANGE_SHOW_CALL_BACK", LineChartRangeShowCallback.class, null);
        this.ah = (String) nru.c(hashMap, "SHOW_DATA_TYPE", String.class, "");
    }

    private void m() {
        if (this.s == DataInfos.TemperatureDayDetail) {
            ObserverManagerUtil.d(new Observer() { // from class: com.huawei.health.knit.section.view.SectionBarChart_01.3
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    LogUtil.a("SectionBarChart_01", "notify updateChart");
                    if (SectionBarChart_01.this.c != null) {
                        SectionBarChart_01.this.c.setMarkerViewPosition(null);
                        SectionBarChart_01.this.c.highlightValue(nom.f((int) (jdl.e(SectionBarChart_01.this.af, jdl.m(SectionBarChart_01.this.af), 30) / 60000)), false);
                        ObserverManagerUtil.e("REFRESH_TEMPERATURE_CHART");
                    }
                }
            }, "REFRESH_TEMPERATURE_CHART");
        }
    }

    private void j() {
        TemperatureBarChart temperatureBarChart;
        LogUtil.a("SectionBarChart_01", "onSelect is ", this.ah);
        l();
        if (this.e == null || (temperatureBarChart = this.c) == null || temperatureBarChart.acquireScrollAdapter() == null) {
            return;
        }
        boolean z = this.w;
        if (z) {
            LogUtil.a("SectionBarChart_01", "mIsSelected is ", Boolean.valueOf(z));
            this.c.clearValues();
            this.e.addDataLayer((HwHealthBarScrollChartHolder) this.c, this.s);
        }
        this.w = true;
        this.c.setWillNotDraw(false);
        LogUtil.a("SectionBarChart_01", "mBarChart.setShowDataType is ", this.ah);
        this.c.setShowDataType(this.ah);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.c.acquireScrollAdapter();
        if (acquireScrollAdapter != null) {
            acquireScrollAdapter.setFlag(1 | acquireScrollAdapter.getFlag());
        }
        this.c.setMarkerViewPosition(null);
        this.c.refresh();
    }

    public void c() {
        this.f = new ArrayList<>(16);
        this.b = new eaq(this.f);
        this.ai.setIsCompatibleScrollView(true);
        this.ai.setIsScroll(false);
        this.ai.setAdapter(this.b);
        if (this.c == null) {
            TemperatureBarChart temperatureBarChart = new TemperatureBarChart(this.n.getApplicationContext());
            this.c = temperatureBarChart;
            temperatureBarChart.setLayerType(1, null);
            a();
            this.f.clear();
            this.f.add(this.c);
            this.e.addDataLayer((HwHealthBarScrollChartHolder) this.c, this.s);
            this.b.notifyDataSetChanged();
            k();
            o();
            t();
            s();
            this.c.acquireScrollAdapter().acquireXAxisValueFormatter().setHealthType(HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_TEMPERATURE);
        }
    }

    private void o() {
        this.c.setOnDataChangedListener(new TemperatureBarChart.OnDataChangedListener() { // from class: egi
            @Override // com.huawei.health.knit.section.chart.TemperatureBarChart.OnDataChangedListener
            public final void onDataChangedListener(edm edmVar) {
                SectionBarChart_01.this.d(edmVar);
            }
        });
    }

    public /* synthetic */ void d(edm edmVar) {
        float a2 = edmVar.a();
        float i = edmVar.i();
        float c = edmVar.c();
        float d = edmVar.d();
        if ((a2 == 0.0f || i == 0.0f) && (c == 0.0f || d == 0.0f)) {
            LogUtil.h("SectionBarChart_01", "initViews MaxTemperatureValue = ", Float.valueOf(edmVar.a()), " MaxSuspectedValue = ", Float.valueOf(edmVar.c()));
            this.o.setText("--");
            this.l.setVisibility(8);
            this.aj.setVisibility(8);
            return;
        }
        String d2 = d(a2, i, c, d, this.v);
        Pair<String, String> aiN_ = aiN_(a2, i, c, d, this.v);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence) aiN_.first);
        aiO_(spannableStringBuilder, (String) aiN_.first, (String) aiN_.second);
        if (!d2.isEmpty()) {
            this.aj.setVisibility(0);
            this.z.setText(d2);
        } else {
            this.aj.setVisibility(8);
        }
        this.o.setText(spannableStringBuilder);
        this.l.setVisibility(8);
    }

    private void aiO_(SpannableStringBuilder spannableStringBuilder, String str, String str2) {
        int i;
        int indexOf = str.indexOf(str2);
        int length = str2.length();
        if (indexOf < 0 || (i = length + indexOf) > spannableStringBuilder.length()) {
            LogUtil.b("SectionBarChart_01", "TemperatureStorage spannableString overflow");
            return;
        }
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362906_res_0x7f0a045a)), 0, str.length(), 17);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131299241_res_0x7f090ba9)), 0, str.length(), 17);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363006_res_0x7f0a04be)), indexOf, i, 17);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131299236_res_0x7f090ba4)), indexOf, i, 17);
        spannableStringBuilder.setSpan(new TypefaceSpan(Constants.FONT), indexOf, i, 17);
    }

    private static String d(float f, float f2, float f3, float f4, boolean z) {
        return (f3 == 0.0f || f4 == 0.0f) ? "" : (!(f == 0.0f && f2 == 0.0f && f3 != f4) && (f <= 0.0f || f2 <= 0.0f)) ? "" : d(f3, f4, z);
    }

    private static Pair<String, String> aiN_(float f, float f2, float f3, float f4, boolean z) {
        if (f == 0.0f && f2 == 0.0f && f3 > 0.0f && f4 > 0.0f) {
            if (f3 != f4) {
                return aiL_(f3, f4, z);
            }
            return aiM_(f4, z);
        }
        if (f > 0.0f && f2 > 0.0f && f3 == 0.0f && f4 == 0.0f) {
            return aiL_(f, f2, z);
        }
        if (f > 0.0f && f2 > 0.0f && f3 > 0.0f && f4 > 0.0f) {
            return aiL_(Math.max(f, f3), Math.min(f2, f4), z);
        }
        return new Pair<>("--", "");
    }

    private static String d(float f, float f2, boolean z) {
        String e = UnitUtil.e(f2, 1, 1);
        String e2 = UnitUtil.e(f, 1, 1);
        if (f2 != f) {
            if (z) {
                return BaseApplication.getContext().getString(R$string.IDS_temp_suspected_range_celsius_unit, e, e2);
            }
            return BaseApplication.getContext().getString(R$string.IDS_temp_suspected_range_fahrenheit_unit, e, e2);
        }
        if (z) {
            return BaseApplication.getContext().getString(R$string.IDS_temp_suspected_celsius_unit, e2);
        }
        return BaseApplication.getContext().getString(R$string.IDS_temp_suspected_fahrenheit_unit, e2);
    }

    private static Pair<String, String> aiM_(float f, boolean z) {
        String string;
        String e = UnitUtil.e(f, 1, 1);
        if (z) {
            string = BaseApplication.getContext().getString(R$string.IDS_temp_over_suspected_celsius_unit, e);
        } else {
            string = BaseApplication.getContext().getString(R$string.IDS_temp_over_suspected_fahrenheit_unit, e);
        }
        return new Pair<>(string, e);
    }

    private static Pair<String, String> aiL_(float f, float f2, boolean z) {
        String string;
        String string2 = BaseApplication.getContext().getString(R$string.IDS_hw_health_coresleep_standard_range_1, UnitUtil.e(f2, 1, 1), UnitUtil.e(f, 1, 1));
        if (z) {
            string = BaseApplication.getContext().getString(R$string.IDS_temperature_celsius_unit, string2);
        } else {
            string = BaseApplication.getContext().getString(R$string.IDS_temperature_fahrenheit_unit, string2);
        }
        return new Pair<>(string, string2);
    }

    private void t() {
        this.c.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: com.huawei.health.knit.section.view.SectionBarChart_01.5
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public void onRangeShow(int i, int i2) {
                long millis = TimeUnit.MINUTES.toMillis(i);
                if (jdl.d(millis, TimeUnit.MINUTES.toMillis(i2))) {
                    SectionBarChart_01.this.a(i);
                }
                SectionBarChart_01.this.b(i, i2);
                if (SectionBarChart_01.this.k != null) {
                    SectionBarChart_01.this.k.onRangeChange(SectionBarChart_01.this.getContext(), millis);
                }
            }
        });
    }

    private void s() {
        this.c.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.health.knit.section.view.SectionBarChart_01.1
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                SectionBarChart_01.this.m.setText(str);
                int h = nom.h((int) SectionBarChart_01.this.c.fetchMarkViewMinuteValue());
                ObserverManagerUtil.c("KnitHealthData_MarkView_" + SectionBarChart_01.this.s, Long.valueOf(TimeUnit.MINUTES.toMillis(h)));
                SectionBarChart_01.this.a(h);
            }
        });
    }

    private void l() {
        String valueOf;
        String valueOf2;
        String valueOf3;
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.ah)) {
            this.u.setVisibility(8);
            this.ad.setVisibility(8);
            this.p.setVisibility(8);
            return;
        }
        if (this.g.isEmpty()) {
            return;
        }
        if (this.v) {
            valueOf = String.valueOf(this.g.get(0));
            valueOf2 = String.valueOf(this.g.get(1));
            valueOf3 = String.valueOf(this.g.get(2));
        } else {
            valueOf = String.valueOf(this.g.get(3));
            valueOf2 = String.valueOf(this.g.get(4));
            valueOf3 = String.valueOf(this.g.get(5));
        }
        this.u.setText(valueOf);
        this.ad.setText(valueOf2);
        this.p.setText(valueOf3);
        this.u.setVisibility(0);
        this.ad.setVisibility(0);
        this.p.setVisibility(0);
    }

    private void g() {
        if (this.f2672a == null) {
            LogUtil.h("SectionBarChart_01", "initView mainView is null, start to inflate");
            this.f2672a = LayoutInflater.from(this.n).inflate(R.layout.section_temperature_barchart, (ViewGroup) this, false);
        }
        if (this.al == null) {
            LogUtil.h("SectionBarChart_01", "initView mWarningTipsView is null, start to inflate");
            this.al = LayoutInflater.from(this.n).inflate(R.layout.section_temperature_barchart_tips_popview, (ViewGroup) this, false);
        }
        this.i = (ImageView) this.f2672a.findViewById(R.id.temperature_arrow_left);
        this.h = (ImageView) this.f2672a.findViewById(R.id.temperature_arrow_right);
        this.t = (HealthTextView) this.f2672a.findViewById(R.id.temperature_detail_time_date_tv);
        this.m = (HealthTextView) this.f2672a.findViewById(R.id.temperature_cursor_time);
        this.o = (HealthTextView) this.f2672a.findViewById(R.id.temperature_cursor_value);
        this.aj = (ImageView) this.f2672a.findViewById(R.id.warning_tips_img);
        this.z = (HealthTextView) this.al.findViewById(R.id.section_temperature_barchart_tips);
        if (nsn.s()) {
            this.o.setTextSize(1, 80.0f);
        }
        this.l = (HealthTextView) this.f2672a.findViewById(R.id.temperature_cursor_unit);
        this.d = (HealthTextView) this.f2672a.findViewById(R.id.temperature_cursor_avg);
        this.ai = (HealthViewPager) this.f2672a.findViewById(R.id.temperature_viewpager);
        n();
        this.i.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.aj.setOnClickListener(this);
        setLiftAndRightImage();
        Typeface cKN_ = nsk.cKN_();
        this.o.setTypeface(cKN_);
        this.l.setTypeface(cKN_);
    }

    private void n() {
        if (nsn.t()) {
            c(R.id.temperature_chart_view_legend_trable, R.id.temperature_chart_view_legend_trable_inflated);
        } else {
            c(R.id.temperature_chart_view_legend, R.id.temperature_chart_view_legend_inflated);
        }
        View view = this.q;
        if (view != null) {
            this.u = (HealthTextView) view.findViewById(R.id.temperature_low_legend);
            this.ad = (HealthTextView) this.q.findViewById(R.id.temperature_normal_legend);
            this.p = (HealthTextView) this.q.findViewById(R.id.temperature_high_legend);
        }
    }

    private void c(int i, int i2) {
        if (this.ak == null) {
            this.ak = (ViewStub) this.f2672a.findViewById(i);
        }
        if (this.ak.getParent() != null) {
            this.q = this.ak.inflate();
        } else {
            this.q = this.f2672a.findViewById(i2);
        }
    }

    private void b(Context context) {
        if (context == null) {
            LogUtil.h("SectionBarChart_01", "initConstructor context or dataType is null");
            return;
        }
        this.n = context;
        this.ae = context.getResources();
        this.v = UnitUtil.d();
        boolean o = Utils.o();
        this.x = o;
        if (o) {
            this.ah = "SKIN_TEMPERATURE_MIN_MAX";
        }
    }

    private void e(boolean z, boolean z2) {
        TemperatureBarChart temperatureBarChart = this.c;
        if (temperatureBarChart == null) {
            LogUtil.h("SectionBarChart_01", "mBarChart = null");
            return;
        }
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = temperatureBarChart.acquireScrollAdapter();
        if (acquireScrollAdapter != null) {
            acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
        }
        this.c.setWillNotDraw(false);
        if (z) {
            this.c.reflesh(this.af);
            this.c.setMarkerViewPosition(null);
        } else if (z2) {
            this.c.reflesh(this.af);
            TemperatureBarChart temperatureBarChart2 = this.c;
            if (temperatureBarChart2 instanceof HwHealthBaseScrollBarLineChart) {
                temperatureBarChart2.adsorbMarkerViewToSelectedDataByDataArea();
            }
        } else {
            this.c.refresh();
            TemperatureBarChart temperatureBarChart3 = this.c;
            if (temperatureBarChart3 instanceof HwHealthBaseScrollBarLineChart) {
                temperatureBarChart3.adsorbMarkerViewToSelectedDataByDataArea();
            }
        }
        LogUtil.a("SectionBarChart_01", "lineChart reflesh on show range change" + this.af);
    }

    public void setLiftAndRightImage() {
        if (LanguageUtil.bc(this.n)) {
            this.i.setBackground(ContextCompat.getDrawable(this.n, R.drawable._2131427831_res_0x7f0b01f7));
            this.h.setBackground(ContextCompat.getDrawable(this.n, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.i.setBackground(ContextCompat.getDrawable(this.n, R.drawable._2131427825_res_0x7f0b01f1));
            this.h.setBackground(ContextCompat.getDrawable(this.n, R.drawable._2131427831_res_0x7f0b01f7));
        }
    }

    private void a() {
        this.c.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.health.knit.section.view.SectionBarChart_01.2
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                SectionBarChart_01.this.i();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                SectionBarChart_01.this.i();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.c.canScrollOlderPager()) {
            this.i.setVisibility(0);
            this.i.setClickable(true);
            this.ab.set(true);
        } else {
            this.i.setVisibility(4);
            this.i.setClickable(false);
            this.ab.set(false);
        }
        if (this.c.canScrollNewerPager()) {
            this.h.setVisibility(0);
            this.h.setClickable(true);
            this.ac.set(true);
        } else {
            this.h.setVisibility(4);
            this.h.setClickable(false);
            this.ac.set(false);
        }
    }

    private void k() {
        int f;
        if (this.y <= 0) {
            LogUtil.h("SectionBarChart_01", "setLastTimeForDataPlatform lastTimestamp invalid");
            return;
        }
        TemperatureBarChart temperatureBarChart = this.c;
        if (temperatureBarChart == null || temperatureBarChart.acquireScrollAdapter() == null) {
            LogUtil.h("SectionBarChart_01", "setLastTimeForDataPlatform lastTimestamp invalid");
            return;
        }
        if (this.s.isDayData()) {
            f = nom.f(nom.a(this.y));
        } else if (this.s.isWeekData()) {
            f = nom.f(nom.m(this.y));
        } else if (this.s.isMonthData()) {
            f = nom.f(nom.f(this.y));
        } else {
            LogUtil.a("SectionBarChart_01", "setLastTimeForDataPlatform dataInfos is not day / month / year");
            return;
        }
        LogUtil.a("SectionBarChart_01", "startTimestamp=", Integer.valueOf(f));
        TemperatureBarChart temperatureBarChart2 = this.c;
        temperatureBarChart2.setShowRange(f, temperatureBarChart2.acquireScrollAdapter().acquireRange());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (this.j == null) {
            this.j = new HealthCalendar();
        }
        this.j = this.j.transformFromCalendar(calendar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        this.t.setText(this.c.formatRangeText(i, i2));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void initView(KnitFragment knitFragment, int i) {
        super.initView(knitFragment, i);
        knitFragment.setOnActivityResultListener(new KnitFragment.OnActivityResultListener() { // from class: com.huawei.health.knit.section.view.SectionBarChart_01.4
            @Override // com.huawei.health.knit.ui.KnitFragment.OnActivityResultListener
            public void onActivityResult(int i2, int i3, Intent intent) {
                if (i3 != -1 || intent == null) {
                    return;
                }
                Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
                if (serializableExtra instanceof HealthCalendar) {
                    SectionBarChart_01.this.j = (HealthCalendar) serializableExtra;
                    SectionBarChart_01 sectionBarChart_01 = SectionBarChart_01.this;
                    sectionBarChart_01.af = sectionBarChart_01.j.transformCalendar().getTimeInMillis();
                    if (SectionBarChart_01.this.c != null) {
                        SectionBarChart_01.this.c.reflesh(SectionBarChart_01.this.af);
                        LogUtil.a("SectionBarChart_01", "lineChart reflesh on activity result" + SectionBarChart_01.this.af);
                        return;
                    }
                    LogUtil.h("SectionBarChart_01", "onActivityResult mBarChart = null");
                }
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.i) {
            if (!this.ab.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            b();
        } else if (view == this.h) {
            if (!this.ac.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            h();
        } else if (view.getId() == R.id.temperature_detail_time_date_tv) {
            KnitFragment knitFragment = getKnitFragment();
            if (knitFragment != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("calendar", this.j);
                bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(new int[]{2104, HiHealthDataType.b, DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value(), DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()}, new int[]{DicDataTypeUtil.DataType.LOW_BODY_TEMPERATURE_ALARM.value(), DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM.value(), DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_ALARM.value()}));
                HealthCalendarActivity.cxl_(knitFragment, bundle);
            }
        } else if (view == this.aj) {
            f();
        } else {
            LogUtil.h("SectionBarChart_01", "click view unknow");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        View view = this.al;
        if (this.ag == null) {
            PopupWindow popupWindow = new PopupWindow(view, -2, -2);
            this.ag = popupWindow;
            popupWindow.setOutsideTouchable(true);
            view.setOnClickListener(new View.OnClickListener() { // from class: egh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SectionBarChart_01.this.aiP_(view2);
                }
            });
        }
        int[] aiK_ = aiK_(this.aj, view);
        this.ag.showAtLocation(this.aj, 0, aiK_[0], aiK_[1]);
    }

    public /* synthetic */ void aiP_(View view) {
        this.ag.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private int[] aiK_(View view, View view2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        view2.measure(0, 0);
        int measuredWidth = view2.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        return new int[]{(iArr[0] - (measuredWidth / 2)) + (view.getMeasuredWidth() / 2), iArr[1] + (measuredHeight / 2)};
    }

    private void h() {
        TemperatureBarChart temperatureBarChart = this.c;
        if (temperatureBarChart == null || temperatureBarChart.isAnimating()) {
            return;
        }
        d();
    }

    private void d() {
        TemperatureBarChart temperatureBarChart = this.c;
        Objects.requireNonNull(temperatureBarChart);
        temperatureBarChart.scrollOnePageNewer(new egj(this, temperatureBarChart));
    }

    private void b() {
        LogUtil.c("SectionBarChart_01", "Day initLeftArrowClick");
        TemperatureBarChart temperatureBarChart = this.c;
        if (temperatureBarChart == null || temperatureBarChart.isAnimating()) {
            return;
        }
        e();
    }

    private void e() {
        TemperatureBarChart temperatureBarChart = this.c;
        Objects.requireNonNull(temperatureBarChart);
        temperatureBarChart.scrollOnePageOlder(new egk(this, temperatureBarChart));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void clear() {
        LogUtil.a("SectionBarChart_01", "onDestroy");
        ObserverManagerUtil.e("REFRESH_TEMPERATURE_CHART");
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionBarChart_01";
    }
}
