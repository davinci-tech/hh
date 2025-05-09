package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.TimeSharingFragment;
import com.huawei.health.knit.section.view.SectionPieChart;
import com.huawei.health.knit.section.view.SectionPieChartBasicAdapter;
import com.huawei.health.knit.section.view.SectionPieChartTrendAdapter;
import com.huawei.health.knit.ui.KnitHealthDetailActivity;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ixx;
import defpackage.nkz;
import defpackage.nld;
import defpackage.nmj;
import defpackage.nqx;
import defpackage.nrn;
import defpackage.nru;
import defpackage.nsi;
import defpackage.nsk;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class SectionPieChart extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private View f2712a;
    private HealthTextView aa;
    private HealthTextView ab;
    private nqx ac;
    private LinearLayout ad;
    private HealthViewPager ae;
    private View af;
    private SectionPieChartTrendAdapter ag;
    private HealthTextView ah;
    private ViewStub ai;
    private HealthTextView aj;
    private View ak;
    private HealthTextView al;
    private HealthRingChart am;
    private HealthRingChartAdapter an;
    private String ao;
    private SpannableString ap;
    private HealthTextView aq;
    private int ar;
    private boolean as;
    private View au;
    private ViewStub ax;
    private OnClickSectionListener b;
    private View c;
    private ViewStub d;
    private ViewStub e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private ImageView i;
    private HealthTextView j;
    private HealthTextView k;
    private SectionPieChartBasicAdapter l;
    private LinearLayout m;
    private HealthTextView n;
    private HealthTextView o;
    private View.OnClickListener p;
    private Context q;
    private LinearLayout r;
    private HashMap<String, Object> s;
    private LinearLayout t;
    private boolean u;
    private HealthDivider v;
    private boolean w;
    private boolean x;
    private View y;
    private HealthSubTabWidget z;

    public SectionPieChart(Context context) {
        this(context, null);
    }

    public SectionPieChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionPieChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ao = "";
        this.ar = nrn.d(R.color._2131296532_res_0x7f090114);
        this.as = false;
        this.u = false;
        this.x = false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionPieChart", "onCreateView");
        this.q = context;
        g();
        return this.ak;
    }

    private void g() {
        if (this.ak == null) {
            LogUtil.h("SectionPieChart", "initView mainView is null, start to inflate");
            if (nsn.p()) {
                this.ak = LayoutInflater.from(this.q).inflate(R.layout.section_pie_chart_trable, (ViewGroup) this, false);
            } else {
                this.ak = LayoutInflater.from(this.q).inflate(R.layout.section_pie_chart, (ViewGroup) this, false);
            }
        }
        LinearLayout linearLayout = (LinearLayout) this.ak.findViewById(R.id.section_pie_ll);
        this.m = linearLayout;
        linearLayout.setMinimumWidth(nsn.h(this.q));
        this.r = (LinearLayout) this.ak.findViewById(R.id.first_layer_left_layout);
        this.f = (HealthTextView) this.ak.findViewById(R.id.first_layer_left_top_tv);
        this.g = (HealthTextView) this.ak.findViewById(R.id.first_layer_left_left_tv);
        this.t = (LinearLayout) this.ak.findViewById(R.id.first_layer_right_layout);
        this.k = (HealthTextView) this.ak.findViewById(R.id.first_layer_right_top_tv);
        this.h = (HealthTextView) this.ak.findViewById(R.id.first_layer_right_left_tv);
        this.o = (HealthTextView) this.ak.findViewById(R.id.first_layer_right_right_tv);
        this.v = (HealthDivider) this.ak.findViewById(R.id.section_divider);
        this.y = this.ak.findViewById(R.id.second_layer_piechart_container);
        this.am = (HealthRingChart) this.ak.findViewById(R.id.second_layer_piechart);
        this.aq = (HealthTextView) this.ak.findViewById(R.id.third_layer_tv);
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.q, new nld(true, true));
        this.an = healthRingChartAdapter;
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: ehg
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                String e;
                e = UnitUtil.e(nkzVar.e() * 100.0f, 2, 1);
                return e;
            }
        }, 1);
        this.am.setAdapter(this.an);
        this.d = (ViewStub) this.ak.findViewById(R.id.basic_situation_stub);
        this.ax = (ViewStub) this.ak.findViewById(R.id.trend_stub);
        this.ai = (ViewStub) this.ak.findViewById(R.id.time_sharing_stub);
        this.e = (ViewStub) this.ak.findViewById(R.id.expand_stub);
        this.p = new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionPieChart.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SectionPieChart.this.a();
                SectionPieChart.this.as = !r0.as;
                SectionPieChart sectionPieChart = SectionPieChart.this;
                sectionPieChart.c(sectionPieChart.as);
                if (!SectionPieChart.this.as) {
                    SectionPieChart.this.getKnitFragment().getHealthScrollView().fling(0);
                    SectionPieChart.this.getKnitFragment().getHealthScrollView().smoothScrollTo(0, 0);
                }
                SectionPieChart.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionPieChart", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionPieChart", "no need to bind");
            return;
        }
        this.s = hashMap;
        setText(hashMap);
        c(hashMap);
        h();
        d(hashMap);
    }

    private void c(HashMap<String, Object> hashMap) {
        this.an.d(nru.d((Map) hashMap, "SECOND_LAYER_RING_CHART_LEGEND_COLOR", 0));
        List<nkz> d = nru.d(hashMap, "SECOND_LAYER_RING_CHART", nkz.class, new ArrayList());
        this.am.c(d);
        List d2 = nru.d(hashMap, "SECOND_LAYER_RING_CHART_DESC", String.class, new ArrayList());
        if (d2.size() == 2) {
            this.am.setDesc((String) d2.get(0), (String) d2.get(1));
        }
        final int d3 = nru.d((Map) hashMap, "SECOND_LAYER_RING_CHART_VALUE_UNIT_RES_ID", -16777216);
        this.an.a(new HealthRingChartAdapter.DataFormatter() { // from class: ehj
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                return SectionPieChart.this.a(d3, nkzVar);
            }
        });
        this.am.c(d);
        this.ar = nru.d((Map) hashMap, "THIRD_LAYER_CLICK_TEXT_COLOR", -16777216);
        this.b = (OnClickSectionListener) nru.c(hashMap, "CLICK_EVENT_LISTENER", OnClickSectionListener.class, null);
        this.ao = nru.b(hashMap, "THIRD_LAYER_TEXT", "");
        this.ap = (SpannableString) nru.c(hashMap, "THIRD_LAYER_CLICK_TEXT", SpannableString.class, new SpannableString(""));
    }

    public /* synthetic */ String a(int i, nkz nkzVar) {
        int i2 = (int) nkzVar.i();
        return this.q.getResources().getQuantityString(i, i2, UnitUtil.e(i2, 1, 0));
    }

    private void d(HashMap<String, Object> hashMap) {
        boolean z = nru.d((Map) hashMap, KnitHealthDetailActivity.KEY_SUB_PAGE_INDEX, 0) != 0;
        this.w = z;
        if (!this.u) {
            this.u = true;
            this.as = z;
        }
        e(hashMap);
        b(hashMap);
        f(hashMap);
        a(this.s);
    }

    private void b(HashMap<String, Object> hashMap) {
        HealthTextView healthTextView = this.n;
        if (healthTextView != null) {
            healthTextView.setText(nru.b(hashMap, "BASIC_HEADER_TITLE", ""));
        }
        if (this.l != null) {
            this.l.d(nru.d(hashMap, "BASIC_DATA", SectionPieChartBasicAdapter.b.class, new ArrayList()));
        }
    }

    private void f(HashMap<String, Object> hashMap) {
        HealthTextView healthTextView = this.aj;
        if (healthTextView != null) {
            healthTextView.setText(nru.b(hashMap, "TREND_HEADER_TITLE", ""));
        }
        nsy.cMr_(this.aa, nru.b(hashMap, "TREND_TITLE_PRIMARY", ""));
        nsy.cMr_(this.ab, nru.b(hashMap, "TREND_TITLE_SECONDARY", ""));
        nsy.cMr_(this.al, nru.b(hashMap, "TREND_INDICATOR_TITLE", ""));
        if (this.ag != null) {
            this.ag.e(nru.d(hashMap, "TREND_DATA", SectionPieChartTrendAdapter.b.class, new ArrayList()));
        }
    }

    private void a(HashMap<String, Object> hashMap) {
        Map map = (Map) this.s.get("TIME_SHARING_BASIC_DATA");
        Map<String, List<nkz>> map2 = (Map) this.s.get("TIME_SHARING_RING_CHART");
        boolean z = true;
        if (map2 != null && map2.size() == 1) {
            nsy.cMA_(this.af, 8);
            this.x = false;
            LogUtil.a("SectionPieChart", "hide time share");
            return;
        }
        this.x = true;
        i();
        HealthTextView healthTextView = this.ah;
        if (healthTextView != null) {
            healthTextView.setText(nru.b(hashMap, "TIME_SHARING_TITLE", ""));
        }
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("timeSharingBasicMap size: ");
        Object obj = Constants.NULL;
        sb.append(map != null ? Integer.valueOf(map.size()) : Constants.NULL);
        sb.append("timeSharingRingMap size: ");
        if (map2 != null) {
            obj = Integer.valueOf(map2.size());
        }
        sb.append(obj);
        objArr[0] = sb.toString();
        LogUtil.a("SectionPieChart", objArr);
        if (this.ae == null || this.z == null || map == null) {
            LogUtil.a("SectionPieChart", "mSubTabWidget, mTimeSharingViewPager or timeSharingBasicMap is null");
            return;
        }
        this.ac = new nqx(getKnitFragment().getChildFragmentManager(), this.ae, this.z);
        this.z.h();
        HashMap hashMap2 = new HashMap(map);
        if (LanguageUtil.bc(this.q)) {
            ListIterator listIterator = new ArrayList(hashMap2.entrySet()).listIterator(hashMap2.size());
            while (listIterator.hasPrevious()) {
                c(map2, (Map.Entry) listIterator.previous(), z);
                z = false;
            }
            return;
        }
        Iterator it = hashMap2.entrySet().iterator();
        while (it.hasNext()) {
            c(map2, (Map.Entry) it.next(), z);
            z = false;
        }
    }

    private void c(Map<String, List<nkz>> map, Map.Entry<String, List<SectionPieChartBasicAdapter.b>> entry, boolean z) {
        TimeSharingFragment b = TimeSharingFragment.b();
        b.b(map != null ? map.get(entry.getKey()) : null, getResources().getString(R$string.IDS_blood_pressure_time_point_basic_infomation), entry.getValue());
        b.a(this.ae);
        this.ac.c(this.z.c(entry.getKey()), b, z);
    }

    private void b() {
        if (this.f2712a != null) {
            return;
        }
        View inflate = this.d.inflate();
        this.f2712a = inflate;
        this.n = (HealthTextView) inflate.findViewById(R.id.basic_situation_sub_header);
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.f2712a.findViewById(R.id.basic_recycler_view);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.q));
        SectionPieChartBasicAdapter sectionPieChartBasicAdapter = new SectionPieChartBasicAdapter(this.q);
        this.l = sectionPieChartBasicAdapter;
        healthRecycleView.setAdapter(sectionPieChartBasicAdapter);
        this.n.post(new Runnable() { // from class: com.huawei.health.knit.section.view.SectionPieChart.4
            @Override // java.lang.Runnable
            public void run() {
                if (!SectionPieChart.this.w || SectionPieChart.this.getKnitFragment() == null || SectionPieChart.this.getKnitFragment().getHealthScrollView() == null || SectionPieChart.this.n == null) {
                    return;
                }
                SectionPieChart.this.w = false;
                HealthScrollView healthScrollView = SectionPieChart.this.getKnitFragment().getHealthScrollView();
                int[] iArr = new int[2];
                healthScrollView.getLocationInWindow(iArr);
                int[] iArr2 = new int[2];
                SectionPieChart.this.n.getLocationOnScreen(iArr2);
                healthScrollView.scrollTo(0, iArr2[1] - iArr[1]);
            }
        });
    }

    private void c() {
        if (this.au != null) {
            return;
        }
        View inflate = this.ax.inflate();
        this.au = inflate;
        this.aj = (HealthTextView) inflate.findViewById(R.id.trend_sub_header);
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.au.findViewById(R.id.trend_recycler);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.q));
        SectionPieChartTrendAdapter sectionPieChartTrendAdapter = new SectionPieChartTrendAdapter(this.q);
        this.ag = sectionPieChartTrendAdapter;
        healthRecycleView.setAdapter(sectionPieChartTrendAdapter);
        this.al = (HealthTextView) this.au.findViewById(R.id.trend_indicator_title);
        this.ad = (LinearLayout) this.au.findViewById(R.id.right_oval_text_layout);
        this.aa = (HealthTextView) this.au.findViewById(R.id.primary_title);
        this.ab = (HealthTextView) this.au.findViewById(R.id.secondary_title);
    }

    private void e() {
        LogUtil.a("SectionPieChart", "inflateTimeSharingIfNeeded");
        if (this.af != null) {
            return;
        }
        View inflate = this.ai.inflate();
        this.af = inflate;
        this.ah = (HealthTextView) inflate.findViewById(R.id.time_sharing_sub_header);
        this.ae = (HealthViewPager) this.af.findViewById(R.id.time_sharing_view_pager);
        this.z = (HealthSubTabWidget) this.af.findViewById(R.id.time_sharing_tab_widget);
        this.ae.setIsAutoHeight(true);
        this.ae.setFocusableInTouchMode(false);
        this.ae.requestFocus();
    }

    private void e(HashMap<String, Object> hashMap) {
        DataInfos dataInfos = (DataInfos) nru.c(hashMap, "DATA_INFO", DataInfos.class, DataInfos.BloodPressureDayDetail);
        if (this.f2712a == null && dataInfos != DataInfos.BloodPressureDayDetail) {
            b();
        }
        if (this.au == null && dataInfos != DataInfos.BloodPressureDayDetail) {
            c();
        }
        if (this.af == null && dataInfos != DataInfos.BloodPressureDayDetail) {
            e();
        }
        if (this.c == null) {
            View inflate = this.e.inflate();
            this.c = inflate;
            this.i = (ImageView) inflate.findViewById(R.id.expand_arrow);
            this.j = (HealthTextView) this.c.findViewById(R.id.expand_arrow_hint);
            this.c.setOnClickListener(this.p);
        }
        c(this.as);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        LogUtil.a("SectionPieChart", "showMoreAnalysis: " + z);
        nsy.cMq_(this.j, z ? R$string.IDS_hide_analysis : R$string.IDS_more_analysis);
        HealthTextView healthTextView = this.j;
        if (healthTextView != null) {
            healthTextView.setAllCaps(true);
        }
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setRotationX(z ? 180.0f : 0.0f);
        }
        if (!z) {
            a(this.s);
        }
        int i = z ? 0 : 8;
        nsy.cMA_(this.v, i);
        nsy.cMA_(this.y, i);
        nsy.cMA_(this.f2712a, i);
        nsy.cMA_(this.au, i);
        i();
        nsy.cMA_(this.aq, i);
    }

    private void i() {
        nsy.cMA_(this.af, (this.as && this.x) ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int d = nru.d((Map) this.s, "DATA_INFO_INDEX", 1);
        if ((d == 2 || d == 3) && !this.as) {
            HashMap hashMap = new HashMap();
            hashMap.put("click", "1");
            hashMap.put("type", Integer.valueOf(d));
            ixx.d().d(this.q, AnalyticsValue.BLOOD_PRESSURE_MORE_ANALYSIS_2040204.value(), hashMap, 0);
        }
    }

    private void h() {
        setKeywordClickable(this.aq, new SpannableString(this.ao), Pattern.compile(this.ap.toString()), new ClickableSpan() { // from class: com.huawei.health.knit.section.view.SectionPieChart.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (SectionPieChart.this.b != null) {
                    SectionPieChart.this.b.onClick("THIRD_LAYER_CLICK_TEXT");
                }
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        HealthTextView healthTextView = this.ab;
        if (healthTextView == null) {
            LogUtil.a("SectionPieChart", "mSecondaryTitle is null");
        } else {
            final ViewTreeObserver viewTreeObserver = healthTextView.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.SectionPieChart.5
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (SectionPieChart.this.ad != null && (SectionPieChart.this.ad.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) SectionPieChart.this.ad.getLayoutParams();
                        if (nsy.cMe_(SectionPieChart.this.ab)) {
                            layoutParams.addRule(3, R.id.left_oval_text_layout);
                            layoutParams.addRule(18, R.id.left_oval_text_layout);
                        } else {
                            layoutParams.addRule(17, R.id.left_oval_text_layout);
                        }
                        SectionPieChart.this.ad.setLayoutParams(layoutParams);
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                        return;
                    }
                    LogUtil.a("SectionPieChart", "no need to reshape view");
                }
            });
        }
    }

    public void setKeywordClickable(HealthTextView healthTextView, SpannableString spannableString, Pattern pattern, ClickableSpan clickableSpan) {
        Matcher matcher = pattern.matcher(spannableString.toString());
        while (matcher.find()) {
            String group = matcher.group();
            if (!"".equals(group)) {
                int indexOf = spannableString.toString().indexOf(group);
                setClickTextView(healthTextView, spannableString, indexOf, indexOf + group.length(), clickableSpan);
            }
        }
    }

    public void setClickTextView(HealthTextView healthTextView, SpannableString spannableString, int i, int i2, ClickableSpan clickableSpan) {
        TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.q, R.style.health_basic_click_textview);
        spannableString.setSpan(textAppearanceSpan, i, i2, 33);
        spannableString.setSpan(clickableSpan, i, i2, 33);
        spannableString.setSpan(new ForegroundColorSpan(this.ar), i, i2, 33);
        if (LanguageUtil.p(this.q) && (nsn.ag(this.q) || nsn.ae(this.q))) {
            StringBuilder sb = new StringBuilder(spannableString.toString());
            sb.insert(sb.length() - this.ap.length(), "\n");
            SpannableString spannableString2 = new SpannableString(sb);
            int i3 = i2 + 1;
            spannableString2.setSpan(textAppearanceSpan, i, i3, 33);
            spannableString2.setSpan(clickableSpan, i, i2, 33);
            spannableString2.setSpan(new ForegroundColorSpan(this.ar), i, i3, 33);
            healthTextView.setText(spannableString2);
        } else {
            healthTextView.setText(spannableString);
        }
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setText(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.f, nru.b(hashMap, "FIRST_LAYER_LEFT_TOP", ""));
        nsy.cMr_(this.g, e(nru.b(hashMap, "FIRST_LAYER_LEFT_LEFT", "")));
        nsy.cMr_(this.k, nru.b(hashMap, "FIRST_LAYER_RIGHT_TOP", ""));
        nsy.cMr_(this.h, nru.b(hashMap, "FIRST_LAYER_RIGHT_LEFT", ""));
        this.o.setText(nru.b(hashMap, "FIRST_LAYER_RIGHT_RIGHT", ""));
        j();
    }

    private void j() {
        if (LanguageUtil.bp(getContext()) || LanguageUtil.ac(getContext())) {
            LinearLayout linearLayout = this.r;
            if (linearLayout != null) {
                linearLayout.setLayoutDirection(0);
            }
            LinearLayout linearLayout2 = this.t;
            if (linearLayout2 != null) {
                linearLayout2.setLayoutDirection(0);
            }
        }
    }

    private CharSequence e(String str) {
        SpannableString spannableString = new SpannableString(str);
        nsi.cKH_(spannableString, str, new ForegroundColorSpan(getResources().getColor(R.color._2131299241_res_0x7f090ba9)));
        nsi.cKH_(spannableString, str, new AbsoluteSizeSpan(12, true));
        nsi.cKH_(spannableString, str, new nmj(nsk.cKP_()));
        Matcher matcher = Pattern.compile("[\\d\\/]", 2).matcher(spannableString);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            nsi.cKG_(spannableString, start, end, new TextAppearanceSpan(getContext(), R.style.blood_pressure_data_style));
            nsi.cKE_(spannableString, start, end);
        }
        return spannableString;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionPieChart";
    }
}
