package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class in {

    /* renamed from: a, reason: collision with root package name */
    private hz f1189a;

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        public static Map<String, in> f1190a = new HashMap();
    }

    public static in a(hz hzVar) {
        if (hzVar == null || TextUtils.isEmpty(hzVar.a())) {
            return null;
        }
        if (a.f1190a.get(hzVar.a()) == null) {
            a.f1190a.put(hzVar.a(), new in(hzVar));
        }
        return a.f1190a.get(hzVar.a());
    }

    private in(hz hzVar) {
        this.f1189a = hzVar;
    }

    public final String a(Context context, String str, String str2, String str3) {
        hz hzVar;
        if (context == null || (hzVar = this.f1189a) == null || TextUtils.isEmpty(hzVar.a())) {
            return null;
        }
        List<b> a2 = b.a(a(context, this.f1189a.a(), str3));
        if (a2.size() == 0) {
            return "";
        }
        for (int i = 0; i < a2.size(); i++) {
            b bVar = a2.get(i);
            if (bVar.a(str, str2)) {
                return bVar.c;
            }
        }
        return null;
    }

    public final void a(Context context, String str, String str2, String str3, String str4) {
        hz hzVar;
        if (context == null || (hzVar = this.f1189a) == null || TextUtils.isEmpty(hzVar.a())) {
            return;
        }
        List<b> a2 = b.a(a(context, this.f1189a.a(), str3));
        for (int i = 0; i < a2.size(); i++) {
            b bVar = a2.get(i);
            if (bVar.a(str, str2)) {
                bVar.c = str4;
                b(context, this.f1189a.a(), str3, b.a(a2).toString());
                return;
            }
        }
        a2.add(new b(str, str2, str4));
        b(context, this.f1189a.a(), str3, b.a(a2).toString());
    }

    private static void b(Context context, String str, String str2, String str3) {
        if (str3 == null || TextUtils.isEmpty(str)) {
            return;
        }
        c(context, "C7ADB20F22F238708BA5EE26D0401DB9" + hv.b(str), "ik".concat(String.valueOf(str2)), str3);
    }

    private static String a(Context context, String str, String str2) {
        return b(context, "C7ADB20F22F238708BA5EE26D0401DB9" + hv.b(str), "ik".concat(String.valueOf(str2)));
    }

    private static void c(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return;
        }
        String g = ia.g(hl.a(ia.a(str3)));
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, g);
        edit.commit();
    }

    private static String b(Context context, String str, String str2) {
        return (context == null || TextUtils.isEmpty(str2)) ? "" : ia.a(hl.b(ia.d(context.getSharedPreferences(str, 0).getString(str2, ""))));
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        private String f1191a;
        private String b;
        private String c;

        public b(String str, String str2, String str3) {
            this.f1191a = str;
            this.b = str2;
            this.c = str3;
        }

        public final boolean a(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                str = this.f1191a;
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = this.b;
            }
            return this.f1191a.equals(str) && this.b.equals(str2);
        }

        private JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("sdkVersion", this.f1191a);
                jSONObject.put("cpuType", this.b);
                jSONObject.put("content", this.c);
                return jSONObject;
            } catch (Throwable unused) {
                return new JSONObject();
            }
        }

        public static JSONArray a(List<b> list) {
            if (list == null) {
                return new JSONArray();
            }
            JSONArray jSONArray = new JSONArray();
            for (b bVar : list) {
                if (bVar != null && bVar != null && !TextUtils.isEmpty(bVar.c)) {
                    jSONArray.put(bVar.a());
                }
            }
            return jSONArray;
        }

        private static b a(JSONObject jSONObject) {
            try {
                return new b(jSONObject.optString("sdkVersion"), jSONObject.optString("cpuType"), jSONObject.optString("content"));
            } catch (Throwable unused) {
                return null;
            }
        }

        public static List<b> a(String str) {
            if (TextUtils.isEmpty(str)) {
                return new ArrayList();
            }
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(a(jSONArray.getJSONObject(i)));
                }
                return arrayList;
            } catch (Throwable unused) {
                return new ArrayList();
            }
        }
    }
}
