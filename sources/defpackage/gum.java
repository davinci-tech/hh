package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.healthcloud.plugintrack.manager.inteface.ITrackPointDataUpdater;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gum {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12945a = new Object();
    private List<gyc> b;
    private ExtendHandler d;
    private ScheduledExecutorService h;
    private List<gyc> i;
    private float e = 0.0f;
    private float f = 0.0f;
    private Context c = null;
    private ITrackPointDataUpdater g = null;

    public void d() {
        List<gyc> d = gwk.d(this.c, "track_point_data.txt");
        this.i = d;
        if (d == null) {
            this.i = new ArrayList(16);
        } else {
            this.e = 0.0f;
            this.f = 0.0f;
            for (gyc gycVar : d) {
                this.e += gycVar.a();
                this.f += gycVar.d();
            }
            this.e /= 1000.0f;
        }
        LogUtil.a("Track_TrackPointDataUtils", "recoveryPointData ", Integer.valueOf(this.i.size()));
    }

    public List<gyc> c() {
        return this.i;
    }

    public void d(Context context, boolean z) {
        LogUtil.a("Track_TrackPointDataUtils", "initTrackPointDataUtils ", this);
        if (this.d == null) {
            this.d = HandlerCenter.yt_(new Handler.Callback() { // from class: gum.3
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    return false;
                }
            }, "TrackPointDataThread");
        }
        if (context != null) {
            this.c = context;
        } else {
            this.c = BaseApplication.getContext();
        }
        if (z) {
            d();
        } else {
            this.i = new ArrayList(16);
        }
        this.b = new ArrayList(16);
        a();
    }

    public void e() {
        ITrackPointDataUpdater iTrackPointDataUpdater = this.g;
        if (iTrackPointDataUpdater != null) {
            a(iTrackPointDataUpdater.getDistance(), this.g.getActiveCalorie(), System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1L));
        } else {
            LogUtil.h("Track_TrackPointDataUtils", "stopTrackPointDataUtils mUpdater is null");
        }
        f();
        this.g = null;
    }

    public void b() {
        LogUtil.a("Track_TrackPointDataUtils", "destroyTrackPointDataUtils");
        ExtendHandler extendHandler = this.d;
        if (extendHandler != null) {
            extendHandler.quit(false);
            this.d = null;
        }
        gwo.e(this.c, "track_point_data.txt");
    }

    public void a(float f, float f2, long j) {
        float f3 = this.e;
        float f4 = this.f;
        long millis = (j / TimeUnit.MINUTES.toMillis(1L)) * TimeUnit.MINUTES.toMillis(1L);
        gyc gycVar = new gyc();
        gycVar.e(millis - TimeUnit.MINUTES.toMillis(1L));
        gycVar.d(millis);
        gycVar.d(f - f4);
        gycVar.a((f2 - f3) * 1000.0f);
        this.i.add(gycVar);
        this.b.add(gycVar);
        this.e = f2;
        this.f = f;
        i();
    }

    private void i() {
        ExtendHandler extendHandler = this.d;
        if (extendHandler == null) {
            LogUtil.h("Track_TrackPointDataUtils", "mExtendHandler is null");
        } else {
            extendHandler.postTask(new Runnable() { // from class: gum.4
                @Override // java.lang.Runnable
                public void run() {
                    if (gwo.b(gum.this.c, "track_point_data.txt", (List<gyc>) gum.this.b)) {
                        gum.this.b.clear();
                    }
                }
            });
        }
    }

    public void d(ITrackPointDataUpdater iTrackPointDataUpdater) {
        if (iTrackPointDataUpdater != null) {
            this.g = iTrackPointDataUpdater;
        }
    }

    private void a() {
        LogUtil.a("Track_TrackPointDataUtils", "registerMinuteChangedReceiver");
        synchronized (f12945a) {
            ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
            this.h = newScheduledThreadPool;
            newScheduledThreadPool.scheduleAtFixedRate(new b(), 0L, 1L, TimeUnit.SECONDS);
        }
    }

    private void f() {
        LogUtil.a("Track_TrackPointDataUtils", "unregisterMinuteChangedReceiver");
        synchronized (f12945a) {
            ScheduledExecutorService scheduledExecutorService = this.h;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdown();
                this.h = null;
            }
        }
    }

    class b implements Runnable {
        private long c = System.currentTimeMillis() / TimeUnit.MINUTES.toMillis(1);

        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            long currentTimeMillis = System.currentTimeMillis() / TimeUnit.MINUTES.toMillis(1L);
            long j = this.c;
            if (j != currentTimeMillis) {
                LogUtil.a("Track_TrackPointDataUtils", "Time Changed old ", Long.valueOf(j), " new ", Long.valueOf(currentTimeMillis));
                if (gum.this.g != null) {
                    gum.this.a(gum.this.g.getDistance(), gum.this.g.getActiveCalorie(), System.currentTimeMillis());
                } else {
                    LogUtil.h("Track_TrackPointDataUtils", "mUpdater is null");
                }
                this.c = currentTimeMillis;
            }
        }
    }

    public void a(float f) {
        if (f <= 0.0f) {
            LogUtil.h("Track_TrackPointDataUtils", "changePointDataByRatio the ratio is illegal! ratio = ", Float.valueOf(f));
            return;
        }
        List<gyc> list = this.i;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (gyc gycVar : this.i) {
            gycVar.d(gycVar.d() * f);
            gycVar.a(gycVar.a() * f);
        }
    }
}
