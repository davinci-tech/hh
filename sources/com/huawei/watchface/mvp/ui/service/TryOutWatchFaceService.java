package com.huawei.watchface.mvp.ui.service;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import com.huawei.secure.android.common.activity.SafeService;
import com.huawei.watchface.fk;
import com.huawei.watchface.mvp.model.datatype.TryoutBean;
import com.huawei.watchface.t;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes7.dex */
public class TryOutWatchFaceService extends SafeService {
    public static final String TAG = "TryOutWatchFaceService";
    private static final int TRY_OUT_TIME = 300000;
    private static final int TRY_OUT_WATCH_FACE_FOREGROUND_SERVICE = 110;
    public static final String TRY_OUT_WATCH_FACE_SERVICE_CLASS = "com.huawei.watchface.mvp.ui.service.TryOutWatchFaceService";
    private Timer mTryOutTimer;
    private TimerTask mTryOutTimerTask;
    private TryoutBean mTryoutBean;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    class a extends TimerTask {
        private a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            HwLog.i(TryOutWatchFaceService.TAG, "TimerTask -- try out 5 min");
            t.a().a(TryOutWatchFaceService.this.mTryoutBean, "3", false);
            TryOutWatchFaceService.this.stopService();
        }
    }

    @Override // com.huawei.secure.android.common.activity.SafeService, android.app.Service
    public void onCreate() {
        super.onCreate();
        HwLog.i(TAG, "created");
    }

    @Override // com.huawei.secure.android.common.activity.SafeService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            String action = intent.getAction();
            TryoutBean tryoutBean = (TryoutBean) intent.getParcelableExtra("key_try_out_bean");
            this.mTryoutBean = tryoutBean;
            if (tryoutBean == null) {
                HwLog.w(TAG, "onStartCommand -- mTryoutBean is null");
                stopService();
                return super.onStartCommand(intent, i, i2);
            }
            HwLog.i(TAG, "onStartCommand -- action:" + action + "mHitopId:" + this.mTryoutBean.getTryOutHiTopId() + " mVersion:" + this.mTryoutBean.getVersion());
            if ("0".equals(action)) {
                startTryOutTimer();
                showNotification();
            } else {
                HwLog.w(TAG, "onStartCommand -- unKnown intent action");
                stopService();
                return super.onStartCommand(intent, i, i2);
            }
        } else {
            HwLog.w(TAG, "onStartCommand -- intent is null");
            stopService();
        }
        return super.onStartCommand(intent, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopService() {
        stopForeground(1);
        stopSelf();
    }

    private void showNotification() {
        HwLog.i(TAG, "showNotification -- api >= 26");
        Notification a2 = fk.a().a(this.mTryoutBean);
        if (a2 == null) {
            HwLog.e(TAG, "showNotification -- notification is null");
            stopSelf();
        } else if (CommonUtils.isAndroid14()) {
            HwLog.i(TAG, "showNotification -- api >= 34");
            startForeground(110, a2, 16);
        } else {
            startForeground(110, a2);
        }
    }

    public void startTryOutTimer() {
        HwLog.i(TAG, "startTryOutTimer");
        cancelTryoutTimer();
        this.mTryOutTimer = new Timer();
        a aVar = new a();
        this.mTryOutTimerTask = aVar;
        this.mTryOutTimer.schedule(aVar, 300000L);
    }

    public void cancelTryoutTimer() {
        Timer timer = this.mTryOutTimer;
        if (timer != null) {
            timer.cancel();
            this.mTryOutTimer = null;
        }
        TimerTask timerTask = this.mTryOutTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTryOutTimerTask = null;
        }
    }

    @Override // com.huawei.secure.android.common.activity.SafeService, android.app.Service
    public void onDestroy() {
        HwLog.i(TAG, "onDestroy");
        cancelTryoutTimer();
        super.onDestroy();
    }
}
