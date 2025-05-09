package com.huawei.hwservicesmgr;

import android.app.job.JobParameters;
import android.app.job.JobService;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kih;

/* loaded from: classes5.dex */
public class PhoneJobService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        if (jobParameters == null) {
            LogUtil.h("PhoneJobService", "onStartJob params == null");
            return false;
        }
        int jobId = jobParameters.getJobId();
        LogUtil.a("PhoneJobService", "onStartJob jobId ", Integer.valueOf(jobId));
        if (jobId != 10009) {
            return false;
        }
        kih.e().b();
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        if (jobParameters == null) {
            LogUtil.h("PhoneJobService", "onStopJob params == null");
            return false;
        }
        int jobId = jobParameters.getJobId();
        LogUtil.a("PhoneJobService", "onStopJob jobId ", Integer.valueOf(jobId));
        return jobId == 10009;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("PhoneJobService", "onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("PhoneJobService", "onDestroy");
    }
}
