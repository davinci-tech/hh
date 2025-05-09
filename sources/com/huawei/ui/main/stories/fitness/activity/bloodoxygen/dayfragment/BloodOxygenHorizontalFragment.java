package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.knit.section.chart.BloodOxygenLineChart;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseMvpFragment;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalFragment;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.chart.BloodOxygenHorizontalLineChart;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity.HorizontalBloodOxygenDayActivity;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import defpackage.dpg;
import defpackage.ixx;
import defpackage.jec;
import defpackage.koq;
import defpackage.nom;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.ntq;
import defpackage.pjw;
import defpackage.pjx;
import defpackage.pjy;
import defpackage.pkb;
import defpackage.qrp;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.slf4j.Marker;

/* loaded from: classes9.dex */
public class BloodOxygenHorizontalFragment extends CommonBaseMvpFragment<BloodOxygenDayDetailFragmentView, pjw> implements BloodOxygenDayDetailFragmentView, View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9751a;
    private long aa;
    private HealthTextView ab;
    private HealthTextView ac;
    private CustomTitleBar ad;
    private ImageView b;
    private int c;
    private LinearLayout d;
    private HorizontalBloodOxygenDayActivity e;
    private BloodOxygenHorizontalLineChart f;
    private HealthViewPager g;
    private List<Integer> h = null;
    private ArrayList<View> i;
    private ntq<View> j;
    private HealthCalendar k;
    private Context l;
    private pkb m;
    private ImageView n;
    private ImageView o;
    private boolean p;
    private boolean q;
    private RelativeLayout r;
    private LinearLayout s;
    private RelativeLayout t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthTextView w;
    private HealthTextView x;
    private HealthTextView y;
    private HealthTextView z;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i) {
        return i == 18;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseFragment
    public int getLayoutId() {
        return R.layout.horizontal_fragment_blood_oxygen_day_detail;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void setBloodOxygenData(List<HiHealthData> list) {
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void setBloodOxygenLatest(String str, long j) {
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void setBloodOxygenMaxAndMin(String str, String str2) {
    }

    public BloodOxygenHorizontalFragment() {
        d(this.l, AnalyticsValue.BLOOD_OXYGEN_PAGE_CLICK.value(), "3");
        g();
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseFragment
    public void initView(View view) {
        if (getActivity() instanceof HorizontalBloodOxygenDayActivity) {
            this.e = (HorizontalBloodOxygenDayActivity) getActivity();
        }
        HorizontalBloodOxygenDayActivity horizontalBloodOxygenDayActivity = this.e;
        if (horizontalBloodOxygenDayActivity == null) {
            LogUtil.b("BloodOxygenHorizontalFragment", "mActivity is null");
            return;
        }
        Intent intent = horizontalBloodOxygenDayActivity.getIntent();
        if (intent == null) {
            LogUtil.h("BloodOxygenHorizontalFragment", "the mActivity intent is null,pls check");
            this.e.finish();
            return;
        }
        this.aa = intent.getLongExtra(ObserveredClassifiedView.JUMP_TIME_ID, System.currentTimeMillis());
        this.c = intent.getIntExtra("altitude_icon_status", 0);
        this.l = getActivity();
        this.o = (ImageView) view.findViewById(R.id.image_up_arrow_left);
        this.n = (ImageView) view.findViewById(R.id.image_up_arrow_right);
        this.z = (HealthTextView) view.findViewById(R.id.cursortime);
        this.ab = (HealthTextView) view.findViewById(R.id.cursorValue);
        this.u = (HealthTextView) view.findViewById(R.id.cursor_step);
        this.ac = (HealthTextView) view.findViewById(R.id.fitness_detail_time_date_tv);
        this.b = (ImageView) view.findViewById(R.id.altitude_switch_btn);
        this.ac.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("calendar", BloodOxygenHorizontalFragment.this.k);
                bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(47204, new int[]{2107}));
                bundle.putBoolean("isSetGrayUnmarkedDate", true);
                HealthCalendarActivity.cxo_(BloodOxygenHorizontalFragment.this, bundle);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        this.p = true;
        this.g = (HealthViewPager) view.findViewById(R.id.bloodoxygen_day_detail_viewpager);
        CustomTitleBar customTitleBar = (CustomTitleBar) view.findViewById(R.id.fitness_detail_titlebar);
        this.ad = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_hw_health_blood_oxygen));
        this.w = (HealthTextView) view.findViewById(R.id.blood_oxygen_measure_range_1);
        this.y = (HealthTextView) view.findViewById(R.id.blood_oxygen_measure_range_2);
        this.x = (HealthTextView) view.findViewById(R.id.blood_oxygen_measure_range_3);
        this.v = (HealthTextView) view.findViewById(R.id.blood_oxygen_measure_range_4);
        this.d = (LinearLayout) view.findViewById(R.id.blood_oxygen_altitude_legend);
        this.f9751a = (HealthTextView) view.findViewById(R.id.blood_oxygen_measure_range_4);
        this.s = (LinearLayout) view.findViewById(R.id.interval_legend);
        this.t = (RelativeLayout) view.findViewById(R.id.data_bar_layout);
        this.r = (RelativeLayout) view.findViewById(R.id.data_detail_layout);
        this.o.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.f9751a.setText(R$string.IDS_hw_health_blood_oxygen_elevation);
        b();
        setLiftAndRightImage();
        initViewPager();
        initChart();
        if (nsn.c() >= 1.5f) {
            this.ac.setTextSize(1, 21.0f);
            this.z.setTextSize(1, 21.0f);
            this.ab.setTextSize(1, 57.0f);
            this.u.setTextSize(1, 21.0f);
            this.w.setTextSize(1, 18.0f);
            this.y.setTextSize(1, 18.0f);
            this.x.setTextSize(1, 18.0f);
            this.v.setTextSize(1, 18.0f);
        }
    }

    private void b() {
        ImageView imageView = this.b;
        if (imageView == null) {
            return;
        }
        int i = this.c;
        if (i != 0) {
            if (i == 1) {
                imageView.setVisibility(8);
                this.d.setVisibility(8);
                this.u.setVisibility(8);
            }
        } else if ("true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS"))) {
            this.q = true;
            this.b.setVisibility(0);
            this.b.setImageResource(R.drawable._2131429945_res_0x7f0b0a39);
            this.d.setVisibility(0);
            this.u.setVisibility(0);
        } else {
            this.q = false;
            this.b.setVisibility(0);
            this.b.setImageResource(R.drawable._2131429944_res_0x7f0b0a38);
            this.d.setVisibility(8);
            this.u.setVisibility(8);
        }
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BloodOxygenHorizontalFragment.this.q) {
                    BloodOxygenHorizontalFragment.this.e();
                } else {
                    BloodOxygenHorizontalFragment.this.a();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        d(this.l, AnalyticsValue.BLOOD_OXYGEN_PAGE_CLICK.value(), "1");
        c("true");
        this.q = true;
        this.b.setImageResource(R.drawable._2131429945_res_0x7f0b0a39);
        this.d.setVisibility(0);
        this.u.setVisibility(0);
        e(true);
        ObserverManagerUtil.c("BLOOD_AND_ALTITUDE_DATA", true);
    }

    private void c(String str) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS", str, new StorageParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        d(this.l, AnalyticsValue.BLOOD_OXYGEN_PAGE_CLICK.value(), "2");
        c("false");
        this.q = false;
        this.b.setImageResource(R.drawable._2131429944_res_0x7f0b0a38);
        this.d.setVisibility(8);
        this.u.setVisibility(8);
        e(false);
        ObserverManagerUtil.c("BLOOD_AND_ALTITUDE_DATA", false);
    }

    private void e(boolean z) {
        if (z) {
            this.f.setNewRenderer(DataInfos.BloodOxygenAltitudeDayHorizontal, this.l);
        } else {
            this.f.setNewRenderer(DataInfos.BloodOxygenDayHorizontal, this.l);
        }
        this.f.setWillNotDraw(false);
        HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.f.acquireScrollAdapter();
        acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
        this.f.setMarkerViewPosition(null);
        this.f.refresh();
        this.f.zoomToCenter(0.01f, 0.01f);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseFragment
    public void initData() {
        ((pjw) this.mPresenter).initPageParams();
        ((pjw) this.mPresenter).initBloodOxygenInterval();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseMvpFragment
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public pjw createPresenter() {
        return new pjw();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.o) {
            this.k.setDay(r0.getDay() - 1);
            i();
        } else if (view == this.n) {
            HealthCalendar healthCalendar = this.k;
            healthCalendar.setDay(healthCalendar.getDay() + 1);
            i();
        } else {
            LogUtil.h("BloodOxygenHorizontalFragment", "click view unknow");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void i() {
        this.f.reflesh(this.k.transformCalendar().getTimeInMillis());
        long timeInMillis = this.k.transformCalendar().getTimeInMillis();
        setDayAndWeek(nsj.d(getContext(), timeInMillis), dpg.m(timeInMillis), jec.ab(new Date(timeInMillis)), timeInMillis);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void setLiftAndRightImage() {
        if (LanguageUtil.bc(getContext())) {
            this.o.setBackgroundResource(R.mipmap._2131820906_res_0x7f11016a);
            this.n.setBackgroundResource(R.mipmap._2131820905_res_0x7f110169);
        } else {
            this.o.setBackgroundResource(R.mipmap._2131820905_res_0x7f110169);
            this.n.setBackgroundResource(R.mipmap._2131820906_res_0x7f11016a);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void initViewPager() {
        this.i = new ArrayList<>(16);
        this.j = new ntq<>(this.i);
        this.g.setIsCompatibleScrollView(true);
        this.g.setIsScroll(false);
        this.g.setAdapter(this.j);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void initChart() {
        this.m = new pkb(this.l.getApplicationContext(), DataInfos.BloodOxygenDayHorizontal, (BloodOxygenDayDetailFragmentPresenter) this.mPresenter);
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS");
        if (this.f == null || !this.p) {
            this.p = false;
            this.f = new BloodOxygenHorizontalLineChart(this.l, DataInfos.BloodOxygenDayHorizontal);
            if ("true".equals(b2)) {
                BloodOxygenHorizontalLineChart bloodOxygenHorizontalLineChart = new BloodOxygenHorizontalLineChart(this.l.getApplicationContext(), DataInfos.BloodOxygenAltitudeDayHorizontal);
                this.f = bloodOxygenHorizontalLineChart;
                bloodOxygenHorizontalLineChart.setNewRenderer(DataInfos.BloodOxygenAltitudeDayHorizontal, this.l);
            } else {
                BloodOxygenHorizontalLineChart bloodOxygenHorizontalLineChart2 = new BloodOxygenHorizontalLineChart(this.l.getApplicationContext(), DataInfos.BloodOxygenDayHorizontal);
                this.f = bloodOxygenHorizontalLineChart2;
                bloodOxygenHorizontalLineChart2.setNewRenderer(DataInfos.BloodOxygenDayHorizontal, this.l);
            }
        }
        this.f.setLayerType(1, null);
        d();
        this.i.add(this.f);
        this.m.addDataLayer((pkb) this.f, DataInfos.BloodOxygenDayHorizontal);
        if (this.aa > 0 && this.f.acquireScrollAdapter() != null) {
            this.p = false;
            int f = nom.f(nom.a(this.aa));
            BloodOxygenHorizontalLineChart bloodOxygenHorizontalLineChart3 = this.f;
            bloodOxygenHorizontalLineChart3.setShowRange(f, bloodOxygenHorizontalLineChart3.acquireScrollAdapter().acquireRange());
            this.k = qrp.a(this.k, f);
        }
        this.j.notifyDataSetChanged();
        this.f.addOnXRangeSet(new HwHealthBaseScrollBarLineChart.OnXRangeSet() { // from class: pjv
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnXRangeSet
            public final void onRangeShow(int i, int i2) {
                BloodOxygenHorizontalFragment.this.d(i, i2);
            }
        });
        this.f.setOnMarkViewTextNotify(new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: pju
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public final void onTextChanged(String str, List list) {
                BloodOxygenHorizontalFragment.this.b(str, list);
            }
        });
    }

    public /* synthetic */ void d(int i, int i2) {
        if (this.f.isAnimating()) {
            return;
        }
        ((pjw) this.mPresenter).notifyData(i, i2);
    }

    public /* synthetic */ void b(String str, List list) {
        ((pjw) this.mPresenter).notifySourceAndTime(str, list);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void leftArrowClick() {
        BloodOxygenHorizontalLineChart bloodOxygenHorizontalLineChart = this.f;
        Objects.requireNonNull(bloodOxygenHorizontalLineChart);
        bloodOxygenHorizontalLineChart.scrollOnePageOlder(new pjy(this, bloodOxygenHorizontalLineChart));
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void rightArrowClick() {
        BloodOxygenHorizontalLineChart bloodOxygenHorizontalLineChart = this.f;
        Objects.requireNonNull(bloodOxygenHorizontalLineChart);
        bloodOxygenHorizontalLineChart.scrollOnePageNewer(new pjx(this, bloodOxygenHorizontalLineChart));
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void setDayAndWeek(String str, String str2, boolean z, long j) {
        this.aa = j;
        if (LanguageUtil.bb(this.l) || LanguageUtil.r(this.l) || LanguageUtil.d(this.l)) {
            this.ac.setText(str2);
        } else {
            this.ac.setText(str);
        }
        this.k = qrp.a(this.k, nom.h((int) this.f.fetchMarkViewMinuteValue()));
        if (z) {
            this.n.setVisibility(8);
        } else {
            this.n.setVisibility(0);
        }
        this.o.setVisibility(0);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void notifyNumerical(String str, String str2, String str3) {
        SpannableString spannableString;
        if ("--".equals(str2) || "0".equals(str2)) {
            spannableString = new SpannableString("--");
        } else {
            String e = UnitUtil.e(CommonUtil.m(BaseApplication.getActivity(), str2), 2, 0);
            if (nsn.c() >= 1.5f) {
                spannableString = new SpannableString(e);
            } else {
                spannableString = UnitUtil.bCR_(BaseApplication.getContext(), "[\\d]", e, R.style.health_text_chart_emphasize, R.style.health_text_chart_emphasize_small);
            }
        }
        if ("".equals(str3)) {
            str3 = "";
        }
        this.ab.setText(spannableString);
        this.z.setText(str);
        this.u.setText(str3);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public void setBloodOxygenInterval(List<String> list) {
        this.w.setText(String.format(Locale.ENGLISH, this.l.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_lower_than), list.get(0)));
        this.y.setText(String.format(Locale.ENGLISH, this.l.getString(R$string.IDS_press_auto_monitor_relax_range), list.get(1), list.get(2)));
        this.x.setText(String.format(Locale.ENGLISH, this.l.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_greater_than), list.get(3)));
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public BloodOxygenLineChart getBloodOxygenLineChart() {
        return this.f;
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView
    public pkb getBloodOxygenLineChartHolder() {
        return this.m;
    }

    private void d() {
        this.f.setPagerNoMoreListener(new HwHealthBaseScrollBarLineChart.PagerNoMoreListener() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalFragment.2
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyNewerPager(boolean z) {
            }

            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.PagerNoMoreListener
            public void notifyOlderPager(boolean z) {
            }
        });
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseMvpFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        BloodOxygenHorizontalLineChart bloodOxygenHorizontalLineChart = this.f;
        if (bloodOxygenHorizontalLineChart != null) {
            bloodOxygenHorizontalLineChart.setWillNotDraw(false);
            HwHealthBaseScrollBarLineChart.ScrollAdapterInterface acquireScrollAdapter = this.f.acquireScrollAdapter();
            acquireScrollAdapter.setFlag(acquireScrollAdapter.getFlag() | 1);
            this.f.setMarkerViewPosition(null);
            this.f.refresh();
            return;
        }
        LogUtil.a("BloodOxygenHorizontalFragment", "refreshUi mBloodOxygenineChart is null");
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        initViewPager();
        initChart();
    }

    private void g() {
        LogUtil.a("BloodOxygenHorizontalFragment", "enter subscribeBloodOxygenData");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(18);
        HiHealthNativeApi.a(this.l).subscribeHiHealthData(arrayList, new b("BloodOxygenHorizontalFragment", this));
    }

    private void f() {
        String str = "BloodOxygenHorizontalFragment";
        LogUtil.a("BloodOxygenHorizontalFragment", "unSubscribeBloodOxygenData");
        if (koq.c(this.h)) {
            HiHealthNativeApi.a(this.l).unSubscribeHiHealthData(this.h, new a(str, "unSubscribeBloodOxygenData, isSuccess :"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<Integer> list) {
        LogUtil.a("BloodOxygenHorizontalFragment", "subscribeBloodOxygenData, onResult");
        if (koq.c(list)) {
            LogUtil.a("BloodOxygenHorizontalFragment", "registerBloodOxygenListener success");
            this.h = list;
        }
    }

    public static class b implements HiSubscribeListener {

        /* renamed from: a, reason: collision with root package name */
        private String f9754a;
        private WeakReference<BloodOxygenHorizontalFragment> d;

        private b(String str, BloodOxygenHorizontalFragment bloodOxygenHorizontalFragment) {
            this.f9754a = str;
            this.d = new WeakReference<>(bloodOxygenHorizontalFragment);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            BloodOxygenHorizontalFragment bloodOxygenHorizontalFragment;
            WeakReference<BloodOxygenHorizontalFragment> weakReference = this.d;
            if (weakReference == null || (bloodOxygenHorizontalFragment = weakReference.get()) == null) {
                return;
            }
            bloodOxygenHorizontalFragment.a(list);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            final BloodOxygenHorizontalFragment bloodOxygenHorizontalFragment;
            if (hiHealthData != null) {
                LogUtil.a(this.f9754a, "onChange, type = ", Integer.valueOf(i), ", newValue = ", hiHealthData.toString());
            } else {
                LogUtil.a(this.f9754a, "onChange, type = ", Integer.valueOf(i));
            }
            WeakReference<BloodOxygenHorizontalFragment> weakReference = this.d;
            if (weakReference == null || (bloodOxygenHorizontalFragment = weakReference.get()) == null || !bloodOxygenHorizontalFragment.a(i)) {
                return;
            }
            Objects.requireNonNull(bloodOxygenHorizontalFragment);
            HandlerExecutor.a(new Runnable() { // from class: pjz
                @Override // java.lang.Runnable
                public final void run() {
                    BloodOxygenHorizontalFragment.this.h();
                }
            });
        }
    }

    static class a implements HiUnSubscribeListener {

        /* renamed from: a, reason: collision with root package name */
        private String f9753a;
        private String c;

        private a(String str, String str2) {
            this.c = str;
            this.f9753a = str2;
        }

        @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
        public void onResult(boolean z) {
            LogUtil.a(this.c, this.f9753a, Boolean.valueOf(z));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1 || intent == null) {
            return;
        }
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (serializableExtra instanceof HealthCalendar) {
            HealthCalendar healthCalendar = (HealthCalendar) serializableExtra;
            this.k = healthCalendar;
            this.f.reflesh(healthCalendar.transformCalendar().getTimeInMillis());
        }
    }

    private void d(Context context, String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(context, str, hashMap, 0);
        LogUtil.a("BloodOxygenHorizontalFragment", "BICollect", str + Marker.ANY_NON_NULL_MARKER + hashMap.get("type"));
    }
}
