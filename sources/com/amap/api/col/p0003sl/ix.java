package com.amap.api.col.p0003sl;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class ix extends hu {

    /* renamed from: a, reason: collision with root package name */
    public JSONObject f1207a = null;
    public Context b = null;

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final byte[] getEntityBytes() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject jSONObject = this.f1207a;
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    stringBuffer.append(next + "=" + URLEncoder.encode(this.f1207a.get(next).toString(), "utf-8") + "&");
                }
            }
            stringBuffer.append("output=json");
            String f = hn.f(this.b);
            stringBuffer.append("&key=".concat(String.valueOf(f)));
            String a2 = hq.a();
            stringBuffer.append("&ts=".concat(String.valueOf(a2)));
            stringBuffer.append("&scode=" + hq.a(this.b, a2, "key=".concat(String.valueOf(f))));
            return stringBuffer.toString().getBytes("utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final Map<String, String> getRequestHead() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
        hashMap.put(j2.v, Constants.GZIP);
        hashMap.put("User-Agent", "AMAP SDK Android core 4.2.9");
        hashMap.put("X-INFO", hq.b(this.b));
        hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", "4.2.9", "core"));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return ht.a().b() ? "https://restsdk.amap.com/sdk/compliance/params" : "http://restsdk.amap.com/sdk/compliance/params";
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getSDKName() {
        return "core";
    }
}
