package com.huawei.health.ecologydevice.manager;

import android.app.Activity;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceManagerApi;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.networkclient.BindStatusCheckResponse;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.networkclient.OnRequestCallBack;
import com.huawei.health.ecologydevice.networkclient.ResponseResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.cex;
import defpackage.dcz;
import defpackage.dij;
import defpackage.dko;
import defpackage.dks;
import defpackage.jah;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class GprsDeviceHelper {
    private static final String d = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(HealthEngineRequestManager.GRS_KEY, "com.huawei.health" + HealthEngineRequestManager.SERVICE_SUFFIX, "");

    public interface CheckAndBindCallback {
        void onBindException();

        void onBindFinish();
    }

    public static void c(final String str, final String str2, final CheckAndBindCallback checkAndBindCallback) {
        String e = dij.e(str);
        LogUtil.a("GprsDeviceHelper", "deviceCheckAndBind request.");
        c(e, str2, new OnRequestCallBack<BindStatusCheckResponse>() { // from class: com.huawei.health.ecologydevice.manager.GprsDeviceHelper.2
            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(BindStatusCheckResponse bindStatusCheckResponse) {
                if (bindStatusCheckResponse.getBindStatusCode() != 3) {
                    ReleaseLogUtil.d("DEVMGR_GprsDeviceHelper", "Check device bind state bindStatusCode:", Integer.valueOf(bindStatusCheckResponse.getBindStatusCode()));
                    CheckAndBindCallback.this.onBindException();
                } else {
                    long deviceCode = bindStatusCheckResponse.getDeviceBindResp().getDeviceCode();
                    LogUtil.a("GprsDeviceHelper", "deviceCode:", Long.valueOf(deviceCode));
                    SharedPreferenceManager.e("thirdDeviceToApp", "deviceCode", deviceCode);
                    GprsDeviceHelper.d(str, str2, CheckAndBindCallback.this);
                }
            }

            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c("DEVMGR_GprsDeviceHelper", "Check device bind state error code:", Integer.valueOf(i), ",message:", th.getMessage());
                CheckAndBindCallback.this.onBindException();
            }
        });
    }

    public static void c(OnRequestCallBack<BindStatusCheckResponse.DeviceBindResp> onRequestCallBack) {
        long b = SharedPreferenceManager.b("thirdDeviceToApp", "deviceCode", 0L);
        ReleaseLogUtil.e("DEVMGR_GprsDeviceHelper", "deviceCode:", Long.valueOf(b));
        HealthEngineRequestManager.RequestParamsBuilder requestParamsBuilder = new HealthEngineRequestManager.RequestParamsBuilder();
        requestParamsBuilder.setUrl(d + HealthEngineRequestManager.URI_UN_BIND);
        HashMap hashMap = new HashMap(10);
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + c());
        hashMap.put("Content-type", HealthEngineRequestManager.CONTENT_TYPE);
        HashMap hashMap2 = new HashMap(10);
        hashMap2.put("deviceCode", Long.valueOf(b));
        requestParamsBuilder.setRequestCallBack(onRequestCallBack);
        requestParamsBuilder.setTag("GprsDeviceHelper").setHeaderParam(hashMap).setMethod("POST").setBodyParam(hashMap2).create().request();
    }

    private static void c(String str, String str2, OnRequestCallBack<BindStatusCheckResponse> onRequestCallBack) {
        ReleaseLogUtil.e("DEVMGR_GprsDeviceHelper", "prodId:", str, "; uniqueId:", CommonUtil.l(str2));
        HealthEngineRequestManager.RequestParamsBuilder requestParamsBuilder = new HealthEngineRequestManager.RequestParamsBuilder();
        requestParamsBuilder.setUrl(d + HealthEngineRequestManager.URI_CHECK_BIND_STATUS);
        HashMap hashMap = new HashMap(10);
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + c());
        hashMap.put("Content-type", HealthEngineRequestManager.CONTENT_TYPE);
        HashMap hashMap2 = new HashMap(10);
        hashMap2.put("prodId", str);
        hashMap2.put("uniqueId", str2);
        requestParamsBuilder.setRequestCallBack(onRequestCallBack);
        requestParamsBuilder.setTag("GprsDeviceHelper").setHeaderParam(hashMap).setMethod("POST").setBodyParam(hashMap2).create().request();
    }

    private static String c() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
        return TextUtils.isEmpty(accountInfo) ? HealthAccessTokenUtil.getAtInstance().refreshAccessToken() : accountInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final String str, final String str2, final CheckAndBindCallback checkAndBindCallback) {
        if (((DeviceManagerApi) Services.c("PluginDevice", DeviceManagerApi.class)).isBondedDevice(str2)) {
            LogUtil.a("GprsDeviceHelper", "Device is bonded");
            checkAndBindCallback.onBindFinish();
        } else {
            ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).bindDevice(str, new cex("", str, str2, str2), new IDeviceEventHandler() { // from class: com.huawei.health.ecologydevice.manager.GprsDeviceHelper.1
                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onDeviceFound(HealthDevice healthDevice) {
                    LogUtil.c("GprsDeviceHelper", "onDeviceFound");
                }

                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onScanFailed(int i) {
                    LogUtil.c("GprsDeviceHelper", "onScanFailed code:", Integer.valueOf(i));
                }

                @Override // com.huawei.health.device.callback.IDeviceEventHandler
                public void onStateChanged(int i) {
                    LogUtil.a("GprsDeviceHelper", "onStateChanged code :", Integer.valueOf(i));
                    if (i == 7) {
                        String str3 = str;
                        String str4 = str2;
                        dko.b(str3, str4, str4);
                        checkAndBindCallback.onBindFinish();
                        LogUtil.a("GprsDeviceHelper", "Bind Success");
                        return;
                    }
                    checkAndBindCallback.onBindException();
                    LogUtil.a("GprsDeviceHelper", "Local Bind Error");
                }
            });
        }
    }

    public static void TF_(Activity activity, final String str, final OnRequestCallBack<ResponseResult> onRequestCallBack) {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            dks.WB_(activity, activity.getString(R.string._2130851423_res_0x7f02365f), false);
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.a("GprsDeviceHelper", "getSinoAt getAccessToken null");
            accountInfo = HealthAccessTokenUtil.getAtInstance().refreshAccessToken();
        }
        final String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo) || TextUtils.isEmpty(accountInfo2)) {
            LogUtil.a("GprsDeviceHelper", "getSinoAt getAccessToken null");
            dks.WB_(activity, activity.getString(R.string._2130843457_res_0x7f021741), false);
            return;
        }
        LogUtil.a("GprsDeviceHelper", "getSinoAt request.");
        OnRequestCallBack<ResponseResult> onRequestCallBack2 = new OnRequestCallBack<ResponseResult>() { // from class: com.huawei.health.ecologydevice.manager.GprsDeviceHelper.4
            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ResponseResult responseResult) {
                LogUtil.a("GprsDeviceHelper", "getSinoAt onSuccess");
                GprsDeviceHelper.d(accountInfo2, str, responseResult.getAccessToken(), onRequestCallBack);
            }

            @Override // com.huawei.health.ecologydevice.networkclient.OnRequestCallBack
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.c("DEVMGR_GprsDeviceHelper", "getSinoAt onFail errorCode:", Integer.valueOf(i), ",throwable:", th.toString());
            }
        };
        String str2 = GRSManager.a(BaseApplication.getContext()).getUrl(HealthEngineRequestManager.GRS_KEY) + HealthEngineRequestManager.URL_THIRDACCESSTOKENS_SINOCARE;
        HashMap hashMap = new HashMap(10);
        hashMap.put("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + accountInfo);
        new HealthEngineRequestManager.RequestParamsBuilder().setTag("GprsDeviceHelper").setUrl(str2).setHeaderParam(hashMap).setMethod("GET").setRequestCallBack(onRequestCallBack2).create().request();
    }

    public static void d(String str, String str2, String str3, OnRequestCallBack<ResponseResult> onRequestCallBack) {
        String str4 = jah.c().e("domain_ecs_tmqyt_sino") + HealthEngineRequestManager.URL_BLOODSUGAR_UNBINDDEVICE;
        HashMap hashMap = new HashMap(10);
        hashMap.put(HealthEngineRequestManager.PARAMS_NAME_USER_ID, str);
        hashMap.put(HealthEngineRequestManager.PARAMS_DEVICE_SN, str2);
        HashMap hashMap2 = new HashMap(10);
        hashMap2.put(HealthEngineRequestManager.PARAMS_SINO_AUTH, str3);
        new HealthEngineRequestManager.RequestParamsBuilder().setTag("GprsDeviceHelper").setUrl(str4).setHeaderParam(hashMap2).setBodyParam(hashMap).setMethod("POST").setRequestCallBack(onRequestCallBack).create().request();
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("GprsDeviceHelper", "isGprsNative productId is null");
            return false;
        }
        dcz b = ResourceManager.e().b(str);
        return b != null && "PRO_GPRS".equals(b.y()) && "1".equals(b.j());
    }
}
