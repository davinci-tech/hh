package com.huawei.hms.network.embedded;

import java.io.IOException;

/* loaded from: classes9.dex */
public final class b9 extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    public IOException f5189a;
    public IOException b;

    public IOException b() {
        return this.b;
    }

    public void a(IOException iOException) {
        f8.a(this.f5189a, iOException);
        this.b = iOException;
    }

    public IOException a() {
        return this.f5189a;
    }

    public b9(IOException iOException) {
        super(iOException);
        this.f5189a = iOException;
        this.b = iOException;
    }
}
