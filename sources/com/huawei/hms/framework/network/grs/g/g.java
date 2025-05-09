package com.huawei.hms.framework.network.grs.g;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/* loaded from: classes4.dex */
public class g {
    private static final ExecutorService b = ExecutorsUtils.newCachedThreadPool("GrsReqPool");
    private static final Map<String, com.huawei.hms.framework.network.grs.g.j.b> c = new ConcurrentHashMap(16);
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.hms.framework.network.grs.e.a f4544a;

    public void a(String str) {
        synchronized (d) {
            c.remove(str);
        }
    }

    public void a(com.huawei.hms.framework.network.grs.g.j.c cVar, com.huawei.hms.framework.network.grs.b bVar, String str, com.huawei.hms.framework.network.grs.e.c cVar2, int i) {
        b.execute(new b(cVar, str, cVar2, i, bVar));
    }

    public void a(com.huawei.hms.framework.network.grs.e.a aVar) {
        this.f4544a = aVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0063, code lost:
    
        if (r2.a() != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0067, code lost:
    
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.framework.network.grs.g.d a(com.huawei.hms.framework.network.grs.g.j.c r8, java.lang.String r9, com.huawei.hms.framework.network.grs.e.c r10, int r11) {
        /*
            r7 = this;
            java.lang.String r0 = "RequestController"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "request to server with service name is: "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            com.huawei.hms.framework.common.Logger.d(r0, r1)
            com.huawei.hms.framework.network.grs.GrsBaseInfo r0 = r8.b()
            r1 = 1
            android.content.Context r2 = r8.a()
            java.lang.String r0 = r0.getGrsParasKey(r1, r1, r2)
            java.lang.String r1 = "RequestController"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "request spUrlKey: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.huawei.hms.framework.common.Logger.v(r1, r2)
            java.lang.Object r1 = com.huawei.hms.framework.network.grs.g.g.d
            monitor-enter(r1)
            android.content.Context r2 = r8.a()     // Catch: java.lang.Throwable -> Lca
            boolean r2 = com.huawei.hms.framework.common.NetworkUtil.isNetworkAvailable(r2)     // Catch: java.lang.Throwable -> Lca
            r3 = 0
            if (r2 != 0) goto L43
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lca
            return r3
        L43:
            com.huawei.hms.framework.network.grs.h.d$a r2 = com.huawei.hms.framework.network.grs.h.d.a(r0)     // Catch: java.lang.Throwable -> Lca
            java.util.Map<java.lang.String, com.huawei.hms.framework.network.grs.g.j.b> r4 = com.huawei.hms.framework.network.grs.g.g.c     // Catch: java.lang.Throwable -> Lca
            java.lang.Object r5 = r4.get(r0)     // Catch: java.lang.Throwable -> Lca
            com.huawei.hms.framework.network.grs.g.j.b r5 = (com.huawei.hms.framework.network.grs.g.j.b) r5     // Catch: java.lang.Throwable -> Lca
            if (r5 == 0) goto L5d
            boolean r6 = r5.b()     // Catch: java.lang.Throwable -> Lca
            if (r6 != 0) goto L58
            goto L5d
        L58:
            java.util.concurrent.Future r9 = r5.a()     // Catch: java.lang.Throwable -> Lca
            goto L82
        L5d:
            if (r2 == 0) goto L68
            boolean r2 = r2.a()     // Catch: java.lang.Throwable -> Lca
            if (r2 != 0) goto L66
            goto L68
        L66:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lca
            return r3
        L68:
            java.lang.String r2 = "RequestController"
            java.lang.String r5 = "hitGrsRequestBean == null or request block is released."
            com.huawei.hms.framework.common.Logger.d(r2, r5)     // Catch: java.lang.Throwable -> Lca
            java.util.concurrent.ExecutorService r2 = com.huawei.hms.framework.network.grs.g.g.b     // Catch: java.lang.Throwable -> Lca
            com.huawei.hms.framework.network.grs.g.g$a r5 = new com.huawei.hms.framework.network.grs.g.g$a     // Catch: java.lang.Throwable -> Lca
            r5.<init>(r8, r9, r10)     // Catch: java.lang.Throwable -> Lca
            java.util.concurrent.Future r9 = r2.submit(r5)     // Catch: java.lang.Throwable -> Lca
            com.huawei.hms.framework.network.grs.g.j.b r10 = new com.huawei.hms.framework.network.grs.g.j.b     // Catch: java.lang.Throwable -> Lca
            r10.<init>(r9)     // Catch: java.lang.Throwable -> Lca
            r4.put(r0, r10)     // Catch: java.lang.Throwable -> Lca
        L82:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lca
            r10 = -1
            if (r11 == r10) goto L87
            goto L98
        L87:
            android.content.Context r8 = r8.a()
            com.huawei.hms.framework.network.grs.g.j.d r8 = com.huawei.hms.framework.network.grs.g.i.a.a(r8)
            if (r8 == 0) goto L96
            int r11 = r8.c()
            goto L98
        L96:
            r11 = 10
        L98:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r10 = "RequestController"
            java.lang.String r0 = "use grsQueryTimeout %d"
            com.huawei.hms.framework.common.Logger.i(r10, r0, r8)
            long r10 = (long) r11
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Exception -> Lb1 java.util.concurrent.TimeoutException -> Lb5 java.lang.InterruptedException -> Lb9 java.util.concurrent.ExecutionException -> Lbd java.util.concurrent.CancellationException -> Lc1
            java.lang.Object r8 = r9.get(r10, r8)     // Catch: java.lang.Exception -> Lb1 java.util.concurrent.TimeoutException -> Lb5 java.lang.InterruptedException -> Lb9 java.util.concurrent.ExecutionException -> Lbd java.util.concurrent.CancellationException -> Lc1
            com.huawei.hms.framework.network.grs.g.d r8 = (com.huawei.hms.framework.network.grs.g.d) r8     // Catch: java.lang.Exception -> Lb1 java.util.concurrent.TimeoutException -> Lb5 java.lang.InterruptedException -> Lb9 java.util.concurrent.ExecutionException -> Lbd java.util.concurrent.CancellationException -> Lc1
            return r8
        Lb1:
            r8 = move-exception
            java.lang.String r9 = "when check result, find Other Exception, check others"
            goto Lc4
        Lb5:
            r8 = move-exception
            java.lang.String r9 = "when check result, find TimeoutException, check others"
            goto Lc4
        Lb9:
            r8 = move-exception
            java.lang.String r9 = "when check result, find InterruptedException, check others"
            goto Lc4
        Lbd:
            r8 = move-exception
            java.lang.String r9 = "when check result, find ExecutionException, check others"
            goto Lc4
        Lc1:
            r8 = move-exception
            java.lang.String r9 = "when check result, find CancellationException, check others"
        Lc4:
            java.lang.String r10 = "RequestController"
            com.huawei.hms.framework.common.Logger.w(r10, r9, r8)
            return r3
        Lca:
            r8 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lca
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.g.g.a(com.huawei.hms.framework.network.grs.g.j.c, java.lang.String, com.huawei.hms.framework.network.grs.e.c, int):com.huawei.hms.framework.network.grs.g.d");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(d dVar, com.huawei.hms.framework.network.grs.b bVar) {
        if (bVar != null) {
            if (dVar == null) {
                Logger.v("RequestController", "GrsResponse is null");
                bVar.a();
            } else {
                Logger.v("RequestController", "GrsResponse is not null");
                bVar.a(dVar);
            }
        }
    }

    class a implements Callable<d> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.huawei.hms.framework.network.grs.g.j.c f4545a;
        final /* synthetic */ String b;
        final /* synthetic */ com.huawei.hms.framework.network.grs.e.c c;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public d call() {
            return new c(this.f4545a, g.this.f4544a).a(g.b, this.b, this.c);
        }

        a(com.huawei.hms.framework.network.grs.g.j.c cVar, String str, com.huawei.hms.framework.network.grs.e.c cVar2) {
            this.f4545a = cVar;
            this.b = str;
            this.c = cVar2;
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.huawei.hms.framework.network.grs.g.j.c f4546a;
        final /* synthetic */ String b;
        final /* synthetic */ com.huawei.hms.framework.network.grs.e.c c;
        final /* synthetic */ int d;
        final /* synthetic */ com.huawei.hms.framework.network.grs.b e;

        @Override // java.lang.Runnable
        public void run() {
            g gVar = g.this;
            gVar.a(gVar.a(this.f4546a, this.b, this.c, this.d), this.e);
        }

        b(com.huawei.hms.framework.network.grs.g.j.c cVar, String str, com.huawei.hms.framework.network.grs.e.c cVar2, int i, com.huawei.hms.framework.network.grs.b bVar) {
            this.f4546a = cVar;
            this.b = str;
            this.c = cVar2;
            this.d = i;
            this.e = bVar;
        }
    }
}
