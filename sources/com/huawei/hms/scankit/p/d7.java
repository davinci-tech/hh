package com.huawei.hms.scankit.p;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.openalliance.ad.constant.ErrorCode;
import org.eclipse.californium.scandium.dtls.RecordLayer;

/* loaded from: classes4.dex */
public class d7 {
    static final d7[] i;
    private static d7[] j;

    /* renamed from: a, reason: collision with root package name */
    private final boolean f5764a;
    private final int b;
    private final int c;
    public final int d;
    public final int e;
    private final int f;
    private final int g;
    private final int h;

    static {
        d7[] d7VarArr = {new d7(false, 3, 5, 8, 8, 1), new d7(false, 5, 7, 10, 10, 1), new d7(true, 5, 7, 16, 6, 1), new d7(false, 8, 10, 12, 12, 1), new d7(true, 10, 11, 14, 6, 2), new d7(false, 12, 12, 14, 14, 1), new d7(true, 16, 14, 24, 10, 1), new d7(false, 18, 14, 16, 16, 1), new d7(false, 22, 18, 18, 18, 1), new d7(true, 22, 18, 16, 10, 2), new d7(false, 30, 20, 20, 20, 1), new d7(true, 32, 24, 16, 14, 2), new d7(false, 36, 24, 22, 22, 1), new d7(false, 44, 28, 24, 24, 1), new d7(true, 49, 28, 22, 14, 2), new d7(false, 62, 36, 14, 14, 4), new d7(false, 86, 42, 16, 16, 4), new d7(false, 114, 48, 18, 18, 4), new d7(false, 144, 56, 20, 20, 4), new d7(false, 174, 68, 22, 22, 4), new d7(false, 204, 84, 24, 24, 4, 102, 42), new d7(false, OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE, 112, 14, 14, 16, 140, 56), new d7(false, 368, 144, 16, 16, 16, 92, 36), new d7(false, 456, 192, 18, 18, 16, 114, 48), new d7(false, RecordLayer.DEFAULT_IPV4_MTU, 224, 20, 20, 16, 144, 56), new d7(false, 696, 272, 22, 22, 16, 174, 68), new d7(false, 816, 336, 24, 24, 16, 136, 56), new d7(false, 1050, 408, 18, 18, 36, 175, 68), new d7(false, 1304, ErrorCode.ERROR_CODE_TRIGGER_DISTURB, 20, 20, 36, MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT, 62), new i1()};
        i = d7VarArr;
        j = d7VarArr;
    }

    public d7(boolean z, int i2, int i3, int i4, int i5, int i6) {
        this(z, i2, i3, i4, i5, i6, i2, i3);
    }

    public static d7 a(int i2, e7 e7Var, l2 l2Var, l2 l2Var2, boolean z) {
        for (d7 d7Var : j) {
            if (!(e7Var == e7.FORCE_SQUARE && d7Var.f5764a) && ((e7Var != e7.FORCE_RECTANGLE || d7Var.f5764a) && ((l2Var == null || (d7Var.h() >= l2Var.b() && d7Var.g() >= l2Var.a())) && ((l2Var2 == null || (d7Var.h() <= l2Var2.b() && d7Var.g() <= l2Var2.a())) && i2 <= d7Var.b)))) {
                return d7Var;
            }
        }
        if (!z) {
            return null;
        }
        throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: " + i2);
    }

    private int c() {
        int i2 = this.f;
        int i3 = 1;
        if (i2 != 1) {
            i3 = 2;
            if (i2 != 2 && i2 != 4) {
                if (i2 == 16) {
                    return 4;
                }
                if (i2 == 36) {
                    return 6;
                }
                throw new IllegalStateException("Cannot handle this number of data regions");
            }
        }
        return i3;
    }

    private int i() {
        int i2 = this.f;
        if (i2 == 1 || i2 == 2) {
            return 1;
        }
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 36) {
            return 6;
        }
        throw new IllegalStateException("Cannot handle this number of data regions");
    }

    public final int b() {
        return this.c;
    }

    public int d() {
        return this.b / this.g;
    }

    public final int e() {
        return i() * this.e;
    }

    public final int f() {
        return c() * this.d;
    }

    public final int g() {
        return e() + (i() * 2);
    }

    public final int h() {
        return f() + (c() * 2);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5764a ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.d);
        sb.append('x');
        sb.append(this.e);
        sb.append(", symbol size ");
        sb.append(h());
        sb.append('x');
        sb.append(g());
        sb.append(", symbol data size ");
        sb.append(f());
        sb.append('x');
        sb.append(e());
        sb.append(", codewords ");
        sb.append(this.b);
        sb.append('+');
        sb.append(this.c);
        return sb.toString();
    }

    d7(boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.f5764a = z;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        this.h = i8;
    }

    public final int b(int i2) {
        return this.h;
    }

    public final int a() {
        return this.b;
    }

    public int a(int i2) {
        return this.g;
    }
}
