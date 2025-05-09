package com.huawei.agconnect.apms;

/* loaded from: classes8.dex */
public class u implements Runnable {
    public final /* synthetic */ v abc;

    public u(v vVar) {
        this.abc = vVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        v vVar = this.abc;
        long j = vVar.def.totalMemory();
        this.abc.ghi.offer(new x(currentTimeMillis, j - vVar.def.freeMemory(), vVar.def.maxMemory(), j));
    }
}
