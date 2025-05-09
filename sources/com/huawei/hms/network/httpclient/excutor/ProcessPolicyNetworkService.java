package com.huawei.hms.network.httpclient.excutor;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.e;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.inner.api.RequestContext;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class ProcessPolicyNetworkService extends PolicyNetworkService {
    public static final String TAG = "PolicyService";
    public e baseConfig = new e("");

    public void setValues(String str) {
        Logger.i(TAG, "options = " + str);
        this.baseConfig.setOption(str);
    }

    public void setValue(String str, Object obj) {
        this.baseConfig.setValue(str, obj);
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onDestroy(Context context) {
        Logger.v(TAG, "onDestroy");
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onCreate(Context context, Bundle bundle) {
        Logger.v(TAG, "onCreate");
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getVersion() {
        return "8.0.1.307";
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public Map<String, String> getValues(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str2 : strArr) {
            String value = getValue(str, str2);
            if (!value.isEmpty()) {
                hashMap.put(str2, value);
            }
        }
        return hashMap;
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public String getValue(String str, String str2) {
        return this.baseConfig.get(str2);
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService, com.huawei.hms.network.inner.api.NetworkService
    public String getServiceType() {
        return ProcessPolicyNetworkService.class.getName();
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getServiceName() {
        return ProcessPolicyNetworkService.class.getName();
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public String getPolicyNetworkServiceParams() {
        return this.baseConfig.toString();
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public void endRequest(RequestContext requestContext) {
        Logger.v(TAG, ParamConstants.Param.CONTEXT);
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public void beginRequest(RequestContext requestContext) {
        Logger.v(TAG, ParamConstants.Param.CONTEXT);
    }
}
