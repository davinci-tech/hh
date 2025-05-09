package defpackage;

import com.huawei.health.trusport_ee_jni.trusport_eeJNI;

/* loaded from: classes4.dex */
public final class gls {

    /* renamed from: a, reason: collision with root package name */
    public static final gls f12855a;
    private static gls[] b;
    public static final gls c;
    public static final gls d;
    private static int e;
    private final int i;
    private final String j;

    static {
        gls glsVar = new gls("TRUSPORT_EE_SEX_MALE", trusport_eeJNI.TRUSPORT_EE_SEX_MALE_get());
        f12855a = glsVar;
        gls glsVar2 = new gls("TRUSPORT_EE_SEX_FEMALE");
        d = glsVar2;
        gls glsVar3 = new gls("TRUSPORT_EE_SEX_UNKNOWN");
        c = glsVar3;
        b = new gls[]{glsVar, glsVar2, glsVar3};
        e = 0;
    }

    public final int e() {
        return this.i;
    }

    public String toString() {
        return this.j;
    }

    private gls(String str) {
        this.j = str;
        int i = e;
        e = i + 1;
        this.i = i;
    }

    private gls(String str, int i) {
        this.j = str;
        this.i = i;
        e = i + 1;
    }
}
