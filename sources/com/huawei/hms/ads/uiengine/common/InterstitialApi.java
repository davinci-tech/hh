package com.huawei.hms.ads.uiengine.common;

import android.os.Bundle;
import android.view.View;

/* loaded from: classes4.dex */
public interface InterstitialApi {
    Bundle callMethod(String str, Bundle bundle);

    void onAnalysis(String str, Bundle bundle);

    boolean processWhyEventUnified();

    void registerBtn(View view, Bundle bundle);

    void reportEvent(String str, Bundle bundle);

    void setIsVideoCompleted(boolean z);

    void updateShowId(long j);
}
