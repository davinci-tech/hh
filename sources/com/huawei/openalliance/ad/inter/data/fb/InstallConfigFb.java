package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class InstallConfigFb extends Table {
    public ByteBuffer creativeInstallWayInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer creativeInstallWayAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String creativeInstallWay() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer contentBtnInstallWayInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public ByteBuffer contentBtnInstallWayAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public String contentBtnInstallWay() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer appBtnInstallWayInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer appBtnInstallWayAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String appBtnInstallWay() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public InstallConfigFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startInstallConfigFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static InstallConfigFb getRootAsInstallConfigFb(ByteBuffer byteBuffer, InstallConfigFb installConfigFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return installConfigFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static InstallConfigFb getRootAsInstallConfigFb(ByteBuffer byteBuffer) {
        return getRootAsInstallConfigFb(byteBuffer, new InstallConfigFb());
    }

    public static int endInstallConfigFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createInstallConfigFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        addContentBtnInstallWay(flatBufferBuilder, i3);
        addAppBtnInstallWay(flatBufferBuilder, i2);
        addCreativeInstallWay(flatBufferBuilder, i);
        return endInstallConfigFb(flatBufferBuilder);
    }

    public static void addCreativeInstallWay(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static final class Vector extends BaseVector {
        public InstallConfigFb get(InstallConfigFb installConfigFb, int i) {
            return installConfigFb.__assign(InstallConfigFb.__indirect(__element(i), this.bb), this.bb);
        }

        public InstallConfigFb get(int i) {
            return get(new InstallConfigFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addContentBtnInstallWay(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addAppBtnInstallWay(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
