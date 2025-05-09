package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class MotionDataFb extends Table {
    public int width() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer urlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer urlAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String url() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int size() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer sha256InByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 1);
    }

    public ByteBuffer sha256AsByteBuffer() {
        return __vector_as_bytebuffer(16, 1);
    }

    public String sha256() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int height() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer formatInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 12, 1);
    }

    public ByteBuffer formatAsByteBuffer() {
        return __vector_as_bytebuffer(12, 1);
    }

    public String format() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer filePathInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 20, 1);
    }

    public ByteBuffer filePathAsByteBuffer() {
        return __vector_as_bytebuffer(20, 1);
    }

    public String filePath() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int duration() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public long dataId() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public MotionDataFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startMotionDataFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(9);
    }

    public static MotionDataFb getRootAsMotionDataFb(ByteBuffer byteBuffer, MotionDataFb motionDataFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return motionDataFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static MotionDataFb getRootAsMotionDataFb(ByteBuffer byteBuffer) {
        return getRootAsMotionDataFb(byteBuffer, new MotionDataFb());
    }

    public static int endMotionDataFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createMotionDataFb(FlatBufferBuilder flatBufferBuilder, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        flatBufferBuilder.startTable(9);
        addDataId(flatBufferBuilder, j);
        addFilePath(flatBufferBuilder, i8);
        addDuration(flatBufferBuilder, i7);
        addSha256(flatBufferBuilder, i6);
        addSize(flatBufferBuilder, i5);
        addFormat(flatBufferBuilder, i4);
        addHeight(flatBufferBuilder, i3);
        addWidth(flatBufferBuilder, i2);
        addUrl(flatBufferBuilder, i);
        return endMotionDataFb(flatBufferBuilder);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void addUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(5, i, 0);
    }

    public static void addSha256(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static void addFormat(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void addFilePath(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static final class Vector extends BaseVector {
        public MotionDataFb get(MotionDataFb motionDataFb, int i) {
            return motionDataFb.__assign(MotionDataFb.__indirect(__element(i), this.bb), this.bb);
        }

        public MotionDataFb get(int i) {
            return get(new MotionDataFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addDuration(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(7, i, 0);
    }

    public static void addDataId(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(0, j, 0L);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
