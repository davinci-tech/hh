package com.huawei.hms.hihealth.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabs;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aacc;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes9.dex */
public final class Value extends aabq {
    public static final Parcelable.Creator<Value> CREATOR = new aabq.aab(Value.class);
    public static final double DOUBLE_DEFAULT_VALUE_FROM_PARCLE = -1.0d;
    private static final double DOUBLE_MARGIN = 1.0E-6d;
    private static final String MISMATCHED = "Mismatched format";
    private static final String MISMATCHED_FORMAT = "Mismatched format, Please check the data type definition.";
    private static final int RADIX = 10;
    private static final String TAG = "VALUE";
    private static final String VALUE_PARSE_ERROR = "value parse error, Please check the data type definition.";

    @aaby(id = 6)
    private double doubleValue;

    @aaby(id = 4)
    @Deprecated
    private float floatValue;

    @aaby(id = 1)
    private int format;

    @aaby(id = 2)
    private boolean hasSet;

    @aaby(id = 5)
    private Map<String, MapValue> mapValue;

    @aaby(id = 3)
    private String stringValue;

    @Override // com.huawei.hms.health.aabq, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        StringBuilder aab = com.huawei.hms.health.aab.aab("writeValueToParcel begin of ");
        aab.append(Value.class.getName());
        aab.toString();
        if (this.format == 2) {
            if (this.floatValue == 0.0f && this.hasSet) {
                this.floatValue = (float) this.doubleValue;
            }
            float f = this.floatValue;
            if (f != 0.0f) {
                double d = this.doubleValue;
                if (d == 0.0d || d == -1.0d) {
                    this.doubleValue = Double.parseDouble(String.valueOf(f));
                }
            }
        }
        aabs.aab(this, parcel, i);
    }

    public String toString() {
        if (!this.hasSet) {
            return "Value has not set";
        }
        int i = this.format;
        if (i == 1) {
            return String.valueOf(asIntValue());
        }
        if (i == 2) {
            double d = this.doubleValue;
            return d != 0.0d ? String.valueOf(d) : String.valueOf(this.floatValue);
        }
        if (i != 3) {
            if (i == 4) {
                return new TreeMap(this.mapValue).toString();
            }
            if (i != 5) {
                return "Unidentified type";
            }
        }
        return this.stringValue;
    }

    public void setStringValue(String str) {
        if (this.format == 0) {
            this.format = 3;
        }
        Preconditions.checkState(this.format == 3, MISMATCHED_FORMAT);
        this.stringValue = str;
        this.hasSet = true;
    }

    public void setLongValue(long j) {
        if (this.format == 0) {
            this.format = 5;
        }
        Preconditions.checkState(this.format == 5, MISMATCHED_FORMAT);
        this.stringValue = String.valueOf(j);
        this.hasSet = true;
    }

    public void setKeyValue(String str, String str2) {
        if (this.format == 0) {
            this.format = 4;
        }
        Preconditions.checkState(this.format == 4, MISMATCHED_FORMAT);
        if (this.mapValue == null) {
            this.mapValue = new HashMap();
        }
        this.mapValue.put(str, new MapValue(3, str2));
        this.hasSet = true;
    }

    public void setKeyValue(String str, long j) {
        if (this.format == 0) {
            this.format = 4;
        }
        Preconditions.checkState(this.format == 4, MISMATCHED_FORMAT);
        if (this.mapValue == null) {
            this.mapValue = new HashMap();
        }
        this.mapValue.put(str, new MapValue(5, j));
        this.hasSet = true;
    }

    public void setKeyValue(String str, int i) {
        if (this.format == 0) {
            this.format = 4;
        }
        Preconditions.checkState(this.format == 4, MISMATCHED_FORMAT);
        if (this.mapValue == null) {
            this.mapValue = new HashMap();
        }
        this.mapValue.put(str, new MapValue(1, i));
        this.hasSet = true;
    }

    @Deprecated
    public void setKeyValue(String str, float f) {
        if (this.format == 0) {
            this.format = 4;
        }
        Preconditions.checkState(this.format == 4, MISMATCHED_FORMAT);
        if (this.mapValue == null) {
            this.mapValue = new HashMap();
        }
        this.mapValue.put(str, new MapValue(2, f));
        this.hasSet = true;
    }

    public void setKeyValue(String str, double d) {
        if (this.format == 0) {
            this.format = 4;
        }
        Preconditions.checkState(this.format == 4, MISMATCHED_FORMAT);
        if (this.mapValue == null) {
            this.mapValue = new HashMap();
        }
        this.mapValue.put(str, new MapValue(2, d));
        this.hasSet = true;
    }

    public void setIntValue(int i) {
        if (this.format == 0) {
            this.format = 1;
        }
        Preconditions.checkState(this.format == 1, MISMATCHED_FORMAT);
        this.floatValue = i >= 0 ? Float.intBitsToFloat(i) : Float.parseFloat(String.valueOf(i));
        this.hasSet = true;
    }

    @Deprecated
    public void setFloatValue(float f) {
        if (this.format == 0) {
            this.format = 2;
        }
        if (this.format != 2) {
            throw new IllegalStateException(MISMATCHED_FORMAT);
        }
        this.floatValue = f;
        this.hasSet = true;
    }

    public void setDoubleValue(double d) {
        if (this.format == 0) {
            this.format = 2;
        }
        Preconditions.checkState(this.format == 2, MISMATCHED_FORMAT);
        this.doubleValue = d;
        this.hasSet = true;
    }

    public void setActivityIndexValue(String str) {
        setIntValue(aacc.aab(str));
    }

    public void removeMapValue(String str) {
        if (this.format == 0) {
            this.format = 4;
        }
        Preconditions.checkState(this.format == 4, MISMATCHED_FORMAT);
        Map<String, MapValue> map = this.mapValue;
        if (map != null) {
            map.remove(str);
        }
    }

    public boolean isSet() {
        return this.hasSet;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.floatValue), this.stringValue, this.mapValue, Double.valueOf(this.doubleValue));
    }

    public MapValue getMapValue(String str) {
        Preconditions.checkState(this.format == 4, MISMATCHED);
        Map<String, MapValue> map = this.mapValue;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.mapValue.get(str);
    }

    public Map<String, MapValue> getMap() {
        Preconditions.checkState(this.format == 4, MISMATCHED);
        if (this.mapValue == null) {
            this.mapValue = new HashMap();
        }
        return this.mapValue;
    }

    public int getFormat() {
        return this.format;
    }

    public boolean equals(Object obj) {
        Object obj2;
        Object obj3;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return false;
        }
        Value value = (Value) obj;
        int i = this.format;
        if (i != value.format || this.hasSet != value.hasSet) {
            return false;
        }
        if (i == 1) {
            return asIntValue() == value.asIntValue();
        }
        if (i == 2) {
            return Math.abs(this.doubleValue - value.doubleValue) < 1.0E-6d;
        }
        if (i != 3) {
            if (i == 4) {
                obj2 = this.mapValue;
                obj3 = value.mapValue;
                return Objects.equal(obj2, obj3);
            }
            if (i != 5) {
                aabz.aab(TAG, "Invalid format");
                return false;
            }
        }
        obj2 = this.stringValue;
        obj3 = value.stringValue;
        return Objects.equal(obj2, obj3);
    }

    public String asStringValue() {
        Preconditions.checkState(this.format == 3, MISMATCHED);
        return this.stringValue;
    }

    public long asLongValue() {
        Preconditions.checkState(this.format == 5, MISMATCHED);
        try {
            return Long.parseLong(this.stringValue, 10);
        } catch (NumberFormatException unused) {
            throw new IllegalStateException(VALUE_PARSE_ERROR);
        }
    }

    public String asLongToStringValue() {
        Preconditions.checkState(this.format == 5, MISMATCHED);
        return this.stringValue;
    }

    public int asIntValue() {
        Preconditions.checkState(this.format == 1, MISMATCHED);
        float f = this.floatValue;
        return f >= 0.0f ? Float.floatToIntBits(f) : Double.valueOf(String.valueOf(f)).intValue();
    }

    @Deprecated
    public float asFloatValue() {
        Preconditions.checkState(this.format == 2, MISMATCHED);
        return this.floatValue;
    }

    public double asDoubleValue() {
        Preconditions.checkState(this.format == 2, MISMATCHED);
        float f = this.floatValue;
        if (f != 0.0f) {
            double d = this.doubleValue;
            if (d == 0.0d || d == -1.0d) {
                this.doubleValue = Double.parseDouble(String.valueOf(f));
            }
        }
        return this.doubleValue;
    }

    public String asActivityType() {
        return aacc.aab(asIntValue());
    }

    @aabw
    public Value(@aabv(id = 1) int i, @aabv(id = 2) boolean z, @aabv(id = 3) String str, @aabv(id = 4) float f, @aabv(id = 5) Map<String, MapValue> map, @aabv(id = 6) double d) {
        this.format = i;
        this.hasSet = z;
        this.stringValue = str;
        this.floatValue = f;
        this.mapValue = map;
        this.doubleValue = d;
    }

    public Value(int i) {
        this.format = i;
        this.hasSet = false;
        this.stringValue = null;
        this.floatValue = 0.0f;
        this.mapValue = new HashMap();
        this.doubleValue = 0.0d;
    }
}
