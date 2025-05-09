package com.google.protobuf;

import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

@CheckReturnValue
/* loaded from: classes2.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int CHECK_INITIALIZED_BIT = 1024;
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int HAS_HAS_BIT = 4096;
    private static final int INTS_PER_FIELD = 3;
    private static final int LEGACY_ENUM_IS_CLOSED_BIT = 2048;
    private static final int LEGACY_ENUM_IS_CLOSED_MASK = Integer.MIN_VALUE;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_BIT = 256;
    private static final int REQUIRED_MASK = 268435456;
    private static final int UTF8_CHECK_BIT = 512;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final int repeatedFieldOffsetStart;
    private final ProtoSyntax syntax;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private static boolean isEnforceUtf8(int i) {
        return (i & 536870912) != 0;
    }

    private static boolean isLegacyEnumIsClosed(int i) {
        return (i & Integer.MIN_VALUE) != 0;
    }

    private static boolean isRequired(int i) {
        return (i & 268435456) != 0;
    }

    private static long offset(int i) {
        return i & 1048575;
    }

    private static int type(int i) {
        return (i & FIELD_TYPE_MASK) >>> 20;
    }

    private MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, ProtoSyntax protoSyntax, boolean z, int[] iArr2, int i3, int i4, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.syntax = protoSyntax;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(messageLite);
        this.useCachedSizeField = z;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema;
    }

    static <T> MessageSchema<T> newSchema(Class<T> cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x024e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> com.google.protobuf.MessageSchema<T> newSchemaForRawMessageInfo(com.google.protobuf.RawMessageInfo r34, com.google.protobuf.NewInstanceSchema r35, com.google.protobuf.ListFieldSchema r36, com.google.protobuf.UnknownFieldSchema<?, ?> r37, com.google.protobuf.ExtensionSchema<?> r38, com.google.protobuf.MapFieldSchema r39) {
        /*
            Method dump skipped, instructions count: 989
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.newSchemaForRawMessageInfo(com.google.protobuf.RawMessageInfo, com.google.protobuf.NewInstanceSchema, com.google.protobuf.ListFieldSchema, com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, com.google.protobuf.MapFieldSchema):com.google.protobuf.MessageSchema");
    }

    private static java.lang.reflect.Field reflectField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            java.lang.reflect.Field[] declaredFields = cls.getDeclaredFields();
            for (java.lang.reflect.Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        int i;
        FieldInfo[] fields = structuralMessageInfo.getFields();
        if (fields.length == 0) {
            fieldNumber = 0;
            fieldNumber2 = 0;
        } else {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i2 = 0;
        int i3 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i2++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i3++;
            }
        }
        int[] iArr2 = i2 > 0 ? new int[i2] : null;
        int[] iArr3 = i3 > 0 ? new int[i3] : null;
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < fields.length) {
            FieldInfo fieldInfo2 = fields[i4];
            int fieldNumber3 = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i5, objArr);
            if (i6 < checkInitialized.length && checkInitialized[i6] == fieldNumber3) {
                checkInitialized[i6] = i5;
                i6++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr2[i7] = i5;
                i7++;
            } else if (fieldInfo2.getType().id() >= 18 && fieldInfo2.getType().id() <= 49) {
                i = i5;
                iArr3[i8] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                i8++;
                i4++;
                i5 = i + 3;
            }
            i = i5;
            i4++;
            i5 = i + 3;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[checkInitialized.length + iArr2.length + iArr3.length];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length + iArr2.length, iArr3.length);
        return new MessageSchema<>(iArr, objArr, fieldNumber, fieldNumber2, structuralMessageInfo.getDefaultInstance(), structuralMessageInfo.getSyntax(), true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void storeFieldData(com.google.protobuf.FieldInfo r7, int[] r8, int r9, java.lang.Object[] r10) {
        /*
            com.google.protobuf.OneofInfo r0 = r7.getOneof()
            r1 = 0
            if (r0 == 0) goto L23
            com.google.protobuf.FieldType r2 = r7.getType()
            int r2 = r2.id()
            int r2 = r2 + 51
            java.lang.reflect.Field r3 = r0.getValueField()
            long r3 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r3)
            int r3 = (int) r3
            java.lang.reflect.Field r0 = r0.getCaseField()
            long r4 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r0)
            goto L69
        L23:
            com.google.protobuf.FieldType r0 = r7.getType()
            java.lang.reflect.Field r2 = r7.getField()
            long r2 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r2)
            int r3 = (int) r2
            int r2 = r0.id()
            boolean r4 = r0.isList()
            if (r4 != 0) goto L58
            boolean r0 = r0.isMap()
            if (r0 != 0) goto L58
            java.lang.reflect.Field r0 = r7.getPresenceField()
            if (r0 != 0) goto L4a
            r0 = 1048575(0xfffff, float:1.469367E-39)
            goto L4f
        L4a:
            long r4 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r0)
            int r0 = (int) r4
        L4f:
            int r4 = r7.getPresenceMask()
            int r4 = java.lang.Integer.numberOfTrailingZeros(r4)
            goto L6b
        L58:
            java.lang.reflect.Field r0 = r7.getCachedSizeField()
            if (r0 != 0) goto L61
            r0 = r1
            r4 = r0
            goto L6b
        L61:
            java.lang.reflect.Field r0 = r7.getCachedSizeField()
            long r4 = com.google.protobuf.UnsafeUtil.objectFieldOffset(r0)
        L69:
            int r0 = (int) r4
            r4 = r1
        L6b:
            int r5 = r7.getFieldNumber()
            r8[r9] = r5
            boolean r5 = r7.isEnforceUtf8()
            if (r5 == 0) goto L7a
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            goto L7b
        L7a:
            r5 = r1
        L7b:
            boolean r6 = r7.isRequired()
            if (r6 == 0) goto L83
            r1 = 268435456(0x10000000, float:2.524355E-29)
        L83:
            int r6 = r9 + 1
            r1 = r1 | r5
            int r2 = r2 << 20
            r1 = r1 | r2
            r1 = r1 | r3
            r8[r6] = r1
            int r1 = r9 + 2
            int r2 = r4 << 20
            r0 = r0 | r2
            r8[r1] = r0
            java.lang.Class r8 = r7.getMessageFieldClass()
            java.lang.Object r0 = r7.getMapDefaultEntry()
            if (r0 == 0) goto Lbd
            int r9 = r9 / 3
            int r9 = r9 * 2
            java.lang.Object r0 = r7.getMapDefaultEntry()
            r10[r9] = r0
            if (r8 == 0) goto Lae
            int r9 = r9 + 1
            r10[r9] = r8
            goto Lda
        Lae:
            com.google.protobuf.Internal$EnumVerifier r8 = r7.getEnumVerifier()
            if (r8 == 0) goto Lda
            int r9 = r9 + 1
            com.google.protobuf.Internal$EnumVerifier r7 = r7.getEnumVerifier()
            r10[r9] = r7
            goto Lda
        Lbd:
            if (r8 == 0) goto Lc8
            int r9 = r9 / 3
            int r9 = r9 * 2
            int r9 = r9 + 1
            r10[r9] = r8
            goto Lda
        Lc8:
            com.google.protobuf.Internal$EnumVerifier r8 = r7.getEnumVerifier()
            if (r8 == 0) goto Lda
            int r9 = r9 / 3
            int r9 = r9 * 2
            int r9 = r9 + 1
            com.google.protobuf.Internal$EnumVerifier r7 = r7.getEnumVerifier()
            r10[r9] = r7
        Lda:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.storeFieldData(com.google.protobuf.FieldInfo, int[], int, java.lang.Object[]):void");
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T t, T t2) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(t, t2, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(t).equals(this.unknownFieldSchema.getFromMessage(t2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t).equals(this.extensionSchema.getExtensions(t2));
        }
        return true;
    }

    private boolean equals(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return arePresentForEquals(t, t2, i) && Double.doubleToLongBits(UnsafeUtil.getDouble(t, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(t2, offset));
            case 1:
                return arePresentForEquals(t, t2, i) && Float.floatToIntBits(UnsafeUtil.getFloat(t, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(t2, offset));
            case 2:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 3:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 4:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 5:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 6:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 7:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getBoolean(t, offset) == UnsafeUtil.getBoolean(t2, offset);
            case 8:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 9:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 10:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 11:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 12:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 13:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 14:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 15:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, offset) == UnsafeUtil.getInt(t2, offset);
            case 16:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, offset) == UnsafeUtil.getLong(t2, offset);
            case 17:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return isOneofCaseEqual(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, offset), UnsafeUtil.getObject(t2, offset));
            default:
                return true;
        }
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(T t) {
        int i;
        int hashLong;
        int length = this.buffer.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i3);
            int numberAt = numberAt(i3);
            long offset = offset(typeAndOffsetAt);
            switch (type(typeAndOffsetAt)) {
                case 0:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(t, offset)));
                    i2 = i + hashLong;
                    break;
                case 1:
                    i = i2 * 53;
                    hashLong = Float.floatToIntBits(UnsafeUtil.getFloat(t, offset));
                    i2 = i + hashLong;
                    break;
                case 2:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 3:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 4:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 5:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 6:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 7:
                    i = i2 * 53;
                    hashLong = Internal.hashBoolean(UnsafeUtil.getBoolean(t, offset));
                    i2 = i + hashLong;
                    break;
                case 8:
                    i = i2 * 53;
                    hashLong = ((String) UnsafeUtil.getObject(t, offset)).hashCode();
                    i2 = i + hashLong;
                    break;
                case 9:
                    Object object = UnsafeUtil.getObject(t, offset);
                    if (object != null) {
                        hashLong = object.hashCode();
                        i = i2 * 53;
                        i2 = i + hashLong;
                        break;
                    }
                    hashLong = 37;
                    i = i2 * 53;
                    i2 = i + hashLong;
                case 10:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 11:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 12:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 13:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 14:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 15:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getInt(t, offset);
                    i2 = i + hashLong;
                    break;
                case 16:
                    i = i2 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(t, offset));
                    i2 = i + hashLong;
                    break;
                case 17:
                    Object object2 = UnsafeUtil.getObject(t, offset);
                    if (object2 != null) {
                        hashLong = object2.hashCode();
                        i = i2 * 53;
                        i2 = i + hashLong;
                        break;
                    }
                    hashLong = 37;
                    i = i2 * 53;
                    i2 = i + hashLong;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 50:
                    i = i2 * 53;
                    hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                    i2 = i + hashLong;
                    break;
                case 51:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(t, offset)));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Float.floatToIntBits(oneofFloatAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashBoolean(oneofBooleanAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = ((String) UnsafeUtil.getObject(t, offset)).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = oneofIntAt(t, offset);
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(t, offset));
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(t, numberAt, i3)) {
                        i = i2 * 53;
                        hashLong = UnsafeUtil.getObject(t, offset).hashCode();
                        i2 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.unknownFieldSchema.getFromMessage(t).hashCode();
        return this.hasExtensions ? (hashCode * 53) + this.extensionSchema.getExtensions(t).hashCode() : hashCode;
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, T t2) {
        checkMutable(t);
        t2.getClass();
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(t, t2, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t, t2);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t, t2);
        }
    }

    private void mergeSingleField(T t, T t2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(i);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putDouble(t, offset, UnsafeUtil.getDouble(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 1:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putFloat(t, offset, UnsafeUtil.getFloat(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 2:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 3:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 4:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 5:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 6:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 7:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putBoolean(t, offset, UnsafeUtil.getBoolean(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 8:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putObject(t, offset, UnsafeUtil.getObject(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 9:
                mergeMessage(t, t2, i);
                break;
            case 10:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putObject(t, offset, UnsafeUtil.getObject(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 11:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 12:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 13:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 14:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 15:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putInt(t, offset, UnsafeUtil.getInt(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 16:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, offset, UnsafeUtil.getLong(t2, offset));
                    setFieldPresent(t, i);
                    break;
                }
                break;
            case 17:
                mergeMessage(t, t2, i);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(t, t2, offset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, t, t2, offset);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(t2, numberAt, i)) {
                    UnsafeUtil.putObject(t, offset, UnsafeUtil.getObject(t2, offset));
                    setOneofPresent(t, numberAt, i);
                    break;
                }
                break;
            case 60:
                mergeOneofMessage(t, t2, i);
                break;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(t2, numberAt, i)) {
                    UnsafeUtil.putObject(t, offset, UnsafeUtil.getObject(t2, offset));
                    setOneofPresent(t, numberAt, i);
                    break;
                }
                break;
            case 68:
                mergeOneofMessage(t, t2, i);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T t, T t2, int i) {
        if (isFieldPresent(t2, i)) {
            long offset = offset(typeAndOffsetAt(i));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(t2, offset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + t2);
            }
            Schema messageFieldSchema = getMessageFieldSchema(i);
            if (!isFieldPresent(t, i)) {
                if (!isMutable(object)) {
                    unsafe.putObject(t, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(t, offset, newInstance);
                }
                setFieldPresent(t, i);
                return;
            }
            Object object2 = unsafe.getObject(t, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(t, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T t, T t2, int i) {
        int numberAt = numberAt(i);
        if (isOneofPresent(t2, numberAt, i)) {
            long offset = offset(typeAndOffsetAt(i));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(t2, offset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + t2);
            }
            Schema messageFieldSchema = getMessageFieldSchema(i);
            if (!isOneofPresent(t, numberAt, i)) {
                if (!isMutable(object)) {
                    unsafe.putObject(t, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(t, offset, newInstance);
                }
                setOneofPresent(t, numberAt, i);
                return;
            }
            Object object2 = unsafe.getObject(t, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(t, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v16 */
    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T t) {
        int i;
        int i2;
        int i3;
        boolean z;
        int computeDoubleSize;
        int computeBoolSize;
        int computeSizeFixed64List;
        int computeSizeFixed64ListNoTag;
        int computeTagSize;
        int computeUInt32SizeNoTag;
        Unsafe unsafe = UNSAFE;
        ?? r9 = 0;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 1048575;
        while (i6 < this.buffer.length) {
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            int type = type(typeAndOffsetAt);
            int numberAt = numberAt(i6);
            int i9 = this.buffer[i6 + 2];
            int i10 = i9 & i4;
            if (type <= 17) {
                if (i10 != i8) {
                    i5 = i10 == i4 ? r9 : unsafe.getInt(t, i10);
                    i8 = i10;
                }
                i = i5;
                i2 = i8;
                i3 = 1 << (i9 >>> 20);
            } else {
                i = i5;
                i2 = i8;
                i3 = r9;
            }
            long offset = offset(typeAndOffsetAt);
            if (type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) {
                i10 = r9;
            }
            int i11 = i10;
            switch (type) {
                case 0:
                    z = r9;
                    if (!isFieldPresent(t, i6, i2, i, i3)) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i7 += computeDoubleSize;
                        break;
                    }
                case 1:
                    z = r9;
                    if (!isFieldPresent(t, i6, i2, i, i3)) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i7 += computeDoubleSize;
                        break;
                    }
                case 2:
                    z = r9;
                    if (!isFieldPresent(t, i6, i2, i, i3)) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt64Size(numberAt, unsafe.getLong(t, offset));
                        i7 += computeDoubleSize;
                        break;
                    }
                case 3:
                    z = r9;
                    if (!isFieldPresent(t, i6, i2, i, i3)) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeUInt64Size(numberAt, unsafe.getLong(t, offset));
                        i7 += computeDoubleSize;
                        break;
                    }
                case 4:
                    z = r9;
                    if (!isFieldPresent(t, i6, i2, i, i3)) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeInt32Size(numberAt, unsafe.getInt(t, offset));
                        i7 += computeDoubleSize;
                        break;
                    }
                case 5:
                    z = r9;
                    if (!isFieldPresent(t, i6, i2, i, i3)) {
                        break;
                    } else {
                        computeDoubleSize = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i7 += computeDoubleSize;
                        break;
                    }
                case 6:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        z = false;
                        computeDoubleSize = CodedOutputStream.computeFixed32Size(numberAt, 0);
                        i7 += computeDoubleSize;
                        break;
                    }
                    z = false;
                    break;
                case 7:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = CodedOutputStream.computeBoolSize(numberAt, true);
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 8:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        Object object = unsafe.getObject(t, offset);
                        if (object instanceof ByteString) {
                            computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object);
                        } else {
                            computeBoolSize = CodedOutputStream.computeStringSize(numberAt, (String) object);
                        }
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 9:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(t, offset), getMessageFieldSchema(i6));
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 10:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(t, offset));
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 11:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = CodedOutputStream.computeUInt32Size(numberAt, unsafe.getInt(t, offset));
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 12:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = CodedOutputStream.computeEnumSize(numberAt, unsafe.getInt(t, offset));
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 13:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        i7 += CodedOutputStream.computeSFixed32Size(numberAt, 0);
                    }
                    z = false;
                    break;
                case 14:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 15:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = CodedOutputStream.computeSInt32Size(numberAt, unsafe.getInt(t, offset));
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 16:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = CodedOutputStream.computeSInt64Size(numberAt, unsafe.getLong(t, offset));
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 17:
                    if (isFieldPresent(t, i6, i2, i, i3)) {
                        computeBoolSize = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(t, offset), getMessageFieldSchema(i6));
                        i7 += computeBoolSize;
                    }
                    z = false;
                    break;
                case 18:
                    computeSizeFixed64List = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 19:
                    computeSizeFixed64List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 20:
                    computeSizeFixed64List = SchemaUtil.computeSizeInt64List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 21:
                    computeSizeFixed64List = SchemaUtil.computeSizeUInt64List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 22:
                    computeSizeFixed64List = SchemaUtil.computeSizeInt32List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 23:
                    computeSizeFixed64List = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 24:
                    computeSizeFixed64List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 25:
                    computeSizeFixed64List = SchemaUtil.computeSizeBoolList(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 26:
                    computeSizeFixed64List = SchemaUtil.computeSizeStringList(numberAt, (List) unsafe.getObject(t, offset));
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 27:
                    computeSizeFixed64List = SchemaUtil.computeSizeMessageList(numberAt, (List) unsafe.getObject(t, offset), getMessageFieldSchema(i6));
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 28:
                    computeSizeFixed64List = SchemaUtil.computeSizeByteStringList(numberAt, (List) unsafe.getObject(t, offset));
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 29:
                    computeSizeFixed64List = SchemaUtil.computeSizeUInt32List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 30:
                    computeSizeFixed64List = SchemaUtil.computeSizeEnumList(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 31:
                    computeSizeFixed64List = SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 32:
                    computeSizeFixed64List = SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 33:
                    computeSizeFixed64List = SchemaUtil.computeSizeSInt32List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 34:
                    computeSizeFixed64List = SchemaUtil.computeSizeSInt64List(numberAt, (List) unsafe.getObject(t, offset), r9);
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 35:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 36:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 37:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 38:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 39:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 40:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 41:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 42:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 43:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 44:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 45:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 46:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 47:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 48:
                    computeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i11, computeSizeFixed64ListNoTag);
                        }
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag);
                        computeSizeFixed64List = computeTagSize + computeUInt32SizeNoTag + computeSizeFixed64ListNoTag;
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 49:
                    computeSizeFixed64List = SchemaUtil.computeSizeGroupList(numberAt, (List) unsafe.getObject(t, offset), getMessageFieldSchema(i6));
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 50:
                    computeSizeFixed64List = this.mapFieldSchema.getSerializedSize(numberAt, unsafe.getObject(t, offset), getMapFieldDefaultEntry(i6));
                    i7 += computeSizeFixed64List;
                    z = r9;
                    break;
                case 51:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 52:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 53:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(t, offset));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 54:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(t, offset));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 55:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(t, offset));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 56:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 57:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeFixed32Size(numberAt, r9);
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 58:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeBoolSize(numberAt, true);
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 59:
                    if (isOneofPresent(t, numberAt, i6)) {
                        Object object2 = unsafe.getObject(t, offset);
                        if (object2 instanceof ByteString) {
                            computeSizeFixed64List = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2);
                        } else {
                            computeSizeFixed64List = CodedOutputStream.computeStringSize(numberAt, (String) object2);
                        }
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 60:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(t, offset), getMessageFieldSchema(i6));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 61:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(t, offset));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 62:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(t, offset));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 63:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(t, offset));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 64:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeSFixed32Size(numberAt, r9);
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 65:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 66:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(t, offset));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 67:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(t, offset));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                case 68:
                    if (isOneofPresent(t, numberAt, i6)) {
                        computeSizeFixed64List = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(t, offset), getMessageFieldSchema(i6));
                        i7 += computeSizeFixed64List;
                    }
                    z = r9;
                    break;
                default:
                    z = r9;
                    break;
            }
            i6 += 3;
            i5 = i;
            r9 = z;
            i8 = i2;
            i4 = 1048575;
        }
        int unknownFieldsSerializedSize = i7 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t);
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(t).getSerializedSize() : unknownFieldsSerializedSize;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t) {
        return unknownFieldSchema.getSerializedSize(unknownFieldSchema.getFromMessage(t));
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T t, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(t, writer);
        } else {
            writeFieldsInAscendingOrder(t, writer);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0621  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void writeFieldsInAscendingOrder(T r22, com.google.protobuf.Writer r23) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1738
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.writeFieldsInAscendingOrder(java.lang.Object, com.google.protobuf.Writer):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:275:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void writeFieldsInDescendingOrder(T r11, com.google.protobuf.Writer r12) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.writeFieldsInDescendingOrder(java.lang.Object, com.google.protobuf.Writer):void");
    }

    private <K, V> void writeMapHelper(Writer writer, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            writer.writeMap(i, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t, Writer writer) throws IOException {
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(t), writer);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        extensionRegistryLite.getClass();
        checkMutable(t);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t, reader, extensionRegistryLite);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x064c A[Catch: all -> 0x063e, TRY_LEAVE, TryCatch #10 {all -> 0x063e, blocks: (B:17:0x0618, B:35:0x0646, B:37:0x064c, B:50:0x0674, B:51:0x0679), top: B:16:0x0618 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0672  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x06b6 A[LOOP:4: B:65:0x06b2->B:67:0x06b6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x06cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private <UT, UB, ET extends com.google.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(com.google.protobuf.UnknownFieldSchema<UT, UB> r19, com.google.protobuf.ExtensionSchema<ET> r20, T r21, com.google.protobuf.Reader r22, com.google.protobuf.ExtensionRegistryLite r23) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1886
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFromHelper(com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, java.lang.Object, com.google.protobuf.Reader, com.google.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = newInstance;
        return newInstance;
    }

    /* renamed from: com.google.protobuf.MessageSchema$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private int decodeMapEntryValue(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class<?> cls, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return decodeVarint64;
            case 2:
                return ArrayDecoders.decodeBytes(bArr, i, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(bArr, i));
                return i + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i));
                return i + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i));
                return i + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return decodeVarint32;
            case 12:
            case 13:
                int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return decodeVarint642;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) cls), bArr, i, i2, registers);
            case 15:
                int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return decodeVarint322;
            case 16:
                int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return decodeVarint643;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(bArr, i, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0, types: [java.util.Map, java.util.Map<K, V>] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    private <K, V> int decodeMapEntry(byte[] bArr, int i, int i2, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) throws IOException {
        int i3;
        int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
        int i4 = registers.int1;
        if (i4 < 0 || i4 > i2 - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i5 = decodeVarint32 + i4;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (decodeVarint32 < i5) {
            int i6 = decodeVarint32 + 1;
            byte b = bArr[decodeVarint32];
            if (b < 0) {
                i3 = ArrayDecoders.decodeVarint32(b, bArr, i6, registers);
                b = registers.int1;
            } else {
                i3 = i6;
            }
            int i7 = b >>> 3;
            int i8 = b & 7;
            if (i7 == 1) {
                if (i8 == metadata.keyType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(bArr, i3, i2, metadata.keyType, null, registers);
                    obj = registers.object1;
                } else {
                    decodeVarint32 = ArrayDecoders.skipField(b, bArr, i3, i2, registers);
                }
            } else {
                if (i7 == 2 && i8 == metadata.valueType.getWireType()) {
                    decodeVarint32 = decodeMapEntryValue(bArr, i3, i2, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                }
                decodeVarint32 = ArrayDecoders.skipField(b, bArr, i3, i2, registers);
            }
        }
        if (decodeVarint32 != i5) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        map.put(obj, obj2);
        return i5;
    }

    private int parseRepeatedField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, ArrayDecoders.Registers registers) throws IOException {
        int decodeVarint32List;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe.getObject(t, j2);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            unsafe.putObject(t, j2, protobufList);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, i, protobufList, registers);
                }
                if (i5 == 1) {
                    return ArrayDecoders.decodeDoubleList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, i, protobufList, registers);
                }
                if (i5 == 5) {
                    return ArrayDecoders.decodeFloatList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeVarint64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, i, protobufList, registers);
                }
                if (i5 == 1) {
                    return ArrayDecoders.decodeFixed64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, i, protobufList, registers);
                }
                if (i5 == 5) {
                    return ArrayDecoders.decodeFixed32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeBoolList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(i3, bArr, i, i2, protobufList, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 27:
                if (i5 == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(i6), i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 28:
                if (i5 == 2) {
                    return ArrayDecoders.decodeBytesList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    decodeVarint32List = ArrayDecoders.decodePackedVarint32List(bArr, i, protobufList, registers);
                } else if (i5 == 0) {
                    decodeVarint32List = ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                SchemaUtil.filterUnknownEnumList((Object) t, i4, (List<Integer>) protobufList, getEnumFieldVerifier(i6), (Object) null, (UnknownFieldSchema<UT, Object>) this.unknownFieldSchema);
                return decodeVarint32List;
            case 33:
            case 47:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedSInt32List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeSInt32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 34:
            case 48:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedSInt64List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeSInt64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 49:
                if (i5 == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(i6), i3, bArr, i, i2, protobufList, registers);
                }
                break;
        }
        return i;
    }

    private <K, V> int parseMapField(T t, byte[] bArr, int i, int i2, int i3, long j, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i3);
        Object object = unsafe.getObject(t, j);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            unsafe.putObject(t, j, newMapField);
            object = newMapField;
        }
        return decodeMapEntry(bArr, i, i2, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        long j2 = this.buffer[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(ArrayDecoders.decodeDouble(bArr, i)));
                    int i9 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(ArrayDecoders.decodeFloat(bArr, i)));
                    int i10 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i10;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(t, j, Long.valueOf(registers.long1));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint64;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    unsafe.putObject(t, j, Integer.valueOf(registers.int1));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint32;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i)));
                    int i11 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i11;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i)));
                    int i12 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i12;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(t, j, Boolean.valueOf(registers.long1 != 0));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint642;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    int i13 = registers.int1;
                    if (i13 == 0) {
                        unsafe.putObject(t, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !Utf8.isValidUtf8(bArr, decodeVarint322, decodeVarint322 + i13)) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        unsafe.putObject(t, j, new String(bArr, decodeVarint322, i13, Internal.UTF_8));
                        decodeVarint322 += i13;
                    }
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint322;
                }
                break;
            case 60:
                if (i5 == 2) {
                    Object mutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(t, i4, i8);
                    int mergeMessageField = ArrayDecoders.mergeMessageField(mutableOneofMessageFieldForMerge, getMessageFieldSchema(i8), bArr, i, i2, registers);
                    storeOneofMessageField(t, i4, i8, mutableOneofMessageFieldForMerge);
                    return mergeMessageField;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int decodeBytes = ArrayDecoders.decodeBytes(bArr, i, registers);
                    unsafe.putObject(t, j, registers.object1);
                    unsafe.putInt(t, j2, i4);
                    return decodeBytes;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int decodeVarint323 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    int i14 = registers.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i8);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i14)) {
                        unsafe.putObject(t, j, Integer.valueOf(i14));
                        unsafe.putInt(t, j2, i4);
                    } else {
                        getMutableUnknownFields(t).storeField(i3, Long.valueOf(i14));
                    }
                    return decodeVarint323;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int decodeVarint324 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    unsafe.putObject(t, j, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint324;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(t, j, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                    unsafe.putInt(t, j2, i4);
                    return decodeVarint643;
                }
                break;
            case 68:
                if (i5 == 3) {
                    Object mutableOneofMessageFieldForMerge2 = mutableOneofMessageFieldForMerge(t, i4, i8);
                    int mergeGroupField = ArrayDecoders.mergeGroupField(mutableOneofMessageFieldForMerge2, getMessageFieldSchema(i8), bArr, i, i2, (i3 & (-8)) | 4, registers);
                    storeOneofMessageField(t, i4, i8, mutableOneofMessageFieldForMerge2);
                    return mergeGroupField;
                }
                break;
        }
        return i;
    }

    private Schema getMessageFieldSchema(int i) {
        int i2 = (i / 3) * 2;
        Schema schema = (Schema) this.objects[i2];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i2 + 1]);
        this.objects[i2] = schemaFor;
        return schemaFor;
    }

    private Object getMapFieldDefaultEntry(int i) {
        return this.objects[(i / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i) {
        return (Internal.EnumVerifier) this.objects[((i / 3) * 2) + 1];
    }

    /* JADX WARN: Code restructure failed: missing block: B:197:0x0359, code lost:
    
        if (r0 != r19) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x035c, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x03a7, code lost:
    
        r15 = r29;
        r14 = r30;
        r12 = r31;
        r13 = r33;
        r11 = r34;
        r9 = r35;
        r1 = r18;
        r5 = r19;
        r6 = r20;
        r3 = r21;
        r2 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0388, code lost:
    
        if (r0 != r15) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x03a5, code lost:
    
        if (r0 != r15) goto L125;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0098. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    int parseMessage(T r30, byte[] r31, int r32, int r33, int r34, com.google.protobuf.ArrayDecoders.Registers r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1178
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.parseMessage(java.lang.Object, byte[], int, int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T t, int i) {
        Schema messageFieldSchema = getMessageFieldSchema(i);
        long offset = offset(typeAndOffsetAt(i));
        if (!isFieldPresent(t, i)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(t, offset);
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeMessageField(T t, int i, Object obj) {
        UNSAFE.putObject(t, offset(typeAndOffsetAt(i)), obj);
        setFieldPresent(t, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T t, int i, int i2) {
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        if (!isOneofPresent(t, i, i2)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(t, offset(typeAndOffsetAt(i2)));
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeOneofMessageField(T t, int i, int i2, Object obj) {
        UNSAFE.putObject(t, offset(typeAndOffsetAt(i2)), obj);
        setOneofPresent(t, i, i2);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws IOException {
        parseMessage(t, bArr, i, i2, 0, registers);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.Schema
    public void makeImmutable(T t) {
        if (isMutable(t)) {
            if (t instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int length = this.buffer.length;
            for (int i = 0; i < length; i += 3) {
                int typeAndOffsetAt = typeAndOffsetAt(i);
                long offset = offset(typeAndOffsetAt);
                int type = type(typeAndOffsetAt);
                if (type != 9) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(t, numberAt(i), i)) {
                            getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(t, offset));
                        }
                    } else {
                        switch (type) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.listFieldSchema.makeImmutableListAt(t, offset);
                                break;
                            case 50:
                                Unsafe unsafe = UNSAFE;
                                Object object = unsafe.getObject(t, offset);
                                if (object != null) {
                                    unsafe.putObject(t, offset, this.mapFieldSchema.toImmutable(object));
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
                if (isFieldPresent(t, i)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(t, offset));
                }
            }
            this.unknownFieldSchema.makeImmutable(t);
            if (this.hasExtensions) {
                this.extensionSchema.makeImmutable(t);
            }
        }
    }

    private final <K, V> void mergeMap(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(i));
        Object object = UnsafeUtil.getObject(obj, offset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.putObject(obj, offset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            UnsafeUtil.putObject(obj, offset, newMapField);
            object = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj2) {
        Internal.EnumVerifier enumFieldVerifier;
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        return (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) ? ub : (UB) filterUnknownEnumMap(i, numberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema, obj2);
    }

    private <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.getBuilderFromMessage(obj);
                }
                ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.getCodedOutput(), forMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, newCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.checkInitializedCount) {
            int i6 = this.intArray[i5];
            int numberAt = numberAt(i6);
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            int i7 = this.buffer[i6 + 2];
            int i8 = i7 & 1048575;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i3) {
                if (i8 != 1048575) {
                    i4 = UNSAFE.getInt(t, i8);
                }
                i2 = i4;
                i = i8;
            } else {
                i = i3;
                i2 = i4;
            }
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(t, i6, i, i2, i9)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type == 9 || type == 17) {
                if (isFieldPresent(t, i6, i, i2, i9) && !isInitialized(t, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                    return false;
                }
            } else {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(t, numberAt, i6) && !isInitialized(t, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type == 50 && !isMapInitialized(t, typeAndOffsetAt, i6)) {
                            return false;
                        }
                    }
                }
                if (!isListInitialized(t, typeAndOffsetAt, i6)) {
                    return false;
                }
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(t).isInitialized();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object obj, int i, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object obj, int i, int i2) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!messageFieldSchema.isInitialized(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.google.protobuf.Schema] */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? r5 = 0;
        for (Object obj : forMapData.values()) {
            r5 = r5;
            if (r5 == 0) {
                r5 = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            if (!r5.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int i, Object obj, Writer writer) throws IOException {
        if (obj instanceof String) {
            writer.writeString(i, (String) obj);
        } else {
            writer.writeBytes(i, (ByteString) obj);
        }
    }

    private void readString(Object obj, int i, Reader reader) throws IOException {
        if (isEnforceUtf8(i)) {
            UnsafeUtil.putObject(obj, offset(i), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(obj, offset(i), reader.readString());
        } else {
            UnsafeUtil.putObject(obj, offset(i), reader.readBytes());
        }
    }

    private void readStringList(Object obj, int i, Reader reader) throws IOException {
        if (isEnforceUtf8(i)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i)));
        }
    }

    private <E> void readMessageList(Object obj, int i, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i)), schema, extensionRegistryLite);
    }

    private <E> void readGroupList(Object obj, long j, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j), schema, extensionRegistryLite);
    }

    private int numberAt(int i) {
        return this.buffer[i];
    }

    private int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    private int presenceMaskAndOffsetAt(int i) {
        return this.buffer[i + 2];
    }

    private static boolean isMutable(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) obj).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object obj) {
        if (isMutable(obj)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + obj);
    }

    private static <T> double doubleAt(T t, long j) {
        return UnsafeUtil.getDouble(t, j);
    }

    private static <T> float floatAt(T t, long j) {
        return UnsafeUtil.getFloat(t, j);
    }

    private static <T> int intAt(T t, long j) {
        return UnsafeUtil.getInt(t, j);
    }

    private static <T> long longAt(T t, long j) {
        return UnsafeUtil.getLong(t, j);
    }

    private static <T> boolean booleanAt(T t, long j) {
        return UnsafeUtil.getBoolean(t, j);
    }

    private static <T> double oneofDoubleAt(T t, long j) {
        return ((Double) UnsafeUtil.getObject(t, j)).doubleValue();
    }

    private static <T> float oneofFloatAt(T t, long j) {
        return ((Float) UnsafeUtil.getObject(t, j)).floatValue();
    }

    private static <T> int oneofIntAt(T t, long j) {
        return ((Integer) UnsafeUtil.getObject(t, j)).intValue();
    }

    private static <T> long oneofLongAt(T t, long j) {
        return ((Long) UnsafeUtil.getObject(t, j)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T t, long j) {
        return ((Boolean) UnsafeUtil.getObject(t, j)).booleanValue();
    }

    private boolean arePresentForEquals(T t, T t2, int i) {
        return isFieldPresent(t, i) == isFieldPresent(t2, i);
    }

    private boolean isFieldPresent(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return isFieldPresent(t, i);
        }
        return (i3 & i4) != 0;
    }

    private boolean isFieldPresent(T t, int i) {
        boolean equals;
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j != 1048575) {
            return (UnsafeUtil.getInt(t, j) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0;
        }
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.getDouble(t, offset)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.getFloat(t, offset)) != 0;
            case 2:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 3:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 4:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 5:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 6:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(t, offset);
            case 8:
                Object object = UnsafeUtil.getObject(t, offset);
                if (object instanceof String) {
                    equals = ((String) object).isEmpty();
                    break;
                } else if (object instanceof ByteString) {
                    equals = ByteString.EMPTY.equals(object);
                    break;
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return UnsafeUtil.getObject(t, offset) != null;
            case 10:
                equals = ByteString.EMPTY.equals(UnsafeUtil.getObject(t, offset));
                break;
            case 11:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 12:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 13:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 14:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 15:
                return UnsafeUtil.getInt(t, offset) != 0;
            case 16:
                return UnsafeUtil.getLong(t, offset) != 0;
            case 17:
                return UnsafeUtil.getObject(t, offset) != null;
            default:
                throw new IllegalArgumentException();
        }
        return !equals;
    }

    private void setFieldPresent(T t, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.putInt(t, j, (1 << (presenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt(t, j));
    }

    private boolean isOneofPresent(T t, int i, int i2) {
        return UnsafeUtil.getInt(t, (long) (presenceMaskAndOffsetAt(i2) & 1048575)) == i;
    }

    private boolean isOneofCaseEqual(T t, T t2, int i) {
        long presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i) & 1048575;
        return UnsafeUtil.getInt(t, presenceMaskAndOffsetAt) == UnsafeUtil.getInt(t2, presenceMaskAndOffsetAt);
    }

    private void setOneofPresent(T t, int i, int i2) {
        UnsafeUtil.putInt(t, presenceMaskAndOffsetAt(i2) & 1048575, i);
    }

    private int positionForFieldNumber(int i) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, 0);
    }

    private int positionForFieldNumber(int i, int i2) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, i2);
    }

    private int slowPositionForFieldNumber(int i, int i2) {
        int length = (this.buffer.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int numberAt = numberAt(i4);
            if (i == numberAt) {
                return i4;
            }
            if (i < numberAt) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
