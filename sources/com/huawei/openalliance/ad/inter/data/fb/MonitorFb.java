package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class MonitorFb extends Table {
    public StringVector urlVector(StringVector stringVector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector urlVector() {
        return urlVector(new StringVector());
    }

    public int urlLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public String url(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int retryFlag() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 1;
    }

    public ByteBuffer eventTypeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer eventTypeAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String eventType() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public MonitorFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startUrlVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startMonitorFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(3);
    }

    public static MonitorFb getRootAsMonitorFb(ByteBuffer byteBuffer, MonitorFb monitorFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return monitorFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static MonitorFb getRootAsMonitorFb(ByteBuffer byteBuffer) {
        return getRootAsMonitorFb(byteBuffer, new MonitorFb());
    }

    public static int endMonitorFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createUrlVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createMonitorFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3) {
        flatBufferBuilder.startTable(3);
        addRetryFlag(flatBufferBuilder, i3);
        addUrl(flatBufferBuilder, i2);
        addEventType(flatBufferBuilder, i);
        return endMonitorFb(flatBufferBuilder);
    }

    public static void addUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static final class Vector extends BaseVector {
        public MonitorFb get(MonitorFb monitorFb, int i) {
            return monitorFb.__assign(MonitorFb.__indirect(__element(i), this.bb), this.bb);
        }

        public MonitorFb get(int i) {
            return get(new MonitorFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addRetryFlag(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, 1);
    }

    public static void addEventType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
