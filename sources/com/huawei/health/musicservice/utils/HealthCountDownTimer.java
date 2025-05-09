package com.huawei.health.musicservice.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.huawei.ui.commonui.base.BaseActivity;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public abstract class HealthCountDownTimer {
    private static final int END_TAIL;
    private static final int MSG = 1;
    private static final String TAG = "CountDownTimer";
    private volatile long mCountdownInterval;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private volatile long mMillisInFuture;
    private long mStopTimeStamp;
    private volatile boolean mIsCancelled = false;
    private Handler.Callback mCallback = new Handler.Callback() { // from class: com.huawei.health.musicservice.utils.HealthCountDownTimer.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (HealthCountDownTimer.this.mIsCancelled) {
                return true;
            }
            long elapsedRealtime = HealthCountDownTimer.this.mStopTimeStamp - SystemClock.elapsedRealtime();
            LogUtil.c(HealthCountDownTimer.TAG, "handleMessage mCountdownInterval:", Long.valueOf(HealthCountDownTimer.this.mCountdownInterval), ", millisLeft:", Long.valueOf(elapsedRealtime));
            if (elapsedRealtime <= HealthCountDownTimer.END_TAIL) {
                HealthCountDownTimer.this.onFinish();
                return false;
            }
            if (elapsedRealtime < HealthCountDownTimer.this.mCountdownInterval) {
                HealthCountDownTimer.this.mHandler.sendMessageDelayed(HealthCountDownTimer.this.mHandler.obtainMessage(1), elapsedRealtime);
                return false;
            }
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            HealthCountDownTimer.this.onTick(elapsedRealtime);
            long elapsedRealtime3 = HealthCountDownTimer.this.mCountdownInterval - (SystemClock.elapsedRealtime() - elapsedRealtime2);
            if (elapsedRealtime3 >= -5000) {
                while (elapsedRealtime3 < 0 && !HealthCountDownTimer.this.mIsCancelled) {
                    LogUtil.a(HealthCountDownTimer.TAG, "doing too much work in onTick(), skip to next interval...");
                    elapsedRealtime3 += HealthCountDownTimer.this.mCountdownInterval;
                }
            } else {
                LogUtil.a(HealthCountDownTimer.TAG, "onTick() cost too much, delay: " + elapsedRealtime3);
                elapsedRealtime3 = HealthCountDownTimer.this.mCountdownInterval;
            }
            HealthCountDownTimer.this.mHandler.sendMessageDelayed(HealthCountDownTimer.this.mHandler.obtainMessage(1), elapsedRealtime3);
            return false;
        }
    };

    public abstract void onFinish();

    public abstract void onTick(long j);

    static {
        END_TAIL = (BaseActivity.isMiui() || BaseActivity.isSumsung()) ? 300 : 100;
    }

    public HealthCountDownTimer(long j, long j2) {
        this.mMillisInFuture = j;
        this.mCountdownInterval = j2;
        HandlerThread handlerThread = new HandlerThread("CountDownTimerThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper(), this.mCallback);
    }

    public void resetAndTick(long j, long j2) {
        if (j <= 0 || j2 <= 0) {
            LogUtil.e(TAG, "setMillisInFuture mMillisInFuture is not positive");
            return;
        }
        LogUtil.d(TAG, "resetAndTick duration:", Long.valueOf(j), ", interval", Long.valueOf(j2));
        this.mMillisInFuture = j;
        this.mCountdownInterval = j2;
        this.mHandler.removeMessages(1);
        this.mIsCancelled = false;
        this.mStopTimeStamp = SystemClock.elapsedRealtime() + this.mMillisInFuture;
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1), Math.min(j, j2));
    }

    public final void cancel() {
        LogUtil.c(TAG, "countDownTimer cancel");
        this.mIsCancelled = true;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mCountdownInterval = 0L;
    }

    public void release() {
        LogUtil.c(TAG, "countDownTimer released");
        cancel();
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
    }

    public long getCurrentTickInterval() {
        return this.mCountdownInterval;
    }
}
