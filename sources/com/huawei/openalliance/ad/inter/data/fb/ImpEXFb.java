package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes5.dex */
public final class ImpEXFb extends Table {
    public ByteBuffer valueInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer valueAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String value() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer keyInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer keyAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String key() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ImpEXFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startImpEXFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static ImpEXFb getRootAsImpEXFb(ByteBuffer byteBuffer, ImpEXFb impEXFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return impEXFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static ImpEXFb getRootAsImpEXFb(ByteBuffer byteBuffer) {
        return getRootAsImpEXFb(byteBuffer, new ImpEXFb());
    }

    public static int endImpEXFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createImpEXFb(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addValue(flatBufferBuilder, i2);
        addKey(flatBufferBuilder, i);
        return endImpEXFb(flatBufferBuilder);
    }

    /* loaded from: classes9.dex */
    public static final class Vector extends BaseVector {
        public ImpEXFb get(ImpEXFb impEXFb, int i) {
            return impEXFb.__assign(ImpEXFb.__indirect(__element(i), this.bb), this.bb);
        }

        public ImpEXFb get(int i) {
            return get(new ImpEXFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addValue(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addKey(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
