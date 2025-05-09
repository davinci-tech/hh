package com.huawei.phoneservice.faq.base.util;

/* loaded from: classes5.dex */
public interface SdkListener {
    boolean haveSdkErr(String str);

    void onSdkErr(String str, String str2);

    void onSdkInit(int i, int i2, String str);
}
