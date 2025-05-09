package defpackage;

import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginhealthzone.interactors.LocationMgrCallback;
import health.compact.a.EnvironmentInfo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class mqh {

    /* renamed from: a, reason: collision with root package name */
    private static volatile mqh f15110a;
    private static final Object e = new Object();
    private HandlerThread b;
    private LocationMgrCallback d;
    private a g;
    private long i = 0;
    private boolean c = false;

    private mqh() {
        if (this.b == null) {
            HandlerThread handlerThread = new HandlerThread("LocationMgr");
            this.b = handlerThread;
            handlerThread.start();
        }
        if (this.g == null) {
            this.g = new a(this.b.getLooper());
        }
    }

    public static mqh b() {
        mqh mqhVar;
        if (f15110a == null) {
            synchronized (e) {
                if (f15110a == null) {
                    f15110a = new mqh();
                }
                mqhVar = f15110a;
            }
            return mqhVar;
        }
        return f15110a;
    }

    public void a(LocationMgrCallback locationMgrCallback) {
        if (locationMgrCallback == null) {
            LogUtil.h("LocationMgr", "location call back is null.");
            return;
        }
        LogUtil.a("LocationMgr", "getLocation enter.");
        this.d = locationMgrCallback;
        if (!this.c) {
            LogUtil.a("LocationMgr", "request location listener.");
            ktg.c().e(new ILoactionCallback() { // from class: mqg
                @Override // com.huawei.hwlocationmgr.model.ILoactionCallback
                public final void dispatchLocationChanged(Location location) {
                    mqh.this.cnl_(location);
                }
            }, "healthZoneTrackLocation");
            this.c = true;
        }
        d();
        this.g.removeMessages(1);
        this.g.sendEmptyMessageDelayed(1, 60000L);
    }

    /* synthetic */ void cnl_(Location location) {
        mqp mqpVar = new mqp();
        mqpVar.b(Double.valueOf(location.getAltitude()));
        mqpVar.c(Double.valueOf(location.getLatitude()));
        mqpVar.d(Double.valueOf(location.getLongitude()));
        mqpVar.b(AMapLocation.COORD_TYPE_WGS84);
        LogUtil.c("LocationMgr", "new location dispatch provider=", location.getProvider(), " altitude=", Double.valueOf(location.getAltitude()), " latitude=", Double.valueOf(location.getLatitude()), " longitude=", Double.valueOf(location.getLongitude()));
        this.d.onLocationChanged(mqpVar);
    }

    public void e() {
        ktg.c().a("healthZoneTrackLocation");
        a();
        this.b.quitSafely();
        this.c = false;
        synchronized (e) {
            f15110a = null;
        }
    }

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                LogUtil.h("LocationMgr", "request location timeout.");
                mqh.this.e();
            }
        }
    }

    public void d() {
        if (EnvironmentInfo.r() && System.currentTimeMillis() - this.i >= 270000) {
            this.i = System.currentTimeMillis();
            ReleaseLogUtil.e("LocationMgr", "apply power kit now");
            PowerKitManager.e().e("FAMILY_SPACE", 65535, "user-healthZone-location");
            PowerKitManager.e().d("FAMILY_SPACE", 65535, 300000L, "user-healthZone-location");
        }
    }

    public void a() {
        if (EnvironmentInfo.r()) {
            ReleaseLogUtil.e("LocationMgr", "unapply power kit now");
            this.i = 0L;
            PowerKitManager.e().e("FAMILY_SPACE", 65535, "user-healthZone-location");
        }
    }
}
