package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.google.json.JsonSanitizer;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthDeviceApi;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes3.dex */
public class dei {
    private CommonUiResponse<List<List<HiHealthData>>> c;

    private dei() {
    }

    public static dei c() {
        return e.b;
    }

    static class e {
        private static final dei b = new dei();
    }

    public void d(long j, long j2, int i, String str, IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setDeviceUuid(str);
        hiDataReadOption.setReadType(2);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(new int[]{10001});
        if (i != 0) {
            hiDataReadOption.setCount(i);
        }
        LogUtil.a("HealthDataInteractor", "getBloodSugarData readOption: ", hiDataReadOption.toString(), "; uniqueId: ", CommonUtil.l(str));
        kor.a().e(BaseApplication.getContext(), hiDataReadOption, iBaseResponseCallback);
    }

    public void d(final CommonUiResponse<HiHealthData> commonUiResponse) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        final long currentTimeMillis = System.currentTimeMillis();
        hiDataReadOption.setTimeInterval(0L, currentTimeMillis);
        hiDataReadOption.setCount(1);
        hiDataReadOption.setType(HiHealthDataType.d(10001));
        kor.a().e(BaseApplication.getContext(), hiDataReadOption, new IBaseResponseCallback() { // from class: def
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                dei.this.d(currentTimeMillis, commonUiResponse, i, obj);
            }
        });
    }

    /* synthetic */ void d(long j, CommonUiResponse commonUiResponse, int i, Object obj) {
        LogUtil.a("HealthDataInteractor", "queryLastBloodSugarData time = ", Long.valueOf(System.currentTimeMillis() - j));
        e(i, (int) obj, (CommonUiResponse<HiHealthData>) commonUiResponse);
    }

    public void c(String str, final CommonUiResponse<HiHealthData> commonUiResponse) {
        final long currentTimeMillis = System.currentTimeMillis();
        d(0L, currentTimeMillis, 1, str, new IBaseResponseCallback() { // from class: dek
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                dei.this.e(currentTimeMillis, commonUiResponse, i, obj);
            }
        });
    }

    /* synthetic */ void e(long j, CommonUiResponse commonUiResponse, int i, Object obj) {
        LogUtil.a("HealthDataInteractor", "queryLastBloodSugarData time = ", Long.valueOf(System.currentTimeMillis() - j));
        e(i, (int) obj, (CommonUiResponse<HiHealthData>) commonUiResponse);
    }

    private <T> void e(int i, T t, CommonUiResponse<HiHealthData> commonUiResponse) {
        if (i != 0) {
            commonUiResponse.onResponse(100001, null);
        } else if (t instanceof List) {
            commonUiResponse.onResponse(0, (HiHealthData) ((List) t).get(0));
        }
    }

    public void b(HiHealthData hiHealthData, final CommonUiResponse<Integer> commonUiResponse) {
        if (hiHealthData == null) {
            LogUtil.h("HealthDataInteractor", "getLatestBloodSugarDataOneDay healthData is null");
            return;
        }
        long startTime = hiHealthData.getStartTime();
        LogUtil.a("HealthDataInteractor", "getLatestBloodSugarDataOneDay latestTime = ", Long.valueOf(startTime));
        if (startTime == SharedPreferenceManager.b("blood_sugar_module_id", "latest_one_day_time", 0L)) {
            LogUtil.a("HealthDataInteractor", "getLatestBloodSugarDataOneDay latestTime has not changed");
            int a2 = SharedPreferenceManager.a("blood_sugar_module_id", "latest_one_day_measure_count", 0);
            if (a2 > 0) {
                commonUiResponse.onResponse(0, Integer.valueOf(a2));
                return;
            }
            return;
        }
        SharedPreferenceManager.e("blood_sugar_module_id", "latest_one_day_time", startTime);
        long b = gib.b(startTime);
        long a3 = gib.a(startTime);
        LogUtil.a("HealthDataInteractor", "getLatestBloodSugarDataOneDay startTime = ", Long.valueOf(b), " endTime = ", Long.valueOf(a3));
        final long currentTimeMillis = System.currentTimeMillis();
        d(b, a3, 0, hiHealthData.getDeviceUuid(), new IBaseResponseCallback() { // from class: dee
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                dei.this.c(currentTimeMillis, commonUiResponse, i, obj);
            }
        });
    }

    /* synthetic */ void c(long j, CommonUiResponse commonUiResponse, int i, Object obj) {
        LogUtil.a("HealthDataInteractor", "getLatestBloodSugarDataOneDay time = ", Long.valueOf(System.currentTimeMillis() - j));
        a(i, (int) obj, (CommonUiResponse<Integer>) commonUiResponse);
    }

    private <T> void a(int i, T t, CommonUiResponse<Integer> commonUiResponse) {
        if (i != 0) {
            commonUiResponse.onResponse(100001, 0);
        } else if (t instanceof List) {
            int size = ((List) t).size();
            SharedPreferenceManager.b("blood_sugar_module_id", "latest_one_day_measure_count", size);
            LogUtil.h("HealthDataInteractor", "handleLatestDataOneDay blood sugar measure count = ", Integer.valueOf(size));
            commonUiResponse.onResponse(0, Integer.valueOf(size));
        }
    }

    public void e(String str, CommonUiResponse<List<List<HiHealthData>>> commonUiResponse) {
        d(0L, System.currentTimeMillis(), str, commonUiResponse);
    }

    public void d(long j, long j2, String str, CommonUiResponse<List<List<HiHealthData>>> commonUiResponse) {
        this.c = commonUiResponse;
        final long currentTimeMillis = System.currentTimeMillis();
        d(j, j2, 0, str, new IBaseResponseCallback() { // from class: del
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                dei.this.e(currentTimeMillis, i, obj);
            }
        });
    }

    /* synthetic */ void e(long j, int i, Object obj) {
        LogUtil.a("HealthDataInteractor", "readBloodSugarData time = ", Long.valueOf(System.currentTimeMillis() - j));
        e((dei) obj);
    }

    private <T> void e(T t) {
        LogUtil.a("HealthDataInteractor", "handleBloodSugarData enter");
        if (t instanceof List) {
            d((List<HiHealthData>) t);
        }
        LogUtil.a("HealthDataInteractor", "handleBloodSugarData end");
    }

    private void d(List<HiHealthData> list) {
        LogUtil.a("HealthDataInteractor", "handleBloodSugarData dataList.size = ", Integer.valueOf(list.size()));
        List<List<HiHealthData>> e2 = e(list);
        LogUtil.a("HealthDataInteractor", "handleBloodSugarData dataLists.size = ", Integer.valueOf(e2.size()));
        CommonUiResponse<List<List<HiHealthData>>> commonUiResponse = this.c;
        if (commonUiResponse != null) {
            commonUiResponse.onResponse(0, e2);
        }
    }

    private List<List<HiHealthData>> e(List<HiHealthData> list) {
        TreeMap treeMap = new TreeMap(new Comparator() { // from class: deg
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((Long) obj2).compareTo((Long) obj);
                return compareTo;
            }
        });
        for (HiHealthData hiHealthData : list) {
            long a2 = gib.a(nsj.g(hiHealthData.getEndTime()));
            List<HiHealthData> list2 = (List) treeMap.get(Long.valueOf(a2));
            if (list2 == null) {
                ArrayList arrayList = new ArrayList();
                treeMap.put(Long.valueOf(a2), arrayList);
                arrayList.add(hiHealthData);
            } else {
                c(list2, hiHealthData);
            }
        }
        return new ArrayList(treeMap.values());
    }

    private void c(List<HiHealthData> list, HiHealthData hiHealthData) {
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            if (list.get(i).getType() > hiHealthData.getType()) {
                size = i;
                break;
            }
            i++;
        }
        list.add(size, hiHealthData);
    }

    public void a(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c("HealthDataInteractor", "deleteBloodSugarData");
        kor.a().a(BaseApplication.getContext(), i, j, j2, iBaseResponseCallback);
    }

    public void e(HiHealthData hiHealthData, String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c("HealthDataInteractor", "insertBloodSugarData");
        kor.a().d(BaseApplication.getContext(), hiHealthData, str, iBaseResponseCallback);
    }

    public void e(HiHealthData hiHealthData, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchDataSource(HiDataSourceFetchOption.builder().a(1).c(new int[]{hiHealthData.getClientId()}).e(), new HiDataClientListener() { // from class: den
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public final void onResult(List list) {
                dei.b(IBaseResponseCallback.this, list);
            }
        });
    }

    static /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, List list) {
        if (koq.b(list)) {
            LogUtil.h("HealthDataInteractor", "getBloodSugarDataOrigin clientList is null");
            iBaseResponseCallback.d(100001, null);
        } else {
            LogUtil.a("HealthDataInteractor", "getBloodSugarDataOrigin SUCCESS");
            iBaseResponseCallback.d(0, ((HiHealthClient) list.get(0)).getPackageName());
        }
    }

    public void a(int i, String str, HiDataClientListener hiDataClientListener) {
        HiHealthDeviceApi.a(BaseApplication.getContext()).fetchDataClientByUniqueId(i, str, hiDataClientListener);
    }

    public void a(Context context, final CommonUiResponse<HiUserInfo> commonUiResponse) {
        HiHealthNativeApi.a(context).fetchUserData(new HiCommonListener() { // from class: dei.5
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (!(obj instanceof List)) {
                    LogUtil.h("HealthDataInteractor", "contrastCalorieInfoForApp data is fail");
                    return;
                }
                List list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.h("HealthDataInteractor", "setDeviceWeightData userInfos is null or size is zero");
                    return;
                }
                HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                if (hiUserInfo == null) {
                    LogUtil.h("HealthDataInteractor", "setDeviceWeightData onSuccess HiUserInfo is null");
                } else {
                    commonUiResponse.onResponse(0, hiUserInfo);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("HealthDataInteractor", "setDeviceWeightData fetchUserData falied");
            }
        });
    }

    public void b(CommonUiResponse<HeartRateZoneMgr> commonUiResponse) {
        LogUtil.a("HealthDataInteractor", "setRopeHeartRateZone");
        commonUiResponse.onResponse(0, kox.e().b());
    }

    public void e(final CommonUiResponse<Pair<Set<Long>, Map<Long, List<HiHealthData>>>> commonUiResponse) {
        final long currentTimeMillis = System.currentTimeMillis();
        kor.a().a(0L, currentTimeMillis, new int[]{283}, new IBaseResponseCallback() { // from class: deh
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                dei.this.b(currentTimeMillis, commonUiResponse, i, obj);
            }
        });
    }

    /* synthetic */ void b(long j, CommonUiResponse commonUiResponse, int i, Object obj) {
        LogUtil.a("HealthDataInteractor", "queryRopeData time = ", Long.valueOf(System.currentTimeMillis() - j), ", onResult errorCode = ", Integer.valueOf(i));
        if (!(obj instanceof List)) {
            LogUtil.h("HealthDataInteractor", "objData is not instance of List");
            commonUiResponse.onResponse(-1, null);
        } else {
            a((List<HiHealthData>) obj, (CommonUiResponse<Pair<Set<Long>, Map<Long, List<HiHealthData>>>>) commonUiResponse);
        }
    }

    private void a(List<HiHealthData> list, CommonUiResponse<Pair<Set<Long>, Map<Long, List<HiHealthData>>>> commonUiResponse) {
        List<HiHealthData> a2 = a(list);
        if (koq.b(a2)) {
            LogUtil.a("HealthDataInteractor", "handleRopeData filterRopeData is null");
            commonUiResponse.onResponse(-1, null);
            return;
        }
        Map<Long, List<HiHealthData>> c = c(a2);
        if (c.isEmpty()) {
            LogUtil.a("HealthDataInteractor", "handleRopeData map is null");
            commonUiResponse.onResponse(-1, null);
        } else {
            LogUtil.a("HealthDataInteractor", "handleRopeData dataList.size = ", Integer.valueOf(list.size()), ", map.size = ", Integer.valueOf(c.size()));
            commonUiResponse.onResponse(0, new Pair<>(c.keySet(), c));
        }
    }

    private List<HiHealthData> a(List<HiHealthData> list) {
        HiTrackMetaData hiTrackMetaData;
        Map<String, String> extendTrackMap;
        ArrayList arrayList = null;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && (hiTrackMetaData = (HiTrackMetaData) nrv.b(JsonSanitizer.sanitize(hiHealthData.getMetaData()), HiTrackMetaData.class)) != null && (extendTrackMap = hiTrackMetaData.getExtendTrackMap()) != null && extendTrackMap.size() != 0 && !TextUtils.isEmpty(extendTrackMap.get("enduranceAbilityRank"))) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(hiHealthData);
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = "filterRopeDataWithoutRank, healthDataList.size = ";
        objArr[1] = Integer.valueOf(arrayList != null ? arrayList.size() : 0);
        LogUtil.a("HealthDataInteractor", objArr);
        return arrayList;
    }

    private Map<Long, List<HiHealthData>> c(List<HiHealthData> list) {
        TreeMap treeMap = new TreeMap(new Comparator() { // from class: dej
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((Long) obj2).compareTo((Long) obj);
                return compareTo;
            }
        });
        for (HiHealthData hiHealthData : list) {
            long c = gib.c(hiHealthData.getStartTime());
            List<HiHealthData> list2 = (List) treeMap.get(Long.valueOf(c));
            if (list2 == null) {
                ArrayList arrayList = new ArrayList();
                treeMap.put(Long.valueOf(c), arrayList);
                arrayList.add(hiHealthData);
            } else {
                c(list2, hiHealthData);
            }
        }
        return treeMap;
    }

    public void a(HiHealthData hiHealthData, CommonUiResponse<knu> commonUiResponse) {
        if (hiHealthData == null) {
            LogUtil.h("HealthDataInteractor", "getTrackDetailData healthData is null");
        } else {
            c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), commonUiResponse);
        }
    }

    public void c(long j, long j2, final CommonUiResponse<knu> commonUiResponse) {
        final long currentTimeMillis = System.currentTimeMillis();
        hps.a(j, j2, new IBaseResponseCallback() { // from class: dem
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                dei.a(currentTimeMillis, commonUiResponse, i, obj);
            }
        });
    }

    static /* synthetic */ void a(long j, CommonUiResponse commonUiResponse, int i, Object obj) {
        LogUtil.a("HealthDataInteractor", "getTrackDetailData time = ", Long.valueOf(System.currentTimeMillis() - j), ", onResponse errorCode = ", Integer.valueOf(i));
        if (!(obj instanceof knu)) {
            LogUtil.h("HealthDataInteractor", "objData is not instance of HWHealthDataManager.PathData");
        } else {
            commonUiResponse.onResponse(0, (knu) obj);
        }
    }
}
