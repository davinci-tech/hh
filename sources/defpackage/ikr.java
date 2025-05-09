package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes7.dex */
public class ikr {

    /* renamed from: a, reason: collision with root package name */
    private ikm f13415a;
    private Context d;

    private boolean c(int i, String str) {
        return i > 0 && str != null;
    }

    private ikr() {
        this.f13415a = new ikm();
    }

    static class d {
        private static final ikr e = new ikr();
    }

    public static ikr b(Context context) {
        d.e.a(context);
        return d.e;
    }

    private void a(Context context) {
        if (context == null || this.d != null) {
            return;
        }
        this.d = context.getApplicationContext();
    }

    public ikv e(int i, int i2, int i3, String str) {
        return b(i, i2, i3, str);
    }

    private ikv b(int i, int i2, int i3, String str) {
        if (!c(i, str)) {
            LogUtil.h("Debug_StoreClient", "getOwnerClient app <= 0 or device = null", blt.b(str));
            return null;
        }
        int e = ijl.e(this.d, i2, i3);
        if (e <= 0) {
            LogUtil.h("Debug_StoreClient", "getOwnerClient who <= 0 ower = ", Integer.valueOf(i3), ",app = ", Integer.valueOf(i));
            return null;
        }
        String e2 = ikq.e(Integer.toString(i), Integer.toString(e), str);
        ikv e3 = this.f13415a.e(e2);
        if (e3 != null) {
            return e3;
        }
        int b = ijl.b(this.d, str);
        if (b <= 0) {
            LogUtil.h("Debug_StoreClient", "getOwnerClient device <= 0 deviceUUID error ", blt.b(str));
            return null;
        }
        ikv a2 = a(i, e, b);
        if (a2 == null) {
            LogUtil.h("Debug_StoreClient", "getOwnerClient hiHealthContext = null key error ");
            return null;
        }
        this.f13415a.c(e2, a2);
        iks.e().b(e, b);
        return a2;
    }

    public ikv a(int i, int i2, int i3) {
        return c(i, i2, i3);
    }

    private ikv c(int i, int i2, int i3) {
        if (i < 0 || i2 < 0 || i3 < 0) {
            LogUtil.h("Debug_StoreClient", "getUserClient app < 0 or device < 0 or who < 0");
            return null;
        }
        String e = ikq.e(Integer.toString(i), Integer.toString(i2), Integer.toString(i3));
        ikv e2 = this.f13415a.e(e);
        if (e2 != null) {
            return e2;
        }
        int b = iis.d().b(i2, i3, i);
        if (b <= 0) {
            LogUtil.h("Debug_StoreClient", "getUserClient client <= 0 key = ", e);
            return null;
        }
        ikv ikvVar = new ikv(i, i3, i2, b);
        this.f13415a.c(e, ikvVar);
        iks.e().b(i2, i3);
        return ikvVar;
    }

    public int e(int i, int i2, int i3) {
        return b(i, i2, i3);
    }

    private int b(int i, int i2, int i3) {
        if (i < 0 || i2 < 0 || i3 < 0) {
            LogUtil.h("Debug_StoreClient", "getAllClientID app < 0 or device < 0 or who < 0");
            return 0;
        }
        String e = ikq.e(Integer.toString(i), Integer.toString(i2), Integer.toString(i3));
        ikv e2 = this.f13415a.e(e);
        if (e2 != null) {
            return e2.b();
        }
        int b = iis.d().b(i2, i3, i);
        if (b <= 0) {
            LogUtil.h("Debug_StoreClient", "getAllClientID client <= 0 key = ", e);
            return 0;
        }
        this.f13415a.c(e, new ikv(i, i3, i2, b));
        iks.e().b(i2, i3);
        return b;
    }

    public ikv a(int i, int i2, String str) {
        return e(i, i2, str);
    }

    private ikv e(int i, int i2, String str) {
        if (i < 0 || i2 < 0 || str == null) {
            LogUtil.h("Debug_StoreClient", "getReadHiHealthContext app < 0 or device = null or who < 0");
            return null;
        }
        String e = ikq.e(Integer.toString(i), Integer.toString(i2), str);
        ikv e2 = this.f13415a.e(e);
        if (e2 != null) {
            return e2;
        }
        int b = ijl.b(this.d, str);
        if (b <= 0) {
            LogUtil.h("Debug_StoreClient", "getReadHiHealthContext device <= 0 deviceUUID error ");
            return null;
        }
        ikv a2 = a(i, i2, b);
        if (a2 == null) {
            LogUtil.h("Debug_StoreClient", "getReadHiHealthContext hiHealthContext = null key error ");
            return null;
        }
        this.f13415a.c(e, a2);
        iks.e().b(i2, b);
        return a2;
    }

    public void c() {
        this.f13415a.b();
    }

    public static int e(ikr ikrVar, int i, int i2, int i3, int i4) {
        ikv a2;
        if (i == 0) {
            a2 = ikrVar.a(0, i3, 0);
        } else if (i == 1) {
            a2 = ikrVar.a(i2, i3, 0);
        } else {
            a2 = i != 2 ? null : ikrVar.a(0, i3, i4);
        }
        if (a2 == null) {
            ReleaseLogUtil.d("Debug_StoreClient", "getStatClient statClient is null,writeStatType is ", Integer.valueOf(i), ",app is ", Integer.valueOf(i2), ", userID ", Integer.valueOf(i3), ", deviceID ", Integer.valueOf(i4));
            return 0;
        }
        return a2.b();
    }
}
