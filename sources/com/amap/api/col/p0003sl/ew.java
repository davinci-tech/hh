package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.ServiceSettings;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class ew<T, V> extends ev<T, V> {
    @Override // com.amap.api.col.p0003sl.ev
    protected abstract V a(String str) throws AMapException;

    @Override // com.amap.api.col.p0003sl.ev
    protected abstract String c();

    @Override // com.amap.api.col.p0003sl.ev, com.amap.api.col.p0003sl.ka
    public Map<String, String> getParams() {
        return null;
    }

    public ew(Context context, T t) {
        super(context, t);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public byte[] getEntityBytes() {
        try {
            String c = c();
            StringBuffer stringBuffer = new StringBuffer();
            if (c != null) {
                stringBuffer.append(c);
                stringBuffer.append("&");
            }
            stringBuffer.append(Constants.LANGUAGE_ASSIGN_STR).append(ServiceSettings.getInstance().getLanguage());
            String stringBuffer2 = stringBuffer.toString();
            String c2 = c(stringBuffer2);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(stringBuffer2);
            String a2 = hq.a();
            stringBuffer3.append("&ts=".concat(String.valueOf(a2)));
            stringBuffer3.append("&scode=" + hq.a(this.i, a2, c2));
            return stringBuffer3.toString().getBytes("utf-8");
        } catch (Throwable th) {
            fd.a(th, "ProtocalHandler", "getEntity");
            return null;
        }
    }

    @Override // com.amap.api.col.p0003sl.ev, com.amap.api.col.p0003sl.ka
    public Map<String, String> getRequestHead() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
        hashMap.put(j2.v, Constants.GZIP);
        hashMap.put("User-Agent", "AMAP SDK Android Search 9.2.0");
        hashMap.put("X-INFO", hq.b(this.i));
        hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", "9.2.0", "sea"));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    private static String c(String str) {
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(d(str2));
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() > 1 ? (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1) : str;
    }

    protected static String b(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            fd.a(e, "ProtocalHandler", "strEncoderUnsupportedEncodingException");
            return "";
        } catch (Exception e2) {
            fd.a(e2, "ProtocalHandler", "strEncoderException");
            return "";
        }
    }

    private static String d(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            fd.a(e, "ProtocalHandler", "strReEncoder");
            return "";
        } catch (Exception e2) {
            fd.a(e2, "ProtocalHandler", "strReEncoderException");
            return "";
        }
    }
}
