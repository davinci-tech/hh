package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Random;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class lu {

    /* renamed from: a, reason: collision with root package name */
    public static Context f14865a;
    public static lu e;
    public long b;
    public String c;
    public String d;
    public boolean f = false;
    public String g;
    public String j;

    private void a(Context context) {
        if (context != null) {
            f14865a = context.getApplicationContext();
        }
        if (this.f) {
            return;
        }
        this.f = true;
        j();
    }

    public static lu b(Context context) {
        lu luVar;
        synchronized (lu.class) {
            if (e == null) {
                e = new lu();
            }
            if (f14865a == null) {
                e.a(context);
            }
            luVar = e;
        }
        return luVar;
    }

    private String d() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    private void g() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tid", this.d);
            jSONObject.put("client_key", this.c);
            jSONObject.put("timestamp", this.b);
            jSONObject.put("vimei", this.g);
            jSONObject.put("vimsi", this.j);
            a.a("alipay_tid_storage", "tidinfo", jSONObject.toString(), true);
        } catch (Exception e2) {
            ma.c(e2);
        }
    }

    private void h() {
        this.d = "";
        this.c = c();
        this.b = System.currentTimeMillis();
        this.g = d();
        this.j = d();
        a.e("alipay_tid_storage", "tidinfo");
    }

    private void i() {
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void j() {
        /*
            r9 = this;
            java.lang.String r0 = ""
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r2 = 0
            java.lang.String r3 = "alipay_tid_storage"
            java.lang.String r4 = "tidinfo"
            r5 = 1
            java.lang.String r3 = lu.a.a(r3, r4, r5)     // Catch: java.lang.Exception -> L53
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L53
            if (r4 != 0) goto L4f
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L53
            r4.<init>(r3)     // Catch: java.lang.Exception -> L53
            java.lang.String r3 = "tid"
            java.lang.String r3 = r4.optString(r3, r0)     // Catch: java.lang.Exception -> L53
            java.lang.String r5 = "client_key"
            java.lang.String r5 = r4.optString(r5, r0)     // Catch: java.lang.Exception -> L4d
            java.lang.String r6 = "timestamp"
            long r7 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L4a
            long r6 = r4.optLong(r6, r7)     // Catch: java.lang.Exception -> L4a
            java.lang.Long r1 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Exception -> L4a
            java.lang.String r6 = "vimei"
            java.lang.String r6 = r4.optString(r6, r0)     // Catch: java.lang.Exception -> L4a
            java.lang.String r7 = "vimsi"
            java.lang.String r2 = r4.optString(r7, r0)     // Catch: java.lang.Exception -> L48
            goto L5a
        L48:
            r0 = move-exception
            goto L57
        L4a:
            r0 = move-exception
            r6 = r2
            goto L57
        L4d:
            r0 = move-exception
            goto L55
        L4f:
            r0 = r2
            r5 = r0
            r6 = r5
            goto L5c
        L53:
            r0 = move-exception
            r3 = r2
        L55:
            r5 = r2
            r6 = r5
        L57:
            defpackage.ma.c(r0)
        L5a:
            r0 = r2
            r2 = r3
        L5c:
            java.lang.String r3 = "mspl"
            java.lang.String r4 = "tid_str: load"
            defpackage.ma.a(r3, r4)
            boolean r3 = r9.c(r2, r5, r6, r0)
            if (r3 == 0) goto L6d
            r9.h()
            goto L7b
        L6d:
            r9.d = r2
            r9.c = r5
            long r1 = r1.longValue()
            r9.b = r1
            r9.g = r6
            r9.j = r0
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lu.j():void");
    }

    public String a() {
        return this.d;
    }

    public String e() {
        return this.c;
    }

    public static class a {
        public static void e(String str, String str2) {
            if (lu.f14865a == null) {
                return;
            }
            lu.f14865a.getSharedPreferences(str, 0).edit().remove(str2).apply();
        }

        public static String a(String str, String str2, boolean z) {
            if (lu.f14865a == null) {
                return null;
            }
            String string = lu.f14865a.getSharedPreferences(str, 0).getString(str2, null);
            if (!TextUtils.isEmpty(string) && z) {
                string = lb.c(a(), string, string);
                if (TextUtils.isEmpty(string)) {
                    ma.a("mspl", "tid_str: pref failed");
                }
            }
            ma.a("mspl", "tid_str: from local");
            return string;
        }

        public static void a(String str, String str2, String str3, boolean z) {
            if (lu.f14865a == null) {
                return;
            }
            SharedPreferences sharedPreferences = lu.f14865a.getSharedPreferences(str, 0);
            if (z) {
                String a2 = a();
                String e = lb.e(a2, str3, str3);
                if (TextUtils.isEmpty(e)) {
                    String.format("LocalPreference::putLocalPreferences failed %s，%s", str3, a2);
                }
                str3 = e;
            }
            sharedPreferences.edit().putString(str2, str3).apply();
        }

        public static String a() {
            String str;
            try {
                str = lu.f14865a.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                ma.c(th);
                str = "";
            }
            return (str + "0000000000000000000000000000").substring(0, 24);
        }
    }

    private boolean c(String str, String str2, String str3, String str4) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4);
    }

    public String c() {
        String hexString = Long.toHexString(System.currentTimeMillis());
        return hexString.length() > 10 ? hexString.substring(hexString.length() - 10) : hexString;
    }

    public void d(String str, String str2) {
        ma.a("mspl", "tid_str: save");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.d = str;
        this.c = str2;
        this.b = System.currentTimeMillis();
        g();
        i();
    }
}
