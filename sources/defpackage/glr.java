package defpackage;

import com.huawei.health.trusport_ee_jni.trusport_eeJNI;

/* loaded from: classes4.dex */
public final class glr {

    /* renamed from: a, reason: collision with root package name */
    public static final glr f12854a;
    private static glr[] aa;
    public static final glr b;
    public static final glr c;
    public static final glr d;
    public static final glr e;
    public static final glr f;
    public static final glr g;
    public static final glr h;
    public static final glr i;
    public static final glr j;
    public static final glr k;
    public static final glr l;
    public static final glr m;
    public static final glr n;
    public static final glr o;
    public static final glr p;
    public static final glr q;
    public static final glr r;
    public static final glr s;
    public static final glr t;
    public static final glr u;
    public static final glr v;
    public static final glr w;
    public static final glr x;
    private static int y;
    private final int ab;
    private final String ad;

    static {
        glr glrVar = new glr("TRUSPORT_EE_ERROR_OK", trusport_eeJNI.TRUSPORT_EE_ERROR_OK_get());
        w = glrVar;
        glr glrVar2 = new glr("TRUSPORT_EE_ERROR_NULL_POINTER");
        x = glrVar2;
        glr glrVar3 = new glr("TRUSPORT_EE_ERROR_BUFFER_OVERFLOW");
        b = glrVar3;
        glr glrVar4 = new glr("TRUSPORT_EE_ERROR_DATA_SIZE_MISMATCH");
        d = glrVar4;
        glr glrVar5 = new glr("TRUSPORT_EE_ERROR_DATA_NOT_AVAILABLE");
        c = glrVar5;
        glr glrVar6 = new glr("TRUSPORT_EE_ERROR_INVALID_HR");
        l = glrVar6;
        glr glrVar7 = new glr("TRUSPORT_EE_ERROR_INVALID_SPEED");
        o = glrVar7;
        glr glrVar8 = new glr("TRUSPORT_EE_ERROR_INVALID_SR");
        m = glrVar8;
        glr glrVar9 = new glr("TRUSPORT_EE_ERROR_INVALID_AGE");
        j = glrVar9;
        glr glrVar10 = new glr("TRUSPORT_EE_ERROR_INVALID_HEIGHT");
        n = glrVar10;
        glr glrVar11 = new glr("TRUSPORT_EE_ERROR_INVALID_WEIGHT");
        v = glrVar11;
        glr glrVar12 = new glr("TRUSPORT_EE_ERROR_INVALID_ACTIVITY_CLASS");
        i = glrVar12;
        glr glrVar13 = new glr("TRUSPORT_EE_ERROR_INVALID_VO2MAX");
        q = glrVar13;
        glr glrVar14 = new glr("TRUSPORT_EE_ERROR_INVALID_STEP_LENGTH");
        r = glrVar14;
        glr glrVar15 = new glr("TRUSPORT_EE_ERROR_INVALID_TIMESTAMP");
        s = glrVar15;
        glr glrVar16 = new glr("TRUSPORT_EE_ERROR_INVALID_ACTION");
        h = glrVar16;
        glr glrVar17 = new glr("TRUSPORT_EE_ERROR_INVALID_VERBOSITY");
        p = glrVar17;
        glr glrVar18 = new glr("TRUSPORT_EE_ERROR_INVALID_EE");
        k = glrVar18;
        glr glrVar19 = new glr("TRUSPORT_EE_ERROR_INVALID_SWOLF");
        t = glrVar19;
        glr glrVar20 = new glr("TRUSPORT_EE_ERROR_INVALID_DISTANCE");
        f = glrVar20;
        glr glrVar21 = new glr("TRUSPORT_EE_ERROR_INVALID_DURATION");
        g = glrVar21;
        glr glrVar22 = new glr("TRUSPORT_EE_ERROR_CORRUPTED_DATA");
        f12854a = glrVar22;
        glr glrVar23 = new glr("TRUSPORT_EE_ERROR_INCOMPATIBLE_COMMON");
        e = glrVar23;
        glr glrVar24 = new glr("TRUSPORT_EE_ERROR_UNKNOWN_ERROR");
        u = glrVar24;
        aa = new glr[]{glrVar, glrVar2, glrVar3, glrVar4, glrVar5, glrVar6, glrVar7, glrVar8, glrVar9, glrVar10, glrVar11, glrVar12, glrVar13, glrVar14, glrVar15, glrVar16, glrVar17, glrVar18, glrVar19, glrVar20, glrVar21, glrVar22, glrVar23, glrVar24};
        y = 0;
    }

    public String toString() {
        return this.ad;
    }

    public static glr c(int i2) {
        glr[] glrVarArr = aa;
        if (i2 < glrVarArr.length && i2 >= 0) {
            glr glrVar = glrVarArr[i2];
            if (glrVar.ab == i2) {
                return glrVar;
            }
        }
        int i3 = 0;
        while (true) {
            glr[] glrVarArr2 = aa;
            if (i3 < glrVarArr2.length) {
                glr glrVar2 = glrVarArr2[i3];
                if (glrVar2.ab == i2) {
                    return glrVar2;
                }
                i3++;
            } else {
                throw new IllegalArgumentException("No enum " + glr.class + " with value " + i2);
            }
        }
    }

    private glr(String str) {
        this.ad = str;
        int i2 = y;
        y = i2 + 1;
        this.ab = i2;
    }

    private glr(String str, int i2) {
        this.ad = str;
        this.ab = i2;
        y = i2 + 1;
    }
}
