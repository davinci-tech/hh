package com.huawei.hwappdfxmgr.crashmgr;

import com.huawei.haf.common.dfx.memory.AbstractMemoryHandler;
import com.huawei.haf.common.dfx.memory.MemoryMonitorConfig;
import com.huawei.haf.common.os.MemoryUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import defpackage.kts;
import health.compact.a.EnvironmentInfo;
import health.compact.a.EnvironmentUtils;
import health.compact.a.ProcessUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public final class HealthMemoryHandler extends AbstractMemoryHandler {
    private final long b;
    private final boolean c;

    @Override // com.huawei.haf.common.dfx.memory.MemoryCallback
    public int minKillDexThresholdSize() {
        return 40;
    }

    public HealthMemoryHandler() {
        super("TimeEat_MemoryMonitor");
        if (EnvironmentUtils.b()) {
            this.b = 86400000L;
        } else {
            this.b = 0L;
        }
        this.c = false;
    }

    @Override // com.huawei.haf.common.dfx.memory.MemoryCallback
    public MemoryMonitorConfig getMonitorConfig() {
        if (EnvironmentUtils.b()) {
            return c();
        }
        if (EnvironmentUtils.e()) {
            return d();
        }
        return null;
    }

    private MemoryMonitorConfig c() {
        if (MemoryUtils.c() > 3072) {
            return new MemoryMonitorConfig(86, 102);
        }
        return new MemoryMonitorConfig(60, 72);
    }

    private MemoryMonitorConfig d() {
        if (MemoryUtils.c() > 3072) {
            return new MemoryMonitorConfig(92, 110);
        }
        return new MemoryMonitorConfig(70, 85);
    }

    @Override // com.huawei.haf.common.dfx.memory.MemoryCallback
    public boolean isAutoCheck() {
        return this.c;
    }

    @Override // com.huawei.haf.common.dfx.memory.MemoryCallback
    public long minKillTimeInterval() {
        return this.b;
    }

    @Override // com.huawei.haf.common.dfx.memory.MemoryCallback
    public void reportDexOverThresholdSize(long j) {
        kts.b(2);
    }

    @Override // com.huawei.haf.common.dfx.memory.AbstractMemoryHandler, com.huawei.haf.common.dfx.memory.MemoryCallback
    public void reportLeak(String str, String str2, String str3, long j) {
        if ("CloseGuard".equals(str)) {
            Object[] objArr = new Object[2];
            objArr[0] = "leakInfo: ";
            objArr[1] = LogUtil.c() ? str2 : str3;
            ReleaseLogUtil.e("TimeEat_MemoryMonitor", objArr);
        }
        super.reportLeak(str, str2, str3, j);
    }

    @Override // com.huawei.haf.common.dfx.memory.AbstractMemoryHandler
    public void onSendLeakInfo(String str, String str2) {
        e(str, str2, OperationKey.HEALTH_APP_LEAK_90030005.value());
    }

    @Override // com.huawei.haf.common.dfx.memory.AbstractMemoryHandler
    public void onSendMemoryInfo(String str, String str2) {
        e(str, str2, OperationKey.HEALTH_APP_MEMORY_90030004.value());
    }

    private void e(String str, String str2, String str3) {
        boolean z = !EnvironmentInfo.c(getContext());
        LogUtil.c("TimeEat_MemoryMonitor", "reportMemoryInfo isAppSigned=", Boolean.valueOf(z));
        if (z) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put(OpAnalyticsConstants.MONITOR_KEY, str);
            linkedHashMap.put(OpAnalyticsConstants.MONITOR_INFO, str2);
            linkedHashMap.put(OpAnalyticsConstants.PROC_NAME, ProcessUtil.b());
            OpAnalyticsUtil.getInstance().setEvent2nd(str3, linkedHashMap);
        }
    }
}
