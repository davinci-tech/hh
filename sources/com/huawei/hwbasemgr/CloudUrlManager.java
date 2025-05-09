package com.huawei.hwbasemgr;

/* loaded from: classes.dex */
public interface CloudUrlManager {
    String getCommonCountryCode();

    String getCountryCode();

    String getMccCountryCode();

    String getNoCheckUrl(String str, String str2);

    String getNoCheckUrl(String str, String str2, String str3);

    String getUrl(String str);
}
