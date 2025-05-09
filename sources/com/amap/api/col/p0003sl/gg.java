package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class gg extends ev<String, String> {
    private String k;

    @Override // com.amap.api.col.p0003sl.ev
    protected final String c() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ String a(String str) throws AMapException {
        return b(str);
    }

    public gg(Context context, String str) {
        super(context, str);
        this.k = str;
    }

    @Override // com.amap.api.col.p0003sl.ev, com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        byte[] bArr;
        StringBuilder sb = new StringBuilder("channel=open_api&flag=1");
        sb.append("&address=" + URLEncoder.encode(this.k));
        StringBuffer stringBuffer = new StringBuffer("open_api1");
        stringBuffer.append(this.k).append("@8UbJH6N2szojnTHONAWzB6K7N1kaj7Y0iUMarxac");
        String b = hv.b(stringBuffer.toString());
        sb.append("&sign=");
        sb.append(b.toUpperCase(Locale.US));
        sb.append("&output=json");
        try {
            bArr = gp.a(sb.toString().getBytes("utf-8"), "Yaynpa84IKOfasFx".getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            fd.a(e, "ShareUrlSearchHandler", "getParams");
            bArr = null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("ent", "2");
        hashMap.put("in", hs.b(bArr));
        hashMap.put("keyt", "openapi");
        return hashMap;
    }

    private static String b(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String a2 = fl.a(jSONObject, "code");
            String a3 = fl.a(jSONObject, "message");
            if ("1".equals(a2)) {
                return fl.a(jSONObject, "transfer_url");
            }
            if ("0".equals(a2)) {
                throw new AMapException(AMapException.AMAP_SERVICE_UNKNOWN_ERROR, 0, a3);
            }
            if ("2".equals(a2)) {
                throw new AMapException(AMapException.AMAP_SHARE_FAILURE, 0, a3);
            }
            if ("3".equals(a2)) {
                throw new AMapException(AMapException.AMAP_SERVICE_INVALID_PARAMS, 0, a3);
            }
            if ("4".equals(a2)) {
                throw new AMapException("用户签名未通过", 0, a3);
            }
            if ("5".equals(a2)) {
                throw new AMapException(AMapException.AMAP_SHARE_LICENSE_IS_EXPIRED, 0, a3);
            }
            return null;
        } catch (JSONException e) {
            fd.a(e, "ShareUrlSearchHandler", "paseJSON");
            return null;
        }
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.f();
    }
}
