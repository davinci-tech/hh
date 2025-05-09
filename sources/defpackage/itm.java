package defpackage;

import android.content.Context;
import android.os.Process;
import android.util.SparseArray;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealthservice.sync.dataswitch.CoreSleepSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthStatReq;
import com.huawei.hwcloudmodel.model.unite.GetHealthStatRsp;
import com.huawei.hwcloudmodel.model.unite.ProfessionalSleepTotal;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class itm implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private CoreSleepSwitch f13602a;
    private Context b;
    private HiSyncOption c;
    private jbs d;
    private int e;
    private ijr i;
    private int j;

    public itm(Context context, HiSyncOption hiSyncOption, int i) {
        LogUtil.c("HiH_HiSyncSleepStat", "HiSyncSleepStat create");
        this.b = context.getApplicationContext();
        this.c = hiSyncOption;
        this.j = i;
        b();
    }

    private void b() {
        this.d = jbs.a(this.b);
        this.i = ijr.d();
        this.e = HiDateUtil.c(System.currentTimeMillis());
        this.f13602a = new CoreSleepSwitch(this.b);
    }

    private void bCv_(SparseArray<Integer> sparseArray, boolean z) throws iut {
        if (z) {
            LogUtil.c("HiH_HiSyncSleepStat", " downloadAllStat too much need to download, start a thread! downloadDaysMap is ", sparseArray);
            bCw_(sparseArray);
        } else {
            LogUtil.c("HiH_HiSyncSleepStat", " downloadAllStat don't need to start a thread! downloadDaysMap = ", sparseArray);
            bCx_(sparseArray);
        }
    }

    private void bCx_(SparseArray<Integer> sparseArray) throws iut {
        LogUtil.c("HiH_HiSyncSleepStat", " downloadAllStatByTimeSync() downloadDaysMap = ", sparseArray);
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("HiH_HiSyncSleepStat", " downloadAllStatByTimeSync() downloadDaysMap is null or empty, stop pullStat");
        } else {
            bCy_(sparseArray);
        }
    }

    private void bCw_(SparseArray<Integer> sparseArray) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("HiH_HiSyncSleepStat", " downloadAllStatByTime() downloadDaysMap is null or empty, stop pullDataByVersion");
        } else {
            new Thread(new b(sparseArray)).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bCy_(SparseArray<Integer> sparseArray) throws iut {
        LogUtil.c("HiH_HiSyncSleepStat", " performDownloadByTime ");
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            int keyAt = sparseArray.keyAt(size);
            c(keyAt, sparseArray.get(keyAt).intValue());
        }
    }

    private void c(int i, int i2) throws iut {
        if (i > i2 || i <= 0) {
            LogUtil.h("HiH_HiSyncSleepStat", "downloadCoreSleepStatByTime the time is not right");
            return;
        }
        ReleaseLogUtil.e("HiH_HiSyncSleepStat", "downloadCoreSleepStatByTime startTime is ", Integer.valueOf(i), " , endDay is ", Integer.valueOf(i2));
        GetHealthStatRsp d = d(i, i2);
        if (ius.a(d, false)) {
            List<ProfessionalSleepTotal> professionalSleepTotal = d.getProfessionalSleepTotal();
            this.i.a(this.j, 10009, i2, 0L);
            if (HiCommonUtil.d(professionalSleepTotal)) {
                LogUtil.h("HiH_HiSyncSleepStat", "downloadSleepStatByTime coreSleepStats is null or empty");
            } else {
                e(professionalSleepTotal);
            }
        }
    }

    private GetHealthStatRsp d(int i, int i2) {
        GetHealthStatReq getHealthStatReq = new GetHealthStatReq();
        getHealthStatReq.setStartTime(i);
        getHealthStatReq.setEndTime(i2);
        getHealthStatReq.setDataSource(2);
        getHealthStatReq.setDeviceCode(0L);
        HashSet hashSet = new HashSet(1);
        hashSet.add(9);
        getHealthStatReq.setTypes(hashSet);
        return this.d.a(getHealthStatReq);
    }

    private void e(List<ProfessionalSleepTotal> list) {
        if (c(this.f13602a.e(list, this.j)) == 0) {
            ivg.c().a(3, "sync download", new ikv(this.b.getPackageName()));
        }
    }

    private int c(List<igo> list) {
        igo igoVar;
        if (list == null || list.isEmpty()) {
            return 7;
        }
        Iterator<igo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                igoVar = null;
                break;
            }
            igoVar = it.next();
            if (igoVar.f() == 44105) {
                break;
            }
        }
        if (igoVar == null) {
            ReleaseLogUtil.e("HiH_HiSyncSleepStat", "saveCoreStatToDB Duration error");
            return 7;
        }
        igo d = ijd.c(this.b).d(igoVar.e(), igoVar.f(), igoVar.d());
        if (igoVar != null && d != null) {
            Iterator<igo> it2 = list.iterator();
            while (it2.hasNext()) {
                igo next = it2.next();
                if (HiHealthDataType.i(next.f())) {
                    it2.remove();
                } else if (ijd.c(this.b).d(next.e(), next.f(), next.d()) != null && igoVar.l() < d.l()) {
                    ReleaseLogUtil.e("HiH_HiSyncSleepStat", Integer.valueOf(next.e()), ",Duration=", Double.valueOf(igoVar.l()), Constants.LINK, Double.valueOf(d.l()));
                    it2.remove();
                }
            }
        }
        igo d2 = ijd.c(this.b).d(igoVar.e(), 44108, igoVar.d());
        if (igoVar != null && d == null && d2 != null) {
            Iterator<igo> it3 = list.iterator();
            while (it3.hasNext()) {
                if (44108 == it3.next().f()) {
                    it3.remove();
                }
            }
        }
        return isf.a(this.b).transferHealthStatData(list);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        SparseArray<Integer> bCF_;
        ReleaseLogUtil.e("HiH_HiSyncSleepStat", "pullDataByVersion() begin !");
        ivc.c(1.0d, "SYNC_SLEEP_STAT_DOWNLOAD_PERCENT_MAX");
        long a2 = HiDateUtil.a(this.e);
        if (iuz.f()) {
            LogUtil.c("HiH_HiSyncSleepStat", "pullDataByVersion() first sync pull all stat!");
            bCF_ = iuz.bCF_(1388509200000L, a2, 90);
        } else {
            igq c = this.i.c(this.j, 0L, 9);
            if (c == null || c.a() == 0 || c.a() > a2 - HwExerciseConstants.TEN_DAY_SECOND) {
                bCF_ = iuz.bCF_(a2 - HwExerciseConstants.TEN_DAY_SECOND, a2, 90);
            } else {
                bCF_ = iuz.bCF_(c.a(), a2, 90);
            }
        }
        bCv_(bCF_, false);
        ivc.b(this.b);
        ReleaseLogUtil.e("HiH_HiSyncSleepStat", "pullDataByVersion() end!");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        itn.b(this.b).c(this.j, this.c, this.f13602a);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
        bCv_(iuz.bCF_(j, j2, 90), false);
    }

    static class b implements Runnable {
        SparseArray<Integer> c;
        private WeakReference<itm> e;

        private b(itm itmVar, SparseArray<Integer> sparseArray) {
            this.c = sparseArray;
            this.e = new WeakReference<>(itmVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            itm itmVar = this.e.get();
            if (itmVar == null) {
                LogUtil.h("HiH_HiSyncSleepStat", "StatDownloadRunnable() mSyncStat = null");
                return;
            }
            if (!BaseApplication.isRunningForeground()) {
                ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(Process.myTid())), 1);
            }
            try {
                itmVar.bCy_(this.c);
            } catch (iut e) {
                ReleaseLogUtil.c("HiH_HiSyncSleepStat", "downloadSleepStatByTime error ! e is ", e.getMessage());
            }
        }
    }

    public String toString() {
        return "--HiSyncSleepStat{}";
    }
}
