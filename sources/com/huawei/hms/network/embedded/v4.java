package com.huawei.hms.network.embedded;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class v4 extends w4 {
    public static final String b = "AllDetectImpl";

    /* renamed from: a, reason: collision with root package name */
    public Map<Integer, List<y4>> f5538a = new HashMap();

    public String toString() {
        return "AllDetectImpl{allDetectMap=" + this.f5538a + '}';
    }

    @Override // com.huawei.hms.network.embedded.w4
    public y4 b(int i) {
        List<y4> list = this.f5538a.get(Integer.valueOf(i));
        return (list == null || list.isEmpty()) ? new x4() : list.get(list.size() - 1);
    }

    @Override // com.huawei.hms.network.embedded.w4
    public long b() {
        if (this.f5538a.isEmpty()) {
            return 0L;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<Integer> it = this.f5538a.keySet().iterator();
        while (it.hasNext()) {
            y4 b2 = b(it.next().intValue());
            if (b2 != null && b2.b() > 0) {
                currentTimeMillis = Math.min(currentTimeMillis, b2.b());
            }
        }
        return currentTimeMillis;
    }

    @Override // com.huawei.hms.network.embedded.w4
    public void a(y4 y4Var) {
        synchronized (this) {
            int d = y4Var.d();
            if (this.f5538a.get(Integer.valueOf(d)) == null) {
                this.f5538a.put(Integer.valueOf(d), new ArrayList());
            }
            this.f5538a.get(Integer.valueOf(d)).add(y4Var);
        }
    }

    @Override // com.huawei.hms.network.embedded.w4
    public Map<Integer, List<y4>> a() {
        return this.f5538a;
    }

    @Override // com.huawei.hms.network.embedded.w4
    public List<y4> a(int i) {
        return this.f5538a.get(Integer.valueOf(i));
    }
}
