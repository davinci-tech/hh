package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

/* loaded from: classes5.dex */
public final class vq extends Table {
    public static void b(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(2, f, 0.0d);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, int i, int i2, float f) {
        flatBufferBuilder.startTable(3);
        a(flatBufferBuilder, f);
        b(flatBufferBuilder, i2);
        a(flatBufferBuilder, i);
        return a(flatBufferBuilder);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }
}
