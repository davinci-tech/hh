package com.huawei.health.knit.section.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.CoreSleepDayDetailView;
import com.huawei.health.knit.section.view.BarChartCustomSection;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.HarmonyOsTypefaceSpan;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ecu;
import defpackage.gge;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.ntq;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class BarChartCustomSection extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f2617a;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private ImageView af;
    private ImageView ag;
    private boolean ah;
    private LinearLayout ai;
    private HealthTextView aj;
    private ImageView ak;
    private HealthTextView al;
    private ImageView am;
    private HealthTextView an;
    private ImageView ao;
    private ImageView ap;
    private HealthTextView aq;
    private HealthTextView ar;
    private ImageView as;
    private LinearLayout at;
    private HealthTextView au;
    private HealthTextView av;
    private int aw;
    private HealthProgressBar ax;
    private int ay;
    private HealthViewPager.OnViewPagerTouchEvent az;
    private int b;
    private String ba;
    private LinearLayout bb;
    private String bc;
    private LinearLayout bd;
    private LinearLayout be;
    private HealthTextView bf;
    private HealthTextView bg;
    private long bh;
    private int bi;
    private HealthTextView bj;
    private HealthTextView bk;
    private LinearLayout bl;
    private long bm;
    private List<ecu> bn;
    private HealthTextView bo;
    private HealthTextView bp;
    private LinearLayout bq;
    private ImageView br;
    private boolean bs;
    private CoreSleepDayDetailView bt;
    private List<ecu> bu;
    private ArrayList<CoreSleepDayDetailView> bv;
    private boolean bw;
    private HealthTextView bx;
    private View.OnClickListener by;
    private Typeface bz;
    private boolean c;
    private HealthTextView ca;
    private double cb;
    private View ce;
    private boolean d;
    private OnClickSectionListener e;
    private OnClickSectionListener f;
    private HealthViewPager g;
    private RelativeLayout h;
    private Context i;
    private LinearLayout j;
    private int k;
    private String l;
    private String m;
    private Date n;
    private HealthTextView o;
    private HealthTextView p;
    private LinearLayout q;
    private HealthTextView r;
    private int s;
    private LinearLayout t;
    private View u;
    private int v;
    private ImageView w;
    private ImageView x;
    private HealthTextView y;
    private int z;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected boolean isSupportSafeRegion() {
        return false;
    }

    public BarChartCustomSection(Context context) {
        super(context);
        this.bn = new ArrayList(16);
        this.cb = 0.0d;
        this.bv = new ArrayList<>(16);
        this.n = new Date(System.currentTimeMillis());
        this.ae = true;
        this.ac = false;
        this.s = 0;
        this.aa = false;
        this.ad = false;
        this.bm = 0L;
        this.bh = 0L;
    }

    public BarChartCustomSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.bn = new ArrayList(16);
        this.cb = 0.0d;
        this.bv = new ArrayList<>(16);
        this.n = new Date(System.currentTimeMillis());
        this.ae = true;
        this.ac = false;
        this.s = 0;
        this.aa = false;
        this.ad = false;
        this.bm = 0L;
        this.bh = 0L;
    }

    public BarChartCustomSection(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bn = new ArrayList(16);
        this.cb = 0.0d;
        this.bv = new ArrayList<>(16);
        this.n = new Date(System.currentTimeMillis());
        this.ae = true;
        this.ac = false;
        this.s = 0;
        this.aa = false;
        this.ad = false;
        this.bm = 0L;
        this.bh = 0L;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.i = context;
        g();
        e();
        d();
        k();
        return this.ce;
    }

    private void k() {
        ObserverManagerUtil.d(new Observer() { // from class: com.huawei.health.knit.section.view.BarChartCustomSection.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (objArr == null) {
                    return;
                }
                Object obj = objArr[0];
                if (obj instanceof Integer) {
                    int intValue = ((Integer) obj).intValue();
                    if (intValue == 100) {
                        BarChartCustomSection.this.a((Boolean) true);
                        return;
                    }
                    if (intValue == 101) {
                        if (BarChartCustomSection.this.bk != null) {
                            BarChartCustomSection.this.bk.setVisibility(8);
                        }
                        if (BarChartCustomSection.this.at != null) {
                            BarChartCustomSection.this.at.setVisibility(8);
                            return;
                        }
                        return;
                    }
                    BarChartCustomSection.this.d(intValue);
                }
            }
        }, "SLEEP_SYNC_RATE");
    }

    private void g() {
        if (this.ce == null) {
            LogUtil.b("BarChartCustomSection", "initView mainView is null, start to inflate");
            this.ce = LayoutInflater.from(this.i).inflate(R.layout.section_custom_bar_layout, (ViewGroup) this, false);
        }
        h();
        this.af.setOnClickListener(this);
        this.br.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.w.setOnClickListener(this);
        if (LanguageUtil.bc(this.i)) {
            this.af.setImageResource(R.drawable._2131430338_res_0x7f0b0bc2);
            this.br.setImageResource(R.drawable._2131430150_res_0x7f0b0b06);
        } else {
            this.af.setImageResource(R.drawable._2131430150_res_0x7f0b0b06);
            this.br.setImageResource(R.drawable._2131430338_res_0x7f0b0bc2);
        }
        f();
        i();
        c();
    }

    private void e() {
        View cMd_ = nsy.cMd_(this.ce, R.id.go_to_ecg_tips_layout);
        this.u = cMd_;
        ((HealthTextView) nsy.cMd_(cMd_, R.id.toast_no_notice_tv)).setVisibility(8);
        this.bx = (HealthTextView) nsy.cMd_(this.u, R.id.toast_try_tv);
        ((HealthTextView) nsy.cMd_(this.u, R.id.toast_cancel_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.BarChartCustomSection.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != null && BarChartCustomSection.this.u != null) {
                    BarChartCustomSection.this.u.setVisibility(8);
                    if (BarChartCustomSection.this.by != null) {
                        BarChartCustomSection.this.by.onClick(view);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ca = (HealthTextView) nsy.cMd_(this.u, R.id.toast_title_tv);
    }

    private void d() {
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMd_(this.ce, R.id.core_sleep_btn_tips_layout);
        this.h = relativeLayout;
        if (relativeLayout == null) {
            LogUtil.a("BarChartCustomSection", "initCoreSleepTips, mCoreSleepBtnTipsLayout is null");
        }
    }

    private void c() {
        LogUtil.b("debugn", "initLoadingView");
        this.ax.setLayerType(1, null);
        this.bk.setVisibility(4);
        this.t.setVisibility(4);
        this.y.setVisibility(4);
        this.be.setVisibility(4);
        this.bd.setVisibility(4);
        this.at.setVisibility(0);
        this.av.setText(R$string.IDS_getting_file);
        this.bj.setVisibility(8);
    }

    private void i() {
        this.bt.setColorType(0);
        this.bt.setDayDetailView(true);
        LogUtil.b("BarChartCustomSection", "initViewPagerData mSleepDataList: ", this.bn.toString());
    }

    private void f() {
        if (this.bt == null) {
            CoreSleepDayDetailView coreSleepDayDetailView = new CoreSleepDayDetailView(this.i);
            this.bt = coreSleepDayDetailView;
            coreSleepDayDetailView.setLayerType(1, null);
            this.bt.setDayDetailView(true);
        }
        this.bv.clear();
        this.bv.add(this.bt);
        this.g.setAdapter(new ntq(this.bv));
        this.g.setIsScroll(false);
        this.g.setOnViewPagerTouchEventListener(new HealthViewPager.OnViewPagerTouchEvent() { // from class: com.huawei.health.knit.section.view.BarChartCustomSection.4
            @Override // com.huawei.ui.commonui.viewpager.HealthViewPager.OnViewPagerTouchEvent
            public void setIsViewTouch(Boolean bool) {
            }

            @Override // com.huawei.ui.commonui.viewpager.HealthViewPager.OnViewPagerTouchEvent
            public void onTouchDown(MotionEvent motionEvent) {
                if (BarChartCustomSection.this.az != null) {
                    BarChartCustomSection.this.az.onTouchDown(motionEvent);
                }
            }

            @Override // com.huawei.ui.commonui.viewpager.HealthViewPager.OnViewPagerTouchEvent
            public void onTouchUp(MotionEvent motionEvent) {
                if (BarChartCustomSection.this.az != null) {
                    BarChartCustomSection.this.az.setIsViewTouch(Boolean.valueOf(BarChartCustomSection.this.bt.e()));
                    BarChartCustomSection.this.az.onTouchUp(motionEvent);
                }
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.b("BarChartCustomSection", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            return;
        }
        setTempDataListAndSleepDataList(hashMap);
        CoreSleepDayDetailView.OnClickViewDefaultListener onClickViewDefaultListener = (CoreSleepDayDetailView.OnClickViewDefaultListener) nru.c(hashMap, "BAR_CHART_CLICK_EVENT", CoreSleepDayDetailView.OnClickViewDefaultListener.class, null);
        if (onClickViewDefaultListener != null) {
            this.bt.setonClickViewDefaultListener(onClickViewDefaultListener);
        }
        this.f = (OnClickSectionListener) nru.c(hashMap, "BAR_CHART_ARROW_COMMON_EVENT", OnClickSectionListener.class, null);
        this.by = (View.OnClickListener) nru.c(hashMap, "TEXT_CANCEL_CLICK", View.OnClickListener.class, null);
        this.m = nru.b(hashMap, "BAR_CHART_PERIOD_STRING", "");
        this.l = nru.b(hashMap, "BAR_CHART_TYPE_STRING", "");
        this.k = nru.d((Map) hashMap, "BAR_CHART_TIME_INT", 0);
        this.z = nru.d((Map) hashMap, "SLEEP_HYBRID_NIGHT_TIME", 0);
        this.v = nru.d((Map) hashMap, "SLEEP_HYBRID_NOON_TIME", 0);
        this.d = nru.d((Map) hashMap, "BAR_CHART_DATA_VALID", false);
        this.aw = nru.d((Map) hashMap, "BAR_CHART_TOTAL_MANUAL_BED_TIME", 0);
        this.ay = nru.d((Map) hashMap, "BAR_CHART_TOTAL_MANUAL_SLEEP_TIME", 0);
        this.bc = nru.b(hashMap, "BAR_CHART_TOTAL_MANUAL_BED_TITLE", "");
        this.ba = nru.b(hashMap, "BAR_CHART_TOTAL_MANUAL_SLEEP_TITLE", "");
        this.e = (OnClickSectionListener) nru.c(hashMap, "BAR_CHART_CALENDAR_CLICK_EVENT", OnClickSectionListener.class, null);
        this.az = (HealthViewPager.OnViewPagerTouchEvent) nru.c(hashMap, "BAR_CHART_VIEWPAGER_TOUCH_EVENT", HealthViewPager.OnViewPagerTouchEvent.class, null);
        this.bi = nru.d((Map) hashMap, "BAR_CHART_PROCESS_TEXT_VISIBILITY", 8);
        setVisibilityOrText(hashMap);
        setNoDataRec(hashMap);
        d(hashMap);
        this.ae = nru.d((Map) hashMap, "BAR_CHART_IS_SCIENCE_SLEEP", true);
        Object obj = hashMap.get("BAR_CHART_VALID_DATA");
        if (obj instanceof Double) {
            this.cb = ((Double) obj).doubleValue();
        }
        this.ac = nru.d((Map) hashMap, "BAR_CHART_IS_TYPE", false);
        this.aa = nru.d((Map) hashMap, "BAR_COMMON_IS_PHONE", false);
        this.ad = nru.d((Map) hashMap, "BAR_COMMON_IS_MANUAL", false);
        LogUtil.a("BarChartCustomSection", "mIsPhone is", Boolean.valueOf(this.aa));
        long d = nru.d((Map) hashMap, "BAR_COMMON_PHONE_START_TIME", 0L);
        this.bm = d;
        LogUtil.a("BarChartCustomSection", "mPhoneStartTime is", Long.valueOf(d));
        long d2 = nru.d((Map) hashMap, "BAR_COMMON_PHONE_END_TIME", 0L);
        this.bh = d2;
        LogUtil.a("BarChartCustomSection", "mPhoneEndTime is", Long.valueOf(d2));
        this.n = (Date) nru.c(hashMap, "BAR_CHART_CURRENT_DAY", Date.class, null);
        this.b = nru.d((Map) hashMap, "BAR_CHART_COLOR_TYPE", 0);
        setViewUtilsData(hashMap);
        this.s = nru.d((Map) hashMap, "SLEEP_DAY_BAR_CHART_DEVICE_ID", 0);
        this.s = nru.d((Map) hashMap, "SLEEP_DAY_BAR_CHART_DEVICE_ID", 0);
        this.ah = nru.d((Map) hashMap, "BAR_SCIENCE_IS_SUPPORT_BEDTIME", false);
        if (this.ad) {
            nsy.cMm_(this.ag, (Drawable) nru.c(hashMap, "BAR_CHART_LEGEND_SIX_DRAWABLE", Drawable.class, null));
            nsy.cMm_(this.ap, (Drawable) nru.c(hashMap, "BAR_CHART_LEGEND_SEVEN_DRAWABLE", Drawable.class, null));
            nsy.cMr_(this.aj, nru.b(hashMap, "BAR_CHART_LEGEND_SIX_TEXT", ""));
            nsy.cMr_(this.ar, nru.b(hashMap, "BAR_CHART_LEGEND_SEVEN_TEXT", ""));
            this.ab = nru.d((Map) hashMap, "BAR_COMMON_IS_MANUAL_INCOMPLETE", false);
        }
        d(this.m, this.l, this.k);
        j();
    }

    private void n() {
        int i = this.z;
        int i2 = this.v;
        if (i != i2 && i != 0 && i2 != 0) {
            this.y.setVisibility(0);
            String string = this.i.getString(R$string.IDS_fitness_total_sleep_data_title);
            String e = e(this.z);
            String string2 = this.i.getString(R$string.IDS_fitness_core_sleep_noontime_sleep);
            String e2 = e(this.v);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(string);
            stringBuffer.append(" ");
            stringBuffer.append(e);
            stringBuffer.append(" | ");
            stringBuffer.append(string2);
            stringBuffer.append(" ");
            stringBuffer.append(e2);
            this.y.setText(stringBuffer.toString());
            return;
        }
        this.y.setVisibility(4);
    }

    private String e(int i) {
        int i2 = i / 60;
        int i3 = i % 60;
        double d = i3;
        String e = UnitUtil.e(d, 1, 0);
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(d, 1, 0));
        if (i2 == 0) {
            return i3 == 0 ? quantityString.replace(e, "--") : quantityString;
        }
        return BaseApplication.getContext().getString(com.huawei.ui.commonui.R$string.IDS_two_parts, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(i2, 1, 0)), quantityString);
    }

    private void d(HashMap<String, Object> hashMap) {
        LogUtil.a("BarChartCustomSection", "coreSleepBtnTipsLayoutResponse");
        Object obj = hashMap.get("CORE_SLEEP_BTN_TIPS");
        LogUtil.a("BarChartCustomSection", "callback: ", obj);
        if (!(obj instanceof IBaseResponseCallback)) {
            LogUtil.b("BarChartCustomSection", "callback type is invalid");
            return;
        }
        RelativeLayout relativeLayout = this.h;
        if (relativeLayout == null) {
            LogUtil.b("BarChartCustomSection", "mCoreSleepBtnTipsLayout is null");
        } else {
            ((IBaseResponseCallback) obj).d(0, relativeLayout);
        }
    }

    private void setVisibilityOrText(HashMap<String, Object> hashMap) {
        nsy.cMr_(this.o, nru.b(hashMap, "BAR_CHART_DATE_TEXT", ""));
        nsy.cMm_(this.w, (Drawable) nru.c(hashMap, "BAR_CHART_HELP_ICON", Drawable.class, null));
        nsy.cMA_(this.w, nru.d((Map) hashMap, "BAR_CHART_HELP_ICON_VISIBILITY", 8));
        nsy.cMr_(this.bx, nru.b(hashMap, "ECG_TEXT_TRY_TEXT", this.i.getString(R$string.IDS_device_bt_right_btn_info)));
        nsy.cMo_(this.bx, (OnClickSectionListener) nru.c(hashMap, "ECG_TEXT_TRY_CLICK_EVENT", OnClickSectionListener.class, null), "ECG_TEXT_TRY_CLICK_EVENT");
        nsy.cMr_(this.ca, nru.b(hashMap, "ECG_TEXT_CONTENT_TEXT", ""));
        nsy.cMA_(this.u, nru.d((Map) hashMap, "ECG_TIPS_LAYOUT_VISIBILITY", 8));
        nsy.cMA_(this.j, nru.d((Map) hashMap, "BAR_CHART_CORE_LEGEND_VISIBILITY", 0));
        nsy.cMA_(this.bl, nru.d((Map) hashMap, "BAR_CHART_REM_LEGEND_VISIBILITY", 0));
        nsy.cMA_(this.bb, nru.d((Map) hashMap, "BAR_CHART_NOON_LEGEND_VISIBILITY", 4));
    }

    private void setViewUtilsData(HashMap<String, Object> hashMap) {
        nsy.cMm_(this.ak, (Drawable) nru.c(hashMap, "BAR_CHART_LEGEND_ONE_DRAWABLE", Drawable.class, null));
        nsy.cMm_(this.ao, (Drawable) nru.c(hashMap, "BAR_CHART_LEGEND_TWO_DRAWABLE", Drawable.class, null));
        nsy.cMm_(this.as, (Drawable) nru.c(hashMap, "BAR_CHART_LEGEND_THREE_DRAWABLE", Drawable.class, null));
        nsy.cMm_(this.ag, (Drawable) nru.c(hashMap, "BAR_CHART_LEGEND_FOUR_DRAWABLE", Drawable.class, null));
        nsy.cMm_(this.am, (Drawable) nru.c(hashMap, "BAR_CHART_LEGEND_FIVE_DRAWABLE", Drawable.class, null));
        nsy.cMm_(this.ap, (Drawable) nru.c(hashMap, "BAR_CHART_LEGEND_FIVE_DRAWABLE", Drawable.class, null));
        nsy.cMr_(this.al, nru.b(hashMap, "BAR_CHART_LEGEND_ONE_TEXT", ""));
        nsy.cMr_(this.au, nru.b(hashMap, "BAR_CHART_LEGEND_TWO_TEXT", ""));
        nsy.cMr_(this.aq, nru.b(hashMap, "BAR_CHART_LEGEND_THREE_TEXT", ""));
        nsy.cMr_(this.aj, nru.b(hashMap, "BAR_CHART_LEGEND_FOUR_TEXT", ""));
        nsy.cMr_(this.an, nru.b(hashMap, "BAR_CHART_LEGEND_FIVE_TEXT", ""));
        nsy.cMr_(this.ar, nru.b(hashMap, "BAR_CHART_LEGEND_FIVE_TEXT", ""));
        nsy.cMr_(this.bk, nru.b(hashMap, "BAR_CHART_SYNC_TEXT", this.i.getResources().getString(R$string.IDS_fitness_core_sleep_no_sleep_data)));
    }

    private void setNoDataRec(HashMap<String, Object> hashMap) {
        Object obj = hashMap.get("BAR_CHART_IS_TODAY");
        if (obj instanceof Boolean) {
            d(((Boolean) obj).booleanValue());
        }
        Object obj2 = hashMap.get("BAR_CHART_IS_DISMISS_LOADING");
        if (obj2 instanceof Boolean) {
            c(((Boolean) obj2).booleanValue());
        }
        Object obj3 = hashMap.get("BAR_CHART_IS_NO_DATA_REC");
        if (obj3 instanceof Boolean) {
            e(((Boolean) obj3).booleanValue());
        }
        Object obj4 = hashMap.get("BAR_CHART_IS_DATA_SYNCING");
        if (obj4 instanceof Integer) {
            d(((Integer) obj4).intValue());
        }
    }

    private void j() {
        int i;
        if (this.bt != null && this.c) {
            LogUtil.a("BarChartCustomSection", "prepareRefreshChart misPhone ", Boolean.valueOf(this.aa), " is manual ", Boolean.valueOf(this.ad));
            this.bt.setColorType(this.b);
            this.bt.isSupportBedTime(this.ah);
            this.bt.refreshFitnessDataList(b(), this.ae, this.cb, this.ac, this.n, this.aa, this.ad, this.ab);
        }
        HealthTextView healthTextView = this.bk;
        if (healthTextView == null || (i = this.bi) == Integer.MAX_VALUE) {
            return;
        }
        healthTextView.setVisibility(i);
        if (this.bi == 0) {
            nsy.cMA_(this.y, 4);
            nsy.cMA_(this.be, 4);
            nsy.cMA_(this.bd, 4);
        }
    }

    private List<ecu> b() {
        if (!this.ah) {
            LogUtil.a("BarChartCustomSection", "mIsSupportBedTime is false");
            return this.bn;
        }
        ArrayList arrayList = new ArrayList();
        for (ecu ecuVar : this.bn) {
            if (ecuVar.d() != 69) {
                arrayList.add(ecuVar);
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.b("BarChartCustomSection", "mSleepDataList is not enough");
            return this.bn;
        }
        ArrayList arrayList2 = new ArrayList();
        ecu ecuVar2 = new ecu();
        int i = 0;
        while (i < arrayList.size()) {
            ecu ecuVar3 = (ecu) arrayList.get(i);
            if (i == 0 || i == arrayList.size() - 1) {
                if (ecuVar3.d() == 67) {
                    arrayList2.add(ecuVar3);
                }
            } else {
                if (ecuVar2.d() == 67 && ecuVar2.a() != ecuVar3.c()) {
                    arrayList2.add(ecuVar2);
                }
                if (ecuVar3.d() == 67 && ecuVar2.a() != ecuVar3.c()) {
                    arrayList2.add(ecuVar3);
                }
            }
            i++;
            ecuVar2 = ecuVar3;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.remove((ecu) it.next());
        }
        LogUtil.a("BarChartCustomSection", "delete ears ", arrayList2.toString());
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Boolean bool) {
        if (bool.booleanValue()) {
            this.bj.setVisibility(0);
            this.bj.setText(UnitUtil.e(100.0d, 2, 0));
            this.at.postDelayed(new Runnable() { // from class: eff
                @Override // java.lang.Runnable
                public final void run() {
                    BarChartCustomSection.this.a();
                }
            }, 500L);
        }
    }

    public /* synthetic */ void a() {
        this.at.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        this.bk.setVisibility(8);
        this.bj.setVisibility(0);
        this.at.setVisibility(0);
        this.y.setVisibility(4);
        this.be.setVisibility(4);
        this.bd.setVisibility(4);
        this.av.setText(R$string.IDS_pull_to_refresh_refreshing_label);
        this.bj.setText(UnitUtil.e(i, 2, 0));
    }

    private void e(boolean z) {
        if (z) {
            this.bg.setText("");
            this.p.setText("");
            this.t.setVisibility(4);
            this.y.setVisibility(4);
            this.be.setVisibility(4);
            this.bd.setVisibility(4);
        }
    }

    private void c(boolean z) {
        if (z && this.bw != z) {
            LogUtil.b("dbugn", "dealLoading TEXT_VISIBILITY" + z);
            HealthTextView healthTextView = this.bk;
            if (healthTextView != null) {
                healthTextView.setVisibility(8);
            }
            LinearLayout linearLayout = this.at;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
        } else if (this.bw != z) {
            LogUtil.b("dbugn", "11dealLoading TEXT_VISIBILITY" + z);
            HealthTextView healthTextView2 = this.bk;
            if (healthTextView2 != null) {
                healthTextView2.setVisibility(8);
            }
            LinearLayout linearLayout2 = this.at;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(0);
            }
            HealthTextView healthTextView3 = this.av;
            if (healthTextView3 != null) {
                healthTextView3.setText(R$string.IDS_getting_file);
            }
            HealthTextView healthTextView4 = this.bj;
            if (healthTextView4 != null) {
                healthTextView4.setVisibility(8);
            }
            nsy.cMA_(this.y, 4);
            nsy.cMA_(this.be, 4);
            nsy.cMA_(this.bd, 4);
        } else {
            LogUtil.b("BarChartCustomSection", "dealLoading wrong");
        }
        this.bw = z;
    }

    private void d(boolean z) {
        if (z && this.bs != z) {
            this.af.setVisibility(0);
            this.br.setVisibility(4);
            this.bq.setClickable(false);
        } else if (this.bs != z) {
            this.af.setVisibility(0);
            this.br.setVisibility(0);
            this.bq.setClickable(true);
            this.at.setVisibility(0);
            this.bw = false;
            this.bj.setVisibility(8);
            this.av.setText(this.i.getString(R$string.IDS_getting_file));
        } else {
            LogUtil.b("BarChartCustomSection", "dealIsToday wrong");
        }
        this.bs = z;
    }

    private void d(String str, String str2, int i) {
        this.be.setVisibility(0);
        this.bd.setVisibility(8);
        this.r.setVisibility(4);
        if (this.ad) {
            setManualData(i);
            return;
        }
        if (i == 0) {
            this.be.setVisibility(4);
            this.bd.setVisibility(4);
            return;
        }
        this.bg.setVisibility(0);
        HealthTextView healthTextView = this.bg;
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        healthTextView.setText(str);
        HealthTextView healthTextView2 = this.p;
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        healthTextView2.setText(str2);
        setSleepDataToCommonSleep(i);
        n();
    }

    private void setManualData(int i) {
        if (i == 0) {
            this.be.setVisibility(4);
            this.bd.setVisibility(4);
            return;
        }
        this.be.setVisibility(0);
        this.bd.setVisibility(0);
        this.q.setVisibility(0);
        this.y.setVisibility(4);
        if (!TextUtils.isEmpty(this.bc)) {
            this.bg.setText(this.bc);
            this.bg.setVisibility(0);
        } else {
            this.be.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.ba)) {
            this.bf.setText(this.ba);
            this.bf.setVisibility(0);
        } else {
            this.bd.setVisibility(8);
        }
        int i2 = this.aw;
        if (i2 != 0) {
            this.bo.setText(ahB_(i2));
            this.bo.setVisibility(0);
        } else {
            this.be.setVisibility(8);
        }
        int i3 = this.ay;
        if (i3 != 0) {
            this.bp.setText(ahB_(i3));
            this.bp.setVisibility(0);
            return;
        }
        this.bd.setVisibility(8);
    }

    private void setSleepDataToCommonSleep(int i) {
        if (i < 0) {
            this.bo.setVisibility(4);
            return;
        }
        this.bo.setText(ahB_(i));
        this.bo.setVisibility(0);
    }

    private SpannableString ahB_(int i) {
        String str;
        this.bz = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        int i2 = i / 60;
        int i3 = i % 60;
        double d = i3;
        String e = UnitUtil.e(d, 1, 0);
        String str2 = "";
        try {
            str = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(d, 1, 0));
        } catch (IllegalFormatConversionException e2) {
            LogUtil.h("BarChartCustomSection", "getTimeSpannableString() strMin IllegalFormatConversionException = ", e2);
            str = "";
        }
        int i4 = R.dimen._2131363007_res_0x7f0a04bf;
        if (i2 == 0) {
            if (i3 == 0) {
                str = str.replace(e, "--");
                this.t.setVisibility(4);
            } else {
                this.t.setVisibility(0);
            }
            SpannableString spannableString = new SpannableString(str);
            int indexOf = i3 != 0 ? str.indexOf(e) : str.indexOf("--");
            int length = i3 != 0 ? e.length() : 2;
            if (indexOf < 0) {
                return spannableString;
            }
            ahC_(spannableString, indexOf, length);
            Resources resources = getResources();
            if (!nsn.m()) {
                i4 = R.dimen._2131363006_res_0x7f0a04be;
            }
            spannableString.setSpan(new AbsoluteSizeSpan(resources.getDimensionPixelSize(i4)), indexOf, length + indexOf, 17);
            return spannableString;
        }
        this.t.setVisibility(0);
        try {
            str2 = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(i2, 1, 0));
        } catch (IllegalFormatConversionException e3) {
            LogUtil.h("BarChartCustomSection", "getTimeSpannableString() strHour IllegalFormatConversionException = ", e3);
        }
        String string = BaseApplication.getContext().getString(com.huawei.ui.commonui.R$string.IDS_two_parts, str2, str);
        String e4 = UnitUtil.e(i2, 1, 0);
        int lastIndexOf = string.lastIndexOf(e);
        int indexOf2 = string.indexOf(e4);
        SpannableString spannableString2 = new SpannableString(string);
        if (lastIndexOf >= 0) {
            ahC_(spannableString2, lastIndexOf, e.length());
            spannableString2.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(nsn.m() ? R.dimen._2131363007_res_0x7f0a04bf : R.dimen._2131363006_res_0x7f0a04be)), lastIndexOf, e.length() + lastIndexOf, 17);
        }
        if (indexOf2 >= 0) {
            ahC_(spannableString2, indexOf2, e4.length());
            Resources resources2 = getResources();
            if (!nsn.m()) {
                i4 = R.dimen._2131363006_res_0x7f0a04be;
            }
            spannableString2.setSpan(new AbsoluteSizeSpan(resources2.getDimensionPixelSize(i4)), indexOf2, e4.length() + indexOf2, 17);
        }
        return spannableString2;
    }

    private void ahC_(SpannableString spannableString, int i, int i2) {
        if (this.bz == null) {
            LogUtil.c("BarChartCustomSection", "custom typeface is null.,return");
        } else if (Build.VERSION.SDK_INT >= 28) {
            spannableString.setSpan(new HarmonyOsTypefaceSpan(this.bz), i, i2 + i, 17);
        } else {
            spannableString.setSpan(new TypefaceSpan(Constants.FONT), i, i2 + i, 17);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", "1");
        gge.e(AnalyticsValue.SLEEP_DETAILS_CLICK_21300046.value(), hashMap);
        if (view == null) {
            LogUtil.b("BarChartCustomSection", "onClick View null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.left_arrow_iv) {
            this.y.setVisibility(4);
            this.be.setVisibility(4);
            this.bd.setVisibility(4);
            OnClickSectionListener onClickSectionListener = this.f;
            if (onClickSectionListener != null) {
                onClickSectionListener.onClick("BAR_LEFT_ARROW_CLICK_EVENT");
            }
        } else if (view.getId() == R.id.right_arrow_iv) {
            this.y.setVisibility(4);
            this.be.setVisibility(4);
            this.bd.setVisibility(4);
            OnClickSectionListener onClickSectionListener2 = this.f;
            if (onClickSectionListener2 != null) {
                onClickSectionListener2.onClick("BAR_RIGHT_ARROW_CLICK_EVENT");
            }
        } else if (view.getId() == R.id.fitness_detail_time_date_tv) {
            OnClickSectionListener onClickSectionListener3 = this.e;
            if (onClickSectionListener3 != null) {
                onClickSectionListener3.onClick("BAR_CHART_CALENDAR_CLICK_EVENT");
            }
        } else if (view.getId() == R.id.hw_health_fitness_data_origin_icon) {
            OnClickSectionListener onClickSectionListener4 = this.f;
            if (onClickSectionListener4 != null) {
                onClickSectionListener4.onClick("BAR_HELP_ICON_CLICK_EVENT");
            }
        } else {
            LogUtil.b("BarChartCustomSection", "view id is invalid");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public List<Bitmap> getShareBitmap() {
        int height = this.f2617a.getHeight();
        int width = getWidth();
        Bitmap createBitmap = Bitmap.createBitmap(width, getHeight(), Bitmap.Config.ARGB_4444);
        draw(new Canvas(createBitmap));
        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, getHeight() - height, width, height);
        createBitmap.recycle();
        return Collections.singletonList(createBitmap2);
    }

    public String getCurrentDayMsg() {
        HealthTextView healthTextView = this.o;
        return healthTextView != null ? healthTextView.getText().toString() : "";
    }

    public int getmDeviceType() {
        return this.s;
    }

    private void h() {
        this.o = (HealthTextView) this.ce.findViewById(R.id.fitness_detail_time_date_tv);
        this.af = (ImageView) this.ce.findViewById(R.id.left_arrow_iv);
        this.ai = (LinearLayout) this.ce.findViewById(R.id.linear_left_arrow_iv);
        this.br = (ImageView) this.ce.findViewById(R.id.right_arrow_iv);
        this.bq = (LinearLayout) this.ce.findViewById(R.id.linear_right_arrow_iv);
        this.bg = (HealthTextView) this.ce.findViewById(R.id.day_time_period);
        this.t = (LinearLayout) this.ce.findViewById(R.id.day_time_duration_time);
        this.be = (LinearLayout) this.ce.findViewById(R.id.time_period_ll_1);
        this.p = (HealthTextView) this.ce.findViewById(R.id.day_time_duration_type);
        this.bo = (HealthTextView) this.ce.findViewById(R.id.common_sleep_sleep_time);
        this.ak = (ImageView) this.ce.findViewById(R.id.legend_one_image);
        this.al = (HealthTextView) this.ce.findViewById(R.id.legend_one_text);
        this.ao = (ImageView) this.ce.findViewById(R.id.legend_two_image);
        this.au = (HealthTextView) this.ce.findViewById(R.id.legend_two_text);
        this.as = (ImageView) this.ce.findViewById(R.id.legend_three_image);
        this.aq = (HealthTextView) this.ce.findViewById(R.id.legend_three_text);
        this.y = (HealthTextView) this.ce.findViewById(R.id.night_and_noon_hybrid_time);
        if (LanguageUtil.br(this.i)) {
            this.aq.setMaxLines(10);
            this.aq.setWidth(nsn.c(this.i.getApplicationContext(), 50.0f));
        }
        this.am = (ImageView) this.ce.findViewById(R.id.legend_four_image);
        this.an = (HealthTextView) this.ce.findViewById(R.id.legend_four_text);
        if (LanguageUtil.bk(this.i)) {
            this.aq.setWidth(nsn.c(this.i.getApplicationContext(), 80.0f));
            this.an.setWidth(nsn.c(this.i.getApplicationContext(), 80.0f));
        }
        if (LanguageUtil.f(this.i)) {
            this.an.setWidth(nsn.c(this.i.getApplicationContext(), 80.0f));
            this.au.setWidth(nsn.c(this.i.getApplicationContext(), 100.0f));
            this.al.setWidth(nsn.c(this.i.getApplicationContext(), 100.0f));
        }
        if (LanguageUtil.k(this.i)) {
            this.an.setWidth(nsn.c(this.i.getApplicationContext(), 80.0f));
        }
        this.ag = (ImageView) this.ce.findViewById(R.id.legend_five_image);
        this.aj = (HealthTextView) this.ce.findViewById(R.id.legend_five_text);
        this.ap = (ImageView) this.ce.findViewById(R.id.legend_six_image);
        this.ar = (HealthTextView) this.ce.findViewById(R.id.legend_six_text);
        this.g = (HealthViewPager) this.ce.findViewById(R.id.fitness_sleep_detail_viewpager);
        this.ax = (HealthProgressBar) this.ce.findViewById(R.id.sleep_loading_iv);
        this.av = (HealthTextView) this.ce.findViewById(R.id.custom_progress_dialog_desc);
        this.bj = (HealthTextView) this.ce.findViewById(R.id.custom_progress_dialog_percent);
        this.bk = (HealthTextView) this.ce.findViewById(R.id.processDialog_title_text);
        this.at = (LinearLayout) this.ce.findViewById(R.id.sleep_loading_layout);
        this.j = (LinearLayout) this.ce.findViewById(R.id.sleep_color_block);
        this.bl = (LinearLayout) this.ce.findViewById(R.id.core_sleep_rem_layout);
        this.bb = (LinearLayout) this.ce.findViewById(R.id.sleep_true_noon_sleep_legend_layout);
        this.f2617a = (RelativeLayout) this.ce.findViewById(R.id.core_sleep_share_image);
        this.w = (ImageView) this.ce.findViewById(R.id.hw_health_fitness_data_origin_icon);
        this.bd = (LinearLayout) this.ce.findViewById(R.id.time_period_ll_2);
        this.bf = (HealthTextView) this.ce.findViewById(R.id.day_time_period_2);
        this.q = (LinearLayout) this.ce.findViewById(R.id.day_time_duration_time_2);
        this.r = (HealthTextView) this.ce.findViewById(R.id.day_time_duration_type_2);
        this.bp = (HealthTextView) this.ce.findViewById(R.id.common_sleep_sleep_time_2);
        this.x = (ImageView) this.ce.findViewById(R.id.hw_health_fitness_data_origin_icon_2);
        jcf.bEz_(this.af, nsf.h(R$string.accessibility_last_day));
        jcf.bEz_(this.ai, nsf.h(R$string.accessibility_last_day));
        jcf.bEz_(this.br, nsf.h(R$string.accessibility_next_day));
        jcf.bEz_(this.bq, nsf.h(R$string.accessibility_next_day));
    }

    private void setTempDataListAndSleepDataList(HashMap<String, Object> hashMap) {
        List<ecu> list = (List) nru.c(hashMap, "BAR_CHART_DATA_LIST", List.class, null);
        if (list != null) {
            boolean b = b(hashMap, list);
            this.c = b;
            LogUtil.a("BarChartCustomSection", "setTempDataListAndSleepDataList isNeedRefresh is ", Boolean.valueOf(b));
            if (this.c) {
                this.bn = list;
            }
            this.bu = new ArrayList(list);
        }
    }

    private boolean b(HashMap<String, Object> hashMap, List<ecu> list) {
        List<ecu> list2 = this.bu;
        return (list2 != null && koq.b(list2, list, true) && nru.d((Map) hashMap, "BAR_CHART_COLOR_TYPE", 0) == this.b && nru.d((Map) hashMap, "BAR_COMMON_IS_PHONE", false) == this.aa && !koq.b(this.bn) && nru.d((Map) hashMap, "BAR_COMMON_IS_MANUAL", false) == this.ad && nru.d((Map) hashMap, "BAR_CHART_IS_SCIENCE_SLEEP", true) == this.ae && nru.d((Map) hashMap, "BAR_CHART_IS_TYPE", false) == this.ac && !nru.d((Map) hashMap, "is_need_refresh_chart", false)) ? false : true;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "BarChartCustomSection";
    }
}
