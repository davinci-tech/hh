package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

/* loaded from: classes5.dex */
public final class vp extends Table {
    public static void c(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(5, i, 0);
    }

    public static void b(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(4, i, 0);
    }

    public static void b(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(3, f, 0.0d);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(0, j, 0L);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(2, f, 0.0d);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, long j, int i, float f, float f2, int i2, int i3) {
        flatBufferBuilder.startTable(6);
        a(flatBufferBuilder, j);
        c(flatBufferBuilder, i3);
        b(flatBufferBuilder, i2);
        b(flatBufferBuilder, f2);
        a(flatBufferBuilder, f);
        a(flatBufferBuilder, i);
        return a(flatBufferBuilder);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }
}
