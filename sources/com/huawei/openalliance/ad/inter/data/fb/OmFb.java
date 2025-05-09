package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class OmFb extends Table {
    public ByteBuffer verificationParametersInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public ByteBuffer verificationParametersAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public String verificationParameters() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer vendorKeyInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer vendorKeyAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String vendorKey() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer resourceUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer resourceUrlAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String resourceUrl() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public OmFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startOmFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static OmFb getRootAsOmFb(ByteBuffer byteBuffer, OmFb omFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return omFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static OmFb getRootAsOmFb(ByteBuffer byteBuffer) {
        return getRootAsOmFb(byteBuffer, new OmFb());
    }

    public static int endOmFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createOmFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        addVerificationParameters(flatBufferBuilder, i3);
        addResourceUrl(flatBufferBuilder, i2);
        addVendorKey(flatBufferBuilder, i);
        return endOmFb(flatBufferBuilder);
    }

    public static void addVerificationParameters(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static final class Vector extends BaseVector {
        public OmFb get(OmFb omFb, int i) {
            return omFb.__assign(OmFb.__indirect(__element(i), this.bb), this.bb);
        }

        public OmFb get(int i) {
            return get(new OmFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addVendorKey(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addResourceUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
