package com.huawei.health.manager.util;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nip;
import health.compact.a.SharedPerferenceUtils;
import java.util.concurrent.Semaphore;

/* loaded from: classes.dex */
public class GoalValue {

    /* renamed from: a, reason: collision with root package name */
    private Semaphore f2806a = new Semaphore(1);
    private Context b;
    private int c;

    public GoalValue(Context context) {
        this.c = 10000;
        this.b = null;
        this.b = context;
        if (context != null) {
            this.c = SharedPerferenceUtils.q(context);
        }
    }

    public void c() {
        LogUtil.a("Step_GoalValue", "fetch lock");
        try {
            this.f2806a.acquire();
        } catch (InterruptedException unused) {
            LogUtil.b("Step_GoalValue", "fetch lock get failed");
        }
        if (this.b == null) {
            LogUtil.a("Step_GoalValue", "refreshGoal mContext is null");
        } else {
            nip.d("900200006", new IBaseResponseCallback() { // from class: com.huawei.health.manager.util.GoalValue.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!(obj instanceof Integer)) {
                        GoalValue goalValue = GoalValue.this;
                        goalValue.c = SharedPerferenceUtils.q(goalValue.b);
                        LogUtil.h("Step_GoalValue", "data not inatnceOf integer");
                        GoalValue.this.f2806a.release();
                        return;
                    }
                    GoalValue.this.c = ((Integer) obj).intValue();
                    SharedPerferenceUtils.b(GoalValue.this.b, GoalValue.this.c);
                    GoalValue.this.f2806a.release();
                    LogUtil.a("Step_GoalValue", "fetch unlock");
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0047: MOVE (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:23:0x0047 */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int e() {
        /*
            r7 = this;
            java.lang.String r0 = "Step_GoalValue"
            r1 = 1
            r2 = 0
            java.util.concurrent.Semaphore r3 = r7.f2806a     // Catch: java.lang.Throwable -> L2b java.lang.InterruptedException -> L2d
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L2b java.lang.InterruptedException -> L2d
            r5 = 2000(0x7d0, double:9.88E-321)
            boolean r3 = r3.tryAcquire(r5, r4)     // Catch: java.lang.Throwable -> L2b java.lang.InterruptedException -> L2d
            if (r3 == 0) goto L13
            int r0 = r7.c     // Catch: java.lang.InterruptedException -> L2e java.lang.Throwable -> L46
            goto L23
        L13:
            android.content.Context r4 = r7.b     // Catch: java.lang.InterruptedException -> L2e java.lang.Throwable -> L46
            int r4 = health.compact.a.SharedPerferenceUtils.q(r4)     // Catch: java.lang.InterruptedException -> L2e java.lang.Throwable -> L46
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.InterruptedException -> L2e java.lang.Throwable -> L46
            java.lang.String r6 = "getGoalValue over time"
            r5[r2] = r6     // Catch: java.lang.InterruptedException -> L2e java.lang.Throwable -> L46
            health.compact.a.ReleaseLogUtil.c(r0, r5)     // Catch: java.lang.InterruptedException -> L2e java.lang.Throwable -> L46
            r0 = r4
        L23:
            if (r3 == 0) goto L45
            java.util.concurrent.Semaphore r1 = r7.f2806a
            r1.release()
            goto L45
        L2b:
            r0 = move-exception
            goto L48
        L2d:
            r3 = r2
        L2e:
            android.content.Context r4 = r7.b     // Catch: java.lang.Throwable -> L46
            int r4 = health.compact.a.SharedPerferenceUtils.q(r4)     // Catch: java.lang.Throwable -> L46
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L46
            java.lang.String r5 = "getGoalValue InterruptedException"
            r1[r2] = r5     // Catch: java.lang.Throwable -> L46
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L46
            if (r3 == 0) goto L44
            java.util.concurrent.Semaphore r0 = r7.f2806a
            r0.release()
        L44:
            r0 = r4
        L45:
            return r0
        L46:
            r0 = move-exception
            r2 = r3
        L48:
            if (r2 == 0) goto L4f
            java.util.concurrent.Semaphore r1 = r7.f2806a
            r1.release()
        L4f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.manager.util.GoalValue.e():int");
    }
}
