package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.inner.api.NetworkKitInnerImpl;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class n4 {
    public static final String c = "ServicePolicyExecutor";

    /* renamed from: a, reason: collision with root package name */
    public JSONObject f5389a;
    public PolicyNetworkService b;

    public boolean b(String str) {
        try {
            return this.f5389a.has(str) ? this.f5389a.getBoolean(str) : StringUtils.stringToBoolean(this.b.getConfigValue(str), false);
        } catch (JSONException unused) {
            return StringUtils.stringToBoolean(this.b.getConfigValue(str), false);
        }
    }

    public void a() {
        PolicyNetworkService policyNetworkService = NetworkKitInnerImpl.getInstance().getPolicyNetworkService(NetworkService.Constants.CONFIG_SERVICE);
        this.b = policyNetworkService;
        if (policyNetworkService == null) {
            throw new IllegalStateException("configPolicyService is error");
        }
    }

    public Boolean a(String str) {
        try {
            if (this.f5389a.has(str)) {
                return Boolean.valueOf(this.f5389a.getBoolean(str));
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    private void c(String str) {
        try {
            this.f5389a = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
        } catch (JSONException unused) {
            this.f5389a = new JSONObject();
            Logger.w(c, "call method NetworkServiceManager init set options occur JSONException");
        }
    }

    public n4(String str) {
        c(str);
    }
}
