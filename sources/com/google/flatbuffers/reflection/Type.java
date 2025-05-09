package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes8.dex */
public final class Type extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }

    public static Type getRootAsType(ByteBuffer byteBuffer) {
        return getRootAsType(byteBuffer, new Type());
    }

    public static Type getRootAsType(ByteBuffer byteBuffer, Type type) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return type.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Type __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public byte baseType() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public byte element() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.get(__offset + this.bb_pos);
        }
        return (byte) 0;
    }

    public int index() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return -1;
    }

    public int fixedLength() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getShort(__offset + this.bb_pos) & 65535;
        }
        return 0;
    }

    public long baseSize() {
        if (__offset(12) != 0) {
            return this.bb.getInt(r0 + this.bb_pos) & 4294967295L;
        }
        return 4L;
    }

    public long elementSize() {
        if (__offset(14) != 0) {
            return this.bb.getInt(r0 + this.bb_pos) & 4294967295L;
        }
        return 0L;
    }

    public static int createType(FlatBufferBuilder flatBufferBuilder, byte b, byte b2, int i, int i2, long j, long j2) {
        flatBufferBuilder.startTable(6);
        addElementSize(flatBufferBuilder, j2);
        addBaseSize(flatBufferBuilder, j);
        addIndex(flatBufferBuilder, i);
        addFixedLength(flatBufferBuilder, i2);
        addElement(flatBufferBuilder, b2);
        addBaseType(flatBufferBuilder, b);
        return endType(flatBufferBuilder);
    }

    public static void startType(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(6);
    }

    public static void addBaseType(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(0, b, 0);
    }

    public static void addElement(FlatBufferBuilder flatBufferBuilder, byte b) {
        flatBufferBuilder.addByte(1, b, 0);
    }

    public static void addIndex(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(2, i, -1);
    }

    public static void addFixedLength(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addShort(3, (short) i, 0);
    }

    public static void addBaseSize(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(4, (int) j, 4);
    }

    public static void addElementSize(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(5, (int) j, 0);
    }

    public static int endType(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public Type get(int i) {
            return get(new Type(), i);
        }

        public Type get(Type type, int i) {
            return type.__assign(Type.__indirect(__element(i), this.bb), this.bb);
        }
    }
}
