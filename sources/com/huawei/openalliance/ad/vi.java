package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

/* loaded from: classes5.dex */
public final class vi extends Table {
    public static void z(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(25, i, 0);
    }

    public static void y(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(24, i, 0);
    }

    public static void x(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(23, i, 0);
    }

    public static void w(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(22, i, 0);
    }

    public static void v(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(21, i, 0);
    }

    public static void u(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(20, i, 0);
    }

    public static void t(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(19, i, 0);
    }

    public static void s(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(18, i, 0);
    }

    public static void r(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(17, i, 0);
    }

    public static void q(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(16, i, 0);
    }

    public static void p(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(15, i, 0);
    }

    public static void o(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(14, i, 0);
    }

    public static void n(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(13, i, 0);
    }

    public static void m(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(12, i, 0);
    }

    public static void l(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(11, i, 0);
    }

    public static void k(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(10, i, 0);
    }

    public static void j(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(9, i, 0);
    }

    public static void i(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static void h(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(7, i, 0);
    }

    public static void g(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void f(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(5, i, 0);
    }

    public static void e(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(4, i, 0);
    }

    public static int e(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void d(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static int d(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void c(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static int c(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void b(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static int b(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(30, f, 0.0d);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, float f, int i31, int i32, int i33, int i34) {
        flatBufferBuilder.startTable(35);
        H(flatBufferBuilder, i34);
        G(flatBufferBuilder, i33);
        F(flatBufferBuilder, i32);
        E(flatBufferBuilder, i31);
        a(flatBufferBuilder, f);
        D(flatBufferBuilder, i30);
        C(flatBufferBuilder, i29);
        B(flatBufferBuilder, i28);
        A(flatBufferBuilder, i27);
        z(flatBufferBuilder, i26);
        y(flatBufferBuilder, i25);
        x(flatBufferBuilder, i24);
        w(flatBufferBuilder, i23);
        v(flatBufferBuilder, i22);
        u(flatBufferBuilder, i21);
        t(flatBufferBuilder, i20);
        s(flatBufferBuilder, i19);
        r(flatBufferBuilder, i18);
        q(flatBufferBuilder, i17);
        p(flatBufferBuilder, i16);
        o(flatBufferBuilder, i15);
        n(flatBufferBuilder, i14);
        m(flatBufferBuilder, i13);
        l(flatBufferBuilder, i12);
        k(flatBufferBuilder, i11);
        j(flatBufferBuilder, i10);
        i(flatBufferBuilder, i9);
        h(flatBufferBuilder, i8);
        g(flatBufferBuilder, i7);
        f(flatBufferBuilder, i6);
        e(flatBufferBuilder, i5);
        d(flatBufferBuilder, i4);
        c(flatBufferBuilder, i3);
        b(flatBufferBuilder, i2);
        a(flatBufferBuilder, i);
        return a(flatBufferBuilder);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static void H(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(34, i, 0);
    }

    public static void G(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(33, i, 0);
    }

    public static void F(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(32, i, 0);
    }

    public static void E(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(31, i, 0);
    }

    public static void D(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(29, i, 0);
    }

    public static void C(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(28, i, 0);
    }

    public static void B(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(27, i, 0);
    }

    public static void A(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(26, i, 0);
    }
}
