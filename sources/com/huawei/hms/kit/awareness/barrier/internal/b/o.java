package com.huawei.hms.kit.awareness.barrier.internal.b;

/* loaded from: classes9.dex */
public class o extends f {
    public final int b;
    public final int c;
    public final String d;
    public final String e;

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public boolean c() {
        return true;
    }

    public boolean f() {
        return this.c == 2;
    }

    public boolean e() {
        return this.c == 1;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.b.f
    public com.huawei.hms.kit.awareness.barrier.internal.type.f d() {
        return com.huawei.hms.kit.awareness.barrier.internal.type.f.WIFI;
    }

    public boolean b() {
        return this.b == 4;
    }

    public boolean a() {
        return this.b == 3;
    }

    public o(int i, int i2, String str, String str2) {
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = str2;
    }
}
