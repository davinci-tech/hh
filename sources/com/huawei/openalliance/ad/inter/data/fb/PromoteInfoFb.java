package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class PromoteInfoFb extends Table {
    public int type() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer nameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String name() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public PromoteInfoFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startPromoteInfoFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static PromoteInfoFb getRootAsPromoteInfoFb(ByteBuffer byteBuffer, PromoteInfoFb promoteInfoFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return promoteInfoFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static PromoteInfoFb getRootAsPromoteInfoFb(ByteBuffer byteBuffer) {
        return getRootAsPromoteInfoFb(byteBuffer, new PromoteInfoFb());
    }

    public static int endPromoteInfoFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createPromoteInfoFb(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addName(flatBufferBuilder, i2);
        addType(flatBufferBuilder, i);
        return endPromoteInfoFb(flatBufferBuilder);
    }

    public static final class Vector extends BaseVector {
        public PromoteInfoFb get(PromoteInfoFb promoteInfoFb, int i) {
            return promoteInfoFb.__assign(PromoteInfoFb.__indirect(__element(i), this.bb), this.bb);
        }

        public PromoteInfoFb get(int i) {
            return get(new PromoteInfoFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
