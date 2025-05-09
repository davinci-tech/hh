package com.huawei.hms.network.embedded;

import java.io.IOException;

/* loaded from: classes9.dex */
public final class da extends IOException {

    /* renamed from: a, reason: collision with root package name */
    public final r9 f5222a;

    public da(r9 r9Var) {
        super("stream was reset: " + r9Var);
        this.f5222a = r9Var;
    }
}
