package defpackage;

import android.content.Context;
import android.os.Process;
import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hihealthservice.sync.dataswitch.BloodOxygenStatSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthStatReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthStatReq;
import com.huawei.hwcloudmodel.model.unite.BloodOxygenSaturationTotal;
import com.huawei.hwcloudmodel.model.unite.GetHealthStatRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes7.dex */
public class itg implements HiSyncBase {
    private static final int[] b = {47201, 47202, 47203, 47204};
    private static final String[] c = {"maxBloodOxygenSaturation", "minBloodOxygenSaturation", "avgBloodOxygenSaturation", "lastBloodOxygenSaturation"};

    /* renamed from: a, reason: collision with root package name */
    private BloodOxygenStatSwitch f13596a;
    private int d;
    private Context e;
    private jbs f;
    private HiSyncOption g;
    private iis h;
    private int j;
    private ijr l;
    private int k = 0;
    private int n = 0;
    private boolean i = false;

    public itg(Context context, HiSyncOption hiSyncOption, int i) {
        LogUtil.c("Debug_HiSyncHealthSensitiveStat", "HiSyncHealthSensitiveStat create");
        this.e = context.getApplicationContext();
        this.g = hiSyncOption;
        this.j = i;
        e();
    }

    private void e() {
        this.i = iuz.i();
        this.f = jbs.a(this.e);
        this.l = ijr.d();
        this.d = HiDateUtil.c(System.currentTimeMillis());
        this.h = iis.d();
        this.f13596a = new BloodOxygenStatSwitch(this.e);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        SparseArray<Integer> bCF_;
        ivc.c(1.0d, "SYNC_SENSITIVE_STAT_DOWNLOAD_PERCENT_MAX");
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveStat", "Can not open db ", "hihealth_sensitive.db");
            ivc.b(this.e);
            return;
        }
        ReleaseLogUtil.e("HiH_HiSyncHlthSensSt", "pullDataByVersion() begin!");
        long a2 = HiDateUtil.a(this.d);
        if (iuz.f()) {
            LogUtil.c("Debug_HiSyncHealthSensitiveStat", "pullDataByVersion() first sync pull all stat!");
            bCF_ = iuz.bCF_(1388509200000L, a2, 90);
        } else {
            LogUtil.c("Debug_HiSyncHealthSensitiveStat", "pullDataByVersion() only pullDataByVersion recent days stat");
            bCF_ = iuz.bCF_(a2 - HwExerciseConstants.TEN_DAY_SECOND, a2, 90);
        }
        bCk_(bCF_, false);
        ivc.b(this.e);
        ReleaseLogUtil.e("HiH_HiSyncHlthSensSt", "pullDataByVersion() end!");
    }

    private void bCk_(SparseArray<Integer> sparseArray, boolean z) throws iut {
        if (z) {
            LogUtil.c("Debug_HiSyncHealthSensitiveStat", "downloadAllStat too much need to download ,start a thread! downloadDaysMap is ", sparseArray);
            bCl_(sparseArray);
        } else {
            LogUtil.c("Debug_HiSyncHealthSensitiveStat", "downloadAllStat do not need to start a thread! downloadDaysMap is ", sparseArray);
            bCm_(sparseArray);
        }
    }

    private void bCm_(SparseArray<Integer> sparseArray) throws iut {
        LogUtil.c("Debug_HiSyncHealthSensitiveStat", "downloadAllStatByTimeSync downloadDaysMap is ", sparseArray);
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("Debug_HiSyncHealthSensitiveStat", "downloadAllStatByTimeSync() downloadDaysMap is null, stop pullStat");
        } else {
            bCn_(sparseArray);
        }
    }

    private void bCl_(SparseArray<Integer> sparseArray) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("Debug_HiSyncHealthSensitiveStat", "downloadAllStatByTime() downloadDaysMap is null, stop pullDataByVersion");
        } else {
            new Thread(new b(sparseArray)).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bCn_(SparseArray<Integer> sparseArray) throws iut {
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            int keyAt = sparseArray.keyAt(size);
            e(keyAt, sparseArray.get(keyAt).intValue());
        }
    }

    private void e(int i, int i2) throws iut {
        if (i > i2 || i <= 0) {
            LogUtil.h("Debug_HiSyncHealthSensitiveStat", "downloadHealthStatByTime the time is not right");
            return;
        }
        LogUtil.a("Debug_HiSyncHealthSensitiveStat", "downloadHealthStatByTime startTime is ", Integer.valueOf(i), " , endDay is ", Integer.valueOf(i2));
        GetHealthStatRsp c2 = c(i, i2);
        if (ius.a(c2, false)) {
            this.l.a(this.j, this.g.getSyncDataType(), i2, 0L);
            d(c2.getBloodOxygenSaturationTotal());
        }
    }

    private GetHealthStatRsp c(int i, int i2) {
        GetHealthStatReq getHealthStatReq = new GetHealthStatReq();
        getHealthStatReq.setStartTime(i);
        getHealthStatReq.setEndTime(i2);
        getHealthStatReq.setDataSource(2);
        HashSet hashSet = new HashSet(1);
        hashSet.add(16);
        getHealthStatReq.setTypes(hashSet);
        LogUtil.a("HiH_HiSyncHlthSensSt", "getHealthStatRsp req is ", HiJsonUtil.e(getHealthStatReq));
        return this.f.a(getHealthStatReq);
    }

    private void d(List<BloodOxygenSaturationTotal> list) {
        int transferHealthStatData;
        if (list == null) {
            LogUtil.h("Debug_HiSyncHealthSensitiveStat", "downloadHealthStatByTime bloodOxygenStats is null");
            return;
        }
        List<igo> b2 = this.f13596a.b(list, this.j);
        if (iwe.d(this.e, "isInsertBatchStats", this.j, false) && !iuz.b(b2)) {
            transferHealthStatData = isf.a(this.e).insertBatchDayStatTable(b2);
        } else {
            transferHealthStatData = isf.a(this.e).transferHealthStatData(b2);
        }
        if (transferHealthStatData == 0) {
            ivg.c().a(18, "sync download", new ikv(this.e.getPackageName()));
        }
    }

    private boolean c(List<HiHealthData> list) throws iut {
        if (!this.i) {
            int i = this.n + 1;
            this.n = i;
            iuz.e(i, this.g.getSyncManual());
        } else {
            int i2 = this.n + 1;
            this.n = i2;
            if (i2 > 3) {
                this.k += 2;
                return false;
            }
        }
        List<BloodOxygenSaturationTotal> d = this.f13596a.d(list);
        if (d.isEmpty()) {
            this.k++;
            return false;
        }
        AddHealthStatReq addHealthStatReq = new AddHealthStatReq();
        addHealthStatReq.setBloodOxygenSaturationTotal(d);
        addHealthStatReq.setTimeZone(list.get(0).getTimeZone());
        while (true) {
            if (this.k < 2) {
                if (!ius.a(this.f.d(addHealthStatReq), false)) {
                    this.k++;
                } else {
                    ReleaseLogUtil.e("HiH_HiSyncHlthSensSt", "addBloodOxygenStat success ! uploadCount is ", Integer.valueOf(this.n));
                    Object[] objArr = new Object[2];
                    objArr[0] = "stat is ";
                    objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(d) : null;
                    LogUtil.c("Debug_HiSyncHealthSensitiveStat", objArr);
                    return true;
                }
            } else {
                ReleaseLogUtil.e("HiH_HiSyncHlthSensSt", "addBloodOxygenStat failed ! uploadCount is ", Integer.valueOf(this.n));
                Object[] objArr2 = new Object[2];
                objArr2[0] = "stat is ";
                objArr2[1] = CommonUtil.aq() ? HiJsonUtil.e(d) : null;
                LogUtil.c("Debug_HiSyncHealthSensitiveStat", objArr2);
                return false;
            }
        }
    }

    private void b(int i) throws iut {
        int[] iArr;
        List<HiHealthData> c2;
        while (this.k < 2 && (c2 = c(i, (iArr = b), c)) != null && !c2.isEmpty()) {
            if (c(c2)) {
                iuz.d(this.e, c2, iArr, i);
            }
        }
        this.k = 0;
    }

    private List<HiHealthData> c(int i, int[] iArr, String[] strArr) {
        return iuz.e(this.e, i, iArr, strArr, new int[]{50, this.i});
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveStat", "Can not open db ", "hihealth_sensitive.db");
            return;
        }
        ReleaseLogUtil.e("HiH_HiSyncHlthSensSt", "pushData() begin!");
        if (!ism.m()) {
            ReleaseLogUtil.d("HiH_HiSyncHlthSensSt", "pushData() dataPrivacy switch is closed, push end!");
            return;
        }
        int h = this.h.h(this.j);
        if (h <= 0) {
            LogUtil.h("Debug_HiSyncHealthSensitiveStat", "pushData() no statClient get, maybe no data need to push ,push end !");
        } else {
            b(h);
        }
        ReleaseLogUtil.e("HiH_HiSyncHlthSensSt", "pushData() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveStat", "Can not open db ", "hihealth_sensitive.db");
        } else {
            bCk_(iuz.bCF_(j, j2, 90), false);
        }
    }

    static class b implements Runnable {
        private WeakReference<itg> c;
        SparseArray<Integer> e;

        private b(itg itgVar, SparseArray<Integer> sparseArray) {
            this.e = sparseArray;
            this.c = new WeakReference<>(itgVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            itg itgVar = this.c.get();
            if (itgVar == null) {
                LogUtil.h("Debug_HiSyncHealthSensitiveStat", "StatDownloadRunnable() mSyncStat = null");
                return;
            }
            if (!BaseApplication.isRunningForeground()) {
                ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(Process.myTid())), 1);
            }
            try {
                itgVar.bCn_(this.e);
            } catch (iut e) {
                ReleaseLogUtil.c("HiH_HiSyncHlthSensSt", "downloadHealthSensitiveStatByTime error ! e is ", e.getMessage());
            }
        }
    }

    public String toString() {
        return "--HiSyncHealthSensitiveStat {}";
    }
}
