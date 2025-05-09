package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.dataswitch.BradycardiaStatSwitch;
import com.huawei.hihealthservice.sync.dataswitch.ExerciseIntensityStatSwitch;
import com.huawei.hihealthservice.sync.dataswitch.HeartRateRiseStatSwitch;
import com.huawei.hihealthservice.sync.dataswitch.HeartRateStatSwitch;
import com.huawei.hihealthservice.sync.dataswitch.MenstrualStatSwitch;
import com.huawei.hihealthservice.sync.dataswitch.SleepStatSwitch;
import com.huawei.hihealthservice.sync.dataswitch.StressStatSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthStatReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthStatReq;
import com.huawei.hwcloudmodel.model.unite.BradycardiaAlarmTotal;
import com.huawei.hwcloudmodel.model.unite.GetHealthStatRsp;
import com.huawei.hwcloudmodel.model.unite.HeartRateRiseAlarmTotal;
import com.huawei.hwcloudmodel.model.unite.HeartRateTotal;
import com.huawei.hwcloudmodel.model.unite.MenstrualTotal;
import com.huawei.hwcloudmodel.model.unite.SleepTotal;
import com.huawei.hwcloudmodel.model.unite.StressTotal;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.ui.openservice.OpenServiceUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class iti implements HiSyncBase {
    private ijr aa;
    private StressStatSwitch ab;
    private MenstrualStatSwitch ac;
    private SleepStatSwitch ad;
    private BradycardiaStatSwitch n;
    private iis p;
    private HiSyncOption q;
    private int r;
    private HeartRateRiseStatSwitch s;
    private HeartRateStatSwitch t;
    private Context v;
    private ExerciseIntensityStatSwitch w;
    private int x;
    private jbs y;
    private static final int[] j = {46016, 46017, 46018, 46019, 46020};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f13598a = {"maxHeartRate", "minHeartRate", "avgRestingHeartRate", "lastHeartRate", "lastRestHeartRate"};
    private static final int[] i = {47401, 47405, 47402, 47403, 47404};
    private static final String[] f = {"menstrual_status", "menstrual_sub_status", "menstrual_quantity", "menstrual_dysmenorrhea", "menstrual_physical"};
    private static final int[] l = {44308, 44305, 44304, 44306, 44307};
    private static final String[] m = {"stress_score_count", "stress_score_max", "stress_score_min", "stress_score_avg", "stress_score_last"};
    private static final int[] b = {47102, 47103, 47104, 47105, 47106, 47101, 47107, 47108, 47109};
    private static final String[] c = {OpenServiceUtil.Location.STEP, "RUN", "CYCLE", "FITNESS", "HEART", "TOTAL", "CLIMB", "SWIM", "UNKNOWHIGH"};
    private static final int[] o = {44001, 44002, 44003, 44005, 44004, 44006, 44007, 44008, 44009};
    private static final String[] k = {"stat_sleep_deep_duration", "stat_sleep_shallow_duration", "stat_sleep_wake_duration", "stat_sleep_wake_count", "stat_sleep_duration_sum", "stat_sleep_start_time", "stat_sleep_end_time", "stat_sleep_regular_start_time", "stat_sleep_regular_end_time"};
    private static final int[] g = {47004, 47003, 47002, 47005};
    private static final String[] h = {"heart_rate_rise_duration", "heart_rate_rise_max", "heart_rate_rise_min", "heart_rate_rise_alarmtimes"};
    private static final int[] e = {47054, 47053, 47052, 47055};
    private static final String[] d = {"heart_rate_bradycardia_duration", "heart_rate_bradycardia_max", "heart_rate_bradycardia_min", "heart_rate_bradycardia_alarmtimes"};
    private int z = 0;
    private int ai = 0;
    private boolean u = false;

    public iti(Context context, HiSyncOption hiSyncOption, int i2) {
        LogUtil.c("Debug_HiSyncHealthStat", "HiSyncHealthStat create");
        this.v = context.getApplicationContext();
        this.q = hiSyncOption;
        this.x = i2;
        e();
    }

    private void e() {
        this.u = iuz.i();
        this.y = jbs.a(this.v);
        this.aa = ijr.d();
        this.r = HiDateUtil.c(System.currentTimeMillis());
        this.t = new HeartRateStatSwitch(this.v);
        this.p = iis.d();
        this.ab = new StressStatSwitch(this.v);
        this.w = new ExerciseIntensityStatSwitch(this.v);
        this.ad = new SleepStatSwitch(this.v);
        this.s = new HeartRateRiseStatSwitch(this.v);
        this.n = new BradycardiaStatSwitch(this.v);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        SparseArray<Integer> bCF_;
        SparseArray<Integer> bCF_2;
        ReleaseLogUtil.e("HiH_HiSyncHealthStat", "pullDataByVersion() begin !");
        long a2 = HiDateUtil.a(this.r);
        long e2 = HiDateUtil.e(a2, 1);
        if (iuz.f()) {
            LogUtil.c("Debug_HiSyncHealthStat", "pullDataByVersion() first sync pull all stat!");
            bCF_ = iuz.bCF_(1388509200000L, a2, 90);
            bCF_2 = iuz.bCF_(1388509200000L, e2, 90);
        } else {
            LogUtil.c("Debug_HiSyncHealthStat", "pullDataByVersion() only pullDataByVersion recent days stat");
            bCF_ = iuz.bCF_(a2 - HwExerciseConstants.TEN_DAY_SECOND, a2, 90);
            bCF_2 = iuz.bCF_(a2 - ThirdLoginDataStorageUtil.REFRESH_TOKEN_INTERVAL, e2, 90);
        }
        bCp_(bCF_, bCF_2, false);
        ReleaseLogUtil.e("HiH_HiSyncHealthStat", "pullDataByVersion() end!");
    }

    private void bCp_(SparseArray<Integer> sparseArray, SparseArray<Integer> sparseArray2, boolean z) throws iut {
        if (z) {
            LogUtil.c("Debug_HiSyncHealthStat", "downloadAllStat too much need to download ,start a thread! downloadDaysMap is ", sparseArray);
            bCq_(sparseArray, sparseArray2);
        } else {
            LogUtil.c("Debug_HiSyncHealthStat", "downloadAllStat do not need to start a thread! downloadDaysMap is ", sparseArray);
            bCr_(sparseArray, sparseArray2);
        }
    }

    private void bCr_(SparseArray<Integer> sparseArray, SparseArray<Integer> sparseArray2) throws iut {
        LogUtil.c("Debug_HiSyncHealthStat", "downloadAllStatByTimeSync downloadDaysMap is ", sparseArray);
        if (sparseArray == null || sparseArray.size() <= 0 || sparseArray2 == null || sparseArray2.size() == 0) {
            LogUtil.h("Debug_HiSyncHealthStat", "downloadAllStatByTimeSync() downloadDaysMap is null, stop pullStat");
        } else {
            bCt_(sparseArray, sparseArray2);
        }
    }

    private void bCq_(SparseArray<Integer> sparseArray, SparseArray<Integer> sparseArray2) {
        if (sparseArray == null || sparseArray.size() <= 0 || sparseArray2 == null || sparseArray2.size() <= 0) {
            LogUtil.h("Debug_HiSyncHealthStat", "downloadAllStatByTime() downloadDaysMap is null, stop pullDataByVersion");
        } else {
            new Thread(new e(sparseArray, sparseArray2)).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bCt_(SparseArray<Integer> sparseArray, SparseArray<Integer> sparseArray2) throws iut {
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            int keyAt = sparseArray.keyAt(size);
            c(keyAt, sparseArray.get(keyAt).intValue());
        }
        bCs_(sparseArray2);
    }

    public void b(int i2, int i3) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncHealthStat", "enter downloadMenstrualData");
        long a2 = HiDateUtil.a(this.r);
        bCs_(iuz.bCF_(a2 - (i2 * 86400000), a2 + (i3 * 86400000), 90));
        HiBroadcastUtil.c(this.v, 9);
    }

    private void bCs_(SparseArray<Integer> sparseArray) throws iut {
        if (d() == 1 || a()) {
            for (int size = sparseArray.size() - 1; size >= 0; size--) {
                int keyAt = sparseArray.keyAt(size);
                a(keyAt, sparseArray.get(keyAt).intValue());
            }
        }
    }

    private boolean a() {
        HiUserPreference a2 = ijy.c(this.v).a(this.x, "CycleCalendarSetting");
        if (a2 == null || HiCommonUtil.b(a2.getValue())) {
            ReleaseLogUtil.d("Debug_HiSyncHealthStat", "getMenstrualActiveState MENSTRUAL_CONFIG_KEY is null!");
            return false;
        }
        try {
            return new JSONObject(a2.getValue()).getBoolean("activeStatus");
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HiH_HiSyncHealthStat", "getMenstrualActiveState JSONException", LogAnonymous.b((Throwable) e2));
            return false;
        }
    }

    private int d() {
        HiUserPreference a2 = ijy.c(this.v).a(this.x, "com.huawei.health.mc");
        if (a2 == null || HiCommonUtil.b(a2.getValue())) {
            ReleaseLogUtil.d("Debug_HiSyncHealthStat", "getOldMenstrualState MENSTRUAL_CONFIG_KEY is null!");
            return 0;
        }
        try {
            return new JSONObject(a2.getValue()).getInt("masterSwitch");
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HiH_HiSyncHealthStat", "getMenstrualActiveState JSONException", LogAnonymous.b((Throwable) e2));
            return 0;
        }
    }

    private void c(int i2, int i3) throws iut {
        if (i2 > i3 || i2 <= 0) {
            LogUtil.h("Debug_HiSyncHealthStat", "downloadHealthStatByTime the time is not right");
            return;
        }
        ReleaseLogUtil.e("Debug_HiSyncHealthStat", "downloadHealthStatByTime startTime is ", Integer.valueOf(i2), " , endDay is ", Integer.valueOf(i3));
        GetHealthStatRsp d2 = d(i2, i3);
        if (ius.a(d2, false)) {
            this.aa.a(this.x, this.q.getSyncDataType(), i3, 0L);
            g(d2.getHeartRateTotal());
            n(d2.getStressTotal());
            l(d2.getSleepTotal());
            i(d2.getHeartRateRiseTotal());
            f(d2.getBradycardiaAlarmTotal());
        }
    }

    private void a(int i2, int i3) throws iut {
        if (i2 > i3 || i2 <= 0) {
            LogUtil.h("Debug_HiSyncHealthStat", "downloadFutureStat the time is not right");
            return;
        }
        ReleaseLogUtil.e("Debug_HiSyncHealthStat", "downloadFutureStat startTime is ", Integer.valueOf(i2), " , endDay is ", Integer.valueOf(i3));
        GetHealthStatRsp e2 = e(i2, i3);
        if (ius.a(e2, false)) {
            h(e2.getMenstrualTotal());
        }
    }

    private GetHealthStatRsp d(int i2, int i3) {
        GetHealthStatReq getHealthStatReq = new GetHealthStatReq();
        getHealthStatReq.setStartTime(i2);
        getHealthStatReq.setEndTime(i3);
        getHealthStatReq.setDataSource(2);
        HashSet hashSet = new HashSet(this.u ? 3 : 6);
        hashSet.add(3);
        hashSet.add(7);
        hashSet.add(11);
        hashSet.add(12);
        if (!this.u) {
            hashSet.add(13);
            hashSet.add(14);
        }
        getHealthStatReq.setTypes(hashSet);
        LogUtil.a("HiH_HiSyncHealthStat", "getHealthStatRsp req is ", HiJsonUtil.e(getHealthStatReq));
        return this.y.a(getHealthStatReq);
    }

    private GetHealthStatRsp e(int i2, int i3) {
        GetHealthStatReq getHealthStatReq = new GetHealthStatReq();
        getHealthStatReq.setStartTime(i2);
        getHealthStatReq.setEndTime(i3);
        getHealthStatReq.setDataSource(2);
        HashSet hashSet = new HashSet(1);
        hashSet.add(1001);
        getHealthStatReq.setTypes(hashSet);
        LogUtil.a("HiH_HiSyncHealthStat", "getFutureStatRsp req is ", HiJsonUtil.e(getHealthStatReq));
        return this.y.a(getHealthStatReq);
    }

    private void g(List<HeartRateTotal> list) {
        int transferHealthStatData;
        if (list == null) {
            LogUtil.h("Debug_HiSyncHealthStat", "downloadHealthStatByTime heartRateStats is null");
            return;
        }
        List<igo> d2 = this.t.d(list, this.x);
        if (iwe.d(this.v, "isInsertBatchStats", this.x, false) && !iuz.b(d2)) {
            transferHealthStatData = isf.a(this.v).insertBatchDayStatTable(d2);
        } else {
            transferHealthStatData = isf.a(this.v).transferHealthStatData(d2);
        }
        if (transferHealthStatData == 0) {
            ivg.c().a(5, "sync download", new ikv(this.v.getPackageName()));
        }
    }

    private void h(List<MenstrualTotal> list) {
        if (list == null) {
            LogUtil.h("Debug_HiSyncHealthStat", "downloadHealthStatByTime menstrualStats is null");
            return;
        }
        for (MenstrualTotal menstrualTotal : list) {
            Object[] objArr = new Object[2];
            objArr[0] = "saveHeartStatToDB menstrualStat is ";
            objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(menstrualTotal) : null;
            LogUtil.c("Debug_HiSyncHealthStat", objArr);
            if (this.ac == null) {
                this.ac = new MenstrualStatSwitch(this.v);
            }
            List<igo> b2 = this.ac.b(menstrualTotal, this.x);
            if (koq.b(b2)) {
                return;
            } else {
                isf.a(this.v).transferHealthStatData(b2);
            }
        }
    }

    private void n(List<StressTotal> list) {
        int transferHealthStatData;
        if (list == null) {
            LogUtil.h("Debug_HiSyncHealthStat", "downloadHealthStatByTime stressTotals is null");
            return;
        }
        List<igo> e2 = this.ab.e(list, this.x);
        if (iwe.d(this.v, "isInsertBatchStats", this.x, false) && !iuz.b(e2)) {
            transferHealthStatData = isf.a(this.v).insertBatchDayStatTable(e2);
        } else {
            transferHealthStatData = isf.a(this.v).transferHealthStatData(e2);
        }
        if (transferHealthStatData == 0) {
            ivg.c().a(14, "sync download", new ikv(this.v.getPackageName()));
        }
    }

    private void l(List<SleepTotal> list) {
        int transferHealthStatData;
        if (list == null) {
            LogUtil.h("Debug_HiSyncHealthStat", "saveSleepStat sleepTotals is null");
            return;
        }
        List<igo> d2 = this.ad.d(list, this.x);
        if (iwe.d(this.v, "isInsertBatchStats", this.x, false) && !iuz.b(d2)) {
            transferHealthStatData = isf.a(this.v).insertBatchDayStatTable(d2);
        } else {
            transferHealthStatData = isf.a(this.v).transferHealthStatData(d2);
        }
        if (transferHealthStatData == 0) {
            ivg.c().a(2, "sync download", new ikv(this.v.getPackageName()));
        }
    }

    private void i(List<HeartRateRiseAlarmTotal> list) {
        if (list == null) {
            LogUtil.h("Debug_HiSyncHealthStat", "saveHeartRateRiseStat heartRateRiseAlarmTotals is null");
            return;
        }
        List<igo> c2 = this.s.c(list, this.x);
        if (iwe.d(this.v, "isInsertBatchStats", this.x, false) && !iuz.b(c2)) {
            isf.a(this.v).insertBatchDayStatTable(c2);
        } else {
            isf.a(this.v).transferHealthStatData(c2);
        }
    }

    private void f(List<BradycardiaAlarmTotal> list) {
        if (list == null) {
            LogUtil.h("Debug_HiSyncHealthStat", "saveBradycardiaStat bradycardiaAlarmTotals is null");
            return;
        }
        List<igo> c2 = this.n.c(list, this.x);
        if (iwe.d(this.v, "isInsertBatchStats", this.x, false) && !iuz.b(c2)) {
            isf.a(this.v).insertBatchDayStatTable(c2);
        } else {
            isf.a(this.v).transferHealthStatData(c2);
        }
    }

    private boolean b(List<HiHealthData> list) throws iut {
        if (!this.u) {
            int i2 = this.ai + 1;
            this.ai = i2;
            iuz.e(i2, this.q.getSyncManual());
        } else {
            int i3 = this.ai + 1;
            this.ai = i3;
            if (3 < i3) {
                this.z += 2;
                return false;
            }
        }
        List<HeartRateTotal> e2 = this.t.e(list);
        if (e2.isEmpty()) {
            this.z++;
            return false;
        }
        AddHealthStatReq addHealthStatReq = new AddHealthStatReq();
        addHealthStatReq.setHeartRateTotal(e2);
        addHealthStatReq.setTimeZone(list.get(0).getTimeZone());
        while (true) {
            if (this.z < 2) {
                if (!ius.a(this.y.d(addHealthStatReq), false)) {
                    this.z++;
                } else {
                    ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addHeartRateStat success ! uploadCount ", Integer.valueOf(this.ai));
                    Object[] objArr = new Object[2];
                    objArr[0] = "stat is ";
                    objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(e2) : null;
                    LogUtil.c("Debug_HiSyncHealthStat", objArr);
                    return true;
                }
            } else {
                ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addHeartRateStat failed ! uploadCount is ", Integer.valueOf(this.ai));
                Object[] objArr2 = new Object[2];
                objArr2[0] = "stat is ";
                objArr2[1] = CommonUtil.aq() ? HiJsonUtil.e(e2) : null;
                LogUtil.c("Debug_HiSyncHealthStat", objArr2);
                return false;
            }
        }
    }

    private boolean e(List<HiHealthData> list) throws iut {
        if (!this.u) {
            int i2 = this.ai + 1;
            this.ai = i2;
            iuz.e(i2, this.q.getSyncManual());
        } else {
            int i3 = this.ai + 1;
            this.ai = i3;
            if (i3 > 3) {
                this.z += 2;
                return false;
            }
        }
        if (this.ac == null) {
            this.ac = new MenstrualStatSwitch(this.v);
        }
        List<MenstrualTotal> c2 = this.ac.c(list);
        if (c2.isEmpty()) {
            this.z++;
            return false;
        }
        AddHealthStatReq addHealthStatReq = new AddHealthStatReq();
        addHealthStatReq.setMenstrualTotal(c2);
        addHealthStatReq.setTimeZone(list.get(0).getTimeZone());
        while (true) {
            if (this.z < 2) {
                if (!ius.a(this.y.d(addHealthStatReq), false)) {
                    this.z++;
                } else {
                    ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addMenstrualStat success ! uploadCount is ", Integer.valueOf(this.ai));
                    Object[] objArr = new Object[2];
                    objArr[0] = "stat is ";
                    objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(c2) : null;
                    LogUtil.c("Debug_HiSyncHealthStat", objArr);
                    return true;
                }
            } else {
                ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addMenstrualStat failed ! uploadCount is ", Integer.valueOf(this.ai));
                Object[] objArr2 = new Object[2];
                objArr2[0] = "stat is ";
                objArr2[1] = CommonUtil.aq() ? HiJsonUtil.e(c2) : null;
                LogUtil.c("Debug_HiSyncHealthStat", objArr2);
                return false;
            }
        }
    }

    private boolean j(List<HiHealthData> list) throws iut {
        if (!this.u) {
            int i2 = this.ai + 1;
            this.ai = i2;
            iuz.e(i2, this.q.getSyncManual());
        } else {
            int i3 = this.ai + 1;
            this.ai = i3;
            if (3 < i3) {
                this.z += 2;
                return false;
            }
        }
        List<StressTotal> a2 = this.ab.a(list);
        if (a2.isEmpty()) {
            this.z++;
            return false;
        }
        AddHealthStatReq addHealthStatReq = new AddHealthStatReq();
        addHealthStatReq.setStressTotal(a2);
        addHealthStatReq.setTimeZone(list.get(0).getTimeZone());
        while (true) {
            if (this.z < 2) {
                if (!ius.a(this.y.d(addHealthStatReq), false)) {
                    this.z++;
                } else {
                    ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addStressStats success ! uploadCount is ", Integer.valueOf(this.ai));
                    Object[] objArr = new Object[2];
                    objArr[0] = "stat is ";
                    objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(a2) : null;
                    LogUtil.c("Debug_HiSyncHealthStat", objArr);
                    return true;
                }
            } else {
                ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addStressStats failed ! uploadCount is ", Integer.valueOf(this.ai));
                Object[] objArr2 = new Object[2];
                objArr2[0] = "stat is ";
                objArr2[1] = CommonUtil.aq() ? HiJsonUtil.e(a2) : null;
                LogUtil.c("Debug_HiSyncHealthStat", objArr2);
                return false;
            }
        }
    }

    private boolean c(List<HiHealthData> list) throws iut {
        if (!this.u) {
            int i2 = this.ai + 1;
            this.ai = i2;
            iuz.e(i2, this.q.getSyncManual());
        } else {
            int i3 = this.ai + 1;
            this.ai = i3;
            if (3 < i3) {
                this.z += 2;
                return false;
            }
        }
        List<SleepTotal> c2 = this.ad.c(list);
        if (c2.isEmpty()) {
            this.z++;
            return false;
        }
        AddHealthStatReq addHealthStatReq = new AddHealthStatReq();
        addHealthStatReq.setSleepTotal(c2);
        addHealthStatReq.setTimeZone(list.get(0).getTimeZone());
        while (true) {
            if (this.z < 2) {
                if (!ius.a(this.y.d(addHealthStatReq), false)) {
                    this.z++;
                } else {
                    ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addSleepStats success ! uploadCount is ", Integer.valueOf(this.ai));
                    Object[] objArr = new Object[2];
                    objArr[0] = "stat is ";
                    objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(c2) : null;
                    LogUtil.c("Debug_HiSyncHealthStat", objArr);
                    return true;
                }
            } else {
                ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addSleepStats failed ! uploadCount is ", Integer.valueOf(this.ai));
                Object[] objArr2 = new Object[2];
                objArr2[0] = "stat is ";
                objArr2[1] = CommonUtil.aq() ? HiJsonUtil.e(c2) : null;
                LogUtil.c("Debug_HiSyncHealthStat", objArr2);
                return false;
            }
        }
    }

    private boolean a(List<HiHealthData> list) throws iut {
        if (!this.u) {
            int i2 = this.ai + 1;
            this.ai = i2;
            iuz.e(i2, this.q.getSyncManual());
        } else {
            int i3 = this.ai + 1;
            this.ai = i3;
            if (3 < i3) {
                this.z += 2;
                return false;
            }
        }
        List<HeartRateRiseAlarmTotal> d2 = this.s.d(list);
        if (d2.isEmpty()) {
            this.z++;
            return false;
        }
        AddHealthStatReq addHealthStatReq = new AddHealthStatReq();
        addHealthStatReq.setHeartRateRiseAlarmTotal(d2);
        addHealthStatReq.setTimeZone(list.get(0).getTimeZone());
        while (true) {
            if (this.z < 2) {
                if (!ius.a(this.y.d(addHealthStatReq), false)) {
                    this.z++;
                } else {
                    ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addHeartRateRiseStats success ! uploadCount is ", Integer.valueOf(this.ai));
                    Object[] objArr = new Object[2];
                    objArr[0] = "stat is ";
                    objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(d2) : null;
                    LogUtil.c("Debug_HiSyncHealthStat", objArr);
                    return true;
                }
            } else {
                ReleaseLogUtil.e("HiH_HiSyncHealthStat", "addHeartRateRiseStats failed ! uploadCount is ", Integer.valueOf(this.ai));
                Object[] objArr2 = new Object[2];
                objArr2[0] = "stat is ";
                objArr2[1] = CommonUtil.aq() ? HiJsonUtil.e(d2) : null;
                LogUtil.c("Debug_HiSyncHealthStat", objArr2);
                return false;
            }
        }
    }

    private void a(int i2) throws iut {
        int[] iArr;
        List<HiHealthData> b2;
        while (this.z < 2 && (b2 = b(i2, (iArr = o), k)) != null && !b2.isEmpty()) {
            if (c(b2)) {
                iuz.d(this.v, b2, iArr, i2);
            }
        }
        this.z = 0;
    }

    private void d(int i2) throws iut {
        int[] iArr;
        List<HiHealthData> b2;
        while (this.z < 2 && (b2 = b(i2, (iArr = j), f13598a)) != null && !b2.isEmpty()) {
            if (b(b2)) {
                iuz.d(this.v, b2, iArr, i2);
            }
        }
        this.z = 0;
    }

    private void b(int i2) throws iut {
        while (this.z < 2) {
            int[] iArr = i;
            List<HiHealthData> b2 = b(i2, iArr, f);
            if (koq.b(b2)) {
                break;
            } else if (e(b2)) {
                iuz.d(this.v, b2, iArr, i2);
            }
        }
        this.z = 0;
    }

    private void i(int i2) throws iut {
        int[] iArr;
        List<HiHealthData> b2;
        while (this.z < 2 && (b2 = b(i2, (iArr = l), m)) != null && !b2.isEmpty()) {
            if (j(b2)) {
                iuz.d(this.v, b2, iArr, i2);
            }
        }
        this.z = 0;
    }

    private void c(int i2) throws iut {
        int[] iArr;
        List<HiHealthData> b2;
        while (this.z < 2 && (b2 = b(i2, (iArr = g), h)) != null && !b2.isEmpty()) {
            if (a(b2)) {
                iuz.d(this.v, b2, iArr, i2);
            }
        }
        this.z = 0;
    }

    private boolean d(List<HiHealthData> list) throws iut {
        if (!this.u) {
            int i2 = this.ai + 1;
            this.ai = i2;
            iuz.e(i2, this.q.getSyncManual());
        } else {
            int i3 = this.ai + 1;
            this.ai = i3;
            if (3 < i3) {
                this.z += 2;
                return false;
            }
        }
        List<BradycardiaAlarmTotal> c2 = this.n.c(list);
        if (c2.isEmpty()) {
            this.z++;
            return false;
        }
        AddHealthStatReq addHealthStatReq = new AddHealthStatReq();
        addHealthStatReq.setBradycardiaAlarmTotal(c2);
        addHealthStatReq.setTimeZone(list.get(0).getTimeZone());
        while (true) {
            if (this.z < 2) {
                if (!ius.a(this.y.d(addHealthStatReq), false)) {
                    this.z++;
                } else {
                    ReleaseLogUtil.e("Debug_HiSyncHealthStat", "addHeartRateBradycardiaStats success ! uploadCount is ", Integer.valueOf(this.ai));
                    Object[] objArr = new Object[2];
                    objArr[0] = "stat is ";
                    objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(c2) : null;
                    LogUtil.c("Debug_HiSyncHealthStat", objArr);
                    return true;
                }
            } else {
                ReleaseLogUtil.e("Debug_HiSyncHealthStat", "addHeartRateBradycardiaStats failed ! uploadCount is ", Integer.valueOf(this.ai));
                Object[] objArr2 = new Object[2];
                objArr2[0] = "stat is ";
                objArr2[1] = CommonUtil.aq() ? HiJsonUtil.e(c2) : null;
                LogUtil.c("Debug_HiSyncHealthStat", objArr2);
                return false;
            }
        }
    }

    private void e(int i2) throws iut {
        int[] iArr;
        List<HiHealthData> b2;
        while (this.z < 2 && (b2 = b(i2, (iArr = e), d)) != null && !b2.isEmpty()) {
            if (d(b2)) {
                iuz.d(this.v, b2, iArr, i2);
            }
        }
        this.z = 0;
    }

    private List<HiHealthData> b(int i2, int[] iArr, String[] strArr) {
        return iuz.e(this.v, i2, iArr, strArr, new int[]{50, this.u});
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncHealthStat", "pushData() begin!");
        if (!ism.m()) {
            ReleaseLogUtil.d("HiH_HiSyncHealthStat", "pushData() dataPrivacy switch is closed, push end!");
            return;
        }
        int h2 = this.p.h(this.x);
        if (h2 <= 0) {
            LogUtil.h("Debug_HiSyncHealthStat", "pushData() no statClient get, maybe no data need to push ,push end !");
        } else {
            d(h2);
            b(h2);
            i(h2);
            if (!this.u) {
                c(h2);
                e(h2);
            }
            a(h2);
        }
        ReleaseLogUtil.e("HiH_HiSyncHealthStat", "pushData() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j2, long j3) throws iut {
        bCp_(iuz.bCF_(j2, j3, 90), iuz.bCF_(j2, j3, 90), false);
    }

    static class e implements Runnable {
        SparseArray<Integer> c;
        SparseArray<Integer> d;
        private WeakReference<iti> e;

        private e(iti itiVar, SparseArray<Integer> sparseArray, SparseArray<Integer> sparseArray2) {
            this.d = sparseArray;
            this.c = sparseArray2;
            this.e = new WeakReference<>(itiVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            iti itiVar = this.e.get();
            if (itiVar != null) {
                try {
                    itiVar.bCt_(this.d, this.c);
                    return;
                } catch (iut e) {
                    ReleaseLogUtil.c("HiH_HiSyncHealthStat", "downloadHealthStatByTime error ! e is ", e.getMessage());
                    return;
                }
            }
            LogUtil.h("Debug_HiSyncHealthStat", "StatDownloadRunnable() mSyncStat = null");
        }
    }

    public String toString() {
        return "--HiSyncHealthStat{}";
    }
}
