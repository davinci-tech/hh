package defpackage;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/* loaded from: classes7.dex */
public class mb {
    public static String e;

    public static void c(lv lvVar, Context context, String str, String str2) {
        synchronized (mb.class) {
            try {
                String e2 = lb.e(d(context), str2, str);
                if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(e2)) {
                    kl.c(lvVar, "cp", "TriDesDecryptError", String.format("%s,%s", str, str2));
                }
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, e2).apply();
            } catch (Throwable th) {
                ma.c(th);
            }
        }
    }

    public static String d(lv lvVar, Context context, String str, String str2) {
        String str3;
        synchronized (mb.class) {
            try {
                String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
                str3 = TextUtils.isEmpty(string) ? null : lb.c(d(context), string, str);
                if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(str3)) {
                    kl.c(lvVar, "cp", "TriDesEncryptError", String.format("%s,%s", str, string));
                }
            } catch (Exception e2) {
                ma.c(e2);
            }
        }
        return str3;
    }

    public static String d(Context context) {
        String str;
        if (TextUtils.isEmpty(e)) {
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                ma.c(th);
                str = "";
            }
            e = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return e;
    }
}
