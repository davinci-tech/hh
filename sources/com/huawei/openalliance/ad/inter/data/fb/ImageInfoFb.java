package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class ImageInfoFb extends Table {
    public int width() {
        int __offset = __offset(8);
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

    public ByteBuffer originalUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer originalUrlAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String originalUrl() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer imageTypeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 1);
    }

    public ByteBuffer imageTypeAsByteBuffer() {
        return __vector_as_bytebuffer(18, 1);
    }

    public String imageType() {
        int __offset = __offset(18);
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

    public int fileSize() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public boolean checkSha256() {
        int __offset = __offset(16);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ImageInfoFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startImageInfoFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(8);
    }

    public static ImageInfoFb getRootAsImageInfoFb(ByteBuffer byteBuffer, ImageInfoFb imageInfoFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return imageInfoFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static ImageInfoFb getRootAsImageInfoFb(ByteBuffer byteBuffer) {
        return getRootAsImageInfoFb(byteBuffer, new ImageInfoFb());
    }

    public static int endImageInfoFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createImageInfoFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, boolean z, int i7) {
        flatBufferBuilder.startTable(8);
        addImageType(flatBufferBuilder, i7);
        addSha256(flatBufferBuilder, i6);
        addFileSize(flatBufferBuilder, i5);
        addHeight(flatBufferBuilder, i4);
        addWidth(flatBufferBuilder, i3);
        addOriginalUrl(flatBufferBuilder, i2);
        addUrl(flatBufferBuilder, i);
        addCheckSha256(flatBufferBuilder, z);
        return endImageInfoFb(flatBufferBuilder);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void addUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addSha256(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addOriginalUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addImageType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static final class Vector extends BaseVector {
        public ImageInfoFb get(ImageInfoFb imageInfoFb, int i) {
            return imageInfoFb.__assign(ImageInfoFb.__indirect(__element(i), this.bb), this.bb);
        }

        public ImageInfoFb get(int i) {
            return get(new ImageInfoFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addFileSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(4, i, 0);
    }

    public static void addCheckSha256(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(6, z, false);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
