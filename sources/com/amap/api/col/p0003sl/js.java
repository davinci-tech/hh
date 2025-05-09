package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.ka;
import java.util.Map;

/* loaded from: classes2.dex */
public final class js extends ka {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f1229a;
    private Map<String, String> b;

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getRequestHead() {
        return null;
    }

    public js(byte[] bArr, Map<String, String> map) {
        this.f1229a = bArr;
        this.b = map;
        setDegradeAbility(ka.a.SINGLE);
        setHttpProtocol(ka.c.HTTPS);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        return this.b;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final byte[] getEntityBytes() {
        return this.f1229a;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return "https://adiu.amap.com/ws/device/adius";
    }
}
