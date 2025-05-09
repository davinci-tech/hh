package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/* loaded from: classes8.dex */
public final class KeyValue extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }

    public static KeyValue getRootAsKeyValue(ByteBuffer byteBuffer) {
        return getRootAsKeyValue(byteBuffer, new KeyValue());
    }

    public static KeyValue getRootAsKeyValue(ByteBuffer byteBuffer, KeyValue keyValue) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return keyValue.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public KeyValue __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String key() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer keyAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer keyInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public String value() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer valueAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public ByteBuffer valueInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public static int createKeyValue(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addValue(flatBufferBuilder, i2);
        addKey(flatBufferBuilder, i);
        return endKeyValue(flatBufferBuilder);
    }

    public static void startKeyValue(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addKey(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(i);
        flatBufferBuilder.slot(0);
    }

    public static void addValue(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int endKeyValue(FlatBufferBuilder flatBufferBuilder) {
        int endTable = flatBufferBuilder.endTable();
        flatBufferBuilder.required(endTable, 4);
        return endTable;
    }

    @Override // com.google.flatbuffers.Table
    public int keysCompare(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return compareStrings(__offset(4, num.intValue(), byteBuffer), __offset(4, num2.intValue(), byteBuffer), byteBuffer);
    }

    public static KeyValue __lookup_by_key(KeyValue keyValue, int i, String str, ByteBuffer byteBuffer) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int i2 = byteBuffer.getInt(i - 4);
        int i3 = 0;
        while (i2 != 0) {
            int i4 = i2 / 2;
            int __indirect = __indirect(((i3 + i4) * 4) + i, byteBuffer);
            int compareStrings = compareStrings(__offset(4, byteBuffer.capacity() - __indirect, byteBuffer), bytes, byteBuffer);
            if (compareStrings > 0) {
                i2 = i4;
            } else {
                if (compareStrings >= 0) {
                    if (keyValue == null) {
                        keyValue = new KeyValue();
                    }
                    return keyValue.__assign(__indirect, byteBuffer);
                }
                int i5 = i4 + 1;
                i3 += i5;
                i2 -= i5;
            }
        }
        return null;
    }

    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public KeyValue get(int i) {
            return get(new KeyValue(), i);
        }

        public KeyValue get(KeyValue keyValue, int i) {
            return keyValue.__assign(KeyValue.__indirect(__element(i), this.bb), this.bb);
        }

        public KeyValue getByKey(String str) {
            return KeyValue.__lookup_by_key(null, __vector(), str, this.bb);
        }

        public KeyValue getByKey(KeyValue keyValue, String str) {
            return KeyValue.__lookup_by_key(keyValue, __vector(), str, this.bb);
        }
    }
}
