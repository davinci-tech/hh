package com.huawei.hihealth.motion.service.stepcounter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.huawei.health.IDaemonRemoteManager;
import com.huawei.hihealth.motion.HealthOpensdkClient;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.motion.IFlushResult;
import com.huawei.hihealth.motion.IServiceReady;
import com.huawei.hihealth.motion.RealStepCallback;
import com.huawei.hihealth.motion.common.AsyncInvocationHandler;
import com.huawei.hihealth.motion.common.ServiceRef;
import com.huawei.hihealth.motion.util.ExecuteResultLocalToRemote;
import com.huawei.hihealth.motion.util.RealStepReportProxy;
import com.huawei.hihealth.motion.util.ResultLocalToRemote;
import com.huawei.hihealth.motion.util.StepLocalToRemoteProxy;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HwStepCounter implements IHwStepCounter, IServiceReady {
    private static IExecuteResult b;
    private static HwStepCounter e;
    private Context d = null;
    private boolean c = false;
    private ServiceRef<IDaemonRemoteManager> h = null;
    private List<StepLocalToRemoteProxy> j = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<RealStepReportProxyRecord> f4137a = new ArrayList();

    public static IHwStepCounter e(Context context, IExecuteResult iExecuteResult) {
        synchronized (HwStepCounter.class) {
            HwStepCounter hwStepCounter = e;
            if (hwStepCounter != null) {
                return (IHwStepCounter) Proxy.newProxyInstance(hwStepCounter.getClass().getClassLoader(), e.getClass().getInterfaces(), new AsyncInvocationHandler(e));
            }
            if (context == null) {
                return null;
            }
            HwStepCounter hwStepCounter2 = new HwStepCounter();
            e = hwStepCounter2;
            b = iExecuteResult;
            IHwStepCounter iHwStepCounter = (IHwStepCounter) Proxy.newProxyInstance(hwStepCounter2.getClass().getClassLoader(), e.getClass().getInterfaces(), new AsyncInvocationHandler(e));
            iHwStepCounter.init(context.getApplicationContext());
            return iHwStepCounter;
        }
    }

    private HwStepCounter() {
    }

    @Override // com.huawei.hihealth.motion.IServiceReady
    public boolean isServiceReady() {
        return this.c;
    }

    static class RealStepReportProxyRecord {
        int b;
        RealStepReportProxy e;

        RealStepReportProxyRecord(RealStepReportProxy realStepReportProxy, int i) {
            this.e = realStepReportProxy;
            this.b = i;
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void init(Context context) {
        this.d = context;
        Log.d("HealthOpenSDK_HSC", "bindStepService ...");
        Intent intent = new Intent();
        intent.setPackage(HealthOpensdkClient.b());
        intent.setClassName(HealthOpensdkClient.b(), "com.huawei.health.manager.DaemonService");
        this.h = new ServiceRef<IDaemonRemoteManager>(this.d, intent, true) { // from class: com.huawei.hihealth.motion.service.stepcounter.HwStepCounter.1
            @Override // com.huawei.hihealth.motion.common.ServiceRef
            /* renamed from: bwG_, reason: merged with bridge method [inline-methods] */
            public IDaemonRemoteManager transf(IBinder iBinder) {
                return IDaemonRemoteManager.Stub.asInterface(iBinder);
            }

            @Override // com.huawei.hihealth.motion.common.ServiceRef
            public void onRebind() {
                int i;
                synchronized (HwStepCounter.this.j) {
                    for (int i2 = 0; i2 < HwStepCounter.this.j.size(); i2++) {
                        HwStepCounter hwStepCounter = HwStepCounter.this;
                        hwStepCounter.c((StepLocalToRemoteProxy) hwStepCounter.j.get(i2));
                    }
                }
                synchronized (HwStepCounter.this.f4137a) {
                    for (i = 0; i < HwStepCounter.this.f4137a.size(); i++) {
                        HwStepCounter hwStepCounter2 = HwStepCounter.this;
                        hwStepCounter2.d(((RealStepReportProxyRecord) hwStepCounter2.f4137a.get(i)).e, ((RealStepReportProxyRecord) HwStepCounter.this.f4137a.get(i)).b);
                    }
                }
            }

            @Override // com.huawei.hihealth.motion.common.ServiceRef
            public void onServiceReady() {
                HwStepCounter.this.c = true;
                if (HwStepCounter.b != null) {
                    HwStepCounter.b.onSuccess(null);
                }
            }

            @Override // com.huawei.hihealth.motion.common.ServiceRef
            public void onServiceFailed() {
                if (HwStepCounter.b != null) {
                    HwStepCounter.b.onFailed(null);
                }
            }

            @Override // com.huawei.hihealth.motion.common.ServiceRef
            public void onServiceException() {
                HwStepCounter.this.c = false;
                if (HwStepCounter.b != null) {
                    HwStepCounter.b.onServiceException(null);
                }
            }
        };
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void setUserInfo(Bundle bundle, IExecuteResult iExecuteResult) {
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.setUserInfo(bundle);
                if (iExecuteResult != null) {
                    iExecuteResult.onSuccess(null);
                }
                Log.d("HealthOpenSDK_HSC", "setUserInfo success");
                return;
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "setUserInfo : RemoteEx" + e2.getMessage());
            }
        }
        if (iExecuteResult != null) {
            iExecuteResult.onFailed(null);
        }
        Log.d("HealthOpenSDK_HSC", "setUserInfo failed");
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void setStepsNotifiEnable(boolean z, IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "setStepsNotifiEnable " + z);
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.setStepsNotifiEnable(z);
                if (iExecuteResult != null) {
                    iExecuteResult.onSuccess(null);
                }
                Log.d("HealthOpenSDK_HSC", "setStepsNotifiEnable success");
                return;
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "setStepsNotifiEnable : RemoteEx" + e2.getMessage());
            }
        }
        if (iExecuteResult != null) {
            iExecuteResult.onFailed(null);
        }
        Log.d("HealthOpenSDK_HSC", "setStepsNotifiEnable failed");
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void flushCacheToDB(IFlushResult iFlushResult) {
        Log.d("HealthOpenSDK_HSC", "flushCacheToDB ");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager == null) {
            if (iFlushResult != null) {
                iFlushResult.onServiceException(null);
            }
        } else {
            try {
                iDaemonRemoteManager.flushCacheToDB(new ResultLocalToRemote(iFlushResult));
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "flushCacheToDB : RemoteEx" + e2.getMessage());
            }
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void setGoalNotifiEnable(boolean z, IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "setGoalNotifiEnable " + z);
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.setGoalNotifiEnable(z);
                if (iExecuteResult != null) {
                    iExecuteResult.onSuccess(null);
                }
                Log.d("HealthOpenSDK_HSC", "setGoalNotifiEnable success");
                return;
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "setGoalNotifiEnable : RemoteEx" + e2.getMessage());
            }
        }
        if (iExecuteResult != null) {
            iExecuteResult.onFailed(null);
        }
        Log.d("HealthOpenSDK_HSC", "setGoalNotifiEnable failed");
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void registerStepReport(ICommonReport iCommonReport, IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "registerStepReport " + iCommonReport);
        if (c(iCommonReport) != null) {
            Log.w("HealthOpenSDK_HSC", "registerStepReport report already exists!");
            return;
        }
        StepLocalToRemoteProxy stepLocalToRemoteProxy = new StepLocalToRemoteProxy(iCommonReport);
        if (c(stepLocalToRemoteProxy)) {
            synchronized (this.j) {
                this.j.add(stepLocalToRemoteProxy);
            }
            iExecuteResult.onSuccess(null);
            return;
        }
        iExecuteResult.onFailed(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(StepLocalToRemoteProxy stepLocalToRemoteProxy) {
        Log.d("HealthOpenSDK_HSC", "registerStepReportInner " + stepLocalToRemoteProxy);
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                return iDaemonRemoteManager.registerStepReportCallback(stepLocalToRemoteProxy);
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "registerStepCallbackInter : RemoteEx" + e2.getMessage());
            }
        }
        return false;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void unRegisterStepReport(ICommonReport iCommonReport) {
        Log.d("HealthOpenSDK_HSC", "unRegisterStepReport " + iCommonReport);
        StepLocalToRemoteProxy c = c(iCommonReport);
        if (c != null && b(c)) {
            synchronized (this.j) {
                this.j.remove(c);
            }
        }
    }

    private boolean b(StepLocalToRemoteProxy stepLocalToRemoteProxy) {
        Log.d("HealthOpenSDK_HSC", "unRegisterStepReportInner " + stepLocalToRemoteProxy);
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                return iDaemonRemoteManager.unRegisterStepReportCallback(stepLocalToRemoteProxy);
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "unRegisterStepReport : RemoteEx" + e2.getMessage());
            }
        }
        return false;
    }

    private StepLocalToRemoteProxy c(ICommonReport iCommonReport) {
        synchronized (this.j) {
            for (int i = 0; i < this.j.size(); i++) {
                if (this.j.get(i).isScameLocalCallback(iCommonReport)) {
                    return this.j.get(i);
                }
            }
            return null;
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void registerRealTimeReport(RealStepCallback realStepCallback, int i) {
        Log.d("HealthOpenSDK_HSC", "registerRealTimeReport ...");
        if (realStepCallback == null) {
            return;
        }
        RealStepReportProxy realStepReportProxy = new RealStepReportProxy(realStepCallback);
        if (d(realStepReportProxy, i)) {
            synchronized (this.f4137a) {
                this.f4137a.add(new RealStepReportProxyRecord(realStepReportProxy, i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(RealStepReportProxy realStepReportProxy, int i) {
        Log.d("HealthOpenSDK_HSC", "registerRealTimeReportInner ...");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        boolean z = false;
        if (iDaemonRemoteManager != null) {
            try {
                z = iDaemonRemoteManager.registerRealTimeStepReport(realStepReportProxy, i);
                Log.d("HealthOpenSDK_HSC", "registerRealTimeReport reporter：" + realStepReportProxy);
                return z;
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "registerRealTimeReport : RemoteEx" + e2.getMessage());
                return z;
            }
        }
        Log.w("HealthOpenSDK_HSC", "registerRealTimeReport : mDaemonRemoteManager = null");
        return false;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void unRegisterRealTimeReport() {
        if (d()) {
            synchronized (this.f4137a) {
                this.f4137a.clear();
            }
        }
    }

    private boolean d() {
        Log.d("HealthOpenSDK_HSC", "unRegisterRealTimeReportInner");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                return iDaemonRemoteManager.unRegisterRealTimeStepReport();
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "unRegisterRealTimeReport : RemoteEx" + e2.getMessage());
            }
        } else {
            Log.w("HealthOpenSDK_HSC", "unRegisterRealTimeReport : mDaemonRemoteManager = null");
        }
        return false;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getNotificationSupport(IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "getNotificationSupport");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                Bundle bundle = new Bundle();
                boolean notificationSupport = iDaemonRemoteManager.getNotificationSupport();
                bundle.putBoolean("notificationSupport", notificationSupport);
                if (iExecuteResult != null) {
                    iExecuteResult.onSuccess(bundle);
                }
                Log.d("HealthOpenSDK_HSC", "getNotificationSupport success: " + notificationSupport);
                return;
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "setNotificationEnable : RemoteEx" + e2.getMessage());
            }
        }
        if (iExecuteResult != null) {
            iExecuteResult.onFailed(null);
        }
        Log.d("HealthOpenSDK_HSC", "getNotificationSupport failed");
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getStepsNotifiState(IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "getStepsNotifiState");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                Bundle bundle = new Bundle();
                boolean stepsNotifiState = iDaemonRemoteManager.getStepsNotifiState();
                bundle.putBoolean("stepsNotifiState", stepsNotifiState);
                if (iExecuteResult != null) {
                    iExecuteResult.onSuccess(bundle);
                }
                Log.d("HealthOpenSDK_HSC", "getStepsNotifiState success: " + stepsNotifiState);
                return;
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "getStepsNotifiState : RemoteEx" + e2.getMessage());
            }
        }
        if (iExecuteResult != null) {
            iExecuteResult.onFailed(null);
        }
        Log.d("HealthOpenSDK_HSC", "getStepsNotifiState failed");
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getGoalNotifiState(IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "getGoalNotifiState");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                Bundle bundle = new Bundle();
                boolean goalNotifiState = iDaemonRemoteManager.getGoalNotifiState();
                bundle.putBoolean("goalNotifiState", goalNotifiState);
                if (iExecuteResult != null) {
                    iExecuteResult.onSuccess(bundle);
                }
                Log.d("HealthOpenSDK_HSC", "getGoalNotifiState success: " + goalNotifiState);
                return;
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "getGoalNotifiState : RemoteEx" + e2.getMessage());
            }
        }
        if (iExecuteResult != null) {
            iExecuteResult.onFailed(null);
        }
        Log.d("HealthOpenSDK_HSC", "getGoalNotifiState failed");
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void setStepCounterSwitchStatus(boolean z) {
        Log.d("HealthOpenSDK_HSC", "setStepCounterSwitchStatus " + z);
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.setStepCounterSwitchStatus(z);
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "setStepCounterSwitchStatus : RemoteEx" + e2.getMessage());
            }
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public boolean getStepCounterSwitchStatus() {
        Log.d("HealthOpenSDK_HSC", "getStepCounterSwitchStatus");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                return iDaemonRemoteManager.getStepCounterSwitchStatus();
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "setStepCounterSwitchStatus : RemoteEx" + e2.getMessage());
            }
        }
        return false;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public int getStepCounterClass() {
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        int i = 3;
        if (iDaemonRemoteManager != null) {
            try {
                i = iDaemonRemoteManager.getStepCounterClass();
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "getStepCounterClass : RemoteEx" + e2.getMessage());
            }
            Log.d("HealthOpenSDK_HSC", "getStepCounterClass result=" + i);
        }
        return i;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getSleepData(IExecuteResult iExecuteResult) {
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        Log.d("HealthOpenSDK_HSC", "mDaemonRemoteManager:" + iDaemonRemoteManager);
        try {
            iDaemonRemoteManager.getSleepData(new ExecuteResultLocalToRemote(iExecuteResult));
        } catch (Exception e2) {
            Log.w("HealthOpenSDK_HSC", "getSleepData : RemoteEx" + e2.getMessage());
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getTodaySportData(IExecuteResult iExecuteResult) {
        try {
            this.h.get().getTodaySportData(new ExecuteResultLocalToRemote(iExecuteResult));
        } catch (Exception e2) {
            Log.w("HealthOpenSDK_HSC", "getTodaySportData : RemoteEx" + e2.getMessage());
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getStandSteps(IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "getStandSteps callback:" + iExecuteResult);
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.getStandSteps(new ExecuteResultLocalToRemote(iExecuteResult));
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "getStandSteps : Exception " + e2.getMessage());
            }
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void getDebugInfo(IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "getDebugInfo callback:" + iExecuteResult);
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.getDebugInfo(new ExecuteResultLocalToRemote(iExecuteResult));
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "getDebugInfo : RemoteEx" + e2.getMessage());
            }
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void isNeedPromptKeepAlive(IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "isNeedPromptKeepAlive callback:" + iExecuteResult);
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager == null) {
            Log.w("HealthOpenSDK_HSC", "isNeedPromptKeepAlive remote null,return");
            return;
        }
        try {
            iDaemonRemoteManager.isNeedPromptKeepAlive(new ExecuteResultLocalToRemote(iExecuteResult));
        } catch (Exception e2) {
            Log.w("HealthOpenSDK_HSC", "isNeedPromptKeepAlive : RemoteEx" + e2.getMessage());
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void makePromptNoSense() {
        Log.d("HealthOpenSDK_HSC", "makePromptNoSense");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager == null) {
            Log.w("HealthOpenSDK_HSC", "makePromptNoSense remote null,return");
            return;
        }
        try {
            iDaemonRemoteManager.makePromptNoSense();
        } catch (Exception e2) {
            Log.w("HealthOpenSDK_HSC", "makePromptNoSense : RemoteEx" + e2.getMessage());
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void notifyUserInfoChanged(IExecuteResult iExecuteResult) {
        Log.d("HealthOpenSDK_HSC", "notifyUserInfoChanged ");
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.notifyUserInfoChanged();
                if (iExecuteResult != null) {
                    iExecuteResult.onSuccess(null);
                }
                Log.d("HealthOpenSDK_HSC", "notifyUserInfoChanged success");
                return;
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "notifyUserInfoChanged : RemoteEx" + e2.getMessage());
            }
        }
        if (iExecuteResult != null) {
            iExecuteResult.onFailed(null);
        }
        Log.d("HealthOpenSDK_HSC", "notifyUserInfoChanged failed");
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void tickTrackDog() {
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.tickTrackDog();
                Log.d("HealthOpenSDK_HSC", "tickTrackDog over ");
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "tickTrackDog : RemoteEx " + e2.getMessage());
            }
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public void switchTrackMonitor(boolean z) {
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        if (iDaemonRemoteManager != null) {
            try {
                iDaemonRemoteManager.switchTrackMonitor(z);
                Log.d("HealthOpenSDK_HSC", "switchTrackMonitor " + z);
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "switchTrackMonitor : RemoteEx " + e2.getMessage());
            }
        }
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.IHwStepCounter
    public int getDeviceOriginalClass() {
        IDaemonRemoteManager iDaemonRemoteManager = this.h.get();
        int i = 3;
        if (iDaemonRemoteManager != null) {
            try {
                i = iDaemonRemoteManager.getDeviceOriginalClass();
            } catch (Exception e2) {
                Log.w("HealthOpenSDK_HSC", "getDeviceOriginalClass : RemoteEx" + e2.getMessage());
            }
            Log.d("HealthOpenSDK_HSC", "getDeviceOriginalClass result=" + i);
        }
        return i;
    }

    @Override // com.huawei.hihealth.motion.service.stepcounter.Releasable
    public void release() {
        Log.i("HealthOpenSDK_HSC", "release");
        synchronized (this.j) {
            for (int i = 0; i < this.j.size(); i++) {
                b(this.j.get(i));
            }
            this.j.clear();
        }
        synchronized (this.f4137a) {
            if (this.f4137a.size() != 0) {
                d();
                this.f4137a.clear();
            }
        }
        this.h.release();
    }

    public static void e() {
        synchronized (HwStepCounter.class) {
            IHwStepCounter e2 = e((Context) null, (IExecuteResult) null);
            if (e2 == null) {
                Log.w("HealthOpenSDK_HSC", "no instance need release");
                e = null;
                b = null;
            } else {
                e2.release();
                e = null;
                b = null;
            }
        }
    }
}
