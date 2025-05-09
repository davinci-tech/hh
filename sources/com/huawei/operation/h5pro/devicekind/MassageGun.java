package com.huawei.operation.h5pro.devicekind;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.listener.IuniversalCallback;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.PluginOperationAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.ble.BleOperatorCompactImpl;
import com.huawei.operation.h5pro.dataproceessor.HealthDataProcessor;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.pluginbase.PluginBaseAdapter;
import defpackage.iea;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class MassageGun {
    private static final long SEVEN_DAYS = 604800000;
    private static final String TAG = "MassageGun";
    private BleOperatorCompactImpl mBleOperatorCompactImpl;
    private HealthDataProcessor mDataProcessor;
    private String mProductId;
    private String mUniqueId;

    public MassageGun(Context context, BleOperatorCompactImpl bleOperatorCompactImpl, H5ProInstance h5ProInstance, ContentValues contentValues, String str) {
        this.mUniqueId = "";
        this.mProductId = str;
        this.mBleOperatorCompactImpl = bleOperatorCompactImpl;
        if (contentValues != null) {
            this.mUniqueId = contentValues.getAsString("uniqueId");
        }
        this.mDataProcessor = new HealthDataProcessor(context, h5ProInstance, contentValues, str, this.mUniqueId);
    }

    public void getBanner(final int i, final String str) {
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h(TAG, "Network is not Connected");
            this.mBleOperatorCompactImpl.getBannerInfo("", str);
        } else {
            final MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
            marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.operation.h5pro.devicekind.MassageGun$$ExternalSyntheticLambda1
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    MassageGun.this.m711xeaf8d4be(marketingApi, i, str, (Map) obj);
                }
            });
        }
    }

    /* renamed from: lambda$getBanner$0$com-huawei-operation-h5pro-devicekind-MassageGun, reason: not valid java name */
    /* synthetic */ void m711xeaf8d4be(MarketingApi marketingApi, int i, String str, Map map) {
        if (this.mBleOperatorCompactImpl == null) {
            LogUtil.h(TAG, "bleOperator is null");
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        if (filterMarketingRules.containsKey(Integer.valueOf(i))) {
            ResourceResultInfo resourceResultInfo = filterMarketingRules.get(Integer.valueOf(i));
            if (resourceResultInfo == null) {
                LogUtil.h(TAG, "getBannerInfo = null");
                this.mBleOperatorCompactImpl.getBannerInfo("", str);
                return;
            }
            List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
            if (koq.b(resourceResultInfo.getResources())) {
                LogUtil.a(TAG, "getBannerInfo is empty");
                this.mBleOperatorCompactImpl.getBannerInfo("", str);
            } else {
                LogUtil.a(TAG, "getBannerInfo size:", Integer.valueOf(resources.size()));
                this.mBleOperatorCompactImpl.getBannerInfo(new Gson().toJson(resourceResultInfo), str);
            }
        }
    }

    public void getCourseRecommend(String str, String str2) {
        JSONObject jSONObject;
        LogUtil.a(TAG, "getCourseRecommend");
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "data is null");
            this.mBleOperatorCompactImpl.callBackJsResult(String.valueOf(1), str2, null);
            return;
        }
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h(TAG, "Network is not Connected");
            this.mBleOperatorCompactImpl.callBackJsResult(String.valueOf(1), str2, null);
            return;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "getCourseRecommend jsonException");
        }
        if (!jSONObject.has("type")) {
            LogUtil.a(TAG, "type is null");
            this.mBleOperatorCompactImpl.callBackJsResult(String.valueOf(1), str2, null);
            return;
        }
        if (!BleConstants.TYPE_FASCIA_GUN_INDEX.equals(jSONObject.getString("type"))) {
            LogUtil.a(TAG, "This type is not supported");
            this.mBleOperatorCompactImpl.callBackJsResult(String.valueOf(1), str2, null);
            return;
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(30257);
        stack.add(30281);
        stack.add(30264);
        stack.add(30258);
        stack.add(30265);
        stack.add(30259);
        stack.add(30260);
        stack.add(30262);
        stack.add(30266);
        stack.add(30273);
        stack.add(30274);
        stack.add(30283);
        ArrayList arrayList = new ArrayList(16);
        long currentTimeMillis = System.currentTimeMillis();
        execQueryAllData(stack, arrayList, currentTimeMillis, currentTimeMillis - 604800000, str2);
    }

    private void execQueryAllData(final Stack<Integer> stack, final List<iea> list, final long j, final long j2, final String str) {
        LogUtil.a(TAG, "execQueryAllData");
        if (stack.isEmpty()) {
            LogUtil.a(TAG, "types is empty");
            Gson gson = new Gson();
            ArrayList arrayList = new ArrayList();
            Iterator<iea> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(getActivityJsonMap(it.next()));
            }
            String json = gson.toJson(arrayList);
            LogUtil.c(TAG, "activityJson: ", json);
            startCourseRecommend(json, str);
            return;
        }
        if (this.mDataProcessor == null) {
            LogUtil.a(TAG, "mDataProcessor is null");
        } else {
            this.mDataProcessor.execQueryAllDataApi(j2, j, stack.pop().intValue(), str, new IuniversalCallback() { // from class: com.huawei.operation.h5pro.devicekind.MassageGun$$ExternalSyntheticLambda2
                @Override // com.huawei.hihealth.listener.IuniversalCallback
                public final void onResult(int i, Object obj, String str2) {
                    MassageGun.this.m710xcd680371(list, stack, j, j2, str, i, obj, str2);
                }
            });
        }
    }

    /* renamed from: lambda$execQueryAllData$1$com-huawei-operation-h5pro-devicekind-MassageGun, reason: not valid java name */
    /* synthetic */ void m710xcd680371(List list, Stack stack, long j, long j2, String str, int i, Object obj, String str2) {
        LogUtil.a(TAG, "execQuery resultCode = ", Integer.valueOf(i));
        if (i == 0 && (obj instanceof List)) {
            List list2 = (List) obj;
            LogUtil.a(TAG, "execQuery list size = ", Integer.valueOf(list2.size()));
            list.addAll(list2);
        } else {
            LogUtil.a(TAG, "detailInfo is other type");
        }
        execQueryAllData(stack, list, j, j2, str);
    }

    private Map<String, Object> getActivityJsonMap(iea ieaVar) {
        HiTrackMetaData hiTrackMetaData;
        HashMap hashMap = new HashMap();
        if (ieaVar == null) {
            return hashMap;
        }
        try {
            hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(ieaVar.getMetaData(), HiTrackMetaData.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b(TAG, "trackMetaData is error");
            hiTrackMetaData = null;
        }
        if (hiTrackMetaData == null) {
            return hashMap;
        }
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        Map<String, Integer> wearSportData = hiTrackMetaData.getWearSportData();
        assembleRopeDataMap(hashMap, extendTrackMap);
        assembleSwimDataMap(hashMap, extendTrackMap, wearSportData);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(getSportType(String.valueOf(hiTrackMetaData.getSportType()))));
        hashMap.put("startTime", String.valueOf(ieaVar.getStartTime()));
        hashMap.put("endTime", String.valueOf(ieaVar.getEndTime()));
        hashMap.put("totalTime", Long.valueOf(hiTrackMetaData.getTotalTime()));
        hashMap.put(BleConstants.TOTAL_DISTANCE, Integer.valueOf(hiTrackMetaData.getTotalDistance()));
        hashMap.put(BleConstants.TOTAL_CALORIES, Integer.valueOf(hiTrackMetaData.getTotalCalories()));
        hashMap.put("step", Integer.valueOf(hiTrackMetaData.getTotalSteps()));
        hashMap.put(BleConstants.AVERAGE_PACE, Float.valueOf(hiTrackMetaData.getAvgPace()));
        hashMap.put(BleConstants.AVERAGE_STEP_RATE, Integer.valueOf(hiTrackMetaData.getAvgStepRate()));
        hashMap.put(BleConstants.AVERAGE_HEART_RATE, Integer.valueOf(hiTrackMetaData.getAvgHeartRate()));
        hashMap.put(BleConstants.TOTAL_DESCENT, Float.valueOf(hiTrackMetaData.getTotalDescent()));
        hashMap.put("dataSource", Integer.valueOf(hiTrackMetaData.getSportDataSource()));
        return hashMap;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int getSportType(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 49748:
                if (str.equals(BleConstants.SPORT_TYPE_WALK)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 49749:
                if (str.equals(BleConstants.SPORT_TYPE_RUN)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 49750:
                if (str.equals(BleConstants.SPORT_TYPE_BIKE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 49776:
                if (str.equals(BleConstants.SPORT_TYPE_TREADMILL)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 49777:
                if (str.equals(BleConstants.SPORT_TYPE_INDOOR_BIKE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 49835:
                if (str.equals(BleConstants.SPORT_TYPE_INDOOR_WALKING)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return 30005;
        }
        if (c == 1) {
            return 30006;
        }
        if (c == 2) {
            return 30007;
        }
        if (c == 3) {
            return 30006;
        }
        if (c == 4) {
            return 30007;
        }
        if (c == 5) {
            return 30005;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtil.b(TAG, e.getMessage());
            return 0;
        }
    }

    private void assembleSwimDataMap(Map<String, Object> map, Map map2, Map map3) {
        if (map3.isEmpty()) {
            return;
        }
        map.put("swim_stroke", String.valueOf(map2.get("swim_stroke")));
        map.put(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES, String.valueOf(map2.get(HwExerciseConstants.JSON_NAME_SWIM_PULL_TIMES)));
        map.put("swim_pull_freq", String.valueOf(map2.get("swim_pull_freq")));
        map.put(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH, String.valueOf(map2.get(HwExerciseConstants.JSON_NAME_SWIM_POOL_LENGTH)));
        map.put("swim_laps", String.valueOf(map2.get("swim_laps")));
        map.put(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF, String.valueOf(map2.get(HwExerciseConstants.JSON_NAME_SWIM_AVG_SWOLF)));
        map.put("max_met", String.valueOf(map2.get("max_met")));
        map.put("recovery_time", String.valueOf(map2.get("recovery_time")));
    }

    private void assembleRopeDataMap(Map<String, Object> map, Map map2) {
        if (map2.isEmpty()) {
            return;
        }
        map.put("interruptTimes", String.valueOf(map2.get("interruptTimes")));
        map.put("maxJumpsTime", String.valueOf(map2.get("maxJumpsTime")));
        map.put("eteAlgoKey", String.valueOf(map2.get("eteAlgoKey")));
        map.put("skipSpeed", String.valueOf(map2.get("skipSpeed")));
        map.put("skipNum", String.valueOf(map2.get("skipNum")));
        map.put("maxSkippingTimes", String.valueOf(map2.get("maxSkippingTimes")));
    }

    private void startCourseRecommend(String str, final String str2) {
        LogUtil.a(TAG, "startCourseRecommend");
        PluginBaseAdapter adapter = PluginOperation.getInstance(null).getAdapter();
        if (adapter == null || TextUtils.isEmpty(this.mProductId)) {
            LogUtil.h(TAG, "mAdapter or mProductId is null!");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "activityJson is null");
            this.mBleOperatorCompactImpl.callBackJsResult(String.valueOf(1), str2, null);
        } else if (adapter instanceof PluginOperationAdapter) {
            ((PluginOperationAdapter) adapter).getCourseRecommend(this.mProductId, this.mUniqueId, str, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.devicekind.MassageGun$$ExternalSyntheticLambda0
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    MassageGun.this.m712x42adc245(str2, i, obj);
                }
            });
        }
    }

    /* renamed from: lambda$startCourseRecommend$2$com-huawei-operation-h5pro-devicekind-MassageGun, reason: not valid java name */
    /* synthetic */ void m712x42adc245(String str, int i, Object obj) {
        if (i == 0) {
            LogUtil.a(TAG, "getCourseRecommend success");
            this.mBleOperatorCompactImpl.callBackJsResult(String.valueOf(0), str, String.valueOf(obj));
            return;
        }
        LogUtil.a(TAG, "getCourseRecommend fail, errMsg");
        this.mBleOperatorCompactImpl.callBackJsResult(String.valueOf(1), str, null);
    }
}
