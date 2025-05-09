package defpackage;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.secure.android.common.util.SafeString;
import java.util.UUID;

/* loaded from: classes5.dex */
public class kti {
    private static String d = "";

    public static String b() {
        String str = Build.MODEL;
        return TextUtils.isEmpty(str) ? "unknown" : str;
    }

    public static String c(Context context) {
        String str;
        try {
            str = Settings.Global.getString(context.getContentResolver(), "unified_device_name");
        } catch (Throwable th) {
            ksy.d("TerminalInfo", "Settings.Global Exception = " + th.getClass().getSimpleName(), true);
            str = null;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
            return str;
        }
        String o = ksi.o();
        if (!TextUtils.isEmpty(o)) {
            return o;
        }
        String str2 = Build.MODEL;
        return TextUtils.isEmpty(str2) ? "unknown" : str2;
    }

    public static String a() {
        if (TextUtils.isEmpty(d)) {
            String uuid = UUID.randomUUID().toString();
            d = uuid;
            if (TextUtils.isEmpty(uuid)) {
                return "NULL";
            }
        }
        return d;
    }

    public static String b(Context context) {
        ksy.b("TerminalInfo", "generateUuid start.", true);
        String a2 = krn.c(context).a("UUID", "");
        if (TextUtils.isEmpty(a2)) {
            ksy.b("TerminalInfo", "deviceId is null.", true);
            return a(context);
        }
        if (!"{{001}}".equalsIgnoreCase(SafeString.substring(a2, 0, 7))) {
            return a(context);
        }
        return SafeString.substring(a2, 7);
    }

    private static String a(Context context) {
        String str;
        String a2 = a();
        if ("NULL".equalsIgnoreCase(a2)) {
            str = a2;
        } else {
            str = "{{001}}" + a2;
        }
        krn.c(context).d("UUID", str);
        return a2;
    }
}
