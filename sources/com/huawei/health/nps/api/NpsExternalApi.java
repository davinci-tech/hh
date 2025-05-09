package com.huawei.health.nps.api;

import android.content.Context;

/* loaded from: classes3.dex */
public interface NpsExternalApi {
    boolean isShowDeviceNps();

    boolean isShowNps();

    boolean isWeightDeviceNps();

    void sendNpsAfterRun();

    void showDeviceNpsPage(Context context);

    void showNpsPage(Context context);
}
