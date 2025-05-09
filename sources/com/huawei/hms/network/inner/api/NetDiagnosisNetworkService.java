package com.huawei.hms.network.inner.api;

import com.huawei.hms.network.netdiag.qoe.QoeMetrics;
import java.util.Map;

/* loaded from: classes9.dex */
public abstract class NetDiagnosisNetworkService extends InterceptorNetworkService {
    public abstract boolean checkConnectivity();

    public abstract String getNetworkMetrics();

    public abstract QoeMetrics getQoeMetrics(boolean z);

    public abstract Map<String, Integer> getSignalMetrics();

    public abstract Map<String, String> getSyncNetDiagnosisInfo(long j, long j2, boolean z, boolean z2);

    public abstract Map<String, String> getWebsocketNetworkData(long j, long j2);

    public abstract boolean networkUnavailable(long j, long j2);

    public abstract void requestThirdMetrics(String str);
}
