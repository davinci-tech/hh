package defpackage;

import android.content.Context;
import android.os.Process;
import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealthservice.sync.dataswitch.SportStatSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportStatReq;
import com.huawei.hwcloudmodel.model.SportTotalData;
import com.huawei.hwcloudmodel.model.unite.GetSportStatRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes7.dex */
public class itu implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private int f13606a;
    private jbs b;
    private Context c;
    private int d;
    private HiSyncOption e;
    private ijr f;
    private HiDataReadOption h;
    private SportStatSwitch i;

    static /* synthetic */ HiHealthData d(HiHealthData hiHealthData) {
        return hiHealthData;
    }

    public itu(Context context, HiSyncOption hiSyncOption, int i) {
        LogUtil.c("HiH_HiSyncTtlSptStat", "HiSyncSportStat create");
        this.c = context.getApplicationContext();
        this.e = hiSyncOption;
        this.f13606a = i;
        a();
    }

    private void a() {
        this.b = jbs.a(this.c);
        this.i = new SportStatSwitch(this.c);
        this.f = ijr.d();
        this.d = HiDateUtil.c(System.currentTimeMillis());
        this.h = c();
    }

    private void bCA_(SparseArray<Integer> sparseArray, boolean z) throws iut {
        if (z) {
            LogUtil.c("HiH_HiSyncTtlSptStat", "downloadAllStat too much need to download ,start a thread ! downloadDaysMap is ", sparseArray);
            bCB_(sparseArray);
            return;
        }
        LogUtil.c("HiH_HiSyncTtlSptStat", "downloadAllStat do not need to start a thread ! downloadDaysMap is ", sparseArray);
        bCC_(sparseArray);
        HiBroadcastUtil.c(this.c, 1);
        HiBroadcastUtil.i(this.c);
        ivg.c().a(1, "HiSyncTotalSportStat", new ikv(this.c.getPackageName()));
    }

    private void bCC_(SparseArray<Integer> sparseArray) throws iut {
        LogUtil.c("HiH_HiSyncTtlSptStat", "downloadAllStatByTimeSync downloadDaysMap is ", sparseArray);
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("HiH_HiSyncTtlSptStat", "downloadAllStatByTimeSync() downloadDaysMap is null,stop pullStat");
        } else {
            bCD_(sparseArray);
        }
    }

    private void bCB_(SparseArray<Integer> sparseArray) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("HiH_HiSyncTtlSptStat", "downloadAllStatByTime() downloadDaysMap is null,stop pullDataByVersion");
        } else {
            new Thread(new d(sparseArray)).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bCD_(SparseArray<Integer> sparseArray) throws iut {
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            int keyAt = sparseArray.keyAt(size);
            b(keyAt, sparseArray.get(keyAt).intValue());
            ivc.b(this.c, 1.0d, 1.0d, 1.0d);
        }
    }

    private void b(int i, int i2) throws iut {
        if (i > i2 || i <= 0) {
            LogUtil.h("HiH_HiSyncTtlSptStat", "downloadOneStatByTime the time is not right");
            return;
        }
        ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "downloadOneStatByTime startTime is ", Integer.valueOf(i), " , endTime is ", Integer.valueOf(i2));
        GetSportStatReq getSportStatReq = new GetSportStatReq();
        getSportStatReq.setStartTime(Integer.valueOf(i));
        getSportStatReq.setEndTime(Integer.valueOf(i2));
        getSportStatReq.setDataSource(3);
        GetSportStatRsp b = this.b.b(getSportStatReq);
        if (ius.a(b, false)) {
            this.f.a(this.f13606a, this.e.getSyncDataType(), i2, 0L);
            List<SportTotalData> sportStat = b.getSportStat();
            if (HiCommonUtil.d(sportStat)) {
                LogUtil.h("HiH_HiSyncTtlSptStat", "downloadOneStatByTime sportsStat is null or empty");
            } else {
                d(sportStat);
            }
        }
    }

    private void d(List<SportTotalData> list) {
        int transferHealthStatData;
        LogUtil.c("HiH_HiSyncTtlSptStat", "saveSportTotalToDB sportsStat size=", Integer.valueOf(list.size()));
        e(list);
        LogUtil.c("HiH_HiSyncTtlSptStat", "saveSportTotalToDB check after sportsStat size=", Integer.valueOf(list.size()));
        List<igo> a2 = this.i.a(list, this.f13606a);
        List<HiHealthData> b = this.i.b(list, this.f13606a);
        if (iwe.d(this.c, "isInsertBatchStats", this.f13606a, false) && !iuz.b(a2)) {
            transferHealthStatData = isf.a(this.c).insertBatchDayStatTable(a2);
            isf.a(this.c).insertBatchHealthDetailData(c(b), this.f13606a);
        } else {
            transferHealthStatData = isf.a(this.c).transferHealthStatData(a2);
            isf.a(this.c).saveSyncHealthDetailData(b, this.f13606a);
        }
        if (transferHealthStatData == 0) {
            ivg.c().a(1, "sync download", new ikv(this.c.getPackageName()));
        }
    }

    private List<HiHealthData> c(List<HiHealthData> list) {
        return (List) ((Map) list.stream().collect(Collectors.toMap(new Function() { // from class: itq
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return itu.c((HiHealthData) obj);
            }
        }, new Function() { // from class: itr
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return itu.d((HiHealthData) obj);
            }
        }, new BinaryOperator() { // from class: its
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return itu.c((HiHealthData) obj, (HiHealthData) obj2);
            }
        }))).values().stream().collect(Collectors.toList());
    }

    static /* synthetic */ String c(HiHealthData hiHealthData) {
        return hiHealthData.getStartTime() + Constants.LINK + hiHealthData.getType() + Constants.LINK + hiHealthData.getClientId();
    }

    static /* synthetic */ HiHealthData c(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        return hiHealthData.getValue() >= hiHealthData2.getValue() ? hiHealthData : hiHealthData2;
    }

    private void e(List<SportTotalData> list) {
        ArrayList<SportTotalData> arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (SportTotalData sportTotalData : list) {
            if (sportTotalData != null) {
                if (sportTotalData.getSportType().intValue() != 0) {
                    LogUtil.h("HiH_HiSyncTtlSptStat", "saveSportTotalToDB the sport stat type is not right ,type is ", sportTotalData.getSportType());
                } else if (0 == sportTotalData.getDeviceCode().longValue()) {
                    if (sportTotalData.getDataSource().intValue() == 0) {
                        arrayList.add(sportTotalData);
                    } else {
                        arrayList2.add(sportTotalData);
                    }
                }
            }
        }
        for (SportTotalData sportTotalData2 : arrayList) {
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((SportTotalData) it.next()).getRecordDay().equals(sportTotalData2.getRecordDay())) {
                    LogUtil.a("HiH_HiSyncTtlSptStat", "saveSportTotalToDB app and cloud has same day stat, day= ", sportTotalData2.getRecordDay());
                    list.remove(sportTotalData2);
                    break;
                }
            }
        }
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        SparseArray<Integer> bCF_;
        ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "pullDataByVersion() begin !");
        ivc.c(1.0d, "SYNC_SPORT_STAT_DOWNLOAD_PERCENT_MAX");
        long a2 = HiDateUtil.a(this.d);
        if (iuz.f()) {
            LogUtil.c("HiH_HiSyncTtlSptStat", "pullDataByVersion() first sync pull all stat!");
            bCF_ = iuz.bCF_(1388509200000L, a2, 90);
        } else {
            LogUtil.c("HiH_HiSyncTtlSptStat", "pullDataByVersion() only pullDataByVersion recent stat!");
            bCF_ = iuz.bCF_(a2 - HwExerciseConstants.TEN_DAY_SECOND, a2, 90);
        }
        bCA_(bCF_, false);
        ivc.b(this.c);
        ReleaseLogUtil.e("HiH_HiSyncTtlSptStat", "pullDataByVersion() end!");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
        bCA_(iuz.bCF_(j, j2, 90), false);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        itt.e(this.c).c(this.f13606a, this.h, this.e, this.i);
    }

    private HiDataReadOption c() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        int[] iArr = {901, 902, 903, 904, 905, TypedValues.Custom.TYPE_REFERENCE};
        hiDataReadOption.setConstantsKey(new String[]{"deviceStepStat", "deviceDistanceStat", "deviceCalorieStat", "deviceAltitudeStat", "deviceFloorStat", "deviceDurationStat"});
        hiDataReadOption.setType(iArr);
        return hiDataReadOption;
    }

    static class d implements Runnable {
        SparseArray<Integer> c;
        private WeakReference<itu> e;

        private d(itu ituVar, SparseArray<Integer> sparseArray) {
            this.c = sparseArray;
            this.e = new WeakReference<>(ituVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            itu ituVar = this.e.get();
            if (ituVar == null) {
                LogUtil.h("HiH_HiSyncTtlSptStat", "StatDownloadRunnable() mSyncStat = null");
                return;
            }
            try {
                if (!BaseApplication.isRunningForeground()) {
                    ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(Process.myTid())), 1);
                }
                ituVar.bCD_(this.c);
                iuz.f(ituVar.c, 10004);
            } catch (iut e) {
                ReleaseLogUtil.c("HiH_HiSyncTtlSptStat", "downloadOneStatByTime error ! e is ", e.getMessage());
            }
        }
    }

    public String toString() {
        return "--HiSyncTotalSportStat{}";
    }
}
