package com.huawei.health.connectivity.standstepcounter;

import android.content.Context;
import android.os.SystemClock;
import com.huawei.health.manager.ScreenOnStatisticsUtil;
import com.huawei.health.manager.util.TotalDetailStepsCacheBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gnj;
import defpackage.gnr;
import defpackage.jdl;
import health.compact.a.CommonUtil;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.LogicalStepCounter;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepCounterSupport;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class StandStepDataManager {
    private long b;
    private long f;
    private boolean i;
    private int o = 0;
    private int l = 0;
    private int n = 0;
    private int e = 0;
    private int g = 0;
    private long m = -1;
    private long c = -1;
    private long h = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f2212a = 0;
    private c k = new c();
    private Context d = BaseApplication.getContext();
    private long j = SharedPerferenceUtils.l(this.d);

    public interface IGetCacheTodaySteps {
        int getCacheTodaySteps();

        boolean isProcessNewDayReport(boolean z);
    }

    public StandStepDataManager() {
        this.f = 0L;
        this.b = 0L;
        this.i = false;
        this.b = System.currentTimeMillis();
        if (StepCounterSupport.d(this.d) == 3) {
            this.i = true;
        }
        g();
        TotalDetailStepsCacheBean u = SharedPerferenceUtils.u(this.d);
        if (u != null) {
            this.f = u.f();
            return;
        }
        String[] y = SharedPerferenceUtils.y(this.d);
        if (y != null) {
            try {
                this.f = Long.parseLong(y[0]);
            } catch (NumberFormatException e) {
                LogUtil.h("Step_StandStepDtaMgr", "StandStepDataManager(context) ", e.getMessage());
            }
        }
        LogUtil.a("Step_StandStepDtaMgr", "the first init mLastReportTimestamp = ", Long.valueOf(this.f));
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private int f2213a;
        private long b;

        private c() {
            this(-1L, -1);
        }

        private c(long j, int i) {
            this.b = j;
            this.f2213a = i;
        }
    }

    public void c(int i) {
        this.g = i;
    }

    public int e() {
        return this.g;
    }

    private void g() {
        LogUtil.a("Step_StandStepDtaMgr", "initDataManager enter...");
        h();
    }

    public int b() {
        return this.o;
    }

    public boolean i() {
        return !jdl.d(this.f, System.currentTimeMillis());
    }

    public int a() {
        return this.l;
    }

    public void a(long j, int i) {
        if (this.k == null) {
            return;
        }
        this.f = j;
        LogUtil.c("Step_StandStepDtaMgr", "mPreSteps = ", Integer.valueOf(this.g));
        if (!jdl.d(this.k.b, j) || i < this.k.f2213a) {
            if (jdl.d(this.k.b, j) && i < this.k.f2213a) {
                this.k.f2213a = i;
                int i2 = this.o - this.e;
                this.n = i2;
                this.l = i2;
                SharedPerferenceUtils.b(this.d, this.k.b, this.k.f2213a, this.n);
                return;
            }
            if (c(j, i - e()) || this.k.b == this.c) {
                h();
                return;
            }
            return;
        }
        this.o = (i - this.k.f2213a) + this.n + this.e;
        this.l = (i - this.k.f2213a) + this.n + this.f2212a;
        long b = LogUtil.b(2000, this.h);
        if (b != -1) {
            LogUtil.a("Step_StandStepDtaMgr", "mTodaySteps = ", Integer.valueOf(this.o), " mTodayStepsThisDevice = ", Integer.valueOf(this.l));
            this.h = b;
        }
    }

    public boolean b(int i) {
        boolean z;
        int i2 = this.o;
        if (i > i2) {
            int i3 = (i - i2) + this.e;
            this.e = i3;
            this.o = i;
            d(i3);
            z = true;
        } else {
            z = false;
        }
        LogUtil.a("Step_StandStepDtaMgr", "syncWithHiHealth totalSteps = ", Integer.valueOf(i), " mDiffStepsFromDb = ", Integer.valueOf(this.e));
        return z;
    }

    public void a(int i, int i2, int i3, int i4, int i5) {
        ReleaseLogUtil.e("Step_StandStepDtaMgr", "recordStepsBeforeShutdown mTodaySteps = ", Integer.valueOf(this.o));
        TotalDetailStepsCacheBean.Builder builder = new TotalDetailStepsCacheBean.Builder();
        builder.c(this.f);
        builder.c(this.o);
        builder.i(i);
        builder.d(i2);
        builder.a(i3);
        builder.a(SystemClock.elapsedRealtime());
        builder.e(i4);
        builder.b(i5);
        SharedPerferenceUtils.e(this.d, builder.c());
    }

    public long c() {
        return this.m;
    }

    public boolean e(long j, boolean z) {
        c cVar = this.k;
        if (cVar == null) {
            ReleaseLogUtil.c("Step_StandStepDtaMgr", "mTodayStandardBase is null");
            return false;
        }
        if (this.i) {
            cVar.b = this.b;
            this.b = j;
        }
        if ((!z && this.k.b == -1 && this.j == -1) || (!b(j, this.k.b) && !b(j, this.j))) {
            return false;
        }
        b(z, this.d);
        ReleaseLogUtil.e("Step_StandStepDtaMgr", "theDayChanged ", Long.valueOf(this.k.b), " timeStamp = ", Long.valueOf(j));
        this.m = this.k.b;
        j();
        return true;
    }

    private void b(boolean z, Context context) {
        boolean z2;
        if (!gnr.b(context) && this.o <= 0 && ScreenOnStatisticsUtil.a(context, this.k.b)) {
            if (!z) {
                boolean c2 = gnj.c(this.k.f2213a, context);
                if (LogicalStepCounter.c(this.d).f()) {
                    z2 = gnj.e(this.k.f2213a, LogicalStepCounter.c(this.d).c(), context);
                } else {
                    z2 = false;
                }
                if (c2 || z2) {
                    DaemonServiceSpUtils.f();
                    if (DaemonServiceSpUtils.g() && DaemonServiceSpUtils.c()) {
                        DaemonServiceSpUtils.a(true);
                        return;
                    }
                    return;
                }
                return;
            }
            gnj.h();
        }
    }

    private boolean b(long j, long j2) {
        return (j2 == -1 || jdl.d(j, j2)) ? false : true;
    }

    private void j() {
        LogUtil.a("Step_StandStepDtaMgr", "initEnvironment");
        c cVar = this.k;
        if (cVar != null) {
            cVar.b = -1L;
            this.k.f2213a = -1;
        }
        this.o = 0;
        this.l = 0;
        this.n = 0;
        this.e = 0;
        this.g = 0;
        this.j = -1L;
        this.f2212a = 0;
        SharedPerferenceUtils.d(this.d, -1L);
    }

    private void h() {
        TotalDetailStepsCacheBean u = SharedPerferenceUtils.u(this.d);
        if (u != null) {
            if (jdl.d(System.currentTimeMillis(), u.f())) {
                this.o = u.j();
                ReleaseLogUtil.e("Step_StandStepDtaMgr", "totalDetailStepsCacheBean != null, SystemClock.elapsedRealtime() = ", Long.valueOf(SystemClock.elapsedRealtime()));
                if (SystemClock.elapsedRealtime() < u.d()) {
                    a(u);
                }
            }
        } else {
            String[] y = SharedPerferenceUtils.y(this.d);
            if (y != null) {
                try {
                    if (jdl.d(System.currentTimeMillis(), Long.parseLong(y[0]))) {
                        this.o = Integer.parseInt(y[1]);
                        LogUtil.a("Step_StandStepDtaMgr", "SystemClock.elapsedRealtime() = ", Long.valueOf(SystemClock.elapsedRealtime()));
                        if (SystemClock.elapsedRealtime() < Long.parseLong(y[5])) {
                            d(y);
                        }
                    }
                } catch (NumberFormatException e) {
                    LogUtil.h("Step_StandStepDtaMgr", "reloadBasicStepDataAndTotalSteps ", e.getMessage());
                }
            }
        }
        if (!f()) {
            this.k.b = -1L;
            this.k.f2213a = -1;
            this.n = 0;
            this.e = 0;
            LogUtil.c("Step_StandStepDtaMgr", "reloadBasicStepDataAndTotalSteps no record...");
        }
        LogUtil.a("Step_StandStepDtaMgr", "reload : ", LogAnonymous.b(this.k.f2213a), " restar ", LogAnonymous.b(this.n));
    }

    private void d(String[] strArr) {
        a(CommonUtil.h(strArr[1]));
        TotalDetailStepsCacheBean.Builder builder = new TotalDetailStepsCacheBean.Builder();
        builder.c(CommonUtil.g(strArr[0]));
        builder.c(CommonUtil.h(strArr[1]));
        builder.i(CommonUtil.h(strArr[2]));
        builder.d(CommonUtil.h(strArr[3]));
        builder.a(CommonUtil.h(strArr[4]));
        builder.a(SystemClock.elapsedRealtime());
        SharedPerferenceUtils.d(this.d, builder.c());
    }

    private void a(TotalDetailStepsCacheBean totalDetailStepsCacheBean) {
        a(totalDetailStepsCacheBean.j());
        TotalDetailStepsCacheBean.Builder builder = new TotalDetailStepsCacheBean.Builder();
        builder.c(totalDetailStepsCacheBean.f());
        builder.c(totalDetailStepsCacheBean.j());
        builder.i(totalDetailStepsCacheBean.g());
        builder.d(totalDetailStepsCacheBean.a());
        builder.a(totalDetailStepsCacheBean.b());
        builder.a(totalDetailStepsCacheBean.d());
        builder.e(totalDetailStepsCacheBean.c());
        builder.b(totalDetailStepsCacheBean.e());
        SharedPerferenceUtils.e(this.d, builder.c());
    }

    private void a(int i) {
        int i2;
        ReleaseLogUtil.e("Step_StandStepDtaMgr", "updateBasicRestartStepsInfo restartStep = ", Integer.valueOf(i));
        String[] x = SharedPerferenceUtils.x(this.d);
        if (x != null) {
            try {
                if (jdl.d(System.currentTimeMillis(), Long.parseLong(x[0]))) {
                    if (x.length == 4) {
                        i2 = Integer.parseInt(x[3]);
                        SharedPerferenceUtils.d(this.d, Long.parseLong(x[0]), Integer.parseInt(x[3]));
                    } else {
                        i2 = 0;
                    }
                    LogUtil.a("Step_StandStepDtaMgr", "updateBasicRestartStepsInfo restart but same day");
                    String[] g = SharedPerferenceUtils.g(this.d);
                    if (g != null && jdl.d(System.currentTimeMillis(), Long.parseLong(g[0]))) {
                        i2 = Integer.parseInt(g[1]);
                    }
                    SharedPerferenceUtils.b(this.d, System.currentTimeMillis(), 0, i - i2);
                    this.o = i;
                }
            } catch (NumberFormatException e) {
                LogUtil.h("Step_StandStepDtaMgr", "updateBasicRestartStepsInfo ", e.getMessage());
            }
        }
    }

    private void d(int i) {
        LogUtil.a("Step_StandStepDtaMgr", "updateBasicDiffFromDBStepsInfo diffStepsFromDB = ", Integer.valueOf(i));
        SharedPerferenceUtils.d(this.d, System.currentTimeMillis(), i);
    }

    private boolean f() {
        String[] x = SharedPerferenceUtils.x(this.d);
        if (x != null) {
            try {
                if (jdl.d(Long.parseLong(x[0]), System.currentTimeMillis())) {
                    if (x.length == 4) {
                        this.e = Integer.parseInt(x[3]);
                        SharedPerferenceUtils.d(this.d, Long.parseLong(x[0]), Integer.parseInt(x[3]));
                    }
                    c cVar = this.k;
                    if (cVar != null) {
                        cVar.b = Long.parseLong(x[0]);
                        this.k.f2213a = Integer.parseInt(x[1]);
                        this.n = Integer.parseInt(x[2]);
                        String[] g = SharedPerferenceUtils.g(this.d);
                        if (g != null && jdl.d(Long.parseLong(g[0]), System.currentTimeMillis())) {
                            this.e = Integer.parseInt(g[1]);
                        }
                        ReleaseLogUtil.e("Step_StandStepDtaMgr", "tryToReloadTodayBasicSteps", Long.valueOf(this.k.b), "|", Integer.valueOf(this.k.f2213a), "|", Integer.valueOf(this.n), "|", Integer.valueOf(this.e));
                        return true;
                    }
                }
            } catch (NumberFormatException e) {
                LogUtil.h("Step_StandStepDtaMgr", "tryToReloadTodayBasicSteps ", e.getMessage());
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x008e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c(long r9, int r11) {
        /*
            r8 = this;
            android.content.Context r0 = r8.d
            java.lang.String[] r0 = health.compact.a.SharedPerferenceUtils.x(r0)
            java.lang.String r1 = "Step_StandStepDtaMgr"
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L35
            r4 = r0[r2]     // Catch: java.lang.NumberFormatException -> L24
            java.lang.String r4 = r4.trim()     // Catch: java.lang.NumberFormatException -> L24
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L24
            if (r11 < r4) goto L35
            r0 = r0[r3]     // Catch: java.lang.NumberFormatException -> L24
            long r4 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L24
            boolean r0 = defpackage.jdl.d(r4, r9)     // Catch: java.lang.NumberFormatException -> L24
            r0 = r0 ^ r2
            goto L36
        L24:
            r0 = move-exception
            java.lang.String r4 = "tryToRecordAsBasicStepData"
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r4, r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            r0 = r3
            goto L36
        L35:
            r0 = r2
        L36:
            java.lang.String r4 = "tryToRecordAsBasicStepData isWrite = "
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r5}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r4)
            if (r0 == 0) goto L8e
            android.content.Context r0 = r8.d
            java.lang.String[] r0 = health.compact.a.SharedPerferenceUtils.g(r0)
            if (r0 == 0) goto L6b
            int r4 = r0.length     // Catch: java.lang.NumberFormatException -> L6e
            r5 = 2
            if (r4 < r5) goto L6b
            r4 = r0[r3]     // Catch: java.lang.NumberFormatException -> L6e
            long r4 = java.lang.Long.parseLong(r4)     // Catch: java.lang.NumberFormatException -> L6e
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.NumberFormatException -> L6e
            boolean r4 = defpackage.jdl.d(r4, r6)     // Catch: java.lang.NumberFormatException -> L6e
            if (r4 == 0) goto L6b
            r0 = r0[r2]     // Catch: java.lang.NumberFormatException -> L6e
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L6e
            r8.e = r0     // Catch: java.lang.NumberFormatException -> L6e
            goto L7d
        L6b:
            r8.e = r3     // Catch: java.lang.NumberFormatException -> L6e
            goto L7d
        L6e:
            r0 = move-exception
            java.lang.String r4 = "tryToRecordAsBasicStepData ex = "
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r4, r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
        L7d:
            android.content.Context r0 = r8.d
            health.compact.a.SharedPerferenceUtils.b(r0, r9, r11, r3)
            android.content.Context r9 = r8.d
            long r10 = java.lang.System.currentTimeMillis()
            int r0 = r8.e
            health.compact.a.SharedPerferenceUtils.d(r9, r10, r0)
            return r2
        L8e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.connectivity.standstepcounter.StandStepDataManager.c(long, int):boolean");
    }

    public Map<String, Object> d() {
        HashMap hashMap = new HashMap(4);
        hashMap.put("standardBase", Integer.valueOf(this.k.f2213a));
        hashMap.put("restartSteps", Integer.valueOf(this.n));
        hashMap.put("otherSteps", Integer.valueOf(this.e));
        hashMap.put("UIShowSteps", Integer.valueOf(this.o));
        return hashMap;
    }

    public void a(long j) {
        if (jdl.d(this.j, j)) {
            return;
        }
        long t = jdl.t(j);
        this.j = t;
        SharedPerferenceUtils.d(this.d, t);
    }

    public void e(int i) {
        int i2 = this.l;
        if (i > i2) {
            this.f2212a = (i - i2) + this.f2212a;
            this.l = i;
        }
        LogUtil.a("Step_StandStepDtaMgr", "syncDiffDeviceStepFromDb totalDeviceSteps = ", Integer.valueOf(i), " mDiffDeviceStep = ", Integer.valueOf(this.f2212a));
    }
}
