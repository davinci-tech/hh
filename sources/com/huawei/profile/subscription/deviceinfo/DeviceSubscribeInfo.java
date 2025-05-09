package com.huawei.profile.subscription.deviceinfo;

import com.huawei.profile.subscription.deviceinfo.SubscribeInfo;

/* loaded from: classes9.dex */
public class DeviceSubscribeInfo extends SubscribeInfo {
    DeviceSubscribeInfo(int i, SubscribeInfo.Builder builder) {
        this.flags = i;
        this.builder = builder;
    }

    public SubscribeInfo.Builder devId(String str) {
        this.deviceId = str;
        return this.builder;
    }

    public SubscribeInfo.Builder devType(String str) {
        this.deviceType = str;
        return this.builder;
    }
}
