package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.g3;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class o3 extends g3 {
    public static final a l = new a();
    public l3 k;

    @Override // com.huawei.hms.network.embedded.g3, com.huawei.hms.network.embedded.g7
    public void callEnd(t6 t6Var) {
    }

    public l3 getWebSocketRequestFinishedInfo() {
        return this.k;
    }

    public static class a extends g3.a {
        @Override // com.huawei.hms.network.embedded.g3.a, com.huawei.hms.network.embedded.g7.b
        public o3 create(t6 t6Var) {
            o3 o3Var = new o3();
            synchronized (this.lock) {
                this.events.put(t6Var, new WeakReference<>(o3Var));
            }
            return o3Var;
        }
    }

    @Override // com.huawei.hms.network.embedded.g3, com.huawei.hms.network.embedded.g7
    public void callStart(t6 t6Var) {
        super.callStart(t6Var);
        this.k = (l3) getRequestFinishedInfo();
    }

    public static a getWebSocketEventFactory() {
        return l;
    }
}
