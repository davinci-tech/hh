package health.compact.a;

import android.content.Context;
import com.huawei.health.monitor.proc.prompt.PromptRecord;
import defpackage.gnj;
import defpackage.koq;
import defpackage.lcu;
import java.util.List;

/* loaded from: classes.dex */
public class ProcessAliveMonitor {
    private static ProcessAliveMonitor c;

    /* renamed from: a, reason: collision with root package name */
    private Context f13134a;
    private final Object b;
    private PromptRecord d;

    private ProcessAliveMonitor(Context context) {
        Object obj = new Object();
        this.b = obj;
        this.f13134a = null;
        this.d = null;
        synchronized (obj) {
            this.f13134a = context;
            this.d = new PromptRecord(context);
            long b = AliveStatusMgr.b(this.f13134a);
            if (b < 300000) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_ProcessAliveMonitor", "isDeathLongTime false,working correct");
                this.d.a(false);
                a();
                return;
            }
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d("Step_ProcessAliveMonitor", "think process unexpect death long time, deathMills = ", Long.valueOf(b));
            com.huawei.hwlogsmodel.LogUtil.h("Step_ProcessAliveMonitor", "death long time,working unusuall!!!");
            boolean b2 = lcu.b();
            com.huawei.hwlogsmodel.LogUtil.a("Step_ProcessAliveMonitor", "isKeepAlive:", Boolean.valueOf(b2));
            if (b2) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_ProcessAliveMonitor", "isDeathLongTime(notEmui) true,need prompt");
                this.d.a(true);
                a();
                return;
            }
            List<String> c2 = ThirdMobileManager.c(this.f13134a);
            com.huawei.hwlogsmodel.LogUtil.a("Step_ProcessAliveMonitor", "hasThirdMobileMgr(isEmui) :", Boolean.valueOf(koq.b(c2)));
            if (koq.b(c2)) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_ProcessAliveMonitor", "isDeathLongTime(Emui) true and hasThirdMobileMgr(Emui) false,not need prompt");
                this.d.a(false);
                a();
                gnj.d();
                return;
            }
            gnj.c(c2);
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_ProcessAliveMonitor", "isDeathLongTime(Emui) true and hasThirdMobileMgr(Emui) true,Can Prompt Tips!!!");
            this.d.a(true);
            a();
        }
    }

    public static ProcessAliveMonitor a(Context context) {
        synchronized (ProcessAliveMonitor.class) {
            if (context == null) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_ProcessAliveMonitor", "getInstance context null,return null");
                return null;
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            } else {
                com.huawei.hwlogsmodel.LogUtil.h("Step_ProcessAliveMonitor", "appContext null,use context(not null)");
            }
            if (c == null) {
                c = new ProcessAliveMonitor(context);
            }
            return c;
        }
    }

    public final void a() {
        synchronized (this.b) {
            AliveStatusMgr.c(this.f13134a);
        }
    }

    public boolean d() {
        boolean b;
        synchronized (this.b) {
            b = this.d.b();
        }
        return b;
    }

    public void e() {
        synchronized (this.b) {
            this.d.a();
        }
    }

    public void b() {
        synchronized (this.b) {
            this.d.d();
            h();
        }
    }

    public void c() {
        synchronized (this.b) {
            this.d.c();
            h();
        }
    }

    private void h() {
        AliveStatusMgr.e(this.f13134a);
    }
}
