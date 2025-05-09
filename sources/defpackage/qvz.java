package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.manager.DaemonService;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.health.weight.WeightApi;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.ICommonReport;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.up.model.UserInfomation;
import defpackage.qvz;
import health.compact.a.CommonUtils;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;

/* loaded from: classes.dex */
public class qvz {
    private static volatile HealthOpenSDK c;
    private static d g;
    private static e h;
    private static CountDownLatch i;
    private static final AtomicInteger f = new AtomicInteger();
    private static final AtomicInteger j = new AtomicInteger();

    /* renamed from: a, reason: collision with root package name */
    private static final AtomicBoolean f16619a = new AtomicBoolean();
    private static final AtomicLong b = new AtomicLong();
    private static final AtomicBoolean e = new AtomicBoolean();
    private static final Observer d = new Observer() { // from class: qym
        @Override // com.huawei.haf.design.pattern.Observer
        public final void notify(String str, Object[] objArr) {
            qvz.a(str, objArr);
        }
    };

    static /* synthetic */ void a(String str, Object[] objArr) {
        boolean d2 = ProcessUtil.d();
        ReleaseLogUtil.b("R_DietDiaryUtils", "OBSERVER label ", str, " objects ", objArr, " isMainProcess ", Boolean.valueOf(d2));
        if (d2 && "EXIT_APP".equals(str)) {
            o();
            HandlerCenter.d().e(new Runnable() { // from class: qxe
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.i();
                }
            }, 200L);
        }
    }

    static /* synthetic */ void i() {
        ObserverManagerUtil.e("EXIT_APP");
        e.set(false);
        ReleaseLogUtil.b("R_DietDiaryUtils", "OBSERVER unregisterObserver EXIT_APP");
    }

    /* loaded from: classes7.dex */
    static final class d implements ICommonReport {
        private final CountDownLatch e;

        d(CountDownLatch countDownLatch) {
            this.e = countDownLatch;
        }

        @Override // com.huawei.hihealth.motion.ICommonReport
        public void report(Bundle bundle) {
            if (bundle == null) {
                ReleaseLogUtil.a("R_DietDiaryUtils", "SportDataCommonReport report bundle is null");
            } else {
                double d = bundle.getInt("carior", 0);
                int a2 = (int) UnitUtil.a(d / 1000.0d, 0);
                qvz.j.set(a2);
                LogUtil.c("DietDiaryUtils", "SportDataCommonReport report calorie ", Double.valueOf(d), " todaySportConsumption ", Integer.valueOf(a2));
            }
            CountDownLatch countDownLatch = this.e;
            if (countDownLatch == null) {
                ReleaseLogUtil.a("R_DietDiaryUtils", "SportDataCommonReport report mCountDownLatch is null");
            } else {
                countDownLatch.countDown();
            }
        }
    }

    /* loaded from: classes7.dex */
    static class e implements IExecuteResult {
        private e() {
        }

        /* synthetic */ e(AnonymousClass3 anonymousClass3) {
            this();
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "SportDataExecuteResult onSuccess object ", obj);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            int i = qvz.f.get();
            if (i < 10) {
                qvz.f.set(i + 1);
                HandlerExecutor.d(new Runnable() { // from class: qys
                    @Override // java.lang.Runnable
                    public final void run() {
                        qvz.f("onFailed");
                    }
                }, 200L);
            }
            ReleaseLogUtil.a("R_DietDiaryUtils", "SportDataExecuteResult onFailed atomicRetryCount ", Integer.valueOf(i), " object ", obj);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            int i = qvz.f.get();
            if (i < 5) {
                qvz.f.set(i + 1);
                try {
                    BaseApplication.e().startService(new Intent(BaseApplication.e(), (Class<?>) DaemonService.class));
                } catch (IllegalStateException | SecurityException e) {
                    ReleaseLogUtil.c("R_DietDiaryUtils", "onServiceException exception ", ExceptionUtils.d(e));
                }
                qvz.f("onServiceException");
            }
            ReleaseLogUtil.a("R_DietDiaryUtils", "SportDataExecuteResult onServiceException atomicRetryCount ", Integer.valueOf(i), " object ", obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(final String str) {
        boolean d2 = ProcessUtil.d();
        Boolean valueOf = Boolean.valueOf(d2);
        AtomicBoolean atomicBoolean = e;
        ReleaseLogUtil.b("R_DietDiaryUtils", "subscribeSportData source ", str, " isMainProcess ", valueOf, " isRegisteredExitApp ", Boolean.valueOf(atomicBoolean.get()));
        if (d2 && !atomicBoolean.get()) {
            atomicBoolean.set(true);
            ObserverManagerUtil.d(d, "EXIT_APP");
        }
        f16619a.set(true);
        ThreadPoolManager.d().execute(new Runnable() { // from class: qwk
            @Override // java.lang.Runnable
            public final void run() {
                qvz.a(str);
            }
        });
    }

    static /* synthetic */ void a(String str) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "subscribeSportData source ", str, " sHealthOpenSDK ", c);
        if (c == null) {
            Context e2 = BaseApplication.e();
            c = dss.c(e2).d();
            i = new CountDownLatch(1);
            g = new d(i);
            h = new e(null);
            dss.c(e2).c(h);
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e3) {
            ReleaseLogUtil.c("R_DietDiaryUtils", "subscribeSportData exception ", ExceptionUtils.d(e3));
        }
        c.b(g, h);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0077, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0072, code lost:
    
        if (r4 == null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void o() {
        /*
            java.util.concurrent.atomic.AtomicBoolean r0 = defpackage.qvz.f16619a
            boolean r1 = r0.get()
            java.lang.String r2 = "unSubscribeSportData isRegisteredSportData "
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            java.lang.String r3 = "R_DietDiaryUtils"
            health.compact.a.ReleaseLogUtil.b(r3, r2)
            if (r1 != 0) goto L19
            return
        L19:
            r1 = 0
            r0.set(r1)
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            dss r0 = defpackage.dss.c(r0)
            qvz$e r2 = defpackage.qvz.h
            r0.e(r2)
            r0 = 2
            r2 = 1
            r4 = 0
            com.huawei.haf.threadpool.ThreadPoolManager r5 = com.huawei.haf.threadpool.ThreadPoolManager.d()     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            qws r6 = new qws     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            r6.<init>()     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            java.util.concurrent.Future r4 = r5.submit(r6)     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            r6 = 2000(0x7d0, double:9.88E-321)
            java.lang.Object r5 = r4.get(r6, r5)     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            java.lang.String r7 = "unSubscribeSportData result "
            r6[r1] = r7     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            r6[r2] = r5     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            health.compact.a.ReleaseLogUtil.b(r3, r6)     // Catch: java.lang.Throwable -> L5b java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61
            if (r4 == 0) goto L77
            goto L74
        L5b:
            r0 = move-exception
            goto L78
        L5d:
            r5 = move-exception
            goto L62
        L5f:
            r5 = move-exception
            goto L62
        L61:
            r5 = move-exception
        L62:
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L5b
            java.lang.String r6 = "unSubscribeSportData exception "
            r0[r1] = r6     // Catch: java.lang.Throwable -> L5b
            java.lang.String r1 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)     // Catch: java.lang.Throwable -> L5b
            r0[r2] = r1     // Catch: java.lang.Throwable -> L5b
            health.compact.a.ReleaseLogUtil.c(r3, r0)     // Catch: java.lang.Throwable -> L5b
            if (r4 == 0) goto L77
        L74:
            r4.cancel(r2)
        L77:
            return
        L78:
            if (r4 == 0) goto L7d
            r4.cancel(r2)
        L7d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.qvz.o():void");
    }

    static /* synthetic */ Boolean h() throws Exception {
        if (c != null) {
            c.d((ICommonReport) g);
        }
        return true;
    }

    private static boolean c(quh quhVar) {
        if (quhVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "isInvalidIntakeGoal dietRecord is null");
            return true;
        }
        qts d2 = quhVar.d();
        if (d2 == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "isInvalidIntakeGoal dietRecordOverview is null");
            return true;
        }
        float c2 = d2.c();
        LogUtil.c("DietDiaryUtils", "isInvalidIntakeGoal goal ", Float.valueOf(c2));
        return c2 <= 0.0f;
    }

    private static int a(qtk qtkVar) {
        if (qtkVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "computeIntakeGoal dietRecordBaseInfo is null");
            return 1200;
        }
        int c2 = qtkVar.c();
        int a2 = qtkVar.a();
        int b2 = qtkVar.b();
        LogUtil.c("DietDiaryUtils", "computeIntakeGoal activityCalGoal ", Integer.valueOf(c2), " restingCaloriesBasic ", Integer.valueOf(a2), " caloricDeficitGoal ", Integer.valueOf(b2), " goal ", Integer.valueOf((c2 + a2) - b2), " basalMetabolism ", Float.valueOf(a2 / 1.2f), " dietRecordBaseInfo ", qtkVar);
        return (int) UnitUtil.a(Math.max(r12, r13), 0);
    }

    private static void b(quh quhVar) {
        qts d2 = quhVar.d();
        if (d2 == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "setTodayConsumption dietCalorieOverview is null");
        } else {
            d2.c(f());
        }
    }

    private static int f() {
        CountDownLatch countDownLatch;
        boolean z = f16619a.get();
        if (!z || (countDownLatch = i) == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getTodaySportConsumption isRegisteredSportData ", Boolean.valueOf(z), " sSportDataCommonReportCountDownLatch ", i);
            return g();
        }
        try {
            if (!countDownLatch.await(3L, TimeUnit.SECONDS)) {
                ReleaseLogUtil.a("R_DietDiaryUtils", "getTodaySportConsumption isAwait false");
                return g();
            }
            int i2 = j.get();
            if (i2 <= 0) {
                ReleaseLogUtil.a("R_DietDiaryUtils", "getTodaySportConsumption todaySportConsumption ", Integer.valueOf(i2));
                i2 = g();
            }
            LogUtil.c("DietDiaryUtils", "getTodaySportConsumption todaySportConsumption ", Integer.valueOf(i2));
            return i2;
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("R_DietDiaryUtils", "getTodaySportConsumption exception ", ExceptionUtils.d(e2));
            return g();
        }
    }

    private static int g() {
        quh e2 = gsd.e();
        if (e2 == null || !jdl.ac(DateFormatUtil.c(e2.c()))) {
            LogUtil.a("DietDiaryUtils", "getTodaySportConsumptionCache dietRecord ", e2);
            return 0;
        }
        if (e2.d() == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getTodaySportConsumptionCache dietCalorieOverview is null");
            return 0;
        }
        int a2 = (int) UnitUtil.a(r0.e(), 0);
        LogUtil.c("DietDiaryUtils", "getTodaySportConsumptionCache todaySportConsumptionCache ", Integer.valueOf(a2));
        return a2;
    }

    private static quh e(int i2, long j2, qts qtsVar, List<qul> list) {
        quh quhVar = new quh(i2, j2, qtsVar, list, jdl.q(j2));
        quhVar.b(j2);
        return quhVar;
    }

    private static qts b(long j2, long j3, float f2) {
        float f3;
        int i2;
        int i3;
        int i4;
        qtk e2 = e(j2, j3);
        int d2 = e2.d();
        if (jdl.ac(j2)) {
            f3 = f();
            i2 = e2.c();
            i4 = e2.b();
            i3 = e2.e();
        } else {
            f3 = 0.0f;
            i2 = 0;
            i3 = d2;
            i4 = 0;
        }
        qts qtsVar = new qts(f2, f3, a(e2), d2);
        qtsVar.a(e2.h());
        qtsVar.c(i2);
        qtsVar.e(i4);
        qtsVar.a(i3);
        LogUtil.c("DietDiaryUtils", "getDietCalorieOverview endTime ", Long.valueOf(j2), " timeout ", Long.valueOf(j3), " intake ", Float.valueOf(f2), " dietRecordBaseInfo ", e2, " dietCalorieOverview ", qtsVar);
        return qtsVar;
    }

    private static qtk e(final long j2, long j3) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "getDietRecordBaseInfo endTime ", Long.valueOf(j2), " timeout ", Long.valueOf(j3));
        final qtk qtkVar = new qtk();
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        WeightTargetDifferences k = gsd.k();
        if (k == null) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwu
                @Override // java.lang.Runnable
                public final void run() {
                    rag.a((ResponseCallback<WeightTargetDifferences>) new ResponseCallback() { // from class: qwo
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i2, Object obj) {
                            qvz.c(r1, r2, r4, i2, (WeightTargetDifferences) obj);
                        }
                    });
                }
            });
        } else {
            LogUtil.c("DietDiaryUtils", "getDietRecordBaseInfo weightTargetDifferencesCache ", k);
            a(j2, qtkVar, countDownLatch, k);
        }
        gsi f2 = gsd.f();
        if (f2 == null) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwx
                @Override // java.lang.Runnable
                public final void run() {
                    kot.a().b(new ResponseCallback() { // from class: qyg
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i2, Object obj) {
                            qvz.e(qtk.this, r2, i2, (gsi) obj);
                        }
                    });
                }
            });
        } else {
            LogUtil.c("DietDiaryUtils", "getDietRecordBaseInfo weightManagerCache ", f2);
            qtkVar.d(f2.c());
            qtkVar.e(f2.a());
            countDownLatch.countDown();
        }
        Map<Integer, List<HiHealthData>> e2 = gsd.e(j2);
        int e3 = e(e2, DicDataTypeUtil.DataType.RESTING_METABOLISM.value());
        AtomicLong atomicLong = b;
        final long j4 = atomicLong.get();
        long currentTimeMillis = System.currentTimeMillis();
        if (e3 <= 0 || Math.abs(currentTimeMillis - j4) > 60000) {
            atomicLong.set(currentTimeMillis);
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwz
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.b(r0, (ResponseCallback<Map<Integer, List<HiHealthData>>>) new ResponseCallback() { // from class: qxy
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i2, Object obj) {
                            qvz.d(r1, r3, r5, r6, i2, (Map) obj);
                        }
                    });
                }
            });
        } else {
            LogUtil.c("DietDiaryUtils", "getDietRecordBaseInfo timeMillis ", Long.valueOf(j4), " mapCache ", e2);
            a(j2, qtkVar, countDownLatch, e2);
        }
        try {
            ReleaseLogUtil.b("R_DietDiaryUtils", "getDietRecordBaseInfo isAwait ", Boolean.valueOf(countDownLatch.await(j3 <= 0 ? 5000L : j3, TimeUnit.MILLISECONDS)), " timeout ", Long.valueOf(j3));
        } catch (InterruptedException e4) {
            ReleaseLogUtil.c("R_DietDiaryUtils", "getDietRecordBaseInfo exception ", ExceptionUtils.d(e4));
        }
        LogUtil.c("DietDiaryUtils", "getDietRecordBaseInfo dietRecordBaseInfo ", qtkVar);
        return qtkVar;
    }

    static /* synthetic */ void c(CountDownLatch countDownLatch, long j2, qtk qtkVar, int i2, WeightTargetDifferences weightTargetDifferences) {
        LogUtil.c("DietDiaryUtils", "getDietRecordBaseInfo weightTargetDifferences ", weightTargetDifferences);
        if (weightTargetDifferences == null) {
            countDownLatch.countDown();
        } else {
            a(j2, qtkVar, countDownLatch, weightTargetDifferences);
        }
    }

    static /* synthetic */ void e(qtk qtkVar, CountDownLatch countDownLatch, int i2, gsi gsiVar) {
        LogUtil.c("DietDiaryUtils", "getDietRecordBaseInfo weightManager ", gsiVar);
        if (gsiVar != null) {
            qtkVar.d(gsiVar.c());
            qtkVar.e(gsiVar.a());
        }
        countDownLatch.countDown();
    }

    static /* synthetic */ void d(long j2, long j3, qtk qtkVar, CountDownLatch countDownLatch, int i2, Map map) {
        LogUtil.c("DietDiaryUtils", "getDietRecordBaseInfo timeMillis ", Long.valueOf(j2), " map ", map);
        a(j3, qtkVar, countDownLatch, (Map<Integer, List<HiHealthData>>) map);
    }

    private static void a(final long j2, final qtk qtkVar, final CountDownLatch countDownLatch, WeightTargetDifferences weightTargetDifferences) {
        long e2 = weightTargetDifferences.e();
        int c2 = jdl.c(e2);
        int c3 = jdl.c(j2);
        ReleaseLogUtil.b("R_DietDiaryUtils", "setWeightTargetDifferences targetBeginDate ", Long.valueOf(e2), " targetBeginDateDay ", Integer.valueOf(c2), " endTimeDay ", Integer.valueOf(c3), " endTime ", Long.valueOf(j2));
        if (c2 <= c3) {
            qtkVar.d(weightTargetDifferences.a() / 1000.0d);
            countDownLatch.countDown();
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwg
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.a(j2, qtkVar, countDownLatch);
                }
            });
        }
    }

    static /* synthetic */ void a(long j2, final qtk qtkVar, final CountDownLatch countDownLatch) {
        HiDataReadOption b2 = b(0L, j2);
        b2.setCount(1);
        c(b2, (ResponseCallback<Map<Integer, quh>>) new ResponseCallback() { // from class: qwr
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                qvz.b(qtk.this, countDownLatch, i2, (Map) obj);
            }
        });
    }

    static /* synthetic */ void b(qtk qtkVar, CountDownLatch countDownLatch, int i2, Map map) {
        quh quhVar;
        qts d2;
        LogUtil.c("DietDiaryUtils", "setWeightTargetDifferences readDietRecord map ", map);
        if (map != null) {
            Set entrySet = map.entrySet();
            if (koq.c(entrySet)) {
                Iterator it = entrySet.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (entry != null && (quhVar = (quh) entry.getValue()) != null && (d2 = quhVar.d()) != null) {
                        qtkVar.d(d2.j());
                        break;
                    }
                }
            }
        }
        countDownLatch.countDown();
    }

    private static void a(final long j2, final qtk qtkVar, final CountDownLatch countDownLatch, Map<Integer, List<HiHealthData>> map) {
        if (map == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "setRestingCalories map is null");
            return;
        }
        final List<HiHealthData> list = map.get(Integer.valueOf(DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM.value()));
        int e2 = e(map, DicDataTypeUtil.DataType.RESTING_METABOLISM.value());
        if (e2 > 0) {
            qtkVar.b(e2);
            int e3 = e(map, DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE.value());
            if (e3 > 0) {
                e2 = e3;
            }
            qtkVar.a(e2);
            c(j2, list, qtkVar);
            countDownLatch.countDown();
            return;
        }
        ReleaseLogUtil.a("R_DietDiaryUtils", "setRestingCalories restingCaloriesBasic ", Integer.valueOf(e2));
        ThreadPoolManager.d().execute(new Runnable() { // from class: qxh
            @Override // java.lang.Runnable
            public final void run() {
                qvz.d(j2, (List<HiHealthData>) list, qtkVar, countDownLatch);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(long j2, List<HiHealthData> list, qtk qtkVar, CountDownLatch countDownLatch) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.BASAL_METABOLISM});
        hiAggregateOption.setType(new int[]{2024});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setStartTime(jdl.d(j2, -29));
        LogUtil.c("DietDiaryUtils", "queryBasalMetabolismList hiAggregateOption ", hiAggregateOption, " currentRestingCaloriesList ", list, " dietRecordBaseInfo ", qtkVar);
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new AnonymousClass3(j2, list, qtkVar, countDownLatch));
    }

    /* renamed from: qvz$3, reason: invalid class name */
    class AnonymousClass3 implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f16620a;
        final /* synthetic */ List c;
        final /* synthetic */ qtk d;
        final /* synthetic */ CountDownLatch e;

        AnonymousClass3(long j, List list, qtk qtkVar, CountDownLatch countDownLatch) {
            this.f16620a = j;
            this.c = list;
            this.d = qtkVar;
            this.e = countDownLatch;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "queryBasalMetabolismList errorCode ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
            double d = 0.0d;
            if (koq.c(list)) {
                Iterator<HiHealthData> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    HiHealthData next = it.next();
                    if (next != null && next.getDouble(BleConstants.BASAL_METABOLISM) > 0.0d && String.valueOf(0).equals(next.getMetaData())) {
                        d = next.getDouble(BleConstants.BASAL_METABOLISM);
                        break;
                    }
                }
            }
            final int a2 = (int) UnitUtil.a(1.2000000476837158d * d, 0);
            LogUtil.c("DietDiaryUtils", "queryBasalMetabolismList restingCaloriesBasic ", Integer.valueOf(a2), " basalMetabolism ", Double.valueOf(d));
            ThreadPoolManager d2 = ThreadPoolManager.d();
            final long j = this.f16620a;
            final List list2 = this.c;
            final qtk qtkVar = this.d;
            final CountDownLatch countDownLatch = this.e;
            d2.execute(new Runnable() { // from class: qyo
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.AnonymousClass3.e(a2, j, list2, qtkVar, countDownLatch);
                }
            });
        }

        static /* synthetic */ void e(final int i, final long j, final List list, final qtk qtkVar, final CountDownLatch countDownLatch) {
            if (i <= 0) {
                qvz.e(j, (List<HiHealthData>) list, qtkVar, countDownLatch);
            } else {
                qvz.a(i, j, (ResponseCallback<Integer>) new ResponseCallback() { // from class: qyl
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i2, Object obj) {
                        qvz.AnonymousClass3.b(qtk.this, i, j, list, countDownLatch, i2, (Integer) obj);
                    }
                });
            }
        }

        static /* synthetic */ void b(qtk qtkVar, int i, long j, List list, CountDownLatch countDownLatch, int i2, Integer num) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "queryBasalMetabolismList insertErrorCode ", Integer.valueOf(i2));
            if (i2 == 0) {
                qtkVar.b(i);
                qvz.c(j, (List<HiHealthData>) list, qtkVar);
            }
            countDownLatch.countDown();
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.c("DietDiaryUtils", "queryBasalMetabolismList onResultIntent intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3), " list ", list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final long j2, final List<HiHealthData> list, final qtk qtkVar, final CountDownLatch countDownLatch) {
        if (qtkVar.j() == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "queryWeightList userInfomation is null");
            countDownLatch.countDown();
        } else {
            long c2 = jdl.c(j2, -1);
            e(jdl.d(c2, -29), c2, (ResponseCallback<Double>) new ResponseCallback() { // from class: qwd
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i2, Object obj) {
                    qvz.e(j2, list, qtkVar, countDownLatch, i2, (Double) obj);
                }
            });
        }
    }

    static /* synthetic */ void e(final long j2, final List list, final qtk qtkVar, final CountDownLatch countDownLatch, int i2, Double d2) {
        double doubleValue = d2 != null ? d2.doubleValue() : 0.0d;
        if (doubleValue > 0.0d) {
            b(j2, (List<HiHealthData>) list, qtkVar, countDownLatch, doubleValue);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qxd
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.e(0L, r0, (ResponseCallback<Double>) new ResponseCallback() { // from class: qxc
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i3, Object obj) {
                            qvz.b(r1, (List<HiHealthData>) r3, r4, r5, r13 != null ? ((Double) obj).doubleValue() : 0.0d);
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(long j2, long j3, final ResponseCallback<Double> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "queryWeight callback is null startTime ", Long.valueOf(j2), " endTime ", Long.valueOf(j3));
            return;
        }
        HiAggregateOption a2 = grz.a("");
        a2.setSortOrder(1);
        a2.setAggregateType(1);
        a2.setGroupUnitType(0);
        a2.setCount(1);
        a2.setTimeRange(j2, j3);
        LogUtil.c("DietDiaryUtils", "queryWeight hiAggregateOption ", a2);
        rag.a(a2, (ResponseCallback<List<cfe>>) new ResponseCallback() { // from class: qxu
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                qvz.d(ResponseCallback.this, i2, (List) obj);
            }
        });
    }

    static /* synthetic */ void d(ResponseCallback responseCallback, int i2, List list) {
        cfe cfeVar;
        LogUtil.c("DietDiaryUtils", "queryWeight errorCode ", Integer.valueOf(i2), " list ", list);
        responseCallback.onResponse(i2, Double.valueOf((!koq.c(list) || (cfeVar = (cfe) list.get(0)) == null) ? 0.0d : cfeVar.ax()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final long j2, final List<HiHealthData> list, final qtk qtkVar, final CountDownLatch countDownLatch, double d2) {
        UserInfomation j3 = qtkVar.j();
        if (j3 == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "setRestingCaloriesBasic userInfomation is null");
            countDownLatch.countDown();
            return;
        }
        double weight = d2 > 0.0d ? d2 : j3.getWeight();
        int height = j3.getHeight();
        int age = j3.getAge();
        if (weight <= 0.0d || height <= 0 || age <= 0) {
            LogUtil.a("DietDiaryUtils", "setRestingCaloriesBasic weight ", Double.valueOf(weight), " height ", Integer.valueOf(height), " age ", Integer.valueOf(age));
            countDownLatch.countDown();
            return;
        }
        int gender = j3.getGender();
        double d3 = ((10.0d * weight) + (height * 6.25d)) - (age * 5);
        double d4 = (gender == 0 ? d3 + 5.0d : d3 - 161.0d) * 1.2d;
        final int a2 = (int) UnitUtil.a(d4, 0);
        if (a2 <= 0) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "setRestingCaloriesBasic restingCaloriesBasic ", Integer.valueOf(a2));
            countDownLatch.countDown();
        } else {
            LogUtil.c("DietDiaryUtils", "setRestingCaloriesBasic lastWeight ", Double.valueOf(d2), " weight ", Double.valueOf(weight), " height ", Integer.valueOf(height), " age ", Integer.valueOf(age), " gender ", Integer.valueOf(gender), " tempRestingCaloriesBasic ", Double.valueOf(d4), " restingCaloriesBasic ", Integer.valueOf(a2));
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwe
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.a(r0, r1, (ResponseCallback<Integer>) new ResponseCallback() { // from class: qwf
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i2, Object obj) {
                            qvz.c(qtk.this, r2, r3, r5, r6, i2, (Integer) obj);
                        }
                    });
                }
            });
        }
    }

    static /* synthetic */ void c(qtk qtkVar, int i2, long j2, List list, CountDownLatch countDownLatch, int i3, Integer num) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "setRestingCaloriesBasic insertErrorCode ", Integer.valueOf(i3));
        if (i3 == 0) {
            qtkVar.b(i2);
            c(j2, (List<HiHealthData>) list, qtkVar);
        }
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(long j2, long j3, ResponseCallback<Map<Integer, Integer>> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "querySportCalories callback is null");
            return;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{40003});
        hiAggregateOption.setConstantsKey(new String[]{"calorie_sum"});
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setStartTime(j2);
        hiAggregateOption.setEndTime(j3);
        LogUtil.c("DietDiaryUtils", "querySportCalories hiAggregateOption ", hiAggregateOption);
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new AnonymousClass1(responseCallback));
    }

    /* renamed from: qvz$1, reason: invalid class name */
    class AnonymousClass1 implements HiAggregateListener {
        final /* synthetic */ ResponseCallback b;

        AnonymousClass1(ResponseCallback responseCallback) {
            this.b = responseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, final int i, int i2) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "querySportCalories errorCode ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
            final HashMap hashMap = new HashMap();
            if (koq.c(list)) {
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData != null) {
                        hashMap.put(Integer.valueOf(DateFormatUtil.c(hiHealthData.getStartTime(), TimeZone.getDefault())), Integer.valueOf((int) UnitUtil.a(hiHealthData.getInt("calorie_sum") / 1000.0d, 0)));
                    }
                }
            }
            ThreadPoolManager d = ThreadPoolManager.d();
            final ResponseCallback responseCallback = this.b;
            d.execute(new Runnable() { // from class: qyn
                @Override // java.lang.Runnable
                public final void run() {
                    ResponseCallback.this.onResponse(i, hashMap);
                }
            });
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "querySportCalories onResultIntent intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(long j2, List<HiHealthData> list, qtk qtkVar) {
        int i2;
        double t;
        double d2;
        if (qtkVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "setRestingCaloriesBurned dietRecordBaseInfo is null timeMillis ", Long.valueOf(j2), " currentRestingCaloriesList ", list);
            return;
        }
        int c2 = jdl.c(j2);
        int c3 = jdl.c(System.currentTimeMillis());
        double d3 = qtkVar.d();
        ReleaseLogUtil.b("R_DietDiaryUtils", "setRestingCaloriesBurned timeMillis ", Long.valueOf(j2), " timeDays ", Integer.valueOf(c2), " currentDays ", Integer.valueOf(c3));
        int i3 = 0;
        if (c2 < c3) {
            qtkVar.c((int) UnitUtil.a(d3, 0));
            LogUtil.c("DietDiaryUtils", "setRestingCaloriesBurned restingCalories ", Double.valueOf(d3), " dietRecordBaseInfo ", qtkVar);
            return;
        }
        double d4 = 1000.0d;
        long j3 = 0;
        if (koq.c(list)) {
            i2 = 0;
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData != null) {
                    int a2 = (int) UnitUtil.a(hiHealthData.getValue() / d4, i3);
                    LogUtil.c("DietDiaryUtils", "setRestingCaloriesBurned currentRestingCaloriesTemp ", Integer.valueOf(a2), " currentRestingCalories ", Integer.valueOf(i2));
                    if (a2 > i2) {
                        j3 = hiHealthData.getModifiedTime();
                        i2 = a2;
                    }
                    i3 = 0;
                    d4 = 1000.0d;
                }
            }
        } else {
            i2 = 0;
        }
        double currentTimeMillis = System.currentTimeMillis();
        if (i2 > 0) {
            t = j3;
            if (currentTimeMillis <= t) {
                d2 = 0.0d;
                qtkVar.c((int) UnitUtil.a(i2 + (((d3 / 24.0d) / 60.0d) * d2), 0));
                LogUtil.c("DietDiaryUtils", "setRestingCaloriesBurned restingCalories ", Double.valueOf(d3), " currentRestingCalories ", Integer.valueOf(i2), " modifiedTime ", Long.valueOf(j3), " diffMinute ", Double.valueOf(d2), " dietRecordBaseInfo ", qtkVar, " currentRestingCaloriesList ", list);
            }
        } else {
            t = jdl.t(System.currentTimeMillis());
        }
        d2 = ((currentTimeMillis - t) / 1000.0d) / 60.0d;
        qtkVar.c((int) UnitUtil.a(i2 + (((d3 / 24.0d) / 60.0d) * d2), 0));
        LogUtil.c("DietDiaryUtils", "setRestingCaloriesBurned restingCalories ", Double.valueOf(d3), " currentRestingCalories ", Integer.valueOf(i2), " modifiedTime ", Long.valueOf(j3), " diffMinute ", Double.valueOf(d2), " dietRecordBaseInfo ", qtkVar, " currentRestingCaloriesList ", list);
    }

    private static quh d(quh quhVar) {
        if (quhVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "dietRecord is null");
            return null;
        }
        return new quh(quhVar.c(), quhVar.f(), quhVar.d(), quhVar.a(), quhVar.j());
    }

    private static HiDataReadOption b(long j2, long j3) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(jdl.d(j2, -1));
        hiDataReadOption.setEndTime(jdl.c(j3, 1));
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.DIET_RECORD.value()});
        hiDataReadOption.setSortOrder(1);
        LogUtil.c("DietDiaryUtils", "getDietRecordOption hiDataReadOption ", hiDataReadOption);
        return hiDataReadOption;
    }

    private static void c(final long j2, final long j3) {
        boolean d2 = ProcessUtil.d();
        ReleaseLogUtil.b("R_DietDiaryUtils", "syncDietRecordToDevice isMainProcess ", Boolean.valueOf(d2), " startTime ", Long.valueOf(j2), " endTime ", Long.valueOf(j3));
        if (d2) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwb
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.d(j2, j3);
                }
            });
        }
    }

    static /* synthetic */ void d(long j2, long j3) {
        jfq.c();
        dpe.e(j2, j3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final quh quhVar, final boolean z, final ResponseCallback<quh> responseCallback) {
        boolean a2 = jfa.a();
        ReleaseLogUtil.b("R_DietDiaryUtils", "insertDietRecord isQueryDietRecord ", Boolean.valueOf(z), " isPullAllStatus ", Boolean.valueOf(a2));
        if (!a2) {
            if (responseCallback != null) {
                responseCallback.onResponse(600002, null);
                return;
            }
            return;
        }
        if (c(quhVar)) {
            if (responseCallback != null) {
                responseCallback.onResponse(AwarenessConstants.ERROR_UNKNOWN_CODE, null);
                return;
            }
            return;
        }
        final String j2 = quhVar.j();
        final long f2 = quhVar.f();
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(DicDataTypeUtil.DataType.DIET_RECORD_SET.value());
        hiHealthData.setTimeZone(j2);
        hiHealthData.setStartTime(f2);
        hiHealthData.setEndTime(f2);
        hiHealthData.setDeviceUuid("-1");
        if (z) {
            hiHealthData.setModifiedTime(quhVar.b());
        }
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.DIET_RECORD.value()), HiJsonUtil.e(quhVar));
        hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        LogUtil.c("DietDiaryUtils", "insertDietRecord hiDataInsertOption ", hiDataInsertOption);
        HiHealthNativeApi.a(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: qwm
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                qvz.c(z, responseCallback, quhVar, f2, j2, i2, obj);
            }
        });
    }

    static /* synthetic */ void c(final boolean z, final ResponseCallback responseCallback, final quh quhVar, final long j2, final String str, final int i2, Object obj) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "insertDietRecord isQueryDietRecord ", Boolean.valueOf(z), " errorCode ", Integer.valueOf(i2), " object ", obj);
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "insertDietRecord callback is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwp
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.d(z, i2, responseCallback, quhVar, j2, str);
                }
            });
        }
    }

    static /* synthetic */ void d(boolean z, int i2, ResponseCallback responseCallback, quh quhVar, long j2, String str) {
        if (z || i2 != 0) {
            responseCallback.onResponse(i2, quhVar);
        } else {
            responseCallback.onResponse(i2, quhVar);
            LogUtil.c("DietDiaryUtils", "insertDietRecord time ", Long.valueOf(j2), " timeZone ", str, " dietRecord ", quhVar);
        }
    }

    private static void c(HiDataReadOption hiDataReadOption, ResponseCallback<Map<Integer, quh>> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "readDietRecord callback is null");
        } else if (hiDataReadOption == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "readDietRecord hiDataReadOption is null");
            responseCallback.onResponse(-1, new HashMap());
        } else {
            LogUtil.c("DietDiaryUtils", "readDietRecord hiDataReadOption ", hiDataReadOption);
            HiHealthNativeApi.a(BaseApplication.e()).readHiHealthData(hiDataReadOption, new AnonymousClass4(responseCallback));
        }
    }

    /* renamed from: qvz$4, reason: invalid class name */
    class AnonymousClass4 implements HiDataReadResultListener {
        final /* synthetic */ ResponseCallback c;

        AnonymousClass4(ResponseCallback responseCallback) {
            this.c = responseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, final int i, int i2) {
            quh quhVar;
            ReleaseLogUtil.b("R_DietDiaryUtils", "readDietRecord onResult errorCode ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
            if (!(obj instanceof SparseArray)) {
                ThreadPoolManager d = ThreadPoolManager.d();
                final ResponseCallback responseCallback = this.c;
                d.execute(new Runnable() { // from class: qyk
                    @Override // java.lang.Runnable
                    public final void run() {
                        ResponseCallback.this.onResponse(i, new HashMap());
                    }
                });
                return;
            }
            Object obj2 = ((SparseArray) obj).get(DicDataTypeUtil.DataType.DIET_RECORD.value());
            if (!koq.e(obj2, HiHealthData.class)) {
                ThreadPoolManager d2 = ThreadPoolManager.d();
                final ResponseCallback responseCallback2 = this.c;
                d2.execute(new Runnable() { // from class: qyt
                    @Override // java.lang.Runnable
                    public final void run() {
                        ResponseCallback.this.onResponse(i, new HashMap());
                    }
                });
                return;
            }
            final HashMap hashMap = new HashMap();
            for (HiHealthData hiHealthData : (List) obj2) {
                if (hiHealthData != null) {
                    String metaData = hiHealthData.getMetaData();
                    if (!TextUtils.isEmpty(metaData)) {
                        try {
                            quhVar = (quh) HiJsonUtil.e(metaData, quh.class);
                        } catch (Exception e) {
                            ReleaseLogUtil.c("R_DietDiaryUtils", "readDietRecord onResult exception ", ExceptionUtils.d(e));
                            quhVar = null;
                        }
                        if (quhVar == null) {
                            ReleaseLogUtil.a("R_DietDiaryUtils", "readDietRecord onResult record is null metaData ", metaData);
                        } else {
                            long f = quhVar.f();
                            if (String.valueOf(f).matches("\\d{10}")) {
                                ReleaseLogUtil.a("R_DietDiaryUtils", "readDietRecord onResult time ", Long.valueOf(f), " data ", hiHealthData);
                                f *= 1000;
                                quhVar.e(f);
                            }
                            int c = qvz.c(quhVar, f, quhVar.j());
                            quhVar.b(hiHealthData.getModifiedTime());
                            quhVar.e();
                            hashMap.put(Integer.valueOf(c), quhVar);
                        }
                    }
                }
            }
            ThreadPoolManager d3 = ThreadPoolManager.d();
            final ResponseCallback responseCallback3 = this.c;
            d3.execute(new Runnable() { // from class: qyq
                @Override // java.lang.Runnable
                public final void run() {
                    ResponseCallback.this.onResponse(i, hashMap);
                }
            });
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "readDietRecord onResultIntent intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3), " object ", obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(quh quhVar, long j2, String str) {
        int c2;
        if (String.valueOf(quhVar.c()).matches("\\d{8}")) {
            return quhVar.c();
        }
        if (jdl.q(j2, jdl.b()) == j2) {
            c2 = DateFormatUtil.c(j2, jdl.b());
            quhVar.c(c2);
        } else {
            c2 = DateFormatUtil.c(j2, jdl.d(str));
            quhVar.c(c2);
        }
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final int i2, long j2, final ResponseCallback<Integer> responseCallback) {
        if (i2 <= 0) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "insertBasicRestingCalories restingCaloriesBasic ", Integer.valueOf(i2));
            if (responseCallback != null) {
                responseCallback.onResponse(-1, Integer.valueOf(i2));
                return;
            }
            return;
        }
        boolean a2 = jfa.a();
        LogUtil.c("DietDiaryUtils", "insertBasicRestingCalories restingCaloriesBasic ", Integer.valueOf(i2), " timeMillis ", Long.valueOf(j2), " isPullAllStatus ", Boolean.valueOf(a2));
        if (!a2) {
            if (responseCallback != null) {
                responseCallback.onResponse(600002, Integer.valueOf(i2));
                return;
            }
            return;
        }
        HiHealthData hiHealthData = new HiHealthData();
        long t = jdl.t(j2);
        hiHealthData.setEndTime(t);
        hiHealthData.setStartTime(t);
        hiHealthData.setModifiedTime(1L);
        hiHealthData.setType(DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value());
        hiHealthData.setDeviceUuid("-1");
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.RESTING_METABOLISM.value()), Integer.valueOf(i2 * 1000));
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        LogUtil.c("DietDiaryUtils", "insertBasicRestingCalories hiDataInsertOption ", hiDataInsertOption);
        HiHealthNativeApi.a(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: qwc
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i3, Object obj) {
                qvz.b(ResponseCallback.this, i2, i3, obj);
            }
        });
    }

    static /* synthetic */ void b(final ResponseCallback responseCallback, final int i2, final int i3, Object obj) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "insertBasicRestingCalories errorCode ", Integer.valueOf(i3), " object ", obj);
        if (i3 == 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value()));
            c(arrayList);
        }
        if (responseCallback != null) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qxl
                @Override // java.lang.Runnable
                public final void run() {
                    ResponseCallback.this.onResponse(i3, Integer.valueOf(i2));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(long j2, ResponseCallback<Map<Integer, List<HiHealthData>>> responseCallback) {
        int[] iArr;
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "readRestingCalorie callback is null");
            return;
        }
        if (jdl.ac(j2)) {
            iArr = new int[]{DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE.value(), DicDataTypeUtil.DataType.RESTING_METABOLISM.value(), DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM.value()};
        } else {
            iArr = new int[]{DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE.value(), DicDataTypeUtil.DataType.RESTING_METABOLISM.value()};
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setEndTime(jdl.e(j2));
        hiDataReadOption.setStartTime(jdl.t(j2));
        LogUtil.c("DietDiaryUtils", "readRestingCalorie hiDataReadOption ", hiDataReadOption, " timeMillis ", Long.valueOf(j2));
        HiHealthManager.d(BaseApplication.e()).readHiHealthData(hiDataReadOption, new AnonymousClass2(responseCallback, j2));
    }

    /* renamed from: qvz$2, reason: invalid class name */
    class AnonymousClass2 implements HiDataReadResultListener {
        final /* synthetic */ long d;
        final /* synthetic */ ResponseCallback e;

        AnonymousClass2(ResponseCallback responseCallback, long j) {
            this.e = responseCallback;
            this.d = j;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, final int i, int i2) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "readRestingCalorie onResult errorCode ", Integer.valueOf(i));
            final HashMap hashMap = new HashMap();
            if (obj instanceof SparseArray) {
                SparseArray sparseArray = (SparseArray) obj;
                Object obj2 = sparseArray.get(DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE.value());
                if (koq.e(obj2, HiHealthData.class)) {
                    hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE.value()), (List) obj2);
                }
                Object obj3 = sparseArray.get(DicDataTypeUtil.DataType.RESTING_METABOLISM.value());
                if (koq.e(obj3, HiHealthData.class)) {
                    hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.RESTING_METABOLISM.value()), (List) obj3);
                }
                Object obj4 = sparseArray.get(DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM.value());
                if (koq.e(obj4, HiHealthData.class)) {
                    hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM.value()), (List) obj4);
                }
            }
            ThreadPoolManager d = ThreadPoolManager.d();
            final ResponseCallback responseCallback = this.e;
            d.execute(new Runnable() { // from class: qyp
                @Override // java.lang.Runnable
                public final void run() {
                    ResponseCallback.this.onResponse(i, hashMap);
                }
            });
            gsd.b(this.d, hashMap);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "readRestingCalorie onResultIntent intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3), " object ", obj);
        }
    }

    private static int e(Map<Integer, List<HiHealthData>> map, int i2) {
        if (map == null) {
            ReleaseLogUtil.a("DietDiaryUtils", "getRestingCalories map is null key ", Integer.valueOf(i2));
            return 0;
        }
        List<HiHealthData> list = map.get(Integer.valueOf(i2));
        if (koq.b(list)) {
            ReleaseLogUtil.a("DietDiaryUtils", "getRestingCalories list ", list, " key ", Integer.valueOf(i2));
            return 0;
        }
        int i3 = 0;
        int i4 = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                int a2 = (int) UnitUtil.a(hiHealthData.getValue() / 1000.0d, 0);
                if (hiHealthData.getModifiedTime() == 1) {
                    i3 = Math.max(i3, a2);
                } else {
                    i4 = Math.max(i4, a2);
                }
            }
        }
        LogUtil.c("DietDiaryUtils", "getRestingCalories formulaRestingCalories ", Integer.valueOf(i3), " restingCalories ", Integer.valueOf(i4), " key ", Integer.valueOf(i2), " map ", map);
        return i4 > 0 ? i4 : i3;
    }

    private static void a(qul qulVar) {
        if (qulVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "setEatTime meal is null");
            return;
        }
        long d2 = qulVar.d();
        if (String.valueOf(d2).matches("\\d{10}")) {
            qulVar.e(d2 * 1000);
        }
    }

    private static long a(long j2) {
        long t = jdl.t(j2);
        long t2 = jdl.t(System.currentTimeMillis());
        int e2 = jdl.e(t, t2);
        long j3 = e2 > 1 ? t : t2;
        ReleaseLogUtil.b("R_DietDiaryUtils", "getStartTime eatTime ", Long.valueOf(j2), " eatStartTime ", Long.valueOf(t), " currentStartTime ", Long.valueOf(t2), " intervalDay ", Integer.valueOf(e2), " startTime ", Long.valueOf(j3));
        return j3;
    }

    public static void c(final List<Integer> list) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "syncCloud syncDataTypeList ", list);
        ThreadPoolManager.d().execute(new Runnable() { // from class: qwv
            @Override // java.lang.Runnable
            public final void run() {
                qvz.e(list);
            }
        });
    }

    static /* synthetic */ void e(List list) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setForceSync(true);
        hiSyncOption.setSyncDataTypes(list);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, new HiCommonListener() { // from class: qvz.5
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i2, Object obj) {
                ReleaseLogUtil.b("R_DietDiaryUtils", "syncCloud onSuccess errorCode ", Integer.valueOf(i2), " object ", obj);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i2, Object obj) {
                ReleaseLogUtil.a("R_DietDiaryUtils", "syncCloud onFailure errorCode ", Integer.valueOf(i2), " errorMessage ", obj);
            }
        });
    }

    public static void a(final qul qulVar, final ResponseCallback<quh> responseCallback) {
        LogUtil.c("DietDiaryUtils", "addMeal meal ", qulVar);
        if (qulVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "addMeal meal is null");
            if (responseCallback != null) {
                responseCallback.onResponse(-1, null);
                return;
            }
            return;
        }
        a(qulVar);
        qulVar.c(System.currentTimeMillis());
        final long d2 = qulVar.d();
        final long a2 = a(d2);
        final long e2 = jdl.e(a2);
        c(b(a2, e2), (ResponseCallback<Map<Integer, quh>>) new ResponseCallback() { // from class: qxs
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                qvz.c(ResponseCallback.this, qulVar, d2, a2, e2, i2, (Map) obj);
            }
        });
        qtn.b();
    }

    static /* synthetic */ void c(final ResponseCallback responseCallback, final qul qulVar, final long j2, final long j3, final long j4, int i2, final Map map) {
        LogUtil.c("DietDiaryUtils", "addMeal readDietRecord errorCode ", Integer.valueOf(i2), " map ", map);
        if (map == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "addMeal readDietRecord map is null");
            if (responseCallback != null) {
                responseCallback.onResponse(i2, null);
                return;
            }
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: qxv
            @Override // java.lang.Runnable
            public final void run() {
                qvz.c(qul.this, j2, map, j3, j4, responseCallback);
            }
        });
    }

    static /* synthetic */ void c(qul qulVar, long j2, Map map, final long j3, final long j4, final ResponseCallback responseCallback) {
        quh quhVar;
        TimeZone timeZone = TimeZone.getDefault();
        String j5 = qulVar.j();
        if (!TextUtils.isEmpty(j5) && !jdl.q(System.currentTimeMillis()).equals(j5)) {
            int offset = timeZone.getOffset(j2);
            int rawOffset = TimeZone.getTimeZone("GMT" + j5).getRawOffset();
            qulVar.e((((long) offset) + j2) - ((long) rawOffset));
            LogUtil.c("DietDiaryUtils", "addMeal readDietRecord eatTime ", Long.valueOf(j2), " offset ", Integer.valueOf(offset), " rawOffset ", Integer.valueOf(rawOffset));
        }
        int c2 = DateFormatUtil.c(qulVar.d(), jdl.d(qulVar.j()));
        quh quhVar2 = (quh) map.get(Integer.valueOf(c2));
        LogUtil.c("DietDiaryUtils", "addMeal readDietRecord timeZoneString ", j5, " eatTime ", Long.valueOf(j2), " startTime ", Long.valueOf(j3), " endTime ", Long.valueOf(j4), " dayFormat ", Integer.valueOf(c2), " dietRecord ", quhVar2);
        if (quhVar2 == null) {
            quhVar = e(c2, jdl.t(qulVar.d()), b(System.currentTimeMillis(), 0L, qulVar.b()), (List<qul>) Collections.singletonList(qulVar));
        } else {
            quhVar2.a(qulVar);
            quhVar = quhVar2;
        }
        LogUtil.c("DietDiaryUtils", "addMeal readDietRecord dietRecord ", quhVar);
        d((quh) HiJsonUtil.e(HiJsonUtil.e(quhVar), quh.class), false, (ResponseCallback<quh>) new ResponseCallback() { // from class: qxt
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                qvz.c(ResponseCallback.this, j3, j4, i2, (quh) obj);
            }
        });
    }

    static /* synthetic */ void c(ResponseCallback responseCallback, long j2, long j3, int i2, quh quhVar) {
        LogUtil.c("DietDiaryUtils", "addMeal insertDietRecord resultCode ", Integer.valueOf(i2), " resultDietRecord ", quhVar);
        if (responseCallback != null) {
            responseCallback.onResponse(i2, quhVar);
        }
        if (i2 != 0) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "addMeal insertDietRecord resultCode ", Integer.valueOf(i2), " startTime ", Long.valueOf(j2), " endTime ", Long.valueOf(j3));
        } else {
            c(j2, j3);
        }
    }

    public static void c(final qul qulVar, final ResponseCallback<quh> responseCallback) {
        if (qulVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "updateMeal meal is null");
            if (responseCallback != null) {
                responseCallback.onResponse(-1, null);
                return;
            }
            return;
        }
        a(qulVar);
        qulVar.c(System.currentTimeMillis());
        final long d2 = qulVar.d();
        final long a2 = a(d2);
        final long e2 = jdl.e(a2);
        c(b(a2, e2), (ResponseCallback<Map<Integer, quh>>) new ResponseCallback() { // from class: qyf
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                qvz.d(ResponseCallback.this, d2, qulVar, a2, e2, i2, (Map) obj);
            }
        });
    }

    static /* synthetic */ void d(final ResponseCallback responseCallback, long j2, qul qulVar, final long j3, final long j4, int i2, Map map) {
        LogUtil.c("DietDiaryUtils", "updateMeal readDietRecord errorCode ", Integer.valueOf(i2), " map ", map);
        if (map == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "updateMeal readDietRecord map is null");
            if (responseCallback != null) {
                responseCallback.onResponse(i2, null);
                return;
            }
            return;
        }
        int c2 = DateFormatUtil.c(j2, jdl.d(qulVar.j()));
        quh quhVar = (quh) map.get(Integer.valueOf(c2));
        if (quhVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "updateMeal readDietRecord dietRecord is null dayFormat ", Integer.valueOf(c2));
            if (responseCallback != null) {
                responseCallback.onResponse(i2, null);
                return;
            }
            return;
        }
        boolean b2 = quhVar.b(qulVar);
        LogUtil.c("DietDiaryUtils", "updateMeal readDietRecord isSuccess ", Boolean.valueOf(b2), " eatTime ", Long.valueOf(j2), " dayFormat ", Integer.valueOf(c2), " dietRecord ", quhVar);
        if (b2) {
            final String e2 = HiJsonUtil.e(quhVar);
            ThreadPoolManager.d().execute(new Runnable() { // from class: qye
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.d((quh) HiJsonUtil.e(e2, quh.class), false, (ResponseCallback<quh>) new ResponseCallback() { // from class: qxi
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i3, Object obj) {
                            qvz.b(ResponseCallback.this, r2, r4, i3, (quh) obj);
                        }
                    });
                }
            });
        } else if (responseCallback != null) {
            responseCallback.onResponse(i2, quhVar);
        }
    }

    static /* synthetic */ void b(ResponseCallback responseCallback, long j2, long j3, int i2, quh quhVar) {
        LogUtil.c("DietDiaryUtils", "updateMeal insertDietRecord resultCode ", Integer.valueOf(i2), " resultDietRecord ", quhVar);
        if (responseCallback != null) {
            responseCallback.onResponse(i2, quhVar);
        }
        if (i2 != 0) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "updateMeal insertDietRecord resultCode ", Integer.valueOf(i2), " startTime ", Long.valueOf(j2), " endTime ", Long.valueOf(j3));
        } else {
            c(j2, j3);
        }
    }

    public static void j() {
        kot.a().b(new ResponseCallback() { // from class: qxn
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                qvz.c(i2, (gsi) obj);
            }
        });
    }

    static /* synthetic */ void c(int i2, gsi gsiVar) {
        if (gsiVar == null) {
            LogUtil.a("DietDiaryUtils", "weightManager is null");
            return;
        }
        gsh onePointFull = ((WeightApi) Services.c("Main", WeightApi.class)).getOnePointFull(gsiVar.g());
        gsh e2 = gsiVar.e();
        LogUtil.c("DietDiaryUtils", "onePointFull is : ", onePointFull, "onePointFull in weightManager is : ", e2);
        if (!a(onePointFull, e2)) {
            LogUtil.c("DietDiaryUtils", "is not need to updateOnePointFull");
        } else {
            kpu.e(gsiVar, System.currentTimeMillis(), false, null, false, onePointFull);
        }
    }

    public static void a(final List<qul> list, final ResponseCallback<List<quh>> responseCallback) {
        if (list == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "recordMeals meal is null");
            if (responseCallback != null) {
                responseCallback.onResponse(-1, new ArrayList());
                return;
            }
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            a(list.get(i2));
        }
        long d2 = ((qul) Collections.min(list, Comparator.comparingLong(new ToLongFunction() { // from class: qwy
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((qul) obj).d();
            }
        }))).d();
        long d3 = ((qul) Collections.max(list, Comparator.comparingLong(new ToLongFunction() { // from class: qwy
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((qul) obj).d();
            }
        }))).d();
        final long a2 = a(d2);
        final long e2 = jdl.e(d3);
        final ArrayList arrayList = new ArrayList();
        final HashMap hashMap = new HashMap();
        LogUtil.c("DietDiaryUtils", "recordMeals meals： ", list, " meals size is: ", Integer.valueOf(list.size()), "startTime is: ", Long.valueOf(a2), "endTime is: ", Long.valueOf(e2));
        c(b(a2, e2), (ResponseCallback<Map<Integer, quh>>) new ResponseCallback() { // from class: qxb
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i3, Object obj) {
                qvz.e(ResponseCallback.this, list, hashMap, arrayList, a2, e2, i3, (Map) obj);
            }
        });
    }

    static /* synthetic */ void e(final ResponseCallback responseCallback, List list, Map map, List list2, final long j2, final long j3, int i2, Map map2) {
        int i3;
        LogUtil.c("DietDiaryUtils", "recordMeals readDietRecord errorCode ", Integer.valueOf(i2), " map ", map2);
        if (map2 == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "recordMeals readDietRecord map is null");
            if (responseCallback != null) {
                responseCallback.onResponse(i2, new ArrayList());
                return;
            }
            return;
        }
        for (0; i3 < list.size(); i3 + 1) {
            int c2 = DateFormatUtil.c(((qul) list.get(i3)).d(), jdl.d(((qul) list.get(i3)).j()));
            LogUtil.c("DietDiaryUtils", "recordMeals dayFormat is: ", Integer.valueOf(c2));
            quh quhVar = (quh) map2.get(Integer.valueOf(c2));
            qul qulVar = (qul) list.get(i3);
            if (quhVar == null) {
                LogUtil.a("R_DietDiaryUtils", "recordMeals readDietRecord dietRecord is null, dayFormat is: ", Integer.valueOf(c2));
                qts b2 = b(System.currentTimeMillis(), 0L, qulVar.b());
                long t = jdl.t(qulVar.d());
                ArrayList arrayList = new ArrayList();
                arrayList.add(qulVar);
                quhVar = e(c2, t, b2, arrayList);
            } else {
                boolean e2 = quhVar.e(qulVar);
                LogUtil.c("DietDiaryUtils", "recordMeals readDietRecord isSuccess ", Boolean.valueOf(e2), " dayFormat: ", Integer.valueOf(c2), " dietRecord: ", quhVar, " meal: ", qulVar);
                i3 = e2 ? 0 : i3 + 1;
            }
            map.put(Integer.valueOf(c2), quhVar);
            map2.put(Integer.valueOf(c2), quhVar);
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            list2.add((quh) ((Map.Entry) it.next()).getValue());
        }
        if (koq.b(list2)) {
            ReleaseLogUtil.b("DietDiaryUtils", "recordMeals fusion success");
            if (responseCallback != null) {
                responseCallback.onResponse(0, new ArrayList());
                return;
            }
            return;
        }
        LogUtil.c("DietDiaryUtils", "recordMeals dietRecord: ", list2);
        final String e3 = HiJsonUtil.e(list2);
        ThreadPoolManager.d().execute(new Runnable() { // from class: qyi
            @Override // java.lang.Runnable
            public final void run() {
                qvz.d((List<quh>) HiJsonUtil.b(e3, new TypeToken<List<quh>>() { // from class: qvz.8
                }.getType()), false, (ResponseCallback<List<quh>>) new ResponseCallback() { // from class: qwi
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i4, Object obj) {
                        qvz.d(ResponseCallback.this, r2, r4, i4, (List) obj);
                    }
                });
            }
        });
    }

    static /* synthetic */ void d(ResponseCallback responseCallback, long j2, long j3, int i2, List list) {
        LogUtil.c("DietDiaryUtils", "recordMeals insertDietRecord resultCode： ", Integer.valueOf(i2), " resultDietRecord： ", list);
        if (responseCallback != null) {
            responseCallback.onResponse(i2, list);
        }
        if (i2 != 0) {
            return;
        }
        dpe.e(j2, j3, keb.b("DietDiaryUtils"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final List<quh> list, final boolean z, final ResponseCallback<List<quh>> responseCallback) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "insertDietRecords isQueryDietRecord ", Boolean.valueOf(z));
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        for (int i2 = 0; i2 < list.size(); i2++) {
            quh quhVar = list.get(i2);
            if (!jfa.a()) {
                if (responseCallback != null) {
                    responseCallback.onResponse(600002, null);
                    return;
                }
                return;
            }
            if (c(quhVar)) {
                if (responseCallback != null) {
                    responseCallback.onResponse(AwarenessConstants.ERROR_UNKNOWN_CODE, null);
                    return;
                }
                return;
            }
            String j2 = quhVar.j();
            long f2 = quhVar.f();
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setType(DicDataTypeUtil.DataType.DIET_RECORD_SET.value());
            hiHealthData.setTimeZone(j2);
            hiHealthData.setStartTime(f2);
            hiHealthData.setEndTime(f2);
            hiHealthData.setDeviceUuid("-1");
            if (z) {
                hiHealthData.setModifiedTime(quhVar.b());
            }
            HashMap hashMap = new HashMap();
            hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.DIET_RECORD.value()), HiJsonUtil.e(quhVar));
            hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap));
            hiDataInsertOption.addData(hiHealthData);
        }
        LogUtil.c("DietDiaryUtils", "insertDietRecord hiDataInsertOption ", hiDataInsertOption);
        HiHealthNativeApi.a(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: qxo
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i3, Object obj) {
                qvz.d(z, responseCallback, list, i3, obj);
            }
        });
    }

    static /* synthetic */ void d(final boolean z, final ResponseCallback responseCallback, final List list, final int i2, Object obj) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "insertDietRecord isQueryDietRecord ", Boolean.valueOf(z), " errorCode ", Integer.valueOf(i2), " object ", obj);
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "insertDietRecord callback is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qxx
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.e(z, i2, responseCallback, list);
                }
            });
        }
    }

    static /* synthetic */ void e(boolean z, int i2, ResponseCallback responseCallback, List list) {
        if (z || i2 != 0) {
            responseCallback.onResponse(i2, list);
        } else {
            responseCallback.onResponse(i2, list);
            LogUtil.c("DietDiaryUtils", "insertDietRecordList success");
        }
    }

    public static void b(int i2, int i3, ResponseCallback<List<quh>> responseCallback) {
        c(i2, i3, 0L, true, responseCallback);
    }

    public static void a(int i2, int i3, long j2, ResponseCallback<List<quh>> responseCallback) {
        c(i2, i3, j2, true, responseCallback);
    }

    public static void d(int i2, int i3, ResponseCallback<List<quh>> responseCallback) {
        c(i2, i3, 0L, false, responseCallback);
    }

    public static void d(int i2, int i3, long j2, ResponseCallback<List<quh>> responseCallback) {
        c(i2, i3, j2, false, responseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(final int i2, final int i3, final long j2, final boolean z, final ResponseCallback<List<quh>> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getDietRecord callback is null sourceStartTime ", Integer.valueOf(i2), " sourceEndTime ", Integer.valueOf(i3), " timeout ", Long.valueOf(j2), " isBasicDietRecord ", Boolean.valueOf(z));
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qxz
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.c(i2, i3, j2, z, (ResponseCallback<List<quh>>) responseCallback);
                }
            });
            return;
        }
        long c2 = DateFormatUtil.c(i2);
        long e2 = jdl.e(DateFormatUtil.c(i3));
        LogUtil.c("DietDiaryUtils", "getDietRecord sourceStartTime ", Long.valueOf(c2), " sourceEndTime ", Long.valueOf(e2), " timeout ", Long.valueOf(j2), " isBasicDietRecord ", Boolean.valueOf(z), " dumpStackTraceInfo ", DfxUtils.d(Thread.currentThread(), null));
        long currentTimeMillis = System.currentTimeMillis();
        final long min = Math.min(c2, jdl.t(currentTimeMillis));
        final long min2 = Math.min(e2, jdl.e(currentTimeMillis));
        if (jdl.ac(min2)) {
            boolean z2 = f16619a.get();
            ReleaseLogUtil.b("R_DietDiaryUtils", "getDietRecord isRegisteredSportData ", Boolean.valueOf(z2), " endTime ", Long.valueOf(min2));
            if (!z2) {
                f.set(0);
                f("getDietRecord");
            }
        }
        final HiDataReadOption b2 = b(min, min2);
        c(b2, (ResponseCallback<Map<Integer, quh>>) new ResponseCallback() { // from class: qyc
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i4, Object obj) {
                qvz.c(ResponseCallback.this, i2, i3, min, min2, j2, z, b2, i4, (Map) obj);
            }
        });
    }

    static /* synthetic */ void c(ResponseCallback responseCallback, final int i2, final int i3, long j2, long j3, long j4, boolean z, HiDataReadOption hiDataReadOption, int i4, Map map) {
        LogUtil.c("DietDiaryUtils", "getDietRecord map ", map);
        if (map == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getDietRecord map is null errorCode ", Integer.valueOf(i4));
            responseCallback.onResponse(i4, Collections.emptyList());
            o();
            return;
        }
        map.entrySet().removeIf(new Predicate() { // from class: qxp
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return qvz.e(i2, i3, (Map.Entry) obj);
            }
        });
        LogUtil.c("DietDiaryUtils", "getDietRecordByDate startDate:", Integer.valueOf(i2), " endDate:", Integer.valueOf(i3), " map ", map);
        long t = jdl.t(j2);
        long e2 = jdl.e(j3);
        if (jdl.e(t, e2) <= 8) {
            a(j4, z, t, e2, map);
        }
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        quh quhVar = (quh) map.get(Integer.valueOf(b2));
        if (i2 >= b2 && quhVar != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(quhVar);
            responseCallback.onResponse(i4, arrayList);
            o();
            return;
        }
        b(hiDataReadOption, (Map<Integer, quh>) map, (ResponseCallback<List<quh>>) responseCallback);
        o();
    }

    static /* synthetic */ boolean e(int i2, int i3, Map.Entry entry) {
        return ((Integer) entry.getKey()).intValue() < i2 || ((Integer) entry.getKey()).intValue() > i3;
    }

    private static void a(long j2, boolean z, long j3, long j4, Map<Integer, quh> map) {
        for (long t = jdl.t(j3); t <= j4; t = jdl.y(t)) {
            int c2 = DateFormatUtil.c(t, TimeZone.getDefault());
            quh quhVar = map.get(Integer.valueOf(c2));
            if (quhVar == null) {
                long e2 = jdl.e(t);
                long min = Math.min(e2, j4);
                qts b2 = b(jdl.ac(t) ? System.currentTimeMillis() : min, j2, 0.0f);
                quh e3 = e(c2, t, b2, new ArrayList());
                LogUtil.c("DietDiaryUtils", "dayFormat ", Integer.valueOf(c2), "processDietRecord dayStartTime ", Long.valueOf(t), " tempDayEndTime ", Long.valueOf(e2), " optionEndTime ", Long.valueOf(j4), " dayEndTime ", Long.valueOf(min), " dietCalorieOverview ", b2, " dayDietRecord ", e3);
                final String e4 = HiJsonUtil.e(e3);
                ThreadPoolManager.d().execute(new Runnable() { // from class: qww
                    @Override // java.lang.Runnable
                    public final void run() {
                        qvz.d((quh) HiJsonUtil.e(e4, quh.class), true, (ResponseCallback<quh>) new ResponseCallback() { // from class: qwl
                            @Override // com.huawei.hwbasemgr.ResponseCallback
                            public final void onResponse(int i2, Object obj) {
                                LogUtil.c("DietDiaryUtils", "processDietRecord insertErrorCode ", Integer.valueOf(i2), " insertDietRecord ", (quh) obj);
                            }
                        });
                    }
                });
                quhVar = e3;
            } else if (!z) {
                if (jdl.ac(t)) {
                    e(quhVar, j2);
                } else {
                    a(quhVar, j2);
                }
            }
            map.put(Integer.valueOf(c2), d(quhVar));
        }
    }

    private static void e(quh quhVar, long j2) {
        LogUtil.c("DietDiaryUtils", "refreshTodayDietRecord timeout ", Long.valueOf(j2), " dietRecord ", quhVar);
        b(quhVar);
        long currentTimeMillis = System.currentTimeMillis();
        qts d2 = quhVar.d();
        if (d2 == null) {
            d2 = b(currentTimeMillis, j2, 0.0f);
        }
        qtk e2 = e(currentTimeMillis, j2);
        d2.b(currentTimeMillis);
        d2.a(e2.h());
        int a2 = a(e2);
        if (a2 > 0) {
            d2.b(a2);
        }
        int d3 = e2.d();
        if (d3 > 0) {
            d2.a(d3);
        }
        int c2 = e2.c();
        if (c2 > 0) {
            d2.c(c2);
        }
        int b2 = e2.b();
        if (b2 > 0) {
            d2.e(b2);
        }
        int e3 = e2.e();
        if (e3 > 0) {
            d2.a(e3);
        }
        if (c(quhVar)) {
            quhVar.b(jdl.t(currentTimeMillis));
        } else {
            quhVar.b(quhVar.b() + 1);
        }
        LogUtil.c("DietDiaryUtils", "refreshTodayDietRecord dietRecordOverview ", d2, " dietRecord ", quhVar);
        final String e4 = HiJsonUtil.e(quhVar);
        ThreadPoolManager.d().execute(new Runnable() { // from class: qxk
            @Override // java.lang.Runnable
            public final void run() {
                qvz.d((quh) HiJsonUtil.e(e4, quh.class), true, (ResponseCallback<quh>) new ResponseCallback() { // from class: qxm
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i2, Object obj) {
                        qvz.b(i2, (quh) obj);
                    }
                });
            }
        });
    }

    static /* synthetic */ void b(int i2, quh quhVar) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "refreshTodayDietRecord insertErrorCode ", Integer.valueOf(i2));
        gsd.a(quhVar);
    }

    private static void a(quh quhVar, long j2) {
        LogUtil.c("DietDiaryUtils", "refreshHistoryDietRecord timeout ", Long.valueOf(j2), " dietRecord ", quhVar);
        qts d2 = quhVar.d();
        if (d2 == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "refreshHistoryDietRecord dietRecordOverview is null");
            return;
        }
        final long c2 = DateFormatUtil.c(quhVar.c());
        int a2 = (int) UnitUtil.a(d2.i(), 0);
        if (a2 <= 0) {
            qtk e2 = e(c2, j2);
            int d3 = e2.d();
            LogUtil.c("DietDiaryUtils", "refreshHistoryDietRecord restingCalories ", Integer.valueOf(d3), " dietRecordBaseInfo ", e2);
            if (d3 > 0) {
                d2.a(d3);
                c(quhVar, true);
                return;
            }
            return;
        }
        final HashMap hashMap = new HashMap();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolManager.d().execute(new Runnable() { // from class: qyd
            @Override // java.lang.Runnable
            public final void run() {
                qvz.b(c2, (ResponseCallback<Map<Integer, List<HiHealthData>>>) new ResponseCallback() { // from class: qxf
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i2, Object obj) {
                        qvz.a(r1, r2, i2, (Map) obj);
                    }
                });
            }
        });
        try {
            ReleaseLogUtil.b("R_DietDiaryUtils", "refreshHistoryDietRecord isAwait ", Boolean.valueOf(countDownLatch.await(5L, TimeUnit.SECONDS)));
        } catch (InterruptedException e3) {
            ReleaseLogUtil.c("R_DietDiaryUtils", "refreshHistoryDietRecord exception ", ExceptionUtils.d(e3));
        }
        int e4 = e(hashMap, DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE.value());
        if (e4 <= 0) {
            e4 = e(hashMap, DicDataTypeUtil.DataType.RESTING_METABOLISM.value());
        }
        if (e4 > 0) {
            d2.a(e4);
        }
        c(quhVar, a2 != e4);
    }

    static /* synthetic */ void a(Map map, CountDownLatch countDownLatch, int i2, Map map2) {
        map.putAll(map2);
        countDownLatch.countDown();
        LogUtil.c("DietDiaryUtils", "refreshHistoryDietRecord errorCode ", Integer.valueOf(i2), " map ", map2, " restingCalorieMap ", map);
    }

    private static void c(quh quhVar, boolean z) {
        qts d2 = quhVar.d();
        if (d2 == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "refreshHistoryRestingCaloriesBurned dietRecordOverview is null");
            return;
        }
        boolean z2 = false;
        int a2 = (int) UnitUtil.a(d2.i(), 0);
        int h2 = d2.h();
        if (a2 != h2) {
            d2.a(a2);
            z2 = true;
        }
        LogUtil.c("DietDiaryUtils", "refreshHistoryRestingCaloriesBurned restingCalories ", Integer.valueOf(a2), " restingCaloriesBurned ", Integer.valueOf(h2), " isRestingCaloriesBurnedChange ", Boolean.valueOf(z2), " isRestingCaloriesChange ", Boolean.valueOf(z), " dietRecord ", quhVar);
        if (z || z2) {
            d2.b(System.currentTimeMillis());
            quhVar.b(quhVar.b() + 1);
            final String e2 = HiJsonUtil.e(quhVar);
            ThreadPoolManager.d().execute(new Runnable() { // from class: qxq
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.d((quh) HiJsonUtil.e(e2, quh.class), true, (ResponseCallback<quh>) new ResponseCallback() { // from class: qwh
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i2, Object obj) {
                            LogUtil.c("DietDiaryUtils", "refreshHistoryRestingCaloriesBurned insertErrorCode ", Integer.valueOf(i2), " insertDietRecord ", (quh) obj);
                        }
                    });
                }
            });
        }
    }

    private static void b(final HiDataReadOption hiDataReadOption, Map<Integer, quh> map, ResponseCallback<List<quh>> responseCallback) {
        quh value;
        final HashMap hashMap = new HashMap();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolManager.d().execute(new Runnable() { // from class: qxj
            @Override // java.lang.Runnable
            public final void run() {
                qvz.c(r0.getStartTime(), HiDataReadOption.this.getEndTime(), (ResponseCallback<Map<Integer, Integer>>) new ResponseCallback() { // from class: qwq
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i2, Object obj) {
                        qvz.c(r1, r2, i2, (Map) obj);
                    }
                });
            }
        });
        try {
            ReleaseLogUtil.b("R_DietDiaryUtils", "decorateDietRecord isAwait ", Boolean.valueOf(countDownLatch.await(5L, TimeUnit.SECONDS)));
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("R_DietDiaryUtils", "decorateDietRecord exception ", ExceptionUtils.d(e2));
        }
        int b2 = DateFormatUtil.b(jdl.t(System.currentTimeMillis()));
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, quh> entry : map.entrySet()) {
            if (entry != null && (value = entry.getValue()) != null) {
                arrayList.add(value);
                int intValue = entry.getKey().intValue();
                if (hashMap.containsKey(Integer.valueOf(intValue))) {
                    if (intValue == b2) {
                        b(value);
                    } else {
                        qts d2 = value.d();
                        if (d2 != null) {
                            if (((Integer) hashMap.get(Integer.valueOf(intValue))) != null) {
                                d2.c(r3.intValue());
                            }
                            d2.a((int) UnitUtil.a(d2.i(), 0));
                        }
                    }
                }
            }
        }
        responseCallback.onResponse(0, arrayList);
    }

    static /* synthetic */ void c(Map map, CountDownLatch countDownLatch, int i2, Map map2) {
        map.putAll(map2);
        countDownLatch.countDown();
        LogUtil.c("DietDiaryUtils", "decorateDietRecord errorCode ", Integer.valueOf(i2), " resultMap ", map2, " sportCaloriesMap ", map);
    }

    public static void e(final boolean z, final ResponseCallback<Boolean> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "isSupportDiet callback is null isFullDiet ", Boolean.valueOf(z));
            return;
        }
        if (!Utils.i()) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "isSupportDiet ifAllowLogin false isFullDiet ", Boolean.valueOf(z));
            responseCallback.onResponse(0, false);
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.e());
        if (loginInit == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "isSupportDiet loginInit is null isFullDiet ", Boolean.valueOf(z));
            responseCallback.onResponse(-1, false);
            return;
        }
        boolean isLogined = loginInit.getIsLogined();
        boolean isKidAccount = loginInit.isKidAccount();
        if (!isLogined || isKidAccount) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "isSupportDiet isLogined ", Boolean.valueOf(isLogined), " isKidAccount ", Boolean.valueOf(isKidAccount), " isFullDiet ", Boolean.valueOf(z));
            responseCallback.onResponse(0, false);
            return;
        }
        final String accountInfo = loginInit.getAccountInfo(1010);
        ReleaseLogUtil.b("R_DietDiaryUtils", "isSupportDiet countryCode ", accountInfo, " isFullDiet ", Boolean.valueOf(z));
        if (TextUtils.isEmpty(accountInfo)) {
            responseCallback.onResponse(-1, false);
        } else if (!Utils.o()) {
            responseCallback.onResponse(0, true);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwn
                @Override // java.lang.Runnable
                public final void run() {
                    kpp.c(new kpn(accountInfo), new ResponseCallback<Boolean>() { // from class: qvz.9
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public void onResponse(int i2, Boolean bool) {
                            boolean z2 = false;
                            if (!bool.booleanValue()) {
                                ResponseCallback.this.onResponse(i2, false);
                                return;
                            }
                            boolean a2 = gsd.a("THIRD_DIET_TIME_MILLIS");
                            boolean a3 = gsd.a("SIMPLIFY_DIET_TIME_MILLIS");
                            ResponseCallback responseCallback2 = ResponseCallback.this;
                            if (r2) {
                                z2 = a2;
                            } else if (a3 || a2) {
                                z2 = true;
                            }
                            responseCallback2.onResponse(i2, Boolean.valueOf(z2));
                        }
                    });
                }
            });
        }
    }

    public static void b() {
        if (gsd.a()) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: qya
            @Override // java.lang.Runnable
            public final void run() {
                qvz.e(false, (ResponseCallback<Boolean>) new ResponseCallback() { // from class: qwj
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i2, Object obj) {
                        qvz.d(i2, (Boolean) obj);
                    }
                });
            }
        });
    }

    static /* synthetic */ void d(int i2, Boolean bool) {
        if (bool != null && bool.booleanValue()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qxa
                @Override // java.lang.Runnable
                public final void run() {
                    kot.a().c(new ResponseCallback() { // from class: qyj
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i3, Object obj) {
                            LogUtil.c("DietDiaryUtils", "checkFatReductionShapingState floatValue ", (Float) obj, " errorCode ", Integer.valueOf(i3));
                        }
                    });
                }
            });
        }
    }

    private static boolean d(List<qul> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "hasFood list ", list);
            return false;
        }
        for (qul qulVar : list) {
            if (qulVar != null) {
                List<quk> c2 = qulVar.c();
                if (koq.b(c2)) {
                    continue;
                } else {
                    for (quk qukVar : c2) {
                        if (qukVar != null) {
                            String a2 = qukVar.a();
                            if (!TextUtils.isEmpty(a2)) {
                                return true;
                            }
                            ReleaseLogUtil.a("R_DietDiaryUtils", "hasFood foodId ", a2);
                        }
                    }
                }
            }
        }
        return false;
    }

    private static List<String> a(List<qul> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getNeedQueryFoodIdList list ", list);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (qul qulVar : list) {
            if (qulVar != null) {
                List<quk> c2 = qulVar.c();
                if (!koq.b(c2)) {
                    for (quk qukVar : c2) {
                        if (qukVar != null && qukVar.j() != 0 && !"-1".equals(qukVar.a())) {
                            String a2 = qukVar.a();
                            if (TextUtils.isEmpty(a2)) {
                                ReleaseLogUtil.a("R_DietDiaryUtils", "getNeedQueryFoodIdList foodId ", a2);
                            } else if (!arrayList.contains(a2)) {
                                arrayList.add(a2);
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static boolean a(gsh gshVar, gsh gshVar2) {
        if (gshVar == null || gshVar.a() == 0) {
            return false;
        }
        return gshVar2 == null || gshVar2.a() == 0 || Math.abs(gshVar2.a() - gshVar.a()) > 0;
    }

    private static float e(quk qukVar, cwk cwkVar) {
        if (qukVar == null || cwkVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getFoodCoefficient descriptor ", qukVar, " food ", cwkVar);
            return 0.0f;
        }
        int b2 = cwkVar.b();
        if (b2 <= 0) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getFoodCoefficient foodCount ", Integer.valueOf(b2), " food ", cwkVar, " descriptor ", qukVar);
            return 0.0f;
        }
        String n = qukVar.n();
        float b3 = qukVar.b();
        if (!TextUtils.equals(n, cwkVar.h())) {
            List<cwh> a2 = cwkVar.a();
            if (koq.b(a2)) {
                return 1.0f;
            }
            for (cwh cwhVar : a2) {
                if (cwhVar != null && TextUtils.equals(n, cwhVar.d())) {
                    b3 *= cwhVar.c();
                }
            }
            return 1.0f;
        }
        return b3 / b2;
    }

    private static Map<String, Float> c(quk qukVar, List<cwk> list) {
        float f2;
        float f3;
        float f4;
        if (qukVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getFoodDescriptorDietAnalysis descriptor is null");
            return new HashMap();
        }
        String a2 = qukVar.a();
        if (TextUtils.isEmpty(a2)) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getFoodDescriptorDietAnalysis descriptorFoodId ", a2);
            return new HashMap();
        }
        HashMap hashMap = new HashMap(3);
        Iterator<cwk> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                f2 = 0.0f;
                f3 = 0.0f;
                f4 = 0.0f;
                break;
            }
            cwk next = it.next();
            if (next != null && TextUtils.equals(a2, next.d())) {
                float e2 = e(qukVar, next);
                f2 = next.c() * e2;
                f3 = next.i() * e2;
                f4 = next.e() * e2;
                break;
            }
        }
        hashMap.put("carbohydrate", Float.valueOf((float) UnitUtil.a(f2, 2)));
        hashMap.put("protein", Float.valueOf((float) UnitUtil.a(f3, 2)));
        hashMap.put("fat", Float.valueOf((float) UnitUtil.a(f4, 2)));
        return hashMap;
    }

    private static float d(Map<String, Float> map, String str) {
        Float f2;
        if (map == null || (f2 = map.get(str)) == null) {
            return 0.0f;
        }
        return f2.floatValue();
    }

    private static Map<String, Float> a(List<qul> list, List<cwk> list2) {
        if (koq.b(list) || koq.b(list2)) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getDietAnalysis mealList ", list, " foodList ", list2);
            return new HashMap();
        }
        HashMap hashMap = new HashMap(3);
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        for (qul qulVar : list) {
            if (qulVar != null) {
                List<quk> c2 = qulVar.c();
                if (!koq.b(c2)) {
                    Iterator<quk> it = c2.iterator();
                    while (it.hasNext()) {
                        Map<String, Float> c3 = c(it.next(), list2);
                        f2 += d(c3, "carbohydrate");
                        f3 += d(c3, "protein");
                        f4 += d(c3, "fat");
                    }
                }
            }
        }
        hashMap.put("carbohydrate", Float.valueOf((float) UnitUtil.a(f2, 2)));
        hashMap.put("protein", Float.valueOf((float) UnitUtil.a(f3, 2)));
        hashMap.put("fat", Float.valueOf((float) UnitUtil.a(f4, 2)));
        return hashMap;
    }

    private static Map<String, Float> c(List<qul> list, boolean z) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getDietAnalysis mealList ", list, " isCustomizeFood ", Boolean.valueOf(z));
            return new HashMap();
        }
        HashMap hashMap = new HashMap(3);
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        for (qul qulVar : list) {
            if (qulVar != null) {
                List<quk> c2 = qulVar.c();
                if (!koq.b(c2)) {
                    for (quk qukVar : c2) {
                        if (qukVar != null) {
                            if (z == (qukVar.j() == 0)) {
                                f2 += qukVar.d();
                                f3 += qukVar.f();
                                f4 += qukVar.e();
                            }
                        }
                    }
                }
            }
        }
        hashMap.put("carbohydrate", Float.valueOf((float) UnitUtil.a(f2, 2)));
        hashMap.put("protein", Float.valueOf((float) UnitUtil.a(f3, 2)));
        hashMap.put("fat", Float.valueOf((float) UnitUtil.a(f4, 2)));
        return hashMap;
    }

    private static cwj b(Map<String, Float> map, Map<String, Float> map2) {
        LogUtil.c("DietDiaryUtils", "getInitDeviceSyncDietAnalysis dietAnalysis ", map, " customizeDietAnalysis ", map2);
        return new cwj((int) UnitUtil.a(d(map, "carbohydrate") + d(map2, "carbohydrate"), 0), (int) UnitUtil.a(d(map, "protein") + d(map2, "protein"), 0), (int) UnitUtil.a(d(map, "fat") + d(map2, "fat"), 0));
    }

    private static void d(quh quhVar, final ResponseCallback<cwj> responseCallback) {
        final List<qul> a2 = quhVar.a();
        if (!d(a2)) {
            LogUtil.a("DietDiaryUtils", "initDeviceSyncDietAnalysis mealList ", a2);
            responseCallback.onResponse(0, null);
            return;
        }
        final List<String> a3 = a(a2);
        LogUtil.c("DietDiaryUtils", "initDeviceSyncDietAnalysis needQueryFoodIdList ", a3);
        if (koq.b(a3)) {
            responseCallback.onResponse(0, b(c(a2, false), c(a2, true)));
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qwt
                @Override // java.lang.Runnable
                public final void run() {
                    kpp.b(new qvj(a3), new ResponseCallback() { // from class: qyh
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i2, Object obj) {
                            qvz.c(r1, r2, i2, (List) obj);
                        }
                    });
                }
            });
        }
    }

    static /* synthetic */ void c(List list, ResponseCallback responseCallback, int i2, List list2) {
        Map<String, Float> map;
        LogUtil.c("DietDiaryUtils", "initDeviceSyncDietAnalysis errorCode ", Integer.valueOf(i2), " foodList ", list2);
        if (koq.b(list2)) {
            List<cwk> j2 = gsd.j();
            LogUtil.c("DietDiaryUtils", "initDeviceSyncDietAnalysis foodListCache ", j2);
            if (koq.b(j2)) {
                map = c((List<qul>) list, false);
            } else {
                map = a((List<qul>) list, j2);
            }
        } else {
            Map<String, Float> a2 = a((List<qul>) list, (List<cwk>) list2);
            gsd.b((List<cwk>) list2);
            map = a2;
        }
        responseCallback.onResponse(i2, b(map, c((List<qul>) list, true)));
    }

    private static boolean d(qvm qvmVar) {
        cwp e2;
        cwp e3;
        cwp e4;
        Map<String, String> c2 = gsd.c();
        LogUtil.c("DietDiaryUtils", "isNeedQueryNutritionAnalysisItem dietAnalysisCache ", c2);
        if (c2 == null || (e2 = e(c2, "fatAnalysis")) == null || e2.d() <= 0 || e2.a() <= 0 || (e3 = e(c2, "proteinAnalysis")) == null || e3.d() <= 0 || e3.a() <= 0 || (e4 = e(c2, "carbohydrateAnalysis")) == null || e4.d() <= 0 || e4.a() <= 0 || CommonUtils.h(c2.get("date")) != DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault())) {
            return true;
        }
        qvm qvmVar2 = (qvm) HiJsonUtil.e(c2.get("dietAnalysisRequest"), qvm.class);
        if (qvmVar2 != null) {
            return (TextUtils.equals(qvmVar2.h(), qvmVar.h()) && TextUtils.equals(qvmVar2.d(), qvmVar.d()) && qvmVar2.b() == qvmVar.b() && qvmVar2.a() == qvmVar.a() && qvmVar2.e() == qvmVar.e() && qvmVar2.c() == qvmVar.c()) ? false : true;
        }
        ReleaseLogUtil.a("R_DietDiaryUtils", "isNeedQueryNutritionAnalysisItem cacheDietAnalysisRequest is null");
        return true;
    }

    private static cwp e(Map<String, String> map, String str) {
        if (map == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getNutritionAnalysisItem map is null");
            return null;
        }
        return (cwp) HiJsonUtil.e(map.get(str), cwp.class);
    }

    private static void e(final qvm qvmVar, final cwj cwjVar, final quh quhVar, final ResponseCallback<cwj> responseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: qxg
            @Override // java.lang.Runnable
            public final void run() {
                qvz.a(qvm.this, responseCallback, cwjVar, quhVar);
            }
        });
    }

    static /* synthetic */ void a(final qvm qvmVar, final ResponseCallback responseCallback, final cwj cwjVar, final quh quhVar) {
        if (!d(qvmVar)) {
            Map<String, String> c2 = gsd.c();
            LogUtil.c("DietDiaryUtils", "getNutritionAnalysisItem dietAnalysisCache ", c2);
            responseCallback.onResponse(0, d(cwjVar, e(c2, "carbohydrateAnalysis"), e(c2, "proteinAnalysis"), e(c2, "fatAnalysis")));
            return;
        }
        kpp.a(qvmVar, new ResponseCallback() { // from class: qyb
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                qvz.b(ResponseCallback.this, cwjVar, qvmVar, quhVar, i2, (Map) obj);
            }
        });
    }

    static /* synthetic */ void b(ResponseCallback responseCallback, cwj cwjVar, qvm qvmVar, quh quhVar, int i2, Map map) {
        LogUtil.c("DietDiaryUtils", "getNutritionAnalysisItem resultErrorCode ", Integer.valueOf(i2), " resultMap ", map);
        if (map == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getSupportDeviceSyncDietAnalysis resultMap is null");
            responseCallback.onResponse(i2, cwjVar);
            return;
        }
        cwp cwpVar = (cwp) map.get("carbohydrateAnalysis");
        cwp cwpVar2 = (cwp) map.get("proteinAnalysis");
        cwp cwpVar3 = (cwp) map.get("fatAnalysis");
        HashMap hashMap = new HashMap(5);
        hashMap.put("carbohydrateAnalysis", HiJsonUtil.e(cwpVar));
        hashMap.put("proteinAnalysis", HiJsonUtil.e(cwpVar2));
        hashMap.put("fatAnalysis", HiJsonUtil.e(cwpVar3));
        hashMap.put("dietAnalysisRequest", HiJsonUtil.e(qvmVar));
        hashMap.put("date", String.valueOf(quhVar.c()));
        boolean a2 = jfa.a();
        LogUtil.c("DietDiaryUtils", "getNutritionAnalysisItem isPullAllStatus ", Boolean.valueOf(a2));
        if (a2) {
            gsd.b(hashMap);
        }
        responseCallback.onResponse(i2, d(cwjVar, cwpVar, cwpVar2, cwpVar3));
    }

    private static void c(final quh quhVar, final ResponseCallback<cwj> responseCallback) {
        d(quhVar, (ResponseCallback<cwj>) new ResponseCallback() { // from class: qvx
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                qvz.a(ResponseCallback.this, quhVar, i2, (cwj) obj);
            }
        });
    }

    static /* synthetic */ void a(final ResponseCallback responseCallback, final quh quhVar, int i2, final cwj cwjVar) {
        LogUtil.c("DietDiaryUtils", "getSupportDeviceSyncDietAnalysis initDeviceSyncDietAnalysis ", cwjVar);
        if (cwjVar == null) {
            responseCallback.onResponse(i2, null);
            return;
        }
        final qts d2 = quhVar.d();
        if (d2 == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getSupportDeviceSyncDietAnalysis dietCalorieOverview is null");
            responseCallback.onResponse(i2, cwjVar);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qxr
                @Override // java.lang.Runnable
                public final void run() {
                    qvz.e((ResponseCallback<UserMemberInfo>) new ResponseCallback() { // from class: qvy
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i3, Object obj) {
                            qvz.b(ResponseCallback.this, r2, r3, r4, i3, (UserMemberInfo) obj);
                        }
                    });
                }
            });
        }
    }

    static /* synthetic */ void b(ResponseCallback responseCallback, cwj cwjVar, qts qtsVar, quh quhVar, int i2, UserMemberInfo userMemberInfo) {
        if (userMemberInfo == null || userMemberInfo.getMemberFlag() != 1 || gpn.d(userMemberInfo)) {
            responseCallback.onResponse(i2, cwjVar);
            return;
        }
        LogUtil.c("DietDiaryUtils", "getSupportDeviceSyncDietAnalysis dietCalorieOverview ", qtsVar);
        double j2 = qtsVar.j();
        e(new qvm(j2 > 0.0d ? "1" : j2 < 0.0d ? "2" : "3", (int) UnitUtil.a(qtsVar.c(), 0)), cwjVar, quhVar, (ResponseCallback<cwj>) responseCallback);
    }

    private static cwj d(cwj cwjVar, cwp cwpVar, cwp cwpVar2, cwp cwpVar3) {
        if (cwpVar != null) {
            cwjVar.b(new int[]{cwpVar.d(), cwpVar.a()});
        }
        if (cwpVar2 != null) {
            cwjVar.a(new int[]{cwpVar2.d(), cwpVar2.a()});
        }
        if (cwpVar3 != null) {
            cwjVar.c(new int[]{cwpVar3.d(), cwpVar3.a()});
        }
        return cwjVar;
    }

    public static void a(final quh quhVar, final ResponseCallback<cwj> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getDeviceSyncDietAnalysis callback is null dietRecord ", quhVar);
            return;
        }
        if (quhVar == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getDeviceSyncDietAnalysis dietRecord is null");
            responseCallback.onResponse(-1, null);
        } else if (gsd.a("THIRD_DIET_TIME_MILLIS")) {
            c(quhVar, responseCallback);
        } else if (!gsd.l()) {
            ReleaseLogUtil.b("R_DietDiaryUtils", "is not need to GetSupportDietSetting");
            responseCallback.onResponse(-1, null);
        } else {
            e(true, (ResponseCallback<Boolean>) new ResponseCallback() { // from class: qxw
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i2, Object obj) {
                    qvz.c(ResponseCallback.this, quhVar, i2, (Boolean) obj);
                }
            });
        }
    }

    static /* synthetic */ void c(ResponseCallback responseCallback, quh quhVar, int i2, Boolean bool) {
        ReleaseLogUtil.b("R_DietDiaryUtils", "getDeviceSyncDietAnalysis resultBoolean ", bool, " errorCode ", Integer.valueOf(i2));
        if (!bool.booleanValue()) {
            responseCallback.onResponse(i2, null);
        } else {
            c(quhVar, (ResponseCallback<cwj>) responseCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final ResponseCallback<UserMemberInfo> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getVipInfo callback is null");
            return;
        }
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            ReleaseLogUtil.a("R_DietDiaryUtils", "getVipInfo vipApi is null");
            responseCallback.onResponse(-1, null);
        } else {
            vipApi.getVipInfo(new VipCallback() { // from class: qvz.7
                @Override // com.huawei.health.vip.api.VipCallback
                public void onSuccess(Object obj) {
                    LogUtil.c("DietDiaryUtils", "getVipInfo onSuccess object ", obj);
                    ResponseCallback.this.onResponse(0, obj instanceof UserMemberInfo ? (UserMemberInfo) obj : null);
                }

                @Override // com.huawei.health.vip.api.VipCallback
                public void onFailure(int i2, String str) {
                    ReleaseLogUtil.a("R_DietDiaryUtils", "getVipInfo onFailure errorCode ", Integer.valueOf(i2), " errorMessage ", str);
                    ResponseCallback.this.onResponse(i2, null);
                }
            });
        }
    }
}
