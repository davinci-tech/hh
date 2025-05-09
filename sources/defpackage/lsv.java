package defpackage;

import android.content.Context;
import com.huawei.hms.support.log.KitLog;
import com.huawei.openplatform.abl.log.util.d;
import com.huawei.openplatform.abl.log.util.g;

/* loaded from: classes5.dex */
public class lsv {
    private static volatile lsv b;
    private static final byte[] e = new byte[0];
    private KitLog c;
    private boolean d = false;

    /* renamed from: a, reason: collision with root package name */
    private int f14859a = 4;

    public void c(String str, String str2, Object... objArr) {
        if (this.c != null) {
            b(str, g.a(str2, objArr));
        }
    }

    public void b(String str, String str2) {
        KitLog kitLog = this.c;
        if (kitLog != null) {
            kitLog.w(str, str2);
        }
    }

    public void e(String str, String str2, Object... objArr) {
        if (this.c != null) {
            a(str, g.a(str2, objArr));
        }
    }

    public void a(String str, String str2) {
        KitLog kitLog = this.c;
        if (kitLog != null) {
            kitLog.i(str, str2);
        }
    }

    public void b(String str, String str2, Object... objArr) {
        if (this.c != null) {
            d(str, g.a(str2, objArr));
        }
    }

    public void d(String str, String str2) {
        KitLog kitLog = this.c;
        if (kitLog != null) {
            kitLog.e(str, str2);
        }
    }

    public boolean b(int i) {
        return this.d && i >= this.f14859a;
    }

    public void a(String str, String str2, Object... objArr) {
        if (this.c != null) {
            c(str, g.a(str2, objArr));
        }
    }

    public void c(String str, String str2) {
        KitLog kitLog = this.c;
        if (kitLog != null) {
            kitLog.d(str, str2);
        }
    }

    public void d(Context context, int i, String str) {
        KitLog kitLog = this.c;
        if (kitLog != null && context != null) {
            kitLog.init(context.getApplicationContext(), i, str);
        }
        this.f14859a = i;
        this.d = true;
    }

    public static lsv e() {
        if (b == null) {
            synchronized (e) {
                if (b == null) {
                    b = new lsv();
                }
            }
        }
        return b;
    }

    private lsv() {
        if (d.a("com.huawei.hms.support.log.KitLog")) {
            this.c = new KitLog();
        }
    }
}
