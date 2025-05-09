package com.huawei.hms.network.embedded;

import android.os.SystemClock;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.LimitQueue;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import java.util.LinkedHashSet;

/* loaded from: classes9.dex */
public class t4 extends r4<c5, String> {
    public static final String b = "NetworkInfoCache";
    public static volatile t4 c = new t4();

    /* renamed from: a, reason: collision with root package name */
    public LimitQueue<c5> f5498a = new LimitQueue<>(8);

    @Override // com.huawei.hms.network.embedded.r4
    public int obtainNetworkQuality(long j, long j2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (int i = 0; i < this.f5498a.size(); i++) {
            c5 c5Var = this.f5498a.get(i);
            if (c5Var != null && j <= c5Var.b() && c5Var.b() <= j2) {
                linkedHashSet.add(Integer.valueOf(c5Var.c()));
                linkedHashSet2.add(c5Var.a());
            }
        }
        if (linkedHashSet.isEmpty()) {
            return !linkedHashSet2.isEmpty() ? 2 : 0;
        }
        return 1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hms.network.embedded.r4
    public c5 getPeekLastInfo() {
        c5 peekLast = this.f5498a.peekLast();
        if (peekLast != null) {
            return peekLast;
        }
        Logger.v(b, "the networkInfoMetrics is null,and return new object");
        return new b5();
    }

    @Override // com.huawei.hms.network.embedded.r4
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void updateCacheInfo(String str) {
        b5 a2 = a(SystemClock.elapsedRealtime());
        c5 peekLast = this.f5498a.peekLast();
        if (peekLast != null && peekLast.c() == a2.c() && peekLast.a() == a2.a()) {
            Logger.v(b, "the network change don't meet interval!");
        } else {
            this.f5498a.add(a2);
        }
    }

    public b5 a(long j) {
        b5 b5Var = new b5();
        b5Var.a(NetworkUtil.getCurrentNetworkType());
        b5Var.a(NetworkUtil.getNetworkStatus(ContextHolder.getResourceContext()));
        b5Var.a(j);
        return b5Var;
    }

    public static t4 getInstance() {
        return c;
    }
}
