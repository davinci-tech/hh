package com.huawei.operation.utils;

import android.content.ContentValues;
import android.text.TextUtils;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ixx;
import defpackage.knl;
import defpackage.nsj;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class BleJsBiOperate {
    private static final int DEFAULT_MAP_SIZE = 16;
    private static final String KEY_API_NAME = "apiName";
    private static final String KEY_BLE_CONNECT_STATE = "state";
    private static final String KEY_CALL_TIME = "callTime";
    private static final String KEY_COST_TIME = "costTime";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_DATA_SIZE = "data_size";
    private static final String KEY_DATA_TYPE = "dataType";
    private static final String KEY_DEVICE_NAME = "name";
    private static final String KEY_DEVICE_TYPE = "deviceType";
    private static final String KEY_KIND_NAME = "kind_name";
    private static final String KEY_PRODUCT_ID = "productId";
    private static final String KEY_RESULT = "result";
    private static final String KEY_TIME_LIST = "time_list";
    private static final String KEY_UNIQUE_ID = "uniqueId";
    private static final String MAC_ADDRESS = "macAddress";
    private static final String OTA_URL = "url";
    private static final String TAG = "BleJsBiOperate";
    private long mCallingEndTime;
    private long mCallingStartTime;

    public void init() {
        this.mCallingStartTime = System.currentTimeMillis();
    }

    public void biApiCalling(ContentValues contentValues, String str, String str2, int i, String str3) {
        LogUtil.c(TAG, "entry biApiCalling!");
        if (contentValues == null) {
            LogUtil.b(TAG, "biApiCalling: deviceInfo is null");
            return;
        }
        this.mCallingEndTime = System.currentTimeMillis();
        HashMap hashMap = new HashMap(16);
        hashMap.put("uniqueId", knl.d(contentValues.getAsString("uniqueId")));
        hashMap.put("productId", str);
        hashMap.put("name", getDeviceName(str));
        hashMap.put("deviceType", contentValues.getAsString("deviceType"));
        hashMap.put("apiName", str2);
        hashMap.put(KEY_DATA_TYPE, Integer.valueOf(i));
        hashMap.put("result", str3);
        hashMap.put("country", Utils.getCountryCode());
        hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        hashMap.put("costTime", String.valueOf(this.mCallingEndTime - this.mCallingStartTime));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_BLUETOOTH_PRIVATE_PROTOCOL_H5_ACCESS_2040007.value(), hashMap, 0);
    }

    public void tickBiBleConnect(ContentValues contentValues, int i, String str) {
        LogUtil.c(TAG, "entry tickBiBleConnect!");
        if (contentValues == null) {
            LogUtil.b(TAG, "tickBiBleConnect: deviceInfo is null");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put(KEY_KIND_NAME, contentValues.getAsString("deviceType"));
        hashMap.put("uniqueId", knl.d(contentValues.getAsString("uniqueId")));
        hashMap.put("productId", str);
        hashMap.put("state", String.valueOf(i));
        hashMap.put("name", getDeviceName(str));
        hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
        hashMap.put("prodId", ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(str));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.THIRD_PARTY_EQUIPMENT_H5_BLUETOOTH_CONNECT_2060107.value(), hashMap, 0);
    }

    public void tickBiDataSize(ContentValues contentValues, List<HiHealthData> list, String str, int i) {
        LogUtil.c(TAG, "entry tickBiDataSize!");
        if (contentValues == null) {
            LogUtil.b(TAG, "tickBiDataSize: deviceInfo is null");
            return;
        }
        String d = knl.d(contentValues.getAsString("uniqueId"));
        HashMap hashMap = new HashMap(16);
        hashMap.put(KEY_KIND_NAME, contentValues.getAsString("deviceType"));
        hashMap.put("uniqueId", d);
        hashMap.put("productId", str);
        hashMap.put(KEY_DATA_SIZE, String.valueOf(list.size()));
        hashMap.put("name", getDeviceName(str));
        hashMap.put(KEY_DATA_TYPE, String.valueOf(i));
        ArrayList arrayList = new ArrayList();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(nsj.e(it.next().getEndTime()));
        }
        hashMap.put(KEY_TIME_LIST, arrayList);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.THIRD_PARTY_EQUIPMENT_H5_OFFLINE_DATA_2060108.value(), hashMap, 0);
    }

    public void tickBiOtaDownloadComplete(String str, String str2, String str3) {
        LogUtil.c(TAG, "entry tickBiOtaDownloadComplete!");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a(TAG, "productId or uniqueId is empty");
            return;
        }
        String prodId = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(str);
        HashMap hashMap = new HashMap(16);
        if (!TextUtils.isEmpty(prodId)) {
            hashMap.put("prodId", prodId);
        }
        hashMap.put(MAC_ADDRESS, knl.d(str2));
        hashMap.put("url", str3);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.OTA_DOWNLOAD_COMPLETE_EVENT.value(), hashMap, 0);
    }

    private String getDeviceName(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "productId is empty");
            return "";
        }
        String productInfo = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProductInfo(str);
        if (!TextUtils.isEmpty(productInfo)) {
            try {
                return new JSONObject(productInfo).getString("deviceName");
            } catch (JSONException unused) {
                LogUtil.b(TAG, "getDeviceName jsonException");
            }
        }
        return "";
    }
}
