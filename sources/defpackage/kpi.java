package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kpi {

    /* renamed from: a, reason: collision with root package name */
    private static kpi f14520a = null;
    private static boolean e = false;
    private int ad;
    private int ah;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int o;
    private String u;
    private boolean v;
    private static final Object c = new Object();
    private static final int[] b = {DicDataTypeUtil.DataType.BED_TIME.value(), DicDataTypeUtil.DataType.RISING_TIME.value(), DicDataTypeUtil.DataType.DAILY_SLEEP_LATENCY.value()};
    private static final String[] d = {"bedTime", "risingTime", "latency"};
    private int t = 22103;
    private int r = DicDataTypeUtil.DataType.DAILY_SLEEP_LATENCY.value();
    private String m = "latency";
    private int p = 22101;
    private int y = 22104;
    private int s = 22102;
    private int q = 22105;
    private int w = 0;
    private long k = 0;
    private Map<String, Long> z = new HashMap(16);
    private int[] ac = {44102, 44103, 44101, 44107, 44203, 44201, 44202, 44208, 44106, 44105, 44108, 44001, 44002, 44003, 44104, 44005, 44206, 44109, 44110};
    private int[] ae = {44102, 44103, 44101, 44107, 44203, 44201, 44202, 44208, 44106, 44105, 44108, 44001, 44002, 44003, 44104, 44005, 44206, 44004, 44008, 44009, 44109, 44110};
    private String[] aa = {"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_dream_key", "core_sleep_wake_count_key", "core_sleep_score_key", "core_sleep_fall_key", "core_sleep_wake_up_key", "core_sleep_snore_freq_key", "core_sleep_deep_sleep_part_key", "core_sleep_total_sleep_time_key", "core_sleep_day_sleep_time_key", "sleep_deep_key", "sleep_shallow_key", "sleep_wake_key", "core_sleep_wake_key", "sleep_wake_count_key", "core_sleep_valid_data_key", "data_session_manual_sleep_bed_time_key", "sleep_device_category_key"};
    private String[] l = {"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_dream_key", "core_sleep_wake_count_key", "core_sleep_score_key", "core_sleep_fall_key", "core_sleep_wake_up_key", "core_sleep_snore_freq_key", "core_sleep_deep_sleep_part_key", "core_sleep_total_sleep_time_key", "core_sleep_day_sleep_time_key", "sleep_deep_key", "sleep_shallow_key", "sleep_wake_key", "core_sleep_wake_key", "sleep_wake_count_key", "core_sleep_valid_data_key", "common_sleep_duration_sleep_sum_key", "stat_sleep_start_time", "stat_sleep_end_time", "data_session_manual_sleep_bed_time_key", "sleep_device_category_key"};
    private int[] ab = {DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_WAKEUP_TIME.value()};
    private String[] x = {"data_session_manual_sleep_go_to_bed_time_key", "data_session_manual_sleep_get_up_time_key", "data_session_manual_sleep_sleep_time_key", "data_session_manual_sleep_wake_time_key"};
    private Context n = BaseApplication.getContext();

    private kpi() {
    }

    public static kpi a() {
        kpi kpiVar;
        synchronized (c) {
            if (f14520a == null) {
                f14520a = new kpi();
            }
            kpiVar = f14520a;
        }
        return kpiVar;
    }

    public void b(final long j, final int i, final int i2, final IBaseResponseCallback iBaseResponseCallback) {
        long j2 = j * 1000;
        final String valueOf = String.valueOf(DateFormatUtil.b(j2));
        final SparseArray<List<HiHealthData>> awg_ = fch.awg_(valueOf);
        if (e && awg_ != null && koq.c(awg_.get(22100))) {
            LogUtil.a("HiH_SleepDataModelInteractor", "queryDayDayData use cache");
            bPZ_(awg_, i2, i, j, iBaseResponseCallback);
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j2, ((j + ((i * i2) * 60)) * 1000) - 1);
        hiDataReadOption.setType(new int[]{22100});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kpi.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i3, Object obj, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i3, int i4) {
                if (obj instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (kpi.e) {
                        boolean unused = kpi.e = false;
                        long currentTimeMillis = System.currentTimeMillis();
                        boolean awi_ = fch.awi_(awg_, sparseArray);
                        LogUtil.a("HiH_SleepDataModelInteractor", "queryDayDayData diff time: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms");
                        if (!awi_) {
                            LogUtil.a("HiH_SleepDataModelInteractor", "queryDayDayData same cache");
                            return;
                        }
                        fch.awj_(valueOf, sparseArray);
                    }
                    kpi.this.bPZ_(sparseArray, i2, i, j, iBaseResponseCallback);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bPZ_(SparseArray<List<HiHealthData>> sparseArray, int i, int i2, long j, IBaseResponseCallback iBaseResponseCallback) {
        List<HiHealthData> list;
        boolean z;
        boolean z2;
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList();
        if (sparseArray == null || sparseArray.size() <= 0) {
            list = arrayList2;
            z = true;
        } else {
            list = sparseArray.get(22100);
            z = false;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "first core sleep detail point = ";
        objArr[1] = list == null ? 0 : list.toString();
        LogUtil.a("SleepDataModelInteractor", objArr);
        if (koq.c(list)) {
            Iterator<HiHealthData> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z2 = true;
                    break;
                }
                HiHealthData next = it.next();
                if (!(next instanceof HiHealthData)) {
                    return;
                }
                HiHealthData hiHealthData = next;
                if (hiHealthData.getType() != 22105 && hiHealthData.getType() != 22104 && hiHealthData.getType() != 22106 && hiHealthData.getType() != 22107) {
                    z2 = false;
                    break;
                }
            }
        } else {
            z = true;
            z2 = true;
        }
        if (!z) {
            this.t = 22103;
            this.p = 22101;
            this.y = 22104;
            c(list, false);
        }
        if (!z2 && !z) {
            Object[] objArr2 = new Object[2];
            objArr2[0] = "size = ";
            objArr2[1] = Integer.valueOf(list == null ? 0 : list.size());
            LogUtil.a("HiH_SleepDataModelInteractor", objArr2);
            if (koq.c(list)) {
                Collections.sort(list, new Comparator<HiHealthData>() { // from class: kpi.2
                    @Override // java.util.Comparator
                    /* renamed from: e, reason: merged with bridge method [inline-methods] */
                    public int compare(HiHealthData hiHealthData2, HiHealthData hiHealthData3) {
                        return Long.compare(hiHealthData2.getStartTime(), hiHealthData3.getStartTime());
                    }
                });
            }
            this.w = 0;
            ArrayList arrayList3 = new ArrayList(16);
            this.z.clear();
            for (int i3 = 0; i3 < i; i3++) {
                b(arrayList, list, j, new int[]{i3, 1}, arrayList3);
            }
            a(arrayList, arrayList3, iBaseResponseCallback);
            return;
        }
        b(j, i2, i, iBaseResponseCallback, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> bPY_(List<HiHealthData> list, SparseArray<Object> sparseArray, List<HiHealthData> list2, IBaseResponseCallback iBaseResponseCallback, List<SleepTotalData> list3) {
        if (koq.c(list)) {
            this.t = 22001;
            this.p = 22002;
            this.y = 22003;
            c(list, true);
            List<HiHealthData> list4 = (List) sparseArray.get(22000);
            if (!koq.b(list2)) {
                Iterator<HiHealthData> it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getType() != 22105) {
                            break;
                        }
                    } else {
                        list4.addAll(list2);
                        break;
                    }
                }
            }
            return list4;
        }
        if (!koq.c(list2)) {
            return list2;
        }
        for (HiHealthData hiHealthData : list2) {
            if (hiHealthData.getType() == 22106 || hiHealthData.getType() == 22107) {
                return list2;
            }
        }
        return list2;
    }

    private void b(final long j, final int i, final int i2, final IBaseResponseCallback iBaseResponseCallback, final List<HiHealthData> list) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j * 1000, ((((i * i2) * 60) + j) * 1000) - 1);
        hiDataReadOption.setType(new int[]{22000});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(this.n).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kpi.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i3, Object obj, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i3, int i4) {
                SparseArray sparseArray = (SparseArray) obj;
                ArrayList arrayList = new ArrayList(16);
                List list2 = null;
                if ((sparseArray == null || sparseArray.size() <= 0) && koq.b(list)) {
                    LogUtil.a("SleepDataModelInteractor", "all detail is null");
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(0, arrayList);
                        return;
                    }
                } else {
                    if (sparseArray != null && sparseArray.size() > 0) {
                        list2 = (List) sparseArray.get(22000);
                    }
                    list2 = kpi.this.bPY_(list2, sparseArray, list, iBaseResponseCallback, arrayList);
                }
                Object[] objArr = new Object[2];
                objArr[0] = "size = ";
                objArr[1] = Integer.valueOf(list2 == null ? 0 : list2.size());
                LogUtil.a("HiH_SleepDataModelInteractor", objArr);
                Object[] objArr2 = new Object[2];
                objArr2[0] = "final sleep detail point = ";
                objArr2[1] = list2 == null ? 0 : list2.toString();
                LogUtil.a("SleepDataModelInteractor", objArr2);
                if (!koq.b(list2)) {
                    kpi.this.w = 0;
                    ArrayList arrayList2 = new ArrayList(16);
                    kpi.this.z.clear();
                    for (int i5 = 0; i5 < i2; i5++) {
                        kpi.this.b(arrayList, (List<HiHealthData>) list2, j, new int[]{i5, i}, arrayList2);
                    }
                    kpi.this.a(arrayList, arrayList2, iBaseResponseCallback);
                    return;
                }
                iBaseResponseCallback.d(0, arrayList);
            }
        });
    }

    private void c(List<HiHealthData> list, boolean z) {
        if (koq.b(list)) {
            return;
        }
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            HiHealthData hiHealthData = list.get(size);
            if (hiHealthData.getType() == 22105) {
                size--;
            } else {
                int i = hiHealthData.getInt("trackdata_deviceType");
                String string = hiHealthData.getString("device_uniquecode");
                if (hiHealthData.getType() == 22106 || hiHealthData.getType() == 22107) {
                    this.ah = 5;
                    this.u = string;
                    return;
                } else {
                    if (i == 0) {
                        return;
                    }
                    this.u = string;
                    if (i == 32) {
                        this.ah = 3;
                    } else if (i == 506) {
                        this.ah = 4;
                    } else if (z) {
                        this.ah = 1;
                    } else {
                        this.ah = 2;
                    }
                }
            }
        }
        LogUtil.a("HiH_SleepDataModelInteractor", "getDeviceType mSleepDeviceType is ", Integer.valueOf(this.ah));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<SleepTotalData> list, List<HiHealthData> list2, long j, int[] iArr, List<Map<String, Long>> list3) {
        SleepTotalData sleepTotalData = new SleepTotalData();
        this.k = (j + (iArr[0] * iArr[1] * 60)) * 1000;
        if (list2 != null) {
            int size = list2.size();
            int i = this.w;
            if (size > i && list2.get(i).getStartTime() == this.k) {
                a(list2, sleepTotalData, list3);
            }
        }
        list.add(sleepTotalData);
    }

    private void a(List<HiHealthData> list, SleepTotalData sleepTotalData, List<Map<String, Long>> list2, HiHealthData hiHealthData) {
        if (this.z.get("core_sleep_start_time_key") == null) {
            this.z.put("core_sleep_start_time_key", Long.valueOf(hiHealthData.getStartTime()));
        }
        if (this.w + 1 < list.size()) {
            HiHealthData hiHealthData2 = list.get(this.w + 1);
            if (hiHealthData2.getType() != this.q) {
                this.z.put("core_sleep_end_time_key", Long.valueOf(hiHealthData.getEndTime()));
                list2.add(this.z);
                this.z = new HashMap(16);
            } else if (hiHealthData2.getStartTime() != hiHealthData.getEndTime()) {
                this.z.put("core_sleep_end_time_key", Long.valueOf(hiHealthData.getEndTime()));
                list2.add(this.z);
                this.z = new HashMap(16);
            } else {
                this.z.put("core_sleep_end_time_key", Long.valueOf(hiHealthData2.getEndTime()));
            }
        } else {
            LogUtil.a("HiH_SleepDataModelInteractor", "setFlagData this is a last minute");
            this.z.put("core_sleep_end_time_key", Long.valueOf(hiHealthData.getEndTime()));
        }
        if (this.w == list.size() - 1) {
            list2.add(this.z);
        }
        sleepTotalData.setType(FitnessSleepType.HW_FITNESS_NOON);
        sleepTotalData.setNoonSleepTime(1);
    }

    private void a(List<HiHealthData> list, SleepTotalData sleepTotalData, List<Map<String, Long>> list2) {
        HiHealthData hiHealthData = list.get(this.w);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(hiHealthData.getStartTime());
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        if ((i == 20 && i2 == 0) || (i == 19 && i2 == 59)) {
            HashMap hashMap = new HashMap();
            hashMap.put("IS_SUSPICIOUS", true);
            sleepTotalData.setUiDataMap(hashMap);
        }
        int i3 = this.ah;
        if (i3 != 0) {
            sleepTotalData.setDeviceType(i3);
        }
        String str = this.u;
        if (str != null) {
            sleepTotalData.setDeviceId(str);
        }
        if (hiHealthData.getType() == 22106) {
            sleepTotalData.setType(8);
            sleepTotalData.setManualBedTime(1);
        } else if (hiHealthData.getType() == 22107) {
            sleepTotalData.setType(9);
            sleepTotalData.setManualOnTime(1);
        } else if (this.t == hiHealthData.getType()) {
            sleepTotalData.setType(7);
            sleepTotalData.setDeepSleepTime(1);
        } else if (this.p == hiHealthData.getType()) {
            sleepTotalData.setType(6);
            sleepTotalData.setShallowSleepTime(1);
        } else if (this.y == hiHealthData.getType()) {
            sleepTotalData.setType(FitnessSleepType.HW_FITNESS_WAKE);
            sleepTotalData.setWakeupDuration(1);
        } else if (this.s == hiHealthData.getType()) {
            sleepTotalData.setType(FitnessSleepType.HW_FITNESS_DREAM);
            sleepTotalData.setSlumberSleepTime(1);
        } else if (this.q == hiHealthData.getType()) {
            a(list, sleepTotalData, list2, hiHealthData);
        } else {
            LogUtil.a("HiH_SleepDataModelInteractor", "tepSleepData.getType()  = ", Integer.valueOf(hiHealthData.getType()));
        }
        this.w++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<SleepTotalData> list, List<Map<String, Long>> list2, IBaseResponseCallback iBaseResponseCallback) {
        StringBuilder sb = new StringBuilder(list2.size());
        StringBuilder sb2 = new StringBuilder(list2.size());
        for (int i = 0; i < list2.size(); i++) {
            Map<String, Long> map = list2.get(i);
            Long l = map.get("core_sleep_start_time_key");
            if (l == null) {
                LogUtil.b("HiH_SleepDataModelInteractor", "setNoonSleepData start is null, noonMapList ", Integer.valueOf(list2.size()), " i ", Integer.valueOf(i));
            } else {
                Long l2 = map.get("core_sleep_end_time_key");
                if (l2 == null) {
                    LogUtil.b("HiH_SleepDataModelInteractor", "setNoonSleepData end is null, noonMapList ", Integer.valueOf(list2.size()), " i ", Integer.valueOf(i));
                } else {
                    long longValue = l.longValue();
                    long longValue2 = l2.longValue();
                    new Date(longValue).setTime(longValue2);
                    if (i == list2.size() - 1) {
                        sb.append(longValue);
                        sb2.append(longValue2);
                    } else {
                        sb.append(longValue);
                        sb.append(",");
                        sb2.append(longValue2);
                        sb2.append(",");
                    }
                }
            }
        }
        if (list2.size() != 0 && list.size() != 0) {
            SleepTotalData sleepTotalData = list.get(0);
            sleepTotalData.setNoonStartTime(sb.toString());
            sleepTotalData.setNoonEndTime(sb2.toString());
            sleepTotalData.setNoonMapList(list2);
            list.remove(0);
            list.add(0, sleepTotalData);
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, list);
        }
    }

    public void c(long j, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.a("HiH_SleepDataModelInteractor", "getCoreSleepDetail callback is null");
            return;
        }
        if (i2 == 1 && i3 <= 1440) {
            b(j, i2, i3, iBaseResponseCallback);
            return;
        }
        if (i2 >= 1440) {
            d(j, i, i2, i3, iBaseResponseCallback);
        } else if (i3 >= 1440) {
            b(j, i2, i3, iBaseResponseCallback);
        } else {
            LogUtil.a("HiH_SleepDataModelInteractor", "getCoreSleepDetail callback ErrorConstants.ERR_NONE ");
            iBaseResponseCallback.d(100001, null);
        }
    }

    /* JADX WARN: Type inference failed for: r4v10, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.time.LocalDateTime] */
    private void d(long j, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        if (i == 0) {
            iBaseResponseCallback.d(100001, null);
            return;
        }
        long j2 = j * 1000;
        long j3 = ((j + ((i2 * i3) * 60)) * 1000) - 1;
        if (i3 == 365) {
            Date date = new Date();
            date.setTime(j2);
            j3 = Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMonths(12L).atZone(ZoneId.systemDefault()).toInstant()).getTime() - 1;
        } else if (i == 5 && i3 == 12) {
            j3 = ((j + (i2 * 60)) * 1000) - 1;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j2);
        hiAggregateOption.setEndTime(j3);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(this.l);
        hiAggregateOption.setType(this.ae);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setGroupUnitType(i);
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(this.n).aggregateHiHealthData(hiAggregateOption, new d(iBaseResponseCallback, new int[]{i, i2, i3}, j, j3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bQb_(List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback, int[] iArr, long j, SparseArray<List<HiHealthData>> sparseArray) {
        List<HiHealthData> list2;
        if (iBaseResponseCallback == null) {
            LogUtil.h("HiH_SleepDataModelInteractor", "setSummary callback is null");
            return;
        }
        if (list == null || list.isEmpty()) {
            iBaseResponseCallback.d(100001, null);
            return;
        }
        LogUtil.a("HiH_SleepDataModelInteractor", "sumdata.size() = ", Integer.valueOf(list.size()), ", sumdata = ", list.toString());
        ArrayList arrayList = new ArrayList(16);
        List<HiHealthData> arrayList2 = new ArrayList<>(16);
        if (sparseArray != null) {
            arrayList2 = sparseArray.get(0);
            list2 = g(sparseArray.get(1));
        } else {
            list2 = arrayList;
        }
        ArrayList arrayList3 = new ArrayList(16);
        if (koq.c(arrayList2)) {
            arrayList3.addAll(e(arrayList2));
            LogUtil.a("HiH_SleepDataModelInteractor", "filterManualData.size() = ", Integer.valueOf(arrayList3.size()), ", filterManualData = ", arrayList3.toString());
        } else {
            LogUtil.a("HiH_SleepDataModelInteractor", "manualData is null");
        }
        ArrayList arrayList4 = new ArrayList(16);
        int i = iArr[2];
        int i2 = iArr[1];
        if (i == 1) {
            e(list, arrayList4, arrayList3);
        } else {
            int[] iArr2 = {i, i2};
            if (i == 14) {
                a(iArr[0], j, list, arrayList4, iArr2, arrayList3, list2);
            } else {
                e(iArr[0], j, list, arrayList4, iArr2, arrayList3);
            }
        }
        LogUtil.a("HiH_SleepDataModelInteractor", "sleepTotalData.size() = ", Integer.valueOf(arrayList4.size()));
        iBaseResponseCallback.d(0, arrayList4);
    }

    private List<HiHealthData> g(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h("HiH_SleepDataModelInteractor", "getFilterData data is null");
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getInt("trackdata_deviceType") == 32) {
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.a("HiH_SleepDataModelInteractor", "filterPhoneData.size() = ", Integer.valueOf(arrayList.size()), ", filterPhoneData = ", arrayList.toString());
        return arrayList;
    }

    private List<HiHealthData> e(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getInt("device_uniquecode") == -1) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private void e(int i, long j, List<HiHealthData> list, List<SleepTotalData> list2, int[] iArr, List<HiHealthData> list3) {
        boolean z;
        int i2;
        d(list3);
        HashMap hashMap = new HashMap(3);
        hashMap.put("data_session_manual_sleep_go_to_bed_time_key", a(list3, "data_session_manual_sleep_go_to_bed_time_key"));
        hashMap.put("data_session_manual_sleep_get_up_time_key", a(list3, "data_session_manual_sleep_get_up_time_key"));
        hashMap.put("data_session_manual_sleep_sleep_time_key", a(list3, "data_session_manual_sleep_sleep_time_key"));
        int i3 = iArr[0];
        int i4 = 1;
        boolean z2 = i3 == 2 || i3 == 8;
        int i5 = 0;
        for (char c2 = 0; i5 < iArr[c2]; c2 = 0) {
            SleepTotalData sleepTotalData = new SleepTotalData();
            for (HiHealthData hiHealthData : list) {
                long startTime = hiHealthData.getStartTime();
                if (z2) {
                    i2 = jdl.e(j * 1000, startTime) - i4;
                    z = z2;
                } else {
                    z = z2;
                    i2 = (int) ((((startTime / 1000) - j) / 60) / iArr[i4]);
                }
                if (i == 5) {
                    int t = jec.t(jec.c(j));
                    int t2 = jec.t(jec.c(startTime / 1000));
                    if (t2 < t) {
                        t2 += 12;
                    }
                    i2 = t2 - t;
                }
                if (i2 == i5) {
                    b(sleepTotalData, hiHealthData, iArr);
                }
                i4 = 1;
                if (this.v) {
                    c(j, iArr[1], hashMap, i5, sleepTotalData);
                    sleepTotalData.setIsManual(true);
                }
                z2 = z;
            }
            sleepTotalData.setAvgManualBedTime(this.f);
            sleepTotalData.setAvgManualRisingTime(this.j);
            sleepTotalData.setAvgManualSleepTime(this.g);
            sleepTotalData.setAvgManualWakeTime(this.i);
            sleepTotalData.setDailyManualBedScore(this.o);
            sleepTotalData.setDailyManualRisingScore(this.ad);
            sleepTotalData.setAvgManualLatencyTime(this.h);
            list2.add(sleepTotalData);
            i5++;
        }
    }

    private void c(long j, int i, Map<String, List<HiHealthData>> map, int i2, SleepTotalData sleepTotalData) {
        for (Map.Entry<String, List<HiHealthData>> entry : map.entrySet()) {
            for (HiHealthData hiHealthData : entry.getValue()) {
                long j2 = i;
                boolean z = ((int) ((((hiHealthData.getStartTime() / 1000) - (j - 14400)) / 60) / j2)) == i2;
                boolean z2 = ((int) ((((hiHealthData.getStartTime() / 1000) - j) / 60) / j2)) == i2;
                if (entry.getKey() == "data_session_manual_sleep_go_to_bed_time_key" && z) {
                    sleepTotalData.setFallTime(nrq.e(hiHealthData.getStartTime(), hiHealthData.getTimeZone(), 1));
                } else if (entry.getKey() == "data_session_manual_sleep_sleep_time_key" && z) {
                    sleepTotalData.setManualFallTime(nrq.e(hiHealthData.getStartTime(), hiHealthData.getTimeZone(), 1));
                } else if (entry.getKey() == "data_session_manual_sleep_get_up_time_key" && z2) {
                    sleepTotalData.setWakeUpTime(nrq.e(hiHealthData.getStartTime(), hiHealthData.getTimeZone(), 1));
                } else {
                    LogUtil.a("HiH_SleepDataModelInteractor", "other keys");
                }
            }
        }
    }

    private SleepTotalData e(HiHealthData hiHealthData) {
        double d2 = hiHealthData.getDouble("core_sleep_fall_key");
        double d3 = hiHealthData.getDouble("core_sleep_wake_up_key");
        String timeZone = hiHealthData.getTimeZone();
        SleepTotalData sleepTotalData = new SleepTotalData();
        sleepTotalData.setDeepSleepTime(hiHealthData.getInt("core_sleep_deep_key"));
        sleepTotalData.setShallowSleepTime(hiHealthData.getInt("core_sleep_shallow_key"));
        sleepTotalData.setWakeupDuration(hiHealthData.getInt("core_sleep_wake_key"));
        sleepTotalData.setSlumberSleepTime(hiHealthData.getInt("core_sleep_wake_dream_key"));
        sleepTotalData.setNoonSleepTime(hiHealthData.getInt("core_sleep_day_sleep_time_key"));
        sleepTotalData.setWakeupTimes(hiHealthData.getInt("core_sleep_wake_count_key"));
        sleepTotalData.setManualBedTime(hiHealthData.getInt("data_session_manual_sleep_bed_time_key"));
        sleepTotalData.setManualOnTime(hiHealthData.getInt("core_sleep_total_sleep_time_key"));
        sleepTotalData.setScore(hiHealthData.getInt("core_sleep_score_key"));
        LogUtil.a("SleepDataModelInteractor", "sum set wakeuptimes ", Integer.valueOf(hiHealthData.getInt("core_sleep_wake_count_key")), " setScore ", Integer.valueOf(hiHealthData.getInt("core_sleep_score_key")));
        sleepTotalData.setFallTime(nrq.e(d2, timeZone, 1));
        sleepTotalData.setCoreSleepFallTime((long) d2);
        if (d2 == 0.0d) {
            sleepTotalData.setFallTime(0);
            sleepTotalData.setCoreSleepFallTime(0L);
        }
        sleepTotalData.setCoreSleepWakeupTime((long) d3);
        sleepTotalData.setWakeUpTime(nrq.e(d3, timeZone, 1));
        if (d3 == 0.0d) {
            sleepTotalData.setCoreSleepWakeupTime(0L);
            sleepTotalData.setWakeUpTime(0);
        }
        sleepTotalData.setSnoreFreq(hiHealthData.getInt("core_sleep_snore_freq_key"));
        sleepTotalData.setDeepSleepPart(hiHealthData.getInt("core_sleep_deep_sleep_part_key"));
        sleepTotalData.setTotalSleepTime(hiHealthData.getInt("core_sleep_total_sleep_time_key"));
        sleepTotalData.setType(31);
        sleepTotalData.setValidData(hiHealthData.getDouble("core_sleep_valid_data_key"));
        return sleepTotalData;
    }

    private void e(List<HiHealthData> list, List<SleepTotalData> list2, List<HiHealthData> list3) {
        SleepTotalData sleepTotalData = new SleepTotalData();
        HiHealthData hiHealthData = list.get(0);
        int i = hiHealthData.getInt("core_sleep_deep_key");
        int i2 = hiHealthData.getInt("core_sleep_shallow_key");
        int i3 = hiHealthData.getInt("core_sleep_wake_key");
        int i4 = hiHealthData.getInt("core_sleep_wake_count_key");
        int i5 = hiHealthData.getInt("core_sleep_wake_dream_key");
        int i6 = hiHealthData.getInt("core_sleep_day_sleep_time_key");
        int i7 = hiHealthData.getInt("core_sleep_score_key");
        int i8 = hiHealthData.getInt("core_sleep_total_sleep_time_key");
        int i9 = hiHealthData.getInt("common_sleep_duration_sleep_sum_key");
        int i10 = hiHealthData.getInt("data_session_manual_sleep_bed_time_key");
        LogUtil.a("SleepDataModelInteractor", "deepSum is", Integer.valueOf(i), "shallowSum is", Integer.valueOf(i2), "coreWakeSum is", Integer.valueOf(i3), "wakeCount is", Integer.valueOf(i4), "dreamSum is", Integer.valueOf(i5), "noonSum is", Integer.valueOf(i6), "score is", Integer.valueOf(i7), "sleepTotalTime is", Integer.valueOf(i8), " manualBedTime is ", Integer.valueOf(i10));
        boolean z = i2 > 0 || i > 0 || i5 > 0;
        boolean z2 = i10 > 0;
        if (z || i6 > 0) {
            sleepTotalData = e(hiHealthData);
        } else if (i9 != 0) {
            a(sleepTotalData, hiHealthData);
        } else if (z2) {
            sleepTotalData = e(hiHealthData);
        }
        if (!z && i6 > 0 && i9 != 0) {
            sleepTotalData = new SleepTotalData();
            a(sleepTotalData, hiHealthData);
        }
        d(list3);
        int b2 = b(list3);
        if (b2 >= 0) {
            sleepTotalData.setManualFallTime(b2);
        } else {
            sleepTotalData.setManualFallTime(-1);
        }
        sleepTotalData.setAvgManualWakeTime(this.i);
        if (koq.b(list3)) {
            sleepTotalData.setTypeNum(-1);
        } else {
            sleepTotalData.setTypeNum(0);
        }
        list2.add(sleepTotalData);
    }

    private void a(SleepTotalData sleepTotalData, HiHealthData hiHealthData) {
        int i = hiHealthData.getInt("sleep_deep_key");
        int i2 = hiHealthData.getInt("sleep_shallow_key");
        int i3 = hiHealthData.getInt("sleep_wake_key");
        int i4 = hiHealthData.getInt("sleep_wake_count_key");
        int i5 = hiHealthData.getInt("common_sleep_duration_sleep_sum_key");
        sleepTotalData.setDeepSleepTime(i / 60);
        sleepTotalData.setShallowSleepTime(i2 / 60);
        sleepTotalData.setWakeupDuration(i3 / 60);
        sleepTotalData.setWakeupTimes(i4);
        sleepTotalData.setTotalSleepTime(i5 / 60);
        sleepTotalData.setType(30);
        String timeZone = hiHealthData.getTimeZone();
        sleepTotalData.setCommonFallTime(nrq.e(hiHealthData.getDouble("stat_sleep_start_time"), timeZone, 1));
        sleepTotalData.setCommonWakeUpTime(nrq.e(hiHealthData.getDouble("stat_sleep_end_time"), timeZone, 1));
        sleepTotalData.setSleepDayTime(hiHealthData.getStartTime());
    }

    private void a(int i, long j, List<HiHealthData> list, List<SleepTotalData> list2, int[] iArr, List<HiHealthData> list3, List<HiHealthData> list4) {
        long j2 = j + k.b.l;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        b(list, arrayList, arrayList2, j2, (String) null);
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        b(list3, arrayList3, arrayList4, j2, "data_session_manual_sleep_get_up_time_key");
        ArrayList arrayList5 = new ArrayList(7);
        ArrayList arrayList6 = new ArrayList(7);
        b(list4, arrayList5, arrayList6, j2, "risingTime");
        List<List<HiHealthData>> b2 = b(arrayList, arrayList3, arrayList5);
        List<List<HiHealthData>> b3 = b(arrayList2, arrayList4, arrayList6);
        list2.addAll(e(i, j, iArr, b2));
        list2.addAll(e(i, j2, iArr, b3));
    }

    private List<List<HiHealthData>> b(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(list);
        arrayList.add(list2);
        arrayList.add(list3);
        return arrayList;
    }

    private List<SleepTotalData> e(int i, long j, int[] iArr, List<List<HiHealthData>> list) {
        LogUtil.a("HiH_SleepDataModelInteractor", "sleepDatas:", list.toString());
        ArrayList arrayList = new ArrayList();
        int i2 = iArr[0];
        if (i2 == 14) {
            i2 /= 2;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            SleepTotalData sleepTotalData = new SleepTotalData();
            for (int i4 = 0; i4 < list.size(); i4++) {
                if (!koq.b(list.get(i4))) {
                    Iterator<HiHealthData> it = list.get(i4).iterator();
                    while (it.hasNext()) {
                        if (b(sleepTotalData, it.next(), j, new int[]{iArr[0], iArr[1], i3, i})) {
                            break;
                        }
                    }
                }
            }
            LogUtil.a("SleepDataModelInteractor", sleepTotalData);
            arrayList.add(sleepTotalData);
        }
        return arrayList;
    }

    private void b(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3, long j, String str) {
        long j2;
        if (koq.b(list)) {
            LogUtil.h("HiH_SleepDataModelInteractor", "sumDataList is empty in matchDataList.");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (str == null) {
                j2 = hiHealthData.getStartTime() / 1000;
            } else {
                j2 = hiHealthData.getLong(str) / 1000;
            }
            if (j2 < j) {
                list2.add(hiHealthData);
            } else {
                list3.add(hiHealthData);
            }
        }
    }

    private void b(SleepTotalData sleepTotalData, HiHealthData hiHealthData, int[] iArr) {
        String timeZone = hiHealthData.getTimeZone();
        this.v = false;
        int round = Math.round(hiHealthData.getFloat("core_sleep_shallow_key"));
        int round2 = Math.round(hiHealthData.getFloat("core_sleep_deep_key"));
        int round3 = Math.round(hiHealthData.getFloat("core_sleep_wake_dream_key"));
        int round4 = Math.round(hiHealthData.getFloat("core_sleep_day_sleep_time_key"));
        int round5 = Math.round(hiHealthData.getFloat("core_sleep_wake_key"));
        int round6 = Math.round(hiHealthData.getFloat("data_session_manual_sleep_bed_time_key"));
        int i = hiHealthData.getInt("sleep_deep_key") / 60;
        int i2 = hiHealthData.getInt("sleep_shallow_key") / 60;
        int round7 = Math.round(hiHealthData.getFloat("sleep_device_category_key"));
        boolean z = round > 0 || round2 > 0 || round3 > 0;
        boolean z2 = round6 > 0;
        sleepTotalData.setDeivceCategory(round7);
        if (z || round4 > 0) {
            b(sleepTotalData, hiHealthData, timeZone, round, round2, round3, round4, round5, round7, iArr, true);
            return;
        }
        if (i + i2 > 0) {
            e(sleepTotalData, hiHealthData, timeZone);
        } else if (z2) {
            this.v = true;
            b(sleepTotalData, hiHealthData, timeZone, round, round2, round3, round4, round5, round7, iArr, false);
        } else {
            e(sleepTotalData, hiHealthData, timeZone);
        }
    }

    private void e(SleepTotalData sleepTotalData, HiHealthData hiHealthData, String str) {
        sleepTotalData.setIsCommon(true);
        sleepTotalData.setDeepSleepTime(hiHealthData.getInt("sleep_deep_key") / 60);
        sleepTotalData.setShallowSleepTime(hiHealthData.getInt("sleep_shallow_key") / 60);
        sleepTotalData.setTotalSleepTime(sleepTotalData.getDeepSleepTime() + sleepTotalData.getShallowSleepTime());
        sleepTotalData.setWakeupDuration(hiHealthData.getInt("sleep_wake_key") / 60);
        sleepTotalData.setWakeupTimes(hiHealthData.getInt("sleep_wake_count_key"));
        sleepTotalData.setType(30);
        sleepTotalData.setCommonFallTime(nrq.e(hiHealthData.getDouble("stat_sleep_start_time"), str, 1));
        sleepTotalData.setCommonWakeUpTime(nrq.e(hiHealthData.getDouble("stat_sleep_end_time"), str, 1));
        sleepTotalData.setSleepDayTime(hiHealthData.getStartTime());
    }

    private void b(SleepTotalData sleepTotalData, HiHealthData hiHealthData, String str, int i, int i2, int i3, int i4, int i5, int i6, int[] iArr, boolean z) {
        int i7 = iArr[0];
        if (i7 == 2 || i7 == 8) {
            b(sleepTotalData, hiHealthData, str, i, i2, i3, i4, i5, true);
            return;
        }
        if (nrq.b(i6)) {
            sleepTotalData.setIsPhone(true);
            sleepTotalData.setNoonSleepTime(i4);
            sleepTotalData.setType(31);
            sleepTotalData.setSleepDayTime(hiHealthData.getStartTime());
            sleepTotalData.setPhoneSuggestTotalSleepTime(Math.round(hiHealthData.getFloat("core_sleep_total_sleep_time_key")));
            long d2 = nrq.d(hiHealthData.getStartTime(), str, -1);
            sleepTotalData.setPhoneSuggestFallTime(nrq.c(hiHealthData.getDouble("core_sleep_fall_key"), d2));
            sleepTotalData.setPhoneSuggestWakeTime(nrq.c(hiHealthData.getDouble("core_sleep_wake_up_key"), d2));
            sleepTotalData.setTotalSleepTime(Math.round(hiHealthData.getFloat("core_sleep_total_sleep_time_key")));
            sleepTotalData.setWakeupDuration(hiHealthData.getInt("core_sleep_wake_key"));
            sleepTotalData.setDeepSleepTime(hiHealthData.getInt("core_sleep_deep_key"));
            sleepTotalData.setShallowSleepTime(hiHealthData.getInt("core_sleep_shallow_key"));
            return;
        }
        if (!z || i6 == Integer.parseInt("001", 16)) {
            sleepTotalData.setIsManual(true);
            sleepTotalData.setManualBedTime(Math.round(hiHealthData.getFloat("data_session_manual_sleep_bed_time_key")));
            sleepTotalData.setManualOnTime(Math.round(hiHealthData.getFloat("core_sleep_total_sleep_time_key")));
            sleepTotalData.setTotalSleepTime(Math.round(hiHealthData.getFloat("core_sleep_total_sleep_time_key")));
            sleepTotalData.setType(31);
            sleepTotalData.setSleepDayTime(hiHealthData.getStartTime());
            return;
        }
        sleepTotalData.setIsCoreSleep(true);
        b(sleepTotalData, hiHealthData, str, i, i2, i3, i4, i5, false);
    }

    private void b(SleepTotalData sleepTotalData, HiHealthData hiHealthData, String str, int i, int i2, int i3, int i4, int i5, boolean z) {
        sleepTotalData.setShallowSleepTime(i);
        sleepTotalData.setDeepSleepTime(i2);
        sleepTotalData.setSlumberSleepTime(i3);
        sleepTotalData.setWakeupDuration(i5);
        sleepTotalData.setWakeupTimes(hiHealthData.getInt("core_sleep_wake_count_key"));
        sleepTotalData.setScore(hiHealthData.getInt("core_sleep_score_key"));
        sleepTotalData.setSnoreFreq(hiHealthData.getInt("core_sleep_snore_freq_key"));
        sleepTotalData.setDeepSleepPart(hiHealthData.getInt("core_sleep_deep_sleep_part_key"));
        if (z) {
            sleepTotalData.setManualBedTime(Math.round(hiHealthData.getFloat("data_session_manual_sleep_bed_time_key")));
            sleepTotalData.setManualOnTime(Math.round(hiHealthData.getFloat("core_sleep_total_sleep_time_key")));
        }
        double d2 = hiHealthData.getDouble("core_sleep_fall_key");
        sleepTotalData.setFallTime(nrq.e(d2, str, 1));
        sleepTotalData.setCoreSleepFallTime((long) d2);
        if (d2 == 0.0d) {
            sleepTotalData.setFallTime(0);
            sleepTotalData.setCoreSleepFallTime(0L);
        }
        double d3 = hiHealthData.getDouble("core_sleep_wake_up_key");
        sleepTotalData.setCoreSleepWakeupTime((long) d3);
        sleepTotalData.setWakeUpTime(nrq.e(d3, str, 1));
        if (d3 == 0.0d) {
            sleepTotalData.setCoreSleepWakeupTime(0L);
            sleepTotalData.setWakeUpTime(0);
        }
        sleepTotalData.setTotalSleepTime(Math.round(hiHealthData.getFloat("core_sleep_total_sleep_time_key")));
        sleepTotalData.setNoonSleepTime(i4);
        sleepTotalData.setType(31);
        sleepTotalData.setSleepDayTime(hiHealthData.getStartTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bQa_(SparseArray<List<HiHealthData>> sparseArray, List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback, int i, int i2, long j) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HiH_SleepDataModelInteractor", "setMonthYearSummary callback is null");
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.a("HiH_SleepDataModelInteractor", "setMonthYearSummary data = null ");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        List<HiHealthData> arrayList = new ArrayList<>(16);
        List<HiHealthData> arrayList2 = new ArrayList<>(16);
        if (sparseArray != null) {
            arrayList = a(sparseArray.get(0));
            arrayList2 = g(sparseArray.get(1));
        }
        LogUtil.a("HiH_SleepDataModelInteractor", "data.size() = ", Integer.valueOf(list.size()));
        ArrayList arrayList3 = new ArrayList(16);
        arrayList3.addAll(e(3, j, new int[]{i, i2}, b(list, arrayList, arrayList2)));
        LogUtil.a("HiH_SleepDataModelInteractor", "sleepTotalData.size() = ", Integer.valueOf(arrayList3.size()));
        iBaseResponseCallback.d(0, arrayList3);
    }

    private boolean b(SleepTotalData sleepTotalData, HiHealthData hiHealthData, long j, int[] iArr) {
        if (e(sleepTotalData, hiHealthData, j, iArr) != iArr[2]) {
            return false;
        }
        if (sleepTotalData.isDataValid()) {
            b(sleepTotalData, hiHealthData);
            return true;
        }
        b(sleepTotalData, hiHealthData, iArr);
        return true;
    }

    private int e(SleepTotalData sleepTotalData, HiHealthData hiHealthData, long j, int[] iArr) {
        long startTime;
        if (sleepTotalData.isManual()) {
            startTime = nrq.e(hiHealthData.getLong("data_session_manual_sleep_get_up_time_key"), hiHealthData.getTimeZone());
        } else if (sleepTotalData.isPhone()) {
            startTime = c(sleepTotalData, hiHealthData);
        } else {
            startTime = hiHealthData.getStartTime();
        }
        if (iArr[3] == 3) {
            return (int) ((((startTime / 1000) - j) / 60) / iArr[1]);
        }
        int t = jec.t(jec.c(j));
        int t2 = jec.t(jec.c(startTime / 1000));
        if (t2 < t) {
            t2 += 12;
        }
        return t2 - t;
    }

    private long c(SleepTotalData sleepTotalData, HiHealthData hiHealthData) {
        if (sleepTotalData.getPhoneSuggestWakeTime() == 1440) {
            return nrq.e(hiHealthData.getLong("bedTime"), hiHealthData.getTimeZone());
        }
        return nrq.e(hiHealthData.getLong("risingTime"), hiHealthData.getTimeZone());
    }

    private void b(SleepTotalData sleepTotalData, HiHealthData hiHealthData) {
        if (sleepTotalData.isPhone() & hiHealthData.containsKey("bedTime")) {
            long c2 = c(sleepTotalData, hiHealthData);
            sleepTotalData.setPhoneStartTime(nrq.c(sleepTotalData.getPhoneStartTime(), hiHealthData.getLong("bedTime"), 0));
            sleepTotalData.setPhoneGoBedTime(nrq.d(sleepTotalData.getPhoneGoBedTime(), nrq.c(hiHealthData.getLong("bedTime"), c2, hiHealthData.getTimeZone()), 0));
            sleepTotalData.setPhoneGetupTime(nrq.d(sleepTotalData.getPhoneGetupTime(), nrq.c(hiHealthData.getLong("risingTime"), c2, hiHealthData.getTimeZone()), 1));
            b(sleepTotalData);
            sleepTotalData.setPhoneSleepLatency(e(sleepTotalData, hiHealthData, false));
            return;
        }
        if (sleepTotalData.isManual() && hiHealthData.containsKey("data_session_manual_sleep_go_to_bed_time_key")) {
            sleepTotalData.setManualGoBedTime(nrq.c(sleepTotalData.getManualGoBedTime(), hiHealthData.getLong("data_session_manual_sleep_go_to_bed_time_key"), 0));
            sleepTotalData.setManualGetUpTime(nrq.c(sleepTotalData.getManualGetUpTime(), hiHealthData.getLong("data_session_manual_sleep_get_up_time_key"), 1));
            sleepTotalData.setManualFallTime(nrq.d(sleepTotalData.getManualFallTime(), nrq.e(hiHealthData.getLong("data_session_manual_sleep_sleep_time_key"), hiHealthData.getTimeZone(), 1), 0));
            sleepTotalData.setManualWakeUpTime(nrq.d(sleepTotalData.getManualWakeUpTime(), nrq.e(hiHealthData.getLong("data_session_manual_sleep_wake_time_key"), hiHealthData.getTimeZone(), 1), 1));
            sleepTotalData.setManualSleepLatency(e(sleepTotalData, hiHealthData, true));
        }
    }

    private void b(SleepTotalData sleepTotalData) {
        if (sleepTotalData.getPhoneSuggestFallTime() == 0) {
            sleepTotalData.setPhoneGoBedTime(0);
        }
        if (sleepTotalData.getPhoneSuggestWakeTime() == 1440) {
            sleepTotalData.setPhoneGetupTime(ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL);
        }
    }

    private int e(SleepTotalData sleepTotalData, HiHealthData hiHealthData, boolean z) {
        String timeZone = TextUtils.isEmpty(hiHealthData.getTimeZone()) ? "+8:00" : hiHealthData.getTimeZone();
        sleepTotalData.setTimeZone(timeZone);
        if (z) {
            return sleepTotalData.getManualFallTime() - nrq.e(sleepTotalData.getManualGoBedTime(), timeZone, 1);
        }
        return sleepTotalData.getPhoneSuggestFallTime() - sleepTotalData.getPhoneGoBedTime();
    }

    static class d implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private IBaseResponseCallback f14525a;
        private final int[] b;
        private final WeakReference<kpi> c;
        private final long d;
        private final long e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        private d(kpi kpiVar, IBaseResponseCallback iBaseResponseCallback, int[] iArr, long j, long j2) {
            this.c = new WeakReference<>(kpiVar);
            this.f14525a = iBaseResponseCallback;
            this.b = iArr;
            this.d = j;
            this.e = j2;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            WeakReference<kpi> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.h("HiH_SleepDataModelInteractor", "SummaryHiAggregateListener mReference is null");
                return;
            }
            kpi kpiVar = weakReference.get();
            if (kpiVar == null) {
                LogUtil.h("HiH_SleepDataModelInteractor", "SummaryHiAggregateListener interactor is null");
                return;
            }
            int[] iArr = this.b;
            int i3 = iArr[2];
            if (i3 == 365) {
                kpiVar.a(kpiVar, list, this.f14525a, this.d, this.e, i3, iArr[1]);
            } else {
                kpiVar.e(kpiVar, list, this.f14525a, iArr, this.d, this.e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final kpi kpiVar, final List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback, final int[] iArr, final long j, long j2) {
        HiDataFilter.DataFilterExpression dataFilterExpression;
        HiDataFilter.DataFilterExpression dataFilterExpression2;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        long j3 = j * 1000;
        hiAggregateOption.setStartTime(j3 - 259200000);
        hiAggregateOption.setEndTime(259200000 + j2);
        hiAggregateOption.setType(this.ab);
        hiAggregateOption.setConstantsKey(this.x);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        if (iArr != null && iArr.length >= 3 && iArr[2] > 1) {
            dataFilterExpression2 = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.BIGGER_EQUAL, j3);
            dataFilterExpression = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.LESS_EQUAL, j2);
        } else {
            long e2 = jec.e(new Date(j3));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(j3));
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 20, 0, 0);
            long l = jec.l(calendar.getTime());
            HiDataFilter.DataFilterExpression dataFilterExpression3 = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.BIGGER_EQUAL, e2 * 1000);
            dataFilterExpression = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.LESS_EQUAL, l * 1000);
            dataFilterExpression2 = dataFilterExpression3;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataFilterExpression2);
        arrayList.add(dataFilterExpression);
        HiDataAggregateProOption c2 = HiDataAggregateProOption.builder().c(hiAggregateOption).b(HiJsonUtil.e(new HiDataFilter(arrayList, Collections.singletonList(0)))).c((String) null).c();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(c2);
        arrayList2.add(d(j, j2));
        HiHealthNativeApi.a(this.n).aggregateHiHealthDataProEx(arrayList2, new HiAggregateListenerEx() { // from class: kpi.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                kpiVar.bQb_(list, iBaseResponseCallback, iArr, j, sparseArray);
            }
        });
    }

    private HiDataAggregateProOption d(long j, long j2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j * 1000);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setType(b);
        hiAggregateOption.setConstantsKey(d);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        return new HiDataAggregateProOption.Builder().c(hiAggregateOption).c((String) null).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final kpi kpiVar, final List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback, final long j, long j2, final int i, final int i2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setType(this.ab);
        hiAggregateOption.setConstantsKey(this.x);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        HiDataFilter.DataFilterExpression dataFilterExpression = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.BIGGER_EQUAL, 1000 * j);
        HiDataFilter.DataFilterExpression dataFilterExpression2 = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.LESS_EQUAL, j2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataFilterExpression);
        arrayList.add(dataFilterExpression2);
        HiDataAggregateProOption c2 = HiDataAggregateProOption.builder().c(hiAggregateOption).b(HiJsonUtil.e(new HiDataFilter(arrayList, Collections.singletonList(0)))).c((String) null).c();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(c2);
        arrayList2.add(d(j, j2));
        HiHealthNativeApi.a(this.n).aggregateHiHealthDataProEx(arrayList2, new HiAggregateListenerEx() { // from class: kpi.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i3, int i4) {
                kpiVar.bQa_(sparseArray, list, iBaseResponseCallback, i, i2, j);
            }
        });
    }

    private int b(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.a("HiH_SleepDataModelInteractor", "no manual sleep data");
            return -1;
        }
        Collections.sort(list, new Comparator<HiHealthData>() { // from class: kpi.9
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                return (int) (hiHealthData.getLong("data_session_manual_sleep_go_to_bed_time_key") - hiHealthData2.getLong("data_session_manual_sleep_go_to_bed_time_key"));
            }
        });
        HiHealthData hiHealthData = list.get(0);
        String timeZone = TextUtils.isEmpty(hiHealthData.getTimeZone()) ? "+8:00" : hiHealthData.getTimeZone();
        int e2 = nrq.e(hiHealthData.getLong("data_session_manual_sleep_go_to_bed_time_key"), timeZone, 1);
        int e3 = nrq.e(hiHealthData.getLong("data_session_manual_sleep_sleep_time_key"), timeZone, 1);
        LogUtil.a("HiH_SleepDataModelInteractor", "first manual sleep part bedtime is ", Integer.valueOf(e2), " sleepTime is ", Integer.valueOf(e3));
        return e3 - e2;
    }

    private List<HiHealthData> a(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getLong("data_session_manual_sleep_sleep_time_key") != 0 || hiHealthData.getLong("data_session_manual_sleep_wake_time_key") != 0) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private SleepTotalData d(List<HiHealthData> list) {
        SleepTotalData sleepTotalData = new SleepTotalData();
        this.f = 0;
        this.j = 0;
        this.g = 0;
        this.i = 0;
        if (koq.b(list)) {
            LogUtil.a("HiH_SleepDataModelInteractor", "calculateManualAvg datas is empty!");
            return sleepTotalData;
        }
        List<HiHealthData> a2 = a(list);
        List<HiHealthData> a3 = a(a2, "data_session_manual_sleep_go_to_bed_time_key");
        List<HiHealthData> a4 = a(a2, "data_session_manual_sleep_get_up_time_key");
        List<HiHealthData> a5 = a(a2, "data_session_manual_sleep_sleep_time_key");
        List<HiHealthData> a6 = a(a2, "data_session_manual_sleep_wake_time_key");
        if (!e(a3, a4)) {
            e(0, 0, 0, 0, a3, a4, true);
        } else {
            LogUtil.a("HiH_SleepDataModelInteractor", "all bed rising list empty!");
        }
        if (!e(a5, a6)) {
            e(0, 0, 0, 0, a5, a6, false);
        } else {
            LogUtil.a("HiH_SleepDataModelInteractor", "all sleep wake list empty!");
        }
        if (!koq.b(a5) && !koq.b(a3)) {
            b(a5, a3);
        } else {
            LogUtil.h("HiH_SleepDataModelInteractor", "all sleepTimeList&bedTimeList empty!");
        }
        sleepTotalData.setAvgManualBedTime(this.f);
        sleepTotalData.setAvgManualRisingTime(this.j);
        sleepTotalData.setAvgManualSleepTime(this.g);
        sleepTotalData.setAvgManualWakeTime(this.i);
        sleepTotalData.setDailyManualBedScore(this.o);
        sleepTotalData.setDailyManualRisingScore(this.ad);
        sleepTotalData.setAvgManualLatencyTime(this.h);
        return sleepTotalData;
    }

    private void b(List<HiHealthData> list, List<HiHealthData> list2) {
        if (list.size() == list2.size()) {
            int size = list.size();
            Iterator<HiHealthData> it = list.iterator();
            int i = 0;
            int i2 = 0;
            while (it.hasNext()) {
                i2 += nrq.e(r4.getStartTime(), it.next().getTimeZone(), 1);
            }
            Iterator<HiHealthData> it2 = list2.iterator();
            while (it2.hasNext()) {
                i += nrq.e(r14.getStartTime(), it2.next().getTimeZone(), 1);
            }
            int i3 = (i2 - i) / size;
            this.h = i3;
            LogUtil.a("HiH_SleepDataModelInteractor", "getManualAvgLatency ", Integer.valueOf(i3), " sumBedTime ", Integer.valueOf(i), " sumSleepTime ", Integer.valueOf(i2), " sleepTimeCnt ", Integer.valueOf(size));
            return;
        }
        ReleaseLogUtil.d("HiH_SleepDataModelInteractor", "size not equal, sleepTimeList size ", Integer.valueOf(list.size()), " bedTimeList size ", Integer.valueOf(list2.size()));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0280  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(int r26, int r27, int r28, int r29, java.util.List<com.huawei.hihealth.HiHealthData> r30, java.util.List<com.huawei.hihealth.HiHealthData> r31, boolean r32) {
        /*
            Method dump skipped, instructions count: 874
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kpi.e(int, int, int, int, java.util.List, java.util.List, boolean):void");
    }

    private void d(int i, int i2, int i3, int i4, List<HiHealthData> list, List<HiHealthData> list2, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getStartTime() != 0) {
                int e2 = nrq.e(hiHealthData.getStartTime(), hiHealthData.getTimeZone(), 1);
                i += e2;
                i2++;
                arrayList.add(Integer.valueOf(e2));
            }
        }
        int i5 = 0;
        if (z) {
            this.f = Math.round(i / i2);
            Iterator it = arrayList.iterator();
            int i6 = 0;
            int i7 = 0;
            while (it.hasNext()) {
                i7++;
                int intValue = ((Integer) it.next()).intValue() - this.f;
                i6 += intValue * intValue;
            }
            this.o = Math.round(i6 / i7);
        } else {
            this.g = Math.round(i / i2);
        }
        ArrayList arrayList2 = new ArrayList();
        for (HiHealthData hiHealthData2 : list2) {
            if (hiHealthData2.getStartTime() != 0) {
                int e3 = nrq.e(hiHealthData2.getStartTime(), hiHealthData2.getTimeZone(), 1);
                i3 += e3;
                i4++;
                arrayList2.add(Integer.valueOf(e3));
            }
        }
        if (z) {
            this.j = Math.round(i3 / i4);
            Iterator it2 = arrayList2.iterator();
            int i8 = 0;
            while (it2.hasNext()) {
                i8++;
                int intValue2 = ((Integer) it2.next()).intValue() - this.j;
                i5 += intValue2 * intValue2;
            }
            this.ad = Math.round(i5 / i8);
            return;
        }
        this.i = Math.round(i3 / i4);
    }

    private List<HiHealthData> a(List<HiHealthData> list, String str) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            long j = hiHealthData.getLong(str);
            if (j != 0) {
                HiHealthData hiHealthData2 = new HiHealthData();
                hiHealthData2.setStartTime(j);
                hiHealthData2.setTimeZone(TextUtils.isEmpty(hiHealthData.getTimeZone()) ? "+8:00" : hiHealthData.getTimeZone());
                arrayList.add(hiHealthData2);
                Collections.sort(arrayList, new Comparator<HiHealthData>() { // from class: kpi.6
                    @Override // java.util.Comparator
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public int compare(HiHealthData hiHealthData3, HiHealthData hiHealthData4) {
                        return (int) (hiHealthData3.getStartTime() - hiHealthData4.getStartTime());
                    }
                });
            }
        }
        return arrayList;
    }

    private boolean c(int i, c cVar, c cVar2, long j, long j2) {
        if (j2 - cVar.e.getStartTime() > 72000) {
            return false;
        }
        if (i == 2 && j - (cVar2.e.getStartTime() / 1000) < 7200) {
            return true;
        }
        if (i != 3 || j - (cVar2.e.getStartTime() / 1000) >= 1800) {
            return i == 1 && a(cVar2.e.getStartTime() / 1000) == a(j);
        }
        return true;
    }

    private int c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        return calendar.get(11);
    }

    private int a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        return calendar.get(5);
    }

    private List<c> c(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new c(it.next(), false));
        }
        return arrayList;
    }

    private long b(List<HiHealthData> list, int i) {
        return list.get(i).getStartTime() / 1000;
    }

    private long c(List<c> list, int i) {
        return list.get(i).e.getStartTime() / 1000;
    }

    private boolean c(List<HiHealthData> list, List<HiHealthData> list2) {
        return Math.abs(list.size() - list2.size()) >= 2;
    }

    private boolean e(List<HiHealthData> list, List<HiHealthData> list2) {
        return koq.b(list) && koq.b(list2);
    }

    class c {
        private boolean c;
        private HiHealthData e;

        public c(HiHealthData hiHealthData, boolean z) {
            this.e = hiHealthData;
            this.c = z;
        }

        public c() {
        }
    }
}
