package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPairedDevice;
import com.huawei.multisimsdk.multidevicemanager.common.AbsPrimaryDevice;
import com.huawei.multisimsdk.multidevicemanager.util.ResponseHandler;
import com.huawei.multisimsdk.odsa.utils.OdsaHttpConnectionUtils;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class loi {

    /* renamed from: a, reason: collision with root package name */
    private static final int f14804a;
    private static final Object b;
    private static final int c;
    private static final int d;
    private static loi e;
    private static final Executor j;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f14804a = availableProcessors;
        int i2 = availableProcessors + 1;
        d = i2;
        int i3 = (availableProcessors * 2) + 1;
        c = i3;
        j = new ThreadPoolExecutor(i2, i3, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        b = new Object();
    }

    public static loi d() {
        loi loiVar;
        synchronized (b) {
            if (e == null) {
                e = new loi();
            }
            loiVar = e;
        }
        return loiVar;
    }

    public void c(String str, String str2, ResponseHandler responseHandler) {
        i iVar = new i(new Handler(Looper.getMainLooper()), str, str2, responseHandler);
        loq.c("AsyncHttpUtils", "threadPoolExecutor.execute");
        j.execute(iVar);
    }

    public void a(Context context, String str, String str2, int i2, ResponseHandler responseHandler) {
        e eVar = new e(context, new Handler(Looper.getMainLooper()), responseHandler, str, str2, i2);
        loq.c("AsyncHttpUtils", "threadPoolExecutor.execute, slotId=" + i2);
        j.execute(eVar);
    }

    public void d(String str, String str2, String str3, ResponseHandler responseHandler) {
        i iVar = new i(new Handler(Looper.getMainLooper()), str, str2, str3, responseHandler);
        loq.c("AsyncHttpUtils", "threadPoolExecutor.execute");
        j.execute(iVar);
    }

    public void d(String str, String str2, String str3, String str4, ResponseHandler responseHandler) {
        i iVar = new i(new Handler(Looper.getMainLooper()), str, str2, str3, str4, responseHandler);
        loq.c("AsyncHttpUtils", "threadPoolExecutor.execute");
        j.execute(iVar);
    }

    public void e(String str, String str2, String str3, ResponseHandler responseHandler) {
        c cVar = new c(new Handler(Looper.getMainLooper()), str, str2, str3, responseHandler);
        loq.c("AsyncHttpUtils", "threadPoolExecutor.execute, aka get request.");
        j.execute(cVar);
    }

    public void c(String str, String str2, String str3, ResponseHandler responseHandler) {
        a aVar = new a(new Handler(Looper.getMainLooper()), str, str2, responseHandler, str3);
        loq.c("AsyncHttpUtils", "threadPoolExecutor.execute, aka get request.");
        j.execute(aVar);
    }

    class b implements Runnable {
        String c;
        ResponseHandler e;

        b(String str, ResponseHandler responseHandler) {
            this.e = responseHandler;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.e.onCallback(this.c);
        }
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        ResponseHandler f14807a;
        String d;
        int e;

        d(int i, String str, ResponseHandler responseHandler) {
            this.e = i;
            this.f14807a = responseHandler;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f14807a.onCallback(this.e, this.d);
        }
    }

    class i implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        String f14810a;
        String b;
        String c;
        ResponseHandler d;
        Handler e;
        String g;
        String h;

        i(Handler handler, String str, String str2, ResponseHandler responseHandler) {
            this.e = handler;
            this.g = str;
            this.h = str2;
            this.d = responseHandler;
        }

        i(Handler handler, String str, String str2, String str3, ResponseHandler responseHandler) {
            this.e = handler;
            this.g = str;
            this.h = str2;
            this.d = responseHandler;
            this.f14810a = str3;
        }

        i(Handler handler, String str, String str2, String str3, String str4, ResponseHandler responseHandler) {
            this.e = handler;
            this.g = str;
            this.h = str2;
            this.d = responseHandler;
            this.f14810a = str3;
            this.c = str4;
            log a2 = log.a();
            String e = lnc.e(a2.c(), a2.b());
            this.b = String.format("sip:%1$s@ims.mnc%2$s.mcc%3$s.3gppnetwork.org", e, lop.b(e), lop.a(e));
        }

        @Override // java.lang.Runnable
        public void run() {
            Handler handler;
            try {
                String b = lol.c().b(this.g, this.h, this.f14810a, this.c, this.b);
                if (b == null || (handler = this.e) == null) {
                    return;
                }
                handler.post(loi.this.new b(b, this.d));
            } catch (KeyManagementException unused) {
                loq.b("AsyncHttpUtils", "doPostRequest->KeyManagementException");
            } catch (NoSuchAlgorithmException unused2) {
                loq.b("AsyncHttpUtils", "doPostRequest->NoSuchAlgorithmException");
            }
        }
    }

    class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        Handler f14808a;
        String b;
        Context c;
        int d;
        ResponseHandler e;
        String i;

        e(Context context, Handler handler, ResponseHandler responseHandler, String str, String str2, int i) {
            this.f14808a = handler;
            this.e = responseHandler;
            this.i = str;
            this.b = str2;
            this.d = i;
            this.c = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Handler handler;
            b bVar;
            Handler handler2;
            String str = null;
            try {
                try {
                    try {
                        try {
                            loq.c("AsyncHttpUtils", "run gbaStep1(), slotId=" + this.d);
                            str = lou.e().d(this.c, this.b, this.d, this.i);
                            if (loq.b.booleanValue()) {
                                loq.c("AsyncHttpUtils", "result=" + str);
                            }
                        } catch (KeyManagementException unused) {
                            loq.b("AsyncHttpUtils", "doPostRequest->KeyManagementException");
                            if (str == null || (handler = this.f14808a) == null) {
                                return;
                            } else {
                                bVar = loi.this.new b(str, this.e);
                            }
                        }
                    } catch (NoSuchAlgorithmException unused2) {
                        loq.b("AsyncHttpUtils", "doPostRequest->NoSuchAlgorithmException");
                        if (str == null || (handler = this.f14808a) == null) {
                            return;
                        } else {
                            bVar = loi.this.new b(str, this.e);
                        }
                    }
                } catch (Exception unused3) {
                    loq.b("AsyncHttpUtils", "doPostRequest->Exception:");
                    if (str == null || (handler = this.f14808a) == null) {
                        return;
                    } else {
                        bVar = loi.this.new b(str, this.e);
                    }
                }
                if (str == null || (handler = this.f14808a) == null) {
                    return;
                }
                bVar = loi.this.new b(str, this.e);
                handler.post(bVar);
            } catch (Throwable th) {
                if (str != null && (handler2 = this.f14808a) != null) {
                    handler2.post(loi.this.new b(str, this.e));
                }
                throw th;
            }
        }
    }

    public void b(AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, Map<String, String> map, ResponseHandler responseHandler, int i2) {
        j.execute(new h(new Handler(Looper.getMainLooper()), responseHandler, absPrimaryDevice, absPairedDevice, i2, map));
    }

    class h implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        Map<String, String> f14809a;
        Handler b;
        AbsPairedDevice c;
        int d;
        AbsPrimaryDevice e;
        ResponseHandler j;

        h(Handler handler, ResponseHandler responseHandler, AbsPrimaryDevice absPrimaryDevice, AbsPairedDevice absPairedDevice, int i, Map<String, String> map) {
            this.b = handler;
            this.j = responseHandler;
            this.e = absPrimaryDevice;
            this.c = absPairedDevice;
            this.d = i;
            this.f14809a = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            String d;
            Handler handler;
            try {
                if (lop.e("ODSA_SUPPORT_POST_METHOD", (Boolean) false).booleanValue()) {
                    d = OdsaHttpConnectionUtils.c().b(this.d, this.e, this.c, this.f14809a);
                } else {
                    d = OdsaHttpConnectionUtils.c().d(this.d, this.e, this.c, this.f14809a);
                }
                if (d == null || (handler = this.b) == null) {
                    return;
                }
                handler.post(loi.this.new d(this.d, d, this.j));
            } catch (Exception e) {
                loq.e("AsyncHttpUtils", "doPostRequest->Exception:", e);
            }
        }
    }

    class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        String f14806a;
        Handler b;
        String c;
        String d;
        ResponseHandler e;
        String f;

        c(Handler handler, String str, String str2, String str3, ResponseHandler responseHandler) {
            this.b = handler;
            this.f14806a = str;
            this.f = str2;
            this.d = str3;
            this.e = responseHandler;
        }

        @Override // java.lang.Runnable
        public void run() {
            String a2;
            Handler handler;
            if (lop.e("ODSA_SUPPORT_POST_METHOD", (Boolean) false).booleanValue()) {
                a2 = OdsaHttpConnectionUtils.c().c(this.f14806a, this.f, this.c, this.d);
            } else {
                a2 = OdsaHttpConnectionUtils.c().a(this.f14806a, this.f, this.c, this.d);
            }
            if (a2 == null || (handler = this.b) == null) {
                return;
            }
            handler.post(loi.this.new b(a2, this.e));
        }
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        String f14805a;
        String b;
        Handler c;
        ResponseHandler d;
        String e;

        a(Handler handler, String str, String str2, ResponseHandler responseHandler, String str3) {
            this.c = handler;
            this.f14805a = str;
            this.e = str2;
            this.b = str3;
            this.d = responseHandler;
        }

        @Override // java.lang.Runnable
        public void run() {
            Handler handler;
            String b = OdsaHttpConnectionUtils.c().b(this.f14805a, this.e, this.b);
            if (b == null || (handler = this.c) == null) {
                return;
            }
            handler.post(loi.this.new b(b, this.d));
        }
    }
}
