package com.amap.api.col.p0003sl;

import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes8.dex */
public abstract class dd extends cz {
    @Override // com.amap.api.col.p0003sl.cz, com.amap.api.col.p0003sl.ka
    public Map<String, String> getParams() {
        return null;
    }

    public dd() {
        setProxy(hy.a(z.f1381a));
        setConnectionTimeout(5000);
        setSoTimeout(50000);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public Map<String, String> getRequestHead() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("User-Agent", v.c);
        hashtable.put(j2.v, Constants.GZIP);
        hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", "9.3.0", "3dmap"));
        hashtable.put("x-INFO", hq.a(z.f1381a));
        hashtable.put(MedalConstants.EVENT_KEY, hn.f(z.f1381a));
        hashtable.put("logversion", "2.1");
        return hashtable;
    }

    protected static String a(String str) {
        String b = b(str);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        String a2 = hq.a();
        stringBuffer.append("&ts=".concat(String.valueOf(a2)));
        stringBuffer.append("&scode=" + hq.a(z.f1381a, a2, b));
        return stringBuffer.toString();
    }

    private static String b(String str) {
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(c(str2));
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() > 1 ? (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1) : str;
    }

    private static String c(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            iv.c(e, "AbstractProtocalHandler", "strReEncoder");
            return "";
        } catch (Exception e2) {
            iv.c(e2, "AbstractProtocalHandler", "strReEncoderException");
            return "";
        }
    }

    @Override // com.amap.api.col.p0003sl.ka
    public String getIPV6URL() {
        String url = getURL();
        return (url == null || !url.contains("http://restsdk.amap.com/v4/gridmap?")) ? url : dv.a(url);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public boolean isSupportIPV6() {
        String url = getURL();
        return url != null && url.contains("http://restsdk.amap.com/v4/gridmap?");
    }
}
