package com.amap.api.col.p0003sl;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.operation.utils.CloudParamKeys;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class ij {

    /* renamed from: a, reason: collision with root package name */
    public static int f1180a = 1;
    public static int b = 2;
    private String c;
    private int d;
    private long e = System.currentTimeMillis();
    private String f;

    private ij(int i, String str, String str2) {
        this.c = str2;
        this.d = i;
        this.f = str;
    }

    public static ij a(String str, String str2) {
        return new ij(f1180a, str, str2);
    }

    public static ij b(String str, String str2) {
        return new ij(b, str, str2);
    }

    public final int a() {
        return this.d;
    }

    public final String b() {
        new JSONObject();
        return this.c;
    }

    private String d() {
        return this.f;
    }

    public final String c() {
        return a(this.d);
    }

    public static String a(int i) {
        return i == b ? VastAttribute.ERROR : CloudParamKeys.INFO;
    }

    public static boolean a(ij ijVar) {
        return (ijVar == null || TextUtils.isEmpty(ijVar.b())) ? false : true;
    }

    private static String b(ij ijVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CloudParamKeys.INFO, ijVar.b());
            jSONObject.put("session", ijVar.d());
            jSONObject.put("timestamp", ijVar.e);
            return jSONObject.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(List<ij> list) {
        if (list == null) {
            return "";
        }
        try {
            if (list.size() == 0) {
                return "";
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<ij> it = list.iterator();
            while (it.hasNext()) {
                String b2 = b(it.next());
                if (!TextUtils.isEmpty(b2)) {
                    jSONArray.put(b2);
                }
            }
            return jSONArray.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
