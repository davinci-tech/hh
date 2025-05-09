package com.huawei.ui.main.stories.health.lactatethreshold.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.LacticLineChart;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface;
import com.huawei.ui.main.stories.health.lactatethreshold.chart.LacticChartHoriHolder;
import com.huawei.ui.main.stories.health.lactatethreshold.view.LacticChartHolderView;
import defpackage.edw;
import defpackage.koq;
import defpackage.nom;
import defpackage.nqo;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.ntq;
import defpackage.qkt;
import defpackage.qkx;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class LacticChartHolderView extends LinearLayout implements RqViewInterface, View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10207a;
    private Context b;
    private HealthTextView c;
    private DataInfos d;
    private HealthCalendar e;
    private ImageView f;
    private LacticChartHoriHolder g;
    private LacticLineChart h;
    private ntq i;
    private LacticChartHoriHolder j;
    private LinearLayout k;
    private LinearLayout l;
    private ImageView m;
    private StartCalendarViewListener n;
    private HealthViewPager o;
    private ArrayList<View> p;
    private long r;
    private HealthTextView s;
    private HealthTextView t;

    public interface StartCalendarViewListener {
        void onStartCalendarView();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setDateRange(String str, String str2, int i) {
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setExactDateAndData(String str, int i, int i2) {
    }

    public LacticChartHolderView(Context context) {
        this(context, null);
    }

    public LacticChartHolderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LacticChartHolderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = DataInfos.NoDataPlaceHolder;
        this.n = null;
        this.b = context;
        a();
    }

    private void a() {
        if (nsn.c() > 1.0f) {
            LayoutInflater.from(this.b).inflate(R.layout.lactic_line_chart_view_largefont, this);
        } else {
            LayoutInflater.from(this.b).inflate(R.layout.lactic_line_chart_view, this);
        }
        this.o = (HealthViewPager) findViewById(R.id.viewpager);
        this.f = (ImageView) findViewById(R.id.chart_arrow_left);
        this.m = (ImageView) findViewById(R.id.chart_arrow_right);
        this.f.setOnClickListener(this);
        this.m.setOnClickListener(this);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.rq_date_range);
        this.f10207a = healthTextView;
        healthTextView.setOnClickListener(this);
        this.c = (HealthTextView) findViewById(R.id.rq_cursor_time);
        this.l = (LinearLayout) findViewById(R.id.third_layer_1);
        this.k = (LinearLayout) findViewById(R.id.third_layer_2);
        this.t = (HealthTextView) this.l.findViewById(R.id.third_layer_value);
        this.s = (HealthTextView) this.k.findViewById(R.id.third_layer_value);
        setLeftAndRightImage();
        initViewPager();
        if (nsn.r()) {
            float dimension = getResources().getDimension(R.dimen._2131363042_res_0x7f0a04e2);
            this.t.setTextSize(0, dimension);
            this.s.setTextSize(0, dimension);
            this.f10207a.setTextSize(0, getResources().getDimension(R.dimen._2131363015_res_0x7f0a04c7));
            this.c.setTextSize(0, getResources().getDimension(R.dimen._2131362996_res_0x7f0a04b4));
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setLeftAndRightImage() {
        if (LanguageUtil.bc(this.b)) {
            this.f.setImageResource(R.mipmap._2131820906_res_0x7f11016a);
            this.m.setImageResource(R.mipmap._2131820905_res_0x7f110169);
        } else {
            this.f.setImageResource(R.mipmap._2131820905_res_0x7f110169);
            this.m.setImageResource(R.mipmap._2131820906_res_0x7f11016a);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void initViewPager() {
        this.p = new ArrayList<>();
        this.i = new ntq(this.p);
        this.o.setIsCompatibleScrollView(true);
        this.o.setIsScroll(false);
        this.o.setAdapter(this.i);
        if (nsn.r()) {
            setPagerHeight(getResources().getDimensionPixelSize(R.dimen._2131362971_res_0x7f0a049b));
        }
    }

    private void setPagerHeight(int i) {
        ViewGroup.LayoutParams layoutParams = this.o.getLayoutParams();
        layoutParams.height = i;
        this.o.setLayoutParams(layoutParams);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (nsn.c() <= 1.0f || nsn.r()) {
            return;
        }
        int[] iArr = new int[2];
        this.o.getLocationOnScreen(iArr);
        int j = nsn.j() - iArr[1];
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362971_res_0x7f0a049b);
        if (j <= dimensionPixelSize) {
            j = dimensionPixelSize;
        }
        setPagerHeight(j);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void initChart() {
        LacticLineChart lacticLineChart = new LacticLineChart(this.b);
        this.h = lacticLineChart;
        this.p.add(lacticLineChart);
        this.i.notifyDataSetChanged();
        this.h.setLayerType(1, null);
        this.h.acquireLayout().j(getResources().getDimensionPixelSize(R.dimen._2131363029_res_0x7f0a04d5));
        this.h.setMarkViewCenteredWhenEmpty(false);
        d();
        dFb_(this.l, false);
        dFb_(this.k, true);
        LacticChartHoriHolder lacticChartHoriHolder = new LacticChartHoriHolder(this.b.getApplicationContext(), "lthrPace");
        this.g = lacticChartHoriHolder;
        lacticChartHoriHolder.addDataLayer((LacticChartHoriHolder) this.h, this.d);
        LacticChartHoriHolder lacticChartHoriHolder2 = new LacticChartHoriHolder(this.b.getApplicationContext(), "lthrHr");
        this.j = lacticChartHoriHolder2;
        lacticChartHoriHolder2.a(true);
        this.j.addDataLayer((LacticChartHoriHolder) this.h, this.d);
        this.h.reflesh(this.r);
    }

    private void dFb_(ViewGroup viewGroup, boolean z) {
        Drawable dFa_;
        HealthTextView healthTextView = (HealthTextView) viewGroup.findViewById(R.id.third_layer_label);
        HealthTextView healthTextView2 = (HealthTextView) viewGroup.findViewById(R.id.third_layer_unit);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable._2131429925_res_0x7f0b0a25);
        if (z) {
            healthTextView.setText(getResources().getString(R$string.IDS_hw_show_main_home_page_sport_pace));
            healthTextView2.setText(getContext().getString(R$string.IDS_pace_unit));
            dFa_ = dFa_(drawable, R.color._2131298895_res_0x7f090a4f);
        } else {
            healthTextView.setText(getResources().getString(R$string.IDS_hw_health_show_healthdata_heart_bmp));
            healthTextView2.setText(getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string));
            dFa_ = dFa_(drawable, R.color._2131297883_res_0x7f09065b);
        }
        healthTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(dFa_, (Drawable) null, (Drawable) null, (Drawable) null);
        if (nsn.r()) {
            float dimension = getResources().getDimension(R.dimen._2131362973_res_0x7f0a049d);
            healthTextView.setTextSize(0, dimension);
            healthTextView2.setTextSize(0, dimension);
        }
    }

    private Drawable dFa_(Drawable drawable, int i) {
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, ContextCompat.getColor(getContext(), i));
        return wrap;
    }

    private void d() {
        this.h.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: qkw
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public final void onRangeShow(int i, int i2) {
                LacticChartHolderView.this.e(i, i2);
            }
        });
        this.h.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.ui.main.stories.health.lactatethreshold.view.LacticChartHolderView.2
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                String str2;
                String str3;
                LacticChartHolderView.this.e(nom.h((int) LacticChartHolderView.this.h.fetchMarkViewMinuteValue()));
                LacticChartHolderView.this.c.setText(str);
                if (koq.b(list)) {
                    return;
                }
                if (list.size() > 1) {
                    str2 = LacticChartHolderView.this.e(list.get(1).b, false);
                    str3 = LacticChartHolderView.this.e(list.get(0).b, true);
                } else {
                    str2 = "--";
                    str3 = "--";
                }
                LacticChartHolderView.this.t.setText(str2);
                LacticChartHolderView.this.s.setText(str3);
            }
        });
        this.h.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.ui.main.stories.health.lactatethreshold.view.LacticChartHolderView.4
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                LacticChartHolderView.this.c();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                LacticChartHolderView.this.c();
            }
        });
    }

    public /* synthetic */ void e(int i, int i2) {
        if (nsj.a(TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(i2))) {
            e(i);
        }
        this.f10207a.setText(this.h.formatRangeText(i, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(HwHealthBaseEntry hwHealthBaseEntry, boolean z) {
        if (hwHealthBaseEntry == null || !(hwHealthBaseEntry.getData() instanceof edw)) {
            return "--";
        }
        int y = (int) hwHealthBaseEntry.getY();
        if (z) {
            return nqo.d(y);
        }
        return UnitUtil.e(y, 1, 0);
    }

    public void e(DataInfos dataInfos, long j) {
        this.d = dataInfos;
        this.r = j;
        initChart();
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setLeftClickEvent() {
        LacticLineChart lacticLineChart = this.h;
        Objects.requireNonNull(lacticLineChart);
        lacticLineChart.scrollOnePageOlder(new qkt(this, lacticLineChart));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqViewInterface
    public void setRightClickEvent() {
        LacticLineChart lacticLineChart = this.h;
        Objects.requireNonNull(lacticLineChart);
        lacticLineChart.scrollOnePageNewer(new qkx(this, lacticLineChart));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f.setVisibility(this.h.canScrollOlderPager() ? 0 : 8);
        this.m.setVisibility(this.h.canScrollNewerPager() ? 0 : 8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.m) {
            setRightClickEvent();
        } else if (view == this.f) {
            setLeftClickEvent();
        } else if (view == this.f10207a) {
            e();
        } else {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void e() {
        StartCalendarViewListener startCalendarViewListener = this.n;
        if (startCalendarViewListener != null) {
            startCalendarViewListener.onStartCalendarView();
        }
    }

    public void e(long j, HealthCalendar healthCalendar) {
        this.e = healthCalendar;
        this.h.reflesh(j);
    }

    public void e(int i) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (this.e == null) {
            this.e = new HealthCalendar();
        }
        this.e = this.e.transformFromCalendar(calendar);
    }

    public void setStartCalendarViewListener(StartCalendarViewListener startCalendarViewListener) {
        this.n = startCalendarViewListener;
    }
}
