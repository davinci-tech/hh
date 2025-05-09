package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.basichealthmodel.service.SyncService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes3.dex */
public class awj {
    private static volatile awj b;

    /* renamed from: a, reason: collision with root package name */
    private Context f260a;
    private Timer d = new Timer("HealthLife_SyncManager");

    private awj() {
        b();
        this.f260a = BaseApplication.getContext();
    }

    public static awj a() {
        awj awjVar;
        if (b == null) {
            synchronized (awj.class) {
                if (b == null) {
                    b = new awj();
                }
                awjVar = b;
            }
            return awjVar;
        }
        return b;
    }

    private void b() {
        this.d.schedule(new TimerTask() { // from class: awj.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                boolean ai = azi.ai();
                LogUtil.a("HealthLife_SyncManager", "startTimer isSupportStartService ", Boolean.valueOf(ai));
                if (ai) {
                    try {
                        Intent intent = new Intent(awj.this.f260a, (Class<?>) SyncService.class);
                        intent.putExtra("sync_type", 1);
                        intent.putExtra("sync_user_id", azi.p());
                        awj.this.f260a.startService(intent);
                    } catch (IllegalStateException | SecurityException e) {
                        LogUtil.b("HealthLife_SyncManager", "startTimer exception ", LogAnonymous.b(e));
                    }
                }
            }
        }, 3600000L, 3600000L);
    }

    public void b(String str, int i, int i2, int i3) {
        boolean ah = azi.ah();
        boolean ai = azi.ai();
        LogUtil.a("HealthLife_SyncManager", "startSync isUseNewHealthModel ", Boolean.valueOf(ah), " isSupportStartService ", Boolean.valueOf(ai), " type ", Integer.valueOf(i3), " startTime ", Integer.valueOf(i), " endTime ", Integer.valueOf(i2));
        if (ah || (!ai)) {
            return;
        }
        try {
            Intent intent = new Intent(this.f260a, (Class<?>) SyncService.class);
            intent.putExtra("sync_starttime", i);
            intent.putExtra("sync_endtime", i2);
            intent.putExtra("sync_type", i3);
            intent.putExtra("sync_user_id", str);
            this.f260a.startService(intent);
        } catch (IllegalStateException | SecurityException e) {
            LogUtil.b("HealthLife_SyncManager", "startSync exception ", LogAnonymous.b(e));
        }
    }
}
