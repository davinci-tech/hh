package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.google.gson.Gson;
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
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class fcd {
    private static fcd b = null;
    private static boolean c = false;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f12434a = {DicDataTypeUtil.DataType.BED_TIME.value(), DicDataTypeUtil.DataType.RISING_TIME.value(), DicDataTypeUtil.DataType.DAILY_SLEEP_LATENCY.value(), DicDataTypeUtil.DataType.SLEEP_SCORE.value(), DicDataTypeUtil.DataType.WAKE_COUNT.value(), DicDataTypeUtil.DataType.SLEEP_END_REASON.value(), DicDataTypeUtil.DataType.WAKE_UP_FEELING.value()};
    private static final String[] e = {"bedTime", "risingTime", "latency", "sleepScore", "wakeCount", "sleepEndReason", "wakeUpFeeling"};
    private int[] h = {44102, 44103, 44101, 44107, 44203, 44201, 44202, 44208, 44106, 44105, 44108, 44001, 44002, 44003, 44104, 44005, 44206, 44004, 44008, 44009, 44109, 44110, 44209, 44210, 44211, 44212, 44213, 44214, 44205, 44216, 44217, 44218, 44219, 44220, 44221, 44222, 44227, 44228, 44229, 44230, 44223, 44224, 44225, 44226, 44231, 44232, 44233, 44234};
    private String[] i = {"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_dream_key", "core_sleep_wake_count_key", "core_sleep_score_key", "core_sleep_fall_key", "core_sleep_wake_up_key", "core_sleep_snore_freq_key", "core_sleep_deep_sleep_part_key", "core_sleep_total_sleep_time_key", "core_sleep_day_sleep_time_key", "sleep_deep_key", "sleep_shallow_key", "sleep_wake_key", "core_sleep_wake_key", "sleep_wake_count_key", "core_sleep_valid_data_key", "common_sleep_duration_sleep_sum_key", "stat_sleep_start_time", "stat_sleep_end_time", "data_session_manual_sleep_bed_time_key", "sleep_device_category_key", "minHeartrate", "maxHeartrate", "minOxygenSaturation", "maxOxygenSaturation", "minBreathrate", "maxBreathrate", "core_sleep_go_bed_key", "core_sleep_off_bed_key", "core_sleep_bed_time_key", "core_sleep_latency_key", "avgHeartrate", "minHeartrateBaseline", "maxHeartrateBaseline", "heartrateDayToBaseline", "avgBreathrate", "minBreathrateBaseline", "maxBreathrateBaseline", "breathrateDayToBaseline", "avgOxygenSaturation", "minOxygenSaturationBaseline", "maxOxygenSaturationBaseline", "oxygenSaturationDayToBaseline", "avgHrv", "minHrvBaseline", "maxHrvBaseline", "hrvDayToBaseline"};
    private int[] f = {DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_WAKEUP_TIME.value()};
    private String[] j = {"data_session_manual_sleep_go_to_bed_time_key", "data_session_manual_sleep_get_up_time_key", "data_session_manual_sleep_sleep_time_key", "data_session_manual_sleep_wake_time_key"};
    private Context g = BaseApplication.getContext();

    private fcd() {
    }

    public static fcd c() {
        fcd fcdVar;
        synchronized (d) {
            if (b == null) {
                b = new fcd();
            }
            fcdVar = b;
        }
        return fcdVar;
    }

    public static void d(boolean z) {
        c = z;
    }

    public void d(Date date, Date date2, final int[] iArr, final IBaseResponseCallback iBaseResponseCallback) {
        final String valueOf = String.valueOf(DateFormatUtil.b(date.getTime()));
        final SparseArray<List<HiHealthData>> awg_ = fch.awg_(valueOf);
        final long e2 = jdl.e(date2.getTime(), 20, 0) - 1;
        final long e3 = jdl.e(jdl.b(date.getTime(), -1), 20, 0);
        if (c && awg_ != null && koq.c(awg_.get(22100))) {
            ReleaseLogUtil.e("R_Sleep_SleepDataQueryHelper", "detail data use cache");
            awc_(awg_, iArr, new long[]{e3, e2}, iBaseResponseCallback, true);
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(DateFormatUtil.a(e3, DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), DateFormatUtil.a(e2, DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), HiDataReadOption.TimeFormatType.DATE_FORMAT_MILLISECONDS);
        hiDataReadOption.setType(new int[]{22100});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: fcd.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj instanceof SparseArray) {
                    ReleaseLogUtil.e("R_Sleep_SleepDataQueryHelper", "Got detail data");
                    SparseArray sparseArray = (SparseArray) obj;
                    if (fcd.c) {
                        boolean unused = fcd.c = false;
                        long currentTimeMillis = System.currentTimeMillis();
                        boolean awi_ = fch.awi_(awg_, sparseArray);
                        LogUtil.a("SleepDataQueryHelper", "queryDayDayData diff time: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms");
                        if (!awi_ && !fcd.this.awb_(sparseArray)) {
                            LogUtil.a("SleepDataQueryHelper", "queryDayDayData same cache");
                            return;
                        }
                        fch.awj_(valueOf, sparseArray);
                    }
                    fcd.this.awc_(sparseArray, iArr, new long[]{e3, e2}, iBaseResponseCallback, false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean awb_(SparseArray<List<HiHealthData>> sparseArray) {
        boolean z;
        boolean z2;
        List<HiHealthData> arrayList = new ArrayList<>();
        if (sparseArray != null && sparseArray.size() > 0) {
            arrayList = sparseArray.get(22100);
        }
        if (!fcj.c(arrayList)) {
            return true;
        }
        if (koq.c(arrayList)) {
            z = b(arrayList);
            z2 = false;
        } else {
            z = true;
            z2 = true;
        }
        ReleaseLogUtil.e("R_Sleep_SleepDataQueryHelper", "isOnlyNoonOrManual ", Boolean.valueOf(z), "isCoreEmpty ", Boolean.valueOf(z2));
        return z || z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void awc_(SparseArray<List<HiHealthData>> sparseArray, int[] iArr, long[] jArr, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        List<HiHealthData> list;
        boolean z2;
        boolean z3;
        int i = iArr[2];
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList();
        if (sparseArray == null || sparseArray.size() <= 0) {
            list = arrayList2;
            z2 = true;
        } else {
            list = sparseArray.get(22100);
            z2 = false;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "first core sleep detail point = ";
        objArr[1] = list == null ? 0 : list.toString();
        LogUtil.a("SleepDataQueryHelper", objArr);
        ReleaseLogUtil.e("R_Sleep_SleepDataQueryHelper", "detail data is null: ", Boolean.valueOf(koq.b(list)));
        if (fcj.c(list)) {
            if (koq.c(list)) {
                z3 = b(list);
            } else {
                z2 = true;
                z3 = true;
            }
            fds fdsVar = new fds();
            fdsVar.e(jArr[0]);
            if (!z2) {
                fdsVar.d(22103);
                fdsVar.e(22101);
                fdsVar.a(22104);
                fcj.c(list, fdsVar, false);
            }
            if (!z && (z3 || z2)) {
                if (z) {
                    return;
                }
                c(fdsVar, iArr, jArr, iBaseResponseCallback, list);
                return;
            }
            Object[] objArr2 = new Object[2];
            objArr2[0] = "size = ";
            objArr2[1] = Integer.valueOf(list == null ? 0 : list.size());
            LogUtil.a("SleepDataQueryHelper", objArr2);
            for (int i2 = 0; i2 < i; i2++) {
                b(arrayList, list, new int[]{i2, 1}, fdsVar);
            }
            LogUtil.a("SleepDataQueryHelper", "parseDatas, idxSleep = ", Integer.valueOf(fdsVar.f()));
            LogUtil.a("SleepDataQueryHelper", "sleepTypeCount:", Arrays.toString(fdsVar.f12462a));
            e(arrayList, fdsVar.h(), iBaseResponseCallback);
        }
    }

    private boolean b(List<HiHealthData> list) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getType() != 22105 && hiHealthData.getType() != 22104 && hiHealthData.getType() != 22106 && hiHealthData.getType() != 22107) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> awa_(List<HiHealthData> list, SparseArray<Object> sparseArray, List<HiHealthData> list2, fds fdsVar) {
        if (koq.c(list)) {
            LogUtil.a("SleepDataQueryHelper", "choose normalSleepDataList");
            fdsVar.d(22001);
            fdsVar.e(22002);
            fdsVar.a(22003);
            fcj.c(list, fdsVar, true);
            List<HiHealthData> list3 = (List) sparseArray.get(22000);
            if (!koq.b(list2)) {
                Iterator<HiHealthData> it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getType() != 22105) {
                            break;
                        }
                    } else {
                        list3.addAll(list2);
                        break;
                    }
                }
            }
            return list3;
        }
        LogUtil.a("SleepDataQueryHelper", "choose coreSleepDataList");
        fdsVar.b(false);
        if (!koq.c(list2)) {
            return list2;
        }
        for (HiHealthData hiHealthData : list2) {
            if (hiHealthData.getType() == 22106 || hiHealthData.getType() == 22107) {
                fdsVar.b(true);
                return list2;
            }
        }
        return list2;
    }

    private void c(fds fdsVar, int[] iArr, long[] jArr, IBaseResponseCallback iBaseResponseCallback, List<HiHealthData> list) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(jArr[0], jArr[1]);
        hiDataReadOption.setType(new int[]{22000});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(this.g).readHiHealthData(hiDataReadOption, new c(iArr, list, iBaseResponseCallback, fdsVar, this));
    }

    static class c implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private fds f12437a;
        private int b;
        private List<HiHealthData> c;
        private IBaseResponseCallback d;
        private int e;
        private WeakReference<fcd> h;

        c(int[] iArr, List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback, fds fdsVar, fcd fcdVar) {
            this.b = iArr[1];
            this.e = iArr[2];
            this.c = list;
            this.f12437a = fdsVar;
            this.h = new WeakReference<>(fcdVar);
            this.d = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            fcd fcdVar = this.h.get();
            if (fcdVar == null || this.d == null || this.f12437a == null) {
                LogUtil.b("SleepDataQueryHelper", "sleepDataQueryHelper null or callback null or sleepQueryDetailData null");
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            ArrayList arrayList = new ArrayList(16);
            List list = null;
            if ((sparseArray == null || sparseArray.size() <= 0) && koq.b(this.c)) {
                ReleaseLogUtil.e("R_Sleep_SleepDataQueryHelper", "all detail is null");
                IBaseResponseCallback iBaseResponseCallback = this.d;
                if (iBaseResponseCallback != null) {
                    iBaseResponseCallback.d(0, arrayList);
                    return;
                }
            } else {
                if (sparseArray != null && sparseArray.size() > 0) {
                    list = (List) sparseArray.get(22000);
                }
                list = fcdVar.awa_(list, sparseArray, this.c, this.f12437a);
            }
            Object[] objArr = new Object[2];
            objArr[0] = "size = ";
            objArr[1] = Integer.valueOf(list == null ? 0 : list.size());
            LogUtil.a("SleepDataQueryHelper", objArr);
            Object[] objArr2 = new Object[2];
            objArr2[0] = "final sleep detail point = ";
            objArr2[1] = list == null ? 0 : list.toString();
            LogUtil.a("SleepDataQueryHelper", objArr2);
            if (koq.b(list)) {
                this.d.d(0, arrayList);
                return;
            }
            this.f12437a.c(0);
            this.f12437a.a(new HashMap(16));
            this.f12437a.e(new ArrayList(16));
            for (int i3 = 0; i3 < this.e; i3++) {
                fcdVar.b(arrayList, (List<HiHealthData>) list, new int[]{i3, this.b}, this.f12437a);
            }
            LogUtil.a("SleepDataQueryHelper", "getNormalSleepDailyDetail, idxSleep = ", Integer.valueOf(this.f12437a.f()));
            fcdVar.e(arrayList, this.f12437a.h(), this.d);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("SleepDataQueryHelper", "onResultIntent : read failed errorCode is", Integer.valueOf(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, List<HiHealthData> list2, int[] iArr, fds fdsVar) {
        HiHealthData hiHealthData = new HiHealthData();
        long m = fdsVar.m();
        long j = iArr[0];
        long j2 = iArr[1];
        if (list2 != null && list2.size() > fdsVar.f() && list2.get(fdsVar.f()).getStartTime() == m + (j * j2 * 60000)) {
            a(list2, hiHealthData, fdsVar);
        }
        list.add(hiHealthData);
    }

    private void b(List<HiHealthData> list, HiHealthData hiHealthData, fds fdsVar, HiHealthData hiHealthData2) {
        if (fdsVar.j().get("core_sleep_start_time_key") == null) {
            fdsVar.j().put("core_sleep_start_time_key", Long.valueOf(hiHealthData2.getStartTime()));
        }
        if (fdsVar.f() + 1 < list.size()) {
            HiHealthData hiHealthData3 = list.get(fdsVar.f() + 1);
            if (hiHealthData3.getType() != fdsVar.d()) {
                fdsVar.j().put("core_sleep_end_time_key", Long.valueOf(hiHealthData2.getEndTime()));
                fdsVar.h().add(new HashMap(fdsVar.j()));
                fdsVar.j().clear();
            } else if (hiHealthData3.getStartTime() != hiHealthData2.getEndTime()) {
                fdsVar.j().put("core_sleep_end_time_key", Long.valueOf(hiHealthData2.getEndTime()));
                fdsVar.h().add(new HashMap(fdsVar.j()));
                fdsVar.j().clear();
            } else {
                fdsVar.j().put("core_sleep_end_time_key", Long.valueOf(hiHealthData3.getEndTime()));
            }
        } else {
            LogUtil.a("SleepDataQueryHelper", "setFlagData this is a last minute");
            fdsVar.j().put("core_sleep_end_time_key", Long.valueOf(hiHealthData2.getEndTime()));
        }
        if (fdsVar.f() == list.size() - 1) {
            fdsVar.h().add(fdsVar.j());
        }
        hiHealthData.putInt("NOON_SLEEP_TIME", 1);
    }

    private void a(List<HiHealthData> list, HiHealthData hiHealthData, fds fdsVar) {
        HiHealthData hiHealthData2 = list.get(fdsVar.f());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(hiHealthData2.getStartTime());
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        hiHealthData.setStartTime(hiHealthData2.getStartTime());
        hiHealthData.setEndTime(hiHealthData2.getEndTime());
        if ((i == 20 && i2 == 0) || (i == 19 && i2 == 59)) {
            hiHealthData.putBoolean("IS_SUSPICIOUS", true);
        }
        if (fdsVar.g() != 0) {
            hiHealthData.putInt("DEVICE_TYPE", fdsVar.g());
        }
        if (fdsVar.i() != null) {
            hiHealthData.putString("DEVICE_ID", fdsVar.i());
        }
        if (hiHealthData2.getType() == 22106) {
            hiHealthData.putInt("BED_TIME", 1);
            fdsVar.b(0);
        } else if (hiHealthData2.getType() == 22107) {
            hiHealthData.putInt("SLEEP_TIME", 1);
            fdsVar.b(1);
        } else if (fdsVar.b() == hiHealthData2.getType()) {
            hiHealthData.putInt("DEEP_SLEEP_TIME", 1);
            fdsVar.b(2);
        } else if (fdsVar.e() == hiHealthData2.getType()) {
            hiHealthData.putInt("SHALLOW_SLEEP_TIME", 1);
            fdsVar.b(3);
        } else if (fdsVar.a() == hiHealthData2.getType()) {
            hiHealthData.putInt("WAKE_UP_DURATION", 1);
            fdsVar.b(4);
        } else if (fdsVar.c() == hiHealthData2.getType()) {
            hiHealthData.putInt("SLUMBER_SLEEP_TIME", 1);
            fdsVar.b(5);
        } else if (fdsVar.d() == hiHealthData2.getType() && !fdsVar.l()) {
            b(list, hiHealthData, fdsVar, hiHealthData2);
            fdsVar.b(6);
        } else {
            LogUtil.a("SleepDataQueryHelper", "tepSleepData.getType()  = ", Integer.valueOf(hiHealthData2.getType()));
            fdsVar.b(7);
        }
        fdsVar.c(fdsVar.f() + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list, List<Map<String, Long>> list2, IBaseResponseCallback iBaseResponseCallback) {
        StringBuilder sb = new StringBuilder(list2.size());
        StringBuilder sb2 = new StringBuilder(list2.size());
        for (int i = 0; i < list2.size(); i++) {
            Map<String, Long> map = list2.get(i);
            Long l = map.get("core_sleep_start_time_key");
            if (l == null) {
                LogUtil.b("SleepDataQueryHelper", "setNoonSleepData start is null, noonMapList ", Integer.valueOf(list2.size()), " i ", Integer.valueOf(i));
            } else {
                Long l2 = map.get("core_sleep_end_time_key");
                if (l2 == null) {
                    LogUtil.b("SleepDataQueryHelper", "setNoonSleepData end is null, noonMapList ", Integer.valueOf(list2.size()), " i ", Integer.valueOf(i));
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
            HiHealthData hiHealthData = list.get(0);
            hiHealthData.putString("NOON_START_TIME", sb.toString());
            hiHealthData.putString("NOON_END_TIME", sb2.toString());
            hiHealthData.putString("NOON_MAP_LIST", new Gson().toJson(list2));
            list.remove(0);
            list.add(0, hiHealthData);
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, list);
        }
    }

    public void b(Date date, Date date2, int[] iArr, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.e("R_Sleep_SleepDataQueryHelper", "getCoreSleepDetail callback is null");
            return;
        }
        if (iArr == null || iArr.length != 3) {
            ReleaseLogUtil.e("R_Sleep_SleepDataQueryHelper", "unit is null");
            return;
        }
        int i = iArr[1];
        if (i == 1 && iArr[2] <= 1440) {
            d(date, date2, iArr, iBaseResponseCallback);
            return;
        }
        if (i >= 1440) {
            c(date, date2, iArr, iBaseResponseCallback);
        } else if (iArr[2] >= 1440) {
            d(date, date2, iArr, iBaseResponseCallback);
        } else {
            LogUtil.a("SleepDataQueryHelper", "getCoreSleepDetail callback ErrorConstants.ERR_NONE ");
            iBaseResponseCallback.d(100001, null);
        }
    }

    private void c(Date date, Date date2, int[] iArr, IBaseResponseCallback iBaseResponseCallback) {
        if (iArr[0] == 0) {
            iBaseResponseCallback.d(100001, null);
            return;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setConstantsKey(this.i);
        hiAggregateOption.setType(this.h);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setGroupUnitType(iArr[0]);
        hiAggregateOption.setTimeInterval(DateFormatUtil.a(jdl.t(date.getTime()), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), DateFormatUtil.a(jdl.e(date2.getTime()), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), HiDataReadOption.TimeFormatType.DATE_FORMAT_MILLISECONDS);
        HiDataAggregateProOption c2 = HiDataAggregateProOption.builder().c(hiAggregateOption).b(true).c();
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(this.g).aggregateHiHealthDataPro(c2, new d(iBaseResponseCallback, iArr, date, date2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void awe_(List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback, int[] iArr, long j, SparseArray<List<HiHealthData>> sparseArray) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("SleepDataQueryHelper", "setSummary callback is null");
            return;
        }
        if (list == null || list.isEmpty()) {
            ReleaseLogUtil.d("R_Sleep_SleepDataQueryHelper", "Core stat is empty");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "Other stat is empty, ";
        objArr[1] = Boolean.valueOf(sparseArray == null || sparseArray.size() == 0);
        ReleaseLogUtil.e("R_Sleep_SleepDataQueryHelper", objArr);
        int i = iArr[2];
        if (i == 14) {
            awf_(iArr, j, list, iBaseResponseCallback, sparseArray);
        } else {
            awd_(sparseArray, list, iBaseResponseCallback, i, iArr[1], j);
        }
    }

    private List<HiHealthData> a(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h("SleepDataQueryHelper", "getFilterData data is null");
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getInt("trackdata_deviceType") == 32) {
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.a("SleepDataQueryHelper", "filterPhoneData.size() = ", Integer.valueOf(arrayList.size()), ", filterPhoneData = ", arrayList.toString());
        return arrayList;
    }

    private void awf_(int[] iArr, long j, List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback, SparseArray<List<HiHealthData>> sparseArray) {
        List<HiHealthData> list2;
        List<HiHealthData> list3;
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        if (sparseArray != null) {
            List<HiHealthData> e2 = e(sparseArray.get(0));
            list3 = a(sparseArray.get(1));
            list2 = e2;
        } else {
            list2 = arrayList;
            list3 = arrayList2;
        }
        long b2 = jdl.b(j, 7);
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        d(list, arrayList3, arrayList4, b2, (String) null);
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        d(list2, arrayList5, arrayList6, b2, "data_session_manual_sleep_get_up_time_key");
        ArrayList arrayList7 = new ArrayList(7);
        ArrayList arrayList8 = new ArrayList(7);
        d(list3, arrayList7, arrayList8, b2, "risingTime");
        List<List<HiHealthData>> e3 = fcj.e(arrayList3, arrayList5, arrayList7);
        List<List<HiHealthData>> e4 = fcj.e(arrayList4, arrayList6, arrayList8);
        ArrayList arrayList9 = new ArrayList(16);
        arrayList9.addAll(fcj.d(iArr[0], j, new int[]{iArr[2], iArr[1]}, e3));
        arrayList9.addAll(fcj.d(iArr[0], b2, new int[]{iArr[2], iArr[1]}, e4));
        LogUtil.a("SleepDataQueryHelper", "sleepViewDataList.size() = ", Integer.valueOf(arrayList9.size()));
        iBaseResponseCallback.d(0, arrayList9);
    }

    private void d(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3, long j, String str) {
        long j2;
        if (koq.b(list)) {
            LogUtil.h("SleepDataQueryHelper", "sumDataList is empty in matchDataList.");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (str == null) {
                j2 = hiHealthData.getStartTime();
            } else {
                j2 = hiHealthData.getLong(str);
            }
            if (j2 < j) {
                list2.add(hiHealthData);
            } else {
                list3.add(hiHealthData);
            }
        }
    }

    private void awd_(SparseArray<List<HiHealthData>> sparseArray, List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback, int i, int i2, long j) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("SleepDataQueryHelper", "setMonthYearSummary callback is null");
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.a("SleepDataQueryHelper", "setMonthYearSummary data = null ");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        List<HiHealthData> arrayList = new ArrayList<>(16);
        List<HiHealthData> arrayList2 = new ArrayList<>(16);
        if (sparseArray != null) {
            arrayList = e(sparseArray.get(0));
            arrayList2 = a(sparseArray.get(1));
        }
        ArrayList arrayList3 = new ArrayList(16);
        arrayList3.addAll(fcj.d(3, j, new int[]{i, i2}, fcj.e(list, arrayList, arrayList2)));
        LogUtil.a("SleepDataQueryHelper", "sleepViewDataList.size() = ", Integer.valueOf(arrayList3.size()));
        iBaseResponseCallback.d(0, arrayList3);
    }

    static class d implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private final IBaseResponseCallback f12438a;
        private final Date b;
        private final Date c;
        private final WeakReference<fcd> d;
        private final int[] e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        private d(fcd fcdVar, IBaseResponseCallback iBaseResponseCallback, int[] iArr, Date date, Date date2) {
            this.d = new WeakReference<>(fcdVar);
            this.f12438a = iBaseResponseCallback;
            this.e = iArr;
            this.b = date;
            this.c = date2;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            WeakReference<fcd> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("SleepDataQueryHelper", "SummaryHiAggregateListener mReference is null");
                return;
            }
            fcd fcdVar = weakReference.get();
            if (fcdVar != null) {
                fcdVar.d(fcdVar, list, this.f12438a, this.e, this.b, this.c);
                if (jdl.ac(this.c.getTime()) && koq.c(list)) {
                    fch.d(String.valueOf(DateFormatUtil.b(this.c.getTime())), list.get(list.size() - 1));
                    return;
                }
                return;
            }
            LogUtil.h("SleepDataQueryHelper", "SummaryHiAggregateListener interactor is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final fcd fcdVar, final List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback, final int[] iArr, final Date date, Date date2) {
        HiDataFilter.DataFilterExpression dataFilterExpression;
        HiDataFilter.DataFilterExpression dataFilterExpression2;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(DateFormatUtil.a(jdl.t(jdl.b(date.getTime(), -3)), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), DateFormatUtil.a(jdl.e(jdl.b(date2.getTime(), 3)), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), HiDataReadOption.TimeFormatType.DATE_FORMAT_MILLISECONDS);
        hiAggregateOption.setType(this.f);
        hiAggregateOption.setConstantsKey(this.j);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        if (iArr != null && iArr.length >= 3 && iArr[2] > 1) {
            dataFilterExpression = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.BIGGER_EQUAL, jdl.t(date.getTime()));
            dataFilterExpression2 = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.LESS_EQUAL, jdl.e(date2.getTime()));
        } else {
            dataFilterExpression = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.BIGGER_EQUAL, jdl.e(jdl.b(date.getTime(), -1), 20, 0));
            dataFilterExpression2 = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.LESS_EQUAL, jdl.e(date2.getTime(), 20, 0));
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataFilterExpression);
        arrayList.add(dataFilterExpression2);
        HiDataAggregateProOption c2 = HiDataAggregateProOption.builder().c(hiAggregateOption).b(HiJsonUtil.e(new HiDataFilter(arrayList, Collections.singletonList(0)))).c((String) null).c();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(c2);
        arrayList2.add(d(date, date2));
        HiHealthNativeApi.a(this.g).aggregateHiHealthDataProEx(arrayList2, new HiAggregateListenerEx() { // from class: fcd.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public void onResult(SparseArray<List<HiHealthData>> sparseArray, int i, int i2) {
                fcdVar.awe_(list, iBaseResponseCallback, iArr, jdl.t(date.getTime()), sparseArray);
            }
        });
    }

    private HiDataAggregateProOption d(Date date, Date date2) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(DateFormatUtil.a(jdl.t(date.getTime()), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), DateFormatUtil.a(jdl.e(date2.getTime()), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), HiDataReadOption.TimeFormatType.DATE_FORMAT_MILLISECONDS);
        hiAggregateOption.setType(f12434a);
        hiAggregateOption.setConstantsKey(e);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        return new HiDataAggregateProOption.Builder().c(hiAggregateOption).c((String) null).c();
    }

    private List<HiHealthData> e(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h("SleepDataQueryHelper", "getFilterData data is null");
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getLong("data_session_manual_sleep_sleep_time_key") != 0 || hiHealthData.getLong("data_session_manual_sleep_wake_time_key") != 0) {
                arrayList.add(hiHealthData);
            }
        }
        LogUtil.a("SleepDataQueryHelper", "fliterSimpleManualDatas.size() = ", Integer.valueOf(arrayList.size()), ", fliterSimpleManualDatas = ", arrayList.toString());
        return arrayList;
    }
}
