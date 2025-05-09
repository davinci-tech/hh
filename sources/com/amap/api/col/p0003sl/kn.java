package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes2.dex */
public final class kn extends kr {

    /* renamed from: a, reason: collision with root package name */
    private Context f1262a;
    private String b;
    private jm e;
    private Object[] f;

    public kn(Context context, kr krVar, jm jmVar, String str, Object... objArr) {
        super(krVar);
        this.f1262a = context;
        this.b = str;
        this.e = jmVar;
        this.f = objArr;
    }

    @Override // com.amap.api.col.p0003sl.kr
    protected final byte[] a(byte[] bArr) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        String a2 = ia.a(bArr);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return ia.a("{\"pinfo\":\"" + ia.a(this.e.b(ia.a(b()))) + "\",\"els\":[" + a2 + "]}");
    }

    private String b() {
        try {
            return String.format(ia.c(this.b), this.f);
        } catch (Throwable th) {
            th.printStackTrace();
            iv.c(th, "ofm", "gpj");
            return "";
        }
    }
}
