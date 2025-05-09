package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class ImageExtFb extends Table {
    public long size() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public ByteBuffer sha256InByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer sha256AsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String sha256() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer formatInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public ByteBuffer formatAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public String format() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int checkSha256Flag() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ImageExtFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startImageExtFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static ImageExtFb getRootAsImageExtFb(ByteBuffer byteBuffer, ImageExtFb imageExtFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return imageExtFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static ImageExtFb getRootAsImageExtFb(ByteBuffer byteBuffer) {
        return getRootAsImageExtFb(byteBuffer, new ImageExtFb());
    }

    public static int endImageExtFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createImageExtFb(FlatBufferBuilder flatBufferBuilder, long j, int i, int i2, int i3) {
        flatBufferBuilder.startTable(4);
        addSize(flatBufferBuilder, j);
        addCheckSha256Flag(flatBufferBuilder, i3);
        addFormat(flatBufferBuilder, i2);
        addSha256(flatBufferBuilder, i);
        return endImageExtFb(flatBufferBuilder);
    }

    public static void addSize(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(0, j, 0L);
    }

    public static void addSha256(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static final class Vector extends BaseVector {
        public ImageExtFb get(ImageExtFb imageExtFb, int i) {
            return imageExtFb.__assign(ImageExtFb.__indirect(__element(i), this.bb), this.bb);
        }

        public ImageExtFb get(int i) {
            return get(new ImageExtFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addFormat(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addCheckSha256Flag(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
