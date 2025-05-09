package com.huawei.health.sportservice.datasource;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SportComponentType(classify = 3, name = ComponentName.TIME_SELF_SOURCE)
/* loaded from: classes8.dex */
public class TimeSelfSource extends BaseSource<Integer> implements SportLifecycle {
    private static final String TAG = "SportService_TimeDataSource";
    private static final int TIME_INCREMENT_DEFAULT = 1000;
    private ScheduledExecutorService mExecutor;
    private long mSportDuration = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
    }

    static /* synthetic */ long access$014(TimeSelfSource timeSelfSource, long j) {
        long j2 = timeSelfSource.mSportDuration + j;
        timeSelfSource.mSportDuration = j2;
        return j2;
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
        BaseSportManager.getInstance().updateSourceData(getLogTag(), "TIME_ONE_SECOND_DURATION", Long.valueOf(this.mSportDuration));
    }

    class TimeRunner implements Runnable {
        private TimeRunner() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (BaseSportManager.getInstance().getStatus() != 1) {
                    return;
                }
                TimeSelfSource.access$014(TimeSelfSource.this, 1000L);
                LogUtil.a(TimeSelfSource.TAG, "mSportDuration", Long.valueOf(TimeSelfSource.this.mSportDuration));
                TimeSelfSource.this.updateSourceData();
            } catch (Exception unused) {
                ReleaseLogUtil.c(TimeSelfSource.TAG, "TimeRunner Exception");
            }
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        if (this.mExecutor == null) {
            ReleaseLogUtil.b(TAG, "onStartSport");
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.mExecutor = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleAtFixedRate(new TimeRunner(), 0L, 1000L, TimeUnit.MILLISECONDS);
        }
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        shutdown();
    }

    private void shutdown() {
        ScheduledExecutorService scheduledExecutorService = this.mExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mExecutor = null;
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("TIME_ONE_SECOND_DURATION");
        if (data instanceof Long) {
            long longValue = ((Long) data).longValue();
            this.mSportDuration = longValue;
            LogUtil.a(TAG, "recoveryData() mSportDuration: ", Long.valueOf(longValue));
        }
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void destroy() {
        super.destroy();
        shutdown();
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
