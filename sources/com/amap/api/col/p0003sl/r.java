package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.col.p0003sl.s;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class r extends Thread {
    private static int c = 0;
    private static int d = 3;
    private static long e = 30000;
    private static boolean g = false;

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f1355a;
    private IAMapDelegate b;
    private a f = null;
    private Handler h = new Handler(Looper.getMainLooper()) { // from class: com.amap.api.col.3sl.r.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (r.g) {
                return;
            }
            if (r.this.f == null) {
                r rVar = r.this;
                rVar.f = new a(rVar.b, r.this.f1355a == null ? null : (Context) r.this.f1355a.get());
            }
            dt.a().a(r.this.f);
        }
    };

    static /* synthetic */ int b() {
        int i = c;
        c = i + 1;
        return i;
    }

    static /* synthetic */ boolean e() {
        g = true;
        return true;
    }

    public r(Context context, IAMapDelegate iAMapDelegate) {
        this.f1355a = null;
        if (context != null) {
            this.f1355a = new WeakReference<>(context);
        }
        this.b = iAMapDelegate;
        f();
    }

    private static void f() {
        c = 0;
        g = false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            g();
        } catch (Throwable th) {
            iv.c(th, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th.printStackTrace();
            dx.b(dw.e, "auth pro exception " + th.getMessage());
        }
    }

    private void g() {
        if (g) {
            return;
        }
        int i = 0;
        while (i <= d) {
            i++;
            this.h.sendEmptyMessageDelayed(0, i * e);
        }
    }

    @Override // java.lang.Thread
    public final void interrupt() {
        this.b = null;
        this.f1355a = null;
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.h = null;
        this.f = null;
        f();
        super.interrupt();
    }

    static final class a extends lb {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<IAMapDelegate> f1357a;
        private WeakReference<Context> b;
        private s c;

        public a(IAMapDelegate iAMapDelegate, Context context) {
            this.f1357a = null;
            this.b = null;
            this.f1357a = new WeakReference<>(iAMapDelegate);
            if (context != null) {
                this.b = new WeakReference<>(context);
            }
        }

        @Override // com.amap.api.col.p0003sl.lb
        public final void runTask() {
            s.a d;
            WeakReference<Context> weakReference;
            try {
                if (r.g) {
                    return;
                }
                if (this.c == null && (weakReference = this.b) != null && weakReference.get() != null) {
                    this.c = new s(this.b.get(), "");
                }
                r.b();
                if (r.c > r.d) {
                    r.e();
                    a();
                    return;
                }
                s sVar = this.c;
                if (sVar == null || (d = sVar.d()) == null) {
                    return;
                }
                if (!d.d) {
                    a();
                }
                r.e();
            } catch (Throwable th) {
                iv.c(th, "authForPro", "loadConfigData_uploadException");
                dx.b(dw.e, "auth exception get data " + th.getMessage());
            }
        }

        private void a() {
            final IAMapDelegate iAMapDelegate;
            WeakReference<IAMapDelegate> weakReference = this.f1357a;
            if (weakReference == null || weakReference.get() == null || (iAMapDelegate = this.f1357a.get()) == null || iAMapDelegate.getMapConfig() == null) {
                return;
            }
            iAMapDelegate.queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.r.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    IAMapDelegate iAMapDelegate2 = iAMapDelegate;
                    if (iAMapDelegate2 == null || iAMapDelegate2.getMapConfig() == null) {
                        return;
                    }
                    MapConfig mapConfig = iAMapDelegate.getMapConfig();
                    mapConfig.setProFunctionAuthEnable(false);
                    if (mapConfig.isUseProFunction()) {
                        iAMapDelegate.setMapCustomEnable(mapConfig.isCustomStyleEnable(), true);
                        iAMapDelegate.reloadMapCustomStyle();
                        db.a(a.this.b == null ? null : (Context) a.this.b.get(), "鉴权失败，当前key没有自定义纹理的使用权限，自定义纹理相关内容，将不会呈现！");
                    }
                }
            });
        }
    }
}
