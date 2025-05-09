package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class VideoFb extends Table {
    public int width() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer urlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer urlAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String url() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public long size() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public ByteBuffer sha256InByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 14, 1);
    }

    public ByteBuffer sha256AsByteBuffer() {
        return __vector_as_bytebuffer(14, 1);
    }

    public String sha256() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer localPathInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 20, 1);
    }

    public ByteBuffer localPathAsByteBuffer() {
        return __vector_as_bytebuffer(20, 1);
    }

    public String localPath() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int height() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer formatInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 1);
    }

    public ByteBuffer formatAsByteBuffer() {
        return __vector_as_bytebuffer(16, 1);
    }

    public String format() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public long duration() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public int checkSha256Flag() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public VideoFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startVideoFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(9);
    }

    public static VideoFb getRootAsVideoFb(ByteBuffer byteBuffer, VideoFb videoFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return videoFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static VideoFb getRootAsVideoFb(ByteBuffer byteBuffer) {
        return getRootAsVideoFb(byteBuffer, new VideoFb());
    }

    public static int endVideoFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createVideoFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, long j, long j2, int i4, int i5, int i6, int i7) {
        flatBufferBuilder.startTable(9);
        addSize(flatBufferBuilder, j2);
        addDuration(flatBufferBuilder, j);
        addLocalPath(flatBufferBuilder, i7);
        addCheckSha256Flag(flatBufferBuilder, i6);
        addFormat(flatBufferBuilder, i5);
        addSha256(flatBufferBuilder, i4);
        addHeight(flatBufferBuilder, i3);
        addWidth(flatBufferBuilder, i2);
        addUrl(flatBufferBuilder, i);
        return endVideoFb(flatBufferBuilder);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addSize(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(4, j, 0L);
    }

    public static void addSha256(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addLocalPath(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void addFormat(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static final class Vector extends BaseVector {
        public VideoFb get(VideoFb videoFb, int i) {
            return videoFb.__assign(VideoFb.__indirect(__element(i), this.bb), this.bb);
        }

        public VideoFb get(int i) {
            return get(new VideoFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addDuration(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(3, j, 0L);
    }

    public static void addCheckSha256Flag(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(7, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
