package com.amap.api.col.p0003sl;

import android.content.Context;

/* loaded from: classes2.dex */
public final class ky extends kx {

    /* renamed from: a, reason: collision with root package name */
    private Context f1269a;
    private boolean b;

    public ky(Context context, boolean z) {
        this.f1269a = context;
        this.b = z;
    }

    @Override // com.amap.api.col.p0003sl.kx
    protected final boolean c() {
        return hr.o(this.f1269a) == 1 || this.b;
    }
}
