package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import com.google.flatbuffers.reflection.KeyValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes8.dex */
public final class EnumVal extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }

    public static EnumVal getRootAsEnumVal(ByteBuffer byteBuffer) {
        return getRootAsEnumVal(byteBuffer, new EnumVal());
    }

    public static EnumVal getRootAsEnumVal(ByteBuffer byteBuffer, EnumVal enumVal) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return enumVal.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public EnumVal __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String name() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer nameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public long value() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public Type unionType() {
        return unionType(new Type());
    }

    public Type unionType(Type type) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return type.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public String documentation(int i) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int documentationLength() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public StringVector documentationVector() {
        return documentationVector(new StringVector());
    }

    public StringVector documentationVector(StringVector stringVector) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public KeyValue attributes(int i) {
        return attributes(new KeyValue(), i);
    }

    public KeyValue attributes(KeyValue keyValue, int i) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return keyValue.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int attributesLength() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public KeyValue attributesByKey(String str) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return KeyValue.__lookup_by_key(null, __vector(__offset), str, this.bb);
        }
        return null;
    }

    public KeyValue attributesByKey(KeyValue keyValue, String str) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return KeyValue.__lookup_by_key(keyValue, __vector(__offset), str, this.bb);
        }
        return null;
    }

    public KeyValue.Vector attributesVector() {
        return attributesVector(new KeyValue.Vector());
    }

    public KeyValue.Vector attributesVector(KeyValue.Vector vector) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public static int createEnumVal(FlatBufferBuilder flatBufferBuilder, int i, long j, int i2, int i3, int i4) {
        flatBufferBuilder.startTable(6);
        addValue(flatBufferBuilder, j);
        addAttributes(flatBufferBuilder, i4);
        addDocumentation(flatBufferBuilder, i3);
        addUnionType(flatBufferBuilder, i2);
        addName(flatBufferBuilder, i);
        return endEnumVal(flatBufferBuilder);
    }

    public static void startEnumVal(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(6);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addValue(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(j);
        flatBufferBuilder.slot(1);
    }

    public static void addUnionType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addDocumentation(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static int createDocumentationVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startDocumentationVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addAttributes(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static int createAttributesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startAttributesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endEnumVal(FlatBufferBuilder flatBufferBuilder) {
        int endTable = flatBufferBuilder.endTable();
        flatBufferBuilder.required(endTable, 4);
        return endTable;
    }

    @Override // com.google.flatbuffers.Table
    public int keysCompare(Integer num, Integer num2, ByteBuffer byteBuffer) {
        long j = byteBuffer.getLong(__offset(6, num.intValue(), byteBuffer));
        long j2 = byteBuffer.getLong(__offset(6, num2.intValue(), byteBuffer));
        if (j > j2) {
            return 1;
        }
        return j < j2 ? -1 : 0;
    }

    public static EnumVal __lookup_by_key(EnumVal enumVal, int i, long j, ByteBuffer byteBuffer) {
        int i2 = byteBuffer.getInt(i - 4);
        int i3 = 0;
        while (i2 != 0) {
            int i4 = i2 / 2;
            int __indirect = __indirect(((i3 + i4) * 4) + i, byteBuffer);
            long j2 = byteBuffer.getLong(__offset(6, byteBuffer.capacity() - __indirect, byteBuffer));
            char c = j2 > j ? (char) 1 : j2 < j ? (char) 65535 : (char) 0;
            if (c > 0) {
                i2 = i4;
            } else {
                if (c >= 0) {
                    if (enumVal == null) {
                        enumVal = new EnumVal();
                    }
                    return enumVal.__assign(__indirect, byteBuffer);
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

        public EnumVal get(int i) {
            return get(new EnumVal(), i);
        }

        public EnumVal get(EnumVal enumVal, int i) {
            return enumVal.__assign(EnumVal.__indirect(__element(i), this.bb), this.bb);
        }

        public EnumVal getByKey(long j) {
            return EnumVal.__lookup_by_key(null, __vector(), j, this.bb);
        }

        public EnumVal getByKey(EnumVal enumVal, long j) {
            return EnumVal.__lookup_by_key(enumVal, __vector(), j, this.bb);
        }
    }
}
