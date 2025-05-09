package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.listener.ChartStatisticChangeCallback;
import com.huawei.health.knit.section.listener.OnMarkViewTextNotify;
import com.huawei.health.knit.section.listener.OnXRangeTextCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveHoursBaseBarChartProvider;
import com.huawei.ui.main.stories.fitness.util.chart.StepModuleBarChartHolder;
import defpackage.ggl;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.nom;
import defpackage.noy;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.phw;
import defpackage.qrp;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public abstract class ActiveHoursBaseBarChartProvider extends MajorProvider<phw> {
    private static final Long HALF_HOUR_TIME_MILLIS = 1800000L;
    private static final String TAG = "SCUI_ActiveHoursBaseBarChartProvider";
    private boolean isFirst;
    private Date lastReqDate;
    private phw mActiveHoursData;
    private final Context mAppContext;
    private HealthCalendar mCalendar;
    private a mCalendarSelectResponseCallback;
    private int mCurrentTimes;
    private String mCursorTime;
    private boolean mHasInsertCurTime;
    private boolean mIsCompleted;
    private long mRefreshTimestamp;
    private SectionBean mSectionBean;
    private int mStartTimestamp;
    private StepModuleBarChartHolder mStepModuleBarChartHolder;
    private String mTextDate;

    protected abstract DataInfos getDataInfo();

    protected abstract int getStartTimestamp(long j);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (phw) obj);
    }

    public ActiveHoursBaseBarChartProvider() {
        Context e = BaseApplication.e();
        this.mAppContext = e;
        this.mStartTimestamp = 0;
        this.lastReqDate = new Date();
        this.isFirst = true;
        this.mRefreshTimestamp = ggl.a(System.currentTimeMillis()) + HALF_HOUR_TIME_MILLIS.longValue();
        this.mStartTimestamp = getStartTimestamp(System.currentTimeMillis());
        this.mStepModuleBarChartHolder = new StepModuleBarChartHolder(e);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        this.mCalendarSelectResponseCallback = new a(this);
        this.mSectionBean = sectionBean;
        this.mActiveHoursData = new phw();
        Bundle extra = getExtra();
        if (extra != null) {
            long j = extra.getLong("default_time_millis");
            if (j > 0) {
                long a2 = ggl.a(j) + HALF_HOUR_TIME_MILLIS.longValue();
                this.mRefreshTimestamp = a2;
                this.mStartTimestamp = getStartTimestamp(a2);
            }
        }
        sectionBean.e(this.mActiveHoursData);
        registerObserver();
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, phw phwVar) {
        LogUtil.a(TAG, "enter parseParams data is ", phwVar.toString());
        hashMap.clear();
        hashMap.put("BAR_CHART_DATE_TEXT", this.mTextDate);
        if (getDataInfo().isDayData()) {
            hashMap.put("BAR_CHART_LEGEND_ONE_TEXT", this.mIsCompleted ? new SpannableString(context.getResources().getString(R$string.IDS_completed)) : new SpannableString(context.getResources().getString(R$string.IDS_fitness_undone)));
        } else {
            hashMap.put("BAR_CHART_LEGEND_ONE_TEXT", subTitleValueSpannable(context, context.getResources().getQuantityString(R.plurals.IDS_single_circle_active_target_desc, this.mCurrentTimes, UnitUtil.e(this.mCurrentTimes, 1, 0))));
        }
        hashMap.put("BAR_CHART_PERIOD_STRING", this.mCursorTime);
        hashMap.put("BAR_COMMON_START_TIME", Integer.valueOf(this.mStartTimestamp));
        long j = this.mRefreshTimestamp;
        if (j > 0) {
            hashMap.put("BAR_COMMON_REFLESH_TIME", Long.valueOf(j));
        }
        hashMap.put("BAR_COMMON_CHART_HOLDER", this.mStepModuleBarChartHolder);
        hashMap.put("BAR_DATA_INFOS", getDataInfo());
        setOnXRangeSetCallback(hashMap);
        setOnMarkViewTextNotify(context, hashMap);
        setStatisticChangeCallback(hashMap, phwVar);
        setClickListener(hashMap);
        this.mRefreshTimestamp = 0L;
    }

    private SpannableString subTitleValueSpannable(Context context, String str) {
        return UnitUtil.bCR_(context, "\\d", str, R.style.health_chart_cursor_bar_sub_value, R.style.health_chart_cursor_bar_sub_unit);
    }

    private void setStatisticChangeCallback(HashMap<String, Object> hashMap, final phw phwVar) {
        hashMap.put("BAR_OBSERVER_LABEL_CUMULATIVE_AVERAGE", new ChartStatisticChangeCallback() { // from class: phu
            @Override // com.huawei.health.knit.section.listener.ChartStatisticChangeCallback
            public final void onStatisticChange(int i, int i2) {
                ActiveHoursBaseBarChartProvider.this.m804x591d1a1f(phwVar, i, i2);
            }
        });
    }

    /* renamed from: lambda$setStatisticChangeCallback$0$com-huawei-ui-main-stories-fitness-activity-active-provider-ActiveHoursBaseBarChartProvider, reason: not valid java name */
    public /* synthetic */ void m804x591d1a1f(phw phwVar, int i, int i2) {
        LogUtil.a(TAG, "ChartStatisticChangeCallback cumulativeSum is", Integer.valueOf(i), "dailyAverage is", Integer.valueOf(i2));
        phwVar.b(i);
        phwVar.a(i2);
        notifyMinorProviders(phwVar);
    }

    private void setOnXRangeSetCallback(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_MARK_CHANGE_CALL_BACK", new OnXRangeTextCallback() { // from class: phr
            @Override // com.huawei.health.knit.section.listener.OnXRangeTextCallback
            public final void onRangeShow(int i, int i2, String str) {
                ActiveHoursBaseBarChartProvider.this.m803x91c72fde(i, i2, str);
            }
        });
    }

    /* renamed from: lambda$setOnXRangeSetCallback$1$com-huawei-ui-main-stories-fitness-activity-active-provider-ActiveHoursBaseBarChartProvider, reason: not valid java name */
    public /* synthetic */ void m803x91c72fde(int i, int i2, String str) {
        boolean d;
        LogUtil.a(TAG, "OnXRangeTextCallback startx is", Integer.valueOf(i));
        LogUtil.a(TAG, "OnXRangeTextCallback endx is", Integer.valueOf(i2));
        long j = i;
        long millis = TimeUnit.MINUTES.toMillis(j);
        Date date = new Date(millis);
        long j2 = i2;
        Date date2 = new Date(TimeUnit.MINUTES.toMillis(j2));
        LogUtil.a(TAG, "OnXRangeTextCallback startxDate is", date);
        LogUtil.a(TAG, "OnXRangeTextCallback endxDate is", date2);
        if (getDataInfo() == DataInfos.ActiveHoursYearDetail) {
            d = jdl.i(millis, this.lastReqDate.getTime());
        } else {
            d = jdl.d(millis, this.lastReqDate.getTime());
        }
        if ((isInBegin(date) && !d) || this.isFirst) {
            this.lastReqDate = date;
            LogUtil.a(TAG, "OnXRangeTextCallback startTime is", Long.valueOf(TimeUnit.MINUTES.toMillis(j)));
            this.isFirst = false;
        }
        if (str.equals(this.mTextDate)) {
            return;
        }
        this.mTextDate = str;
        if (jdl.d(TimeUnit.MINUTES.toMillis(j), TimeUnit.MINUTES.toMillis(j2))) {
            this.mCalendar = qrp.a(this.mCalendar, i);
        }
        SectionBean sectionBean = this.mSectionBean;
        sectionBean.e(sectionBean.e());
    }

    private void setOnMarkViewTextNotify(final Context context, HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_MARK_TEXT_CALL_BACK", new OnMarkViewTextNotify() { // from class: phv
            @Override // com.huawei.health.knit.section.listener.OnMarkViewTextNotify
            public final void onTextChanged(String str, List list, float f) {
                ActiveHoursBaseBarChartProvider.this.m802x59c75cd(context, str, list, f);
            }
        });
    }

    /* renamed from: lambda$setOnMarkViewTextNotify$2$com-huawei-ui-main-stories-fitness-activity-active-provider-ActiveHoursBaseBarChartProvider, reason: not valid java name */
    public /* synthetic */ void m802x59c75cd(Context context, String str, List list, float f) {
        if (getDataInfo().isYearData()) {
            this.mCursorTime = getYearTimeRange(str);
        } else {
            this.mCursorTime = str;
        }
        int h = nom.h((int) f);
        this.mCalendar = qrp.a(this.mCalendar, h);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            HwHealthMarkerView.a aVar = (HwHealthMarkerView.a) it.next();
            if (aVar != null) {
                HwHealthBaseEntry hwHealthBaseEntry = aVar.b;
                if (hwHealthBaseEntry instanceof HwHealthBarEntry) {
                    float c = noy.c(((HwHealthBarEntry) hwHealthBaseEntry).acquireModel());
                    this.mIsCompleted = c == 4.0f;
                    this.mCurrentTimes = Math.round(c);
                } else {
                    this.mIsCompleted = false;
                    this.mCurrentTimes = 0;
                }
            }
        }
        setActiveHoursData(context, this.mIsCompleted, this.mCursorTime, TimeUnit.MINUTES.toMillis(h));
        notifyMinorProviders(this.mActiveHoursData);
        this.mSectionBean.e(this.mActiveHoursData);
    }

    private boolean isInBegin(Date date) {
        boolean z = date.getHours() == 0 && date.getMinutes() == 0 && date.getSeconds() == 0;
        if (getDataInfo() != DataInfos.ActiveHoursYearDetail) {
            return z;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5) == 1 && z;
    }

    private void setClickListener(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_CHART_CLICK_EVENT", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveHoursBaseBarChartProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("BAR_CHART_CALENDAR_CLICK_EVENT".equals(str)) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("calendar", ActiveHoursBaseBarChartProvider.this.mCalendar);
                    HealthCalendarActivity.cxj_(BaseApplication.wa_(), bundle, ActiveHoursBaseBarChartProvider.this.mCalendarSelectResponseCallback);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void registerObserver() {
        if (getDataInfo().isDayData()) {
            ObserverManagerUtil.d(new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.active.provider.ActiveHoursBaseBarChartProvider.3
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    if (objArr == null || objArr.length <= 0 || objArr[0] == null) {
                        return;
                    }
                    ActiveHoursBaseBarChartProvider.this.handleObserverData(objArr);
                }
            }, "observer_label_mark_stand");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleObserverData(Object[] objArr) {
        Object obj = objArr[0];
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            LogUtil.a(TAG, "handleObserverData time is ", Long.valueOf(longValue));
            this.mRefreshTimestamp = longValue;
            SectionBean sectionBean = this.mSectionBean;
            if (sectionBean != null) {
                sectionBean.e(sectionBean.e());
            }
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        if (getDataInfo().isDayData()) {
            ObserverManagerUtil.e("observer_label_mark_stand");
        }
        exitBiForSport();
    }

    private void exitBiForSport() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", "1");
        hashMap.put(CommonUtil.PAGE_TYPE, Integer.valueOf(this.mHasInsertCurTime ? 1 : 0));
        hashMap.put("event", 2);
        ixx.d().d(this.mAppContext, AnalyticsValue.HEALTH_HOME_ACTIVE_HOUR_1040092.value(), hashMap, 0);
    }

    private void setActiveHoursData(Context context, boolean z, String str, long j) {
        if (this.mActiveHoursData == null) {
            LogUtil.h(TAG, "setActiveHoursData mActiveHoursData is null");
            return;
        }
        String h = nsf.h(R$string.IDS_stand_now);
        int i = R.color._2131299241_res_0x7f090ba9;
        if (z) {
            h = nsf.h(R$string.IDS_active_standing);
        } else if (ggl.a(j) <= ggl.a(System.currentTimeMillis())) {
            i = R.color._2131296445_res_0x7f0900bd;
        }
        SpannableString spannableString = new SpannableString(h);
        nsi.cKI_(spannableString, h, i);
        this.mActiveHoursData.d(spannableString);
        this.mActiveHoursData.b(str);
        this.mActiveHoursData.b(j);
        this.mActiveHoursData.c(z);
        setIsInsertCurrentHourTime(j, z);
    }

    private void setIsInsertCurrentHourTime(long j, boolean z) {
        if (j < ggl.a(System.currentTimeMillis()) || j > ggl.d(System.currentTimeMillis()) || !z) {
            return;
        }
        this.mHasInsertCurTime = true;
    }

    private String getYearTimeRange(String str) {
        return nsf.b(R$string.IDS_active_daily_average, str);
    }

    static class a implements CommonUiBaseResponse {
        private WeakReference<ActiveHoursBaseBarChartProvider> d;

        a(ActiveHoursBaseBarChartProvider activeHoursBaseBarChartProvider) {
            this.d = new WeakReference<>(activeHoursBaseBarChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            ActiveHoursBaseBarChartProvider activeHoursBaseBarChartProvider = this.d.get();
            if (activeHoursBaseBarChartProvider == null) {
                LogUtil.h(ActiveHoursBaseBarChartProvider.TAG, "onResponse: provider is null");
                return;
            }
            if (i == 1 && (obj instanceof HealthCalendar)) {
                activeHoursBaseBarChartProvider.mCalendar = (HealthCalendar) obj;
                activeHoursBaseBarChartProvider.mStartTimestamp = nom.f(nom.a(activeHoursBaseBarChartProvider.mCalendar.transformCalendar().getTimeInMillis()));
                activeHoursBaseBarChartProvider.mRefreshTimestamp = activeHoursBaseBarChartProvider.mCalendar.transformCalendar().getTimeInMillis();
                if (activeHoursBaseBarChartProvider.mSectionBean != null) {
                    activeHoursBaseBarChartProvider.mSectionBean.e(activeHoursBaseBarChartProvider.mSectionBean.e());
                }
            }
        }
    }
}
