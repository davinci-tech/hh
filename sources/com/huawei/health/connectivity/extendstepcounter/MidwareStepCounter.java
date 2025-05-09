package com.huawei.health.connectivity.extendstepcounter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseArray;
import com.hihonor.health.fw.IReportCallback;
import com.huawei.health.fw.IReportCallback;
import com.huawei.health.fwk.BaseStepCounter;
import com.huawei.health.icommon.HwHealthSensorEventListener;
import com.huawei.health.icommon.ISimpleResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.CommonUtil;
import health.compact.a.HnStandStepCounter;
import health.compact.a.HwStandStepCounter;
import health.compact.a.LogSensor;
import health.compact.a.LogicalStepCounter;
import health.compact.a.OneMinuteStepData;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepCounterSupport;

/* loaded from: classes.dex */
public class MidwareStepCounter extends ExtendStepCounter {
    private static boolean c = false;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f2210a;
    private e b;
    private a d;
    private b f;
    private d g;
    private c i;
    private boolean h = false;
    private int m = 0;
    private BaseStepCounter n = null;
    private ISimpleResultCallback j = null;

    public interface MidwareCallback {
        void onFailed(SparseArray<OneMinuteStepData> sparseArray);

        void onSuccess(SparseArray<OneMinuteStepData> sparseArray);
    }

