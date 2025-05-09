package com.huawei.hihealth.motion.service.healthdevice;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.health.IHealthDeviceOperManager;
import com.huawei.hihealth.motion.HealthOpensdkClient;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.motion.IServiceReady;
import com.huawei.hihealth.motion.common.AsyncInvocationHandler;
import com.huawei.hihealth.motion.common.ServiceRef;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.lang.reflect.Proxy;

/* loaded from: classes.dex */
public class HealthDeviceOper implements IHealthDeviceOper, IServiceReady {
    private static IExecuteResult b;
    private static HealthDeviceOper e;
    private HandlerThread j = null;

    /* renamed from: a, reason: collision with root package name */
    private WorkerHandler f4136a = null;
    private String c = null;
    private boolean h = false;
    private Context d = null;
    private ServiceRef<IHealthDeviceOperManager> g = null;

    public static IHealthDeviceOper b(Context context, IExecuteResult iExecuteResult) {
        synchronized (HealthDeviceOper.class) {
            HealthDeviceOper healthDeviceOper = e;
            if (healthDeviceOper != null) {
                return (IHealthDeviceOper) Proxy.newProxyInstance(healthDeviceOper.getClass().getClassLoader(), e.getClass().getInterfaces(), new AsyncInvocationHandler(e));
            }
            if (context == null) {
                return null;
            }
            HealthDeviceOper healthDeviceOper2 = new HealthDeviceOper();
            e = healthDeviceOper2;
            b = iExecuteResult;
            IHealthDeviceOper iHealthDeviceOper = (IHealthDeviceOper) Proxy.newProxyInstance(healthDeviceOper2.getClass().getClassLoader(), e.getClass().getInterfaces(), new AsyncInvocationHandler(e));
            iHealthDeviceOper.init(context.getApplicationContext());
            return iHealthDeviceOper;
        }
    }

    @Override // com.huawei.hihealth.motion.IServiceReady
    public boolean isServiceReady() {
        return this.h;
    }

    class WorkerHandler extends Handler {
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null || message.what != 1) {
                return;
            }
            HealthDeviceOper healthDeviceOper = HealthDeviceOper.this;
            healthDeviceOper.holdDevice(healthDeviceOper.c);
        }
    }

    private HealthDeviceOper() {
        d();
    }

    private void d() {
        HandlerThread handlerThread = new HandlerThread("Health_sdk");
        this.j = handlerThread;
        handlerThread.start();
        this.f4136a = new WorkerHandler(this.j.getLooper());
    }

    @Override // com.huawei.hihealth.motion.service.healthdevice.IHealthDeviceOper
    public void init(Context context) {
        this.d = context;
        Log.d("healthOpenSDK_HealthDeviceOper", "HealthDeviceOper bindDaemonService...");
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.device.oper");
        intent.setPackage(HealthOpensdkClient.b());
        intent.setClassName(HealthOpensdkClient.b(), "com.huawei.health.manager.DaemonService");
        this.g = new ServiceRef<IHealthDeviceOperManager>(this.d, intent) { // from class: com.huawei.hihealth.motion.service.healthdevice.HealthDeviceOper.1
            @Override // com.huawei.hihealth.motion.common.ServiceRef
            public void onRebind() {
            }

            @Override // com.huawei.hihealth.motion.common.ServiceRef
            /* renamed from: bwF_, reason: merged with bridge method [inline-methods] */
            public IHealthDeviceOperManager transf(IBinder iBinder) {
                return IHealthDeviceOperManager.Stub.asInterface(iBinder);
            }

            @Override // com.huawei.hihealth.motion.common.ServiceRef
            public void onServiceReady() {
                HealthDeviceOper.this.h = true;
                if (HealthDeviceOper.b != null) {
                    HealthDeviceOper.b.onSuccess(null);
                }
            }

            @Override // com.huawei.hihealth.motion.common.ServiceRef
            public void onServiceFailed() {
                if (HealthDeviceOper.b != null) {
                    HealthDeviceOper.b.onFailed(null);
                }
            }

            @Override // com.huawei.hihealth.motion.common.ServiceRef
            public void onServiceException() {
                HealthDeviceOper.this.h = false;
                if (HealthDeviceOper.b != null) {
                    HealthDeviceOper.b.onServiceException(null);
                }
            }
        };
    }

    @Override // com.huawei.hihealth.motion.service.healthdevice.IHealthDeviceOper
    public void holdDevice(String str) {
        Log.d("healthOpenSDK_HealthDeviceOper", "HealthDeviceOper holdDevice()");
        this.c = str;
        WorkerHandler workerHandler = this.f4136a;
        if (workerHandler != null && workerHandler.hasMessages(1)) {
            this.f4136a.removeMessages(1);
        }
        IHealthDeviceOperManager iHealthDeviceOperManager = this.g.get();
        if (iHealthDeviceOperManager != null) {
            try {
                iHealthDeviceOperManager.holdDevice(str);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
            WorkerHandler workerHandler2 = this.f4136a;
            if (workerHandler2 != null) {
                workerHandler2.sendEmptyMessageDelayed(1, PreConnectManager.CONNECT_INTERNAL);
            }
        }
    }

    @Override // com.huawei.hihealth.motion.service.healthdevice.IHealthDeviceOper
    public void releaseDevice(String str) {
        Log.d("healthOpenSDK_HealthDeviceOper", "HealthDeviceOper releaseDevice()");
        WorkerHandler workerHandler = this.f4136a;
        if (workerHandler != null && workerHandler.hasMessages(1)) {
            this.f4136a.removeMessages(1);
        }
        IHealthDeviceOperManager iHealthDeviceOperManager = this.g.get();
        if (iHealthDeviceOperManager != null) {
            try {
                iHealthDeviceOperManager.releaseDevice(str);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.Releasable
    public void release() {
        Log.d("healthOpenSDK_HealthDeviceOper", "release() ...");
        HandlerThread handlerThread = this.j;
        if (handlerThread != null) {
            handlerThread.quit();
            try {
                this.j.join(100L);
            } catch (InterruptedException unused) {
                Log.e("healthOpenSDK_HealthDeviceOper", "worker thread couldnt join");
            }
            this.j = null;
            WorkerHandler workerHandler = this.f4136a;
            if (workerHandler != null) {
                workerHandler.removeCallbacksAndMessages(null);
                this.f4136a = null;
            }
        }
        this.g.release();
    }

    public static void e() {
        synchronized (HealthDeviceOper.class) {
            IHealthDeviceOper b2 = b(null, null);
            if (b2 == null) {
                Log.w("healthOpenSDK_HealthDeviceOper", "no instance need release");
                e = null;
                b = null;
            } else {
                b2.release();
                e = null;
                b = null;
            }
        }
    }
}
