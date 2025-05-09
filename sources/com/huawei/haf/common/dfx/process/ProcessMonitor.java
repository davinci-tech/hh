package com.huawei.haf.common.dfx.process;

import com.huawei.haf.common.dfx.DfxMonitorTask;
import com.huawei.haf.common.utils.CollectionUtils;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.ProcessUtil;
import java.util.List;

/* loaded from: classes.dex */
public final class ProcessMonitor implements DfxMonitorTask {
    private static DfxMonitorTask b;
    private static ProcessStatusMonitor d;

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public long monitorDelayTime() {
        return 120000L;
    }

    private ProcessMonitor() {
    }

    public static void a(List<String> list, ProcessCallback processCallback) {
        if (!CollectionUtils.d(list) && list.contains(ProcessUtil.b()) && d == null) {
            if (processCallback == null) {
                processCallback = new DefaultProcessHandler("HAF_ProcessMonitor");
            }
            d = new ProcessStatusMonitor(list, processCallback);
            ProcessMonitor processMonitor = new ProcessMonitor();
            b = processMonitor;
            DfxMonitorCenter.d(processMonitor);
        }
    }

    public static void e() {
        if (d != null) {
            DfxMonitorTask dfxMonitorTask = b;
            if (dfxMonitorTask != null) {
                DfxMonitorCenter.b(dfxMonitorTask);
                b = null;
            }
            d.close();
            d = null;
        }
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public void onMonitor() {
        ProcessStatusMonitor processStatusMonitor = d;
        if (processStatusMonitor != null) {
            DfxMonitorCenter.d(this);
            processStatusMonitor.b();
        }
    }
}
