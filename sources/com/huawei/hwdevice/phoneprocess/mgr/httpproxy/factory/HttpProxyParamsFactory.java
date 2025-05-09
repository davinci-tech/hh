package com.huawei.hwdevice.phoneprocess.mgr.httpproxy.factory;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import java.util.Map;

/* loaded from: classes9.dex */
public interface HttpProxyParamsFactory {
    Map<String, Object> getBody(String str, DeviceInfo deviceInfo);

    Map<String, String> getCommonBody(DeviceInfo deviceInfo);

    Map<String, String> getCommonHeaders();

    Map<String, String> getHeaders(String str);

    Map<String, String> getParams(String str);
}
