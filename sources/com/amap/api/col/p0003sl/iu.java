package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.ka;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class iu extends hu {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f1199a;
    private String b;

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final boolean isHostToIP() {
        return false;
    }

    public iu(byte[] bArr, String str) {
        this.b = "1";
        this.f1199a = (byte[]) bArr.clone();
        this.b = str;
        setDegradeAbility(ka.a.SINGLE);
        setHttpProtocol(ka.c.HTTP);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getRequestHead() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put("Content-Length", String.valueOf(this.f1199a.length));
        return hashMap;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        String c = ia.c(io.b);
        String str = this.b;
        byte[] a2 = ia.a(io.f1192a);
        byte[] bArr = new byte[a2.length + 50];
        System.arraycopy(this.f1199a, 0, bArr, 0, 50);
        System.arraycopy(a2, 0, bArr, 50, a2.length);
        return String.format(c, "1", str, "1", JsbMapKeyNames.H5_TEXT_DOWNLOAD_OPEN, hv.a(bArr));
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final byte[] getEntityBytes() {
        return this.f1199a;
    }
}
