package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/* loaded from: classes8.dex */
public class v {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static v bcd;
    public ScheduledExecutorService cde;
    public Runtime def;
    public ScheduledFuture efg = null;
    public long fgh = -1;
    public ConcurrentLinkedQueue<x> ghi = new ConcurrentLinkedQueue<>();

    public v(ScheduledExecutorService scheduledExecutorService, Runtime runtime) {
        this.cde = scheduledExecutorService;
        this.def = runtime;
    }

    public static v abc() {
        if (bcd == null) {
            bcd = new v(Executors.newSingleThreadScheduledExecutor(new j0("MemoryCollector")), Runtime.getRuntime());
        }
        return bcd;
    }
}
