package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import com.huawei.openalliance.ad.inter.data.fb.MotionDataFb;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class TemplateDataFb extends Table {
    public ByteBuffer templateContextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer templateContextAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String templateContext() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer motionsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer motionsAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String motions() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public MotionDataFb.Vector motionDataVector(MotionDataFb.Vector vector) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public MotionDataFb.Vector motionDataVector() {
        return motionDataVector(new MotionDataFb.Vector());
    }

    public int motionDataLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public MotionDataFb motionData(MotionDataFb motionDataFb, int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return motionDataFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public MotionDataFb motionData(int i) {
        return motionData(new MotionDataFb(), i);
    }

    public ByteBuffer componentContextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 10, 1);
    }

    public ByteBuffer componentContextAsByteBuffer() {
        return __vector_as_bytebuffer(10, 1);
    }

    public String componentContext() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public TemplateDataFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startTemplateDataFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(4);
    }

    public static void startMotionDataVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static TemplateDataFb getRootAsTemplateDataFb(ByteBuffer byteBuffer, TemplateDataFb templateDataFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return templateDataFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static TemplateDataFb getRootAsTemplateDataFb(ByteBuffer byteBuffer) {
        return getRootAsTemplateDataFb(byteBuffer, new TemplateDataFb());
    }

    public static int endTemplateDataFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createTemplateDataFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4) {
        flatBufferBuilder.startTable(4);
        addComponentContext(flatBufferBuilder, i4);
        addMotionData(flatBufferBuilder, i3);
        addMotions(flatBufferBuilder, i2);
        addTemplateContext(flatBufferBuilder, i);
        return endTemplateDataFb(flatBufferBuilder);
    }

    public static int createMotionDataVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void addTemplateContext(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addMotions(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static final class Vector extends BaseVector {
        public TemplateDataFb get(TemplateDataFb templateDataFb, int i) {
            return templateDataFb.__assign(TemplateDataFb.__indirect(__element(i), this.bb), this.bb);
        }

        public TemplateDataFb get(int i) {
            return get(new TemplateDataFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addMotionData(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addComponentContext(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
