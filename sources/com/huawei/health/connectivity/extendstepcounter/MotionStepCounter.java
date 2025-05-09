package com.huawei.health.connectivity.extendstepcounter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.huawei.health.icommon.ISimpleResultCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.motiondetection.MotionDetectionListener;
import com.huawei.motiondetection.MotionDetectionManager;
import com.huawei.motiondetection.MotionRecoResult;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes.dex */
public class MotionStepCounter extends ExtendStepCounter {

    /* renamed from: a, reason: collision with root package name */
    private Context f2211a;
    private MotionDetectionManager d;
    private boolean c = false;
    private Handler b = new Handler();
    private MotionDetectionListener e = new MotionDetectionListener() { // from class: com.huawei.health.connectivity.extendstepcounter.MotionStepCounter.3
        @Override // com.huawei.motiondetection.MotionDetectionListener
        public void notifyMotionRecoResult(MotionRecoResult motionRecoResult) {
            if (motionRecoResult != null) {
                MotionStepCounter.this.Dn_(motionRecoResult.mMotionExtras);
            }
        }
    };

    public MotionStepCounter(Context context) {
        this.f2211a = null;
        this.d = null;
        if (context == null) {
            LogUtil.h("Step_MotionStepCounter", "MotionStepCounter context null");
        } else {
            this.f2211a = context;
            this.d = new MotionDetectionManager(context);
        }
    }

    static class e implements Runnable {
        private final WeakReference<ISimpleResultCallback> e;

        e(ISimpleResultCallback iSimpleResultCallback) {
            this.e = new WeakReference<>(iSimpleResultCallback);
        }

        @Override // java.lang.Runnable
        public void run() {
            ISimpleResultCallback iSimpleResultCallback;
            WeakReference<ISimpleResultCallback> weakReference = this.e;
            if (weakReference == null || (iSimpleResultCallback = weakReference.get()) == null) {
                return;
            }
            iSimpleResultCallback.onSuccess(null);
        }
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void init(ISimpleResultCallback iSimpleResultCallback) {
        Handler handler = this.b;
        if (handler == null) {
            LogUtil.a("Step_MotionStepCounter", "mHandler = null ");
        } else if (iSimpleResultCallback != null) {
            handler.post(new e(iSimpleResultCallback));
        } else {
            LogUtil.a("Step_MotionStepCounter", "callback = null ");
        }
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void startStepCounter() {
        LogUtil.a("Step_MotionStepCounter", "startStepCounter enter... mIsDeviceOpened = ", Boolean.valueOf(this.c));
        if (this.c) {
            return;
        }
        d();
        this.c = true;
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void stopStepCounter() {
        if (this.c) {
            e();
            this.c = false;
        }
    }

    private void d() {
        try {
            LogUtil.a("Step_MotionStepCounter", "initSensorHubManager enter... mMotionDetectionManager = ", this.d);
            MotionDetectionManager motionDetectionManager = this.d;
            if (motionDetectionManager != null) {
                motionDetectionManager.addMotionListener(this.e);
                this.d.startMotionService();
                try {
                    LogUtil.c("Step_MotionStepCounter", "initSensorHubManager isStartMotion = ", Boolean.valueOf(this.d.startMotionAppsReco(1101)));
                } catch (Error unused) {
                    LogUtil.b("Step_MotionStepCounter", "initSensorHubManager catch error");
                }
            }
        } catch (Exception unused2) {
            LogUtil.b("Step_MotionStepCounter", "initSensorHubManager catch exception");
        }
    }

    private void e() {
        try {
            MotionDetectionManager motionDetectionManager = this.d;
            if (motionDetectionManager != null) {
                motionDetectionManager.removeMotionListener(this.e);
                try {
                    LogUtil.c("Step_MotionStepCounter", "unInitSensorHubManager isStopMotion = ", Boolean.valueOf(this.d.stopMotionAppsReco(1101)));
                } catch (Error unused) {
                    LogUtil.b("Step_MotionStepCounter", "unInitSensorHubManager catch error, stop failed");
                }
                this.d.stopMotionService();
                this.d.destroy();
            }
        } catch (Exception unused2) {
            LogUtil.b("Step_MotionStepCounter", "unInitSensorHubManager catch exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Dn_(Bundle bundle) {
        if (bundle != null) {
            try {
                long j = bundle.getLong("StartTime");
                int[] intArray = bundle.getIntArray("MotionNum");
                int[] intArray2 = bundle.getIntArray("motionType");
                int[] intArray3 = bundle.getIntArray("motionLevel");
                LogUtil.c("Step_MotionStepCounter", String.format(Locale.ENGLISH, "[%d] : %s : %s : %s", Long.valueOf(j), Arrays.toString(intArray), Arrays.toString(intArray3), Arrays.toString(intArray2)));
                dataReport(this.f2211a, j, intArray, intArray3, intArray2);
            } catch (ArrayIndexOutOfBoundsException e2) {
                LogUtil.b("Step_MotionStepCounter", e2.getMessage());
            }
        }
    }
}
