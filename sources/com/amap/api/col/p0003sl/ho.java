package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.jx;
import com.amap.api.col.p0003sl.ka;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.ble.BleConstants;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class ho {
    private static volatile boolean C = false;

    /* renamed from: a, reason: collision with root package name */
    public static int f1133a = -1;
    public static String b = "";
    public static Context c = null;
    private static String k = "6";
    private static String l = "4";
    private static String m = "9";
    private static String n = "8";
    private static volatile boolean o = true;
    private static Vector<e> p = new Vector<>();
    private static Map<String, Integer> q = new HashMap();
    private static String r = null;
    private static long s = 0;
    public static volatile boolean d = false;
    private static volatile ConcurrentHashMap<String, Long> t = new ConcurrentHashMap<>(8);
    private static volatile ConcurrentHashMap<String, Long> u = new ConcurrentHashMap<>(8);
    private static volatile ConcurrentHashMap<String, d> v = new ConcurrentHashMap<>(8);
    private static boolean w = false;
    public static int e = 5000;
    public static boolean f = true;
    public static boolean g = false;
    private static int x = 3;
    public static boolean h = true;
    public static boolean i = false;
    private static int y = 3;
    public static boolean j = false;
    private static ConcurrentHashMap<String, Boolean> z = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Boolean> A = new ConcurrentHashMap<>();
    private static ArrayList<jx.a> B = new ArrayList<>();
    private static Queue<jx.c> D = new LinkedList();

    public interface a {
        void a(b bVar);
    }

    public static final class f {

        /* renamed from: a, reason: collision with root package name */
        public static boolean f1140a = true;
        public static boolean b = false;
        public static boolean c = true;
        public static int d = 0;
        public static boolean e = false;
        public static int f;
    }

    public static void a(Context context, String str) {
        hn.a(context, str);
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        @Deprecated
        public JSONObject f1135a;

        @Deprecated
        public JSONObject b;
        public String c;
        public int d = -1;
        public long e = 0;
        public JSONObject f;
        public a g;
        public C0021b h;
        private boolean i;

        public static final class a {

            /* renamed from: a, reason: collision with root package name */
            public boolean f1136a;
            public boolean b;
            public JSONObject c;
        }

        /* renamed from: com.amap.api.col.3sl.ho$b$b, reason: collision with other inner class name */
        public static final class C0021b {

            /* renamed from: a, reason: collision with root package name */
            public boolean f1137a;
        }
    }

    public static boolean a(String str, boolean z2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return z2;
            }
            String[] split = URLDecoder.decode(str).split("/");
            return split[split.length - 1].charAt(4) % 2 == 1;
        } catch (Throwable unused) {
            return z2;
        }
    }

    public static b a(Context context, hz hzVar, String str, Map<String, String> map) {
        return b(context, hzVar, str, map);
    }

    private static b b(Context context, hz hzVar, String str, Map<String, String> map) {
        return a(context, hzVar, str, map, null, null, null);
    }

    public static b a(Context context, hz hzVar, String str, String str2, String str3, String str4) {
        return a(context, hzVar, str, null, str2, str3, str4);
    }

    public static void a(Context context) {
        if (context != null) {
            c = context.getApplicationContext();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01bd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01be  */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v14, types: [com.amap.api.col.3sl.ho$b] */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9, types: [com.amap.api.col.3sl.ho$b] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.amap.api.col.3sl.ho.b a(android.content.Context r23, com.amap.api.col.p0003sl.hz r24, java.lang.String r25, java.util.Map<java.lang.String, java.lang.String> r26, java.lang.String r27, java.lang.String r28, java.lang.String r29) {
        /*
            Method dump skipped, instructions count: 643
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.ho.a(android.content.Context, com.amap.api.col.3sl.hz, java.lang.String, java.util.Map, java.lang.String, java.lang.String, java.lang.String):com.amap.api.col.3sl.ho$b");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x02c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0209 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.content.Context r20, com.amap.api.col.p0003sl.hz r21, java.lang.String r22, com.amap.api.col.3sl.ho.b r23, org.json.JSONObject r24) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 758
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.ho.a(android.content.Context, com.amap.api.col.3sl.hz, java.lang.String, com.amap.api.col.3sl.ho$b, org.json.JSONObject):void");
    }

    private static void a(Context context, hz hzVar, Throwable th) {
        a(context, hzVar, th.getMessage());
    }

    public static void a(String str, boolean z2, boolean z3, boolean z4) {
        if (TextUtils.isEmpty(str) || c == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("downLevel", String.valueOf(z2));
        hashMap.put("ant", hr.o(c) == 0 ? "0" : "1");
        if (z4) {
            hashMap.put("type", z2 ? m : n);
        } else {
            hashMap.put("type", z2 ? k : l);
        }
        hashMap.put("status", z3 ? "0" : "1");
        String jSONObject = new JSONObject(hashMap).toString();
        if (TextUtils.isEmpty(jSONObject)) {
            return;
        }
        try {
            ki kiVar = new ki(c, "core", "2.0", "O002");
            kiVar.a(jSONObject);
            kj.a(kiVar, c);
        } catch (hm unused) {
        }
    }

    public static void a(jx.c cVar) {
        if (cVar == null || c == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("serverip", cVar.c);
        hashMap.put("hostname", cVar.e);
        hashMap.put(BleConstants.KEY_PATH, cVar.d);
        hashMap.put("csid", cVar.f1240a);
        hashMap.put("degrade", String.valueOf(cVar.b.a()));
        hashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(cVar.m));
        hashMap.put("errorsubcode", String.valueOf(cVar.n));
        hashMap.put("connecttime", String.valueOf(cVar.h));
        hashMap.put("writetime", String.valueOf(cVar.i));
        hashMap.put("readtime", String.valueOf(cVar.j));
        hashMap.put("datasize", String.valueOf(cVar.l));
        hashMap.put("totaltime", String.valueOf(cVar.f));
        String jSONObject = new JSONObject(hashMap).toString();
        "--埋点--".concat(String.valueOf(jSONObject));
        jx.b();
        if (TextUtils.isEmpty(jSONObject)) {
            return;
        }
        try {
            ki kiVar = new ki(c, "core", "2.0", "O008");
            kiVar.a(jSONObject);
            kj.a(kiVar, c);
        } catch (hm unused) {
        }
    }

    private static void a(Context context, hz hzVar, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("amap_sdk_auth_fail", "1");
        hashMap.put("amap_sdk_auth_fail_type", str);
        hashMap.put("amap_sdk_name", hzVar.a());
        hashMap.put("amap_sdk_version", hzVar.c());
        String jSONObject = new JSONObject(hashMap).toString();
        if (TextUtils.isEmpty(jSONObject)) {
            return;
        }
        try {
            ki kiVar = new ki(context, "core", "2.0", "O001");
            kiVar.a(jSONObject);
            kj.a(kiVar, context);
        } catch (hm unused) {
        }
    }

    static final class c extends ju {
        private String h;
        private Map<String, String> i;
        private String j;
        private String k;
        private String l;

        @Override // com.amap.api.col.p0003sl.ju
        public final byte[] c() {
            return null;
        }

        c(Context context, hz hzVar, String str, Map<String, String> map, String str2, String str3, String str4) {
            super(context, hzVar);
            this.h = str;
            this.i = map;
            this.j = str2;
            this.k = str3;
            this.l = str4;
            setHttpProtocol(ka.c.HTTPS);
            setDegradeAbility(ka.a.FIX);
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final Map<String, String> getRequestHead() {
            if (TextUtils.isEmpty(this.l)) {
                return null;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("host", this.l);
            return hashMap;
        }

        @Override // com.amap.api.col.p0003sl.ka
        public final String getURL() {
            return a("https://restsdk.amap.com/v3/iasdkauth", this.j);
        }

        @Override // com.amap.api.col.p0003sl.hu, com.amap.api.col.p0003sl.ka
        public final String getIPV6URL() {
            return a("https://dualstack-arestapi.amap.com/v3/iasdkauth", this.k);
        }

        private static String a(String str, String str2) {
            try {
                return !TextUtils.isEmpty(str2) ? Uri.parse(str).buildUpon().encodedAuthority(str2).build().toString() : str;
            } catch (Throwable unused) {
                return str;
            }
        }

        @Override // com.amap.api.col.p0003sl.ka
        protected final String getIPDNSName() {
            if (!TextUtils.isEmpty(this.l)) {
                return this.l;
            }
            return super.getIPDNSName();
        }

        @Override // com.amap.api.col.p0003sl.ju
        public final byte[] d() {
            String u = hr.u(this.f1231a);
            if (!TextUtils.isEmpty(u)) {
                u = hv.b(new StringBuilder(u).reverse().toString());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("authkey", TextUtils.isEmpty(this.h) ? "" : this.h);
            hashMap.put("plattype", OsType.ANDROID);
            hashMap.put("product", this.b.a());
            hashMap.put("version", this.b.b());
            hashMap.put("output", "json");
            StringBuilder sb = new StringBuilder();
            sb.append(Build.VERSION.SDK_INT);
            hashMap.put("androidversion", sb.toString());
            hashMap.put("deviceId", u);
            hashMap.put("manufacture", Build.MANUFACTURER);
            Map<String, String> map = this.i;
            if (map != null && !map.isEmpty()) {
                hashMap.putAll(this.i);
            }
            hashMap.put("abitype", ia.a(this.f1231a));
            hashMap.put("ext", this.b.d());
            return ia.a(ia.a(hashMap));
        }

        @Override // com.amap.api.col.p0003sl.ju
        protected final String e() {
            return "3.0";
        }
    }

    public static boolean a() {
        e a2;
        if (c != null) {
            i();
            if (!c()) {
                return false;
            }
            if (b()) {
                return true;
            }
        }
        return o && (a2 = a(c, "IPV6_CONFIG_NAME", "open_common")) != null && a2.a() < 5;
    }

    private static boolean a(InetAddress inetAddress) {
        return inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isAnyLocalAddress();
    }

    private static void i() {
        try {
            Context context = c;
            if (context != null) {
                String t2 = hr.t(context);
                if (!TextUtils.isEmpty(r) && !TextUtils.isEmpty(t2) && r.equals(t2) && System.currentTimeMillis() - s < 60000) {
                    return;
                }
                if (!TextUtils.isEmpty(t2)) {
                    r = t2;
                }
            } else if (System.currentTimeMillis() - s < PreConnectManager.CONNECT_INTERNAL) {
                return;
            }
            s = System.currentTimeMillis();
            q.clear();
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (!networkInterface.getInterfaceAddresses().isEmpty()) {
                    String displayName = networkInterface.getDisplayName();
                    Iterator<InterfaceAddress> it2 = networkInterface.getInterfaceAddresses().iterator();
                    int i2 = 0;
                    while (it2.hasNext()) {
                        InetAddress address = it2.next().getAddress();
                        if (address instanceof Inet6Address) {
                            if (!a((Inet6Address) address)) {
                                i2 |= 2;
                            }
                        } else if (address instanceof Inet4Address) {
                            Inet4Address inet4Address = (Inet4Address) address;
                            if (!a(inet4Address) && !inet4Address.getHostAddress().startsWith(ia.c("FMTkyLjE2OC40My4"))) {
                                i2 |= 1;
                            }
                        }
                    }
                    if (i2 != 0) {
                        if (displayName != null && displayName.startsWith("wlan")) {
                            q.put("WIFI", Integer.valueOf(i2));
                        } else if (displayName != null && displayName.startsWith("rmnet")) {
                            q.put("MOBILE", Integer.valueOf(i2));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            is.a(th, "at", "ipstack");
        }
    }

    public static boolean b() {
        Integer num;
        Context context = c;
        if (context == null) {
            return false;
        }
        String t2 = hr.t(context);
        return (TextUtils.isEmpty(t2) || (num = q.get(t2.toUpperCase())) == null || num.intValue() != 2) ? false : true;
    }

    public static boolean c() {
        Integer num;
        Context context = c;
        if (context == null) {
            return false;
        }
        String t2 = hr.t(context);
        return (TextUtils.isEmpty(t2) || (num = q.get(t2.toUpperCase())) == null || num.intValue() < 2) ? false : true;
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        o = ji.a(context, "open_common", "a2", true);
    }

    private static void c(Context context) {
        if (context == null) {
            return;
        }
        f = ji.a(context, "open_common", "a13", true);
        h = ji.a(context, "open_common", "a6", true);
        g = ji.a(context, "open_common", "a7", false);
        e = ji.a(context, "open_common", "a8", 5000);
        x = ji.a(context, "open_common", "a9", 3);
        i = ji.a(context, "open_common", "a10", false);
        y = ji.a(context, "open_common", "a11", 3);
        j = ji.a(context, "open_common", "a12", false);
    }

    private static void a(Context context, String str, String str2, e eVar) {
        if (eVar == null || TextUtils.isEmpty(eVar.f1139a)) {
            return;
        }
        String b2 = eVar.b();
        if (TextUtils.isEmpty(b2) || context == null) {
            return;
        }
        SharedPreferences.Editor a2 = ji.a(context, str2);
        a2.putString(str, b2);
        ji.a(a2);
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private String f1139a;
        private String b;
        private AtomicInteger c;

        public e(String str, String str2, int i) {
            this.f1139a = str;
            this.b = str2;
            this.c = new AtomicInteger(i);
        }

        public final void a(String str) {
            this.b = str;
        }

        public final int a() {
            AtomicInteger atomicInteger = this.c;
            if (atomicInteger == null) {
                return 0;
            }
            return atomicInteger.get();
        }

        public final String b() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(VideoPlayFlag.PLAY_IN_ALL, this.f1139a);
                jSONObject.put(it.i, this.b);
                jSONObject.put("h", this.c.get());
                return jSONObject.toString();
            } catch (Throwable unused) {
                return "";
            }
        }

        public static e b(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new e(jSONObject.optString(VideoPlayFlag.PLAY_IN_ALL), jSONObject.optString(it.i), jSONObject.optInt("h"));
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static void e() {
        if (d) {
            return;
        }
        try {
            Context context = c;
            if (context == null) {
                return;
            }
            d = true;
            ht.a().a(context);
            b(context);
            c(context);
            f.f1140a = ji.a(context, "open_common", "ucf", f.f1140a);
            f.b = ji.a(context, "open_common", "fsv2", f.b);
            f.c = ji.a(context, "open_common", "usc", f.c);
            f.d = ji.a(context, "open_common", "umv", f.d);
            f.e = ji.a(context, "open_common", "ust", f.e);
            f.f = ji.a(context, "open_common", "ustv", f.f);
        } catch (Throwable unused) {
        }
    }

    static final class d {

        /* renamed from: a, reason: collision with root package name */
        hz f1138a;
        String b;
        a c;

        private d() {
        }

        /* synthetic */ d(byte b) {
            this();
        }
    }

    public static void a(Context context, hz hzVar, String str, a aVar) {
        synchronized (ho.class) {
            if (context == null || hzVar == null) {
                return;
            }
            try {
                if (c == null) {
                    c = context.getApplicationContext();
                }
                String a2 = hzVar.a();
                if (TextUtils.isEmpty(a2)) {
                    return;
                }
                a(hzVar);
                if (v == null) {
                    v = new ConcurrentHashMap<>(8);
                }
                if (u == null) {
                    u = new ConcurrentHashMap<>(8);
                }
                if (t == null) {
                    t = new ConcurrentHashMap<>(8);
                }
                if (!v.containsKey(a2)) {
                    d dVar = new d((byte) 0);
                    dVar.f1138a = hzVar;
                    dVar.b = str;
                    dVar.c = aVar;
                    v.put(a2, dVar);
                    t.put(a2, Long.valueOf(ji.b(c, "open_common", a2)));
                    d(c);
                }
            } catch (Throwable th) {
                is.a(th, "at", "rglc");
            }
        }
    }

    public static boolean a(String str, long j2) {
        synchronized (ho.class) {
            boolean z2 = false;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (j2 > c(str)) {
                if (SystemClock.elapsedRealtime() - ((u == null || !u.containsKey(str)) ? 0L : u.get(str).longValue()) > OpAnalyticsConstants.H5_LOADING_DELAY) {
                    z2 = true;
                }
            }
            return z2;
        }
    }

    public static void b(String str, boolean z2) {
        synchronized (ho.class) {
            a(str, z2, (String) null, (String) null, (String) null);
        }
    }

    public static void a(final String str, boolean z2, final String str2, final String str3, final String str4) {
        synchronized (ho.class) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                if (u == null) {
                    u = new ConcurrentHashMap<>(8);
                }
                u.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
                if (v == null) {
                    return;
                }
                if (v.containsKey(str)) {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    if (z2) {
                        jw.a(true, str);
                    }
                    la.a().a(new lb() { // from class: com.amap.api.col.3sl.ho.1
                        @Override // com.amap.api.col.p0003sl.lb
                        public final void runTask() {
                            d dVar = (d) ho.v.get(str);
                            if (dVar == null) {
                                return;
                            }
                            a aVar = dVar.c;
                            b a2 = ho.a(ho.c, dVar.f1138a, dVar.b, str2, str3, str4);
                            if (a2 == null || aVar == null) {
                                return;
                            }
                            aVar.a(a2);
                        }
                    });
                }
            } catch (Throwable th) {
                is.a(th, "at", "lca");
            }
        }
    }

    public static boolean a(String str) {
        synchronized (ho.class) {
            try {
            } catch (Throwable th) {
                is.a(th, "at", "cslct");
            }
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (v == null) {
                return false;
            }
            if (u == null) {
                u = new ConcurrentHashMap<>(8);
            }
            if (v.containsKey(str) && !u.containsKey(str)) {
                u.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
                return true;
            }
            return false;
        }
    }

    public static void b(String str) {
        synchronized (ho.class) {
            if (u == null) {
                return;
            }
            if (u.containsKey(str)) {
                u.remove(str);
            }
        }
    }

    public static long c(String str) {
        synchronized (ho.class) {
            try {
                if (t == null) {
                    t = new ConcurrentHashMap<>(8);
                }
                if (t.containsKey(str)) {
                    return t.get(str).longValue();
                }
            } catch (Throwable th) {
                is.a(th, "at", "glcut");
            }
            return 0L;
        }
    }

    private static void b(String str, long j2) {
        synchronized (ho.class) {
            try {
                if (v != null && v.containsKey(str)) {
                    if (t == null) {
                        t = new ConcurrentHashMap<>(8);
                    }
                    t.put(str, Long.valueOf(j2));
                    Context context = c;
                    if (context != null) {
                        SharedPreferences.Editor a2 = ji.a(context, "open_common");
                        ji.a(a2, str, j2);
                        ji.a(a2);
                    }
                }
            } catch (Throwable th) {
                is.a(th, "at", "ucut");
            }
        }
    }

    private static void a(hz hzVar) {
        if (hzVar != null) {
            try {
                if (TextUtils.isEmpty(hzVar.a())) {
                    return;
                }
                String c2 = hzVar.c();
                if (TextUtils.isEmpty(c2)) {
                    c2 = hzVar.b();
                }
                if (TextUtils.isEmpty(c2)) {
                    return;
                }
                io.a(hzVar.a(), c2);
            } catch (Throwable unused) {
            }
        }
    }

    private static void d(Context context) {
        try {
            if (w) {
                return;
            }
            io.d = ji.a(context, "open_common", "a4", true);
            io.e = ji.a(context, "open_common", "a5", true);
            w = true;
        } catch (Throwable unused) {
        }
    }

    public static void a(boolean z2, String str) {
        try {
            "--markHostNameFailed---hostname=".concat(String.valueOf(str));
            jx.b();
            if (f || z2) {
                if ((i || !z2) && !TextUtils.isEmpty(str)) {
                    if (!z2) {
                        if (z.get(str) != null) {
                            return;
                        }
                        z.put(str, Boolean.TRUE);
                        a(b(str, "a14"), "open_common");
                        return;
                    }
                    if (A.get(str) != null) {
                        return;
                    }
                    A.put(str, Boolean.TRUE);
                    a(b(str, "a15"), "open_common");
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(String str, String str2) {
        e a2 = a(c, str, str2);
        String a3 = ia.a(System.currentTimeMillis(), "yyyyMMdd");
        if (!a3.equals(a2.b)) {
            a2.a(a3);
            a2.c.set(0);
        }
        a2.c.incrementAndGet();
        a(c, str, str2, a2);
    }

    public static boolean d(String str) {
        e a2;
        try {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (f && z.get(str) == null) {
                Context context = c;
                if (context == null || (a2 = a(context, b(str, "a14"), "open_common")) == null) {
                    return true;
                }
                if (a2.a() < x) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean e(String str) {
        e a2;
        try {
            if (!TextUtils.isEmpty(str) && i && A.get(str) == null) {
                Context context = c;
                if (context == null || (a2 = a(context, b(str, "a15"), "open_common")) == null) {
                    return true;
                }
                if (a2.a() < y) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private static String b(String str, String str2) {
        return str2 + "_" + hv.a(str.getBytes());
    }

    public static void b(jx.c cVar) {
        synchronized (B) {
            boolean z2 = false;
            for (int i2 = 0; i2 < B.size(); i2++) {
                jx.a aVar = B.get(i2);
                if (cVar.c.equals(aVar.b) && cVar.d.equals(aVar.e) && cVar.m == aVar.f) {
                    if (aVar.f == 1) {
                        aVar.i = ((aVar.j.get() * aVar.i) + cVar.f) / (aVar.j.get() + 1);
                    }
                    aVar.j.getAndIncrement();
                    z2 = true;
                }
            }
            if (!z2) {
                B.add(new jx.a(cVar));
            }
            jx.b();
        }
    }

    public static jx.a f() {
        if (C) {
            return null;
        }
        synchronized (B) {
            if (C) {
                return null;
            }
            Collections.sort(B);
            if (B.size() <= 0) {
                return null;
            }
            jx.a clone = B.get(0).clone();
            C = true;
            return clone;
        }
    }

    public static void a(boolean z2, jx.a aVar) {
        if (!C || aVar == null) {
            return;
        }
        synchronized (B) {
            if (z2) {
                Iterator<jx.a> it = B.iterator();
                while (it.hasNext()) {
                    jx.a next = it.next();
                    if (next.b.equals(aVar.b) && next.e.equals(aVar.e) && next.f == aVar.f) {
                        if (next.j == aVar.j) {
                            it.remove();
                            jx.b();
                        } else {
                            next.j.set(next.j.get() - aVar.j.get());
                            jx.b();
                        }
                    }
                }
            }
            C = false;
            Iterator<jx.a> it2 = B.iterator();
            jx.b();
            while (it2.hasNext()) {
                jx.a next2 = it2.next();
                String str = next2.e;
                Objects.toString(next2.j);
                int i2 = next2.f;
                jx.b();
            }
            jx.b();
        }
    }

    public static void c(jx.c cVar) {
        if (cVar != null && j) {
            synchronized (D) {
                D.offer(cVar);
                jx.b();
            }
        }
    }

    public static jx.c g() {
        synchronized (D) {
            jx.c poll = D.poll();
            if (poll != null) {
                return poll;
            }
            return null;
        }
    }

    public static void d() {
        try {
            e a2 = a(c, "IPV6_CONFIG_NAME", "open_common");
            String a3 = ia.a(System.currentTimeMillis(), "yyyyMMdd");
            if (!a3.equals(a2.b)) {
                a2.a(a3);
                a2.c.set(0);
            }
            a2.c.incrementAndGet();
            a(c, "IPV6_CONFIG_NAME", "open_common", a2);
        } catch (Throwable unused) {
        }
    }

    private static e a(Context context, String str, String str2) {
        e eVar;
        synchronized (ho.class) {
            if (!TextUtils.isEmpty(str)) {
                for (int i2 = 0; i2 < p.size(); i2++) {
                    eVar = p.get(i2);
                    if (eVar != null && str.equals(eVar.f1139a)) {
                        break;
                    }
                }
            }
            eVar = null;
            if (eVar != null) {
                return eVar;
            }
            if (context == null) {
                return null;
            }
            e b2 = e.b(ji.b(context, str2, str, ""));
            String a2 = ia.a(System.currentTimeMillis(), "yyyyMMdd");
            if (b2 == null) {
                b2 = new e(str, a2, 0);
            }
            if (!a2.equals(b2.b)) {
                b2.a(a2);
                b2.c.set(0);
            }
            p.add(b2);
            return b2;
        }
    }
}
