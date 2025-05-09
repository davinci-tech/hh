package com.huawei.hwappdfxmgr.crashmgr;

import com.huawei.haf.common.dfx.block.DefaultThreadPoolHandler;
import com.huawei.haf.threadpool.ThreadPoolStateInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import defpackage.ixm;
import health.compact.a.EnvironmentInfo;
import health.compact.a.ProcessUtil;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class HealthThreadPoolHandler extends DefaultThreadPoolHandler {
    public HealthThreadPoolHandler() {
        super("TimeEat_PoolMonitor");
    }

    @Override // com.huawei.haf.common.dfx.block.DefaultThreadPoolHandler
    public void d(String str, String str2) {
        boolean z = !EnvironmentInfo.c(getContext());
        LogUtil.c("TimeEat_PoolMonitor", "onSendThreadPoolInfo isAppSigned = ", Boolean.valueOf(z));
        if (z) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put(OpAnalyticsConstants.MONITOR_KEY, str);
            linkedHashMap.put(OpAnalyticsConstants.MONITOR_INFO, str2);
            linkedHashMap.put(OpAnalyticsConstants.PROC_NAME, ProcessUtil.b());
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_THREAD_POOL_90030003.value(), linkedHashMap);
        }
    }

    @Override // com.huawei.haf.common.dfx.block.DefaultThreadPoolHandler, com.huawei.haf.threadpool.ThreadPoolCallback
    public void threadPoolStateInfo(String str, ThreadPoolStateInfo threadPoolStateInfo, long j) {
        super.threadPoolStateInfo(str, threadPoolStateInfo, j);
        ixm.e("TimeEat_PoolMonitor", j);
    }
}
