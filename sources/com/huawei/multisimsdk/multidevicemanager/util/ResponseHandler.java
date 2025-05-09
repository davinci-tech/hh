package com.huawei.multisimsdk.multidevicemanager.util;

/* loaded from: classes5.dex */
public interface ResponseHandler {
    default void onCallback(int i, String str) {
    }

    void onCallback(String str);
}
