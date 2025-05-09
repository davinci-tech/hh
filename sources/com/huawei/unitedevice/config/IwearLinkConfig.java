package com.huawei.unitedevice.config;

import android.content.Context;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes.dex */
public interface IwearLinkConfig {
    default void applyOrReleaseResource(boolean z, String str, int i, long j, String str2) {
    }

    default boolean getAuthorizationStatus(Context context) {
        return false;
    }

    default boolean isLogin(Context context) {
        return true;
    }

    default boolean noCloudVersion() {
        return true;
    }

    default void notification(int i) {
    }

    default void opEvent2nd(String str, LinkedHashMap<String, String> linkedHashMap, boolean z) {
    }

    default void setProbabilityProblemEvent(String str, String str2) {
    }

    default void wearLinkRiskEvent(String str, String str2) {
    }

    default List<DeviceInfo> getDeviceMgrList(int i, String str) {
        return new ArrayList(16);
    }

    default String getSwitchSetting(String str, String str2) {
        return "1";
    }

    default String getDeviceRelation(String str) {
        return "main_relationship";
    }

    default String getCommonCountryCode(Context context) {
        return "CN";
    }

    default String getBrand(int i) {
        return "";
    }
}
