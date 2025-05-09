package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.netdiag.info.SignalInfoMetrics;
import java.util.Map;

/* loaded from: classes9.dex */
public class e5 implements SignalInfoMetrics {

    /* renamed from: a, reason: collision with root package name */
    public int f5228a;
    public Map<String, Integer> b = NetworkUtil.getLteSignalInfo(ContextHolder.getResourceContext());
    public long c;

    public String toString() {
        return "SignalInfoImpl{wifiSignalStrength=" + getWifiSignalStrength() + ", signalTimeStamp=" + getSignalTimeStamp() + ", MobileSignalStrength=" + getMobileSignalStrength() + ", LteRsrq=" + getLteRsrq() + ", LteRssi=" + getLteRssi() + ", LteRssnr=" + getLteRssnr() + ", LteRsrp=" + getLteRsrp() + ", LteCqi=" + getLteCqi() + '}';
    }

    @Override // com.huawei.hms.network.netdiag.info.SignalInfoMetrics
    public int getWifiSignalStrength() {
        return this.f5228a;
    }

    @Override // com.huawei.hms.network.netdiag.info.SignalInfoMetrics
    public long getSignalTimeStamp() {
        return this.c;
    }

    @Override // com.huawei.hms.network.netdiag.info.SignalInfoMetrics
    public int getMobileSignalStrength() {
        return a(NetworkUtil.SignalType.LTE_DBM);
    }

    @Override // com.huawei.hms.network.netdiag.info.SignalInfoMetrics
    public int getLteRssnr() {
        return a(NetworkUtil.SignalType.LTE_RSSNR);
    }

    @Override // com.huawei.hms.network.netdiag.info.SignalInfoMetrics
    public int getLteRssi() {
        return a(NetworkUtil.SignalType.LTE_RSSI);
    }

    @Override // com.huawei.hms.network.netdiag.info.SignalInfoMetrics
    public int getLteRsrq() {
        return a(NetworkUtil.SignalType.LTE_RSRQ);
    }

    @Override // com.huawei.hms.network.netdiag.info.SignalInfoMetrics
    public int getLteRsrp() {
        return a(NetworkUtil.SignalType.LTE_RSRP);
    }

    @Override // com.huawei.hms.network.netdiag.info.SignalInfoMetrics
    public int getLteCqi() {
        return a(NetworkUtil.SignalType.LTE_CQI);
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(int i) {
        this.f5228a = i;
    }

    private int a(String str) {
        Map<String, Integer> map = this.b;
        if (map == null || map.get(str) == null) {
            return Integer.MAX_VALUE;
        }
        return this.b.get(str).intValue();
    }
}
