package com.huawei.health.algorithm.api;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public interface BreatheWearAppInterface {
    boolean getDeviceSupportBreatheReport();

    String getDeviceUuid();

    void registerNotificationPress(IBaseResponseCallback iBaseResponseCallback);

    void setHeartRateReportStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback);

    void unRegisterNotificationPress(IBaseResponseCallback iBaseResponseCallback);
}
