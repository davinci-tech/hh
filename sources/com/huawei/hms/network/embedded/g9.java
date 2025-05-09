package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.v7;
import java.io.IOException;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public interface g9 {

    /* renamed from: a, reason: collision with root package name */
    public static final int f5265a = 100;

    long a(v7 v7Var) throws IOException;

    @Nullable
    v7.a a(boolean z) throws IOException;

    y8 a();

    yb a(t7 t7Var, long j) throws IOException;

    void a(t7 t7Var) throws IOException;

    j7 b() throws IOException;

    zb b(v7 v7Var) throws IOException;

    void c() throws IOException;

    void cancel();

    void d() throws IOException;
}
