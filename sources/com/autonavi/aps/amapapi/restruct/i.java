package com.autonavi.aps.amapapi.restruct;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.mp;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.profile.profile.ProfileExtendConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public final class i {
    static long d;
    static long e;
    static long f;
    public static long g;
    static long h;
    private com.autonavi.aps.amapapi.c E;

    /* renamed from: a, reason: collision with root package name */
    WifiManager f1634a;
    Context i;
    h t;
    public static HashMap<String, Long> w = new HashMap<>(36);
    public static long x = 0;
    static int y = 0;
    public static long A = 0;
    ArrayList<mp> b = new ArrayList<>();
    ArrayList<mp> c = new ArrayList<>();
    boolean j = false;
    StringBuilder k = null;
    boolean l = true;
    boolean m = true;
    boolean n = true;
    private volatile WifiInfo C = null;
    String o = null;
    TreeMap<Integer, mp> p = null;
    public boolean q = true;
    public boolean r = true;
    public boolean s = false;
    String u = "";
    long v = 0;
    ConnectivityManager z = null;
    private long D = OpAnalyticsConstants.H5_LOADING_DELAY;
    volatile boolean B = false;

    public i(Context context, WifiManager wifiManager, Handler handler) {
        this.f1634a = wifiManager;
        this.i = context;
        h hVar = new h(context, "wifiAgee", handler);
        this.t = hVar;
        hVar.a();
    }

    public final ArrayList<mp> a() {
        if (!this.s) {
            return this.c;
        }
        b(true);
        return this.c;
    }

    private List<mp> r() {
        List<ScanResult> list;
        if (this.f1634a != null) {
            try {
                if (com.autonavi.aps.amapapi.utils.i.c(this.i, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF")) {
                    list = this.f1634a.getScanResults();
                } else {
                    com.autonavi.aps.amapapi.utils.b.a(new Exception("gst_n_aws"), "OPENSDK_WMW", "gsr_n_aws");
                    list = null;
                }
                HashMap<String, Long> hashMap = new HashMap<>(36);
                if (list != null) {
                    for (ScanResult scanResult : list) {
                        hashMap.put(scanResult.BSSID, Long.valueOf(scanResult.timestamp));
                    }
                }
                if (w.isEmpty() || !w.equals(hashMap)) {
                    w = hashMap;
                    x = com.autonavi.aps.amapapi.utils.i.b();
                }
                this.o = null;
                ArrayList arrayList = new ArrayList();
                this.u = "";
                this.C = m();
                if (a(this.C)) {
                    this.u = this.C.getBSSID();
                }
                if (list != null && list.size() > 0) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        ScanResult scanResult2 = list.get(i);
                        mp mpVar = new mp(!TextUtils.isEmpty(this.u) && this.u.equals(scanResult2.BSSID));
                        mpVar.b = scanResult2.SSID;
                        mpVar.d = scanResult2.frequency;
                        mpVar.e = scanResult2.timestamp;
                        mpVar.f1338a = mp.a(scanResult2.BSSID);
                        mpVar.c = (short) scanResult2.level;
                        mpVar.g = (short) ((SystemClock.elapsedRealtime() - (scanResult2.timestamp / 1000)) / 1000);
                        if (mpVar.g < 0) {
                            mpVar.g = (short) 0;
                        }
                        mpVar.f = com.autonavi.aps.amapapi.utils.i.b();
                        arrayList.add(mpVar);
                    }
                }
                this.t.a((List) arrayList);
                return arrayList;
            } catch (SecurityException e2) {
                this.o = e2.getMessage();
            } catch (Throwable th) {
                this.o = null;
                com.autonavi.aps.amapapi.utils.b.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    public static long b() {
        return ((com.autonavi.aps.amapapi.utils.i.b() - x) / 1000) + 1;
    }

    public final WifiInfo c() {
        try {
            if (this.f1634a == null) {
                return null;
            }
            if (!com.autonavi.aps.amapapi.utils.i.c(this.i, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF")) {
                com.autonavi.aps.amapapi.utils.b.a(new Exception("gci_n_aws"), "OPENSDK_WMW", "gci_n_aws");
                return null;
            }
            return this.f1634a.getConnectionInfo();
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "WifiManagerWrapper", "getConnectionInfo");
            return null;
        }
    }

    private int s() {
        WifiManager wifiManager = this.f1634a;
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        return 4;
    }

    private boolean t() {
        long b = com.autonavi.aps.amapapi.utils.i.b() - d;
        if (b < 4900) {
            return false;
        }
        if (u() && b < 9900) {
            return false;
        }
        if (y > 1) {
            long j = this.D;
            if (j == OpAnalyticsConstants.H5_LOADING_DELAY) {
                j = com.autonavi.aps.amapapi.utils.a.n() != -1 ? com.autonavi.aps.amapapi.utils.a.n() : 30000L;
            }
            if (Build.VERSION.SDK_INT >= 28 && b < j) {
                return false;
            }
        }
        if (this.f1634a != null) {
            d = com.autonavi.aps.amapapi.utils.i.b();
            int i = y;
            if (i < 2) {
                y = i + 1;
            }
            if (com.autonavi.aps.amapapi.utils.i.c(this.i, "WYW5kcm9pZC5wZXJtaXNzaW9uLkNIQU5HRV9XSUZJX1NUQVRF")) {
                return this.f1634a.startScan();
            }
            com.autonavi.aps.amapapi.utils.b.a(new Exception("n_cws"), "OPENSDK_WMW", "wfs_n_cws");
        }
        return false;
    }

    public final boolean a(ConnectivityManager connectivityManager) {
        try {
            if (com.autonavi.aps.amapapi.utils.i.a(connectivityManager.getActiveNetworkInfo()) == 1) {
                if (a(c())) {
                    return true;
                }
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "WifiManagerWrapper", "wifiAccess");
        }
        return false;
    }

    private boolean u() {
        if (this.z == null) {
            this.z = (ConnectivityManager) com.autonavi.aps.amapapi.utils.i.a(this.i, "connectivity");
        }
        return a(this.z);
    }

    private boolean v() {
        if (this.f1634a == null) {
            return false;
        }
        return com.autonavi.aps.amapapi.utils.i.g(this.i);
    }

    public final void a(boolean z) {
        Context context = this.i;
        if (!com.autonavi.aps.amapapi.utils.a.m() || !this.n || this.f1634a == null || context == null || !z || com.autonavi.aps.amapapi.utils.i.c() <= 17) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (((Integer) com.autonavi.aps.amapapi.utils.e.a("android.provider.Settings$Global", "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, (Class<?>[]) new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                com.autonavi.aps.amapapi.utils.e.a("android.provider.Settings$Global", "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", 1}, (Class<?>[]) new Class[]{ContentResolver.class, String.class, Integer.TYPE});
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
        }
    }

    public static boolean a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getSSID()) || !com.autonavi.aps.amapapi.utils.i.a(wifiInfo.getBSSID())) ? false : true;
    }

    public final String d() {
        return this.o;
    }

    private void d(boolean z) {
        ArrayList<mp> arrayList = this.b;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (com.autonavi.aps.amapapi.utils.i.b() - g > 3600000) {
            g();
        }
        if (this.p == null) {
            this.p = new TreeMap<>(Collections.reverseOrder());
        }
        this.p.clear();
        if (this.s && z) {
            try {
                this.c.clear();
            } catch (Throwable unused) {
            }
        }
        int size = this.b.size();
        this.v = 0L;
        for (int i = 0; i < size; i++) {
            mp mpVar = this.b.get(i);
            if (mpVar.h) {
                this.v = mpVar.f;
            }
            if (com.autonavi.aps.amapapi.utils.i.a(mpVar != null ? mp.a(mpVar.f1338a) : "") && (size <= 20 || a(mpVar.c))) {
                if (this.s && z) {
                    this.c.add(mpVar);
                }
                if (!TextUtils.isEmpty(mpVar.b)) {
                    if (!"<unknown ssid>".equals(mpVar.b)) {
                        mpVar.b = String.valueOf(i);
                    }
                } else {
                    mpVar.b = "unkwn";
                }
                this.p.put(Integer.valueOf((mpVar.c * 25) + i), mpVar);
            }
        }
        this.b.clear();
        Iterator<mp> it = this.p.values().iterator();
        while (it.hasNext()) {
            this.b.add(it.next());
        }
        this.p.clear();
    }

    public final ArrayList<mp> e() {
        if (this.b == null) {
            return null;
        }
        ArrayList<mp> arrayList = new ArrayList<>();
        if (!this.b.isEmpty()) {
            arrayList.addAll(this.b);
        }
        return arrayList;
    }

    public final void b(boolean z) {
        if (z) {
            w();
        } else {
            x();
        }
        boolean z2 = false;
        if (this.B) {
            this.B = false;
            z();
        }
        y();
        if (com.autonavi.aps.amapapi.utils.i.b() - g > 20000) {
            this.b.clear();
        }
        e = com.autonavi.aps.amapapi.utils.i.b();
        if (this.b.isEmpty()) {
            g = com.autonavi.aps.amapapi.utils.i.b();
            List<mp> r = r();
            if (r != null) {
                this.b.addAll(r);
                z2 = true;
            }
        }
        d(z2);
    }

    public final void f() {
        try {
            this.s = true;
            List<mp> r = r();
            if (r != null) {
                this.b.clear();
                this.b.addAll(r);
            }
            d(true);
        } catch (Throwable unused) {
        }
    }

    private void w() {
        if (B()) {
            long b = com.autonavi.aps.amapapi.utils.i.b();
            if (b - e >= PreConnectManager.CONNECT_INTERNAL) {
                this.b.clear();
                h = g;
            }
            x();
            if (b - e >= PreConnectManager.CONNECT_INTERNAL) {
                for (int i = 20; i > 0 && g == h; i--) {
                    try {
                        Thread.sleep(150L);
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    public final void a(boolean z, boolean z2, boolean z3, long j) {
        this.l = z;
        this.m = z2;
        this.n = z3;
        if (j < PreConnectManager.CONNECT_INTERNAL) {
            this.D = PreConnectManager.CONNECT_INTERNAL;
        } else {
            this.D = j;
        }
    }

    public final void g() {
        this.C = null;
        this.b.clear();
    }

    private void x() {
        if (B()) {
            try {
                if (t()) {
                    f = com.autonavi.aps.amapapi.utils.i.b();
                }
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "WifiManager", "wifiScan");
            }
        }
    }

    private void y() {
        List<mp> list;
        if (h != g) {
            try {
                list = r();
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "WifiManager", "updateScanResult");
                list = null;
            }
            h = g;
            if (list != null) {
                this.b.clear();
                this.b.addAll(list);
            } else {
                this.b.clear();
            }
        }
    }

    public final void a(com.autonavi.aps.amapapi.c cVar) {
        this.E = cVar;
    }

    public final void h() {
        A = System.currentTimeMillis();
        com.autonavi.aps.amapapi.c cVar = this.E;
        if (cVar != null) {
            cVar.b();
        }
    }

    public final void i() {
        if (this.f1634a != null && com.autonavi.aps.amapapi.utils.i.b() - g > 4900) {
            g = com.autonavi.aps.amapapi.utils.i.b();
        }
    }

    private void z() {
        int i;
        try {
            if (this.f1634a == null) {
                return;
            }
            try {
                i = s();
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "OPENSDK_WMW", "cwsc");
                i = 4;
            }
            if (this.b == null) {
                this.b = new ArrayList<>();
            }
            if (i == 0 || i == 1 || i == 4) {
                g();
            }
        } catch (Throwable unused) {
        }
    }

    public final void j() {
        if (this.f1634a == null) {
            return;
        }
        this.B = true;
    }

    private static boolean a(int i) {
        try {
            return WifiManager.calculateSignalLevel(i, 20) > 0;
        } catch (ArithmeticException e2) {
            com.autonavi.aps.amapapi.utils.b.a(e2, "Aps", "wifiSigFine");
            return true;
        }
    }

    public final boolean k() {
        return this.q;
    }

    public final boolean l() {
        return this.r;
    }

    private void A() {
        try {
            if (com.autonavi.aps.amapapi.utils.i.c(this.i, "EYW5kcm9pZC5wZXJtaXNzaW9uLkFDQ0VTU19XSUZJX1NUQVRF")) {
                this.r = this.f1634a.isWifiEnabled();
            }
        } catch (Throwable unused) {
        }
    }

    private boolean B() {
        this.q = v();
        A();
        if (this.q && this.l) {
            if (f != 0) {
                if (com.autonavi.aps.amapapi.utils.i.b() - f >= 4900 && com.autonavi.aps.amapapi.utils.i.b() - g >= ProfileExtendConstants.TIME_OUT) {
                    com.autonavi.aps.amapapi.utils.i.b();
                }
            }
            return true;
        }
        return false;
    }

    public final WifiInfo m() {
        this.C = c();
        return this.C;
    }

    public final boolean n() {
        return this.j;
    }

    public final String o() {
        String str;
        StringBuilder sb = this.k;
        int i = 0;
        if (sb == null) {
            this.k = new StringBuilder(700);
        } else {
            sb.delete(0, sb.length());
        }
        this.j = false;
        int size = this.b.size();
        boolean z = false;
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i >= size) {
                break;
            }
            String a2 = mp.a(this.b.get(i).f1338a);
            if (!this.m && !"<unknown ssid>".equals(this.b.get(i).b)) {
                z = true;
            }
            if (TextUtils.isEmpty(this.u) || !this.u.equals(a2)) {
                z3 = z2;
                str = "nb";
            } else {
                str = "access";
            }
            this.k.append(String.format(Locale.US, "#%s,%s", a2, str));
            i++;
            z2 = z3;
        }
        if (this.b.size() == 0) {
            z = true;
        }
        if (!this.m && !z) {
            this.j = true;
        }
        if (!z2 && !TextUtils.isEmpty(this.u)) {
            StringBuilder sb2 = this.k;
            sb2.append("#");
            sb2.append(this.u);
            this.k.append(",access");
        }
        return this.k.toString();
    }

    public final void c(boolean z) {
        g();
        this.b.clear();
        this.t.a(z);
    }

    public static String p() {
        return String.valueOf(com.autonavi.aps.amapapi.utils.i.b() - g);
    }

    public final long q() {
        return this.v;
    }
}
