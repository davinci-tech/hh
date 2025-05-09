package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class ImageFb extends Table {
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

    public ByteBuffer localPathInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 12, 1);
    }

    public ByteBuffer localPathAsByteBuffer() {
        return __vector_as_bytebuffer(12, 1);
    }

    public String localPath() {
        int __offset = __offset(12);
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

    public ImageExtFb ext(ImageExtFb imageExtFb) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return imageExtFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public ImageExtFb ext() {
        return ext(new ImageExtFb());
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public ImageFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startImageFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(5);
    }

    public static ImageFb getRootAsImageFb(ByteBuffer byteBuffer, ImageFb imageFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return imageFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static ImageFb getRootAsImageFb(ByteBuffer byteBuffer) {
        return getRootAsImageFb(byteBuffer, new ImageFb());
    }

    public static int endImageFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createImageFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5) {
        flatBufferBuilder.startTable(5);
        addLocalPath(flatBufferBuilder, i5);
        addExt(flatBufferBuilder, i4);
        addHeight(flatBufferBuilder, i3);
        addWidth(flatBufferBuilder, i2);
        addUrl(flatBufferBuilder, i);
        return endImageFb(flatBufferBuilder);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addLocalPath(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static final class Vector extends BaseVector {
        public ImageFb get(ImageFb imageFb, int i) {
            return imageFb.__assign(ImageFb.__indirect(__element(i), this.bb), this.bb);
        }

        public ImageFb get(int i) {
            return get(new ImageFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void addExt(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
