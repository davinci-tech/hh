package com.huawei.updatesdk.a.b.c;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.huawei.updatesdk.a.a.d.h;
import com.huawei.updatesdk.a.b.c.c.c;
import com.huawei.updatesdk.a.b.c.c.d;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class b extends AsyncTask<c, Void, d> {

    /* renamed from: a, reason: collision with root package name */
    protected c f10816a;
    private final com.huawei.updatesdk.a.b.c.c.a c;
    private d b = null;
    private com.huawei.updatesdk.a.a.b.b d = null;
    private int e = 0;

    public final d c() {
        d dVar = null;
        do {
            if (this.e > 0 && dVar != null) {
                com.huawei.updatesdk.a.a.c.a.a.a.a("StoreTask", "call store error! responseCode:" + dVar.d() + ", retryTimes:" + this.e);
            }
            dVar = a();
        } while (c(dVar));
        this.b = dVar;
        return dVar;
    }

    public void b() {
        com.huawei.updatesdk.a.a.b.b bVar = this.d;
        if (bVar != null) {
            bVar.a();
            this.d = null;
        }
    }

    public final void a(Executor executor) {
        executeOnExecutor(executor, this.f10816a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(d dVar) {
        com.huawei.updatesdk.a.a.c.a.a.a.a("StoreTask", "onPostExecute, method:" + this.f10816a.b());
        com.huawei.updatesdk.b.g.b.a().remove(this);
        e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public d doInBackground(c... cVarArr) {
        com.huawei.updatesdk.a.a.c.a.a.a.a("StoreTask", "doInBackground, method:" + this.f10816a.b());
        com.huawei.updatesdk.b.g.b.a((AsyncTask) this);
        d c = c();
        com.huawei.updatesdk.a.b.c.c.a aVar = this.c;
        if (aVar != null) {
            aVar.a(this.f10816a, c);
        }
        return c;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x010c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.huawei.updatesdk.a.b.c.c.d a() {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.a.b.c.b.a():com.huawei.updatesdk.a.b.c.c.d");
    }

    private void e() {
        b(this.b);
    }

    private void d(String str) {
        com.huawei.updatesdk.a.a.a.a("StoreTask", TextUtils.isEmpty(str) ? "UpdateSDK response error, resData == null" : "UpdateSDK response error, resData is not json string");
    }

    private String d() {
        String a2 = com.huawei.updatesdk.b.g.a.a();
        com.huawei.updatesdk.a.a.c.a.a.a.c("StoreTask", "UserAgent is: " + a2);
        return TextUtils.isEmpty(a2) ? "Android/1.0" : a2;
    }

    private boolean c(String str) {
        return (str == null || str.contains("emuiApiLevel") || str.contains("ts") || str.contains("harmonyApiLevel")) ? false : true;
    }

    private boolean c(d dVar) {
        if (isCancelled()) {
            return false;
        }
        if (dVar.d() != 1 && dVar.d() != 2) {
            return false;
        }
        int i = this.e;
        this.e = i + 1;
        if (i >= 3) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("StoreTask", "retry completed total times = " + this.e + ",response.responseCode = " + dVar.d());
            return false;
        }
        com.huawei.updatesdk.a.a.c.a.a.a.a("StoreTask", "retry times = " + this.e + ",response.responseCode = " + dVar.d());
        return true;
    }

    private boolean b(String str) {
        return TextUtils.isEmpty(str) || !h.d(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(com.huawei.updatesdk.a.b.c.c.d r5) {
        /*
            r4 = this;
            java.lang.String r0 = "notifyResult, create response error, method:"
            boolean r1 = r4.isCancelled()
            if (r1 != 0) goto L57
            com.huawei.updatesdk.a.b.c.c.a r1 = r4.c
            if (r1 == 0) goto L57
            if (r5 != 0) goto L50
            java.lang.String r1 = "notifyResult, response is null"
            java.lang.String r2 = "StoreTask"
            com.huawei.updatesdk.a.a.c.a.a.a.b(r2, r1)
            com.huawei.updatesdk.a.b.c.c.c r1 = r4.f10816a     // Catch: java.lang.IllegalAccessException -> L20 java.lang.InstantiationException -> L27
            java.lang.String r1 = r1.b()     // Catch: java.lang.IllegalAccessException -> L20 java.lang.InstantiationException -> L27
            com.huawei.updatesdk.a.b.c.c.d r5 = com.huawei.updatesdk.a.b.c.a.a(r1)     // Catch: java.lang.IllegalAccessException -> L20 java.lang.InstantiationException -> L27
            goto L3d
        L20:
            r1 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            goto L2d
        L27:
            r1 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
        L2d:
            com.huawei.updatesdk.a.b.c.c.c r0 = r4.f10816a
            java.lang.String r0 = r0.b()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.huawei.updatesdk.a.a.c.a.a.a.a(r2, r0, r1)
        L3d:
            if (r5 != 0) goto L47
            com.huawei.updatesdk.a.b.c.c.d r5 = new com.huawei.updatesdk.a.b.c.c.d
            r5.<init>()
            com.huawei.updatesdk.a.b.c.c.d$a r0 = com.huawei.updatesdk.a.b.c.c.d.a.PARAM_ERROR
            goto L49
        L47:
            com.huawei.updatesdk.a.b.c.c.d$a r0 = com.huawei.updatesdk.a.b.c.c.d.a.UNKNOWN_EXCEPTION
        L49:
            r5.a(r0)
            r0 = 1
            r5.b(r0)
        L50:
            com.huawei.updatesdk.a.b.c.c.a r0 = r4.c
            com.huawei.updatesdk.a.b.c.c.c r1 = r4.f10816a
            r0.b(r1, r5)
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.updatesdk.a.b.c.b.b(com.huawei.updatesdk.a.b.c.c.d):void");
    }

    private boolean a(String str) {
        try {
            return Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)").matcher(str).find();
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.a.b("StoreTask", "is contain ip error: " + e.getMessage());
            return true;
        }
    }

    private void a(String str, Throwable th) {
        StringBuilder sb = new StringBuilder("invoke store error, exceptionType:");
        String th2 = th.toString();
        if (a(th.toString())) {
            th2 = th.getClass().getName();
        }
        sb.append(th2);
        sb.append(", url:");
        sb.append(str);
        sb.append(", method:");
        sb.append(this.f10816a.b());
        sb.append(", retryTimes:");
        sb.append(this.e);
        String sb2 = sb.toString();
        com.huawei.updatesdk.a.a.c.a.a.a.a("StoreTask", sb2, th);
        com.huawei.updatesdk.a.a.a.a("StoreTask", "UpdateSDK call store error: " + sb2);
    }

    private void a(d dVar, Throwable th, String str) {
        d.a aVar;
        int i = 1;
        if (th instanceof ConnectException) {
            aVar = d.a.CONNECT_EXCEPTION;
        } else if ((th instanceof SocketTimeoutException) || (th.getMessage() != null && th.getMessage().contains("ConnectTimeoutException"))) {
            aVar = d.a.CONNECT_EXCEPTION;
            i = 2;
        } else if (th instanceof IllegalArgumentException) {
            aVar = d.a.PARAM_ERROR;
            i = 5;
        } else if (th instanceof IllegalAccessException) {
            aVar = d.a.UNKNOWN_EXCEPTION;
        } else {
            if (!(th instanceof ArrayIndexOutOfBoundsException)) {
                if ((th instanceof InterruptedException) || (th instanceof InterruptedIOException)) {
                    a(dVar, 0, d.a.NORMAL, th);
                    com.huawei.updatesdk.a.a.a.a("StoreTask", "UpdateSDK task is canceled");
                } else if (th instanceof IOException) {
                    aVar = d.a.IO_EXCEPTION;
                } else if (dVar != null) {
                    dVar.a(th.toString());
                }
                a(str, th);
            }
            aVar = d.a.NO_PROGUARD;
            i = 6;
        }
        a(dVar, i, aVar, th);
        a(str, th);
    }

    private void a(d dVar, int i, d.a aVar, Throwable th) {
        if (dVar != null) {
            dVar.b(i);
            dVar.a(aVar);
            String th2 = th.toString();
            if (a(th.toString())) {
                th2 = th.getClass().getName();
            }
            dVar.a(th2);
        }
    }

    private d a(String str, d dVar) {
        try {
            dVar.fromJson(new JSONObject(str));
            dVar.b(0);
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("StoreTask", "parse json error", e);
        }
        return dVar;
    }

    public b(c cVar, com.huawei.updatesdk.a.b.c.c.a aVar) {
        this.f10816a = cVar;
        this.c = aVar;
    }
}
