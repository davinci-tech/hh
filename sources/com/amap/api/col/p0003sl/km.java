package com.amap.api.col.p0003sl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes2.dex */
public final class km extends kr {

    /* renamed from: a, reason: collision with root package name */
    private jm f1261a;

    public km() {
        this.f1261a = new jo();
    }

    public km(kr krVar) {
        super(krVar);
        this.f1261a = new jo();
    }

    @Override // com.amap.api.col.p0003sl.kr
    protected final byte[] a(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return this.f1261a.b(bArr);
    }
}
