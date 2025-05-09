package com.huawei.hianalytics.framework.config;

import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public interface ICollectorConfig {
    long getAbsTime();

    String getAppId();

    String getCollectUrl(String str);

    DeviceAttributeCollector getDeviceAttribute(String str);

    EvtHeaderAttributeCollector getEvtCustomHeader(String str, JSONObject jSONObject);

    Map<String, String> getHttpHeader(String str);

    int getMetricPolicy(String str);

    String getPkgName();

    RomAttributeCollector getRomAttribute(String str, String str2);

    boolean getShortLinkEnabled(String str);

    String getUdid();

    boolean isEnableSession(String str);

    boolean isLocalEncrypted(String str);

    boolean isUploadEncrypted(String str);

    void requestConfig();
}
