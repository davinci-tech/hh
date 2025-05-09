package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class ko extends kr {

    /* renamed from: a, reason: collision with root package name */
    private StringBuilder f1263a;
    private boolean b;

    public ko() {
        this.f1263a = new StringBuilder();
        this.b = true;
    }

    public ko(kr krVar) {
        super(krVar);
        this.f1263a = new StringBuilder();
        this.b = true;
    }

    @Override // com.amap.api.col.p0003sl.kr
    protected final byte[] a(byte[] bArr) {
        byte[] a2 = ia.a(this.f1263a.toString());
        this.d = a2;
        this.b = true;
        StringBuilder sb = this.f1263a;
        sb.delete(0, sb.length());
        return a2;
    }

    @Override // com.amap.api.col.p0003sl.kr
    public final void b(byte[] bArr) {
        String a2 = ia.a(bArr);
        if (this.b) {
            this.b = false;
        } else {
            this.f1263a.append(",");
        }
        StringBuilder sb = this.f1263a;
        sb.append("{\"log\":\"");
        sb.append(a2);
        sb.append("\"}");
    }
}
