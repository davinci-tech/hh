package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes9.dex */
public class pkh implements IChartStorageHelper {
    private DataInfos b;
    private BloodOxygenDayDetailFragmentPresenter c;

    public pkh(BloodOxygenDayDetailFragmentPresenter bloodOxygenDayDetailFragmentPresenter) {
        this.c = bloodOxygenDayDetailFragmentPresenter;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper
    public void queryStepDayData(Context context, long j, long j2, DataInfos dataInfos, HwHealthChartHolder.b bVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (dataInfos.isDayData() && !nom.p(j)) {
            responseCallback.onResult(-1, null);
            return;
        }
        this.b = dataInfos;
        int i = AnonymousClass3.f16166a[dataInfos.ordinal()];
        if (i == 1 || i == 2) {
            if (dataInfos == DataInfos.BloodOxygenDayHorizontal && nsn.a(100)) {
                LogUtil.h("BloodOxygenLineChartStorageHelper", "queryStepDayData read many times");
                return;
            } else {
                e(context, j, j2, responseCallback);
                return;
            }
        }
        LogUtil.h("R_BloodOxygen_BloodOxygenLineChartStorageHelper", "no case match !");
    }

    /* renamed from: pkh$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f16166a;

        static {
            int[] iArr = new int[DataInfos.values().length];
            f16166a = iArr;
            try {
                iArr[DataInfos.BloodOxygenDayHorizontal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f16166a[DataInfos.BloodOxygenDayDetail.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private HiDataReadOption a(long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        LogUtil.a("BloodOxygenLineChartStorageHelper", "getReadOption startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(new int[]{2103, 2107, DicDataTypeUtil.DataType.ALTITUDE_TYPE.value(), DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value()});
        return hiDataReadOption;
    }

    private void e(Context context, long j, long j2, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("BloodOxygenLineChartStorageHelper", "requestDayOxygenData callback is null");
        } else {
            HiHealthNativeApi.a(context).readHiHealthData(a(j, j2), new b(responseCallback));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
        int i;
        List<HiHealthData> list;
        if (!(obj instanceof SparseArray)) {
            LogUtil.b("BloodOxygenLineChartStorageHelper", "data can not convert");
            responseCallback.onResult(-1, null);
            this.c.notifyLatestBloodOxygen(-1, null);
            this.c.notifyMaxAndMinBloodOxy(-1, null);
            this.c.notifyRemindBloodOxygen(-1, null);
            this.c.notifyAltitude(-1, null);
            this.c.notifyLakelouise(-1, null);
            ObserverManagerUtil.c("BLOOD_AND_ALTITUDE_DATA", 0);
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (this.b != DataInfos.BloodOxygenDayHorizontal) {
            ObserverManagerUtil.c("BLOOD_OXYGEN_RECORDS", sparseArray);
        }
        if (sparseArray.size() <= 0) {
            responseCallback.onResult(-1, null);
            this.c.notifyLatestBloodOxygen(-1, null);
            this.c.notifyMaxAndMinBloodOxy(-1, null);
            this.c.notifyRemindBloodOxygen(-1, null);
            this.c.notifyAltitude(-1, null);
            this.c.notifyLakelouise(-1, null);
            ObserverManagerUtil.c("BLOOD_AND_ALTITUDE_DATA", 0);
            return;
        }
        List<HiHealthData> arrayList = new ArrayList<>(16);
        Object obj2 = sparseArray.get(2103);
        if (obj2 instanceof List) {
            List<HiHealthData> list2 = (List) obj2;
            LogUtil.a("BloodOxygenLineChartStorageHelper", "oxygenMeasureValueList.size :", Integer.valueOf(list2.size()));
            arrayList = list2;
        }
        List<HiHealthData> arrayList2 = new ArrayList<>(16);
        Object obj3 = sparseArray.get(2107);
        if (obj3 instanceof List) {
            List<HiHealthData> list3 = (List) obj3;
            LogUtil.a("BloodOxygenLineChartStorageHelper", "oxygenRemindValueList.size :", Integer.valueOf(list3.size()));
            arrayList2 = list3;
        }
        List<HiHealthData> arrayList3 = new ArrayList<>(16);
        Object obj4 = sparseArray.get(DicDataTypeUtil.DataType.ALTITUDE_TYPE.value());
        if (obj4 instanceof List) {
            List<HiHealthData> list4 = (List) obj4;
            LogUtil.a("BloodOxygenLineChartStorageHelper", "altitudeValueList.size :", Integer.valueOf(list4.size()));
            arrayList3 = list4;
        }
        if (koq.c(arrayList)) {
            i = koq.c(arrayList3) ? 1 : 2;
        } else {
            arrayList3.clear();
            i = 0;
        }
        ObserverManagerUtil.c("BLOOD_AND_ALTITUDE_DATA", Integer.valueOf(i));
        ArrayList arrayList4 = new ArrayList(16);
        Object obj5 = sparseArray.get(DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value());
        if (obj5 instanceof List) {
            List<HiHealthData> list5 = (List) obj5;
            LogUtil.a("BloodOxygenLineChartStorageHelper", "lakeLouiseValueList.size :", Integer.valueOf(list5.size()));
            list = list5;
        } else {
            list = arrayList4;
        }
        this.c.notifyRemindBloodOxygen(0, arrayList2);
        this.c.notifyLatestBloodOxygen(0, arrayList);
        this.c.notifyMaxAndMinBloodOxy(0, arrayList);
        this.c.notifyAltitude(0, arrayList3);
        this.c.notifyLakelouise(0, list);
        HashMap hashMap = new HashMap(16);
        if (!SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS").equals("true")) {
            arrayList3.clear();
            list.clear();
        }
        a(arrayList, arrayList2, arrayList3, list, hashMap);
        if (hashMap.size() > 0) {
            responseCallback.onResult(0, hashMap);
        } else {
            responseCallback.onResult(-1, null);
        }
    }

    private void b(List<HiHealthData> list, List<HiHealthData> list2, Map<Long, IStorageModel> map) {
        ecm ecmVar;
        for (HiHealthData hiHealthData : list) {
            long startTime = hiHealthData.getStartTime();
            if (hiHealthData.getIntValue() <= 100 && hiHealthData.getIntValue() > 0) {
                map.put(Long.valueOf(startTime), new ecm(hiHealthData.getIntValue()));
            } else {
                LogUtil.h("BloodOxygenLineChartStorageHelper", "max > 100 or min <0");
            }
        }
        if (koq.c(list2)) {
            for (int i = 0; i < list2.size(); i++) {
                HiHealthData hiHealthData2 = list2.get(i);
                long startTime2 = hiHealthData2.getStartTime();
                if (map.get(Long.valueOf(startTime2)) instanceof ecm) {
                    ecmVar = (ecm) map.get(Long.valueOf(startTime2));
                    ecmVar.a(hiHealthData2.getIntValue());
                } else {
                    ecmVar = new ecm(0.0f);
                    ecmVar.a(hiHealthData2.getIntValue());
                }
                map.put(Long.valueOf(startTime2), ecmVar);
            }
        }
    }

    private void a(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3, List<HiHealthData> list4, Map<Long, IStorageModel> map) {
        if (this.b == DataInfos.BloodOxygenDayHorizontal) {
            LogUtil.a("BloodOxygenLineChartStorageHelper", "setResultMap isHorizontal");
            b(list, list3, map);
            return;
        }
        HashSet hashSet = new HashSet(16);
        StringBuilder sb = new StringBuilder();
        for (HiHealthData hiHealthData : list2) {
            hashSet.add(Long.valueOf(hiHealthData.getStartTime()));
            sb.append(hiHealthData.getStartTime());
            sb.append(" ");
            sb.append(hiHealthData.getType());
            sb.append(" ");
            sb.append(hiHealthData.getIntValue());
            sb.append(",");
        }
        LogUtil.a("BloodOxygenLineChartStorageHelper", "setResultMap remind data = ", sb.toString());
        a(list, list3, list4, hashSet, map);
    }

    private void a(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3, Set<Long> set, Map<Long, IStorageModel> map) {
        ecm ecmVar;
        ecm ecmVar2;
        StringBuilder sb = new StringBuilder();
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            float f = 0.0f;
            if (!it.hasNext()) {
                break;
            }
            HiHealthData next = it.next();
            if (next != null && next.getIntValue() > 0) {
                long startTime = next.getStartTime();
                sb.append(startTime);
                sb.append(" ");
                sb.append(next.getType());
                sb.append(" ");
                sb.append(next.getIntValue());
                sb.append(",");
                Calendar b2 = b(startTime);
                IStorageModel iStorageModel = map.get(Long.valueOf(b2.getTimeInMillis()));
                if (iStorageModel instanceof ecm) {
                    ecmVar2 = (ecm) iStorageModel;
                    f = ecmVar2.a();
                } else {
                    ecmVar2 = new ecm(0.0f);
                }
                ecm ecmVar3 = ecmVar2;
                float f2 = f;
                if (set.contains(Long.valueOf(startTime))) {
                    ecmVar3.b(true);
                }
                e(map, next, b2, ecmVar3, f2);
            }
        }
        if (koq.c(list2)) {
            HiHealthData[] hiHealthDataArr = new HiHealthData[2];
            int i = -1;
            int i2 = 0;
            while (i2 < list2.size()) {
                HiHealthData hiHealthData = list2.get(i2);
                long startTime2 = hiHealthData.getStartTime();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(startTime2);
                int i3 = calendar.get(11) * 2;
                int i4 = calendar.get(12) < 30 ? i3 + 1 : i3 + 2;
                if (i2 % 5 == 0) {
                    b(map, hiHealthData);
                } else {
                    int intValue = hiHealthData.getIntValue();
                    if (i == i4 || i == -1) {
                        HiHealthData hiHealthData2 = hiHealthDataArr[0];
                        if (hiHealthData2 == null || intValue < hiHealthData2.getIntValue()) {
                            hiHealthDataArr[0] = hiHealthData;
                        }
                        HiHealthData hiHealthData3 = hiHealthDataArr[1];
                        if (hiHealthData3 == null || intValue > hiHealthData3.getIntValue()) {
                            hiHealthDataArr[1] = hiHealthData;
                        }
                        if (i2 == list2.size() - 1) {
                            b(map, hiHealthDataArr[0]);
                            b(map, hiHealthDataArr[1]);
                        }
                    } else {
                        b(map, hiHealthDataArr[0]);
                        b(map, hiHealthDataArr[1]);
                        hiHealthDataArr = new HiHealthData[2];
                    }
                }
                i2++;
                i = i4;
            }
        }
        if (koq.c(list3)) {
            for (HiHealthData hiHealthData4 : list3) {
                long startTime3 = hiHealthData4.getStartTime();
                if (map.get(Long.valueOf(startTime3)) instanceof ecm) {
                    ecmVar = (ecm) map.get(Long.valueOf(startTime3));
                    ecmVar.e(hiHealthData4.getIntValue());
                } else {
                    ecmVar = new ecm(0.0f);
                    ecmVar.e(hiHealthData4.getIntValue());
                }
                map.put(Long.valueOf(startTime3), ecmVar);
            }
        }
        TreeMap treeMap = new TreeMap(new Comparator<Long>() { // from class: pkh.2
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(Long l, Long l2) {
                return (int) (l2.longValue() - l.longValue());
            }
        });
        treeMap.putAll(map);
        new HashMap(16);
        HashMap hashMap = new HashMap(16);
        for (Map.Entry entry : treeMap.entrySet()) {
            ecm ecmVar4 = (ecm) entry.getValue();
            ecmVar4.d(Math.round(ecmVar4.a()));
            hashMap.put((Long) entry.getKey(), ecmVar4);
        }
        treeMap.clear();
        treeMap.putAll(hashMap);
        LogUtil.a("BloodOxygenLineChartStorageHelper", "setHalfHourData all data = ", sb.toString());
    }

    private void b(Map<Long, IStorageModel> map, HiHealthData hiHealthData) {
        ecm ecmVar;
        if (hiHealthData == null) {
            return;
        }
        long startTime = hiHealthData.getStartTime();
        if (map.get(Long.valueOf(startTime)) instanceof ecm) {
            ecmVar = (ecm) map.get(Long.valueOf(startTime));
        } else {
            ecmVar = new ecm(0.0f);
        }
        ecmVar.a(hiHealthData.getIntValue());
        map.put(Long.valueOf(startTime), ecmVar);
    }

    private void e(Map<Long, IStorageModel> map, HiHealthData hiHealthData, Calendar calendar, ecm ecmVar, float f) {
        if (f == 0.0f) {
            ecmVar.d(hiHealthData.getIntValue());
            ecmVar.b(1);
        } else {
            int c = ecmVar.c();
            float f2 = c;
            int i = c + 1;
            ecmVar.b(i);
            ecmVar.d(((f * f2) + hiHealthData.getIntValue()) / i);
        }
        map.put(Long.valueOf(calendar.getTimeInMillis()), ecmVar);
    }

    private Calendar b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.setTimeZone(jdl.d(jdl.q(j)));
        if (calendar.get(12) / 30 == 0) {
            calendar.set(12, 14);
            calendar.set(13, 30);
        } else {
            calendar.set(12, 44);
            calendar.set(13, 30);
        }
        calendar.set(14, 0);
        return calendar;
    }

    static class b implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<pkh> f16167a;
        private ResponseCallback<Map<Long, IStorageModel>> c;

        private b(pkh pkhVar, ResponseCallback<Map<Long, IStorageModel>> responseCallback) {
            this.f16167a = new WeakReference<>(pkhVar);
            this.c = responseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            pkh pkhVar = this.f16167a.get();
            if (pkhVar == null) {
                LogUtil.h("BloodOxygenLineChartStorageHelper", "DataReadListener storageHelper is null");
                return;
            }
            ResponseCallback<Map<Long, IStorageModel>> responseCallback = this.c;
            if (responseCallback != null) {
                pkhVar.a(obj, responseCallback);
                this.c = null;
            } else {
                LogUtil.h("BloodOxygenLineChartStorageHelper", "DataReadListener mCallback is null");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.c("BloodOxygenLineChartStorageHelper", "DataReadListener onResultIntent");
        }
    }
}
