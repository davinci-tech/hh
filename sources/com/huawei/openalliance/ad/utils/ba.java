package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.constant.InsAppKey;
import com.huawei.openalliance.ad.ey;
import com.huawei.openalliance.ad.fw;
import com.huawei.openalliance.ad.ho;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class ba {
    public static void a(Context context, String str) {
        cg a2;
        List<String> arrayList;
        fw a3 = ey.a(context);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(InsAppKey.ENCODING_MODE);
            String optString2 = jSONObject.optString(InsAppKey.INS_APP_TYPES);
            String optString3 = jSONObject.optString(InsAppKey.INS_APP);
            long optLong = jSONObject.optLong(InsAppKey.LABEL_GEN_TIME);
            long d = a3.d();
            if (jSONObject.has(InsAppKey.LABEL_GEN_TIME) && optLong <= d) {
                ho.b("InsAppsUtil", "ins app label not update");
                return;
            }
            if (optString.equals(a3.b())) {
                optString3 = a(a3.a(), optString3);
                ho.a("InsAppsUtil", "merged ins app: %s", optString3);
            } else {
                ho.a("InsAppsUtil", "override ins app");
            }
            a3.a(optString3);
            if (!cz.b(optString)) {
                a3.b(optString);
            }
            if (cz.b(optString2)) {
                a2 = cg.a(context);
                arrayList = new ArrayList<>();
            } else {
                a2 = cg.a(context);
                arrayList = Arrays.asList(optString2.split(","));
            }
            a2.b(arrayList);
            if (jSONObject.has(InsAppKey.LABEL_GEN_TIME)) {
                a3.a(optLong);
            }
        } catch (Throwable th) {
            ho.c("InsAppsUtil", "parse ins app result fail: %s", th.getClass().getSimpleName());
        }
    }

    private static String a(String str, String str2) {
        List<String> a2 = cz.a(str2, ",");
        List<String> a3 = cz.a(str, ",");
        HashSet hashSet = new HashSet();
        if (!bg.a(a2)) {
            hashSet.addAll(a2);
        }
        if (!bg.a(a3)) {
            hashSet.addAll(a3);
        }
        return cz.a((Collection<String>) hashSet, ",");
    }

    public static int a(String str) {
        if (cz.b(str)) {
            return 180;
        }
        int a2 = cz.a(str, 180);
        if (a2 == -1) {
            return -1;
        }
        if (a2 < 0) {
            return 180;
        }
        return a2;
    }
}
