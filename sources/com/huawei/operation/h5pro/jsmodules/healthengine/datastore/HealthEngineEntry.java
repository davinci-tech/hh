package com.huawei.operation.h5pro.jsmodules.healthengine.datastore;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.TrendQuery;
import com.huawei.hihealth.device.HiHealthDeviceInfo;
import com.huawei.hihealth.listener.IuniversalCallback;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hihealthkit.HiHealthDataQuery;
import com.huawei.hihealthkit.data.store.HiRealTimeListener;
import com.huawei.hihealthkit.data.store.HiSportDataCallback;
import com.huawei.hihealthkit.data.type.HiHealthDataType;
import com.huawei.hms.network.embedded.q0;
import com.huawei.operation.h5pro.jsmodules.healthengine.service.HealthEngineService;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.idr;
import defpackage.idw;
import defpackage.idx;
import defpackage.idy;
import defpackage.idz;
import defpackage.iea;
import defpackage.ieb;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class HealthEngineEntry extends JsBaseModule {
    private static final String TAG = "H5PRO_HealthEngineEntry";
    private idr mH5AppContext;
    private final List<Long> mReadingHeartRateCallbackIdList = new ArrayList();
    private final List<Long> mReadingRriCallbackIdList = new ArrayList();
    private final List<Long> mRealTimeSportDataCallbackIdList = new ArrayList();

    @JavascriptInterface
    public void startReadingHeartRate(final long j) {
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mReadingHeartRateCallbackIdList.add(Long.valueOf(j));
        LogUtil.i(TAG, "startReadingHeartRate enter");
        ieb.c(this.mH5AppContext, new HiRealTimeListener() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry.1
            @Override // com.huawei.hihealthkit.data.store.HiRealTimeListener
            public void onResult(int i) {
                LogUtil.i(HiRealTimeListener.TAG, "startReadingHeartRate onResult state:" + i);
                if (i == 0) {
                    LogUtil.i(HiRealTimeListener.TAG, "startReadingHeartRate onResult SUCCESS");
                } else {
                    LogUtil.i(HiRealTimeListener.TAG, "startReadingHeartRate onResult FAIL");
                }
            }

            @Override // com.huawei.hihealthkit.data.store.HiRealTimeListener
            public void onChange(int i, String str) {
                LogUtil.i(HiRealTimeListener.TAG, String.format(Locale.ENGLISH, "startReadingHeartRate: onChange stateCode -> %d value -> %s", Integer.valueOf(i), str));
                H5ProJsCbkInvoker<Object> jsCbkInvoker = HealthEngineEntry.this.mH5ProInstance == null ? null : HealthEngineEntry.this.mH5ProInstance.getJsCbkInvoker();
                if (i == 100000) {
                    HealthEngineEntry.this.onSuccessCallback(jsCbkInvoker, j, str);
                } else {
                    HealthEngineEntry.this.onFailureCallback(jsCbkInvoker, j, "startReadingHeartRate onResult FAIL", i);
                }
            }
        });
    }

    @JavascriptInterface
    public void stopReadingHeartRate(final long j) {
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        if (!this.mReadingHeartRateCallbackIdList.isEmpty()) {
            Iterator<Long> it = this.mReadingHeartRateCallbackIdList.iterator();
            while (it.hasNext()) {
                onCompleteCallback(it.next().longValue(), "startReadingHeartRate complete", 0);
            }
        }
        ieb.a(this.mH5AppContext, new HiRealTimeListener() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry.2
            @Override // com.huawei.hihealthkit.data.store.HiRealTimeListener
            public void onResult(int i) {
                LogUtil.i(HiRealTimeListener.TAG, "stopReadingHeartRate onResult state:" + i);
                H5ProJsCbkInvoker<Object> jsCbkInvoker = HealthEngineEntry.this.mH5ProInstance == null ? null : HealthEngineEntry.this.mH5ProInstance.getJsCbkInvoker();
                if (i == 100000) {
                    HealthEngineEntry.this.onSuccessCallback(jsCbkInvoker, j, 0);
                    LogUtil.i(HiRealTimeListener.TAG, "stopReadingHeartRate onResult SUCCESS");
                } else {
                    HealthEngineEntry.this.onFailureCallback(jsCbkInvoker, j, "stopReadingHeartRate onResult FAIL", i);
                    LogUtil.i(HiRealTimeListener.TAG, "stopReadingHeartRate onResult FAIL");
                }
            }

            @Override // com.huawei.hihealthkit.data.store.HiRealTimeListener
            public void onChange(int i, String str) {
                LogUtil.i(HiRealTimeListener.TAG, String.format(Locale.ENGLISH, "stopReadingHeartRate: onChange stateCode -> %d value -> %s", Integer.valueOf(i), str));
            }
        });
    }

    @JavascriptInterface
    public void startReadingRri(final long j) {
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mReadingRriCallbackIdList.add(Long.valueOf(j));
        ieb.d(this.mH5AppContext, new HiRealTimeListener() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry.3
            @Override // com.huawei.hihealthkit.data.store.HiRealTimeListener
            public void onResult(int i) {
                LogUtil.i(HiRealTimeListener.TAG, "startReadingRRI onResult stateCode:" + i);
                H5ProJsCbkInvoker<Object> jsCbkInvoker = HealthEngineEntry.this.mH5ProInstance == null ? null : HealthEngineEntry.this.mH5ProInstance.getJsCbkInvoker();
                if (jsCbkInvoker == null) {
                    return;
                }
                if (i == 100000) {
                    LogUtil.i(HiRealTimeListener.TAG, "startReadingRRI onResult SUCCESS");
                } else {
                    HealthEngineEntry.this.onFailureCallback(jsCbkInvoker, j, "startReadingRRI onResult FAIL", i);
                    LogUtil.i(HiRealTimeListener.TAG, "startReadingRRI onResult FAIL");
                }
            }

            @Override // com.huawei.hihealthkit.data.store.HiRealTimeListener
            public void onChange(int i, String str) {
                HealthEngineEntry.this.onSuccessCallback(HealthEngineEntry.this.mH5ProInstance == null ? null : HealthEngineEntry.this.mH5ProInstance.getJsCbkInvoker(), j, str);
                LogUtil.i(HiRealTimeListener.TAG, String.format(Locale.ENGLISH, "startReadingRri: onChange stateCode -> %d value -> %s", Integer.valueOf(i), str));
            }
        });
    }

    @JavascriptInterface
    public void stopReadingRri(final long j) {
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        if (!this.mReadingRriCallbackIdList.isEmpty()) {
            Iterator<Long> it = this.mReadingRriCallbackIdList.iterator();
            while (it.hasNext()) {
                onCompleteCallback(it.next().longValue(), "startReadingRri complete", 0);
            }
        }
        ieb.e(this.mH5AppContext, new HiRealTimeListener() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry.4
            @Override // com.huawei.hihealthkit.data.store.HiRealTimeListener
            public void onResult(int i) {
                LogUtil.i(HiRealTimeListener.TAG, "stopReadingRRI onResult stateCode:" + i);
                H5ProJsCbkInvoker<Object> jsCbkInvoker = HealthEngineEntry.this.mH5ProInstance == null ? null : HealthEngineEntry.this.mH5ProInstance.getJsCbkInvoker();
                if (i == 100000) {
                    HealthEngineEntry.this.onSuccessCallback(jsCbkInvoker, j, Integer.valueOf(i));
                } else {
                    HealthEngineEntry.this.onFailureCallback(jsCbkInvoker, j, "stopReadingRri onResult FAIL", i);
                }
            }

            @Override // com.huawei.hihealthkit.data.store.HiRealTimeListener
            public void onChange(int i, String str) {
                LogUtil.i(HiRealTimeListener.TAG, "stopReadingRri onChange stateCode: " + i + " value: " + str);
            }
        });
    }

    @JavascriptInterface
    public void startRealTimeSportData(final long j) {
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mRealTimeSportDataCallbackIdList.add(Long.valueOf(j));
        ieb.c(this.mH5AppContext, new HiSportDataCallback() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry.5
            @Override // com.huawei.hihealthkit.data.store.HiSportDataCallback
            public void onResult(int i) {
                LogUtil.i(HiSportDataCallback.TAG, "startRealTimeSportData onResult errorCode = " + i);
                if (i != 0) {
                    HealthEngineEntry.this.onFailureCallback(j, "startRealTimeSportData failed", i);
                }
            }

            @Override // com.huawei.hihealthkit.data.store.HiSportDataCallback
            public void onDataChanged(int i, Bundle bundle) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("state", i);
                    for (String str : bundle.keySet()) {
                        jSONObject.put(str, bundle.get(str));
                    }
                } catch (JSONException e) {
                    LogUtil.i(HiSportDataCallback.TAG, String.valueOf(e));
                }
                HealthEngineEntry.this.onSuccessCallback(HealthEngineEntry.this.mH5ProInstance == null ? null : HealthEngineEntry.this.mH5ProInstance.getJsCbkInvoker(), j, jSONObject);
            }
        });
    }

    @JavascriptInterface
    public void stopRealTimeSportData(final long j) {
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        if (!this.mRealTimeSportDataCallbackIdList.isEmpty()) {
            Iterator<Long> it = this.mRealTimeSportDataCallbackIdList.iterator();
            while (it.hasNext()) {
                onCompleteCallback(it.next().longValue(), "startRealTimeSportData complete", 0);
            }
            this.mRealTimeSportDataCallbackIdList.clear();
        }
        ieb.a(this.mH5AppContext, new HiSportDataCallback() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry.6
            @Override // com.huawei.hihealthkit.data.store.HiSportDataCallback
            public void onDataChanged(int i, Bundle bundle) {
            }

            @Override // com.huawei.hihealthkit.data.store.HiSportDataCallback
            public void onResult(int i) {
                LogUtil.i(HiSportDataCallback.TAG, "stopRealTimeSportData onResult resultCode = " + i);
                HealthEngineEntry.this.onSuccessCallback(HealthEngineEntry.this.mH5ProInstance == null ? null : HealthEngineEntry.this.mH5ProInstance.getJsCbkInvoker(), j, Integer.valueOf(i));
            }
        });
    }

    @JavascriptInterface
    public void getWeight(long j) {
        LogUtil.i(TAG, "getWeight enter");
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.e(h5AppContext, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void getHeight(long j) {
        LogUtil.i(TAG, "getHeight enter");
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.b(h5AppContext, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void sendDeviceCommand(long j, String str) {
        LogUtil.i(TAG, "sendDeviceCommand enter");
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        try {
            String optString = new JSONObject(str).optString("commandOption", "");
            if (TextUtils.isEmpty(optString)) {
                onFailureCallback(j, "commandOption is empty");
            } else {
                ieb.e(this.mH5AppContext, optString, new HealthEngineResultCallback(this.mH5ProInstance, j));
            }
        } catch (JSONException e) {
            onFailureCallback(j, e.getMessage());
            LogUtil.w(TAG, "sendDeviceCommand error", e.getMessage());
        }
    }

    @JavascriptInterface
    public void getDeviceList(long j) {
        LogUtil.i(TAG, "getDeviceList enter");
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.c(h5AppContext, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void getGender(long j) {
        LogUtil.i(TAG, "getGender enter");
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.d(h5AppContext, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void getBirthday(long j) {
        LogUtil.i(TAG, "getBirthday enter");
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.a(h5AppContext, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void execQuery(long j, String str) {
        LogUtil.i(TAG, "execQuery enter");
        HiHealthParamObj hiHealthParamObj = (HiHealthParamObj) GsonUtil.parseJson(str, HiHealthParamObj.class);
        if (hiHealthParamObj == null) {
            onFailureCallback(j, "execQuery: Invalid parameter");
            return;
        }
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.c(h5AppContext, new HiHealthDataQuery(hiHealthParamObj.getType(), hiHealthParamObj.getStartTime(), hiHealthParamObj.getEndTime(), hiHealthParamObj.getQueryOption()), 0, new HealthEngineQueryResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void querySleepWakeTime(long j, String str) {
        LogUtil.i(TAG, "querySleepWakeTime start");
        HiHealthParamObj hiHealthParamObj = (HiHealthParamObj) GsonUtil.parseJson(str, HiHealthParamObj.class);
        if (hiHealthParamObj == null) {
            onFailureCallback(j, "params error");
            return;
        }
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.e(h5AppContext, new HiHealthDataQuery(hiHealthParamObj.getType(), hiHealthParamObj.getStartTime(), hiHealthParamObj.getEndTime(), hiHealthParamObj.getQueryOption()), 0, new HealthEngineQueryResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void getCount(long j, String str) {
        LogUtil.i(TAG, "getCount enter: " + str);
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        HiHealthParamObj hiHealthParamObj = (HiHealthParamObj) GsonUtil.parseJson(str, HiHealthParamObj.class);
        if (hiHealthParamObj == null) {
            onFailureCallback(j, "getCount: Invalid parameter");
        } else {
            ieb.a(this.mH5AppContext, new HiHealthDataQuery(hiHealthParamObj.getType(), hiHealthParamObj.getStartTime(), hiHealthParamObj.getEndTime(), hiHealthParamObj.getQueryOption()), new HealthEngineResultCallback(this.mH5ProInstance, j));
        }
    }

    @JavascriptInterface
    public void queryTrendData(long j, String str) {
        LogUtil.i(TAG, "queryTrendData start");
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "param is empty");
            return;
        }
        if (this.mContext == null) {
            LogUtil.w(TAG, "queryTrendData: context is null");
            return;
        }
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("timeout");
            int[] intArray = HealthEngineService.INSTANCE.toIntArray(jSONObject.optJSONArray("dataTypes"));
            int[] intArray2 = HealthEngineService.INSTANCE.toIntArray(jSONObject.optJSONArray("trendPeriods"));
            boolean optBoolean = jSONObject.optBoolean("isForce", false);
            String optString = jSONObject.optString(ParsedFieldTag.RECORD_DAY, "");
            if (!TextUtils.isEmpty(optString)) {
                idr idrVar = this.mH5AppContext;
                if (intArray == null) {
                    intArray = new int[0];
                }
                if (intArray2 == null) {
                    intArray2 = new int[0];
                }
                ieb.b(idrVar, new TrendQuery(intArray, intArray2, optBoolean, optString), optInt, new TrendDataCallback(this, j));
                return;
            }
            idr idrVar2 = this.mH5AppContext;
            if (intArray == null) {
                intArray = new int[0];
            }
            if (intArray2 == null) {
                intArray2 = new int[0];
            }
            ieb.b(idrVar2, new TrendQuery(intArray, intArray2, optBoolean), optInt, new TrendDataCallback(this, j));
        } catch (JSONException e) {
            LogUtil.e(TAG, "queryTrendData: exception -> ", e);
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void syncData(long j, String str) {
        LogUtil.i(TAG, "syncData");
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "syncData: Invalid parameter");
            return;
        }
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        try {
            int[] intArray = HealthEngineService.INSTANCE.toIntArray(new JSONObject(str).optJSONArray("types"));
            if (intArray == null) {
                onFailureCallback(j, "syncData: syncDataTypes is null");
            } else {
                ieb.d(this.mH5AppContext, intArray, new HealthEngineResultCallback(this.mH5ProInstance, j));
            }
        } catch (JSONException e) {
            LogUtil.e(TAG, "syncData: exception -> " + e.getMessage());
            onFailureCallback(j, e.getMessage());
        }
    }

    @JavascriptInterface
    public void saveSample(long j, String str) {
        LogUtil.i(TAG, "saveSample enter");
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        HiHealthParamObj hiHealthParamObj = (HiHealthParamObj) GsonUtil.parseContainsMapJson(str, HiHealthParamObj.class);
        if (hiHealthParamObj == null) {
            onFailureCallback(j, "saveSample: Invalid parameter");
            return;
        }
        idy hiHealthData = getHiHealthData(hiHealthParamObj);
        HiHealthDeviceObj deviceInfo = hiHealthParamObj.getDeviceInfo();
        if (deviceInfo != null) {
            if (TextUtils.isEmpty(deviceInfo.getDeviceUniqueCode())) {
                LogUtil.e(TAG, "Device unique code is empty, please check the parameters.");
                onFailureCallback(j, "param error, please check DeviceUniqueCode");
                return;
            } else {
                LogUtil.i(TAG, "Set device info");
                hiHealthData.setSourceDevice(new HiHealthDeviceInfo(deviceInfo.getDeviceUniqueCode(), deviceInfo.getDeviceName(), deviceInfo.getDeviceModel()));
            }
        }
        ieb.b(this.mH5AppContext, hiHealthData, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void saveSamples(long j, String str) {
        LogUtil.i(TAG, "saveSamples enter");
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        ArrayList arrayList = new ArrayList();
        List<HiHealthParamObj> parseParamListJson = parseParamListJson(str);
        if (parseParamListJson == null || parseParamListJson.isEmpty()) {
            onFailureCallback(j, "saveSamples: Invalid parameter");
            return;
        }
        for (HiHealthParamObj hiHealthParamObj : parseParamListJson) {
            idy hiHealthData = getHiHealthData(hiHealthParamObj);
            HiHealthDeviceObj deviceInfo = hiHealthParamObj.getDeviceInfo();
            if (deviceInfo != null) {
                if (TextUtils.isEmpty(deviceInfo.getDeviceUniqueCode())) {
                    LogUtil.e(TAG, "Device unique code is empty, please check the parameters.");
                    onFailureCallback(j, "param error, please check DeviceUniqueCode");
                    return;
                } else {
                    LogUtil.i(TAG, "Set device info");
                    hiHealthData.setSourceDevice(new HiHealthDeviceInfo(deviceInfo.getDeviceUniqueCode(), deviceInfo.getDeviceName(), deviceInfo.getDeviceModel()));
                }
            }
            arrayList.add(hiHealthData);
        }
        ieb.d(this.mH5AppContext, arrayList, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void deleteSample(long j, String str) {
        LogUtil.i(TAG, "deleteSample enter");
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        HiHealthParamObj hiHealthParamObj = (HiHealthParamObj) GsonUtil.parseContainsMapJson(str, HiHealthParamObj.class);
        if (hiHealthParamObj == null) {
            onFailureCallback(j, "deleteSample: Invalid parameter");
        } else {
            ieb.c(this.mH5AppContext, getHiHealthData(hiHealthParamObj), new HealthEngineResultCallback(this.mH5ProInstance, j));
        }
    }

    @JavascriptInterface
    public void deleteSamples(long j, String str) {
        LogUtil.i(TAG, "deleteSamples enter");
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        ArrayList arrayList = new ArrayList();
        List<HiHealthParamObj> parseParamListJson = parseParamListJson(str);
        if (parseParamListJson == null || parseParamListJson.isEmpty()) {
            onFailureCallback(j, "deleteSamples: Invalid parameter");
            return;
        }
        Iterator<HiHealthParamObj> it = parseParamListJson.iterator();
        while (it.hasNext()) {
            arrayList.add(getHiHealthData(it.next()));
        }
        ieb.b(this.mH5AppContext, arrayList, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    private idy getHiHealthData(HiHealthParamObj hiHealthParamObj) {
        idy idxVar;
        HiHealthDataType.Category d = ieb.d(this.mH5AppContext, hiHealthParamObj.getType());
        LogUtil.i(TAG, "getHiHealthData: category -> " + d);
        if (d == HiHealthDataType.Category.SET || d == HiHealthDataType.Category.BUSINESS) {
            idxVar = new idx(hiHealthParamObj.getType(), buildHiHealthMap(hiHealthParamObj.getMap()), hiHealthParamObj.getStartTime(), hiHealthParamObj.getEndTime());
            idxVar.setUpdateTime(hiHealthParamObj.getModifyTime());
            idxVar.setMetaData(hiHealthParamObj.getMetadata());
        } else if (d == HiHealthDataType.Category.POINT || d == HiHealthDataType.Category.STAT) {
            idxVar = new idz(hiHealthParamObj.getType(), hiHealthParamObj.getStartTime(), hiHealthParamObj.getEndTime(), hiHealthParamObj.getValue(), hiHealthParamObj.getHiHealthUnit());
            idxVar.setMetaData(hiHealthParamObj.getMetadata());
        } else if (d == HiHealthDataType.Category.SEQUENCE) {
            idxVar = new iea(hiHealthParamObj.getType(), hiHealthParamObj.getStartTime(), hiHealthParamObj.getEndTime(), hiHealthParamObj.getSequenceContent(), hiHealthParamObj.getMetadata());
            idxVar.setString("simple_data", hiHealthParamObj.getSimpleData());
        } else {
            idxVar = new idw(hiHealthParamObj.getType(), hiHealthParamObj.getStartTime(), hiHealthParamObj.getEndTime());
        }
        ContentValues buildHiHealthValueHolder = buildHiHealthValueHolder(hiHealthParamObj.getValueHolder());
        if (buildHiHealthValueHolder.size() == 0) {
            return idxVar;
        }
        ContentValues valueHolder = idxVar.getValueHolder();
        if (valueHolder == null) {
            idxVar.setValueHolder(buildHiHealthValueHolder);
        } else {
            valueHolder.putAll(buildHiHealthValueHolder);
        }
        return idxVar;
    }

    private Map<Integer, Object> buildHiHealthMap(List<HiHealthMapItemObj> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (HiHealthMapItemObj hiHealthMapItemObj : list) {
            hashMap.put(Integer.valueOf(hiHealthMapItemObj.getType()), hiHealthMapItemObj.getValue());
        }
        return hashMap;
    }

    private ContentValues buildHiHealthValueHolder(Map<String, Object> map) {
        ContentValues contentValues = new ContentValues();
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof String) {
                    contentValues.put(entry.getKey(), (String) value);
                } else if (value instanceof Boolean) {
                    contentValues.put(entry.getKey(), (Boolean) value);
                } else if (value instanceof Integer) {
                    contentValues.put(entry.getKey(), (Integer) value);
                } else if (value instanceof Long) {
                    contentValues.put(entry.getKey(), (Long) value);
                } else if (value instanceof Double) {
                    contentValues.put(entry.getKey(), (Double) value);
                } else {
                    LogUtil.w(TAG, "buildHiHealthContentValues: Unknown type of data -> " + value);
                }
            }
        }
        return contentValues;
    }

    static class HealthEngineResultCallback implements ResultCallback {
        private final long mCallbackId;
        private final WeakReference<H5ProJsCbkInvoker<Object>> mWrH5ProJsCbkInvoker;

        HealthEngineResultCallback(H5ProInstance h5ProInstance, long j) {
            this.mWrH5ProJsCbkInvoker = h5ProInstance == null ? null : new WeakReference<>(h5ProInstance.getJsCbkInvoker());
            this.mCallbackId = j;
        }

        @Override // com.huawei.hihealth.listener.ResultCallback
        public void onResult(int i, Object obj) {
            LogUtil.d(ResultCallback.TAG, "HealthEngineResultCallback onResult " + i);
            H5ProJsCbkInvoker h5ProJsCbkInvoker = (H5ProJsCbkInvoker) GeneralUtil.getReferent(this.mWrH5ProJsCbkInvoker);
            if (h5ProJsCbkInvoker == null) {
                LogUtil.w(ResultCallback.TAG, "HealthEngineResultCallback: h5ProJsCbkInvoker is null");
            } else if (i == 0) {
                h5ProJsCbkInvoker.onSuccess(obj, this.mCallbackId);
            } else {
                h5ProJsCbkInvoker.onFailure(i, GsonUtil.toJson(obj), this.mCallbackId);
            }
        }
    }

    static class HealthEngineQueryResultCallback implements ResultCallback {
        private final long mCallbackId;
        private final WeakReference<H5ProJsCbkInvoker<Object>> mWrH5ProJsCbkInvoker;

        HealthEngineQueryResultCallback(H5ProInstance h5ProInstance, long j) {
            this.mWrH5ProJsCbkInvoker = h5ProInstance == null ? null : new WeakReference<>(h5ProInstance.getJsCbkInvoker());
            this.mCallbackId = j;
        }

        @Override // com.huawei.hihealth.listener.ResultCallback
        public void onResult(int i, Object obj) {
            LogUtil.i(ResultCallback.TAG, "HealthEngineQueryResultCallback onResult: " + i);
            H5ProJsCbkInvoker h5ProJsCbkInvoker = (H5ProJsCbkInvoker) GeneralUtil.getReferent(this.mWrH5ProJsCbkInvoker);
            if (h5ProJsCbkInvoker == null) {
                LogUtil.w(ResultCallback.TAG, "HealthEngineQueryResultCallback: h5ProJsCbkInvoker is null");
                return;
            }
            if (i != 0) {
                h5ProJsCbkInvoker.onFailure(i, new Gson().toJson(obj), this.mCallbackId);
                return;
            }
            if (obj == null) {
                h5ProJsCbkInvoker.onSuccess(null, this.mCallbackId);
                return;
            }
            if (obj instanceof List) {
                List<?> list = (List) obj;
                LogUtil.i(ResultCallback.TAG, "resultObjectList size " + list.size());
                if (list.isEmpty()) {
                    h5ProJsCbkInvoker.onSuccess(list.toString(), this.mCallbackId);
                    return;
                } else {
                    h5ProJsCbkInvoker.onSuccess(parseCallbackData(list).toString(), this.mCallbackId);
                    return;
                }
            }
            h5ProJsCbkInvoker.onSuccess(obj, this.mCallbackId);
        }

        private List<JSONObject> parseCallbackData(List<?> list) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                JSONObject jSONObject = new JSONObject();
                try {
                    if (obj instanceof idx) {
                        idx idxVar = (idx) obj;
                        jSONObject.put("type", idxVar.getType());
                        jSONObject.put("startTime", idxVar.getStartTime());
                        jSONObject.put("endTime", idxVar.getEndTime());
                        jSONObject.put("updateTime", idxVar.getUpdateTime());
                        jSONObject.put("sourceDevice", parseSourceDevice(idxVar));
                        jSONObject.put(q0.j, parseValuesData(idxVar.getMap()));
                        jSONObject.put("metadata", idxVar.getMetaData());
                    } else if (obj instanceof idz) {
                        idz idzVar = (idz) obj;
                        jSONObject.put("type", idzVar.getType());
                        jSONObject.put("startTime", idzVar.getStartTime());
                        jSONObject.put("endTime", idzVar.getEndTime());
                        jSONObject.put("updateTime", idzVar.getUpdateTime());
                        jSONObject.put("sourceDevice", parseSourceDevice(idzVar));
                        jSONObject.put("value", idzVar.getValue());
                        jSONObject.put("pointUnit", idzVar.getPointUnit());
                        jSONObject.put("doubleValue", idzVar.getDoubleValue());
                        jSONObject.put("stringValue", idzVar.getStringValue());
                    } else if (!(obj instanceof idw)) {
                        LogUtil.e(ResultCallback.TAG, "Unknown type of data");
                    } else {
                        idy idyVar = (idw) obj;
                        jSONObject.put("type", idyVar.getType());
                        jSONObject.put("startTime", idyVar.getStartTime());
                        jSONObject.put("endTime", idyVar.getEndTime());
                        jSONObject.put("updateTime", idyVar.getUpdateTime());
                        jSONObject.put("sourceDevice", parseSourceDevice(idyVar));
                    }
                } catch (JSONException unused) {
                    LogUtil.e(ResultCallback.TAG, "parseCallbackData: itemCallbackObject put error");
                }
                arrayList.add(jSONObject);
            }
            return arrayList;
        }

        private JSONObject parseValuesData(Map<?, ?> map) {
            JSONObject jSONObject = new JSONObject();
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    try {
                        jSONObject.put(String.valueOf(entry.getKey()), entry.getValue());
                    } catch (JSONException unused) {
                        LogUtil.e(ResultCallback.TAG, "values put error -> " + entry.getKey());
                    }
                }
            }
            return jSONObject;
        }

        private JSONObject parseSourceDevice(idy idyVar) throws JSONException {
            HiHealthDeviceInfo sourceDevice = idyVar.getSourceDevice();
            JSONObject jSONObject = new JSONObject();
            if (sourceDevice != null) {
                jSONObject.put("deviceName", sourceDevice.getDeviceName());
                jSONObject.put("deviceUniqueCode", sourceDevice.getDeviceUniqueCode());
                jSONObject.put("deviceModel", sourceDevice.getDeviceModel());
                String deviceType = sourceDevice.getDeviceType();
                if (!TextUtils.isEmpty(deviceType)) {
                    jSONObject.put("deviceType", deviceType);
                }
            } else {
                LogUtil.e(ResultCallback.TAG, "Device Info is empty");
            }
            return jSONObject;
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        this.mH5AppContext = null;
        super.onDestroy();
    }

    @JavascriptInterface
    public void queryData(final long j, String str) {
        LogUtil.i(TAG, "queryData start");
        if (this.mContext == null) {
            LogUtil.w(TAG, "queryData: context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "param is empty");
            return;
        }
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        HealthKitDictQuery parseQueryParam = HealthEngineService.INSTANCE.parseQueryParam(str);
        if (parseQueryParam == null) {
            onFailureCallback(j, "param parse error");
        } else {
            ieb.d(this.mH5AppContext, parseQueryParam, new IuniversalCallback() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry.7
                @Override // com.huawei.hihealth.listener.IuniversalCallback
                public void onResult(int i, Object obj, String str2) {
                    LogUtil.i(IuniversalCallback.TAG, "onResult: errCode: " + i);
                    if (i == 0) {
                        HealthEngineService.INSTANCE.onQueryResult(HealthEngineEntry.this.mH5ProInstance == null ? null : HealthEngineEntry.this.mH5ProInstance.getJsCbkInvoker(), j, obj);
                    } else {
                        HealthEngineEntry.this.onFailureCallback(j, str2, i);
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void stopSport(long j) {
        LogUtil.i(TAG, "stopSport enter");
        if (this.mContext == null) {
            LogUtil.w(TAG, "stopSport: context is null");
            return;
        }
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.h(h5AppContext, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    private List<HiHealthParamObj> parseParamListJson(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return (List) new Gson().fromJson(str, new TypeToken<List<HiHealthParamObj>>() { // from class: com.huawei.operation.h5pro.jsmodules.healthengine.datastore.HealthEngineEntry.8
                }.getType());
            } catch (JsonSyntaxException unused) {
                LogUtil.e(TAG, "parseParamListJson: JsonSyntaxException");
            }
        }
        return Collections.emptyList();
    }

    @JavascriptInterface
    public void startReadingAtrial(long j) {
        LogUtil.i(TAG, "startReadingAtrial start");
        if (this.mContext == null) {
            LogUtil.w(TAG, "startReadingAtrial: context is null");
            return;
        }
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.j(h5AppContext, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void stopReadingAtrial(long j) {
        LogUtil.i(TAG, "stopReadingAtrial start");
        if (this.mContext == null) {
            LogUtil.w(TAG, "stopReadingAtrial: context is null");
            return;
        }
        idr h5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        this.mH5AppContext = h5AppContext;
        ieb.g(h5AppContext, new HealthEngineResultCallback(this.mH5ProInstance, j));
    }

    @JavascriptInterface
    public void startSportEx(long j, String str) {
        LogUtil.i(TAG, "startSportEx enter");
        if (this.mContext == null) {
            LogUtil.w(TAG, "startSportEx: context is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "param is empty");
            return;
        }
        this.mH5AppContext = HealthEngineService.INSTANCE.getH5AppContext(this.mContext, this.mH5ProInstance);
        StartSportParam startSportParam = new StartSportParam();
        try {
            LogUtil.i(TAG, "start parses startSportEx param");
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof Integer) {
                    startSportParam.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Double) {
                    startSportParam.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof String) {
                    startSportParam.putString(next, (String) obj);
                } else if (obj instanceof Boolean) {
                    startSportParam.putBoolean(next, ((Boolean) obj).booleanValue());
                } else {
                    LogUtil.w(TAG, "value: Only Integer, Double,Boolean, and String are supported. " + next);
                }
            }
            ieb.c(this.mH5AppContext, startSportParam, new HealthEngineResultCallback(this.mH5ProInstance, j));
        } catch (JSONException e) {
            onFailureCallback(j, e.getMessage());
            LogUtil.w(TAG, "startSportEx error", e.getMessage());
        }
    }

    static class TrendDataCallback implements IuniversalCallback {
        private final long mCallbackId;
        private final WeakReference<HealthEngineEntry> mWrHealthEngineEntry;

        TrendDataCallback(HealthEngineEntry healthEngineEntry, long j) {
            this.mWrHealthEngineEntry = new WeakReference<>(healthEngineEntry);
            this.mCallbackId = j;
        }

        @Override // com.huawei.hihealth.listener.IuniversalCallback
        public void onResult(int i, Object obj, String str) {
            LogUtil.i(IuniversalCallback.TAG, "TrendDataCallback#onResult: errCode -> " + i);
            HealthEngineEntry healthEngineEntry = (HealthEngineEntry) GeneralUtil.getReferent(this.mWrHealthEngineEntry);
            if (healthEngineEntry == null) {
                LogUtil.w(IuniversalCallback.TAG, "TrendDataCallback#onResult: healthEngineEntry is null");
                return;
            }
            if (i == 0 || i == 1023 || i == 1004) {
                healthEngineEntry.onSuccessCallback(this.mCallbackId, obj);
            } else {
                healthEngineEntry.onFailureCallback(this.mCallbackId, str, i);
            }
            if (i != 1023) {
                healthEngineEntry.onCompleteCallback(this.mCallbackId, str, i);
            }
        }
    }
}
