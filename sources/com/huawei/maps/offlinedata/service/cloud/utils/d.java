package com.huawei.maps.offlinedata.service.cloud.utils;

import com.amap.api.col.p0003sl.it;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes5.dex */
public class d {
    public static String a(String str, String str2) {
        String str3 = it.k + f.b().replaceAll("[^(0-9)]", "") + a(str2, 9, '0') + str + new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH).format(new Date()) + String.format(Locale.ROOT, "%04d", Integer.valueOf(e.a().nextInt(10000)));
        return str3.length() <= 64 ? str3 : str3.replace(str, str.substring(str3.length() - 64));
    }

    private static String a(String str, int i, char c) {
        int length = str.length();
        if (length == i) {
            return str;
        }
        if (length > i) {
            return str.substring(length - i);
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (length < i) {
            stringBuffer.append(c);
            length++;
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