    public MidwareStepCounter(Context context) {
        this.f2210a = null;
        this.b = new e();
        if (CommonUtil.bj()) {
            this.i = new c();
            this.d = new a();
        } else {
            this.f = new b();
            this.g = new d();
        }
        if (context == null) {
            LogUtil.h("Step_MidwareStepCnt", "MidwareStepCounter context is null.");
            this.f2210a = BaseApplication.getContext();
        } else {
            this.f2210a = context;
        }
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void init(ISimpleResultCallback iSimpleResultCallback) {
        if (iSimpleResultCallback == null) {
            LogUtil.h("Step_MidwareStepCnt", "callback is null.");
        } else {
            this.j = iSimpleResultCallback;
            c();
        }
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void startStepCounter() {
        ReleaseLogUtil.b("Step_MidwareStepCnt", "startStepCounter ", this.n);
        BaseStepCounter baseStepCounter = this.n;
        if (baseStepCounter != null) {
            baseStepCounter.openStepCounter();
            d(true);
            LogUtil.a("Step_MidwareStepCnt", "registerCallback ", this.n);
            if (CommonUtil.bj()) {
                this.n.registerDataCallback(this.i);
            } else {
                this.n.registerDataCallback(this.f);
            }
        }
        a();
    }

    private void a() {
        LogUtil.a("Step_MidwareStepCnt", "registerLogCallback ", this.n);
        BaseStepCounter baseStepCounter = this.n;
        if (baseStepCounter != null) {
            LogUtil.a("Step_MidwareStepCnt", "registerLogCallback:", baseStepCounter, " mLogCallback:", this.g);
            if (CommonUtil.bj()) {
                this.n.registerLogCallback(this.d);
            } else {
                this.n.registerLogCallback(this.g);
            }
        }
    }

    private void f() {
        LogUtil.a("Step_MidwareStepCnt", "unRegisterLogCallback ", this.n);
        BaseStepCounter baseStepCounter = this.n;
        if (baseStepCounter != null) {
            LogUtil.a("Step_MidwareStepCnt", "unRegisterLogCallback:", baseStepCounter, " mLogCallback:", this.g);
            if (CommonUtil.bj()) {
                this.n.unRegisterLogCallback(this.d);
            } else {
                this.n.unRegisterLogCallback(this.g);
            }
        }
    }

    @Override // com.huawei.health.connectivity.extendstepcounter.ExtendStepCounter
    public void stopStepCounter() {
        HwHealthSensorEventListener e2;
        ReleaseLogUtil.b("Step_MidwareStepCnt", "stopStepCounter ", this.n);
        if (Build.VERSION.SDK_INT >= 28 && (e2 = LogicalStepCounter.c(this.f2210a).e()) != null) {
            e2.notifyStepCounterError(false);
        }
        BaseStepCounter baseStepCounter = this.n;
        if (baseStepCounter != null) {
            baseStepCounter.closeStepCounter();
            d(false);
            LogUtil.a("Step_MidwareStepCnt", "unRegisterCallback ", this.n);
            if (CommonUtil.bj()) {
                this.n.unRegisterDataCallback(this.i);
            } else {
                this.n.unRegisterDataCallback(this.f);
            }
        }
        f();
    }

    public String e() {
        BaseStepCounter baseStepCounter = this.n;
        if (baseStepCounter != null) {
            return baseStepCounter.getVersion();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c() {
        /*
            r6 = this;
            java.lang.String r0 = "initMidwareSerivce "
            android.content.Context r1 = r6.f2210a
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "Step_MidwareStepCnt"
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            android.content.Context r0 = r6.f2210a
            r2 = 0
            if (r0 == 0) goto L56
            android.content.Intent r0 = r6.Dk_(r0)
            if (r0 == 0) goto L2c
            android.content.Context r3 = r6.f2210a     // Catch: java.lang.SecurityException -> L22
            com.huawei.health.connectivity.extendstepcounter.MidwareStepCounter$e r4 = r6.b     // Catch: java.lang.SecurityException -> L22
            r5 = 1
            boolean r0 = r3.bindService(r0, r4, r5)     // Catch: java.lang.SecurityException -> L22
            goto L2d
        L22:
            java.lang.String r0 = "mid ware bind failed"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
        L2c:
            r0 = r2
        L2d:
            if (r0 != 0) goto L48
            java.lang.String r3 = "mid ware bind ret false,init onfailed"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r3)
            android.content.Context r3 = r6.f2210a
            health.compact.a.LogicalStepCounter r3 = health.compact.a.LogicalStepCounter.c(r3)
            r3.c(r2)
            com.huawei.health.icommon.ISimpleResultCallback r2 = r6.j
            r3 = 0
            r2.onFailed(r3)
        L48:
            java.lang.String r2 = "bind ret "
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            health.compact.a.ReleaseLogUtil.b(r1, r2)
            r2 = r0
        L56:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.connectivity.extendstepcounter.MidwareStepCounter.c():boolean");
    }

    private Intent Dk_(Context context) {
        return StepCounterSupport.aaC_(context);
    }

    class e implements ServiceConnection {
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private e() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (CommonUtil.bj()) {
                MidwareStepCounter.this.n = new HnStandStepCounter(iBinder);
            } else {
                MidwareStepCounter.this.n = new HwStandStepCounter(iBinder);
            }
            String e = MidwareStepCounter.this.e();
            LogUtil.a("Step_MidwareStepCnt", "MyServiceConn connect ", e);
            if (MidwareStepCounter.this.j != null) {
                if (e != null) {
                    MidwareStepCounter.this.j.onSuccess(null);
                } else {
                    MidwareStepCounter.this.j.onFailed(null);
                }
            }
        }
    }

    class b extends IReportCallback.Stub {
        private b() {
        }

        @Override // com.huawei.health.fw.IReportCallback
        public void onResult(Bundle bundle) {
            MidwareStepCounter.this.Dj_(bundle);
        }
    }

    class c extends IReportCallback.Stub {
        private c() {
        }

        @Override // com.hihonor.health.fw.IReportCallback
        public void onResult(Bundle bundle) {
            MidwareStepCounter.this.Dj_(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Dj_(Bundle bundle) {
        if (bundle != null) {
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    HwHealthSensorEventListener e2 = LogicalStepCounter.c(this.f2210a).e();
                    if (bundle.getInt("ability") == 1) {
                        SharedPerferenceUtils.c(this.f2210a, true);
                    } else {
                        SharedPerferenceUtils.c(this.f2210a, false);
                    }
                    if (e2 != null) {
                        e2.dataReport(bundle.getInt("total_steps"), false);
                    }
                }
                dataReport(this.f2210a, bundle.getLong("start_time"), bundle.getIntArray(MedalConstants.EVENT_STEPS), bundle.getIntArray("floors"), bundle.getIntArray(ContentTemplateRecord.MOTIONS));
            } catch (ArrayIndexOutOfBoundsException e3) {
                LogUtil.b("Step_MidwareStepCnt", "MyCallback onResult ", e3.getMessage());
            }
        }
    }

    class d extends IReportCallback.Stub {
        private d() {
        }

        @Override // com.huawei.health.fw.IReportCallback
        public void onResult(Bundle bundle) {
            MidwareStepCounter.this.Dl_(bundle);
        }
    }

    class a extends IReportCallback.Stub {
        private a() {
        }

        @Override // com.hihonor.health.fw.IReportCallback
        public void onResult(Bundle bundle) {
            MidwareStepCounter.this.Dl_(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Dl_(Bundle bundle) {
        if (bundle != null) {
            try {
                LogSensor.e(bundle.getByteArray("sensorhub_log"));
            } catch (ArrayIndexOutOfBoundsException e2) {
                LogUtil.h("Step_MidwareStepCnt", e2.getMessage());
            }
        }
    }

    public boolean b() {
        synchronized (e) {
            if (!this.h) {
                int i = this.m + 1;
                this.m = i;
                if (i != 6) {
                    LogUtil.h("Step_MidwareStepCnt", "cannot sync with db while midware cache not ready,retry:", Integer.valueOf(i));
                    return false;
                }
                LogUtil.h("Step_MidwareStepCnt", "cannot sync with db while midware cache not ready,force sync");
                this.m = 0;
                this.h = true;
                return true;
            }
            this.m = 0;
            return true;
        }
    }

    public static boolean d() {
        return c;
    }

    private static void d(boolean z) {
        c = z;
    }
}
