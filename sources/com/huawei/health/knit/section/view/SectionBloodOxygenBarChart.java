package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.BloodOxygenLineChart;
import com.huawei.health.knit.section.chart.InteractorLineChartHolder;
import com.huawei.health.knit.section.listener.FullScreenCallback;
import com.huawei.health.knit.section.listener.OnXRangeSetCallback;
import com.huawei.health.knit.section.view.SectionBloodOxygenBarChart;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import defpackage.eci;
import defpackage.ecs;
import defpackage.efb;
import defpackage.egn;
import defpackage.egq;
import defpackage.jcf;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.ntq;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes3.dex */
public class SectionBloodOxygenBarChart extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private ViewTreeObserver.OnGlobalLayoutListener f2675a;
    private HealthTextView aa;
    private HwProgressBar ab;
    private HealthTextView ac;
    private LinearLayout ad;
    private int ae;
    private LinearLayout af;
    private HealthTextView ag;
    private HealthTextView ah;
    private LinearLayout ai;
    private ArrayList<View> aj;
    private boolean am;
    private List<Integer> an;
    private boolean b;
    private boolean c;
    private boolean d;
    private LinearLayout e;
    private LinearLayout f;
    private boolean g;
    private HealthTextView h;
    private List<Integer> i;
    private ImageView j;
    private List<String> k;
    private BloodOxygenLineChart l;
    private HealthTextView m;
    private InteractorLineChartHolder n;
    private ntq<View> o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private Context t;
    private ImageView u;
    private ImageView v;
    private HealthViewPager w;
    private ImageView x;
    private View y;
    private ImageView z;

    public SectionBloodOxygenBarChart(Context context) {
        this(context, null);
        this.t = context;
    }

    public SectionBloodOxygenBarChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionBloodOxygenBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.an = new ArrayList();
        this.ae = Integer.MIN_VALUE;
        this.g = false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.t = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_bar_chart, (ViewGroup) this, false);
        setIntervalLegendLayout(inflate);
        this.ae = Integer.MIN_VALUE;
        this.v = (ImageView) inflate.findViewById(R.id.image_up_arrow_left);
        this.ad = (LinearLayout) inflate.findViewById(R.id.loading_layout);
        this.ab = (HwProgressBar) inflate.findViewById(R.id.loading_iv);
        this.u = (ImageView) inflate.findViewById(R.id.image_up_arrow_right);
        this.q = (HealthTextView) inflate.findViewById(R.id.detail_time_date);
        this.p = (HealthTextView) inflate.findViewById(R.id.cursor_time);
        this.s = (HealthTextView) inflate.findViewById(R.id.cursor_value);
        this.r = (HealthTextView) inflate.findViewById(R.id.cursor_step);
        this.x = (ImageView) inflate.findViewById(R.id.horizontal_jump_btn);
        this.w = (HealthViewPager) inflate.findViewById(R.id.detail_view_pager);
        this.m = (HealthTextView) inflate.findViewById(R.id.cursor_blood_oxygen_text);
        this.j = (ImageView) inflate.findViewById(R.id.altitude_switch_btn);
        this.e = (LinearLayout) inflate.findViewById(R.id.first_ll);
        g();
        b();
        a();
        e();
        h();
        j();
        i();
        if ((LanguageUtil.bj(this.t) || LanguageUtil.v(this.t) || LanguageUtil.f(this.t) || LanguageUtil.r(this.t) || LanguageUtil.bk(this.t) || LanguageUtil.b(this.t)) && (this.m.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m.getLayoutParams();
            layoutParams.setMarginStart(this.t.getResources().getDimensionPixelSize(R.dimen._2131363829_res_0x7f0a07f5));
            this.m.setLayoutParams(layoutParams);
        }
        if (nsn.r()) {
            this.m.setTextSize(1, 24.0f);
            this.s.setTextSize(1, 24.0f);
            this.r.setTextSize(1, 24.0f);
        }
        return inflate;
    }

    private void i() {
        jcf.bEz_(this.v, nsf.h(R$string.accessibility_last_day));
        jcf.bEz_(this.u, nsf.h(R$string.accessibility_next_day));
    }

    private void g() {
        if (this.f2675a == null) {
            this.f2675a = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBarChart.4
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    int left;
                    if (SectionBloodOxygenBarChart.this.z == null) {
                        return;
                    }
                    if (LanguageUtil.bc(SectionBloodOxygenBarChart.this.t)) {
                        left = (((LinearLayout) SectionBloodOxygenBarChart.this.z.getParent()).getWidth() - SectionBloodOxygenBarChart.this.z.getRight()) - (SectionBloodOxygenBarChart.this.z.getWidth() / 3);
                    } else {
                        left = SectionBloodOxygenBarChart.this.z.getLeft();
                    }
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) SectionBloodOxygenBarChart.this.ai.getLayoutParams();
                    if (left != layoutParams.getMarginStart()) {
                        layoutParams.setMarginStart(left);
                        SectionBloodOxygenBarChart.this.ai.setLayoutParams(layoutParams);
                    }
                }
            };
            this.y.getViewTreeObserver().addOnGlobalLayoutListener(this.f2675a);
            ObserverManagerUtil.d(new Observer() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBarChart.1
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    if (koq.e(objArr, 0)) {
                        Object obj = objArr[0];
                        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                            SectionBloodOxygenBarChart.this.y.getViewTreeObserver().removeOnGlobalLayoutListener(SectionBloodOxygenBarChart.this.f2675a);
                        }
                    }
                }
            }, "REMOVE_GLOBAL_LAYOUT");
            ObserverManagerUtil.d(new Observer() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBarChart.5
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    if (koq.e(objArr, 0) && (objArr[0] instanceof Boolean)) {
                        SectionBloodOxygenBarChart.this.u.setVisibility(((Boolean) objArr[0]).booleanValue() ? 8 : 0);
                    }
                }
            }, "BLOOD_OXYGEN_ARROW_VISIBLE");
        }
    }

    private void c() {
        if (this.am) {
            if (!(this.l.getRenderer() instanceof eci)) {
                this.l.setNewRenderer(DataInfos.BloodOxygenAltitudeDayDetail, this.t);
            }
        } else if (!(this.l.getRenderer() instanceof ecs)) {
            this.l.setNewRenderer(DataInfos.BloodOxygenDayDetail, this.t);
        }
        f();
    }

    private void f() {
        this.l.setWillNotDraw(false);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.l.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
        this.l.setMarkerViewPosition(null);
        this.l.refresh();
    }

    private void setIntervalLegendLayout(View view) {
        if (nsn.p()) {
            aiW_(view, R.id.interval_legend_viewstub_treble, R.id.interval_legend_viewstub_treble_inflated);
        } else {
            aiW_(view, R.id.interval_legend_viewstub, R.id.interval_legend_viewstub_inflated);
        }
        View view2 = this.y;
        if (view2 != null) {
            this.aa = (HealthTextView) view2.findViewById(R.id.blood_oxygen_measure_range_1);
            this.ah = (HealthTextView) this.y.findViewById(R.id.blood_oxygen_measure_range_2);
            this.ag = (HealthTextView) this.y.findViewById(R.id.blood_oxygen_measure_range_3);
            this.ac = (HealthTextView) this.y.findViewById(R.id.blood_oxygen_measure_range_4);
            this.h = (HealthTextView) this.y.findViewById(R.id.blood_oxygen_measure_range_5);
            this.f = (LinearLayout) this.y.findViewById(R.id.legend_layout_5);
            this.ai = (LinearLayout) this.y.findViewById(R.id.legend_layout_abnormal);
            this.af = (LinearLayout) this.y.findViewById(R.id.extra_line_legend_layout);
            this.z = (ImageView) this.y.findViewById(R.id.range_1_image);
        }
    }

    private void e() {
        this.k = new ArrayList(16);
        String e = UnitUtil.e(70.0d, 2, 0);
        String e2 = UnitUtil.e(70.0d, 1, 0);
        String e3 = UnitUtil.e(89.0d, 2, 0);
        String e4 = UnitUtil.e(90.0d, 2, 0);
        this.k.add(e);
        this.k.add(e2);
        this.k.add(e3);
        this.k.add(e4);
        setBloodOxygenInterval(this.k);
    }

    private void setBloodOxygenInterval(List<String> list) {
        this.aa.setText(String.format(Locale.ENGLISH, this.t.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_lower_than), list.get(0)));
        this.ah.setText(String.format(Locale.ENGLISH, this.t.getString(R$string.IDS_press_auto_monitor_relax_range), list.get(1), list.get(2)));
        this.ag.setText(String.format(Locale.ENGLISH, this.t.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_greater_than), list.get(3)));
        if (efb.c()) {
            this.ac.setText(this.t.getString(R$string.IDS_hw_health_have_lower_spo2_high_risk));
        } else {
            this.ac.setText(this.t.getString(R$string.IDS_hw_health_blood_oxygen_have_lower_value));
        }
    }

    private void aiW_(View view, int i, int i2) {
        ViewStub viewStub = (ViewStub) view.findViewById(i);
        if (viewStub.getParent() != null) {
            this.y = viewStub.inflate();
        } else {
            this.y = view.findViewById(i2);
        }
    }

    private void b() {
        this.aj = new ArrayList<>(16);
    }

    private void a() {
        this.ab.setLayerType(1, null);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS");
        if (b.equals("true")) {
            this.l = new BloodOxygenLineChart(this.t.getApplicationContext(), DataInfos.BloodOxygenAltitudeDayDetail);
        } else {
            this.l = new BloodOxygenLineChart(this.t.getApplicationContext(), DataInfos.BloodOxygenDayDetail);
        }
        if (b.equals("true")) {
            this.l.setNewRenderer(DataInfos.BloodOxygenAltitudeDayDetail, this.t);
        } else {
            this.l.setNewRenderer(DataInfos.BloodOxygenDayDetail, this.t);
        }
        this.l.setLayerType(1, null);
        d();
        this.aj.add(0, this.l);
        this.o = new ntq<>(this.aj);
        this.w.setIsCompatibleScrollView(true);
        this.w.setIsScroll(false);
        this.w.setAdapter(this.o);
    }

    private void d() {
        this.l.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBarChart.2
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
            }
        });
    }

    private void h() {
        if (LanguageUtil.bc(this.t)) {
            this.v.setBackground(ContextCompat.getDrawable(this.t, R.drawable._2131427831_res_0x7f0b01f7));
            this.u.setBackground(ContextCompat.getDrawable(this.t, R.drawable._2131427825_res_0x7f0b01f1));
        } else {
            this.v.setBackground(ContextCompat.getDrawable(this.t, R.drawable._2131427825_res_0x7f0b01f1));
            this.u.setBackground(ContextCompat.getDrawable(this.t, R.drawable._2131427831_res_0x7f0b01f7));
        }
    }

    private void j() {
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBarChart.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!SectionBloodOxygenBarChart.this.l.isAnimating()) {
                    BloodOxygenLineChart bloodOxygenLineChart = SectionBloodOxygenBarChart.this.l;
                    BloodOxygenLineChart bloodOxygenLineChart2 = SectionBloodOxygenBarChart.this.l;
                    Objects.requireNonNull(bloodOxygenLineChart2);
                    bloodOxygenLineChart.scrollOnePageOlder(new egn(this, bloodOxygenLineChart2));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBarChart.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!SectionBloodOxygenBarChart.this.l.isAnimating()) {
                    if (SectionBloodOxygenBarChart.this.l.getCurrentPagerStartX() == SectionBloodOxygenBarChart.this.l.getCurrentPagerStartX(jdl.t(System.currentTimeMillis()) - 1)) {
                        SectionBloodOxygenBarChart.this.u.setVisibility(8);
                    }
                    BloodOxygenLineChart bloodOxygenLineChart = SectionBloodOxygenBarChart.this.l;
                    BloodOxygenLineChart bloodOxygenLineChart2 = SectionBloodOxygenBarChart.this.l;
                    Objects.requireNonNull(bloodOxygenLineChart2);
                    bloodOxygenLineChart.scrollOnePageNewer(new egq(this, bloodOxygenLineChart2));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        if (hashMap.get("ALTITUDE_IMAGE_LIST") instanceof List) {
            this.i = (List) hashMap.get("ALTITUDE_IMAGE_LIST");
        }
        if ((hashMap.get("BAR_COMMON_CHART_HOLDER") instanceof InteractorLineChartHolder) && this.n == null) {
            InteractorLineChartHolder interactorLineChartHolder = (InteractorLineChartHolder) hashMap.get("BAR_COMMON_CHART_HOLDER");
            this.n = interactorLineChartHolder;
            interactorLineChartHolder.addDataLayer((InteractorLineChartHolder) this.l, DataInfos.BloodOxygenDayDetail);
            this.l.acquireScrollAdapter().acquireXAxisValueFormatter().enableMarkerViewShowRange(true);
            this.o.notifyDataSetChanged();
            if (hashMap.get("BAR_COMMON_MARK_CHANGE_CALL_BACK") instanceof OnXRangeSetCallback) {
                final OnXRangeSetCallback onXRangeSetCallback = (OnXRangeSetCallback) hashMap.get("BAR_COMMON_MARK_CHANGE_CALL_BACK");
                this.l.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: egl
                    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
                    public final void onRangeShow(int i, int i2) {
                        SectionBloodOxygenBarChart.this.b(onXRangeSetCallback, i, i2);
                    }
                });
            }
            if (hashMap.get("BAR_COMMON_MARK_TEXT_CALL_BACK") instanceof HwHealthMarkerView.OnMarkViewTextNotify) {
                final HwHealthMarkerView.OnMarkViewTextNotify onMarkViewTextNotify = (HwHealthMarkerView.OnMarkViewTextNotify) hashMap.get("BAR_COMMON_MARK_TEXT_CALL_BACK");
                this.l.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: egm
                    @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
                    public final void onTextChanged(String str, List list) {
                        HwHealthMarkerView.OnMarkViewTextNotify.this.onTextChanged(str, list);
                    }
                });
            }
        }
        if ((hashMap.get("BAR_COMMON_START_TIME") instanceof Integer) && ((Integer) hashMap.get("BAR_COMMON_START_TIME")).intValue() != 0) {
            this.l.setShowRange(((Integer) hashMap.get("BAR_COMMON_START_TIME")).intValue(), this.l.acquireScrollAdapter().acquireRange());
            this.l.refresh();
        }
        if (hashMap.get("BAR_COMMON_REFLESH_TIME") instanceof Long) {
            this.l.reflesh(((Long) hashMap.get("BAR_COMMON_REFLESH_TIME")).longValue());
        }
        nsy.cMr_(this.q, nru.b(hashMap, "BAR_CHART_DATE_TEXT", ""));
        nsy.cMr_(this.p, nru.b(hashMap, "BAR_CHART_PERIOD_STRING", ""));
        if (hashMap.get("BAR_CHART_VALUE") instanceof SpannableString) {
            this.s.setText((SpannableString) hashMap.get("BAR_CHART_VALUE"));
        }
        if (hashMap.get("BAR_CHART_VALUE_STEP") instanceof String) {
            nsy.cMr_(this.r, nru.b(hashMap, "BAR_CHART_VALUE_STEP", ""));
        }
        c(hashMap);
    }

    public /* synthetic */ void b(OnXRangeSetCallback onXRangeSetCallback, int i, int i2) {
        onXRangeSetCallback.onRangeShow(i, i2, this.l.fetchMarkViewMinuteValue());
    }

    private void c(HashMap<String, Object> hashMap) {
        boolean z;
        nsy.cMr_(this.h, nru.b(hashMap, "BAR_CHART_LEGEND_TEXT", ""));
        nsy.cMr_(this.m, nru.b(hashMap, "BAR_CHART_CUSOR_NAME_TEXT", ""));
        nsy.cMz_(this.x, nru.d((Map) hashMap, "FULL_SCREEN_IMAGE", 0));
        if (hashMap.get("VISIVILITY_LIST") instanceof List) {
            d(hashMap);
        }
        if (hashMap.get("ALTITUDE_SWITCH_LIST") instanceof List) {
            e(hashMap);
        }
        if (hashMap.get("BAR_CHART_CLICK_EVENT") instanceof OnClickSectionListener) {
            setClickListenerEvent(hashMap.get("BAR_CHART_CLICK_EVENT"));
        }
        if ((hashMap.get("BAR_CHART_REFRESH_CHART_ONLY") instanceof Boolean) && ((Boolean) hashMap.get("BAR_CHART_REFRESH_CHART_ONLY")).booleanValue()) {
            c();
            z = true;
        } else {
            z = false;
        }
        if ((hashMap.get("BAR_CHART_REFRESH") instanceof Boolean) && !z && ((Boolean) hashMap.get("BAR_CHART_REFRESH")).booleanValue()) {
            f();
        }
        if (hashMap.get("FULL_SCREEN_CALLBACK") instanceof FullScreenCallback) {
            final FullScreenCallback fullScreenCallback = (FullScreenCallback) hashMap.get("FULL_SCREEN_CALLBACK");
            this.x.setOnClickListener(new View.OnClickListener() { // from class: egp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SectionBloodOxygenBarChart.this.aiX_(fullScreenCallback, view);
                }
            });
        }
        if (this.r.getVisibility() == 8 && String.valueOf((SpannableString) hashMap.get("BAR_CHART_VALUE")).equals("--")) {
            this.m.setVisibility(8);
        } else {
            this.m.setVisibility(0);
        }
    }

    public /* synthetic */ void aiX_(FullScreenCallback fullScreenCallback, View view) {
        fullScreenCallback.click(this.l.queryMarkerViewTimeRangeMin());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(HashMap<String, Object> hashMap) {
        List list = (List) hashMap.get("ALTITUDE_SWITCH_LIST");
        if (list.size() < 4 || this.j == null) {
            return;
        }
        boolean booleanValue = ((Boolean) list.get(0)).booleanValue();
        this.am = ((Boolean) list.get(1)).booleanValue();
        this.c = ((Boolean) list.get(2)).booleanValue();
        this.d = ((Boolean) list.get(3)).booleanValue();
        if (!booleanValue) {
            this.j.setVisibility(8);
            return;
        }
        if (!koq.b(this.i, 1)) {
            this.j.setVisibility(0);
            if (this.am) {
                this.j.setImageResource(this.i.get(1).intValue());
                return;
            } else {
                this.j.setImageResource(this.i.get(0).intValue());
                return;
            }
        }
        LogUtil.d("SectionBloodOxygenLineChart", "wrong mAltitudeImageList!");
    }

    private void d(HashMap<String, Object> hashMap) {
        int left;
        this.b = false;
        List list = (List) hashMap.get("VISIVILITY_LIST");
        if (list.size() == 2) {
            boolean booleanValue = ((Boolean) list.get(0)).booleanValue();
            boolean booleanValue2 = ((Boolean) list.get(1)).booleanValue();
            if (booleanValue) {
                this.r.setVisibility(0);
            } else {
                this.r.setVisibility(8);
            }
            if (booleanValue2) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
        } else {
            LogUtil.d("SectionBloodOxygenLineChart", "wrong boolean list!");
        }
        if (this.z == null) {
            return;
        }
        if (LanguageUtil.bc(this.t)) {
            left = (((LinearLayout) this.z.getParent()).getWidth() - this.z.getRight()) - (this.z.getWidth() / 3);
        } else {
            left = this.z.getLeft();
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.ai.getLayoutParams();
        if (left != layoutParams.getMarginStart()) {
            layoutParams.setMarginStart(left);
            this.ai.setLayoutParams(layoutParams);
        }
    }

    private void setClickListenerEvent(Object obj) {
        if (obj instanceof OnClickSectionListener) {
            final OnClickSectionListener onClickSectionListener = (OnClickSectionListener) obj;
            HealthTextView healthTextView = this.q;
            if (healthTextView != null) {
                healthTextView.setOnClickListener(new View.OnClickListener() { // from class: ego
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SectionBloodOxygenBarChart.aiV_(OnClickSectionListener.this, view);
                    }
                });
            }
            ImageView imageView = this.j;
            if (imageView != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionBloodOxygenBarChart.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        onClickSectionListener.onClick("ALTITUDE_SWITCH_CLICK_EVENT");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void aiV_(OnClickSectionListener onClickSectionListener, View view) {
        onClickSectionListener.onClick("BAR_CHART_CALENDAR_CLICK_EVENT");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void clear() {
        super.clear();
        ObserverManagerUtil.e("REMOVE_GLOBAL_LAYOUT");
        ObserverManagerUtil.e("BLOOD_OXYGEN_ARROW_VISIBLE");
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionBloodOxygenLineChart";
    }
}
