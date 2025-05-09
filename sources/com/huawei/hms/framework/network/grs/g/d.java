package com.huawei.hms.framework.network.grs.g;

import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.embedded.k;
import com.huawei.hms.network.embedded.q0;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class d {
    private static final String o = "d";

    /* renamed from: a, reason: collision with root package name */
    private Map<String, List<String>> f4542a;
    private byte[] b;
    private int c;
    private long d;
    private long e;
    private long f;
    private String g;
    private int h;
    private int i;
    private String j;
    private long k;
    private String l;
    private Exception m;
    private String n;

    private void d(String str) {
    }

    private void e(String str) {
    }

    public void a(int i) {
    }

    public boolean o() {
        return this.c == 200;
    }

    public boolean n() {
        return this.c == 503;
    }

    public boolean m() {
        return this.c == 304;
    }

    public String l() {
        return this.n;
    }

    public long k() {
        return this.k;
    }

    public String j() {
        return this.g;
    }

    public long i() {
        return this.d;
    }

    public long h() {
        return this.e;
    }

    public long g() {
        return this.f;
    }

    public int f() {
        return this.h;
    }

    public String e() {
        return this.l;
    }

    public Exception d() {
        return this.m;
    }

    public int c() {
        return this.i;
    }

    public void b(String str) {
        this.n = str;
    }

    public void b(long j) {
        this.e = j;
    }

    public int b() {
        return this.c;
    }

    public void a(String str) {
        this.l = str;
    }

    public void a(long j) {
        this.f = j;
    }

    public String a() {
        return this.j;
    }

    private void s() {
        q();
        p();
    }

    private Map<String, String> r() {
        HashMap hashMap = new HashMap(16);
        Map<String, List<String>> map = this.f4542a;
        if (map == null || map.size() <= 0) {
            Logger.v(o, "parseRespHeaders {respHeaders == null} or {respHeaders.size() <= 0}");
            return hashMap;
        }
        for (Map.Entry<String, List<String>> entry : this.f4542a.entrySet()) {
            String key = entry.getKey();
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                hashMap.put(key, it.next());
            }
        }
        return hashMap;
    }

    private void q() {
        if (o() || n() || m()) {
            Map<String, String> r = r();
            if (r.size() <= 0) {
                Logger.w(o, "parseHeader {headers.size() <= 0}");
                return;
            }
            try {
                if (o() || m()) {
                    b(r);
                    a(r);
                }
                if (n()) {
                    c(r);
                }
            } catch (JSONException e) {
                Logger.w(o, "parseHeader catch JSONException: %s", StringUtils.anonymizeMessage(e.getMessage()));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0050, code lost:
    
        if (r8.getInt("resultCode") == 0) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void p() {
        /*
            r9 = this;
            java.lang.String r0 = "errorDesc"
            java.lang.String r1 = "errorList"
            java.lang.String r2 = "errorCode"
            java.lang.String r3 = "resultCode"
            java.lang.String r4 = "isSuccess"
            boolean r5 = r9.m()
            r6 = 1
            if (r5 == 0) goto L1c
            java.lang.String r0 = com.huawei.hms.framework.network.grs.g.d.o
            java.lang.String r1 = "GRSSDK get httpcode{304} not any changed."
            com.huawei.hms.framework.common.Logger.i(r0, r1)
            r9.c(r6)
            return
        L1c:
            boolean r5 = r9.o()
            r7 = 2
            if (r5 != 0) goto L2e
            java.lang.String r0 = com.huawei.hms.framework.network.grs.g.d.o
            java.lang.String r1 = "GRSSDK parse server body all failed."
            com.huawei.hms.framework.common.Logger.i(r0, r1)
            r9.c(r7)
            return
        L2e:
            byte[] r5 = r9.b     // Catch: org.json.JSONException -> Lb6
            java.lang.String r5 = com.huawei.hms.framework.common.StringUtils.byte2Str(r5)     // Catch: org.json.JSONException -> Lb6
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lb6
            r8.<init>(r5)     // Catch: org.json.JSONException -> Lb6
            boolean r5 = r8.has(r4)     // Catch: org.json.JSONException -> Lb6
            if (r5 == 0) goto L46
            int r3 = r8.getInt(r4)     // Catch: org.json.JSONException -> Lb6
            if (r3 != r6) goto L54
            goto L52
        L46:
            boolean r4 = r8.has(r3)     // Catch: org.json.JSONException -> Lb6
            if (r4 == 0) goto L56
            int r3 = r8.getInt(r3)     // Catch: org.json.JSONException -> Lb6
            if (r3 != 0) goto L54
        L52:
            r3 = r6
            goto L5e
        L54:
            r3 = r7
            goto L5e
        L56:
            java.lang.String r3 = com.huawei.hms.framework.network.grs.g.d.o     // Catch: org.json.JSONException -> Lb6
            java.lang.String r4 = "sth. wrong because server errorcode's key."
            com.huawei.hms.framework.common.Logger.e(r3, r4)     // Catch: org.json.JSONException -> Lb6
            r3 = -1
        L5e:
            java.lang.String r4 = "services"
            if (r3 == r6) goto L69
            boolean r5 = r8.has(r4)     // Catch: org.json.JSONException -> Lb6
            if (r5 == 0) goto L69
            r3 = 0
        L69:
            r9.c(r3)     // Catch: org.json.JSONException -> Lb6
            java.lang.String r5 = ""
            if (r3 == r6) goto L91
            if (r3 != 0) goto L73
            goto L91
        L73:
            boolean r1 = r8.has(r2)     // Catch: org.json.JSONException -> Lb6
            if (r1 == 0) goto L7e
            int r1 = r8.getInt(r2)     // Catch: org.json.JSONException -> Lb6
            goto L80
        L7e:
            r1 = 9001(0x2329, float:1.2613E-41)
        L80:
            r9.b(r1)     // Catch: org.json.JSONException -> Lb6
            boolean r1 = r8.has(r0)     // Catch: org.json.JSONException -> Lb6
            if (r1 == 0) goto L8d
            java.lang.String r5 = r8.getString(r0)     // Catch: org.json.JSONException -> Lb6
        L8d:
            r9.d(r5)     // Catch: org.json.JSONException -> Lb6
            goto Lcd
        L91:
            boolean r0 = r8.has(r4)     // Catch: org.json.JSONException -> Lb6
            if (r0 == 0) goto La0
            org.json.JSONObject r0 = r8.getJSONObject(r4)     // Catch: org.json.JSONException -> Lb6
            java.lang.String r0 = r0.toString()     // Catch: org.json.JSONException -> Lb6
            goto La1
        La0:
            r0 = r5
        La1:
            r9.f(r0)     // Catch: org.json.JSONException -> Lb6
            boolean r0 = r8.has(r1)     // Catch: org.json.JSONException -> Lb6
            if (r0 == 0) goto Lb2
            org.json.JSONObject r0 = r8.getJSONObject(r1)     // Catch: org.json.JSONException -> Lb6
            java.lang.String r5 = r0.toString()     // Catch: org.json.JSONException -> Lb6
        Lb2:
            r9.e(r5)     // Catch: org.json.JSONException -> Lb6
            goto Lcd
        Lb6:
            r0 = move-exception
            java.lang.String r1 = com.huawei.hms.framework.network.grs.g.d.o
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r2 = "GrsResponse GrsResponse(String result) JSONException: %s"
            com.huawei.hms.framework.common.Logger.w(r1, r2, r0)
            r9.c(r7)
        Lcd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.g.d.p():void");
    }

    private void f(String str) {
        this.g = str;
    }

    private void c(Map<String, String> map) {
        long j;
        if (map.containsKey(q0.f)) {
            String str = map.get(q0.f);
            if (!TextUtils.isEmpty(str)) {
                try {
                    j = Long.parseLong(str);
                } catch (NumberFormatException e) {
                    Logger.w(o, "getRetryAfter addHeadersToResult NumberFormatException", e);
                }
                long j2 = j * 1000;
                Logger.v(o, "convert retry-afterTime{%s}", Long.valueOf(j2));
                c(j2);
            }
        }
        j = 0;
        long j22 = j * 1000;
        Logger.v(o, "convert retry-afterTime{%s}", Long.valueOf(j22));
        c(j22);
    }

    private void c(String str) {
        this.j = str;
    }

    private void c(long j) {
        this.k = j;
    }

    private void c(int i) {
        this.h = i;
    }

    private void b(Map<String, String> map) {
        long time;
        if (map.containsKey("Cache-Control")) {
            String str = map.get("Cache-Control");
            if (!TextUtils.isEmpty(str) && str.contains("max-age=")) {
                try {
                    time = Long.parseLong(str.substring(str.indexOf("max-age=") + 8));
                    try {
                        Logger.v(o, "Cache-Control value{%s}", Long.valueOf(time));
                    } catch (NumberFormatException e) {
                        e = e;
                        Logger.w(o, "getExpireTime addHeadersToResult NumberFormatException", e);
                        if (time > 0) {
                        }
                        time = k.b.m;
                        long j = time * 1000;
                        Logger.i(o, "convert expireTime{%s}", Long.valueOf(j));
                        c(String.valueOf(j + System.currentTimeMillis()));
                    }
                } catch (NumberFormatException e2) {
                    e = e2;
                    time = 0;
                }
            }
            time = 0;
        } else {
            if (map.containsKey("Expires")) {
                String str2 = map.get("Expires");
                Logger.v(o, "expires is{%s}", str2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.ROOT);
                String str3 = map.containsKey("Date") ? map.get("Date") : null;
                try {
                    time = (simpleDateFormat.parse(str2).getTime() - (TextUtils.isEmpty(str3) ? new Date() : simpleDateFormat.parse(str3)).getTime()) / 1000;
                } catch (ParseException e3) {
                    Logger.w(o, "getExpireTime ParseException.", e3);
                }
            } else {
                Logger.i(o, "response headers neither contains Cache-Control nor Expires.");
            }
            time = 0;
        }
        if (time > 0 || time > 2592000) {
            time = k.b.m;
        }
        long j2 = time * 1000;
        Logger.i(o, "convert expireTime{%s}", Long.valueOf(j2));
        c(String.valueOf(j2 + System.currentTimeMillis()));
    }

    private void b(int i) {
        this.i = i;
    }

    private void a(Map<String, String> map) {
        String str;
        String str2;
        if (map.containsKey("ETag")) {
            String str3 = map.get("ETag");
            if (!TextUtils.isEmpty(str3)) {
                Logger.i(o, "success get Etag from server");
                a(str3);
                return;
            } else {
                str = o;
                str2 = "The Response Heads Etag is Empty";
            }
        } else {
            str = o;
            str2 = "Response Heads has not Etag";
        }
        Logger.i(str, str2);
    }

    public d(Exception exc, long j) {
        this.c = 0;
        this.h = 2;
        this.i = 9001;
        this.j = "";
        this.k = 0L;
        this.l = "";
        this.m = exc;
        this.d = j;
    }

    public d(int i, Map<String, List<String>> map, byte[] bArr, long j) {
        this.h = 2;
        this.i = 9001;
        this.j = "";
        this.k = 0L;
        this.l = "";
        this.c = i;
        this.f4542a = map;
        this.b = ByteBuffer.wrap(bArr).array();
        this.d = j;
        s();
    }
}
