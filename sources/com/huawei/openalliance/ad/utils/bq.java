package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class bq {

    /* renamed from: a, reason: collision with root package name */
    private static byte f7642a = 1;

    public static boolean c(Context context) {
        Object c;
        Object a2;
        bp a3 = com.huawei.openalliance.ad.cb.a(context).a();
        if (a3 instanceof br) {
            c = br.c();
        } else {
            c = bs.c();
        }
        boolean booleanValue = (c == null || (a2 = ck.a(c, "isMultiSimEnabled", (Class<?>[]) null, (Object[]) null)) == null || !(a2 instanceof Boolean)) ? false : ((Boolean) a2).booleanValue();
        ho.b("mutiCardFactory", "isHwGeminiSupport1 %s", String.valueOf(booleanValue));
        return booleanValue;
    }

    public static boolean b(Context context) {
        byte b = f7642a;
        if (b != 1) {
            if (b == 3 || b == 4) {
                return true;
            }
        } else {
            if (a()) {
                f7642a = (byte) 4;
                return true;
            }
            if (c(context)) {
                f7642a = (byte) 3;
                return true;
            }
            f7642a = (byte) 2;
        }
        return false;
    }

    private static boolean a() {
        String str;
        boolean z;
        try {
            z = ck.a(Class.forName("com.mediatek.common.featureoption.FeatureOption").getDeclaredField("MTK_GEMINI_SUPPORT"), true).getBoolean(null);
        } catch (Error unused) {
            str = "MTK NoClassDefFoundError";
            ho.c("mutiCardFactory", str);
            z = false;
            ho.b("mutiCardFactory", "isMtkGeminiSupport %s", String.valueOf(z));
            return z;
        } catch (Exception unused2) {
            str = "cannot find ext mtkapi";
            ho.c("mutiCardFactory", str);
            z = false;
            ho.b("mutiCardFactory", "isMtkGeminiSupport %s", String.valueOf(z));
            return z;
        }
        ho.b("mutiCardFactory", "isMtkGeminiSupport %s", String.valueOf(z));
        return z;
    }

    public static bp a(Context context) {
        b(context);
        return f7642a == 4 ? new bt() : com.huawei.openalliance.ad.cb.a(context).a();
    }
}
