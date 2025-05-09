package com.huawei.operation.h5pro.jsmodules.device;

import android.webkit.JavascriptInterface;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bgb;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DeviceResourceJsApi extends JsBaseModule {
    private static final int DOWNLOAD_RESOURCE_FAIL = 100010;
    private static final int DOWNLOAD_RESOURCE_NETWORK_ERROR = 100008;
    private static final String TAG = "DeviceResourceJsApi";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.util.List] */
    @JavascriptInterface
    public void downloadResource(final long j, String str) {
        ReleaseLogUtil.b(TAG, "downloadResource callbackId ", Long.valueOf(j));
        String devicePairInfo = DevicePairUtils.getDevicePairInfo(str);
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(devicePairInfo);
            if (jSONObject.has("uuids")) {
                arrayList = (List) HiJsonUtil.e(jSONObject.optString("uuids"), List.class);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "isReconnect error");
        }
        bgb.c().downloadIndexByUuidList(arrayList, new DownloadDeviceInfoCallBack() { // from class: com.huawei.operation.h5pro.jsmodules.device.DeviceResourceJsApi.1
            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onSuccess() {
                DeviceResourceJsApi.this.onCompleteCallback(j, "", 0);
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onFailure(int i) {
                DeviceResourceJsApi.this.onFailureCallback(j, "", DeviceResourceJsApi.DOWNLOAD_RESOURCE_FAIL);
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void netWorkError() {
                DeviceResourceJsApi.this.onFailureCallback(j, "", DeviceResourceJsApi.DOWNLOAD_RESOURCE_NETWORK_ERROR);
            }

            @Override // com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack
            public void onDownload(int i) {
                DeviceResourceJsApi.this.onSuccessCallback(j, Integer.valueOf(i));
            }
        });
    }

    @JavascriptInterface
    public void getPairDescription(long j, String str) {
        ReleaseLogUtil.b(TAG, "enter getPairDescription, callbackId: ", Long.valueOf(j));
        bgb.c().getPairDescription("");
        HashMap hashMap = new HashMap();
        hashMap.put("deviceShape", 1);
        onSuccessCallback(j, HiJsonUtil.e(hashMap));
    }

    @JavascriptInterface
    public void getValueFeature(long j, String str) {
        ReleaseLogUtil.b(TAG, "enter getValueFeature, callbackId: ", Long.valueOf(j));
        bgb.c().getValueFeature("");
        onSuccessCallback(j, new ArrayList());
    }
}
