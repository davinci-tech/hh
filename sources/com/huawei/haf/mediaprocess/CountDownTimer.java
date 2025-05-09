package com.huawei.haf.mediaprocess;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.huawei.haf.handler.HandlerExecutor;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public abstract class CountDownTimer {
    private static final int MSG = 1;
    private static final String TAG = "CountDownTimer";
    private volatile long mCountdownInterval;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private volatile long mMillisInFuture;
    private long mStopTimeInFuture;
    private boolean mIsCancelled = false;
    private Handler.Callback mCallback = new Handler.Callback() { // from class: com.huawei.haf.mediaprocess.CountDownTimer.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (CountDownTimer.this.mIsCancelled) {
                return true;
            }
            long elapsedRealtime = CountDownTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
            if (elapsedRealtime <= 0) {
                CountDownTimer.this.onFinish();
                return false;
            }
            if (elapsedRealtime < CountDownTimer.this.mCountdownInterval) {
                CountDownTimer.this.mHandler.sendMessageDelayed(CountDownTimer.this.mHandler.obtainMessage(1), elapsedRealtime);
                return false;
            }
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            CountDownTimer.this.onTick(elapsedRealtime);
            long elapsedRealtime3 = (elapsedRealtime2 + CountDownTimer.this.mCountdownInterval) - SystemClock.elapsedRealtime();
            while (elapsedRealtime3 < 0) {
                elapsedRealtime3 += CountDownTimer.this.mCountdownInterval;
            }
            CountDownTimer.this.mHandler.sendMessageDelayed(CountDownTimer.this.mHandler.obtainMessage(1), elapsedRealtime3);
            return false;
        }
    };

    public abstract void onFinish();

    public abstract void onTick(long j);

    public CountDownTimer(long j, long j2) {
        this.mMillisInFuture = j;
        this.mCountdownInterval = j2;
        if (!HandlerExecutor.c()) {
            HandlerThread handlerThread = new HandlerThread("CountDownTimerThread");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper(), this.mCallback);
            return;
        }
        this.mHandler = new Handler(this.mCallback);
    }

    public void setMillisInFuture(long j, long j2) {
        LogUtil.c(TAG, "setMillisInFuture mills:", Long.valueOf(j), " interval:", Long.valueOf(j2));
        if (j <= 0 || j2 <= 0) {
            LogUtil.e(TAG, "setMillisInFuture mMillisInFuture is not positive");
            return;
        }
        this.mMillisInFuture = j;
        this.mCountdownInterval = j2;
        this.mHandler.removeMessages(1);
        this.mIsCancelled = false;
        this.mStopTimeInFuture = SystemClock.elapsedRealtime() + this.mMillisInFuture;
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1));
    }

    public final void cancel() {
        LogUtil.c(TAG, "countDownTimer cancel");
        this.mIsCancelled = true;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
        this.mCountdownInterval = 0L;
    }

    public final void pause() {
        if (this.mHandler != null) {
            LogUtil.c(TAG, "countDownTimer pause");
            this.mHandler.removeMessages(1);
        }
    }

    public final CountDownTimer start() {
        this.mIsCancelled = false;
        if (this.mMillisInFuture <= 0) {
            LogUtil.e(TAG, "start mMillisInFuture is not positive");
            onFinish();
            return this;
        }
        LogUtil.c(TAG, "countDownTimer start");
        this.mStopTimeInFuture = SystemClock.elapsedRealtime() + this.mMillisInFuture;
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1));
        return this;
    }

    public long getCurrentTickInterval() {
        return this.mCountdownInterval;
    }
}
