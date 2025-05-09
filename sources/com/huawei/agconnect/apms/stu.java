package com.huawei.agconnect.apms;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.agconnect.apms.anr.NativeHandler;
import com.huawei.agconnect.apms.collect.HiAnalyticsManager;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public class stu implements Runnable {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public final uvw efg;
    public long fgh;
    public final ScheduledExecutorService bcd = Executors.newSingleThreadScheduledExecutor(new j0("Collector"));
    public ScheduledFuture cde = null;
    public long def = 60000;
    public Lock ghi = new ReentrantLock();

    public static class abc implements Runnable {
        public stu abc;
        public boolean bcd;

        public abc(stu stuVar, boolean z) {
            this.abc = stuVar;
            this.bcd = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.abc.abc(this.bcd);
            HiAnalyticsManager.getInstance().onReport();
        }
    }

    public stu(uvw uvwVar) {
        this.efg = uvwVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void abc(boolean r7) {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            if (r7 == 0) goto L24
            com.huawei.agconnect.apms.n r7 = com.huawei.agconnect.apms.n.abc()     // Catch: java.lang.Throwable -> L2a
            boolean r7 = r7.bcd()     // Catch: java.lang.Throwable -> L2a
            if (r7 == 0) goto L24
            boolean r7 = r6.bcd()     // Catch: java.lang.Throwable -> L2a
            if (r7 == 0) goto L1e
            com.huawei.agconnect.apms.log.AgentLog r7 = com.huawei.agconnect.apms.stu.abc     // Catch: java.lang.Throwable -> L2a
            java.lang.String r2 = "skipping collection while app in background."
            r7.info(r2)     // Catch: java.lang.Throwable -> L2a
            goto L42
        L1e:
            com.huawei.agconnect.apms.uvw r7 = r6.efg     // Catch: java.lang.Throwable -> L2a
            r7.bcd()     // Catch: java.lang.Throwable -> L2a
            goto L42
        L24:
            com.huawei.agconnect.apms.uvw r7 = r6.efg     // Catch: java.lang.Throwable -> L2a
            r7.bcd()     // Catch: java.lang.Throwable -> L2a
            goto L42
        L2a:
            r7 = move-exception
            com.huawei.agconnect.apms.log.AgentLog r2 = com.huawei.agconnect.apms.stu.abc
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "exception occurred while executing collect: "
            r3.<init>(r4)
            java.lang.String r7 = r7.getMessage()
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r2.error(r7)
        L42:
            com.huawei.agconnect.apms.uvw r7 = r6.efg
            r2 = 4
            int r7 = r7.bcd
            if (r2 != r7) goto L4c
            r6.cde()
        L4c:
            long r2 = java.lang.System.currentTimeMillis()
            com.huawei.agconnect.apms.log.AgentLog r7 = com.huawei.agconnect.apms.stu.abc
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "tick took "
            r4.<init>(r5)
            long r2 = r2 - r0
            r4.append(r2)
            java.lang.String r0 = "ms."
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r7.debug(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.stu.abc(boolean):void");
    }

    public final boolean bcd() {
        String str;
        if (Build.VERSION.SDK_INT >= 28) {
            str = Application.getProcessName();
        } else {
            try {
                str = (String) Class.forName("android.app.ActivityThread").getMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable unused) {
                str = "";
            }
        }
        if (Agent.getContext() == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equals(Agent.getContext().getPackageName());
    }

    public void cde() {
        if (this.cde != null) {
            abc();
            abc.debug("collector stopped.");
            uvw uvwVar = this.efg;
            uvwVar.getClass();
            try {
                Iterator it = ((ArrayList) uvwVar.cde()).iterator();
                while (it.hasNext()) {
                    ((qrs) it.next()).ijk();
                }
            } catch (Throwable th) {
                uvw.abc.error("exception occurred while notifying onCollectStop: " + th.getMessage());
            }
            if (uvwVar.hij != null) {
                NativeHandler bcd = NativeHandler.bcd();
                jkl jklVar = uvwVar.hij;
                synchronized (bcd.bcd) {
                    Set<jkl> set = bcd.cde;
                    if (set != null) {
                        set.remove(jklVar);
                    }
                }
                uvwVar.hij = null;
            }
        }
    }

    public final void def() {
        long currentTimeMillis = this.fgh == 0 ? -1L : System.currentTimeMillis() - this.fgh;
        if (1000 + currentTimeMillis >= this.def || currentTimeMillis == -1) {
            long currentTimeMillis2 = System.currentTimeMillis();
            try {
                abc(true);
            } catch (Throwable th) {
                abc.error("exception occurred when tick: " + th.getMessage());
            }
            this.fgh = currentTimeMillis2;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.ghi.lock();
            def();
        } finally {
            try {
            } finally {
            }
        }
    }

    public final void abc() {
        try {
            this.ghi.lock();
            ScheduledFuture scheduledFuture = this.cde;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
                this.cde = null;
            }
        } finally {
            this.ghi.unlock();
        }
    }
}
