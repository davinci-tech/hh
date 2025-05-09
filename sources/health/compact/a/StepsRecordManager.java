package health.compact.a;

import com.huawei.health.icommon.LocalStepDataReport;
import defpackage.jdl;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class StepsRecordManager {
    private ReportController c;
    private StepsRecord e = new StepsRecord();
    private long d = 0;

    /* renamed from: a, reason: collision with root package name */
    private AtomicBoolean f13140a = new AtomicBoolean(true);

    public StepsRecordManager() {
        this.c = null;
        this.c = new ReportController();
    }

    public void d(LocalStepDataReport localStepDataReport) {
        if (localStepDataReport == null) {
            return;
        }
        synchronized (this) {
            this.c.a(localStepDataReport);
        }
    }

    public void c(long j, StepsRecord stepsRecord) {
        synchronized (this) {
            if (this.f13140a.get()) {
                d(j);
            }
            this.e.b(stepsRecord);
        }
    }

    private void d(long j) {
        long j2 = this.d;
        if (j2 == 0 || !jdl.d(j, j2)) {
            e();
            this.d = j;
        }
    }

    void e() {
        synchronized (this) {
            this.d = 0L;
            this.e.d();
        }
    }

    public void c(boolean z) {
        synchronized (this) {
            if (!this.f13140a.get() || c()) {
                StepsRecord stepsRecord = new StepsRecord();
                stepsRecord.b(this.e);
                this.c.d(stepsRecord, z);
            }
        }
    }

    public boolean c() {
        synchronized (this) {
            if (this.d != 0 && this.e.c()) {
                return true;
            }
            return false;
        }
    }

    public void b() {
        synchronized (this) {
            this.f13140a.set(false);
        }
    }

    public StepsRecord a() {
        StepsRecord stepsRecord;
        synchronized (this) {
            stepsRecord = this.e;
        }
        return stepsRecord;
    }
}
