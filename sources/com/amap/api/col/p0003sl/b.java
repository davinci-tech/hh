package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.amap.api.services.district.DistrictSearchQuery;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    jt f914a;

    public b(Context context) {
        this.f914a = null;
        try {
            ht.a().a(context);
        } catch (Throwable unused) {
        }
        this.f914a = jt.a();
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5) {
        Map<String, String> b = b(context, str2, str3, str4, str5, null, null, null);
        b.put("children", "1");
        b.put("page", "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map<String, String> b = b(context, str2, str3, null, str4, str5, str6, str7);
        b.put("children", "1");
        b.put("page", "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }

    public final String a(Context context, String str, String str2) {
        Map<String, String> b = b(context, str2, null, null, null, null, null, null);
        b.put("extensions", "all");
        b.put("subdistrict", "0");
        return a(context, str, b);
    }

    private String a(Context context, String str, Map<String, String> map) {
        try {
            HashMap hashMap = new HashMap(16);
            com.autonavi.aps.amapapi.trans.b bVar = new com.autonavi.aps.amapapi.trans.b();
            hashMap.clear();
            hashMap.put("Content-Type", RequestBody.HEAD_VALUE_CONTENT_TYPE_URLENCODED);
            hashMap.put("Connection", "Keep-Alive");
            hashMap.put("User-Agent", "AMAP_Location_SDK_Android 6.1.0");
            String a2 = hq.a();
            String a3 = hq.a(context, a2, ia.b(map));
            map.put("ts", a2);
            map.put("scode", a3);
            bVar.b(map);
            bVar.a(hashMap);
            bVar.a(str);
            bVar.setProxy(hy.a(context));
            bVar.setConnectionTimeout(com.autonavi.aps.amapapi.utils.b.i);
            bVar.setSoTimeout(com.autonavi.aps.amapapi.utils.b.i);
            try {
                return new String(jt.a(bVar).f1250a, "utf-8");
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "GeoFenceNetManager", "post");
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Map<String, String> b(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap hashMap = new HashMap(16);
        hashMap.put(MedalConstants.EVENT_KEY, hn.f(context));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("keywords", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("types", str2);
        }
        if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str6)) {
            hashMap.put("location", str6 + "," + str5);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put(DistrictSearchQuery.KEYWORDS_CITY, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put(TypedValues.CycleType.S_WAVE_OFFSET, str4);
        }
        if (!TextUtils.isEmpty(str7)) {
            hashMap.put("radius", str7);
        }
        return hashMap;
    }
}
