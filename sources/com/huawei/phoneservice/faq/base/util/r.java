package com.huawei.phoneservice.faq.base.util;

import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.entity.FaqSdkServiceUrlResponse;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class r {
    public static String g() {
        return j.c().getSdk(FaqConstants.SDK_URL_YUN);
    }

    public static String a(String str) {
        StringBuilder sb;
        String str2;
        if ("Y".equals(j.c().getSdk(FaqConstants.FAQ_USE_OLD_DOMAIN))) {
            sb = new StringBuilder();
            sb.append(d());
            str2 = "/secured/CCPC/EN/";
        } else {
            sb = new StringBuilder();
            sb.append(i());
            str2 = "/forward/ccpc_gateway_sdk/";
        }
        sb.append(str2);
        sb.append(str);
        sb.append("/1");
        return sb.toString();
    }

    public static String i() {
        return j.c().getSdk(FaqConstants.SDK_URL_SGW);
    }

    public static String d() {
        return j.c().getSdk(FaqConstants.SDK_URL_MD);
    }

    public static String b() {
        String sdk = j.c().getSdk(FaqConstants.GRS_URL_LOGSERVICE);
        i.d("FaqSdk", "getLogServiceUrl urls " + sdk);
        return sdk;
    }

    public static String e() {
        String sdk = j.c().getSdk(FaqConstants.FAQ_MODELTYPE);
        return l.e(sdk) ? d.a() : sdk;
    }

    public static String c() {
        String sdk = j.c().getSdk(FaqConstants.GRS_URL_SGW);
        i.d("FaqSdk", "getInitSgwUrl urls " + sdk);
        return sdk;
    }

    public static void d(Map<String, String> map, Map<String, String> map2) {
        if (map == null || map2 == null) {
            return;
        }
        String str = map.get(FaqConstants.GRS_SERVICE_KEY_CCPC);
        if (!TextUtils.isEmpty(str)) {
            map2.put(FaqConstants.GRS_URL_CCPC, str);
        }
        String str2 = map.get(FaqConstants.GRS_SERVICE_KEY_SGW);
        if (!TextUtils.isEmpty(str2)) {
            map2.put(FaqConstants.GRS_URL_SGW, str2);
        }
        String str3 = map.get("ROOT");
        if (!TextUtils.isEmpty(str3)) {
            map2.put(FaqConstants.GRS_URL_LOGSERVICE, str3);
        }
        String str4 = map.get(FaqConstants.GRS_SERVICE_KEY_DMPA);
        if (!TextUtils.isEmpty(str4)) {
            map2.put(FaqConstants.GRS_URL_DMPA, str4);
        }
        j.c().apply();
    }

    public static void d(FaqSdkServiceUrlResponse.ServiceUrl serviceUrl, Map<String, String> map) {
        if (!TextUtils.isEmpty(serviceUrl.b())) {
            map.put(FaqConstants.SDK_URL_HA, serviceUrl.b());
        }
        if (!TextUtils.isEmpty(serviceUrl.c())) {
            map.put(FaqConstants.SDK_URL_MD, serviceUrl.c());
        }
        if (!TextUtils.isEmpty(serviceUrl.d())) {
            map.put(FaqConstants.SDK_URL_SGW, serviceUrl.d());
        }
        if (!TextUtils.isEmpty(serviceUrl.e())) {
            map.put(FaqConstants.SDK_URL_YUN, serviceUrl.e());
        }
        j.c().apply();
    }

    public static Map<String, String> e(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        hashMap.putAll(map);
        hashMap.remove(FaqConstants.SDK_URL_HA);
        hashMap.remove(FaqConstants.SDK_URL_MD);
        hashMap.remove(FaqConstants.SDK_URL_SGW);
        hashMap.remove(FaqConstants.SDK_URL_YUN);
        hashMap.remove(FaqConstants.GRS_URL_CCPC);
        hashMap.remove(FaqConstants.GRS_URL_SGW);
        hashMap.remove(FaqConstants.GRS_URL_LOGSERVICE);
        hashMap.remove(FaqConstants.GRS_URL_DMPA);
        return hashMap;
    }

    public static String a() {
        String sdk = j.c().getSdk(FaqConstants.GRS_URL_CCPC);
        i.d("FaqSdk", "getInitCcpcUrl urls " + sdk);
        return sdk;
    }
}
