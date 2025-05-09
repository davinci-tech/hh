package com.huawei.hwidauth.h;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwidauth.c.k;
import com.huawei.secure.android.common.intent.SafeBundle;
import defpackage.krs;
import defpackage.ksv;
import defpackage.ksy;

/* loaded from: classes5.dex */
public abstract class f<T extends k> {
    private static final String e = "f";

    /* renamed from: a, reason: collision with root package name */
    public T f6367a;
    public Context b;
    public String c;
    public a d;

    public interface a {
        void a(int i, String str);

        void a(SafeBundle safeBundle);
    }

    protected abstract void a(T t, Response<ResponseBody> response);

    public void a() {
        krs.a().execute(new Runnable() { // from class: com.huawei.hwidauth.h.f.2
            @Override // java.lang.Runnable
            public void run() {
                RestClient c = ksv.c(f.this.b, f.this.c);
                if (c == null) {
                    ksy.c(f.e, "restClient is null.", true);
                    f.this.a(2003, "restClient is null.");
                    return;
                }
                com.huawei.hwidauth.utils.a aVar = (com.huawei.hwidauth.utils.a) c.create(com.huawei.hwidauth.utils.a.class);
                try {
                    String a2 = f.this.f6367a.a();
                    String c2 = f.this.f6367a.c();
                    ksy.b(f.e, "request url  >>> " + c2 + f.this.c, false);
                    ksy.b(f.e, "request body >>> " + a2, false);
                    aVar.a(f.this.c, RequestBody.create(" text/html; charset=utf-8", a2.getBytes("UTF-8"))).enqueue(new ResultCallback<ResponseBody>() { // from class: com.huawei.hwidauth.h.f.2.5
                        @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                        public void onResponse(Response<ResponseBody> response) {
                            ksy.b(f.e, "request onResponse", true);
                            if (response == null) {
                                f.this.a(2005, "response is null.");
                            } else {
                                f.this.a((f) f.this.f6367a, response);
                            }
                        }

                        @Override // com.huawei.hms.framework.network.restclient.ResultCallback
                        public void onFailure(Throwable th) {
                            ksy.b(f.e, "onFailure", true);
                            f.this.a(2005, "request fail.");
                        }
                    });
                } catch (Exception e2) {
                    ksy.c(f.e, "Exception:" + e2.getClass().getSimpleName(), true);
                    f.this.a(2015, e2.getMessage());
                }
            }
        });
    }

    protected void a(final SafeBundle safeBundle) {
        if (this.d == null) {
            ksy.b(e, "mCallback is null", true);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hwidauth.h.f.1
                @Override // java.lang.Runnable
                public void run() {
                    f.this.d.a(safeBundle);
                }
            });
        }
    }

    protected void a(final int i, final String str) {
        if (this.d == null) {
            ksy.b(e, "mCallback is null", true);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hwidauth.h.f.4
                @Override // java.lang.Runnable
                public void run() {
                    f.this.d.a(i, str);
                }
            });
        }
    }
}
