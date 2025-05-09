package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.wear.oversea.account.ProcessDataSyncHelper;

/* loaded from: classes7.dex */
public final class suj {
    private static volatile suj c;
    private static final byte[] e = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences f17237a;
    private Context b;
    private String d = "NFC_Properties";

    private suj(Context context) {
        this.f17237a = null;
        this.b = context;
        this.f17237a = context.getSharedPreferences("NFC_Properties", 0);
    }

    public static suj b(Context context) {
        if (c == null) {
            synchronized (e) {
                if (c == null) {
                    c = new suj(context.getApplicationContext());
                }
            }
        }
        return c;
    }

    public String e(String str, String str2) {
        return this.f17237a.getString(str, str2);
    }

    public int d(String str, int i) {
        return this.f17237a.getInt(str, i);
    }

    public Long d(String str, Long l) {
        return Long.valueOf(this.f17237a.getLong(str, l.longValue()));
    }

    public boolean a(String str, Long l) {
        ProcessDataSyncHelper.d(this.b).c(this.b, this.d, str, l);
        return this.f17237a.edit().putLong(str, l.longValue()).commit();
    }

    public boolean a(String str, String str2) {
        ProcessDataSyncHelper.d(this.b).c(this.b, this.d, str, str2);
        return this.f17237a.edit().putString(str, str2).commit();
    }

    public boolean c(String str, int i) {
        ProcessDataSyncHelper.d(this.b).c(this.b, this.d, str, Integer.valueOf(i));
        return this.f17237a.edit().putInt(str, i).commit();
    }

    public boolean a(String str) {
        return this.f17237a.contains(str);
    }

    public boolean e(String str) {
        ProcessDataSyncHelper.d(this.b).e(this.b, this.d, str);
        return this.f17237a.edit().remove(str).commit();
    }
}
