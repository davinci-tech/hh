package com.autonavi.aps.amapapi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.ho;
import com.amap.api.col.p0003sl.hz;
import com.amap.api.col.p0003sl.ia;
import com.amap.api.col.p0003sl.iv;
import com.amap.api.col.p0003sl.kh;
import com.amap.api.col.p0003sl.kj;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a {
    private static volatile boolean h = false;
    private static boolean i = true;
    private static int j = 1000;
    private static int k = 200;
    private static boolean l = false;
    private static int m = 20;
    private static int n = 0;
    private static volatile int o = 0;
    private static boolean p = true;
    private static boolean q = false;
    private static int r = -1;
    private static long s;
    private static ArrayList<String> t = new ArrayList<>();
    private static ArrayList<String> u = new ArrayList<>();
    private static volatile boolean v = false;
    private static boolean w = true;
    private static long x = 300000;
    private static boolean y = false;
    private static double z = 0.618d;
    private static boolean A = true;
    private static int B = 80;

    /* renamed from: a, reason: collision with root package name */
    static long f1645a = 3600000;
    private static boolean C = false;
    private static boolean D = true;
    private static boolean E = false;
    public static volatile long b = 0;
    static boolean c = true;
    private static boolean F = true;
    private static long G = -1;
    private static boolean H = true;
    private static int I = 1;
    private static boolean J = false;
    private static int K = 5;
    private static boolean L = false;
    private static String M = "CMjAzLjEwNy4xLjEvMTU0MDgxL2Q";
    private static long N = 0;
    public static boolean d = false;
    public static boolean e = false;
    public static int f = 20480;
    public static int g = 10800000;

    public static void a(final Context context) {
        if (h) {
            return;
        }
        h = true;
        ho.a(context, b.c(), b.d(), new ho.a() { // from class: com.autonavi.aps.amapapi.utils.a.1
            @Override // com.amap.api.col.3sl.ho.a
            public final void a(ho.b bVar) {
                a.a(context, bVar);
            }
        });
    }

    public static boolean a() {
        return i;
    }

    public static int b() {
        return k;
    }

    public static int c() {
        if (o < 0) {
            o = 0;
        }
        return o;
    }

    private static void a(ho.b bVar, SharedPreferences.Editor editor) {
        try {
            ho.b.a aVar = bVar.g;
            if (aVar != null) {
                boolean z2 = aVar.f1136a;
                i = z2;
                h.a(editor, TrackConstants$Events.EXCEPTION, z2);
                JSONObject jSONObject = aVar.c;
                if (jSONObject != null) {
                    j = jSONObject.optInt("fn", j);
                    int optInt = jSONObject.optInt("mpn", k);
                    k = optInt;
                    if (optInt > 500) {
                        k = 500;
                    }
                    if (k < 30) {
                        k = 30;
                    }
                    l = ho.a(jSONObject.optString("igu"), false);
                    m = jSONObject.optInt("ms", m);
                    o = jSONObject.optInt("rot", 0);
                    n = jSONObject.optInt("pms", 0);
                }
                kh.a(j, l, m, n);
                kj.a(l, n);
                h.a(editor, "fn", j);
                h.a(editor, "mpn", k);
                h.a(editor, "igu", l);
                h.a(editor, "ms", m);
                h.a(editor, "rot", o);
                h.a(editor, "pms", n);
            }
        } catch (Throwable th) {
            b.a(th, "AuthUtil", "loadConfigDataUploadException");
        }
    }

    private static void a(JSONObject jSONObject, SharedPreferences.Editor editor) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("11G");
            if (optJSONObject != null) {
                boolean a2 = ho.a(optJSONObject.optString("able"), true);
                w = a2;
                if (a2) {
                    x = optJSONObject.optInt("c", 300) * 1000;
                }
                y = ho.a(optJSONObject.optString("fa"), false);
                z = Math.min(1.0d, Math.max(0.2d, optJSONObject.optDouble("ms", 0.618d)));
                h.a(editor, "ca", w);
                h.a(editor, "ct", x);
                h.a(editor, "11G_fa", y);
                h.a(editor, "11G_ms", String.valueOf(z));
            }
        } catch (Throwable th) {
            b.a(th, "AuthUtil", "loadConfigDataCacheAble");
        }
    }

    static boolean a(Context context, ho.b bVar) {
        SharedPreferences.Editor editor;
        try {
            editor = h.a(context, "pref");
        } catch (Throwable unused) {
            editor = null;
        }
        try {
            a(bVar, editor);
            c(context);
            JSONObject jSONObject = bVar.f;
            if (jSONObject == null) {
                if (editor != null) {
                    try {
                        h.a(editor);
                    } catch (Throwable unused2) {
                    }
                }
                return true;
            }
            a(context, jSONObject, editor);
            a(jSONObject, editor);
            c(jSONObject, editor);
            e(jSONObject, editor);
            g(jSONObject, editor);
            f(jSONObject, editor);
            h(jSONObject, editor);
            b(jSONObject, editor);
            if (editor != null) {
                try {
                    h.a(editor);
                } catch (Throwable unused3) {
                }
            }
            return true;
        } catch (Throwable unused4) {
            if (editor == null) {
                return false;
            }
            try {
                h.a(editor);
                return false;
            } catch (Throwable unused5) {
                return false;
            }
        }
    }

    private static void b(JSONObject jSONObject, SharedPreferences.Editor editor) {
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("197");
            if (jSONObject2 != null) {
                boolean a2 = ho.a(jSONObject2.optString("able"), false);
                h.a(editor, "197a", a2);
                if (a2) {
                    h.a(editor, "197dv", jSONObject2.optString("sv", ""));
                    h.a(editor, "197tv", jSONObject2.optString("tv", ""));
                } else {
                    h.a(editor, "197dv", "");
                    h.a(editor, "197tv", "");
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void b(Context context) {
        if (v) {
            return;
        }
        v = true;
        try {
            i = h.a(context, "pref", TrackConstants$Events.EXCEPTION, i);
            c(context);
        } catch (Throwable th) {
            b.a(th, "AuthUtil", "loadLastAbleState p1");
        }
        try {
            j = h.a(context, "pref", "fn", j);
            k = h.a(context, "pref", "mpn", k);
            l = h.a(context, "pref", "igu", l);
            m = h.a(context, "pref", "ms", m);
            o = h.a(context, "pref", "rot", 0);
            int a2 = h.a(context, "pref", "pms", 0);
            n = a2;
            kh.a(j, l, m, a2);
            kj.a(l, n);
        } catch (Throwable th2) {
            b.a(th2, "AuthUtil", "loadLastAbleState p2");
        }
        try {
            w = h.a(context, "pref", "ca", w);
            x = h.a(context, "pref", "ct", x);
            y = h.a(context, "pref", "11G_fa", y);
            double doubleValue = Double.valueOf(h.a(context, "pref", "11G_ms", String.valueOf(z))).doubleValue();
            z = doubleValue;
            z = Math.max(0.2d, doubleValue);
        } catch (Throwable th3) {
            b.a(th3, "AuthUtil", "loadLastAbleState p3");
        }
        try {
            c = h.a(context, "pref", "fr", c);
        } catch (Throwable th4) {
            b.a(th4, "AuthUtil", "loadLastAbleState p4");
        }
        try {
            F = h.a(context, "pref", "asw", F);
        } catch (Throwable th5) {
            b.a(th5, "AuthUtil", "loadLastAbleState p5");
        }
        try {
            G = h.a(context, "pref", "awsi", G);
        } catch (Throwable th6) {
            b.a(th6, "AuthUtil", "loadLastAbleState p6");
        }
        try {
            H = h.a(context, "pref", "15ua", H);
            I = h.a(context, "pref", "15un", I);
            N = h.a(context, "pref", "15ust", N);
        } catch (Throwable th7) {
            b.a(th7, "AuthUtil", "loadLastAbleState p7");
        }
        try {
            J = h.a(context, "pref", "ok9", J);
            K = h.a(context, "pref", "ok10", K);
            M = h.a(context, "pref", "ok11", M);
        } catch (Throwable th8) {
            b.a(th8, "AuthUtil", "loadLastAbleState p8");
        }
        try {
            d = h.a(context, "pref", "17ya", false);
            e = h.a(context, "pref", "17ym", false);
            g = h.a(context, "pref", "17yi", 2) * 3600000;
            f = h.a(context, "pref", "17yx", 100) * 1024;
        } catch (Throwable th9) {
            b.a(th9, "AuthUtil", "loadLastAbleState p9");
        }
        try {
            b = i.b();
            f1645a = h.a(context, "pref", "13S_at", f1645a);
            D = h.a(context, "pref", "13S_nla", D);
            A = h.a(context, "pref", "13J_able", A);
            B = h.a(context, "pref", "13J_c", B);
        } catch (Throwable th10) {
            b.a(th10, "AuthUtil", "loadLastAbleState p10");
        }
        ho.b(context);
        try {
            String a3 = h.a(context, "pref", "13S_mlpl", (String) null);
            if (!TextUtils.isEmpty(a3)) {
                E = a(context, new JSONArray(ia.c(a3)));
            }
        } catch (Throwable th11) {
            b.a(th11, "AuthUtil", "loadLastAbleState p11");
        }
        try {
            boolean a4 = h.a(context, "pref", "197a", false);
            String a5 = h.a(context, "pref", "197dv", "");
            String a6 = h.a(context, "pref", "197tv", "");
            if (a4 && b.f1647a.equals(a5)) {
                for (String str : b.b) {
                    if (str.equals(a6)) {
                        b.f1647a = a6;
                    }
                }
            }
        } catch (Throwable th12) {
            b.a(th12, "AuthUtil", "loadLastAbleState p12");
        }
    }

    public static long d() {
        return x;
    }

    public static boolean e() {
        return w;
    }

    public static boolean a(long j2) {
        if (!w) {
            return false;
        }
        long a2 = i.a();
        long j3 = x;
        return j3 < 0 || a2 - j2 < j3;
    }

    public static boolean f() {
        return y;
    }

    public static double g() {
        return z;
    }

    public static void c(Context context) {
        try {
            hz c2 = b.c();
            c2.a(i);
            iv.a(context, c2);
        } catch (Throwable unused) {
        }
    }

    public static boolean h() {
        return A;
    }

    public static int i() {
        return B;
    }

    private static void c(JSONObject jSONObject, SharedPreferences.Editor editor) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("13J");
            if (optJSONObject != null) {
                boolean a2 = ho.a(optJSONObject.optString("able"), true);
                A = a2;
                if (a2) {
                    B = optJSONObject.optInt("c", B);
                }
                h.a(editor, "13J_able", A);
                h.a(editor, "13J_c", B);
            }
        } catch (Throwable th) {
            b.a(th, "AuthUtil", "loadConfigDataGpsGeoAble");
        }
    }

    public static boolean j() {
        return D;
    }

    public static boolean k() {
        return E;
    }

    private static void a(Context context, JSONObject jSONObject, SharedPreferences.Editor editor) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("13S");
            if (optJSONObject != null) {
                try {
                    long optInt = optJSONObject.optInt("at", 123) * 60000;
                    f1645a = optInt;
                    h.a(editor, "13S_at", optInt);
                } catch (Throwable th) {
                    b.a(th, "AuthUtil", "requestSdkAuthInterval");
                }
                d(optJSONObject, editor);
                try {
                    boolean a2 = ho.a(optJSONObject.optString("nla"), true);
                    D = a2;
                    h.a(editor, "13S_nla", a2);
                } catch (Throwable unused) {
                }
                try {
                    boolean a3 = ho.a(optJSONObject.optString("asw"), true);
                    F = a3;
                    h.a(editor, "asw", a3);
                } catch (Throwable unused2) {
                }
                try {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("mlpl");
                    if (optJSONArray != null && optJSONArray.length() > 0 && context != null) {
                        h.a(editor, "13S_mlpl", ia.b(optJSONArray.toString()));
                        E = a(context, optJSONArray);
                    } else {
                        E = false;
                        h.a(editor, "13S_mlpl");
                    }
                } catch (Throwable unused3) {
                }
            }
        } catch (Throwable th2) {
            b.a(th2, "AuthUtil", "loadConfigAbleStatus");
        }
    }

    private static boolean a(Context context, JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0 && context != null) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        if (i.b(context, jSONArray.getString(i2))) {
                            return true;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static boolean l() {
        return c;
    }

    private static void d(JSONObject jSONObject, SharedPreferences.Editor editor) {
        if (jSONObject == null) {
            return;
        }
        try {
            boolean a2 = ho.a(jSONObject.optString("re"), true);
            c = a2;
            h.a(editor, "fr", a2);
        } catch (Throwable th) {
            b.a(th, "AuthUtil", "checkReLocationAble");
        }
    }

    public static boolean m() {
        return F;
    }

    public static long n() {
        return G;
    }

    private static void e(JSONObject jSONObject, SharedPreferences.Editor editor) {
        JSONArray optJSONArray;
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("15O");
            if (optJSONObject != null) {
                if (ho.a(optJSONObject.optString("able"), false) && ((optJSONArray = optJSONObject.optJSONArray("fl")) == null || optJSONArray.length() <= 0 || optJSONArray.toString().contains(Build.MANUFACTURER))) {
                    G = optJSONObject.optInt("iv", 30) * 1000;
                } else {
                    G = -1L;
                }
                h.a(editor, "awsi", G);
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean o() {
        return L;
    }

    public static boolean p() {
        return J;
    }

    public static String q() {
        return ia.c(M);
    }

    private static void f(JSONObject jSONObject, SharedPreferences.Editor editor) {
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("17Y");
            if (jSONObject2 != null) {
                boolean a2 = ho.a(jSONObject2.optString("able"), false);
                d = a2;
                h.a(editor, "17ya", a2);
                boolean a3 = ho.a(jSONObject2.optString("mup"), false);
                e = a3;
                h.a(editor, "17ym", a3);
                int optInt = jSONObject2.optInt("max", 20);
                if (optInt > 0) {
                    h.a(editor, "17yx", optInt);
                    f = optInt * 1024;
                }
                int optInt2 = jSONObject2.optInt("inv", 3);
                if (optInt2 > 0) {
                    h.a(editor, "17yi", optInt2);
                    g = optInt2 * 3600000;
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void g(JSONObject jSONObject, SharedPreferences.Editor editor) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("15U");
            if (optJSONObject != null) {
                boolean a2 = ho.a(optJSONObject.optString("able"), true);
                int optInt = optJSONObject.optInt("yn", I);
                N = optJSONObject.optLong("sysTime", N);
                h.a(editor, "15ua", a2);
                h.a(editor, "15un", optInt);
                h.a(editor, "15ust", N);
            }
        } catch (Throwable unused) {
        }
    }

    private static void h(JSONObject jSONObject, SharedPreferences.Editor editor) {
        int parseInt;
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("17J");
            if (optJSONObject != null) {
                boolean a2 = ho.a(optJSONObject.optString("able"), false);
                J = a2;
                h.a(editor, "ok9", a2);
                if (a2) {
                    String optString = optJSONObject.optString("auth");
                    String optString2 = optJSONObject.optString("ht");
                    M = optString2;
                    h.a(editor, "ok11", optString2);
                    ho.a(optString, false);
                    L = ho.a(optJSONObject.optString("nr"), false);
                    String optString3 = optJSONObject.optString("tm");
                    if (TextUtils.isEmpty(optString3) || (parseInt = Integer.parseInt(optString3)) <= 0 || parseInt >= 20) {
                        return;
                    }
                    K = parseInt;
                    h.a(editor, "ok10", parseInt);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean r() {
        return H && I > 0;
    }

    public static int s() {
        return I;
    }

    public static long t() {
        return N;
    }
}
