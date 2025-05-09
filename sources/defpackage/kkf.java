package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class kkf implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private jgo f14429a;

    public kkf(jgo jgoVar) {
        this.f14429a = jgoVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        ReleaseLogUtil.e("Dfx_EventLogUploadTask", "start upload task.");
        jgo jgoVar = this.f14429a;
        if (jgoVar == null) {
            LogUtil.a("EventLogUploadTask", "mLogUpload == null");
            return;
        }
        String f = jgoVar.f();
        if (f == null) {
            return;
        }
        boolean e = cvz.e(f);
        this.f14429a.a(e);
        if (e) {
            this.f14429a.c("2019");
        } else {
            this.f14429a.c("1019");
        }
        ReleaseLogUtil.e("Dfx_EventLogUploadTask", "isHonorDeviceLog:", Boolean.valueOf(e));
        kkh.c(this.f14429a);
    }
}
