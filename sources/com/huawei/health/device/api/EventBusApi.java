package com.huawei.health.device.api;

/* loaded from: classes3.dex */
public interface EventBusApi {
    void publishDeviceDownMsg();

    void publishHmsLoginState(String str);

    void publishSingleDeviceDownMsg();

    void subscribeAm16Bind();
}
