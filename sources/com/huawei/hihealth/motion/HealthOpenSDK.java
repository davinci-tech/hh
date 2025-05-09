package com.huawei.hihealth.motion;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.common.Constant;
import com.huawei.health.ITrackDataReport;
import com.huawei.health.ITrackSportManager;
import com.huawei.hihealth.motion.service.OpenSDKServiceRegister;
import com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter;
import com.huawei.hihealth.motion.service.stepcounter.NullHwStepCounter;
import com.huawei.hwsmartinteractmgr.ISmartInteract;
import com.huawei.hwsmartinteractmgr.ISmartMsgReadResultListener;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HealthOpenSDK extends HealthOpenSDKCommon {
    private Object d = new Object();
    private Context j = null;
    private String g = null;
    private ITrackSportManager f = null;
    private ISmartInteract i = null;

    /* renamed from: a, reason: collision with root package name */
    private IExecuteResult f4132a = null;
    private List<TrackLocalToRemoteProxy> o = new ArrayList();
    private HandlerThread n = null;
    private WorkerHandler h = null;
    private IHwStepCounter e = new NullHwStepCounter();
    ServiceConnection b = new ServiceConnection() { // from class: com.huawei.hihealth.motion.HealthOpenSDK.4
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i("HealthOpenSDK", Constant.SERVICE_CONNECT_MESSAGE);
            HealthOpenSDK.this.i = ISmartInteract.Stub.asInterface(iBinder);
            synchronized (HealthOpenSDK.this.d) {
                HealthOpenSDK.this.d.notifyAll();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("HealthOpenSDK", "onServiceDisconnected");
            Log.d("HealthOpenSDK", "onServiceDisconnected " + componentName);
            HealthOpenSDK.this.i = null;
            synchronized (HealthOpenSDK.this.d) {
                HealthOpenSDK.this.d.notifyAll();
            }
        }
    };
    ServiceConnection c = new ServiceConnection() { // from class: com.huawei.hihealth.motion.HealthOpenSDK.5
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("HealthOpenSDK", "name : " + componentName + " service " + iBinder);
            HealthOpenSDK.this.f = ITrackSportManager.Stub.asInterface(iBinder);
            if (HealthOpenSDK.this.f == null || HealthOpenSDK.this.f4132a == null) {
                return;
            }
            HealthOpenSDK.this.f4132a.onSuccess(null);
            Log.d("HealthOpenSDK", "Bind Success " + System.currentTimeMillis());
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("HealthOpenSDK", "onServiceDisconnected " + componentName);
            HealthOpenSDK.this.f4132a.onServiceException(null);
            HealthOpenSDK.this.f();
            HealthOpenSDK.this.f = null;
        }
    };

    @Override // com.huawei.hihealth.motion.HealthOpenSDKCommon
    @Deprecated
    public int initSDK(Context context, final IExecuteResult iExecuteResult, String str) {
        Log.d("HealthOpenSDK", "deprecated initSDK : " + str + ", ver:" + d() + ", this:" + this);
        if (context == null || iExecuteResult == null) {
            Log.e("HealthOpenSDK", "deprecated initSDK :context == null || cb == null");
            return -2;
        }
        if (this.n != null) {
            Log.e("HealthOpenSDK", "deprecated initSDK :mWorkingThread != null");
            return -3;
        }
        this.j = context.getApplicationContext();
        this.g = str;
        HandlerThread handlerThread = new HandlerThread("health_sdk");
        this.n = handlerThread;
        handlerThread.start();
        this.h = new WorkerHandler(this.n.getLooper());
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hihealth.motion.HealthOpenSDK.1
            @Override // java.lang.Runnable
            public void run() {
                HealthOpenSDK healthOpenSDK = HealthOpenSDK.this;
                healthOpenSDK.e = (IHwStepCounter) healthOpenSDK.d("hwstepcounter", iExecuteResult);
                Log.i("HealthOpenSDK", "deprecated initSDK handler iHwStepCounter = " + HealthOpenSDK.this.e);
            }
        });
        Log.i("HealthOpenSDK", "deprecated initSDK iHwStepCounter = " + this.e);
        return 0;
    }

    @Override // com.huawei.hihealth.motion.HealthOpenSDKCommon
    public void destorySDK() {
        Log.d("HealthOpenSDK", "destorySDK " + this.g + ", this:" + this);
        if (this.e != null) {
            Log.d("HealthOpenSDK", "destorySDK iHwStepCounter != null");
            this.e = new NullHwStepCounter();
        }
        OpenSDKServiceRegister.c(this);
        if (this.i != null) {
            this.j.unbindService(this.b);
            this.i = null;
        }
        HandlerThread handlerThread = this.n;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.n = null;
        }
        if (this.h != null) {
            this.h = null;
        }
    }

    public String d() {
        Log.d("HealthOpenSDK", "getSDKVersion 2.1.0.100");
        return "2.1.0.100";
    }

    private boolean c(final TrackLocalToRemoteProxy trackLocalToRemoteProxy) {
        Log.d("HealthOpenSDK", "registerTrackingReportInter:" + trackLocalToRemoteProxy + " at:" + System.currentTimeMillis());
        WorkerHandler workerHandler = this.h;
        if (workerHandler == null) {
            return false;
        }
        workerHandler.post(new Runnable() { // from class: com.huawei.hihealth.motion.HealthOpenSDK.2
            @Override // java.lang.Runnable
            public void run() {
                if (HealthOpenSDK.this.f == null || trackLocalToRemoteProxy == null) {
                    return;
                }
                try {
                    HealthOpenSDK.this.f.registerDataCallback(trackLocalToRemoteProxy);
                    HealthOpenSDK.this.o.add(trackLocalToRemoteProxy);
                } catch (Exception e) {
                    Log.w("HealthOpenSDK", "registerTrackCallbackInter : RemoteEx" + e.getMessage());
                }
            }
        });
        return true;
    }

    @Deprecated
    public boolean b(ICommonReport iCommonReport, IExecuteResult iExecuteResult) {
        this.e.registerStepReport(iCommonReport, iExecuteResult);
        return true;
    }

    @Deprecated
    public void d(ICommonReport iCommonReport) {
        this.e.unRegisterStepReport(iCommonReport);
    }

    @Deprecated
    public boolean d(RealStepCallback realStepCallback, int i) {
        this.e.registerRealTimeReport(realStepCallback, i);
        return true;
    }

    @Deprecated
    public boolean b() {
        this.e.unRegisterRealTimeReport();
        return true;
    }

    @Deprecated
    public void a(IFlushResult iFlushResult) {
        this.e.flushCacheToDB(iFlushResult);
    }

    @Deprecated
    public void d(boolean z, IExecuteResult iExecuteResult) {
        this.e.setStepsNotifiEnable(z, iExecuteResult);
    }

    @Deprecated
    public void e(boolean z, IExecuteResult iExecuteResult) {
        this.e.setGoalNotifiEnable(z, iExecuteResult);
    }

    @Deprecated
    public void a(IExecuteResult iExecuteResult) {
        this.e.getNotificationSupport(iExecuteResult);
    }

    @Deprecated
    public void d(IExecuteResult iExecuteResult) {
        this.e.getStepsNotifiState(iExecuteResult);
    }

    @Deprecated
    public void c(IExecuteResult iExecuteResult) {
        this.e.getGoalNotifiState(iExecuteResult);
    }

    public int c() {
        return this.e.getDeviceOriginalClass();
    }

    @Deprecated
    public boolean b(IExecuteResult iExecuteResult) {
        this.e.getTodaySportData(iExecuteResult);
        return true;
    }

    /* renamed from: com.huawei.hihealth.motion.HealthOpenSDK$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ HealthOpenSDK c;
        final /* synthetic */ ISmartMsgResult e;

        @Override // java.lang.Runnable
        public void run() {
            if (this.c.i == null) {
                this.c.i();
            }
            try {
                if (this.c.i != null) {
                    this.c.i.checkSmartMsg(1, this.c.new SmartResultLocalToRemote(this.e));
                } else {
                    Log.e("HealthOpenSDK", "bindService fail, mISmartInteract is null");
                    this.e.onResult(-1, null);
                }
            } catch (Exception e) {
                this.e.onResult(-1, null);
                Log.w("HealthOpenSDK", "getSmartMsg : RemoteEx" + e.getMessage());
            }
        }
    }

    @Deprecated
    public boolean e(IExecuteResult iExecuteResult) {
        this.e.getStandSteps(iExecuteResult);
        return true;
    }

    @Deprecated
    public void i(IExecuteResult iExecuteResult) {
        this.e.isNeedPromptKeepAlive(iExecuteResult);
    }

    @Deprecated
    public void e() {
        this.e.makePromptNoSense();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i() {
        Log.i("HealthOpenSDK", "bindSmartService");
        Intent intent = new Intent();
        intent.setAction("com.huawei.hwsmartinteractmgr.service.SmartInteractService");
        intent.setPackage(HealthOpensdkClient.b());
        boolean bindService = this.j.bindService(intent, this.b, 1);
        Log.i("HealthOpenSDK", "bindSmartService end, ret=" + bindService);
        synchronized (this.d) {
            try {
                this.d.wait(5000L);
            } catch (InterruptedException e) {
                Log.e("HealthOpenSDK", " bindStepService InterruptedException = " + e.getMessage());
            }
        }
        return bindService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        Intent intent = new Intent();
        intent.setAction("com.huawei.healthcloud.plugintrack.trackSdk.TrackService");
        intent.setPackage(HealthOpensdkClient.b());
        boolean bindService = this.j.bindService(intent, this.c, 0);
        Log.d("HealthOpenSDK", "Bind Track Service at " + System.currentTimeMillis());
        return bindService;
    }

    public void d(boolean z) {
        try {
            this.e.switchTrackMonitor(z);
            Log.d("HealthOpenSDK", "switchTrackMonitor " + z);
        } catch (Exception e) {
            Log.w("HealthOpenSDK", "switchTrackMonitor : RemoteEx " + e.getMessage());
        }
    }

    public void a() {
        try {
            this.e.tickTrackDog();
            Log.d("HealthOpenSDK", "tickTrackDog over ");
        } catch (Exception e) {
            Log.w("HealthOpenSDK", "tickTrackDog : RemoteEx " + e.getMessage());
        }
    }

    class WorkerHandler extends Handler {
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null || message.what != 101) {
                return;
            }
            TrackLocalToRemoteProxy trackLocalToRemoteProxy = (TrackLocalToRemoteProxy) message.obj;
            if (HealthOpenSDK.this.f != null || HealthOpenSDK.this.j()) {
                HealthOpenSDK.this.e(100, trackLocalToRemoteProxy);
            } else if (trackLocalToRemoteProxy != null) {
                trackLocalToRemoteProxy.d(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, TrackLocalToRemoteProxy trackLocalToRemoteProxy) {
        for (int i2 = 0; i2 < i; i2++) {
            if (c(trackLocalToRemoteProxy)) {
                if (trackLocalToRemoteProxy != null) {
                    trackLocalToRemoteProxy.d(true);
                    return;
                }
                return;
            }
            try {
                Log.d("HealthOpenSDK", "Try register sleep:" + System.currentTimeMillis());
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                Log.w("HealthOpenSDK", "tryToRegisterTrack : RemoteEx" + e.getMessage());
            }
        }
        if (trackLocalToRemoteProxy != null) {
            trackLocalToRemoteProxy.d(false);
        }
    }

    class TrackLocalToRemoteProxy extends ITrackDataReport.Stub {
        private ICommonReport d;
        private IExecuteResult e;

        public void d(boolean z) {
            IExecuteResult iExecuteResult = this.e;
            if (iExecuteResult != null) {
                if (z) {
                    iExecuteResult.onSuccess(null);
                } else {
                    iExecuteResult.onFailed(null);
                }
            }
        }

        @Override // com.huawei.health.ITrackDataReport
        public void report(Bundle bundle) throws RemoteException {
            ICommonReport iCommonReport = this.d;
            if (iCommonReport != null) {
                iCommonReport.report(bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        List<TrackLocalToRemoteProxy> list = this.o;
        if (list != null) {
            synchronized (list) {
                Log.d("HealthOpenSDK", "cleanTrackProxyList");
                this.o.clear();
            }
        }
    }

    class SmartResultLocalToRemote extends ISmartMsgReadResultListener.Stub {
        private ISmartMsgResult d;

        public SmartResultLocalToRemote(ISmartMsgResult iSmartMsgResult) {
            this.d = iSmartMsgResult;
        }

        @Override // com.huawei.hwsmartinteractmgr.ISmartMsgReadResultListener
        public void onResult(int i, SmartMsgDbObject smartMsgDbObject) throws RemoteException {
            this.d.onResult(i, smartMsgDbObject);
        }
    }

    public Object d(String str, IExecuteResult iExecuteResult) {
        if (iExecuteResult == null) {
            Log.e("HealthOpenSDK", "getOpenSDKService: cb is null");
            return null;
        }
        this.f4132a = iExecuteResult;
        Log.d("HealthOpenSDK", "getOpenSDKService cb = " + iExecuteResult + ", name = " + str);
        Object b = OpenSDKServiceRegister.b(this.j, str, this);
        StringBuilder sb = new StringBuilder("getOpenSDKService result = ");
        sb.append(b);
        Log.d("HealthOpenSDK", sb.toString());
        if (b != null) {
            boolean isServiceReady = ((IServiceReady) b).isServiceReady();
            Log.i("HealthOpenSDK", "getOpenSDKService: isServiceReady = " + isServiceReady);
            if (isServiceReady) {
                WorkerHandler workerHandler = this.h;
                if (workerHandler == null) {
                    Log.e("HealthOpenSDK", "mHandler == null");
                    return null;
                }
                workerHandler.post(new Runnable() { // from class: com.huawei.hihealth.motion.HealthOpenSDK.6
                    @Override // java.lang.Runnable
                    public void run() {
                        HealthOpenSDK.this.b((Object) null);
                    }
                });
            }
        }
        return b;
    }

    public void b(Object obj) {
        Log.i("HealthOpenSDK", "notifyServiceReady");
        IExecuteResult iExecuteResult = this.f4132a;
        if (iExecuteResult != null) {
            iExecuteResult.onSuccess(obj);
        }
    }

    public void d(Object obj) {
        Log.e("HealthOpenSDK", "notifyServiceReady");
        IExecuteResult iExecuteResult = this.f4132a;
        if (iExecuteResult != null) {
            iExecuteResult.onServiceException(obj);
        }
    }
}
