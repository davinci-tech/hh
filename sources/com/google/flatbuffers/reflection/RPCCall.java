package com.google.flatbuffers.reflection;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import com.google.flatbuffers.reflection.KeyValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/* loaded from: classes8.dex */
public final class RPCCall extends Table {
    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }

    public static RPCCall getRootAsRPCCall(ByteBuffer byteBuffer) {
        return getRootAsRPCCall(byteBuffer, new RPCCall());
    }

    public static RPCCall getRootAsRPCCall(ByteBuffer byteBuffer, RPCCall rPCCall) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return rPCCall.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public RPCCall __assign(int i, ByteBuffer byteBuffer) {
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

    public Object request() {
        return request(new Object());
    }

    public Object request(Object object) {
        int __offset = __offset(6);
        if (__offset != 0) {
            return object.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public Object response() {
        return response(new Object());
    }

    public Object response(Object object) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return object.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public KeyValue attributes(int i) {
        return attributes(new KeyValue(), i);
    }

    public KeyValue attributes(KeyValue keyValue, int i) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return keyValue.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public int attributesLength() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public KeyValue attributesByKey(String str) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return KeyValue.__lookup_by_key(null, __vector(__offset), str, this.bb);
        }
        return null;
    }

    public KeyValue attributesByKey(KeyValue keyValue, String str) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return KeyValue.__lookup_by_key(keyValue, __vector(__offset), str, this.bb);
        }
        return null;
    }

    public KeyValue.Vector attributesVector() {
        return attributesVector(new KeyValue.Vector());
    }

    public KeyValue.Vector attributesVector(KeyValue.Vector vector) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
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

    public static int createRPCCall(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5) {
        flatBufferBuilder.startTable(5);
        addDocumentation(flatBufferBuilder, i5);
        addAttributes(flatBufferBuilder, i4);
        addResponse(flatBufferBuilder, i3);
        addRequest(flatBufferBuilder, i2);
        addName(flatBufferBuilder, i);
        return endRPCCall(flatBufferBuilder);
    }

    public static void startRPCCall(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(5);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(i);
        flatBufferBuilder.slot(0);
    }

    public static void addRequest(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addResponse(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addAttributes(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
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

    public static int endRPCCall(FlatBufferBuilder flatBufferBuilder) {
        int endTable = flatBufferBuilder.endTable();
        flatBufferBuilder.required(endTable, 4);
        flatBufferBuilder.required(endTable, 6);
        flatBufferBuilder.required(endTable, 8);
        return endTable;
    }

    @Override // com.google.flatbuffers.Table
    public int keysCompare(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return compareStrings(__offset(4, num.intValue(), byteBuffer), __offset(4, num2.intValue(), byteBuffer), byteBuffer);
    }

    public static RPCCall __lookup_by_key(RPCCall rPCCall, int i, String str, ByteBuffer byteBuffer) {
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
                    if (rPCCall == null) {
                        rPCCall = new RPCCall();
                    }
                    return rPCCall.__assign(__indirect, byteBuffer);
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

        public RPCCall get(int i) {
            return get(new RPCCall(), i);
        }

        public RPCCall get(RPCCall rPCCall, int i) {
            return rPCCall.__assign(RPCCall.__indirect(__element(i), this.bb), this.bb);
        }

        public RPCCall getByKey(String str) {
            return RPCCall.__lookup_by_key(null, __vector(), str, this.bb);
        }

        public RPCCall getByKey(RPCCall rPCCall, String str) {
            return RPCCall.__lookup_by_key(rPCCall, __vector(), str, this.bb);
        }
    }
}
