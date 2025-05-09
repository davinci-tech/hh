package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class j0 implements ThreadFactory {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static Thread.UncaughtExceptionHandler bcd = new abc();
    public ThreadGroup cde;
    public String def;
    public AtomicInteger efg = new AtomicInteger(1);

    public class abc implements Thread.UncaughtExceptionHandler {
        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            j0.abc.error("uncaught exception " + th.getMessage() + " occurred in thread [" + thread.getName() + "].");
        }
    }

    public j0(String str) {
        SecurityManager securityManager = System.getSecurityManager();
        this.cde = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.def = "APMS_" + str + Constants.LINK;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.cde, runnable, this.def + this.efg.getAndIncrement(), 0L);
        thread.setUncaughtExceptionHandler(bcd);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }
}
