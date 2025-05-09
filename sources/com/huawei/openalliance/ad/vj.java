package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

/* loaded from: classes5.dex */
public final class vj extends Table {
    public static void b(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startTable(1);
        b(flatBufferBuilder, i);
        return a(flatBufferBuilder);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }
}
