package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.wear.oversea.account.ProcessDataSyncHelper;

/* loaded from: classes7.dex */
public final class stc {
    private static final byte[] b = new byte[0];
    private static volatile stc d;

    /* renamed from: a, reason: collision with root package name */
    private String f17224a;
    private Context c;
    private SharedPreferences e;

    private stc(Context context) {
        this.e = null;
        this.c = context;
        if (context != null) {
            this.f17224a = "swipecard_wallet_account";
            this.e = context.getSharedPreferences("swipecard_wallet_account", 0);
        }
    }

    public static stc b(Context context) {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new stc(context.getApplicationContext());
                }
            }
        }
        return d;
    }

    public boolean d(String str, String str2) {
        if (this.e == null) {
            return false;
        }
        if ("user_id".equals(str)) {
            stq.e("AccountPref", "putString key is userid");
            return false;
        }
        if ("last_user_id".equals(str)) {
            stq.e("AccountPref", "putString key is userid");
            return false;
        }
        ProcessDataSyncHelper.d(this.c).c(this.c, this.f17224a, str, str2);
        return this.e.edit().putString(str, str2).commit();
    }

    public String c(String str, String str2) {
        if (this.e == null) {
            return str2;
        }
        if ("user_id".equals(str)) {
            stq.e("AccountPref", "getString key is userid");
            return "";
        }
        if ("last_user_id".equals(str)) {
            stq.e("AccountPref", "getString key is userid");
            return "";
        }
        return this.e.getString(str, str2);
    }
}
