package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class AdvertiserInfoFb extends Table {
    public ByteBuffer valueInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public ByteBuffer valueAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public String value() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int seq() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer keyInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer keyAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String key() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public AdvertiserInfoFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startAdvertiserInfoFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static AdvertiserInfoFb getRootAsAdvertiserInfoFb(ByteBuffer byteBuffer, AdvertiserInfoFb advertiserInfoFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return advertiserInfoFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static AdvertiserInfoFb getRootAsAdvertiserInfoFb(ByteBuffer byteBuffer) {
        return getRootAsAdvertiserInfoFb(byteBuffer, new AdvertiserInfoFb());
    }

    public static int endAdvertiserInfoFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createAdvertiserInfoFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        addValue(flatBufferBuilder, i3);
        addKey(flatBufferBuilder, i2);
        addSeq(flatBufferBuilder, i);
        return endAdvertiserInfoFb(flatBufferBuilder);
    }

    public static void addValue(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static final class Vector extends BaseVector {
        public AdvertiserInfoFb get(AdvertiserInfoFb advertiserInfoFb, int i) {
            return advertiserInfoFb.__assign(AdvertiserInfoFb.__indirect(__element(i), this.bb), this.bb);
        }

        public AdvertiserInfoFb get(int i) {
            return get(new AdvertiserInfoFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addSeq(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addKey(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
