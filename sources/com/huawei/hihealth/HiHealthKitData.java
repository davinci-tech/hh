package com.huawei.hihealth;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class HiHealthKitData implements Parcelable {
    public static final int ARRAY_MAX_SIZE = 65535;
    public static final Parcelable.Creator<HiHealthKitData> CREATOR = new Parcelable.Creator<HiHealthKitData>() { // from class: com.huawei.hihealth.HiHealthKitData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buL_, reason: merged with bridge method [inline-methods] */
        public HiHealthKitData createFromParcel(Parcel parcel) {
            return new HiHealthKitData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HiHealthKitData[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new HiHealthKitData[i];
        }
    };
    private static final double DEFAULT_VALUE = -1.0d;
    private static final int MAP_INITIAL_CAPACITY = 16;
    private Map mMap;
    private int mType;
    private ContentValues mValueHolder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiHealthKitData() {
        this.mValueHolder = new ContentValues();
        this.mMap = new HashMap(16);
    }

    public HiHealthKitData(Parcel parcel) {
        this.mValueHolder = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
        this.mType = parcel.readInt();
        this.mMap = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mValueHolder, i);
        parcel.writeInt(this.mType);
        parcel.writeMap(this.mMap);
    }

    public void setType(int i) {
        this.mType = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setStartTime(long j) {
        this.mValueHolder.put("start_time", Long.valueOf(j));
    }

    public long getStartTime() {
        Long asLong = this.mValueHolder.getAsLong("start_time");
        if (asLong == null) {
            return 0L;
        }
        return asLong.longValue();
    }

    public void setEndTime(long j) {
        this.mValueHolder.put("end_time", Long.valueOf(j));
    }

    public long getUpdateTime() {
        Long asLong = this.mValueHolder.getAsLong("update_time");
        if (asLong == null) {
            return 0L;
        }
        return asLong.longValue();
    }

    public void setUpdateTime(long j) {
        this.mValueHolder.put("update_time", Long.valueOf(j));
    }

    public long getEndTime() {
        Long asLong = this.mValueHolder.getAsLong("end_time");
        if (asLong == null) {
            return 0L;
        }
        return asLong.longValue();
    }

    public void setValue(int i) {
        this.mValueHolder.put("point_value", Integer.valueOf(i));
    }

    public void setDoubleValue(Double d) {
        this.mValueHolder.put("point_value", d);
    }

    public void setMap(Map map) {
        this.mMap = map;
    }

    public Map getMap() {
        return this.mMap;
    }

    public void setContentValues(ContentValues contentValues) {
        if (contentValues != null) {
            this.mValueHolder = contentValues;
        }
    }

    public ContentValues getContentValue() {
        return this.mValueHolder;
    }

    public int getIntValue() {
        Integer asInteger = this.mValueHolder.getAsInteger("point_value");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public double getDoubleValue() {
        Double asDouble = this.mValueHolder.getAsDouble("point_value");
        if (asDouble == null) {
            return -1.0d;
        }
        return asDouble.doubleValue();
    }

    public final void putString(String str, String str2) {
        this.mValueHolder.put(str, str2);
    }

    public String getString(String str) {
        return this.mValueHolder.getAsString(str);
    }

    public void putDouble(String str, double d) {
        this.mValueHolder.put(str, Double.valueOf(d));
    }

    public double getDouble(String str) {
        Double asDouble = this.mValueHolder.getAsDouble(str);
        if (asDouble == null) {
            return 0.0d;
        }
        return asDouble.doubleValue();
    }

    public final void putFloat(String str, float f) {
        this.mValueHolder.put(str, Float.valueOf(f));
    }

    public float getFloat(String str) {
        Float asFloat = this.mValueHolder.getAsFloat(str);
        if (asFloat == null) {
            return 0.0f;
        }
        return asFloat.floatValue();
    }

    public void putInt(String str, int i) {
        this.mValueHolder.put(str, Integer.valueOf(i));
    }

    public int getInt(String str) {
        Integer asInteger = this.mValueHolder.getAsInteger(str);
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public void putLong(String str, long j) {
        this.mValueHolder.put(str, Long.valueOf(j));
    }

    public long getLong(String str) {
        Long asLong = this.mValueHolder.getAsLong(str);
        if (asLong == null) {
            return 0L;
        }
        return asLong.longValue();
    }

    public void putBoolean(String str, boolean z) {
        this.mValueHolder.put(str, Boolean.valueOf(z));
    }

    public boolean getBoolean(String str) {
        Boolean asBoolean = this.mValueHolder.getAsBoolean(str);
        if (asBoolean == null) {
            return false;
        }
        return asBoolean.booleanValue();
    }

    public void setTimeInterval(long j, long j2) {
        this.mValueHolder.put("start_time", Long.valueOf(j));
        this.mValueHolder.put("end_time", Long.valueOf(j2));
    }
}
