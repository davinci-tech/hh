package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class TitleFb extends Table {
    public ByteBuffer textInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer textAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String text() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int len() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public TitleFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startTitleFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static TitleFb getRootAsTitleFb(ByteBuffer byteBuffer, TitleFb titleFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return titleFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static TitleFb getRootAsTitleFb(ByteBuffer byteBuffer) {
        return getRootAsTitleFb(byteBuffer, new TitleFb());
    }

    public static int endTitleFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createTitleFb(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addLen(flatBufferBuilder, i2);
        addText(flatBufferBuilder, i);
        return endTitleFb(flatBufferBuilder);
    }

    public static final class Vector extends BaseVector {
        public TitleFb get(TitleFb titleFb, int i) {
            return titleFb.__assign(TitleFb.__indirect(__element(i), this.bb), this.bb);
        }

        public TitleFb get(int i) {
            return get(new TitleFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addText(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addLen(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
