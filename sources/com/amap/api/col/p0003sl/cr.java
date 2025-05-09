package com.amap.api.col.p0003sl;

import android.content.Context;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class cr extends hf<String, a> {

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public byte[] f947a;
        public int b = -1;
    }

    @Override // com.amap.api.col.p0003sl.hf
    protected final /* bridge */ /* synthetic */ a a(String str) throws he {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.hf
    protected final String c() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final boolean isSupportIPV6() {
        return true;
    }

    @Override // com.amap.api.col.p0003sl.hf
    protected final /* synthetic */ a a(byte[] bArr) throws he {
        return b(bArr);
    }

    public final void b(String str) {
        this.h = str;
    }

    public cr(Context context, String str) {
        super(context, str);
        this.h = "/map/styles";
    }

    private static a b(byte[] bArr) throws he {
        a aVar = new a();
        aVar.f947a = bArr;
        return aVar;
    }

    @Override // com.amap.api.col.p0003sl.cz, com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        HashMap hashMap = new HashMap(16);
        hashMap.put(MedalConstants.EVENT_KEY, hn.f(this.g));
        hashMap.put("output", "bin");
        String a2 = hq.a();
        String a3 = hq.a(this.g, a2, ia.b(hashMap));
        hashMap.put("ts", a2);
        hashMap.put("scode", a3);
        return hashMap;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return this.h;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getIPV6URL() {
        return dv.a(getURL());
    }
}
