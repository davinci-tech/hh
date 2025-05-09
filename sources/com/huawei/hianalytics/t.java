package com.huawei.hianalytics;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.DaoManager;
import com.huawei.hianalytics.dc.StateKeeper;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hianalytics.z;
import com.huawei.hms.common.PackageConstants;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class t {

    /* renamed from: a, reason: collision with root package name */
    public static u f3899a = null;

    /* renamed from: a, reason: collision with other field name */
    public static String f83a = "";
    public static u b;

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m586a() {
        String str;
        if (s.a().f74a) {
            String b2 = j.b(EnvUtils.getAppContext());
            if (HiLog.isDebugEnable()) {
                HiLog.d("CC", "current process " + b2);
            }
            if (!TextUtils.equals(b2, "com.huawei.hwid.core") && !TextUtils.equals(b2, PackageConstants.GENERAL_SERVICES_ACTION)) {
                return false;
            }
            if (!j.m566b()) {
                str = "not China rom";
            } else {
                if (j.h()) {
                    boolean m572e = j.m572e();
                    if (m572e) {
                        try {
                            Class.forName("com.hihonor.android.app.IHwActivityNotifierEx");
                        } catch (Throwable unused) {
                        }
                        return true;
                    }
                    if (m572e) {
                        return false;
                    }
                    try {
                        Class.forName("com.huawei.android.app.IHwActivityNotifierEx");
                        return true;
                    } catch (Throwable unused2) {
                        return false;
                    }
                }
                str = "not phone or pad";
            }
        } else {
            str = "isCollectEnable false";
        }
        HiLog.d("CC", str);
        return false;
    }

    public static void d(final String str) {
        for (String str2 : g.f3871a) {
            if (TextUtils.equals(str2, str)) {
                TaskThread.getRecordThread().addToQueue(new Runnable() { // from class: com.huawei.hianalytics.t$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        t.a(str);
                    }
                });
                return;
            }
        }
    }

    public static void f(final String str) {
        if (w.a().f97c.contains(str)) {
            f83a = str;
            TaskThread.getRecordThread().addToQueue(new Runnable() { // from class: com.huawei.hianalytics.t$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    t.b(str);
                }
            });
        }
    }

    public static void a(boolean z) {
        if (z) {
            if (TextUtils.isEmpty(f83a) || HiAnalyticsManager.getInstanceByTag(f83a) == null) {
                return;
            }
            f(f83a);
            return;
        }
        synchronized (StateKeeper.class) {
            if (StateKeeper.isInit()) {
                HiLog.si("CC", "unregisterMetric");
                z zVar = z.c.f3968a;
                synchronized (zVar) {
                    HiLog.d("MediaHelper", "unregister");
                    if (zVar.f117a) {
                        zVar.f113a.unregisterEventObserver(zVar.f114a);
                    }
                }
                StateKeeper.setInit(false);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005a, code lost:
    
        if (r0.contains("metrics1.data") == false) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b() {
        /*
            com.huawei.hianalytics.w r0 = com.huawei.hianalytics.w.a()
            r0.getClass()
            android.content.Context r1 = com.huawei.hianalytics.core.common.EnvUtils.getAppContext()
            java.lang.String r1 = com.huawei.hianalytics.j.b(r1)
            java.util.List<java.lang.String> r0 = r0.f95b
            boolean r0 = r0.contains(r1)
            r1 = 0
            if (r0 != 0) goto L19
            return r1
        L19:
            boolean r0 = com.huawei.hianalytics.j.m566b()
            java.lang.String r2 = "CC"
            if (r0 != 0) goto L27
            java.lang.String r0 = "not China rom"
            com.huawei.hianalytics.core.log.HiLog.d(r2, r0)
            return r1
        L27:
            java.lang.String r0 = "Analytics_Kit_Tag"
            com.huawei.hianalytics.process.HiAnalyticsInstance r0 = com.huawei.hianalytics.process.HiAnalyticsManager.getInstanceByTag(r0)
            if (r0 != 0) goto L30
            goto L5d
        L30:
            com.huawei.hianalytics.w r3 = com.huawei.hianalytics.w.a()
            boolean r3 = r3.f96b
            if (r3 != 0) goto L39
            goto L63
        L39:
            java.lang.String r0 = r0.getCollectUrl()
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L44
            goto L5d
        L44:
            java.lang.String r3 = "metrics1-drcn"
            boolean r3 = r0.contains(r3)
            if (r3 != 0) goto L63
            java.lang.String r3 = "metrics-drcn"
            boolean r3 = r0.contains(r3)
            if (r3 != 0) goto L63
            java.lang.String r3 = "metrics1.data"
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L5d
            goto L63
        L5d:
            java.lang.String r0 = "not China url"
            com.huawei.hianalytics.core.log.HiLog.d(r2, r0)
            return r1
        L63:
            boolean r0 = com.huawei.hianalytics.j.h()
            if (r0 != 0) goto L6f
            java.lang.String r0 = "not phone or pad"
            com.huawei.hianalytics.core.log.HiLog.d(r2, r0)
            return r1
        L6f:
            boolean r0 = com.huawei.hianalytics.j.m571d()
            if (r0 != 0) goto L7b
            java.lang.String r0 = "emui level below 29"
            com.huawei.hianalytics.core.log.HiLog.d(r2, r0)
            return r1
        L7b:
            android.content.Context r0 = com.huawei.hianalytics.core.common.EnvUtils.getAppContext()
            java.lang.String r3 = "android.permission.STATUS_BAR_SERVICE"
            int r0 = r0.checkSelfPermission(r3)
            if (r0 == 0) goto L99
            android.content.Context r0 = com.huawei.hianalytics.core.common.EnvUtils.getAppContext()
            java.lang.String r3 = "android.permission.MEDIA_CONTENT_CONTROL"
            int r0 = r0.checkSelfPermission(r3)
            if (r0 == 0) goto L99
            java.lang.String r0 = "permission is denied"
            com.huawei.hianalytics.core.log.HiLog.d(r2, r0)
            return r1
        L99:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.t.b():boolean");
    }

    public static void c(String str) {
        if (StateKeeper.isInit()) {
            HiLog.d("CC", "register has init");
            return;
        }
        try {
            if (f3899a == null) {
                f3899a = new u(TaskThread.getWorkLooper(), 1, str);
            }
            if (m586a()) {
                HiLog.si("CC", "register");
                i0 a2 = k0.a();
                h0 b2 = a2.b();
                b2.b();
                p pVar = new p(f3899a, str);
                b2.a(pVar);
                u uVar = f3899a;
                uVar.getClass();
                uVar.b = new WeakReference<>(pVar);
                h0 mo549a = a2.mo549a();
                mo549a.b();
                v vVar = new v(f3899a, str);
                mo549a.a(vVar);
                u uVar2 = f3899a;
                uVar2.getClass();
                uVar2.f84a = new WeakReference<>(vVar);
                b0 b0Var = new b0(f3899a, str);
                mo549a.a(b0Var);
                u uVar3 = f3899a;
                uVar3.getClass();
                uVar3.c = new WeakReference<>(b0Var);
                StateKeeper.setInit(true);
            }
        } catch (Throwable th) {
            HiLog.i("CC", "register " + th.getMessage());
        }
    }

    public static void e(String str) {
        HiLog.si("CC", "registerMetric");
        if (StateKeeper.isMetricInit()) {
            HiLog.d("CC", "registerMetric has init");
            return;
        }
        try {
            if (b()) {
                if (b == null) {
                    b = new u(TaskThread.getWorkLooper(), 2, str);
                }
                j.m557a();
                SQLiteDatabase writableDatabase = o.a(EnvUtils.getAppContext(), "haformal_event.db", null, 6).getWritableDatabase();
                DaoManager.getInstance().createMcInfoTable(writableDatabase);
                DaoManager.getInstance().createMcTagTable(writableDatabase);
                a0 a0Var = new a0(b);
                u uVar = b;
                uVar.getClass();
                uVar.d = new WeakReference<>(a0Var);
                z.c.f3968a.f3966a = a0Var;
                z.c.f3968a.b();
                h0 b2 = k0.a().b();
                b2.b();
                p pVar = new p(b, str);
                b2.a(pVar);
                u uVar2 = b;
                uVar2.getClass();
                uVar2.b = new WeakReference<>(pVar);
                StateKeeper.setMetricInit(true);
            }
        } catch (Throwable th) {
            HiLog.i("CC", "registerMetric: " + th.getMessage());
        }
    }

    public static /* synthetic */ void b(String str) {
        synchronized (StateKeeper.class) {
            e(str);
        }
    }

    public static /* synthetic */ void a(String str) {
        synchronized (StateKeeper.class) {
            c(str);
        }
    }

    public static void a() {
        if (StateKeeper.isInit()) {
            HiLog.si("CC", "unregister");
            try {
                i0 a2 = k0.a();
                h0 b2 = a2.b();
                b2.c();
                b2.a();
                h0 mo549a = a2.mo549a();
                mo549a.c();
                mo549a.a();
                StateKeeper.setInit(false);
            } catch (Throwable th) {
                HiLog.i("CC", "unregister " + th.getMessage());
            }
        }
    }
}
