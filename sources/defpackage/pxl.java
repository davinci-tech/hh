package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class pxl {
    private static final Object c = new Object();
    private static pxl e;
    private int f = 22103;
    private int h = 22101;
    private int i = 22104;
    private int j = 22102;
    private int g = 22105;
    private int l = 0;
    private long d = 0;
    private int o = 0;
    private Map<String, Long> n = new HashMap(16);
    private int[] m = {44102, 44103, 44101, 44107, 44203, 44201, 44202, 44208, 44106, 44105, 44108, 44001, 44002, 44003, 44104, 44005, 44206};
    private int[] p = {44102, 44103, 44101, 44107, 44203, 44201, 44202, 44208, 44106, 44105, 44108, 44001, 44002, 44003, 44104, 44005, 44206, 44004, 44008, 44009};
    private String[] k = {"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_dream_key", "core_sleep_wake_count_key", "core_sleep_score_key", "core_sleep_fall_key", "core_sleep_wake_up_key", "core_sleep_snore_freq_key", "core_sleep_deep_sleep_part_key", "core_sleep_total_sleep_time_key", "core_sleep_day_sleep_time_key", "sleep_deep_key", "sleep_shallow_key", "sleep_wake_key", "core_sleep_wake_key", "sleep_wake_count_key", "core_sleep_valid_data_key"};

    /* renamed from: a, reason: collision with root package name */
    private String[] f16331a = {"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_dream_key", "core_sleep_wake_count_key", "core_sleep_score_key", "core_sleep_fall_key", "core_sleep_wake_up_key", "core_sleep_snore_freq_key", "core_sleep_deep_sleep_part_key", "core_sleep_total_sleep_time_key", "core_sleep_day_sleep_time_key", "sleep_deep_key", "sleep_shallow_key", "sleep_wake_key", "core_sleep_wake_key", "sleep_wake_count_key", "core_sleep_valid_data_key", "common_sleep_duration_sleep_sum_key", "stat_sleep_start_time", "stat_sleep_end_time"};
    private Context b = BaseApplication.getContext();

    private pxl() {
    }

    public static pxl e() {
        pxl pxlVar;
        synchronized (c) {
            if (e == null) {
                e = new pxl();
            }
            pxlVar = e;
        }
        return pxlVar;
    }

    public void e(final long j, final int i, final int i2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j * 1000, ((((i * i2) * 60) + j) * 1000) - 1);
        hiDataReadOption.setType(new int[]{22100, 22000});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(this.b).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: pxl.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i3, Object obj, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i3, int i4) {
                List list;
                ArrayList arrayList = new ArrayList(16);
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray == null || sparseArray.size() <= 0) {
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(0, arrayList);
                    }
                    list = null;
                } else {
                    list = (List) sparseArray.get(22100);
                    pxl.this.f = 22103;
                    pxl.this.h = 22101;
                    pxl.this.i = 22104;
                }
                if (list == null || list.size() <= 0) {
                    if (sparseArray != null && sparseArray.size() > 0) {
                        list = (List) sparseArray.get(22000);
                    }
                    pxl.this.f = 22001;
                    pxl.this.h = 22002;
                    pxl.this.i = 22003;
                }
                List list2 = list;
                Object[] objArr = new Object[2];
                objArr[0] = "size = ";
                objArr[1] = Integer.valueOf(list2 == null ? 0 : list2.size());
                LogUtil.a("HiH_SleepOldDataModelInteractor", objArr);
                Object[] objArr2 = new Object[2];
                objArr2[0] = "sleep detail point = ";
                objArr2[1] = list2 == null ? 0 : list2.toString();
                LogUtil.c("SleepOldDataModelInteractor", objArr2);
                pxl.this.l = 0;
                ArrayList arrayList2 = new ArrayList(16);
                pxl.this.n.clear();
                for (int i5 = 0; i5 < i2; i5++) {
                    pxl.this.b(arrayList, (List<HiHealthData>) list2, j, new int[]{i5, i}, arrayList2);
                }
                pxl.this.c(arrayList, arrayList2, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<SleepTotalData> list, List<HiHealthData> list2, long j, int[] iArr, List<Map<String, Long>> list3) {
        SleepTotalData sleepTotalData = new SleepTotalData();
        this.d = (j + (iArr[0] * iArr[1] * 60)) * 1000;
        if (list2 != null) {
            int size = list2.size();
            int i = this.l;
            if (size > i && list2.get(i).getStartTime() == this.d) {
                a(list2, sleepTotalData, list3);
            }
        }
        list.add(sleepTotalData);
    }

    private void a(List<HiHealthData> list, SleepTotalData sleepTotalData, List<Map<String, Long>> list2) {
        HiHealthData hiHealthData = list.get(this.l);
        this.o = hiHealthData.getInt("trackdata_deviceType");
        if (this.f == hiHealthData.getType()) {
            sleepTotalData.setType(7);
            sleepTotalData.setDeepSleepTime(1);
        } else if (this.h == hiHealthData.getType()) {
            sleepTotalData.setType(6);
            sleepTotalData.setShallowSleepTime(1);
        } else if (this.i == hiHealthData.getType()) {
            sleepTotalData.setType(FitnessSleepType.HW_FITNESS_WAKE);
            sleepTotalData.setWakeupDuration(1);
        } else if (this.j == hiHealthData.getType()) {
            sleepTotalData.setType(FitnessSleepType.HW_FITNESS_DREAM);
            sleepTotalData.setSlumberSleepTime(1);
        } else if (this.g == hiHealthData.getType()) {
            if (this.n.get("core_sleep_start_time_key") == null) {
                this.n.put("core_sleep_start_time_key", Long.valueOf(hiHealthData.getStartTime()));
            }
            if (this.l + 1 < list.size()) {
                HiHealthData hiHealthData2 = list.get(this.l + 1);
                if (hiHealthData2.getType() != this.g) {
                    this.n.put("core_sleep_end_time_key", Long.valueOf(hiHealthData.getEndTime()));
                    list2.add(this.n);
                    this.n = new HashMap(16);
                } else if (hiHealthData2.getStartTime() != hiHealthData.getEndTime()) {
                    this.n.put("core_sleep_end_time_key", Long.valueOf(hiHealthData.getEndTime()));
                    list2.add(this.n);
                    this.n = new HashMap(16);
                } else {
                    this.n.put("core_sleep_end_time_key", Long.valueOf(hiHealthData2.getEndTime()));
                }
            } else {
                LogUtil.a("HiH_SleepOldDataModelInteractor", "setFlagData this is a last minute");
                this.n.put("core_sleep_end_time_key", Long.valueOf(hiHealthData.getEndTime()));
            }
            if (this.l == list.size() - 1) {
                list2.add(this.n);
            }
            sleepTotalData.setType(FitnessSleepType.HW_FITNESS_NOON);
            sleepTotalData.setNoonSleepTime(1);
        } else {
            LogUtil.a("HiH_SleepOldDataModelInteractor", "tepSleepData.getType()  = ", Integer.valueOf(hiHealthData.getType()));
        }
        sleepTotalData.setDeviceType(this.o);
        this.l++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<SleepTotalData> list, List<Map<String, Long>> list2, IBaseResponseCallback iBaseResponseCallback) {
        StringBuilder sb = new StringBuilder(list2.size());
        StringBuilder sb2 = new StringBuilder(list2.size());
        for (int i = 0; i < list2.size(); i++) {
            Map<String, Long> map = list2.get(i);
            Long l = map.get("core_sleep_start_time_key");
            if (l == null) {
                LogUtil.b("HiH_SleepOldDataModelInteractor", "setNoonSleepData start is null, noonMapList ", Integer.valueOf(list2.size()), " i ", Integer.valueOf(i));
            } else {
                Long l2 = map.get("core_sleep_end_time_key");
                if (l2 == null) {
                    LogUtil.b("HiH_SleepOldDataModelInteractor", "setNoonSleepData end is null, noonMapList ", Integer.valueOf(list2.size()), " i ", Integer.valueOf(i));
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
            list.remove(0);
            list.add(0, sleepTotalData);
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, list);
        }
    }

    public void b(long j, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.a("HiH_SleepOldDataModelInteractor", "getCoreSleepDetail callback is null");
            return;
        }
        if (i2 == 1 && i3 <= 1440) {
            e(j, i2, i3, iBaseResponseCallback);
        } else if (i2 >= 1440) {
            e(j, i, i2, i3, iBaseResponseCallback);
        } else {
            LogUtil.a("HiH_SleepOldDataModelInteractor", "getCoreSleepDetail callback ErrorConstants.ERR_NONE ");
            iBaseResponseCallback.d(100001, null);
        }
    }

    private void e(long j, int i, int i2, int i3, IBaseResponseCallback iBaseResponseCallback) {
        int i4;
        if (i == 0) {
            iBaseResponseCallback.d(100001, null);
            return;
        }
        long j2 = ((((i2 * i3) * 60) + j) * 1000) - 1;
        if (i == 5 && i3 == 12) {
            j2 = (((i2 * 60) + j) * 1000) - 1;
            i4 = 3;
        } else {
            i4 = 1;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(1000 * j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setAggregateType(i4);
        hiAggregateOption.setConstantsKey(this.f16331a);
        hiAggregateOption.setType(this.p);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setGroupUnitType(i);
        hiAggregateOption.setReadType(0);
        HiHealthManager.d(this.b).aggregateHiHealthData(hiAggregateOption, new c(iBaseResponseCallback, new int[]{i, i2, i3}, j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback, int[] iArr, long j) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HiH_SleepOldDataModelInteractor", "setSummary callback is null");
            return;
        }
        if (list == null || list.isEmpty()) {
            iBaseResponseCallback.d(100001, null);
            return;
        }
        LogUtil.a("HiH_SleepOldDataModelInteractor", "data.size() = ", Integer.valueOf(list.size()));
        LogUtil.c("HiH_SleepOldDataModelInteractor", "setSummary data = ", list.toString());
        ArrayList arrayList = new ArrayList(16);
        int i = iArr[2];
        int i2 = iArr[1];
        if (i == 1) {
            c(list, arrayList);
        } else {
            a(iArr[0], j, list, arrayList, new int[]{i, i2});
        }
        LogUtil.a("HiH_SleepOldDataModelInteractor", "sleepTotalData.size() = ", Integer.valueOf(arrayList.size()));
        iBaseResponseCallback.d(0, arrayList);
    }

    private void c(List<HiHealthData> list, List<SleepTotalData> list2) {
        int i;
        SleepTotalData sleepTotalData = new SleepTotalData();
        HiHealthData hiHealthData = list.get(0);
        int i2 = hiHealthData.getInt("core_sleep_deep_key");
        int i3 = hiHealthData.getInt("core_sleep_shallow_key");
        int i4 = hiHealthData.getInt("core_sleep_wake_key");
        int i5 = hiHealthData.getInt("core_sleep_wake_count_key");
        int i6 = hiHealthData.getInt("core_sleep_wake_dream_key");
        int i7 = hiHealthData.getInt("core_sleep_day_sleep_time_key");
        int i8 = hiHealthData.getInt("core_sleep_score_key");
        double d = hiHealthData.getDouble("core_sleep_fall_key");
        double d2 = hiHealthData.getDouble("core_sleep_wake_up_key");
        int i9 = hiHealthData.getInt("core_sleep_snore_freq_key");
        int i10 = hiHealthData.getInt("core_sleep_deep_sleep_part_key");
        int i11 = hiHealthData.getInt("core_sleep_total_sleep_time_key");
        double d3 = hiHealthData.getDouble("core_sleep_valid_data_key");
        String timeZone = hiHealthData.getTimeZone();
        if (i3 > 0 || i2 > 0 || i6 > 0 || i7 > 0) {
            sleepTotalData.setDeepSleepTime(i2);
            sleepTotalData.setShallowSleepTime(i3);
            sleepTotalData.setWakeupDuration(i4);
            sleepTotalData.setSlumberSleepTime(i6);
            sleepTotalData.setNoonSleepTime(i7);
            sleepTotalData.setWakeupTimes(i5);
            sleepTotalData.setScore(i8);
            sleepTotalData.setFallTime(nrq.e(d, timeZone, 1));
            sleepTotalData.setCoreSleepFallTime((long) d);
            if (d == 0.0d) {
                i = 0;
                sleepTotalData.setFallTime(0);
                sleepTotalData.setCoreSleepFallTime(0L);
            } else {
                i = 0;
            }
            sleepTotalData.setCoreSleepWakeupTime((long) d2);
            sleepTotalData.setWakeUpTime(nrq.e(d2, timeZone, 1));
            if (d2 == 0.0d) {
                sleepTotalData.setCoreSleepWakeupTime(0L);
                sleepTotalData.setWakeUpTime(i);
            }
            sleepTotalData.setSnoreFreq(i9);
            sleepTotalData.setDeepSleepPart(i10);
            sleepTotalData.setTotalSleepTime(i11);
            sleepTotalData.setType(31);
            sleepTotalData.setValidData(d3);
        } else {
            a(sleepTotalData, hiHealthData);
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

    private void a(int i, long j, List<HiHealthData> list, List<SleepTotalData> list2, int[] iArr) {
        for (int i2 = 0; i2 < iArr[0]; i2++) {
            SleepTotalData sleepTotalData = new SleepTotalData();
            for (HiHealthData hiHealthData : list) {
                long startTime = hiHealthData.getStartTime() / 1000;
                int i3 = (int) (((startTime - j) / 60) / iArr[1]);
                if (i == 5) {
                    int t = jec.t(jec.c(j));
                    int t2 = jec.t(jec.c(startTime));
                    if (t2 < t) {
                        t2 += 12;
                    }
                    i3 = t2 - t;
                }
                if (i3 == i2) {
                    e(sleepTotalData, hiHealthData);
                }
            }
            LogUtil.a("SleepOldDataModelInteractor", "getCoreSleepSummary sleepTotal = ", sleepTotalData);
            list2.add(sleepTotalData);
        }
    }

    private void e(SleepTotalData sleepTotalData, HiHealthData hiHealthData) {
        String timeZone = hiHealthData.getTimeZone();
        int round = Math.round(hiHealthData.getFloat("core_sleep_shallow_key"));
        int round2 = Math.round(hiHealthData.getFloat("core_sleep_deep_key"));
        int round3 = Math.round(hiHealthData.getFloat("core_sleep_wake_dream_key"));
        int round4 = Math.round(hiHealthData.getFloat("core_sleep_day_sleep_time_key"));
        int round5 = Math.round(hiHealthData.getFloat("core_sleep_wake_key"));
        if (round > 0 || round2 > 0 || round3 > 0 || round4 > 0) {
            sleepTotalData.setShallowSleepTime(round);
            sleepTotalData.setDeepSleepTime(round2);
            sleepTotalData.setSlumberSleepTime(round3);
            sleepTotalData.setWakeupDuration(round5);
            sleepTotalData.setNoonSleepTime(round4);
            sleepTotalData.setWakeupTimes(hiHealthData.getInt("core_sleep_wake_count_key"));
            sleepTotalData.setScore(hiHealthData.getInt("core_sleep_score_key"));
            sleepTotalData.setSnoreFreq(hiHealthData.getInt("core_sleep_snore_freq_key"));
            sleepTotalData.setDeepSleepPart(hiHealthData.getInt("core_sleep_deep_sleep_part_key"));
            sleepTotalData.setTotalSleepTime(Math.round(hiHealthData.getFloat("core_sleep_total_sleep_time_key")));
            double d = hiHealthData.getDouble("core_sleep_fall_key");
            sleepTotalData.setFallTime(nrq.e(d, timeZone, 1));
            sleepTotalData.setCoreSleepFallTime((long) d);
            if (d == 0.0d) {
                sleepTotalData.setFallTime(0);
                sleepTotalData.setCoreSleepFallTime(0L);
            }
            double d2 = hiHealthData.getDouble("core_sleep_wake_up_key");
            sleepTotalData.setCoreSleepWakeupTime((long) d2);
            sleepTotalData.setWakeUpTime(nrq.e(d2, timeZone, 1));
            if (d2 == 0.0d) {
                sleepTotalData.setCoreSleepWakeupTime(0L);
                sleepTotalData.setWakeUpTime(0);
            }
            sleepTotalData.setType(31);
            sleepTotalData.setSleepDayTime(hiHealthData.getStartTime());
            return;
        }
        sleepTotalData.setDeepSleepTime(hiHealthData.getInt("sleep_deep_key") / 60);
        sleepTotalData.setShallowSleepTime(hiHealthData.getInt("sleep_shallow_key") / 60);
        sleepTotalData.setWakeupDuration(hiHealthData.getInt("sleep_wake_key") / 60);
        sleepTotalData.setWakeupTimes(hiHealthData.getInt("sleep_wake_count_key"));
        sleepTotalData.setType(30);
        sleepTotalData.setCommonFallTime(nrq.e(hiHealthData.getDouble("stat_sleep_start_time"), timeZone, 1));
        sleepTotalData.setCommonWakeUpTime(nrq.e(hiHealthData.getDouble("stat_sleep_end_time"), timeZone, 1));
        sleepTotalData.setSleepDayTime(hiHealthData.getStartTime());
    }

    static class c implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private IBaseResponseCallback f16333a;
        private final int[] b;
        private final long c;
        private final WeakReference<pxl> e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        private c(pxl pxlVar, IBaseResponseCallback iBaseResponseCallback, int[] iArr, long j) {
            this.e = new WeakReference<>(pxlVar);
            this.f16333a = iBaseResponseCallback;
            this.b = iArr;
            this.c = j;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            WeakReference<pxl> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.h("HiH_SleepOldDataModelInteractor", "SummaryHiAggregateListener mReference is null");
                return;
            }
            pxl pxlVar = weakReference.get();
            if (pxlVar != null) {
                pxlVar.c(list, this.f16333a, this.b, this.c);
                this.f16333a = null;
            } else {
                LogUtil.h("HiH_SleepOldDataModelInteractor", "SummaryHiAggregateListener interactor is null");
            }
        }
    }
}
