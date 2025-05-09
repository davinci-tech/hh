package defpackage;

import android.graphics.Bitmap;
import java.util.List;

/* loaded from: classes4.dex */
public class gxp {
    private static gxp c;

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f12992a;
    private hjw b = null;
    private List<hjd> d;
    private boolean e;

    public static gxp a() {
        if (c == null) {
            c = new gxp();
        }
        return c;
    }

    public void e(hjw hjwVar) {
        this.b = hjwVar;
    }

    public hjw c() {
        return this.b;
    }

    public static void b() {
        gxp gxpVar = c;
        if (gxpVar != null) {
            gxpVar.f12992a = null;
            gxpVar.d = null;
            gxpVar.b = null;
        }
        c = null;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public void aVI_(Bitmap bitmap) {
        this.f12992a = bitmap;
    }

    public boolean e() {
        return this.f12992a != null;
    }

    public Bitmap aVH_() {
        return this.f12992a;
    }

    public void d(List<hjd> list) {
        this.d = list;
    }

    public List<hjd> f() {
        return this.d;
    }
}
