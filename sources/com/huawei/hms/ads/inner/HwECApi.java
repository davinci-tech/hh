package com.huawei.hms.ads.inner;

import android.content.Context;
import com.huawei.openalliance.ad.inter.a;

/* loaded from: classes9.dex */
public class HwECApi {
    public static void setECCallback(IECCallback iECCallback) {
        a.a().a(iECCallback);
    }

    public static void setAccessToken(String str) {
        a.a().a(str);
    }

    public static boolean openLandingPage(Context context, String str) {
        return a.a().a(context, str);
    }
}
