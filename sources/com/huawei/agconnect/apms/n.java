package com.huawei.agconnect.apms;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
public class n {
    public static n abc;
    public final Set<j> cde = new HashSet();
    public AtomicBoolean def = new AtomicBoolean(false);
    public AtomicLong efg = new AtomicLong(0);
    public ExecutorService bcd = Executors.newSingleThreadExecutor(new j0("AppStateMonitor"));

    public static n abc() {
        if (abc == null) {
            abc = new n();
        }
        return abc;
    }

    public boolean bcd() {
        return !this.def.get();
    }

    public void abc(j jVar) {
        synchronized (this.cde) {
            this.cde.add(jVar);
        }
    }
}
