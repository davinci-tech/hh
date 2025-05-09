package com.huawei.operation.h5pro.jsmodules.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback;
import defpackage.lqi;
import defpackage.lqj;
import defpackage.nsn;
import health.compact.a.GRSManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@H5ProService(name = "HealthCommonApi")
/* loaded from: classes.dex */
public class HealthCommonApi {
    private static final String TAG = "H5PRO_HealthCommonApi";
    private static final String THIRD_HMS_AUTH_URL = "/healthkit/v1/thirdAuthInfo";
    private static volatile VersionInfo versionInfo;

    @H5ProMethod
    public static void getVersionInfo(ServiceApiCallback<VersionInfo> serviceApiCallback) {
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "getVersionInfo: callback is null");
            return;
        }
        if (versionInfo == null) {
            versionInfo = new VersionInfo();
        }
        Objects.requireNonNull(versionInfo);
        LogUtil.a(TAG, "getApiLevel:", 9);
        serviceApiCallback.onSuccess(versionInfo);
    }

    @H5ProMethod
    public static void getAbilities(ServiceApiCallback<JSONObject> serviceApiCallback) {
        LogUtil.a(TAG, "getAbilities start");
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "getAbilities: callback is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("abilities", new JSONArray((Collection) AbilitiesConfig.ABILITIES));
            serviceApiCallback.onSuccess(jSONObject);
        } catch (JSONException e) {
            LogUtil.b(TAG, "getAbilities: JSONException");
            serviceApiCallback.onFailure(-1, e.getMessage());
        }
    }

    @H5ProMethod(name = "isImperialUnit")
    public static void isImperialUnit(ServiceApiCallback<Boolean> serviceApiCallback) {
        LogUtil.a(TAG, "isImperialUnit start");
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "isImperialUnit callback is null");
        } else {
            serviceApiCallback.onSuccess(Boolean.valueOf(UnitUtil.h()));
        }
    }

    @H5ProMethod(name = "isCelsiusUnit")
    public static void isCelsiusUnit(ServiceApiCallback<Boolean> serviceApiCallback) {
        LogUtil.a(TAG, "isCelsiusUnit start");
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "isCelsiusUnit callback is null");
        } else {
            serviceApiCallback.onSuccess(Boolean.valueOf(UnitUtil.d()));
        }
    }

    @H5ProMethod(name = "getThirdAuthInfo")
    public static void getThirdAuthInfo(String str, ServiceApiCallback<String> serviceApiCallback) {
        Log.i(TAG, "getThirdAuthInfo");
        if (TextUtils.isEmpty(str)) {
            LogUtil.b(TAG, "getThirdAuthInfo: x-thirdApp-AT is null.");
            serviceApiCallback.onFailure(-1, "x-thirdApp-AT is null.");
            return;
        }
        Context context = BaseApplication.getContext();
        String url = GRSManager.a(context).getUrl(HealthEngineRequestManager.GRS_KEY);
        if (TextUtils.isEmpty(url)) {
            LogUtil.b(TAG, "getThirdAuthInfo: failed to get hms auth domain.");
            serviceApiCallback.onFailure(-1, "failed to get hms auth domain.");
            return;
        }
        request(url + THIRD_HMS_AUTH_URL, LoginInit.getInstance(context).getAccountInfo(1015), str, true, serviceApiCallback);
    }

    @H5ProMethod(name = "getClientType")
    public static void getClientType(ServiceApiCallback<Integer> serviceApiCallback) {
        int b = nsn.b();
        ReleaseLogUtil.e(TAG, "getClientType:", Integer.valueOf(b));
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "isCelsiusUnit callback is null");
        } else {
            serviceApiCallback.onSuccess(Integer.valueOf(b));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void request(final String str, String str2, final String str3, final boolean z, final ServiceApiCallback<String> serviceApiCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-type", "application/json");
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + str2);
        hashMap.put("x-thirdApp-AT", str3);
        lqi.d().c(str, hashMap, new HashMap(), String.class, new ResultCallback<String>() { // from class: com.huawei.operation.h5pro.jsmodules.common.HealthCommonApi.1
            @Override // com.huawei.networkclient.ResultCallback
            public void onSuccess(String str4) {
                ServiceApiCallback.this.onSuccess(str4);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                Log.e(HealthCommonApi.TAG, "getThirdAuthInfo: " + th.getMessage());
                if (th instanceof lqj) {
                    lqj lqjVar = (lqj) th;
                    if (lqjVar.e() == 401 && z) {
                        Log.i(HealthCommonApi.TAG, "getThirdAuthInfo: refreshAccessToken");
                        HealthCommonApi.request(str, HealthAccessTokenUtil.getAtInstance().refreshAccessToken(), str3, false, ServiceApiCallback.this);
                        return;
                    }
                    ServiceApiCallback.this.onFailure(lqjVar.e(), lqjVar.getMessage());
                    return;
                }
                ServiceApiCallback.this.onFailure(-1, th.getMessage());
            }
        });
    }
}
