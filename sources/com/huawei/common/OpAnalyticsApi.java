package com.huawei.common;

import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public interface OpAnalyticsApi {
    void onReport(String str, String str2);

    void reportErrorCode(String str, LinkedHashMap<String, String> linkedHashMap);
}
