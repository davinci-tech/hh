package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class AssetFb extends Table {
    public VideoFb video(VideoFb videoFb) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return videoFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public VideoFb video() {
        return video(new VideoFb());
    }

    public TitleFb title(TitleFb titleFb) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return titleFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public TitleFb title() {
        return title(new TitleFb());
    }

    public ImageFb img(ImageFb imageFb) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return imageFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public ImageFb img() {
        return img(new ImageFb());
    }

    public int id() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer filePathInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 1);
    }

    public ByteBuffer filePathAsByteBuffer() {
        return __vector_as_bytebuffer(18, 1);
    }

    public String filePath() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public DataFb data(DataFb dataFb) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return dataFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public DataFb data() {
        return data(new DataFb());
    }

    public ByteBuffer contextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public ByteBuffer contextAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public String context() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer aliasInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer aliasAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String alias() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public AssetFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startAssetFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(8);
    }

    public static AssetFb getRootAsAssetFb(ByteBuffer byteBuffer, AssetFb assetFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return assetFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static AssetFb getRootAsAssetFb(ByteBuffer byteBuffer) {
        return getRootAsAssetFb(byteBuffer, new AssetFb());
    }

    public static int endAssetFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createAssetFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        flatBufferBuilder.startTable(8);
        addFilePath(flatBufferBuilder, i8);
        addTitle(flatBufferBuilder, i7);
        addVideo(flatBufferBuilder, i6);
        addImg(flatBufferBuilder, i5);
        addData(flatBufferBuilder, i4);
        addContext(flatBufferBuilder, i3);
        addAlias(flatBufferBuilder, i2);
        addId(flatBufferBuilder, i);
        return endAssetFb(flatBufferBuilder);
    }

    public static void addVideo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addTitle(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addImg(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addFilePath(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static void addData(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static final class Vector extends BaseVector {
        public AssetFb get(AssetFb assetFb, int i) {
            return assetFb.__assign(AssetFb.__indirect(__element(i), this.bb), this.bb);
        }

        public AssetFb get(int i) {
            return get(new AssetFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addContext(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addAlias(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
