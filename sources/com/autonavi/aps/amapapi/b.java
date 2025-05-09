package com.autonavi.aps.amapapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.ia;
import com.amap.api.col.p0003sl.iv;
import com.amap.api.col.p0003sl.kb;
import com.amap.api.col.p0003sl.mp;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.restruct.e;
import com.autonavi.aps.amapapi.restruct.g;
import com.autonavi.aps.amapapi.restruct.i;
import com.autonavi.aps.amapapi.trans.f;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class b {
    static int C = -1;
    private static boolean M = false;
    boolean H;
    private Handler P;
    private g Q;
    private String R;
    private c T;
    public static String[] F = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    public static String G = "android.permission.ACCESS_BACKGROUND_LOCATION";
    private static volatile boolean S = false;

    /* renamed from: a, reason: collision with root package name */
    Context f1612a = null;
    ConnectivityManager b = null;
    i c = null;
    e d = null;
    com.autonavi.aps.amapapi.storage.a e = null;
    com.autonavi.aps.amapapi.trans.e f = null;
    ArrayList<mp> g = new ArrayList<>();
    a h = null;
    AMapLocationClientOption i = new AMapLocationClientOption();
    com.autonavi.aps.amapapi.model.a j = null;
    long k = 0;
    private int K = 0;
    f l = null;
    boolean m = false;
    private String L = null;
    com.autonavi.aps.amapapi.trans.c n = null;
    StringBuilder o = new StringBuilder();
    boolean p = true;
    boolean q = true;
    AMapLocationClientOption.GeoLanguage r = AMapLocationClientOption.GeoLanguage.DEFAULT;
    boolean s = true;
    boolean t = false;
    WifiInfo u = null;
    boolean v = true;
    private String N = null;
    StringBuilder w = null;
    boolean x = false;
    public boolean y = false;
    int z = 12;
    private boolean O = true;
    com.autonavi.aps.amapapi.restruct.b A = null;
    boolean B = false;
    com.autonavi.aps.amapapi.filters.a D = null;
    String E = null;
    IntentFilter I = null;
    LocationManager J = null;

    public b(boolean z) {
        this.H = z;
    }

    public final void a(Handler handler) {
        this.P = handler;
    }

    public final void a(Context context) {
        try {
            if (this.f1612a != null) {
                return;
            }
            this.D = new com.autonavi.aps.amapapi.filters.a();
            Context applicationContext = context.getApplicationContext();
            this.f1612a = applicationContext;
            com.autonavi.aps.amapapi.utils.i.b(applicationContext);
            if (this.c == null) {
                this.c = new i(this.f1612a, (WifiManager) com.autonavi.aps.amapapi.utils.i.a(this.f1612a, "wifi"), this.P);
            }
            if (this.d == null) {
                this.d = new e(this.f1612a, this.P);
            }
            this.Q = new g(context, this.P);
            if (this.e == null) {
                this.e = new com.autonavi.aps.amapapi.storage.a();
            }
            if (this.f == null) {
                this.f = new com.autonavi.aps.amapapi.trans.e();
            }
        } catch (Throwable th) {
            th.printStackTrace();
            com.autonavi.aps.amapapi.utils.b.a(th, "Aps", "initBase");
        }
    }

    public final void a() {
        e eVar = this.d;
        if (eVar != null) {
            eVar.b();
        }
    }

    public final void b() {
        this.n = com.autonavi.aps.amapapi.trans.c.a(this.f1612a);
        i();
        if (this.b == null) {
            this.b = (ConnectivityManager) com.autonavi.aps.amapapi.utils.i.a(this.f1612a, "connectivity");
        }
        if (this.l == null) {
            this.l = new f();
        }
    }

    private void i() {
        if (this.n != null) {
            try {
                if (this.i == null) {
                    this.i = new AMapLocationClientOption();
                }
                this.n.a(this.i.getHttpTimeOut(), this.i.getLocationProtocol().equals(AMapLocationClientOption.AMapLocationProtocol.HTTPS), j());
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: com.autonavi.aps.amapapi.b$1, reason: invalid class name */
    static final /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1613a;

        static {
            int[] iArr = new int[AMapLocationClientOption.GeoLanguage.values().length];
            f1613a = iArr;
            try {
                iArr[AMapLocationClientOption.GeoLanguage.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1613a[AMapLocationClientOption.GeoLanguage.ZH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1613a[AMapLocationClientOption.GeoLanguage.EN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private int j() {
        int i;
        if (this.i.getGeoLanguage() != null && (i = AnonymousClass1.f1613a[this.i.getGeoLanguage().ordinal()]) != 1) {
            if (i == 2) {
                return 1;
            }
            if (i == 3) {
                return 2;
            }
        }
        return 0;
    }

    public final void c() {
        if (this.A == null) {
            this.A = new com.autonavi.aps.amapapi.restruct.b(this.f1612a);
        }
        l();
        this.c.b(false);
        this.g = this.c.e();
        this.d.a(false, p());
        this.e.a(this.f1612a);
        b(this.f1612a);
        this.y = true;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.i = aMapLocationClientOption;
        if (aMapLocationClientOption == null) {
            this.i = new AMapLocationClientOption();
        }
        i iVar = this.c;
        if (iVar != null) {
            this.i.isWifiActiveScan();
            iVar.a(this.i.isWifiScan(), this.i.isMockEnable(), AMapLocationClientOption.isOpenAlwaysScanWifi(), aMapLocationClientOption.getScanWifiInterval());
        }
        i();
        com.autonavi.aps.amapapi.storage.a aVar = this.e;
        if (aVar != null) {
            aVar.a(this.i);
        }
        com.autonavi.aps.amapapi.trans.e eVar = this.f;
        if (eVar != null) {
            eVar.a(this.i);
        }
        k();
    }

    private void k() {
        boolean z;
        boolean z2;
        boolean z3;
        AMapLocationClientOption.GeoLanguage geoLanguage = AMapLocationClientOption.GeoLanguage.DEFAULT;
        boolean z4 = true;
        try {
            geoLanguage = this.i.getGeoLanguage();
            z = this.i.isNeedAddress();
            try {
                z3 = this.i.isOffset();
            } catch (Throwable unused) {
                z2 = true;
            }
        } catch (Throwable unused2) {
            z = true;
            z2 = true;
        }
        try {
            z4 = this.i.isLocationCacheEnable();
            this.t = this.i.isOnceLocationLatest();
            this.B = this.i.isSensorEnable();
            if (z3 != this.q || z != this.p || z4 != this.s || geoLanguage != this.r) {
                r();
            }
        } catch (Throwable unused3) {
            z2 = z4;
            z4 = z3;
            boolean z5 = z2;
            z3 = z4;
            z4 = z5;
            this.q = z3;
            this.p = z;
            this.s = z4;
            this.r = geoLanguage;
        }
        this.q = z3;
        this.p = z;
        this.s = z4;
        this.r = geoLanguage;
    }

    public final void d() {
        if (this.o.length() > 0) {
            StringBuilder sb = this.o;
            sb.delete(0, sb.length());
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:20|(2:22|(1:24)(1:25))|26|(2:27|28)|(6:33|34|35|36|37|(2:39|40)(2:41|(2:43|44)(9:45|(1:47)(2:73|(1:75)(2:76|(1:78)))|48|49|(2:52|(1:54)(2:55|(1:57)(2:58|(1:60)(1:61))))|62|(2:64|(1:69)(1:68))|70|71)))|83|34|35|36|37|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x009f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00a0, code lost:
    
        com.autonavi.aps.amapapi.utils.b.a(r0, "Aps", "getLocation getCgiListParam");
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.autonavi.aps.amapapi.model.a a(com.autonavi.aps.amapapi.a r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 467
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.b.a(com.autonavi.aps.amapapi.a):com.autonavi.aps.amapapi.model.a");
    }

    public final com.autonavi.aps.amapapi.model.a a(com.autonavi.aps.amapapi.model.a aVar) {
        this.D.a(this.s);
        return this.D.a(aVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e() {
        /*
            r4 = this;
            r0 = 0
            r4.E = r0
            r1 = 0
            r4.x = r1
            r4.y = r1
            com.autonavi.aps.amapapi.storage.a r1 = r4.e
            if (r1 == 0) goto L11
            android.content.Context r2 = r4.f1612a
            r1.b(r2)
        L11:
            com.autonavi.aps.amapapi.filters.a r1 = r4.D
            if (r1 == 0) goto L18
            r1.a()
        L18:
            com.autonavi.aps.amapapi.trans.e r1 = r4.f
            if (r1 == 0) goto L1e
            r4.f = r0
        L1e:
            com.autonavi.aps.amapapi.restruct.g r1 = r4.Q
            if (r1 == 0) goto L27
            boolean r2 = r4.H
            r1.a(r2)
        L27:
            android.content.Context r1 = r4.f1612a     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L3b
            com.autonavi.aps.amapapi.b$a r2 = r4.h     // Catch: java.lang.Throwable -> L33
            if (r2 == 0) goto L3b
            r1.unregisterReceiver(r2)     // Catch: java.lang.Throwable -> L33
            goto L3b
        L33:
            r1 = move-exception
            java.lang.String r2 = "Aps"
            java.lang.String r3 = "destroy"
            com.autonavi.aps.amapapi.utils.b.a(r1, r2, r3)     // Catch: java.lang.Throwable -> L66
        L3b:
            r4.h = r0
            com.autonavi.aps.amapapi.restruct.e r1 = r4.d
            if (r1 == 0) goto L46
            boolean r2 = r4.H
            r1.a(r2)
        L46:
            com.autonavi.aps.amapapi.restruct.i r1 = r4.c
            if (r1 == 0) goto L4f
            boolean r2 = r4.H
            r1.c(r2)
        L4f:
            java.util.ArrayList<com.amap.api.col.3sl.mp> r1 = r4.g
            if (r1 == 0) goto L56
            r1.clear()
        L56:
            com.autonavi.aps.amapapi.restruct.b r1 = r4.A
            if (r1 == 0) goto L5d
            r1.f()
        L5d:
            r4.j = r0
            r4.f1612a = r0
            r4.w = r0
            r4.J = r0
            return
        L66:
            r1 = move-exception
            r4.h = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.b.e():void");
    }

    private void l() {
        try {
            if (this.h == null) {
                this.h = new a();
            }
            if (this.I == null) {
                IntentFilter intentFilter = new IntentFilter();
                this.I = intentFilter;
                intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                this.I.addAction("android.net.wifi.SCAN_RESULTS");
            }
            this.f1612a.registerReceiver(this.h, this.I);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "Aps", "initBroadcastListener");
        }
    }

    private boolean a(long j) {
        if (!this.O) {
            this.O = true;
            return false;
        }
        if (com.autonavi.aps.amapapi.utils.i.b() - j < 800) {
            return (com.autonavi.aps.amapapi.utils.i.a(this.j) ? com.autonavi.aps.amapapi.utils.i.a() - this.j.getTime() : 0L) <= PreConnectManager.CONNECT_INTERNAL;
        }
        return false;
    }

    private String c(com.autonavi.aps.amapapi.a aVar) {
        int h = this.d.h();
        com.autonavi.aps.amapapi.restruct.d e = this.d.e();
        com.autonavi.aps.amapapi.restruct.d f = this.d.f();
        ArrayList<mp> arrayList = this.g;
        boolean z = arrayList == null || arrayList.isEmpty();
        String str = "";
        if (e == null && f == null && z) {
            if (this.b == null) {
                this.b = (ConnectivityManager) com.autonavi.aps.amapapi.utils.i.a(this.f1612a, "connectivity");
            }
            if (com.autonavi.aps.amapapi.utils.i.c() >= 31) {
                if (com.autonavi.aps.amapapi.utils.i.a(this.f1612a) && !this.c.l()) {
                    this.z = 18;
                    this.o.append("飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关#1802");
                    com.autonavi.aps.amapapi.utils.g.a((String) null, 2132);
                    aVar.f("#1802");
                    return "";
                }
            } else if (com.autonavi.aps.amapapi.utils.i.a(this.f1612a) && !this.c.k()) {
                this.z = 18;
                this.o.append("飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关#1801");
                com.autonavi.aps.amapapi.utils.g.a((String) null, 2132);
                aVar.f("#1801");
                return "";
            }
            if (com.autonavi.aps.amapapi.utils.i.c() >= 28) {
                if (this.J == null) {
                    this.J = (LocationManager) this.f1612a.getApplicationContext().getSystemService("location");
                }
                if (!((Boolean) com.autonavi.aps.amapapi.utils.e.a(this.J, "isLocationEnabled", new Object[0])).booleanValue()) {
                    this.z = 12;
                    this.o.append("定位服务没有开启，请在设置中打开定位服务开关#1206");
                    aVar.f("#1206");
                    com.autonavi.aps.amapapi.utils.g.a((String) null, 2121);
                    return "";
                }
            }
            if (!com.autonavi.aps.amapapi.utils.i.e(this.f1612a)) {
                this.z = 12;
                this.o.append("定位权限被禁用,请授予应用定位权限#1201");
                aVar.f("#1201");
                com.autonavi.aps.amapapi.utils.g.a((String) null, 2121);
                return "";
            }
            if (com.autonavi.aps.amapapi.utils.i.c() >= 24 && com.autonavi.aps.amapapi.utils.i.c() < 28 && Settings.Secure.getInt(this.f1612a.getContentResolver(), "location_mode", 0) == 0) {
                this.z = 12;
                aVar.f("#1206");
                this.o.append("定位服务没有开启，请在设置中打开定位服务开关#1206");
                com.autonavi.aps.amapapi.utils.g.a((String) null, 2121);
                return "";
            }
            String k = this.d.k();
            String d = this.c.d();
            if (this.c.a(this.b) && d != null) {
                this.z = 12;
                aVar.f("#1202");
                this.o.append("获取基站与获取WIFI的权限都被禁用，请在安全软件中打开应用的定位权限#1202");
                com.autonavi.aps.amapapi.utils.g.a((String) null, 2121);
                return "";
            }
            if (k != null) {
                this.z = 12;
                if (!this.c.k()) {
                    aVar.f("#1204");
                    this.o.append("WIFI开关关闭，并且获取基站权限被禁用，请在安全软件中打开应用的定位权限或者打开WIFI开关#1204");
                } else {
                    aVar.f("#1205");
                    this.o.append("获取的WIFI列表为空，并且获取基站权限被禁用，请在安全软件中打开应用的定位权限#1205");
                }
                com.autonavi.aps.amapapi.utils.g.a((String) null, 2121);
                return "";
            }
            if (!this.c.k() && !this.d.n()) {
                this.z = 19;
                aVar.f("#1901");
                this.o.append("没有检查到SIM卡，并且WIFI开关关闭，请打开WIFI开关或者插入SIM卡#1901");
                com.autonavi.aps.amapapi.utils.g.a((String) null, 2133);
                return "";
            }
            if (!this.c.k()) {
                aVar.f("#1301");
                this.o.append("获取到的基站为空，并且关闭了WIFI开关，请您打开WIFI开关再发起定位#1301");
            } else {
                aVar.f("#1302");
                if (this.c.c() != null) {
                    this.o.append("获取到的基站和WIFI信息均为空，请检查是否授予APP定位权限");
                    if (!com.autonavi.aps.amapapi.utils.i.f(this.f1612a)) {
                        this.o.append("或后台运行没有后台定位权限");
                    }
                    this.o.append("#1302");
                } else {
                    this.o.append("获取到的基站和WIFI信息均为空，请移动到有WIFI的区域，若确定当前区域有WIFI，请检查是否授予APP定位权限");
                    if (!com.autonavi.aps.amapapi.utils.i.f(this.f1612a)) {
                        this.o.append("或后台运行没有后台定位权限");
                    }
                    this.o.append("#1302");
                }
            }
            this.z = 13;
            com.autonavi.aps.amapapi.utils.g.a((String) null, 2131);
            return "";
        }
        WifiInfo m = this.c.m();
        this.u = m;
        this.v = i.a(m);
        String str2 = "cgiwifi";
        if (h == 0) {
            boolean z2 = !this.g.isEmpty() || this.v;
            boolean z3 = f != null;
            if (!z3) {
                if (this.v && this.g.isEmpty()) {
                    this.z = 2;
                    aVar.f("#0201");
                    this.o.append("当前基站为伪基站，并且WIFI权限被禁用，请在安全软件中打开应用的定位权限#0201");
                    com.autonavi.aps.amapapi.utils.g.a((String) null, 2021);
                    return "";
                }
                if (this.g.size() == 1) {
                    this.z = 2;
                    if (!this.v) {
                        aVar.f("#0202");
                        this.o.append("当前基站为伪基站，并且搜到的WIFI数量不足，请移动到WIFI比较丰富的区域#0202");
                        com.autonavi.aps.amapapi.utils.g.a((String) null, 2022);
                        return "";
                    }
                    if (this.g.get(0).h) {
                        aVar.f("#0202");
                        this.o.append("当前基站为伪基站，并且搜到的WIFI数量不足，请移动到WIFI比较丰富的区域#0202");
                        com.autonavi.aps.amapapi.utils.g.a((String) null, 2021);
                        return "";
                    }
                }
            }
            String format = String.format(Locale.US, "#%s#", HAWebViewInterface.NETWORK);
            if (z3) {
                StringBuilder sb = new StringBuilder();
                sb.append(f.b());
                if (this.g.isEmpty() && !this.v) {
                    str2 = "cgi";
                }
                sb.append("network#");
                sb.append(str2);
                str = sb.toString();
            } else if (z2) {
                str = format + "wifi";
            } else {
                this.z = 2;
                if (!this.c.k()) {
                    aVar.f("#0203");
                    this.o.append("当前基站为伪基站,并且关闭了WIFI开关，请在设置中打开WIFI开关#0203");
                } else {
                    aVar.f("#0204");
                    this.o.append("当前基站为伪基站,并且没有搜索到WIFI，请移动到WIFI比较丰富的区域#0204");
                }
                com.autonavi.aps.amapapi.utils.g.a((String) null, 2022);
            }
        } else if (h != 1) {
            if (h != 2) {
                this.z = 11;
                com.autonavi.aps.amapapi.utils.g.a((String) null, 2111);
                aVar.f("#1101");
                this.o.append("get cgi failure#1101");
            } else if (e != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(e.f1627a);
                sb2.append("#");
                sb2.append(e.b);
                sb2.append("#");
                sb2.append(e.h);
                sb2.append("#");
                sb2.append(e.i);
                sb2.append("#");
                sb2.append(e.j);
                sb2.append("#network#");
                if (this.g.isEmpty() && !this.v) {
                    str2 = "cgi";
                }
                sb2.append(str2);
                str = sb2.toString();
            }
        } else if (e != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(e.f1627a);
            sb3.append("#");
            sb3.append(e.b);
            sb3.append("#");
            sb3.append(e.c);
            sb3.append("#");
            sb3.append(e.d);
            sb3.append("#network#");
            if (this.g.isEmpty() && !this.v) {
                str2 = "cgi";
            }
            sb3.append(str2);
            str = sb3.toString();
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        return com.autonavi.aps.amapapi.utils.i.e() + str;
    }

    private StringBuilder a(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder(700);
        } else {
            sb.delete(0, sb.length());
        }
        sb.append(this.d.m());
        sb.append(this.c.o());
        return sb;
    }

    private byte[] m() throws Throwable {
        if (this.l == null) {
            this.l = new f();
        }
        if (this.i == null) {
            this.i = new AMapLocationClientOption();
        }
        this.l.a(this.f1612a, this.i.isNeedAddress(), this.i.isOffset(), this.d, this.c, this.b, this.E, this.Q);
        return this.l.a();
    }

    final class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                return;
            }
            try {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                    if (b.this.c != null) {
                        b.this.c.i();
                    }
                    try {
                        if (intent.getExtras() == null || !intent.getExtras().getBoolean("resultsUpdated", true) || b.this.c == null) {
                            return;
                        }
                        b.this.c.h();
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                }
                if (!action.equals("android.net.wifi.WIFI_STATE_CHANGED") || b.this.c == null) {
                    return;
                }
                b.this.c.j();
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "Aps", "onReceive");
            }
        }
    }

    private com.autonavi.aps.amapapi.model.a a(com.autonavi.aps.amapapi.model.a aVar, kb kbVar, com.autonavi.aps.amapapi.a aVar2) {
        if (kbVar != null) {
            try {
                if (kbVar.f1250a != null && kbVar.f1250a.length != 0) {
                    com.autonavi.aps.amapapi.trans.e eVar = new com.autonavi.aps.amapapi.trans.e();
                    String str = new String(kbVar.f1250a, "UTF-8");
                    if (str.contains("\"status\":\"0\"")) {
                        com.autonavi.aps.amapapi.model.a a2 = eVar.a(str, this.f1612a, kbVar, aVar2);
                        a2.h(this.w.toString());
                        return a2;
                    }
                    if (!str.contains("</body></html>")) {
                        return null;
                    }
                    aVar.setErrorCode(5);
                    i iVar = this.c;
                    if (iVar != null && iVar.a(this.b)) {
                        aVar2.f("#0501");
                        this.o.append("您连接的是一个需要登录的网络，请确认已经登入网络#0501");
                        com.autonavi.aps.amapapi.utils.g.a((String) null, 2051);
                    } else {
                        aVar2.f("#0502");
                        this.o.append("请求可能被劫持了#0502");
                        com.autonavi.aps.amapapi.utils.g.a((String) null, 2052);
                    }
                    aVar.setLocationDetail(this.o.toString());
                    return aVar;
                }
            } catch (Throwable th) {
                aVar.setErrorCode(4);
                com.autonavi.aps.amapapi.utils.b.a(th, "Aps", "checkResponseEntity");
                aVar2.f("#0403");
                this.o.append("check response exception ex is" + th.getMessage() + "#0403");
                aVar.setLocationDetail(this.o.toString());
                return aVar;
            }
        }
        aVar.setErrorCode(4);
        this.o.append("网络异常,请求异常#0403");
        aVar2.f("#0403");
        aVar.h(this.w.toString());
        aVar.setLocationDetail(this.o.toString());
        if (kbVar != null) {
            com.autonavi.aps.amapapi.utils.g.a(kbVar.d, 2041);
        }
        return aVar;
    }

    private static void c(com.autonavi.aps.amapapi.model.a aVar) {
        if (aVar.getErrorCode() == 0 && aVar.getLocationType() == 0) {
            if ("-5".equals(aVar.d()) || "1".equals(aVar.d()) || "2".equals(aVar.d()) || "14".equals(aVar.d()) || "24".equals(aVar.d()) || "-1".equals(aVar.d())) {
                aVar.setLocationType(5);
            } else {
                aVar.setLocationType(6);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0106 A[Catch: all -> 0x01fd, TRY_LEAVE, TryCatch #2 {all -> 0x01fd, blocks: (B:12:0x0052, B:14:0x007b, B:17:0x0086, B:19:0x008e, B:22:0x0096, B:23:0x0098, B:25:0x009e, B:26:0x00a8, B:29:0x00b1, B:31:0x00c4, B:33:0x00c8, B:34:0x00d2, B:37:0x00e8, B:39:0x00ee, B:41:0x00f2, B:42:0x0102, B:44:0x0106, B:74:0x00f9, B:75:0x00ff), top: B:11:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x013e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.autonavi.aps.amapapi.model.a b(boolean r12, com.autonavi.aps.amapapi.a r13) {
        /*
            Method dump skipped, instructions count: 778
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.b.b(boolean, com.autonavi.aps.amapapi.a):com.autonavi.aps.amapapi.model.a");
    }

    private boolean n() {
        return this.k == 0 || com.autonavi.aps.amapapi.utils.i.b() - this.k > 20000;
    }

    private void b(Context context) {
        try {
            if (context.checkCallingOrSelfPermission(ia.c("EYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFQ1VSRV9TRVRUSU5HUw==")) == 0) {
                this.m = true;
            }
        } catch (Throwable unused) {
        }
    }

    private void o() {
        i iVar = this.c;
        if (iVar == null) {
            return;
        }
        iVar.a(this.m);
    }

    public final void f() {
        c cVar = this.T;
        if (cVar != null) {
            cVar.d();
        }
    }

    public final void b(com.autonavi.aps.amapapi.a aVar) {
        try {
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "Aps", "initFirstLocateParam");
        }
        if (this.x) {
            return;
        }
        q();
        if (this.t) {
            l();
        }
        this.c.b(this.t);
        this.g = this.c.e();
        this.d.a(true, p());
        String c = c(aVar);
        this.N = c;
        if (!TextUtils.isEmpty(c)) {
            this.w = a(this.w);
        }
        this.x = true;
    }

    private boolean p() {
        ArrayList<mp> e = this.c.e();
        this.g = e;
        return e == null || e.size() <= 0;
    }

    private void q() {
        if (this.N != null) {
            this.N = null;
        }
        StringBuilder sb = this.w;
        if (sb != null) {
            sb.delete(0, sb.length());
        }
    }

    public final com.autonavi.aps.amapapi.model.a a(boolean z) {
        if (this.c.n()) {
            return a(15, "networkLocation has been mocked!#1502");
        }
        if (TextUtils.isEmpty(this.N)) {
            return a(this.z, this.o.toString());
        }
        com.autonavi.aps.amapapi.model.a a2 = this.e.a(this.f1612a, this.N, this.w, true, z);
        if (com.autonavi.aps.amapapi.utils.i.a(a2)) {
            d(a2);
        }
        return a2;
    }

    private void d(com.autonavi.aps.amapapi.model.a aVar) {
        if (aVar != null) {
            this.j = aVar;
        }
    }

    public final com.autonavi.aps.amapapi.model.a a(boolean z, com.autonavi.aps.amapapi.a aVar) {
        if (z) {
            aVar.e("statics");
        } else {
            aVar.e("first");
        }
        if (this.f1612a == null) {
            aVar.f("#0101");
            this.o.append("context is null#0101");
            com.autonavi.aps.amapapi.utils.g.a((String) null, 2011);
            return a(1, this.o.toString());
        }
        if (this.c.n()) {
            aVar.f("#1502");
            return a(15, "networkLocation has been mocked!#1502");
        }
        b();
        if (TextUtils.isEmpty(this.N)) {
            return a(this.z, this.o.toString());
        }
        com.autonavi.aps.amapapi.model.a b = b(z, aVar);
        if (com.autonavi.aps.amapapi.utils.i.a(b) && !S) {
            this.e.a(this.w.toString());
            this.e.a(this.d.e());
            d(b);
        }
        S = true;
        return b;
    }

    public final void b(com.autonavi.aps.amapapi.model.a aVar) {
        if (com.autonavi.aps.amapapi.utils.i.a(aVar)) {
            this.e.a(this.N, this.w, aVar, this.f1612a, true);
        }
    }

    public final void g() {
        try {
            if (this.f1612a == null) {
                return;
            }
            if (this.T == null) {
                this.T = new c(this.f1612a);
            }
            this.T.a(this.d, this.c, this.P);
        } catch (Throwable th) {
            iv.c(th, "as", "stc");
        }
    }

    public final void h() {
        c cVar = this.T;
        if (cVar != null) {
            cVar.a();
        }
    }

    private void r() {
        try {
            com.autonavi.aps.amapapi.storage.a aVar = this.e;
            if (aVar != null) {
                aVar.a();
            }
            d(null);
            this.O = false;
            com.autonavi.aps.amapapi.filters.a aVar2 = this.D;
            if (aVar2 != null) {
                aVar2.a();
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "Aps", "cleanCache");
        }
    }

    public final com.autonavi.aps.amapapi.model.a a(double d, double d2) {
        try {
            String a2 = this.n.a(this.f1612a, d, d2);
            if (!a2.contains("\"status\":\"1\"")) {
                return null;
            }
            com.autonavi.aps.amapapi.model.a a3 = this.f.a(a2);
            a3.setLatitude(d);
            a3.setLongitude(d2);
            return a3;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final void a(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() != 0) {
            return;
        }
        com.autonavi.aps.amapapi.restruct.f fVar = new com.autonavi.aps.amapapi.restruct.f();
        fVar.f1631a = aMapLocation.getLocationType();
        fVar.d = aMapLocation.getTime();
        fVar.e = (int) aMapLocation.getAccuracy();
        fVar.b = aMapLocation.getLatitude();
        fVar.c = aMapLocation.getLongitude();
        if (aMapLocation.getLocationType() == 1) {
            this.Q.a(fVar);
        }
    }

    public final void a(com.autonavi.aps.amapapi.model.a aVar, int i) {
        if (aVar != null && aVar.getErrorCode() == 0) {
            com.autonavi.aps.amapapi.restruct.f fVar = new com.autonavi.aps.amapapi.restruct.f();
            fVar.d = aVar.getTime();
            fVar.e = (int) aVar.getAccuracy();
            fVar.b = aVar.getLatitude();
            fVar.c = aVar.getLongitude();
            fVar.f1631a = i;
            fVar.g = Integer.parseInt(aVar.d());
            fVar.h = aVar.l();
            this.Q.b(fVar);
        }
    }

    private static com.autonavi.aps.amapapi.model.a a(int i, String str) {
        com.autonavi.aps.amapapi.model.a aVar = new com.autonavi.aps.amapapi.model.a("");
        aVar.setErrorCode(i);
        aVar.setLocationDetail(str);
        if (i == 15) {
            com.autonavi.aps.amapapi.utils.g.a((String) null, 2151);
        }
        return aVar;
    }
}
