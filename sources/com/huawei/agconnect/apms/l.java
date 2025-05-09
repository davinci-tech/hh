package com.huawei.agconnect.apms;

import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class l implements Runnable {
    public final /* synthetic */ n abc;

    public l(n nVar) {
        this.abc = nVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        HashSet hashSet;
        if (this.abc.efg.incrementAndGet() != 1 || this.abc.def.get()) {
            return;
        }
        this.abc.def.set(true);
        n nVar = this.abc;
        synchronized (nVar.cde) {
            hashSet = new HashSet(nVar.cde);
        }
        i iVar = new i(nVar);
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((j) it.next()).abc(iVar);
        }
    }
}
