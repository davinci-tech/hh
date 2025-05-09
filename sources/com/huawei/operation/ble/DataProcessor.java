package com.huawei.operation.ble;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.beans.HealthDataQueryOption;
import com.huawei.operation.share.ResultCallback;
import com.huawei.operation.utils.BleJsBiOperate;
import com.huawei.operation.utils.Utils;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DataProcessor {
    private static final int HASH_MAP_DEFAULT_CAPACITY = 16;
    private static final int SUBSCRIPT_ZERO = 0;
    private static final String TAG = "DataProcessor";
    private HashMap<String, String> mCallbackMap;
    private ContentValues mDeviceInfo;
    private Handler mHandler;
    private boolean mIsRetryFailed;
    private String mProductId;
    private String mUniqueId;
    private String mUserUuid;
    private int[] mWeightDataIntTypes = BleConstants.getWeightDataIntTypesClone();
    private int[] mBloodSugarDataTypes = BleConstants.getBloodSugarDataTypesClone();
    private String[] mBloodSugarDataKeyTypes = BleConstants.getBloodSugarDataKeyTypesClone();
    private int[] mBloodPressureDataTypes = BleConstants.getBloodPressureDataValueTypesClone();
    private String[] mWeightDataTypesKey = BleConstants.getWeightDataTypesKeyClone();
    private String[] mWeightDataTypes = BleConstants.getWeightDataJsonTypesClone();

    public DataProcessor(Builder builder) {
        this.mDeviceInfo = builder.mDeviceInfo;
        this.mProductId = builder.mProductId;
        this.mUniqueId = builder.mUniqueId;
        this.mHandler = builder.mHandler;
        this.mCallbackMap = builder.mCallbackMap;
    }

    public static class Builder {
        private HashMap<String, String> mCallbackMap;
        private ContentValues mDeviceInfo;
        private Handler mHandler;
        private String mProductId;
        private String mUniqueId;

        public Builder setDeviceInfo(ContentValues contentValues) {
            this.mDeviceInfo = contentValues;
            return this;
        }

        public Builder setProductId(String str) {
            this.mProductId = str;
            return this;
        }

        public Builder setUniqueId(String str) {
            this.mUniqueId = str;
            return this;
        }

        public Builder setHandler(Handler handler) {
            this.mHandler = handler;
            return this;
        }

        public Builder setCallbackMap(HashMap<String, String> hashMap) {
            this.mCallbackMap = hashMap;
            return this;
        }

        public DataProcessor build() {
            return new DataProcessor(this);
        }
    }

    public void setIsRetryFailed(boolean z) {
        this.mIsRetryFailed = z;
    }

    public void setUserUuid(String str) {
        this.mUserUuid = str;
    }

    private void callBackJsExecQueryData(String str, String str2, boolean z) {
        LogUtil.a(TAG, "callBackJsExecQueryData");
        Bundle bundle = new Bundle();
        JSONObject jSONObject = new JSONObject();
        bundle.putString("function", this.mCallbackMap.get(z ? BleConstants.ON_HAS_HEALTH_DATA_RESULT_NAME : BleConstants.ON_GET_DATA_RESULT_NAME));
        bundle.putString("errCode", str);
        if (str2 != null) {
            bundle.putString("data", str2);
        } else {
            try {
                jSONObject.put("resultCode", str);
                jSONObject.put("data", "");
            } catch (JSONException unused) {
                LogUtil.b(TAG, "callBackJsExecQueryData JSONException");
            }
            bundle.putString("data", jSONObject.toString());
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 10010;
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    private void callBackJsResult(String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("function", this.mCallbackMap.get(str));
        bundle.putString("errCode", str2);
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.setData(bundle);
        this.mHandler.sendMessage(obtainMessage);
    }

    private void saveDataToPlatform(final BleJsBiOperate bleJsBiOperate, List<HiHealthData> list, final int i, boolean z, final String str) {
        LogUtil.a(TAG, "saveHealthDataToPlatform");
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(list);
        final String str2 = z ? BleConstants.SAVE_MULTIPLE_HEALTH_DATA : BleConstants.SAVE_HEALTH_DATA;
        BleJsDataApi.getInstance().saveSample(hiDataInsertOption, 3, new ResultCallback() { // from class: com.huawei.operation.ble.DataProcessor$$ExternalSyntheticLambda3
            @Override // com.huawei.operation.share.ResultCallback
            public final void onResult(int i2, Object obj) {
                DataProcessor.this.m693x67952e1c(i, str, bleJsBiOperate, str2, i2, obj);
            }
        });
    }

    /* renamed from: lambda$saveDataToPlatform$0$com-huawei-operation-ble-DataProcessor, reason: not valid java name */
    /* synthetic */ void m693x67952e1c(int i, String str, BleJsBiOperate bleJsBiOperate, String str2, int i2, Object obj) {
        LogUtil.a(TAG, "saveHealthDataToPlatform resultCode：", Integer.valueOf(i2));
        if (i2 == 0 && (i == 10001 || i == 10002 || i == 10006)) {
            LogUtil.a(TAG, "saveHealthDataToPlatform: syncCloudAfterInsert");
            Utils.syncCloudAfterInsert();
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "callbackFunc is null");
            return;
        }
        if (i2 == 0) {
            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, str2, i, String.valueOf(i2));
        } else if (!this.mIsRetryFailed) {
            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, str2, i, String.valueOf(i2));
            this.mIsRetryFailed = true;
        }
        callBackJsResult(str, 10009, String.valueOf(Utils.filterResultCode(i2)));
    }

    public void execQuery(String str) {
        int i;
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
                    if (i2 != 2104 && i2 != HiHealthDataType.b) {
                        if (i2 == 2103) {
                            execQueryDataApi(j, j2, i2, null);
                            return;
                        }
                        if (i2 == 10006) {
                            execQueryWeight(j, j2);
                            return;
                        }
                        if (i2 == 10001) {
                            HealthDataQueryOption buildBloodSugarQueryOption = buildBloodSugarQueryOption(i2, jSONObject, bleJsBiOperate);
                            if (buildBloodSugarQueryOption != null) {
                                execQueryDataApi(j, j2, i2, buildBloodSugarQueryOption);
                                return;
                            }
                            return;
                        }
                        if (i2 == 10002) {
                            execQueryBloodPressure(j, j2);
                            return;
                        }
                        if (i2 == 30029) {
                            execQueryDataApi(j, j2, i2, null);
                            return;
                        } else if (i2 == 2109) {
                            execQueryDataApi(j, j2, i2, null);
                            return;
                        } else {
                            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, i2, BleConstants.PARAM_INVALID_STRING);
                            callBackJsExecQueryData(BleConstants.PARAM_INVALID_STRING, null, false);
                            return;
                        }
                    }
                    execQueryDataApi(j, j2, i2, null);
                    return;
                }
                LogUtil.h(TAG, "execQuery startTime < 0 or startTime > endTime");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, i2, BleConstants.PARAM_INVALID_STRING);
                callBackJsExecQueryData(BleConstants.PARAM_INVALID_STRING, null, false);
            } catch (JSONException unused) {
                i = i2;
                LogUtil.b(TAG, "execQueryData JSONException");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
                callBackJsExecQueryData(BleConstants.PARAM_INVALID_STRING, null, false);
            }
        } catch (JSONException unused2) {
            i = 0;
        }
    }

    private HealthDataQueryOption buildBloodSugarQueryOption(int i, JSONObject jSONObject, BleJsBiOperate bleJsBiOperate) {
        int optInt = jSONObject.optInt(BleConstants.LIMIT);
        int optInt2 = optInt != 0 ? 0 : jSONObject.optInt(BleConstants.QUERY_KIND);
        if (optInt < 0 || optInt2 < 0 || optInt2 > 2) {
            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
            callBackJsExecQueryData(BleConstants.PARAM_INVALID_STRING, null, false);
            return null;
        }
        HealthDataQueryOption healthDataQueryOption = new HealthDataQueryOption();
        healthDataQueryOption.setQueryKind(optInt2);
        healthDataQueryOption.setLimit(optInt);
        return healthDataQueryOption;
    }

    public void hasHealthDataQuery(String str) {
        int i;
        LogUtil.a(TAG, "hasHealthDataQuery");
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt("type");
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                LinkedHashMap linkedHashMap = new LinkedHashMap(16);
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    linkedHashMap.put(Long.valueOf(jSONArray.getJSONObject(i3).getLong("startTime")), 0);
                }
                if (jSONArray.length() >= 1) {
                    long j = jSONArray.getJSONObject(0).getLong("startTime");
                    long j2 = jSONArray.getJSONObject(jSONArray.length() - 1).getLong("startTime");
                    LogUtil.a(TAG, "hasHealthDataQuery type = ", Integer.valueOf(i2), ",startTime = ", Long.valueOf(j), ",endTime = ", Long.valueOf(j2));
                    if (j >= 0 && j <= j2) {
                        if (i2 != 10001) {
                            LogUtil.h(TAG, "hasHealthDataQuery type: ", Integer.valueOf(i2));
                            callBackJsExecQueryData(BleConstants.PARAM_INVALID_STRING, null, true);
                            return;
                        } else {
                            HealthDataQueryOption healthDataQueryOption = new HealthDataQueryOption();
                            healthDataQueryOption.setStartTimes(linkedHashMap);
                            execQueryDataApi(j, j2, i2, healthDataQueryOption);
                            return;
                        }
                    }
                    LogUtil.a(TAG, "hasHealthDataQuery startTime < 0 or startTime > endTime");
                    bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.HAS_HEALTH_DATA, i2, BleConstants.PARAM_INVALID_STRING);
                    callBackJsExecQueryData(BleConstants.PARAM_INVALID_STRING, null, true);
                    return;
                }
                LogUtil.h(TAG, "hasHealthDataQuery dataArray length is 0");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.HAS_HEALTH_DATA, i2, BleConstants.PARAM_INVALID_STRING);
                callBackJsExecQueryData(BleConstants.PARAM_INVALID_STRING, null, true);
            } catch (JSONException unused) {
                i = i2;
                LogUtil.b(TAG, "hasHealthDataQuery JSONException");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.HAS_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
                callBackJsExecQueryData(BleConstants.PARAM_INVALID_STRING, null, true);
            }
        } catch (JSONException unused2) {
            i = 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x005f, code lost:
    
        return com.huawei.operation.ble.BleConstants.PARAM_INVALID_STRING;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getOneBloodSugar(java.util.List<com.huawei.hihealth.HiHealthData> r12, org.json.JSONObject r13, long r14, long r16) {
        /*
            r11 = this;
            r6 = r11
            r7 = r13
            java.lang.String r0 = "getOneBloodSugar"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "DataProcessor"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 0
            r8 = r0
        Lf:
            java.lang.String[] r0 = r6.mBloodSugarDataKeyTypes
            int r1 = r0.length
            if (r8 >= r1) goto L64
            r0 = r0[r8]
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L60
            java.lang.String[] r0 = r6.mBloodSugarDataKeyTypes
            r0 = r0[r8]
            double r9 = r13.optDouble(r0)
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 < 0) goto L5d
            r0 = 4629841154425225216(0x4040800000000000, double:33.0)
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L34
            goto L5d
        L34:
            int[] r0 = r6.mBloodSugarDataTypes
            r1 = r0[r8]
            r0 = r11
            r2 = r14
            r4 = r16
            com.huawei.hihealth.HiHealthData r0 = r0.buildHiHealthData(r1, r2, r4)
            r0.setValue(r9)
            com.huawei.hihealth.data.model.HiBloodSugarMetaData r1 = new com.huawei.hihealth.data.model.HiBloodSugarMetaData
            r1.<init>()
            java.lang.String r2 = "isConfirmed"
            boolean r2 = r13.optBoolean(r2)
            r1.setConfirmed(r2)
            java.lang.String r1 = com.huawei.hihealth.util.HiJsonUtil.e(r1)
            r0.setMetaData(r1)
            r1 = r12
            r12.add(r0)
            goto L61
        L5d:
            java.lang.String r0 = com.huawei.operation.ble.BleConstants.PARAM_INVALID_STRING
            return r0
        L60:
            r1 = r12
        L61:
            int r8 = r8 + 1
            goto Lf
        L64:
            java.lang.String r0 = "0"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.ble.DataProcessor.getOneBloodSugar(java.util.List, org.json.JSONObject, long, long):java.lang.String");
    }

    private void execQueryBloodPressure(long j, long j2) {
        LogUtil.a(TAG, "execQueryBloodPressure");
        final BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        HiAggregateOption hiAggregateOption = getHiAggregateOption(j, j2, new String[]{BleConstants.BLOOD_PRESSURE}, new int[]{10002});
        hiAggregateOption.setDeviceUuid(this.mUniqueId);
        hiAggregateOption.setReadType(2);
        HiAggregateOption hiAggregateOption2 = getHiAggregateOption(j, j2, new String[]{BleConstants.BLOODPRESSURE_SPHYGMUS}, new int[]{2018});
        hiAggregateOption2.setDeviceUuid(this.mUniqueId);
        hiAggregateOption2.setReadType(2);
        BleJsDataApi.getInstance().execQueryBloodPressure(hiAggregateOption, hiAggregateOption2, new ResultCallback() { // from class: com.huawei.operation.ble.DataProcessor$$ExternalSyntheticLambda0
            @Override // com.huawei.operation.share.ResultCallback
            public final void onResult(int i, Object obj) {
                DataProcessor.this.m690x9ee78428(bleJsBiOperate, i, obj);
            }
        });
    }

    /* renamed from: lambda$execQueryBloodPressure$1$com-huawei-operation-ble-DataProcessor, reason: not valid java name */
    /* synthetic */ void m690x9ee78428(BleJsBiOperate bleJsBiOperate, int i, Object obj) {
        LogUtil.a(TAG, "execQueryBloodPressure resultCode = ", Integer.valueOf(i));
        bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, 10002, String.valueOf(i));
        if (obj instanceof String) {
            callBackJsExecQueryData(String.valueOf(i), (String) obj, false);
        } else {
            callBackJsExecQueryData(String.valueOf(i), null, false);
        }
    }

    private HiAggregateOption getHiAggregateOption(long j, long j2, String[] strArr, int[] iArr) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("");
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setTimeRange(j, j2);
        return hiAggregateOption;
    }

    private String getOneBloodPressure(List<HiHealthData> list, JSONObject jSONObject, long j, long j2) {
        LogUtil.a(TAG, "getOneBloodPressure");
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value()), Double.valueOf(jSONObject.optDouble(BleConstants.BLOODPRESSURE_SYSTOLIC)));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value()), Double.valueOf(jSONObject.optDouble(BleConstants.BLOODPRESSURE_DIASTOLIC)));
        if (jSONObject.has(BleConstants.BLOODPRESSURE_SPHYGMUS)) {
            hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()), Double.valueOf(jSONObject.optDouble(BleConstants.BLOODPRESSURE_SPHYGMUS)));
        }
        HiHealthData buildHiHealthData = buildHiHealthData(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value(), j, j2);
        buildHiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        list.add(buildHiHealthData);
        if (!jSONObject.has(BleConstants.REST_HEARTRATE)) {
            return "0";
        }
        double optDouble = jSONObject.optDouble(BleConstants.REST_HEARTRATE);
        HiHealthData buildHiHealthData2 = buildHiHealthData(2018, j, j2);
        buildHiHealthData2.setValue(optDouble);
        list.add(buildHiHealthData2);
        return "0";
    }

    private HiHealthData buildHiHealthData(int i, long j, long j2) {
        HiHealthData hiHealthData = new HiHealthData(i);
        hiHealthData.setDeviceUuid(this.mUniqueId);
        hiHealthData.setTimeInterval(j, j2);
        return hiHealthData;
    }

    private boolean checkDataKeyValid(int i, JSONObject jSONObject) {
        LogUtil.a(TAG, "checkDataKeyValid, type = ", Integer.valueOf(i));
        if (i == 10002) {
            return jSONObject.has(BleConstants.BLOODPRESSURE_SYSTOLIC) && jSONObject.has(BleConstants.BLOODPRESSURE_DIASTOLIC);
        }
        if (i == 10006) {
            return true;
        }
        if (i == 2103) {
            return jSONObject.has(BleConstants.BLOOD_OXYGEN);
        }
        if (i == HiHealthDataType.b || i == 2104) {
            return jSONObject.has(BleConstants.TEMPERATURE);
        }
        if (i == 2109) {
            return jSONObject.has(BleConstants.URIC_ACID);
        }
        return i == 10001 || i == 30029;
    }

    private boolean checkDataValueValid(int i, JSONObject jSONObject) {
        LogUtil.a(TAG, "checkDataValueValid");
        if (i == 10002) {
            return checkBloodPressureValueValid(jSONObject);
        }
        if (i == 10006) {
            return checkWeightValueValid(jSONObject);
        }
        if (i == 2103) {
            double optDouble = jSONObject.optDouble(BleConstants.BLOOD_OXYGEN);
            return optDouble > 0.0d && optDouble <= 100.0d;
        }
        if (i != HiHealthDataType.b && i != 2104) {
            return i == 2109 ? jSONObject.optInt(BleConstants.URIC_ACID) > 0 : i == 10001 || i == 30029;
        }
        double optDouble2 = jSONObject.optDouble(BleConstants.TEMPERATURE);
        return optDouble2 >= 34.0d && optDouble2 <= 42.0d;
    }

    private boolean checkWeightValueValid(JSONObject jSONObject) {
        double optDouble = jSONObject.optDouble("weight");
        if (optDouble >= 0.1d && optDouble <= 500.0d) {
            double optDouble2 = jSONObject.optDouble(BleConstants.PROTEIN_VALUE);
            if (optDouble2 >= 0.0d && optDouble2 <= 500.0d) {
                double optDouble3 = jSONObject.optDouble(BleConstants.BMI);
                if (optDouble3 >= 1.0d && optDouble3 <= 200.0d) {
                    double optDouble4 = jSONObject.optDouble(BleConstants.MUSCLE_MASS);
                    if (optDouble4 < 0.1d || optDouble4 > 150.0d || jSONObject.optDouble(BleConstants.BASAL_METABOLISM) <= 0.0d) {
                        return false;
                    }
                    double optDouble5 = jSONObject.optDouble(BleConstants.MOISTURE);
                    if (optDouble5 > 0.0d && optDouble5 <= 500.0d) {
                        double optDouble6 = jSONObject.optDouble(BleConstants.VISCERAL_FAT_LEVEL);
                        if (optDouble6 >= 1.0d && optDouble6 <= 59.0d) {
                            double optDouble7 = jSONObject.optDouble(BleConstants.BONE_SALT);
                            if (optDouble7 >= 0.5d && optDouble7 <= 5.0d) {
                                return checkWeightValueValidMore(jSONObject);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean checkWeightValueValidMore(JSONObject jSONObject) {
        double optDouble = jSONObject.optDouble(BleConstants.PROTEIN_RATE);
        if (optDouble <= 0.0d || optDouble > 100.0d) {
            return false;
        }
        double optDouble2 = jSONObject.optDouble(BleConstants.BODY_SCORE);
        if (optDouble2 <= 0.0d || optDouble2 > 100.0d) {
            return false;
        }
        double optDouble3 = jSONObject.optDouble(BleConstants.BODY_AGE);
        if (optDouble3 < 5.0d || optDouble3 > 99.0d) {
            return false;
        }
        double optDouble4 = jSONObject.optDouble(BleConstants.BODY_FAT_RATE);
        if (optDouble4 <= 0.0d || optDouble4 > 100.0d) {
            return false;
        }
        double optDouble5 = jSONObject.optDouble(BleConstants.IMPEDANCE);
        if (optDouble5 < 0.1d || optDouble5 > 100000.0d) {
            return false;
        }
        double optDouble6 = jSONObject.optDouble(BleConstants.MOISTURE_RATE);
        if (optDouble6 <= 0.0d || optDouble6 > 100.0d) {
            return false;
        }
        double optDouble7 = jSONObject.optDouble(BleConstants.SKELETAL_MUSCLE_MASS);
        return optDouble7 >= 1.0d && optDouble7 <= 150.0d;
    }

    private boolean checkBloodPressureValueValid(JSONObject jSONObject) {
        double optDouble = jSONObject.optDouble(BleConstants.BLOODPRESSURE_SYSTOLIC);
        double optDouble2 = jSONObject.optDouble(BleConstants.BLOODPRESSURE_DIASTOLIC);
        if (Double.isNaN(optDouble) || optDouble < 10.0d || optDouble > 500.0d || Double.isNaN(optDouble2) || optDouble2 < 10.0d || optDouble2 > 500.0d) {
            return false;
        }
        if (jSONObject.has(BleConstants.BLOODPRESSURE_SPHYGMUS)) {
            double optDouble3 = jSONObject.optDouble(BleConstants.BLOODPRESSURE_SPHYGMUS);
            if (Double.isNaN(optDouble3) || optDouble3 < 1.0d || optDouble3 > 200.0d) {
                return false;
            }
        }
        if (!jSONObject.has(BleConstants.REST_HEARTRATE)) {
            return true;
        }
        double optDouble4 = jSONObject.optDouble(BleConstants.REST_HEARTRATE);
        return !Double.isNaN(optDouble4) && optDouble4 >= 1.0d && optDouble4 <= 200.0d;
    }

    private void execQueryDataApi(long j, long j2, final int i, HealthDataQueryOption healthDataQueryOption) {
        int[] iArr;
        LogUtil.a(TAG, "execQueryDataApi");
        final BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        if (i == 10001) {
            iArr = this.mBloodSugarDataTypes;
            if (healthDataQueryOption != null && healthDataQueryOption.getLimit() != 0) {
                hiDataReadOption.setCount(healthDataQueryOption.getLimit());
            }
        } else {
            iArr = i == 30029 ? new int[]{30002} : new int[]{i};
        }
        hiDataReadOption.setTimeInterval(j, j2);
        boolean z = false;
        hiDataReadOption.setOwnerId(0);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setDeviceUuid(this.mUniqueId);
        hiDataReadOption.setReadType(2);
        LogUtil.a(TAG, "execQueryDataApi hiDataReadOption: ", hiDataReadOption.toString(), "; mUniqueId: ", CommonUtil.l(this.mUniqueId));
        if (healthDataQueryOption != null && healthDataQueryOption.getStartTimes() != null) {
            z = true;
        }
        final boolean z2 = z;
        final String str = z2 ? BleConstants.HAS_HEALTH_DATA : BleConstants.GET_HEALTH_DATA;
        BleJsDataApi.getInstance().execQuery(hiDataReadOption, i, healthDataQueryOption, new ResultCallback() { // from class: com.huawei.operation.ble.DataProcessor$$ExternalSyntheticLambda2
            @Override // com.huawei.operation.share.ResultCallback
            public final void onResult(int i2, Object obj) {
                DataProcessor.this.m691lambda$execQueryDataApi$2$comhuaweioperationbleDataProcessor(bleJsBiOperate, str, i, z2, i2, obj);
            }
        });
    }

    /* renamed from: lambda$execQueryDataApi$2$com-huawei-operation-ble-DataProcessor, reason: not valid java name */
    /* synthetic */ void m691lambda$execQueryDataApi$2$comhuaweioperationbleDataProcessor(BleJsBiOperate bleJsBiOperate, String str, int i, boolean z, int i2, Object obj) {
        LogUtil.a(TAG, "execQueryDataApi resultCode = ", Integer.valueOf(i2));
        bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, str, i, String.valueOf(i2));
        if (obj instanceof String) {
            callBackJsExecQueryData(String.valueOf(i2), (String) obj, z);
        } else {
            callBackJsExecQueryData(String.valueOf(i2), null, z);
        }
    }

    public void saveData(String str) {
        int i;
        LogUtil.a(TAG, "saveData function");
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt("type");
            if (i2 == 2104) {
                try {
                    i2 = HiHealthDataType.b;
                } catch (JSONException unused) {
                    i = i2;
                    LogUtil.b(TAG, "saveData JSONException");
                    bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
                    callBackJsResult(BleConstants.ON_SAVE_MEASURE_RESULT_NAME, 10009, BleConstants.PARAM_INVALID_STRING);
                    return;
                }
            }
            LogUtil.a(TAG, "saveData, type = ", Integer.valueOf(i2));
            ArrayList arrayList = new ArrayList(10);
            if (!isParserOneDataSuccess(arrayList, jSONObject, i2)) {
                LogUtil.h(TAG, "saveData failed");
                bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_HEALTH_DATA, i2, BleConstants.PARAM_INVALID_STRING);
                callBackJsResult(BleConstants.ON_SAVE_MEASURE_RESULT_NAME, 10009, BleConstants.PARAM_INVALID_STRING);
            } else {
                saveDataToPlatform(bleJsBiOperate, arrayList, i2, false, BleConstants.ON_SAVE_MEASURE_RESULT_NAME);
                if (i2 == 30029) {
                    saveRopeSkipCaloriesData(bleJsBiOperate, arrayList, false);
                }
            }
        } catch (JSONException unused2) {
            i = 0;
        }
    }

    public void saveMultipleData(String str) {
        int i;
        LogUtil.a(TAG, "saveMultipleData");
        BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt("type");
            if (i2 == 2104) {
                try {
                    i2 = HiHealthDataType.b;
                } catch (JSONException unused) {
                    i = i2;
                    LogUtil.b(TAG, "saveMultipleData, the first level of JSONObject parsing error");
                    bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_MULTIPLE_HEALTH_DATA, i, BleConstants.PARAM_INVALID_STRING);
                    callBackJsResult(BleConstants.ON_SAVE_MULTIPLE_MEASURE_RESULT_NAME, 10013, BleConstants.PARAM_INVALID_STRING);
                    return;
                }
            }
            LogUtil.a(TAG, "saveMultipleData, type = ", Integer.valueOf(i2));
            JSONArray jSONArray = jSONObject.getJSONArray("data");
            if (jSONArray != null && jSONArray.length() != 0 && jSONArray.length() <= 1000) {
                LogUtil.a(TAG, "saveMultipleData dataObject size: ", Integer.valueOf(jSONArray.length()));
                ArrayList arrayList = new ArrayList(10);
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    if (!isParserOneDataSuccess(arrayList, jSONArray.getJSONObject(i3), i2)) {
                        LogUtil.h(TAG, "saveMultipleData failed index: ", Integer.valueOf(i3));
                        bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_MULTIPLE_HEALTH_DATA, i2, BleConstants.PARAM_INVALID_STRING);
                        callBackJsResult(BleConstants.ON_SAVE_MULTIPLE_MEASURE_RESULT_NAME, 10013, BleConstants.PARAM_INVALID_STRING);
                        return;
                    }
                }
                if (arrayList.size() > 0) {
                    bleJsBiOperate.tickBiDataSize(this.mDeviceInfo, arrayList, this.mProductId, i2);
                }
                saveDataToPlatform(bleJsBiOperate, arrayList, i2, true, BleConstants.ON_SAVE_MULTIPLE_MEASURE_RESULT_NAME);
                if (i2 == 30029) {
                    saveRopeSkipCaloriesData(bleJsBiOperate, arrayList, true);
                    return;
                }
                return;
            }
            LogUtil.h(TAG, "saveMultipleData resultCode = ", BleConstants.PARAM_INVALID_STRING);
            bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.SAVE_MULTIPLE_HEALTH_DATA, i2, BleConstants.PARAM_INVALID_STRING);
            callBackJsResult(BleConstants.ON_SAVE_MULTIPLE_MEASURE_RESULT_NAME, 10013, BleConstants.PARAM_INVALID_STRING);
        } catch (JSONException unused2) {
            i = 0;
        }
    }

    private boolean isParserOneDataSuccess(List<HiHealthData> list, JSONObject jSONObject, int i) {
        long j;
        long j2;
        try {
            long optLong = jSONObject.optLong("startTime");
            long optLong2 = jSONObject.optLong("endTime");
            if (optLong <= 0) {
                j = System.currentTimeMillis();
                j2 = j;
            } else {
                j = optLong;
                j2 = optLong2;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("value");
            if (i == 10001) {
                optJSONObject.put(BleConstants.IS_CONFIRMED, jSONObject.optBoolean(BleConstants.IS_CONFIRMED));
            }
            if (j <= j2 && optJSONObject != null) {
                String oneHiHealthData = getOneHiHealthData(i, list, optJSONObject, j, j2);
                LogUtil.a(TAG, "isParserOneDataSuccess condition = ", oneHiHealthData);
                return String.valueOf(0).equals(oneHiHealthData);
            }
            LogUtil.h(TAG, "isParserOneDataSuccess msg startTime > endTime or valueObject = null");
            return false;
        } catch (JSONException e) {
            LogUtil.b(TAG, "isParserOneDataSuccess msg = ", e.getMessage());
            return false;
        }
    }

    private void saveRopeSkipCaloriesData(BleJsBiOperate bleJsBiOperate, List<HiHealthData> list, boolean z) {
        LogUtil.a(TAG, "saveRopeSkipCaloriesData");
        List<HiHealthData> assembleRopeCaloriePoints = ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).assembleRopeCaloriePoints(list);
        if (assembleRopeCaloriePoints.isEmpty()) {
            return;
        }
        saveDataToPlatform(bleJsBiOperate, assembleRopeCaloriePoints, 30001, z, null);
    }

    private String getOneHiHealthData(int i, List<HiHealthData> list, JSONObject jSONObject, long j, long j2) {
        LogUtil.a(TAG, "getOneHiHealthData saveType = ", Integer.valueOf(i));
        if (!checkDataKeyValid(i, jSONObject) || !checkDataValueValid(i, jSONObject)) {
            LogUtil.h(TAG, "getOneHiHealthData params is invalid");
            return BleConstants.PARAM_INVALID_STRING;
        }
        String str = BleConstants.PARAM_INVALID_STRING;
        if (i == HiHealthDataType.b || i == 2104) {
            return getOneTemperature(list, jSONObject, j, j2, i);
        }
        if (i == 2103) {
            return getOneBloodOxygen(list, jSONObject, j, j2);
        }
        if (i == 10006) {
            return getOneWeight(list, jSONObject, j, j2);
        }
        if (i == 10001) {
            return getOneBloodSugar(list, jSONObject, j, j2);
        }
        if (i == 10002) {
            return getOneBloodPressure(list, jSONObject, j, j2);
        }
        if (i == 2109) {
            return getOneUricAcid(list, jSONObject, j, j2);
        }
        if (i == 30029) {
            return getOneSequenceData(i, list, jSONObject, j, j2);
        }
        LogUtil.h(TAG, "getOneHiHealthData not this type");
        return str;
    }

    private String getOneBloodOxygen(List<HiHealthData> list, JSONObject jSONObject, long j, long j2) {
        LogUtil.a(TAG, "getOneBloodOxygen");
        double optDouble = jSONObject.optDouble(BleConstants.BLOOD_OXYGEN);
        HiHealthData buildHiHealthData = buildHiHealthData(2103, j, j2);
        buildHiHealthData.setValue(optDouble);
        list.add(buildHiHealthData);
        return "0";
    }

    private String getOneTemperature(List<HiHealthData> list, JSONObject jSONObject, long j, long j2, int i) {
        LogUtil.a(TAG, "getOneTemperature");
        double optDouble = jSONObject.optDouble(BleConstants.TEMPERATURE);
        HiHealthData buildHiHealthData = buildHiHealthData(i, j, j2);
        buildHiHealthData.setValue(optDouble);
        list.add(buildHiHealthData);
        return "0";
    }

    private void execQueryWeight(long j, long j2) {
        LogUtil.a(TAG, "execQueryWeight");
        final BleJsBiOperate bleJsBiOperate = new BleJsBiOperate();
        bleJsBiOperate.init();
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        hiAggregateOption.setTimeRange(j, j2);
        BleJsDataApi.getInstance().execQueryWeight(hiAggregateOption, this.mUniqueId, new ResultCallback() { // from class: com.huawei.operation.ble.DataProcessor$$ExternalSyntheticLambda1
            @Override // com.huawei.operation.share.ResultCallback
            public final void onResult(int i, Object obj) {
                DataProcessor.this.m692lambda$execQueryWeight$3$comhuaweioperationbleDataProcessor(bleJsBiOperate, i, obj);
            }
        });
    }

    /* renamed from: lambda$execQueryWeight$3$com-huawei-operation-ble-DataProcessor, reason: not valid java name */
    /* synthetic */ void m692lambda$execQueryWeight$3$comhuaweioperationbleDataProcessor(BleJsBiOperate bleJsBiOperate, int i, Object obj) {
        LogUtil.a(TAG, "execQueryWeight resultCode = ", Integer.valueOf(i));
        bleJsBiOperate.biApiCalling(this.mDeviceInfo, this.mProductId, BleConstants.GET_HEALTH_DATA, 10006, String.valueOf(i));
        if (obj instanceof String) {
            callBackJsExecQueryData(String.valueOf(i), (String) obj, false);
        } else {
            callBackJsExecQueryData(String.valueOf(i), null, false);
        }
    }

    private String getOneWeight(List<HiHealthData> list, JSONObject jSONObject, long j, long j2) {
        LogUtil.a(TAG, "getOneWeight");
        HiHealthData buildHiHealthData = buildHiHealthData(10006, j, j2);
        buildHiHealthData.setMetaData(this.mUserUuid);
        int i = 0;
        while (true) {
            String[] strArr = this.mWeightDataTypes;
            if (i >= strArr.length) {
                break;
            }
            if (jSONObject.has(strArr[i])) {
                buildHiHealthData.putDouble(this.mWeightDataTypesKey[i], jSONObject.optDouble(this.mWeightDataTypes[i]));
            }
            i++;
        }
        if (!buildHiHealthData.containsKey("protein")) {
            buildHiHealthData.putDouble("protein", handleNewProtein(buildHiHealthData));
        }
        list.add(buildHiHealthData);
        return String.valueOf(0);
    }

    private double handleNewProtein(HiHealthData hiHealthData) {
        if (!hiHealthData.containsKey("bodyWeight") || !hiHealthData.containsKey(BleConstants.BODY_FAT_RATE) || !hiHealthData.containsKey(BleConstants.BONE_SALT) || !hiHealthData.containsKey(BleConstants.MOISTURE)) {
            return 0.0d;
        }
        return HiCommonUtil.b(hiHealthData.getDouble("bodyWeight"), hiHealthData.getDouble(BleConstants.BODY_FAT_RATE), hiHealthData.getDouble(BleConstants.MOISTURE), hiHealthData.getDouble(BleConstants.BONE_SALT));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0061 A[Catch: JSONException -> 0x0090, TryCatch #1 {JSONException -> 0x0090, blocks: (B:10:0x0041, B:12:0x0061, B:15:0x006d, B:21:0x0048), top: B:4:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006d A[Catch: JSONException -> 0x0090, TRY_LEAVE, TryCatch #1 {JSONException -> 0x0090, blocks: (B:10:0x0041, B:12:0x0061, B:15:0x006d, B:21:0x0048), top: B:4:0x001e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getOneSequenceData(int r14, java.util.List<com.huawei.hihealth.HiHealthData> r15, org.json.JSONObject r16, long r17, long r19) {
        /*
            r13 = this;
            r0 = r14
            r1 = r16
            java.lang.String r2 = "getOneSequenceData"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "DataProcessor"
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            com.google.gson.GsonBuilder r2 = new com.google.gson.GsonBuilder     // Catch: org.json.JSONException -> L8f
            r2.<init>()     // Catch: org.json.JSONException -> L8f
            r2.serializeSpecialFloatingPointValues()     // Catch: org.json.JSONException -> L8f
            com.google.gson.Gson r2 = r2.create()     // Catch: org.json.JSONException -> L8f
            r4 = 30029(0x754d, float:4.208E-41)
            r5 = 1
            r6 = 0
            if (r0 != r4) goto L48
            java.lang.String r0 = "sportType"
            int r0 = r1.getInt(r0)     // Catch: org.json.JSONException -> L8f
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch: org.json.JSONException -> L8f
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L8f
            java.lang.String r8 = "getOneSequenceData sportType: "
            r7.<init>(r8)     // Catch: org.json.JSONException -> L8f
            r7.append(r0)     // Catch: org.json.JSONException -> L8f
            java.lang.String r7 = r7.toString()     // Catch: org.json.JSONException -> L8f
            r4[r6] = r7     // Catch: org.json.JSONException -> L8f
            com.huawei.hwlogsmodel.LogUtil.a(r3, r4)     // Catch: org.json.JSONException -> L8f
            r4 = 283(0x11b, float:3.97E-43)
            if (r0 != r4) goto L46
            r4 = r13
            com.huawei.hihealth.data.model.HiTrackMetaData r0 = r13.parseRopeSkippingMetaData(r1)     // Catch: org.json.JSONException -> L90
            goto L5f
        L46:
            r4 = r13
            goto L5e
        L48:
            r4 = r13
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch: org.json.JSONException -> L90
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L90
            java.lang.String r8 = "getOneSequenceData saveType: "
            r7.<init>(r8)     // Catch: org.json.JSONException -> L90
            r7.append(r14)     // Catch: org.json.JSONException -> L90
            java.lang.String r0 = r7.toString()     // Catch: org.json.JSONException -> L90
            r1[r6] = r0     // Catch: org.json.JSONException -> L90
            com.huawei.hwlogsmodel.LogUtil.h(r3, r1)     // Catch: org.json.JSONException -> L90
        L5e:
            r0 = 0
        L5f:
            if (r0 != 0) goto L6d
            java.lang.Object[] r0 = new java.lang.Object[r5]     // Catch: org.json.JSONException -> L90
            java.lang.String r1 = "getOneSequenceData metaData = null"
            r0[r6] = r1     // Catch: org.json.JSONException -> L90
            com.huawei.hwlogsmodel.LogUtil.h(r3, r0)     // Catch: org.json.JSONException -> L90
            java.lang.String r0 = com.huawei.operation.ble.BleConstants.PARAM_INVALID_STRING     // Catch: org.json.JSONException -> L90
            return r0
        L6d:
            java.lang.Class<com.huawei.hihealth.data.model.HiTrackMetaData> r1 = com.huawei.hihealth.data.model.HiTrackMetaData.class
            java.lang.String r0 = r2.toJson(r0, r1)     // Catch: org.json.JSONException -> L90
            r8 = 30001(0x7531, float:4.204E-41)
            r7 = r13
            r9 = r17
            r11 = r19
            com.huawei.hihealth.HiHealthData r1 = r7.buildHiHealthData(r8, r9, r11)     // Catch: org.json.JSONException -> L90
            r1.setMetaData(r0)     // Catch: org.json.JSONException -> L90
            java.lang.String r0 = "0"
            r1.setSequenceData(r0)     // Catch: org.json.JSONException -> L90
            r0 = r15
            r15.add(r1)     // Catch: org.json.JSONException -> L90
            java.lang.String r0 = java.lang.String.valueOf(r6)
            return r0
        L8f:
            r4 = r13
        L90:
            java.lang.String r0 = "getOneSequenceData JSONException"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r0)
            java.lang.String r0 = com.huawei.operation.ble.BleConstants.PARAM_INVALID_STRING
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.ble.DataProcessor.getOneSequenceData(int, java.util.List, org.json.JSONObject, long, long):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0066 A[Catch: JSONException -> 0x0080, TryCatch #0 {JSONException -> 0x0080, blocks: (B:3:0x0011, B:8:0x003f, B:11:0x0054, B:13:0x0066, B:14:0x0075), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hihealth.data.model.HiTrackMetaData parseRopeSkippingMetaData(org.json.JSONObject r10) {
        /*
            r9 = this;
            java.lang.String r0 = "parseRopeSkippingMetaData"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "DataProcessor"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            com.huawei.hihealth.data.model.HiTrackMetaData r0 = new com.huawei.hihealth.data.model.HiTrackMetaData
            r0.<init>()
            r2 = 0
            java.lang.String r3 = "sportType"
            int r3 = r10.getInt(r3)     // Catch: org.json.JSONException -> L80
            r0.setSportType(r3)     // Catch: org.json.JSONException -> L80
            r3 = 0
            r0.setHasTrackPoint(r3)     // Catch: org.json.JSONException -> L80
            java.lang.String r4 = "totalTime"
            long r4 = r10.getLong(r4)     // Catch: org.json.JSONException -> L80
            r0.setTotalTime(r4)     // Catch: org.json.JSONException -> L80
            java.lang.String r6 = "totalCalories"
            int r6 = r10.getInt(r6)     // Catch: org.json.JSONException -> L80
            r0.setTotalCalories(r6)     // Catch: org.json.JSONException -> L80
            r7 = 0
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 < 0) goto L3e
            if (r6 >= 0) goto L3c
            goto L3e
        L3c:
            r4 = 1
            goto L3f
        L3e:
            r4 = r3
        L3f:
            java.lang.String r5 = "mExtendTrackDataMap"
            org.json.JSONObject r10 = r10.getJSONObject(r5)     // Catch: org.json.JSONException -> L80
            java.util.HashMap r5 = new java.util.HashMap     // Catch: org.json.JSONException -> L80
            r6 = 16
            r5.<init>(r6)     // Catch: org.json.JSONException -> L80
            boolean r10 = r9.buildRopeMapDataSuccess(r5, r10)     // Catch: org.json.JSONException -> L80
            if (r10 != 0) goto L53
            goto L54
        L53:
            r3 = r4
        L54:
            r0.setExtendTrackDataMap(r5)     // Catch: org.json.JSONException -> L80
            java.lang.String r10 = "skipSpeed"
            java.lang.Object r10 = r5.get(r10)     // Catch: org.json.JSONException -> L80
            java.lang.String r10 = (java.lang.String) r10     // Catch: org.json.JSONException -> L80
            boolean r4 = android.text.TextUtils.isEmpty(r10)     // Catch: org.json.JSONException -> L80
            if (r4 != 0) goto L75
            java.lang.Float r10 = java.lang.Float.valueOf(r10)     // Catch: org.json.JSONException -> L80
            float r10 = r10.floatValue()     // Catch: org.json.JSONException -> L80
            int r10 = defpackage.koj.d(r10, r2)     // Catch: org.json.JSONException -> L80
            r0.setAbnormalTrack(r10)     // Catch: org.json.JSONException -> L80
        L75:
            r10 = 7
            r0.setChiefSportDataType(r10)     // Catch: org.json.JSONException -> L80
            r10 = 5
            r0.setSportDataSource(r10)     // Catch: org.json.JSONException -> L80
            if (r3 != 0) goto L8a
            goto L89
        L80:
            java.lang.String r10 = "parseRopeSkippingMetaData JSONException"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
        L89:
            r0 = r2
        L8a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.ble.DataProcessor.parseRopeSkippingMetaData(org.json.JSONObject):com.huawei.hihealth.data.model.HiTrackMetaData");
    }

    private boolean buildRopeMapDataSuccess(Map map, JSONObject jSONObject) throws JSONException {
        try {
            String string = jSONObject.getString("skipNum");
            LogUtil.a(TAG, "skipNumStr = " + string);
            map.put("skipNum", string);
            long parseLong = Long.parseLong(string);
            String string2 = jSONObject.getString("skipSpeed");
            map.put("skipSpeed", string2);
            long parseLong2 = Long.parseLong(string2);
            if (parseLong >= 0 && parseLong2 >= 0) {
                if (jSONObject.has("stumblingRope")) {
                    String string3 = jSONObject.getString("stumblingRope");
                    map.put("stumblingRope", string3);
                    if (Long.parseLong(string3) < 0) {
                        return false;
                    }
                } else {
                    map.put("stumblingRope", "-1");
                }
                if (jSONObject.has("maxSkippingTimes")) {
                    String string4 = jSONObject.getString("maxSkippingTimes");
                    map.put("maxSkippingTimes", string4);
                    if (Long.parseLong(string4) < 0) {
                        return false;
                    }
                } else {
                    map.put("maxSkippingTimes", "-1");
                }
                return true;
            }
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "parseRopeSkippingMetaData NumberFormatException");
            return false;
        }
    }

    private String getOneUricAcid(List<HiHealthData> list, JSONObject jSONObject, long j, long j2) {
        LogUtil.a(TAG, "getOneUricAcid");
        int optInt = jSONObject.optInt(BleConstants.URIC_ACID);
        HiHealthData buildHiHealthData = buildHiHealthData(2109, j, j2);
        buildHiHealthData.setValue(optInt);
        list.add(buildHiHealthData);
        return String.valueOf(0);
    }

    public void deleteMultipleHealthData(String str) {
        LogUtil.a(TAG, BleConstants.ON_DELETE_MEASURE_RESULT_NAME);
        int i = 0;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt("type");
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                LogUtil.a(TAG, "deleteMultipleHealthData type = ", Integer.valueOf(i2));
                if (jSONArray.length() == 0) {
                    LogUtil.a(TAG, "deleteMultipleHealthData dataArray.length() = 0");
                    callBackJsResult(BleConstants.ON_DELETE_MEASURE_RESULT_NAME, 10015, BleConstants.PARAM_INVALID_STRING);
                    return;
                }
                if (i2 == 10001) {
                    deleteOneHealthData(jSONArray, 0, BleConstants.BLOOD_SUGAR_TYPE);
                    return;
                }
                if (i2 == 10002) {
                    deleteOneHealthData(jSONArray, 0, BleConstants.BLOOD_PRESSURE_TYPE);
                    return;
                }
                if (i2 == 10006) {
                    deleteOneHealthData(jSONArray, 0, BleConstants.WEIGHT_TYPE);
                    return;
                }
                if (i2 == 30029) {
                    deleteOneHealthData(jSONArray, 0, BleConstants.SKIPPING_ROPE_TYPE);
                    return;
                }
                if (i2 != 2104 && i2 != HiHealthDataType.b) {
                    if (i2 == 2103) {
                        deleteOneHealthData(jSONArray, 0, BleConstants.BLOOD_OXYGEN_TYPE);
                        return;
                    } else if (i2 == 2109) {
                        deleteOneHealthData(jSONArray, 0, BleConstants.URIC_ACID_TYPE);
                        return;
                    } else {
                        LogUtil.a(TAG, "deleteMultipleHealthData Types that cannot be deleted");
                        callBackJsResult(BleConstants.ON_DELETE_MEASURE_RESULT_NAME, 10015, BleConstants.PARAM_INVALID_STRING);
                        return;
                    }
                }
                deleteOneHealthData(jSONArray, 0, BleConstants.BODY_THERMOMETER_TYPE);
            } catch (JSONException unused) {
                i = i2;
                LogUtil.b(TAG, "deleteMultipleHealthData JSONException, type = ", Integer.valueOf(i));
                callBackJsResult(BleConstants.ON_DELETE_MEASURE_RESULT_NAME, 10015, String.valueOf(4));
            }
        } catch (JSONException unused2) {
        }
    }

    private void deleteOneHealthData(final JSONArray jSONArray, final int i, final String str) {
        int[] iArr;
        try {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            long j = jSONObject.getLong("startTime");
            long j2 = jSONObject.getLong("endTime");
            LogUtil.a(TAG, "deleteOneHealthData startTime = ", Long.valueOf(j), ",endTime = ", Long.valueOf(j2));
            HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
            hiDataDeleteOption.setTimeInterval(j, j2);
            int i2 = jSONObject.getJSONObject("value").getInt(str);
            if (i2 == 10002) {
                iArr = this.mBloodPressureDataTypes;
            } else {
                iArr = i2 == 10006 ? this.mWeightDataIntTypes : new int[]{i2};
            }
            hiDataDeleteOption.setTypes(iArr);
            BleJsDataApi.getInstance().deleteSample(hiDataDeleteOption, 3, new ResultCallback() { // from class: com.huawei.operation.ble.DataProcessor$$ExternalSyntheticLambda4
                @Override // com.huawei.operation.share.ResultCallback
                public final void onResult(int i3, Object obj) {
                    DataProcessor.this.m689x9e3bcd26(i, jSONArray, str, i3, obj);
                }
            });
        } catch (JSONException unused) {
            LogUtil.b(TAG, "deleteOneHealthData JSONException, index = ", Integer.valueOf(i));
            callBackJsResult(BleConstants.ON_DELETE_MEASURE_RESULT_NAME, 10015, String.valueOf(4));
        }
    }

    /* renamed from: lambda$deleteOneHealthData$4$com-huawei-operation-ble-DataProcessor, reason: not valid java name */
    /* synthetic */ void m689x9e3bcd26(int i, JSONArray jSONArray, String str, int i2, Object obj) {
        LogUtil.a(TAG, "deleteOneHealthData resultCode：", Integer.valueOf(i2));
        if (i2 != 0 || i == jSONArray.length() - 1) {
            callBackJsResult(BleConstants.ON_DELETE_MEASURE_RESULT_NAME, 10015, String.valueOf(Utils.filterResultCode(i2)));
        } else {
            deleteOneHealthData(jSONArray, i + 1, str);
        }
    }
}
