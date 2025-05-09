package com.autonavi.aps.amapapi.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.hs;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.restruct.d;
import com.autonavi.aps.amapapi.utils.i;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hms.network.embedded.n1;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    Hashtable<String, ArrayList<C0027a>> f1636a = new Hashtable<>();
    private long i = 0;
    private boolean j = false;
    private String k = "2.0.201501131131".replace(".", "");
    private String l = null;
    boolean b = true;
    long c = 0;
    String d = null;
    d e = null;
    private String m = null;
    private long n = 0;
    boolean f = true;
    boolean g = true;
    String h = String.valueOf(AMapLocationClientOption.GeoLanguage.DEFAULT);

    public final void a(String str, StringBuilder sb, com.autonavi.aps.amapapi.model.a aVar, Context context, boolean z) {
        try {
            if (i.a(aVar)) {
                String str2 = str + "&" + aVar.isOffset() + "&" + aVar.i() + "&" + aVar.j();
                if (!a(str2, aVar) || aVar.e().equals("mem") || aVar.e().equals("file") || aVar.e().equals("wifioff") || OpAnalyticsConstants.WATCH_FAIL_CODE.equals(aVar.d())) {
                    return;
                }
                if (b()) {
                    c();
                }
                JSONObject f = aVar.f();
                if (i.a(f, "offpct")) {
                    f.remove("offpct");
                    aVar.a(f);
                }
                if (str2.contains("wifi")) {
                    if (TextUtils.isEmpty(sb)) {
                        return;
                    }
                    if (aVar.getAccuracy() >= 300.0f) {
                        int i = 0;
                        for (String str3 : sb.toString().split("#")) {
                            if (str3.contains(",")) {
                                i++;
                            }
                        }
                        if (i >= 8) {
                            return;
                        }
                    } else if (aVar.getAccuracy() <= 3.0f) {
                        return;
                    }
                    if (str2.contains("cgiwifi") && !TextUtils.isEmpty(aVar.g())) {
                        String replace = str2.replace("cgiwifi", "cgi");
                        com.autonavi.aps.amapapi.model.a h = aVar.h();
                        if (i.a(h)) {
                            a(replace, new StringBuilder(), h, context, true);
                        }
                    }
                } else if (str2.contains("cgi") && ((sb != null && sb.indexOf(",") != -1) || "4".equals(aVar.d()))) {
                    return;
                }
                com.autonavi.aps.amapapi.model.a a2 = a(str2, sb, false);
                if (i.a(a2) && a2.toStr().equals(aVar.toStr(3))) {
                    return;
                }
                this.i = i.b();
                C0027a c0027a = new C0027a();
                c0027a.a(aVar);
                c0027a.a(TextUtils.isEmpty(sb) ? null : sb.toString());
                if (this.f1636a.containsKey(str2)) {
                    this.f1636a.get(str2).add(c0027a);
                } else {
                    ArrayList<C0027a> arrayList = new ArrayList<>();
                    arrayList.add(c0027a);
                    this.f1636a.put(str2, arrayList);
                }
                if (z) {
                    try {
                        a(str2, aVar, sb, context);
                    } catch (Throwable th) {
                        com.autonavi.aps.amapapi.utils.b.a(th, n1.b, "add");
                    }
                }
            }
        } catch (Throwable th2) {
            com.autonavi.aps.amapapi.utils.b.a(th2, n1.b, "add");
        }
    }

    public final com.autonavi.aps.amapapi.model.a a(Context context, String str, StringBuilder sb, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str) || !com.autonavi.aps.amapapi.utils.a.e()) {
            return null;
        }
        String str2 = str + "&" + this.f + "&" + this.g + "&" + this.h;
        if (str2.contains(GeocodeSearch.GPS) || !com.autonavi.aps.amapapi.utils.a.e() || sb == null) {
            return null;
        }
        if (b()) {
            c();
            return null;
        }
        if (z && !this.j) {
            try {
                String a2 = a(str2, sb, context);
                c();
                a(context, a2, z2);
            } catch (Throwable unused) {
            }
        }
        if (this.f1636a.isEmpty()) {
            return null;
        }
        return a(str2, sb, z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x008b A[Catch: all -> 0x0094, TryCatch #0 {all -> 0x0094, blocks: (B:3:0x0001, B:5:0x0009, B:8:0x0012, B:10:0x001a, B:12:0x0022, B:14:0x0030, B:16:0x0048, B:18:0x0052, B:20:0x0064, B:23:0x006f, B:25:0x0073, B:27:0x0079, B:28:0x0085, B:30:0x008b, B:31:0x008f, B:36:0x0042), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.autonavi.aps.amapapi.model.a a(java.lang.String r5, java.lang.StringBuilder r6, boolean r7) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = "cgiwifi"
            boolean r1 = r5.contains(r1)     // Catch: java.lang.Throwable -> L94
            if (r1 != 0) goto L42
            java.lang.String r1 = "wifi"
            boolean r1 = r5.contains(r1)     // Catch: java.lang.Throwable -> L94
            if (r1 == 0) goto L12
            goto L42
        L12:
            java.lang.String r6 = "cgi"
            boolean r6 = r5.contains(r6)     // Catch: java.lang.Throwable -> L94
            if (r6 == 0) goto L40
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.autonavi.aps.amapapi.storage.a$a>> r6 = r4.f1636a     // Catch: java.lang.Throwable -> L94
            boolean r6 = r6.containsKey(r5)     // Catch: java.lang.Throwable -> L94
            if (r6 == 0) goto L40
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.autonavi.aps.amapapi.storage.a$a>> r6 = r4.f1636a     // Catch: java.lang.Throwable -> L94
            java.lang.Object r6 = r6.get(r5)     // Catch: java.lang.Throwable -> L94
            java.util.ArrayList r6 = (java.util.ArrayList) r6     // Catch: java.lang.Throwable -> L94
            int r6 = r6.size()     // Catch: java.lang.Throwable -> L94
            if (r6 <= 0) goto L40
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.autonavi.aps.amapapi.storage.a$a>> r6 = r4.f1636a     // Catch: java.lang.Throwable -> L94
            java.lang.Object r6 = r6.get(r5)     // Catch: java.lang.Throwable -> L94
            java.util.ArrayList r6 = (java.util.ArrayList) r6     // Catch: java.lang.Throwable -> L94
            r1 = 0
            java.lang.Object r6 = r6.get(r1)     // Catch: java.lang.Throwable -> L94
            com.autonavi.aps.amapapi.storage.a$a r6 = (com.autonavi.aps.amapapi.storage.a.C0027a) r6     // Catch: java.lang.Throwable -> L94
            goto L46
        L40:
            r6 = r0
            goto L46
        L42:
            com.autonavi.aps.amapapi.storage.a$a r6 = r4.a(r6, r5)     // Catch: java.lang.Throwable -> L94
        L46:
            if (r6 == 0) goto L9c
            com.autonavi.aps.amapapi.model.a r1 = r6.a()     // Catch: java.lang.Throwable -> L94
            boolean r1 = com.autonavi.aps.amapapi.utils.i.a(r1)     // Catch: java.lang.Throwable -> L94
            if (r1 == 0) goto L9c
            com.autonavi.aps.amapapi.model.a r1 = r6.a()     // Catch: java.lang.Throwable -> L94
            java.lang.String r2 = "mem"
            r1.e(r2)     // Catch: java.lang.Throwable -> L94
            java.lang.String r2 = r6.b()     // Catch: java.lang.Throwable -> L94
            r1.h(r2)     // Catch: java.lang.Throwable -> L94
            if (r7 != 0) goto L85
            long r2 = r1.getTime()     // Catch: java.lang.Throwable -> L94
            boolean r7 = com.autonavi.aps.amapapi.utils.a.a(r2)     // Catch: java.lang.Throwable -> L94
            if (r7 == 0) goto L6f
            goto L85
        L6f:
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.autonavi.aps.amapapi.storage.a$a>> r7 = r4.f1636a     // Catch: java.lang.Throwable -> L94
            if (r7 == 0) goto L9c
            boolean r7 = r7.containsKey(r5)     // Catch: java.lang.Throwable -> L94
            if (r7 == 0) goto L9c
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.autonavi.aps.amapapi.storage.a$a>> r7 = r4.f1636a     // Catch: java.lang.Throwable -> L94
            java.lang.Object r5 = r7.get(r5)     // Catch: java.lang.Throwable -> L94
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch: java.lang.Throwable -> L94
            r5.remove(r6)     // Catch: java.lang.Throwable -> L94
            goto L9c
        L85:
            boolean r5 = com.autonavi.aps.amapapi.utils.i.a(r1)     // Catch: java.lang.Throwable -> L94
            if (r5 == 0) goto L8f
            r5 = 0
            r4.c = r5     // Catch: java.lang.Throwable -> L94
        L8f:
            r5 = 4
            r1.setLocationType(r5)     // Catch: java.lang.Throwable -> L94
            return r1
        L94:
            r5 = move-exception
            java.lang.String r6 = "Cache"
            java.lang.String r7 = "get1"
            com.autonavi.aps.amapapi.utils.b.a(r5, r6, r7)
        L9c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.storage.a.a(java.lang.String, java.lang.StringBuilder, boolean):com.autonavi.aps.amapapi.model.a");
    }

    private boolean b() {
        long b = i.b();
        long j = this.i;
        return j != 0 && (this.f1636a.size() > 360 || b - j > Constants.ANALYSIS_EVENT_KEEP_TIME);
    }

    private static boolean a(String str, com.autonavi.aps.amapapi.model.a aVar) {
        if (TextUtils.isEmpty(str) || !i.a(aVar) || str.startsWith("#")) {
            return false;
        }
        return str.contains(HAWebViewInterface.NETWORK);
    }

    private void c() {
        this.i = 0L;
        if (!this.f1636a.isEmpty()) {
            this.f1636a.clear();
        }
        this.j = false;
    }

    private C0027a a(StringBuilder sb, String str) {
        C0027a c0027a;
        char c;
        C0027a c0027a2;
        if (this.f1636a.isEmpty() || TextUtils.isEmpty(sb)) {
            return null;
        }
        if (!this.f1636a.containsKey(str)) {
            return null;
        }
        Hashtable hashtable = new Hashtable();
        Hashtable hashtable2 = new Hashtable();
        Hashtable hashtable3 = new Hashtable();
        ArrayList<C0027a> arrayList = this.f1636a.get(str);
        char c2 = 1;
        int size = arrayList.size() - 1;
        while (size >= 0) {
            C0027a c0027a3 = arrayList.get(size);
            if (!TextUtils.isEmpty(c0027a3.b())) {
                if (!a(c0027a3.b(), sb)) {
                    c = 0;
                } else {
                    if (i.a(c0027a3.b(), sb.toString())) {
                        c0027a2 = c0027a3;
                        c0027a = c0027a2;
                        break;
                    }
                    c = c2;
                }
                a(c0027a3.b(), (Hashtable<String, String>) hashtable);
                a(sb.toString(), (Hashtable<String, String>) hashtable2);
                hashtable3.clear();
                Iterator it = hashtable.keySet().iterator();
                while (it.hasNext()) {
                    hashtable3.put((String) it.next(), "");
                }
                Iterator it2 = hashtable2.keySet().iterator();
                while (it2.hasNext()) {
                    hashtable3.put((String) it2.next(), "");
                }
                Set keySet = hashtable3.keySet();
                double[] dArr = new double[keySet.size()];
                double[] dArr2 = new double[keySet.size()];
                Iterator it3 = keySet.iterator();
                int i = 0;
                while (it3 != null && it3.hasNext()) {
                    String str2 = (String) it3.next();
                    double d = 0.0d;
                    dArr[i] = hashtable.containsKey(str2) ? 1.0d : 0.0d;
                    if (hashtable2.containsKey(str2)) {
                        d = 1.0d;
                    }
                    dArr2[i] = d;
                    i++;
                }
                keySet.clear();
                double[] a2 = a(dArr, dArr2);
                if (a2[0] < 0.800000011920929d) {
                    c0027a2 = c0027a3;
                    if (a2[c2] < Math.min(com.autonavi.aps.amapapi.utils.a.g(), 0.618d)) {
                        if (c != 0 && a2[0] >= Math.min(com.autonavi.aps.amapapi.utils.a.g(), 0.618d)) {
                        }
                    }
                    c0027a = c0027a2;
                    break;
                }
                c0027a2 = c0027a3;
                c0027a = c0027a2;
                break;
            }
            size--;
            c2 = 1;
        }
        c0027a = null;
        hashtable.clear();
        hashtable2.clear();
        hashtable3.clear();
        return c0027a;
    }

    private static boolean a(String str, StringBuilder sb) {
        String str2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(sb) || !str.contains(",access") || sb.indexOf(",access") == -1) {
            return false;
        }
        String[] split = str.split(",access");
        if (split[0].contains("#")) {
            String str3 = split[0];
            str2 = str3.substring(str3.lastIndexOf("#") + 1);
        } else {
            str2 = split[0];
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return sb.toString().contains(str2 + ",access");
    }

    private static void a(String str, Hashtable<String, String> hashtable) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        hashtable.clear();
        for (String str2 : str.split("#")) {
            if (!TextUtils.isEmpty(str2) && !str2.contains("|")) {
                hashtable.put(str2, "");
            }
        }
    }

    private static double[] a(double[] dArr, double[] dArr2) {
        double[] dArr3 = new double[3];
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < dArr.length; i3++) {
            double d4 = dArr[i3];
            d2 += d4 * d4;
            double d5 = dArr2[i3];
            d3 += d5 * d5;
            d += d4 * d5;
            if (d5 == 1.0d) {
                i2++;
                if (d4 == 1.0d) {
                    i++;
                }
            }
        }
        dArr3[0] = d / (Math.sqrt(d2) * Math.sqrt(d3));
        double d6 = i;
        dArr3[1] = (d6 * 1.0d) / i2;
        dArr3[2] = d6;
        for (int i4 = 0; i4 < 2; i4++) {
            if (dArr3[i4] > 1.0d) {
                dArr3[i4] = 1.0d;
            }
        }
        return dArr3;
    }

    public final void a(Context context) {
        if (this.j) {
            return;
        }
        try {
            c();
            a(context, (String) null, false);
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, n1.b, "loadDB");
        }
        this.j = true;
    }

    /* renamed from: com.autonavi.aps.amapapi.storage.a$a, reason: collision with other inner class name */
    static final class C0027a {

        /* renamed from: a, reason: collision with root package name */
        private com.autonavi.aps.amapapi.model.a f1637a = null;
        private String b = null;

        protected C0027a() {
        }

        public final com.autonavi.aps.amapapi.model.a a() {
            return this.f1637a;
        }

        public final void a(com.autonavi.aps.amapapi.model.a aVar) {
            this.f1637a = aVar;
        }

        public final String b() {
            return this.b;
        }

        public final void a(String str) {
            if (TextUtils.isEmpty(str)) {
                this.b = null;
            } else {
                this.b = str.replace("##", "#");
            }
        }
    }

    private String a(String str, StringBuilder sb, Context context) {
        String str2;
        if (context == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            this.l = i.l(context);
            if (str.contains("&")) {
                str = str.substring(0, str.indexOf("&"));
            }
            String substring = str.substring(str.lastIndexOf("#") + 1);
            if (substring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - 12));
            } else if (!TextUtils.isEmpty(sb) && sb.indexOf(",access") != -1) {
                jSONObject.put("cgi", str.substring(0, str.length() - (substring.length() + 9)));
                String[] split = sb.toString().split(",access");
                if (split[0].contains("#")) {
                    String str3 = split[0];
                    str2 = str3.substring(str3.lastIndexOf("#") + 1);
                } else {
                    str2 = split[0];
                }
                jSONObject.put("mmac", str2);
            }
            return hs.b(com.autonavi.aps.amapapi.security.a.a(jSONObject.toString().getBytes("UTF-8"), this.l));
        } catch (Throwable unused) {
            return null;
        }
    }

    private void a(String str, AMapLocation aMapLocation, StringBuilder sb, Context context) throws Exception {
        if (context == null) {
            return;
        }
        if (this.l == null) {
            this.l = i.l(context);
        }
        String a2 = a(str, sb, context);
        StringBuilder sb2 = new StringBuilder();
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
            sb2.append("CREATE TABLE IF NOT EXISTS hist");
            sb2.append(this.k);
            sb2.append(" (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);");
            sQLiteDatabase.execSQL(sb2.toString());
            sb2.delete(0, sb2.length());
            sb2.append("REPLACE INTO ");
            sb2.append("hist");
            sb2.append(this.k);
            sb2.append(" VALUES (?, ?, ?, ?)");
            Object[] objArr = new Object[4];
            objArr[0] = a2;
            byte[] a3 = com.autonavi.aps.amapapi.security.a.a(sb.toString().getBytes("UTF-8"), this.l);
            objArr[1] = a3;
            objArr[2] = com.autonavi.aps.amapapi.security.a.a(aMapLocation.toStr().getBytes("UTF-8"), this.l);
            objArr[3] = Long.valueOf(aMapLocation.getTime());
            for (int i = 1; i < 3; i++) {
                objArr[i] = hs.b((byte[]) objArr[i]);
            }
            sQLiteDatabase.execSQL(sb2.toString(), objArr);
            sb2.delete(0, sb2.length());
        } catch (Throwable th) {
            try {
                com.autonavi.aps.amapapi.utils.b.a(th, "DB", "updateHist");
                sb2.delete(0, sb2.length());
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabase.close();
            } finally {
                sb2.delete(0, sb2.length());
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0284 A[LOOP:0: B:33:0x00d2->B:55:0x0284, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0274 A[EDGE_INSN: B:56:0x0274->B:57:0x0274 BREAK  A[LOOP:0: B:33:0x00d2->B:55:0x0284], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02b1 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02b6 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.content.Context r20, java.lang.String r21, boolean r22) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 722
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.storage.a.a(android.content.Context, java.lang.String, boolean):void");
    }

    private void c(Context context) throws Exception {
        boolean isOpen;
        if (context == null) {
            return;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
            if (!i.a(sQLiteDatabase, "hist")) {
                if (sQLiteDatabase != null) {
                    if (isOpen) {
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            try {
                sQLiteDatabase.delete("hist" + this.k, "time<?", new String[]{String.valueOf(i.a() - Constants.ANALYSIS_EVENT_KEEP_TIME)});
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "DB", "clearHist");
                String message = th.getMessage();
                if (!TextUtils.isEmpty(message)) {
                    message.contains("no such table");
                }
            }
            if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                return;
            }
            sQLiteDatabase.close();
        } catch (Throwable th2) {
            try {
                com.autonavi.aps.amapapi.utils.b.a(th2, "DB", "clearHist p2");
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabase.close();
            } finally {
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            }
        }
    }

    public final void b(Context context) {
        try {
            c();
            c(context);
            this.j = false;
            this.d = null;
            this.n = 0L;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, n1.b, "destroy part");
        }
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.g = aMapLocationClientOption.isNeedAddress();
        this.f = aMapLocationClientOption.isOffset();
        this.b = aMapLocationClientOption.isLocationCacheEnable();
        this.h = String.valueOf(aMapLocationClientOption.getGeoLanguage());
    }

    public final void a(d dVar) {
        this.e = dVar;
    }

    private boolean a(com.autonavi.aps.amapapi.model.a aVar, boolean z) {
        if (a(z)) {
            return aVar == null || com.autonavi.aps.amapapi.utils.a.a(aVar.getTime()) || z;
        }
        return false;
    }

    private boolean a(boolean z) {
        if (com.autonavi.aps.amapapi.utils.a.e() || z) {
            return this.b || com.autonavi.aps.amapapi.utils.a.f() || z;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[Catch: all -> 0x00ec, TryCatch #0 {all -> 0x00ec, blocks: (B:6:0x000f, B:8:0x0015, B:12:0x0029, B:20:0x004b, B:24:0x0053, B:26:0x0061, B:33:0x0086, B:34:0x008f, B:36:0x0093, B:38:0x0099, B:40:0x00a4, B:43:0x00c9, B:45:0x00d8, B:53:0x00e5, B:56:0x00a8, B:58:0x00b1, B:60:0x00b5, B:61:0x00be, B:62:0x0073, B:64:0x0079, B:67:0x0089, B:70:0x0019, B:72:0x001d), top: B:5:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0093 A[Catch: all -> 0x00ec, TryCatch #0 {all -> 0x00ec, blocks: (B:6:0x000f, B:8:0x0015, B:12:0x0029, B:20:0x004b, B:24:0x0053, B:26:0x0061, B:33:0x0086, B:34:0x008f, B:36:0x0093, B:38:0x0099, B:40:0x00a4, B:43:0x00c9, B:45:0x00d8, B:53:0x00e5, B:56:0x00a8, B:58:0x00b1, B:60:0x00b5, B:61:0x00be, B:62:0x0073, B:64:0x0079, B:67:0x0089, B:70:0x0019, B:72:0x001d), top: B:5:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d8 A[Catch: all -> 0x00ec, TryCatch #0 {all -> 0x00ec, blocks: (B:6:0x000f, B:8:0x0015, B:12:0x0029, B:20:0x004b, B:24:0x0053, B:26:0x0061, B:33:0x0086, B:34:0x008f, B:36:0x0093, B:38:0x0099, B:40:0x00a4, B:43:0x00c9, B:45:0x00d8, B:53:0x00e5, B:56:0x00a8, B:58:0x00b1, B:60:0x00b5, B:61:0x00be, B:62:0x0073, B:64:0x0079, B:67:0x0089, B:70:0x0019, B:72:0x001d), top: B:5:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b5 A[Catch: all -> 0x00ec, TryCatch #0 {all -> 0x00ec, blocks: (B:6:0x000f, B:8:0x0015, B:12:0x0029, B:20:0x004b, B:24:0x0053, B:26:0x0061, B:33:0x0086, B:34:0x008f, B:36:0x0093, B:38:0x0099, B:40:0x00a4, B:43:0x00c9, B:45:0x00d8, B:53:0x00e5, B:56:0x00a8, B:58:0x00b1, B:60:0x00b5, B:61:0x00be, B:62:0x0073, B:64:0x0079, B:67:0x0089, B:70:0x0019, B:72:0x001d), top: B:5:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00be A[Catch: all -> 0x00ec, TryCatch #0 {all -> 0x00ec, blocks: (B:6:0x000f, B:8:0x0015, B:12:0x0029, B:20:0x004b, B:24:0x0053, B:26:0x0061, B:33:0x0086, B:34:0x008f, B:36:0x0093, B:38:0x0099, B:40:0x00a4, B:43:0x00c9, B:45:0x00d8, B:53:0x00e5, B:56:0x00a8, B:58:0x00b1, B:60:0x00b5, B:61:0x00be, B:62:0x0073, B:64:0x0079, B:67:0x0089, B:70:0x0019, B:72:0x001d), top: B:5:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.autonavi.aps.amapapi.model.a a(com.autonavi.aps.amapapi.restruct.e r16, boolean r17, com.autonavi.aps.amapapi.model.a r18, com.autonavi.aps.amapapi.restruct.i r19, java.lang.StringBuilder r20, java.lang.String r21, android.content.Context r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.storage.a.a(com.autonavi.aps.amapapi.restruct.e, boolean, com.autonavi.aps.amapapi.model.a, com.autonavi.aps.amapapi.restruct.i, java.lang.StringBuilder, java.lang.String, android.content.Context, boolean):com.autonavi.aps.amapapi.model.a");
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a() {
        this.c = 0L;
        this.d = null;
    }
}
