package com.huawei.health.suggestion.h5pro;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fib;
import defpackage.fjg;
import defpackage.fpq;
import defpackage.ggx;
import defpackage.gij;
import defpackage.kvq;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class BodySenseManager {
    private static final String TAG = "BodySenseManager";
    private int mCurrentHeartRate;
    private ScheduledExecutorService mExecutor;
    private fjg mHeartRateHandler;
    private boolean mIsConnectFitnessLinkDevice;
    private IReportDataCallback mReportDataCallback;
    private long mReportTime;
    private IBaseResponseCallback mResponseCallback;

    private BodySenseManager() {
        this.mReportDataCallback = new IReportDataCallback() { // from class: com.huawei.health.suggestion.h5pro.BodySenseManager.1
            @Override // com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback
            public void onChange(kvq kvqVar) {
                if (kvqVar == null) {
                    LogUtil.b(BodySenseManager.TAG, "report data null.");
                    return;
                }
                CoachController.d().c(kvqVar);
                if (BodySenseManager.this.mResponseCallback != null) {
                    BodySenseManager.this.mResponseCallback.d(0, kvqVar);
                }
                if (BodySenseManager.this.mHeartRateHandler != null) {
                    BodySenseManager.this.mHeartRateHandler.pushHeartRate(kvqVar.j(), kvqVar.m());
                }
                BodySenseManager.this.mCurrentHeartRate = kvqVar.j();
                BodySenseManager.this.mReportTime = kvqVar.m();
            }

            @Override // com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback
            public void onResult() {
                LogUtil.a(BodySenseManager.TAG, "report data onResult.");
            }
        };
    }

    /* loaded from: classes8.dex */
    static class SingletonHolder {
        private static final BodySenseManager INSTANCE = new BodySenseManager();

        private SingletonHolder() {
        }
    }

    public static final BodySenseManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setResponseCallback(IBaseResponseCallback iBaseResponseCallback) {
        this.mResponseCallback = iBaseResponseCallback;
    }

    public void startFitnessLink() {
        boolean a2 = ggx.a(BaseApplication.e().getApplicationContext(), gij.b());
        boolean b = fpq.b();
        LogUtil.a(TAG, "startFitnessLink connect wear device:", Boolean.valueOf(a2), Boolean.valueOf(b));
        boolean z = a2 && b;
        this.mIsConnectFitnessLinkDevice = z;
        if (!z) {
            LogUtil.a(TAG, "no need fitness link.");
            return;
        }
        this.mHeartRateHandler = new fjg();
        CoachController.d().e(fpq.b());
        CoachController.d().a(fpq.c());
        CoachController.d().c(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT);
        CoachController.d().c();
        fib.e().c(this.mReportDataCallback);
        CoachController.d().a(0L);
        initInteractorSchedule();
        CoachController.d().a(CoachController.StatusSource.APP, 1);
    }

    public void pauseFitnessLink() {
        if (!this.mIsConnectFitnessLinkDevice) {
            LogUtil.a(TAG, "no need fitness link.");
        } else {
            CoachController.d().a(CoachController.StatusSource.APP, 2);
        }
    }

    public void resumeFitnessLink() {
        if (!this.mIsConnectFitnessLinkDevice) {
            LogUtil.a(TAG, "no need fitness link.");
        } else {
            CoachController.d().a(CoachController.StatusSource.APP, 1);
        }
    }

    public void stopFitnessLink() {
        if (!this.mIsConnectFitnessLinkDevice) {
            LogUtil.a(TAG, "no need fitness link.");
            return;
        }
        CoachController.d().a(CoachController.StatusSource.APP, 3);
        fib.e().d();
        CoachController.d().b(CoachController.StatusSource.NEW_LINK_WEAR);
        this.mResponseCallback = null;
        this.mCurrentHeartRate = 0;
        this.mReportTime = 0L;
        ScheduledExecutorService scheduledExecutorService = this.mExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mExecutor = null;
        }
    }

    public boolean isConnectFitnessLinkDevice() {
        return this.mIsConnectFitnessLinkDevice;
    }

    public void chapterForward(int i) {
        CoachController.d().b(CoachController.StatusSource.APP, i);
    }

    public void chapterBackward(int i) {
        CoachController.d().c(CoachController.StatusSource.APP, i);
    }

    public void interChapterBreak(int i, int i2, int i3) {
        CourseStateProto.CourseState.Builder newBuilder = CourseStateProto.CourseState.newBuilder();
        newBuilder.setCourseIndex(i);
        newBuilder.setCourseSleepTotalTime(i2);
        newBuilder.setCourseSleepTime(i3);
        CoachController.d().e(CoachController.StatusSource.APP, newBuilder.build());
    }

    protected void initInteractorSchedule() {
        if (this.mExecutor == null) {
            this.mExecutor = Executors.newSingleThreadScheduledExecutor();
        }
        this.mExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.huawei.health.suggestion.h5pro.BodySenseManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BodySenseManager.this.m500xded85d25();
            }
        }, 500L, 1000L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: lambda$initInteractorSchedule$0$com-huawei-health-suggestion-h5pro-BodySenseManager, reason: not valid java name */
    /* synthetic */ void m500xded85d25() {
        int a2 = CoachController.d().a();
        if (a2 == 1) {
            CoachController.d().a(CoachController.d().e() + 1);
            fjg fjgVar = this.mHeartRateHandler;
            if (fjgVar != null) {
                fjgVar.saveHeartRate();
            }
            CoachController.d().axL_(CoachController.d().axM_(a2));
        }
        if (a2 == 2) {
            this.mHeartRateHandler.c(true);
        }
    }

    public List<HeartRateData> getHeartRateList() {
        fjg fjgVar = this.mHeartRateHandler;
        if (fjgVar != null) {
            return fjgVar.getHeartRateList();
        }
        return Collections.emptyList();
    }

    public List<HeartRateData> getInvalidHeartRateList() {
        fjg fjgVar = this.mHeartRateHandler;
        if (fjgVar != null) {
            return fjgVar.getInvalidHeartRateList();
        }
        return Collections.emptyList();
    }

    public int getCurrentHeartRate() {
        return this.mCurrentHeartRate;
    }

    public long getReportTime() {
        return this.mReportTime;
    }

    public void updateCalories(float f) {
        CoachController.d().b(f);
    }

    public void updateActiveCalories(float f) {
        CoachController.d().d(f);
    }
}
