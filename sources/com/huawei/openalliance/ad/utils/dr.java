package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class dr {
    public static boolean a(Context context) {
        if (!com.huawei.openalliance.ad.bz.a(context).l()) {
            ho.b("WifiUtils", "base location switch off");
            return false;
        }
        gc b = fh.b(context);
        if (!b.v()) {
            ho.b("WifiUtils", "collect devCntList switch off");
            return false;
        }
        if (fh.b(context).y() <= 0) {
            return false;
        }
        long x = b.x();
        if (System.currentTimeMillis() - b.bE() < 60000 * x) {
            ho.b("WifiUtils", "The reporting interval is less than %s min", Long.valueOf(x));
            return false;
        }
        b.h(System.currentTimeMillis());
        return true;
    }
}
