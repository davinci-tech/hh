package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes2.dex */
public final class kw extends kx {

    /* renamed from: a, reason: collision with root package name */
    protected int f1268a;
    protected long b;
    private String d;
    private Context e;

    public kw(Context context, int i, String str, kx kxVar) {
        super(kxVar);
        this.f1268a = i;
        this.d = str;
        this.e = context;
    }

    @Override // com.amap.api.col.p0003sl.kx
    protected final boolean c() {
        if (this.b == 0) {
            String a2 = it.a(this.e, this.d);
            this.b = TextUtils.isEmpty(a2) ? 0L : Long.parseLong(a2);
        }
        return System.currentTimeMillis() - this.b >= ((long) this.f1268a);
    }

    @Override // com.amap.api.col.p0003sl.kx
    public final void a_(boolean z) {
        super.a_(z);
        if (z) {
            String str = this.d;
            long currentTimeMillis = System.currentTimeMillis();
            this.b = currentTimeMillis;
            it.a(this.e, str, String.valueOf(currentTimeMillis));
        }
    }
}
