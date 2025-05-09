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
public abstract class jm {

    /* renamed from: a, reason: collision with root package name */
    jm f1218a;

    protected abstract byte[] a(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException;

    jm() {
    }

    jm(jm jmVar) {
        this.f1218a = jmVar;
    }

    public final byte[] b(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        jm jmVar = this.f1218a;
        if (jmVar != null) {
            bArr = jmVar.b(bArr);
        }
        return a(bArr);
    }
}
