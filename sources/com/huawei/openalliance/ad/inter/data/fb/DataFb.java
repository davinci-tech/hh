package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class DataFb extends Table {
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

    public int type() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int len() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public DataFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startDataFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static DataFb getRootAsDataFb(ByteBuffer byteBuffer, DataFb dataFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return dataFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static DataFb getRootAsDataFb(ByteBuffer byteBuffer) {
        return getRootAsDataFb(byteBuffer, new DataFb());
    }

    public static int endDataFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createDataFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        addLen(flatBufferBuilder, i3);
        addValue(flatBufferBuilder, i2);
        addType(flatBufferBuilder, i);
        return endDataFb(flatBufferBuilder);
    }

    public static void addValue(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static final class Vector extends BaseVector {
        public DataFb get(DataFb dataFb, int i) {
            return dataFb.__assign(DataFb.__indirect(__element(i), this.bb), this.bb);
        }

        public DataFb get(int i) {
            return get(new DataFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addLen(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
