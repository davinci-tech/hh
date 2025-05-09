package com.amap.api.col.p0003sl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/* loaded from: classes2.dex */
public final class kq extends kr {
    public kq() {
    }

    public kq(kr krVar) {
        super(krVar);
    }

    @Override // com.amap.api.col.p0003sl.kr
    protected final byte[] a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
        stringBuffer.append(" ");
        stringBuffer.append(UUID.randomUUID().toString());
        stringBuffer.append(" ");
        if (stringBuffer.length() != 53) {
            return new byte[0];
        }
        byte[] a2 = ia.a(stringBuffer.toString());
        byte[] bArr2 = new byte[a2.length + bArr.length];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(bArr, 0, bArr2, a2.length, bArr.length);
        return bArr2;
    }
}
