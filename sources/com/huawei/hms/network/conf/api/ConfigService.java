package com.huawei.hms.network.conf.api;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.j;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.inner.api.RequestContext;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class ConfigService extends PolicyNetworkService {
    public static final String TAG = "ConfigService";

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onDestroy(Context context) {
        Logger.v(TAG, "onDestroy");
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onCreate(Context context, Bundle bundle) {
        ConfigAPI.init(context);
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getVersion() {
        return "8.0.1.307";
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public Map<String, String> getValues(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str2 : strArr) {
            hashMap.put(str2, getValue(str, str2));
        }
        return hashMap;
    }

    public String getValue(String str, String str2, String str3) {
        return getValue(str2, str + "|" + str3);
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public String getValue(String str, String str2) {
        Object a2 = j.e().a(str2);
        return a2 != null ? String.valueOf(a2) : "";
    }

    public String getValue(String str) {
        return getValue(null, str);
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService, com.huawei.hms.network.inner.api.NetworkService
    public String getServiceType() {
        return ConfigService.class.getName();
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getServiceName() {
        return NetworkService.Constants.CONFIG_SERVICE;
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getConfigValue(String str) {
        Object value = ConfigAPI.getValue(str);
        if (value != null) {
            return String.valueOf(value);
        }
        return null;
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public void endRequest(RequestContext requestContext) {
        Logger.v(TAG, "endRequest");
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public void beginRequest(RequestContext requestContext) {
        Logger.v(TAG, "beginRequest");
    }
}
