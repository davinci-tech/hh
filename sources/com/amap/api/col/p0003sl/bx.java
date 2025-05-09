package com.amap.api.col.p0003sl;

import java.util.Hashtable;
import java.util.Map;

/* loaded from: classes2.dex */
public final class bx extends cz {

    /* renamed from: a, reason: collision with root package name */
    private String f934a;

    @Override // com.amap.api.col.p0003sl.cz, com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final boolean isSupportIPV6() {
        return false;
    }

    public bx(String str) {
        this.f934a = str;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getRequestHead() {
        Hashtable hashtable = new Hashtable(32);
        hashtable.put("User-Agent", "MAC=channel:amapapi");
        return hashtable;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return this.f934a;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getIPV6URL() {
        return getURL();
    }
}
