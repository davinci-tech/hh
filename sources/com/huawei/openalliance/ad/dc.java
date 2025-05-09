package com.huawei.openalliance.ad;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.huawei.openalliance.ad.beans.metadata.v3.CachedContent;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.IAd;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class dc {

    /* renamed from: a, reason: collision with root package name */
    private static com.huawei.openalliance.ad.inter.data.h f6693a;
    private static com.huawei.openalliance.ad.inter.data.d b;
    private static IAd c;
    private static List<String> d;
    private static Map<String, List<CachedContent>> e;
    private static final byte[] f = new byte[0];
    private static ContentRecord g;
    private static ContentRecord h;
    private static Drawable i;
    private static Bitmap j;
    private static ContentRecord k;
    private static com.huawei.openalliance.ad.inter.data.e l;
    private static ContentRecord m;
    private static int n;
    private static com.huawei.openalliance.ad.inter.data.d o;

    public static void o() {
        synchronized (f) {
            n = 0;
        }
    }

    public static int n() {
        int i2;
        synchronized (f) {
            i2 = n;
        }
        return i2;
    }

    public static ContentRecord m() {
        ContentRecord contentRecord;
        synchronized (f) {
            contentRecord = m;
        }
        return contentRecord;
    }

    public static com.huawei.openalliance.ad.inter.data.e l() {
        com.huawei.openalliance.ad.inter.data.e eVar;
        synchronized (f) {
            eVar = l;
        }
        return eVar;
    }

    public static ContentRecord k() {
        ContentRecord contentRecord;
        synchronized (f) {
            contentRecord = k;
        }
        return contentRecord;
    }

    public static Bitmap j() {
        Bitmap bitmap;
        synchronized (f) {
            bitmap = j;
        }
        return bitmap;
    }

    public static Drawable i() {
        Drawable drawable;
        synchronized (f) {
            drawable = i;
        }
        return drawable;
    }

    public static ContentRecord h() {
        ContentRecord contentRecord;
        synchronized (f) {
            contentRecord = h;
        }
        return contentRecord;
    }

    public static ContentRecord g() {
        ContentRecord contentRecord;
        synchronized (f) {
            contentRecord = g;
        }
        return contentRecord;
    }

    public static Map<String, List<CachedContent>> f() {
        Map<String, List<CachedContent>> map;
        synchronized (f) {
            map = e;
        }
        return map;
    }

    public static List<String> e() {
        List<String> list;
        synchronized (f) {
            list = d;
        }
        return list;
    }

    public static IAd d() {
        IAd iAd;
        synchronized (f) {
            iAd = c;
        }
        return iAd;
    }

    public static void c(ContentRecord contentRecord) {
        synchronized (f) {
            if (contentRecord == null) {
                ho.a("GlobalDataShare", "set icon ad null");
                k = null;
            } else {
                k = contentRecord;
            }
        }
    }

    public static com.huawei.openalliance.ad.inter.data.d c() {
        com.huawei.openalliance.ad.inter.data.d dVar;
        synchronized (f) {
            dVar = b;
        }
        return dVar;
    }

    public static void b(com.huawei.openalliance.ad.inter.data.d dVar) {
        synchronized (f) {
            if (dVar == null) {
                ho.a("GlobalDataShare", "set interstitial ad null");
                b = null;
            } else {
                b = dVar;
            }
        }
    }

    public static void b(ContentRecord contentRecord) {
        synchronized (f) {
            if (contentRecord == null) {
                ho.a("GlobalDataShare", "set spare splash ad null");
                h = null;
            } else {
                h = contentRecord;
            }
        }
    }

    public static com.huawei.openalliance.ad.inter.data.h b() {
        com.huawei.openalliance.ad.inter.data.h hVar;
        synchronized (f) {
            hVar = f6693a;
        }
        return hVar;
    }

    public static void a(Map<String, List<CachedContent>> map) {
        synchronized (f) {
            if (com.huawei.openalliance.ad.utils.bl.a(map)) {
                ho.a("GlobalDataShare", "set kit cached templateIds null");
                e = null;
            } else {
                e = map;
            }
        }
    }

    public static void a(List<String> list) {
        synchronized (f) {
            if (com.huawei.openalliance.ad.utils.bg.a(list)) {
                ho.a("GlobalDataShare", "set kit cached contentIds null");
                d = null;
            } else {
                d = list;
            }
        }
    }

    public static void a(com.huawei.openalliance.ad.inter.data.h hVar) {
        synchronized (f) {
            if (hVar == null) {
                ho.a("GlobalDataShare", "set reward ad null");
                f6693a = null;
            } else {
                f6693a = hVar;
            }
        }
    }

    public static void a(com.huawei.openalliance.ad.inter.data.e eVar) {
        synchronized (f) {
            if (eVar == null) {
                ho.a("GlobalDataShare", "set native ad null");
                l = null;
            } else {
                l = eVar;
            }
        }
    }

    public static void a(com.huawei.openalliance.ad.inter.data.d dVar) {
        synchronized (f) {
            if (dVar == null) {
                ho.a("GlobalDataShare", "interstitial is null.");
                o = null;
            } else {
                o = dVar;
            }
        }
    }

    public static void a(IAd iAd) {
        synchronized (f) {
            if (iAd == null) {
                ho.a("GlobalDataShare", "set linkedSplashAd ad null");
                c = null;
            } else {
                c = iAd;
            }
        }
    }

    public static void a(ContentRecord contentRecord) {
        synchronized (f) {
            if (contentRecord == null) {
                ho.a("GlobalDataShare", "set normal splash ad null");
                g = null;
            } else {
                g = contentRecord;
            }
        }
    }

    public static void a(Drawable drawable) {
        synchronized (f) {
            if (drawable == null) {
                ho.a("GlobalDataShare", "set drawable ad null");
                i = null;
            } else {
                i = drawable;
            }
        }
    }

    public static void a(Bitmap bitmap) {
        synchronized (f) {
            if (bitmap == null) {
                ho.a("GlobalDataShare", "set bitmap ad null");
                j = null;
            } else {
                j = bitmap;
            }
        }
    }

    public static void a(int i2) {
        synchronized (f) {
            n = i2;
        }
    }

    public static com.huawei.openalliance.ad.inter.data.d a() {
        com.huawei.openalliance.ad.inter.data.d dVar;
        synchronized (f) {
            dVar = o;
        }
        return dVar;
    }
}
