package com.huawei.health.baseapi.hiaiengine;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes3.dex */
public interface HiAiEngineApi {
    void cancelSpeak();

    void createKit(Context context, Intent intent, String str, HiAiKitCreateListener hiAiKitCreateListener);

    HiAiFaceDetectorApi getHiAiFaceDetectorApi();

    HiAiIntelligentSleepApi getHiAiIntelligentSleepApi();

    HiAiSmartClipApi getHiAiSmartClip();

    String getLngAndLat(Context context);

    void initHiAiEngine();

    void initKitRecognizeEngine(String str, String str2, HiAiKitRecognizeListener hiAiKitRecognizeListener);

    void initKitTtsEngine(HiAiKitInitTtsListener hiAiKitInitTtsListener);

    void initServiceRoute();

    boolean isDestroyKit();

    boolean isSpeaking();

    void startRecognize(String str, String str2, String str3);

    void stopSpeak();

    void textToSpeak(String str, Intent intent);

    void updateSwitch(boolean z);

    void updateVoiceContext(String str);

    void writeAudio(byte[] bArr);
}
