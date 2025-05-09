package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.util.Session;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class o0 extends opq implements j {
    public static o0 abc = new o0();
    public Session bcd = Session.getInstance();
    public final Set<n0> cde = new HashSet();

    public static o0 jkl() {
        return abc;
    }

    @Override // com.huawei.agconnect.apms.j
    public void abc(i iVar) {
        lmn();
    }

    @Override // com.huawei.agconnect.apms.j
    public void bcd(i iVar) {
        if (this.bcd.isExpired()) {
            lmn();
        }
    }

    @Override // com.huawei.agconnect.apms.opq, com.huawei.agconnect.apms.qrs
    public void hij() {
        if (this.bcd.isExpired()) {
            lmn();
        }
    }

    public Session klm() {
        return this.bcd;
    }

    public void lmn() {
        if (Agent.isDisabled()) {
            return;
        }
        this.bcd = Session.getInstance();
        synchronized (this.cde) {
            for (n0 n0Var : this.cde) {
                if (n0Var != null) {
                    n0Var.abc(this.bcd);
                }
            }
        }
    }

    public void abc(n0 n0Var) {
        synchronized (this.cde) {
            this.cde.add(n0Var);
        }
    }
}
