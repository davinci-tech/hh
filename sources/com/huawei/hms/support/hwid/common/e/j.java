package com.huawei.hms.support.hwid.common.e;

import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public class j {
    public static String a(String str, String[] strArr) {
        return Pattern.compile(strArr[0]).matcher(str).replaceAll(strArr[1]);
    }

    public static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            g.c("StringUtil", "paseInt error " + e.getClass().getSimpleName(), true);
            return -1;
        }
    }
}
