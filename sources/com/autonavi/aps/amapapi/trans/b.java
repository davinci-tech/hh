package com.autonavi.aps.amapapi.trans;

import android.text.TextUtils;
import com.amap.api.col.p0003sl.hu;
import java.util.Map;

/* loaded from: classes2.dex */
public final class b extends hu {

    /* renamed from: a, reason: collision with root package name */
    Map<String, String> f1641a = null;
    Map<String, String> b = null;
    String g = "";
    byte[] h = null;
    private String i = null;

    public final void a(Map<String, String> map) {
        this.f1641a = map;
    }

    public final void b(Map<String, String> map) {
        this.b = map;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getRequestHead() {
        return this.f1641a;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        return this.b;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return this.g;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final void a(byte[] bArr) {
        this.h = bArr;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final byte[] getEntityBytes() {
        return this.h;
    }

    public final void b(String str) {
        this.i = str;
    }

    @Override // com.amap.api.col.p0003sl.hu, com.amap.api.col.p0003sl.ka
    public final String getIPV6URL() {
        if (!TextUtils.isEmpty(this.i)) {
            return this.i;
        }
        return super.getIPV6URL();
    }
}
