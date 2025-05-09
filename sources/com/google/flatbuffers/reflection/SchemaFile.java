package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/* loaded from: classes8.dex */
public final class SchemaFile extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }

    public static SchemaFile getRootAsSchemaFile(ByteBuffer byteBuffer) {
        return getRootAsSchemaFile(byteBuffer, new SchemaFile());
    }

    public static SchemaFile getRootAsSchemaFile(ByteBuffer byteBuffer, SchemaFile schemaFile) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return schemaFile.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public SchemaFile __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String filename() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer filenameAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer filenameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public String includedFilenames(int i) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int includedFilenamesLength() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public StringVector includedFilenamesVector() {
        return includedFilenamesVector(new StringVector());
    }

    public StringVector includedFilenamesVector(StringVector stringVector) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public static int createSchemaFile(FlatBufferBuilder flatBufferBuilder, int i, int i2) {
        flatBufferBuilder.startTable(2);
        addIncludedFilenames(flatBufferBuilder, i2);
        addFilename(flatBufferBuilder, i);
        return endSchemaFile(flatBufferBuilder);
    }

    public static void startSchemaFile(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(2);
    }

    public static void addFilename(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(i);
        flatBufferBuilder.slot(0);
    }

    public static void addIncludedFilenames(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static int createIncludedFilenamesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startIncludedFilenamesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endSchemaFile(FlatBufferBuilder flatBufferBuilder) {
        int endTable = flatBufferBuilder.endTable();
        flatBufferBuilder.required(endTable, 4);
        return endTable;
    }

    @Override // com.google.flatbuffers.Table
    public int keysCompare(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return compareStrings(__offset(4, num.intValue(), byteBuffer), __offset(4, num2.intValue(), byteBuffer), byteBuffer);
    }

    public static SchemaFile __lookup_by_key(SchemaFile schemaFile, int i, String str, ByteBuffer byteBuffer) {
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
                    if (schemaFile == null) {
                        schemaFile = new SchemaFile();
                    }
                    return schemaFile.__assign(__indirect, byteBuffer);
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

        public SchemaFile get(int i) {
            return get(new SchemaFile(), i);
        }

        public SchemaFile get(SchemaFile schemaFile, int i) {
            return schemaFile.__assign(SchemaFile.__indirect(__element(i), this.bb), this.bb);
        }

        public SchemaFile getByKey(String str) {
            return SchemaFile.__lookup_by_key(null, __vector(), str, this.bb);
        }

        public SchemaFile getByKey(SchemaFile schemaFile, String str) {
            return SchemaFile.__lookup_by_key(schemaFile, __vector(), str, this.bb);
        }
    }
}
