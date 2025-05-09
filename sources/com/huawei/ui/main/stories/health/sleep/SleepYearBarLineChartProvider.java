package com.huawei.ui.main.stories.health.sleep;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepBarChartView;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AsyncSelectorSerialize;
import com.huawei.ui.main.stories.health.sleep.SleepYearBarLineChartProvider;
import defpackage.jec;
import defpackage.nol;
import defpackage.nom;
import defpackage.pwd;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Map;

/* loaded from: classes9.dex */
public class SleepYearBarLineChartProvider extends SleepBaseBarLineChartProvider {
    private Handler c;
    private e d;
    private pwd e;

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected int getCoreSleepType() {
        return 4;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected int getLayoutId() {
        return R.layout.fragment_fitness_coresleepbase_detail;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void processJump(long j) {
        Activity activity = this.sleepActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(new Handler(Looper.getMainLooper())) { // from class: com.huawei.ui.main.stories.health.sleep.SleepYearBarLineChartProvider.3
            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AsyncSelectorSerialize
            public void onFailed(int i) {
            }

            @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AsyncSelectorSerialize
            public void onSuccess(Map map) {
            }
        };
        asyncSelectorSerialize.add(new AnonymousClass1(j, (KnitSleepDetailActivity) activity));
        asyncSelectorSerialize.run();
    }

    /* renamed from: com.huawei.ui.main.stories.health.sleep.SleepYearBarLineChartProvider$1, reason: invalid class name */
    public class AnonymousClass1 implements AsyncSelectorSerialize.Action {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ KnitSleepDetailActivity f10233a;
        final /* synthetic */ long c;

        @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AsyncSelectorSerialize.Action
        public int getFailedValue() {
            return 0;
        }

        AnonymousClass1(long j, KnitSleepDetailActivity knitSleepDetailActivity) {
            this.c = j;
            this.f10233a = knitSleepDetailActivity;
        }

        @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.utils.AsyncSelectorSerialize.Action
        public void execute(Map map) {
            nol nolVar = new nol();
            final long j = this.c;
            final KnitSleepDetailActivity knitSleepDetailActivity = this.f10233a;
            nolVar.cCv_(new ValueAnimator.AnimatorUpdateListener() { // from class: qns
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SleepYearBarLineChartProvider.AnonymousClass1.dGw_(j, knitSleepDetailActivity, valueAnimator);
                }
            }, 800);
        }

        public static /* synthetic */ void dGw_(long j, KnitSleepDetailActivity knitSleepDetailActivity, ValueAnimator valueAnimator) {
            ObserverManagerUtil.c("SleepYearBarLineChartProvider", Integer.valueOf((int) (jec.f(j) / 60000)));
            knitSleepDetailActivity.getViewPager().setCurrentItem(2);
        }
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void initOtherView() {
        this.d = new e();
        this.e = getInteractor();
        this.c = getBaseHandler();
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void showCurrentDate() {
        LogUtil.a("SleepYearBarLineChartProvider", "Last Timestamp=", Long.valueOf(this.mLastTimestamp));
        Date i = jec.i(new Date(this.mLastTimestamp > 0 ? this.mLastTimestamp : System.currentTimeMillis()));
        long b = jec.b(i, -11);
        setEndDay(jec.b(jec.d(jec.c(i, 1), -1)));
        setStartDay(new Date(b * 1000));
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    public void showLastSleepData(CoreSleepBarChartView coreSleepBarChartView) {
        if (this.mLastTimestamp <= 0 || coreSleepBarChartView.acquireScrollAdapter() == null) {
            return;
        }
        int f = nom.f(nom.t(this.mLastTimestamp));
        LogUtil.a("SleepYearBarLineChartProvider", "startTimestamp=", Integer.valueOf(f));
        coreSleepBarChartView.setShowRange(f, coreSleepBarChartView.acquireScrollAdapter().acquireRange());
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected LinearLayout getConfiguredLayout() {
        return (LinearLayout) this.mView.findViewById(R.id.sleep_year_operation_config_layout);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected LinearLayout getMarketingLayout() {
        return (LinearLayout) this.mView.findViewById(R.id.sleep_year_operation_marketing_layout);
    }

    public void a(boolean z) {
        if (!z) {
            setLoadingState(true);
        }
        LogUtil.a("SleepYearBarLineChartProvider", "SleepYearDetail enter requestYearDatas");
        Date startDay = getStartDay();
        if (startDay == null && !z) {
            showCurrentDate();
        }
        if (startDay != null) {
            pwd pwdVar = this.e;
            if (pwdVar == null) {
                LogUtil.a("SleepYearBarLineChartProvider", "mInteractor is null!");
            } else {
                pwdVar.d(new Date[]{getStartDay(), getEndDay()}, getTag(), 4, false, this.d);
            }
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepViewCallback
    public void sleepToTop() {
        this.mHealthScrollView.fullScroll(33);
    }

    static class e implements CommonUiBaseResponse {
        private WeakReference<SleepYearBarLineChartProvider> c;

        private e(SleepYearBarLineChartProvider sleepYearBarLineChartProvider) {
            this.c = new WeakReference<>(sleepYearBarLineChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            SleepYearBarLineChartProvider sleepYearBarLineChartProvider = this.c.get();
            if (sleepYearBarLineChartProvider == null) {
                return;
            }
            if (i == 0 && obj != null) {
                LogUtil.a("SleepYearBarLineChartProvider", "SleepYearDetail objData is not null!");
            }
            sleepYearBarLineChartProvider.c.sendEmptyMessage(6001);
            sleepYearBarLineChartProvider.c.sendEmptyMessageDelayed(6002, 1L);
        }
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected void requestData(boolean z) {
        a(z);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getNextEndDay() {
        return new Date(jec.b(getEndDay(), 12) * 1000);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getNextStartDay() {
        return new Date(jec.b(getStartDay(), 12) * 1000);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getPreviousStartDay() {
        return new Date(jec.b(getStartDay(), -12) * 1000);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected Date getPreviousEndDay() {
        return new Date(jec.b(getEndDay(), -12) * 1000);
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected DataInfos getDataInfo() {
        return DataInfos.CoreSleepYearDetail;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected boolean getIfVerify(long j) {
        int t = jec.t(new Date(j));
        int u = jec.u(new Date(j));
        int a2 = jec.a();
        int d = jec.d();
        if (u < d) {
            return true;
        }
        return u == d && t <= a2;
    }

    @Override // com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider
    protected String getTag() {
        return "SleepYearBarLineChartProvider";
    }
}
