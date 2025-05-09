package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.treadmill.CallBackToReportStepsOrEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class sqr {
    private static Context e;

    /* renamed from: a, reason: collision with root package name */
    private ScheduledExecutorService f17218a;
    private HealthOpenSDK b;
    private njm c;
    private IExecuteResult d;

    private sqr() {
        this.c = null;
        this.d = new IExecuteResult() { // from class: sqr.2
            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onFailed(Object obj) {
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onServiceException(Object obj) {
            }

            @Override // com.huawei.hihealth.motion.IExecuteResult
            public void onSuccess(Object obj) {
                if (obj instanceof Bundle) {
                    int i = ((Bundle) obj).getInt("standSteps", 0);
                    LogUtil.c("Track_VibraStepCounterHelper", "mExecuteResult onSuccess");
                    if (sqr.this.c != null) {
                        sqr.this.c.e(i);
                    }
                }
            }
        };
    }

    public static sqr d(Context context) {
        e = context;
        return a.c;
    }

    public boolean c(njl njlVar, CallBackToReportStepsOrEvent callBackToReportStepsOrEvent, int i) {
        njm a2 = njm.a(e, true);
        this.c = a2;
        if (a2 == null) {
            LogUtil.h("Track_VibraStepCounterHelper", "initAndStartVibraStepCount, getInstance of StepsCounter returns null");
            return false;
        }
        boolean a3 = a2.a(njlVar, callBackToReportStepsOrEvent, i);
        if (a3) {
            LogUtil.a("Track_VibraStepCounterHelper", "now will start scheduleAtFixedRate for set cur steps from stepCountModule to vibraStepsCounter");
            if (this.b == null) {
                this.b = d();
            }
            if (this.f17218a == null) {
                ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
                this.f17218a = newSingleThreadScheduledExecutor;
                newSingleThreadScheduledExecutor.scheduleAtFixedRate(new d(), 0L, 1000L, TimeUnit.MILLISECONDS);
            }
        }
        return a3;
    }

    public int c() {
        njm njmVar = this.c;
        if (njmVar == null) {
            LogUtil.h("Track_VibraStepCounterHelper", "getCurrentSteps, mStepsCounter is null");
            return -1;
        }
        return njmVar.c();
    }

    public boolean e(int i, int i2, int i3) {
        njm njmVar = this.c;
        if (njmVar == null) {
            LogUtil.h("Track_VibraStepCounterHelper", "refreshWorkoutParameters, mStepsCounter is null");
            return false;
        }
        return njmVar.c(i, i2, i3);
    }

    public void b() {
        njm njmVar = this.c;
        if (njmVar != null) {
            njmVar.d();
            LogUtil.a("Track_VibraStepCounterHelper", "stopVibraStepsCount: stopCountSteps");
        }
        ScheduledExecutorService scheduledExecutorService = this.f17218a;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        this.f17218a = null;
        this.b = null;
        this.c = null;
    }

    private HealthOpenSDK d() {
        if (this.b == null) {
            HealthOpenSDK b = iwz.b();
            this.b = b;
            b.initSDK(e, new IExecuteResult() { // from class: sqr.5
                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onSuccess(Object obj) {
                    LogUtil.c("Track_VibraStepCounterHelper", "healthOpenSDKCallback initSDK success");
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onFailed(Object obj) {
                    LogUtil.b("Track_VibraStepCounterHelper", "healthOpenSDKCallback : initSDK Failed");
                }

                @Override // com.huawei.hihealth.motion.IExecuteResult
                public void onServiceException(Object obj) {
                    LogUtil.b("Track_VibraStepCounterHelper", "healthOpenSDKCallback : initSDK onServiceException");
                }
            }, "HuaweiHealth");
        }
        return this.b;
    }

    static class a {
        private static final sqr c = new sqr();
    }

    class d implements Runnable {
        private d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (sqr.this.b != null) {
                sqr.this.b.e(sqr.this.d);
            }
        }
    }
}
