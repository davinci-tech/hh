package com.huawei.hms.health;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.safeparcel.SafeParcelReader;
import com.huawei.hms.common.internal.safeparcel.SafeParcelWriter;
import com.huawei.operation.utils.Constants;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class aabs {
    private static <T> T aab(Parcel parcel, Class cls) {
        if (parcel.readInt() == 0) {
            return null;
        }
        if (cls == null || !Parcelable.class.isAssignableFrom(cls)) {
            return (T) parcel.readValue((cls == null || cls.getClassLoader() == null) ? ClassLoader.getSystemClassLoader() : cls.getClassLoader());
        }
        if (aab(cls) != null) {
            return (T) aab(cls).createFromParcel(parcel);
        }
        return null;
    }

    private static Object aabb(Field field, Parcel parcel, int i) {
        switch (aabu.aab(field).ordinal()) {
            case 5:
                Parcelable.Creator<Parcelable> aab = aab(field);
                int aab2 = aab(parcel, i);
                int dataPosition = parcel.dataPosition();
                if (aab2 == 0) {
                    return aab.newArray(0);
                }
                int readInt = parcel.readInt();
                Object[] createTypedArray = parcel.createTypedArray(aab);
                parcel.setDataPosition(dataPosition + readInt);
                return createTypedArray;
            case 6:
                return SafeParcelReader.createStringArray(parcel, i);
            case 7:
                return SafeParcelReader.createByteArray(parcel, i);
            case 8:
            default:
                return null;
            case 9:
                return SafeParcelReader.createIntArray(parcel, i);
            case 10:
                return SafeParcelReader.createLongArray(parcel, i);
            case 11:
                return SafeParcelReader.createFloatArray(parcel, i);
            case 12:
                return SafeParcelReader.createDoubleArray(parcel, i);
            case 13:
                return SafeParcelReader.createCharArray(parcel, i);
            case 14:
                return SafeParcelReader.createBooleanArray(parcel, i);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Object[] aab(Map<Integer, Field> map, Annotation[][] annotationArr, Parcel parcel) {
        int i;
        StringBuilder sb;
        int i2;
        Object[] objArr = new Object[annotationArr.length];
        HashMap hashMap = new HashMap();
        int i3 = 0;
        for (int i4 = 0; i4 < annotationArr.length; i4++) {
            Object obj = null;
            objArr[i4] = null;
            Annotation[] annotationArr2 = annotationArr[i4];
            int length = annotationArr2.length;
            int i5 = 0;
            while (true) {
                if (i5 < length) {
                    Annotation annotation = annotationArr2[i5];
                    if (annotation instanceof aabv) {
                        i2 = ((aabv) annotation).id();
                    } else {
                        i5++;
                    }
                } else {
                    i2 = -1;
                }
            }
            if (i2 > 0 && i2 <= 10000 && map.get(Integer.valueOf(i2)) != null) {
                Field field = map.get(Integer.valueOf(i2));
                switch (aabu.aab(field).ordinal()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 21:
                    case 22:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                        objArr[i4] = obj;
                        hashMap.put(Integer.valueOf(i2), Integer.valueOf(i4));
                        break;
                    case 15:
                        obj = -1;
                        objArr[i4] = obj;
                        hashMap.put(Integer.valueOf(i2), Integer.valueOf(i4));
                        break;
                    case 16:
                        obj = -1L;
                        objArr[i4] = obj;
                        hashMap.put(Integer.valueOf(i2), Integer.valueOf(i4));
                        break;
                    case 17:
                    case 23:
                    case 24:
                        obj = 0;
                        objArr[i4] = obj;
                        hashMap.put(Integer.valueOf(i2), Integer.valueOf(i4));
                        break;
                    case 18:
                        obj = false;
                        objArr[i4] = obj;
                        hashMap.put(Integer.valueOf(i2), Integer.valueOf(i4));
                        break;
                    case 19:
                        obj = Float.valueOf(-1.0f);
                        objArr[i4] = obj;
                        hashMap.put(Integer.valueOf(i2), Integer.valueOf(i4));
                        break;
                    case 20:
                        obj = Double.valueOf(-1.0d);
                        objArr[i4] = obj;
                        hashMap.put(Integer.valueOf(i2), Integer.valueOf(i4));
                        break;
                    default:
                        StringBuilder aab = aab.aab("Unexpected value : ");
                        aab.append(aabu.aab(field));
                        aabz.aab("SafeParcelUtil", aab.toString());
                        StringBuilder aab2 = aab.aab("Unexpected value : ");
                        aab2.append(aabu.aab(field));
                        throw new IllegalStateException(aab2.toString());
                }
            }
        }
        StringBuilder aab3 = aab.aab("idAndIndexMap size : ");
        aab3.append(hashMap.size());
        aab3.toString();
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        while (true) {
            i = i3 + 1;
            if (i3 <= validateObjectHeader && parcel.dataPosition() < validateObjectHeader) {
                int readHeader = SafeParcelReader.readHeader(parcel);
                int fieldId = SafeParcelReader.getFieldId(readHeader);
                Field field2 = map.get(Integer.valueOf(fieldId));
                if (field2 == null || fieldId <= 0 || fieldId > 10000 || !hashMap.containsKey(Integer.valueOf(fieldId))) {
                    sb = new StringBuilder("skip Unknown fieldId : ");
                    sb.append(fieldId);
                } else {
                    try {
                        objArr[((Integer) hashMap.get(Integer.valueOf(fieldId))).intValue()] = aab(parcel, field2, readHeader);
                        field2.getName();
                    } catch (IllegalAccessException | IllegalStateException unused) {
                        sb = new StringBuilder("skip Error field : ");
                        sb.append(fieldId);
                        sb.append(Constants.LEFT_BRACKET_ONLY);
                        sb.append(field2.getName());
                        sb.append(Constants.RIGHT_BRACKET_ONLY);
                    }
                    i3 = i;
                }
                aabz.aab("SafeParcelUtil", sb.toString());
                SafeParcelReader.skipUnknownField(parcel, readHeader);
                i3 = i;
            }
        }
        if (i > validateObjectHeader) {
            aabz.aab("SafeParcelUtil", "Max loop reached, readParcelObject parcel failed");
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return objArr;
    }

    private static Object aab(Field field, Parcel parcel, int i) {
        int ordinal = aabu.aab(field).ordinal();
        if (ordinal == 2) {
            return SafeParcelReader.createStringList(parcel, i);
        }
        if (ordinal == 3) {
            Class aab = aab(field, 0);
            if (aab != null && Parcelable.class.isAssignableFrom(aab)) {
                return SafeParcelReader.createTypedList(parcel, i, aab(aab));
            }
            ArrayList arrayList = new ArrayList();
            SafeParcelReader.readList(parcel, i, arrayList, (aab == null || aab.getClassLoader() == null) ? ClassLoader.getSystemClassLoader() : aab.getClassLoader());
            return arrayList;
        }
        if (ordinal == 21) {
            return SafeParcelReader.createString(parcel, i);
        }
        if (ordinal != 22) {
            switch (ordinal) {
                case 25:
                    return SafeParcelReader.readIntegerObject(parcel, i);
                case 26:
                    return SafeParcelReader.readLongObject(parcel, i);
                case 27:
                    return SafeParcelReader.readFloatObject(parcel, i);
                case 28:
                    return SafeParcelReader.readDoubleObject(parcel, i);
                case 29:
                    return SafeParcelReader.readBooleanObject(parcel, i);
                default:
                    StringBuilder aab2 = aab.aab("Unexpected value（or value is null）: ");
                    aab2.append(aabu.aab(field));
                    aabz.aabc("SafeParcelUtil", aab2.toString());
                    return null;
            }
        }
        int aab3 = aab(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (aab3 == 0) {
            return Collections.emptyMap();
        }
        int readInt = parcel.readInt();
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < aab3; i2++) {
            hashMap.put(aab(parcel, aab(field, 0)), aab(parcel, aab(field, 1)));
        }
        parcel.setDataPosition(dataPosition + readInt);
        return hashMap;
    }

    private static boolean aab(Object obj, int i, boolean z, Parcel parcel, Field field, int i2) {
        int ordinal = aabu.aab(field).ordinal();
        if (ordinal == 8) {
            SafeParcelWriter.writeIBinder(parcel, i, obj == null ? null : ((IInterface) obj).asBinder(), z);
        } else if (ordinal == 22) {
            Map map = (Map) obj;
            if (map != null) {
                int aab = aab(parcel, i, map.size());
                if (map.size() != 0) {
                    parcel.writeInt(aab);
                    for (Map.Entry entry : map.entrySet()) {
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        aab(parcel, key, i2);
                        if (value == null) {
                            parcel.writeInt(0);
                        } else {
                            aab(parcel, value, i2);
                        }
                    }
                    aaba(parcel, aab);
                }
            }
        } else if (ordinal == 0) {
            SafeParcelWriter.writeParcelable(parcel, i, (Parcelable) obj, i2, z);
        } else if (ordinal == 1) {
            SafeParcelWriter.writeIBinder(parcel, i, (IBinder) obj, z);
        } else if (ordinal == 2) {
            SafeParcelWriter.writeStringList(parcel, i, (List) obj, z);
        } else if (ordinal == 3) {
            Class aab2 = aab(field, 0);
            if (aab2 == null || !Parcelable.class.isAssignableFrom(aab2)) {
                SafeParcelWriter.writeList(parcel, i, (List) obj, z);
            } else {
                SafeParcelWriter.writeTypedList(parcel, i, (List) obj, z);
            }
        } else {
            if (ordinal != 4) {
                return false;
            }
            SafeParcelWriter.writeBundle(parcel, i, (Bundle) obj, z);
        }
        return true;
    }

    private static boolean aab(Object obj, int i, boolean z, Parcel parcel, aabu aabuVar, int i2) {
        switch (aabuVar.ordinal()) {
            case 5:
                Parcelable[] parcelableArr = (Parcelable[]) obj;
                if (parcelableArr == null) {
                    return true;
                }
                int aab = aab(parcel, i, parcelableArr.length);
                if (parcelableArr.length == 0) {
                    return true;
                }
                parcel.writeInt(aab);
                parcel.writeInt(parcelableArr.length);
                for (Parcelable parcelable : parcelableArr) {
                    if (parcelable == null) {
                        parcel.writeInt(0);
                    } else {
                        aab(parcel, parcelable, i2);
                    }
                }
                aaba(parcel, aab);
                return true;
            case 6:
                SafeParcelWriter.writeStringArray(parcel, i, (String[]) obj, z);
                return true;
            case 7:
                SafeParcelWriter.writeByteArray(parcel, i, (byte[]) obj, z);
                return true;
            case 8:
            default:
                return false;
            case 9:
                SafeParcelWriter.writeIntArray(parcel, i, (int[]) obj, z);
                return true;
            case 10:
                SafeParcelWriter.writeLongArray(parcel, i, (long[]) obj, z);
                return true;
            case 11:
                SafeParcelWriter.writeFloatArray(parcel, i, (float[]) obj, z);
                return true;
            case 12:
                SafeParcelWriter.writeDoubleArray(parcel, i, (double[]) obj, z);
                return true;
            case 13:
                SafeParcelWriter.writeCharArray(parcel, i, (char[]) obj, z);
                return true;
            case 14:
                SafeParcelWriter.writeBooleanArray(parcel, i, (boolean[]) obj, z);
                return true;
        }
    }

    private static void aab(Parcelable parcelable, Parcel parcel, Field field, int i) throws IllegalAccessException {
        aaby aabyVar = (aaby) field.getAnnotation(aaby.class);
        if (aabyVar == null) {
            aabz.aab("SafeParcelUtil", "getFieldId IllegalStateException");
            throw new IllegalStateException();
        }
        int id = aabyVar.id();
        if (id <= 0 || id > 10000) {
            aabz.aab("SafeParcelUtil", "fieldId Must be in the range of 1-10000, failed fieldId : " + id);
            return;
        }
        boolean isAccessible = field.isAccessible();
        boolean CanBeNull = field.getAnnotation(aaby.class) != null ? ((aaby) field.getAnnotation(aaby.class)).CanBeNull() : true;
        field.setAccessible(true);
        try {
            Object obj = field.get(parcelable);
            aabu aab = aabu.aab(field);
            if (aab(obj, id, CanBeNull, parcel, field, i)) {
                return;
            }
            if (aab(obj, id, CanBeNull, parcel, aab, i)) {
                return;
            }
            if (aab(obj, id, parcel, aab)) {
                return;
            }
            int ordinal = aab.ordinal();
            if (ordinal != 21) {
                switch (ordinal) {
                    case 25:
                        SafeParcelWriter.writeIntegerObject(parcel, id, (Integer) obj, CanBeNull);
                        break;
                    case 26:
                        SafeParcelWriter.writeLongObject(parcel, id, (Long) obj, CanBeNull);
                        break;
                    case 27:
                        SafeParcelWriter.writeFloatObject(parcel, id, (Float) obj, CanBeNull);
                        break;
                    case 28:
                        SafeParcelWriter.writeDoubleObject(parcel, id, (Double) obj, CanBeNull);
                        break;
                    case 29:
                        SafeParcelWriter.writeBooleanObject(parcel, id, (Boolean) obj, CanBeNull);
                        break;
                    default:
                        aabz.aab("SafeParcelUtil", "[writeObjectField] cant find fit case");
                        break;
                }
            } else {
                SafeParcelWriter.writeString(parcel, id, (String) obj, CanBeNull);
            }
        } finally {
            field.setAccessible(isAccessible);
        }
    }

    private static void aaba(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    private static Object aaba(Field field, Parcel parcel, int i) {
        switch (aabu.aab(field).ordinal()) {
            case 15:
                return Integer.valueOf(SafeParcelReader.readInt(parcel, i));
            case 16:
                return Long.valueOf(SafeParcelReader.readLong(parcel, i));
            case 17:
                return Character.valueOf(SafeParcelReader.readChar(parcel, i));
            case 18:
                return Boolean.valueOf(SafeParcelReader.readBoolean(parcel, i));
            case 19:
                return Float.valueOf(SafeParcelReader.readFloat(parcel, i));
            case 20:
                return Double.valueOf(SafeParcelReader.readDouble(parcel, i));
            case 21:
            case 22:
            default:
                return null;
            case 23:
                return Byte.valueOf(SafeParcelReader.readByte(parcel, i));
            case 24:
                return Short.valueOf(SafeParcelReader.readShort(parcel, i));
        }
    }

    private static boolean aab(Object obj, int i, Parcel parcel, aabu aabuVar) {
        switch (aabuVar.ordinal()) {
            case 15:
                SafeParcelWriter.writeInt(parcel, i, ((Integer) obj).intValue());
                return true;
            case 16:
                SafeParcelWriter.writeLong(parcel, i, ((Long) obj).longValue());
                return true;
            case 17:
                SafeParcelWriter.writeChar(parcel, i, ((Character) obj).charValue());
                return true;
            case 18:
                SafeParcelWriter.writeBoolean(parcel, i, ((Boolean) obj).booleanValue());
                return true;
            case 19:
                SafeParcelWriter.writeFloat(parcel, i, ((Float) obj).floatValue());
                return true;
            case 20:
                SafeParcelWriter.writeDouble(parcel, i, ((Double) obj).doubleValue());
                return true;
            case 21:
            case 22:
            default:
                return false;
            case 23:
                SafeParcelWriter.writeByte(parcel, i, ((Byte) obj).byteValue());
                return true;
            case 24:
                SafeParcelWriter.writeShort(parcel, i, ((Short) obj).shortValue());
                return true;
        }
    }

    public static void aab(Parcelable parcelable, Parcel parcel, int i) {
        int i2;
        parcelable.getClass();
        HashMap hashMap = new HashMap();
        Class<?> cls = parcelable.getClass();
        while (true) {
            i2 = 0;
            if (cls == null) {
                break;
            }
            Field[] declaredFields = cls.getDeclaredFields();
            int length = declaredFields.length;
            while (i2 < length) {
                Field field = declaredFields[i2];
                if (field != null && field.isAnnotationPresent(aaby.class)) {
                    int id = ((aaby) field.getAnnotation(aaby.class)).id();
                    if (hashMap.get(Integer.valueOf(id)) != null) {
                        StringBuilder aab = aab.aab("Field of ");
                        aab.append(field.getName());
                        aab.append(Constants.LEFT_BRACKET_ONLY);
                        aab.append(id);
                        aab.append(") repeat with ");
                        aab.append(((Field) hashMap.get(Integer.valueOf(id))).getName());
                        aab.append(" in ");
                        aab.append(cls.getName());
                        aabz.aab("SafeParcelUtil", aab.toString());
                    } else {
                        hashMap.put(Integer.valueOf(id), field);
                    }
                }
                i2++;
            }
            cls = cls.getSuperclass();
        }
        Object[] array = hashMap.keySet().toArray();
        Arrays.sort(array);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int length2 = array.length;
        while (i2 < length2) {
            try {
                aab(parcelable, parcel, (Field) hashMap.get(array[i2]), i);
            } catch (Exception unused) {
                aabz.aab("SafeParcelUtil", "write Object Field Exception");
            }
            i2++;
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private static <T> void aab(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        if (t instanceof Parcelable) {
            ((Parcelable) t).writeToParcel(parcel, i);
        } else {
            parcel.writeValue(t);
        }
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    private static Object aab(Field field, Parcel parcel, int i, boolean z) throws IllegalAccessException {
        int ordinal = aabu.aab(field).ordinal();
        if (ordinal == 0) {
            return SafeParcelReader.createParcelable(parcel, i, aab(field));
        }
        boolean z2 = true;
        if (ordinal == 1) {
            return SafeParcelReader.readIBinder(parcel, i);
        }
        if (ordinal == 4) {
            return SafeParcelReader.createBundle(parcel, i);
        }
        Object obj = null;
        if (ordinal != 8) {
            return null;
        }
        Class<?>[] declaredClasses = field.getType().getDeclaredClasses();
        int length = declaredClasses.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z2 = false;
                break;
            }
            Class<?> cls = declaredClasses[i2];
            try {
                IBinder readIBinder = SafeParcelReader.readIBinder(parcel, i);
                if (readIBinder != null) {
                    obj = cls.getDeclaredMethod("asInterface", IBinder.class).invoke(null, readIBinder);
                    break;
                }
                break;
            } catch (NoSuchMethodException | InvocationTargetException unused) {
                aabz.aab("SafeParcelUtil", "[readObjectField] readIBinder Exception, ignore");
                i2++;
            }
        }
        if (z2 || z) {
            return obj;
        }
        throw new IllegalStateException("[interface] Field illegal : " + field);
    }

    private static Object aab(Parcel parcel, Field field, int i) throws IllegalAccessException {
        boolean isAccessible = field.isAccessible();
        boolean CanBeNull = field.getAnnotation(aaby.class) != null ? ((aaby) field.getAnnotation(aaby.class)).CanBeNull() : true;
        field.setAccessible(true);
        try {
            Object aab = aab(field, parcel, i, CanBeNull);
            if (aab != null) {
                return aab;
            }
            Object aabb = aabb(field, parcel, i);
            if (aabb != null) {
                return aabb;
            }
            Object aaba = aaba(field, parcel, i);
            return aaba != null ? aaba : aab(field, parcel, i);
        } finally {
            field.setAccessible(isAccessible);
        }
    }

    static Class aab(Field field, int i) {
        Type type;
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            if (parameterizedType.getActualTypeArguments().length >= 1 && i < parameterizedType.getActualTypeArguments().length && i >= 0) {
                type = parameterizedType.getActualTypeArguments()[i];
                if (type instanceof Class) {
                    return (Class) type;
                }
                return (Class) type;
            }
        }
        type = null;
        return (Class) type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v4, types: [android.os.Parcelable] */
    public static <T extends Parcelable> T aab(Class<T> cls, Parcel parcel) {
        String str;
        T t = null;
        try {
            HashMap hashMap = new HashMap();
            Class<T> cls2 = cls;
            while (true) {
                int i = 0;
                if (cls2 != null) {
                    Field[] declaredFields = cls2.getDeclaredFields();
                    int length = declaredFields.length;
                    while (i < length) {
                        Field field = declaredFields[i];
                        if (field.isAnnotationPresent(aaby.class)) {
                            int id = ((aaby) field.getAnnotation(aaby.class)).id();
                            if (hashMap.containsKey(Integer.valueOf(id))) {
                                aabz.aab("SafeParcelUtil", "Field of " + field.getName() + " (" + id + ") in " + cls2.getName() + " is repeat of " + ((Field) hashMap.get(Integer.valueOf(id))).getName());
                                return null;
                            }
                            hashMap.put(Integer.valueOf(id), field);
                        }
                        i++;
                    }
                    cls2 = cls2.getSuperclass();
                } else {
                    hashMap.size();
                    cls.getName();
                    Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
                    int length2 = declaredConstructors.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        Constructor<?> constructor = declaredConstructors[i2];
                        boolean isAccessible = constructor.isAccessible();
                        constructor.setAccessible(true);
                        if (constructor.isAnnotationPresent(aabw.class)) {
                            cls.getName();
                            t = (Parcelable) constructor.newInstance(aab(hashMap, constructor.getParameterAnnotations(), parcel));
                            i = 1;
                            break;
                        }
                        constructor.setAccessible(isAccessible);
                        i2++;
                    }
                    if (i == 0) {
                        aabz.aab("SafeParcelUtil", "[createObject] not find Constructor of " + cls.getName());
                    }
                }
            }
        } catch (IllegalAccessException unused) {
            str = "Encountered an IllegalAccessException";
            aabz.aab("SafeParcelUtil", str);
            return t;
        } catch (InstantiationException unused2) {
            str = "Encountered an InstantiationException";
            aabz.aab("SafeParcelUtil", str);
            return t;
        } catch (InvocationTargetException unused3) {
            str = "Encountered an InvocationTargetException";
            aabz.aab("SafeParcelUtil", str);
            return t;
        }
    }

    static Parcelable.Creator<Parcelable> aab(Field field) {
        Class<?> type = field.getType();
        if (type.isArray()) {
            type = type.getComponentType();
        }
        if (type != null && Parcelable.class.isAssignableFrom(type)) {
            return aab(type);
        }
        aabz.aab("SafeParcelUtil", type + " not have Parcelable Field");
        throw new IllegalStateException(type + " not have Parcelable Field");
    }

    static Parcelable.Creator<Parcelable> aab(Class cls) {
        try {
            Field declaredField = cls.getDeclaredField("CREATOR");
            AccessController.doPrivileged(new aabt(declaredField));
            return (Parcelable.Creator) declaredField.get(null);
        } catch (IllegalAccessException unused) {
            aabz.aab("SafeParcelUtil", cls + " CREATOR is not useable");
            throw new IllegalArgumentException(cls + " CREATOR is not useable");
        } catch (NoSuchFieldException unused2) {
            aabz.aab("SafeParcelUtil", "not CREATOR in " + cls);
            throw new IllegalArgumentException("not CREATOR in " + cls);
        }
    }

    private static int aab(Parcel parcel, int i, int i2) {
        if (i2 == 0 || i2 >= 65535) {
            parcel.writeInt(i & 65535);
            parcel.writeInt(i2);
        } else {
            parcel.writeInt(i | (i2 << 16));
        }
        return parcel.dataPosition();
    }

    private static int aab(Parcel parcel, int i) {
        return (i | 65535) != 65535 ? (i >> 16) & 65535 : parcel.readInt();
    }
}
