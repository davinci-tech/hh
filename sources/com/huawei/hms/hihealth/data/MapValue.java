package com.huawei.hms.hihealth.data;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;

/* loaded from: classes9.dex */
public class MapValue extends aabq {
    public static final Parcelable.Creator<MapValue> CREATOR = new aabq.aab(MapValue.class);
    private static final double EPSILON = 1.0E-7d;

    @aaby(id = 6)
    private double doubleValue;

    @aaby(id = 2)
    @Deprecated
    private float floatValue;

    @aaby(id = 1)
    private final int format;

    @aaby(id = 3)
    private int intValue;

    @aaby(id = 5)
    private long longValue;

    @aaby(id = 4)
    private String strValue;

    public String toString() {
        int i = this.format;
        return i != 1 ? i != 2 ? i != 3 ? i != 5 ? "unknown format" : String.valueOf(this.longValue) : String.valueOf(this.strValue) : String.valueOf(this.doubleValue) : String.valueOf(this.intValue);
    }

    @Deprecated
    public void setFloatValue(Float f) {
        this.floatValue = f.floatValue();
    }

    public void setDoubleValue(Double d) {
        this.doubleValue = d.doubleValue();
    }

    public int hashCode() {
        return (int) this.doubleValue;
    }

    public int getFormat() {
        return this.format;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MapValue) {
            MapValue mapValue = (MapValue) obj;
            int i = this.format;
            if (i == mapValue.format) {
                if (i == 1) {
                    return this.intValue == mapValue.intValue;
                }
                if (i == 2) {
                    return Math.abs(this.doubleValue - mapValue.doubleValue) < EPSILON;
                }
                if (i != 3) {
                    return i == 5 && this.longValue == mapValue.longValue;
                }
                String str = this.strValue;
                return str != null && str.equals(mapValue.strValue);
            }
        }
        return false;
    }

    public final String asStringValue() {
        Preconditions.checkState(this.format == 3, "Format mismatch");
        return this.strValue;
    }

    public final long asLongValue() {
        Preconditions.checkState(this.format == 5, "Format mismatch");
        return this.longValue;
    }

    public final int asIntValue() {
        Preconditions.checkState(this.format == 1, "Format mismatch");
        return this.intValue;
    }

    @Deprecated
    public final float asFloatValue() {
        Preconditions.checkState(this.format == 2, "Format mismatch");
        return this.floatValue;
    }

    public final double asDoubleValue() {
        Preconditions.checkState(this.format == 2, "Format mismatch");
        float f = this.floatValue;
        if (f != 0.0f) {
            double d = this.doubleValue;
            if (d == 0.0d || d == -1.0d) {
                this.doubleValue = Double.parseDouble(String.valueOf(f));
            }
        }
        return this.doubleValue;
    }

    public MapValue(int i, String str) {
        this.format = i;
        this.strValue = str;
    }

    public MapValue(int i, long j) {
        this.format = i;
        this.longValue = j;
    }

    public MapValue(int i, int i2) {
        this.format = i;
        this.intValue = i2;
    }

    @aabw
    public MapValue(@aabv(id = 1) int i, @aabv(id = 2) float f, @aabv(id = 3) int i2, @aabv(id = 4) String str, @aabv(id = 5) long j, @aabv(id = 6) double d) {
        this.format = i;
        this.floatValue = f;
        this.intValue = i2;
        this.strValue = str;
        this.longValue = j;
        this.doubleValue = d;
    }

    @Deprecated
    public MapValue(int i, float f) {
        this.format = i;
        this.floatValue = f;
    }

    public MapValue(int i, double d) {
        this.format = i;
        this.doubleValue = d;
    }
}
