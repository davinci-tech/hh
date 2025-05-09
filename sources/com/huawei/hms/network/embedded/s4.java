package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.LimitQueue;
import com.huawei.hms.framework.common.Logger;

/* loaded from: classes9.dex */
public class s4 extends r4<w4, w4> {
    public static final String b = "DetectInfoCache";
    public static s4 c = new s4();

    /* renamed from: a, reason: collision with root package name */
    public LimitQueue<w4> f5472a = new LimitQueue<>(5, false);

    @Override // com.huawei.hms.network.embedded.r4
    public int obtainNetworkQuality(long j, long j2) {
        w4 peekLastInfo = getPeekLastInfo();
        if (peekLastInfo.b(1).c() == 204) {
            return 4;
        }
        return peekLastInfo.b(0).c() == 204 ? 5 : 0;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hms.network.embedded.r4
    public w4 getPeekLastInfo() {
        w4 peekLast = this.f5472a.peekLast();
        if (peekLast == null) {
            return new v4();
        }
        return Math.abs(System.currentTimeMillis() - peekLast.b()) < 300000 ? peekLast : new v4();
    }

    @Override // com.huawei.hms.network.embedded.r4
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void updateCacheInfo(w4 w4Var) {
        Logger.v(b, "DetectCache update :" + w4Var.toString());
        this.f5472a.remove(w4Var);
        this.f5472a.add(w4Var);
    }

    public static s4 getInstance() {
        return c;
    }
}
