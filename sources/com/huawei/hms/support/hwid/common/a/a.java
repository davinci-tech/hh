package com.huawei.hms.support.hwid.common.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.support.hwid.common.e.g;
import java.util.UUID;

/* loaded from: classes9.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static String f5997a = "";

    public static String a() {
        String str = Build.MODEL;
        return TextUtils.isEmpty(str) ? "unknown" : str;
    }

    public static String b() {
        if (TextUtils.isEmpty(f5997a)) {
            String uuid = UUID.randomUUID().toString();
            f5997a = uuid;
            if (TextUtils.isEmpty(uuid)) {
                return "NULL";
            }
        }
        return f5997a;
    }

    public static String a(Context context) {
        g.a("TerminalInfo", "generateUuid start.", true);
        String b = com.huawei.hms.support.hwid.common.d.a.a(context).b("UUID", "");
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        g.a("TerminalInfo", "uuid is null.", true);
        String b2 = b();
        com.huawei.hms.support.hwid.common.d.a.a(context).c("UUID", b2);
        return b2;
    }
}
