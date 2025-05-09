package com.huawei.ui.main.stories.health.sleep;

import android.os.Handler;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepBarChartView;
import defpackage.jec;
import defpackage.nom;
import defpackage.pwd;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes9.dex */
public class SleepWeekBarLineChartProvider extends SleepBaseBarLineChartProvider {
    private pwd b;
    private b c;
    private Handler e;

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected int getCoreSleepType() {
        return 2;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected int getLayoutId() {
        return R.layout.fragment_fitness_core_sleep_week_detail;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void showCurrentDate() {
        Date date = new Date(System.currentTimeMillis());
        setEndDay(a(date));
        setStartDay(e(date));
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    public void showLastSleepData(CoreSleepBarChartView coreSleepBarChartView) {
        if (this.mLastTimestamp <= 0 || coreSleepBarChartView.acquireScrollAdapter() == null) {
            return;
        }
        int f = nom.f(nom.m(this.mLastTimestamp));
        LogUtil.a("SleepWeekBarChartProvider", "startTimestamp=", Integer.valueOf(f));
        coreSleepBarChartView.setShowRange(f, coreSleepBarChartView.acquireScrollAdapter().acquireRange());
    }

    public Date a(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(7, 1);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        return calendar.getTime();
    }

    public Date e(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(7, 2);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected DataInfos getDataInfo() {
        return DataInfos.CoreSleepWeekDetail;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected LinearLayout getConfiguredLayout() {
        return (LinearLayout) this.mView.findViewById(R.id.sleep_week_operation_config_layout);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected LinearLayout getMarketingLayout() {
        return (LinearLayout) this.mView.findViewById(R.id.sleep_week_operation_marketing_layout);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void initOtherView() {
        this.c = new b();
        this.b = getInteractor();
        this.e = getBaseHandler();
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void requestData(boolean z) {
        d(z);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getNextEndDay() {
        return new Date(jec.e(getEndDay(), 7) * 1000);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getNextStartDay() {
        return new Date(jec.e(getStartDay(), 7) * 1000);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getPreviousStartDay() {
        return new Date(jec.e(getStartDay(), -7) * 1000);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getPreviousEndDay() {
        return new Date(jec.e(getEndDay(), -7) * 1000);
    }

    public void d(boolean z) {
        LogUtil.a("SleepWeekBarChartProvider", "week enter requestWeekDatas");
        Date startDay = getStartDay();
        if (startDay == null && !z) {
            showCurrentDate();
        }
        if (startDay != null) {
            pwd pwdVar = this.b;
            if (pwdVar != null) {
                pwdVar.d(new Date[]{getStartDay(), getEndDay()}, getTag(), 2, false, this.c);
            } else {
                LogUtil.a("SleepWeekBarChartProvider", "mInteractor is null! ");
                setLoadingState(false);
            }
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepViewCallback
    public void sleepToTop() {
        this.mHealthScrollView.fullScroll(33);
    }

    static class b implements CommonUiBaseResponse {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<SleepWeekBarLineChartProvider> f10232a;

        private b(SleepWeekBarLineChartProvider sleepWeekBarLineChartProvider) {
            this.f10232a = new WeakReference<>(sleepWeekBarLineChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            SleepWeekBarLineChartProvider sleepWeekBarLineChartProvider = this.f10232a.get();
            if (sleepWeekBarLineChartProvider == null) {
                return;
            }
            sleepWeekBarLineChartProvider.setLoadingState(false);
            if (i == 0 && obj != null) {
                LogUtil.a("SleepWeekBarChartProvider", "SleepWeekDetail requestSleepDetailUIData objData is not null!");
            }
            sleepWeekBarLineChartProvider.e.sendEmptyMessage(6001);
        }
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected String getTag() {
        return "SleepWeekBarChartProvider";
    }
}
