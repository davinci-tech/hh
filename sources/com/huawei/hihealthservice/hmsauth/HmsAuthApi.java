package com.huawei.hihealthservice.hmsauth;

/* loaded from: classes.dex */
public interface HmsAuthApi {
    int hmsAuth(String str, int i, boolean z);

    boolean isValidRedirectUrl(String str, String str2, String str3);

    boolean isValidSignature(String str, String str2, String str3);
}
