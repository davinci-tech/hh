package com.huawei.agconnect.apms;

import android.os.Process;
import android.system.Os;
import android.system.OsConstants;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class t {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public static final long bcd = TimeUnit.SECONDS.toMicros(1);
    public static t cde;
    public ScheduledExecutorService def;
    public ScheduledFuture efg;
    public long fgh;
    public String ghi;
    public long hij;
    public ConcurrentLinkedQueue<y> ijk;

    public t() {
        abc.debug("CPUCollector");
        this.efg = null;
        this.fgh = -1L;
        this.ghi = String.format(Locale.ENGLISH, "/proc/%s/stat", Integer.valueOf(Process.myPid()));
        this.hij = Os.sysconf(OsConstants._SC_CLK_TCK);
        this.def = Executors.newSingleThreadScheduledExecutor(new j0("CPUCollector"));
        this.ijk = new ConcurrentLinkedQueue<>();
    }

    public static t abc() {
        if (cde == null) {
            cde = new t();
        }
        return cde;
    }
}
