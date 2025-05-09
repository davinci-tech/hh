package com.huawei.hms.network.embedded;

import java.io.IOException;

/* loaded from: classes9.dex */
public interface t6 extends Cloneable {

    public interface a {
        t6 a(t7 t7Var);
    }

    void cancel();

    t6 clone();

    void enqueue(u6 u6Var);

    v7 execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    t7 request();

    ac timeout();
}
