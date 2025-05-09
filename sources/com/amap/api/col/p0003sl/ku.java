package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public final class ku extends kx {
    private Context b;
    private boolean d;
    private int e;
    private int f;

    /* renamed from: a, reason: collision with root package name */
    private String f1266a = "iKey";
    private int g = 0;

    public ku(Context context, boolean z, int i, int i2, String str) {
        a(context, z, i, i2, str, 0);
    }

    public ku(Context context, boolean z, int i, int i2, String str, int i3) {
        a(context, z, i, i2, str, i3);
    }

    private void a(Context context, boolean z, int i, int i2, String str, int i3) {
        this.b = context;
        this.d = z;
        this.e = i;
        this.f = i2;
        this.f1266a = str;
        this.g = i3;
    }

    @Override // com.amap.api.col.p0003sl.kx
    protected final boolean c() {
        if (hr.o(this.b) == 1) {
            return true;
        }
        if (!this.d) {
            return false;
        }
        String a2 = it.a(this.b, this.f1266a);
        if (TextUtils.isEmpty(a2)) {
            return true;
        }
        String[] split = a2.split("\\|");
        if (split != null && split.length >= 2) {
            return !ia.a(System.currentTimeMillis(), "yyyyMMdd").equals(split[0]) || Integer.parseInt(split[1]) < this.f;
        }
        it.b(this.b, this.f1266a);
        return true;
    }

    @Override // com.amap.api.col.p0003sl.kx
    public final int a() {
        int i;
        if ((hr.o(this.b) == 1 || (i = this.e) <= 0) && ((i = this.g) <= 0 || i >= Integer.MAX_VALUE)) {
            i = Integer.MAX_VALUE;
        }
        return this.c != null ? Math.max(i, this.c.a()) : i;
    }

    @Override // com.amap.api.col.p0003sl.kx
    public final void a_(int i) {
        if (hr.o(this.b) == 1) {
            return;
        }
        String a2 = ia.a(System.currentTimeMillis(), "yyyyMMdd");
        String a3 = it.a(this.b, this.f1266a);
        if (!TextUtils.isEmpty(a3)) {
            String[] split = a3.split("\\|");
            if (split == null || split.length < 2) {
                it.b(this.b, this.f1266a);
            } else if (a2.equals(split[0])) {
                i += Integer.parseInt(split[1]);
            }
        }
        it.a(this.b, this.f1266a, a2 + "|" + i);
    }
}
