package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.network.embedded.b6;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* loaded from: classes9.dex */
public class u5 extends v5 {
    public static final String h = "HttpDetectQuery";
    public static final String i = "DETECT_APP_ID";
    public String f;
    public String e = "";
    public Callable<d5> g = new a();

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0099, code lost:
    
        if (r4 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00b5, code lost:
    
        if (r4 == null) goto L25;
     */
    @Override // com.huawei.hms.network.embedded.v5
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hms.network.embedded.w5 b(com.huawei.hms.network.embedded.i5 r9) {
        /*
            r8 = this;
            java.lang.String r0 = "the http detect is coming!"
            java.lang.String r1 = "HttpDetectQuery"
            com.huawei.hms.framework.common.Logger.v(r1, r0)
            r0 = 1
            r9.b(r0)
            android.content.Context r2 = com.huawei.hms.framework.common.ContextHolder.getAppContext()
            java.lang.String r3 = "DETECT_APP_ID"
            java.lang.String r4 = ""
            java.lang.String r2 = com.huawei.hms.framework.common.PackageManagerCompat.getMetaDataFromApp(r2, r3, r4)
            r8.e = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "the obtain appId is:"
            r2.<init>(r3)
            java.lang.String r3 = r8.e
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.huawei.hms.framework.common.Logger.v(r1, r2)
            java.lang.String r2 = r8.e
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            r3 = 0
            if (r2 == 0) goto L41
            r0 = 10030001(0x990bb1, float:1.4055025E-38)
            r9.a(r0)
            com.huawei.hms.network.embedded.w5 r9 = r8.f5539a
            r9.a(r3)
            goto L57
        L41:
            android.content.Context r2 = com.huawei.hms.framework.common.ContextHolder.getResourceContext()
            java.lang.String r2 = com.huawei.hms.network.embedded.c6.a(r2)
            r8.f = r2
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L5a
            r0 = 10030002(0x990bb2, float:1.4055026E-38)
            r9.a(r0)
        L57:
            com.huawei.hms.network.embedded.w5 r9 = r8.f5539a
            return r9
        L5a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "the detectDomainName is :"
            r2.<init>(r4)
            java.lang.String r4 = r8.f
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.huawei.hms.framework.common.Logger.v(r1, r2)
            r2 = 10030000(0x990bb0, float:1.4055024E-38)
            r4 = 0
            java.util.concurrent.ExecutorService r5 = r8.b     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            java.util.concurrent.Callable<com.huawei.hms.network.embedded.d5> r6 = r8.g     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            java.util.concurrent.Future r4 = r5.submit(r6)     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            r6 = 5000(0x1388, double:2.4703E-320)
            java.lang.Object r5 = r4.get(r6, r5)     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            com.huawei.hms.network.embedded.d5 r5 = (com.huawei.hms.network.embedded.d5) r5     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            int r6 = r5.a()     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            r7 = 404(0x194, float:5.66E-43)
            if (r6 != r7) goto L90
            com.huawei.hms.network.embedded.w5 r6 = r8.f5539a     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            r6.a(r3)     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
        L90:
            int r3 = r5.a()     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            java.lang.String r5 = r8.f     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            r9.a(r3, r5)     // Catch: java.lang.Throwable -> L9c java.util.concurrent.RejectedExecutionException -> L9e java.lang.Throwable -> Lab
            if (r4 == 0) goto Lba
            goto Lb7
        L9c:
            r9 = move-exception
            goto Lbd
        L9e:
            java.lang.String r3 = "executorService submit is RejectedExecutionException"
            com.huawei.hms.framework.common.Logger.w(r1, r3)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r1 = r8.f     // Catch: java.lang.Throwable -> L9c
            r9.a(r2, r1)     // Catch: java.lang.Throwable -> L9c
            if (r4 == 0) goto Lba
            goto Lb7
        Lab:
            java.lang.String r3 = "the connect is overTime"
            com.huawei.hms.framework.common.Logger.w(r1, r3)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r1 = r8.f     // Catch: java.lang.Throwable -> L9c
            r9.a(r2, r1)     // Catch: java.lang.Throwable -> L9c
            if (r4 == 0) goto Lba
        Lb7:
            r4.cancel(r0)
        Lba:
            com.huawei.hms.network.embedded.w5 r9 = r8.f5539a
            return r9
        Lbd:
            if (r4 == 0) goto Lc2
            r4.cancel(r0)
        Lc2:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.embedded.u5.b(com.huawei.hms.network.embedded.i5):com.huawei.hms.network.embedded.w5");
    }

    public class a implements Callable<d5> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public d5 call() {
            return c6.a(ContextHolder.getAppContext(), u5.this.f + b6.e.f5176a, u5.this.e);
        }

        public a() {
        }
    }

    public u5(ExecutorService executorService) {
        this.b = executorService;
    }
}
