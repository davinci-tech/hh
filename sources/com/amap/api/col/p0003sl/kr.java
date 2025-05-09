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
public abstract class kr {
    kr c;
    byte[] d = null;

    protected abstract byte[] a(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException;

    public void b(byte[] bArr) {
    }

    kr() {
    }

    kr(kr krVar) {
        this.c = krVar;
    }

    public final byte[] a() throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        byte[] a2 = a(this.d);
        kr krVar = this.c;
        if (krVar == null) {
            return a2;
        }
        krVar.d = a2;
        return krVar.a();
    }
}
