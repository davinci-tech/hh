package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

/* loaded from: classes5.dex */
public final class vn extends Table {
    public static void c(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void b(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int a(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        c(flatBufferBuilder, i3);
        b(flatBufferBuilder, i2);
        a(flatBufferBuilder, i);
        return a(flatBufferBuilder);
    }

    public static int a(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }
}
