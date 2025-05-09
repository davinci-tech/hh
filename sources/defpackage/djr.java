package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class djr {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11684a = new Object();
    private static djr b;
    private boolean d;
    private boolean e = false;

    public static djr c() {
        djr djrVar;
        synchronized (f11684a) {
            if (b == null) {
                b = new djr();
            }
            djrVar = b;
        }
        return djrVar;
    }

    public void d(boolean z) {
        LogUtil.a("TransferParamsHelper", " setJumpVibrantLifePage isJumpVibrantLifePage：", Boolean.valueOf(z));
        this.e = z;
    }

    public boolean d() {
        return this.e;
    }

    public boolean e() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public static void b() {
        synchronized (f11684a) {
            b = null;
        }
    }
}
