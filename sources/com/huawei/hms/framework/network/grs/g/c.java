package com.huawei.hms.framework.network.grs.g;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.h.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class c {
    private static final String l = "c";

    /* renamed from: a, reason: collision with root package name */
    private final GrsBaseInfo f4541a;
    private final Context b;
    private final com.huawei.hms.framework.network.grs.e.a c;
    private d d;
    private final com.huawei.hms.framework.network.grs.g.j.c i;
    private final Map<String, Future<d>> e = new ConcurrentHashMap(16);
    private final List<d> f = new CopyOnWriteArrayList();
    private final JSONArray g = new JSONArray();
    private final List<String> h = new CopyOnWriteArrayList();
    private String j = "";
    private long k = 1;

    public void a(com.huawei.hms.framework.network.grs.g.j.d dVar) {
    }

    public void a(d dVar) {
        synchronized (this) {
            this.f.add(dVar);
            d dVar2 = this.d;
            if (dVar2 != null && (dVar2.o() || this.d.m())) {
                Logger.v(l, "grsResponseResult is ok");
                return;
            }
            if (dVar.n()) {
                Logger.i(l, "GRS server open 503 limiting strategy.");
                com.huawei.hms.framework.network.grs.h.d.a(this.f4541a.getGrsParasKey(true, true, this.b), new d.a(dVar.k(), SystemClock.elapsedRealtime()));
                return;
            }
            if (dVar.m()) {
                Logger.i(l, "GRS server open 304 Not Modified.");
            }
            if (!dVar.o() && !dVar.m()) {
                Logger.v(l, "grsResponseResult has exception so need return");
                return;
            }
            this.d = dVar;
            this.c.a(this.f4541a, dVar, this.b, this.i);
            for (Map.Entry<String, Future<d>> entry : this.e.entrySet()) {
                if (!entry.getKey().equals(dVar.l()) && !entry.getValue().isCancelled()) {
                    Logger.i(l, "future cancel");
                    entry.getValue().cancel(true);
                }
            }
        }
    }

    public String a() {
        return this.j;
    }

    public d a(ExecutorService executorService, String str, com.huawei.hms.framework.network.grs.e.c cVar) {
        if (this.h.isEmpty()) {
            return null;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        d a2 = a(executorService, this.h, str, cVar);
        Logger.i(l, "use 2.0 interface return http's code is：{%d}", Integer.valueOf(a2 == null ? 0 : a2.b()));
        e.a(new ArrayList(this.f), SystemClock.elapsedRealtime() - elapsedRealtime, this.g, this.b);
        this.f.clear();
        return a2;
    }

    private String d() {
        com.huawei.hms.framework.network.grs.f.b a2 = com.huawei.hms.framework.network.grs.f.b.a(this.b.getPackageName());
        com.huawei.hms.framework.network.grs.local.model.a a3 = a2 != null ? a2.a() : null;
        if (a3 == null) {
            return "";
        }
        String a4 = a3.a();
        Logger.v(l, "get appName from local assets is{%s}", a4);
        return a4;
    }

    private void c() {
        String grsParasKey = this.f4541a.getGrsParasKey(true, true, this.b);
        this.j = this.c.a().a(grsParasKey + "ETag", "");
    }

    private void b() {
        com.huawei.hms.framework.network.grs.g.j.d a2 = com.huawei.hms.framework.network.grs.g.i.a.a(this.b);
        if (a2 == null) {
            Logger.w(l, "g*s***_se****er_conf*** maybe has a big error");
            return;
        }
        a(a2);
        List<String> a3 = a2.a();
        if (a3 == null || a3.size() <= 0) {
            Logger.v(l, "maybe grs_base_url config with [],please check.");
            return;
        }
        if (a3.size() > 10) {
            throw new IllegalArgumentException("grs_base_url's count is larger than MAX value 10");
        }
        String b = a2.b();
        if (a3.size() > 0) {
            for (String str : a3) {
                if (str.startsWith("https://")) {
                    a(b, str);
                } else {
                    Logger.w(l, "grs server just support https scheme url,please check.");
                }
            }
        }
        Logger.v(l, "request to GRS server url is {%s}", this.h);
    }

    private d b(d dVar) {
        String str;
        String str2;
        for (Map.Entry<String, Future<d>> entry : this.e.entrySet()) {
            if (dVar != null && (dVar.o() || dVar.m())) {
                break;
            }
            try {
                dVar = entry.getValue().get(40000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e = e;
                str = l;
                str2 = "{checkResponse} when check result, find InterruptedException, check others";
                Logger.w(str, str2, e);
            } catch (CancellationException unused) {
                Logger.i(l, "{checkResponse} when check result, find CancellationException, check others");
            } catch (ExecutionException e2) {
                e = e2;
                str = l;
                str2 = "{checkResponse} when check result, find ExecutionException, check others";
                Logger.w(str, str2, e);
            } catch (TimeoutException unused2) {
                Logger.w(l, "{checkResponse} when check result, find TimeoutException, cancel current request task");
                if (!entry.getValue().isCancelled()) {
                    entry.getValue().cancel(true);
                }
            }
        }
        return dVar;
    }

    private void a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str);
        String grsReqParamJoint = this.f4541a.getGrsReqParamJoint(false, false, d(), this.b);
        if (!TextUtils.isEmpty(grsReqParamJoint)) {
            sb.append("?");
            sb.append(grsReqParamJoint);
        }
        this.h.add(sb.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0097 A[LOOP:0: B:2:0x0005->B:13:0x0097, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x008f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.framework.network.grs.g.d a(java.util.concurrent.ExecutorService r16, java.util.List<java.lang.String> r17, java.lang.String r18, com.huawei.hms.framework.network.grs.e.c r19) {
        /*
            r15 = this;
            r9 = r15
            r0 = 0
            r10 = 0
            r11 = r0
            r12 = r10
        L5:
            int r0 = r17.size()
            if (r12 >= r0) goto L9b
            r13 = r17
            java.lang.Object r0 = r13.get(r12)
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L8a
            com.huawei.hms.framework.network.grs.g.a r14 = new com.huawei.hms.framework.network.grs.g.a
            android.content.Context r5 = r9.b
            com.huawei.hms.framework.network.grs.GrsBaseInfo r7 = r9.f4541a
            r1 = r14
            r2 = r0
            r3 = r12
            r4 = r15
            r6 = r18
            r8 = r19
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            java.util.concurrent.Callable r1 = r14.g()
            r2 = r16
            java.util.concurrent.Future r1 = r2.submit(r1)
            java.util.Map<java.lang.String, java.util.concurrent.Future<com.huawei.hms.framework.network.grs.g.d>> r3 = r9.e
            r3.put(r0, r1)
            r3 = 1
            long r4 = r9.k     // Catch: java.util.concurrent.TimeoutException -> L68 java.lang.InterruptedException -> L70 java.util.concurrent.ExecutionException -> L79 java.util.concurrent.CancellationException -> L82
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.util.concurrent.TimeoutException -> L68 java.lang.InterruptedException -> L70 java.util.concurrent.ExecutionException -> L79 java.util.concurrent.CancellationException -> L82
            java.lang.Object r0 = r1.get(r4, r0)     // Catch: java.util.concurrent.TimeoutException -> L68 java.lang.InterruptedException -> L70 java.util.concurrent.ExecutionException -> L79 java.util.concurrent.CancellationException -> L82
            r1 = r0
            com.huawei.hms.framework.network.grs.g.d r1 = (com.huawei.hms.framework.network.grs.g.d) r1     // Catch: java.util.concurrent.TimeoutException -> L68 java.lang.InterruptedException -> L70 java.util.concurrent.ExecutionException -> L79 java.util.concurrent.CancellationException -> L82
            if (r1 == 0) goto L66
            boolean r0 = r1.o()     // Catch: java.util.concurrent.TimeoutException -> L5c java.lang.InterruptedException -> L5e java.util.concurrent.ExecutionException -> L61 java.util.concurrent.CancellationException -> L64
            if (r0 != 0) goto L53
            boolean r0 = r1.m()     // Catch: java.util.concurrent.TimeoutException -> L5c java.lang.InterruptedException -> L5e java.util.concurrent.ExecutionException -> L61 java.util.concurrent.CancellationException -> L64
            if (r0 == 0) goto L66
        L53:
            java.lang.String r0 = com.huawei.hms.framework.network.grs.g.c.l     // Catch: java.util.concurrent.TimeoutException -> L5c java.lang.InterruptedException -> L5e java.util.concurrent.ExecutionException -> L61 java.util.concurrent.CancellationException -> L64
            java.lang.String r4 = "grs request return body is not null and is OK."
            com.huawei.hms.framework.common.Logger.i(r0, r4)     // Catch: java.util.concurrent.TimeoutException -> L5c java.lang.InterruptedException -> L5e java.util.concurrent.ExecutionException -> L61 java.util.concurrent.CancellationException -> L64
            r11 = r1
            goto L8d
        L5c:
            r11 = r1
            goto L68
        L5e:
            r0 = move-exception
            r11 = r1
            goto L71
        L61:
            r0 = move-exception
            r11 = r1
            goto L7a
        L64:
            r11 = r1
            goto L82
        L66:
            r11 = r1
            goto L8c
        L68:
            java.lang.String r0 = com.huawei.hms.framework.network.grs.g.c.l
            java.lang.String r1 = "the wait timed out"
            com.huawei.hms.framework.common.Logger.w(r0, r1)
            goto L8c
        L70:
            r0 = move-exception
        L71:
            java.lang.String r1 = com.huawei.hms.framework.network.grs.g.c.l
            java.lang.String r4 = "the current thread was interrupted while waiting"
            com.huawei.hms.framework.common.Logger.w(r1, r4, r0)
            goto L8d
        L79:
            r0 = move-exception
        L7a:
            java.lang.String r1 = com.huawei.hms.framework.network.grs.g.c.l
            java.lang.String r3 = "the computation threw an ExecutionException"
            com.huawei.hms.framework.common.Logger.w(r1, r3, r0)
            goto L8c
        L82:
            java.lang.String r0 = com.huawei.hms.framework.network.grs.g.c.l
            java.lang.String r1 = "{requestServer} the computation was cancelled"
            com.huawei.hms.framework.common.Logger.i(r0, r1)
            goto L8d
        L8a:
            r2 = r16
        L8c:
            r3 = r10
        L8d:
            if (r3 == 0) goto L97
            java.lang.String r0 = com.huawei.hms.framework.network.grs.g.c.l
            java.lang.String r1 = "needBreak is true so need break current circulation"
            com.huawei.hms.framework.common.Logger.v(r0, r1)
            goto L9b
        L97:
            int r12 = r12 + 1
            goto L5
        L9b:
            com.huawei.hms.framework.network.grs.g.d r0 = r15.b(r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.g.c.a(java.util.concurrent.ExecutorService, java.util.List, java.lang.String, com.huawei.hms.framework.network.grs.e.c):com.huawei.hms.framework.network.grs.g.d");
    }

    public c(com.huawei.hms.framework.network.grs.g.j.c cVar, com.huawei.hms.framework.network.grs.e.a aVar) {
        this.i = cVar;
        this.f4541a = cVar.b();
        this.b = cVar.a();
        this.c = aVar;
        b();
        c();
    }
}
