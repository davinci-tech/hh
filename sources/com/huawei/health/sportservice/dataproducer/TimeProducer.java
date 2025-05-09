package com.huawei.health.sportservice.dataproducer;

import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

@SportComponentType(classify = 2, name = ComponentName.TIME_PRODUCER)
/* loaded from: classes8.dex */
public class TimeProducer extends BaseProducer implements SportLifecycle {
    private static final String TAG = "SportService_TimeProducer";
    private boolean mIsStop = false;
    private int mIntervalForFiveSecond = 0;
    private long mSportDuration = 0;
    private long mPauseDuration = 0;
    private long mLastPauseTimestamp = 0;

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        BaseSportManager.getInstance().subscribeToSource("TIME_ONE_SECOND_DURATION", this);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        distribute(this.mSportDuration, System.currentTimeMillis() - this.mPauseDuration);
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onResumeSport() {
        updatePauseDuration();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPauseSport() {
        updatePauseTimestamp();
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onStopSport() {
        this.mIsStop = true;
        onSaveData();
    }

    private boolean isPaused() {
        return BaseSportManager.getInstance().getStatus() == 2;
    }

    private void updatePauseDuration() {
        if (this.mLastPauseTimestamp != 0) {
            this.mPauseDuration += System.currentTimeMillis() - this.mLastPauseTimestamp;
            this.mLastPauseTimestamp = 0L;
        }
        LogUtil.a(TAG, "updatePauseDuration " + this.mPauseDuration);
    }

    private void updatePauseTimestamp() {
        this.mLastPauseTimestamp = System.currentTimeMillis();
        LogUtil.a(TAG, "updatePauseTimestamp " + this.mLastPauseTimestamp);
    }

    private void updatePauseDurationIfPaused() {
        boolean isPaused = isPaused();
        LogUtil.c(TAG, "updatePauseDuration isPaused: " + isPaused);
        if (isPaused) {
            updatePauseDuration();
            updatePauseTimestamp();
        }
    }

    private void distribute(long j, long j2) {
        BaseSportManager.getInstance().stagingAndNotification("TIME_ONE_SECOND_TIMESTAMP", Long.valueOf(j2));
        BaseSportManager.getInstance().stagingAndNotification("TIME_ONE_SECOND_DURATION", Long.valueOf(j));
        int i = this.mIntervalForFiveSecond + 1;
        this.mIntervalForFiveSecond = i;
        if (i == 5) {
            this.mIntervalForFiveSecond = 0;
            BaseSportManager.getInstance().stagingAndNotification("TIME_FIVE_SECOND_TIMESTAMP", Long.valueOf(j2));
            BaseSportManager.getInstance().stagingAndNotification("TIME_FIVE_SECOND_DURATION", Long.valueOf(j));
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer, com.huawei.health.sportservice.inter.SourceToProducer
    public void onSourceDataChanged(Object obj) {
        long longValue = ((Long) obj).longValue();
        this.mSportDuration = longValue;
        LogUtil.a(TAG, "onTimeChange", Long.valueOf(longValue));
        onStagingAndNotification();
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onStagingAndNotification() {
        if (this.mIsStop) {
            return;
        }
        updatePauseDurationIfPaused();
        distribute(this.mSportDuration, System.currentTimeMillis() - this.mPauseDuration);
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void onSaveData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            motionPathSimplify.saveTotalTime(this.mSportDuration);
            motionPathSimplify.saveEndTime(System.currentTimeMillis());
            if (this.mIsStop && this.mLastPauseTimestamp != 0) {
                this.mPauseDuration += System.currentTimeMillis() - this.mLastPauseTimestamp;
            }
            motionPathSimplify.saveStartTime((System.currentTimeMillis() - this.mSportDuration) - this.mPauseDuration);
            BaseSportManager.getInstance().stagingData("MOTION_PATH_SIMPLIFY_DATA", motionPathSimplify);
        }
    }

    @Override // com.huawei.health.sportservice.dataproducer.BaseProducer
    void recoveryData() {
        Object data = BaseSportManager.getInstance().getData("MOTION_PATH_SIMPLIFY_DATA");
        if (data instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) data;
            this.mSportDuration = motionPathSimplify.requestTotalTime();
            long requestEndTime = motionPathSimplify.requestEndTime();
            long requestStartTime = motionPathSimplify.requestStartTime();
            if (requestEndTime > 0 && requestStartTime > 0) {
                this.mPauseDuration = (requestEndTime - this.mSportDuration) - requestStartTime;
            }
            LogUtil.a(TAG, "recovery mSportDuration ", Long.valueOf(this.mSportDuration), " mPauseDuration ", Long.valueOf(this.mPauseDuration));
        }
    }
}
