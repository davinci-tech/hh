package defpackage;

import android.content.Context;
import android.os.Process;
import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.data.constant.HiHealthDataKey;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.dataswitch.SportStatSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddSportTotalReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDimenStatisticsReq;
import com.huawei.hwcloudmodel.model.SportTotalData;
import com.huawei.hwcloudmodel.model.unite.GetSportDimenStatRsp;
import com.huawei.hwcloudmodel.model.unite.SportTotal;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class itf implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private int f13594a;
    private boolean b;
    private jbs c;
    private HiSyncOption d;
    private iis e;
    private Context f;
    private int g;
    private SportStatSwitch i;
    private ijr j;
    private int h = 0;
    private int l = 0;

    public itf(Context context, HiSyncOption hiSyncOption, int i) {
        this.b = false;
        LogUtil.c("Debug_HiSyncDimenSportStat", "HiSyncSportStat create");
        this.f = context.getApplicationContext();
        this.d = hiSyncOption;
        this.g = i;
        e();
        this.b = iuz.i();
    }

    private void e() {
        this.c = jbs.a(this.f);
        this.i = new SportStatSwitch(this.f);
        this.e = iis.d();
        this.j = ijr.d();
        this.f13594a = HiDateUtil.c(System.currentTimeMillis());
    }

    private GetSportDimenStatRsp b(GetSportDimenStatisticsReq getSportDimenStatisticsReq) {
        return this.c.b(getSportDimenStatisticsReq);
    }

    private void bCf_(SparseArray<Integer> sparseArray, boolean z) throws iut {
        if (z) {
            LogUtil.c("Debug_HiSyncDimenSportStat", "downloadAllStat too much need to download ,start a thread ! downloadDaysMap is ", sparseArray);
            bCg_(sparseArray);
        } else {
            LogUtil.c("Debug_HiSyncDimenSportStat", "downloadAllStat do not need to start a thread ! downloadDaysMap is ", sparseArray);
            bCh_(sparseArray);
        }
    }

    private void bCh_(SparseArray<Integer> sparseArray) throws iut {
        LogUtil.c("Debug_HiSyncDimenSportStat", "downloadAllStatByTimeSync downloadDaysMap is ", sparseArray);
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("Debug_HiSyncDimenSportStat", "downloadAllStatByTimeSync() downloadDaysMap is null, stop pullStat!");
        } else {
            bCi_(sparseArray);
        }
    }

    private void bCg_(SparseArray<Integer> sparseArray) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("Debug_HiSyncDimenSportStat", "downloadAllStatByTime() downloadDaysMap is null, stop pullDataByVersion!");
        } else {
            new Thread(new d(this, sparseArray)).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bCi_(SparseArray<Integer> sparseArray) throws iut {
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            int keyAt = sparseArray.keyAt(size);
            a(keyAt, sparseArray.get(keyAt).intValue());
        }
    }

    private void a(int i, int i2) throws iut {
        if (i > i2 || i <= 0) {
            LogUtil.h("Debug_HiSyncDimenSportStat", "downloadOneStatByTime the time is not right!");
            return;
        }
        LogUtil.c("Debug_HiSyncDimenSportStat", "downloadOneStatByTime startTime is ", Integer.valueOf(i), " , endTime is ", Integer.valueOf(i2));
        GetSportDimenStatisticsReq getSportDimenStatisticsReq = new GetSportDimenStatisticsReq();
        getSportDimenStatisticsReq.setStartTime(Integer.valueOf(i));
        getSportDimenStatisticsReq.setEndTime(Integer.valueOf(i2));
        GetSportDimenStatRsp b = b(getSportDimenStatisticsReq);
        if (ius.a(b, false)) {
            List<SportTotalData> sportStat = b.getSportStat();
            this.j.a(this.g, this.d.getSyncDataType(), i2, 0L);
            if (sportStat == null || sportStat.isEmpty()) {
                LogUtil.h("Debug_HiSyncDimenSportStat", "downloadOneStatByTime stringListMap is null or empty");
            } else {
                a(sportStat);
            }
        }
    }

    private void a(List<SportTotalData> list) {
        int transferHealthStatData;
        List<igo> a2 = this.i.a(this.i.b(list), this.g);
        List<HiHealthData> b = this.i.b(list, this.g);
        if (iwe.d(this.f, "isInsertBatchStats", this.g, false) && !iuz.b(a2)) {
            transferHealthStatData = isf.a(this.f).insertBatchDayStatTable(a2);
            isf.a(this.f).insertBatchHealthDetailData(b, this.g);
        } else {
            transferHealthStatData = isf.a(this.f).transferHealthStatData(a2);
            isf.a(this.f).saveSyncHealthDetailData(b, this.g);
        }
        if (transferHealthStatData == 0) {
            ivg.c().a(1, "sync download", new ikv(this.f.getPackageName()));
        }
    }

    private boolean b(List<HiHealthData> list) throws iut {
        if (!this.b) {
            int i = this.l + 1;
            this.l = i;
            iuz.e(i, this.d.getSyncManual());
        } else {
            int i2 = this.l + 1;
            this.l = i2;
            if (i2 > 3) {
                this.h += 2;
                return false;
            }
        }
        List<SportTotal> d2 = this.i.d(list);
        if (d2.isEmpty()) {
            this.h++;
            LogUtil.h("Debug_HiSyncDimenSportStat", "addDimenSportStat sportTotals is null or empty");
            return false;
        }
        AddSportTotalReq addSportTotalReq = new AddSportTotalReq();
        addSportTotalReq.setTotalInfo(d2);
        addSportTotalReq.setTimeZone(list.get(0).getTimeZone());
        while (this.h < 2) {
            if (!ius.a(this.c.e(addSportTotalReq), false)) {
                this.h++;
            } else {
                ReleaseLogUtil.e("Debug_HiSyncDimenSportStat", "addDimenSportStat success ! uploadCount is ", Integer.valueOf(this.l));
                LogUtil.a("Debug_HiSyncDimenSportStat", "stat is ", HiJsonUtil.e(d2));
                return true;
            }
        }
        ReleaseLogUtil.e("Debug_HiSyncDimenSportStat", "addDimenSportStat failed ! uploadCount is ", Integer.valueOf(this.l));
        return false;
    }

    public void b(int i) throws iut {
        List<HiHealthData> a2;
        while (this.h < 2 && (a2 = a(i)) != null && !a2.isEmpty()) {
            if (b(a2)) {
                iuz.d(this.f, a2, HiHealthDataType.l(), i);
            }
        }
        this.h = 0;
    }

    private List<HiHealthData> a(int i) {
        int[] iArr = new int[2];
        if (!this.b) {
            iArr[0] = 50;
            iArr[1] = 0;
            return iuz.e(this.f, i, HiHealthDataType.l(), HiHealthDataKey.d(), iArr);
        }
        iArr[0] = 50;
        iArr[1] = 1;
        return iuz.e(this.f, i, HiHealthDataType.l(), HiHealthDataKey.d(), iArr);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        SparseArray<Integer> bCF_;
        LogUtil.a("Debug_HiSyncDimenSportStat", "pullDataByVersion() begin !");
        ivc.c(1.0d, "SYNC_DIMEN_SPORT_STAT_DOWNLOAD_PERCENT_MAX");
        long a2 = HiDateUtil.a(this.f13594a);
        if (iuz.f()) {
            LogUtil.c("Debug_HiSyncDimenSportStat", "pullDataByVersion() first sync pull all stat");
            bCF_ = iuz.bCF_(1388509200000L, a2, 90);
        } else {
            LogUtil.c("Debug_HiSyncDimenSportStat", "pullDataByVersion() only pullDataByVersion recent stat");
            bCF_ = iuz.bCF_(a2 - HwExerciseConstants.TEN_DAY_SECOND, a2, 90);
        }
        bCf_(bCF_, false);
        ivc.b(this.f);
        LogUtil.a("Debug_HiSyncDimenSportStat", "pullDataByVersion() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        LogUtil.a("Debug_HiSyncDimenSportStat", "pushData() begin !");
        if (!ism.l()) {
            LogUtil.h("Debug_HiSyncDimenSportStat", "pushData() dataPrivacy switch is closed ,can not pushData right now ,push end !");
            return;
        }
        ivc.c(5.0d, "SYNC_SPORT_STAT_DOWNLOAD_PERCENT_MAX");
        int h = this.e.h(this.g);
        if (h <= 0) {
            LogUtil.h("Debug_HiSyncDimenSportStat", "pushData() no statClient get, maybe no data need to pushData");
        } else {
            b(h);
        }
        ivc.b(this.f);
        LogUtil.a("Debug_HiSyncDimenSportStat", "pushData() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
        bCg_(iuz.bCF_(j, j2, 90));
    }

    static class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<itf> f13595a;
        SparseArray<Integer> b;

        public d(itf itfVar, SparseArray<Integer> sparseArray) {
            this.b = sparseArray;
            this.f13595a = new WeakReference<>(itfVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            itf itfVar = this.f13595a.get();
            if (itfVar == null) {
                LogUtil.h("Debug_HiSyncDimenSportStat", "StatDownloadRunnable() mSyncStat = null");
                return;
            }
            if (!BaseApplication.isRunningForeground()) {
                ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(Process.myTid())), 1);
            }
            try {
                itfVar.bCi_(this.b);
            } catch (iut e) {
                ReleaseLogUtil.c("HiH_HiSyncDimenSportStat", "downloadOneStatByTime error ! e is ", e.getMessage());
            }
        }
    }

    public String toString() {
        return "--HiSyncSportStat{}";
    }
}
