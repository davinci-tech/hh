package com.huawei.hms.network.netdiag.cache;

import android.os.SystemClock;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.LimitQueue;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.network.embedded.e5;
import com.huawei.hms.network.embedded.r4;
import com.huawei.hms.network.netdiag.info.SignalInfoMetrics;
import java.util.LinkedHashSet;

/* loaded from: classes9.dex */
public class SignalInfoCache extends r4<SignalInfoMetrics, Long> {
    public static final String c = "SignalInfoCache";
    public static volatile SignalInfoCache d = new SignalInfoCache();

    /* renamed from: a, reason: collision with root package name */
    public LimitQueue<SignalInfoMetrics> f5645a = new LimitQueue<>(8);
    public SignalInfoMetrics b;

    @Override // com.huawei.hms.network.embedded.r4
    public void updateCacheInfo(Long l) {
        SignalInfoMetrics peekLast = this.f5645a.peekLast();
        this.b = getSignalInfo(SystemClock.elapsedRealtime());
        if (peekLast == null || Math.abs(peekLast.getWifiSignalStrength() - this.b.getWifiSignalStrength()) > 15 || Math.abs(peekLast.getMobileSignalStrength() - this.b.getMobileSignalStrength()) > 15) {
            this.f5645a.add(this.b);
            return;
        }
        Logger.v(c, "the signal not meet interval!" + this.b.getMobileSignalStrength() + "/" + this.b.getWifiSignalStrength());
    }

    public SignalInfoMetrics peekLastInfo() {
        SignalInfoMetrics peekLast = this.f5645a.peekLast();
        if (peekLast != null) {
            return peekLast;
        }
        Logger.v(c, "the networkInfoMetrics is null,and return new object");
        e5 e5Var = new e5();
        e5Var.a(NetworkUtil.getWifiRssi(ContextHolder.getResourceContext()));
        return e5Var;
    }

    @Override // com.huawei.hms.network.embedded.r4
    public int obtainNetworkQuality(long j, long j2) {
        if (this.f5645a.size() <= 1) {
            return 0;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (int i = 0; i < this.f5645a.size(); i++) {
            SignalInfoMetrics signalInfoMetrics = this.f5645a.get(i);
            if (signalInfoMetrics != null && j <= signalInfoMetrics.getSignalTimeStamp() && signalInfoMetrics.getSignalTimeStamp() <= j2) {
                linkedHashSet.add(Integer.valueOf(signalInfoMetrics.getMobileSignalStrength()));
                linkedHashSet2.add(Integer.valueOf(signalInfoMetrics.getMobileSignalStrength()));
            }
        }
        return (linkedHashSet.size() <= 1 && linkedHashSet2.size() <= 1) ? 0 : 3;
    }

    public e5 getSignalInfo(long j) {
        e5 e5Var = new e5();
        e5Var.a(NetworkUtil.getWifiRssi(ContextHolder.getResourceContext()));
        e5Var.a(j);
        Logger.v(c, e5Var);
        return e5Var;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hms.network.embedded.r4
    public SignalInfoMetrics getPeekLastInfo() {
        SignalInfoMetrics peekLast = this.f5645a.peekLast();
        if (peekLast != null) {
            return peekLast;
        }
        Logger.v(c, "the networkInfoMetrics is null,and return new object");
        return new e5();
    }

    public SignalInfoMetrics getLastInfo() {
        SignalInfoMetrics signalInfoMetrics = this.b;
        return signalInfoMetrics != null ? signalInfoMetrics : new e5();
    }

    public static SignalInfoCache getInstance() {
        return d;
    }
}
