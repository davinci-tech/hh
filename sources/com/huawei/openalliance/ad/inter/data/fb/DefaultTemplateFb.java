package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class DefaultTemplateFb extends Table {
    public int tptFcCtl() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer defTptIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer defTptIdAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String defTptId() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public DefaultTemplateFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startDefaultTemplateFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static DefaultTemplateFb getRootAsDefaultTemplateFb(ByteBuffer byteBuffer, DefaultTemplateFb defaultTemplateFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return defaultTemplateFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static DefaultTemplateFb getRootAsDefaultTemplateFb(ByteBuffer byteBuffer) {
        return getRootAsDefaultTemplateFb(byteBuffer, new DefaultTemplateFb());
    }

    public static int endDefaultTemplateFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createDefaultTemplateFb(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addTptFcCtl(flatBufferBuilder, i2);
        addDefTptId(flatBufferBuilder, i);
        return endDefaultTemplateFb(flatBufferBuilder);
    }

    public static final class Vector extends BaseVector {
        public DefaultTemplateFb get(DefaultTemplateFb defaultTemplateFb, int i) {
            return defaultTemplateFb.__assign(DefaultTemplateFb.__indirect(__element(i), this.bb), this.bb);
        }

        public DefaultTemplateFb get(int i) {
            return get(new DefaultTemplateFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addTptFcCtl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addDefTptId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
