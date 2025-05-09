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
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.linechart.view.commonlinechart.HwHealthCommonLineChart;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseMvpChartView;
import com.huawei.ui.main.stories.health.temperature.chart.TemperatureRemindListView;
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
import defpackage.qoq;
import defpackage.qpf;
import defpackage.qpr;
import defpackage.qpv;
import defpackage.qpy;
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
public class TemperatureLineChartView extends CommonBaseMvpChartView<qpf> implements BaseComponent, MultiViewDataObserverView.OnSelectListener, View.OnClickListener, OnActivityResultInterface {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10251a;
    private final AtomicBoolean aa;
    private String ab;
    private HealthTextView ac;
    private TemperatureRemindListView ad;
    private HealthViewPager ae;
    private ViewStub af;
    private ImageView b;
    private HealthTextView c;
    private TemperatureCardView d;
    private HealthCalendar e;
    private HealthTextView f;
    private Context g;
    private HealthTextView h;
    private DataInfos i;
    private HealthTextView j;
    private HealthTextView k;
    private boolean l;
    private HealthTextView m;
    private View n;
    private Fragment o;
    private boolean p;
    private long q;
    private boolean r;
    private HwHealthCommonLineChart s;
    private boolean t;
    private final AtomicBoolean u;
    private qoq v;
    private HealthTextView w;
    private ArrayList<View> x;
    private qac y;
    private Resources z;

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public HwHealthBarChart getBarChart() {
        return null;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public int getLayoutId() {
        return R.layout.temperature_chart_view;
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void initComponent(List<ComponentParam> list) {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onCreate() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onPause() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onResume() {
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onStop() {
    }

    public TemperatureLineChartView(Context context) {
        this(context, null);
    }

    public TemperatureLineChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TemperatureLineChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.aa = new AtomicBoolean(false);
        this.u = new AtomicBoolean(false);
        this.ab = "TEMPERATURE_MIN_MAX";
        this.t = true;
        this.l = false;
        c(context);
    }

    private void c(Context context) {
        if (context == null) {
            LogUtil.h("TemperatureLineChartView", "initConstructor context or dataType is null");
            return;
        }
        this.g = context;
        this.z = context.getResources();
        this.t = UnitUtil.d();
        boolean o = Utils.o();
        this.r = o;
        if (o) {
            this.ab = "SKIN_TEMPERATURE_MIN_MAX";
        }
        initLayout(this.g, this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mPresenter == 0) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.b) {
            if (!this.aa.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ((qpf) this.mPresenter).initLeftArrowClick();
        } else if (view == this.f10251a) {
            if (!this.u.get()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ((qpf) this.mPresenter).initRightArrowClick();
        } else if (view.getId() == R.id.temperature_detail_time_date_tv) {
            ((qpf) this.mPresenter).startCalendar(this.o, this.e);
        } else {
            LogUtil.h("TemperatureLineChartView", "click view unknow");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseMvpChartView
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public qpf createPresenter() {
        return new qpf();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void setLiftAndRightImage() {
        if (LanguageUtil.bc(this.g)) {
            this.b.setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131427831_res_0x7f0b01f7));
            this.f10251a.setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.b.setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131427825_res_0x7f0b01f1));
            this.f10251a.setBackground(ContextCompat.getDrawable(this.g, R.drawable._2131427831_res_0x7f0b01f7));
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void initViewPager() {
        this.x = new ArrayList<>(16);
        this.y = new qac(this.x);
        this.ae.setIsCompatibleScrollView(true);
        this.ae.setIsScroll(false);
        this.ae.setAdapter(this.y);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void initChart() {
        this.v = new qoq(this.g.getApplicationContext(), (LineChartViewPresenter) this.mPresenter);
        if (this.s == null) {
            HwHealthCommonLineChart hwHealthCommonLineChart = new HwHealthCommonLineChart(this.g.getApplicationContext());
            this.s = hwHealthCommonLineChart;
            hwHealthCommonLineChart.setLayerType(1, null);
            e();
            this.x.add(0, this.s);
            this.v.addDataLayer((qoq) this.s, DataInfos.TemperatureDayDetail);
            this.y.notifyDataSetChanged();
            this.s.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: com.huawei.ui.main.stories.health.temperature.view.TemperatureLineChartView.2
                @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
                public void onRangeShow(int i, int i2) {
                    if (nsj.a(TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(i2))) {
                        TemperatureLineChartView temperatureLineChartView = TemperatureLineChartView.this;
                        temperatureLineChartView.e = qrp.a(temperatureLineChartView.e, i);
                    }
                    ((qpf) TemperatureLineChartView.this.mPresenter).notifyData(i, i2);
                }
            });
            this.s.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.ui.main.stories.health.temperature.view.TemperatureLineChartView.5
                @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
                public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                    String c = qpr.c(qpr.b(str));
                    int h = nom.h((int) TemperatureLineChartView.this.s.fetchMarkViewMinuteValue());
                    TemperatureLineChartView temperatureLineChartView = TemperatureLineChartView.this;
                    temperatureLineChartView.e = qrp.a(temperatureLineChartView.e, h);
                    ((qpf) TemperatureLineChartView.this.mPresenter).notifyCursorDataAndTime(c, list);
                }
            });
        }
    }

    private void e() {
        this.s.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.ui.main.stories.health.temperature.view.TemperatureLineChartView.4
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                TemperatureLineChartView.this.a();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                TemperatureLineChartView.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.s.canScrollOlderPager()) {
            this.b.setVisibility(0);
            this.b.setClickable(true);
            this.aa.set(true);
        } else {
            this.b.setVisibility(4);
            this.b.setClickable(false);
            this.aa.set(false);
        }
        if (this.s.canScrollNewerPager()) {
            this.f10251a.setVisibility(0);
            this.f10251a.setClickable(true);
            this.u.set(true);
        } else {
            this.f10251a.setVisibility(4);
            this.f10251a.setClickable(false);
            this.u.set(false);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void clickLeftArrow() {
        HwHealthCommonLineChart hwHealthCommonLineChart = this.s;
        Objects.requireNonNull(hwHealthCommonLineChart);
        hwHealthCommonLineChart.scrollOnePageOlder(new qpv(this, hwHealthCommonLineChart));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void clickRightArrow() {
        HwHealthCommonLineChart hwHealthCommonLineChart = this.s;
        Objects.requireNonNull(hwHealthCommonLineChart);
        hwHealthCommonLineChart.scrollOnePageNewer(new qpy(this, hwHealthCommonLineChart));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void setDayAndWeek(String str, String str2, boolean z) {
        this.k.setText(str);
        if (z) {
            this.f10251a.setVisibility(8);
        } else {
            this.f10251a.setVisibility(0);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyNumerical(String str, List<HwHealthMarkerView.a> list) {
        HealthTextView healthTextView = this.h;
        if (healthTextView != null) {
            healthTextView.setText(str);
        } else {
            LogUtil.h("TemperatureLineChartView", "notifyNumerical, mCursorTime is null");
        }
        c(list);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyMaxAndMin(int i, List<HiHealthData> list) {
        if (this.r) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
            qpr.a(list, this.d);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public void notifyRemindData(int i, List<HiHealthData> list) {
        boolean c = koq.c(list);
        this.l = c;
        if (!c || this.r || "SKIN_TEMPERATURE_MIN_MAX".equals(this.ab)) {
            this.ad.setVisibility(8);
        } else {
            this.ad.e(list, this.q);
            this.ad.setVisibility(0);
        }
    }

    private void c(List<HwHealthMarkerView.a> list) {
        String parse;
        String string;
        if (koq.b(list)) {
            LogUtil.h("TemperatureLineChartView", "notifyView datas is empty");
            parse = "--";
        } else {
            parse = this.v.parse(list.get(list.size() - 1).b);
        }
        if ("--".equals(parse)) {
            this.f.setText(parse);
            this.c.setVisibility(8);
            this.j.setVisibility(8);
            return;
        }
        if (this.t) {
            string = this.z.getString(R$string.IDS_settings_health_temperature_unit, "");
        } else {
            string = this.z.getString(R$string.IDS_temp_unit_fahrenheit, "");
        }
        DataInfos dataInfos = this.i;
        if (dataInfos != null && dataInfos.isDayData()) {
            this.f.setTextSize(0, getResources().getDimension(R.dimen._2131365073_res_0x7f0a0cd1));
        }
        this.f.setText(parse);
        this.j.setText(string);
        this.c.setVisibility(0);
        this.j.setVisibility(0);
        Typeface cKN_ = nsk.cKN_();
        this.f.setTypeface(cKN_);
        this.j.setTypeface(cKN_);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface
    public HwHealthLineChart getLineChart() {
        return this.s;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public void initView(View view) {
        setIntervalLegendLayout(view);
        this.b = (ImageView) view.findViewById(R.id.temperature_arrow_left);
        this.f10251a = (ImageView) view.findViewById(R.id.temperature_arrow_right);
        this.k = (HealthTextView) view.findViewById(R.id.temperature_detail_time_date_tv);
        this.h = (HealthTextView) view.findViewById(R.id.temperature_cursor_time);
        this.f = (HealthTextView) view.findViewById(R.id.temperature_cursor_value);
        this.j = (HealthTextView) view.findViewById(R.id.temperature_cursor_unit);
        this.c = (HealthTextView) view.findViewById(R.id.temperature_cursor_avg);
        this.ae = (HealthViewPager) view.findViewById(R.id.temperature_viewpager);
        this.d = (TemperatureCardView) view.findViewById(R.id.temperature_detail_level_card_view);
        TemperatureRemindListView temperatureRemindListView = (TemperatureRemindListView) view.findViewById(R.id.temperature_warning_view);
        this.ad = temperatureRemindListView;
        temperatureRemindListView.setVisibility(8);
        this.ad.a(true);
        this.b.setOnClickListener(this);
        this.f10251a.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.d.setListener(this);
        this.d.setVisibility(8);
        setLiftAndRightImage();
        initViewPager();
    }

    private void j() {
        String string;
        String string2;
        String string3;
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.ab)) {
            this.w.setVisibility(8);
            this.ac.setVisibility(8);
            this.m.setVisibility(8);
            return;
        }
        if (this.t) {
            String e = UnitUtil.e(35.0d, 1, 1);
            String e2 = UnitUtil.e(37.20000076293945d, 1, 1);
            string = this.z.getString(R$string.IDS_temperature_normal_range, e, e2);
            string2 = this.z.getString(R$string.IDS_temperature_less_than, e);
            string3 = this.z.getString(R$string.IDS_temperature_more_than, e2);
        } else {
            String e3 = UnitUtil.e(qpr.c(35.0f), 1, 1);
            String e4 = UnitUtil.e(qpr.c(37.2f), 1, 1);
            string = this.z.getString(R$string.IDS_temperature_fahrenheit_normal, e3, e4);
            string2 = this.z.getString(R$string.IDS_temperature_fahrenheit_less, e3);
            string3 = this.z.getString(R$string.IDS_temperature_fahrenheit_more, e4);
        }
        this.w.setText(string2);
        this.ac.setText(string);
        this.m.setText(string3);
        this.w.setVisibility(0);
        this.ac.setVisibility(0);
        this.m.setVisibility(0);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.base.CommonBaseChartView
    public void initData() {
        if (this.mPresenter != 0) {
            ((qpf) this.mPresenter).initPageParams();
        }
    }

    private void setIntervalLegendLayout(View view) {
        if (nsn.t()) {
            dHi_(view, R.id.temperature_chart_view_legend_trable, R.id.temperature_chart_view_legend_trable_inflated);
        } else {
            dHi_(view, R.id.temperature_chart_view_legend, R.id.temperature_chart_view_legend_inflated);
        }
        View view2 = this.n;
        if (view2 != null) {
            this.w = (HealthTextView) view2.findViewById(R.id.temperature_low_legend);
            this.ac = (HealthTextView) this.n.findViewById(R.id.temperature_normal_legend);
            this.m = (HealthTextView) this.n.findViewById(R.id.temperature_high_legend);
        }
    }

    private void dHi_(View view, int i, int i2) {
        if (this.af == null) {
            this.af = (ViewStub) view.findViewById(i);
        }
        if (this.af.getParent() != null) {
            this.n = this.af.inflate();
        } else {
            this.n = view.findViewById(i2);
        }
    }

    private void f() {
        if ("SKIN_TEMPERATURE_MIN_MAX".equals(this.ab)) {
            this.ad.setVisibility(8);
        } else if (this.l) {
            this.ad.setVisibility(0);
        }
    }

    @Override // com.huawei.ui.main.stories.health.temperature.view.levelcard.MultiViewDataObserverView.OnSelectListener
    public void onSelect(String str, boolean z) {
        HwHealthCommonLineChart hwHealthCommonLineChart;
        LogUtil.c("TemperatureLineChartView", "onSelect is ", str);
        if (str.equals("TEMPERATURE_MIN_MAX")) {
            e(AnalyticsValue.TEMPERATURE_SWITCH_TYPE_2060076.value(), 0);
        } else if (str.equals("SKIN_TEMPERATURE_MIN_MAX")) {
            e(AnalyticsValue.TEMPERATURE_SWITCH_TYPE_2060076.value(), 1);
        } else {
            LogUtil.c("TemperatureLineChartView", "onSelect unKnow");
        }
        this.ab = str;
        j();
        f();
        if (this.v == null || (hwHealthCommonLineChart = this.s) == null || hwHealthCommonLineChart.acquireScrollAdapter() == null) {
            return;
        }
        this.v.e(str);
        if (this.p && z) {
            this.s.clearValues();
            this.v.addDataLayer((qoq) this.s, DataInfos.TemperatureDayDetail);
        }
        this.p = true;
        this.s.setWillNotDraw(false);
        this.v.c(str);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.s.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
        this.s.setMarkerViewPosition(null);
        this.s.resetYaxisAnimateValue();
        this.s.refresh();
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
        if (this.d != null) {
            onSelect(this.ab, false);
            this.d.a(this.ab);
        }
    }

    private void d() {
        this.v = null;
        this.s = null;
        this.q = System.currentTimeMillis();
        initChart();
        c((List<HwHealthMarkerView.a>) null);
    }

    private void b() {
        if (this.q <= 0) {
            LogUtil.h("TemperatureLineChartView", "setLastTimeForDataPlatform lastTimestamp invalid");
            return;
        }
        HwHealthCommonLineChart hwHealthCommonLineChart = this.s;
        if (hwHealthCommonLineChart == null || hwHealthCommonLineChart.acquireScrollAdapter() == null) {
            LogUtil.h("TemperatureLineChartView", "setLastTimeForDataPlatform lastTimestamp invalid");
            return;
        }
        if (this.i.isDayData()) {
            int f = nom.f(nom.a(this.q));
            LogUtil.a("TemperatureLineChartView", "startTimestamp=", Integer.valueOf(f));
            HwHealthCommonLineChart hwHealthCommonLineChart2 = this.s;
            hwHealthCommonLineChart2.setShowRange(f, hwHealthCommonLineChart2.acquireScrollAdapter().acquireRange());
            return;
        }
        LogUtil.a("TemperatureLineChartView", "setLastTimeForDataPlatform dataInfos is not day / month / year");
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setDateStamp(long j) {
        if (j == 1) {
            d();
            return;
        }
        if (this.q != j) {
            this.q = j;
            b();
        }
        if (this.q > 0) {
            onSelect(this.ab, ArkUIXConstants.DELETE.equals(qpr.d()));
        }
        LogUtil.c("TemperatureLineChartView", "setDateStamp is ", Long.valueOf(j), ", mShowDataType ", this.ab);
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void setPageType(Constants.PageType pageType) {
        if (pageType == Constants.PageType.DAY) {
            this.i = DataInfos.TemperatureDayDetail;
        }
    }

    private void e(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this.g.getApplicationContext(), str, hashMap, 0);
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onDestory() {
        qpr.a((String) null);
    }

    @Override // com.huawei.ui.main.stories.template.BaseComponent
    public void onDayWeekYear(int i) {
        e(AnalyticsValue.TEMPERATURE_SWITCH_RANGE_2060075.value(), i);
    }

    @Override // com.huawei.ui.main.stories.health.temperature.view.OnActivityResultInterface
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (serializableExtra instanceof HealthCalendar) {
            this.e = (HealthCalendar) serializableExtra;
            this.s.reflesh(((qpf) this.mPresenter).prossCalendarSelect(this.e));
        }
    }

    @Override // com.huawei.ui.main.stories.health.temperature.view.OnActivityResultInterface
    public void setFragment(Fragment fragment) {
        this.o = fragment;
    }
}
