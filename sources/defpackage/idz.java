package defpackage;

import android.content.ContentValues;

/* loaded from: classes.dex */
public final class idz extends idy {
    private ContentValues mContentValues = new ContentValues();

    public idz() {
    }

    public idz(int i, long j, long j2, int i2, int i3) {
        setType(i);
        setValue(i2);
        setPointUnit(i3);
        setStartTime(j);
        setEndTime(j2);
    }

    public idz(int i, long j, long j2, double d, int i2) {
        setType(i);
        setValue(d);
        setPointUnit(i2);
        setStartTime(j);
        setEndTime(j2);
    }

    public idz(int i, long j, long j2, String str, int i2) {
        setType(i);
        setValue(str);
        setPointUnit(i2);
        setStartTime(j);
        setEndTime(j2);
    }

    public void setValue(double d) {
        this.mContentValues.put("point_value", Double.valueOf(d));
    }

    public void setValue(int i) {
        this.mContentValues.put("point_value", Integer.valueOf(i));
    }

    public void setValue(String str) {
        this.mContentValues.put("metadata", str);
    }

    public int getValue() {
        Integer asInteger = this.mContentValues.getAsInteger("point_value");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }

    public double getDoubleValue() {
        Double asDouble = this.mContentValues.getAsDouble("point_value");
        if (asDouble == null) {
            return -1.0d;
        }
        return asDouble.doubleValue();
    }

    public String getStringValue() {
        String asString = this.mContentValues.getAsString("metadata");
        return asString == null ? "" : asString;
    }

    public void setPointUnit(int i) {
        this.mContentValues.put(" point_unit", Integer.valueOf(i));
    }

    public int getPointUnit() {
        Integer asInteger = this.mContentValues.getAsInteger(" point_unit");
        if (asInteger == null) {
            return 0;
        }
        return asInteger.intValue();
    }
}
