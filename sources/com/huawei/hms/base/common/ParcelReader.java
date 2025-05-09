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
import com.huawei.operation.utils.Constants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class ParcelReader {
    private static final int FIELDID_HEADER = 65262;
    private static final int INDEX_POSITION = 0;
    private static final int INDEX_SIZE = 1;
    private int endPosition;
    HashMap<Integer, Integer[]> mapFields = new HashMap<>();
    private Parcel parcel;

    public ParcelReader(Parcel parcel) {
        this.parcel = parcel;
        initFields();
    }

    private void initFields() {
        int readInt = this.parcel.readInt();
        int i = readInt & 65535;
        int readInt2 = (readInt & SupportMenu.CATEGORY_MASK) != -65536 ? (readInt >> 16) & 65535 : this.parcel.readInt();
        if (i != FIELDID_HEADER) {
            throw new ParseException("Expected object header. Got 0x" + Integer.toHexString(i), this.parcel);
        }
        int dataPosition = this.parcel.dataPosition();
        int i2 = readInt2 + dataPosition;
        if (i2 < dataPosition || i2 > this.parcel.dataSize()) {
            throw new ParseException("Size read is invalid start=" + dataPosition + " end=" + i2, this.parcel);
        }
        while (this.parcel.dataPosition() < i2) {
            int readInt3 = this.parcel.readInt();
            int readInt4 = (readInt3 & SupportMenu.CATEGORY_MASK) != -65536 ? (readInt3 >> 16) & 65535 : this.parcel.readInt();
            int dataPosition2 = this.parcel.dataPosition();
            this.mapFields.put(Integer.valueOf(readInt3 & 65535), new Integer[]{Integer.valueOf(dataPosition2), Integer.valueOf(readInt4)});
            this.parcel.setDataPosition(dataPosition2 + readInt4);
        }
        if (this.parcel.dataPosition() != i2) {
            throw new ParseException("Overread allowed size end=" + i2, this.parcel);
        }
        this.endPosition = i2;
    }

    private int moveField(int i) {
        Integer[] numArr = this.mapFields.get(Integer.valueOf(i));
        if (numArr == null) {
            throw new ParseException("Field not exist:" + numArr, this.parcel);
        }
        this.parcel.setDataPosition(numArr[0].intValue());
        return numArr[1].intValue();
    }

    private void ensureFieldSize(int i, int i2) {
        Integer[] numArr = this.mapFields.get(Integer.valueOf(i));
        if (numArr == null) {
            throw new ParseException("Field not exist:" + numArr, this.parcel);
        }
        int intValue = numArr[1].intValue();
        if (intValue == i2) {
            return;
        }
        throw new ParseException("Expected size " + i2 + " got " + intValue + " (0x" + Integer.toHexString(intValue) + Constants.RIGHT_BRACKET_ONLY, this.parcel);
    }

    private int moveFieldAndEnsureSize(int i, int i2) {
        Integer[] numArr = this.mapFields.get(Integer.valueOf(i));
        if (numArr == null) {
            throw new ParseException("Field not exist:" + numArr, this.parcel);
        }
        this.parcel.setDataPosition(numArr[0].intValue());
        ensureFieldSize(i, i2);
        return i2;
    }

    public void finish() {
        this.parcel.setDataPosition(this.endPosition);
    }

    public boolean readBoolean(int i, boolean z) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return z;
        }
        moveFieldAndEnsureSize(i, 4);
        return this.parcel.readInt() != 0;
    }

    public Boolean readBooleanObject(int i, Boolean bool) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return bool;
        }
        if (moveField(i) == 0) {
            return null;
        }
        ensureFieldSize(i, 4);
        int readInt = this.parcel.readInt();
        if (readInt == 0) {
            return Boolean.FALSE;
        }
        if (readInt != 1) {
            return null;
        }
        return Boolean.TRUE;
    }

    public byte readByte(int i, byte b) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return b;
        }
        moveFieldAndEnsureSize(i, 4);
        return (byte) this.parcel.readInt();
    }

    public char readChar(int i, char c) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return c;
        }
        moveFieldAndEnsureSize(i, 4);
        return (char) this.parcel.readInt();
    }

    public short readShort(int i, short s) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return s;
        }
        moveFieldAndEnsureSize(i, 4);
        return (short) this.parcel.readInt();
    }

    public int readInt(int i, int i2) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return i2;
        }
        moveFieldAndEnsureSize(i, 4);
        return this.parcel.readInt();
    }

    public Integer readIntegerObject(int i, Integer num) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return num;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        ensureFieldSize(moveField, 4);
        return Integer.valueOf(this.parcel.readInt());
    }

    public long readLong(int i, long j) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return j;
        }
        moveFieldAndEnsureSize(i, 8);
        return this.parcel.readLong();
    }

    public Long readLongObject(int i, Long l) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return l;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        ensureFieldSize(moveField, 8);
        return Long.valueOf(this.parcel.readLong());
    }

    public BigInteger createBigInteger(int i, BigInteger bigInteger) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return bigInteger;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        byte[] createByteArray = this.parcel.createByteArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return new BigInteger(createByteArray);
    }

    public float readFloat(int i, float f) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return f;
        }
        moveFieldAndEnsureSize(i, 4);
        return this.parcel.readFloat();
    }

    public Float readFloatObject(int i, Float f) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return f;
        }
        if (moveField(i) == 0) {
            return null;
        }
        ensureFieldSize(i, 4);
        return Float.valueOf(this.parcel.readFloat());
    }

    public double readDouble(int i, double d) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return d;
        }
        moveFieldAndEnsureSize(i, 8);
        return this.parcel.readDouble();
    }

    public Double readDoubleObject(int i, Double d) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return d;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        ensureFieldSize(moveField, 8);
        return Double.valueOf(this.parcel.readDouble());
    }

    public BigDecimal createBigDecimal(int i, BigDecimal bigDecimal) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return bigDecimal;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        byte[] createByteArray = this.parcel.createByteArray();
        int readInt = this.parcel.readInt();
        this.parcel.setDataPosition(dataPosition + moveField);
        return new BigDecimal(new BigInteger(createByteArray), readInt);
    }

    public String createString(int i, String str) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return str;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        String readString = this.parcel.readString();
        this.parcel.setDataPosition(dataPosition + moveField);
        return readString;
    }

    public IBinder readIBinder(int i, IBinder iBinder) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return iBinder;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        IBinder readStrongBinder = this.parcel.readStrongBinder();
        this.parcel.setDataPosition(dataPosition + moveField);
        return readStrongBinder;
    }

    public <T extends Parcelable> T readParcelable(int i, Parcelable.Creator<T> creator, T t) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return t;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        T createFromParcel = creator.createFromParcel(this.parcel);
        this.parcel.setDataPosition(dataPosition + moveField);
        return createFromParcel;
    }

    public Bundle readBundle(int i, Bundle bundle) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return bundle;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        Bundle readBundle = this.parcel.readBundle();
        this.parcel.setDataPosition(dataPosition + moveField);
        return readBundle;
    }

    public byte[] createByteArray(int i, byte[] bArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return bArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new byte[0];
        }
        int dataPosition = this.parcel.dataPosition();
        byte[] createByteArray = this.parcel.createByteArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createByteArray;
    }

    public byte[][] createByteArrayArray(int i, byte[][] bArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return bArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new byte[0][];
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        byte[][] bArr2 = new byte[readInt][];
        for (int i2 = 0; i2 < readInt; i2++) {
            bArr2[i2] = this.parcel.createByteArray();
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return bArr2;
    }

    public boolean[] createBooleanArray(int i, boolean[] zArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return zArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new boolean[0];
        }
        int dataPosition = this.parcel.dataPosition();
        boolean[] createBooleanArray = this.parcel.createBooleanArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createBooleanArray;
    }

    public char[] createCharArray(int i, char[] cArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return cArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new char[0];
        }
        int dataPosition = this.parcel.dataPosition();
        char[] createCharArray = this.parcel.createCharArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createCharArray;
    }

    public int[] createIntArray(int i, int[] iArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return iArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new int[0];
        }
        int dataPosition = this.parcel.dataPosition();
        int[] createIntArray = this.parcel.createIntArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createIntArray;
    }

    public long[] createLongArray(int i, long[] jArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return jArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new long[0];
        }
        int dataPosition = this.parcel.dataPosition();
        long[] createLongArray = this.parcel.createLongArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createLongArray;
    }

    public BigInteger[] createBigIntegerArray(int i, BigInteger[] bigIntegerArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return bigIntegerArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new BigInteger[0];
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        BigInteger[] bigIntegerArr2 = new BigInteger[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            bigIntegerArr2[i2] = new BigInteger(this.parcel.createByteArray());
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return bigIntegerArr2;
    }

    public float[] createFloatArray(int i, float[] fArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return fArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new float[0];
        }
        int dataPosition = this.parcel.dataPosition();
        float[] createFloatArray = this.parcel.createFloatArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createFloatArray;
    }

    public double[] createDoubleArray(int i, double[] dArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return dArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new double[0];
        }
        int dataPosition = this.parcel.dataPosition();
        double[] createDoubleArray = this.parcel.createDoubleArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createDoubleArray;
    }

    public BigDecimal[] createBigDecimalArray(int i, BigDecimal[] bigDecimalArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return bigDecimalArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new BigDecimal[0];
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        BigDecimal[] bigDecimalArr2 = new BigDecimal[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            byte[] createByteArray = this.parcel.createByteArray();
            bigDecimalArr2[i2] = new BigDecimal(new BigInteger(createByteArray), this.parcel.readInt());
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return bigDecimalArr2;
    }

    public String[] createStringArray(int i, String[] strArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return strArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new String[0];
        }
        int dataPosition = this.parcel.dataPosition();
        String[] createStringArray = this.parcel.createStringArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createStringArray;
    }

    public IBinder[] createIBinderArray(int i, IBinder[] iBinderArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return iBinderArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new IBinder[0];
        }
        int dataPosition = this.parcel.dataPosition();
        IBinder[] createBinderArray = this.parcel.createBinderArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createBinderArray;
    }

    public ArrayList<Boolean> createBooleanList(int i, ArrayList<Boolean> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        ArrayList<Boolean> arrayList2 = new ArrayList<>();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList2.add(Boolean.valueOf(this.parcel.readInt() != 0));
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return arrayList2;
    }

    public ArrayList<Integer> createIntegerList(int i, ArrayList<Integer> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList2.add(Integer.valueOf(this.parcel.readInt()));
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return arrayList2;
    }

    public SparseBooleanArray createSparseBooleanArray(int i, SparseBooleanArray sparseBooleanArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseBooleanArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        SparseBooleanArray readSparseBooleanArray = this.parcel.readSparseBooleanArray();
        this.parcel.setDataPosition(dataPosition + moveField);
        return readSparseBooleanArray;
    }

    public SparseIntArray createSparseIntArray(int i, SparseIntArray sparseIntArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseIntArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseIntArray2.append(this.parcel.readInt(), this.parcel.readInt());
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseIntArray2;
    }

    public SparseArray<Float> createFloatSparseArray(int i, SparseArray<Float> sparseArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        SparseArray<Float> sparseArray2 = new SparseArray<>();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseArray2.append(this.parcel.readInt(), Float.valueOf(this.parcel.readFloat()));
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseArray2;
    }

    public SparseArray<Double> createDoubleSparseArray(int i, SparseArray<Double> sparseArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        SparseArray<Double> sparseArray2 = new SparseArray<>();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseArray2.append(this.parcel.readInt(), Double.valueOf(this.parcel.readDouble()));
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseArray2;
    }

    public SparseLongArray createSparseLongArray(int i, SparseLongArray sparseLongArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseLongArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        SparseLongArray sparseLongArray2 = new SparseLongArray();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseLongArray2.append(this.parcel.readInt(), this.parcel.readLong());
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseLongArray2;
    }

    public SparseArray<String> createStringSparseArray(int i, SparseArray<String> sparseArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        SparseArray<String> sparseArray2 = new SparseArray<>();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseArray2.append(this.parcel.readInt(), this.parcel.readString());
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseArray2;
    }

    public SparseArray<Parcel> createParcelSparseArray(int i, SparseArray<Parcel> sparseArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        SparseArray<Parcel> sparseArray2 = new SparseArray<>();
        for (int i2 = 0; i2 < readInt; i2++) {
            int readInt2 = this.parcel.readInt();
            int readInt3 = this.parcel.readInt();
            if (readInt3 != 0) {
                int dataPosition2 = this.parcel.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(this.parcel, dataPosition2, readInt3);
                sparseArray2.append(readInt2, obtain);
                this.parcel.setDataPosition(dataPosition2 + readInt3);
            } else {
                sparseArray2.append(readInt2, null);
            }
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseArray2;
    }

    public <T> SparseArray<T> createTypedSparseArray(int i, Parcelable.Creator<T> creator, SparseArray<T> sparseArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        SparseArray<T> sparseArray2 = new SparseArray<>();
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseArray2.append(this.parcel.readInt(), this.parcel.readInt() != 0 ? creator.createFromParcel(this.parcel) : null);
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseArray2;
    }

    public SparseArray<IBinder> createIBinderSparseArray(int i, SparseArray<IBinder> sparseArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        SparseArray<IBinder> sparseArray2 = new SparseArray<>(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseArray2.append(this.parcel.readInt(), this.parcel.readStrongBinder());
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseArray2;
    }

    public SparseArray<byte[]> createByteArraySparseArray(int i, SparseArray<byte[]> sparseArray) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return sparseArray;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        SparseArray<byte[]> sparseArray2 = new SparseArray<>(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            sparseArray2.append(this.parcel.readInt(), this.parcel.createByteArray());
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return sparseArray2;
    }

    public ArrayList<Long> createLongList(int i, ArrayList<Long> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        ArrayList<Long> arrayList2 = new ArrayList<>();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList2.add(Long.valueOf(this.parcel.readLong()));
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return arrayList2;
    }

    public ArrayList<Float> createFloatList(int i, ArrayList<Float> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        ArrayList<Float> arrayList2 = new ArrayList<>();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList2.add(Float.valueOf(this.parcel.readFloat()));
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return arrayList2;
    }

    public ArrayList<Double> createDoubleList(int i, ArrayList<Double> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        ArrayList<Double> arrayList2 = new ArrayList<>();
        int readInt = this.parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList2.add(Double.valueOf(this.parcel.readDouble()));
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return arrayList2;
    }

    public ArrayList<String> createStringList(int i, ArrayList<String> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        ArrayList<String> createStringArrayList = this.parcel.createStringArrayList();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createStringArrayList;
    }

    public ArrayList<IBinder> createIBinderList(int i, ArrayList<IBinder> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        ArrayList<IBinder> createBinderArrayList = this.parcel.createBinderArrayList();
        this.parcel.setDataPosition(dataPosition + moveField);
        return createBinderArrayList;
    }

    public <T> T[] createTypedArray(int i, Parcelable.Creator<T> creator, T[] tArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return tArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        Object[] createTypedArray = this.parcel.createTypedArray(creator);
        this.parcel.setDataPosition(dataPosition + moveField);
        return (T[]) createTypedArray;
    }

    public <T> ArrayList<T> createTypedList(int i, Parcelable.Creator<T> creator, ArrayList<T> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        ArrayList<T> createTypedArrayList = this.parcel.createTypedArrayList(creator);
        this.parcel.setDataPosition(dataPosition + moveField);
        return createTypedArrayList;
    }

    public Parcel createParcel(int i, Parcel parcel) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return parcel;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        Parcel obtain = Parcel.obtain();
        obtain.appendFrom(this.parcel, dataPosition, moveField);
        this.parcel.setDataPosition(dataPosition + moveField);
        return obtain;
    }

    public Parcel[] createParcelArray(int i, Parcel[] parcelArr) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return parcelArr;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return new Parcel[0];
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        Parcel[] parcelArr2 = new Parcel[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            int readInt2 = this.parcel.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = this.parcel.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(this.parcel, dataPosition2, readInt2);
                parcelArr2[i2] = obtain;
                this.parcel.setDataPosition(dataPosition2 + readInt2);
            } else {
                parcelArr2[i2] = null;
            }
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return parcelArr2;
    }

    public ArrayList<Parcel> createParcelList(int i, ArrayList<Parcel> arrayList) {
        if (!this.mapFields.containsKey(Integer.valueOf(i))) {
            return arrayList;
        }
        int moveField = moveField(i);
        if (moveField == 0) {
            return null;
        }
        int dataPosition = this.parcel.dataPosition();
        int readInt = this.parcel.readInt();
        ArrayList<Parcel> arrayList2 = new ArrayList<>();
        for (int i2 = 0; i2 < readInt; i2++) {
            int readInt2 = this.parcel.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = this.parcel.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(this.parcel, dataPosition2, readInt2);
                arrayList2.add(obtain);
                this.parcel.setDataPosition(dataPosition2 + readInt2);
            } else {
                arrayList2.add(null);
            }
        }
        this.parcel.setDataPosition(dataPosition + moveField);
        return arrayList2;
    }

    public void readList(int i, List list, ClassLoader classLoader) {
        if (this.mapFields.containsKey(Integer.valueOf(i))) {
            int moveField = moveField(i);
            int dataPosition = this.parcel.dataPosition();
            if (moveField != 0) {
                this.parcel.readList(list, classLoader);
                this.parcel.setDataPosition(dataPosition + moveField);
            }
        }
    }

    public class ParseException extends RuntimeException {
        public ParseException(String str, Parcel parcel) {
            super(str);
        }
    }
}
