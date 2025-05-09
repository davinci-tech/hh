package com.huawei.hms.network.embedded;

import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public interface z7 {

    public interface a {
        z7 a(t7 t7Var, a8 a8Var);
    }

    long a();

    boolean a(int i, @Nullable String str);

    boolean b(eb ebVar);

    boolean b(String str);

    void cancel();

    t7 request();
}
