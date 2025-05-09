package com.amap.api.col.p0003sl;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.amap.api.col.p0003sl.ka;
import com.amap.api.col.p0003sl.kz;
import com.amap.api.maps.AMapException;
import java.util.Map;

/* loaded from: classes2.dex */
public final class jz extends jt {
    private static jz f;
    private la g;
    private Handler h;

    public static jz b() {
        return a(true);
    }

    public static jz c() {
        return a(false);
    }

    private static jz a(boolean z) {
        jz jzVar;
        synchronized (jz.class) {
            try {
                jz jzVar2 = f;
                if (jzVar2 == null) {
                    f = new jz(z);
                } else if (z && jzVar2.g == null) {
                    jzVar2.g = la.a(new kz.a().a("amap-netmanger-threadpool-%d").b());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            jzVar = f;
        }
        return jzVar;
    }

    private jz(boolean z) {
        if (z) {
            try {
                this.g = la.a(new kz.a().a("amap-netmanger-threadpool-%d").b());
            } catch (Throwable th) {
                iv.c(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.h = new a(Looper.getMainLooper(), (byte) 0);
        } else {
            this.h = new a();
        }
    }

    @Deprecated
    public static Map<String, String> d(ka kaVar, boolean z) throws hm {
        d(kaVar);
        kaVar.setHttpProtocol(z ? ka.c.HTTPS : ka.c.HTTP);
        Map<String, String> map = null;
        long j = 0;
        boolean z2 = false;
        if (b(kaVar)) {
            boolean c = c(kaVar);
            try {
                j = SystemClock.elapsedRealtime();
                map = a(kaVar, a(kaVar, c), c(kaVar, c));
            } catch (hm e) {
                if (!c) {
                    throw e;
                }
                z2 = true;
            }
        }
        return map == null ? a(kaVar, b(kaVar, z2), a(kaVar, j)) : map;
    }

    private static Map<String, String> a(ka kaVar, ka.b bVar, int i) throws hm {
        try {
            d(kaVar);
            kaVar.setDegradeType(bVar);
            kaVar.setReal_max_timeout(i);
            return new jx().a(kaVar);
        } catch (hm e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new hm(AMapException.ERROR_UNKNOWN);
        }
    }

    @Deprecated
    private static kb e(ka kaVar, boolean z) throws hm {
        d(kaVar);
        kaVar.setHttpProtocol(z ? ka.c.HTTPS : ka.c.HTTP);
        kb kbVar = null;
        long j = 0;
        boolean z2 = false;
        if (b(kaVar)) {
            boolean c = c(kaVar);
            try {
                j = SystemClock.elapsedRealtime();
                kbVar = b(kaVar, a(kaVar, c), c(kaVar, c));
            } catch (hm e) {
                if (e.f() == 21 && kaVar.getDegradeAbility() == ka.a.INTERRUPT_IO) {
                    throw e;
                }
                if (!c) {
                    throw e;
                }
                z2 = true;
            }
        }
        return (kbVar == null || kbVar.f1250a == null || kbVar.f1250a.length <= 0) ? b(kaVar, b(kaVar, z2), a(kaVar, j)) : kbVar;
    }

    public static kb e(ka kaVar) throws hm {
        return e(kaVar, kaVar.isHttps());
    }

    private static kb b(ka kaVar, ka.b bVar, int i) throws hm {
        try {
            d(kaVar);
            kaVar.setDegradeType(bVar);
            kaVar.setReal_max_timeout(i);
            return new jx().b(kaVar);
        } catch (hm e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new hm(AMapException.ERROR_UNKNOWN);
        }
    }

    static final class a extends Handler {
        /* synthetic */ a(Looper looper, byte b) {
            this(looper);
        }

        private a(Looper looper) {
            super(looper);
        }

        public a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == 0) {
                    Object obj = message.obj;
                } else {
                    if (i != 1) {
                        return;
                    }
                    Object obj2 = message.obj;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
