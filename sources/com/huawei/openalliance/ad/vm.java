package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

/* loaded from: classes5.dex */
public final class vm extends Table {
    public static void f(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(5, i, 0);
    }

    public static void e(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void d(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static void c(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void b(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6) {
        flatBufferBuilder.startTable(6);
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
