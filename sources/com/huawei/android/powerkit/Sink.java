package com.huawei.android.powerkit;

/* loaded from: classes2.dex */
public interface Sink {
    void onPowerBaseLineUsing(String str, String str2, long j, int i, String str3);

    void onPowerOverUsing(String str, int i, long j, long j2, String str2);

    void onStateChanged(int i, int i2, int i3, String str, int i4);
}
