package defpackage;

import android.app.Application;

/* loaded from: classes5.dex */
public final class jrr {

    /* renamed from: a, reason: collision with root package name */
    private static jrr f14038a = new jrr();
    private Application c = null;

    private jrr() {
    }

    public static jrr e() {
        return f14038a;
    }

    public void bJg_(Application application) {
        if (application != null) {
            this.c = application;
        }
    }
}
