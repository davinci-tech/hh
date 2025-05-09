package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class AudioInfoFb extends Table {
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
        return __vector_in_bytebuffer(byteBuffer, 10, 1);
    }

    public ByteBuffer sha256AsByteBuffer() {
        return __vector_as_bytebuffer(10, 1);
    }

    public String sha256() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer mimeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 12, 1);
    }

    public ByteBuffer mimeAsByteBuffer() {
        return __vector_as_bytebuffer(12, 1);
    }

    public String mime() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int fileSize() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int duration() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public AudioInfoFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startAudioInfoFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(5);
    }

    public static AudioInfoFb getRootAsAudioInfoFb(ByteBuffer byteBuffer, AudioInfoFb audioInfoFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return audioInfoFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static AudioInfoFb getRootAsAudioInfoFb(ByteBuffer byteBuffer) {
        return getRootAsAudioInfoFb(byteBuffer, new AudioInfoFb());
    }

    public static int endAudioInfoFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createAudioInfoFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5) {
        flatBufferBuilder.startTable(5);
        addMime(flatBufferBuilder, i5);
        addSha256(flatBufferBuilder, i4);
        addFileSize(flatBufferBuilder, i3);
        addDuration(flatBufferBuilder, i2);
        addUrl(flatBufferBuilder, i);
        return endAudioInfoFb(flatBufferBuilder);
    }

    public static void addUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addSha256(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addMime(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static final class Vector extends BaseVector {
        public AudioInfoFb get(AudioInfoFb audioInfoFb, int i) {
            return audioInfoFb.__assign(AudioInfoFb.__indirect(__element(i), this.bb), this.bb);
        }

        public AudioInfoFb get(int i) {
            return get(new AudioInfoFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addFileSize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 0);
    }

    public static void addDuration(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
