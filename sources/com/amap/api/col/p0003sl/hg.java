package com.amap.api.col.p0003sl;

import android.content.Context;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes8.dex */
public abstract class hg<T, V> extends hf<T, V> {
    @Override // com.amap.api.col.p0003sl.hf
    protected abstract V a(String str) throws he;

    @Override // com.amap.api.col.p0003sl.hf
    protected abstract String c();

    @Override // com.amap.api.col.p0003sl.cz, com.amap.api.col.p0003sl.ka
    public Map<String, String> getParams() {
        return null;
    }

    public hg(Context context, T t) {
        super(context, t);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public byte[] getEntityBytes() {
        try {
            return c().getBytes("utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.col.p0003sl.hf, com.amap.api.col.p0003sl.ka
    public Map<String, String> getRequestHead() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("Content-Type", " application/json");
        hashMap.put(j2.v, Constants.GZIP);
        hashMap.put("User-Agent", "AMAP SDK Android Trace 9.3.0");
        hashMap.put("x-INFO", hq.b(this.g));
        hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", "9.3.0", "trace"));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }
}
