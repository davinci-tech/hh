package defpackage;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.wear.oversea.util.SystemPropertyValues;
import com.huawei.wisesecurity.kfs.log.ILogKfs;
import java.util.Locale;

/* loaded from: classes7.dex */
class ttt {
    private static int e = -1;
    private boolean c = true;
    private final ILogKfs d;

    ttt(ILogKfs iLogKfs) {
        this.d = iLogKfs;
    }

    private static void e(int i) {
        e = i;
    }

    public void b() {
        this.c = false;
    }

    boolean b(Context context) {
        if (!this.c) {
            this.d.i("BIChecker", "oobe check is off, report is on");
            return false;
        }
        if (!h()) {
            this.d.i("BIChecker", "not huawei device, report is on");
            return false;
        }
        if (context == null) {
            return true;
        }
        if (d()) {
            return e == 0;
        }
        this.d.i("BIChecker", "not ChinaROM");
        try {
            e(Settings.Secure.getInt(context.getContentResolver(), "hw_app_analytics_state"));
            this.d.i("BIChecker", "hw_app_analytics_state value is " + e);
            return e != 1;
        } catch (Settings.SettingNotFoundException unused) {
            this.d.i("BIChecker", "Get OOBE failed");
            return true;
        }
    }

    private boolean d() {
        String e2 = e();
        if (!TextUtils.isEmpty(e2)) {
            return "cn".equalsIgnoreCase(e2);
        }
        String a2 = a();
        if (!TextUtils.isEmpty(a2)) {
            return a2.toLowerCase(Locale.US).contains("cn");
        }
        String c = c();
        if (TextUtils.isEmpty(c)) {
            return false;
        }
        return "cn".equalsIgnoreCase(c);
    }

    private boolean h() {
        return "HUAWEI".equalsIgnoreCase(Build.MANUFACTURER);
    }

    private String e() {
        return ttx.a("ro.product.locale.region", "");
    }

    private String a() {
        return ttx.a(SystemPropertyValues.PROP_LOCALE_KEY, "");
    }

    private String c() {
        return Locale.getDefault().getCountry();
    }
}
