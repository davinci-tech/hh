package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class FeedbackInfoFb extends Table {
    public int type() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer labelInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer labelAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String label() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public long id() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public FeedbackInfoFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startFeedbackInfoFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static FeedbackInfoFb getRootAsFeedbackInfoFb(ByteBuffer byteBuffer, FeedbackInfoFb feedbackInfoFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return feedbackInfoFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static FeedbackInfoFb getRootAsFeedbackInfoFb(ByteBuffer byteBuffer) {
        return getRootAsFeedbackInfoFb(byteBuffer, new FeedbackInfoFb());
    }

    public static int endFeedbackInfoFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createFeedbackInfoFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, long j) {
        flatBufferBuilder.startTable(3);
        addId(flatBufferBuilder, j);
        addType(flatBufferBuilder, i2);
        addLabel(flatBufferBuilder, i);
        return endFeedbackInfoFb(flatBufferBuilder);
    }

    public static void addType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static final class Vector extends BaseVector {
        public FeedbackInfoFb get(FeedbackInfoFb feedbackInfoFb, int i) {
            return feedbackInfoFb.__assign(FeedbackInfoFb.__indirect(__element(i), this.bb), this.bb);
        }

        public FeedbackInfoFb get(int i) {
            return get(new FeedbackInfoFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addLabel(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(2, j, 0L);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
