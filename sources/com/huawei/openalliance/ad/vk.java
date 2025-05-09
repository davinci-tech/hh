package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

/* loaded from: classes5.dex */
public final class vk extends Table {
    public static void n(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(13, i, 0);
    }

    public static void m(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(12, i, 0);
    }

    public static void l(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(11, i, 0);
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
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static void g(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void f(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void e(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void d(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void c(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void b(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        flatBufferBuilder.startTable(14);
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
}
