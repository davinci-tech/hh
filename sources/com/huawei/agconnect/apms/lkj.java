package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import com.huawei.agconnect.apms.hgf;
import com.huawei.agconnect.apms.log.AgentLog;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Dns;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/* loaded from: classes8.dex */
public class lkj extends Call {
    public wxy abc;
    public Request bcd;
    public Call cde;

    public lkj(OkHttpClient okHttpClient, Request request, wxy wxyVar) {
        super(okHttpClient, request);
        abc(okHttpClient, request, wxyVar);
        this.bcd = request;
        this.cde = okHttpClient.newCall(request);
        this.abc = wxyVar;
    }

    public final void abc(OkHttpClient okHttpClient, Request request, wxy wxyVar) {
        gfe gfeVar = new gfe();
        if (gfeVar.bcd != 2 || gfeVar.cde < 6) {
            AgentLog agentLog = hgf.cde;
            if (request == null || request.url() == null || wxyVar == null) {
                return;
            }
            hgf.abc.abc.execute(new ihg(request, wxyVar));
            return;
        }
        AgentLog agentLog2 = jih.abc;
        if (okHttpClient == null) {
            return;
        }
        try {
            if (okHttpClient.getDns() == null) {
                okHttpClient.setDns(Dns.SYSTEM);
            }
            if (okHttpClient.getDns() != null) {
                if (okHttpClient.getDns() instanceof jih) {
                    ((jih) okHttpClient.getDns()).cde = wxyVar;
                } else {
                    jih.abc.debug("set custom dns success.");
                    okHttpClient.setDns(new jih(okHttpClient.getDns(), wxyVar));
                }
            }
        } catch (Throwable th) {
            jih.abc.error("set custom dns class failed: " + th.getMessage());
        }
    }

    public void cancel() {
        this.cde.cancel();
    }

    public void enqueue(Callback callback) {
        abc();
        wxy wxyVar = this.abc;
        Call call = this.cde;
        wxyVar.lkj = call;
        call.enqueue(new kji(callback, wxyVar));
    }

    public Response execute() throws IOException {
        abc();
        wxy wxyVar = this.abc;
        Call call = this.cde;
        wxyVar.lkj = call;
        try {
            Response execute = call.execute();
            if (!abc().edc()) {
                hgf.abc(abc(), execute);
            }
            return execute.newBuilder().body(new dcb(execute.body(), this.abc)).build();
        } catch (IOException e) {
            wxy abc = abc();
            xyz.abc(abc, e);
            if (!abc.edc() && !abc.gfe()) {
                abc.d();
                vwx e2 = abc.e();
                if (e2 != null) {
                    abc.abc(true);
                    rst.cde.add(new HttpEvent(e2, r0.abc()));
                }
            }
            throw e;
        }
    }

    public boolean isCanceled() {
        return this.cde.isCanceled();
    }

    public boolean isExecuted() {
        boolean isExecuted;
        synchronized (this) {
            isExecuted = this.cde.isExecuted();
        }
        return isExecuted;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.huawei.agconnect.apms.wxy abc() {
        /*
            r13 = this;
            com.huawei.agconnect.apms.wxy r0 = r13.abc
            if (r0 != 0) goto Lb
            com.huawei.agconnect.apms.wxy r0 = new com.huawei.agconnect.apms.wxy
            r0.<init>()
            r13.abc = r0
        Lb:
            com.huawei.agconnect.apms.wxy r0 = r13.abc
            com.squareup.okhttp.Request r1 = r13.bcd
            com.huawei.agconnect.apms.log.AgentLog r2 = com.huawei.agconnect.apms.hgf.cde
            if (r1 != 0) goto L14
            goto L80
        L14:
            java.lang.String r2 = r1.urlString()
            java.lang.String r3 = r1.method()
            com.squareup.okhttp.RequestBody r4 = r1.body()
            r5 = -1
            if (r4 == 0) goto L45
            com.squareup.okhttp.RequestBody r4 = r1.body()     // Catch: java.lang.Throwable -> L2d
            long r7 = r4.contentLength()     // Catch: java.lang.Throwable -> L2d
            goto L46
        L2d:
            r4 = move-exception
            com.huawei.agconnect.apms.log.AgentLog r7 = com.huawei.agconnect.apms.hgf.cde
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "failed to get request content length: "
            r8.<init>(r9)
            java.lang.String r4 = r4.getMessage()
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r7.warn(r4)
        L45:
            r7 = r5
        L46:
            r9 = 0
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 >= 0) goto L77
            java.lang.String r4 = "Content-length"
            java.lang.String r1 = r1.header(r4)
            if (r1 == 0) goto L77
            int r4 = r1.length()
            if (r4 <= 0) goto L77
            long r7 = java.lang.Long.parseLong(r1)     // Catch: java.lang.Throwable -> L5f
            goto L77
        L5f:
            r1 = move-exception
            com.huawei.agconnect.apms.log.AgentLog r4 = com.huawei.agconnect.apms.hgf.cde
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "failed to parse content length: "
            r11.<init>(r12)
            java.lang.String r1 = r1.getMessage()
            r11.append(r1)
            java.lang.String r1 = r11.toString()
            r4.warn(r1)
        L77:
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 != 0) goto L7c
            goto L7d
        L7c:
            r9 = r7
        L7d:
            com.huawei.agconnect.apms.xyz.abc(r0, r2, r3, r9)
        L80:
            com.huawei.agconnect.apms.wxy r0 = r13.abc
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.lkj.abc():com.huawei.agconnect.apms.wxy");
    }
}
