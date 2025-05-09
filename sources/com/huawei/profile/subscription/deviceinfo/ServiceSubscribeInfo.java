package com.huawei.profile.subscription.deviceinfo;

import android.text.TextUtils;
import com.huawei.profile.subscription.deviceinfo.SubscribeInfo;

/* loaded from: classes9.dex */
public class ServiceSubscribeInfo extends SubscribeInfo {
    ServiceSubscribeInfo(int i, SubscribeInfo.Builder builder) {
        this.flags = i;
        this.builder = builder;
    }

    public ServiceSubscribeInfo devId(String str) throws SubscribeInfoInvalidException {
        if (!TextUtils.isEmpty(this.deviceType)) {
            throw new SubscribeInfoInvalidException("already assigned device type");
        }
        this.deviceId = str;
        return this;
    }

    public ServiceSubscribeInfo devType(String str) throws SubscribeInfoInvalidException {
        if (!TextUtils.isEmpty(this.deviceId)) {
            throw new SubscribeInfoInvalidException("already assigned device id");
        }
        this.deviceType = str;
        return this;
    }

    public SubscribeInfo.Builder serviceId(String str) {
        this.serviceId = str;
        return this.builder;
    }

    public SubscribeInfo.Builder serviceType(String str) {
        this.serviceType = str;
        return this.builder;
    }
}
