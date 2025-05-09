package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import com.google.flatbuffers.reflection.Field;
import com.google.flatbuffers.reflection.KeyValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/* loaded from: classes8.dex */
public final class Object extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }

    public static Object getRootAsObject(ByteBuffer byteBuffer) {
        return getRootAsObject(byteBuffer, new Object());
    }

    public static Object getRootAsObject(ByteBuffer byteBuffer, Object object) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return object.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public Object __assign(int i, ByteBuffer byteBuffer) {
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

    public Field fields(int i) {
        return fields(new Field(), i);
    }

    public Field fields(Field field, int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return field.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int fieldsLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public Field fieldsByKey(String str) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return Field.__lookup_by_key(null, __vector(__offset), str, this.bb);
        }
        return null;
    }

    public Field fieldsByKey(Field field, String str) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return Field.__lookup_by_key(field, __vector(__offset), str, this.bb);
        }
        return null;
    }

    public Field.Vector fieldsVector() {
        return fieldsVector(new Field.Vector());
    }

    public Field.Vector fieldsVector(Field.Vector vector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public boolean isStruct() {
        int __offset = __offset(8);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public int minalign() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int bytesize() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
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

    public String documentation(int i) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int documentationLength() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public StringVector documentationVector() {
        return documentationVector(new StringVector());
    }

    public StringVector documentationVector(StringVector stringVector) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public String declarationFile() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer declarationFileAsByteBuffer() {
        return __vector_as_bytebuffer(18, 1);
    }

    public ByteBuffer declarationFileInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 1);
    }

    public static int createObject(FlatBufferBuilder flatBufferBuilder, int i, int i2, boolean z, int i3, int i4, int i5, int i6, int i7) {
        flatBufferBuilder.startTable(8);
        addDeclarationFile(flatBufferBuilder, i7);
        addDocumentation(flatBufferBuilder, i6);
        addAttributes(flatBufferBuilder, i5);
        addBytesize(flatBufferBuilder, i4);
        addMinalign(flatBufferBuilder, i3);
        addFields(flatBufferBuilder, i2);
        addName(flatBufferBuilder, i);
        addIsStruct(flatBufferBuilder, z);
        return endObject(flatBufferBuilder);
    }

    public static void startObject(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(8);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(i);
        flatBufferBuilder.slot(0);
    }

    public static void addFields(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createFieldsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startFieldsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addIsStruct(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(2, z, false);
    }

    public static void addMinalign(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(3, i, 0);
    }

    public static void addBytesize(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(4, i, 0);
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

    public static void addDocumentation(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
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

    public static void addDeclarationFile(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static int endObject(FlatBufferBuilder flatBufferBuilder) {
        int endTable = flatBufferBuilder.endTable();
        flatBufferBuilder.required(endTable, 4);
        flatBufferBuilder.required(endTable, 6);
        return endTable;
    }

    @Override // com.google.flatbuffers.Table
    public int keysCompare(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return compareStrings(__offset(4, num.intValue(), byteBuffer), __offset(4, num2.intValue(), byteBuffer), byteBuffer);
    }

    public static Object __lookup_by_key(Object object, int i, String str, ByteBuffer byteBuffer) {
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
                    if (object == null) {
                        object = new Object();
                    }
                    return object.__assign(__indirect, byteBuffer);
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

        public Object get(int i) {
            return get(new Object(), i);
        }

        public Object get(Object object, int i) {
            return object.__assign(Object.__indirect(__element(i), this.bb), this.bb);
        }

        public Object getByKey(String str) {
            return Object.__lookup_by_key(null, __vector(), str, this.bb);
        }

        public Object getByKey(Object object, String str) {
            return Object.__lookup_by_key(object, __vector(), str, this.bb);
        }
    }
}
