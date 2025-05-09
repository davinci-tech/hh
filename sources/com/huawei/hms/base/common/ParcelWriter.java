package com.huawei.hms.base.common;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import androidx.core.internal.view.SupportMenu;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/* loaded from: classes9.dex */
public class ParcelWriter {
    private static final int FIELDID_HEADER = 65262;
    private Parcel parcel;

    public ParcelWriter(Parcel parcel) {
        this.parcel = parcel;
    }

    private void writeFieldHeader(int i, int i2) {
        if (i2 >= 65535) {
            this.parcel.writeInt(i | SupportMenu.CATEGORY_MASK);
            this.parcel.writeInt(i2);
        } else {
            this.parcel.writeInt(i | (i2 << 16));
        }
    }

    private int beginFieldHeader(int i) {
        this.parcel.writeInt(i | SupportMenu.CATEGORY_MASK);
        this.parcel.writeInt(0);
        return this.parcel.dataPosition();
    }

    private void finishFieldHeader(int i) {
        int dataPosition = this.parcel.dataPosition();
        this.parcel.setDataPosition(i - 4);
        this.parcel.writeInt(dataPosition - i);
        this.parcel.setDataPosition(dataPosition);
    }

    public int beginObjectHeader() {
        return beginFieldHeader(FIELDID_HEADER);
    }

    public void finishObjectHeader(int i) {
        finishFieldHeader(i);
    }

    public void writeBoolean(int i, boolean z) {
        writeFieldHeader(i, 4);
        this.parcel.writeInt(z ? 1 : 0);
    }

    public void writeBooleanObject(int i, Boolean bool) {
        writeBooleanObject(i, bool, false);
    }

    public void writeBooleanObject(int i, Boolean bool, boolean z) {
        if (bool != null) {
            writeFieldHeader(i, 4);
            this.parcel.writeInt(bool.booleanValue() ? 1 : 0);
        } else if (z) {
            writeFieldHeader(i, 0);
        }
    }

    public void writeByte(int i, byte b) {
        writeFieldHeader(i, 4);
        this.parcel.writeInt(b);
    }

    public void writeChar(int i, char c) {
        writeFieldHeader(i, 4);
        this.parcel.writeInt(c);
    }

    public void writeShort(int i, short s) {
        writeFieldHeader(i, 4);
        this.parcel.writeInt(s);
    }

    public void writeInt(int i, int i2) {
        writeFieldHeader(i, 4);
        this.parcel.writeInt(i2);
    }

    public void writeIntegerObject(int i, Integer num, boolean z) {
        if (num != null) {
            writeFieldHeader(i, 4);
            this.parcel.writeInt(num.intValue());
        } else if (z) {
            writeFieldHeader(i, 0);
        }
    }

    public void writeLong(int i, long j) {
        writeFieldHeader(i, 8);
        this.parcel.writeLong(j);
    }

    public void writeLongObject(int i, Long l, boolean z) {
        if (l != null) {
            writeFieldHeader(i, 8);
            this.parcel.writeLong(l.longValue());
        } else if (z) {
            writeFieldHeader(i, 0);
        }
    }

