package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class aj {
    public static boolean d() {
        return ck.a("com.hihonor.common.grs.HihonorGrsApp", "getIssueCountryCode", (Class<?>[]) new Class[]{Context.class});
    }

    public static boolean c() {
        return ck.a("com.huawei.hms.framework.network.grs.GrsApp", "getIssueCountryCode", (Class<?>[]) new Class[]{Context.class});
    }

    public static boolean b() {
        try {
            Class.forName("com.hihonor.common.grs.HihonorGrsApi");
            return true;
        } catch (Throwable unused) {
            ho.c("GrsUtil", "check honor grs available error");
            return false;
        }
    }

    public static boolean a() {
        try {
            Class.forName("com.huawei.hms.framework.network.grs.GrsApi");
            return true;
        } catch (Throwable unused) {
            ho.c("GrsUtil", "check grs available error");
            return false;
        }
    }
}
