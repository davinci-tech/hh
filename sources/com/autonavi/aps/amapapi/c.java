package com.autonavi.aps.amapapi;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.view.MotionEventCompat;
import com.amap.api.col.p0003sl.hn;
import com.amap.api.col.p0003sl.hr;
import com.amap.api.col.p0003sl.hs;
import com.amap.api.col.p0003sl.ia;
import com.amap.api.col.p0003sl.it;
import com.amap.api.col.p0003sl.iv;
import com.amap.api.col.p0003sl.jj;
import com.amap.api.col.p0003sl.jq;
import com.amap.api.col.p0003sl.jt;
import com.amap.api.col.p0003sl.kb;
import com.amap.api.col.p0003sl.kd;
import com.amap.api.col.p0003sl.ke;
import com.amap.api.col.p0003sl.kk;
import com.amap.api.col.p0003sl.ku;
import com.amap.api.col.p0003sl.kw;
import com.amap.api.col.p0003sl.la;
import com.amap.api.col.p0003sl.lb;
import com.amap.api.col.p0003sl.lj;
import com.amap.api.col.p0003sl.ll;
import com.amap.api.col.p0003sl.mf;
import com.amap.api.col.p0003sl.mi;
import com.amap.api.col.p0003sl.mo;
import com.amap.api.col.p0003sl.mp;
import com.amap.api.col.p0003sl.mt;
import com.amap.api.col.p0003sl.mu;
import com.amap.api.col.p0003sl.mv;
import com.autonavi.aps.amapapi.restruct.e;
import com.autonavi.aps.amapapi.restruct.i;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.crypto.KeyGenerator;

/* loaded from: classes2.dex */
public final class c implements mv {
    private static long k;

    /* renamed from: a, reason: collision with root package name */
    Context f1615a;
    mf d;
    kd e;
    private Handler g;
    private LocationManager h;
    private a i;
    private ArrayList<ll> f = new ArrayList<>();
    i b = null;
    e c = null;
    private volatile boolean j = false;

    static /* synthetic */ byte[] f() {
        return a(128);
    }

    c(Context context) {
        this.f1615a = null;
        this.f1615a = context;
        kd kdVar = new kd();
        this.e = kdVar;
        kk.a(this.f1615a, kdVar, it.k, 100, 1024000, "0");
        kd kdVar2 = this.e;
        int i = com.autonavi.aps.amapapi.utils.a.g;
        boolean z = com.autonavi.aps.amapapi.utils.a.e;
        int i2 = com.autonavi.aps.amapapi.utils.a.f;
        kdVar2.f = new kw(context, i, "kKey", new ku(context, z, i2, i2 * 10, "carrierLocKey"));
        this.e.e = new jj();
    }

    static final class a implements LocationListener {

        /* renamed from: a, reason: collision with root package name */
        private c f1617a;

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }

        a(c cVar) {
            this.f1617a = cVar;
        }

        final void a(c cVar) {
            this.f1617a = cVar;
        }

