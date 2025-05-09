package com.huawei.hms.network.embedded;

import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import java.io.IOException;
import java.net.URL;

/* loaded from: classes9.dex */
public abstract class i5 {
    public static final String d = "DetectEventListener";
    public static final i5 e = new a();

    /* renamed from: a, reason: collision with root package name */
    public l5 f5304a;
    public x4 b;
    public long c;

    public class a extends i5 {
    }

    public void b(w5 w5Var) {
        this.b = new x4();
        this.c = SystemClock.elapsedRealtime();
    }

    public void b(int i) {
        x4 x4Var = this.b;
        if (x4Var != null) {
            x4Var.a(i);
        }
    }

    public <T extends y4> void a(T t) {
        if (!HianalyticsHelper.getInstance().isEnableReport(ContextHolder.getResourceContext())) {
            Logger.i(d, "HianalyticsHelper report disable, and return!");
        } else if (t == null) {
            Logger.i(d, "the detect data has error! detect == null");
        } else {
            HianalyticsHelper.getInstance().getReportExecutor().execute(new b(t));
        }
    }

    public void a(w5 w5Var) {
        if (this.b != null) {
            this.b.a(SystemClock.elapsedRealtime() - this.c);
            if (w5Var == null || !(w5Var.b() instanceof w4)) {
                return;
            }
            ((v4) w5Var.b()).a(this.b);
            a(this.f5304a, (v4) w5Var.b(), 1003);
        }
    }

    public <T extends w4> void a(l5 l5Var, w4 w4Var, int i) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = w4Var;
        l5Var.a(obtain);
    }

    public void a(int i, String str) {
        x4 x4Var = this.b;
        if (x4Var != null) {
            x4Var.b(i);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                this.b.a(new URL(str).getHost());
            } catch (IOException unused) {
                Logger.w(d, "obtain host has error");
            }
        }
    }

    public class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ y4 f5305a;

        @Override // java.lang.Runnable
        public void run() {
            h5 h5Var = new h5();
            h5Var.put("domain", this.f5305a.a()).put("req_start_time", this.f5305a.b()).put("req_total_time", this.f5305a.e()).put("error_code", this.f5305a.c());
            Logger.v(i5.d, "the detect date :" + h5Var.get());
            HianalyticsHelper.getInstance().onEvent(h5Var.get(), "netdiag");
        }

        public b(y4 y4Var) {
            this.f5305a = y4Var;
        }
    }

    public void a(int i) {
        a(i, "");
    }
}
