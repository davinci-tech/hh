package com.huawei.hms.mlsdk.asr;

import android.os.Bundle;

/* loaded from: classes4.dex */
public interface MLAsrWriteAudioListener {
    void onError(int i, String str);

    void onResults(Bundle bundle);

    void onStartListening();
}
