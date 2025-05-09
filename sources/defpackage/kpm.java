package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import defpackage.kwy;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kpm {
    private static volatile kpm d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f14529a = BaseApplication.getContext();

    private kpm() {
    }

    public static kpm c() {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new kpm();
                }
            }
        }
        return d;
    }

    public void d(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        int[] iArr = {OnRegisterSkinAttrsListener.REGISTER_BY_SCAN};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setConstantsKey(new String[]{"trackData"});
        hiDataReadOption.setCount(1);
        HiHealthManager.d(this.f14529a).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kpm.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                kpm.this.b(obj, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Track_HWHealthDataManager", "getTrackDetailForSportCard callback is null.");
            return;
        }
        if (!(obj instanceof SparseArray)) {
            LogUtil.h("Track_HWHealthDataManager", "getTrackDetailForSportCard onResult data not instanceof SparseArray");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.h("Track_HWHealthDataManager", "getTrackDetailForSportCard onResult map is empty.");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        Object obj2 = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
        if (!koq.e(obj2, HiHealthData.class)) {
            LogUtil.h("Track_HWHealthDataManager", "getTrackDetailForSportCard onResult obj not instanceof List");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        List list = (List) obj2;
        if (list.isEmpty()) {
            LogUtil.h("Track_HWHealthDataManager", "getTrackDetailForSportCard return map list is empty.");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (hiHealthData == null) {
            LogUtil.h("Track_HWHealthDataManager", "getTrackDetailForSportCard return hiHealthData is null.");
            iBaseResponseCallback.d(-1, null);
        } else {
            iBaseResponseCallback.d(0, kpt.e(hiHealthData));
        }
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{30002});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setConstantsKey(new String[]{"trackData"});
        hiDataReadOption.setCount(1);
        HiHealthManager.d(this.f14529a).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kpm.8
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                kpm.this.d(obj, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Track_HWHealthDataManager", "acquireLastTrackSimplifyData callback is null");
            return;
        }
        if (!(obj instanceof SparseArray)) {
            LogUtil.h("Track_HWHealthDataManager", "acquireLastTrackSimplifyData onResult data not instanceof SparseArray");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.h("Track_HWHealthDataManager", "acquireLastTrackSimplifyData onResult data is empty");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        Object obj2 = sparseArray.get(30002);
        if (!koq.e(obj2, HiHealthData.class)) {
            LogUtil.h("Track_HWHealthDataManager", "acquireLastTrackSimplifyData onResult data not instanceof List");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        List list = (List) obj2;
        if (list.isEmpty()) {
            LogUtil.h("Track_HWHealthDataManager", "acquireLastTrackSimplifyData onResult list is empty");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (hiHealthData != null) {
            SharedPreferenceManager.c("privacy_center", "sport_record", String.valueOf(hiHealthData.getStartTime()));
            iBaseResponseCallback.d(0, kpt.e(hiHealthData));
        } else {
            iBaseResponseCallback.d(100001, null);
            LogUtil.h("Track_HWHealthDataManager", "acquireLastTrackSimplifyData onResult hiHealthData is null");
        }
    }

    private static <T> T[] c(T[] tArr, T[]... tArr2) {
        int length = tArr.length;
        for (T[] tArr3 : tArr2) {
            length += tArr3.length;
        }
        T[] tArr4 = (T[]) Arrays.copyOf(tArr, length);
        int length2 = tArr.length;
        for (T[] tArr5 : tArr2) {
            System.arraycopy(tArr5, 0, tArr4, length2, tArr5.length);
            length2 += tArr5.length;
        }
        return tArr4;
    }

    public void b(long j, long j2, int i, final IBaseResponseCallback iBaseResponseCallback, final boolean z) {
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setStartTime(j);
        hiSportStatDataAggregateOption.setEndTime(j2);
        hiSportStatDataAggregateOption.setAggregateType(4);
        hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{287, 291});
        int[] iArr = {22};
        LogUtil.a("Track_HWHealthDataManager", "types", iArr);
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"Track_28722"});
        hiSportStatDataAggregateOption.setType(iArr);
        hiSportStatDataAggregateOption.setGroupUnitType(i);
        hiSportStatDataAggregateOption.setReadType(0);
        HiHealthNativeApi.a(this.f14529a).aggregateSportStatData(hiSportStatDataAggregateOption, new HiAggregateListener() { // from class: kpm.6
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (iBaseResponseCallback == null) {
                    LogUtil.h("Track_HWHealthDataManager", "requestDivingMaxDepth callback is null");
                    return;
                }
                HashMap hashMap = new HashMap();
                if (!koq.b(list)) {
                    iBaseResponseCallback.d(i2, kpm.this.b(list, "Track_28722", hashMap, z));
                } else {
                    LogUtil.h("Track_HWHealthDataManager", "requestDivingMaxDepth() all run data null or empty ");
                    iBaseResponseCallback.d(i2, hashMap);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Object, Double> b(List<HiHealthData> list, String str, Map<Object, Double> map, boolean z) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                if (!z) {
                    map.put(Long.valueOf(hiHealthData.getLong("start_time")), Double.valueOf(hiHealthData.getDouble(str)));
                } else {
                    map.put(hiHealthData.getInt("hihealth_type") + "#" + hiHealthData.getLong("start_time"), Double.valueOf(hiHealthData.getDouble(str)));
                }
            }
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(258);
        arrayList.add(257);
        arrayList.add(259);
        arrayList.add(264);
        arrayList.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE));
        arrayList.add(282);
        arrayList.add(281);
        if (koq.c(list)) {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                if (arrayList.contains(Integer.valueOf(it.next().intValue()))) {
                    it.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(List<Integer> list) {
        list.add(0, 0);
        list.add(1, 258);
        list.add(2, 257);
        list.add(3, 259);
        list.add(4, 10001);
    }

    public static void d(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.b("Track_HWHealthDataManager", "requestSortSportTypeList  callback is null");
            return;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setReadType(0);
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchSportTypeList(hiDataReadOption, new HiCommonListener() { // from class: kpm.10
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (!koq.e(obj, Integer.class)) {
                    LogUtil.b("Track_HWHealthDataManager", "data is null or not fit integer");
                    return;
                }
                ArrayList arrayList = (ArrayList) obj;
                kpm.f(arrayList);
                kpm.b(arrayList);
                kpm.c(arrayList);
                IBaseResponseCallback.this.d(0, arrayList);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                IBaseResponseCallback.this.d(0, new ArrayList());
            }
        });
    }

    public static void e(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.b("Track_HWHealthDataManager", "requestSubSortSportTypeList callback is null");
            return;
        }
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi != null) {
            recordApi.acquireFitnessRecordCategoryList(iBaseResponseCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(List<Integer> list) {
        if (koq.b(list)) {
            LogUtil.b("Track_HWHealthDataManager", "sportTypeList is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(262);
        arrayList.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM));
        d(list, arrayList, 262);
        arrayList.clear();
        arrayList.add(217);
        arrayList.add(218);
        arrayList.add(219);
        d(list, arrayList, 217);
        arrayList.clear();
        arrayList.add(287);
        arrayList.add(291);
        arrayList.add(288);
        arrayList.add(Integer.valueOf(ComponentInfo.PluginPay_A_N));
        d(list, arrayList, 287);
        arrayList.clear();
        arrayList.add(286);
        arrayList.add(Integer.valueOf(HeartRateThresholdConfig.HEART_RATE_LIMIT));
        d(list, arrayList, 286);
    }

    private static void d(List<Integer> list, List<Integer> list2, int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list2.iterator();
        while (it.hasNext()) {
            int indexOf = list.indexOf(Integer.valueOf(it.next().intValue()));
            arrayList.add(Integer.valueOf(indexOf));
            e(list, indexOf);
        }
        Collections.sort(arrayList);
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                i2 = -1;
                break;
            } else {
                i2 = ((Integer) it2.next()).intValue();
                if (i2 >= 0) {
                    break;
                }
            }
        }
        if (i2 < 0) {
            return;
        }
        list.add(i2, Integer.valueOf(i));
    }

    private static void e(List<Integer> list, int i) {
        if (koq.b(list, i)) {
            return;
        }
        list.remove(i);
    }

    public void c(long j, long j2, int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        a(new kwy.a().a(j).e(j2).d(i).d(), z, iBaseResponseCallback);
    }

    public void a(kwy kwyVar, final boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        int[] iArr;
        if (kwyVar == null) {
            return;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(kwyVar.a(), kwyVar.c());
        if (kwyVar.f() == 0) {
            iArr = null;
        } else if (kwyVar.d() != null) {
            iArr = kwyVar.d();
        } else {
            iArr = a(kwyVar.f());
        }
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setCount(kwyVar.e());
        hiDataReadOption.setSortOrder(1);
        HiHealthNativeApi.a(this.f14529a).fetchSequenceDataBySportType(hiDataReadOption, new HiDataReadResultListener() { // from class: kpm.9
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                kpm.this.d(obj, i, z, iBaseResponseCallback);
            }
        });
    }

    public void b(long j, long j2, int[] iArr, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        a(new kwy.a().a(j).e(j2).c(iArr).d(), z, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj, int i, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Track_HWHealthDataManager", "requestTrackSimplifyData callback is null.");
            return;
        }
        if (obj instanceof SparseArray) {
            SparseArray sparseArray = (SparseArray) obj;
            if (!koq.e(sparseArray.get(i), HiHealthData.class)) {
                iBaseResponseCallback.d(i, null);
                LogUtil.h("Track_HWHealthDataManager", "requestTrackSimplifyData onResult obj not instanceof List");
                return;
            }
            List<HiHealthData> list = (List) sparseArray.get(i);
            if (list.isEmpty()) {
                LogUtil.h("Track_HWHealthDataManager", "requestTrackSimplifyData onResult list is empty");
                iBaseResponseCallback.d(i, null);
            } else {
                if (z) {
                    iBaseResponseCallback.d(i, i(list));
                } else {
                    iBaseResponseCallback.d(i, list);
                }
                LogUtil.h("Track_HWHealthDataManager", "requestTrackSimplifyData list.size = ", Integer.valueOf(list.size()));
            }
        }
    }

    private String i(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("Track_HWHealthDataManager", "getMaxmetByTrackData trackData is null or empty.");
            return null;
        }
        int i = 0;
        long j = 0;
        int i2 = 0;
        for (HiHealthData hiHealthData : list) {
            try {
                Map<String, Integer> wearSportData = ((HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class)).getWearSportData();
                if (wearSportData != null && wearSportData.containsKey("max_met")) {
                    if (wearSportData.get("max_met") != null) {
                        i2 = wearSportData.get("max_met").intValue();
                    }
                    if (i2 > i) {
                        j = hiHealthData.getEndTime();
                        i = i2;
                    }
                }
            } catch (JsonSyntaxException unused) {
                LogUtil.h("Track_HWHealthDataManager", "analyzeTrackSimplifyData trackMetaData is error");
            }
        }
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(j));
        stringBuffer.append(Constants.LINK);
        stringBuffer.append(String.valueOf(i));
        return stringBuffer.toString();
    }

    public void a(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        int[] iArr;
        String[] strArr;
        int[] iArr2;
        String[] strArr2;
        if (iBaseResponseCallback == null) {
            LogUtil.h("Track_HWHealthDataManager", "requestTotalSportTimes callback is null.");
            return;
        }
        switch (i) {
            case 257:
                iArr = new int[]{42055, 42056};
                strArr = new String[]{"Track_Walk_Count_Sum", "Track_Walk_Abnormal_Count_Sum"};
                iArr2 = iArr;
                strArr2 = strArr;
                break;
            case 258:
                iArr = new int[]{42105, 42106};
                strArr = new String[]{"Track_Run_Count_Sum", "Track_Run_Abnormal_Count_Sum"};
                iArr2 = iArr;
                strArr2 = strArr;
                break;
            case 259:
                iArr = new int[]{42155, 42156};
                strArr = new String[]{"Track_Ride_Count_Sum", "Track_Ride_Abnormal_Count_Sum"};
                iArr2 = iArr;
                strArr2 = strArr;
                break;
            default:
                iArr2 = null;
                strArr2 = null;
                break;
        }
        e(iBaseResponseCallback, c(j, j2, 1, iArr2, strArr2), i, j, j2);
    }

    private void e(final IBaseResponseCallback iBaseResponseCallback, HiAggregateOption hiAggregateOption, int i, long j, long j2) {
        if (i == 283) {
            HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
            hiSportStatDataAggregateOption.setSortOrder(1);
            hiSportStatDataAggregateOption.setStartTime(j);
            hiSportStatDataAggregateOption.setEndTime(j2);
            hiSportStatDataAggregateOption.setAggregateType(1);
            hiSportStatDataAggregateOption.setGroupUnitType(7);
            hiSportStatDataAggregateOption.setReadType(0);
            hiSportStatDataAggregateOption.setConstantsKey(new String[]{"TIME"});
            hiSportStatDataAggregateOption.setType(new int[]{5});
            hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{i});
            HiHealthNativeApi.a(this.f14529a).aggregateSportStatData(hiSportStatDataAggregateOption, new HiAggregateListener() { // from class: kpm.4
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i2, int i3) {
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 == null) {
                        LogUtil.h("Track_HWHealthDataManager", "requestTrackStatData callback is null");
                    } else {
                        iBaseResponseCallback2.d(i2, list);
                    }
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                    if (iBaseResponseCallback == null) {
                        LogUtil.h("Track_HWHealthDataManager", "requestTrackStatData callback is null");
                    } else {
                        LogUtil.a("Track_HWHealthDataManager", "onResultIntent");
                    }
                }
            });
            return;
        }
        HiHealthManager.d(this.f14529a).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kpm.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    LogUtil.h("Track_HWHealthDataManager", "requestTrackStatData callback is null");
                } else {
                    iBaseResponseCallback2.d(i2, list);
                }
            }
        });
    }

    public void a(long j, long j2, int i, int i2, final IBaseResponseCallback iBaseResponseCallback) {
        int[] iArr;
        String[] strArr;
        LogUtil.a("Track_HWHealthDataManager", "requestTrackStatDistance sportType = ", Integer.valueOf(i2), " timeUnit = ", Integer.valueOf(i));
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setReadType(0);
        if (i2 != 282) {
            switch (i2) {
                case 257:
                    break;
                case 258:
                case 260:
                    iArr = new int[]{42102};
                    strArr = new String[]{"Track_Run_Distance_Sum"};
                    break;
                case 259:
                    iArr = new int[]{42152};
                    strArr = new String[]{"Track_Ride_Distance_Sum"};
                    break;
                default:
                    LogUtil.h("Track_HWHealthDataManager", "requestTrackStatDistance not supported");
                    return;
            }
            hiAggregateOption.setConstantsKey(strArr);
            hiAggregateOption.setType(iArr);
            ArrayList arrayList = new ArrayList();
            arrayList.add(hiAggregateOption);
            HiHealthManager.d(this.f14529a).aggregateHiHealthDataEx(arrayList, new HiAggregateListenerEx() { // from class: kpm.1
                @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
                public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i3, int i4) {
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 == null) {
                        LogUtil.h("Track_HWHealthDataManager", "requestTrackStatDistance callback is null");
                    } else {
                        iBaseResponseCallback2.d(i3, sparseArray);
                    }
                }
            });
        }
        iArr = new int[]{42052};
        strArr = new String[]{"Track_Walk_Distance_Sum"};
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setType(iArr);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(hiAggregateOption);
        HiHealthManager.d(this.f14529a).aggregateHiHealthDataEx(arrayList2, new HiAggregateListenerEx() { // from class: kpm.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i3, int i4) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    LogUtil.h("Track_HWHealthDataManager", "requestTrackStatDistance callback is null");
                } else {
                    iBaseResponseCallback2.d(i3, sparseArray);
                }
            }
        });
    }

    public void e(long j, long j2, int i, int i2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Track_HWHealthDataManager", "requestTrackStatData sportType = ", Integer.valueOf(i2), " timeUnit = ", Integer.valueOf(i));
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setStartTime(j);
        hiSportStatDataAggregateOption.setEndTime(j2);
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setGroupUnitType(7);
        hiSportStatDataAggregateOption.setReadType(0);
        hln c = hln.c(this.f14529a);
        if (c.d(i2) == null || c.d(i2).getSportDataStatics() == null) {
            LogUtil.b("Track_HWHealthDataManager", "can not find sport type in json");
            return;
        }
        LogUtil.a("Track_HWHealthDataManager", "mSportType:", Integer.valueOf(i2));
        int[] e2 = e(c, i2);
        hiSportStatDataAggregateOption.setConstantsKey(d(c, i2));
        hiSportStatDataAggregateOption.setType(e2);
        if (i2 == 287) {
            hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{i2, 291});
        } else {
            hiSportStatDataAggregateOption.setHiHealthTypes(gts.a(i2));
        }
        HiHealthNativeApi.a(this.f14529a).aggregateSportStatData(hiSportStatDataAggregateOption, new HiAggregateListener() { // from class: kpm.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i3, int i4) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    LogUtil.h("Track_HWHealthDataManager", "requestTrackStatData callback is null");
                } else {
                    iBaseResponseCallback2.d(i3, list);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
                if (iBaseResponseCallback == null) {
                    LogUtil.h("Track_HWHealthDataManager", "requestTrackStatData callback is null");
                } else {
                    LogUtil.a("Track_HWHealthDataManager", "onResultIntent");
                }
            }
        });
    }

    private int[] e(hln hlnVar, int i) {
        int[] a2 = hlnVar.a(hlnVar.d(i).getSportDataStatics());
        if (i != 286) {
            return a2;
        }
        int[] iArr = new int[a2.length + 4];
        System.arraycopy(new int[]{42405, 42406, 42404, 42403}, 0, iArr, 0, 4);
        System.arraycopy(a2, 0, iArr, 4, a2.length);
        return iArr;
    }

    private String[] d(hln hlnVar, int i) {
        String[] b = hlnVar.b(hlnVar.d(i).getSportDataStatics());
        return i == 286 ? (String[]) c(hlnVar.b(hlnVar.d(HeartRateThresholdConfig.HEART_RATE_LIMIT).getSportDataStatics()), b) : b;
    }

    public void d(long j, long j2, int i, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Track_HWHealthDataManager", "requestSportStatSum", " timeUnit = ", Integer.valueOf(i));
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setStartTime(j);
        hiSportStatDataAggregateOption.setEndTime(j2);
        hiSportStatDataAggregateOption.setType(new int[]{com.huawei.operation.utils.Constants.REBACK_MARKET_ENTRANCE, 42005, com.huawei.operation.utils.Constants.REBACK_MARKET_RESULT_CODE, 42008, 5, 4});
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"Track_Duration_Sum", "Track_Count_Sum", "Track_Calorie_Sum", "Track_Abnormal_Count_Sum", "Track_Diving_Count", "Track_Diving_Time_Sum"});
        hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{30001, 288, ComponentInfo.PluginPay_A_N});
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setGroupUnitType(i);
        hiSportStatDataAggregateOption.setSortOrder(1);
        HiHealthNativeApi.a(this.f14529a).aggregateSportStatData(hiSportStatDataAggregateOption, new HiAggregateListener() { // from class: kpm.7
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    LogUtil.b("Track_HWHealthDataManager", "requestSportStatSum callback is null");
                } else {
                    iBaseResponseCallback2.d(i2, list);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                if (iBaseResponseCallback == null) {
                    LogUtil.b("Track_HWHealthDataManager", "requestSportStatSum callback is null");
                } else {
                    LogUtil.a("Track_HWHealthDataManager", "requestSportStatSum onResultIntent");
                }
            }
        });
    }

    private HiAggregateOption c(long j, long j2, int i, int[] iArr, String[] strArr) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setAggregateType(i);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setType(iArr);
        return hiAggregateOption;
    }

    private int[] a(int i) {
        return i != 217 ? i != 262 ? i != 257 ? i != 258 ? i != 286 ? i != 287 ? new int[]{i} : new int[]{i, 288, ComponentInfo.PluginPay_A_N, 291} : new int[]{i, HeartRateThresholdConfig.HEART_RATE_LIMIT} : new int[]{i, 264, OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE} : new int[]{i, 281, 282} : new int[]{i, OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM} : new int[]{i, 218, 219};
    }
}
