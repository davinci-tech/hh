package com.huawei.health.baseapi.hiaiengine;

/* loaded from: classes3.dex */
public interface HiAiKitInitTtsListener {
    void onInit();

    void onTtsComplete(String str);

    void onTtsError(int i, String str);

    void onTtsStart(String str);
}
