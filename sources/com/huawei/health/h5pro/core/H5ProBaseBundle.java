package com.huawei.health.h5pro.core;

import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.huawei.health.h5pro.utils.LogUtil;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class H5ProBaseBundle {
    public static final int CAPACITY = 1;
    public static final String KEY_PARCEL_BUNDLE = "KEY_PARCEL_BUNDLE";
    public static final String TAG = "H5PRO_H5ProBaseBundle";
    public HashMap<String, Object> data;

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_PARCEL_BUNDLE, this.data);
        parcel.writeBundle(bundle);
    }

    public void readFromParcel(Parcel parcel, ClassLoader classLoader) {
        Bundle readBundle = parcel.readBundle(classLoader);
        if (readBundle == null) {
            return;
        }
        Serializable serializable = readBundle.getSerializable(KEY_PARCEL_BUNDLE);
        if (serializable instanceof HashMap) {
            this.data = (HashMap) serializable;
        }
    }

    public void putString(String str, String str2) {
        put(str, str2);
    }

    public void putLong(String str, long j) {
        put(str, Long.valueOf(j));
    }

    public void putInt(String str, int i) {
        put(str, Integer.valueOf(i));
    }

    public void putFloat(String str, float f) {
        put(str, Float.valueOf(f));
    }

    public void putDouble(String str, double d) {
        put(str, Double.valueOf(d));
    }

    public void putBoolean(String str, boolean z) {
        put(str, Boolean.valueOf(z));
    }

    public void putAll(H5ProBaseBundle h5ProBaseBundle) {
        Map<String, Object> data;
        if (h5ProBaseBundle == null || (data = h5ProBaseBundle.getData()) == null || data.isEmpty()) {
            return;
        }
        if (this.data == null) {
            this.data = new HashMap<>(data.size());
        }
        this.data.putAll(h5ProBaseBundle.data);
    }

    public String getString(String str, String str2) {
        try {
            return (String) get(str, str2);
        } catch (ClassCastException unused) {
            LogUtil.e(TAG, "getString: ClassCastException -> " + str);
            return str2;
        }
    }

    public String getString(String str) {
        return getString(str, "");
    }

    public long getLong(String str, long j) {
        try {
            return ((Long) get(str, Long.valueOf(j))).longValue();
        } catch (ClassCastException unused) {
            LogUtil.e(TAG, "getLong: ClassCastException -> " + str);
            return j;
        }
    }

    public long getLong(String str) {
        return getLong(str, 0L);
    }

    public int getInt(String str, int i) {
        try {
            return ((Integer) get(str, Integer.valueOf(i))).intValue();
        } catch (ClassCastException unused) {
            LogUtil.e(TAG, "getInt: ClassCastException -> " + str);
            return i;
        }
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public float getFloat(String str, float f) {
        try {
            return ((Float) get(str, Float.valueOf(f))).floatValue();
        } catch (ClassCastException unused) {
            LogUtil.e(TAG, "getFloat: ClassCastException -> " + str);
            return f;
        }
    }

    public float getFloat(String str) {
        return getFloat(str, 0.0f);
    }

    public double getDouble(String str, double d) {
        try {
            return ((Double) get(str, Double.valueOf(d))).doubleValue();
        } catch (ClassCastException unused) {
            LogUtil.e(TAG, "getDouble: ClassCastException -> " + str);
            return d;
        }
    }

    public double getDouble(String str) {
        return getDouble(str, 0.0d);
    }

    public Map<String, Object> getData() {
        HashMap<String, Object> hashMap = this.data;
        return (hashMap == null || hashMap.isEmpty()) ? Collections.emptyMap() : Collections.unmodifiableMap(this.data);
    }

    public boolean getBoolean(String str, boolean z) {
        try {
            return ((Boolean) get(str, Boolean.valueOf(z))).booleanValue();
        } catch (ClassCastException unused) {
            LogUtil.e(TAG, "getBoolean: ClassCastException -> " + str);
            return z;
        }
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    private <T> void put(String str, T t) {
        if (isValid(str)) {
            this.data.put(str, t);
        }
    }

    private boolean isValid(String str) {
        boolean isEmpty = TextUtils.isEmpty(str);
        if (!isEmpty && this.data == null) {
            this.data = new HashMap<>(1);
        }
        return !isEmpty;
    }

    private <T> T get(String str, T t) {
        return isValid(str) ? (T) this.data.get(str) : t;
    }
}
