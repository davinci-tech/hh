package com.huawei.operation.ble;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.json.JsonSanitizer;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.beans.HealthDataQueryOption;
import com.huawei.operation.share.ResultCallback;
import com.huawei.operation.utils.Utils;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.koq;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class BleJsDataApi {
    private static final int MATH_ZERO = 0;
    private static final int NOT_SAVED = 1;
    private static final int QUERY_KIND_IS_CONFIRMED = 1;
    private static final int QUERY_KIND_NOT_CONFIRMED = 2;
    private static final int RETRY_COUNT_SUBTRACTED = 1;
    private static final String TAG = "BleJsDataApi";
    private static volatile Context sContext;
    private String[] bloodPressureDataKeyTypes;
    private String[] bloodPressureDataTypes;
    private String[] bloodSugarDataKeyTypes;
    private int[] bloodSugarDataTypes;
    private String[] weightDataKey;
    private String[] weightDataKeyTypes;

    private BleJsDataApi() {
        this.bloodSugarDataTypes = BleConstants.getBloodSugarDataTypesClone();
        this.bloodSugarDataKeyTypes = BleConstants.getBloodSugarDataKeyTypesClone();
        this.bloodPressureDataTypes = BleConstants.getBloodPressureDataTypesClone();
        this.bloodPressureDataKeyTypes = BleConstants.getBloodPressureDataKeyTypesClone();
        this.weightDataKeyTypes = BleConstants.getWeightDataJsonTypesClone();
        this.weightDataKey = BleConstants.getWeightDataTypesKeyClone();
    }

    static class Instance {
        private static final BleJsDataApi INSTANCE = new BleJsDataApi();

        private Instance() {
        }
    }

    public static BleJsDataApi getInstance() {
        Log.i(TAG, "BleJsDataApi getInstance");
        if (sContext == null) {
            sContext = BaseApplication.getContext();
        }
        return Instance.INSTANCE;
    }

    public void getUserInfoImpl(final ResultCallback resultCallback) {
        LogUtil.a(TAG, "enter getUserInfo");
        final JSONObject jSONObject = new JSONObject();
        final JSONObject jSONObject2 = new JSONObject();
        final HiUserInfo[] hiUserInfoArr = {new HiUserInfo()};
        HiHealthNativeApi.a(sContext).fetchUserData(new HiCommonListener() { // from class: com.huawei.operation.ble.BleJsDataApi.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj instanceof List) {
                    List list = (List) obj;
                    if (list.size() > 0) {
                        hiUserInfoArr[0] = (HiUserInfo) list.get(0);
                        try {
                            double doubleValue = new BigDecimal(hiUserInfoArr[0].getWeight()).setScale(1, 4).doubleValue();
                            if (doubleValue <= 0.0d) {
                                doubleValue = 60.0d;
                            }
                            int height = hiUserInfoArr[0].getHeight();
                            if (height <= 0) {
                                height = 170;
                            }
                            jSONObject2.put("id", hiUserInfoArr[0].getOwnerId());
                            jSONObject2.put("unitType", hiUserInfoArr[0].getUnitType());
                            jSONObject2.put("height", height);
                            jSONObject2.put("weight", doubleValue);
                            jSONObject2.put(CommonConstant.KEY_GENDER, hiUserInfoArr[0].getGender());
                            jSONObject2.put("age", hiUserInfoArr[0].getAge());
                            jSONObject2.put(ParsedFieldTag.TASK_MODIFY_TIME, hiUserInfoArr[0].getMobile());
                            jSONObject.put("resultCode", String.valueOf(Utils.filterResultCode(i)));
                            jSONObject.put("data", jSONObject2);
                            resultCallback.onResult(Utils.filterResultCode(i), jSONObject.toString());
                            return;
                        } catch (JSONException unused) {
                            LogUtil.b(BleJsDataApi.TAG, "getUserInfoImpl JSONException");
                            resultCallback.onResult(Utils.filterResultCode(i), null);
                            return;
                        }
                    }
                    resultCallback.onResult(Utils.filterResultCode(i), null);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a(BleJsDataApi.TAG, "get user info errCode = ", Integer.valueOf(i), " errMsg = ", obj);
                resultCallback.onResult(Utils.filterResultCode(i), null);
            }
        });
    }

    public void saveSample(final HiDataInsertOption hiDataInsertOption, final int i, final ResultCallback resultCallback) {
        HiHealthNativeApi.a(sContext).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: com.huawei.operation.ble.BleJsDataApi$$ExternalSyntheticLambda1
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                BleJsDataApi.this.m685lambda$saveSample$0$comhuaweioperationbleBleJsDataApi(i, hiDataInsertOption, resultCallback, i2, obj);
            }
        });
    }

    /* renamed from: lambda$saveSample$0$com-huawei-operation-ble-BleJsDataApi, reason: not valid java name */
    /* synthetic */ void m685lambda$saveSample$0$comhuaweioperationbleBleJsDataApi(int i, HiDataInsertOption hiDataInsertOption, ResultCallback resultCallback, int i2, Object obj) {
        LogUtil.a(TAG, "saveSample HiHealthNativeApi resultCode : ", Integer.valueOf(i2));
        if (i2 != 0 && i > 0) {
            saveSample(hiDataInsertOption, i - 1, resultCallback);
        } else {
            resultCallback.onResult(Utils.filterResultCode(i2), obj);
        }
    }

    public void deleteSample(final HiDataDeleteOption hiDataDeleteOption, final int i, final ResultCallback resultCallback) {
        HiHealthNativeApi.a(sContext).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: com.huawei.operation.ble.BleJsDataApi$$ExternalSyntheticLambda2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                BleJsDataApi.this.m684lambda$deleteSample$1$comhuaweioperationbleBleJsDataApi(i, hiDataDeleteOption, resultCallback, i2, obj);
            }
        });
    }

    /* renamed from: lambda$deleteSample$1$com-huawei-operation-ble-BleJsDataApi, reason: not valid java name */
    /* synthetic */ void m684lambda$deleteSample$1$comhuaweioperationbleBleJsDataApi(int i, HiDataDeleteOption hiDataDeleteOption, ResultCallback resultCallback, int i2, Object obj) {
        LogUtil.a(TAG, "deleteSample HiHealthNativeApi resultCode : ", Integer.valueOf(i2));
        if (i2 != 0 && i > 0) {
            deleteSample(hiDataDeleteOption, i - 1, resultCallback);
        } else {
            resultCallback.onResult(Utils.filterResultCode(i2), obj);
        }
    }

    public void execQueryBloodPressure(HiAggregateOption hiAggregateOption, final HiAggregateOption hiAggregateOption2, final ResultCallback resultCallback) {
        HiHealthNativeApi.a(sContext).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.operation.ble.BleJsDataApi.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a(BleJsDataApi.TAG, "execQueryBloodPressure onResult errorCode = ", Integer.valueOf(i));
                BleJsDataApi.this.execQueryHeart(list, hiAggregateOption2, resultCallback);
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.a(BleJsDataApi.TAG, "execQueryBloodPressure onResultIntent errorCode = ", Integer.valueOf(i2));
                resultCallback.onResult(Utils.filterResultCode(i2), null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execQueryHeart(final List<HiHealthData> list, HiAggregateOption hiAggregateOption, final ResultCallback resultCallback) {
        HiHealthNativeApi.a(sContext).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.operation.ble.BleJsDataApi.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list2, int i, int i2) {
                LogUtil.a(BleJsDataApi.TAG, "execQueryHeart onResult errorCode = ", Integer.valueOf(i));
                BleJsDataApi.this.handlerBloodHeartResult(list, list2, i, 10002, resultCallback);
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list2, int i2, int i3) {
                LogUtil.a(BleJsDataApi.TAG, "execQueryHeart onResultIntent errorCode = ", Integer.valueOf(i2));
                List list3 = list;
                if (list3 != null && list3.size() > 0) {
                    BleJsDataApi.this.handlerBloodHeartResult(list, list2, i2, 10002, resultCallback);
                } else {
                    resultCallback.onResult(Utils.filterResultCode(i2), null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerBloodHeartResult(List<HiHealthData> list, List<HiHealthData> list2, int i, int i2, ResultCallback resultCallback) {
        ArrayList arrayList = new ArrayList();
        if (list2 != null && list2.size() > 0) {
            for (int i3 = 0; i3 < list2.size(); i3++) {
                HiHealthData hiHealthData = list2.get(i3);
                if (hiHealthData != null) {
                    arrayList.add(Integer.valueOf(hiHealthData.getInt(BleConstants.BLOODPRESSURE_SPHYGMUS)));
                }
            }
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (list != null && list.size() > 0) {
            LogUtil.c(TAG, "handlerBloodHeartResult data = ", list, Integer.valueOf(arrayList.size()));
            for (int i4 = 0; i4 < list.size(); i4++) {
                HiHealthData hiHealthData2 = list.get(i4);
                try {
                    double d = hiHealthData2.getDouble("BLOOD_PRESSURE_SYSTOLIC");
                    double d2 = hiHealthData2.getDouble("BLOOD_PRESSURE_DIASTOLIC");
                    if (d >= 1.0d || d2 >= 1.0d) {
                        try {
                            jSONArray.put(createJsonObject(hiHealthData2, arrayList, i4, i2));
                        } catch (JSONException unused) {
                            LogUtil.b(TAG, "handlerBloodHeartResult JSONException");
                            resultCallback.onResult(Utils.filterResultCode(i), null);
                            return;
                        }
                    }
                } catch (JSONException unused2) {
                }
            }
            try {
                jSONObject.put("resultCode", String.valueOf(Utils.filterResultCode(i)));
                jSONObject.put("data", jSONArray);
                resultCallback.onResult(Utils.filterResultCode(i), jSONObject.toString());
                return;
            } catch (JSONException unused3) {
                LogUtil.b(TAG, "execQueryHeart JSONException");
                resultCallback.onResult(Utils.filterResultCode(i), null);
                return;
            }
        }
        LogUtil.a(TAG, "handlerBloodHeartResult dataBloodPressure is null or dataBloodPressure.size() = 0");
        resultCallback.onResult(Utils.filterResultCode(i), null);
    }

    private JSONObject createJsonObject(HiHealthData hiHealthData, List<Integer> list, int i, int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        int i3 = 0;
        while (true) {
            String[] strArr = this.bloodPressureDataKeyTypes;
            if (i3 >= strArr.length) {
                break;
            }
            jSONObject.put(this.bloodPressureDataTypes[i3], hiHealthData.get(strArr[i3]));
            i3++;
        }
        if (i < list.size()) {
            jSONObject.put(BleConstants.REST_HEARTRATE, list.get(i));
        }
        return buildOneDataObject(i2, hiHealthData, jSONObject);
    }

    public void execQuery(HiDataReadOption hiDataReadOption, final int i, final HealthDataQueryOption healthDataQueryOption, final ResultCallback resultCallback) {
        HiHealthNativeApi.a(sContext).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.operation.ble.BleJsDataApi.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.a(BleJsDataApi.TAG, "execQuery readHiHealthData onResult errorCode", Integer.valueOf(i2));
                LogUtil.c(BleJsDataApi.TAG, "execQuery data = ", obj);
                if (obj != null) {
                    int i4 = i;
                    if (i4 == 10001) {
                        BleJsDataApi.this.execQueryBloodSugarDataToJson((SparseArray) obj, resultCallback, healthDataQueryOption, i2);
                        return;
                    } else {
                        BleJsDataApi.this.execQueryHiHealthDataToJson((SparseArray) obj, resultCallback, i2, i4);
                        return;
                    }
                }
                resultCallback.onResult(Utils.filterResultCode(i2), null);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
                LogUtil.a(BleJsDataApi.TAG, "execQuery readHiHealthData onResultIntent errorCode", Integer.valueOf(i3));
                resultCallback.onResult(Utils.filterResultCode(i3), null);
            }
        });
    }

    private JSONObject execRopeSkippingValue(HiTrackMetaData hiTrackMetaData) throws JSONException {
        LogUtil.a(TAG, "execRopeSkippingValue");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(BleConstants.SPORT_TYPE, hiTrackMetaData.getSportType());
        jSONObject.put("totalTime", hiTrackMetaData.getTotalTime());
        jSONObject.put(BleConstants.TOTAL_CALORIES, hiTrackMetaData.getTotalCalories());
        JSONObject jSONObject2 = new JSONObject();
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        jSONObject2.put("skipNum", extendTrackMap.get("skipNum"));
        jSONObject2.put("skipSpeed", extendTrackMap.get("skipSpeed"));
        jSONObject2.put("stumblingRope", extendTrackMap.get("stumblingRope"));
        jSONObject2.put("maxSkippingTimes", extendTrackMap.get("maxSkippingTimes"));
        jSONObject.put(BleConstants.EXTEND_TRACK_DATA_MAP, jSONObject2);
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execQueryHiHealthDataToJson(SparseArray<Object> sparseArray, ResultCallback resultCallback, int i, int i2) {
        Object obj;
        if (i2 == 30029) {
            obj = sparseArray.get(30002);
        } else {
            obj = sparseArray.get(i2);
        }
        List list = obj instanceof List ? (List) obj : null;
        if (koq.b(list)) {
            resultCallback.onResult(Utils.filterResultCode(i), null);
            return;
        }
        LogUtil.a(TAG, "HiHealthNativeApi resultCode : ", Integer.valueOf(i), " data size : ", Integer.valueOf(list.size()));
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(buildOneHealthDataObject(i2, (HiHealthData) it.next()));
            }
            jSONObject.put("resultCode", String.valueOf(Utils.filterResultCode(i)));
            jSONObject.put("data", jSONArray);
            resultCallback.onResult(Utils.filterResultCode(i), jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b(TAG, "execQueryHiHealthDataToJson JSONException");
            resultCallback.onResult(Utils.filterResultCode(i), null);
        }
    }

    private JSONObject buildOneHealthDataObject(int i, HiHealthData hiHealthData) throws JSONException {
        LogUtil.a(TAG, "buildOneHealthDataObject queryType: ", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        if (i == 2104 || i == HiHealthDataType.b) {
            jSONObject.put(BleConstants.TEMPERATURE, hiHealthData.getValue());
        } else if (i == 2103) {
            jSONObject.put(BleConstants.BLOOD_OXYGEN, hiHealthData.getValue());
        } else if (i == 30029) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) new Gson().fromJson(JsonSanitizer.sanitize(hiHealthData.getMetaData()), HiTrackMetaData.class);
            if (hiTrackMetaData != null && hiTrackMetaData.getSportType() == 283) {
                jSONObject = execRopeSkippingValue(hiTrackMetaData);
            }
        } else if (i == 2109) {
            jSONObject.put(BleConstants.URIC_ACID, hiHealthData.getValue());
        } else {
            LogUtil.h(TAG, "not this queryType");
        }
        return buildOneDataObject(i, hiHealthData, jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject buildOneDataObject(int i, HiHealthData hiHealthData, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("startTime", hiHealthData.getStartTime());
        jSONObject2.put("endTime", hiHealthData.getEndTime());
        jSONObject2.put("type", i);
        jSONObject2.put(BleConstants.DATA_TYPE_NAME, "");
        jSONObject2.put("value", jSONObject);
        return jSONObject2;
    }

    public void execQueryWeight(HiAggregateOption hiAggregateOption, final String str, final ResultCallback resultCallback) {
        HiHealthNativeApi.a(sContext).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: com.huawei.operation.ble.BleJsDataApi.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a(BleJsDataApi.TAG, "execQueryWeight onResult errorCode = ", Integer.valueOf(i));
                if (koq.b(list)) {
                    LogUtil.a(BleJsDataApi.TAG, "execQueryWeight onResult datas is empty");
                    resultCallback.onResult(Utils.filterResultCode(i), null);
                    return;
                }
                LogUtil.a(BleJsDataApi.TAG, "data size= ", Integer.valueOf(list.size()));
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                try {
                    Iterator<HiHealthData> it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            HiHealthData next = it.next();
                            JSONObject jSONObject2 = new JSONObject();
                            if (str.equals(next.get("device_uniquecode"))) {
                                for (int i3 = 0; i3 < BleJsDataApi.this.weightDataKey.length; i3++) {
                                    jSONObject2.put(BleJsDataApi.this.weightDataKeyTypes[i3], next.getDouble(BleJsDataApi.this.weightDataKey[i3]));
                                }
                                jSONArray.put(BleJsDataApi.this.buildOneDataObject(10006, next, jSONObject2));
                            } else {
                                LogUtil.c(BleJsDataApi.TAG, "deviceUniqueId not equal");
                            }
                        } else {
                            jSONObject.put("resultCode", String.valueOf(Utils.filterResultCode(i)));
                            jSONObject.put("data", jSONArray);
                            LogUtil.a(BleJsDataApi.TAG, "dataArray size= ", Integer.valueOf(jSONArray.length()));
                            resultCallback.onResult(Utils.filterResultCode(i), jSONObject.toString());
                            return;
                        }
                    }
                } catch (JSONException unused) {
                    LogUtil.b(BleJsDataApi.TAG, "execQueryWeight JSONException");
                    resultCallback.onResult(Utils.filterResultCode(i), null);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                LogUtil.a(BleJsDataApi.TAG, "execQueryWeight onResultIntent errorCode = ", Integer.valueOf(i2));
                resultCallback.onResult(Utils.filterResultCode(i2), null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execQueryBloodSugarDataToJson(SparseArray<Object> sparseArray, ResultCallback resultCallback, HealthDataQueryOption healthDataQueryOption, int i) {
        if (healthDataQueryOption == null) {
            LogUtil.h(TAG, "execQueryBloodSugarDataToJson queryOption is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        int queryKind = healthDataQueryOption.getQueryKind();
        if (queryKind == 1) {
            showPartBloodSugarRecord(sparseArray, jSONArray, true);
        } else if (queryKind == 2) {
            showPartBloodSugarRecord(sparseArray, jSONArray, false);
        } else {
            showAllBloodSugarRecord(sparseArray, jSONArray, resultCallback, i, healthDataQueryOption);
        }
        try {
            jSONObject.put("resultCode", String.valueOf(Utils.filterResultCode(i)));
            LogUtil.a(TAG, "resultArray.length()", Integer.valueOf(jSONArray.length()));
            jSONObject.put("data", jSONArray);
            resultCallback.onResult(Utils.filterResultCode(i), jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b(TAG, "execQueryBloodSugarDataToJson JSONException");
            resultCallback.onResult(Utils.filterResultCode(i), null);
        }
    }

    private void showPartBloodSugarRecord(SparseArray<Object> sparseArray, JSONArray jSONArray, boolean z) {
        for (int i = 0; i < this.bloodSugarDataTypes.length; i++) {
            List<HiHealthData> bloodSugarDataList = getBloodSugarDataList(i, sparseArray);
            if (koq.b(bloodSugarDataList)) {
                LogUtil.a(TAG, "showPartBloodSugarRecord list is empty");
            } else {
                for (HiHealthData hiHealthData : bloodSugarDataList) {
                    String metaData = hiHealthData.getMetaData();
                    if (!TextUtils.isEmpty(metaData)) {
                        progressBloodSugarData(jSONArray, metaData, z, i, hiHealthData);
                    }
                }
                LogUtil.c(TAG, "execQueryBloodSugarDataToJson data ：", Double.valueOf(bloodSugarDataList.get(0).getValue()), " Type : ", Integer.valueOf(bloodSugarDataList.get(0).getType()));
            }
        }
    }

    private void progressBloodSugarData(JSONArray jSONArray, String str, boolean z, int i, HiHealthData hiHealthData) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(BleConstants.IS_CONFIRMED_DB) && jSONObject.getBoolean(BleConstants.IS_CONFIRMED_DB) == z) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(this.bloodSugarDataKeyTypes[i], hiHealthData.getValue());
                JSONObject buildOneDataObject = buildOneDataObject(10001, hiHealthData, jSONObject2);
                buildOneDataObject.put(BleConstants.IS_CONFIRMED, jSONObject.getBoolean(BleConstants.IS_CONFIRMED_DB));
                jSONArray.put(buildOneDataObject);
            }
        } catch (JSONException e) {
            LogUtil.b(TAG, "execQueryBloodSugarDataToJson JSONException:", e);
        }
    }

    private void showAllBloodSugarRecord(SparseArray<Object> sparseArray, JSONArray jSONArray, ResultCallback resultCallback, int i, HealthDataQueryOption healthDataQueryOption) {
        LogUtil.a(TAG, "showAllBloodSugarRecord");
        try {
            HashMap<Long, Integer> startTimes = healthDataQueryOption.getStartTimes();
            int limit = healthDataQueryOption.getLimit();
            if (startTimes != null) {
                buildBloodSugarResultArray(sparseArray, jSONArray, startTimes);
            } else if (limit != 0) {
                buildLatestBloodSugarResultArray(sparseArray, jSONArray, limit);
            } else {
                showAllBloodSugarResultArray(sparseArray, jSONArray);
            }
        } catch (JSONException unused) {
            LogUtil.b(TAG, "showAllBloodSugarRecord JSONException");
            resultCallback.onResult(Utils.filterResultCode(i), null);
        }
    }

    private void showAllBloodSugarResultArray(SparseArray<Object> sparseArray, JSONArray jSONArray) throws JSONException {
        LogUtil.a(TAG, "showAllBloodSugarResultArray");
        for (int i = 0; i < this.bloodSugarDataTypes.length; i++) {
            List<HiHealthData> bloodSugarDataList = getBloodSugarDataList(i, sparseArray);
            if (koq.b(bloodSugarDataList)) {
                LogUtil.a(TAG, "showAllBloodSugarResultArray list is empty");
            } else {
                Iterator<HiHealthData> it = bloodSugarDataList.iterator();
                while (it.hasNext()) {
                    putDataToResultArray(this.bloodSugarDataKeyTypes[i], it.next(), jSONArray);
                }
                LogUtil.a(TAG, "showAllBloodSugarResultArray data ：", Double.valueOf(bloodSugarDataList.get(0).getValue()), " Type : ", Integer.valueOf(bloodSugarDataList.get(0).getType()));
            }
        }
    }

    private void buildBloodSugarResultArray(SparseArray<Object> sparseArray, JSONArray jSONArray, HashMap<Long, Integer> hashMap) throws JSONException {
        LogUtil.a(TAG, "buildHealthResultArray");
        for (int i = 0; i < this.bloodSugarDataTypes.length; i++) {
            List<HiHealthData> bloodSugarDataList = getBloodSugarDataList(i, sparseArray);
            if (koq.b(bloodSugarDataList)) {
                LogUtil.a(TAG, "buildBloodSugarResultArray list is empty");
            } else {
                for (HiHealthData hiHealthData : bloodSugarDataList) {
                    if (hashMap.containsKey(Long.valueOf(hiHealthData.getStartTime()))) {
                        hashMap.remove(Long.valueOf(hiHealthData.getStartTime()));
                    }
                }
            }
        }
        for (Map.Entry<Long, Integer> entry : hashMap.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("startTime", entry.getKey());
            jSONObject.put("status", 1);
            jSONArray.put(jSONObject);
        }
    }

    private List<HiHealthData> getBloodSugarDataList(int i, SparseArray<Object> sparseArray) {
        Object obj = sparseArray.get(this.bloodSugarDataTypes[i]);
        if (obj instanceof List) {
            return (List) obj;
        }
        return null;
    }

    private void buildLatestBloodSugarResultArray(SparseArray<Object> sparseArray, JSONArray jSONArray, int i) throws JSONException {
        LogUtil.a(TAG, "buildLatestBloodSugarResultArray");
        ArrayList<HiHealthData> arrayList = new ArrayList(16);
        int i2 = 0;
        for (int i3 = 0; i3 < this.bloodSugarDataTypes.length; i3++) {
            List<HiHealthData> bloodSugarDataList = getBloodSugarDataList(i3, sparseArray);
            if (koq.b(bloodSugarDataList)) {
                LogUtil.a(TAG, "buildLatestBloodSugarResultArray list is empty");
            } else {
                arrayList.addAll(bloodSugarDataList);
            }
        }
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData : arrayList) {
            hashMap.put(Long.valueOf(hiHealthData.getEndTime()), hiHealthData);
        }
        TreeMap treeMap = new TreeMap(new Comparator() { // from class: com.huawei.operation.ble.BleJsDataApi$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((Long) obj2).compareTo((Long) obj);
                return compareTo;
            }
        });
        treeMap.putAll(hashMap);
        HashMap hashMap2 = new HashMap(16);
        int i4 = 0;
        while (true) {
            int[] iArr = this.bloodSugarDataTypes;
            if (i4 >= iArr.length) {
                break;
            }
            hashMap2.put(Integer.valueOf(iArr[i4]), this.bloodSugarDataKeyTypes[i4]);
            i4++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            if (i2 >= i) {
                return;
            }
            HiHealthData hiHealthData2 = (HiHealthData) entry.getValue();
            putDataToResultArray((String) hashMap2.get(Integer.valueOf(hiHealthData2.getType())), hiHealthData2, jSONArray);
            i2++;
        }
    }

    private void putDataToResultArray(String str, HiHealthData hiHealthData, JSONArray jSONArray) throws JSONException {
        LogUtil.a(TAG, "putDataToResultArray");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(str, hiHealthData.getValue());
        JSONObject buildOneDataObject = buildOneDataObject(10001, hiHealthData, jSONObject);
        buildOneDataObject.put("value", jSONObject);
        buildOneDataObject.put(BleConstants.IS_CONFIRMED, new JSONObject(hiHealthData.getMetaData()).getBoolean(BleConstants.IS_CONFIRMED_DB));
        jSONArray.put(buildOneDataObject);
    }
}
