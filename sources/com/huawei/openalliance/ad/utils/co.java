package com.huawei.openalliance.ad.utils;

import android.os.Build;
import com.huawei.openalliance.ad.ho;
import javax.net.ssl.SSLContext;

/* loaded from: classes5.dex */
public class co {
    public static SSLContext a() {
        String str;
        if (Build.VERSION.SDK_INT >= 29) {
            ho.a("SSLContextUtil", "use tls 1.3");
            str = "TLSv1.3";
        } else {
            ho.a("SSLContextUtil", "use tls 1.2");
            str = "TLSv1.2";
        }
        return SSLContext.getInstance(str);
    }
}
