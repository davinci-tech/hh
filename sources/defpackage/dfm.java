package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes3.dex */
public class dfm {
    private String b;
    private Context e;

    public dfm(Context context, String str) {
        this.b = str;
        this.e = context;
    }

    public String a(String str) {
        Context context = this.e;
        if (context == null) {
            return "0";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 0);
        return !sharedPreferences.contains(str) ? "0" : sharedPreferences.getString(str, "0");
    }

    public boolean d(String str) {
        Context context = this.e;
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(this.b, 0);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getBoolean(str, false);
        }
        return false;
    }
}
