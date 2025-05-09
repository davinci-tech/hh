package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.sdk.m.u.c;
import com.huawei.operation.utils.Constants;
import java.util.Random;

/* loaded from: classes7.dex */
public class kv {
    public static volatile kv c;

    /* renamed from: a, reason: collision with root package name */
    public String f14620a = "sdk-and-lite";
    public String b;
    public String d;

    public kv() {
        String d = kg.d();
        if (kg.e()) {
            return;
        }
        this.f14620a += '_' + d;
    }

    public static String a() {
        Context d = lw.c().d();
        SharedPreferences sharedPreferences = d.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imei", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String e = TextUtils.isEmpty(lu.b(d).a()) ? e() : c.b(d).b();
        sharedPreferences.edit().putString("virtual_imei", e).apply();
        return e;
    }

    public static kv c() {
        kv kvVar;
        synchronized (kv.class) {
            if (c == null) {
                c = new kv();
            }
            kvVar = c;
        }
        return kvVar;
    }

    public static String e() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    public static String f() {
        String c2;
        Context d = lw.c().d();
        SharedPreferences sharedPreferences = d.getSharedPreferences("virtualImeiAndImsi", 0);
        String string = sharedPreferences.getString("virtual_imsi", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        if (TextUtils.isEmpty(lu.b(d).a())) {
            String e = lw.c().e();
            c2 = (TextUtils.isEmpty(e) || e.length() < 18) ? e() : e.substring(3, 18);
        } else {
            c2 = c.b(d).c();
        }
        String str = c2;
        sharedPreferences.edit().putString("virtual_imsi", str).apply();
        return str;
    }

    public static void b(String str) {
        synchronized (kv.class) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            PreferenceManager.getDefaultSharedPreferences(lw.c().d()).edit().putString("trideskey", str).apply();
            kj.d = str;
        }
    }

    public static String c(Context context) {
        if (context == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder(Constants.LEFT_BRACKET_ONLY);
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            sb.append(packageName);
            sb.append(";");
            sb.append(packageInfo.versionCode);
            sb.append(Constants.RIGHT_BRACKET_ONLY);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        return Float.toString(new TextView(context).getTextSize());
    }

    public String e(lv lvVar, lu luVar, boolean z) {
        Context d = lw.c().d();
        c b = c.b(d);
        if (TextUtils.isEmpty(this.b)) {
            this.b = "Msp/15.8.14 (" + md.c() + ";" + md.e() + ";" + md.a(d) + ";" + md.d(d) + ";" + md.h(d) + ";" + b(d);
        }
        String b2 = c.c(d).b();
        String b3 = md.b(d);
        String b4 = b();
        String c2 = b.c();
        String b5 = b.b();
        String f = f();
        String a2 = a();
        if (luVar != null) {
            this.d = luVar.e();
        }
        String replace = Build.MANUFACTURER.replace(";", " ");
        String replace2 = Build.MODEL.replace(";", " ");
        boolean b6 = lw.b();
        String e = b.e();
        String j = j();
        String g = g();
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append(";");
        sb.append(b2);
        sb.append(";");
        sb.append(b3);
        sb.append(";");
        sb.append(b4);
        sb.append(";");
        sb.append(c2);
        sb.append(";");
        sb.append(b5);
        sb.append(";");
        sb.append(this.d);
        sb.append(";");
        sb.append(replace);
        sb.append(";");
        sb.append(replace2);
        sb.append(";");
        sb.append(b6);
        sb.append(";");
        sb.append(e);
        sb.append(";");
        sb.append(d());
        sb.append(";");
        sb.append(this.f14620a);
        sb.append(";");
        sb.append(f);
        sb.append(";");
        sb.append(a2);
        sb.append(";");
        sb.append(j);
        sb.append(";");
        sb.append(g);
        if (luVar != null) {
            String e2 = me.e(lvVar, d, lu.b(d).a(), me.d(lvVar, d));
            if (!TextUtils.isEmpty(e2)) {
                sb.append(";;;");
                sb.append(e2);
            }
        }
        sb.append(Constants.RIGHT_BRACKET_ONLY);
        return sb.toString();
    }

    public static String j() {
        return "-1";
    }

    public static String g() {
        return "00";
    }

    public static String b() {
        return "1";
    }

    public static String d() {
        return "-1;-1";
    }
}
