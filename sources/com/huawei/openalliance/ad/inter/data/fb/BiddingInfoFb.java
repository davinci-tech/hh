package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class BiddingInfoFb extends Table {
    public float price() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public ByteBuffer nurlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 10, 1);
    }

    public ByteBuffer nurlAsByteBuffer() {
        return __vector_as_bytebuffer(10, 1);
    }

    public String nurl() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer lurlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public ByteBuffer lurlAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public String lurl() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer curInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer curAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String cur() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public BiddingInfoFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startBiddingInfoFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static BiddingInfoFb getRootAsBiddingInfoFb(ByteBuffer byteBuffer, BiddingInfoFb biddingInfoFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return biddingInfoFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static BiddingInfoFb getRootAsBiddingInfoFb(ByteBuffer byteBuffer) {
        return getRootAsBiddingInfoFb(byteBuffer, new BiddingInfoFb());
    }

    public static int endBiddingInfoFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createBiddingInfoFb(FlatBufferBuilder flatBufferBuilder, int i, float f, int i2, int i3) {
        flatBufferBuilder.startTable(4);
        addNurl(flatBufferBuilder, i3);
        addLurl(flatBufferBuilder, i2);
        addPrice(flatBufferBuilder, f);
        addCur(flatBufferBuilder, i);
        return endBiddingInfoFb(flatBufferBuilder);
    }

    public static void addPrice(FlatBufferBuilder flatBufferBuilder, float f) {
        flatBufferBuilder.addFloat(1, f, 0.0d);
    }

    public static void addNurl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static final class Vector extends BaseVector {
        public BiddingInfoFb get(BiddingInfoFb biddingInfoFb, int i) {
            return biddingInfoFb.__assign(BiddingInfoFb.__indirect(__element(i), this.bb), this.bb);
        }

        public BiddingInfoFb get(int i) {
            return get(new BiddingInfoFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addLurl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addCur(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
