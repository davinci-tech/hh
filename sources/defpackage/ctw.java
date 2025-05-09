package defpackage;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class ctw {

    /* renamed from: a, reason: collision with root package name */
    private int f11475a;
    private String b;
    private String c;
    private String d;
    private long e;

    public ctw(String str) {
        this.d = str;
    }

    public ctw() {
    }

    public void a() {
        this.e = System.currentTimeMillis();
    }

    public void d() {
        cpw.c(false, "AddDeviceLogger", "this ", Long.valueOf(System.currentTimeMillis()), " last ", Long.valueOf(this.e), " dis ", Long.valueOf(System.currentTimeMillis() - this.e));
        this.f11475a = (int) (((System.currentTimeMillis() - this.e) / 1000) + 1);
        b();
    }

    public void e(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.b = str;
        d();
    }

    public void a(String str, String str2) {
        this.b = str;
        this.c = str2;
        d();
    }

    private void b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("WiFi Config  Mode ");
        sb.append(this.d);
        sb.append(" Time ");
        sb.append(this.f11475a);
        sb.append("s Result ");
        sb.append(this.b);
        if (!TextUtils.isEmpty(this.c)) {
            sb.append(" Fail Result: ");
            sb.append(this.c);
            cpw.e(false, "AddDeviceLogger", sb.toString());
            return;
        }
        cpw.a(false, "AddDeviceLogger", sb.toString());
    }
}
