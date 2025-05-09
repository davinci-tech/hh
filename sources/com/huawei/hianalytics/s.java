package com.huawei.hianalytics;

import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hianalytics.core.log.HiLog;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class s {

    /* renamed from: a, reason: collision with root package name */
    public static volatile s f3897a;
    public static final List<String> d = Arrays.asList("com.tencent.mm.plugin.appbrand.ui.AppBrandUI%5Cd*", "com.alipay.mobile.nebulax.integration.mpaas.activity.NebulaActivity%5C%24Lite%5Cd*", "com.alipay.mobile.nebulax.xriver.activity.XRiverActivity%5C%24XRiverLite%5Cd*", "com.huawei.fastapp.app.processManager.RpkLoaderActivityEntry%5Cd*", "com.huawei.fastapp.app.processManager.PageLoaderActivityEntryPort%5Cd*", "com.tt.miniapphost.placeholder.MiniAppChildProcessMultiInsActivity%5Cd*", "com.minigame.miniapphost.placeholder.MiniGameActivity%5Cd*", "com.baidu.swan.apps.SwanAppActivity%5Cd*");

    /* renamed from: a, reason: collision with other field name */
    public boolean f74a = true;

    /* renamed from: b, reason: collision with other field name */
    public boolean f77b = true;

    /* renamed from: c, reason: collision with other field name */
    public boolean f80c = true;

    /* renamed from: d, reason: collision with other field name */
    public boolean f82d = true;
    public boolean e = true;

    /* renamed from: a, reason: collision with other field name */
    public String f72a = "all";

    /* renamed from: a, reason: collision with other field name */
    public long f71a = 10800000;

    /* renamed from: b, reason: collision with other field name */
    public long f75b = 43200000;

    /* renamed from: c, reason: collision with other field name */
    public long f78c = 10800000;

    /* renamed from: a, reason: collision with other field name */
    public int f70a = 50;

    /* renamed from: d, reason: collision with other field name */
    public long f81d = 600000;
    public int b = 15;
    public int c = 1000;

    /* renamed from: a, reason: collision with other field name */
    public final List<Pattern> f73a = new ArrayList();

    /* renamed from: b, reason: collision with other field name */
    public final List<String> f76b = new ArrayList();

    /* renamed from: c, reason: collision with other field name */
    public final List<String> f79c = new ArrayList();

    /* renamed from: a, reason: collision with other method in class */
    public Pair<Long, Long> m583a() {
        try {
            long a2 = j.a("global_v2", "lastCollectTime", 0L);
            if (a2 != 0) {
                return Pair.create(Long.valueOf(a2), Long.valueOf(System.currentTimeMillis()));
            }
            long currentTimeMillis = System.currentTimeMillis();
            return Pair.create(Long.valueOf(currentTimeMillis - this.f71a), Long.valueOf(currentTimeMillis));
        } catch (Throwable unused) {
            long currentTimeMillis2 = System.currentTimeMillis();
            return Pair.create(Long.valueOf(currentTimeMillis2 - this.f71a), Long.valueOf(currentTimeMillis2));
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public final void m584a() {
        String a2;
        synchronized (this) {
            try {
                a2 = j.a("global_v2", "DcCfg", "");
            } catch (Throwable th) {
                HiLog.d("DC", "init fail " + th.getMessage());
                m585b();
            }
            if (TextUtils.isEmpty(a2)) {
                m585b();
                return;
            }
            JSONObject jSONObject = new JSONObject(a2);
            this.f74a = jSONObject.optBoolean("collectEnable", false);
            this.f77b = jSONObject.optBoolean("appOpen", false);
            this.f80c = jSONObject.optBoolean("liteApp", false);
            this.f82d = jSONObject.optBoolean("appUsage", false);
            jSONObject.optBoolean("locate", false);
            this.e = jSONObject.optBoolean("pageOpenV2", false);
            this.f71a = jSONObject.optLong("reportInterval", 10800000L);
            this.f75b = jSONObject.optLong("reloadInterval", 43200000L);
            this.f78c = jSONObject.optLong("hmsReportInterval", 10800000L);
            this.f70a = jSONObject.optInt("flushSize", 50);
            this.f81d = jSONObject.optLong("flushInterval", 600000L);
            this.b = jSONObject.optInt("randomBound", 15);
            this.f72a = jSONObject.optString("collectTag", "all");
            this.c = jSONObject.optInt("labelWaitTime", 1000);
            JSONArray optJSONArray = jSONObject.optJSONArray("liteAppPages");
            if (optJSONArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString = optJSONArray.optString(i);
                    if (!TextUtils.isEmpty(optString)) {
                        arrayList.add(URLDecoder.decode(optString, "UTF-8"));
                    }
                }
                a(arrayList);
            }
            String optString2 = jSONObject.optString("pageSwitchListV2", "");
            if (!TextUtils.isEmpty(optString2)) {
                this.f76b.clear();
                this.f76b.addAll(a(optString2));
            }
            String optString3 = jSONObject.optString("pageSwitchBlack", "");
            if (!TextUtils.isEmpty(optString3)) {
                this.f79c.clear();
                this.f79c.addAll(a(optString3));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
    
        if (r5 == 0) goto L53;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.io.ByteArrayOutputStream, java.io.OutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<java.lang.String> a(java.lang.String r7) {
        /*
            java.lang.String r0 = "DcUtil"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            boolean r2 = android.text.TextUtils.isEmpty(r7)
            if (r2 == 0) goto Le
            return r1
        Le:
            r2 = 0
            byte[] r7 = com.huawei.secure.android.common.util.SafeBase64.decode(r7, r2)
            java.lang.String r3 = ""
            if (r7 == 0) goto L95
            int r4 = r7.length
            r5 = 1
            if (r4 < r5) goto L95
            r4 = 0
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L50
            r5.<init>()     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L50
            java.util.zip.InflaterOutputStream r6 = new java.util.zip.InflaterOutputStream     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L47 java.io.IOException -> L4b
            r6.write(r7)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.nio.charset.Charset r7 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.lang.String r7 = r5.toString(r7)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            r6.close()     // Catch: java.io.IOException -> L37
            goto L3f
        L37:
            r3 = move-exception
            java.lang.String r3 = r3.getMessage()
            com.huawei.hianalytics.core.log.HiLog.e(r0, r3)
        L3f:
            r3 = r7
            goto L69
        L41:
            r7 = move-exception
            r4 = r6
            goto L78
        L44:
            r7 = move-exception
            r4 = r6
            goto L52
        L47:
            r7 = move-exception
            r1 = r4
            r4 = r5
            goto L76
        L4b:
            r7 = move-exception
            goto L52
        L4d:
            r7 = move-exception
            r5 = r4
            goto L78
        L50:
            r7 = move-exception
            r5 = r4
        L52:
            java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Throwable -> L47
            com.huawei.hianalytics.core.log.HiLog.e(r0, r7)     // Catch: java.lang.Throwable -> L47
            if (r4 == 0) goto L67
            r4.close()     // Catch: java.io.IOException -> L5f
            goto L67
        L5f:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            com.huawei.hianalytics.core.log.HiLog.e(r0, r7)
        L67:
            if (r5 == 0) goto L95
        L69:
            r5.close()     // Catch: java.io.IOException -> L6d
            goto L95
        L6d:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            com.huawei.hianalytics.core.log.HiLog.e(r0, r7)
            goto L95
        L76:
            r5 = r4
            r4 = r1
        L78:
            if (r4 == 0) goto L86
            r4.close()     // Catch: java.io.IOException -> L7e
            goto L86
        L7e:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.huawei.hianalytics.core.log.HiLog.e(r0, r1)
        L86:
            if (r5 == 0) goto L94
            r5.close()     // Catch: java.io.IOException -> L8c
            goto L94
        L8c:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.huawei.hianalytics.core.log.HiLog.e(r0, r1)
        L94:
            throw r7
        L95:
            boolean r7 = android.text.TextUtils.isEmpty(r3)
            if (r7 == 0) goto L9c
            return r1
        L9c:
            java.lang.String r7 = "\\|"
            java.lang.String[] r7 = r3.split(r7)
            int r0 = r7.length
        La3:
            if (r2 >= r0) goto Lb3
            r3 = r7[r2]
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto Lb0
            r1.add(r3)
        Lb0:
            int r2 = r2 + 1
            goto La3
        Lb3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.s.a(java.lang.String):java.util.List");
    }

    /* renamed from: b, reason: collision with other method in class */
    public final void m585b() {
        this.f74a = true;
        this.f77b = true;
        this.f80c = true;
        this.f82d = true;
        this.e = true;
        this.f71a = 10800000L;
        this.f75b = 43200000L;
        this.f78c = 10800000L;
        this.f70a = 50;
        this.f81d = 600000L;
        this.b = 15;
        this.f72a = "all";
        this.c = 1000;
        try {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                List<String> list = d;
                if (i >= list.size()) {
                    a(arrayList);
                    this.f76b.clear();
                    this.f76b.addAll(a("eJyVUltuGzEMvBHvkGwa1EANL5oa6V/Blbhr1nqBkuws0MOHWifpK47hH0EacYbkkCZ6qIFjSDhDTe3scb4xhQ9c5l9GvwsFQ6GA95BcnThANhgCCVSGW8z0oM/t6kxsHEfHgVrsIzpHZXMCusihr5Lpf+Yo6OkYZQ+YEmxXPU50Lzh5/b9Q2XFJ8YODpaeWciOW5DMG60jOlvgv6VTnrSjtLGfQviGXOsDjdzXsUyhyyTVtZmiaLcVNSov+EvrTguMBDOYdq6sKJc1PcGA6QndCuxfwrxzouA1MdSSyBZ9T0260DOucuhgKqtXyEalZ3N5rDvwFq9m9H+7jwI6WyQNm8KoLaz06TKXKu2W9UGLIXGgBSCaCzev7mla2gb+Szuej2g4kPM5s1XUNUNRWRRPmrLtklyZVUofVH+0do4vT1WIjh4kkCetg73/fux2Z/YXp/7kwXc0l+gf12tNVm8PJvK3ON8z7XuLTvF09Az04dh4="));
                    this.f79c.clear();
                    this.f79c.addAll(a("eJxLzs/VS8zJLEis1MvNT8rMSdXLS00qzUlMLCgoKMqvqNTLLEjW8zANKMpPTi0uDilKzCt2TC7JLMssqQQAqosXBQ=="));
                    return;
                }
                String str = list.get(i);
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(URLDecoder.decode(str, "UTF-8"));
                }
                i++;
            }
        } catch (Throwable th) {
            HiLog.i("DC", "reset fail " + th.getMessage());
        }
    }

    public long b() {
        return this.f71a;
    }

    public final void a(List<String> list) {
        this.f73a.clear();
        for (int i = 0; i < list.size(); i++) {
            try {
                this.f73a.add(Pattern.compile(list.get(i)));
            } catch (Throwable th) {
                HiLog.i("DC", "initLiteAppPagePattern fail: " + th.getMessage());
            }
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public long m582a() {
        return new Random().nextInt(this.b) * 60000;
    }

    public static s a() {
        if (f3897a == null) {
            synchronized (s.class) {
                if (f3897a == null) {
                    f3897a = new s();
                }
            }
        }
        return f3897a;
    }

    public s() {
        m584a();
    }
}
