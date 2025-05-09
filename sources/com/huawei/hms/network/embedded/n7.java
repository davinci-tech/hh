package com.huawei.hms.network.embedded;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public interface n7 {

    public interface a {
        a a(int i, TimeUnit timeUnit);

        v7 a(t7 t7Var) throws IOException;

        @Nullable
        y6 a();

        int b();

        a b(int i, TimeUnit timeUnit);

        int c();

        a c(int i, TimeUnit timeUnit);

        t6 call();

        int d();

        t7 request();
    }

    v7 intercept(a aVar) throws IOException;
}
