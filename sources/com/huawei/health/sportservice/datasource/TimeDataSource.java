package com.huawei.health.sportservice.datasource;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class TimeDataSource extends DataSource {
    private static final String TAG = "TimeDataSource";
    private static final int TIME_INCREMENT_DEFAULT = 1000;
    private ScheduledExecutorService mExecutor;
    private long mSportDuration = 0;
    private boolean mIsSporting = false;

    /* JADX INFO: Access modifiers changed from: private */
    public void increaseTimeForTik() {
        this.mSportDuration += 1000;
    }

    class TimeRunner implements Runnable {
        private TimeRunner() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TimeDataSource.this.mIsSporting) {
                TimeDataSource.this.increaseTimeForTik();
                LogUtil.a(TimeDataSource.TAG, "mSportDuration", Long.valueOf(TimeDataSource.this.mSportDuration));
                TimeDataSource.this.mDataSourceCallback.onTimeChange(TimeDataSource.this.mSportDuration);
            }
        }
    }

    public void isSporting(boolean z) {
        this.mIsSporting = z;
    }

    @Override // com.huawei.health.sportservice.datasource.DataSource
    public void init() {
        if (this.mExecutor == null) {
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.mExecutor = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleAtFixedRate(new TimeRunner(), 0L, 1000L, TimeUnit.MILLISECONDS);
        }
    }

    @Override // com.huawei.health.sportservice.datasource.DataSource
    public void destroy() {
        ScheduledExecutorService scheduledExecutorService = this.mExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mExecutor = null;
        }
    }
}
