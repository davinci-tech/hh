package defpackage;

import android.content.Intent;
import com.huawei.hwidauth.e.b;

/* loaded from: classes5.dex */
public class krm {
    private static krm e;
    private b c;

    private krm() {
    }

    public static krm e() {
        krm krmVar;
        synchronized (krm.class) {
            if (e == null) {
                e(new krm());
            }
            krmVar = e;
        }
        return krmVar;
    }

    private static void e(krm krmVar) {
        synchronized (krm.class) {
            e = krmVar;
        }
    }

    public void b(b bVar) {
        synchronized (this) {
            ksy.b("WeixinAuthLogin", "WeixinAuthLogin register:", true);
            this.c = bVar;
        }
    }

    public void bQr_(Intent intent) {
        synchronized (this) {
            ksy.b("WeixinAuthLogin", "WeixinAuthLogin send:", true);
            b bVar = this.c;
            if (bVar != null) {
                bVar.a(intent);
            } else {
                ksy.c("WeixinAuthLogin", "mWeixinObserver is null.", true);
            }
        }
    }

    public void c() {
        synchronized (this) {
            ksy.b("WeixinAuthLogin", "WeixinAuthLogin unregister:", true);
            if (this.c != null) {
                this.c = null;
            }
        }
    }
}
