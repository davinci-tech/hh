package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes.dex */
public class Recurrence implements Parcelable {
    public static final Parcelable.Creator<Recurrence> CREATOR = new Parcelable.Creator<Recurrence>() { // from class: com.huawei.hihealth.model.Recurrence.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwz_, reason: merged with bridge method [inline-methods] */
        public Recurrence createFromParcel(Parcel parcel) {
            return new Recurrence(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Recurrence[] newArray(int i) {
            return new Recurrence[i];
        }
    };
    private int count;
    private int expectedAchievedCount;
    private int unit;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Recurrence(int i) {
        this(i, 0, 0);
    }

    public Recurrence(int i, int i2) {
        this(i, i2, 0);
    }

    public Recurrence(int i, int i2, int i3) {
        this.unit = i;
        this.count = i2;
        this.expectedAchievedCount = i3;
    }

    protected Recurrence(Parcel parcel) {
        this.unit = parcel.readInt();
        this.count = parcel.readInt();
        this.expectedAchievedCount = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.unit);
        parcel.writeInt(this.count);
        parcel.writeInt(this.expectedAchievedCount);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.unit));
    }

    public boolean equals(Object obj) {
        return (obj instanceof Recurrence) && this.unit == ((Recurrence) obj).getUnit();
    }

    public int getUnit() {
        return this.unit;
    }

    public void setUnit(int i) {
        this.unit = i;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public int getExpectedAchievedCount() {
        return this.expectedAchievedCount;
    }

    public void setExpectedAchievedCount(int i) {
        this.expectedAchievedCount = i;
    }
}
