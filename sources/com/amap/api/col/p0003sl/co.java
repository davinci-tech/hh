package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class co {

    /* renamed from: a, reason: collision with root package name */
    private a f944a;
    private b b;
    private b c;

    public static final class a extends cn {
    }

    public static final class b extends cn {
    }

    public final void a() {
        synchronized (this) {
            a aVar = this.f944a;
            if (aVar != null) {
                aVar.a();
                this.f944a = null;
            }
            b bVar = this.b;
            if (bVar != null) {
                bVar.a();
                this.b = null;
            }
            b bVar2 = this.c;
            if (bVar2 != null) {
                bVar2.a();
                this.c = null;
            }
        }
    }
}
