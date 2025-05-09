package com.huawei.operation.h5pro.jsmodules.device;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bgb;
import health.compact.a.ReleaseLogUtil;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UpdateDeviceJsApi extends JsBaseModule {
    private static final String TAG = "UpdateDeviceJsApi";
    private static final String TAG_RELEASE = "DEVMGR_UpdateDeviceJsApi";

    @JavascriptInterface
    public void getUpdatePageContent(long j, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "getUpdatePageContent JSONException ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "getUpdatePageContent jsonObject is null");
            onFailureCallback(j, "jsonObject is null", -1);
            return;
        }
        Map<String, Boolean> updatePageContent = bgb.e().getUpdatePageContent(jSONObject.optString("deviceMac"));
        if (updatePageContent == null || updatePageContent.isEmpty()) {
            LogUtil.h(TAG, "getUpdatePageContent updateStatus is error");
            onFailureCallback(j, "updateStatus is error", -1);
        } else {
            String e2 = HiJsonUtil.e(updatePageContent);
            LogUtil.a(TAG, "getUpdatePageContent result:", e2);
            onSuccessCallback(j, e2);
        }
    }

    @JavascriptInterface
    public void setUpdateStatus(final long j, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ReleaseLogUtil.c(TAG_RELEASE, "setUpdateStatus JSONException ", ExceptionUtils.d(e));
            jSONObject = null;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.a(TAG_RELEASE, "setUpdateStatus jsonObject is null");
            onFailureCallback(j, "jsonObject is null", -1);
            return;
        }
        String optString = jSONObject.optString("deviceMac");
        String optString2 = jSONObject.optString("content");
        if (TextUtils.isEmpty(optString)) {
            LogUtil.h(TAG, "setUpdateStatus deviceMac is error");
            onFailureCallback(j, "deviceMac is error", -1);
            return;
        }
        if (TextUtils.isEmpty(optString2)) {
            LogUtil.h(TAG, "setUpdateStatus updateStatus is error");
            onFailureCallback(j, "updateStatus is error", -1);
            return;
        }
        Map<String, Boolean> map = (Map) HiJsonUtil.e(optString2, Map.class);
        if (map == null || map.isEmpty()) {
            LogUtil.h(TAG, "setUpdateStatus statusMap is null");
            onFailureCallback(j, "H5 param is error", -1);
        } else {
            bgb.e().setUpdateStatus(map, optString, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.device.UpdateDeviceJsApi.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a(UpdateDeviceJsApi.TAG, "setUpdateStatus errorCode :", Integer.valueOf(i));
                    if (i == 0) {
                        UpdateDeviceJsApi.this.responseSuccess(j, obj);
                    } else {
                        UpdateDeviceJsApi.this.onFailureCallback(j, "setUpdateStatus error", -1);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void responseSuccess(long j, Object obj) {
        if (obj instanceof Map) {
            String e = HiJsonUtil.e((Map) obj);
            LogUtil.a(TAG, "responseSuccess result:", e);
            onSuccessCallback(j, e);
        } else {
            ReleaseLogUtil.b(TAG_RELEASE, "responseSuccess error");
            onFailureCallback(j, "request error", -1);
        }
    }

    @JavascriptInterface
    public void isShowOtaPrivacy(long j, String str) {
        LogUtil.a(TAG, "enter isShowOtaPrivacy");
        String deviceMac = DevicePairUtils.getDeviceMac(str);
        if (TextUtils.isEmpty(deviceMac)) {
            ReleaseLogUtil.b(TAG_RELEASE, "isShowOtaPrivacy,get mac error");
            onFailureCallback(j, "get mac error", -1);
        } else {
            onSuccessCallback(j, Boolean.valueOf(bgb.e().isShowOtaPrivacy(deviceMac)));
        }
    }
}
