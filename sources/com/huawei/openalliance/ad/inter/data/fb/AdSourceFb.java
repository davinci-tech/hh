package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class AdSourceFb extends Table {
    public ByteBuffer dspNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer dspNameAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String dspName() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer dspLogoInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer dspLogoAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String dspLogo() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int displayPosition() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public AdSourceFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startAdSourceFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static AdSourceFb getRootAsAdSourceFb(ByteBuffer byteBuffer, AdSourceFb adSourceFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return adSourceFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static AdSourceFb getRootAsAdSourceFb(ByteBuffer byteBuffer) {
        return getRootAsAdSourceFb(byteBuffer, new AdSourceFb());
    }

    public static int endAdSourceFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createAdSourceFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        addDisplayPosition(flatBufferBuilder, i3);
        addDspLogo(flatBufferBuilder, i2);
        addDspName(flatBufferBuilder, i);
        return endAdSourceFb(flatBufferBuilder);
    }

    public static void addDspName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static final class Vector extends BaseVector {
        public AdSourceFb get(AdSourceFb adSourceFb, int i) {
            return adSourceFb.__assign(AdSourceFb.__indirect(__element(i), this.bb), this.bb);
        }

        public AdSourceFb get(int i) {
            return get(new AdSourceFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addDspLogo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addDisplayPosition(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
