package com.huawei.harmonyos.interwork.base.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/* loaded from: classes3.dex */
public class IntentParams implements Parcelable {
    public static final Parcelable.Creator<IntentParams> CREATOR = new Parcelable.Creator<IntentParams>() { // from class: com.huawei.harmonyos.interwork.base.content.IntentParams.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: Aj_, reason: merged with bridge method [inline-methods] */
        public final IntentParams createFromParcel(Parcel parcel) {
            return new IntentParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final IntentParams[] newArray(int i) {
            return new IntentParams[i];
        }
    };
    private static final int MAX_LIMITE_SIZE = 512000;
    private static final int NULL_OBJ_IN_PARCEL = -1;
    private static final int VALUE_TYPE_BOOLEAN = 1;
    private static final int VALUE_TYPE_BOOLEANARRAY = 11;
    private static final int VALUE_TYPE_BYTE = 2;
    private static final int VALUE_TYPE_BYTEARRAY = 12;
    private static final int VALUE_TYPE_CHAR = 3;
    private static final int VALUE_TYPE_CHARARRAY = 13;
    private static final int VALUE_TYPE_CHARSEQUENCE = 10;
    private static final int VALUE_TYPE_CHARSEQUENCEARRAY = 20;
    private static final int VALUE_TYPE_DOUBLE = 8;
    private static final int VALUE_TYPE_DOUBLEARRAY = 18;
    private static final int VALUE_TYPE_FLOAT = 7;
    private static final int VALUE_TYPE_FLOATARRAY = 17;
    private static final int VALUE_TYPE_INT = 5;
    private static final int VALUE_TYPE_INTARRAY = 15;
    private static final int VALUE_TYPE_LIST = 50;
    private static final int VALUE_TYPE_LONG = 6;
    private static final int VALUE_TYPE_LONGARRAY = 16;
    private static final int VALUE_TYPE_NULL = -1;
    private static final int VALUE_TYPE_SHORT = 4;
    private static final int VALUE_TYPE_SHORTARRAY = 14;
    private static final int VALUE_TYPE_STRING = 9;
    private static final int VALUE_TYPE_STRINGARRAY = 19;
    private Map<String, Object> params;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IntentParams() {
        this.params = new HashMap();
    }

    public IntentParams(IntentParams intentParams) {
        this.params = new HashMap();
        if (intentParams != null) {
            for (Map.Entry<String, Object> entry : intentParams.params.entrySet()) {
                this.params.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private IntentParams(Parcel parcel) {
        int readInt = parcel.readInt();
        this.params = new HashMap();
        if (readInt > MAX_LIMITE_SIZE) {
            return;
        }
        for (int i = 0; i < readInt; i++) {
            this.params.put(parcel.readString(), readObject(parcel).orElse(null));
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.params.size());
        for (Map.Entry<String, Object> entry : this.params.entrySet()) {
            parcel.writeString(entry.getKey());
            writeObject(parcel, entry.getValue());
        }
    }

    private void writeObject(Parcel parcel, Object obj) {
        if (obj == null) {
            parcel.writeInt(-1);
        } else if (obj.getClass().isArray()) {
            writeArrayObject(parcel, obj);
        } else {
            writeBaseTypeObject(parcel, obj);
        }
    }

    private void writeArrayObject(Parcel parcel, Object obj) {
        if (obj instanceof boolean[]) {
            parcel.writeInt(11);
            parcel.writeBooleanArray((boolean[]) obj);
            return;
        }
        if (obj instanceof byte[]) {
            parcel.writeInt(12);
            parcel.writeByteArray((byte[]) obj);
            return;
        }
        if (obj instanceof char[]) {
            parcel.writeInt(13);
            parcel.writeCharArray((char[]) obj);
            return;
        }
        if (obj instanceof int[]) {
            parcel.writeInt(15);
            parcel.writeIntArray((int[]) obj);
            return;
        }
        if (obj instanceof short[]) {
            parcel.writeInt(14);
            writeShortArray(parcel, (short[]) obj);
            return;
        }
        if (obj instanceof long[]) {
            parcel.writeInt(16);
            parcel.writeLongArray((long[]) obj);
            return;
        }
        if (obj instanceof float[]) {
            parcel.writeInt(17);
            parcel.writeFloatArray((float[]) obj);
            return;
        }
        if (obj instanceof double[]) {
            parcel.writeInt(18);
            parcel.writeDoubleArray((double[]) obj);
            return;
        }
        if (obj instanceof String[]) {
            parcel.writeInt(19);
            parcel.writeStringArray((String[]) obj);
        } else if (obj instanceof CharSequence[]) {
            parcel.writeInt(20);
            writeCharSequenceArray(parcel, (CharSequence[]) obj);
        } else {
            throw new IllegalArgumentException("write IntentParams " + this + ": unknown type " + obj.getClass());
        }
    }

    private void writeBaseTypeObject(Parcel parcel, Object obj) {
        if (obj instanceof Boolean) {
            parcel.writeInt(1);
            parcel.writeBoolean(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Byte) {
            parcel.writeInt(2);
            parcel.writeByte(((Byte) obj).byteValue());
            return;
        }
        if (obj instanceof Character) {
            parcel.writeInt(3);
            parcel.writeInt(((Character) obj).charValue());
            return;
        }
        if (obj instanceof Short) {
            parcel.writeInt(4);
            parcel.writeInt(((Short) obj).shortValue());
            return;
        }
        if (obj instanceof Integer) {
            parcel.writeInt(5);
            parcel.writeInt(((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Long) {
            parcel.writeInt(6);
            parcel.writeLong(((Long) obj).longValue());
            return;
        }
        if (obj instanceof Float) {
            parcel.writeInt(7);
            parcel.writeFloat(((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Double) {
            parcel.writeInt(8);
            parcel.writeDouble(((Double) obj).doubleValue());
            return;
        }
        if (obj instanceof String) {
            parcel.writeInt(9);
            parcel.writeString((String) obj);
        } else if (obj instanceof CharSequence) {
            parcel.writeInt(10);
            TextUtils.writeToParcel((CharSequence) obj, parcel, 0);
        } else {
            throw new IllegalArgumentException("write IntentParams " + this + ": unknown type " + obj.getClass());
        }
    }

    private <T> void writeWithPosition(Parcel parcel, Consumer<T> consumer, T t) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        consumer.accept(t);
        int dataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition2);
        consumer.accept(t);
    }

    private void writeShortArray(Parcel parcel, short[] sArr) {
        parcel.writeInt(sArr.length);
        for (short s : sArr) {
            parcel.writeInt(s);
        }
    }

    private void writeCharSequenceArray(Parcel parcel, CharSequence[] charSequenceArr) {
        parcel.writeInt(charSequenceArr.length);
        for (CharSequence charSequence : charSequenceArr) {
            TextUtils.writeToParcel(charSequence, parcel, 0);
        }
    }

    private void writeList(Parcel parcel, List list) {
        if (list == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            writeObject(parcel, it.next());
        }
    }

    private Optional<Object> readObject(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1) {
            return Optional.empty();
        }
        if (readInt > 0 && readInt <= 10) {
            return Optional.ofNullable(readBaseTypeObject(parcel, readInt));
        }
        if (readInt > 10 && readInt <= 20) {
            return Optional.ofNullable(readBaseTypeArrayObject(parcel, readInt));
        }
        throw new IllegalArgumentException("IntentParams " + this + ": unknown value type " + readInt);
    }

    private Object readBaseTypeObject(Parcel parcel, int i) {
        switch (i) {
            case 1:
                return Boolean.valueOf(parcel.readBoolean());
            case 2:
                return Byte.valueOf(parcel.readByte());
            case 3:
                return Character.valueOf((char) parcel.readInt());
            case 4:
                return Short.valueOf((short) parcel.readInt());
            case 5:
                return Integer.valueOf(parcel.readInt());
            case 6:
                return Long.valueOf(parcel.readLong());
            case 7:
                return Float.valueOf(parcel.readFloat());
            case 8:
                return Double.valueOf(parcel.readDouble());
            case 9:
                return parcel.readString();
            case 10:
                return TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            default:
                throw new IllegalArgumentException("IntentParams " + this + ": unknown value type " + i);
        }
    }

    private Object readBaseTypeArrayObject(Parcel parcel, int i) {
        switch (i) {
            case 11:
                return parcel.createBooleanArray();
            case 12:
                return parcel.createByteArray();
            case 13:
                return parcel.createCharArray();
            case 14:
                return readShrotArray(parcel);
            case 15:
                return parcel.createIntArray();
            case 16:
                return parcel.createLongArray();
            case 17:
                return parcel.createFloatArray();
            case 18:
                return parcel.createDoubleArray();
            case 19:
                return parcel.createStringArray();
            case 20:
                return readCharSequenceArray(parcel);
            default:
                throw new IllegalArgumentException("IntentParams " + this + ": unknown value type " + i);
        }
    }

    private short[] readShrotArray(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1 || readInt > MAX_LIMITE_SIZE) {
            return new short[0];
        }
        short[] sArr = new short[readInt];
        for (int i = 0; i < readInt; i++) {
            sArr[i] = (short) parcel.readInt();
        }
        return sArr;
    }

    private CharSequence[] readCharSequenceArray(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1 || readInt > MAX_LIMITE_SIZE) {
            return new CharSequence[0];
        }
        CharSequence[] charSequenceArr = new CharSequence[readInt];
        for (int i = 0; i < readInt; i++) {
            charSequenceArr[i] = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
        return charSequenceArr;
    }

    public <T> void setParam(String str, T t) {
        this.params.put(str, t);
    }

    public boolean isEmpty() {
        return this.params.isEmpty();
    }

    public int size() {
        return this.params.size();
    }

    public void remove(String str) {
        this.params.remove(str);
    }

    public Object getParam(String str) {
        return this.params.get(str);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public boolean hasParam(String str) {
        return this.params.containsKey(str);
    }

    public Set<String> keySet() {
        return this.params.keySet();
    }
}
