package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes7.dex */
public class tyf {
    private static tyf b;
    private SharedPreferences c;

    public boolean c(String str, String str2) {
        try {
            this.c.edit().putString(str, str2).apply();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public String b(String str, String str2) {
        return this.c.getString(str, str2);
    }

    static class e {
        private static tyf c = new tyf();
    }

    public static tyf d(Context context) {
        if (context == null) {
            return null;
        }
        if (b == null) {
            tyf tyfVar = e.c;
            b = tyfVar;
            tyfVar.c = context.getSharedPreferences("com.zhangyue.iReader.sdk.scheme.SharedPreferences", 0);
        }
        return b;
    }

    private tyf() {
    }
}
