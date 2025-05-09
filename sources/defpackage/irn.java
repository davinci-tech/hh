package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.DataReportModel;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes7.dex */
public class irn {
    private static Context e;

    /* renamed from: a, reason: collision with root package name */
    private final Object f13555a;
    private final Object b;
    private final Object c;
    private boolean d;
    private ICommonReport f;
    private HealthOpenSDK g;
    private volatile boolean h;
    private final ipx j;

    private irn() {
        this.c = new Object();
        this.f13555a = new Object();
        this.b = new Object();
        this.h = false;
        this.j = new ipx();
        this.d = false;
    }

    static class a {
        private static final irn c = new irn();
    }

    public static irn b(Context context) {
        e = context.getApplicationContext();
        return a.c;
    }

    private void e(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) {
        synchronized (this.c) {
            this.j.registerObserver(dataReportModel, iRegisterRealTimeCallback);
        }
    }

    private void b(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) {
        synchronized (this.c) {
            this.j.unregisterObserver(dataReportModel, iRegisterRealTimeCallback);
        }
    }

    public void c() {
        LogUtil.a("RealTimeUtil", "start init sdk, init result before ", Boolean.valueOf(this.d));
        if (this.d) {
            return;
        }
        try {
            HealthOpenSDK healthOpenSDK = new HealthOpenSDK();
            this.g = healthOpenSDK;
            healthOpenSDK.initSDK(e, new b(), "Health Kit");
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_RealTimeUtil", "init error", LogAnonymous.b((Throwable) e2));
        }
    }

    class b implements IExecuteResult {
        private b() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            irn.this.d = true;
            ReleaseLogUtil.e("HiH_RealTimeUtil", "healthOpenSDKCallback initSDK success");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            irn.this.d = false;
            if (irn.this.g != null) {
                irn.this.g.destorySDK();
                irn.this.g = null;
            }
            ReleaseLogUtil.d("HiH_RealTimeUtil", "healthOpenSDKCallback : initSDK Failed");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            irn.this.d = false;
            if (irn.this.g != null) {
                irn.this.g.destorySDK();
                irn.this.g = null;
            }
            ReleaseLogUtil.d("HiH_RealTimeUtil", "healthOpenSDKCallback : onServiceException");
        }
    }

    public void c(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback, irc ircVar) {
        int dataType = dataReportModel.getDataType();
        if (b("registerDataAutoReport", dataType, this.g, iRegisterRealTimeCallback, ircVar)) {
            c();
            return;
        }
        synchronized (this.f13555a) {
            if (!this.h) {
                this.f = new e(this.g);
                long currentTimeMillis = System.currentTimeMillis();
                a(this.g, this.f);
                synchronized (this.b) {
                    try {
                        if (!this.h) {
                            this.b.wait(1000L);
                        }
                    } catch (InterruptedException e2) {
                        ReleaseLogUtil.c("HiH_RealTimeUtil", "registerRealTimeCallback: InterruptedException", LogAnonymous.b((Throwable) e2));
                    }
                }
                LogUtil.c("RealTimeUtil", "registerStepReport needs ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " ms");
            }
        }
        if (this.h) {
            e(dataReportModel, iRegisterRealTimeCallback);
            try {
                iRegisterRealTimeCallback.onResult(0, ipd.b(0));
            } catch (RemoteException e3) {
                ReleaseLogUtil.c("HiH_RealTimeUtil", "registerRealTimeCallback RemoteException: ", e3.getMessage());
            }
            e("registerDataAutoReport", 0, dataType, ircVar);
            return;
        }
        try {
            iRegisterRealTimeCallback.onResult(4, ipd.b(4));
        } catch (RemoteException e4) {
            ReleaseLogUtil.c("HiH_RealTimeUtil", "registerRealTimeCallback RemoteException2: ", e4.getMessage());
        }
        e("registerDataAutoReport", 4, dataType, ircVar);
    }

    public void d(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback, irc ircVar) throws RemoteException {
        int dataType = dataReportModel.getDataType();
        if (b("unregisterDataAutoReport", dataType, this.g, iRegisterRealTimeCallback, ircVar)) {
            c();
            return;
        }
        if (this.j.isEmpty()) {
            ReleaseLogUtil.e("HiH_RealTimeUtil", "unregisterRealTimeCallback: dailyActivityObservable is empty, return success");
            iRegisterRealTimeCallback.onResult(0, ipd.b(0));
            e("unregisterDataAutoReport", 0, dataType, ircVar);
        } else {
            b(dataReportModel, iRegisterRealTimeCallback);
            iRegisterRealTimeCallback.onResult(0, ipd.b(0));
            if (this.j.isEmpty()) {
                d(this.g, this.f);
            }
            e("unregisterDataAutoReport", 0, dataType, ircVar);
        }
    }

    private void a(HealthOpenSDK healthOpenSDK, ICommonReport iCommonReport) {
        LogUtil.a("RealTimeUtil", "enter: RegisterStepReport");
        healthOpenSDK.b(iCommonReport, new d());
    }

    private void d(HealthOpenSDK healthOpenSDK, ICommonReport iCommonReport) {
        LogUtil.a("RealTimeUtil", "enter: unRegisterStepReport");
        healthOpenSDK.d(iCommonReport);
        this.h = false;
    }

    class e implements ICommonReport {

        /* renamed from: a, reason: collision with root package name */
        private HealthOpenSDK f13556a;

        e(HealthOpenSDK healthOpenSDK) {
            this.f13556a = healthOpenSDK;
        }

        @Override // com.huawei.hihealth.motion.ICommonReport
        public void report(Bundle bundle) {
            if (bundle == null || !irn.this.h) {
                return;
            }
            irn.this.bCb_(this.f13556a, bundle);
        }
    }

    class d implements IExecuteResult {
        d() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            ReleaseLogUtil.e("HiH_RealTimeUtil", "registerStepReport: IExecuteResult onSuccess");
            irn.this.h = true;
            irn.this.b.notifyAll();
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            ReleaseLogUtil.c("HiH_RealTimeUtil", "registerStepReport: IExecuteResult onFailed");
            irn.this.b.notifyAll();
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            ReleaseLogUtil.c("HiH_RealTimeUtil", "registerStepReport: IExecuteResult onServiceException");
            irn.this.b.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bCb_(HealthOpenSDK healthOpenSDK, Bundle bundle) {
        if (this.j.isEmpty()) {
            ReleaseLogUtil.e("HiH_RealTimeUtil", "dataChangeHandling: dailyActivityObservable is empty, unRegisterStepReport");
            d(healthOpenSDK, this.f);
        }
        this.j.notifyDataChanged(bundle);
    }

    private boolean b(String str, int i, HealthOpenSDK healthOpenSDK, IRegisterRealTimeCallback iRegisterRealTimeCallback, irc ircVar) {
        if (healthOpenSDK != null) {
            return false;
        }
        LogUtil.h("RealTimeUtil", "step sdk is null");
        try {
            iRegisterRealTimeCallback.onResult(4, ipd.b(4));
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HiH_RealTimeUtil", "isOpenSdkEmpty RemoteException: ", e2.getMessage());
        }
        e(str, 4, i, ircVar);
        return true;
    }

    private void e(String str, int i, int i2, irc ircVar) {
        Context e2 = BaseApplication.e();
        iqy iqyVar = new iqy(str, i, iwi.a(e2));
        iqyVar.e(String.valueOf(i2));
        ircVar.c(e2, iqyVar);
    }
}
