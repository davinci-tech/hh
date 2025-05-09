package com.huawei.ui.main.stories.health.sleep;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepBarChartView;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView;
import defpackage.jec;
import defpackage.nom;
import defpackage.pwd;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Date;

/* loaded from: classes9.dex */
public class SleepMonthBarLineChartProvider extends SleepBaseBarLineChartProvider {

    /* renamed from: a, reason: collision with root package name */
    private Handler f10230a;
    private Observer b;
    private pwd d;
    private a e;

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected int getCoreSleepType() {
        return 3;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected int getLayoutId() {
        return R.layout.fragment_fitness_core_sleep_month_detail;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected View initView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_fitness_core_sleep_month_detail, viewGroup, false);
        }
        Bundle extra = getExtra();
        if (extra != null) {
            this.mLastTimestamp = extra.getLong("key_bundle_health_last_data_time", 0L);
            LogUtil.a("SleepMonthBarLineChartProvider", "mLastTimestamp= ", Long.valueOf(this.mLastTimestamp));
        } else {
            LogUtil.b("SleepMonthBarLineChartProvider", "getArguments is null ");
        }
        this.mHealthScrollView = (HealthScrollView) this.mView.findViewById(R.id.core_sleep_year_scrollview);
        this.mHealthScrollView.setScrollViewVerticalDirectionEvent(true);
        Activity activity = this.sleepActivityWeakReference.get();
        if (activity != null) {
            ScrollUtil.cKx_(this.mHealthScrollView, activity.getWindow().getDecorView(), IEventListener.EVENT_ID_DEVICE_DLNA_CONN_SUCC);
        }
        ((CoreSleepServiceView) this.mView.findViewById(R.id.core_sleep_service_view)).setSleepViewCallback(this);
        return this.mView;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void showCurrentDate() {
        LogUtil.a("SleepMonthBarLineChartProvider", "Last Timestamp=", Long.valueOf(this.mLastTimestamp));
        Date b = jec.b();
        if (this.mLastTimestamp > 0) {
            b = new Date(this.mLastTimestamp);
        }
        setEndDay(jec.m(b));
        setStartDay(jec.g(b));
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    public void showLastSleepData(CoreSleepBarChartView coreSleepBarChartView) {
        if (this.mLastTimestamp <= 0 || coreSleepBarChartView.acquireScrollAdapter() == null) {
            return;
        }
        int f = nom.f(nom.f(this.mLastTimestamp));
        LogUtil.a("SleepMonthBarLineChartProvider", "startTimestamp=", Integer.valueOf(f));
        coreSleepBarChartView.setShowRange(f, coreSleepBarChartView.acquireScrollAdapter().acquireRange());
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void requestData(boolean z) {
        b(z);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected DataInfos getDataInfo() {
        return DataInfos.CoreSleepMonthDetail;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected LinearLayout getConfiguredLayout() {
        return (LinearLayout) this.mView.findViewById(R.id.sleep_month_operation_config_layout);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected LinearLayout getMarketingLayout() {
        return (LinearLayout) this.mView.findViewById(R.id.sleep_month_operation_Marketing_layout);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        Observer observer = this.b;
        if (observer != null) {
            ObserverManagerUtil.e(observer, "SleepYearBarLineChartProvider");
        }
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void initOtherView() {
        this.e = new a();
        this.d = getInteractor();
        this.f10230a = getBaseHandler();
        Observer observer = new Observer() { // from class: com.huawei.ui.main.stories.health.sleep.SleepMonthBarLineChartProvider.4
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (objArr[0] instanceof Integer) {
                    SleepMonthBarLineChartProvider.this.mUiDataMap.put("YEAR_JUMP_TO_MONTH_TIME", objArr[0]);
                    SleepMonthBarLineChartProvider.this.mSleepChartData.b(SleepMonthBarLineChartProvider.this.mUiDataMap);
                    SleepMonthBarLineChartProvider.this.mSectionBean.e(SleepMonthBarLineChartProvider.this.mSleepChartData);
                }
            }
        };
        this.b = observer;
        ObserverManagerUtil.d(observer, "SleepYearBarLineChartProvider");
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getNextEndDay() {
        return jec.o(getEndDay());
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getNextStartDay() {
        return jec.j(getStartDay());
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getPreviousStartDay() {
        return jec.h(getStartDay());
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getPreviousEndDay() {
        return jec.q(getEndDay());
    }

    public void b(boolean z) {
        ReleaseLogUtil.e("R_Sleep_SleepYearBarLineChartProvider", "Month SleepMonthDetail enter requestMonthDatas");
        Date startDay = getStartDay();
        if (startDay == null && !z) {
            showCurrentDate();
        }
        if (startDay != null) {
            pwd pwdVar = this.d;
            if (pwdVar != null) {
                pwdVar.d(new Date[]{getStartDay(), getEndDay()}, getTag(), 3, false, this.e);
            } else {
                LogUtil.a("SleepMonthBarLineChartProvider", "mInteractor is null!");
                setLoadingState(false);
            }
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepViewCallback
    public void sleepToTop() {
        this.mHealthScrollView.fullScroll(33);
    }

    static class a implements CommonUiBaseResponse {
        private WeakReference<SleepMonthBarLineChartProvider> c;

        private a(SleepMonthBarLineChartProvider sleepMonthBarLineChartProvider) {
            this.c = new WeakReference<>(sleepMonthBarLineChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            SleepMonthBarLineChartProvider sleepMonthBarLineChartProvider = this.c.get();
            if (sleepMonthBarLineChartProvider == null) {
                return;
            }
            sleepMonthBarLineChartProvider.setLoadingState(false);
            if (i == 0 && obj != null) {
                LogUtil.a("SleepMonthBarLineChartProvider", "SleepMonthDetail requestSleepDetailUIData objData is not null!");
                LogUtil.c("SleepMonthBarLineChartProvider", "SleepMonthDetail data success objData = ", obj);
            }
            sleepMonthBarLineChartProvider.f10230a.sendEmptyMessage(6001);
        }
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected String getTag() {
        return "SleepMonthBarLineChartProvider";
    }
}
