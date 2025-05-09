package com.huawei.health.manager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.IDaemonRemoteManager;
import com.huawei.health.IRealStepDataReport;
import com.huawei.health.IResultCallback;
import com.huawei.health.IStepDataReport;
import com.huawei.health.icommon.ISimpleResultCallback;
import com.huawei.health.icommon.LocalStepDataReport;
import com.huawei.health.manager.util.RemoteCallerFilter;
import com.huawei.health.ui.notification.IUiManager;
import com.huawei.health.ui.notification.UiManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.AuthorizationUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.LogicalStepCounter;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.StepCounterSupport;
import health.compact.a.StepCounterSupportUtils;
import health.compact.a.StepsRecord;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class StepCounterRemoteProxy extends IDaemonRemoteManager.Stub {

    /* renamed from: a, reason: collision with root package name */
    private LogicalInitCallback f2782a;
    private LogicalStepCounter c;
    private RealTimeStepDataReportHelper d;
    private LocalReportProxy e;
    private DaemonService h;
    private IUiManager i;
    private boolean b = false;
    private RemoteCallbackList<IStepDataReport> f = new RemoteCallbackList<IStepDataReport>() { // from class: com.huawei.health.manager.StepCounterRemoteProxy.1
        @Override // android.os.RemoteCallbackList
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onCallbackDied(IStepDataReport iStepDataReport) {
            super.onCallbackDied(iStepDataReport);
            LogUtil.a("Step_CounterRemoteProxy", "RemoteCallbackList unregister when onCallbackDied");
            unregister(iStepDataReport);
        }
    };

    private int e(int i, int i2, int i3) {
        if (i < 390 || i2 < 120 || i3 > 2) {
            return (i < 330 || i2 < 90 || i2 >= 120 || i3 > 3) ? 1 : 2;
        }
        return 3;
    }

    public StepCounterRemoteProxy(DaemonService daemonService) {
        this.e = new LocalReportProxy();
        this.f2782a = new LogicalInitCallback();
        LogUtil.c("Step_CounterRemoteProxy", "StepCounterRemoteProxy");
        this.h = daemonService;
        LogicalStepCounter c = LogicalStepCounter.c(daemonService);
        this.c = c;
        c.c(this.e);
        this.d = new RealTimeStepDataReportHelper(this.h);
        this.c.e(this.f2782a);
        UiManager uiManager = new UiManager(this.h);
        this.c.e(uiManager.c());
        uiManager.b();
        this.i = uiManager.d();
    }

    @Override // com.huawei.health.IDaemonRemoteManager.Stub, android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (!RemoteCallerFilter.e()) {
            throw new RemoteException("hw permission check failed");
        }
        return super.onTransact(i, parcel, parcel2, i2);
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void setStepCounterSwitchStatus(boolean z) throws RemoteException {
        LogUtil.c("Step_CounterRemoteProxy", "setStepCounterSwitchStatus status=", Boolean.valueOf(z));
        LogicalStepCounter logicalStepCounter = this.c;
        if (logicalStepCounter == null || !this.b) {
            return;
        }
        if (z) {
            logicalStepCounter.m();
        } else {
            logicalStepCounter.o();
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean getStepCounterSwitchStatus() throws RemoteException {
        boolean j = this.c.j();
        LogUtil.a("Step_CounterRemoteProxy", "getStepCounterSwitchStatus result= ", Boolean.valueOf(j));
        return j;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean isStepCounterWorking() throws RemoteException {
        LogicalStepCounter logicalStepCounter = this.c;
        if (logicalStepCounter != null) {
            return logicalStepCounter.i();
        }
        return false;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public int getStepCounterClass() throws RemoteException {
        return StepCounterSupport.d(this.h);
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public int getDeviceOriginalClass() throws RemoteException {
        return StepCounterSupport.a(this.h);
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public int getStepCountAuthPermission() throws RemoteException {
        boolean a2 = AuthorizationUtils.a(BaseApplication.e());
        boolean a3 = StepCounterSupportUtils.a(BaseApplication.e());
        if (a2) {
            return !a3 ? -2 : 0;
        }
        return -1;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean registerStepReportCallback(IStepDataReport iStepDataReport) throws RemoteException {
        boolean register = iStepDataReport != null ? this.f.register(iStepDataReport) : false;
        LogicalStepCounter logicalStepCounter = this.c;
        if (logicalStepCounter != null) {
            logicalStepCounter.c(this.e);
        }
        Object[] objArr = new Object[6];
        objArr[0] = "registerStepReportCallback ret = ";
        objArr[1] = Boolean.valueOf(register);
        objArr[2] = "callback == null:";
        objArr[3] = Boolean.valueOf(iStepDataReport == null);
        objArr[4] = "mLogicalStepCounter == null";
        objArr[5] = Boolean.valueOf(this.c == null);
        ReleaseLogUtil.b("Step_CounterRemoteProxy", objArr);
        return register;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean unRegisterStepReportCallback(IStepDataReport iStepDataReport) throws RemoteException {
        boolean unregister = iStepDataReport != null ? this.f.unregister(iStepDataReport) : false;
        ReleaseLogUtil.b("Step_CounterRemoteProxy", "unRegisterStepReportCallback ret = ", Boolean.valueOf(unregister));
        return unregister;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean registerRealTimeStepReport(IRealStepDataReport iRealStepDataReport, int i) throws RemoteException {
        RealTimeStepDataReportHelper realTimeStepDataReportHelper = this.d;
        if (realTimeStepDataReportHelper != null) {
            return realTimeStepDataReportHelper.d(iRealStepDataReport, i);
        }
        return false;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean unRegisterRealTimeStepReport() throws RemoteException {
        RealTimeStepDataReportHelper realTimeStepDataReportHelper = this.d;
        if (realTimeStepDataReportHelper != null) {
            return realTimeStepDataReportHelper.a();
        }
        return false;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void setBaseData(long j, int i, int i2, int i3) throws RemoteException {
        LogUtil.c("Step_CounterRemoteProxy", "setBaseData");
        LogicalStepCounter logicalStepCounter = this.c;
        if (logicalStepCounter != null) {
            logicalStepCounter.b(j, i, i2, i3);
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void setStepsNotifiEnable(boolean z) throws RemoteException {
        LogUtil.c("Step_CounterRemoteProxy", "setNotificationEnable status=", Boolean.valueOf(z));
        IUiManager iUiManager = this.i;
        if (iUiManager != null) {
            iUiManager.changeStepsNotificationStateAsUser(z);
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void setGoalNotifiEnable(boolean z) throws RemoteException {
        LogUtil.c("Step_CounterRemoteProxy", "setNotificationEnable status=", Boolean.valueOf(z));
        IUiManager iUiManager = this.i;
        if (iUiManager != null) {
            iUiManager.changeGoalNotificationStateAsUser(z);
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean getStepsNotifiState() throws RemoteException {
        IUiManager iUiManager = this.i;
        boolean isGetStepsNotificationState = iUiManager != null ? iUiManager.isGetStepsNotificationState() : false;
        LogUtil.a("Step_CounterRemoteProxy", "isGetStepsNotificationState result= ", Boolean.valueOf(isGetStepsNotificationState));
        return isGetStepsNotificationState;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean getGoalNotifiState() throws RemoteException {
        IUiManager iUiManager = this.i;
        boolean isGetGoalNotificationState = iUiManager != null ? iUiManager.isGetGoalNotificationState() : false;
        LogUtil.a("Step_CounterRemoteProxy", "isGetGoalNotificationState result= ", Boolean.valueOf(isGetGoalNotificationState));
        return isGetGoalNotificationState;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void flushCacheToDB(IResultCallback iResultCallback) throws RemoteException {
        if (this.c == null) {
            return;
        }
        this.c.a((ISimpleResultCallback) (iResultCallback != null ? new SimpleResultCallbackProxy(iResultCallback) : null), true);
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void setNotificationEnable(boolean z) {
        IUiManager iUiManager = this.i;
        if (iUiManager != null) {
            iUiManager.changeStepsNotificationStateAsUser(true);
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean isNotificationShown() {
        IUiManager iUiManager = this.i;
        return iUiManager != null && iUiManager.isGetNotificationSupport() && this.i.isGetStepsNotificationState();
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void setUserInfo(Bundle bundle) throws RemoteException {
        LogicalStepCounter logicalStepCounter = this.c;
        if (logicalStepCounter != null) {
            logicalStepCounter.akI_(bundle);
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void getSleepData(IResultCallback iResultCallback) {
        ReleaseLogUtil.b("Step_CounterRemoteProxy", "getSleepData callback");
        if (iResultCallback == null || this.c == null) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        this.c.a(hashMap);
        b(iResultCallback, hashMap);
    }

    private void b(IResultCallback iResultCallback, Map<String, Object> map) {
        try {
            if (map.size() < 7) {
                LogUtil.a("Step_CounterRemoteProxy", "getSleepData sleepDataMap.size() =", Integer.valueOf(map.size()));
                iResultCallback.onFailed(null);
            } else {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putInt("sleep_start_time", Integer.parseInt(String.valueOf(map.get("sleep_start_time"))));
                    bundle.putInt("sleep_end_time", Integer.parseInt(String.valueOf(map.get("sleep_end_time"))));
                    bundle.putInt("sleep_duration_sum", Integer.parseInt(String.valueOf(map.get("sleep_duration_sum"))));
                    bundle.putInt("sleep_deep_duration", Integer.parseInt(String.valueOf(map.get("sleep_deep_duration"))));
                    bundle.putInt("sleep_shallow_duration", Integer.parseInt(String.valueOf(map.get("sleep_shallow_duration"))));
                    bundle.putInt("sleep_wake_duration", Integer.parseInt(String.valueOf(map.get("sleep_wake_duration"))));
                    bundle.putInt("sleep_wake_count", Integer.parseInt(String.valueOf(map.get("sleep_wake_count"))));
                    try {
                        try {
                            bundle.putInt("sleep_quality", e(Integer.parseInt(String.valueOf(map.get("sleep_duration_sum"))), Integer.parseInt(String.valueOf(map.get("sleep_deep_duration"))), Integer.parseInt(String.valueOf(map.get("sleep_wake_count")))));
                            iResultCallback.onSuccess(bundle);
                        } catch (NumberFormatException e) {
                            e = e;
                            LogUtil.h("Step_CounterRemoteProxy", "getSleepData NumberFormatException", e.getMessage());
                            iResultCallback.onFailed(null);
                        }
                    } catch (RemoteException e2) {
                        e = e2;
                        LogUtil.h("Step_CounterRemoteProxy", "getSleepData RemoteException", e.getMessage());
                    }
                } catch (NumberFormatException e3) {
                    e = e3;
                }
            }
        } catch (RemoteException e4) {
            e = e4;
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public boolean getNotificationSupport() {
        IUiManager iUiManager = this.i;
        if (iUiManager != null) {
            return iUiManager.isGetNotificationSupport();
        }
        return false;
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void getTodaySportData(IResultCallback iResultCallback) {
        LogUtil.c("Step_CounterRemoteProxy", "getTodaySportData callback");
        if (iResultCallback != null) {
            LogicalStepCounter logicalStepCounter = this.c;
            if (logicalStepCounter != null) {
                try {
                    iResultCallback.onSuccess(logicalStepCounter.akH_());
                    return;
                } catch (RemoteException e) {
                    LogUtil.h("Step_CounterRemoteProxy", e.getMessage());
                    return;
                }
            }
            try {
                iResultCallback.onFailed(null);
            } catch (RemoteException e2) {
                LogUtil.h("Step_CounterRemoteProxy", e2.getMessage());
            }
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void getDebugInfo(IResultCallback iResultCallback) throws RemoteException {
        LogUtil.c("Step_CounterRemoteProxy", "getDebugInfo callback");
        if (iResultCallback != null) {
            LogicalStepCounter logicalStepCounter = this.c;
            if (logicalStepCounter != null) {
                try {
                    iResultCallback.onSuccess(logicalStepCounter.akG_());
                    return;
                } catch (RemoteException e) {
                    LogUtil.h("Step_CounterRemoteProxy", e.getMessage());
                    return;
                }
            }
            try {
                iResultCallback.onFailed(null);
            } catch (RemoteException e2) {
                LogUtil.h("Step_CounterRemoteProxy", e2.getMessage());
            }
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void isNeedPromptKeepAlive(IResultCallback iResultCallback) throws RemoteException {
        LogUtil.c("Step_CounterRemoteProxy", "isNeedPromptKeepAlive callback");
        if (iResultCallback == null) {
            LogUtil.h("Step_CounterRemoteProxy", "isNeedPromptKeepAlive callback null,", "warning!!!(can not given the result via callback) return");
            return;
        }
        if (this.c == null) {
            LogUtil.h("Step_CounterRemoteProxy", "isNeedPromptKeepAlive mLogicalStepCounter null,", "warning!!!(can not invoke function) return");
            iResultCallback.onFailed(null);
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isNeedPromptKeepAlive", this.c.g());
            iResultCallback.onSuccess(bundle);
        } catch (RemoteException e) {
            LogUtil.h("Step_CounterRemoteProxy", e.getMessage());
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void makePromptNoSense() throws RemoteException {
        LogUtil.a("Step_CounterRemoteProxy", "makePromptNoSense");
        LogicalStepCounter logicalStepCounter = this.c;
        if (logicalStepCounter == null) {
            LogUtil.h("Step_CounterRemoteProxy", "mLogicalStepCounter null,makePromptNoSense failed,return");
        } else {
            logicalStepCounter.l();
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void getStandSteps(final IResultCallback iResultCallback) throws RemoteException {
        LogUtil.c("Step_CounterRemoteProxy", "getStandSteps callback");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.manager.StepCounterRemoteProxy.2
            @Override // java.lang.Runnable
            public void run() {
                int i;
                if (StepCounterRemoteProxy.this.d == null || StepCounterRemoteProxy.this.c == null) {
                    i = -1;
                } else {
                    StepCounterRemoteProxy.this.d.b();
                    i = StepCounterRemoteProxy.this.c.c();
                }
                if (iResultCallback != null) {
                    try {
                        Bundle bundle = new Bundle();
                        bundle.putInt("standSteps", i);
                        iResultCallback.onSuccess(bundle);
                    } catch (RemoteException e) {
                        LogUtil.h("Step_CounterRemoteProxy", e.getMessage());
                    }
                }
            }
        });
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void notifyUserInfoChanged() throws RemoteException {
        LogUtil.a("Step_CounterRemoteProxy", "notifyUserInfoChanged ");
        LogicalStepCounter logicalStepCounter = this.c;
        if (logicalStepCounter != null) {
            logicalStepCounter.k();
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void switchTrackMonitor(boolean z) {
        LogUtil.a("Step_CounterRemoteProxy", "switchTrackMonitor ", Boolean.valueOf(z));
        RealTimeStepDataReportHelper realTimeStepDataReportHelper = this.d;
        if (realTimeStepDataReportHelper == null) {
            return;
        }
        if (z) {
            realTimeStepDataReportHelper.e();
        } else {
            realTimeStepDataReportHelper.c();
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public void tickTrackDog() {
        LogUtil.a("Step_CounterRemoteProxy", "tickTrackDog ", this.d);
        RealTimeStepDataReportHelper realTimeStepDataReportHelper = this.d;
        if (realTimeStepDataReportHelper != null) {
            realTimeStepDataReportHelper.d();
        }
    }

    public void dealBroadcastEvents(Intent intent) {
        if (intent != null) {
            LogUtil.a("Step_CounterRemoteProxy", "dealBroadcastEvents ", intent);
            LogicalStepCounter logicalStepCounter = this.c;
            if (logicalStepCounter != null) {
                logicalStepCounter.akF_(intent);
                return;
            }
            return;
        }
        LogUtil.a("Step_CounterRemoteProxy", "dealBroadcastEvents intent = null");
    }

    public void release() {
        IUiManager iUiManager = this.i;
        if (iUiManager != null) {
            iUiManager.release();
        }
        LogicalStepCounter logicalStepCounter = this.c;
        if (logicalStepCounter != null) {
            logicalStepCounter.d();
        }
    }

    class LocalReportProxy implements LocalStepDataReport {
        private LocalReportProxy() {
        }

        @Override // com.huawei.health.icommon.LocalStepDataReport
        public void report(StepsRecord stepsRecord) {
            int i;
            if (stepsRecord == null) {
                return;
            }
            synchronized (this) {
                try {
                    i = StepCounterRemoteProxy.this.f.beginBroadcast();
                } catch (IllegalStateException e) {
                    LogUtil.h("Step_CounterRemoteProxy", "report state not reade", e.getMessage());
                    i = 0;
                }
                LogUtil.i("Step_CounterRemoteProxy", "Report client count ", Integer.valueOf(i));
                for (int i2 = 0; i2 < i; i2++) {
                    try {
                        try {
                            ((IStepDataReport) StepCounterRemoteProxy.this.f.getBroadcastItem(i2)).report(stepsRecord.aaD_());
                        } catch (NullPointerException e2) {
                            ReleaseLogUtil.c("Step_CounterRemoteProxy", "report remote", LogAnonymous.b((Throwable) e2));
                        }
                    } catch (RemoteException unused) {
                        LogUtil.h("Step_CounterRemoteProxy", "report remote exception...");
                    }
                }
                try {
                    StepCounterRemoteProxy.this.f.finishBroadcast();
                } catch (IllegalStateException e3) {
                    LogUtil.h("Step_CounterRemoteProxy", "report state not reade", e3.getMessage());
                }
            }
        }
    }

    class LogicalInitCallback implements ISimpleResultCallback {
        private LogicalInitCallback() {
        }

        @Override // com.huawei.health.icommon.ISimpleResultCallback
        public void onSuccess(Bundle bundle) {
            StepCounterRemoteProxy.this.b = true;
            if (StepCounterRemoteProxy.this.c.j()) {
                StepCounterRemoteProxy.this.c.m();
            }
        }

        @Override // com.huawei.health.icommon.ISimpleResultCallback
        public void onFailed(Bundle bundle) {
            StepCounterRemoteProxy.this.b = false;
            LogUtil.h("Step_CounterRemoteProxy", "init onfailed try to startStepCounter,may failed");
            if (StepCounterRemoteProxy.this.c.j()) {
                StepCounterRemoteProxy.this.c.m();
            }
        }
    }

    static class SimpleResultCallbackProxy implements ISimpleResultCallback {
        private IResultCallback d;

        SimpleResultCallbackProxy(IResultCallback iResultCallback) {
            this.d = iResultCallback;
        }

        @Override // com.huawei.health.icommon.ISimpleResultCallback
        public void onSuccess(Bundle bundle) {
            IResultCallback iResultCallback = this.d;
            if (iResultCallback != null) {
                try {
                    iResultCallback.onSuccess(bundle);
                } catch (RemoteException e) {
                    LogUtil.h("Step_CounterRemoteProxy", e.getMessage());
                }
            }
        }

        @Override // com.huawei.health.icommon.ISimpleResultCallback
        public void onFailed(Bundle bundle) {
            IResultCallback iResultCallback = this.d;
            if (iResultCallback != null) {
                try {
                    iResultCallback.onFailed(bundle);
                } catch (RemoteException e) {
                    LogUtil.h("Step_CounterRemoteProxy", e.getMessage());
                }
            }
        }
    }

    @Override // com.huawei.health.IDaemonRemoteManager
    public String getVersion() throws RemoteException {
        return "1.0.0.0";
    }
}
