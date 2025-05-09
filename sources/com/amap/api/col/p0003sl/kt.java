package com.amap.api.col.p0003sl;

import java.io.File;

/* loaded from: classes2.dex */
public final class kt extends kx {

    /* renamed from: a, reason: collision with root package name */
    private int f1265a;
    private String b;

    public kt(String str, kx kxVar) {
        super(kxVar);
        this.f1265a = 30;
        this.b = str;
    }

    @Override // com.amap.api.col.p0003sl.kx
    protected final boolean c() {
        return a(this.b) >= this.f1265a;
    }

    private static int a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.list().length;
            }
            return 0;
        } catch (Throwable th) {
            iv.c(th, "fus", "gfn");
            return 0;
        }
    }
}
