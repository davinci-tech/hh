package com.google.android.gms.wearable;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/* loaded from: classes2.dex */
public class DataMap {
    public static final String TAG = "DataMap";
    private final HashMap<String, Object> zzq = new HashMap<>();

    public static DataMap fromBundle(Bundle bundle) {
        bundle.setClassLoader(Asset.class.getClassLoader());
        DataMap dataMap = new DataMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                dataMap.putString(str, (String) obj);
            } else if (obj instanceof Integer) {
                dataMap.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                dataMap.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                dataMap.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof Float) {
                dataMap.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Boolean) {
                dataMap.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                dataMap.putByte(str, ((Byte) obj).byteValue());
            } else if (obj instanceof byte[]) {
                dataMap.putByteArray(str, (byte[]) obj);
            } else if (obj instanceof String[]) {
                dataMap.putStringArray(str, (String[]) obj);
            } else if (obj instanceof long[]) {
                dataMap.putLongArray(str, (long[]) obj);
            } else if (obj instanceof float[]) {
                dataMap.putFloatArray(str, (float[]) obj);
            } else if (obj instanceof Asset) {
                dataMap.putAsset(str, (Asset) obj);
            } else if (obj instanceof Bundle) {
                dataMap.putDataMap(str, fromBundle((Bundle) obj));
            } else if (obj instanceof ArrayList) {
                ArrayList<String> arrayList = (ArrayList) obj;
                int zza = zza(arrayList);
                if (zza == 0) {
                    dataMap.putStringArrayList(str, arrayList);
                } else if (zza == 1) {
                    dataMap.putStringArrayList(str, arrayList);
                } else if (zza == 2) {
                    dataMap.putIntegerArrayList(str, arrayList);
                } else if (zza == 3) {
                    dataMap.putStringArrayList(str, arrayList);
                } else if (zza == 5) {
                    dataMap.putDataMapArrayList(str, arrayListFromBundleArrayList(arrayList));
                }
            }
        }
        return dataMap;
    }

    public static DataMap fromByteArray(byte[] bArr) {
        try {
            return com.google.android.gms.internal.wearable.zze.zza(new com.google.android.gms.internal.wearable.zzf(com.google.android.gms.internal.wearable.zzg.zza(bArr), new ArrayList()));
        } catch (com.google.android.gms.internal.wearable.zzs e) {
            throw new IllegalArgumentException("Unable to convert data", e);
        }
    }

    public static ArrayList<DataMap> arrayListFromBundleArrayList(ArrayList<Bundle> arrayList) {
        ArrayList<DataMap> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Bundle bundle = arrayList.get(i);
            i++;
            arrayList2.add(fromBundle(bundle));
        }
        return arrayList2;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        for (String str : this.zzq.keySet()) {
            Object obj = this.zzq.get(str);
            if (obj instanceof String) {
                bundle.putString(str, (String) obj);
            } else if (obj instanceof Integer) {
                bundle.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof Float) {
                bundle.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Boolean) {
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                bundle.putByte(str, ((Byte) obj).byteValue());
            } else if (obj instanceof byte[]) {
                bundle.putByteArray(str, (byte[]) obj);
            } else if (obj instanceof String[]) {
                bundle.putStringArray(str, (String[]) obj);
            } else if (obj instanceof long[]) {
                bundle.putLongArray(str, (long[]) obj);
            } else if (obj instanceof float[]) {
                bundle.putFloatArray(str, (float[]) obj);
            } else if (obj instanceof Asset) {
                bundle.putParcelable(str, (Asset) obj);
            } else if (obj instanceof DataMap) {
                bundle.putParcelable(str, ((DataMap) obj).toBundle());
            } else if (obj instanceof ArrayList) {
                ArrayList<String> arrayList = (ArrayList) obj;
                int zza = zza(arrayList);
                if (zza == 0) {
                    bundle.putStringArrayList(str, arrayList);
                } else if (zza == 1) {
                    bundle.putStringArrayList(str, arrayList);
                } else if (zza == 2) {
                    bundle.putIntegerArrayList(str, arrayList);
                } else if (zza == 3) {
                    bundle.putStringArrayList(str, arrayList);
                } else if (zza == 4) {
                    ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        Object obj2 = arrayList.get(i);
                        i++;
                        arrayList2.add(((DataMap) obj2).toBundle());
                    }
                    bundle.putParcelableArrayList(str, arrayList2);
                }
            }
        }
        return bundle;
    }

    public byte[] toByteArray() {
        return com.google.android.gms.internal.wearable.zzt.zzb(com.google.android.gms.internal.wearable.zze.zza(this).zzfw);
    }

    public int size() {
        return this.zzq.size();
    }

    public boolean isEmpty() {
        return this.zzq.isEmpty();
    }

    public void clear() {
        this.zzq.clear();
    }

    public boolean containsKey(String str) {
        return this.zzq.containsKey(str);
    }

    public <T> T get(String str) {
        return (T) this.zzq.get(str);
    }

    public Object remove(String str) {
        return this.zzq.remove(str);
    }

    public void putAll(DataMap dataMap) {
        for (String str : dataMap.keySet()) {
            this.zzq.put(str, dataMap.get(str));
        }
    }

    public Set<String> keySet() {
        return this.zzq.keySet();
    }

    public void putBoolean(String str, boolean z) {
        this.zzq.put(str, Boolean.valueOf(z));
    }

    public void putByte(String str, byte b) {
        this.zzq.put(str, Byte.valueOf(b));
    }

    public void putInt(String str, int i) {
        this.zzq.put(str, Integer.valueOf(i));
    }

    public void putLong(String str, long j) {
        this.zzq.put(str, Long.valueOf(j));
    }

    public void putFloat(String str, float f) {
        this.zzq.put(str, Float.valueOf(f));
    }

    public void putDouble(String str, double d) {
        this.zzq.put(str, Double.valueOf(d));
    }

    public void putString(String str, String str2) {
        this.zzq.put(str, str2);
    }

    public void putAsset(String str, Asset asset) {
        this.zzq.put(str, asset);
    }

    public void putDataMap(String str, DataMap dataMap) {
        this.zzq.put(str, dataMap);
    }

    public void putDataMapArrayList(String str, ArrayList<DataMap> arrayList) {
        this.zzq.put(str, arrayList);
    }

    public void putIntegerArrayList(String str, ArrayList<Integer> arrayList) {
        this.zzq.put(str, arrayList);
    }

    public void putStringArrayList(String str, ArrayList<String> arrayList) {
        this.zzq.put(str, arrayList);
    }

    public void putByteArray(String str, byte[] bArr) {
        this.zzq.put(str, bArr);
    }

    public void putLongArray(String str, long[] jArr) {
        this.zzq.put(str, jArr);
    }

    public void putFloatArray(String str, float[] fArr) {
        this.zzq.put(str, fArr);
    }

    public void putStringArray(String str, String[] strArr) {
        this.zzq.put(str, strArr);
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return z;
        }
        try {
            return ((Boolean) obj).booleanValue();
        } catch (ClassCastException e) {
            zza(str, obj, "Boolean", Boolean.valueOf(z), e);
            return z;
        }
    }

    public byte getByte(String str) {
        return getByte(str, (byte) 0);
    }

    public byte getByte(String str, byte b) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return b;
        }
        try {
            return ((Byte) obj).byteValue();
        } catch (ClassCastException e) {
            zza(str, obj, "Byte", Byte.valueOf(b), e);
            return b;
        }
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public int getInt(String str, int i) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return i;
        }
        try {
            return ((Integer) obj).intValue();
        } catch (ClassCastException e) {
            zza(str, obj, "Integer", e);
            return i;
        }
    }

    public long getLong(String str) {
        return getLong(str, 0L);
    }

    public long getLong(String str, long j) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return j;
        }
        try {
            return ((Long) obj).longValue();
        } catch (ClassCastException e) {
            zza(str, obj, "long", e);
            return j;
        }
    }

    public float getFloat(String str) {
        return getFloat(str, 0.0f);
    }

    public float getFloat(String str, float f) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return f;
        }
        try {
            return ((Float) obj).floatValue();
        } catch (ClassCastException e) {
            zza(str, obj, "Float", Float.valueOf(f), e);
            return f;
        }
    }

    public double getDouble(String str) {
        return getDouble(str, 0.0d);
    }

    public double getDouble(String str, double d) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return d;
        }
        try {
            return ((Double) obj).doubleValue();
        } catch (ClassCastException e) {
            zza(str, obj, "Double", Double.valueOf(d), e);
            return d;
        }
    }

    public String getString(String str, String str2) {
        String string = getString(str);
        return string == null ? str2 : string;
    }

    public String getString(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (String) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "String", e);
            return null;
        }
    }

    public Asset getAsset(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (Asset) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "Asset", e);
            return null;
        }
    }

    public DataMap getDataMap(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (DataMap) obj;
        } catch (ClassCastException e) {
            zza(str, obj, TAG, e);
            return null;
        }
    }

    public ArrayList<Integer> getIntegerArrayList(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (ArrayList) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "ArrayList<Integer>", e);
            return null;
        }
    }

    public ArrayList<String> getStringArrayList(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (ArrayList) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "ArrayList<String>", e);
            return null;
        }
    }

    public ArrayList<DataMap> getDataMapArrayList(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (ArrayList) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "ArrayList<DataMap>", e);
            return null;
        }
    }

    public byte[] getByteArray(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (byte[]) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "byte[]", e);
            return null;
        }
    }

    public long[] getLongArray(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (long[]) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "long[]", e);
            return null;
        }
    }

    public float[] getFloatArray(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (float[]) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "float[]", e);
            return null;
        }
    }

    public String[] getStringArray(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (String[]) obj;
        } catch (ClassCastException e) {
            zza(str, obj, "String[]", e);
            return null;
        }
    }

    public boolean equals(Object obj) {
        boolean equals;
        if (!(obj instanceof DataMap)) {
            return false;
        }
        DataMap dataMap = (DataMap) obj;
        if (size() != dataMap.size()) {
            return false;
        }
        for (String str : keySet()) {
            Object obj2 = get(str);
            Object obj3 = dataMap.get(str);
            if (obj2 instanceof Asset) {
                if (!(obj3 instanceof Asset)) {
                    return false;
                }
                Asset asset = (Asset) obj2;
                Asset asset2 = (Asset) obj3;
                if (asset == null || asset2 == null) {
                    if (asset != asset2) {
                        return false;
                    }
                } else {
                    if (!TextUtils.isEmpty(asset.getDigest())) {
                        equals = asset.getDigest().equals(asset2.getDigest());
                    } else {
                        equals = Arrays.equals(asset.getData(), asset2.getData());
                    }
                    if (!equals) {
                        return false;
                    }
                }
            } else if (obj2 instanceof String[]) {
                if (!(obj3 instanceof String[]) || !Arrays.equals((String[]) obj2, (String[]) obj3)) {
                    return false;
                }
            } else if (obj2 instanceof long[]) {
                if (!(obj3 instanceof long[]) || !Arrays.equals((long[]) obj2, (long[]) obj3)) {
                    return false;
                }
            } else if (obj2 instanceof float[]) {
                if (!(obj3 instanceof float[]) || !Arrays.equals((float[]) obj2, (float[]) obj3)) {
                    return false;
                }
            } else if (obj2 instanceof byte[]) {
                if (!(obj3 instanceof byte[]) || !Arrays.equals((byte[]) obj2, (byte[]) obj3)) {
                    return false;
                }
            } else {
                if (obj2 == null || obj3 == null) {
                    return obj2 == obj3;
                }
                if (!obj2.equals(obj3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        return this.zzq.hashCode() * 29;
    }

    public String toString() {
        return this.zzq.toString();
    }

    private static void zza(String str, Object obj, String str2, ClassCastException classCastException) {
        zza(str, obj, str2, "<null>", classCastException);
    }

    private static void zza(String str, Object obj, String str2, Object obj2, ClassCastException classCastException) {
        Log.w(TAG, "Key " + str + " expected " + str2 + " but value was a " + obj.getClass().getName() + ".  The default value " + obj2 + " was returned.");
        Log.w(TAG, "Attempt to cast generated internal exception:", classCastException);
    }

    private static int zza(ArrayList<?> arrayList) {
        int i = 0;
        if (arrayList.isEmpty()) {
            return 0;
        }
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            if (obj != null) {
                if (obj instanceof Integer) {
                    return 2;
                }
                if (obj instanceof String) {
                    return 3;
                }
                if (obj instanceof DataMap) {
                    return 4;
                }
                if (obj instanceof Bundle) {
                    return 5;
                }
            }
        }
        return 1;
    }
}
