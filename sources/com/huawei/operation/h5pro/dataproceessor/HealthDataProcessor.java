package com.huawei.operation.h5pro.dataproceessor;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiHealthDataQueryOption;
import com.huawei.hihealth.device.HiHealthDeviceInfo;
import com.huawei.hihealth.listener.IuniversalCallback;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hihealthkit.HiHealthDataQuery;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.jsmodules.healthengine.service.HealthEngineService;
import com.huawei.operation.utils.BleJsBiOperate;
import com.huawei.operation.utils.Utils;
import defpackage.idr;
import defpackage.idx;
import defpackage.idy;
import defpackage.ieb;
import defpackage.koq;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class HealthDataProcessor {
    private static final int ARRAY_LIST_DEFAULT_CAPACITY = 10;
    private static final String DEVICE_PACKAGE_NAME = "packageName";
    private static final String ECG_PACKAGE_NAME_PREFIX = "com.huawei.health.device.";
    private static final int HASH_MAP_DEFAULT_CAPACITY = 16;
    private static final int SUBSCRIPT_ZERO = 0;
    private static final String TAG = "PluginOperation_HealthDataProcessor";
    private static final int TIMEOUT = 0;
    private idr mAppContext;
    private Context mContext;
    private ContentValues mDeviceInfo;
    private H5ProInstance mInstance;
    private boolean mIsRetryFailed;
    private H5ProJsCbkInvoker<Object> mJsInvoker;
    private String mProductId;
    private String mUniqueId;

    public HealthDataProcessor(Context context, H5ProInstance h5ProInstance, ContentValues contentValues, String str, String str2) {
        LogUtil.a(TAG, "startProcessor");
        this.mInstance = h5ProInstance;
        this.mContext = context;
        this.mJsInvoker = h5ProInstance.getJsCbkInvoker();
        this.mDeviceInfo = contentValues;
        this.mProductId = str;
        this.mUniqueId = str2;
    }

    private void refreshAuthInfo() {
        this.mAppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mInstance);
    }

    public void setIsRetryFailed(boolean z) {
        this.mIsRetryFailed = z;
    }

    public void execQuery(String str, String str2) {
        int i;
        LogUtil.a(TAG, "execQuery function = ", str2);
        refreshAuthInfo();
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt("type");
            try {
                long j = jSONObject.getLong("startTime");
                long j2 = jSONObject.getLong("endTime");
                LogUtil.a(TAG, "execQuery type : ", Integer.valueOf(i2), " startTime : endTime ", Long.valueOf(j), " : ", Long.valueOf(j2));
                if (j >= 0 && j <= j2) {
                    if (i2 != 31001 && i2 != 31002) {
                        bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, i2, BleConstants.ERRCODE_BLE_DATA_ERROR);
                        callBackJsExecQueryData(str2, BleConstants.ERRCODE_BLE_DATA_ERROR, null);
                        return;
                    }
                    execQueryDataApi(str2, j, j2, i2);
                    return;
                }
                LogUtil.h(TAG, "execQuery startTime < 0 or startTime > endTime");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, i2, BleConstants.ERRCODE_BLE_DATA_ERROR);
                callBackJsExecQueryData(str2, BleConstants.ERRCODE_BLE_DATA_ERROR, null);
            } catch (JSONException unused) {
                i = i2;
                LogUtil.b(TAG, "execQueryData JSONException");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, i, BleConstants.ERRCODE_BLE_DATA_ERROR);
                callBackJsExecQueryData(str2, BleConstants.ERRCODE_BLE_DATA_ERROR, null);
            }
        } catch (JSONException unused2) {
            i = 0;
        }
    }

    public void execQueryAllDataApi(long j, long j2, int i, String str, IuniversalCallback iuniversalCallback) {
        refreshAuthInfo();
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        LogUtil.a(TAG, "execQueryDataApi");
        if (j < 0 || j > j2) {
            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, i, BleConstants.ERRCODE_BLE_DATA_ERROR);
            callBackJsExecQueryData(str, BleConstants.ERRCODE_BLE_DATA_ERROR, null);
            return;
        }
        HealthKitDictQuery healthKitDictQuery = new HealthKitDictQuery(i, j, j2, new HiHealthDataQueryOption());
        healthKitDictQuery.putValue("isSportType", true);
        if (iuniversalCallback != null) {
            ieb.d(this.mAppContext, healthKitDictQuery, iuniversalCallback);
        } else {
            LogUtil.a(TAG, "resultCallback is null");
        }
    }

    private void execQueryDataApi(final String str, long j, long j2, final int i) {
        LogUtil.a(TAG, "execQueryDataApi");
        ieb.c(this.mAppContext, new HiHealthDataQuery(i, j, j2, new com.huawei.hihealthkit.HiHealthDataQueryOption()), 0, new ResultCallback() { // from class: com.huawei.operation.h5pro.dataproceessor.HealthDataProcessor.1
            @Override // com.huawei.hihealth.listener.ResultCallback
            public void onResult(int i2, Object obj) {
                LogUtil.a(ResultCallback.TAG, "execQuery resultCode = ", Integer.valueOf(i2));
                if (obj instanceof String) {
                    HealthDataProcessor.this.callBackJsExecQueryData(str, String.valueOf(Utils.filterResultCode(i2)), (String) obj);
                } else {
                    if (!(obj instanceof List)) {
                        HealthDataProcessor.this.callBackJsExecQueryData(str, String.valueOf(Utils.filterResultCode(i2)), null);
                        return;
                    }
                    List list = (List) obj;
                    LogUtil.a(ResultCallback.TAG, "execQuery list size = ", Integer.valueOf(list.size()));
                    HealthDataProcessor.this.execQueryHealthDataToJson(i, list, str, i2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execQueryHealthDataToJson(int i, List<idx> list, String str, int i2) {
        LogUtil.a(TAG, "execQueryHealthDataToJson");
        if (koq.b(list)) {
            callBackJsExecQueryData(str, String.valueOf(Utils.filterResultCode(i2)), null);
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (idx idxVar : list) {
                HiHealthDeviceInfo sourceDevice = idxVar.getSourceDevice();
                if (sourceDevice != null && sourceDevice.getDeviceUniqueCode() != null && sourceDevice.getDeviceUniqueCode().equals(this.mUniqueId)) {
                    Map map = idxVar.getMap();
                    if (map != null) {
                        JSONObject buildOneValueObject = buildOneValueObject(i, map);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("startTime", idxVar.getStartTime());
                        jSONObject.put("endTime", idxVar.getEndTime());
                        jSONObject.put("value", buildOneValueObject);
                        jSONArray.put(jSONObject);
                    }
                }
                LogUtil.h(TAG, "execQueryHealthDataToJson deviceUniqueCode not equals mUniqueId");
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("resultCode", String.valueOf(Utils.filterResultCode(i2)));
            jSONObject2.put("type", i);
            jSONObject2.put("data", jSONArray);
            callBackJsExecQueryData(str, String.valueOf(Utils.filterResultCode(i2)), jSONObject2.toString());
        } catch (JSONException unused) {
            LogUtil.b(TAG, "execQueryHealthDataToJson JSONException");
            callBackJsExecQueryData(str, String.valueOf(Utils.filterResultCode(i2)), null);
        }
    }

    private JSONObject buildOneValueObject(int i, Map map) throws JSONException {
        LogUtil.h(TAG, "buildOneValueObject queryType: ", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        if (i == 31001 || i == 31002) {
            jSONObject.put("meta_data", map.get("meta_data"));
            jSONObject.put("simple_data", map.get("simple_data"));
            jSONObject.put("detail_data", map.get("detail_data"));
        } else {
            LogUtil.h(TAG, "buildOneValueObject queryType: ", Integer.valueOf(i));
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackJsExecQueryData(String str, String str2, String str3) {
        H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker;
        LogUtil.a(TAG, "callBackJsExecQueryData resultCode: ", str2);
        if (TextUtils.isEmpty(str) || (h5ProJsCbkInvoker = this.mJsInvoker) == null) {
            LogUtil.h(TAG, "callBackJsExecQueryData function or mJsInvoker is null");
            return;
        }
        if (str3 != null) {
            h5ProJsCbkInvoker.invokeJsFunc(str, str3);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", String.valueOf(str2));
            jSONObject.put("data", "");
        } catch (JSONException unused) {
            LogUtil.b(TAG, "callBackJsExecQueryData fail JSONException");
        }
        this.mJsInvoker.invokeJsFunc(str, jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackJsResult(String str, String str2) {
        LogUtil.a(TAG, "callBackJsResult resultCode: ", str2);
        if (TextUtils.isEmpty(str) || this.mJsInvoker == null) {
            LogUtil.h(TAG, "callbackFunc or mJsInvoker is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str2);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "callBackJsResult fail JSONException");
        }
        this.mJsInvoker.invokeJsFunc(str, jSONObject.toString());
    }

    private void saveDataToPlatform(final BleJsBiOperate bleJsBiOperate, List<idy> list, final int i, boolean z, final String str) {
        LogUtil.a(TAG, "saveDataToPlatform");
        final String str2 = z ? BleConstants.SAVE_MULTIPLE_HEALTH_DATA : BleConstants.SAVE_HEALTH_DATA;
        ieb.d(this.mAppContext, list, new ResultCallback() { // from class: com.huawei.operation.h5pro.dataproceessor.HealthDataProcessor.2
            @Override // com.huawei.hihealth.listener.ResultCallback
            public void onResult(int i2, Object obj) {
                LogUtil.a(ResultCallback.TAG, "saveDataToPlatform resultCode：", Integer.valueOf(i2));
                if (!TextUtils.isEmpty(str)) {
                    if (i2 == 0) {
                        bleJsBiOperate.biApiCalling(HealthDataProcessor.this.mDeviceInfo, HealthDataProcessor.this.mProductId, str2, i, String.valueOf(i2));
                    } else if (!HealthDataProcessor.this.mIsRetryFailed) {
                        bleJsBiOperate.biApiCalling(HealthDataProcessor.this.mDeviceInfo, HealthDataProcessor.this.mProductId, str2, i, String.valueOf(i2));
                        HealthDataProcessor.this.mIsRetryFailed = true;
                    }
                    HealthDataProcessor.this.callBackJsResult(str, String.valueOf(Utils.filterResultCode(i2)));
                    return;
                }
                LogUtil.h(ResultCallback.TAG, "callbackFunc is null");
            }
        });
    }

    public void saveData(String str, String str2) {
        int i;
        JSONObject jSONObject;
        int i2;
        long currentTimeMillis;
        long j;
        LogUtil.a(TAG, "saveData function : ", str2);
        refreshAuthInfo();
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        try {
            jSONObject = new JSONObject(str);
            i2 = jSONObject.getInt("type");
        } catch (JSONException unused) {
            i = 0;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("value");
            LogUtil.a(TAG, "saveData, type = ", Integer.valueOf(i2));
            if (jSONObject.has("startTime")) {
                currentTimeMillis = jSONObject.getLong("startTime");
                j = jSONObject.getLong("endTime");
            } else {
                currentTimeMillis = System.currentTimeMillis();
                j = currentTimeMillis;
            }
            saveDataApi(str2, i2, jSONObject2, currentTimeMillis, j);
        } catch (JSONException unused2) {
            i = i2;
            LogUtil.b(TAG, "saveData JSONException, type = ", Integer.valueOf(i));
            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_HEALTH_DATA, i, BleConstants.ERRCODE_BLE_DATA_ERROR);
            callBackJsResult(str2, BleConstants.ERRCODE_BLE_DATA_ERROR);
        }
    }

    private void saveDataApi(String str, int i, JSONObject jSONObject, long j, long j2) {
        LogUtil.a(TAG, "saveDataApi");
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        if (jSONObject == null) {
            LogUtil.h(TAG, "saveDataApi valueObject = null");
            callBackJsResult(str, BleConstants.PARAM_INVALID_STRING);
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        String oneHiHealthData = getOneHiHealthData(i, arrayList, jSONObject, j, j2);
        LogUtil.a(TAG, "saveDataApi condition = ", oneHiHealthData);
        if (!String.valueOf(0).equals(oneHiHealthData)) {
            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_HEALTH_DATA, i, oneHiHealthData);
            callBackJsResult(str, oneHiHealthData);
        } else {
            if (arrayList.isEmpty()) {
                LogUtil.a(TAG, "saveDataApi dataList is empty");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
                callBackJsResult(str, BleConstants.PARAM_INVALID_STRING);
                return;
            }
            saveDataToPlatform(bleJsBiOperate, arrayList, i, false, str);
        }
    }

    private String getOneHiHealthData(int i, List<idy> list, JSONObject jSONObject, long j, long j2) {
        LogUtil.a(TAG, "getOneHiHealthData");
        String str = BleConstants.PARAM_INVALID_STRING;
        if (i != 31001) {
            LogUtil.h(TAG, "getOneHiHealthData saveType: ", Integer.valueOf(i));
            return str;
        }
        return getOneEcgData(list, jSONObject, j, j2);
    }

    private String getOneEcgData(List<idy> list, JSONObject jSONObject, long j, long j2) {
        LogUtil.a(TAG, "getOneEcgData");
        if (j > j2 || jSONObject == null) {
            return BleConstants.PARAM_INVALID_STRING;
        }
        try {
            idx idxVar = new idx(31001, parseEcgData(jSONObject), j, j2);
            idxVar.setSourceDevice(new HiHealthDeviceInfo(this.mUniqueId, null, null));
            list.add(idxVar);
            return String.valueOf(0);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getOneEcgData JSONException");
            return BleConstants.PARAM_INVALID_STRING;
        }
    }

    private Map parseEcgData(JSONObject jSONObject) throws JSONException {
        String str;
        String str2;
        String str3;
        LogUtil.a(TAG, "parseEcgData");
        ContentValues contentValues = this.mDeviceInfo;
        str = "";
        if (contentValues != null) {
            String asString = contentValues.containsKey("name") ? this.mDeviceInfo.getAsString("name") : "";
            str3 = this.mDeviceInfo.containsKey("deviceType") ? this.mDeviceInfo.getAsString("deviceType") : "";
            str = asString;
            str2 = this.mDeviceInfo.containsKey("deviceId") ? this.mDeviceInfo.getAsString("deviceId") : "";
        } else {
            str2 = "";
            str3 = str2;
        }
        JSONObject jSONObject2 = new JSONObject(jSONObject.getString("meta_data"));
        jSONObject2.put("productId", this.mProductId);
        jSONObject2.put("packageName", ECG_PACKAGE_NAME_PREFIX + this.mProductId);
        jSONObject2.put("deviceName", str);
        jSONObject2.put("deviceType", str3);
        jSONObject2.put("deviceId", str2);
        jSONObject2.put("prodId", ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(this.mProductId));
        HashMap hashMap = new HashMap(16);
        hashMap.put("meta_data", jSONObject2.toString());
        hashMap.put("simple_data", jSONObject.getString("simple_data"));
        hashMap.put("detail_data", jSONObject.getString("detail_data"));
        return hashMap;
    }

    public void saveMultipleData(String str, String str2) {
        int i;
        JSONObject jSONObject;
        int i2;
        LogUtil.a(TAG, "saveMultipleData function : ", str2);
        refreshAuthInfo();
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        try {
            jSONObject = new JSONObject(str);
            i2 = jSONObject.getInt("type");
        } catch (JSONException unused) {
            i = 0;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            LogUtil.a(TAG, "type = ", Integer.valueOf(i2));
            saveMultipleDataApi(str2, i2, jSONArray);
        } catch (JSONException unused2) {
            i = i2;
            LogUtil.b(TAG, "saveMultipleData, the first level of JSONObject parsing error");
            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_MULTIPLE_HEALTH_DATA, i, BleConstants.ERRCODE_BLE_DATA_ERROR);
            callBackJsResult(str2, BleConstants.ERRCODE_BLE_DATA_ERROR);
        }
    }

    private void saveMultipleDataApi(String str, int i, JSONArray jSONArray) {
        LogUtil.a(TAG, "saveMultipleDataApi");
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        if (jSONArray != null && jSONArray.length() != 0 && jSONArray.length() <= 1000) {
            LogUtil.c(TAG, "saveMultipleDataApi dataObject size: ", Integer.valueOf(jSONArray.length()));
            ArrayList arrayList = new ArrayList(10);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    String oneHiHealthData = getOneHiHealthData(i, arrayList, jSONObject.getJSONObject("value"), jSONObject.getLong("startTime"), jSONObject.getLong("endTime"));
                    if (!String.valueOf(0).equals(oneHiHealthData)) {
                        LogUtil.a(TAG, "saveMultipleDataApi index: ", Integer.valueOf(i2), " condition: ", oneHiHealthData);
                        bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_MULTIPLE_HEALTH_DATA, i, oneHiHealthData);
                        callBackJsResult(str, oneHiHealthData);
                        return;
                    }
                } catch (JSONException unused) {
                    LogUtil.b(TAG, "saveMultipleDataApi：parseSkip, there is an error in record.");
                    bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_MULTIPLE_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
                    callBackJsResult(str, BleConstants.PARAM_INVALID_STRING);
                    return;
                }
            }
            if (arrayList.isEmpty()) {
                LogUtil.h(TAG, "saveMultipleDataApi dataList is empty");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_MULTIPLE_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
                callBackJsResult(str, BleConstants.PARAM_INVALID_STRING);
                return;
            }
            saveDataToPlatform(bleJsBiOperate, arrayList, i, true, str);
            return;
        }
        bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_MULTIPLE_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
        LogUtil.h(TAG, "saveMultipleDataApi resultCode = ", BleConstants.PARAM_INVALID_STRING);
        callBackJsResult(str, BleConstants.PARAM_INVALID_STRING);
    }

    public void deleteMultipleHealthData(String str, String str2) {
        LogUtil.a(TAG, "deleteMultipleHealthData function : ", str2);
        refreshAuthInfo();
        int i = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt("type");
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                LogUtil.a(TAG, "deleteMultipleHealthData type = ", Integer.valueOf(i2));
                if (jSONArray.length() == 0) {
                    LogUtil.a(TAG, "deleteMultipleHealthData dataArray.length() = 0");
                    callBackJsResult(str2, String.valueOf(7));
                } else if (i2 == 31001) {
                    deleteMultipleDataApi(i2, str2, jSONArray);
                } else {
                    LogUtil.a(TAG, "deleteMultipleHealthData Types that cannot be deleted");
                    callBackJsResult(str2, String.valueOf(7));
                }
            } catch (JSONException unused) {
                i = i2;
                LogUtil.b(TAG, "deleteMultipleHealthData JSONException, type = ", Integer.valueOf(i));
                callBackJsResult(str2, String.valueOf(4));
            }
        } catch (JSONException unused2) {
        }
    }

    private void deleteMultipleDataApi(int i, final String str, JSONArray jSONArray) {
        LogUtil.a(TAG, "deleteMultipleDataApi");
        try {
            ArrayList arrayList = new ArrayList(10);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                idx idxVar = new idx(i, new HashMap(16), jSONObject.getLong("startTime"), jSONObject.getLong("endTime"));
                idxVar.setSourceDevice(new HiHealthDeviceInfo(this.mUniqueId, null, null));
                arrayList.add(idxVar);
            }
            ieb.b(this.mAppContext, arrayList, new ResultCallback() { // from class: com.huawei.operation.h5pro.dataproceessor.HealthDataProcessor.3
                @Override // com.huawei.hihealth.listener.ResultCallback
                public void onResult(int i3, Object obj) {
                    LogUtil.a(ResultCallback.TAG, "deleteMultipleDataApi resultCode：", Integer.valueOf(i3));
                    HealthDataProcessor.this.callBackJsResult(str, String.valueOf(Utils.filterResultCode(i3)));
                }
            });
        } catch (JSONException unused) {
            LogUtil.b(TAG, "deleteMultipleDataApi JSONException");
            callBackJsResult(str, String.valueOf(4));
        }
    }

    public void stopProcessor() {
        LogUtil.a(TAG, "stopProcessor");
        this.mContext = null;
        this.mAppContext = null;
        this.mInstance = null;
        this.mProductId = null;
        this.mUniqueId = null;
        this.mJsInvoker = null;
        ContentValues contentValues = this.mDeviceInfo;
        if (contentValues != null) {
            contentValues.clear();
            this.mDeviceInfo = null;
        }
    }
}
