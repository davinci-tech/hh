package com.huawei.agconnect.apms;

import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class k implements Runnable {
    public final /* synthetic */ n abc;

    public k(n nVar) {
        this.abc = nVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        HashSet hashSet;
        if (this.abc.def.get()) {
            n nVar = this.abc;
            synchronized (nVar.cde) {
                hashSet = new HashSet(nVar.cde);
            }
            i iVar = new i(nVar);
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((j) it.next()).bcd(iVar);
            }
            this.abc.def.set(false);
        }
    }
}
