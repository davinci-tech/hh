package com.huawei.hms.common.parcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import androidx.core.internal.view.SupportMenu;
import com.huawei.operation.utils.Constants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class ParcelReader {

    /* renamed from: a, reason: collision with root package name */
    private static final int f4465a = 0;
    private static final int b = 1;
    private static final int c = 65262;
    public HashMap<Integer, Integer[]> d = new HashMap<>();
    private Parcel e;

    private int a(int i) {
        if (i < 0) {
            return 0;
        }
        if (i > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return i;
    }

    public short readShort(int i, short s) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return s;
        }
        b(i, 4);
        return (short) this.e.readInt();
    }

    public <T extends Parcelable> T readParcelable(int i, Parcelable.Creator<T> creator, T t) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return t;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        T createFromParcel = creator.createFromParcel(this.e);
        this.e.setDataPosition(dataPosition + b2);
        return createFromParcel;
    }

    public Long readLongObject(int i, Long l) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return l;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        a(b2, 8);
        return Long.valueOf(this.e.readLong());
    }

    public long readLong(int i, long j) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return j;
        }
        b(i, 8);
        return this.e.readLong();
    }

    public void readList(int i, List list, ClassLoader classLoader) {
        if (this.d.containsKey(Integer.valueOf(i))) {
            int b2 = b(i);
            int dataPosition = this.e.dataPosition();
            if (b2 != 0) {
                this.e.readList(list, classLoader);
                this.e.setDataPosition(dataPosition + b2);
            }
        }
    }

    public Integer readIntegerObject(int i, Integer num) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return num;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        a(b2, 4);
        return Integer.valueOf(this.e.readInt());
    }

    public int readInt(int i, int i2) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return i2;
        }
        b(i, 4);
        return this.e.readInt();
    }

    public IBinder readIBinder(int i, IBinder iBinder) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return iBinder;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        IBinder readStrongBinder = this.e.readStrongBinder();
        this.e.setDataPosition(dataPosition + b2);
        return readStrongBinder;
    }

    public Float readFloatObject(int i, Float f) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return f;
        }
        if (b(i) == 0) {
            return null;
        }
        a(i, 4);
        return Float.valueOf(this.e.readFloat());
    }

    public float readFloat(int i, float f) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return f;
        }
        b(i, 4);
        return this.e.readFloat();
    }

    public Double readDoubleObject(int i, Double d) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return d;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        a(b2, 8);
        return Double.valueOf(this.e.readDouble());
    }

    public double readDouble(int i, double d) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return d;
        }
        b(i, 8);
        return this.e.readDouble();
    }

    public char readChar(int i, char c2) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return c2;
        }
        b(i, 4);
        return (char) this.e.readInt();
    }

    public byte readByte(int i, byte b2) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return b2;
        }
        b(i, 4);
        return (byte) this.e.readInt();
    }

    public Bundle readBundle(int i, Bundle bundle) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return bundle;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        Bundle readBundle = this.e.readBundle();
        this.e.setDataPosition(dataPosition + b2);
        return readBundle;
    }

    public Boolean readBooleanObject(int i, Boolean bool) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return bool;
        }
        if (b(i) == 0) {
            return null;
        }
        a(i, 4);
        int readInt = this.e.readInt();
        if (readInt == 0) {
            return Boolean.FALSE;
        }
        if (readInt != 1) {
            return null;
        }
        return Boolean.TRUE;
    }

    public boolean readBoolean(int i, boolean z) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return z;
        }
        b(i, 4);
        return this.e.readInt() != 0;
    }

    public <T> SparseArray<T> createTypedSparseArray(int i, Parcelable.Creator<T> creator, SparseArray<T> sparseArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        SparseArray<T> sparseArray2 = new SparseArray<>();
        for (int i2 = 0; i2 < a2; i2++) {
            sparseArray2.append(this.e.readInt(), this.e.readInt() != 0 ? creator.createFromParcel(this.e) : null);
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseArray2;
    }

    public <T> ArrayList<T> createTypedList(int i, Parcelable.Creator<T> creator, ArrayList<T> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        ArrayList<T> createTypedArrayList = this.e.createTypedArrayList(creator);
        this.e.setDataPosition(dataPosition + b2);
        return createTypedArrayList;
    }

    public <T> T[] createTypedArray(int i, Parcelable.Creator<T> creator, T[] tArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return tArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        T[] tArr2 = (T[]) this.e.createTypedArray(creator);
        this.e.setDataPosition(dataPosition + b2);
        return tArr2;
    }

    public SparseArray<String> createStringSparseArray(int i, SparseArray<String> sparseArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        SparseArray<String> sparseArray2 = new SparseArray<>();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            sparseArray2.append(this.e.readInt(), this.e.readString());
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseArray2;
    }

    public ArrayList<String> createStringList(int i, ArrayList<String> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        ArrayList<String> createStringArrayList = this.e.createStringArrayList();
        this.e.setDataPosition(dataPosition + b2);
        return createStringArrayList;
    }

    public String[] createStringArray(int i, String[] strArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return strArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        String[] createStringArray = this.e.createStringArray();
        this.e.setDataPosition(dataPosition + b2);
        return createStringArray;
    }

    public String createString(int i, String str) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return str;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        String readString = this.e.readString();
        this.e.setDataPosition(dataPosition + b2);
        return readString;
    }

    public SparseLongArray createSparseLongArray(int i, SparseLongArray sparseLongArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseLongArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        SparseLongArray sparseLongArray2 = new SparseLongArray();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            sparseLongArray2.append(this.e.readInt(), this.e.readLong());
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseLongArray2;
    }

    public SparseIntArray createSparseIntArray(int i, SparseIntArray sparseIntArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseIntArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            sparseIntArray2.append(this.e.readInt(), this.e.readInt());
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseIntArray2;
    }

    public SparseBooleanArray createSparseBooleanArray(int i, SparseBooleanArray sparseBooleanArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseBooleanArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        SparseBooleanArray readSparseBooleanArray = this.e.readSparseBooleanArray();
        this.e.setDataPosition(dataPosition + b2);
        return readSparseBooleanArray;
    }

    public SparseArray<Parcel> createParcelSparseArray(int i, SparseArray<Parcel> sparseArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        SparseArray<Parcel> sparseArray2 = new SparseArray<>();
        for (int i2 = 0; i2 < a2; i2++) {
            int readInt = this.e.readInt();
            int readInt2 = this.e.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = this.e.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(this.e, dataPosition2, readInt2);
                sparseArray2.append(readInt, obtain);
                this.e.setDataPosition(dataPosition2 + readInt2);
            } else {
                sparseArray2.append(readInt, null);
            }
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseArray2;
    }

    public ArrayList<Parcel> createParcelList(int i, ArrayList<Parcel> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        ArrayList<Parcel> arrayList2 = new ArrayList<>();
        for (int i2 = 0; i2 < a2; i2++) {
            int readInt = this.e.readInt();
            if (readInt != 0) {
                int dataPosition2 = this.e.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(this.e, dataPosition2, readInt);
                arrayList2.add(obtain);
                this.e.setDataPosition(dataPosition2 + readInt);
            } else {
                arrayList2.add(null);
            }
        }
        this.e.setDataPosition(dataPosition + b2);
        return arrayList2;
    }

    public Parcel[] createParcelArray(int i, Parcel[] parcelArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return parcelArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        Parcel[] parcelArr2 = new Parcel[a2];
        for (int i2 = 0; i2 < a2; i2++) {
            int readInt = this.e.readInt();
            if (readInt != 0) {
                int dataPosition2 = this.e.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(this.e, dataPosition2, readInt);
                parcelArr2[i2] = obtain;
                this.e.setDataPosition(dataPosition2 + readInt);
            } else {
                parcelArr2[i2] = null;
            }
        }
        this.e.setDataPosition(dataPosition + b2);
        return parcelArr2;
    }

    public Parcel createParcel(int i, Parcel parcel) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return parcel;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        Parcel obtain = Parcel.obtain();
        obtain.appendFrom(this.e, dataPosition, b2);
        this.e.setDataPosition(dataPosition + b2);
        return obtain;
    }

    public ArrayList<Long> createLongList(int i, ArrayList<Long> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        ArrayList<Long> arrayList2 = new ArrayList<>();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            arrayList2.add(Long.valueOf(this.e.readLong()));
        }
        this.e.setDataPosition(dataPosition + b2);
        return arrayList2;
    }

    public long[] createLongArray(int i, long[] jArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return jArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        long[] createLongArray = this.e.createLongArray();
        this.e.setDataPosition(dataPosition + b2);
        return createLongArray;
    }

    public ArrayList<Integer> createIntegerList(int i, ArrayList<Integer> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            arrayList2.add(Integer.valueOf(this.e.readInt()));
        }
        this.e.setDataPosition(dataPosition + b2);
        return arrayList2;
    }

    public int[] createIntArray(int i, int[] iArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return iArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int[] createIntArray = this.e.createIntArray();
        this.e.setDataPosition(dataPosition + b2);
        return createIntArray;
    }

    public SparseArray<IBinder> createIBinderSparseArray(int i, SparseArray<IBinder> sparseArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        SparseArray<IBinder> sparseArray2 = new SparseArray<>(a2);
        for (int i2 = 0; i2 < a2; i2++) {
            sparseArray2.append(this.e.readInt(), this.e.readStrongBinder());
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseArray2;
    }

    public ArrayList<IBinder> createIBinderList(int i, ArrayList<IBinder> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        ArrayList<IBinder> createBinderArrayList = this.e.createBinderArrayList();
        this.e.setDataPosition(dataPosition + b2);
        return createBinderArrayList;
    }

    public IBinder[] createIBinderArray(int i, IBinder[] iBinderArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return iBinderArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        IBinder[] createBinderArray = this.e.createBinderArray();
        this.e.setDataPosition(dataPosition + b2);
        return createBinderArray;
    }

    public SparseArray<Float> createFloatSparseArray(int i, SparseArray<Float> sparseArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        SparseArray<Float> sparseArray2 = new SparseArray<>();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            sparseArray2.append(this.e.readInt(), Float.valueOf(this.e.readFloat()));
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseArray2;
    }

    public ArrayList<Float> createFloatList(int i, ArrayList<Float> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        ArrayList<Float> arrayList2 = new ArrayList<>();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            arrayList2.add(Float.valueOf(this.e.readFloat()));
        }
        this.e.setDataPosition(dataPosition + b2);
        return arrayList2;
    }

    public float[] createFloatArray(int i, float[] fArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return fArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        float[] createFloatArray = this.e.createFloatArray();
        this.e.setDataPosition(dataPosition + b2);
        return createFloatArray;
    }

    public SparseArray<Double> createDoubleSparseArray(int i, SparseArray<Double> sparseArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        SparseArray<Double> sparseArray2 = new SparseArray<>();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            sparseArray2.append(this.e.readInt(), Double.valueOf(this.e.readDouble()));
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseArray2;
    }

    public ArrayList<Double> createDoubleList(int i, ArrayList<Double> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        ArrayList<Double> arrayList2 = new ArrayList<>();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            arrayList2.add(Double.valueOf(this.e.readDouble()));
        }
        this.e.setDataPosition(dataPosition + b2);
        return arrayList2;
    }

    public double[] createDoubleArray(int i, double[] dArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return dArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        double[] createDoubleArray = this.e.createDoubleArray();
        this.e.setDataPosition(dataPosition + b2);
        return createDoubleArray;
    }

    public char[] createCharArray(int i, char[] cArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return cArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        char[] createCharArray = this.e.createCharArray();
        this.e.setDataPosition(dataPosition + b2);
        return createCharArray;
    }

    public SparseArray<byte[]> createByteArraySparseArray(int i, SparseArray<byte[]> sparseArray) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        SparseArray<byte[]> sparseArray2 = new SparseArray<>(a2);
        for (int i2 = 0; i2 < a2; i2++) {
            sparseArray2.append(this.e.readInt(), this.e.createByteArray());
        }
        this.e.setDataPosition(dataPosition + b2);
        return sparseArray2;
    }

    public byte[][] createByteArrayArray(int i, byte[][] bArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return bArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        byte[][] bArr2 = new byte[a2][];
        for (int i2 = 0; i2 < a2; i2++) {
            bArr2[i2] = this.e.createByteArray();
        }
        this.e.setDataPosition(dataPosition + b2);
        return bArr2;
    }

    public byte[] createByteArray(int i, byte[] bArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return bArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        byte[] createByteArray = this.e.createByteArray();
        this.e.setDataPosition(dataPosition + b2);
        return createByteArray;
    }

    public ArrayList<Boolean> createBooleanList(int i, ArrayList<Boolean> arrayList) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        ArrayList<Boolean> arrayList2 = new ArrayList<>();
        int a2 = a(this.e.readInt());
        for (int i2 = 0; i2 < a2; i2++) {
            arrayList2.add(Boolean.valueOf(this.e.readInt() != 0));
        }
        this.e.setDataPosition(dataPosition + b2);
        return arrayList2;
    }

    public boolean[] createBooleanArray(int i, boolean[] zArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return zArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        boolean[] createBooleanArray = this.e.createBooleanArray();
        this.e.setDataPosition(dataPosition + b2);
        return createBooleanArray;
    }

    public BigInteger[] createBigIntegerArray(int i, BigInteger[] bigIntegerArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return bigIntegerArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        BigInteger[] bigIntegerArr2 = new BigInteger[a2];
        for (int i2 = 0; i2 < a2; i2++) {
            bigIntegerArr2[i2] = new BigInteger(this.e.createByteArray());
        }
        this.e.setDataPosition(dataPosition + b2);
        return bigIntegerArr2;
    }

    public BigInteger createBigInteger(int i, BigInteger bigInteger) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return bigInteger;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        byte[] createByteArray = this.e.createByteArray();
        this.e.setDataPosition(dataPosition + b2);
        return new BigInteger(createByteArray);
    }

    public BigDecimal[] createBigDecimalArray(int i, BigDecimal[] bigDecimalArr) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return bigDecimalArr;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        int a2 = a(this.e.readInt());
        BigDecimal[] bigDecimalArr2 = new BigDecimal[a2];
        for (int i2 = 0; i2 < a2; i2++) {
            byte[] createByteArray = this.e.createByteArray();
            bigDecimalArr2[i2] = new BigDecimal(new BigInteger(createByteArray), this.e.readInt());
        }
        this.e.setDataPosition(dataPosition + b2);
        return bigDecimalArr2;
    }

    public BigDecimal createBigDecimal(int i, BigDecimal bigDecimal) {
        if (!this.d.containsKey(Integer.valueOf(i))) {
            return bigDecimal;
        }
        int b2 = b(i);
        if (b2 == 0) {
            return null;
        }
        int dataPosition = this.e.dataPosition();
        byte[] createByteArray = this.e.createByteArray();
        int readInt = this.e.readInt();
        this.e.setDataPosition(dataPosition + b2);
        return new BigDecimal(new BigInteger(createByteArray), readInt);
    }

    private int b(int i, int i2) {
        Integer[] numArr = this.d.get(Integer.valueOf(i));
        if (numArr != null) {
            this.e.setDataPosition(numArr[0].intValue());
            a(i, i2);
            return i2;
        }
        throw new ParseException("Field is null:" + numArr, this.e);
    }

    private int b(int i) {
        Integer[] numArr = this.d.get(Integer.valueOf(i));
        if (numArr != null) {
            this.e.setDataPosition(numArr[0].intValue());
            return numArr[1].intValue();
        }
        throw new ParseException("Field is null:" + numArr, this.e);
    }

    private void a(int i, int i2) {
        Integer[] numArr = this.d.get(Integer.valueOf(i));
        if (numArr == null) {
            throw new ParseException("Field is null:" + numArr, this.e);
        }
        int intValue = numArr[1].intValue();
        if (intValue == i2) {
            return;
        }
        throw new ParseException("the field size is not " + i2 + " got " + intValue + " (0x" + Integer.toHexString(intValue) + Constants.RIGHT_BRACKET_ONLY, this.e);
    }

    private void a() {
        int readInt = this.e.readInt();
        int i = readInt & 65535;
        int readInt2 = (readInt & SupportMenu.CATEGORY_MASK) != -65536 ? (readInt >> 16) & 65535 : this.e.readInt();
        if (i != c) {
            throw new ParseException("Parse header error, not 65262. Got 0x" + Integer.toHexString(i), this.e);
        }
        int dataPosition = this.e.dataPosition();
        int i2 = readInt2 + dataPosition;
        if (i2 < dataPosition || i2 > this.e.dataSize()) {
            throw new ParseException("invalid size, start=" + dataPosition + " end=" + i2, this.e);
        }
        while (this.e.dataPosition() < i2) {
            int readInt3 = this.e.readInt();
            int readInt4 = (readInt3 & SupportMenu.CATEGORY_MASK) != -65536 ? (readInt3 >> 16) & 65535 : this.e.readInt();
            int dataPosition2 = this.e.dataPosition();
            this.d.put(Integer.valueOf(readInt3 & 65535), new Integer[]{Integer.valueOf(dataPosition2), Integer.valueOf(readInt4)});
            this.e.setDataPosition(dataPosition2 + readInt4);
        }
        if (this.e.dataPosition() == i2) {
            return;
        }
        throw new ParseException("the dataPosition is not" + i2, this.e);
    }

    public class ParseException extends RuntimeException {
        public ParseException(String str, Parcel parcel) {
            super(str);
        }
    }

    public ParcelReader(Parcel parcel) {
        this.e = parcel;
        a();
    }
}
