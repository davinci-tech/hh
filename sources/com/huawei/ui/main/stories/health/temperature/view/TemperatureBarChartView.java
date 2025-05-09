package com.huawei.ui.main.stories.health.temperature.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseMvpChartView;
import com.huawei.ui.main.stories.health.temperature.chart.TemperatureBarChart;
import com.huawei.ui.main.stories.health.temperature.chart.TemperatureRemindListView;
import com.huawei.ui.main.stories.health.temperature.view.TemperatureBarChartView;
import com.huawei.ui.main.stories.health.temperature.view.levelcard.MultiViewDataObserverView;
import com.huawei.ui.main.stories.health.temperature.view.levelcard.TemperatureCardView;
import com.huawei.ui.main.stories.template.BaseComponent;
import com.huawei.ui.main.stories.template.ComponentParam;
import com.huawei.ui.main.stories.template.Constants;
import com.huawei.ui.main.stories.template.health.contract.DataDetailFragmentContract;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nom;
import defpackage.nsj;
import defpackage.nsk;
import defpackage.nsn;
import defpackage.qac;
import defpackage.qos;
import defpackage.qph;
import defpackage.qpm;
import defpackage.qpr;
import defpackage.qpw;
import defpackage.qpx;
import defpackage.qrp;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public class TemperatureBarChartView extends CommonBaseMvpChartView<qph> implements BaseComponent, View.OnClickListener, MultiViewDataObserverView.OnSelectListener, OnActivityResultInterface {

    /* renamed from: a, reason: collision with root package name */
    private qac f10249a;
    private final AtomicBoolean aa;
    private TemperatureRemindListView ab;
    private String ac;
    private Resources ad;
    private ViewStub ai;
    private ArrayList<View> b;
    private qos c;
    private TemperatureBarChart d;
    private ImageView e;
    private ImageView f;
    private Context g;
    private List<HiHealthData> h;
    private TemperatureCardView i;
    private HealthCalendar j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private DataInfos o;
    private HealthTextView p;
    private View q;
    private boolean r;
    private boolean s;
    private Fragment t;
    private HealthTextView u;
    private HealthTextView v;
    private final AtomicBoolean w;
    private boolean x;
    private long y;
    private HealthViewPager z;

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public int getLayoutId() {
        return R.layout.temperature_chart_view;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public HwHealthLineChart getLineChart() {
        return null;
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void initComponent(List<ComponentParam> list) {
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyNumerical(String str, List<HwHealthMarkerView.a> list) {
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyRemindData(int i, List<HiHealthData> list) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onCreate() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onPause() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onStop() {
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void setDayAndWeek(String str, String str2, boolean z) {
    }

    public TemperatureBarChartView(Context context) {
        this(context, null);
    }

    public TemperatureBarChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TemperatureBarChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.aa = new AtomicBoolean(false);
        this.w = new AtomicBoolean(false);
        this.ac = "TEMPERATURE_MIN_MAX";
        this.r = true;
        e(context);
    }

    private void e(Context context) {
        if (context == null) {
            LogUtil.h("TemperatureBarChartView", "initConstructor context or dataType is null");
            return;
        }
        this.g = context;
        this.ad = context.getResources();
        this.r = UnitUtil.d();
        boolean o = Utils.o();
        this.s = o;
        if (o) {
            this.ac = "SKIN_TEMPERATURE_MIN_MAX";
        }
        initLayout(this.g, this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mPresenter == 0) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.e) {
            if (!this.aa.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ((qph) this.mPresenter).initLeftArrowClick();
        } else if (view == this.f) {
            if (!this.w.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ((qph) this.mPresenter).initRightArrowClick();
        } else if (view.getId() == R.id.temperature_detail_time_date_tv) {
            ((qph) this.mPresenter).startCalendar(this.t, this.j);
        } else {
            LogUtil.h("TemperatureBarChartView", "click view unknow");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseMvpChartView
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public qph createPresenter() {
        return new qph();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void setLiftAndRightImage() {
        if (LanguageUtil.bc(this.g)) {
            this.e.setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131427831_res_0x7f0b01f7));
            this.f.setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.e.setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131427825_res_0x7f0b01f1));
            this.f.setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131427831_res_0x7f0b01f7));
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void initViewPager() {
        this.b = new ArrayList<>(16);
        this.f10249a = new qac(this.b);
        this.z.setIsCompatibleScrollView(true);
        this.z.setIsScroll(false);
        this.z.setAdapter(this.f10249a);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void initChart() {
        this.c = new qos(this.g.getApplicationContext(), (LineChartViewPresenter) this.mPresenter);
        if (this.d == null) {
            TemperatureBarChart temperatureBarChart = new TemperatureBarChart(this.g.getApplicationContext());
            this.d = temperatureBarChart;
            temperatureBarChart.setLayerType(1, null);
            d();
            this.b.clear();
            this.b.add(this.d);
            this.c.addDataLayer((qos) this.d, this.o);
            this.f10249a.notifyDataSetChanged();
            f();
            a();
        }
    }

    private void a() {
        this.d.setOnDataChangedListener(new TemperatureBarChart.OnDataChangedListener() { // from class: qpt
            @Override // com.huawei.ui.main.stories.health.temperature.chart.TemperatureBarChart.OnDataChangedListener
            public final void onDataChangedListener(float f, float f2) {
                TemperatureBarChartView.this.a(f, f2);
            }
        });
        this.d.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: com.huawei.ui.main.stories.health.temperature.view.TemperatureBarChartView.3
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public void onRangeShow(int i, int i2) {
                LogUtil.a("TemperatureBarChartView", "startX   endX====" + i + i2);
                if (nsj.a(TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(i2))) {
                    TemperatureBarChartView temperatureBarChartView = TemperatureBarChartView.this;
                    temperatureBarChartView.j = qrp.a(temperatureBarChartView.j, i);
                }
                TemperatureBarChartView.this.e(i, i2);
            }
        });
        this.d.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.ui.main.stories.health.temperature.view.TemperatureBarChartView.1
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                LogUtil.a("TemperatureBarChartView", "timeStr====" + str);
                TemperatureBarChartView.this.n.setText(str);
                int h = nom.h((int) TemperatureBarChartView.this.d.fetchMarkViewMinuteValue());
                TemperatureBarChartView temperatureBarChartView = TemperatureBarChartView.this;
                temperatureBarChartView.j = qrp.a(temperatureBarChartView.j, h);
            }
        });
    }

    public /* synthetic */ void a(float f, float f2) {
        String string;
        if (((int) f) != 0 && ((int) f2) != 0) {
            String e = UnitUtil.e(f, 1, 1);
            String e2 = UnitUtil.e(f2, 1, 1);
            if (this.r) {
                string = this.ad.getString(R$string.IDS_settings_health_temperature_unit, "");
            } else {
                string = this.ad.getString(R$string.IDS_temp_unit_fahrenheit, "");
            }
            this.k.setText(e + Constants.LINK + e2);
            this.l.setText(string);
            this.l.setVisibility(0);
            return;
        }
        LogUtil.h("TemperatureBarChartView", "initViews min = ", Float.valueOf(f), " max = ", Float.valueOf(f2));
        this.k.setText("--");
        this.l.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        this.m.setText(this.d.formatRangeText(i, i2));
        g();
    }

    private void d() {
        this.d.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.ui.main.stories.health.temperature.view.TemperatureBarChartView.5
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                TemperatureBarChartView.this.e();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                TemperatureBarChartView.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.d.canScrollOlderPager()) {
            this.e.setVisibility(0);
            this.e.setClickable(true);
            this.aa.set(true);
        } else {
            this.e.setVisibility(4);
            this.e.setClickable(false);
            this.aa.set(false);
        }
        if (this.d.canScrollNewerPager()) {
            this.f.setVisibility(0);
            this.f.setClickable(true);
            this.w.set(true);
        } else {
            this.f.setVisibility(4);
            this.f.setClickable(false);
            this.w.set(false);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void clickLeftArrow() {
        TemperatureBarChart temperatureBarChart = this.d;
        Objects.requireNonNull(temperatureBarChart);
        temperatureBarChart.scrollOnePageOlder(new qpw(this, temperatureBarChart));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void clickRightArrow() {
        TemperatureBarChart temperatureBarChart = this.d;
        Objects.requireNonNull(temperatureBarChart);
        temperatureBarChart.scrollOnePageNewer(new qpx(this, temperatureBarChart));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyMaxAndMin(int i, List<HiHealthData> list) {
        if (this.s) {
            this.i.setVisibility(8);
            return;
        }
        this.i.setVisibility(0);
        this.h = list;
        g();
    }

    private void g() {
        ArrayList arrayList = new ArrayList(16);
        qpr.d(d(this.h), arrayList);
        qpr.a(arrayList, this.i);
    }

    private List<HiHealthData> d(List<HiHealthData> list) {
        long h = nom.h((int) this.d.acquireShowRangeMinimum());
        long h2 = nom.h((int) this.d.acquireShowRangeMaximum());
        long millis = TimeUnit.MINUTES.toMillis(h);
        long millis2 = TimeUnit.MINUTES.toMillis(h2);
        if (!koq.c(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            if (hiHealthData.getStartTime() >= millis && hiHealthData.getEndTime() < millis2) {
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.c("TemperatureBarChartView", "results list ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public HwHealthBarChart getBarChart() {
        return this.d;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public void initView(View view) {
        setIntervalLegendLayout(view);
        this.e = (ImageView) view.findViewById(R.id.temperature_arrow_left);
        this.f = (ImageView) view.findViewById(R.id.temperature_arrow_right);
        this.m = (HealthTextView) view.findViewById(R.id.temperature_detail_time_date_tv);
        this.n = (HealthTextView) view.findViewById(R.id.temperature_cursor_time);
        this.k = (HealthTextView) view.findViewById(R.id.temperature_cursor_value);
        if (nsn.s()) {
            this.k.setTextSize(1, 80.0f);
        }
        this.l = (HealthTextView) view.findViewById(R.id.temperature_cursor_unit);
        this.z = (HealthViewPager) view.findViewById(R.id.temperature_viewpager);
        this.i = (TemperatureCardView) view.findViewById(R.id.temperature_detail_level_card_view);
        TemperatureRemindListView temperatureRemindListView = (TemperatureRemindListView) view.findViewById(R.id.temperature_warning_view);
        this.ab = temperatureRemindListView;
        temperatureRemindListView.a(false);
        this.ab.setVisibility(8);
        i();
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.i.setListener(this);
        this.i.setVisibility(8);
        setLiftAndRightImage();
        initViewPager();
        Typeface cKN_ = nsk.cKN_();
        this.k.setTypeface(cKN_);
        this.l.setTypeface(cKN_);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public void initData() {
        if (this.mPresenter != 0) {
            ((qph) this.mPresenter).initPageParams();
        }
    }

    private void h() {
        String string;
        String string2;
        String string3;
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.ac)) {
            this.u.setVisibility(8);
            this.v.setVisibility(8);
            this.p.setVisibility(8);
            return;
        }
        if (this.r) {
            String e = UnitUtil.e(35.0d, 1, 1);
            String e2 = UnitUtil.e(37.20000076293945d, 1, 1);
            string = this.ad.getString(R$string.IDS_temperature_normal_range, e, e2);
            string2 = this.ad.getString(R$string.IDS_temperature_less_than, e);
            string3 = this.ad.getString(R$string.IDS_temperature_more_than, e2);
        } else {
            String e3 = UnitUtil.e(qpr.c(35.0f), 1, 1);
            String e4 = UnitUtil.e(qpr.c(37.2f), 1, 1);
            string = this.ad.getString(R$string.IDS_temperature_fahrenheit_normal, e3, e4);
            string2 = this.ad.getString(R$string.IDS_temperature_fahrenheit_less, e3);
            string3 = this.ad.getString(R$string.IDS_temperature_fahrenheit_more, e4);
        }
        this.u.setText(string2);
        this.v.setText(string);
        this.p.setText(string3);
        this.u.setVisibility(0);
        this.v.setVisibility(0);
        this.p.setVisibility(0);
    }

    private void i() {
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.ac) || this.s) {
            this.ab.setVisibility(8);
        } else if (qpm.d()) {
            this.ab.setVisibility(0);
        } else {
            this.ab.setVisibility(8);
        }
    }

    @Override // com.huawei.ui.main.stories.health.temperature.view.levelcard.MultiViewDataObserverView.OnSelectListener
    public void onSelect(String str, boolean z) {
        TemperatureBarChart temperatureBarChart;
        LogUtil.c("TemperatureBarChartView", "onSelect is ", str);
        this.ac = str;
        h();
        i();
        if (this.c == null || (temperatureBarChart = this.d) == null || temperatureBarChart.acquireScrollAdapter() == null) {
            return;
        }
        if (this.x && z) {
            this.d.clearValues();
            this.c.addDataLayer((qos) this.d, this.o);
        }
        this.x = true;
        this.d.setWillNotDraw(false);
        this.d.setShowDataType(str);
        this.c.a(str);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.d.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(1 | acquireScrollAdapter.getFlag());
        this.d.setMarkerViewPosition(null);
        this.d.refresh();
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public View getView(Context context) {
        return this.mView;
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setPresenter(DataDetailFragmentContract.DetailFragmentPresenter detailFragmentPresenter) {
        initChart();
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void refreshView(boolean z) {
        if (this.i != null) {
            onSelect(this.ac, false);
            this.i.a(this.ac);
        }
    }

    private void b() {
        this.c = null;
        this.d = null;
        this.y = System.currentTimeMillis();
        initChart();
    }

    private void setIntervalLegendLayout(View view) {
        if (nsn.t()) {
            dHh_(view, R.id.temperature_chart_view_legend_trable, R.id.temperature_chart_view_legend_trable_inflated);
        } else {
            dHh_(view, R.id.temperature_chart_view_legend, R.id.temperature_chart_view_legend_inflated);
        }
        View view2 = this.q;
        if (view2 != null) {
            this.u = (HealthTextView) view2.findViewById(R.id.temperature_low_legend);
            this.v = (HealthTextView) this.q.findViewById(R.id.temperature_normal_legend);
            this.p = (HealthTextView) this.q.findViewById(R.id.temperature_high_legend);
        }
    }

    private void dHh_(View view, int i, int i2) {
        if (this.ai == null) {
            this.ai = (ViewStub) view.findViewById(i);
        }
        if (this.ai.getParent() != null) {
            this.q = this.ai.inflate();
        } else {
            this.q = view.findViewById(i2);
        }
    }

    private void f() {
        int f;
        if (this.y <= 0) {
            LogUtil.h("TemperatureBarChartView", "setLastTimeForDataPlatform lastTimestamp invalid");
            return;
        }
        TemperatureBarChart temperatureBarChart = this.d;
        if (temperatureBarChart == null || temperatureBarChart.acquireScrollAdapter() == null) {
            LogUtil.h("TemperatureBarChartView", "setLastTimeForDataPlatform lastTimestamp invalid");
            return;
        }
        if (this.o.isDayData()) {
            f = nom.f(nom.a(this.y));
        } else if (this.o.isWeekData()) {
            f = nom.f(nom.m(this.y));
        } else if (this.o.isMonthData()) {
            f = nom.f(nom.f(this.y));
        } else {
            LogUtil.a("TemperatureBarChartView", "setLastTimeForDataPlatform dataInfos is not day / month / year");
            return;
        }
        LogUtil.a("TemperatureBarChartView", "startTimestamp=", Integer.valueOf(f));
        TemperatureBarChart temperatureBarChart2 = this.d;
        temperatureBarChart2.setShowRange(f, temperatureBarChart2.acquireScrollAdapter().acquireRange());
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setDateStamp(long j) {
        if (j == 1) {
            b();
            return;
        }
        if (this.y != j) {
            this.y = j;
            f();
        }
        if (this.y > 0) {
            onSelect(this.ac, ArkUIXConstants.DELETE.equals(qpr.d()));
        }
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setPageType(Constants.PageType pageType) {
        if (pageType == Constants.PageType.WEEK) {
            this.o = DataInfos.TemperatureWeekDetail;
        } else {
            this.o = DataInfos.TemperatureMonthDetail;
        }
    }

    private void setBiAnalyticsClickEvent(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this.g.getApplicationContext(), AnalyticsValue.TEMPERATURE_SWITCH_RANGE_2060075.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onResume() {
        i();
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onDestory() {
        qpr.a((String) null);
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onDayWeekYear(int i) {
        setBiAnalyticsClickEvent(i);
    }

    @Override // com.huawei.ui.main.stories.health.temperature.view.OnActivityResultInterface
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (serializableExtra instanceof HealthCalendar) {
            this.j = (HealthCalendar) serializableExtra;
            this.d.reflesh(((qph) this.mPresenter).prossCalendarSelect(this.j));
        }
    }

    @Override // com.huawei.ui.main.stories.health.temperature.view.OnActivityResultInterface
    public void setFragment(Fragment fragment) {
        this.t = fragment;
    }
}
