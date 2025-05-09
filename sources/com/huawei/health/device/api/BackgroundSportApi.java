package com.huawei.health.device.api;

import com.huawei.health.IBaseCommonCallback;
import com.huawei.hihealth.StartSportParam;

/* loaded from: classes3.dex */
public interface BackgroundSportApi {
    void pauseSportEnhance(IBaseCommonCallback iBaseCommonCallback);

    void resumeSportEnhance(IBaseCommonCallback iBaseCommonCallback);

    void sendDeviceControlinstruction(String str, IBaseCommonCallback iBaseCommonCallback);

    void startSportEnhance(StartSportParam startSportParam, IBaseCommonCallback iBaseCommonCallback);

    void stopSportEnhance(IBaseCommonCallback iBaseCommonCallback);
}