    public void writeBigInteger(int i, BigInteger bigInteger, boolean z) {
        if (bigInteger == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeByteArray(bigInteger.toByteArray());
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeFloat(int i, float f) {
        writeFieldHeader(i, 4);
        this.parcel.writeFloat(f);
    }

    public void writeFloatObject(int i, Float f, boolean z) {
        if (f != null) {
            writeFieldHeader(i, 4);
            this.parcel.writeFloat(f.floatValue());
        } else if (z) {
            writeFieldHeader(i, 0);
        }
    }

    public void writeDouble(int i, double d) {
        writeFieldHeader(i, 8);
        this.parcel.writeDouble(d);
    }

    public void writeDoubleObject(int i, Double d, boolean z) {
        if (d != null) {
            writeFieldHeader(i, 8);
            this.parcel.writeDouble(d.doubleValue());
        } else if (z) {
            writeFieldHeader(i, 0);
        }
    }

    public void writeBigDecimal(int i, BigDecimal bigDecimal, boolean z) {
        if (bigDecimal == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
            this.parcel.writeInt(bigDecimal.scale());
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeString(int i, String str, boolean z) {
        if (str == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeString(str);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeIBinder(int i, IBinder iBinder, boolean z) {
        if (iBinder == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeStrongBinder(iBinder);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeParcelable(int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            parcelable.writeToParcel(this.parcel, i2);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeBundle(int i, Bundle bundle, boolean z) {
        if (bundle == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeBundle(bundle);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeByteArray(int i, byte[] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeByteArray(bArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeByteArrayArray(int i, byte[][] bArr, boolean z) {
        if (bArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        this.parcel.writeInt(bArr.length);
        for (byte[] bArr2 : bArr) {
            this.parcel.writeByteArray(bArr2);
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeBooleanArray(int i, boolean[] zArr, boolean z) {
        if (zArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeBooleanArray(zArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeCharArray(int i, char[] cArr, boolean z) {
        if (cArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeCharArray(cArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeIntArray(int i, int[] iArr, boolean z) {
        if (iArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeIntArray(iArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeLongArray(int i, long[] jArr, boolean z) {
        if (jArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeLongArray(jArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeBigIntegerArray(int i, BigInteger[] bigIntegerArr, boolean z) {
        if (bigIntegerArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        this.parcel.writeInt(bigIntegerArr.length);
        for (BigInteger bigInteger : bigIntegerArr) {
            this.parcel.writeByteArray(bigInteger.toByteArray());
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeFloatArray(int i, float[] fArr, boolean z) {
        if (fArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeFloatArray(fArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeDoubleArray(int i, double[] dArr, boolean z) {
        if (dArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeDoubleArray(dArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeBigDecimalArray(int i, BigDecimal[] bigDecimalArr, boolean z) {
        if (bigDecimalArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int length = bigDecimalArr.length;
        this.parcel.writeInt(length);
        for (int i2 = 0; i2 < length; i2++) {
            this.parcel.writeByteArray(bigDecimalArr[i2].unscaledValue().toByteArray());
            this.parcel.writeInt(bigDecimalArr[i2].scale());
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeStringArray(int i, String[] strArr, boolean z) {
        if (strArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeStringArray(strArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeIBinderArray(int i, IBinder[] iBinderArr, boolean z) {
        if (iBinderArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeBinderArray(iBinderArr);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeBooleanList(int i, List<Boolean> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = list.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(list.get(i2).booleanValue() ? 1 : 0);
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeIntegerList(int i, List<Integer> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = list.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(list.get(i2).intValue());
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeLongList(int i, List<Long> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = list.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeLong(list.get(i2).longValue());
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeFloatList(int i, List<Float> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = list.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeFloat(list.get(i2).floatValue());
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeDoubleList(int i, List<Double> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = list.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeDouble(list.get(i2).doubleValue());
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeStringList(int i, List<String> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeStringList(list);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeIBinderList(int i, List<IBinder> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeBinderList(list);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public <T extends Parcelable> void writeTypedArray(int i, T[] tArr, int i2, boolean z) {
        if (tArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        this.parcel.writeInt(tArr.length);
        for (T t : tArr) {
            if (t == null) {
                this.parcel.writeInt(0);
            } else {
                writeParcelableData(t, i2);
            }
        }
        finishFieldHeader(beginFieldHeader);
    }

    public <T extends Parcelable> void writeTypedList(int i, List<T> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = list.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            T t = list.get(i2);
            if (t == null) {
                this.parcel.writeInt(0);
            } else {
                writeParcelableData(t, 0);
            }
        }
        finishFieldHeader(beginFieldHeader);
    }

    private <T extends Parcelable> void writeParcelableData(T t, int i) {
        int dataPosition = this.parcel.dataPosition();
        this.parcel.writeInt(1);
        int dataPosition2 = this.parcel.dataPosition();
        t.writeToParcel(this.parcel, i);
        int dataPosition3 = this.parcel.dataPosition();
        this.parcel.setDataPosition(dataPosition);
        this.parcel.writeInt(dataPosition3 - dataPosition2);
        this.parcel.setDataPosition(dataPosition3);
    }

    public void writeParcel(int i, Parcel parcel, boolean z) {
        if (parcel == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.appendFrom(parcel, 0, parcel.dataSize());
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeParcelArray(int i, Parcel[] parcelArr, boolean z) {
        if (parcelArr == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        this.parcel.writeInt(parcelArr.length);
        for (Parcel parcel : parcelArr) {
            if (parcel != null) {
                this.parcel.writeInt(parcel.dataSize());
                this.parcel.appendFrom(parcel, 0, parcel.dataSize());
            } else {
                this.parcel.writeInt(0);
            }
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeParcelList(int i, List<Parcel> list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = list.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            Parcel parcel = list.get(i2);
            if (parcel != null) {
                this.parcel.writeInt(parcel.dataSize());
                this.parcel.appendFrom(parcel, 0, parcel.dataSize());
            } else {
                this.parcel.writeInt(0);
            }
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeList(int i, List list, boolean z) {
        if (list == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeList(list);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeSparseBooleanArray(int i, SparseBooleanArray sparseBooleanArray, boolean z) {
        if (sparseBooleanArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
            }
        } else {
            int beginFieldHeader = beginFieldHeader(i);
            this.parcel.writeSparseBooleanArray(sparseBooleanArray);
            finishFieldHeader(beginFieldHeader);
        }
    }

    public void writeDoubleSparseArray(int i, SparseArray<Double> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseArray.keyAt(i2));
            this.parcel.writeDouble(sparseArray.valueAt(i2).doubleValue());
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeFloatSparseArray(int i, SparseArray<Float> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseArray.keyAt(i2));
            this.parcel.writeFloat(sparseArray.valueAt(i2).floatValue());
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeSparseIntArray(int i, SparseIntArray sparseIntArray, boolean z) {
        if (sparseIntArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseIntArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseIntArray.keyAt(i2));
            this.parcel.writeInt(sparseIntArray.valueAt(i2));
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeSparseLongArray(int i, SparseLongArray sparseLongArray, boolean z) {
        if (sparseLongArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseLongArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseLongArray.keyAt(i2));
            this.parcel.writeLong(sparseLongArray.valueAt(i2));
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeStringSparseArray(int i, SparseArray<String> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseArray.keyAt(i2));
            this.parcel.writeString(sparseArray.valueAt(i2));
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeParcelSparseArray(int i, SparseArray<Parcel> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseArray.keyAt(i2));
            Parcel valueAt = sparseArray.valueAt(i2);
            if (valueAt != null) {
                this.parcel.writeInt(valueAt.dataSize());
                this.parcel.appendFrom(valueAt, 0, valueAt.dataSize());
            } else {
                this.parcel.writeInt(0);
            }
        }
        finishFieldHeader(beginFieldHeader);
    }

    public <T extends Parcelable> void writeTypedSparseArray(int i, SparseArray<T> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseArray.keyAt(i2));
            T valueAt = sparseArray.valueAt(i2);
            if (valueAt == null) {
                this.parcel.writeInt(0);
            } else {
                writeParcelableData(valueAt, 0);
            }
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeIBinderSparseArray(int i, SparseArray<IBinder> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseArray.keyAt(i2));
            this.parcel.writeStrongBinder(sparseArray.valueAt(i2));
        }
        finishFieldHeader(beginFieldHeader);
    }

    public void writeByteArraySparseArray(int i, SparseArray<byte[]> sparseArray, boolean z) {
        if (sparseArray == null) {
            if (z) {
                writeFieldHeader(i, 0);
                return;
            }
            return;
        }
        int beginFieldHeader = beginFieldHeader(i);
        int size = sparseArray.size();
        this.parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.parcel.writeInt(sparseArray.keyAt(i2));
            this.parcel.writeByteArray(sparseArray.valueAt(i2));
        }
        finishFieldHeader(beginFieldHeader);
    }
}
