package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.CoreSleepBarChartView;
import com.huawei.health.knit.section.listener.BarChartRangeShowCallback;
import com.huawei.health.knit.section.listener.ChartMarkChangeCallback;
import com.huawei.health.knit.section.listener.ChartMarkTextCallback;
import com.huawei.health.knit.section.listener.ClickChartDataCallback;
import com.huawei.health.knit.section.view.BarChartCommonSection;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.HwHealthBarScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.een;
import defpackage.eex;
import defpackage.eey;
import defpackage.gge;
import defpackage.jcf;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nru;
import defpackage.nsf;
import defpackage.nsy;
import defpackage.ntq;
import health.compact.a.LanguageUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class BarChartCommonSection extends BaseSection {
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2615a;
    private ImageView aa;
    private Context ab;
    private HealthViewPager ac;
    private ChartMarkTextCallback ad;
    private LinearLayout ae;
    private boolean af;
    private AtomicBoolean ag;
    private AtomicBoolean ah;
    private HealthTextView ai;
    private LinearLayout aj;
    private HealthTextView ak;
    private HealthTextView al;
    private HealthTextView am;
    private HealthTextView an;
    private LinearLayout ao;
    private float ap;
    private Observer aq;
    private OnClickSectionListener ar;
    private Observer as;
    private OnClickSectionListener at;
    private LinearLayout au;
    private ClickChartDataCallback av;
    private ntq<View> aw;
    private OnClickSectionListener ax;
    private LinearLayout ay;
    private HealthTextView az;
    private String ba;
    private HealthTextView bb;
    private LinearLayout bc;
    private long bd;
    private HealthTextView be;
    private long bf;
    private SleepTotalData bg;
    private HwHealthBarScrollChartHolder bh;
    private HealthTextView bi;
    private LinearLayout bj;
    private LinearLayout bk;
    private LinearLayout bl;
    private HealthTextView bm;
    private LinearLayout bn;
    private HealthTextView bo;
    private int bp;
    private LinearLayout bq;
    private HealthTextView br;
    private View bs;
    private int bv;
    private LinearLayout bw;
    private HealthTextView c;
    protected DataInfos d;
    private LinearLayout e;
    private ImageView f;
    private LinearLayout g;
    private boolean h;
    private ImageView i;
    private boolean j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private ImageView o;
    private ImageView p;
    private ImageView q;
    private ImageView r;
    private ViewTreeObserver.OnGlobalLayoutListener s;
    private ImageView t;
    private ChartMarkChangeCallback u;
    private HealthCalendar v;
    private HealthTextView w;
    private CoreSleepBarChartView x;
    private BarChartRangeShowCallback y;
    private HealthTextView z;

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ahw_(this.e);
        ahw_(this.aj);
        ahw_(this.ay);
        ahw_(this.bj);
        ahw_(this.ao);
        ahw_(this.bl);
        ahw_(this.bq);
    }

    private void ahw_(LinearLayout linearLayout) {
        linearLayout.setGravity(17);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.setMarginEnd(0);
        linearLayout.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object[] objArr, List<LinearLayout> list) {
        int i = 0;
        for (int i2 = 0; i2 < objArr.length - 2; i2++) {
            LinearLayout linearLayout = list.get(i2);
            if (((Boolean) objArr[i2]).booleanValue()) {
                i = ahx_(i, linearLayout);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.bl.setGravity(8388629);
        this.bq.setGravity(8388627);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.bl.getLayoutParams();
        layoutParams.setMarginEnd(nrr.e(this.ab, 32.0f));
        this.bl.setLayoutParams(layoutParams);
    }

    private int ahx_(int i, LinearLayout linearLayout) {
        if (i == 0) {
            linearLayout.setGravity(8388629);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMarginEnd(nrr.e(this.ab, 32.0f));
            linearLayout.setLayoutParams(layoutParams);
            return i + 1;
        }
        linearLayout.setGravity(8388627);
        return i;
    }

    public BarChartCommonSection(Context context) {
        super(context);
        this.bg = new SleepTotalData();
        this.ag = new AtomicBoolean(false);
        this.ah = new AtomicBoolean(false);
        this.ba = "0";
        this.bf = System.currentTimeMillis();
        this.j = true;
        this.h = true;
        this.bd = 0L;
        this.bv = 0;
        this.bp = Integer.MAX_VALUE;
        this.af = true;
        this.aq = new Observer() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (!koq.e(objArr, 6)) {
                    LogUtil.a("BarChartCommonSection", "null args!");
                    return;
                }
                if ((objArr[0] instanceof Boolean) && (objArr[1] instanceof Boolean) && (objArr[2] instanceof Boolean) && (objArr[3] instanceof Boolean) && (objArr[4] instanceof Boolean) && (objArr[5] instanceof Boolean) && (objArr[6] instanceof DataInfos)) {
                    synchronized (BarChartCommonSection.b) {
                        if (((DataInfos) objArr[6]) == BarChartCommonSection.this.d) {
                            BarChartCommonSection.this.i();
                            nsy.cMA_(BarChartCommonSection.this.e, ((Boolean) objArr[0]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.aj, ((Boolean) objArr[1]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.ay, ((Boolean) objArr[2]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bj, ((Boolean) objArr[3]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.ao, ((Boolean) objArr[4]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bl, ((Boolean) objArr[5]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bq, ((Boolean) objArr[5]).booleanValue() ? 0 : 8);
                            int i = 0;
                            for (int i2 = 0; i2 < objArr.length - 1; i2++) {
                                if (i2 == 5 && ((Boolean) objArr[i2]).booleanValue()) {
                                    i += 2;
                                } else if (((Boolean) objArr[i2]).booleanValue()) {
                                    i++;
                                } else {
                                    LogUtil.a("BarChartCommonSection", "not show layout");
                                }
                            }
                            if (i > 5) {
                                nsy.cMA_(BarChartCommonSection.this.bl, 8);
                                nsy.cMA_(BarChartCommonSection.this.bq, 8);
                                nsy.cMA_(BarChartCommonSection.this.g, 0);
                            } else {
                                nsy.cMA_(BarChartCommonSection.this.g, 8);
                            }
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(BarChartCommonSection.this.e);
                            arrayList.add(BarChartCommonSection.this.aj);
                            arrayList.add(BarChartCommonSection.this.ay);
                            arrayList.add(BarChartCommonSection.this.bj);
                            arrayList.add(BarChartCommonSection.this.ao);
                            if (i == 2) {
                                if (((Boolean) objArr[5]).booleanValue()) {
                                    BarChartCommonSection.this.n();
                                    return;
                                }
                                BarChartCommonSection.this.a(objArr, arrayList);
                            }
                        }
                    }
                }
            }
        };
        this.as = new Observer() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (!koq.e(objArr, 0)) {
                    LogUtil.a("BarChartCommonSection", "null args!");
                    return;
                }
                if (objArr[0] instanceof Float) {
                    Object obj = objArr[1];
                    if ((obj instanceof DataInfos) && ((DataInfos) obj) == BarChartCommonSection.this.d) {
                        BarChartCommonSection.this.ap = ((Float) objArr[0]).floatValue();
                        if (BarChartCommonSection.this.aa == null || BarChartCommonSection.this.ap != 0.0f) {
                            return;
                        }
                        BarChartCommonSection.this.aa.setVisibility(8);
                    }
                }
            }
        };
    }

    public BarChartCommonSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.bg = new SleepTotalData();
        this.ag = new AtomicBoolean(false);
        this.ah = new AtomicBoolean(false);
        this.ba = "0";
        this.bf = System.currentTimeMillis();
        this.j = true;
        this.h = true;
        this.bd = 0L;
        this.bv = 0;
        this.bp = Integer.MAX_VALUE;
        this.af = true;
        this.aq = new Observer() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (!koq.e(objArr, 6)) {
                    LogUtil.a("BarChartCommonSection", "null args!");
                    return;
                }
                if ((objArr[0] instanceof Boolean) && (objArr[1] instanceof Boolean) && (objArr[2] instanceof Boolean) && (objArr[3] instanceof Boolean) && (objArr[4] instanceof Boolean) && (objArr[5] instanceof Boolean) && (objArr[6] instanceof DataInfos)) {
                    synchronized (BarChartCommonSection.b) {
                        if (((DataInfos) objArr[6]) == BarChartCommonSection.this.d) {
                            BarChartCommonSection.this.i();
                            nsy.cMA_(BarChartCommonSection.this.e, ((Boolean) objArr[0]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.aj, ((Boolean) objArr[1]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.ay, ((Boolean) objArr[2]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bj, ((Boolean) objArr[3]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.ao, ((Boolean) objArr[4]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bl, ((Boolean) objArr[5]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bq, ((Boolean) objArr[5]).booleanValue() ? 0 : 8);
                            int i = 0;
                            for (int i2 = 0; i2 < objArr.length - 1; i2++) {
                                if (i2 == 5 && ((Boolean) objArr[i2]).booleanValue()) {
                                    i += 2;
                                } else if (((Boolean) objArr[i2]).booleanValue()) {
                                    i++;
                                } else {
                                    LogUtil.a("BarChartCommonSection", "not show layout");
                                }
                            }
                            if (i > 5) {
                                nsy.cMA_(BarChartCommonSection.this.bl, 8);
                                nsy.cMA_(BarChartCommonSection.this.bq, 8);
                                nsy.cMA_(BarChartCommonSection.this.g, 0);
                            } else {
                                nsy.cMA_(BarChartCommonSection.this.g, 8);
                            }
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(BarChartCommonSection.this.e);
                            arrayList.add(BarChartCommonSection.this.aj);
                            arrayList.add(BarChartCommonSection.this.ay);
                            arrayList.add(BarChartCommonSection.this.bj);
                            arrayList.add(BarChartCommonSection.this.ao);
                            if (i == 2) {
                                if (((Boolean) objArr[5]).booleanValue()) {
                                    BarChartCommonSection.this.n();
                                    return;
                                }
                                BarChartCommonSection.this.a(objArr, arrayList);
                            }
                        }
                    }
                }
            }
        };
        this.as = new Observer() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (!koq.e(objArr, 0)) {
                    LogUtil.a("BarChartCommonSection", "null args!");
                    return;
                }
                if (objArr[0] instanceof Float) {
                    Object obj = objArr[1];
                    if ((obj instanceof DataInfos) && ((DataInfos) obj) == BarChartCommonSection.this.d) {
                        BarChartCommonSection.this.ap = ((Float) objArr[0]).floatValue();
                        if (BarChartCommonSection.this.aa == null || BarChartCommonSection.this.ap != 0.0f) {
                            return;
                        }
                        BarChartCommonSection.this.aa.setVisibility(8);
                    }
                }
            }
        };
    }

    public BarChartCommonSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bg = new SleepTotalData();
        this.ag = new AtomicBoolean(false);
        this.ah = new AtomicBoolean(false);
        this.ba = "0";
        this.bf = System.currentTimeMillis();
        this.j = true;
        this.h = true;
        this.bd = 0L;
        this.bv = 0;
        this.bp = Integer.MAX_VALUE;
        this.af = true;
        this.aq = new Observer() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (!koq.e(objArr, 6)) {
                    LogUtil.a("BarChartCommonSection", "null args!");
                    return;
                }
                if ((objArr[0] instanceof Boolean) && (objArr[1] instanceof Boolean) && (objArr[2] instanceof Boolean) && (objArr[3] instanceof Boolean) && (objArr[4] instanceof Boolean) && (objArr[5] instanceof Boolean) && (objArr[6] instanceof DataInfos)) {
                    synchronized (BarChartCommonSection.b) {
                        if (((DataInfos) objArr[6]) == BarChartCommonSection.this.d) {
                            BarChartCommonSection.this.i();
                            nsy.cMA_(BarChartCommonSection.this.e, ((Boolean) objArr[0]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.aj, ((Boolean) objArr[1]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.ay, ((Boolean) objArr[2]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bj, ((Boolean) objArr[3]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.ao, ((Boolean) objArr[4]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bl, ((Boolean) objArr[5]).booleanValue() ? 0 : 8);
                            nsy.cMA_(BarChartCommonSection.this.bq, ((Boolean) objArr[5]).booleanValue() ? 0 : 8);
                            int i2 = 0;
                            for (int i22 = 0; i22 < objArr.length - 1; i22++) {
                                if (i22 == 5 && ((Boolean) objArr[i22]).booleanValue()) {
                                    i2 += 2;
                                } else if (((Boolean) objArr[i22]).booleanValue()) {
                                    i2++;
                                } else {
                                    LogUtil.a("BarChartCommonSection", "not show layout");
                                }
                            }
                            if (i2 > 5) {
                                nsy.cMA_(BarChartCommonSection.this.bl, 8);
                                nsy.cMA_(BarChartCommonSection.this.bq, 8);
                                nsy.cMA_(BarChartCommonSection.this.g, 0);
                            } else {
                                nsy.cMA_(BarChartCommonSection.this.g, 8);
                            }
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(BarChartCommonSection.this.e);
                            arrayList.add(BarChartCommonSection.this.aj);
                            arrayList.add(BarChartCommonSection.this.ay);
                            arrayList.add(BarChartCommonSection.this.bj);
                            arrayList.add(BarChartCommonSection.this.ao);
                            if (i2 == 2) {
                                if (((Boolean) objArr[5]).booleanValue()) {
                                    BarChartCommonSection.this.n();
                                    return;
                                }
                                BarChartCommonSection.this.a(objArr, arrayList);
                            }
                        }
                    }
                }
            }
        };
        this.as = new Observer() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (!koq.e(objArr, 0)) {
                    LogUtil.a("BarChartCommonSection", "null args!");
                    return;
                }
                if (objArr[0] instanceof Float) {
                    Object obj = objArr[1];
                    if ((obj instanceof DataInfos) && ((DataInfos) obj) == BarChartCommonSection.this.d) {
                        BarChartCommonSection.this.ap = ((Float) objArr[0]).floatValue();
                        if (BarChartCommonSection.this.aa == null || BarChartCommonSection.this.ap != 0.0f) {
                            return;
                        }
                        BarChartCommonSection.this.aa.setVisibility(8);
                    }
                }
            }
        };
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.ab = context;
        d();
        return this.bs;
    }

    private void d() {
        if (this.bs == null) {
            LogUtil.b("BarChartCommonSection", "initView mainView is null, start to inflate");
            this.bs = LayoutInflater.from(this.ab).inflate(R.layout.section_common_bar_layout, (ViewGroup) this, false);
        }
        this.ae = (LinearLayout) this.bs.findViewById(R.id.left_arrow_iv);
        this.bc = (LinearLayout) this.bs.findViewById(R.id.right_arrow_iv);
        this.p = (ImageView) nsy.cMd_(this.bs, R.id.fitness_detail_up_arrow_left);
        this.q = (ImageView) nsy.cMd_(this.bs, R.id.fitness_detail_up_arrow_right);
        this.z = (HealthTextView) nsy.cMd_(this.bs, R.id.fitness_detail_time_date_tv);
        this.az = (HealthTextView) this.bs.findViewById(R.id.core_sleep_bar_chart_date_time);
        this.be = (HealthTextView) this.bs.findViewById(R.id.common_sleep_sleep_time);
        this.w = (HealthTextView) this.bs.findViewById(R.id.common_sleep_bed_time);
        this.f2615a = (HealthTextView) this.bs.findViewById(R.id.day_time_duration_type);
        this.c = (HealthTextView) this.bs.findViewById(R.id.day_time_duration_type_2);
        this.am = (HealthTextView) this.bs.findViewById(R.id.deep_sleep_tv);
        this.ak = (HealthTextView) this.bs.findViewById(R.id.light_sleep_tv);
        this.an = (HealthTextView) this.bs.findViewById(R.id.rem_sleep_tv);
        this.al = (HealthTextView) this.bs.findViewById(R.id.wake_sleep_tv);
        this.ai = (HealthTextView) this.bs.findViewById(R.id.true_sleep_noon_tv);
        this.am.setSplittable(false);
        this.ak.setSplittable(false);
        this.an.setSplittable(false);
        this.al.setSplittable(false);
        this.ai.setSplittable(false);
        this.au = (LinearLayout) this.bs.findViewById(R.id.sleep_color_block);
        this.aj = (LinearLayout) this.bs.findViewById(R.id.light_sleep_ll);
        this.ay = (LinearLayout) this.bs.findViewById(R.id.core_sleep_layout_rem_sleep);
        this.bj = (LinearLayout) this.bs.findViewById(R.id.wake_sleep_ll);
        this.ao = (LinearLayout) this.bs.findViewById(R.id.core_sleep_layout_noon_sleep);
        this.n = (ImageView) this.bs.findViewById(R.id.legend_one_image);
        this.r = (ImageView) this.bs.findViewById(R.id.legend_two_image);
        this.t = (ImageView) this.bs.findViewById(R.id.legend_three_image);
        this.i = (ImageView) this.bs.findViewById(R.id.legend_four_image);
        this.f = (ImageView) this.bs.findViewById(R.id.legend_five_image);
        this.aa = (ImageView) this.bs.findViewById(R.id.hw_health_fitness_data_origin_icon);
        this.bl = (LinearLayout) this.bs.findViewById(R.id.manual_bed_ll);
        this.m = (ImageView) this.bs.findViewById(R.id.legend_six_image);
        this.bm = (HealthTextView) this.bs.findViewById(R.id.manual_bed_tv);
        this.bq = (LinearLayout) this.bs.findViewById(R.id.manual_sleep_ll);
        this.e = (LinearLayout) this.bs.findViewById(R.id.lengend_deep_ll);
        this.l = (ImageView) this.bs.findViewById(R.id.legend_seven_image);
        this.bo = (HealthTextView) this.bs.findViewById(R.id.manual_sleep_tv);
        this.bw = (LinearLayout) this.bs.findViewById(R.id.time_period_ll_2);
        this.g = (LinearLayout) this.bs.findViewById(R.id.extra_legend_layout);
        this.bk = (LinearLayout) this.bs.findViewById(R.id.manual_bed_ll_2);
        this.o = (ImageView) this.bs.findViewById(R.id.legend_six_image_2);
        this.bi = (HealthTextView) this.bs.findViewById(R.id.manual_bed_tv_2);
        this.bn = (LinearLayout) this.bs.findViewById(R.id.manual_sleep_ll_2);
        this.k = (ImageView) this.bs.findViewById(R.id.legend_seven_image_2);
        this.br = (HealthTextView) this.bs.findViewById(R.id.manual_sleep_tv_2);
        this.bb = (HealthTextView) this.bs.findViewById(R.id.processDialog_title_text);
        this.z.setOnClickListener(this);
        this.aa.setOnClickListener(this);
        b();
        a();
        g();
    }

    /* renamed from: com.huawei.health.knit.section.view.BarChartCommonSection$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[DataInfos.values().length];
            b = iArr;
            try {
                iArr[DataInfos.CoreSleepWeekDetail.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[DataInfos.CoreSleepMonthDetail.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[DataInfos.CoreSleepYearDetail.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void j() {
        int i = AnonymousClass7.b[this.d.ordinal()];
        if (i == 1) {
            jcf.bEz_(this.p, nsf.h(R$string.accessibility_last_week));
            jcf.bEz_(this.q, nsf.h(R$string.accessibility_next_week));
        } else if (i == 2) {
            jcf.bEz_(this.p, nsf.h(R$string.accessibility_last_month));
            jcf.bEz_(this.q, nsf.h(R$string.accessibility_next_month));
        } else if (i == 3) {
            jcf.bEz_(this.p, nsf.h(R$string.accessibility_last_year));
            jcf.bEz_(this.q, nsf.h(R$string.accessibility_next_year));
        } else {
            ReleaseLogUtil.e("BarChartCommonSection", "datainfos is null");
        }
    }

    private void g() {
        if (this.s == null) {
            this.s = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.4
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (BarChartCommonSection.this.au == null) {
                        return;
                    }
                    int left = BarChartCommonSection.this.au.getLeft();
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) BarChartCommonSection.this.g.getLayoutParams();
                    if (left != layoutParams.getMarginStart()) {
                        layoutParams.setMarginStart(left);
                        BarChartCommonSection.this.g.setLayoutParams(layoutParams);
                    }
                    BarChartCommonSection barChartCommonSection = BarChartCommonSection.this;
                    int ahv_ = barChartCommonSection.ahv_(barChartCommonSection.e, 0);
                    BarChartCommonSection barChartCommonSection2 = BarChartCommonSection.this;
                    int ahv_2 = barChartCommonSection2.ahv_(barChartCommonSection2.aj, ahv_);
                    BarChartCommonSection barChartCommonSection3 = BarChartCommonSection.this;
                    int ahv_3 = barChartCommonSection3.ahv_(barChartCommonSection3.ay, ahv_2);
                    BarChartCommonSection barChartCommonSection4 = BarChartCommonSection.this;
                    int ahv_4 = barChartCommonSection4.ahv_(barChartCommonSection4.bj, ahv_3);
                    BarChartCommonSection barChartCommonSection5 = BarChartCommonSection.this;
                    int ahv_5 = barChartCommonSection5.ahv_(barChartCommonSection5.ao, ahv_4);
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) BarChartCommonSection.this.bk.getLayoutParams();
                    if (layoutParams2.width != ahv_5) {
                        layoutParams2.width = ahv_5;
                        BarChartCommonSection.this.bk.setLayoutParams(layoutParams2);
                    }
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) BarChartCommonSection.this.bn.getLayoutParams();
                    if (layoutParams3.width != ahv_5) {
                        layoutParams3.width = ahv_5;
                        BarChartCommonSection.this.bn.setLayoutParams(layoutParams3);
                    }
                }
            };
            this.au.getViewTreeObserver().addOnGlobalLayoutListener(this.s);
        }
        ObserverManagerUtil.d(this.aq, "SLEEP_BASE_BAR_DREAM_VISIBILITY");
        ObserverManagerUtil.d(this.as, "SLEEP_AVERAGE_ICON_OFFSET");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int ahv_(LinearLayout linearLayout, int i) {
        return Math.max(linearLayout.getWidth(), i);
    }

    private void b() {
        ArrayList arrayList = new ArrayList(3);
        CoreSleepBarChartView coreSleepBarChartView = new CoreSleepBarChartView(this.ab);
        this.x = coreSleepBarChartView;
        coreSleepBarChartView.setLayerType(1, null);
        arrayList.add(0, this.x);
        this.ac = (HealthViewPager) nsy.cMd_(this.bs, R.id.calorie_day_detail_viewpager);
        ntq<View> ntqVar = new ntq<>(arrayList);
        this.aw = ntqVar;
        this.ac.setAdapter(ntqVar);
        this.ac.setIsScroll(false);
        this.h = false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("BarChartCommonSection", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("BarChartCommonSection", "no need to bind");
            return;
        }
        Object obj = hashMap.get("BAR_COMMON_START_TIME");
        if (obj instanceof Long) {
            this.bf = ((Long) obj).longValue();
        }
        LogUtil.b("BarChartCommonSection", "start to set mChartHolder");
        this.bh = (HwHealthBarScrollChartHolder) nru.c(hashMap, "BAR_COMMON_CHART_HOLDER", HwHealthBarScrollChartHolder.class, null);
        LogUtil.b("BarChartCommonSection", "start to set mDefaultStartTime");
        this.bf = ((Long) nru.c(hashMap, "BAR_COMMON_START_TIME", Long.class, 0L)).longValue();
        this.y = (BarChartRangeShowCallback) nru.c(hashMap, "BAR_COMMON_RANGE_SHOW_CALL_BACK", BarChartRangeShowCallback.class, null);
        this.u = (ChartMarkChangeCallback) nru.c(hashMap, "BAR_COMMON_MARK_CHANGE_CALL_BACK", ChartMarkChangeCallback.class, null);
        this.d = (DataInfos) nru.c(hashMap, "BAR_COMMON_DATA_INFO", DataInfos.class, null);
        LogUtil.b("BarChartCommonSection", "start to set ChartMarkTextCallback");
        this.ad = (ChartMarkTextCallback) nru.c(hashMap, "BAR_COMMON_MARK_TEXT_CALL_BACK", ChartMarkTextCallback.class, null);
        long longValue = ((Long) nru.c(hashMap, "BAR_COMMON_REFLESH_TIME", Long.class, 0L)).longValue();
        if (this.bd != longValue) {
            LogUtil.b("BarChartCommonSection", "start to set BAR_COMMON_REFLESH_TIME" + longValue);
            this.x.reflesh(longValue);
            this.bd = longValue;
        }
        LogUtil.b("BarChartCommonSection", "start to set BAR_COMMON_CALENDER_EVENT");
        setSectionText(hashMap);
        setTextSrcOrVisibility(hashMap);
        setBarChartStatus(nru.d((Map) hashMap, "BAR_COMMON_JUMP_TO_MONTH_TIME", 0));
        c();
        j();
    }

    private void c() {
        if (this.j) {
            f();
            if (this.y == null || this.u == null || this.ad == null || this.av == null || this.at == null || this.ax == null) {
                return;
            }
            o();
            this.j = false;
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", "1");
        gge.e(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), hashMap);
        if (view.getId() == R.id.fitness_detail_time_date_tv) {
            this.ar.onClick("BAR_CHART_CALENDAR_CLICK_EVENT");
        } else if (view.getId() == R.id.hw_health_fitness_data_origin_icon) {
            this.ar.onClick("BAR_COMMON_HELP_ICON_LISTENER");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        this.bh.addDataLayer((HwHealthBarScrollChartHolder) this.x, this.d);
        if (this.x.acquireScrollAdapter() != null) {
            this.x.reflesh(this.bf);
        }
        this.x.setMarkerViewPosition(null);
    }

    private void o() {
        LogUtil.b("BarChartCommonSection", "setBarChartListeners");
        this.x.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.5
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public void onRangeShow(int i, int i2) {
                LogUtil.b("BarChartCommonSection", "addOnXRangeSet", Integer.valueOf(i), "  ", Integer.valueOf(i2));
                if (jdl.d(TimeUnit.MINUTES.toMillis(i), TimeUnit.MINUTES.toMillis(i2))) {
                    BarChartCommonSection.this.c(i);
                }
                BarChartCommonSection.this.e(i, i2);
                BarChartCommonSection.this.y.onRangeChange(i, i2);
                BarChartCommonSection.this.z.setText(BarChartCommonSection.this.x.formatRangeText(i, i2));
            }
        });
        this.x.setOnBarChartViewDataChangedListener(new CoreSleepBarChartView.OnBarChartViewDataChangedListener() { // from class: eez
            @Override // com.huawei.health.knit.section.chart.CoreSleepBarChartView.OnBarChartViewDataChangedListener
            public final void onBarChartViewDataChangedListener(float f, float f2) {
                BarChartCommonSection.this.e(f, f2);
            }
        });
        l();
        m();
        this.x.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.2
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
                BarChartCommonSection.this.h();
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
                BarChartCommonSection.this.h();
            }
        });
    }

    public /* synthetic */ void e(float f, float f2) {
        LogUtil.b("BarChartCommonSection", "setOnBarChartViewDataChangedListener time", Float.valueOf(f));
        this.u.onMarkChange(f, f2);
    }

    private void setBarChartStatus(int i) {
        CoreSleepBarChartView coreSleepBarChartView = this.x;
        if (coreSleepBarChartView == null || i == 0 || i == this.bv || coreSleepBarChartView.getData() == null) {
            return;
        }
        LogUtil.b("BarChartCommonSection", "start to BAR_COMMON_JUMP_TO_MONTH_TIME" + i);
        LogUtil.b("debugt", "BAR_COMMON_JUMP_TO_MONTH_TIME mDataInfos is " + this.d);
        this.x.blinkToUnixTime(i);
        this.x.highlightDefValue(-1, false);
        this.bv = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        CoreSleepBarChartView coreSleepBarChartView = this.x;
        if (coreSleepBarChartView == null) {
            health.compact.a.util.LogUtil.c("BarChartCommonSection", "refreshBtnImage chart is null");
            return;
        }
        if (coreSleepBarChartView.canScrollOlderPager()) {
            this.ae.setVisibility(0);
            this.ae.setClickable(true);
            this.ag.set(true);
        } else {
            this.ae.setVisibility(4);
            this.ae.setClickable(false);
            this.ag.set(false);
        }
        if (this.x.canScrollNewerPager()) {
            this.bc.setVisibility(0);
            this.bc.setClickable(true);
            this.ah.set(true);
        } else {
            this.bc.setVisibility(4);
            this.bc.setClickable(false);
            this.ah.set(false);
        }
    }

    private void a() {
        if (LanguageUtil.bc(this.ab)) {
            this.p.setImageResource(R.drawable._2131430338_res_0x7f0b0bc2);
            this.q.setImageResource(R.drawable._2131430150_res_0x7f0b0b06);
        } else {
            this.p.setImageResource(R.drawable._2131430150_res_0x7f0b0b06);
            this.q.setImageResource(R.drawable._2131430338_res_0x7f0b0bc2);
        }
        this.ae.setVisibility(0);
        this.bc.setVisibility(4);
        if (LanguageUtil.bc(this.ab)) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMarginStart((int) een.e(2.0f));
            this.am.setLayoutParams(layoutParams);
            this.am.setTextSize(1, 6.0f);
            een.aha_(this.aj, this.ak);
            een.aha_(this.ay, this.an);
            een.aha_(this.bj, this.al);
            een.aha_(this.ao, this.ai);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (this.v == null) {
            this.v = new HealthCalendar();
        }
        this.v = this.v.transformFromCalendar(calendar);
    }

    public void e(int i, int i2) {
        CoreSleepBarChartView coreSleepBarChartView = this.x;
        if (coreSleepBarChartView == null) {
            return;
        }
        String formatRangeText = coreSleepBarChartView.formatRangeText(i, i2);
        long j = i * 60000;
        d(formatRangeText, DateFormatUtil.b(j, DateFormatUtil.DateFormatType.DATE_FORMAT_WEEK), jdl.ac(j));
    }

    private void d(String str, String str2, boolean z) {
        if (z) {
            this.q.setVisibility(4);
        } else {
            this.q.setVisibility(0);
        }
    }

    private void setSectionText(HashMap<String, Object> hashMap) {
        this.ar = (OnClickSectionListener) nru.c(hashMap, "BAR_COMMON_CALENDER_EVENT", OnClickSectionListener.class, null);
        if (((SpannableString) nru.c(hashMap, "BAR_BED_TIME_VALUE_TEXT", SpannableString.class, new SpannableString(""))).equals(new SpannableString("")) || this.d == DataInfos.CoreSleepYearDetail) {
            nsy.cMA_(this.bw, 8);
            nsy.cMA_(this.f2615a, 8);
            nsy.cMA_(this.c, 8);
            nsy.cMr_(this.be, (CharSequence) nru.c(hashMap, "BAR_TIME_VALUE_TEXT", SpannableString.class, new SpannableString("")));
        } else {
            nsy.cMA_(this.bw, 0);
            nsy.cMA_(this.f2615a, 0);
            nsy.cMA_(this.c, 0);
            nsy.cMr_(this.be, (CharSequence) nru.c(hashMap, "BAR_BED_TIME_VALUE_TEXT", SpannableString.class, new SpannableString("")));
            nsy.cMr_(this.w, (CharSequence) nru.c(hashMap, "BAR_TIME_VALUE_TEXT", SpannableString.class, new SpannableString("")));
            nsy.cMr_(this.f2615a, this.ab.getString(R$string.IDS_manual_sleep_bed_time));
            nsy.cMr_(this.c, this.ab.getString(R$string.IDS_manual_sleep_sleep_time));
        }
        nsy.cMr_(this.am, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_ONE", this.ab.getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_deepsleep)));
        nsy.cMr_(this.ak, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_TWO", this.ab.getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep)));
        nsy.cMr_(this.an, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_THREE", this.ab.getString(R$string.IDS_fitness_core_sleep_rem_sleep)));
        nsy.cMr_(this.al, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_FOUR", this.ab.getString(R$string.IDS_details_sleep_sleep_latency)));
        nsy.cMr_(this.ai, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_FIVE", this.ab.getString(R$string.IDS_fitness_core_sleep_noontime_sleep)));
        nsy.cMr_(this.bm, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_SIX", this.ab.getString(R$string.IDS_manual_sleep_bed)));
        nsy.cMr_(this.bo, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_SEVEN", this.ab.getString(R$string.IDS_manual_sleep_sleep)));
        nsy.cMr_(this.bi, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_SIX", this.ab.getString(R$string.IDS_manual_sleep_bed)));
        nsy.cMr_(this.br, nru.b(hashMap, "BAR_COMMON_LEGEND_TEXT_SEVEN", this.ab.getString(R$string.IDS_manual_sleep_sleep)));
    }

    private void setTextSrcOrVisibility(HashMap<String, Object> hashMap) {
        nsy.cMp_(this.n, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_ONE", 0));
        nsy.cMp_(this.r, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_TWO", 0));
        nsy.cMp_(this.t, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_THREE", 0));
        nsy.cMp_(this.i, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_FOUR", 0));
        nsy.cMp_(this.f, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_FIVE", 0));
        nsy.cMp_(this.m, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_SIX", 0));
        nsy.cMp_(this.o, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_SIX", 0));
        nsy.cMp_(this.l, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_SEVEN", 0));
        nsy.cMp_(this.k, nru.d((Map) hashMap, "BAR_COMMON_LEGEND_SEVEN", 0));
        nsy.cMj_(this.aa, nru.cKj_(hashMap, "BAR_CHART_HELP_ICON", null));
        nsy.cMA_(this.aa, nru.d((Map) hashMap, "BAR_CHART_HELP_ICON_VISIBILITY", 8));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.aa.getLayoutParams();
        layoutParams.topMargin = ((int) this.ap) - this.aa.getHeight();
        this.aa.setLayoutParams(layoutParams);
        this.av = (ClickChartDataCallback) nru.c(hashMap, "BAR_CHART_MID_DATA_CLICK_EVENT", ClickChartDataCallback.class, null);
        int d = nru.d((Map) hashMap, "BAR_COMMON_PROCESS_TEXT_VISIBILITY", 4);
        if (this.bb != null && this.bp != d) {
            LogUtil.b("BarChartCommonSection", "start to BAR_COMMON_PROCESS_TEXT_VISIBILITY");
            this.bb.setVisibility(d);
            this.bp = d;
        }
        this.at = (OnClickSectionListener) nru.c(hashMap, "BAR_COMMON_LEFT_ARROW_LISTENER", OnClickSectionListener.class, null);
        this.ax = (OnClickSectionListener) nru.c(hashMap, "BAR_COMMON_RIGHT_ARROW_LISTENER", OnClickSectionListener.class, null);
        boolean d2 = nru.d((Map) hashMap, "BAR_COMMON_BOTTOM_LINE", false);
        if (this.x != null) {
            LogUtil.b("BarChartCommonSection", "start to BAR_COMMON_BOTTOM_LINE");
            this.x.b(d2);
        }
    }

    private void l() {
        this.x.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.10
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public void onTextChanged(String str, List<HwHealthMarkerView.a> list) {
                LogUtil.b("BarChartCommonSection", "setOnMarkViewTextNotify", str);
                BarChartCommonSection.this.ad.onMarkTextChange(str, list, (int) BarChartCommonSection.this.x.fetchMarkViewMinuteValue());
                if (BarChartCommonSection.this.d == DataInfos.CoreSleepYearDetail) {
                    str = String.format(BarChartCommonSection.this.ab.getString(R$string.IDS_year_sleep_daily_average), str);
                }
                BarChartCommonSection.this.az.setText(str);
                BarChartCommonSection.this.x.queryMarkerViewTimeMills();
                BarChartCommonSection.this.be.setTextColor(BarChartCommonSection.this.ab.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                BarChartCommonSection.this.w.setTextColor(BarChartCommonSection.this.ab.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            }
        });
    }

    private void m() {
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BarChartCommonSection.this.ag.get()) {
                    BarChartCommonSection.this.at.onClick("BAR_COMMON_LEFT_ARROW_LISTENER");
                    CoreSleepBarChartView coreSleepBarChartView = BarChartCommonSection.this.x;
                    CoreSleepBarChartView coreSleepBarChartView2 = BarChartCommonSection.this.x;
                    Objects.requireNonNull(coreSleepBarChartView2);
                    coreSleepBarChartView.scrollOnePageOlder(new eex(this, coreSleepBarChartView2));
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.BarChartCommonSection.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BarChartCommonSection.this.ah.get()) {
                    BarChartCommonSection.this.ax.onClick("BAR_COMMON_RIGHT_ARROW_LISTENER");
                    CoreSleepBarChartView coreSleepBarChartView = BarChartCommonSection.this.x;
                    CoreSleepBarChartView coreSleepBarChartView2 = BarChartCommonSection.this.x;
                    Objects.requireNonNull(coreSleepBarChartView2);
                    coreSleepBarChartView.scrollOnePageNewer(new eey(this, coreSleepBarChartView2));
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void clear() {
        LogUtil.a("BarChartCommonSection", "clear");
        super.clear();
        ObserverManagerUtil.e(this.aq, "SLEEP_BASE_BAR_DREAM_VISIBILITY");
        ObserverManagerUtil.e(this.as, "SLEEP_AVERAGE_ICON_OFFSET");
        LinearLayout linearLayout = this.au;
        if (linearLayout != null) {
            linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this.s);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "BarChartCommonSection";
    }
}
