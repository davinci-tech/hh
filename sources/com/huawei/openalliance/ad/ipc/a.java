package com.huawei.openalliance.ad.ipc;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.dj;

/* loaded from: classes5.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private InterfaceC0194a f7072a;
    private final String b = "unbindTask" + hashCode();
    private int c = 0;
    private String d;
    private Context e;

    /* renamed from: com.huawei.openalliance.ad.ipc.a$a, reason: collision with other inner class name */
    public interface InterfaceC0194a {
        void a();
    }

    public void b() {
        synchronized (this) {
            int i = this.c - 1;
            this.c = i;
            if (i < 0) {
                this.c = 0;
            }
            ho.a(c(), "dec count: %d", Integer.valueOf(this.c));
            if (this.c <= 0) {
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.ipc.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.e();
                    }
                }, this.b, d());
            }
        }
    }

    public void a() {
        synchronized (this) {
            this.c++;
            dj.a(this.b);
            ho.b(c(), "inc count: " + this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ho.b(c(), "unbindService");
        this.f7072a.a();
    }

    private int d() {
        return TextUtils.equals(Constants.HW_INTELLIEGNT_PACKAGE, this.e.getPackageName()) ? 0 : 60000;
    }

    private String c() {
        return "Monitor_" + this.d;
    }

    public a(Context context, String str, InterfaceC0194a interfaceC0194a) {
        this.e = context.getApplicationContext();
        this.d = str;
        this.f7072a = interfaceC0194a;
    }
}
