package defpackage;

import com.huawei.health.trusport_ee_jni.trusport_eeJNI;

/* loaded from: classes4.dex */
public final class glo {

    /* renamed from: a, reason: collision with root package name */
    public static final glo f12853a;
    public static final glo b;
    public static final glo c;
    private static glo[] d;
    private static int e;
    private final int f;
    private final String h;

    static {
        glo gloVar = new glo("TRUSPORT_EE_INTENSITY_LOW", trusport_eeJNI.TRUSPORT_EE_INTENSITY_LOW_get());
        c = gloVar;
        glo gloVar2 = new glo("TRUSPORT_EE_INTENSITY_MEDIUM");
        f12853a = gloVar2;
        glo gloVar3 = new glo("TRUSPORT_EE_INTENSITY_HIGH");
        b = gloVar3;
        d = new glo[]{gloVar, gloVar2, gloVar3};
        e = 0;
    }

    public final int e() {
        return this.f;
    }

    public String toString() {
        return this.h;
    }

    private glo(String str) {
        this.h = str;
        int i = e;
        e = i + 1;
        this.f = i;
    }

    private glo(String str, int i) {
        this.h = str;
        this.f = i;
        e = i + 1;
    }
}
