package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import defpackage.jdl;
import defpackage.pmt;
import defpackage.pob;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class SleepJobService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(final JobParameters jobParameters) {
        if (VersionControlUtil.isSupportSleepManagement()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SleepJobService.1
                @Override // java.lang.Runnable
                public void run() {
                    long s;
                    long a2;
                    boolean z;
                    LogUtil.a("SleepJobService", "run job once");
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (jdl.e(jdl.s(currentTimeMillis), currentTimeMillis) < 15) {
                            s = jdl.u(currentTimeMillis);
                            a2 = jdl.f(currentTimeMillis);
                            z = false;
                        } else {
                            s = jdl.s(currentTimeMillis);
                            a2 = jdl.a(currentTimeMillis);
                            z = true;
                        }
                        pob.d(new e(SleepJobService.this, jobParameters, z), s, a2);
                    } catch (Exception unused) {
                        LogUtil.b("SleepJobService", "TryRequestMonthResult exception");
                    }
                }
            });
            return true;
        }
        pmt.d().e();
        return false;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        stopSelf(i2);
        return 2;
    }

    public static class e implements IBaseResponseCallback {
        private JobParameters b;
        private boolean d;
        private WeakReference<SleepJobService> e;

        public e(SleepJobService sleepJobService, JobParameters jobParameters, boolean z) {
            this.e = new WeakReference<>(sleepJobService);
            this.b = jobParameters;
            this.d = z;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            long a2;
            SleepJobService sleepJobService = this.e.get();
            if (sleepJobService == null) {
                return;
            }
            if (VersionControlUtil.isSupportSleepManagement()) {
                long currentTimeMillis = System.currentTimeMillis();
                if (this.d) {
                    a2 = (jdl.j(currentTimeMillis) - pmt.b()) - currentTimeMillis;
                    LogUtil.a("SleepJobService", "schedule run at next month, interval: ", Long.valueOf(a2));
                } else {
                    a2 = (jdl.a(currentTimeMillis) - pmt.b()) - currentTimeMillis;
                    LogUtil.a("SleepJobService", "schedule run at current month, interval: ", Long.valueOf(a2));
                }
                pmt.d().c(a2);
            }
            sleepJobService.jobFinished(this.b, false);
        }
    }
}
