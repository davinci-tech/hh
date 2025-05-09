package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.beans.inner.CountryCodeBean;
import com.huawei.openalliance.ad.constant.CountryConfig;
import com.huawei.openalliance.ad.en;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class db {
    public static boolean a(Context context) {
        String a2 = new CountryCodeBean(context).a();
        int al = fh.b(context).al();
        if (CountryConfig.isDR3(a2, null) || al <= 0 || "US".equalsIgnoreCase(a2)) {
            ho.b("SyncAppDataUtil", "not allow this area or interval <= 0");
            return false;
        }
        long a3 = en.a(context).a();
        long j = al * 60000;
        boolean z = ao.c() - a3 > j;
        ho.b("SyncAppDataUtil", "lastSyncTime is : %s, syncInterval is : %s", Long.valueOf(a3), Long.valueOf(j));
        return z;
    }
}
