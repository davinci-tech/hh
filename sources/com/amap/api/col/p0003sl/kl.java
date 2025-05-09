package com.amap.api.col.p0003sl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class kl extends kr {

    /* renamed from: a, reason: collision with root package name */
    ByteArrayOutputStream f1260a;

    public kl() {
        this.f1260a = new ByteArrayOutputStream();
    }

    public kl(kr krVar) {
        super(krVar);
        this.f1260a = new ByteArrayOutputStream();
    }

    @Override // com.amap.api.col.p0003sl.kr
    protected final byte[] a(byte[] bArr) {
        byte[] byteArray = this.f1260a.toByteArray();
        try {
            this.f1260a.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f1260a = new ByteArrayOutputStream();
        return byteArray;
    }

    @Override // com.amap.api.col.p0003sl.kr
    public final void b(byte[] bArr) {
        try {
            this.f1260a.write(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
