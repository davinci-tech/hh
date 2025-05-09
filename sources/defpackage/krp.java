package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hms.support.picker.common.AccountPickerUtil;

/* loaded from: classes5.dex */
public class krp {
    private Context b;
    private String c;
    private volatile SharedPreferences d;

    public krp(Context context, String str) {
        this.c = "";
        this.b = context.getApplicationContext();
        this.c = str;
        b();
    }

    private boolean b() {
        if (AccountPickerUtil.isPhoneStillInLockMode(this.b)) {
            ksy.b("HwIdCeSharedPreferences", "Phone Still In Lock Mode", true);
            return false;
        }
        this.d = this.b.getSharedPreferences(this.c, 0);
        boolean z = this.d == null;
        ksy.b("HwIdCeSharedPreferences", "createSharedPreferences: " + z, false);
        return z;
    }

    private boolean e() {
        if (this.b == null) {
            ksy.c("HwIdCeSharedPreferences", "mContext is null, can not use SharedPreferences.", true);
            return false;
        }
        if (this.d == null) {
            ksy.c("HwIdCeSharedPreferences", "mSharedPreferences is null, need init.", true);
            synchronized (krp.class) {
                if (this.d == null) {
                    return b();
                }
            }
        }
        return true;
    }

    public String a(String str, String str2) {
        ksy.b("HwIdCeSharedPreferences", "getString, key:" + str, false);
        return !e() ? str2 : this.d.getString(str, str2);
    }

    public boolean d(String str, String str2) {
        SharedPreferences.Editor edit;
        ksy.b("HwIdCeSharedPreferences", "setString, key:" + str, false);
        if (!e() || (edit = this.d.edit()) == null) {
            return false;
        }
        return edit.putString(str, str2).commit();
    }

    public long c(String str, long j) {
        ksy.b("HwIdCeSharedPreferences", "getLong, key:" + str, false);
        return !e() ? j : this.d.getLong(str, j);
    }

    public boolean b(String str, long j) {
        SharedPreferences.Editor edit;
        ksy.b("HwIdCeSharedPreferences", "saveLong, key:" + str, false);
        if (!e() || (edit = this.d.edit()) == null) {
            return false;
        }
        return edit.putLong(str, j).commit();
    }

    public int c(String str, int i) {
        ksy.b("HwIdCeSharedPreferences", "getLong, key:" + str, false);
        return !e() ? i : this.d.getInt(str, i);
    }

    public boolean c(String str, boolean z) {
        ksy.b("HwIdCeSharedPreferences", "getBoolean, key:" + str, false);
        return !e() ? z : this.d.getBoolean(str, z);
    }

    public boolean d(String str, boolean z) {
        SharedPreferences.Editor edit;
        ksy.b("HwIdCeSharedPreferences", "saveBoolean, key:" + str, false);
        if (!e() || (edit = this.d.edit()) == null) {
            return false;
        }
        return edit.putBoolean(str, z).commit();
    }

    public void e(String str) {
        SharedPreferences.Editor edit;
        ksy.b("HwIdCeSharedPreferences", "deleteKey, key:" + str, false);
        if (e() && (edit = this.d.edit()) != null) {
            edit.remove(str).commit();
        }
    }
}
