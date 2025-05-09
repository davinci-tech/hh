package com.huawei.secure.android.common.intent;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import com.huawei.secure.android.common.activity.a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/* loaded from: classes6.dex */
public class SafeBundle {
    private static final String b = "SafeBundle";
    private static final String c = "";

    /* renamed from: a, reason: collision with root package name */
    private final Bundle f8609a;

    public SafeBundle() {
        this(new Bundle());
    }

    public void clear() {
        try {
            this.f8609a.clear();
        } catch (Throwable unused) {
            a.a(b, "clear exception.");
        }
    }

    public boolean containsKey(String str) {
        try {
            return this.f8609a.containsKey(str);
        } catch (Throwable unused) {
            a.a(b, "containsKey exception. key:");
            return false;
        }
    }

    public Object get(String str) {
        try {
            return this.f8609a.get(str);
        } catch (Throwable th) {
            a.a(b, "get exception: " + th.getMessage(), true);
            return null;
        }
    }

    public IBinder getBinder(String str) {
        try {
            return this.f8609a.getBinder(str);
        } catch (Throwable th) {
            a.a(b, "getBinder exception: " + th.getMessage(), true);
            return null;
        }
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean[] getBooleanArray(String str) {
        try {
            return this.f8609a.getBooleanArray(str);
        } catch (Throwable th) {
            a.a(b, "getBooleanArray exception: " + th.getMessage(), true);
            return new boolean[0];
        }
    }

    public boolean[] getBooleanArrayReturnNotNull(String str) {
        try {
            boolean[] booleanArray = this.f8609a.getBooleanArray(str);
            return booleanArray == null ? new boolean[0] : booleanArray;
        } catch (Throwable th) {
            a.a(b, "getBooleanArray exception: " + th.getMessage(), true);
            return new boolean[0];
        }
    }

    public Bundle getBundle() {
        return this.f8609a;
    }

    public Bundle getBundleReturnNotNull(String str) {
        try {
            Bundle bundle = this.f8609a.getBundle(str);
            return bundle == null ? new Bundle() : bundle;
        } catch (Throwable th) {
            a.a(b, "getBundle exception: " + th.getMessage(), true);
            return new Bundle();
        }
    }

    public byte getByte(String str) {
        try {
            return this.f8609a.getByte(str);
        } catch (Throwable unused) {
            return (byte) 0;
        }
    }

    public byte[] getByteArray(String str) {
        try {
            return this.f8609a.getByteArray(str);
        } catch (Throwable th) {
            a.a(b, "getByteArray exception: " + th.getMessage(), true);
            return new byte[0];
        }
    }

    public byte[] getByteArrayReturnNotNull(String str) {
        try {
            byte[] byteArray = this.f8609a.getByteArray(str);
            return byteArray == null ? new byte[0] : byteArray;
        } catch (Throwable th) {
            a.a(b, "getByteArray exception: " + th.getMessage(), true);
            return new byte[0];
        }
    }

    public char getChar(String str) {
        try {
            return this.f8609a.getChar(str);
        } catch (Throwable unused) {
            return (char) 0;
        }
    }

    public char[] getCharArray(String str) {
        try {
            return this.f8609a.getCharArray(str);
        } catch (Throwable th) {
            a.a(b, "getCharArray exception: " + th.getMessage(), true);
            return new char[0];
        }
    }

    public char[] getCharArrayReturnNotNull(String str) {
        try {
            char[] charArray = this.f8609a.getCharArray(str);
            return charArray == null ? new char[0] : charArray;
        } catch (Throwable th) {
            a.a(b, "getCharArray exception: " + th.getMessage(), true);
            return new char[0];
        }
    }

    public CharSequence getCharSequence(String str) {
        try {
            return this.f8609a.getCharSequence(str);
        } catch (Throwable unused) {
            return "";
        }
    }

    public CharSequence[] getCharSequenceArray(String str) {
        try {
            return this.f8609a.getCharSequenceArray(str);
        } catch (Throwable th) {
            a.a(b, "getCharSequenceArray exception: " + th.getMessage(), true);
            return new CharSequence[0];
        }
    }

    public ArrayList<CharSequence> getCharSequenceArrayList(String str) {
        try {
            return this.f8609a.getCharSequenceArrayList(str);
        } catch (Throwable th) {
            a.a(b, "getCharSequenceArrayList exception: " + th.getMessage(), true);
            return new ArrayList<>();
        }
    }

    public ArrayList<CharSequence> getCharSequenceArrayListReturnNotNull(String str) {
        try {
            ArrayList<CharSequence> charSequenceArrayList = this.f8609a.getCharSequenceArrayList(str);
            return charSequenceArrayList == null ? new ArrayList<>() : charSequenceArrayList;
        } catch (Throwable th) {
            a.a(b, "getCharSequenceArrayList exception: " + th.getMessage(), true);
            return new ArrayList<>();
        }
    }

    public CharSequence[] getCharSequenceArrayReturnNotNull(String str) {
        try {
            CharSequence[] charSequenceArray = this.f8609a.getCharSequenceArray(str);
            return charSequenceArray == null ? new CharSequence[0] : charSequenceArray;
        } catch (Throwable th) {
            a.a(b, "getCharSequenceArrayReturnNotNull exception: " + th.getMessage(), true);
            return new CharSequence[0];
        }
    }

    public CharSequence getCharSequenceReturnNotNull(String str) {
        CharSequence charSequence;
        try {
            charSequence = this.f8609a.getCharSequence(str);
        } catch (Throwable th) {
            a.a(b, "getCharSequenceReturnNotNull exception: " + th.getMessage(), true);
            charSequence = "";
        }
        return TextUtils.isEmpty(charSequence) ? "" : charSequence;
    }

    public double getDouble(String str) {
        return getDouble(str, 0.0d);
    }

    public double[] getDoubleArray(String str) {
        try {
            return this.f8609a.getDoubleArray(str);
        } catch (Throwable th) {
            a.a(b, "getDoubleArray exception: " + th.getMessage(), true);
            return new double[0];
        }
    }

    public double[] getDoubleArrayReturnNotNull(String str) {
        try {
            double[] doubleArray = this.f8609a.getDoubleArray(str);
            return doubleArray == null ? new double[0] : doubleArray;
        } catch (Throwable th) {
            a.a(b, "getDoubleArray exception: " + th.getMessage(), true);
            return new double[0];
        }
    }

    public float getFloat(String str) {
        return getFloat(str, 0.0f);
    }

    public float[] getFloatArray(String str) {
        try {
            return this.f8609a.getFloatArray(str);
        } catch (Throwable th) {
            a.a(b, "getFloatArray exception: " + th.getMessage(), true);
            return new float[0];
        }
    }

    public float[] getFloatArrayReturnNotNull(String str) {
        try {
            float[] floatArray = this.f8609a.getFloatArray(str);
            return floatArray == null ? new float[0] : floatArray;
        } catch (Throwable th) {
            a.a(b, "getFloatArray exception: " + th.getMessage(), true);
            return new float[0];
        }
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public int[] getIntArray(String str) {
        try {
            return this.f8609a.getIntArray(str);
        } catch (Throwable th) {
            a.a(b, "getIntArray exception: " + th.getMessage(), true);
            return new int[0];
        }
    }

    public int[] getIntArrayReturnNotNull(String str) {
        try {
            int[] intArray = this.f8609a.getIntArray(str);
            return intArray == null ? new int[0] : intArray;
        } catch (Throwable th) {
            a.a(b, "getIntArray exception: " + th.getMessage(), true);
            return new int[0];
        }
    }

    public ArrayList<Integer> getIntegerArrayList(String str) {
        try {
            return this.f8609a.getIntegerArrayList(str);
        } catch (Throwable th) {
            a.a(b, "getIntegerArrayList exception: " + th.getMessage(), true);
            return new ArrayList<>();
        }
    }

    public ArrayList<Integer> getIntegerArrayListReturnNotNull(String str) {
        try {
            ArrayList<Integer> integerArrayList = this.f8609a.getIntegerArrayList(str);
            return integerArrayList == null ? new ArrayList<>() : integerArrayList;
        } catch (Throwable th) {
            a.a(b, "getIntegerArrayList exception: " + th.getMessage(), true);
            return new ArrayList<>();
        }
    }

    public long getLong(String str) {
        return getLong(str, 0L);
    }

    public long[] getLongArray(String str) {
        try {
            return this.f8609a.getLongArray(str);
        } catch (Throwable th) {
            a.a(b, "getLongArray exception: " + th.getMessage(), true);
            return new long[0];
        }
    }

    public long[] getLongArrayReturnNotNull(String str) {
        try {
            long[] longArray = this.f8609a.getLongArray(str);
            return longArray == null ? new long[0] : longArray;
        } catch (Throwable th) {
            a.a(b, "getLongArray exception: " + th.getMessage(), true);
            return new long[0];
        }
    }

    public <T extends Parcelable> T getParcelable(String str) {
        try {
            return (T) this.f8609a.getParcelable(str);
        } catch (Throwable th) {
            a.a(b, "getParcelable exception: " + th.getMessage(), true);
            return null;
        }
    }

    public Parcelable[] getParcelableArray(String str) {
        try {
            return this.f8609a.getParcelableArray(str);
        } catch (Throwable th) {
            a.a(b, "getParcelableArray exception: " + th.getMessage(), true);
            return new Parcelable[0];
        }
    }

    public <T extends Parcelable> ArrayList<T> getParcelableArrayList(String str) {
        try {
            return this.f8609a.getParcelableArrayList(str);
        } catch (Throwable th) {
            a.a(b, "getParcelableArrayList exception: " + th.getMessage(), true);
            return null;
        }
    }

    public Parcelable[] getParcelableArrayReturnNotNull(String str) {
        try {
            Parcelable[] parcelableArray = this.f8609a.getParcelableArray(str);
            return parcelableArray == null ? new Parcelable[0] : parcelableArray;
        } catch (Throwable th) {
            a.a(b, "getParcelableArray exception: " + th.getMessage(), true);
            return new Parcelable[0];
        }
    }

    public Object getReturnNotNull(String str) {
        try {
            Object obj = this.f8609a.get(str);
            return obj == null ? new Object() : obj;
        } catch (Throwable th) {
            a.a(b, "get exception: " + th.getMessage(), true);
            return new Object();
        }
    }

    public Serializable getSerializable(String str) {
        try {
            return this.f8609a.getSerializable(str);
        } catch (Throwable th) {
            a.a(b, "getSerializable exception: " + th.getMessage(), true);
            return null;
        }
    }

    public short getShort(String str) {
        try {
            return this.f8609a.getShort(str);
        } catch (Throwable unused) {
            return (short) 0;
        }
    }

    public short[] getShortArray(String str) {
        try {
            return this.f8609a.getShortArray(str);
        } catch (Throwable th) {
            a.a(b, "getShortArray exception: " + th.getMessage(), true);
            return new short[0];
        }
    }

    public short[] getShortArrayReturnNotNull(String str) {
        try {
            short[] shortArray = this.f8609a.getShortArray(str);
            return shortArray == null ? new short[0] : shortArray;
        } catch (Throwable th) {
            a.a(b, "getShortArray exception: " + th.getMessage(), true);
            return new short[0];
        }
    }

    public <T extends Parcelable> SparseArray<T> getSparseParcelableArray(String str) {
        try {
            return this.f8609a.getSparseParcelableArray(str);
        } catch (Throwable th) {
            a.a(b, "getSparseParcelableArray exception: " + th.getMessage(), true);
            return null;
        }
    }

    public String getString(String str) {
        try {
            return this.f8609a.getString(str);
        } catch (Throwable th) {
            a.a(b, "getString exception: " + th.getMessage(), true);
            return "";
        }
    }

    public String[] getStringArray(String str) {
        try {
            return this.f8609a.getStringArray(str);
        } catch (Throwable th) {
            a.a(b, "getStringArray exception: " + th.getMessage(), true);
            return new String[0];
        }
    }

    public ArrayList<String> getStringArrayList(String str) {
        try {
            return this.f8609a.getStringArrayList(str);
        } catch (Throwable th) {
            a.a(b, "getStringArrayList exception: " + th.getMessage(), true);
            return new ArrayList<>();
        }
    }

    public ArrayList<String> getStringArrayListReturnNotNull(String str) {
        try {
            ArrayList<String> stringArrayList = this.f8609a.getStringArrayList(str);
            return stringArrayList == null ? new ArrayList<>() : stringArrayList;
        } catch (Throwable th) {
            a.a(b, "getStringArrayList exception: " + th.getMessage(), true);
            return new ArrayList<>();
        }
    }

    public String[] getStringArrayReturnNotNull(String str) {
        try {
            String[] stringArray = this.f8609a.getStringArray(str);
            return stringArray == null ? new String[0] : stringArray;
        } catch (Throwable th) {
            a.a(b, "getStringArray exception: " + th.getMessage(), true);
            return new String[0];
        }
    }

    public String getStringReturnNotNull(String str) {
        String str2;
        try {
            str2 = this.f8609a.getString(str);
        } catch (Throwable th) {
            a.a(b, "getString exception: " + th.getMessage(), true);
            str2 = "";
        }
        return TextUtils.isEmpty(str2) ? "" : str2;
    }

    public boolean isEmpty() {
        try {
            return this.f8609a.isEmpty();
        } catch (Throwable unused) {
            a.a(b, "isEmpty exception");
            return true;
        }
    }

    public Set<String> keySet() {
        try {
            return this.f8609a.keySet();
        } catch (Throwable unused) {
            a.a(b, "keySet exception.");
            return null;
        }
    }

    public SafeBundle putAll(Bundle bundle) {
        if (bundle != null) {
            try {
                this.f8609a.putAll(bundle);
            } catch (Throwable unused) {
                a.a(b, "putAll exception");
            }
        }
        return this;
    }

    public SafeBundle putBinder(String str, IBinder iBinder) {
        try {
            this.f8609a.putBinder(str, iBinder);
        } catch (Throwable th) {
            a.a(b, "putBundle exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putBoolean(String str, boolean z) {
        try {
            this.f8609a.putBoolean(str, z);
        } catch (Throwable th) {
            a.a(b, "putBoolean exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putBooleanArray(String str, boolean[] zArr) {
        try {
            this.f8609a.putBooleanArray(str, zArr);
        } catch (Throwable th) {
            a.a(b, "putBooleanArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putBundle(String str, Bundle bundle) {
        try {
            this.f8609a.putBundle(str, bundle);
        } catch (Throwable th) {
            a.a(b, "putBundle exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putByte(String str, byte b2) {
        try {
            this.f8609a.putByte(str, b2);
        } catch (Throwable th) {
            a.a(b, "putByte exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putByteArray(String str, byte[] bArr) {
        try {
            this.f8609a.putByteArray(str, bArr);
        } catch (Throwable th) {
            a.a(b, "putByteArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putChar(String str, char c2) {
        try {
            this.f8609a.putChar(str, c2);
        } catch (Throwable th) {
            a.a(b, "putChar exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putCharArray(String str, char[] cArr) {
        try {
            this.f8609a.putCharArray(str, cArr);
        } catch (Throwable th) {
            a.a(b, "putCharArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putCharSequence(String str, CharSequence charSequence) {
        try {
            this.f8609a.putCharSequence(str, charSequence);
        } catch (Throwable th) {
            a.a(b, "putCharSequence exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putCharSequenceArray(String str, CharSequence[] charSequenceArr) {
        try {
            this.f8609a.putCharSequenceArray(str, charSequenceArr);
        } catch (Throwable th) {
            a.a(b, "putCharSequenceArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putCharSequenceArrayList(String str, ArrayList<CharSequence> arrayList) {
        try {
            this.f8609a.putCharSequenceArrayList(str, arrayList);
        } catch (Throwable th) {
            a.a(b, "putCharSequenceArrayList exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putDouble(String str, double d) {
        try {
            this.f8609a.putDouble(str, d);
        } catch (Throwable th) {
            a.a(b, "putDouble exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putDoubleArray(String str, double[] dArr) {
        try {
            this.f8609a.putDoubleArray(str, dArr);
        } catch (Throwable th) {
            a.a(b, "putDoubleArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putFloat(String str, float f) {
        try {
            this.f8609a.putFloat(str, f);
        } catch (Throwable th) {
            a.a(b, "putFloat exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putFloatArray(String str, float[] fArr) {
        try {
            this.f8609a.putFloatArray(str, fArr);
        } catch (Throwable th) {
            a.a(b, "putFloatArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putInt(String str, int i) {
        try {
            this.f8609a.putInt(str, i);
        } catch (Throwable th) {
            a.a(b, "putInt exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putIntArray(String str, int[] iArr) {
        try {
            this.f8609a.putIntArray(str, iArr);
        } catch (Throwable th) {
            a.a(b, "putIntArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putIntegerArrayList(String str, ArrayList<Integer> arrayList) {
        try {
            this.f8609a.putIntegerArrayList(str, arrayList);
        } catch (Throwable th) {
            a.a(b, "putIntegerArrayList exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putLong(String str, long j) {
        try {
            this.f8609a.putLong(str, j);
        } catch (Throwable th) {
            a.a(b, "putLong exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putLongArray(String str, long[] jArr) {
        try {
            this.f8609a.putLongArray(str, jArr);
        } catch (Throwable th) {
            a.a(b, "putLongArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putParcelable(String str, Parcelable parcelable) {
        try {
            this.f8609a.putParcelable(str, parcelable);
        } catch (Throwable th) {
            a.a(b, "putParcelable exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putParcelableArray(String str, Parcelable[] parcelableArr) {
        try {
            this.f8609a.putParcelableArray(str, parcelableArr);
        } catch (Throwable th) {
            a.a(b, "putParcelableArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putParcelableArrayList(String str, ArrayList<? extends Parcelable> arrayList) {
        try {
            this.f8609a.putParcelableArrayList(str, arrayList);
        } catch (Throwable th) {
            a.a(b, "putParcelableArrayList exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putSerializable(String str, Serializable serializable) {
        try {
            this.f8609a.putSerializable(str, serializable);
        } catch (Throwable th) {
            a.a(b, "putSerializable exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putShort(String str, short s) {
        try {
            this.f8609a.putShort(str, s);
        } catch (Throwable th) {
            a.a(b, "putShort exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putShortArray(String str, short[] sArr) {
        try {
            this.f8609a.putShortArray(str, sArr);
        } catch (Throwable th) {
            a.a(b, "putShortArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putSize(String str, Size size) {
        try {
            this.f8609a.putSize(str, size);
        } catch (Throwable th) {
            a.a(b, "putSize exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putSizeF(String str, SizeF sizeF) {
        try {
            this.f8609a.putSizeF(str, sizeF);
        } catch (Throwable th) {
            a.a(b, "putSizeF exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putSparseParcelableArray(String str, SparseArray<? extends Parcelable> sparseArray) {
        try {
            this.f8609a.putSparseParcelableArray(str, sparseArray);
        } catch (Throwable th) {
            a.a(b, "putSparseParcelableArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putString(String str, String str2) {
        try {
            this.f8609a.putString(str, str2);
        } catch (Throwable th) {
            a.a(b, "putString exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putStringArray(String str, String[] strArr) {
        try {
            this.f8609a.putStringArray(str, strArr);
        } catch (Throwable th) {
            a.a(b, "putStringArray exception: " + th.getMessage(), true);
        }
        return this;
    }

    public SafeBundle putStringArrayList(String str, ArrayList<String> arrayList) {
        try {
            this.f8609a.putStringArrayList(str, arrayList);
        } catch (Throwable th) {
            a.a(b, "putStringArrayList exception: " + th.getMessage(), true);
        }
        return this;
    }

    public void remove(String str) {
        try {
            this.f8609a.remove(str);
        } catch (Throwable unused) {
            a.a(b, "remove exception. key:");
        }
    }

    public int size() {
        try {
            return this.f8609a.size();
        } catch (Throwable unused) {
            a.a(b, "size exception");
            return 0;
        }
    }

    public String toString() {
        try {
            return this.f8609a.toString();
        } catch (Throwable unused) {
            a.a(b, "toString exception.");
            return null;
        }
    }

    public SafeBundle(Bundle bundle) {
        this.f8609a = bundle == null ? new Bundle() : bundle;
    }

    public boolean getBoolean(String str, boolean z) {
        try {
            return this.f8609a.getBoolean(str, z);
        } catch (Throwable th) {
            a.a(b, "getBoolean exception : " + th.getMessage(), true);
            return z;
        }
    }

    public Bundle getBundle(String str) {
        try {
            return this.f8609a.getBundle(str);
        } catch (Throwable th) {
            a.a(b, "getBundle exception: " + th.getMessage(), true);
            return null;
        }
    }

    public byte getByte(String str, byte b2) {
        try {
            return this.f8609a.getByte(str, b2).byteValue();
        } catch (Throwable th) {
            a.a(b, "getByte exception: " + th.getMessage(), true);
            return b2;
        }
    }

    public char getChar(String str, char c2) {
        try {
            return this.f8609a.getChar(str, c2);
        } catch (Throwable th) {
            a.a(b, "getChar exception: " + th.getMessage(), true);
            return c2;
        }
    }

    public CharSequence getCharSequence(String str, CharSequence charSequence) {
        try {
            return this.f8609a.getCharSequence(str, charSequence);
        } catch (Throwable th) {
            a.a(b, "getCharSequence exception: " + th.getMessage(), true);
            return charSequence;
        }
    }

    public double getDouble(String str, double d) {
        try {
            return this.f8609a.getDouble(str, d);
        } catch (Throwable th) {
            a.a(b, "getDouble exception: " + th.getMessage(), true);
            return d;
        }
    }

    public float getFloat(String str, float f) {
        try {
            return this.f8609a.getFloat(str, f);
        } catch (Throwable th) {
            a.a(b, "getFloat exception: " + th.getMessage(), true);
            return f;
        }
    }

    public int getInt(String str, int i) {
        try {
            return this.f8609a.getInt(str, i);
        } catch (Throwable th) {
            a.a(b, "getInt exception: " + th.getMessage(), true);
            return i;
        }
    }

    public long getLong(String str, long j) {
        try {
            return this.f8609a.getLong(str, j);
        } catch (Throwable th) {
            a.a(b, "getLong exception: " + th.getMessage(), true);
            return j;
        }
    }

    public short getShort(String str, short s) {
        try {
            return this.f8609a.getShort(str, s);
        } catch (Throwable th) {
            a.a(b, "getShort exception: " + th.getMessage(), true);
            return s;
        }
    }

    public Size getSize(String str) {
        try {
            return this.f8609a.getSize(str);
        } catch (Throwable th) {
            a.a(b, "getSize exception: " + th.getMessage(), true);
            return null;
        }
    }

    public SizeF getSizeF(String str) {
        try {
            return this.f8609a.getSizeF(str);
        } catch (Throwable th) {
            a.a(b, "getSizeF exception: " + th.getMessage(), true);
            return null;
        }
    }

    public <T extends Parcelable> T getParcelable(String str, Class<T> cls) {
        try {
            Parcelable parcelable = this.f8609a.getParcelable(str);
            if (cls.isInstance(parcelable)) {
                return cls.cast(parcelable);
            }
            return null;
        } catch (Throwable th) {
            a.a(b, "getParcelable exception: " + th.getMessage(), true);
            return null;
        }
    }

    public <T extends Serializable> T getSerializable(String str, Class<T> cls) {
        try {
            Serializable serializable = this.f8609a.getSerializable(str);
            if (cls.isInstance(serializable)) {
                return cls.cast(serializable);
            }
            return null;
        } catch (Throwable th) {
            a.a(b, "getSerializable exception: " + th.getMessage(), true);
            return null;
        }
    }

    public String getString(String str, String str2) {
        try {
            return this.f8609a.getString(str, str2);
        } catch (Throwable th) {
            a.a(b, "getString exception: " + th.getMessage(), true);
            return str2;
        }
    }

    public CharSequence getCharSequenceReturnNotNull(String str, CharSequence charSequence) {
        try {
            CharSequence charSequence2 = this.f8609a.getCharSequence(str, charSequence);
            return charSequence2 == null ? "" : charSequence2;
        } catch (Throwable th) {
            a.a(b, "getCharSequence exception: " + th.getMessage(), true);
            return charSequence;
        }
    }

    public String getStringReturnNotNull(String str, String str2) {
        try {
            String string = this.f8609a.getString(str, str2);
            return string == null ? str2 : string;
        } catch (Throwable th) {
            a.a(b, "getString exception: " + th.getMessage(), true);
            return str2;
        }
    }
}
