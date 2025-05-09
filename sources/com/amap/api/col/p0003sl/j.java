package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.autonavi.aps.amapapi.security.a;
import com.autonavi.aps.amapapi.storage.b;
import com.autonavi.aps.amapapi.storage.c;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class j {
    static b b;
    static iz e;
    static long g;

    /* renamed from: a, reason: collision with root package name */
    String f1209a = null;
    b c = null;
    b d = null;
    long f = 0;
    boolean h = false;
    private Context i;

    public j(Context context) {
        this.i = context.getApplicationContext();
    }

    public final void a() {
        if (this.h) {
            return;
        }
        try {
            if (this.f1209a == null) {
                this.f1209a = a.a("MD5", hr.v(this.i));
            }
            if (e == null) {
                e = new iz(this.i, iz.a((Class<? extends iy>) c.class));
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "LastLocationManager", "<init>:DBOperation");
        }
        this.h = true;
    }

    public final boolean a(AMapLocation aMapLocation, String str) {
        if (this.i != null && aMapLocation != null && i.a(aMapLocation) && aMapLocation.getLocationType() != 2 && !aMapLocation.isMock() && !aMapLocation.isFixLastLocation()) {
            b bVar = new b();
            bVar.a(aMapLocation);
            if (aMapLocation.getLocationType() == 1) {
                bVar.a((String) null);
            } else {
                bVar.a(str);
            }
            try {
                b = bVar;
                g = i.b();
                this.c = bVar;
                b bVar2 = this.d;
                if (bVar2 != null && i.a(bVar2.a(), bVar.a()) <= 500.0f) {
                    return false;
                }
                if (i.b() - this.f > OpAnalyticsConstants.H5_LOADING_DELAY) {
                    return true;
                }
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "LastLocationManager", "setLastFix");
            }
        }
        return false;
    }

    public final AMapLocation b() {
        e();
        b bVar = b;
        if (bVar != null && i.a(bVar.a())) {
            return b.a();
        }
        return null;
    }

    public final AMapLocation a(AMapLocation aMapLocation, String str, long j) {
        AMapLocation aMapLocation2;
        Throwable th;
        b bVar;
        boolean a2;
        if (aMapLocation == null || aMapLocation.getErrorCode() == 0 || aMapLocation.getLocationType() == 1 || aMapLocation.getErrorCode() == 7) {
            return aMapLocation;
        }
        try {
            e();
            bVar = b;
        } catch (Throwable th2) {
            aMapLocation2 = aMapLocation;
            th = th2;
        }
        if (bVar != null && bVar.a() != null) {
            if (TextUtils.isEmpty(str)) {
                long b2 = i.b() - b.d();
                a2 = b2 >= 0 && b2 <= j;
                aMapLocation.setTrustedLevel(3);
            } else {
                a2 = i.a(b.b(), str);
                aMapLocation.setTrustedLevel(2);
            }
            if (!a2) {
                return aMapLocation;
            }
            aMapLocation2 = b.a();
            try {
                aMapLocation2.setLocationType(9);
                aMapLocation2.setFixLastLocation(true);
                aMapLocation2.setLocationDetail(aMapLocation.getLocationDetail());
            } catch (Throwable th3) {
                th = th3;
                com.autonavi.aps.amapapi.utils.b.a(th, "LastLocationManager", "fixLastLocation");
                return aMapLocation2;
            }
            return aMapLocation2;
        }
        return aMapLocation;
    }

    public final void c() {
        try {
            d();
            this.f = 0L;
            this.h = false;
            this.c = null;
            this.d = null;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "LastLocationManager", "destroy");
        }
    }

    public final void d() {
        b bVar;
        String str;
        try {
            a();
            b bVar2 = this.c;
            if (bVar2 != null && i.a(bVar2.a()) && e != null && (bVar = this.c) != this.d && bVar.d() == 0) {
                String str2 = this.c.a().toStr();
                String b2 = this.c.b();
                this.d = this.c;
                if (TextUtils.isEmpty(str2)) {
                    str = null;
                } else {
                    String b3 = hs.b(a.a(str2.getBytes("UTF-8"), this.f1209a));
                    str = TextUtils.isEmpty(b2) ? null : hs.b(a.a(b2.getBytes("UTF-8"), this.f1209a));
                    r4 = b3;
                }
                if (TextUtils.isEmpty(r4)) {
                    return;
                }
                b bVar3 = new b();
                bVar3.b(r4);
                bVar3.a(i.b());
                bVar3.a(str);
                e.a(bVar3, "_id=1");
                this.f = i.b();
                b bVar4 = b;
                if (bVar4 != null) {
                    bVar4.a(i.b());
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "LastLocationManager", "saveLastFix");
        }
    }

    private void e() {
        if (b == null || i.b() - g > 180000) {
            b f = f();
            g = i.b();
            if (f == null || !i.a(f.a())) {
                return;
            }
            b = f;
        }
    }

    private b f() {
        Throwable th;
        b bVar;
        String str;
        byte[] b2;
        byte[] b3;
        b bVar2 = null;
        r1 = null;
        r1 = null;
        r1 = null;
        String str2 = null;
        bVar2 = null;
        if (this.i == null) {
            return null;
        }
        a();
        try {
            iz izVar = e;
            if (izVar == null) {
                return null;
            }
            List b4 = izVar.b("_id=1", b.class);
            if (b4 == null || b4.size() <= 0) {
                str = null;
            } else {
                bVar = (b) b4.get(0);
                try {
                    byte[] b5 = hs.b(bVar.c());
                    str = (b5 == null || b5.length <= 0 || (b3 = a.b(b5, this.f1209a)) == null || b3.length <= 0) ? null : new String(b3, "UTF-8");
                    byte[] b6 = hs.b(bVar.b());
                    if (b6 != null && b6.length > 0 && (b2 = a.b(b6, this.f1209a)) != null && b2.length > 0) {
                        str2 = new String(b2, "UTF-8");
                    }
                    bVar.a(str2);
                    bVar2 = bVar;
                } catch (Throwable th2) {
                    th = th2;
                    com.autonavi.aps.amapapi.utils.b.a(th, "LastLocationManager", "readLastFix");
                    return bVar;
                }
            }
            if (TextUtils.isEmpty(str)) {
                return bVar2;
            }
            AMapLocation aMapLocation = new AMapLocation("");
            com.autonavi.aps.amapapi.utils.b.a(aMapLocation, new JSONObject(str));
            if (!i.b(aMapLocation)) {
                return bVar2;
            }
            bVar2.a(aMapLocation);
            return bVar2;
        } catch (Throwable th3) {
            b bVar3 = bVar2;
            th = th3;
            bVar = bVar3;
        }
    }
}
