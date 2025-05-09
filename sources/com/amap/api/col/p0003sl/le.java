package com.amap.api.col.p0003sl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public abstract class le {

    /* renamed from: a, reason: collision with root package name */
    lg f1322a;
    private ByteBuffer b;

    le(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        this.b = allocate;
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        this.f1322a = new lg(this.b);
    }

    public final le a() {
        this.f1322a.a(this.b);
        return this;
    }
}
