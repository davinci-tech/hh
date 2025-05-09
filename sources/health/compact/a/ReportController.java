package health.compact.a;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.icommon.LocalStepDataReport;
import com.huawei.health.manager.common.StandReportReceiver;

/* loaded from: classes.dex */
public class ReportController {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13135a = new Object();
    private StandReportReceiver b;
    private StepsRecord c = new StepsRecord();
    private StepsRecord e = new StepsRecord();

    public ReportController() {
        this.b = null;
        this.b = new StandReportReceiver(new StandReportReceiver.OnScreenOnListener() { // from class: health.compact.a.ReportController.5
            @Override // com.huawei.health.manager.common.StandReportReceiver.OnScreenOnListener
            public void onScreenOn() {
                ThreadPoolManager.d().execute(new Runnable() { // from class: health.compact.a.ReportController.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ReportController.this.c();
                    }
                });
            }
        });
    }

    public void a(LocalStepDataReport localStepDataReport) {
        if (localStepDataReport == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_ReportController", "add-force-data report is null.");
            return;
        }
        this.b.a(localStepDataReport);
        synchronized (f13135a) {
            if (this.e == null) {
                this.e = this.c;
            }
        }
        c();
    }

    public void d(StepsRecord stepsRecord, boolean z) {
        if (stepsRecord == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_ReportController", "record is null.");
            return;
        }
        synchronized (f13135a) {
            if (!z) {
                if (stepsRecord.equals(this.c) || stepsRecord.equals(this.e)) {
                    return;
                }
            }
            this.e = stepsRecord;
            b(z);
        }
    }

    public int c() {
        return b(false);
    }

    private int b(boolean z) {
        synchronized (f13135a) {
            StepsRecord stepsRecord = this.e;
            if (stepsRecord == null) {
                return 0;
            }
            StandReportReceiver standReportReceiver = this.b;
            if (standReportReceiver == null) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_ReportController", "FAILED_NULL_POINTER mReportUtil == null");
                return -3;
            }
            int e = standReportReceiver.e(stepsRecord, z);
            if (e == -3) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_ReportController", "FAILED_NULL_POINTER");
                this.e = null;
            } else if (e == -2) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_ReportController", "FAILED_SCREEN_OFF block report");
            } else if (e == -1) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_ReportController", "FAILED_ERROR_DATA");
                this.e = null;
            } else if (e == 0) {
                this.c = this.e;
                this.e = null;
            }
            return e;
        }
    }
}
