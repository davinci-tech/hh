package com.huawei.profile.subscription.deviceinfo;

import android.text.TextUtils;
import com.huawei.profile.subscription.deviceinfo.SubscribeInfo;

/* loaded from: classes9.dex */
public class CharacteristicSubscribeInfo extends SubscribeInfo {
    CharacteristicSubscribeInfo(int i, SubscribeInfo.Builder builder) {
        this.flags = i;
        this.builder = builder;
    }

    public CharacteristicSubscribeInfo devId(String str) throws SubscribeInfoInvalidException {
        if (!TextUtils.isEmpty(this.deviceType)) {
            throw new SubscribeInfoInvalidException("already assigned device type");
        }
        this.deviceId = str;
        return this;
    }

    public CharacteristicSubscribeInfo devType(String str) throws SubscribeInfoInvalidException {
        if (!TextUtils.isEmpty(this.deviceId)) {
            throw new SubscribeInfoInvalidException("already assigned device id");
        }
        this.deviceType = str;
        return this;
    }

    public CharacteristicSubscribeInfo serviceId(String str) throws SubscribeInfoInvalidException {
        if (!TextUtils.isEmpty(this.serviceType)) {
            throw new SubscribeInfoInvalidException("already assigned service type");
        }
        this.serviceId = str;
        return this;
    }

    public CharacteristicSubscribeInfo serviceType(String str) throws SubscribeInfoInvalidException {
        if (!TextUtils.isEmpty(this.serviceType)) {
            throw new SubscribeInfoInvalidException("already assigned service id");
        }
        this.serviceType = str;
        return this;
    }

    public SubscribeInfo.Builder characterId(String str) {
        this.charId = str;
        return this.builder;
    }

    public SubscribeInfo.Builder characterType(String str) {
        this.charType = str;
        return this.builder;
    }

    public SubscribeInfo.Builder subscribeAllCharacteristics() {
        this.charId = "AllCharacteristics";
        this.charType = "AllCharacteristics";
        this.subscribeAllCharacteristicFlag = 1;
        return this.builder;
    }
}
