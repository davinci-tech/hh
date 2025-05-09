package defpackage;

import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;

/* loaded from: classes5.dex */
public class kzc {
    private static volatile kzc l;
    private static final byte[] o = {-15, Byte.MIN_VALUE, Byte.MIN_VALUE, -14};
    private static final byte[] h = {-15, -123, -123, -14};
    private static final byte[] j = {-15, -125, -125, -14};
    private static final byte[] g = {-15, -127, -127, -14};
    private static final byte[] i = {-15, -126, -126, -14};
    private static final byte[] m = {-15, -96, -96, -14};

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f14712a = {-15, -95, -95, -14};
    private static final byte[] b = {-15, -80, -80, -14};
    private static final byte[] e = {-15, -93, -93, -14};
    private static final byte[] f = {-15, -91, -91, -14};
    private static final byte[] d = {-15, -76, -76, -14};
    private static final byte[] c = {-15, -94, -94, -14};
    private String k = "unknown";
    private boolean q = false;
    private boolean s = false;
    private boolean n = false;
    private boolean p = false;
    private boolean r = false;
    private boolean t = false;
    private int v = 0;
    private long x = 0;
    private ISportDataCallback y = null;
    private ISportDataCallback u = null;

    private kzc() {
    }

    public static kzc n() {
        kzc kzcVar;
        synchronized (kzc.class) {
            if (l == null) {
                l = new kzc();
                LogUtil.a("Track_IDEQ/GlobleOrConsVar", "getInstance of IndoorEquipmentConstants, instance == null, create a new one");
            } else {
                LogUtil.a("Track_IDEQ/GlobleOrConsVar", "getInstance of IndoorEquipmentConstants, instance is not null");
            }
            kzcVar = l;
        }
        return kzcVar;
    }

    public void e(ISportDataCallback iSportDataCallback) {
        if (iSportDataCallback == null) {
            return;
        }
        if (this.y != null) {
            this.y = iSportDataCallback;
            return;
        }
        this.y = iSportDataCallback;
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        if (sportDataOutputApi != null && sportDataOutputApi.isAlreadyInit()) {
            sportDataOutputApi.registerDeviceDataCallback(this.y);
        }
    }

    public void d(ISportDataCallback iSportDataCallback) {
        if (iSportDataCallback == null) {
            LogUtil.b("Track_IDEQ/GlobleOrConsVar", "wearSportDataCallback is null");
        } else {
            this.u = iSportDataCallback;
        }
    }

    public ISportDataCallback p() {
        return this.u;
    }

    public ISportDataCallback l() {
        return this.y;
    }

    public String m() {
        return this.k;
    }

    public void c(String str) {
        this.k = str;
    }

    public boolean s() {
        return this.q;
    }

    public void d(boolean z) {
        this.q = z;
    }

    public boolean q() {
        return this.s;
    }

    public void a(boolean z) {
        this.s = z;
    }

    public boolean y() {
        return this.r;
    }

    public void j(boolean z) {
        this.r = z;
    }

    public boolean t() {
        return this.n;
    }

    public void e(boolean z) {
        this.n = z;
    }

    public boolean x() {
        return this.p;
    }

    public void c(boolean z) {
        this.p = z;
    }

    public void b(boolean z) {
        this.t = z;
    }

    public void v() {
        SportLifecycle sportLifecycle = (SportLifecycle) Services.a("SportService", SportLifecycle.class);
        if (sportLifecycle != null) {
            LogUtil.a("Track_IDEQ/GlobleOrConsVar", "sportDataOutputApi != null");
            sportLifecycle.onStopSport();
        }
    }

    public static byte[] i() {
        return (byte[]) o.clone();
    }

    public static byte[] h() {
        return (byte[]) h.clone();
    }

    public static byte[] c() {
        return (byte[]) j.clone();
    }

    public static byte[] g() {
        return (byte[]) g.clone();
    }

    public static byte[] j() {
        return (byte[]) i.clone();
    }

    public static byte[] o() {
        return (byte[]) m.clone();
    }

    public static byte[] b() {
        return (byte[]) f14712a.clone();
    }

    public static byte[] d() {
        return (byte[]) e.clone();
    }

    public static byte[] f() {
        return (byte[]) f.clone();
    }

    public static byte[] a() {
        return (byte[]) d.clone();
    }

    public static byte[] e() {
        return (byte[]) c.clone();
    }

    public int r() {
        return this.v;
    }

    public void c(int i2) {
        this.v = i2;
    }

    public long k() {
        return this.x;
    }

    public void a(long j2) {
        this.x = j2;
    }
}
