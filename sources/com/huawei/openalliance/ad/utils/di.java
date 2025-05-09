package com.huawei.openalliance.ad.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public abstract class di {
    private static boolean a(String str, long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        return simpleDateFormat.format(date).equals(simpleDateFormat.format(new Date()));
    }

    public static boolean a(long j) {
        if (j == 0) {
            return false;
        }
        return a("yyyy-MM-dd", j);
    }

    public static String a() {
        return ao.a("yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date());
    }
}
