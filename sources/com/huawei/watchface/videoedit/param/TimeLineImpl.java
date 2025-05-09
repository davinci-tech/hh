package com.huawei.watchface.videoedit.param;

import com.huawei.watchface.utils.HwLog;

/* loaded from: classes9.dex */
public class TimeLineImpl implements ItimeLine {
    private boolean mIsSaveMode;
    private TimeLineStatus mPendingStatus;
    TimeLineStatus status;
    private long saveTime = 0;
    private long mStartBaseTime = 0;
    private long mCurrentTime = 0;

    public enum TimeLineStatus {
        INIT,
        ACTIVE,
        SEEKING,
        PAUSE
    }

    public TimeLineImpl() {
        this.status = TimeLineStatus.INIT;
        this.status = TimeLineStatus.INIT;
    }

    @Override // com.huawei.watchface.videoedit.param.ItimeLine
    public long getCurrentTime() {
        if (this.mIsSaveMode) {
            return this.saveTime;
        }
        int i = AnonymousClass1.$SwitchMap$com$huawei$watchface$videoedit$param$TimeLineImpl$TimeLineStatus[this.status.ordinal()];
        if (i == 2 || i == 3) {
            return this.mCurrentTime;
        }
        if (i != 4) {
            return 0L;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.mStartBaseTime;
        this.mCurrentTime = currentTimeMillis;
        return currentTimeMillis;
    }

    /* renamed from: com.huawei.watchface.videoedit.param.TimeLineImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huawei$watchface$videoedit$param$TimeLineImpl$TimeLineStatus;

        static {
            int[] iArr = new int[TimeLineStatus.values().length];
            $SwitchMap$com$huawei$watchface$videoedit$param$TimeLineImpl$TimeLineStatus = iArr;
            try {
                iArr[TimeLineStatus.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huawei$watchface$videoedit$param$TimeLineImpl$TimeLineStatus[TimeLineStatus.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$huawei$watchface$videoedit$param$TimeLineImpl$TimeLineStatus[TimeLineStatus.SEEKING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$huawei$watchface$videoedit$param$TimeLineImpl$TimeLineStatus[TimeLineStatus.ACTIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void dump() {
        HwLog.d(HwLog.TAG, "save mode ? " + this.mIsSaveMode + ", status: " + this.status + ", CurrentTime : " + this.mCurrentTime + " mStartBaseTime : " + this.mStartBaseTime);
    }

    @Override // com.huawei.watchface.videoedit.param.ItimeLine
    public void reset() {
        HwLog.e(HwLog.TAG, "pause and seek reset");
        this.status = TimeLineStatus.INIT;
        this.mStartBaseTime = 0L;
        this.mCurrentTime = 0L;
        this.mIsSaveMode = false;
    }

    @Override // com.huawei.watchface.videoedit.param.ItimeLine
    public void start() {
        HwLog.e(HwLog.TAG, "start");
        if (this.status == TimeLineStatus.SEEKING) {
            this.mStartBaseTime = System.currentTimeMillis() - this.mCurrentTime;
        } else {
            this.mStartBaseTime = System.currentTimeMillis();
        }
        this.status = TimeLineStatus.ACTIVE;
        this.mIsSaveMode = false;
    }

    public void startSaveMode(boolean z) {
        this.mIsSaveMode = z;
        this.saveTime = 0L;
    }

    public void increasByFixTime(long j) {
        this.saveTime += j;
    }

    @Override // com.huawei.watchface.videoedit.param.ItimeLine
    public void pause() {
        HwLog.d(HwLog.TAG, "Pause CurrentTime is " + this.mCurrentTime + " mStartBaseTime is " + this.mStartBaseTime + " status is " + this.status);
        if (this.status == TimeLineStatus.ACTIVE) {
            this.status = TimeLineStatus.PAUSE;
            this.mCurrentTime = System.currentTimeMillis() - this.mStartBaseTime;
        } else if (this.status == TimeLineStatus.SEEKING) {
            this.mPendingStatus = TimeLineStatus.PAUSE;
        } else {
            HwLog.d(HwLog.TAG, "other case");
        }
        HwLog.d(HwLog.TAG, "Pause CurrentTime is " + this.mCurrentTime + " mStartBaseTime is " + this.mStartBaseTime + " status is " + this.status);
    }

    @Override // com.huawei.watchface.videoedit.param.ItimeLine
    public void resume() {
        HwLog.d(HwLog.TAG, "Resume CurrentTime is " + this.mCurrentTime + " mStartBaseTime is " + this.mStartBaseTime + " status is " + this.status);
        if (this.status == TimeLineStatus.PAUSE) {
            this.status = TimeLineStatus.ACTIVE;
            this.mStartBaseTime = System.currentTimeMillis() - this.mCurrentTime;
        } else if (this.status == TimeLineStatus.SEEKING) {
            this.mPendingStatus = TimeLineStatus.ACTIVE;
        } else {
            HwLog.d(HwLog.TAG, "other case");
        }
        HwLog.d(HwLog.TAG, "Resume CurrentTime is " + this.mCurrentTime + " mStartBaseTime is " + this.mStartBaseTime + " status is " + this.status);
    }

    @Override // com.huawei.watchface.videoedit.param.ItimeLine
    public void seekTo(long j) {
        HwLog.d(HwLog.TAG, "SeekTo " + j + " status is " + this.status);
        if (this.status == TimeLineStatus.ACTIVE || this.status == TimeLineStatus.PAUSE) {
            this.mPendingStatus = this.status;
        } else if (this.status == TimeLineStatus.SEEKING) {
            HwLog.d(HwLog.TAG, "status is SEEKING");
        } else {
            this.mPendingStatus = TimeLineStatus.ACTIVE;
        }
        this.status = TimeLineStatus.SEEKING;
        this.mCurrentTime = j;
    }

    @Override // com.huawei.watchface.videoedit.param.ItimeLine
    public TimeLineStatus seekCompleted() {
        HwLog.d(HwLog.TAG, "seekCompleted status is  " + this.status + " mCurrentTime is " + this.mCurrentTime + " mPendingStatus " + this.mPendingStatus);
        if (this.mPendingStatus == TimeLineStatus.ACTIVE) {
            this.status = TimeLineStatus.ACTIVE;
            this.mStartBaseTime = System.currentTimeMillis() - this.mCurrentTime;
        } else if (this.mPendingStatus == TimeLineStatus.PAUSE) {
            this.status = TimeLineStatus.PAUSE;
        } else {
            HwLog.e(HwLog.TAG, "invalid status is  " + this.mPendingStatus);
        }
        return this.status;
    }
}
