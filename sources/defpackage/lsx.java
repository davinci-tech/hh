package defpackage;

import android.text.TextUtils;
import com.huawei.openplatform.abl.log.i;
import com.huawei.openplatform.abl.log.m;

/* loaded from: classes5.dex */
public class lsx {

    /* renamed from: a, reason: collision with root package name */
    private String f14862a;
    private i c;
    private String b = "SDK.";
    private int d = 4;
    private boolean e = false;

    public void e(int i, String str, String str2) {
        if (d(i)) {
            String str3 = this.b + str;
            this.c.a(d(i, str3, str2), i, str3);
        }
    }

    public boolean d(int i) {
        return this.e && i >= this.d;
    }

    public void e(String str, String str2) {
        if (this.e) {
            this.c.a(d(4, str, str2), 4, str);
        }
    }

    public void d(lsq lsqVar) {
        int e = lsqVar.e();
        String b = lsqVar.b();
        String c = lsqVar.c();
        String d = lsqVar.d();
        this.c = lsy.b(lsqVar.j());
        this.d = e;
        this.f14862a = c;
        String str = c + "_Log";
        if (TextUtils.isEmpty(d)) {
            d = str;
        }
        this.b = c + ".";
        this.c.a(b, d);
        this.e = true;
    }

    public void c(int i, String str, Throwable th) {
        if (th == null || !d(i)) {
            return;
        }
        StringBuilder sb = new StringBuilder("Exception: ");
        sb.append(th.getClass().getName());
        sb.append(" msg: ");
        sb.append(m.a(th.getMessage()));
        sb.append('\n');
        int i2 = 0;
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if (i2 >= 10) {
                break;
            }
            sb.append(stackTraceElement.toString());
            sb.append('\n');
            i2++;
        }
        e(i, str, sb.toString());
    }

    public void e(int i, String str, String str2, Throwable th) {
        e(i, str, str2 + " | Exception: " + th.getClass().getSimpleName() + " msg: " + m.a(th.getMessage()));
    }

    private lta d(int i, String str, String str2) {
        lta ltaVar = new lta(this.f14862a, i, str);
        ltaVar.a((lta) str2);
        return ltaVar;
    }
}
