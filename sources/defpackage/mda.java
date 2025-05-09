package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class mda {
    public static void a(Context context) {
        if (Utils.o()) {
            b(context);
        } else {
            e(context);
        }
    }

    private static void e(final Context context) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mda.4
            @Override // java.lang.Runnable
            public void run() {
                mcz d = meh.c(context).d(1, new HashMap(16));
                TotalRecord totalRecord = d instanceof TotalRecord ? (TotalRecord) d : null;
                if (totalRecord == null) {
                    LogUtil.h("PLGACHIEVE_AchieveReportMgr", "computeDomesticData no TotalRecordDB.");
                } else {
                    Context context2 = context;
                    mdc.e(context2, mcv.d(context2).getAdapter(), totalRecord);
                }
            }
        });
    }

    private static void b(final Context context) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mda.1
            @Override // java.lang.Runnable
            public void run() {
                mcz d = meh.c(context).d(1, new HashMap(16));
                TotalRecord totalRecord = d instanceof TotalRecord ? (TotalRecord) d : null;
                mcz d2 = meh.c(context).d(2, new HashMap(16));
                SingleDayRecord singleDayRecord = d2 instanceof SingleDayRecord ? (SingleDayRecord) d2 : null;
                meh c = meh.c(context);
                if (totalRecord == null) {
                    totalRecord = mda.a();
                    c.c(totalRecord);
                }
                if (singleDayRecord == null) {
                    singleDayRecord = mda.e();
                    c.c(singleDayRecord);
                }
                mda.d(totalRecord, singleDayRecord);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(TotalRecord totalRecord, SingleDayRecord singleDayRecord) {
        LogUtil.a("PLGACHIEVE_AchieveReportMgr", "getLocalAchieveData enter.");
        Context e = BaseApplication.e();
        PluginAchieveAdapter adapter = mcv.d(e).getAdapter();
        mdc.e(e, adapter, totalRecord);
        mdc.d(e, adapter, totalRecord);
        mdb.a(e, adapter, singleDayRecord);
        mdb.b(e, adapter, singleDayRecord);
        mdb.d(e, adapter, singleDayRecord);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TotalRecord a() {
        LogUtil.a("PLGACHIEVE_AchieveReportMgr", "Enter initTotalRecord");
        TotalRecord totalRecord = new TotalRecord();
        totalRecord.saveAccumulatedHealthTime("");
        totalRecord.saveAccumulatedCycleDistance("");
        totalRecord.saveAccumulatedRunDistance("");
        totalRecord.saveAccumulatedWalkDistance("");
        return totalRecord;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SingleDayRecord e() {
        LogUtil.a("PLGACHIEVE_AchieveReportMgr", "Enter initSingleRecord");
        SingleDayRecord singleDayRecord = new SingleDayRecord();
        singleDayRecord.saveBestRunDistance("");
        singleDayRecord.saveBestRunPace("");
        singleDayRecord.saveBestStepDay("");
        singleDayRecord.saveBestCycleDistance("");
        singleDayRecord.saveBestCycleSpeed("");
        singleDayRecord.saveBestWalkDistance("");
        singleDayRecord.saveBestRun3KMPace("");
        singleDayRecord.saveBestRun5KMPace("");
        singleDayRecord.saveBestRun10KMPace("");
        singleDayRecord.saveBestRunHMPace("");
        singleDayRecord.saveBestRunFMPace("");
        singleDayRecord.saveBestRopeContinuousCount("");
        singleDayRecord.saveBestRopeDuration("");
        singleDayRecord.saveBestRopeSingCount("");
        singleDayRecord.saveBestRopeSpeed("");
        return singleDayRecord;
    }
}
