package com.huawei.hms.network.httpclient.excutor;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.conf.api.ConfigAPI;
import com.huawei.hms.network.conf.api.ConfigService;
import com.huawei.hms.network.conf.api.DefaultConfigService;
import com.huawei.hms.network.embedded.d3;
import com.huawei.hms.network.embedded.g2;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.config.NetworkConfig;
import com.huawei.hms.network.inner.api.NetworkKitInnerImpl;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.hms.network.inner.api.RequestContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class PolicyExecutor extends PolicyNetworkService {
    public static final String TAG = "PolicyExecutor";
    public final DefaultConfigService defaultConfigService;
    public final List<PolicyNetworkService> policyNetworkServices;
    public final ProcessPolicyNetworkService processPolicyNetworkService;

    public void setValue(String str, Object obj) {
        this.processPolicyNetworkService.setValue(str, obj);
    }

    public void setOptions(String str) {
        this.processPolicyNetworkService.setValues(str);
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onDestroy(Context context) {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                policyNetworkService.onDestroy(context);
            }
        }
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public void onCreate(Context context, Bundle bundle) {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                policyNetworkService.onCreate(context, bundle);
            }
        }
    }

    public boolean isRequestOptionHasConnectTiemout(String str) {
        return (str == null || new NetworkConfig(str).getConnectTimeout() == 0) ? false : true;
    }

    public boolean isConnect(String str) {
        if (str == null) {
            return false;
        }
        return new NetworkConfig(str).enableInnerConnectEmptyBody();
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getVersion() {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                String version = policyNetworkService.getVersion();
                if (!TextUtils.isEmpty(version)) {
                    return version;
                }
            }
        }
        return "";
    }

    public Map<String, String> getValues(boolean z, Request request, String str, String str2, String... strArr) {
        String valueOf;
        int aiConnectTimeout;
        HashMap hashMap = new HashMap();
        for (String str3 : strArr) {
            Iterator<PolicyNetworkService> it = this.policyNetworkServices.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PolicyNetworkService next = it.next();
                if (next != null) {
                    if (!(next instanceof ConfigService) || str == null) {
                        String value = next.getValue(str2, str3);
                        d3 d3Var = new d3(request.getUrl());
                        valueOf = (!str3.equals("core_connect_timeout") || !next.getServiceName().equals("ai") || z || isConnect(request.getOptions()) || isRequestOptionHasConnectTiemout(request.getOptions()) || g2.getInstance().isEnableQuic(d3Var.getHost(), d3Var.getPort()).booleanValue() || !StringUtils.stringToBoolean(String.valueOf(ConfigAPI.getValue(NetworkService.Constants.AI_CONNECTTIMEOUT_SWITCH)), false) || (aiConnectTimeout = next.getAiConnectTimeout()) == -1) ? value : String.valueOf(aiConnectTimeout);
                    } else {
                        valueOf = ((ConfigService) next).getValue(str, str2, str3);
                    }
                    if (!TextUtils.isEmpty(valueOf)) {
                        hashMap.put(str3, valueOf);
                        break;
                    }
                }
            }
        }
        return hashMap;
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public Map<String, String> getValues(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str2 : strArr) {
            Iterator<PolicyNetworkService> it = this.policyNetworkServices.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PolicyNetworkService next = it.next();
                if (next != null) {
                    String value = next.getValue(str, str2);
                    if (!TextUtils.isEmpty(value)) {
                        hashMap.put(str2, value);
                        break;
                    }
                }
            }
        }
        return hashMap;
    }

    public String getValue(String str, String str2, String str3) {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                String value = (!(policyNetworkService instanceof ConfigService) || str == null) ? policyNetworkService.getValue(str2, str3) : ((ConfigService) policyNetworkService).getValue(str, str2, str3);
                if (!TextUtils.isEmpty(value)) {
                    return value;
                }
            }
        }
        return "";
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public String getValue(String str, String str2) {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                String value = policyNetworkService.getValue(str, str2);
                if (!TextUtils.isEmpty(value)) {
                    Logger.d(TAG, "policyNetworkService name: " + policyNetworkService.getServiceName() + " ,key: " + str2 + " ,value: " + value);
                    return value;
                }
            }
        }
        return "";
    }

    public String getUserConfigValue(String str) {
        String value = this.processPolicyNetworkService.getValue(null, str);
        return TextUtils.isEmpty(value) ? "" : value;
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService, com.huawei.hms.network.inner.api.NetworkService
    public String getServiceType() {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                String serviceType = policyNetworkService.getServiceType();
                if (!TextUtils.isEmpty(serviceType)) {
                    return serviceType;
                }
            }
        }
        return "";
    }

    @Override // com.huawei.hms.network.inner.api.NetworkService
    public String getServiceName() {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                String serviceName = policyNetworkService.getServiceName();
                if (!TextUtils.isEmpty(serviceName)) {
                    return serviceName;
                }
            }
        }
        return "";
    }

    public String getRequestPramas(boolean z, Request request, String str, String str2) {
        Map<String, String> values = getValues(z, request, str, str2, (String[]) PolicyNetworkService.RequestConstants.REQUEST_CONSTANTS.toArray(new String[0]));
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, String> entry : values.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        } catch (JSONException unused) {
            Logger.w(TAG, "getRequestPramas JSONException");
        }
        return jSONObject.toString();
    }

    public String getProcessPolicyNetworkServiceParams() {
        return this.processPolicyNetworkService.getPolicyNetworkServiceParams();
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public void endRequest(RequestContext requestContext) {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                policyNetworkService.endRequest(requestContext);
            }
        }
    }

    @Override // com.huawei.hms.network.inner.api.PolicyNetworkService
    public void beginRequest(RequestContext requestContext) {
        for (PolicyNetworkService policyNetworkService : this.policyNetworkServices) {
            if (policyNetworkService != null) {
                policyNetworkService.beginRequest(requestContext);
            }
        }
    }

    public PolicyExecutor() {
        ArrayList arrayList = new ArrayList();
        this.policyNetworkServices = arrayList;
        arrayList.add(NetworkKitInnerImpl.getInstance().getPolicyNetworkService(NetworkService.Constants.CONFIG_SERVICE));
        ProcessPolicyNetworkService processPolicyNetworkService = new ProcessPolicyNetworkService();
        this.processPolicyNetworkService = processPolicyNetworkService;
        arrayList.add(processPolicyNetworkService);
        PolicyNetworkService policyNetworkService = NetworkKitInnerImpl.getInstance().getPolicyNetworkService("ai");
        if (policyNetworkService != null) {
            arrayList.add(policyNetworkService);
        }
        DefaultConfigService defaultConfigService = new DefaultConfigService();
        this.defaultConfigService = defaultConfigService;
        arrayList.add(defaultConfigService);
    }
}
