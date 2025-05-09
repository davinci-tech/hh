package com.huawei.agconnect.apms;

import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.StreamAllocation;

/* loaded from: classes2.dex */
public class a implements Call {
    public wxy abc;
    public Request bcd;
    public Call cde;

    public a(Request request, Call call, wxy wxyVar) {
        this.bcd = request;
        this.cde = call;
        this.abc = wxyVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0087  */
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
            okhttp3.Request r1 = r13.bcd
            com.huawei.agconnect.apms.log.AgentLog r2 = com.huawei.agconnect.apms.d.cde
            if (r1 == 0) goto L8b
            okhttp3.HttpUrl r2 = r1.url()
            if (r2 != 0) goto L1b
            goto L8b
        L1b:
            okhttp3.HttpUrl r2 = r1.url()
            java.lang.String r2 = r2.getUrl()
            java.lang.String r3 = r1.method()
            okhttp3.RequestBody r4 = r1.body()
            r5 = -1
            if (r4 == 0) goto L50
            okhttp3.RequestBody r4 = r1.body()     // Catch: java.lang.Throwable -> L38
            long r7 = r4.contentLength()     // Catch: java.lang.Throwable -> L38
            goto L51
        L38:
            r4 = move-exception
            com.huawei.agconnect.apms.log.AgentLog r7 = com.huawei.agconnect.apms.d.cde
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "failed to get request content length: "
            r8.<init>(r9)
            java.lang.String r4 = r4.getMessage()
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r7.warn(r4)
        L50:
            r7 = r5
        L51:
            r9 = 0
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 >= 0) goto L82
            java.lang.String r4 = "Content-length"
            java.lang.String r1 = r1.header(r4)
            if (r1 == 0) goto L82
            int r4 = r1.length()
            if (r4 <= 0) goto L82
            long r7 = java.lang.Long.parseLong(r1)     // Catch: java.lang.Throwable -> L6a
            goto L82
        L6a:
            r1 = move-exception
            com.huawei.agconnect.apms.log.AgentLog r4 = com.huawei.agconnect.apms.d.cde
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "failed to parse content length: "
            r11.<init>(r12)
            java.lang.String r1 = r1.getMessage()
            r11.append(r1)
            java.lang.String r1 = r11.toString()
            r4.warn(r1)
        L82:
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 != 0) goto L87
            goto L88
        L87:
            r9 = r7
        L88:
            com.huawei.agconnect.apms.xyz.abc(r0, r2, r3, r9)
        L8b:
            com.huawei.agconnect.apms.wxy r0 = r13.abc
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.a.abc():com.huawei.agconnect.apms.wxy");
    }

    @Override // okhttp3.Call
    public void cancel() {
        this.cde.cancel();
    }

    public Object clone() throws CloneNotSupportedException {
        return this.cde.clone();
    }

    @Override // okhttp3.Call
    public void enqueue(Callback callback) {
        abc();
        this.abc.cde(System.currentTimeMillis());
        this.cde.enqueue(new b(callback, this.abc));
    }

    @Override // okhttp3.Call
    public Response execute() throws IOException {
        abc();
        this.abc.cde(System.currentTimeMillis());
        try {
            Response execute = this.cde.execute();
            if (!abc().edc()) {
                d.abc(abc(), execute);
            }
            return execute.newBuilder().body(new f(execute.body(), this.abc)).build();
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

    @Override // okhttp3.Call
    /* renamed from: isCanceled */
    public boolean getCanceled() {
        return this.cde.getCanceled();
    }

    @Override // okhttp3.Call
    public boolean isExecuted() {
        return this.cde.isExecuted();
    }

    @Override // okhttp3.Call
    public Request request() {
        return this.cde.request();
    }

    public StreamAllocation streamAllocation() throws Throwable {
        Method declaredMethod = this.cde.getClass().getDeclaredMethod("streamAllocation", new Class[0]);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return (StreamAllocation) declaredMethod.invoke(this.cde, new Object[0]);
    }

    @Override // okhttp3.Call
    public Call clone() {
        return this.cde.clone();
    }
}
