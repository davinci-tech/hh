package com.huawei.health.pluginlocation.Utils;

import android.text.TextUtils;
import defpackage.eym;
import java.util.LinkedHashMap;

/* loaded from: classes8.dex */
public class BiReportImpl {
    private static final String TAG = "AAR,BiReportImpl";
    private static BiApi biApi;

    public BiReportImpl(BiApi biApi2) {
        biApi = biApi2;
    }

    public static void setEvent(String str, LinkedHashMap<String, String> linkedHashMap) {
        biApi.setEvent(str, linkedHashMap);
    }

    public static void setEventWithReportImmediately(String str, LinkedHashMap<String, String> linkedHashMap) {
        biApi.setEventWithReportImmediately(str, linkedHashMap);
    }

    public static void nativeSetEvent(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            eym.c(TAG, "nativeSetEvent data is null");
            return;
        }
        eym.a(TAG, "eventId is:" + str + ",data is:" + str2);
        String[] split = str2.split(";");
        if (split.length == 0) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str3 : split) {
            String[] split2 = str3.split(",");
            if (split2.length >= 2) {
                linkedHashMap.put(split2[0], split2[1]);
            }
        }
        setEvent(str, linkedHashMap);
    }
}
