package com.huawei.health.baseapi.hiaiengine;

/* loaded from: classes3.dex */
public interface HiAiKitRecognizeListener {
    void onError(int i, String str);

    void onInit();

    void onPartialResult(String str);

    void onResult(String str);

    void onSpeechEnd();

    void startRecord();
}
