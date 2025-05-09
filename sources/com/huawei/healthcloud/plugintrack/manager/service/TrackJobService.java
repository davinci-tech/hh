package com.huawei.healthcloud.plugintrack.manager.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import defpackage.gsx;

/* loaded from: classes4.dex */
public class TrackJobService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        gsx a2 = gsx.a();
        if (!a2.e()) {
            jobFinished(jobParameters, false);
            gsx.b(this);
            return true;
        }
        if (a2.d()) {
            a2.c();
        }
        return false;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        return 1;
    }
}
