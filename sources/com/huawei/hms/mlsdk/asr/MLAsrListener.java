package com.huawei.hms.mlsdk.asr;

import android.os.Bundle;

/* loaded from: classes9.dex */
public interface MLAsrListener {
    void onError(int i, String str);

    void onRecognizingResults(Bundle bundle);

    void onResults(Bundle bundle);

    void onStartListening();

    void onStartingOfSpeech();

    void onState(int i, Bundle bundle);

    void onVoiceDataReceived(byte[] bArr, float f, Bundle bundle);
}