        final void a() {
            this.f1617a = null;
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            try {
                c cVar = this.f1617a;
                if (cVar != null) {
                    cVar.a(location);
                }
            } catch (Throwable unused) {
            }
        }
    }

    final void a() {
        LocationManager locationManager;
        if (com.autonavi.aps.amapapi.utils.i.m(this.f1615a)) {
            return;
        }
        try {
            a aVar = this.i;
            if (aVar != null && (locationManager = this.h) != null) {
                locationManager.removeUpdates(aVar);
            }
            a aVar2 = this.i;
            if (aVar2 != null) {
                aVar2.a();
            }
            if (this.j) {
                g();
                this.b.a((c) null);
                this.c.a((c) null);
                this.c = null;
                this.b = null;
                this.g = null;
                this.j = false;
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "clm", "stc");
        }
    }

    public final void a(e eVar, i iVar, Handler handler) {
        LocationManager locationManager;
        if (this.j || eVar == null || iVar == null || handler == null || com.autonavi.aps.amapapi.utils.i.m(this.f1615a)) {
            return;
        }
        this.j = true;
        this.c = eVar;
        this.b = iVar;
        iVar.a(this);
        this.c.a(this);
        this.g = handler;
        try {
            if (this.h == null && handler != null) {
                this.h = (LocationManager) this.f1615a.getSystemService("location");
            }
            if (this.i == null) {
                this.i = new a(this);
            }
            this.i.a(this);
            a aVar = this.i;
            if (aVar != null && (locationManager = this.h) != null) {
                locationManager.requestLocationUpdates("passive", 1000L, -1.0f, aVar);
            }
            if (this.d == null) {
                mf mfVar = new mf("6.1.0", hn.f(this.f1615a), "S128DF1572465B890OE3F7A13167KLEI", hn.c(this.f1615a), this);
                this.d = mfVar;
                mfVar.a(hr.v(this.f1615a)).b(hr.h(this.f1615a)).c(hr.a(this.f1615a)).d(hr.g(this.f1615a)).e(hr.y(this.f1615a)).f(hr.i(this.f1615a)).g(Build.MODEL).h(Build.MANUFACTURER).i(Build.BRAND).a(Build.VERSION.SDK_INT).j(Build.VERSION.RELEASE).a(mp.a(hr.k(this.f1615a))).k(hr.k(this.f1615a));
                mf.b();
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "col", "init");
        }
    }

    public final void b() {
        try {
            Handler handler = this.g;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.autonavi.aps.amapapi.c.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            if (c.this.d == null || c.this.b == null) {
                                return;
                            }
                            mf.b(c.this.b.a());
                        } catch (Throwable th) {
                            com.autonavi.aps.amapapi.utils.b.a(th, "cl", "upwr");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "cl", "upw");
        }
    }

    public final void c() {
        e eVar;
        try {
            if (this.d == null || (eVar = this.c) == null) {
                return;
            }
            mf.a(eVar.a());
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "cl", "upc");
        }
    }

    final class b extends lb {
        private int b;
        private Location c;

        b(int i) {
            this.b = i;
        }

        b(c cVar, Location location) {
            this(1);
            this.c = location;
        }

        private void a() {
            try {
                if (this.c == null || !c.this.j || com.autonavi.aps.amapapi.utils.i.m(c.this.f1615a)) {
                    return;
                }
                Bundle extras = this.c.getExtras();
                int i = extras != null ? extras.getInt("satellites") : 0;
                if (com.autonavi.aps.amapapi.utils.i.a(this.c, i)) {
                    return;
                }
                if (c.this.b != null && !c.this.b.s) {
                    c.this.b.f();
                }
                ArrayList<mp> a2 = c.this.b.a();
                List<mi> a3 = c.this.c.a();
                lj.a aVar = new lj.a();
                mo moVar = new mo();
                moVar.i = this.c.getAccuracy();
                moVar.f = this.c.getAltitude();
                moVar.d = this.c.getLatitude();
                moVar.h = this.c.getBearing();
                moVar.e = this.c.getLongitude();
                moVar.j = this.c.isFromMockProvider();
                moVar.f1337a = this.c.getProvider();
                moVar.g = this.c.getSpeed();
                moVar.l = (byte) i;
                moVar.b = System.currentTimeMillis();
                moVar.c = this.c.getTime();
                moVar.k = this.c.getTime();
                aVar.f1326a = moVar;
                aVar.b = a2;
                WifiInfo c = c.this.b.c();
                if (c != null) {
                    aVar.c = mp.a(c.getBSSID());
                }
                aVar.d = i.A;
                aVar.f = this.c.getTime();
                aVar.g = (byte) hr.n(c.this.f1615a);
                aVar.h = hr.s(c.this.f1615a);
                aVar.e = c.this.b.k();
                aVar.j = com.autonavi.aps.amapapi.utils.i.a(c.this.f1615a);
                aVar.i = a3;
                ll a4 = mf.a(aVar);
                if (a4 == null) {
                    return;
                }
                synchronized (c.this.f) {
                    c.this.f.add(a4);
                    if (c.this.f.size() >= 5) {
                        c.this.e();
                    }
                }
                c.this.d();
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "cl", "coll");
            }
        }

        private void b() {
            if (com.autonavi.aps.amapapi.utils.i.m(c.this.f1615a)) {
                return;
            }
            jq jqVar = null;
            try {
                long unused = c.k = System.currentTimeMillis();
                if (c.this.e.f.d()) {
                    jqVar = jq.a(new File(c.this.e.f1251a), c.this.e.b);
                    ArrayList arrayList = new ArrayList();
                    byte[] f = c.f();
                    if (f != null) {
                        List b = c.b(jqVar, c.this.e, arrayList, f);
                        if (b != null && b.size() != 0) {
                            c.this.e.f.a_(true);
                            if (mf.a(ia.b(mf.a(com.autonavi.aps.amapapi.security.a.a(f), hs.b(f, mf.a(), ia.c()), b)))) {
                                c.b(jqVar, arrayList);
                            }
                        }
                        try {
                            jqVar.close();
                            return;
                        } catch (Throwable unused2) {
                            return;
                        }
                    }
                    try {
                        jqVar.close();
                        return;
                    } catch (Throwable unused3) {
                        return;
                    }
                }
                if (jqVar != null) {
                    try {
                        jqVar.close();
                    } catch (Throwable unused4) {
                    }
                }
            } catch (Throwable th) {
                try {
                    iv.c(th, "leg", "uts");
                    if (jqVar != null) {
                        try {
                            jqVar.close();
                        } catch (Throwable unused5) {
                        }
                    }
                } catch (Throwable th2) {
                    if (jqVar != null) {
                        try {
                            jqVar.close();
                        } catch (Throwable unused6) {
                        }
                    }
                    throw th2;
                }
            }
        }

        @Override // com.amap.api.col.p0003sl.lb
        public final void runTask() {
            int i = this.b;
            if (i == 1) {
                a();
            } else if (i == 2) {
                b();
            } else if (i == 3) {
                c.this.g();
            }
        }
    }

    public final void a(Location location) {
        try {
            Handler handler = this.g;
            if (handler != null) {
                handler.post(new b(this, location));
            }
        } catch (Throwable th) {
            iv.c(th, "cl", "olcc");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ArrayList<ll> arrayList;
        try {
            if (!com.autonavi.aps.amapapi.utils.i.m(this.f1615a) && (arrayList = this.f) != null && arrayList.size() != 0) {
                ArrayList arrayList2 = new ArrayList();
                synchronized (this.f) {
                    arrayList2.addAll(this.f);
                    this.f.clear();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] a2 = a(256);
                if (a2 == null) {
                    return;
                }
                byteArrayOutputStream.write(c(a2.length));
                byteArrayOutputStream.write(a2);
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ll llVar = (ll) it.next();
                    byte[] b2 = llVar.b();
                    if (b2.length >= 10 && b2.length <= 65535) {
                        byte[] b3 = hs.b(a2, b2, ia.c());
                        byteArrayOutputStream.write(c(b3.length));
                        byteArrayOutputStream.write(b3);
                        byteArrayOutputStream.write(b(llVar.a()));
                    }
                }
                ke.a(Long.toString(System.currentTimeMillis()), byteArrayOutputStream.toByteArray(), this.e);
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "clm", "wtD");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(jq jqVar, List<String> list) {
        if (jqVar != null) {
            try {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    jqVar.c(it.next());
                }
                jqVar.close();
            } catch (Throwable th) {
                iv.c(th, "aps", "dlo");
            }
        }
    }

    public final void d() {
        try {
            if (!com.autonavi.aps.amapapi.utils.i.m(this.f1615a) && System.currentTimeMillis() - k >= 60000) {
                la.a().a(new b(2));
            }
        } catch (Throwable unused) {
        }
    }

    public final void e() {
        try {
            la.a().a(new b(3));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(5:15|(5:16|17|18|19|20)|(0)(3:30|31|(1:(2:35|36))(3:39|40|(10:44|(7:47|48|49|50|51|52|45)|70|71|72|73|74|75|76|(2:85|(2:93|94))(6:(2:81|82)|(2:80|25)|58|59|61|25))(1:109)))|26|27) */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x00dd, code lost:
    
        if (r9 == null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0039, code lost:
    
        if (r9 != null) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0047, code lost:
    
        if (r9 == null) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00ca, code lost:
    
        if (r9 == null) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x00df, code lost:
    
        r9.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x010e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.amap.api.col.p0003sl.ll> b(com.amap.api.col.p0003sl.jq r17, com.amap.api.col.p0003sl.kd r18, java.util.List<java.lang.String> r19, byte[] r20) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.c.b(com.amap.api.col.3sl.jq, com.amap.api.col.3sl.kd, java.util.List, byte[]):java.util.List");
    }

    @Override // com.amap.api.col.p0003sl.mv
    public final mu a(mt mtVar) {
        try {
            com.autonavi.aps.amapapi.trans.b bVar = new com.autonavi.aps.amapapi.trans.b();
            bVar.a(mtVar.b);
            bVar.a(mtVar.f1340a);
            bVar.a(mtVar.d);
            jt.a();
            kb a2 = jt.a(bVar);
            mu muVar = new mu();
            muVar.c = a2.f1250a;
            muVar.b = a2.b;
            muVar.f1341a = 200;
            return muVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static byte[] a(int i) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            if (keyGenerator == null) {
                return null;
            }
            keyGenerator.init(i);
            return keyGenerator.generateKey().getEncoded();
        } catch (Throwable unused) {
            return null;
        }
    }

    private static int a(byte[] bArr) {
        return ((bArr[0] & 255) << 24) | (bArr[3] & 255) | ((bArr[2] & 255) << 8) | ((bArr[1] & 255) << 16);
    }

    private static byte[] c(int i) {
        return new byte[]{(byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8), (byte) (i & 255)};
    }

    private static byte[] b(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }
}
