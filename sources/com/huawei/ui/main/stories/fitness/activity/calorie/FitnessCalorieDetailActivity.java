package com.huawei.ui.main.stories.fitness.activity.calorie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.threecircle.ActiveTipStringUtils;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.ClassType;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.MotionType;
import com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.ActiveTargetActivity;
import com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity;
import com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity;
import com.huawei.ui.main.stories.fitness.views.base.chart.DayProgressBarScrollView;
import com.huawei.ui.main.stories.fitness.views.base.chart.DaySingleViewDataObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ICustomMotionTypeCalculator;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ProportionView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener;
import com.huawei.ui.main.stories.fitness.views.base.chart.utils.UserEvent;
import com.huawei.up.model.UserInfomation;
import defpackage.gso;
import defpackage.jcf;
import defpackage.jdl;
import defpackage.koq;
import defpackage.nip;
import defpackage.nix;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.pxp;
import defpackage.rud;
import health.compact.a.CommonUtils;
import health.compact.a.HiDateUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class FitnessCalorieDetailActivity extends BaseStepDetailActivity {
    private DaySingleViewDataObserverView b;
    private int c;
    private DayProgressBarScrollView i;
    private long k;
    private List<Integer> o;
    private int p;
    private final Context e = BaseApplication.e();
    private final AtomicInteger n = new AtomicInteger();
    private final SparseIntArray h = new SparseIntArray();
    private final b s = new b(this);
    private ProportionView j = null;
    private List<ProportionView.ProportionItem> g = new ArrayList();
    private ProportionView.ProportionItem m = null;
    private ProportionView.ProportionItem t = null;
    private ProportionView.ProportionItem d = null;

    /* renamed from: a, reason: collision with root package name */
    private ProportionView.ProportionItem f9769a = null;
    private ProportionView.ProportionItem f = null;
    private LinearLayout q = null;
    private BaseStepDetailActivity.TextShowFormatter l = new BaseStepDetailActivity.TextShowFormatter() { // from class: com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity.5
        @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity.TextShowFormatter
        public String convertFloat2TextShow(float f) {
            if (f < 1.0f && f != 0.0f) {
                return HiDataFilter.DataFilterExpression.LESS_THAN + UnitUtil.e(1.0d, 1, 0);
            }
            return UnitUtil.e(f, 1, 0);
        }
    };

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public int configSubTabStyle() {
        return R.layout.knit_sub_tab_widget_calorie;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(103);
        HiHealthNativeApi.a(this.e).subscribeHiHealthData(arrayList, new d(this));
        jcf.bEj_(this.s);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("FitnessCalorieDetailActivity", "onDestroy");
        jcf.bEu_(this.s);
        if (koq.c(this.o)) {
            HiHealthNativeApi.a(this.e).unSubscribeHiHealthData(this.o, new HiUnSubscribeListener() { // from class: pkx
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("FitnessCalorieDetailActivity", "onDestroy unSubscribeHiHealthData isSuccess ", Boolean.valueOf(z));
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public View getExtensionView() {
        LinearLayout linearLayout = this.q;
        if (linearLayout != null) {
            return linearLayout;
        }
        this.q = new LinearLayout(this);
        this.j = new ProportionView(this);
        this.m = new ProportionView.d(this, Color.argb(255, 250, 168, 154));
        this.t = new ProportionView.c(this, Color.argb(255, 250, 149, UserInfomation.WEIGHT_DEFAULT_ENGLISH));
        this.d = new ProportionView.a(this, Color.argb(255, 248, 129, 109));
        this.f9769a = new ProportionView.b(this, Color.argb(255, 246, 91, 64));
        this.f = new ProportionView.e(this, Color.argb(255, 245, 62, 31));
        this.g.add(this.m);
        this.g.add(this.t);
        this.g.add(this.d);
        this.g.add(this.f9769a);
        this.g.add(this.f);
        this.j.b(this.g);
        this.j.setSubHeaderText(getResources().getString(R$string.IDS_fitness_subheader_title, getResources().getString(R$string.IDS_active_caloric)));
        this.q.setOrientation(1);
        this.q.addView(this.j, -1, -2);
        String b2 = nsf.b(R$string.IDS_heat_introduction_new_1, UnitUtil.e(300.0d, 1, 0), UnitUtil.e(260.0d, 1, 0));
        LayoutInflater layoutInflater = getLayoutInflater();
        if (layoutInflater != null) {
            ((HealthTextView) layoutInflater.inflate(R.layout.view_what_calorie, (ViewGroup) this.q, true).findViewById(R.id.card_view_what_heat_introduction)).setText(b2);
        }
        return this.q;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initStartArguement(Intent intent) {
        if (intent != null && intent.hasExtra("today_current_colories_total")) {
            this.mCurrentDayValueMinimum = intent.getIntExtra("today_current_colories_total", (int) this.mCurrentDayValueMinimum);
            this.mCurrentDayValueMinimum = (float) UnitUtil.a(this.mCurrentDayValueMinimum / 1000.0f, 0);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initTitleBar() {
        this.mTitleBar.setTitleText(getString(R$string.IDS_active_caloric));
        ArrayList<String> arrayList = new ArrayList<>(1);
        arrayList.add(getString(R$string.IDS_privacy_all_data));
        setTitleBarRightButton(this, arrayList);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initActivityData() {
        nip.d("900200007", new a(this));
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartListener() {
        this.mClassifiedViewList.b(new IOnDataShowListener() { // from class: com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity.4
            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onUserEvent(UserEvent userEvent) {
            }

            @Override // com.huawei.ui.main.stories.fitness.views.base.chart.utils.IOnDataShowListener
            public void onDataShowChanged(DataInfos dataInfos, int i, int i2, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
                if (hwHealthBaseBarLineChart instanceof HwHealthBaseScrollBarLineChart) {
                    FitnessCalorieDetailActivity.this.b((HwHealthBaseScrollBarLineChart) hwHealthBaseBarLineChart, dataInfos);
                    FitnessCalorieDetailActivity.this.b(dataInfos, i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, DataInfos dataInfos) {
        ICustomMotionTypeCalculator c2 = this.mChartLayerHolder.c();
        this.t.setDataValue(c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.WALK));
        this.m.setDataValue(c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.RUN));
        this.d.setDataValue(c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.BIKE));
        this.f9769a.setDataValue(c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.CLIMB));
        ProportionView.ProportionItem proportionItem = this.f;
        float calculate = c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.SUM);
        float calculate2 = c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.WALK);
        float calculate3 = c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.RUN);
        proportionItem.setDataValue((((calculate - calculate2) - calculate3) - c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.BIKE)) - c2.calculate(hwHealthBaseScrollBarLineChart, dataInfos, MotionType.CLIMB));
        this.j.b(this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DataInfos dataInfos, long j) {
        if (dataInfos != null && dataInfos.isDayData()) {
            this.b.setTargetLayoutVisibility(0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j * 60000);
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
            long timeInMillis = calendar.getTimeInMillis();
            this.k = timeInMillis;
            if (jdl.ac(timeInMillis)) {
                this.c = 0;
                this.b.setEditTargetVisibility(0);
                int i = this.n.get();
                if (i > 0) {
                    d(i);
                    return;
                } else {
                    nip.d("900200007", new a(this));
                    return;
                }
            }
            this.c = 8;
            this.b.setEditTargetVisibility(8);
            this.b.setTipVisibility(8);
            int i2 = this.h.get(HiDateUtil.c(this.k));
            if (i2 > 0) {
                d(i2);
                return;
            } else {
                nix.b(this.k, "900200007", new c(this));
                return;
            }
        }
        this.b.setTargetLayoutVisibility(8);
        this.b.setTipVisibility(8);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void initChartLayerHolderData(IChartLayerHolder iChartLayerHolder) {
        iChartLayerHolder.spetifiyDataTypeUnit(new IChartLayerHolder.DataTypeFilter() { // from class: com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity.3
            @Override // com.huawei.ui.commonui.linechart.icommon.IChartLayerHolder.DataTypeFilter
            public boolean isAccept(DataInfos dataInfos) {
                return dataInfos.isCaloriesData();
            }
        }, getString(R$string.IDS_band_data_sport_energy_unit));
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public BaseStepDetailActivity.TextShowFormatter getTextShowFormatter() {
        return this.l;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateDayClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateDayClassifiedView = super.onCreateDayClassifiedView(textShowFormatter);
        enableProgressClassifiedViewObserver(onCreateDayClassifiedView);
        ScrollChartObserverView acquireScrollChartObserverView = onCreateDayClassifiedView.acquireScrollChartObserverView();
        if (!(acquireScrollChartObserverView instanceof DaySingleViewDataObserverView)) {
            LogUtil.h("FitnessCalorieDetailActivity", "onCreateDayClassifiedView !(scrollChartObserverView instanceof DoubleViewDataObserverView)");
            return onCreateDayClassifiedView;
        }
        DaySingleViewDataObserverView daySingleViewDataObserverView = (DaySingleViewDataObserverView) acquireScrollChartObserverView;
        this.b = daySingleViewDataObserverView;
        this.mScrollChartObserverView = daySingleViewDataObserverView.a();
        RelativeLayout dvw_ = this.b.dvw_();
        if (dvw_ instanceof DayProgressBarScrollView) {
            this.i = (DayProgressBarScrollView) dvw_;
        }
        if (!(this.mScrollChartObserverView instanceof ScrollChartObserverTotalDataView)) {
            LogUtil.h("FitnessCalorieDetailActivity", "onCreateDayClassifiedView", "!(scrollChartObserverViewCalorie instanceof ScrollChartObserverTotalDataView)");
            return onCreateDayClassifiedView;
        }
        ((ScrollChartObserverTotalDataView) this.mScrollChartObserverView).b(new ScrollChartObserverTotalDataView.b(this.mCurrentDayValueMinimum, 40003, DataInfos.query(getClassType(), DateType.DATE_DAY)));
        return onCreateDayClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateWeekClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateWeekClassifiedView = super.onCreateWeekClassifiedView(textShowFormatter);
        enableClassifiedViewObserver(onCreateWeekClassifiedView, new BaseStepDetailActivity.b(nsf.h(R$string.IDS_user_profile_achieve_sum), getString(R$string.IDS_band_data_sport_energy_unit), nsf.h(R$string.IDS_daily_average), getString(R$string.IDS_band_data_sport_energy_unit)));
        return onCreateWeekClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateMonthClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateMonthClassifiedView = super.onCreateMonthClassifiedView(textShowFormatter);
        enableClassifiedViewObserver(onCreateMonthClassifiedView, new BaseStepDetailActivity.b(nsf.h(R$string.IDS_user_profile_achieve_sum), getString(R$string.IDS_band_data_sport_energy_unit), nsf.h(R$string.IDS_daily_average), getString(R$string.IDS_band_data_sport_energy_unit)));
        return onCreateMonthClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ObserveredClassifiedView onCreateYearClassifiedView(BaseStepDetailActivity.TextShowFormatter textShowFormatter) {
        ObserveredClassifiedView onCreateYearClassifiedView = super.onCreateYearClassifiedView(textShowFormatter);
        enableClassifiedViewObserver((FitnessCalorieDetailActivity) onCreateYearClassifiedView, new HwHealthBaseScrollBarLineChart.DataRatioProvider() { // from class: com.huawei.ui.main.stories.fitness.activity.calorie.FitnessCalorieDetailActivity.1
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.DataRatioProvider
            public float computeRatio(int i) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(i * 60000);
                calendar.set(5, 1);
                calendar.roll(5, -1);
                return calendar.get(5);
            }
        }, new BaseStepDetailActivity.b(nsf.h(R$string.IDS_user_profile_achieve_sum), getString(R$string.IDS_band_data_sport_energy_unit), nsf.h(R$string.IDS_daily_average), getString(R$string.IDS_band_data_sport_energy_unit)));
        return onCreateYearClassifiedView;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public ClassType getClassType() {
        return ClassType.TYPE_CALORIES;
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueMinimum() {
        return this.mScrollChartObserverView.getContentTextView().getText().toString();
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public String getCurrentDayValueUnit() {
        return getString(R$string.IDS_band_data_sport_energy_unit);
    }

    @Override // com.huawei.ui.main.stories.fitness.base.BaseStepDetailActivity
    public void handleTitleBarRightButtonOnClick(int i) {
        LogUtil.a("FitnessCalorieDetailActivity", "PopViewList setOnClick position is ", Integer.valueOf(i));
        if (i == 0) {
            rud.c(111, this);
        } else {
            LogUtil.h("FitnessCalorieDetailActivity", "PopViewList setOnClick position is else");
        }
    }

    public void a(float f, int i) {
        if (this.b == null || f < 0.0f) {
            LogUtil.h("FitnessCalorieDetailActivity", "updateTargetProgress mDayObserverView is null value ", Float.valueOf(f), " target ", Integer.valueOf(i));
            return;
        }
        updateTargetProgress(this.b.getTargetTextView(), (int) UnitUtil.a(f, 0), i, R.plurals._2130903083_res_0x7f03002b);
        this.b.setEditTargetOnClickListener(new View.OnClickListener() { // from class: pku
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessCalorieDetailActivity.this.dqD_(view);
            }
        });
    }

    public /* synthetic */ void dqD_(View view) {
        if (nsn.a(500)) {
            LogUtil.h("FitnessCalorieDetailActivity", "jumpTarget isFastClick");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ActiveTargetActivity.e(this, 2, 4);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void c(float f, int i) {
        DaySingleViewDataObserverView daySingleViewDataObserverView = this.b;
        if (daySingleViewDataObserverView == null || i <= 0 || f < 0.0f) {
            LogUtil.h("FitnessCalorieDetailActivity", "updateTip mDayObserverView ", daySingleViewDataObserverView, " mTodayTarget ", Integer.valueOf(this.p), " target ", Integer.valueOf(i), " value ", Float.valueOf(f));
            return;
        }
        if (!jdl.ac(this.k)) {
            this.b.setTipVisibility(8);
            return;
        }
        int a2 = i - ((int) UnitUtil.a(f, 0));
        if (a2 <= 0) {
            this.b.setTipVisibility(8);
            this.p = i;
        } else {
            if (isStopUpdateTargetTip(f, this.b)) {
                return;
            }
            b(i, a2);
        }
    }

    private void b(int i, int i2) {
        HashMap<String, String> e = ActiveTipStringUtils.e(i2 * 1000, ActiveTipStringUtils.TipType.CALORIES);
        String str = e.get("description_key");
        if (TextUtils.isEmpty(str)) {
            this.b.setTipVisibility(8);
            this.p = i;
            return;
        }
        final int h = CommonUtils.h(e.get("sport_type_key"));
        this.b.setTipVisibility(0);
        this.b.setTipText(str);
        this.b.setTipButton(R.color._2131296434_res_0x7f0900b2, e.get("button_key"), new View.OnClickListener() { // from class: pkz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessCalorieDetailActivity.this.dqC_(h, view);
            }
        });
        if (i == this.p) {
            return;
        }
        this.p = i;
        pxp.b(0, 2, h, ActiveTipStringUtils.TipType.CALORIES);
    }

    public /* synthetic */ void dqC_(int i, View view) {
        pxp.b(1, 2, i, ActiveTipStringUtils.TipType.CALORIES);
        if (gso.e().aTs_(0, gso.e().aTn_(i, -1, -1.0f, -1, 9), null, this, null) == 0) {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i) {
        HandlerExecutor.a(new Runnable() { // from class: pky
            @Override // java.lang.Runnable
            public final void run() {
                FitnessCalorieDetailActivity.this.a(i);
            }
        });
    }

    public /* synthetic */ void a(int i) {
        DayProgressBarScrollView dayProgressBarScrollView = this.i;
        if (dayProgressBarScrollView == null) {
            return;
        }
        if (i <= 0) {
            i = this.p;
        }
        dayProgressBarScrollView.e(i);
    }

    /* loaded from: classes6.dex */
    static class b implements AccessibilityManager.TouchExplorationStateChangeListener {
        private final WeakReference<FitnessCalorieDetailActivity> c;

        b(FitnessCalorieDetailActivity fitnessCalorieDetailActivity) {
            this.c = new WeakReference<>(fitnessCalorieDetailActivity);
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            FitnessCalorieDetailActivity fitnessCalorieDetailActivity = this.c.get();
            if (fitnessCalorieDetailActivity == null || fitnessCalorieDetailActivity.isFinishing() || fitnessCalorieDetailActivity.isDestroyed()) {
                ReleaseLogUtil.d("FitnessCalorieDetailActivity", "TouchExplorationStateChangeListener onTouchExplorationStateChanged activity ", fitnessCalorieDetailActivity);
                return;
            }
            LogUtil.a("FitnessCalorieDetailActivity", "TouchExplorationStateChangeListener onTouchExplorationStateChanged enabled ", Boolean.valueOf(z), " mEditTargetVisibility ", Integer.valueOf(fitnessCalorieDetailActivity.c), " mDayObserverView ", fitnessCalorieDetailActivity.b);
            if (fitnessCalorieDetailActivity.b == null) {
                return;
            }
            fitnessCalorieDetailActivity.b.setEditTargetAccessibilityVisibility(fitnessCalorieDetailActivity.c);
        }
    }

    /* loaded from: classes6.dex */
    static class a implements IBaseResponseCallback {
        private final WeakReference<FitnessCalorieDetailActivity> c;

        a(FitnessCalorieDetailActivity fitnessCalorieDetailActivity) {
            this.c = new WeakReference<>(fitnessCalorieDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (!(obj instanceof Integer)) {
                LogUtil.h("FitnessCalorieDetailActivity", "TargetCallback onResponse object ", obj);
                return;
            }
            FitnessCalorieDetailActivity fitnessCalorieDetailActivity = this.c.get();
            if (fitnessCalorieDetailActivity == null || fitnessCalorieDetailActivity.isFinishing() || fitnessCalorieDetailActivity.isDestroyed()) {
                LogUtil.h("FitnessCalorieDetailActivity", "TargetCallback onResponse activity ", fitnessCalorieDetailActivity);
                return;
            }
            fitnessCalorieDetailActivity.n.set(((Integer) obj).intValue() / 1000);
            fitnessCalorieDetailActivity.d(fitnessCalorieDetailActivity.n.get());
        }
    }

    /* loaded from: classes6.dex */
    static class c implements ResponseCallback<List<HiHealthData>> {
        private final WeakReference<FitnessCalorieDetailActivity> e;

        c(FitnessCalorieDetailActivity fitnessCalorieDetailActivity) {
            this.e = new WeakReference<>(fitnessCalorieDetailActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<HiHealthData> list) {
            if (CollectionUtils.d(list)) {
                LogUtil.h("FitnessCalorieDetailActivity", "HistoryTargetCallback onResponse list ", list);
                return;
            }
            HiHealthData hiHealthData = list.get(0);
            if (hiHealthData == null) {
                LogUtil.h("FitnessCalorieDetailActivity", "HistoryTargetCallback onResponse data is null");
                return;
            }
            FitnessCalorieDetailActivity fitnessCalorieDetailActivity = this.e.get();
            if (fitnessCalorieDetailActivity != null && !fitnessCalorieDetailActivity.isFinishing() && !fitnessCalorieDetailActivity.isDestroyed()) {
                fitnessCalorieDetailActivity.h.append(HiDateUtil.c(hiHealthData.getStartTime()), hiHealthData.getInt("calorieGoalValue") / 1000);
                fitnessCalorieDetailActivity.d(fitnessCalorieDetailActivity.h.get(HiDateUtil.c(fitnessCalorieDetailActivity.k)));
            } else {
                LogUtil.h("FitnessCalorieDetailActivity", "HistoryTargetCallback onResponse activity ", fitnessCalorieDetailActivity);
            }
        }
    }

    /* loaded from: classes6.dex */
    static class d implements HiSubscribeListener {
        private final WeakReference<FitnessCalorieDetailActivity> b;

        d(FitnessCalorieDetailActivity fitnessCalorieDetailActivity) {
            this.b = new WeakReference<>(fitnessCalorieDetailActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (CollectionUtils.d(list)) {
                LogUtil.h("FitnessCalorieDetailActivity", "ConfigGoalCallback onResult successList ", list);
                return;
            }
            FitnessCalorieDetailActivity fitnessCalorieDetailActivity = this.b.get();
            if (fitnessCalorieDetailActivity != null && !fitnessCalorieDetailActivity.isFinishing() && !fitnessCalorieDetailActivity.isDestroyed()) {
                fitnessCalorieDetailActivity.o = list;
            } else {
                LogUtil.h("FitnessCalorieDetailActivity", "ConfigGoalCallback onResult activity ", fitnessCalorieDetailActivity);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (i != 103) {
                LogUtil.h("FitnessCalorieDetailActivity", "ConfigGoalCallback onChange type ", Integer.valueOf(i));
            } else if ("900200007".equals(str)) {
                nip.d("900200007", new a(this.b.get()));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
